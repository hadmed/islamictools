package com.alpha.param;
import java.util.ArrayList;
import java.util.List;

//import com.alpha.commun.City;
import com.alpha.commun.CityDB;
import com.alpha.commun.utils;
import com.alpha.model.Settings;
import com.alpha.view.Compass;
import com.alpha.view.Menu;
import com.alpha.view.R;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

public class SettingPosition extends Dialog implements OnItemSelectedListener, android.view.View.OnClickListener
{


	//	private Position choix;
	private Settings setting;
	private Context  context;
	private Activity activity;
	private boolean start= false;
	private boolean fromMain;
	
public SettingPosition(Context context,Activity activity,boolean fromMain)
{
	super(context);
	this.context = context;
	this.activity = activity;
	this.fromMain = fromMain;
   ((Compass)activity.findViewById(R.id.bCompassView)).setFreeze(true);
}


private static final class Zone { public static final int CONTINENT = 0,COUNTRY = 1, CITY = 3; };	
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setting = Settings.getInstance(context);
	    //requestWindowFeature(Window.FEATURE_NO_TITLE);
	    // getWindow().setFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND,WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
	    setContentView(R.layout.set_city);
	    setTitle(R.string.set_position);
	    start= false;
	    CityDB cb = new CityDB(context);
 	 	SQLiteDatabase db =  cb.getReadableDatabase();
	    if (db!=null)
	   	 {
	   	 this.setSpinner2(R.id.spinContinent, 1, false, db, "continent", new String[] { "id", "txt"}, Zone.CONTINENT,null);
	   	 this.setSpinner2(R.id.spinCountry, setting.getCountry(), true, db, "country", new String[] { "_id", "pays"}, Zone.COUNTRY,null);
	   	 this.setSpinner2(R.id.spinCity, setting.getCity(), true, db, "city", new String[] { "_id", "ville","region"}, Zone.CITY,"idpays="+setting.getCountry());
	   	 
	   	 db.close();
	   	 
	   	 }
	    start= true;
	    this.infoCity();
	    
	    //((Spinner)findViewById(R.id.spinContinent)).setOnItemSelectedListener(this);
	    ((Spinner)findViewById(R.id.spinCountry)).setOnItemSelectedListener(this);
	    ((Spinner)findViewById(R.id.spinCity)).setOnItemSelectedListener(this);

	    ((Button)findViewById(R.id.param_save)).setOnClickListener(this);
	    ((Button)findViewById(R.id.param_reset)).setOnClickListener(this);
	    //((Button)findViewById(R.id.param_dst)).setOnClickListener(this);
	    ((Button)findViewById(R.id.set_pos_gps)).setOnClickListener(this);

	}

@Override
public void dismiss()
{
	if (fromMain)
		{
		((Compass)activity.findViewById(R.id.bCompassView)).setFreeze(false);
		((Menu)activity).reload();
		}
   // TODO Auto-generated method stub
	super.dismiss();
	
}


private void infoCity()
{
   setText(R.id.latparam, setting.getLatString());
   setText(R.id.lonparam, setting.getLonString());
   setText(R.id.gmtparam, setting.getGmtString());
   setText(R.id.dstparam,(setting.getDst()==1)?"on":"off");		
}


	private void setText(int id,String text)
	{
		TextView txt = (TextView) findViewById(id);
		txt.setText(text);
	}

	private void setSpinner2(int id,int pos,boolean enabled,SQLiteDatabase db,String table,String[] colonne,int zone,String cond)
	{
		try {
		Cursor c = db.query(table, colonne, cond, null, null, null, null);
		activity.startManagingCursor(c);
		List<String> list = new ArrayList<String>(); 
		String region;
		while (c.moveToNext())
		{
			switch (zone)
			{
			case Zone.CONTINENT : 
				list.add(c.getString(1));
				break;
			case Zone.COUNTRY : 
				list.add(c.getString(1));
				break;
			case Zone.CITY : 
				 region = c.getString(2);
				 if (region!=null && !region.trim().equals(""))
				 	{region = " ("+region.trim()+")";} else
				 {region = "";}
				list.add(c.getString(1)+region);
				break;
			
			}
		}
		Spinner spin = (Spinner) findViewById(id);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, list.toArray(new String[0])); 
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spin.setAdapter(adapter);      
		spin.setSelection(pos); //france par defaut
		spin.setEnabled(enabled); 
		c.close();
		} catch(Exception ex) {
			Log.d("sam",ex.getMessage());
		}
	}

private void getGPS()
{
	Location loc = utils.getCurrentLocation(context);	
	if (loc!=null)
	{
		setting.setPosGps(loc.getLatitude(), loc.getLongitude(), loc.getAltitude());
		this.infoCity();
	}
}
	
	
public void onItemSelected(AdapterView<?> source, View view, int idx, long idx2)
{
int idxCountry = setting.getCountry(),idxCity = setting.getCity();
if (start)
{
	switch (source.getId())
	{
	case R.id.spinCity : 
		//Log.d("sam","aa");
		idxCity = idx;
		break;
	case R.id.spinContinent: 
		//Log.d("sam","aa");
		break;
	case R.id.spinCountry : 
		//Log.d("sam","aa");
		if (idx != idxCountry)
			{
			idxCity = 0;
			idxCountry = idx;
			this.reloadCitys(idxCountry,idxCity);	
			}
		break;
	}
	setting.setCity(activity,idxCountry, idxCity);
	this.infoCity();
}
}

private void reloadCitys(int idxCountry,int idxCity)
{
   CityDB cb = new CityDB(context);
	 	SQLiteDatabase db =  cb.getReadableDatabase();
    if (db!=null)
   	 {
   	 this.setSpinner2(R.id.spinCity, idxCity, true, db, "city", new String[] { "_id", "ville","region"}, Zone.CITY,"idpays="+idxCountry);
   	 db.close();
   	 }	
}

public void onNothingSelected(AdapterView<?> arg0)
{
}

public void onClick(View v)
{
switch (v.getId())
{
case R.id.param_save : 
		setting.save();
		dismiss();
break;
case R.id.param_reset : 
	dismiss();
break;
case R.id.set_pos_gps: 
	this.getGPS();
break;

}
}
}
