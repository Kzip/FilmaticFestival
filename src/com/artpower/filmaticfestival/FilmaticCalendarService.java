/**
 * 
 */
package com.artpower.filmaticfestival;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.IBinder;

/**
 * @author Brett Stalbaum
 *
 */
public class FilmaticCalendarService extends Service {
	
	private Timer timer = null;
	FilmaticApplication app = null;
	
	@Override
	public void onCreate() {
		// start the timer
		app = (FilmaticApplication)getApplication();
		startTimer();
	}
	
	private void startTimer() {
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				// check for an internet connection
				ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
				NetworkInfo ni = cm.getActiveNetworkInfo();
				if (ni != null && ni.isConnected()) {
					// get the Application object
					FilmaticApplication app = (FilmaticApplication)getApplication();
					app.readRSSFeed(); // this starts the whole feed update process
				} // else just return and wait for next time
			}
		};
		timer = new Timer(true);
		int time = 1000 * 60 * 60 * 2; // update every two hours
		timer.schedule(task, 0, time);
	}
	
	private void stopTimer() {
		if (timer != null) {
			timer.cancel();
		}
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int id) {
		return START_STICKY;
	}

	/* (non-Javadoc)
	 * This method must be implemented for all services
	 * @see android.app.Service#onBind(android.content.Intent)
	 */
	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}
	
	@Override
	public void onDestroy() {
		stopTimer();
	}

}
