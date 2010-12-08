package com.alpha.param;

import com.alpha.view.Compass;
import com.alpha.view.Menu;
import com.alpha.view.R;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SettingsDialog extends Dialog implements OnClickListener
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
	   ((Compass)activity.findViewById(R.id.bCompassView)).setFreeze(true);
	setContentView(R.layout.set_config);
   getWindow().setFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND,
         WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
   this.assignButton();
}
	
private void assignButton()
{
((Button)findViewById(R.id.set_position)).setOnClickListener(this);
((Button)findViewById(R.id.set_calcul)).setOnClickListener(this);
((Button)findViewById(R.id.set_notification)).setOnClickListener(this);
((Button)findViewById(R.id.set_reciter)).setOnClickListener(this);
((Button)findViewById(R.id.set_theme)).setOnClickListener(this);
((Button)findViewById(R.id.set_about)).setOnClickListener(this);
}

@Override
public void dismiss() {
	   ((Compass)activity.findViewById(R.id.bCompassView)).setFreeze(false);
	   ((Menu)activity).reload();
	super.dismiss();
}

public void onClick(View v)
{
switch (v.getId())
{
case R.id.set_position : 
		SettingPosition set_pos = new SettingPosition(context,activity,false);
		set_pos.show(); break;
case R.id.set_notification : 
	SettingNotification set_not = new SettingNotification(context);
	set_not.show(); break;
case R.id.set_calcul : 
	SettingCalcul set_cal = new SettingCalcul(context);
	set_cal.show(); break;
case R.id.set_reciter : 
	SettingReciteur set_rec = new SettingReciteur(context);
	set_rec.show(); break;
case R.id.set_about : 
	(new SettingAbout(context)).show();	
					break;



}
	
}


}
