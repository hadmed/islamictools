package com.alpha.view;


import com.alpha.model.Settings;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

public class CompassView extends Activity {
 
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(new SampleView(this));
}

public static class SampleView extends View implements SensorEventListener {
   
   private final Bitmap mBitmap;
   private int angle,boussole;
   private final Matrix mMatrix = new Matrix();
   private final int x,y;
   private Settings setting; 
   
   public SampleView(Context context) {
       super(context);
       this.setting = Settings.getInstance(context);
       setFocusable(true);

       mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.boussole_fond);
       angle = 0;
       boussole = 0;
       x = mBitmap.getWidth()/2;
       y = mBitmap.getHeight()/2;
       //mMatrix.setTranslate(100, 100);
       SensorManager m = (SensorManager)context.getSystemService(Context.SENSOR_SERVICE);
       m.registerListener(this,m.getDefaultSensor(Sensor.TYPE_ORIENTATION),SensorManager.SENSOR_DELAY_NORMAL);

   }
  
   @Override protected void onDraw(Canvas canvas) {
       //canvas.drawColor(0xFF000000);
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

	/*
private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

private float initX, initY, radius;
private boolean drawing = false;

 public class MySurfaceThread extends Thread {
 	
 	private SurfaceHolder myThreadSurfaceHolder;
 	private MySurfaceView myThreadSurfaceView;
 	private boolean myThreadRun = false;
 	
 	public MySurfaceThread(SurfaceHolder surfaceHolder, MySurfaceView surfaceView) {
 		myThreadSurfaceHolder = surfaceHolder;
 		myThreadSurfaceView = surfaceView;
     }
 	
 	public void setRunning(boolean b) {
 		myThreadRun = b;
		}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		//super.run();
		while (myThreadRun) {
             Canvas c = null;
             try {
                 c = myThreadSurfaceHolder.lockCanvas(null);
                 synchronized (myThreadSurfaceHolder) {
                     myThreadSurfaceView.onDraw(c);
                 }
             } finally {
                 // do this in a finally so that if an exception is thrown
                 // during the above, we don't leave the Surface in an
                 // inconsistent state
                 if (c != null) {
                 	myThreadSurfaceHolder.unlockCanvasAndPost(c);
                 }
             }
         }
	}
}

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback{
	
	private MySurfaceThread thread;

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		//super.onDraw(canvas);
		if(drawing){
			canvas.drawCircle(initX, initY, radius, paint);
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		//return super.onTouchEvent(event);
		int action = event.getAction();
		if (action==MotionEvent.ACTION_MOVE){
			float x = event.getX();
			float y = event.getY();
			radius = (float) Math.sqrt(Math.pow(x-initX, 2) + Math.pow(y-initY, 2));
			}
		else if (action==MotionEvent.ACTION_DOWN){
			initX = event.getX();
			initY = event.getY();
			radius = 1;
			drawing = true;
			}
		else if (action==MotionEvent.ACTION_UP){
			drawing = false;
			}
		
		return true;
	}

	public MySurfaceView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init();
	}

	public MySurfaceView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init();
	}

	public MySurfaceView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		init();
	}
	
	private void init(){
		getHolder().addCallback(this);
		thread = new MySurfaceThread(getHolder(), this);
		
		setFocusable(true); // make sure we get key events
		
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeWidth(3);
		paint.setColor(Color.WHITE);
	}

	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2,
			int arg3) {
		// TODO Auto-generated method stub
	}


	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		thread.setRunning(true);
		thread.start();
	}


	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		boolean retry = true;
		thread.setRunning(false);
		while (retry) {
			try {
				thread.join();
				retry = false;
			} catch (InterruptedException e) {
			}
        }
	}
}

 @Override
 public void onCreate(Bundle savedInstanceState) {
     super.onCreate(savedInstanceState);
     MySurfaceView mySurfaceView = new MySurfaceView(this);
     setContentView(mySurfaceView);
 }
*/
}
