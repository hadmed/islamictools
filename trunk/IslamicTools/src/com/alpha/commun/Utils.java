package com.alpha.commun;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import com.alpha.pt.Date;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;

public class Utils
{
   public static double deg2rad(final double deg)
   {
  	 return deg*Math.PI/180;
   }

   public static float rad2deg(final double rad)
   {
  	 return (float)(rad*180/Math.PI);
   }
   
   public static String dms2str(final float rad)
   {
   	return (int)rad+"°"+int2str((int)((Math.abs(rad)%1)*60))+"' N";   	
   }

   public static String deg2str(final float rad)
   {
   	return (int)rad+"°"+(int)((Math.abs(rad)%1)*10000);   	
   }

   public static Integer str2int(final String str,final int defaut)
   {
	   return str.matches("[0-9\\-]+") ? Integer.parseInt(str) : defaut ;
   }
	public static String int2str(final Integer val)
	{
		return ((val<10)?"0":"")+Integer.toString(val);	
	}

	public static String time2str(final Integer val)
	{
		return ""+(int)(val/100)+":"+Utils.int2str(Math.abs(val)%100);
		
	}

	public static String calendar2str(final Calendar cal)
	{
		return int2str(cal.get(Calendar.DAY_OF_MONTH))+"/"
		+int2str(cal.get(Calendar.MONTH))+"/"
		+cal.get(Calendar.YEAR);
	}

	public static String calendar2str(final Date date)
	{
		return int2str(date.day)+"/"
		+int2str(date.month)+"/"
		+date.year;
	}

	public static double calendar2Julian(final Calendar cal)
	{
		return Utils.calendar2Julian(cal.get(Calendar.DAY_OF_MONTH), 
				cal.get(Calendar.MONTH)+1, cal.get(Calendar.YEAR));
	}
	
	public static double calendar2Julian(final int day,final int month,final int year) {
		final int GGG = (( year < 1582 )||( year <= 1582 && month < 10 )||( year <= 1582 && month == 10 && day < 5 )) ? 0 : 1;
		double JulianDay = -1 * Math.floor(7 * (Math.floor((month + 9) / 12) + year) / 4);
		final int S = ((month - 9)<0) ? -1 : 1;
		final int A = Math.abs(month - 9);
		double J1 = Math.floor(year + S * Math.floor(A / 7));
		J1 = -1 * Math.floor((Math.floor(J1 / 100) + 1) * 3 / 4);
		JulianDay = JulianDay + Math.floor(275 * month / 9) + day + (GGG * J1);
		JulianDay = JulianDay + 1721027 + 2 * GGG + 367 * year - 0.5;
		return JulianDay;
	}

	public static double hijir2Julian(final int day,final int month,final int year)
	{
	     return Math.floor((year * 10631 + 58442583)/30) + Math.floor((month * 325 - 320)/11) + (day - 1);
	     /*double KHS2 = ((JulianDayHijir + 1.5)/7);
	     double KHS3 = KHS2 - Math.floor(KHS2);
	     return Math.round(KHS3*7 + 0.000000000317) - 1;*/
	}

	public static Date julian2calendar(final double JulianDay)
	{
		final double Z = Math.floor(JulianDay+0.5);
		final double F = JulianDay+0.5 - Z;
		final double I = Math.floor((Z - 1867216.25)/36524.25);
		final double A = (Z < 2299161) ? Z : (Z + 1 + I - Math.floor(I/4));
		final double B = A + 1524 ;
		final double C = Math.floor((B - 122.1)/365.25);
		final double D = Math.floor(365.25 * C);
		final double T = Math.floor((B - D)/ 30.6001);
		final double RJ = B - D - Math.floor(30.6001 * T) + F;
		final int JJ = (int)Math.floor(RJ);
		final int MM = (int)((T < 13.5) ? T - 1 : T - 13 ) ;
		final int AA = (int)((MM > 2.5) ? C - 4716 : (C - 4715));
		return new Date(JJ,MM,AA);
	}
	
	public static int getDayOfWeek(final double JulianDay) {
		final double startDay = (JulianDay + 1.5); //on commence le dimanche
		final double week = (startDay/7);
		final double dayOfWeek = week - Math.floor(week); //un pseudo modulo
		return (int) Math.round(dayOfWeek*7 + 0.000000000317);
	}


	/**
	 * @param JulianDay
	 * @return
	 */
	public static Date julian2Hijir(final double JulianDay) {
		double Z = (JulianDay+0.5);
		int AH = (int) Math.floor((Z * 30 - 58442554)/10631);
		double R2 = Z - Math.floor((AH * 10631 + 58442583)/30);
		int M = (int) Math.floor((R2 * 11 + 330)/325);
		int J = (int)(R2 - Math.floor((M * 325 - 320)/11)) + 1 ;
		return new Date(J,M,AH);
	}

	
	public static Location getCurrentLocation(final Context context) {
		Criteria criteria = new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_FINE);
		criteria.setCostAllowed(true);

		LocationManager locationManager = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
		Location currentLocation = null;
		try {
			currentLocation = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, true));
			if(currentLocation == null) {
				criteria.setAccuracy(Criteria.ACCURACY_COARSE);
				currentLocation = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, true));
			}
		} catch(Exception ex) {
			// GPS and wireless networks are disabled
		}
		return currentLocation;
	}
	
	public static BufferedReader getDoc2(final Context context,final String source,final String fileInZip)
	{
		boolean inZip = (fileInZip!=null) ;
		BufferedReader bread = null;
		
		try{
			InputStream is =  new FileInputStream(source);
			if (is != null)            	
			{
				

				if (inZip)
				{
					ByteArrayInputStream bais = null;
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					ZipInputStream zis = new ZipInputStream(is);
		        	ZipEntry ze;
		        	boolean exit = false;
		        	byte[] datax = new byte[1024];
		        	while ( (ze = zis.getNextEntry())!=null && !exit )
		        	{
		        		if (fileInZip.equalsIgnoreCase(ze.getName()))
		        		{
		        			exit = true;	        			
		        			int count;
		        			while ((count = zis.read(datax)) != -1) {
		        			baos.write(datax, 0, count);
		        			bais = new ByteArrayInputStream(baos.toByteArray());
		        			}
		        			bread =new BufferedReader(new InputStreamReader(bais));
		        		}
		        	}
		        	zis.close();
				} else
				{
					bread = new BufferedReader(new InputStreamReader(is));					
				}
				is.close(); 
			}
		

		}catch(Exception ex)
		{
			
		}

		return bread;
		
	}


}
