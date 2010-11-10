package com.alpha.view;

import java.util.Calendar;

import com.alpha.model.PT;
import com.alpha.model.PrayerTime;
import com.alpha.model.Settings;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CalendarView extends ListActivity 
{
	  private static class EfficientAdapter extends BaseAdapter {      
		  
			 private LayoutInflater mInflater;
		     public EfficientAdapter(Context context) {
		   	  mInflater = LayoutInflater.from(context);
		     }
		     public int getCount() {
		         return Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH)+1;
		     }
		     public Object getItem(int position) {
		         return position;
		     }
		     public long getItemId(int position) {
		         return position;
		     }
		     public View getView(int position, View convertView, ViewGroup parent) {
		         ViewHolder holder;
		         if (convertView == null) {
		             convertView = mInflater.inflate(R.layout.calendar, null);
		             holder = new ViewHolder();
		             holder.date = (TextView) convertView.findViewById(R.id.calDate);
		             holder.fajr = (TextView) convertView.findViewById(R.id.calFajr);
		             holder.shurooq = (TextView) convertView.findViewById(R.id.calShurooq);
		             holder.zuhr = (TextView) convertView.findViewById(R.id.calZuhr);
		             holder.asr = (TextView) convertView.findViewById(R.id.calAsr);
		             holder.maghrib = (TextView) convertView.findViewById(R.id.calMaghrib);
		             holder.isha = (TextView) convertView.findViewById(R.id.calIsha);
		             convertView.setTag(holder);

		         } else {
		             holder = (ViewHolder) convertView.getTag();
		         }
		         
		         try
		         {    switch (position)
		         	{
		         case 0:holder.date.setText("Date");
	             holder.fajr.setText("Fajr");
	             holder.shurooq.setText("Shurooq");
	             holder.zuhr.setText("Zuhr");
	             holder.asr.setText("Asr");
	             holder.maghrib.setText("Maghrib");
	             holder.isha.setText("Isha");
		         	break;
		         default :
		            Settings settings = Settings.getInstance();
		      		Calendar now = Calendar.getInstance();
		      	PrayerTime[] prayerTimes = PT.getPrayerTimes(now.get(Calendar.YEAR), now.get(Calendar.MONTH) +1, position, settings.getLat(), settings.getLon(), settings.getGmt(),0,settings.getMethod());
		      	
		      		holder.date.setText(""+(position)+"/"+(now.get(Calendar.MONTH) +1));
		            holder.fajr.setText(prayerTimes[0].getTime2());
		            holder.shurooq.setText(prayerTimes[1].getTime2());
		            holder.zuhr.setText(prayerTimes[2].getTime2());
		            holder.asr.setText(prayerTimes[3].getTime2());
		            holder.maghrib.setText(prayerTimes[4].getTime2());
		            holder.isha.setText(prayerTimes[5].getTime2());
		         	}
		         }catch(Exception ex){
		         }
		         return convertView;
		     }

		     static class ViewHolder {
		     	TextView date;
		     	TextView fajr;
		     	TextView shurooq;
		     	TextView zuhr;
		     	TextView asr;
		     	TextView maghrib;
		     	TextView isha;
		     }

			public LayoutInflater getMInflater()
			{
				return this.mInflater;
			}
		     
		     
			}
			  
			public void onCreate(Bundle savedInstanceState) {
			       super.onCreate(savedInstanceState);
			       requestWindowFeature(Window.FEATURE_NO_TITLE);	       
			       setListAdapter(new EfficientAdapter(this));
			   }
/*
		   protected void onListItemClick(ListView l, View v, int position, long id) {
		      //Map map = (Map) l.getItemAtPosition(position);
		      //Intent intent = (Intent) map.get("intent");
		   	Intent intent = new Intent(this,Sourate.class);
		   	//Next create the bundle and initialize it
		   	Bundle bundle = new Bundle();
		   	//Add the parameters to bundle as
		   	bundle.putInt("sourate", position);
		   	bundle.putString("titre", SOURATE[position] + ("".equals(SOURATE_FR[position])?"":(" ("+SOURATE_FR[position]+")")));
		   	intent.putExtras(bundle);
		   	startActivity(intent);
		  }
*/		
			

}
