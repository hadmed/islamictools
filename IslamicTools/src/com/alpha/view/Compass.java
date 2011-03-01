package com.alpha.view;

import com.alpha.model.Settings;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.hardware.Sensor;
import android.util.AttributeSet;
import android.view.View;

public class Compass extends View 
{

   private Bitmap mBitmap;
   private int angle,boussole;
   private final Matrix mMatrix = new Matrix();
   private final int x,y;
   private Settings setting; 
   private boolean freeze;
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
		freeze = false;
		x = mBitmap.getWidth()/2;
		y = mBitmap.getHeight()/2;
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
	 if (!freeze)
	  {
      int angleFinal = (int) (boussole + setting.getDegQibla());
      angleFinal = (angleFinal+360)%360;

      angle = (angle+360)%360;
      int d = (int)(angle - angleFinal);
      d=(d+360)%360;
      if (d*d>=6)
     	 {
     	 if ((((angle - angleFinal)+360)%360)>=180)
     		 {
     		 angle += 2;
     		 } else
     		 {
     			 angle -=2;
     		 }
     	 }       
      mMatrix.setRotate((angle+360)%360, x, y);
      invalidate();
      canvas.drawBitmap(mBitmap, mMatrix, null);
		
	  }
	}


	public void onAccuracyChanged(Sensor sensor, int accuracy)
	{
		// TODO Auto-generated method stub
		
	}

	public void setFreeze(boolean freeze)
	{
		this.freeze = freeze;
		invalidate();
		//this.refreshDrawableState();
	}

}
