package com.alpha.view;


import java.util.Calendar;

import com.alpha.commun.HadithList;
import com.alpha.model.PT;
import com.alpha.model.PrayerTime;
import com.alpha.model.Settings;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

public class Menu extends Activity {
    /** Called when the activity is first created. */
   private Context mContext; 

@Override
    public void onCreate(Bundle savedInstanceState) {
   	 
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND,
              WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
        setContentView(R.layout.menu);
        mContext= this;
        setTimePrayer();
        setImageClick();     
    }


private void setTimePrayer()
{
	  //settings.getDst()
   Settings settings = Settings.getInstance(this);
	Calendar now = Calendar.getInstance();
	Log.d("sam", "dst : "+settings.getDst());
	PrayerTime[] prayerTimes = PT.getPrayerTimes(now.get(Calendar.YEAR), now.get(Calendar.MONTH) +1, now.get(Calendar.DAY_OF_MONTH), settings.getLat(), settings.getLon(), settings.getGmt2(),0,settings.getMethod());
	((TextView)findViewById(R.id.pt_fadjr)).setText(prayerTimes[0].getTime());
	((TextView)findViewById(R.id.pt_shurooq)).setText(prayerTimes[1].getTime());
	((TextView)findViewById(R.id.pt_zuhr)).setText(prayerTimes[2].getTime());
	((TextView)findViewById(R.id.pt_asr)).setText(prayerTimes[3].getTime());
	((TextView)findViewById(R.id.pt_maghrib)).setText(prayerTimes[4].getTime());
	((TextView)findViewById(R.id.pt_isha)).setText(prayerTimes[5].getTime());
	
	((TextView)findViewById(R.id.pt_location)).setText(settings.getLocation());

	((TextView)findViewById(R.id.pt_qibla)).setText(settings.getAngleQibla());
	
	((TextView)findViewById(R.id.wHadith)).setText(HadithList.getRadomHadith());
	((TextView)findViewById(R.id.wHadithSrc)).setText("rapporté par "+HadithList.getSourceHadith()+" ");

}

private void setImageClick()
{

   ((ImageView)findViewById(R.id.bQuran)).setOnClickListener(new View.OnClickListener() {
	  public void onClick(View view) {startActivity(new Intent(mContext, QuranView.class));}});

   ((Compass)findViewById(R.id.bCompassView)).setOnClickListener(new View.OnClickListener() {
 	  public void onClick(View view) {startActivity(new Intent(mContext, CompassTest.class));}});
   
  ((ImageView)findViewById(R.id.bMap)).setOnClickListener(new View.OnClickListener() {
  	  public void onClick(View view) {startActivity(new Intent(mContext, SamMapView.class));}});
   
   ((ImageView)findViewById(R.id.bName)).setOnClickListener(new View.OnClickListener() {
  	  public void onClick(View view) {startActivity(new Intent(mContext, NameAllah.class));}});

   ((ImageView)findViewById(R.id.bHadith)).setOnClickListener(new View.OnClickListener() {
   	  public void onClick(View view) {startActivity(new Intent(mContext, HadithView.class));}});

   ((ImageView)findViewById(R.id.bParam)).setOnClickListener(new View.OnClickListener() {
   	  public void onClick(View view) {startActivity(new Intent(mContext, ParamView.class));}});

	((TextView)findViewById(R.id.pt_location)).setOnClickListener(new View.OnClickListener() {
 	  public void onClick(View view) {startActivity(new Intent(mContext, ConfigCityView.class));}});

	((TableLayout)findViewById(R.id.pt_table)).setOnClickListener(new View.OnClickListener() {
 	  public void onClick(View view) {startActivity(new Intent(mContext, CalendarView.class));}});
   
	/*"Coran","Horaire Prière","Boussole","Doua'a","99 noms d'Allah","Sermon Vendredi","Réglage","Exit"*/
	
}

public Context getMContext()
{
	return this.mContext;
}

public void setMContext(Context context)
{
	this.mContext = context;
}
	
	
}