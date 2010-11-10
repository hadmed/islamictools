package com.alpha.view;

import java.util.TimeZone;

import com.alpha.commun.City;
import com.alpha.commun.NumberPicker;
import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class ParamView extends Activity
{

	public void onCreate(Bundle savedInstanceState) {			
	       super.onCreate(savedInstanceState);
	       requestWindowFeature(Window.FEATURE_NO_TITLE);
	       setContentView(R.layout.param);
			NumberPicker lat_deg = (NumberPicker) findViewById(R.id.lat_deg);
			lat_deg.setRange(0, 90);lat_deg.setCurrent(3);lat_deg.setSpeed(50);
			NumberPicker lat_sens = (NumberPicker) findViewById(R.id.lat_sens);
			lat_sens.setRange(0, 1, "Nord,Sud".split(","));lat_sens.setCurrent(0);
			NumberPicker lat_min = (NumberPicker) findViewById(R.id.lat_min);
			lat_min.setRange(0, 59);lat_min.setCurrent(10);lat_min.setSpeed(50);
			
			NumberPicker lon_deg = (NumberPicker) findViewById(R.id.lon_deg);
			lon_deg.setRange(0, 180);lon_deg.setCurrent(3);lon_deg.setSpeed(50);
			NumberPicker lon_sens = (NumberPicker) findViewById(R.id.lon_sens);
			lon_sens.setRange(0, 1, "Est,Ouest".split(","));lon_sens.setCurrent(0);
			NumberPicker lon_min = (NumberPicker) findViewById(R.id.lon_min);
			lon_min.setRange(0, 59);lon_min.setCurrent(10);lon_min.setSpeed(50);

			TimeZone tz= TimeZone.getDefault();
			String[] ids = tz.getAvailableIDs();
			
			//tz.getDisplayName(locale)
        Spinner s1 = (Spinner) findViewById(R.id.gmt_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, ids);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s1.setAdapter(adapter);
	      
/*	 		Object o = findViewById(R.id.picker);
			try 
			{
			    Method m =  (o.getClass()).getMethod("setRange", int.class, int.class);
			    m.invoke(o, 0, 9);
			} 
			catch (Exception e) 
			{
			    Log.e("", e.getMessage());
			}
			
*/
	}

}
