package com.alpha.view;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import com.alpha.ArabicUtilities;
import com.alpha.SamPlayer;

import android.app.ListActivity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SlidingDrawer;
import android.widget.TextView;

public class Sourate extends ListActivity 
{
	private SamPlayer sp = null;
	private int sourate;

	
   private static class EfficientAdapter extends BaseAdapter{
      private LayoutInflater mInflater;
      //private Bitmap mIcon1;
      //private Bitmap mIcon2;
      private AssetManager manager;
      //private Context mContext;
      private int sourate;
      
      private List<String> getSourate(int sourate,String lang)
      {
      	List<String> txt = new ArrayList<String>();
         try
         {         	
         InputStream is = manager.open("sourate/"+lang+"/"+sourate+".gzx");
         
         GZIPInputStream fis = new GZIPInputStream(is);
         if (fis ==null)    
         	
       	  	{
       	  		Log.d("sam", "error fichier");
       	  	} else
       	  	{
       	    BufferedReader bread = new BufferedReader(new InputStreamReader(fis));
       	    while (bread.ready()) {
       	        String s = bread.readLine();	
       	      txt.add(s);
       	    }
       	  		fis.close(); 
       	  	}
      }catch(Exception ex)
      {
     	 Log.d("sam","error : "+ex.getMessage());         	 
      }
      	
      	return txt;
      }
      
 
      
      public EfficientAdapter(Context context,Bundle bundle) {
          // Cache the LayoutInflate to avoid asking for a new one each time.
          mInflater = LayoutInflater.from(context);
          sourate = bundle.getInt("sourate")+1;
          manager = context.getAssets(); 
          //mContext = context;
          SOURATE_TXT = new HashMap<String, List<String>>();
          SOURATE_TXT.put("FR", this.getSourate(sourate, "FR"));
          SOURATE_TXT.put("AR", this.getSourate(sourate, "AR"));
          SOURATE_TXT.put("PH", this.getSourate(sourate, "PH"));
          
      }

      /**
       * The number of items in the list is determined by the number of speeches
       * in our array.
       *
       * @see android.widget.ListAdapter#getCount()
       */
      public int getCount() {
          return SOURATE_TXT.get("AR").size();
      }

      /**
       * Since the data comes from an array, just returning the index is
       * sufficent to get at the data. If we were using a more complex data
       * structure, we would return whatever object represents one row in the
       * list.
       *
       * @see android.widget.ListAdapter#getItem(int)
       */
      public Object getItem(int position) {
          return position;
      }

      /**
       * Use the array index as a unique id.
       *
       * @see android.widget.ListAdapter#getItemId(int)
       */
      public long getItemId(int position) {
          return position;
      }

      /**
       * Make a view to hold each row.
       *
       * @see android.widget.ListAdapter#getView(int, android.view.View,
       *      android.view.ViewGroup)
       */
      public View getView(int position, View convertView, ViewGroup parent) {
          ViewHolder holder;
          if (convertView == null) {
             convertView = mInflater.inflate(R.layout.trad,null);
             holder = new ViewHolder();
              holder.text = (TextView) convertView.findViewById(R.id.text);
              holder.text2 = (TextView) convertView.findViewById(R.id.text2);
              holder.text3 = (TextView) convertView.findViewById(R.id.text3);
              
              //holder.icon = (ImageView) convertView.findViewById(R.id.icon);
              
              convertView.setTag(holder);
              
              manager = convertView.getContext().getAssets(); 
              try
              {
              holder.text.setTypeface(Typeface.createFromAsset(manager, "fonts/arial.ttf"));
              holder.text.setTextSize(22);
              holder.text.setTextColor(0xFF00FF00);
              } catch(Exception ex){
              }
              holder.text2.setTextColor(0xFFc0c0c0);
              holder.text3.setTextColor(0xFFFFFFFF);
              
              /*
              AlertDialog.Builder alt_bld = new AlertDialog.Builder(convertView.getContext());
              alt_bld.setMessage("Do you want to close this window ?")
              .setCancelable(false)
              .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog, int id) {
	            }
              })
              	.setNegativeButton("No", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
					//  Action for 'NO' Button
					dialog.cancel();
					}
					});
              AlertDialog alert = alt_bld.create();
              alert.setTitle("Title");
              //alert.setIcon(R.drawable.icon);
              alert.show();
              */
          
          } else {
              holder = (ViewHolder) convertView.getTag();
          }
          
          try
          {         	 
         	 holder.text.setText(getArabicR(SOURATE_TXT.get("AR").get(position),position==2));
             holder.text2.setText(SOURATE_TXT.get("PH").get(position)); 
             holder.text3.setText(SOURATE_TXT.get("FR").get(position)); 
          }catch(Exception ex){
          }
          //holder.icon.setImageBitmap((position & 1) == 1 ? mIcon1 : mIcon2);
          //return holder.text;
          return convertView;
      }
      private String getArabicR(String s,boolean logg)
      {
      	String res = "";
      	 
      	
      	int i = 0,c;
      	while (i<s.length())
      	{
      		c= s.codePointAt(i);
      		if (logg)
      			Log.d("sam",i+":"+Integer.toHexString(c));
      		switch(c)
      		{
      		case 0x0621 : /*res +=(char)0xFE7B  ;*/ break;
      		case 0x064B : /*res +=(char)0xFE7B  ;*/ break;
      		case 0x064C : /*res +=(char)0xFE7B  ;*/ break;
      		case 0x064D : /*res +=(char)0xFE7B  ;*/ break;
      		case 0x064E : /*res +=(char)0xFE7B  ;*/ break;
      		case 0x064F : /*res +=(char)0xFE7B  ;*/ break;
      		case 0x0650 : /*res +=(char)0xFE7B  ;*/ break;
      		case 0x0651 : /*res +=(char)0xFE7B  ;*/ break;
      		case 0x0652 : /*res +=(char)0xFE7F  ;*/ break;
      		default : res += s.charAt(i); break;
      		
      		}
      		
      		i++;
      		
      	}
      	return ArabicUtilities.reshapeSentence(res);
      	//return ArabicUtilities.reshapeSentence(s);
      }
      
      static class ViewHolder {
      	TextView text;
      	TextView text2;
      	TextView text3;
      	
          //ImageView icon;
      }
  }
   
  @Override
  public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.sourate);
      //requestWindowFeature(Window.FEATURE_NO_TITLE);
      setTitle(getIntent().getExtras().getString("titre"));
      this.sourate = getIntent().getExtras().getInt("sourate")+1;
      getListView().setEmptyView(findViewById(R.id.empty));
      //ListView lv = (ListView)findViewById(R.id.lv);

      /*Runnable task =  new Runnable() {
	   	public void run()
	   	{
      TextView tv = (TextView) findViewById(R.id.exegese);
      tv.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/arial.ttf"));
      tv.setText(Html.fromHtml(xgz));
      //tv.setText(Html.fromHtml(getXGZFile("001.html")));
	   	}
      };
      //new Thread(task).start();
       task.run();
      */
      
      setListAdapter(new EfficientAdapter(this,getIntent().getExtras()));
      WebView wv = (WebView) findViewById(R.id.exegese);
      wv.setBackgroundColor(0);
      //wv.setBackgroundResource(R.drawable.flower);
      //wv.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/arial.ttf"));
      //wv.setText(Html.fromHtml(xgz));
      //wv.setWebViewClient(new Callback());
      StringBuffer tafsir = getXGZFile(this.sourate); 
      if (null!=tafsir)
      {
      wv.loadData(tafsir.toString(), "text/html", "utf-8");
      WebSettings settings = wv.getSettings(); 
      settings.setJavaScriptEnabled(true);
      ((SlidingDrawer)findViewById(R.id.xgz_drawer)).setVisibility(SlidingDrawer.VISIBLE);
      } else
      {
         ((SlidingDrawer)findViewById(R.id.xgz_drawer)).setVisibility(SlidingDrawer.INVISIBLE);
      }
  }
/*
  private class Callback extends WebViewClient {
	  public boolean shouldOverrideUrlLoading(WebView view, String url) {
	  return(false);
	  }
	  }
  */
  protected void onListItemClick(ListView l, View v, int position, long id) {

	  
	  //l.setSelection(position);
	  WebView wv = (WebView) findViewById(R.id.exegese);
	  wv.loadUrl("javascript:location.href='#S"+(position+1)+"'");
	  //l.getFocusedChild().setBackgroundColor(Color.BLUE);
	  //if (sourate == 1 || sourate == 2 ||sourate == 40 )
	 {
		  sp = SamPlayer.getInstance(Sourate.this,(TextView)findViewById(R.id.timing),l);
		  sp.playSourate(sourate, position);

    }
}
		
  @Override
  protected void onDestroy() {
	  if (sp!= null)
	  {
		  sp.close();
	  }
     super.onDestroy();
  }

  
  @Override
public void onPanelClosed(int featureId, Menu menu)
{
	  if (sp!= null)
	  {
		  sp.close();
	  }
	// TODO Auto-generated method stub
	super.onPanelClosed(featureId, menu);
}
  
  private static Map<String,List<String>> SOURATE_TXT;

  

 private StringBuffer getXGZFile(int num_sourate)
  {
  	StringBuffer txt = null;
     try
     {         	
     String sourate = (num_sourate<10?"0":"")+(num_sourate<100?"0":"")+num_sourate+".html";
     
     GZIPInputStream fis = new GZIPInputStream(getAssets().open("sourate/XG/"+sourate+".gzx"));
     if (fis ==null)         	
   	  	{
   	  		Log.d("sam", "error fichier");
   	  	} else
   	  	{
   	  	 txt = new StringBuffer();
   	    BufferedReader bread = new BufferedReader(new InputStreamReader(fis));
   	    while (bread.ready()) {
   	        String s = bread.readLine();	
   	      txt.append(s);
   	    }
   	  	fis.close(); 
   	  	}
  }catch(Exception ex)
  {
 	 Log.d("sam","error : "+ex.getMessage());         	 
  }
  	return txt;
  }

 
}
