package com.alpha.view;

import java.util.List;
import javax.microedition.khronos.opengles.GL;

import com.alpha.model.Settings;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;
import com.google.android.maps.Projection;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DrawFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Picture;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

@SuppressWarnings("deprecation")
public class SamMapView extends MapActivity
{
   private SensorManager mSensorManager;
   private RotateView mRotateView;
   private MapView mMapView;
   public MyLocationOverlay mMyLocationOverlay;
   public Projection projection;
   public List<Overlay> mapOverlays;
   //public static Settings setting = Settings.getInstance();
   
   private class RotateView extends ViewGroup implements SensorListener {
       private static final float SQ2 = 1.414213562373095f;
       private final SmoothCanvas mCanvas = new SmoothCanvas();
       private float mHeading = 0;
       
       public RotateView(Context context) {
           super(context);
       }

       public void onSensorChanged(int sensor, float[] values) {
           //Log.d(TAG, "x: " + values[0] + "y: " + values[1] + "z: " + values[2]);
           synchronized (this) {
               mHeading = values[0];
               invalidate();
           }
       }

       @Override
       protected void dispatchDraw(Canvas canvas) {
           canvas.save(Canvas.MATRIX_SAVE_FLAG);
           canvas.rotate(-mHeading, getWidth() * 0.5f, getHeight() * 0.5f);
           mCanvas.delegate = canvas;
           super.dispatchDraw(mCanvas);
           canvas.restore();
       }

       @Override
       protected void onLayout(boolean changed, int l, int t, int r, int b) {
           final int width = getWidth();
           final int height = getHeight();
           final int count = getChildCount();
           for (int i = 0; i < count; i++) {
               final View view = getChildAt(i);
               final int childWidth = view.getMeasuredWidth();
               final int childHeight = view.getMeasuredHeight();
               final int childLeft = (width - childWidth) / 2;
               final int childTop = (height - childHeight) / 2;
               view.layout(childLeft, childTop, childLeft + childWidth, childTop + childHeight);
           }
       }

       @Override
       protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
           int w = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
           int h = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);
           int sizeSpec;
           if (w > h) {
               sizeSpec = MeasureSpec.makeMeasureSpec((int) (w * SQ2), MeasureSpec.EXACTLY);
           } else {
               sizeSpec = MeasureSpec.makeMeasureSpec((int) (h * SQ2), MeasureSpec.EXACTLY);
           }
           final int count = getChildCount();
           for (int i = 0; i < count; i++) {
               getChildAt(i).measure(sizeSpec, sizeSpec);
           }
           super.onMeasure(widthMeasureSpec, heightMeasureSpec);
       }

       @Override
       public boolean dispatchTouchEvent(MotionEvent ev) {
           // TODO: rotate events too
           return super.dispatchTouchEvent(ev);
       }

       public void onAccuracyChanged(int sensor, int accuracy) {
           // TODO Auto-generated method stub
           
       }
   }

   @Override
   public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);

       mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
       mRotateView = new RotateView(this);
       mMapView = new MapView(this, "0-Bw1fDiukvAPLOukypn-t6-C0YWbmLYyFq-W6w");
       mRotateView.addView(mMapView);
       setContentView(mRotateView);

       mMyLocationOverlay = new MyLocationOverlay(this, mMapView);
       mMyLocationOverlay.runOnFirstFix(new Runnable() { public void run() {
           mMapView.getController().animateTo(mMyLocationOverlay.getMyLocation());
       }});
       
       mMapView.getOverlays().add(mMyLocationOverlay);
       mMapView.getController().setZoom(18);
       mMapView.setClickable(true);
       mMapView.setEnabled(true);
       
       mapOverlays = mMapView.getOverlays();        
       projection = mMapView.getProjection();
       mapOverlays.add(new MyOverlay());    
       
       //ItemizedOverlay<OverlayItem> sam = new 
       mMapView.setSatellite(false);
   }

   @Override
   protected void onResume() {
       super.onResume();
       mSensorManager.registerListener(mRotateView,
               SensorManager.SENSOR_ORIENTATION,
               SensorManager.SENSOR_DELAY_UI);
       mMyLocationOverlay.enableMyLocation();
   }

   @Override
   protected void onStop() {
       mSensorManager.unregisterListener(mRotateView);
       mMyLocationOverlay.disableMyLocation();
       super.onStop();
   }

   @Override
   protected boolean isRouteDisplayed() {
       return false;
   }

   class MyOverlay extends Overlay{
   	 private Paint mPaint;
       
   	public MyOverlay()
   	{}
   	
   	public void draw(Canvas canvas,MapView mapView,boolean shadow)
   	{
   		super.draw(canvas, mapView, shadow);
   		mPaint = new Paint();
         mPaint.setDither(true);
         mPaint.setColor(Color.RED);
         mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
         mPaint.setStrokeJoin(Paint.Join.ROUND);
         mPaint.setStrokeCap(Paint.Cap.ROUND);
         mPaint.setStrokeWidth(2);
   		
         GeoPoint gP1 =  mapView.getMapCenter();
         
         //GeoPoint gP1 = new GeoPoint(mapView.getLatitudeSpan(),mapView.getLongitudeSpan());
         //GeoPoint gP1 = new GeoPoint(mMyLocationOverlay.getMyLocation().getLatitudeE6(),mMyLocationOverlay.getMyLocation().getLongitudeE6());
         //GeoPoint gP1 = new GeoPoint(mMyLocationOverlay.getMyLocation().getLatitudeE6(),mMyLocationOverlay.getMyLocation().getLongitudeE6());
         GeoPoint gP2 = new GeoPoint((int)Settings.getQibla_Lat()*1000000, (int)Settings.getQibla_Lon()*1000000);

         Point p1 = new Point();
         Point p2 = new Point();

         Path path = new Path();

         projection.toPixels(gP1, p1);
         projection.toPixels(gP2, p2);
         
         path.moveTo(p2.x, p2.y);
         path.lineTo(p1.x,p1.y);

         canvas.drawPath(path, mPaint);
   	}
   	
   }
   
   

   static final class SmoothCanvas extends Canvas {
       Canvas delegate;

       private final Paint mSmooth = new Paint(Paint.FILTER_BITMAP_FLAG);

       public void setBitmap(Bitmap bitmap) {
           delegate.setBitmap(bitmap);
       }

       public void setViewport(int width, int height) {
           delegate.setViewport(width, height);
       }

       public boolean isOpaque() {
           return delegate.isOpaque();
       }

       public int getWidth() {
           return delegate.getWidth();
       }

       public int getHeight() {
           return delegate.getHeight();
       }

       public int save() {
           return delegate.save();
       }

       public int save(int saveFlags) {
           return delegate.save(saveFlags);
       }

       public int saveLayer(RectF bounds, Paint paint, int saveFlags) {
           return delegate.saveLayer(bounds, paint, saveFlags);
       }

       public int saveLayer(float left, float top, float right, float
               bottom, Paint paint,
               int saveFlags) {
           return delegate.saveLayer(left, top, right, bottom, paint,
                   saveFlags);
       }

       public int saveLayerAlpha(RectF bounds, int alpha, int saveFlags) {
           return delegate.saveLayerAlpha(bounds, alpha, saveFlags);
       }

       public int saveLayerAlpha(float left, float top, float right,
               float bottom, int alpha,
               int saveFlags) {
           return delegate.saveLayerAlpha(left, top, right, bottom,
                   alpha, saveFlags);
       }

       public void restore() {
           delegate.restore();
       }

       public int getSaveCount() {
           return delegate.getSaveCount();
       }

       public void restoreToCount(int saveCount) {
           delegate.restoreToCount(saveCount);
       }

       public void translate(float dx, float dy) {
           delegate.translate(dx, dy);
       }

       public void scale(float sx, float sy) {
           delegate.scale(sx, sy);
       }

       public void rotate(float degrees) {
           delegate.rotate(degrees);
       }

       public void skew(float sx, float sy) {
           delegate.skew(sx, sy);
       }

       public void concat(Matrix matrix) {
           delegate.concat(matrix);
       }

       public void setMatrix(Matrix matrix) {
           delegate.setMatrix(matrix);
       }

       public void getMatrix(Matrix ctm) {
           delegate.getMatrix(ctm);
       }

       public boolean clipRect(RectF rect, Region.Op op) {
           return delegate.clipRect(rect, op);
       }

       public boolean clipRect(Rect rect, Region.Op op) {
           return delegate.clipRect(rect, op);
       }

       public boolean clipRect(RectF rect) {
           return delegate.clipRect(rect);
       }

       public boolean clipRect(Rect rect) {
           return delegate.clipRect(rect);
       }

       public boolean clipRect(float left, float top, float right,
               float bottom, Region.Op op) {
           return delegate.clipRect(left, top, right, bottom, op);
       }

       public boolean clipRect(float left, float top, float right,
               float bottom) {
           return delegate.clipRect(left, top, right, bottom);
       }

       public boolean clipRect(int left, int top, int right, int bottom) {
           return delegate.clipRect(left, top, right, bottom);
       }

       public boolean clipPath(Path path, Region.Op op) {
           return delegate.clipPath(path, op);
       }

       public boolean clipPath(Path path) {
           return delegate.clipPath(path);
       }

       public boolean clipRegion(Region region, Region.Op op) {
           return delegate.clipRegion(region, op);
       }

       public boolean clipRegion(Region region) {
           return delegate.clipRegion(region);
       }

       public DrawFilter getDrawFilter() {
           return delegate.getDrawFilter();
       }

       public void setDrawFilter(DrawFilter filter) {
           delegate.setDrawFilter(filter);
       }

       public GL getGL() {
           return delegate.getGL();
       }

       public boolean quickReject(RectF rect, EdgeType type) {
           return delegate.quickReject(rect, type);
       }

       public boolean quickReject(Path path, EdgeType type) {
           return delegate.quickReject(path, type);
       }

       public boolean quickReject(float left, float top, float right,
               float bottom,
               EdgeType type) {
           return delegate.quickReject(left, top, right, bottom, type);
       }

       public boolean getClipBounds(Rect bounds) {
           return delegate.getClipBounds(bounds);
       }

       public void drawRGB(int r, int g, int b) {
           delegate.drawRGB(r, g, b);
       }

       public void drawARGB(int a, int r, int g, int b) {
           delegate.drawARGB(a, r, g, b);
       }

       public void drawColor(int color) {
           delegate.drawColor(color);
       }

       public void drawColor(int color, PorterDuff.Mode mode) {
           delegate.drawColor(color, mode);
       }

       public void drawPaint(Paint paint) {
           delegate.drawPaint(paint);
       }

       public void drawPoints(float[] pts, int offset, int count,
               Paint paint) {
           delegate.drawPoints(pts, offset, count, paint);
       }

       public void drawPoints(float[] pts, Paint paint) {
           delegate.drawPoints(pts, paint);
       }

       public void drawPoint(float x, float y, Paint paint) {
           delegate.drawPoint(x, y, paint);
       }

       public void drawLine(float startX, float startY, float stopX,
               float stopY, Paint paint) {
           delegate.drawLine(startX, startY, stopX, stopY, paint);
       }

       public void drawLines(float[] pts, int offset, int count, Paint paint) {
           delegate.drawLines(pts, offset, count, paint);
       }

       public void drawLines(float[] pts, Paint paint) {
           delegate.drawLines(pts, paint);
       }

       public void drawRect(RectF rect, Paint paint) {
           delegate.drawRect(rect, paint);
       }

       public void drawRect(Rect r, Paint paint) {
           delegate.drawRect(r, paint);
       }

       public void drawRect(float left, float top, float right, float
               bottom, Paint paint) {
           delegate.drawRect(left, top, right, bottom, paint);
       }

       public void drawOval(RectF oval, Paint paint) {
           delegate.drawOval(oval, paint);
       }

       public void drawCircle(float cx, float cy, float radius, Paint paint) {
           delegate.drawCircle(cx, cy, radius, paint);
       }

       public void drawArc(RectF oval, float startAngle, float
               sweepAngle, boolean useCenter,
               Paint paint) {
           delegate.drawArc(oval, startAngle, sweepAngle, useCenter, paint);
       }

       public void drawRoundRect(RectF rect, float rx, float ry, Paint paint) {
           delegate.drawRoundRect(rect, rx, ry, paint);
       }

       public void drawPath(Path path, Paint paint) {
           delegate.drawPath(path, paint);
       }

       public void drawBitmap(Bitmap bitmap, float left, float top,
               Paint paint) {
           if (paint == null) {
               paint = mSmooth;
           } else {
               paint.setFilterBitmap(true);
           }
           delegate.drawBitmap(bitmap, left, top, paint);
       }

       public void drawBitmap(Bitmap bitmap, Rect src, RectF dst,
               Paint paint) {
           if (paint == null) {
               paint = mSmooth;
           } else {
               paint.setFilterBitmap(true);
           }
           delegate.drawBitmap(bitmap, src, dst, paint);
       }

       public void drawBitmap(Bitmap bitmap, Rect src, Rect dst, Paint paint) {
           if (paint == null) {
               paint = mSmooth;
           } else {
               paint.setFilterBitmap(true);
           }
           delegate.drawBitmap(bitmap, src, dst, paint);
       }

       public void drawBitmap(int[] colors, int offset, int stride,
               int x, int y, int width,
               int height, boolean hasAlpha, Paint paint) {
           if (paint == null) {
               paint = mSmooth;
           } else {
               paint.setFilterBitmap(true);
           }
           delegate.drawBitmap(colors, offset, stride, x, y, width,
                   height, hasAlpha, paint);
       }

       public void drawBitmap(Bitmap bitmap, Matrix matrix, Paint paint) {
           if (paint == null) {
               paint = mSmooth;
           } else {
               paint.setFilterBitmap(true);
           }
           delegate.drawBitmap(bitmap, matrix, paint);
       }

       public void drawBitmapMesh(Bitmap bitmap, int meshWidth, int
               meshHeight, float[] verts,
               int vertOffset, int[] colors, int colorOffset, Paint paint) {
           delegate.drawBitmapMesh(bitmap, meshWidth, meshHeight,
                   verts, vertOffset, colors,
                   colorOffset, paint);
       }

       public void drawVertices(VertexMode mode, int vertexCount,
               float[] verts, int vertOffset,
               float[] texs, int texOffset, int[] colors, int
               colorOffset, short[] indices,
               int indexOffset, int indexCount, Paint paint) {
           delegate.drawVertices(mode, vertexCount, verts,
                   vertOffset, texs, texOffset, colors,
                   colorOffset, indices, indexOffset, indexCount, paint);
       }

       public void drawText(char[] text, int index, int count, float
               x, float y, Paint paint) {
           delegate.drawText(text, index, count, x, y, paint);
       }

       public void drawText(String text, float x, float y, Paint paint) {
           delegate.drawText(text, x, y, paint);
       }

       public void drawText(String text, int start, int end, float x,
               float y, Paint paint) {
           delegate.drawText(text, start, end, x, y, paint);
       }

       public void drawText(CharSequence text, int start, int end,
               float x, float y, Paint paint) {
           delegate.drawText(text, start, end, x, y, paint);
       }

       public void drawPosText(char[] text, int index, int count,
               float[] pos, Paint paint) {
           delegate.drawPosText(text, index, count, pos, paint);
       }

       public void drawPosText(String text, float[] pos, Paint paint) {
           delegate.drawPosText(text, pos, paint);
       }

       public void drawTextOnPath(char[] text, int index, int count,
               Path path, float hOffset,
               float vOffset, Paint paint) {
           delegate.drawTextOnPath(text, index, count, path, hOffset,
                   vOffset, paint);
       }

       public void drawTextOnPath(String text, Path path, float
               hOffset, float vOffset,
               Paint paint) {
           delegate.drawTextOnPath(text, path, hOffset, vOffset, paint);
       }

       public void drawPicture(Picture picture) {
           delegate.drawPicture(picture);
       }

       public void drawPicture(Picture picture, RectF dst) {
           delegate.drawPicture(picture, dst);
       }

       public void drawPicture(Picture picture, Rect dst) {
           delegate.drawPicture(picture, dst);
       }
   }
}