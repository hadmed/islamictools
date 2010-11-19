package com.alpha.view;

import com.alpha.model.Settings;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.AttributeSet;
import android.view.View;

public class Compass extends View implements SensorEventListener
{

   private Bitmap mBitmap;
   private int angle,boussole;
   private final Matrix mMatrix = new Matrix();
   private final int x,y;
   private Settings setting; 

	public Compass(Context context)
	{
		this(context,null);
		// TODO Auto-generated constructor stub
	}


	public Compass(Context context, AttributeSet attrs)
	{
		this(context, attrs, 0);
		// TODO Auto-generated constructor stub
	}

	public Compass(Context context, AttributeSet attrs, int defStyle)
	{
		super(context,attrs);
		this.setting = Settings.getInstance(context);
      mBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.compass_back);
		angle = 0;
		boussole = 0;
		x = mBitmap.getWidth()/2;
		y = mBitmap.getHeight()/2;
		SensorManager m = (SensorManager)context.getSystemService(Context.SENSOR_SERVICE);
		m.registerListener(this,m.getDefaultSensor(Sensor.TYPE_ORIENTATION),SensorManager.SENSOR_DELAY_NORMAL);
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
      int angleFinal = (int) (boussole + setting.getDegQibla());
      angleFinal = (angleFinal+360)%360;
      angle = (angle+360)%360;
      int d = (int)(angle - angleFinal);
      d=(d+360)%360;
      if (d*d>=10)
     	 {
     	 if ((((angle - angleFinal)+360)%360)>=180)
     		 {
     		 angle += 3;
     		 } else
     		 {
     			 angle -=3;
     		 }
     	 }       
      mMatrix.setRotate((angle+360)%360, x, y);
      invalidate();
      canvas.drawBitmap(mBitmap, mMatrix, null);

	}


	public void onAccuracyChanged(Sensor sensor, int accuracy)
	{
		// TODO Auto-generated method stub
		
	}


	public void onSensorChanged(SensorEvent event)
	{
	   float values[] = event.values;
	   switch (event.sensor.getType()) {
	   case Sensor.TYPE_ORIENTATION :
	      //if (currTime - lastOrientationUpdate < SENSOR_REFRESH_MS)    break;
	      //lastOrientationUpdate = System.currentTimeMillis();
	      //boussole = (int)values[0];
	      break;
	   }
		
	}

}
