package com.alpha.commun;

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

	public static String int2str(Integer x)
	{
		return ((x<10)?"0":"")+Integer.toString(x);	
	}

	public static String time2str(Integer x)
	{
		return ""+(int)(x/100)+":"+utils.int2str(Math.abs(x)%100);
		
	}
	
	
}
