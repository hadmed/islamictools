package com.alpha.view;

import java.io.BufferedReader;
import com.alpha.commun.Utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class DocumentView extends Activity{

	private String source;	
	private String fileInZip;	
	
@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setResult(RESULT_OK, new Intent());
	setContentView(R.layout.doc_view);
	source = getIntent().getExtras().getString("source");
	fileInZip = getIntent().getExtras().containsKey("fileInZip") ? getIntent().getExtras().getString("fileInZip") : null ; 
    WebView wv = (WebView) findViewById(R.id.doc_v);
    wv.setBackgroundColor(0);
    BufferedReader doc = Utils.getDoc2(this,source,fileInZip);
    

    if (null!=doc)
    {
    StringBuffer docRead = new StringBuffer();  
    try{
    while (doc.ready())
    	{
    	docRead.append(doc.readLine());
    	}
    }catch(Exception ex)
    {
    	
    }
    wv.loadData(docRead.toString(), "text/html", "utf-8");    
    WebSettings settings = wv.getSettings(); 
    settings.setJavaScriptEnabled(true);
    } else
    {
		setResult(RESULT_CANCELED, (new Intent()).setAction("!"));
		finish();
    }

}

}
