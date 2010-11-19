package com.alpha;

import com.alpha.model.Settings;
import com.alpha.view.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
 
/**
 * Permet de connaître à tout moment plusieurs positions :
 * - Accéléromètre : x,y,z
 * - Bousole : angle
 * - GPS : longitude, latitude, altitude, vitesse
 */
public final class PositionSensor implements    LocationListener,
                                                SensorEventListener
                                                {
    /**
     * Fréquence de rafraichissement du sensor en ms.
     */
    private final static int SENSOR_REFRESH_MS = 500;
 
    //private float angle = 0f;
    private long lastOrientationUpdate = System.currentTimeMillis();
 
    private double longitude, latitude, altitude;
    private long lastGpsUpdate = System.currentTimeMillis();
 
    private Activity activity = null;

    private Bitmap bmp;
    private ImageView img;
    private Context context; 
    private long lastRefreshLayoutUpdate = System.currentTimeMillis();
 
    /**
     * Constructeur mettant en place :
     * - la sonde accéléromètre;
     * - la sonde orientation;
     * - la sonde GPS.
     */
    public PositionSensor(Activity activity,Context context) {
        this.activity = activity;
        this.context = context;
        this.img=(ImageView)this.activity.findViewById(R.id.compassId);
        this.bmp = BitmapFactory.decodeResource(this.activity.getResources(), R.drawable.compass_back);
        
        SensorManager m = (SensorManager) activity.getSystemService(Context.SENSOR_SERVICE);
        //m.registerListener(this,m.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),SensorManager.SENSOR_DELAY_NORMAL);
        m.registerListener(this,m.getDefaultSensor(Sensor.TYPE_ORIENTATION),SensorManager.SENSOR_DELAY_NORMAL);
        LocationManager locationManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,this);
    }
 
    /**
     * Met à jour les données longitude,latitude,... dans la vue principale.
     */
    public void refreshLayout() {
            long currTime = System.currentTimeMillis();
            if (currTime - lastRefreshLayoutUpdate < SENSOR_REFRESH_MS)
                return;
            lastRefreshLayoutUpdate = System.currentTimeMillis();
          Settings settings = Settings.getInstance(this.context);
         settings.setPosGps(latitude, longitude, altitude);         
         ((TextView)activity.findViewById(R.id.pt_location)).setText(settings.getLocation());
    }
 
    /**
     * Exécuté par la sonde GPS pour mettre à jour des attributs contenant les coordonées GPS.
     */

    public void onLocationChanged(Location location) {
        {
            long currTime = System.currentTimeMillis();
            if (currTime - lastGpsUpdate < SENSOR_REFRESH_MS)
                return;
            lastGpsUpdate = System.currentTimeMillis();
        } // end-block
        altitude = location.getAltitude();
        latitude = location.getLatitude();
        longitude = location.getLongitude();
        //speed = location.getSpeed();
        refreshLayout();
    }
 
    //@Override
    public void onProviderDisabled(String provider) {
    }
 
    //@Override
    public void onProviderEnabled(String provider) {
    }
 
    //@Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }
 
    //@Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
 
    /**
     * Exécuté par les sondes accéléromètre et boussole pour mettre à jour les attributs x,y,z et angle.
     */
    //@Override
    public void onSensorChanged(SensorEvent event) {
 
        long currTime = System.currentTimeMillis();
 
        float values[] = event.values;
 
        switch (event.sensor.getType()) {
 
            /*case Sensor.TYPE_ACCELEROMETER :
                if (currTime - lastAccelerometerUpdate < SENSOR_REFRESH_MS)
                    break;
                lastAccelerometerUpdate = System.currentTimeMillis();
 
                x = values[0];
                y = values[1];
                z = values[2];
 
                break;*/
 
            case Sensor.TYPE_ORIENTATION :
 
                if (currTime - lastOrientationUpdate < SENSOR_REFRESH_MS)
                    break;
                lastOrientationUpdate = System.currentTimeMillis();
                Settings settings = Settings.getInstance(this.context);
                settings.setAngle(values[0]);         
                break;
        } 
        refreshLayout();
    }
 
}
