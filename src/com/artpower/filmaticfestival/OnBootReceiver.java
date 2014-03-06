/**
 * 
 */
package com.artpower.filmaticfestival;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Launches the calendar service on boot
 * @author Brett Stalbaum
 *
 */
public class OnBootReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// if the service has not already been started by the Application, don't restart
		if (!FilmaticApplication.isCalendarServiceRunning(context)) {
			Intent serviceIntent = new Intent(context, FilmaticCalendarService.class);
			context.startService(serviceIntent);
			Log.println(Log.ASSERT, "started it in BroadcastReciever", "started");
		} else Log.println(Log.ASSERT, "NOT started it in BroadcastReciever", "started");
	}

}
