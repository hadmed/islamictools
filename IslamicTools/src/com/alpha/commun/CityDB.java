package com.alpha.commun;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.app.ProgressDialog;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class CityDB extends SQLiteOpenHelper
{
	private Context context;
	ProgressDialog mprog;
    
   
	public CityDB(Context context, String name, CursorFactory factory, int version)
	{
		super(context, name, factory, version);
		this.context = context;
	}

   public CityDB(Context context) {
      super(context, Param.DB_NAME, null, Param.VERSION_DB);
  	this.context = context;
   }

   
	@Override
	public void onCreate(SQLiteDatabase db)
	{
		InputStream fis=null;
		try
		{
			fis =  context.getAssets().open("sql/gps.sql");
	  	   BufferedReader bread = new BufferedReader(new InputStreamReader(fis));
		   String country="";
		   int cpt = -1;
		   int cptCity = 0;
		   //db.beginTransaction();
	  	   while (bread.ready()) {
		        String s = bread.readLine().trim();	
		        if (s!=null && s.startsWith("#"))
		        {
		      	  country = s.substring(1);
		      	  cpt++;
		      	  cptCity = 0;
		      	  db.execSQL("insert into country values ("+cpt+",\""+country+"\",0);");
		        } else
		        if (s!=null && !s.equals("") )
		        {
		      	  s = s.replaceAll("~", "insert into city values ("+cptCity+","+cpt+",");		      	  
		      	  cptCity++;
		      	  db.execSQL(s);
		        }
		    }
		} catch (Exception ex)
		{
			//Log.d("sam", "erreur : "+ex.getMessage());			
		} finally{
			try{
				if (fis!=null)
					fis.close();
			}catch (Exception e) {
				
			}	
				
			}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
	}

}
