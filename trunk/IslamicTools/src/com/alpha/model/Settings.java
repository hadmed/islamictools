
package com.alpha.model;

import java.util.TimeZone;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.alpha.commun.utils;


public class Settings {
	private double lat;
	private double lon;
	private double alt;
	private String location;
//	private double speed=0;	
	private int gmt;
	private int dst;

	private int country;
	private int city;

	private static String PARAM_NAME = "islamicTools";
	private float angle;
	private int method;
	private Context context;
	
	private static Settings instance;
   private static double qibla_Lat = 21.4225,qibla_Lon = 39.826111;
	
	public static Settings getInstance(Context context) {
		if(instance == null)
			instance = new Settings(context);
		return instance;
	}
	
	private Settings(Context context) {
		this.context = context;
		SharedPreferences param = context.getSharedPreferences(PARAM_NAME, 0);
		this.country = param.getInt("country", 79 );
		this.city = param.getInt("city", 410);
		
		this.location = param.getString("location", "FR, Paris");
		//lat = 50.7323f;  //507283
		this.lat = param.getFloat("latitude", 50.7283f);
		//lat = 50.7283f;
		//lon = 3.104f;  //31616
		this.lon = param.getFloat("longitude", 3.1616f);
		//lon = 3.1616f;
		this.alt = param.getFloat("alt", 0);
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
	}

	public int getGmt() {
		return gmt;
	}
	
	public int getGmt2() {
		return gmt/100;
	}
	public double getLat() {
		return lat;
	}
	
	public double getLon() {
		return lon;
	}	
	
	public String getAngleQibla()
	{
		
      double rlng =  utils.deg2rad(qibla_Lon - lon);
      return utils.deg2str(utils.rad2deg(Math.atan2(Math.sin(rlng), Math.cos(utils.deg2rad(lat))* Math.tan( utils.deg2rad(qibla_Lat))- Math.sin(utils.deg2rad(lat))* Math.cos(rlng))));		
	}

	public float getDegQibla()
	{		
      double rlng =  utils.deg2rad(qibla_Lon - lon);
      return utils.rad2deg(Math.atan2(Math.sin(rlng), Math.cos(utils.deg2rad(lat))* Math.tan( utils.deg2rad(qibla_Lat))- Math.sin(utils.deg2rad(lat))* Math.cos(rlng)));		
	}

	/*
	public void load() {

	}*/

	public void save() {
   SharedPreferences param = this.context.getSharedPreferences(PARAM_NAME, 0);
      SharedPreferences.Editor editor = param.edit();
      editor.putString("location", this.location);
      editor.putInt("city", this.city);
      editor.putInt("country", this.country);      
      editor.putFloat("latitude", (float)this.lat);
      editor.putFloat("longitude", (float)this.lon);
      editor.putFloat("alt", (float)this.alt);
      editor.putInt("gmt", this.gmt);
      editor.putFloat("angle", this.angle);
      editor.putInt("dst", this.dst);
      editor.putInt("method", this.method);
      editor.commit();

      Log.d("sam","location:"+this.location);
      Log.d("sam","city:"+this.city);
      Log.d("sam","country:"+this.country);      
      Log.d("sam","latitude:"+(float)this.lat);
      Log.d("sam","longitude:"+(float)this.lon);
      Log.d("sam","alt:"+(float)this.alt);
      Log.d("sam","gmt:"+this.gmt);
      Log.d("sam","angle:"+this.angle);
      Log.d("sam","dst:"+this.dst);
      Log.d("sam","method:"+this.method);
      
	}

	public void setTimeZone(int gmt,int dst) {
		this.gmt = gmt;
		this.dst = dst;
	}

	public void setPosGps(double lat,double lon,double alt) {
		this.lat = lat;
		this.lon = lon;
		this.alt = alt;
		this.location = lat+"/"+lon+" (GPS)"; 
	}
	
	public void setCity(String location,int country,int city,double lat,double lon,int gmt,int dst) {
		this.lat = lat/10000;
		this.lon = lon/10000;
		this.gmt = gmt;
		this.dst = dst;

		this.country = country;
		this.city = city;
		this.location = location; 
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
}
