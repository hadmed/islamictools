package com.alpha.model;

import com.alpha.pt.Date;
import com.alpha.pt.Location;
import com.alpha.pt.Method;
import com.alpha.pt.Prayer;

public class PT {
	public static PrayerTime [] getPrayerTimes(int year, int month, int day,double latitude,double longitude,float gmt,int dst,int method) {
		PrayerTime[] prayers = new PrayerTime[7];
	    int i;
	  
	    Location loc = new Location();
	    Method conf = new Method();
	    Date date = new Date();

	    Prayer[] ptList = new Prayer[6];
	    for (int j = 0; j < ptList.length; j++) {
			ptList[j] = new Prayer();
		}
	    Prayer[] imsaak = new Prayer[1];
	    imsaak[0] = new Prayer();
	    Prayer[] nextImsaak = new Prayer[1];
	    nextImsaak[0] = new Prayer();
	    Prayer[] nextFajr = new Prayer[1];
	    nextFajr[0] = new Prayer();
	    /* fill the Date structure */
	    date.day = day;
	    date.month = month;
	    date.year = year;
	    /* fill the location info. structure */
	    loc.degreeLat = latitude;
	    loc.degreeLong = longitude;
	    loc.gmtDiff = gmt;
	    loc.dst = dst;
	    loc.seaLevel = 0;
	    loc.pressure = 1010;
	    loc.temperature= 10;

	  
	    /* auto fill the method structure. Have a look at prayer.h for a
	     * list of supported methods */
	    // TODO Use another methods, not egypt survey only
	    com.alpha.pt.PrayerTime.getMethod(method, conf);
	    conf.round = 0;
	  
	    /* Call the main function to fill the Prayer times array of
	     * structures */
	    com.alpha.pt.PrayerTime.getPrayerTimes (loc, conf, date, ptList);

	    /* Call functions for other prayer times and qibla */
	    com.alpha.pt.PrayerTime.getImsaak (loc, conf, date, imsaak);
	    com.alpha.pt.PrayerTime.getNextDayFajr (loc, conf, date, nextFajr);
	    com.alpha.pt.PrayerTime.getNextDayImsaak (loc, conf, date, nextImsaak);
	     

	    for (i = 0; i < 6; i++) {
	    	prayers[i] = new PrayerTime(year,month,day,ptList[i].hour,ptList[i].minute,ptList[i].second);
	    }
	    prayers[6] = new PrayerTime(year,month,day+1,nextFajr[0].hour,nextFajr[0].minute,nextFajr[0].second);

		return prayers;
	}
}
