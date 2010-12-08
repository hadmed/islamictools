package com.alpha.view;


import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


public class SermonView extends ListActivity
{
@Override
public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    setListAdapter(new SermonListAdapter(this));
  }

@Override
protected void onListItemClick(ListView l, View v, int position, long id) {
	Integer type = (Integer)mTitles[position][1]; 
	if (type>0)
	{
	//super.onListItemClick(l, v, position, id);
 	Intent intent = new Intent(this,DocumentView.class);
 	Bundle bundle = new Bundle();
 	bundle.putInt("type",type);
 	bundle.putString("source", "doc/sermon/FR/"+(String)mTitles[position][2]);
 	intent.putExtras(bundle);
 	startActivity(intent);
	}

}

private class SermonListAdapter extends BaseAdapter  {
   public SermonListAdapter(Context context) {
       mContext = context;
   }

   public int getCount() {
       return mTitles.length;
   }

   public Object getItem(int position) {
       return position;
   }

   public long getItemId(int position) {
       return position;
   }
   
   public View getView(int position, View convertView, ViewGroup parent) {
       SpeechView sv;
       if (convertView == null) {
           sv = new SpeechView(mContext, (String)mTitles[position][0],(Integer)mTitles[position][1]>0);
       } else {
           sv = (SpeechView) convertView;
           sv.setTitle((String)mTitles[position][0],(Integer)mTitles[position][1]>0);
       }
       return sv;
   }

   private Context mContext;
 
}


private Object[][] mTitles = 
{
{"Au revoir Ramadan!(khayaat)",1,"ramadan.html"},
{"Blâmer le fait de suivre les passions",1,"passions.html"},
{"Ceux qui prennent le chemin vers Allah",1,"chemin_allah.html"},
{"Et Il ne vous a imposés aucune gêne dans la religion",0},
{"L'accusation d'apostasie",0},
{"L'avertissement contre l'isolement",0},
{"L'importance des bonnes qualités dans l'Islam",0},
{"L'importance du mariage et ses principes",0},
{"L'interdiction de nuire aux musulmans",0},
{"L'école du mois de Ramadan",0},
{"L'économie islamique",0},
{"L'épreuve par les maladies et ses bénéfices",0},
{"La beauté et l'art dans l'Islam",0},
{"La bénédiction et les moyens de l'obtenir",0},
{"La compréhension du _juste milieu_ dans l'Islam",0},
{"La douceur de la foi",0},
{"La justice",0},
{"La loi des épreuves",0},
{"La modestie",1,"modestie.html"},
{"La prière",1,"priere.html"},
{"La relation conjugale",0},
{"La religion, c'est de conseiller les gens",0},
{"La responsabilité de la parole",0},
{"La réalisation de la servitude pour Allah",0},
{"La sécurité est une chose nécessaire pour l'homme",0},
{"La tentation de la déviation des compréhensions",0},
{"La veille du quinze Châbaane",0},
{"La véritable droiture dans la religion",0},
{"Le bienfait de la bonne santé et du temps libre",0},
{"Le Coeur pur",0},
{"Le danger de la lutte contre la vertu",0},
{"Le danger de médire des savants",0},
{"Le mois de Ramadan et le Coran",0},
{"Le mois de Ramadan une école et des leçons",0},
{"Le retour des pèlerins",0},
{"Le retour à la vérité est un mérite",0},
{"Le sang du musulman est protégé dans l'Islam",0},
{"Le temps est la vie",0},
{"Les actions des cours",0},
{"Les bénéfices du jeûne",0},
{"Les causes des problèmes conjugaux",0},
{"Les droits de la femme",0},
{"Les jeunes et les vacances",0},
{"Les leçons tirées du pèlerinage",0},
{"Les moyens de communication",0},
{"Les règles de l'accusation d'apostasie",0},
{"Les signes de la fin du monde",1,"fin_monde.html"},
{"Les versets du pèlerinage",0},
{"Ne soyez pas trompés par la vie de ce monde",0},
{"Nous ne devons craindre qu'Allah seul",0},
{"Pardonner aux gens",0},
{"Réflexions après le pèlerinage",0},
{"Se juger soi-même et corriger les situations",0}
};   

private class SpeechView extends LinearLayout {
   public SpeechView(Context context, String title,boolean enable) {
       super(context);

       this.setOrientation(VERTICAL);

       mTitle = new TextView(context);
       mTitle.setText(title);
       mTitle.setTextColor(enable ? 0xFF00FF00 : 0xFFc0c0c0  );
       mTitle.setHeight(30);
       addView(mTitle, new LinearLayout.LayoutParams(
               LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));

   }

   public void setTitle(String title,boolean enable) {
       mTitle.setText(title);
       mTitle.setTextColor(enable ? 0xFF00FF00 : 0xFFc0c0c0  );
       mTitle.setHeight(30);
   }


   private TextView mTitle;
}
	
}
