package com.alpha.view;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class QuranView extends ListActivity
{
	  private static class EfficientAdapter extends BaseAdapter {      
		  
	 private LayoutInflater mInflater;
     public EfficientAdapter(Context context) {
   	  mInflater = LayoutInflater.from(context);
     }
     public int getCount() {
         return SOURATE.length;
     }
     public Object getItem(int position) {
         return position;
     }
     public long getItemId(int position) {
         return position;
     }
     public View getView(int position, View convertView, ViewGroup parent) {
         ViewHolder holder;
         if (convertView == null) {
             convertView = mInflater.inflate(R.layout.sourate_txt, null);
             holder = new ViewHolder();
             holder.text = (TextView) convertView.findViewById(R.id.souratePH);
             holder.text.setTextColor(0xFF542800);
             holder.text.setHeight(24);
             holder.text2 = (TextView) convertView.findViewById(R.id.sourateFR);
             holder.text2.setTextColor(0xFFd86804);
             convertView.setTag(holder);

         } else {
             holder = (ViewHolder) convertView.getTag();
         }
         
         try
         {         	 
            holder.text.setText("["+(position+1)+"] "+SOURATE[position]); 
            holder.text2.setText(SOURATE_FR[position]); 
            
         }catch(Exception ex){
         }
         return convertView;
     }

     static class ViewHolder {
     	TextView text;
     	TextView text2;
     }
     
	}
	  
	public void onCreate(Bundle savedInstanceState) {
	       super.onCreate(savedInstanceState);
	       setContentView(R.layout.quran);
	       setListAdapter(new EfficientAdapter(this));
	   }

   protected void onListItemClick(ListView l, View v, int position, long id) {
   	Intent intent = new Intent(this,Sourate.class);
   	Bundle bundle = new Bundle();
   	bundle.putInt("sourate", position);
   	bundle.putString("titre", SOURATE[position] + ("".equals(SOURATE_FR[position])?"":(" ("+SOURATE_FR[position]+")")));
   	intent.putExtras(bundle);
   	startActivity(intent);
  }
	
	
	 private static final String[] SOURATE = {
		 "AL-FATIHA",
		 "AL-BAQARAH",
		 "AL-IMRAN",
		 "AN-NISA'",
		 "AL-MA-IDAH",
		 "AL-ANAM",
		 "AL-ARAF",
		 "AL-ANFAL",
		 "AT-TAWBAH",
		 "YUNUS",
		 "HOUD",
		 "YOUSOUF",
		 "AR-RAAD",
		 "IBRAHIM",
		 "AL-HIJR",
		 "AN-NAHL",
		 "AL-ISRA",
		 "AL-KAHF",
		 "MARYAM",
		 "TA-HA",
		 "AL-ANBIYA",
		 "AL-HAJJ",
		 "AL-MUMINUNE",
		 "AN-NUR",
		 "AL FURQANE",
		 "AS-SHUARAA",
		 "AN-NAML",
		 "AL-QASAS",
		 "AL-ANKABUT",
		 "AR-RUM",
		 "LUQMAN",
		 "AS-SAJDA",
		 "AL-AHZAB",
		 "SABA",
		 "FATIR",
		 "YA-SIN",
		 "SAFFAT",
		 "SAD",
		 "AZ-ZUMAR",
		 "GAFIR",
		 "FUSSILAT",
		 "ACHOURA",
		 "AZZUKHRUF",
		 "AD-DUKHAN",
		 "AL-JATHYA",
		 "AL-AHQAF",
		 "MOUHAMMAD",
		 "AL-FATH",
		 "AL-HUJURAT",
		 "QAF",
		 "AD-DARIYAT",
		 "AT-TUR",
		 "AN-NAJM",
		 "AL-QAMAR",
		 "AR-RAHMAN",
		 "AL-WAQI'A",
		 "AL-HADID",
		 "AL-MUJADALAH",
		 "AL-HASR",
		 "AL-MUMTAHANAH",
		 "AS-SAFF",
		 "AL-JUMUA",
		 "AL-MUNAFIQOUN",
		 "AT-TAGABOUN",
		 "AT-TALAQ",
		 "AT-TAHRIM",
		 "AL-MOULK",
		 "AL-QALAM",
		 "AL-HAQQAH",
		 "AL-MAARIJ",
		 "NOUH",
		 "AL-JINN",
		 "AL-MOUZZAMIL",
		 "AL-MOUDDATTIR",
		 "AL-QIYAMAH",
		 "AL-INSAN",
		 "AL-MOURSALATE",
		 "AN-NABA",
		 "AN-NAZIATE",
		 "ABASA",
		 "AT-TAKWIR",
		 "AL-INFITAR",
		 "AL- MOUTAFFIFOUNE",
		 "AL-INSIQAQ",
		 "AL-BOUROUJ",
		 "AT-TARIQ",
		 "AL-A'LA",
		 "AL-GHASIYAH",
		 "AL-FAJR",
		 "AL-BALAD",
		 "ACH-CHAMS",
		 "AL-LAYL",
		 "AD-DOUHA",
		 "AS-SARH",
		 "AT-TIN",
		 "AL-ALAQ",
		 "AL-QADR",
		 "AL-BAYYINAH",
		 "AZ-ZALZALAH",
		 "AL-ADIYATE",
		 "AL-QARIAH",
		 "AT-TAKATOUR",
		 "AL-ASR",
		 "AL-HOUMAZAH",
		 "AL-FIL",
		 "QOURAYSH",
		 "AL-MA'OUN",
		 "AL-KAWTAR",
		 "AL-KAFIROUNE",
		 "AN-NASR",
		 "AL-MASAD",
		 "AL-IHLAS",
		 "AL-FALAQ",
		 "AN-NAS"
	 };
	
	 private static final String[] SOURATE_FR = {
  "Prologue/ouverture",
 "La vache",
 "La famille d'Imran",
 "Les femmes",
 "La table servie",
 "Les bestiaux",
 "",
 "Le butin",
 "Le désaveu/le repentir",
 "Jonas",
 "",
 "Joseph",
 "Le tonnerre",
 "Abraham",
 "",
 "Les abeilles",
 "Le voyage nocturne",
 "La caverne",
 "Marie",
 "",
 "Les prophètes",
 "Le pèlerinage",
 "Les croyants",
 "La lumiere",
 "Le discernement",
 "Les poetes",
 "Les fourmis",
 "Le recit",
 "L'araignée",
 "Les romains",
 "",
 "Le prosternation",
 "Les coalisés",
 "",
 "Le créateur",
 "",
 "Les rangées",
 "",
 "Les groupes",
 "Le pardonneur",
 "Les versets detaillés",
 "La consultation",
 "L'ornement",
 "La fumée",
 "L'agenouillée",
 "",
 "",
 "La victoire éclatante",
 "Les appartements",
 "",
 "Qui éparpillent",
 "",
 "L'étoile",
 "La lune",
 "Le tout misericordieux",
 "L'événement",
 "Le fer",
 "La discussion",
 "L'exode",
 "L'éprouvée",
 "Le rang",
 "Le vendredi",
 "Les hypocrites",
 "La grande perte",
 "Le divorce",
 "L'interdiction",
 "La royaute",
 "La plume",
 "Celle qui montre la verité",
 "Les voies d'ascension",
 "Noé",
 "Les djinns",
 "L'enveloppe",
 "Le revêtu d'un manteau",
 "La resurrection",
 "L'homme",
 "Les envoyés",
 "La nouvelle",
 "Les anges qui arrachent les âmes",
 "Il s'est renfrogné",
 "L'obscurcissement",
 "La rupture",
 "Les fraudeurs",
 "La déchirure",
 "Les constellations",
 "L'astre nocturne",
 "Le très-haut",
 "L'enveloppante",
 "L'aube",
 "La cite",
 "Le soleil",
 "La nuit",
 "Le jour montant",
 "L'ouverture",
 "Le figuier",
 "L'adhérence",
 "La destinée",
 "La preuve",
 "La secousse",
 "Les coursiers",
 "Le fracas",
 "La course aux richesses",
 "Le temps",
 "Les calomniateurs",
 "L'éléphant",
 "Les Qurayshites",
 "L'ustensile",
 "L'abondance",
 "Les infidèles",
 "Les secours",
 "Les fibres",
 "Le monothéisme pur",
 "L'aube naissante",
 "Les hommes"
 };
 
}
