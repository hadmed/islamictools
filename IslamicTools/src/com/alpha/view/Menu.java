package com.alpha.view;

import java.util.Calendar;

import com.alpha.commun.HadithList;
import com.alpha.commun.MsgNotification;
import com.alpha.model.PT;
import com.alpha.model.PrayerTime;
import com.alpha.model.Settings;
import com.alpha.param.SettingPosition;
import com.alpha.param.SettingsDialog;

import android.app.Activity;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author Selim Amroune
 * 
 */
public class Menu extends Activity implements OnClickListener {
	/** Called when the activity is first created. */
	private Context mContext;
	private Activity activity;
	private static int ParametreCode = 1;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		// getWindow().setFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND,WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.menu);
		mContext = this;
		activity = this;
		this.reload();
		MsgNotification.start(this, getString(R.string.menu_title),
				Notification.DEFAULT_LIGHTS);
	}

	// reload time prayer and association with button/picture
	public void reload() {
		setTimePrayer();
		setImageClick();
	}

	// fill prayer time and hadith
	private void setTimePrayer() {
		Settings settings = Settings.getInstance(this);
		Calendar now = Calendar.getInstance();
		PrayerTime[] prayerTimes = PT.getPrayerTimes(now.get(Calendar.YEAR),
				now.get(Calendar.MONTH) + 1, now.get(Calendar.DAY_OF_MONTH),
				settings.getLat(), settings.getLon(), settings.getGmt2(), 0,
				settings.getMethod());
		((TextView) findViewById(R.id.pt_fadjr)).setText(prayerTimes[0]
				.getTime());
		((TextView) findViewById(R.id.pt_shurooq)).setText(prayerTimes[1]
				.getTime());
		((TextView) findViewById(R.id.pt_zuhr)).setText(prayerTimes[2]
				.getTime());
		((TextView) findViewById(R.id.pt_asr))
				.setText(prayerTimes[3].getTime());
		((TextView) findViewById(R.id.pt_maghrib)).setText(prayerTimes[4]
				.getTime());
		((TextView) findViewById(R.id.pt_isha)).setText(prayerTimes[5]
				.getTime());
		((TextView) findViewById(R.id.pt_location)).setText(settings
				.getLocation());
		((TextView) findViewById(R.id.pt_qibla)).setText(settings
				.getAngleQibla());
		HadithList.Msg msg = HadithList.getInstance().getHadithTxt(-1);
		((TextView)findViewById(R.id.wHadith)).setText(msg.txt);
		((TextView)findViewById(R.id.wHadithSrc)).setText(msg.ref);
	}

	// associate button with action
	private void setImageClick() {
		((ImageView) findViewById(R.id.bQuran)).setOnClickListener(this);
		((Compass) findViewById(R.id.bCompassView)).setOnClickListener(this);
		((ImageView) findViewById(R.id.bName)).setOnClickListener(this);
		((View) findViewById(R.id.cHadith)).setOnClickListener(this);
		((ImageView) findViewById(R.id.bCalendar)).setOnClickListener(this);
		((ImageView) findViewById(R.id.bBook)).setOnClickListener(this);
		((TextView) findViewById(R.id.pt_location)).setOnClickListener(this);
		((TableLayout) findViewById(R.id.pt_table)).setOnClickListener(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		menu.add(R.string.set_parametre);
		return super.onCreateOptionsMenu(menu);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		default:
			SettingsDialog sd = new SettingsDialog(mContext, activity);
			sd.show();
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onActivityResult(int, int,
	 * android.content.Intent)
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// WakeLock.release();
		if (requestCode == ParametreCode) {
			reload();
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	public Context getMContext() {
		return this.mContext;
	}

	public void setMContext(Context context) {
		this.mContext = context;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onDestroy()
	 */
	@Override
	protected void onDestroy() {
		MsgNotification.stopNotification();
		super.onDestroy();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bQuran:
			startActivity(new Intent(mContext, QuranView.class));
			break;
		case R.id.bCompassView:
			startActivity(new Intent(mContext, BoussoleQibla.class));
			break;
		case R.id.bName:
			startActivity(new Intent(mContext, NameAllah.class));
			break;
		case R.id.cHadith:
			HadithList.Msg msg = HadithList.getInstance().getHadithTxt(-1);
			((TextView)findViewById(R.id.wHadith)).setText(msg.txt);
			((TextView)findViewById(R.id.wHadithSrc)).setText(msg.ref);
			break;
		case R.id.bCalendar:
			startActivity(new Intent(mContext, DayView.class));
			break;

		case R.id.bBook:
			startActivity(new Intent(mContext, BookView.class));
			break;

		case R.id.pt_location:
			Toast.makeText(mContext, R.string.init_DB, Toast.LENGTH_SHORT)
					.show();
			SettingPosition cvd = new SettingPosition(mContext, activity, true);
			cvd.show();
			break;
		case R.id.pt_table:
			startActivity(new Intent(mContext, CalendarView.class));
			break;
		}
	}

}