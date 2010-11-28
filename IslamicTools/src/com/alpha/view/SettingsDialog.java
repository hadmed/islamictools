package com.alpha.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class SettingsDialog extends Dialog
{
private Activity activity;
private Context  context ;

public SettingsDialog(Context context,Activity activity) {
	super(context);
	this.context = context;
	this.activity = activity;
}


@Override
protected void onCreate(Bundle savedInstanceState)
{
	super.onCreate(savedInstanceState);
	setContentView(R.layout.config);
   getWindow().setFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND,
         WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
   this.assignButton();
}
	
private void assignButton()
{
((Button)findViewById(R.id.set_position)).setOnClickListener(
		new View.OnClickListener() {
		 	  public void onClick(View view) {
		 		  ConfigCityView cvd = new ConfigCityView(context,activity);
		 		  cvd.show();
		 	  }});	
}


}
