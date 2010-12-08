package com.alpha.view;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class DocumentView extends Activity{

private String source;	
	
@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.doc_view);
	source = getIntent().getExtras().getString("source");
	
    WebView wv = (WebView) findViewById(R.id.doc_v);
    wv.setBackgroundColor(0);
    StringBuffer doc = getDoc(source); 
    if (null!=doc)
    {
    wv.loadData(doc.toString(), "text/html", "utf-8");
    WebSettings settings = wv.getSettings(); 
    settings.setJavaScriptEnabled(true);
    }

}
	
private StringBuffer getDoc(String source)
{
	StringBuffer txt = null;
   try
   {         	
   //String doc = (ref<10?"0":"")+(ref<100?"0":"")+ref+".html";
   
   InputStream fis = getAssets().open(source);
   if (fis != null)         	
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
