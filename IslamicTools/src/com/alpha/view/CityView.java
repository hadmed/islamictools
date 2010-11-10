package com.alpha.view;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import com.alpha.commun.City;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class CityView extends Activity
{
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    requestWindowFeature(Window.FEATURE_NO_TITLE);
	    setContentView(R.layout.city);
	    this.setSpinner(R.id.spinContinent, City.Continent, 1, false);
	    this.setSpinner(R.id.spinCountry, City.Pays, 79, true);
	    int idxPays = 410;
	    this.setSpinner(R.id.spinCity, City.city, idxPays, true);
	    int[] pos = City.pos[idxPays];
	    Position pp = new Position(pos[0],pos[1],pos[2],pos[3]);
	    setText(R.id.latparam, pp.getLatitude());
	    setText(R.id.lonparam, pp.getLongitude());
	    setText(R.id.gmtparam, pp.getTimezone());
	    setText(R.id.dstparam,pp.isDaylight()?"on":"off");
     	}

	private void setText(int id,String text)
	{
		TextView txt = (TextView) findViewById(id);
		txt.setText(text);
	}
	private void setSpinner(int id,String[] src,int pos,boolean enabled)
	{
	    Spinner spin = (Spinner) findViewById(id);
	    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, src); 
	     adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	     spin.setAdapter(adapter);      
	     spin.setSelection(pos); //france par defaut
	     spin.setEnabled(enabled); 
	}
	
   private List<String> getCity() {
      List<String>dataList = new ArrayList<String>();
      XmlPullParserFactory pullMaker;
      try {
          pullMaker = XmlPullParserFactory.newInstance();
          XmlPullParser parser = pullMaker.newPullParser();
          InputStream fis = getAssets().open("xml/gps.xml");
          parser.setInput(fis, null);
          int eventType = parser.getEventType();
       	/*String city ="";
       	String latitude ="",longitude ="",timezone ="",daylight ="";
       	boolean inLatitude =false,inLongitude =false,inTimezone =false,inDaylight =false;
       	Position pos ;*/
       	
          while (eventType != XmlPullParser.END_DOCUMENT) {
              switch (eventType) {
              case XmlPullParser.START_DOCUMENT:
                  break;
              case XmlPullParser.START_TAG:
                  if (parser.getName().compareTo("country") == 0) {
                  	dataList.add(parser.getAttributeValue(null, "name"));
                  	//ss.append(parser.getAttributeValue(null, "name")+",");
                  } else if (parser.getName().compareTo("city") == 0) {
                  	//inLatitude =false;inLongitude =false;inTimezone =false;inDaylight =false;
                  } else if (parser.getName().compareTo("latitude") == 0) {
                  	//inLatitude =true;inLongitude =false;inTimezone =false;inDaylight =false;
                  } else if (parser.getName().compareTo("longitude") == 0) {
                     
                  } else if (parser.getName().compareTo("timezone") == 0) {
                     
                  } else if (parser.getName().compareTo("daylight") == 0) {
                     
                  } 
                  	
                  break;
              case XmlPullParser.END_TAG:
                  if (parser.getName().equals("country")) {
                  	//pos = new Position(0, 0, 0, 0);
                  	
                  } 
                  break;
              case XmlPullParser.TEXT:
                 
                  break;

              }
              eventType = parser.next();
          }

      } catch (Exception e) {
          Log.e("sam", "Pull parser failed", e);
      }
      return dataList;
  }

 

public class Position{
	private int latitude,longitude,timezone,daylight;
	public Position(int latitude,int longitude,int timezone,int daylight)
	{
		this.latitude = latitude;
		this.longitude = longitude;
		this.timezone = timezone;
		this.daylight = daylight;
	}
	public String getLatitude()
	{
		return ""+((int)this.latitude/10000)+"°"+((int)this.latitude%10000);
	}
	public String getLongitude()
	{
		return ""+((int)this.longitude/10000)+"°"+((int)this.longitude%10000);
	}
	public String getTimezone()
	{
		return ""+((int)this.timezone/100)+":"+((int)this.timezone%100);
	}
	public boolean isDaylight()
	{
		return this.daylight==1;
	}	
}
}
