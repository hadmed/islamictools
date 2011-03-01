package com.alpha.view;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class NameAllah extends Activity
{

	 @Override
	  public void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	      setContentView(R.layout.name_allah);
	      setTitle("Les 99 noms d'Allah");
	      //GridView gridview = (GridView)findViewById(R.id.gridNameAllah); 
	      ListView listView = (ListView)findViewById(R.id.gridNameAllah);
	      ArrayList<HashMap<String, Object>> nameList = new ArrayList<HashMap<String, Object>>(); 
/**/
   		List<String> name = this.charger("trad");
   		List<String> name2 = this.charger("name99_fr");

	      for(int i = 0;i < mThumbIds.length;i++) { 
	      	HashMap<String, Object> map = new HashMap<String, Object>(); 
	      	map.put("img", mThumbIds[i]); 
	      	map.put("txt", name.get(i)); 
	      	map.put("txt2", name2.get(i)); 
	      	nameList.add(map); 
	      	} 
	      SimpleAdapter saNameItem = new SimpleAdapter(this, 
	      		  nameList, 
	      		  R.layout.name_allah_item, 
	      		  new String[]{"img","txt","txt2"}, 
	      		  new int[]{R.id.ItemImage,R.id.ItemText,R.id.ItemText2});  

	      		listView.setAdapter(saNameItem); 
	 }

	 private List<String> charger(String src)
	 {//
		 List<String> rs = new ArrayList<String>();
	     try
 	     {         	
 	     InputStream fis = getAssets().open("allah/"+src+".txt");
 	     if (fis ==null)         	
 	   	  	{
 	   	  		Log.d("sam", "error fichier");
 	   	  	} else
 	   	  	{
 	   	      BufferedReader bread = new BufferedReader(new InputStreamReader(fis));
 	   	    while (bread.ready()) {
 	   	       rs.add(bread.readLine());
 	   	    }
 	   	  	fis.close(); 
 	   	  	}
 	  }catch(Exception ex)
 	  {
 	 	 Log.d("sam","error : "+ex.getMessage());         	 
 	  } 
		 return rs; 
	 }
	 
	   private static final Integer[] mThumbIds = {
	   		R.drawable.allah,R.drawable.rahman,R.drawable.rahim
	   		,R.drawable.malik,R.drawable.quddus,R.drawable.salam
	   		,R.drawable.mumin,R.drawable.muhay,R.drawable.aziz
	   		,R.drawable.jabbar,R.drawable.mutakabbir,R.drawable.khaliq
	   		,R.drawable.bari,R.drawable.musawwir,R.drawable.ghaffar
	   		,R.drawable.qahhar,R.drawable.wahhab,R.drawable.razzaq
	   		,R.drawable.fattah,R.drawable.alim,R.drawable.qabid
	   		,R.drawable.basit,R.drawable.khafid,R.drawable.rafi
	   		,R.drawable.muizz,R.drawable.baith,R.drawable.shahid
	   		,R.drawable.haqq,R.drawable.wakil,R.drawable.qawi
	   		,R.drawable.matin,R.drawable.wali1,R.drawable.hamid
	   		,R.drawable.muhsi,R.drawable.mubdi,R.drawable.muid
	   		,R.drawable.muhyi,R.drawable.mumit,R.drawable.hayy
	   		,R.drawable.qayyum,R.drawable.wajid,R.drawable.majid2
	   		,R.drawable.wahid,R.drawable.samad,R.drawable.qadir
	   		,R.drawable.muqtad,R.drawable.muqad,R.drawable.muakh
	   		,R.drawable.awwal,R.drawable.akhir,R.drawable.mudhill
	   		,R.drawable.sami,R.drawable.basir,R.drawable.hakam
	   		,R.drawable.adl,R.drawable.latif,R.drawable.khabir
	   		,R.drawable.halim,R.drawable.azim,R.drawable.ghafur
	   		,R.drawable.shakur,R.drawable.ali,R.drawable.kabir
	   		,R.drawable.hafiz,R.drawable.muqit,R.drawable.hasib
	   		,R.drawable.jalil,R.drawable.karim,R.drawable.raqib
	   		,R.drawable.mujib,R.drawable.wasi,R.drawable.hakim
	   		,R.drawable.wadud,R.drawable.majid1,R.drawable.zahir
	   		,R.drawable.batin,R.drawable.wali2,R.drawable.mutaali
	   		,R.drawable.barr,R.drawable.tawwab,R.drawable.muntaq
	   		,R.drawable.afu,R.drawable.rauf,R.drawable.malik2
	   		,R.drawable.dhul,R.drawable.muqsit,R.drawable.jami
	   		,R.drawable.ghani,R.drawable.mughni,R.drawable.mani
	   		,R.drawable.darr,R.drawable.nafi,R.drawable.nur
	   		,R.drawable.hadi,R.drawable.badi,R.drawable.baqi
	   		,R.drawable.warith,R.drawable.rashid,R.drawable.sabur
	   };
}
