package com.alpha.view;

import java.util.Calendar;
import com.alpha.commun.Utils;
import com.alpha.pt.Date;
import com.alpha.pt.DateHijir;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

public class DayView extends Activity
{

final static String[] monthAR={"","Mouharram","Safar","Rabi' I","Rabi' II","Joumada I","Joumada II",
	"Rajab","Ch'ban","Ramadan","Chawwal","Dhou Al-Qi'da","Dhou Al-Hijja"};

final static DateHijir[] DAYISLAMIC=
{
new DateHijir(1, 1, R.id.day_01),
new DateHijir(10, 1, R.id.day_02),
new DateHijir(12, 3, R.id.day_03),
new DateHijir(26, 7, R.id.day_04),
new DateHijir(1, 9, R.id.day_05),
new DateHijir(26, 9, R.id.day_06),
new DateHijir(1, 10, R.id.day_07),
new DateHijir(10, 12, R.id.day_08)
};


private String getArabicDate(final DateHijir dh,final int yearH,final int thisYear)
{
Date dd= Utils.julian2calendar(Utils.hijir2Julian(dh.day,dh.month, yearH));
return (dd.year<thisYear) ? getArabicDate(dh, yearH+1, thisYear) : Utils.calendar2str(dd);
}

@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    setContentView(R.layout.day_view);

Calendar c = Calendar.getInstance();
double julian1 = Utils.calendar2Julian(c);
Date dd = Utils.julian2Hijir(julian1); 
int yearH = dd.year;   
for (DateHijir dayPos : DAYISLAMIC)
{
	((TextView)findViewById(dayPos.id_ref)).setText(getArabicDate(dayPos,yearH,c.get(Calendar.YEAR)));
}
//calcul astronomique
//double JulianDay = utils.calendar2Julian(Calendar.getInstance());
/*int jorSemaine = utils.getDayOfWeek(JulianDay);*/
//Date d1 = utils.julian2calendar(utils.hijir2Julian(1, 1, yearH));
//String recup = d1.day+"/"+d1.month+"/"+d1.year;
((TextView)findViewById(R.id.dateSam)).setText(dd.day+" "+monthAR[dd.month]+" "+dd.year);
}

}
