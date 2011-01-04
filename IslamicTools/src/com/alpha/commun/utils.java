package com.alpha.commun;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;

public class utils
{
   public static double deg2rad(double deg)
   {
  	 return deg*Math.PI/180;
   }

   public static float rad2deg(double rad)
   {
  	 return (float)(rad*180/Math.PI);
   }
   
   public static String dms2str(float rad)
   {
   	return (int)rad+"°"+int2str((int)((Math.abs(rad)%1)*60))+"' N";   	
   }

   public static String deg2str(float rad)
   {
   	return (int)rad+"°"+(int)((Math.abs(rad)%1)*10000);   	
   }

   public static Integer str2int(String str, int defaut)
   {
	   return str.matches("[0-9\\-]+") ? Integer.parseInt(str) : defaut ;
   }
	public static String int2str(Integer x)
	{
		return ((x<10)?"0":"")+Integer.toString(x);	
	}

	public static String time2str(Integer x)
	{
		return ""+(int)(x/100)+":"+utils.int2str(Math.abs(x)%100);
		
	}
	
	public static Location getCurrentLocation(Context context) {
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

}
