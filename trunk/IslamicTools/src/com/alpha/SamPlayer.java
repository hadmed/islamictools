package com.alpha;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.alpha.model.Settings;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Handler;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

public class SamPlayer 
{
	private static SamPlayer instance = null;
	private MediaPlayer mp = null;
	private Context context;
	private int sourate;
	private  TextView tv;
	private  ListView lv;
   
	//private int[] posLecture = {5851,12069,16300,18887,21499,27298,31425};
	private String[] posLecture; /*{	6375,12445,18019,22626,27259,33956,39530,52758};*/
	private boolean timing;
	
	private String folderQuran ; 
	
	private String fileSourate(int sourate)
	{
		String str = folderQuran;
		str += "/"+((sourate<100)?"0":"")+((sourate<10)?"0":"")+sourate+".mp3";
		Log.d("sam","file : "+str);
		return (new File(str)).exists() ? str : null;
	}
	
	private String[] fileTiming(int sourate)
	{
		String[] recup= {};
		String str = folderQuran;
		str += "/"+((sourate<100)?"0":"")+((sourate<10)?"0":"")+sourate+".time";
		Log.d("sam","file : "+str);
		File ff = new File(str);
		if (!ff.exists())
			return null;
		try{
		InputStream is = new FileInputStream(ff);
		BufferedReader bread = new BufferedReader(new InputStreamReader(is));
   	    while (bread.ready()) {
   	      String s = bread.readLine().trim();	
   	      if (s.startsWith("#") || s.startsWith("!")) {}
   	      else   	  
   	    	recup = s.split(",");
   	    }
   	  	is.close(); 
   	  	return recup;
		} catch (Exception ex)
		{
			return null;
		}
	}
	
	
	private SamPlayer(Context context)
	{
/*constructeur*/
		this.context = context;
		this.sourate = -1;
	}
	
	public boolean isPlaying()
	{
		return (mp!=null&&mp.isLooping());
	}
	
	public boolean playSourate(final int sourate,int verse)
	{
		if (this.sourate != sourate && mp != null)
		{
			Log.d("sam", "chargement sourate :"+sourate);
			mp.stop();
			mp.release();
			mp = null;
		}
		
		this.sourate = sourate;
		
		if (fileSourate(sourate)== null)
			return false;
		
		this.posLecture = this.fileTiming(sourate);
		
		this.timing = this.posLecture != null;
		
		if (mp !=null)
		{
			if (this.timing && verse<=posLecture.length)
			{
				mp.seekTo(Integer.parseInt(posLecture[verse]));
			}
			if (!mp.isPlaying())
			{
				mp.start();
			}
		} else
		{
			try{
					mp = new MediaPlayer(); //.create(this.context, "/quran/a002.mp3");
					mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                  public void onCompletion(MediaPlayer arg0) {
                  	tv.setText("----");
                  	mp.release();
                  	mp = null;
                  }
              });
					mp.setDataSource(fileSourate(sourate));
					mp.prepare();
					mp.start();
					final Handler handler=new Handler();
				   final Runnable playerTask = new Runnable() {
				   	public void run()
				   	{
				   		try{
				   			{
				   				int pos =mp.getCurrentPosition() , i = 0;
				   				while (Integer.parseInt(posLecture[i]) < pos && i<posLecture.length)
				   				{
				   					i++;
				   				}
				   				if (i>0)
				   					lv.setSelection(i-1);
				   				tv.setText("["+i+"]"+mp.getCurrentPosition());
				   				if (mp.isPlaying() && tv != null && mp !=null)
				   					{
				   						handler.postDelayed(this, 500);
				   					}
				   			}
				   			
				   		} catch (Exception ex)
				   		{
				   			Log.e("sam",ex.getMessage(),ex);
				   		}
				   	}
				  };	  
				  if (this.timing)
					  {
					  	handler.postDelayed(playerTask, 100);         
					  }

			} catch (Exception ex)
			{
				Log.d("sam", "error lecture Q = "+ex.getMessage());
			}
		}
		
		
		return true;
	}
	
	public synchronized static SamPlayer getInstance(Context context,TextView tv,ListView lv){
		if (instance == null) 
		{
			/*si l'instance n'existe pas, on la créé*/
			try{
				instance = new SamPlayer(context) ;
			} catch (Exception e)
			{
				e.printStackTrace();			
			}
	} 
	instance.setTv(tv);
	instance.setLv(lv);
	instance.folderQuran = Settings.getInstance(context).getFolderQuran();
	
	return instance;	
	}
	

	public void close()
	{
		if (mp!=null)
		{
			mp.stop();
			mp.release();
			mp = null;
			
		}
		
	}
	
	public Context getContext()
	{
		return this.context;
	}

	public TextView getTv()
	{
		return this.tv;
	}

	public void setTv(TextView tv)
	{
		this.tv = tv;
	}

	public ListView getLv()
	{
		return this.lv;
	}

	public void setLv(ListView lv)
	{
		this.lv = lv;
	}

	
}
