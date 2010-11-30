package com.alpha;

import java.io.File;

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
	private int[][] posLecture = /*{	6375,12445,18019,22626,27259,33956,39530,52758};*/
	{{},
	{6375,12445,18019,22626,27259,33956,39530,52758},
	/*sourate 002*/
	{0,7107,14794,23814,35263,51127,62524,75175,88531,100607,113232,126849,137567,146743,171750,
		195555,203530,216128,237922,245766,271269,301761,315300,346393,370250,385147,423528,466716,
		490234,508240,529981,560552,587805,601997,637139,653995,674561,695884,711565,729701,747315,
		769265,791424,801018,809019,825405,837794,850654,868059,890819,916975,932133,947762,958244,
		967342,1003216,1021587,1031704,1055901,1080098,1099358,1125331,1194380,1221346,1240423,
		1256783,1268128,1279708,1302311,1323086,1343391,1361475,1383974,1397016,1410372,1453325,
		1475954,1507099,1517921,1531617,1558531,1584347,1601961,1616231,1656075,1675779,1739316,
		1756198,1789747,1800674,1829024,1862259,1899204,1915669,1950210,1971847,1985464,2014337,
		2040937,2058055,2075329,2087797,2113169,2197552,2213442,2229332,2254548,2275323,2292205,
		2311595,2345718,2368687,2388443,2411595,2444177,2477412,2493250,2506972,2521739,2550272,
		2565065,2596419,2618944,2636532,2657150,2683776,2719414,2754164,2771804,2798430,2819936,
		2839979,2850853,2871967,2902825,2920099,2935362,2981110,3007736,3020857,3039281,3075572,
		3094597,3121380,3176297,3216507,3255515,3274644,3282122,3303053,3321137,3357011,3381182,
		3391612,3406196,3423026,3443017,3462668,3477696,3502624,3525828,3538949,3555805,3565478,
		3577345,3629911,3665654,3677416,3702187,3723040,3737519,3761481,3780976,3800889,3828142,
		3860149,3876248,3891224,3957217,3997401,4007883,4024687,4040420,4058948,4073610,4110816,
		4160874,4184183,4254825,4278369,4305988,4319710,4351195,4358125,4375034,4398238,4413840,
		4485057,4517456,4550926,4565536,4589942,4606119,4617803,4651770,4670194,4683341,4695966,
		4710576,4731925,4749199,4768067,4791637,4812830,4871221,4904900,4932258,4958336,5022552,
		5041681,5072539,5101046,5151339,5184888,5209111,5225158,5241596,5258714,5265905,5316015,
		5370488,5408739,5459450,5499242,5567951,5599018,5654144,5679856,5717323,5725899,5741658,
		5771210,5779289,5787656,5809920,5818992,5835483,5892019,5932098,5964236,6025866,6046771,
		6077289,6089809,6143629,6168662,6220653,6245869,6279130,6317903,6384549,6426535,6450549,
		6474903,6491413,6533869,6565929,6616875,6655021,6673575,6692861,6711598,6736631,6769892,
		6811068,6833358,6884174,6895989,6916450,6931713,6947707,6963414,6980741,7125623,7163508,
		7194836,7227679,7280793}

	,
	 /*sourate 040*/
	 {0,7107,11763,19762,34187,47672,75864,88957,122034,144662,160655,177196,197003,214929,231549,
		240097,257265,278456,293430,312819,321654,340625,369522,389355,398843,408854,435792,456826,
		472610,516292,546783,561574,577044,586637,601715,637953,665204,676442,707612,718380,732675,
		765203,779315,793845,827157,846937,859769,877747,907402,923760,940171,963217,977538,992512,
		1005266,1010836,1024791,1054733,1068479,1086379,1099446,1118051,1140209,1155235,1164018,
		1192340,1209769,1230908,1273337,1291419,1303964,1316796,1327381,1335197,1344424,1365275,
		1378864,1392140,1409674,1445363,1455400,1469486,1478870,1507114,1532380,1548503,1569642}

	};
	 
	private String folderQuran ; // = "/sdcard/quran/";
	//private String[] srcSourate = {"/sdcard/quran/a002.mp3" , "/sdcard/quran/a002.mp3"};
	
	private String fileSourate(int sourate)
	{
		String str = folderQuran;
		str += "/"+((sourate<100)?"0":"")+((sourate<10)?"0":"")+sourate+".mp3";
		Log.d("sam","file : "+str);
		
		return (new File(str)).exists() ? str : null;
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
		
		if (mp !=null)
		{
			//mp.seekTo(posLecture[(sourate==40) ? 3 : sourate][verse]);
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
				   				while (posLecture[(sourate==40) ? 3 : sourate][i] < pos && i<posLecture[(sourate==40) ? 3 : sourate].length)
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
				  //handler.postDelayed(playerTask, 100);         

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
