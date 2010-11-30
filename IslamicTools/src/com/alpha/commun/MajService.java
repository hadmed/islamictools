package com.alpha.commun;

import java.util.LinkedList;
import java.util.Queue;

import widget.Tempus;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProviderInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.text.format.DateUtils;
import android.text.format.Time;
import android.widget.RemoteViews;

/**
 * Background service to build any requested widget updates. Uses a single
 * background thread to walk through an update queue, querying
 * {@link WebserviceHelper} as needed to fill database. Also handles scheduling
 * of future updates, usually in 6-hour increments.
 */
public class MajService extends Service implements Runnable {
    private static final long UPDATE_INTERVAL = 1 * DateUtils.MINUTE_IN_MILLIS;
    private static final long UPDATE_THROTTLE = 1 * DateUtils.MINUTE_IN_MILLIS;
    private static Object sLock = new Object();

    /**
     * Specific {@link Intent#setAction(String)} used when performing a full
     * update of all widgets, usually when an update alarm goes off.
     */
    public static final String ACTION_UPDATE_ALL = "com.alpha.commun.UPDATE_ALL";
    /**
     * Flag if there is an update thread already running. We only launch a new
     * thread if one isn't already running.
     */
    private static boolean sThreadRunning = false;

    /**
     * Internal queue of requested widget updates. You <b>must</b> access
     * through {@link #requestUpdate(int[])} or {@link #getNextUpdate()} to make
     * sure your access is correctly synchronized.
     */
    private static Queue<Integer> sAppWidgetIds = new LinkedList<Integer>();

    /**
     * Request updates for the given widgets. Will only queue them up, you are
     * still responsible for starting a processing thread if needed, usually by
     * starting the parent service.
     */
    public static void requestUpdate(int[] appWidgetIds) {
        synchronized (sLock) {
            for (int appWidgetId : appWidgetIds) {
                sAppWidgetIds.add(appWidgetId);
            }
        }
    }

    /**
     * Peek if we have more updates to perform. This method is special because
     * it assumes you're calling from the update thread, and that you will
     * terminate if no updates remain. (It atomically resets
     * {@link #sThreadRunning} when none remain to prevent race conditions.)
     */
    private static boolean hasMoreUpdates() {
        synchronized (sLock) {
            boolean hasMore = !sAppWidgetIds.isEmpty();
            if (!hasMore) {
                sThreadRunning = false;
            }
            return hasMore;
        }
    }

    /**
     * Poll the next widget update in the queue.
     */
    private static int getNextUpdate() {
        synchronized (sLock) {
            if (sAppWidgetIds.peek() == null) {
                return AppWidgetManager.INVALID_APPWIDGET_ID;
            } else {
                return sAppWidgetIds.poll();
            }
        }
    }

    /**
     * Start this service, creating a background processing thread, if not
     * already running. If started with {@link #ACTION_UPDATE_ALL}, will
     * automatically add all widgets to the requested update queue.
     */
    @Override
    public void onStart(Intent intent, int startId) {
   	 super.onStart(intent, startId);
        // If requested, trigger update of all widgets
        if (ACTION_UPDATE_ALL.equals(intent.getAction())) {
            AppWidgetManager manager = AppWidgetManager.getInstance(this);
            requestUpdate(manager.getAppWidgetIds(new ComponentName(this, Tempus.class)));
        }
        // Only start processing thread if not already running
        synchronized (sLock) {
            if (!sThreadRunning) {
                sThreadRunning = true;
                new Thread(this).start();
            }
        }
    }

    /**
     * Main thread for running through any requested widget updates until none
     * remain. Also sets alarm to perform next update.
     */
    public void run() {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        //ContentResolver resolver = getContentResolver();
        //long now = System.currentTimeMillis();
        while (hasMoreUpdates()) {
            int appWidgetId = getNextUpdate();
            // Process this update through the correct provider
            AppWidgetProviderInfo info = appWidgetManager.getAppWidgetInfo(appWidgetId);
            String providerName = info.provider.getClassName();
            RemoteViews updateViews = null;

            if (providerName.equals(Tempus.class.getName())) {
            	//updateViews = Tempus.buildUpdate(this);
            	updateViews = Tempus.updateAppWidget(this);
                //@SAM  -- updateViews = Tempus.buildUpdate(this, appWidgetUri);
            } 
            
            /*else if (providerName.equals(TinyAppWidget.class.getName())) {
                updateViews = TinyAppWidget.buildUpdate(this, appWidgetUri);
            }*/

            // Push this update to surface
            if (updateViews != null) {
                appWidgetManager.updateAppWidget(appWidgetId, updateViews);
            }
        }

        // Schedule next update alarm, usually just before a 6-hour block. This
        // triggers updates at roughly 5:50AM, 11:50AM, 5:50PM, and 11:50PM.
        Time time = new Time();
        time.set(System.currentTimeMillis() + UPDATE_INTERVAL);
        /*time.hour -= (time.hour % 6);
        time.minute = 0;
        time.second = 0;*/

        long nextUpdate = time.toMillis(false);
        long nowMillis = System.currentTimeMillis();

        // Throttle our updates just in case the math went funky
        if (nextUpdate - nowMillis < UPDATE_THROTTLE) {
            nextUpdate = nowMillis + UPDATE_THROTTLE;
        }

        //long deltaMinutes = (nextUpdate - nowMillis) / DateUtils.MINUTE_IN_MILLIS;
        
        Intent updateIntent = new Intent(ACTION_UPDATE_ALL);
        updateIntent.setClass(this, MajService.class);

        PendingIntent pendingIntent = PendingIntent.getService(this, 0, updateIntent, 0);

        // Schedule alarm, and force the device awake for this update
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, nextUpdate, pendingIntent);

        // No updates remaining, so stop service
        stopSelf();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
