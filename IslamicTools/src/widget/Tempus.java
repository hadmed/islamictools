package widget;

import java.util.Calendar;

import com.alpha.commun.HadithList;
import com.alpha.commun.MajService;
import com.alpha.commun.MsgNotification;
import com.alpha.model.PT;
import com.alpha.model.PrayerTime;
import com.alpha.model.Settings;
//import com.alpha.view.ParamView;
import com.alpha.view.R;

//import android.app.PendingIntent;
import android.app.Notification;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
//import android.content.res.Resources;
//import android.os.Vibrator;
import android.util.Log;
import android.widget.RemoteViews;

public class Tempus extends AppWidgetProvider
{
private static String TAG = "sam";
	
	
@Override
public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
   Log.d(TAG, "onUpdate");

   if (appWidgetIds == null) {
   	appWidgetIds = appWidgetManager.getAppWidgetIds(
              new ComponentName(context, Tempus.class));
  }

   
   appWidgetManager.updateAppWidget(appWidgetIds[0],  updateAppWidget(context));

  MajService.requestUpdate(appWidgetIds);
  context.startService(new Intent(context, MajService.class));


}

@Override
public void onDeleted(Context context, int[] appWidgetIds) {
    Log.d(TAG, "onDeleted");
}


@Override
public void onEnabled(Context context) {
    Log.d(TAG, "onEnabled");
}

@Override
public void onDisabled(Context context) {
    // When the first widget is created, stop listening for the TIMEZONE_CHANGED and
    // TIME_CHANGED broadcasts.
    Log.d(TAG, "onDisabled");
}


public void onReceived(Context context){
   Log.d(TAG, "onReceived");
}

public static RemoteViews updateAppWidget(Context context) {
  /*Intent intent = new Intent(context, ParamView.class);
  PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
  RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.tempus_widget);
  views.setOnClickPendingIntent( R.id.widgetClick, pendingIntent);
*/
  
     //special SAM
  Intent updateIntent = new Intent(MajService.ACTION_UPDATE_ALL);
  updateIntent.setClass(context, MajService.class);
  PendingIntent pendingIntent = PendingIntent.getService(context, 0, updateIntent, 0);

  RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.tempus_widget);
  views.setOnClickPendingIntent( R.id.widgetClick, pendingIntent);
  
  
  Settings settings = Settings.getInstance(context);
	Calendar now;
	now = Calendar.getInstance();

	PrayerTime[] prayerTimes = PT.getPrayerTimes(now.get(Calendar.YEAR), now.get(Calendar.MONTH) +1, now.get(Calendar.DAY_OF_MONTH), settings.getLat(), settings.getLon(), settings.getGmt(),0,settings.getMethod());
	
	now.add(Calendar.MINUTE, settings.getAlert_before());
	now.set(Calendar.SECOND, 0);
	now.set(Calendar.MILLISECOND, 0);

	for (int k=1;k<5;k++)
	{
		if(now.equals(prayerTimes[k].getCalendar2()))
		{
		     MsgNotification.start(context, "C'est l'heure de la prière !" , Notification.DEFAULT_ALL);
			
			/*Vibrator v = (Vibrator)context.getSystemService(Context.VIBRATOR_SERVICE);  
			long milliseconds = 1000;  
			v.vibrate(milliseconds);  */
		}
	}
	now = Calendar.getInstance();
	now.add(Calendar.MINUTE, -15); //la selection de la prière reste 15 minutes après l'heure
	int idxSelected = R.id.wi_shurooq;
	if (now.before(prayerTimes[1].getCalendar())) {idxSelected = R.id.wi_fadjr; }
	else if (now.before(prayerTimes[2].getCalendar())) {idxSelected = R.id.wi_zuhr;}
	else if (now.before(prayerTimes[3].getCalendar())) {idxSelected = R.id.wi_asr;}
	else if (now.before(prayerTimes[4].getCalendar())) {idxSelected = R.id.wi_maghrib;}
	else if (now.after(prayerTimes[4].getCalendar())) {idxSelected = R.id.wi_isha;}
	
  views.setTextViewText(R.id.wi_fadjr, prayerTimes[0].getTime2());
  views.setTextColor(R.id.wi_fadjr, 0xFFFFFFFF); 
  views.setTextViewText(R.id.wi_shurooq, prayerTimes[1].getTime2());
  views.setTextColor(R.id.wi_shurooq, 0xFFFFFFFF); 
  views.setTextViewText(R.id.wi_zuhr, prayerTimes[2].getTime2());
  views.setTextColor(R.id.wi_zuhr, 0xFFFFFFFF); 
  views.setTextViewText(R.id.wi_asr, prayerTimes[3].getTime2());
  views.setTextColor(R.id.wi_asr, 0xFFFFFFFF); 
  views.setTextViewText(R.id.wi_maghrib, prayerTimes[4].getTime2());
  views.setTextColor(R.id.wi_maghrib, 0xFFFFFFFF); 
  views.setTextViewText(R.id.wi_isha, prayerTimes[5].getTime2());
  views.setTextColor(R.id.wi_isha, 0xFFFFFFFF);
  views.setTextColor(idxSelected, 0xFFFF0000);
   views.setTextViewText(R.id.widgetClick, HadithList.getInstance(context).getHadith(-1));
   views.setTextViewText(R.id.srcHadith, "rapporté par "+HadithList.getInstance(context).getSourceHadith()+"  ");
 return views;
}

/**
 * Build an update for the given medium widget. Should only be called from a
 * service or thread to prevent ANR during database queries.
 */
/*
public static RemoteViews buildUpdate(Context context) {
    Log.d(TAG, "Building medium widget update");

    RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.tempus_widget);


    //ContentResolver resolver = context.getContentResolver();
    //Resources res = context.getResources();


    // Connect click intent to launch details dialog
    Intent detailIntent = new Intent(context, ParamView.class);
    //detailIntent.setData(appWidgetUri);
    PendingIntent pending = PendingIntent.getActivity(context, 0, detailIntent, 0);
    views.setOnClickPendingIntent(R.id.widgetClick, pending);

    return views;
}*/


}
