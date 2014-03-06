package com.artpower.filmaticfestival;

/**
 * 
 */


import java.util.ArrayList;
import java.util.Date;

/**
 * @author Brett Stalbaum
 *
 */
public class EventItem {
	
/* all elements in the rss feed: title, link, comments, pubDate, dc:creator,
 * category(7), guid, description, content:encoded, wfw:commentRss, slash:comments
 * event_location, event_date, event_start_time, event_end_time, event_description
 *
 * we care: title, link, pubDate, category (event_year), category (category),
 * category (5 more as keywords), guid, daily_event (this does not appear in all records),
 * event_location, event_start_time, event_end_time, event_description
 */
	
	private int id;
	private String title = null;
	private String link = null;
	private long pubDate;
	// there are multiple categories in the feed
	private ArrayList<String> keywords = null;
	private boolean dailyEvent = false;
	private String eventLocation = null;
	private long eventStartTime;
	private long eventEndTime;
	private String eventDescription;
	
	public EventItem(	int id, String title, String link, long pubDate,
						ArrayList<String> keywords, boolean dailyEvent, 
						String eventLocation, long eventStartTime, 
						long eventEndTime, String eventDescription) {
		this.id = id;
		this.title = title;
		this.link = link;
		this.pubDate = pubDate;
		this.keywords = keywords;
		this.dailyEvent = dailyEvent;
		this.eventLocation = eventLocation;
		this.eventStartTime = eventStartTime;
		this.eventEndTime = eventEndTime;
		this.eventDescription = eventDescription;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getLink() {
		return link;
	}
	
	public void setLink(String link) {
		this.link = link;
	}
	
	public long getPubDate() {
		return pubDate;
	}
	
	public void setPubDate(long pubDate) {
		this.pubDate = pubDate;
	}
	
	public ArrayList<String> getKeywords() {
		return keywords;
	}
	
	public void setKeywords(ArrayList<String> keywords) {
		this.keywords = keywords;
	}
	
	public boolean isDailyEvent() {
		return dailyEvent;
	}
	
	public void setDailyEvent(boolean dailyEvent) {
		this.dailyEvent = dailyEvent;
	}
	
	public String getEventLocation() {
		return eventLocation;
	}
	
	public void setEventLocation(String eventLocation) {
		this.eventLocation = eventLocation;
	}
	
	public long getEventStartTime() {
		return eventStartTime;
	}
	
	public void setEventStartTime(long eventStartTime) {
		this.eventStartTime = eventStartTime;
	}
	
	public long getEventEndTime() {
		return eventEndTime;
	}
	
	public void setEventEndTime(long eventEndTime) {
		this.eventEndTime = eventEndTime;
	}
	
	public String getEventDescription() {
		return eventDescription;
	}
	
	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}
	
	public String toString() {
		
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < keywords.size(); i++) {
			buf.append(keywords.get(i) + " ");
		}
		return 
			"id: " + id + "\n" + 
			"title: " + title + "\n" + 
			"link: " + link + "\n" +
			"pubDate: " + new Date(pubDate) + "\n" +
			"keywords: " + buf.toString() + "\n" +
			"dailyEvent: " + dailyEvent + "\n" +
			"eventLocation: " + eventLocation + "\n" +
			"eventStartTime: " + new Date(eventStartTime) + "\n" +
			"eventEndTime: " + new Date(eventEndTime) + "\n" +
			"eventDescription: " + eventDescription + "\n\n";
	}

}
