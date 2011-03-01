
package com.alpha.model;

import java.util.TimeZone;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.alpha.commun.CityDB;
import com.alpha.commun.Param;
import com.alpha.commun.Utils;


public class Settings {
	private long lat;
	private long lon;
	private int alt;
	private String location;
//	private double speed=0;	
	private int gmt;
	private int dst;
	private int country;
	private int city;
	private float angle;
	private int method;
	private int notification;
	private int alert_before;
	private Context context;
	private String folderQuran;
	private float[] offset;
	
	
	
	public static int test = 0;
	
	private static Settings instance;
   private static double qibla_Lat = 21.4225,qibla_Lon = 39.826111;
	
	public static Settings getInstance(Context context) {
		if(instance == null)
			instance = new Settings(context);
		return instance;
	}
	
	private Settings(Context context) {
		this.context = context;
		SharedPreferences param = context.getSharedPreferences(Param.NAME, 0);

		if (param.getInt("version", 0)!=Param.VERSION_PARAM)
		{
			this.reset();
		}
		this.country = param.getInt("country", 79 );
		this.city = param.getInt("city", 410);
		this.location = param.getString("location", "FR, Paris");
		//lat = 50.7323f;  //507283
		this.lat = param.getLong("latitude", 488779);
		//lat = 50.7283f;
		//lon = 3.104f;  //31616
		this.lon = param.getLong("longitude", 23445);
		//lon = 3.1616f;
		this.alt = param.getInt("alt", 0);
		//alt = 0;
		//gmt = TimeZone.getDefault().getRawOffset();
		this.gmt = param.getInt("gmt", 100);
		//gmt = +1;
		this.angle = param.getFloat("angle", 0f);
		//angle = 0f;
		this.dst = param.getInt("dst", TimeZone.getDefault().getDSTSavings());
		//dst = TimeZone.getDefault().getDSTSavings();
		this.method = param.getInt("method", com.alpha.pt.PrayerTime.MUSLIM_LEAGUE);
		//method = com.alpha.pt.PrayerTime.MUSLIM_LEAGUE;
		this.folderQuran = param.getString("folderQuran","/sdcard/quran/c");
		
		this.notification = param.getInt("notification",0);

		this.alert_before = param.getInt("alert_before",0);

		this.offset = new float[6];
		for (int i = 0; i < offset.length; i++) {
			this.offset[i] = param.getFloat("offset"+i, 0);
		}

	}

	public void reset()
	{
		 SharedPreferences param = this.context.getSharedPreferences(Param.NAME, 0);
		 param.edit().clear().commit(); 	
	}

	public int getGmt() {
		return gmt/100;
	}
	
	public int getGmt2() {
		return (int)gmt/100;
	}
	
	public String getGmtString() {
		return Utils.time2str(gmt);
	}
	
	public double getLat() {
		return lat/10000;
	}
	
	public double getLon() {
		return lon/10000;
	}
	
	public String getLatString() {
		return ""+(int)(lat/10000)+"°"+(Math.abs(lat)%10000);
	}
	public String getLonString() {
		return ""+(int)(lon/10000)+"°"+(Math.abs(lon)%10000);
	}	
	
	public String getAngleQibla()
	{
		return Utils.dms2str(this.getDegQibla());
      //double rlng =  utils.deg2rad(qibla_Lon - this.getLon());
      //return utils.deg2str(utils.rad2deg(Math.atan2(Math.sin(rlng), Math.cos(utils.deg2rad(this.getLat()))* Math.tan( utils.deg2rad(qibla_Lat))- Math.sin(utils.deg2rad(this.getLat()))* Math.cos(rlng))));		
	}

	public float getDegQibla()
	{		
      double rlng =  Utils.deg2rad(qibla_Lon - this.getLon());
      return Utils.rad2deg(Math.atan2(Math.sin(rlng), Math.cos(Utils.deg2rad(this.getLat()))* Math.tan( Utils.deg2rad(qibla_Lat))- Math.sin(Utils.deg2rad(this.getLat()))* Math.cos(rlng)));		
	}

	/*
	public void load() {

	}*/

	public void save() {
   SharedPreferences param = this.context.getSharedPreferences(Param.NAME, 0);
      SharedPreferences.Editor editor = param.edit();
      editor.putString("location", this.location);
      editor.putInt("city", this.city);
      editor.putInt("country", this.country);      
      editor.putLong("latitude", this.lat);
      editor.putLong("longitude", this.lon);
      editor.putInt("alt", this.alt);
      editor.putInt("gmt", this.gmt);
      editor.putFloat("angle", this.angle);
      editor.putInt("dst", this.dst);
      editor.putInt("method", this.method);
      editor.putInt("version", Param.VERSION_PARAM);
      editor.putString("folderQuran", this.folderQuran);
      editor.putInt("notification", this.notification);
      editor.putInt("alert_before", this.alert_before);
      editor.putFloat("offset0", this.offset[0]);
      editor.putFloat("offset1", this.offset[1]);
      editor.putFloat("offset2", this.offset[2]);
      editor.putFloat("offset3", this.offset[3]);
      editor.putFloat("offset4", this.offset[4]);
      editor.putFloat("offset5", this.offset[5]);

      editor.commit();
	}

	public void setTimeZone(int gmt,int dst) {
		this.gmt = gmt;
		this.dst = dst;
	}

	public void setPosGps(double lat,double lon,double alt) {
		this.lat = (int)(lat*10000);
		this.lon = (int)(lon*10000);
		this.alt = (int)alt;
		this.location = this.getLatString()+"/"+this.getLonString()+" (GPS)"; 
	}

	public int getMethod() {
		return method ;
	}
	
	public void setMethod(int method) {
		this.method = method;
	}
	
	public double getAlt()
	{
		return this.alt;
	}
	
	public String getLocation()
	{
		return this.location;
	}

	public float getAngle()
	{
		return this.angle;
	}

	public void setAngle(float angle)
	{
		this.angle = angle;
	}

	public int getDst()
	{
		return (this.dst>0 ) ? 1 : 0;
	}

	public static double getQibla_Lat()
	{
		return qibla_Lat;
	}

	public static double getQibla_Lon()
	{
		return qibla_Lon;
	}

	public int getCountry()
	{
		return this.country;
	}

	public int getCity()
	{
		return this.city;
	}

	public void setCity(Activity act,int country, int city)
	{
		this.city = city;
		this.country = country;
		this.location ="-";
		
	    CityDB cb = new CityDB(context);
	 	 	SQLiteDatabase db =  cb.getReadableDatabase();
		   if (db!=null)
			{
				Cursor c = db.query("city", new String[]{"_id","ville","region","latitude","longitude","gmt","dst"}, "_id="+city+" and idpays="+country, null, null, null, null);
				act.startManagingCursor(c);
				//String region;
				while (c.moveToNext())
				{
					this.lat = c.getInt(3);
					this.lon = c.getInt(4);
					this.gmt = c.getInt(5);
					this.dst = c.getInt(6);
					this.location = c.getString(1);
				}
				 db.close();
			}
		
	}

	public String getFolderQuran()
	{
		return this.folderQuran;
	}

	public void setFolderQuran(String folderQuran)
	{
		this.folderQuran = folderQuran;
	}
	
	public void setNotification(int notification)
	{
		this.notification = notification;
	}

	public int getNotification()
	{
		return this.notification;
	}

	public int getAlert_before() {
		return alert_before;
	}

	public void setAlert_before(int alert_before) {
		this.alert_before = alert_before;
	}


	public boolean isSpkOn(int param)
	{
		return ((this.notification & param) > 0) ;
	}

	public float[] getOffset() {
		return offset;
	}

	public void setOffset(float[] offset) {
		this.offset = offset;
	}
	
}
