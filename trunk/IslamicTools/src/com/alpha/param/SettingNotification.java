package com.alpha.param;

import com.alpha.commun.Param;
import com.alpha.model.Settings;
import com.alpha.view.R;
import com.alpha.view.R.drawable;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

public class SettingNotification extends Dialog implements android.view.View.OnClickListener
{
	private Settings setting;
	private Context  context;
	private Boolean spk_fadjr,spk_zuhr,spk_asr,spk_maghrib,spk_isha;

	public SettingNotification(Context context)
	{
		super(context);
		this.context = context;
	}

@Override
protected void onCreate(Bundle savedInstanceState)
{
   super.onCreate(savedInstanceState);
   requestWindowFeature(Window.FEATURE_NO_TITLE);
   // getWindow().setFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND,WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
   setContentView(R.layout.set_notification);
   this.reset();
}

private void reset()
{
   setting = Settings.getInstance(context);   
   spk_fadjr = setting.isSpkOn(Param.fadjr);
   spk_zuhr = setting.isSpkOn(Param.zuhr);
   spk_asr = setting.isSpkOn(Param.asr);
   spk_maghrib = setting.isSpkOn(Param.maghrib);
   spk_isha = setting.isSpkOn(Param.isha);
   this.setNotification(true);
}

private void setSpk(int src,boolean actif,boolean assign)
{
ImageView img =	(ImageView)findViewById(src);
img.setImageResource(actif ? R.drawable.speaker_on : R.drawable.speaker_off);
if (assign)
	img.setOnClickListener(this);
}

private void setNotification(boolean assign)
{
this.setSpk(R.id.set_spk_fadjr, spk_fadjr,assign);
this.setSpk(R.id.set_spk_zuhr, spk_zuhr,assign);
this.setSpk(R.id.set_spk_asr, spk_asr,assign);
this.setSpk(R.id.set_spk_maghrib, spk_maghrib,assign);
this.setSpk(R.id.set_spk_isha, spk_isha,assign);
if (assign)
	{
	((Button)findViewById(R.id.set_notif_save)).setOnClickListener(this);
	((Button)findViewById(R.id.set_notif_reset)).setOnClickListener(this);
	}
}

private void save()
{
int notif = 0;
notif += spk_fadjr ? Param.fadjr : 0;
notif += spk_zuhr ? Param.zuhr: 0;
notif += spk_asr ? Param.asr : 0;
notif += spk_maghrib ? Param.maghrib : 0;
notif += spk_isha ? Param.isha : 0;
setting.setNotification(notif);	
setting.save();
}

@Override
public void onClick(View v) {
switch (v.getId())
{
case R.id.set_spk_fadjr: spk_fadjr = !spk_fadjr; break; 
case R.id.set_spk_zuhr: spk_zuhr = !spk_zuhr; break; 
case R.id.set_spk_asr: spk_asr = !spk_asr; break; 
case R.id.set_spk_maghrib: spk_maghrib = !spk_maghrib; break; 
case R.id.set_spk_isha: spk_isha = !spk_isha; break; 
case R.id.set_notif_save : this.save(); break;
case R.id.set_notif_reset : this.reset(); break;
}
this.setNotification(false);
}

}
