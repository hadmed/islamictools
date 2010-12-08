package com.alpha.commun;

import com.alpha.view.R;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;

public class MsgNotification {
	private static Context context;
	private static Notification notification;

	public static void start(Context context, short timeIndex, long actualTime) {
		MsgNotification.context = context;
		notification = new Notification(R.drawable.islamic,"Islamic Tools by Selim Amroune",1000);
		notification.tickerText = "Islamic Tools by Selim Amroune";
		stopNotification();
		startNotification();		
	}

	public static void stopNotification() {
		if(context != null) ((NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE)).cancelAll();
	}
	
	private static void startNotification() {
		Intent i = new Intent(context, Menu.class);
		notification.setLatestEventInfo(context, context.getString(R.string.app_name), notification.tickerText, PendingIntent.getActivity(context, 0, i, PendingIntent.FLAG_CANCEL_CURRENT | PendingIntent.FLAG_ONE_SHOT));
		//notification.contentIntent = PendingIntent.getBroadcast(context, 0, new Intent(context, ClickNotificationReceiver.class), PendingIntent.FLAG_CANCEL_CURRENT | PendingIntent.FLAG_ONE_SHOT);
		//notification.deleteIntent = PendingIntent.getBroadcast(context, 0, new Intent(context, ClearNotificationReceiver.class), PendingIntent.FLAG_CANCEL_CURRENT | PendingIntent.FLAG_ONE_SHOT);
		((NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE)).notify(1, notification);
	}

	
}
