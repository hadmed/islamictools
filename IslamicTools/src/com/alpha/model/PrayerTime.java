package com.alpha.model;

import java.util.Calendar;

import com.alpha.commun.utils;

public class PrayerTime {
	private int year,month,day,hour,minute,second;

	public PrayerTime(int year, int month, int day, int hour, int minute, int second) {
		this.year = year;
		this.month = month;
		this.day = day;
		this.hour = hour;
		this.minute = minute;
		this.second = second;
	}

	public int getDay() {
		return day;
	}

	public int getHour() {
		return hour;
	}


	public int getMinute() {
		return minute;
	}

	public int getMonth() {
		return month;
	}

	public int getSecond() {
		return second;
	}

	public int getYear() {
		return year;
	}
	public Calendar getCalendar() {
		Calendar pt = Calendar.getInstance();
		pt.set(Calendar.YEAR, pt.get(Calendar.YEAR) );
		pt.set(Calendar.MONTH, pt.get(Calendar.MONTH) );
		pt.set(Calendar.DAY_OF_MONTH, pt.get(Calendar.DAY_OF_MONTH) );
		pt.set(Calendar.HOUR_OF_DAY, hour);
		pt.set(Calendar.MINUTE, minute);
		pt.set(Calendar.SECOND, second);
		return pt;
	}

	public Calendar getCalendar2() {
		Calendar pt = Calendar.getInstance();
		pt.set(Calendar.YEAR, pt.get(Calendar.YEAR) );
		pt.set(Calendar.MONTH, pt.get(Calendar.MONTH) );
		pt.set(Calendar.DAY_OF_MONTH, pt.get(Calendar.DAY_OF_MONTH) );
		pt.set(Calendar.HOUR_OF_DAY, hour);
		pt.set(Calendar.MINUTE, minute);
		pt.set(Calendar.SECOND, 0);
		pt.set(Calendar.MILLISECOND, 0);
		return pt;
	}
	public String getTime() {
		return utils.int2str(getHour())+":"+utils.int2str(getMinute())+":"+utils.int2str(getSecond());
	}
	
	public String getTime2() {
		return utils.int2str(getHour())+":"+utils.int2str(getMinute());
	}
	
/*	public boolean beforeNow()
	{
		Calendar now = Calendar.getInstance();
		return now.get(Calendar.YEAR)<year||
		now.get(Calendar.MONTH)<month||
		now.get(Calendar.DAY_OF_MONTH)<day||
		now.get(Calendar.H)<year||
		now.get(Calendar.YEAR)<year||
		now.get(Calendar.YEAR)<year;
	}*/
	
}
