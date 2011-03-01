package com.alpha.commun;

import android.content.Context;
import android.os.PowerManager;
//import android.os.Vibrator;

public class WakeLock {

	private static PowerManager.WakeLock wakeLock;

	public static void acquire(Context context) {
		if(wakeLock != null && wakeLock.isHeld()) return;

		wakeLock = ((PowerManager)context.getSystemService(Context.POWER_SERVICE)).newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "Alarme - Islamic Tools!");
		wakeLock.acquire();
	}

	public static void release() {
		if(wakeLock != null && wakeLock.isHeld()) {
			wakeLock.release();
		}
		wakeLock = null;
	}
}