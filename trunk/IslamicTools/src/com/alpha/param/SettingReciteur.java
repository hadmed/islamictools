package com.alpha.param;

import java.util.ArrayList;
import java.util.List;

import com.alpha.model.Settings;
import com.alpha.view.R;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class SettingReciteur extends Dialog implements android.view.View.OnClickListener
{
	private Settings setting;

	public SettingReciteur(Context context)
	{
		super(context);
	}

@Override
protected void onCreate(Bundle savedInstanceState)
{
   super.onCreate(savedInstanceState);
   requestWindowFeature(Window.FEATURE_NO_TITLE);
   // getWindow().setFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND,WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
   setContentView(R.layout.set_reciter);
   this.reset();
}

private void reset()
{
   setting = Settings.getInstance(this.getContext());
   //int pos= setting.getMethod();
	List<String> reciter = new ArrayList<String>(); 
		
	reciter.add("Abdallah Mohammad al-Matroud"); 
	reciter.add("Mishary Rashid Alafasy"); 

	Spinner spin = (Spinner) findViewById(R.id.set_rec_name);
	ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_item, reciter.toArray(new String[0])); 
	adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	spin.setAdapter(adapter);      
	//spin.setSelection(pos); //france par defaut
	//spin.setOnItemClickListener(this);
	this.setNotification(true);
}


private void setNotification(boolean assign)
{
	
((EditText)findViewById(R.id.set_rec_folder)).setText(setting.getFolderQuran());	
	
if (assign)
	{
	((Button)findViewById(R.id.set_cal_save)).setOnClickListener(this);
	((Button)findViewById(R.id.set_cal_reset)).setOnClickListener(this);
	}
}

private void save()
{
	setting.save();
	dismiss();
}

@Override
public void onClick(View v) {
switch (v.getId())
{
	case R.id.set_cal_save : this.save(); break;
	case R.id.set_cal_reset : this.reset(); break;
}
}

}
