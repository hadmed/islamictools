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
import android.widget.Spinner;

public class SettingCalcul extends Dialog implements android.view.View.OnClickListener
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
   // getWindow().setFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND,WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
   setContentView(R.layout.set_calcul);
   this.reset();
}

private void reset()
{
   setting = Settings.getInstance(context);
   int pos= setting.getMethod();
	List<String> methods = new ArrayList<String>(); 
	
	/*
	<string name="egypt_survey"></string>
    <string name="karachi_shaf"></string>
    <string name="karachi_hanaf"></string>
    <string name="north_america"></string>
    <string name="muslim_league"></string>
    <string name="umm_alqurra"></string>
    <string name="fixed_ishaa"></string>

	 * 
	 * */
	
	methods.add("Le Cadastre général d'Egypte"); //EGYPT_SURVEY
	methods.add("Shaf`i - L'Université des Sciences Islamiques de Karachi"); //KARACHI_SHAF
	methods.add("Hanafi - L'Université des Sciences Islamiques de Karachi"); //KARACHI_HANAF
	methods.add("La Fédération Islamique de l'Amérique du nord"); //NORTH_AMERICA
	methods.add("La Ligue du Monde Islamique"); //MUSLIM_LEAGUE
	methods.add("Umm Ul Qurâ,  Arabie Saoudite"); //UMM_ALQURRA
	methods.add("Intervalle fixe pour Ishaa (toujours à 90)"); //FIXED_ISHAA

	Spinner spin = (Spinner) findViewById(R.id.set_cal_method);
	ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, methods.toArray(new String[0])); 
	adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	spin.setAdapter(adapter);      
	spin.setSelection(pos); //france par defaut
	//spin.setOnItemClickListener(this);
	this.setNotification(true);
}


private void setNotification(boolean assign)
{
if (assign)
	{
	((Button)findViewById(R.id.set_cal_save)).setOnClickListener(this);
	((Button)findViewById(R.id.set_cal_reset)).setOnClickListener(this);
	}
}

private void save()
{
	setting.save();
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
