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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class SettingCalcul extends Dialog implements android.view.View.OnClickListener, OnItemSelectedListener
{
	private Settings setting;
	private Context  context;

	public SettingCalcul(Context context)
	{
		super(context);
		this.context = context;
	}

@Override
protected void onCreate(Bundle savedInstanceState)
{
   super.onCreate(savedInstanceState);
   requestWindowFeature(Window.FEATURE_NO_TITLE);
   setContentView(R.layout.set_calcul);
   this.reset();
}

private void reset()
{
   setting = Settings.getInstance(context);
   int pos= setting.getMethod();
	List<String> methods = new ArrayList<String>(); 
	methods.add(context.getString(R.string.EGYPT_SURVEY)); //EGYPT_SURVEY
	methods.add(context.getString(R.string.KARACHI_SHAF)); //KARACHI_SHAF
	methods.add(context.getString(R.string.KARACHI_HANAF)); //KARACHI_HANAF
	methods.add(context.getString(R.string.NORTH_AMERICA)); //NORTH_AMERICA
	methods.add(context.getString(R.string.MUSLIM_LEAGUE)); //MUSLIM_LEAGUE
	methods.add(context.getString(R.string.UMM_ALQURRA)); //UMM_ALQURRA
	methods.add(context.getString(R.string.FIXED_ISHAA)); //FIXED_ISHAA

	Spinner spin = (Spinner) findViewById(R.id.set_cal_method);
	ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, methods.toArray(new String[0])); 
	adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	spin.setAdapter(adapter);      
	spin.setSelection(pos);
	spin.setOnItemSelectedListener(this);
	
	
	((TextView)findViewById(R.id.set_cal_fadjr)).setText(""+setting.getOffset()[0]);
	((TextView)findViewById(R.id.set_cal_shurooq)).setText(""+setting.getOffset()[1]);
	((TextView)findViewById(R.id.set_cal_zuhr)).setText(""+setting.getOffset()[2]);
	((TextView)findViewById(R.id.set_cal_asr)).setText(""+setting.getOffset()[3]);
	((TextView)findViewById(R.id.set_cal_maghrib)).setText(""+setting.getOffset()[4]);
	((TextView)findViewById(R.id.set_cal_isha)).setText(""+setting.getOffset()[5]);
	
	this.setAssignation(true);
}


private void setAssignation(boolean assign)
{
if (assign)
	{
	((Button)findViewById(R.id.set_cal_save)).setOnClickListener(this);
	((Button)findViewById(R.id.set_cal_reset)).setOnClickListener(this);
	}
}

private void save()
{
	float[] offset = new float[6];
	offset[0] = Float.parseFloat(((TextView)findViewById(R.id.set_cal_fadjr)).getText().toString());
	offset[1] = Float.parseFloat(((TextView)findViewById(R.id.set_cal_shurooq)).getText().toString());
	offset[2] = Float.parseFloat(((TextView)findViewById(R.id.set_cal_zuhr)).getText().toString());
	offset[3] = Float.parseFloat(((TextView)findViewById(R.id.set_cal_asr)).getText().toString());
	offset[4] = Float.parseFloat(((TextView)findViewById(R.id.set_cal_maghrib)).getText().toString());
	offset[5] = Float.parseFloat(((TextView)findViewById(R.id.set_cal_isha)).getText().toString());
	setting.setOffset(offset);
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

private void setMethod(int method)
{
/*enable/disable area*/	
setting.setMethod(method);
}


@Override
public void onItemSelected(AdapterView<?> source, View view, int idx, long idx2) {
switch (source.getId())
{
case R.id.set_cal_method:
	setMethod(idx);
	break;

}	
	
}

@Override
public void onNothingSelected(AdapterView<?> arg0) {
	// TODO Auto-generated method stub
	
}

}
