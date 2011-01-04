package com.alpha.commun;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;

public abstract class ReadSLC
{

private static ReadSLC instance;
private Map<String,List<String>> data;
private static int i = -1 ;	
	
/*
public static ReadSLC getInstance(Context context) {
	if(instance == null)
		instance = new ReadSLC(context,"doc/hadith.slc");
	return instance;
}*/


public ReadSLC(Context context,String source) {
	this.data = new HashMap<String, List<String>>();
	try{
		InputStream is = context.getAssets().open(source);
        if (is != null)            	
      	  	{
      	    BufferedReader bread = new BufferedReader(new InputStreamReader(is));
      	    while (bread.ready()) {
      	        String s = bread.readLine();
      	        if (s!=null && s.trim() != "")
      	        {
      	        String idx = s.substring(0,1);
      	        if (!this.data.containsKey(idx))
      	        {
          	        this.data.put(idx, new ArrayList<String>());      	        	
      	        }
      	        if (s.length()>1)
      	        	{
      	        		this.data.get(idx).add(s.substring(1));
      	        	}
      	        }
      	    }
      	  		is.close(); 
      	  	}
	}catch (Exception ex)
	{
	}
}	
	
public String getElement(String idx,int position)
{
	return this.data.get(idx).get(position);
}


}
