
package com.alpha.model;

import java.util.TimeZone;

import com.alpha.commun.utils;


public class Settings {
	private double lat;
	private double lon;
	private double alt;
	private String location;
//	private double speed=0;	
	private int gmt;
	private int dst;
	
	private float angle;
	private int method;
	
	private static Settings instance;
   private static double qibla_Lat = 21.4225,qibla_Lon = 39.826111;
	
	public static Settings getInstance() {
		if(instance == null)
			instance = new Settings();
		return instance;
	}
	
	private Settings() {		
		this.location = "FR, Tourcoing";
		//lat = 50.7323f;  //507283
		lat = 50.7283f;
		//lon = 3.104f;  //31616
		lon = 3.1616f;
		alt = 0;
		//gmt = TimeZone.getDefault().getRawOffset();
		gmt = +1;
		angle = 0f;
		dst = TimeZone.getDefault().getDSTSavings();
		method = com.alpha.pt.PrayerTime.MUSLIM_LEAGUE;
	}

	public int getGmt() {
		return gmt;
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

	
	public void load() {

	}

	public void save() {
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
}
