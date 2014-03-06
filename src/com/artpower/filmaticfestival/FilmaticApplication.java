package com.artpower.filmaticfestival;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Standard Application class
 */

/**
 * @author Brett Stalbaum
 *
 */
public class FilmaticApplication extends Application {
	
	public static final String urlString = "http://client.frankshanley.com/filmatic/feed/?post_type=event";
	public static String RSSString = null;
	
	@Override
	public void onCreate() {
		// start the service if not running (most of the time is should be
		// because we start it in OnBootReceiver.java
		if (!isCalendarServiceRunning(this)) {
			Intent intent = new Intent(this, FilmaticCalendarService.class);
			startService(intent);
		}
		Log.println(Log.ASSERT, "onCreate()", this.toString());
		
	}
	
	// method returns true if the service is running
	protected static boolean isCalendarServiceRunning(Context context) {
	    ActivityManager manager = (ActivityManager)context.getSystemService(ACTIVITY_SERVICE);
	    List<RunningServiceInfo> rsis = manager.getRunningServices(Integer.MAX_VALUE);
	    for (int i = 0; i < rsis.size(); i ++) {
	        if (FilmaticCalendarService.class.getName().equals(rsis.get(i).service.getClassName())) {
	            return true;
	        }
	    }
	    return false;
	}
	
	// method triggers the entire update process (called only by FilmaticCalendarService)
	public void readRSSFeed() {
		Thread t = new Thread(
				new Runnable() {
					@Override
					public void run() {
						try {
							// get the stream
							URL url = new URL(urlString);
							InputStream in = url.openStream();
							// download
							byte[] byteBuf = new byte[2048];
							StringBuffer strBuf = new StringBuffer();
							int readLen = in.read(byteBuf); // priming read
							while (readLen != -1) {
								strBuf.append(new String(byteBuf, 0, readLen, "UTF-8"));
								readLen = in.read(byteBuf);
							}
							RSSString = strBuf.toString();
							if (RSSString.length() > 0) {
								processFeed(RSSString);
							} else { // else just wait for next time
								Log.println(Log.ASSERT, "empty feed", "" + RSSString.length());
							}
							
						} catch (Exception e) {
							Log.e(e.getMessage(), e.toString());
						}
					}
				}
			);
		t.start();
	}
	
	private static ArrayList<EventItem> processFeed(String RSSString) {
		ArrayList<EventItem> list = new ArrayList<EventItem>();
		DocumentBuilderFactory dBuilder = DocumentBuilderFactory.newInstance();

		// read the document, parse out the data
		try {
			DocumentBuilder parser = dBuilder.newDocumentBuilder();
			parser.setErrorHandler(new ErrorHandler() {
				@Override
				public void warning(SAXParseException e) {
					//System.out.println("warn: " + e.getMessage());
					// Log.e(e.toString(), e.getMessage());
				}

				@Override
				public void error(SAXParseException e) {
					// Log.e(e.toString(), e.getMessage());
					//System.out.println("error: " + e.getMessage());
				}

				@Override
				public void fatalError(SAXParseException e)
						throws SAXParseException {
					// Log.e(e.toString(), e.getMessage());
					//System.out.println("fatal: " + e.getMessage());
					throw e;
				}
			});
			// get the date and store it
			Document doc = parser.parse(new ByteArrayInputStream(RSSString.getBytes("UTF-8")));
			
			// read the channel data, we only care about the date actually
			NodeList nodeList = doc.getElementsByTagName("lastBuildDate");
			Node item = nodeList.item(0); // there is only one "lastBuildDate"
			// get the date and store it...
			// convert to millis
			
			// save it to the App
			System.out.println(getRFC822Milliseconds(item.getTextContent()));
			
			// continue processing
			nodeList = doc.getElementsByTagName("item");
			int id = -1;
			String title = null;
			String link = null;
			long pubDate = Long.MIN_VALUE;
			// there are multiple categories in the feed
			// eventYear and category are important to this app
			// the rest are keywords
			ArrayList<String> keywords = new ArrayList<String>(10);
			boolean dailyEvent = false;
			String eventLocation = null;
			long eventStartTime = Long.MIN_VALUE;
			long eventEndTime= Long.MIN_VALUE;
			String eventDescription = null;
						
			for (int i = 0; i < nodeList.getLength(); i++) {
				NodeList subList = nodeList.item(i).getChildNodes();
				
				// clear the local data
				id = -1;
				title = null;
				link = null;
				pubDate = Long.MIN_VALUE;
				// there are multiple categories in the feed
				// eventYear and category are important to this app
				// the rest are keywords
				keywords = new ArrayList<String>(10);
				dailyEvent = false;
				eventLocation = null;
				eventStartTime = Long.MIN_VALUE;
				eventEndTime= Long.MIN_VALUE;
				eventDescription = null;
				
				for (int j = 0; j < subList.getLength(); j++) {
					Node temp = subList.item(j);
					String element = temp.getNodeName();
					if (element.contains("title")) {
						title = temp.getTextContent();
						//System.out.print(title + "*");
					} else if (element.contains("link")) {
						link = temp.getTextContent();
						//System.out.print(link + "*");
					} else if (element.contains("pubDate")) {
						pubDate = getRFC822Milliseconds(temp.getTextContent());
						//System.out.print(pubDate + "*");
					} else if (element.contains("category")) {
						keywords.add(temp.getTextContent());
						//System.out.print(temp.getTextContent() + "*");
					} else if (element.contains("guid")) {
						String tempString = temp.getTextContent();
						id = Integer.parseInt(tempString.substring(tempString.lastIndexOf("p=")+2, tempString.length()));
					} else if (element.contains("daily_event")) { // (this does not appear in all records)
						dailyEvent = temp.getTextContent().contains("1");
					} else if (element.contains("event_location")) {
						eventLocation = temp.getTextContent();
					} else if (element.contains("event_start_time")) {
						eventStartTime = Long.parseLong(temp.getTextContent() + "000"); // start time and end time are in seconds, not millis
					} else if (element.contains("event_end_time")) {
						eventEndTime = Long.parseLong(temp.getTextContent() + "000");
					} else if (element.contains("event_description")) {
						eventDescription = temp.getTextContent();
					}
				}
				list.add(new EventItem(
						id, title, link, pubDate, keywords,
						dailyEvent, eventLocation, eventStartTime,
						eventEndTime, eventDescription
						)
				);
			}

		} catch (SAXException e) {
			//System.out.println("Sax: " + e.getMessage());
			// Log.e(e.toString(), e.getMessage());
		} catch (IOException e) {
			//System.out.println("IO: " + e.getMessage());
			e.printStackTrace();
			// Log.e(e.toString(), e.getMessage());
		} catch (ParserConfigurationException e) {
			//System.out.println("Parser: " + e.getMessage());
			// e1.printStackTrace();
		}
		return list;

	}
	
	public static long getRFC822Milliseconds(String rfc822String) {
		String rfc822Pattern = "EEE, dd MMM yyyy HH:mm:ss Z";
		SimpleDateFormat format = new SimpleDateFormat(rfc822Pattern, Locale.US);
		Date date = null;
		try {
			date = format.parse(rfc822String);
		} catch (DOMException e1) {
			return 0;
		} catch (ParseException e1) {
			return 0;
		}
		return date.getTime();
	}

}
