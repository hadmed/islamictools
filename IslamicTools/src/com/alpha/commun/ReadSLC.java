package com.alpha.commun;

import java.io.File;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alpha.view.DocumentView;
import com.alpha.view.R;

public class ReadSLC extends ListActivity
{
private OpenSLCFile openSLCFile;
private String zipFile = null;
private File source;
private static final int CodeX = 1735;

@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setResult(RESULT_OK, new Intent());
	requestWindowFeature(Window.FEATURE_NO_TITLE);

	source = new File(getIntent().getExtras().getString("source"));	
	zipFile = getIntent().getExtras().containsKey("fileInZip") ? getIntent().getExtras().getString("fileInZip") : null;
	openSLCFile = zipFile !=null ? new OpenSLCFile(source,zipFile) : new OpenSLCFile(source,OpenSLCFile.TYPEREAD.CONFIG_AND_DATA);
	if (openSLCFile.getActif())
	{
		setListAdapter(new SLCAdapter(this,openSLCFile));
	} else
	{
		setResult(RESULT_CANCELED, (new Intent()).setAction("!"));
		finish();		
	}
}

@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		if (openSLCFile.getType()==1)
		{
			if ( !"_".equals(this.openSLCFile.getData("#",position)) )
			{
    		   	Intent intent;
    		   	intent = new Intent(this, openSLCFile.getOpener() == 1  ? DocumentView.class : ReadSLC.class);
    		   	Bundle bundle = new Bundle();
			 	bundle.putString("source", source.getAbsolutePath());
    		   	if (source.getName().endsWith(".slcz"))
    		   	{    		   		
    			 	bundle.putString("fileInZip", this.openSLCFile.getData("#",position) );    		   		
    		   	} 
			 	intent.putExtras(bundle);
			 	startActivityForResult(intent,CodeX);			 	
			}			
			
			
		}
	}
	
	@Override
		protected void onActivityResult(int requestCode, int resultCode, Intent data) {
			// TODO Auto-generated method stub
		if (requestCode == CodeX &&  resultCode == RESULT_CANCELED && "!".equals(data.getAction()))
		{
			Toast toast = Toast.makeText(this, R.string.file_not_found, Toast.LENGTH_SHORT);
			toast.show();
		}

		//super.onActivityResult(requestCode, resultCode, data);
			
			
		}
	private static class SLCAdapter extends BaseAdapter 
	{
		//private Context context;
		private OpenSLCFile openSLCFile;
		private LayoutInflater mInflater;

		public SLCAdapter(Context context,OpenSLCFile openSLCFile) {
			//this.context = context;
			this.openSLCFile = openSLCFile;
			this.mInflater = LayoutInflater.from(context);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return openSLCFile.length();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;
			if (convertView == null) {
				holder = new ViewHolder();
				if (openSLCFile.getType()==0)
				{
					convertView = mInflater.inflate(R.layout.slc_type0, null);
					holder.text = (TextView) convertView.findViewById(R.id.slc_txt1);
					holder.text2 = (TextView) convertView.findViewById(R.id.slc_txt2);
					holder.text3 = (TextView) convertView.findViewById(R.id.slc_txt3);
				}
				if (openSLCFile.getType()==1)
				{
					convertView = mInflater.inflate(R.layout.slc_type1, null);
					holder.text = (TextView) convertView.findViewById(R.id.slc_txt1);
				}
				if (openSLCFile.getType()==2)
				{
					convertView = mInflater.inflate(R.layout.slc_type2, null);
					holder.text = (TextView) convertView.findViewById(R.id.slc_txt1);
					holder.text2 = (TextView) convertView.findViewById(R.id.slc_txt2);
				}
				convertView.setTag(holder);

			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			try
			{         	 
				if(openSLCFile.getType()==0)
				{
					holder.text.setText(openSLCFile.getMessage("*",position)); //"Référence ["+(position+1)+"]" 
					if (isColor("*")) {holder.text.setTextColor(getColor("*"));}
					holder.text2.setText(openSLCFile.getMessage("_",position)); 
					if (isColor("_")) {holder.text2.setTextColor(getColor("_"));}
					holder.text3.setText(openSLCFile.getMessage("#",position)); //Rapporté par $ 
					if (isColor("#")) {holder.text3.setTextColor(getColor("#"));}
				}
				if(openSLCFile.getType()==1)
				{
					holder.text.setText(openSLCFile.getMessage("*",position)); 
					
					if ("_".equals(openSLCFile.getData("#", position)))
					{
						holder.text.setTextColor(0xCCFFFFFF);
					} else
					{
						holder.text.setTextColor(0xFF00FF00);
					}
				}
				if(openSLCFile.getType()==2)
				{
					holder.text.setText(openSLCFile.getMessage("_",position)); //"Référence ["+(position+1)+"]" 
					if (isColor("_")) {holder.text.setTextColor(getColor("_"));}
					holder.text2.setText(openSLCFile.getMessage("#",position)); 
					if (isColor("#")) {holder.text.setTextColor(getColor("#"));}
				}

			}catch(Exception ex){
			}
			return convertView;		
		}

		private boolean isColor(String code)
		{
			return openSLCFile.containtConfigKey(code+"+");
		}
		
		private int getColor(String code)
		{
			return openSLCFile.containtConfigKey(code+"+") ? Color.parseColor(openSLCFile.getConfigFromKey(code+"+")) : 0xCEFFFFFF;
		}
		
		static class ViewHolder {
			TextView text;
			TextView text2;
			TextView text3;	      	
		}


	}

}
