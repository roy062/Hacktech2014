package com.ripecho.hacktech2014;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.OnScaleGestureListener;
import android.view.View;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

public class PixelGridView extends View {
	private DrawActivity parent;
	private Paint linePaint;
	private Paint toolPaint;
	private Paint bgPaint;
	private int szX;
	private int szY;
	private int numPxX;
	private int numPxY;
	private int width = 1;
	private int height = 1;
	private ScaleGestureDetector sgd;
	
	
	private static final int INVALID_POINTER_ID = -1;
	private int mActivePointerId = INVALID_POINTER_ID;
	private float mPosX;
	private float mPosY;
	private float mLastTouchX;
	private float mLastTouchY;
	private float mScaleFactor = 1.f;

	
	public PixelGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
		parent = (DrawActivity) context;
		linePaint = new Paint();
		toolPaint = new Paint();
		bgPaint = new Paint();
		
		linePaint.setColor(Color.BLACK);
		linePaint.setStrokeWidth(0);
		toolPaint.setColor(parent.getColor());
		bgPaint.setColor(Color.LTGRAY);
		bgPaint.setStyle(Paint.Style.FILL);
		setBackgroundColor(Color.DKGRAY);
		sgd = new ScaleGestureDetector(context, new ScaleListener());
	}

	public boolean onTouchEvent(MotionEvent ev) {
		
	    // Let the ScaleGestureDetector inspect all events.
	    sgd.onTouchEvent(ev);
	    
	    final int action = ev.getAction();
	    switch (action & MotionEvent.ACTION_MASK) {
	    case MotionEvent.ACTION_DOWN: {
	        final float x = ev.getX();
	        final float y = ev.getY();
	        
	        mLastTouchX = x;
	        mLastTouchY = y;
	        mActivePointerId = ev.getPointerId(0);
	        break;
	    }
	        
	    case MotionEvent.ACTION_MOVE: {
	        final int pointerIndex = ev.findPointerIndex(mActivePointerId);
	        final float x = ev.getX(pointerIndex);
	        final float y = ev.getY(pointerIndex);

	        // Only move if the ScaleGestureDetector isn't processing a gesture.
	        if (!sgd.isInProgress()) {
	            final float dx = x - mLastTouchX;
	            final float dy = y - mLastTouchY;

	            mPosX += dx;
	            mPosY += dy;

	            invalidate();
	        }

	        mLastTouchX = x;
	        mLastTouchY = y;

	        break;
	    }
	        
	    case MotionEvent.ACTION_UP: {
	        mActivePointerId = INVALID_POINTER_ID;
	        break;
	    }
	        
	    case MotionEvent.ACTION_CANCEL: {
	        mActivePointerId = INVALID_POINTER_ID;
	        break;
	    }
	    
	    case MotionEvent.ACTION_POINTER_UP: {
	        final int pointerIndex = (ev.getAction() & MotionEvent.ACTION_POINTER_INDEX_MASK) 
	                >> MotionEvent.ACTION_POINTER_INDEX_SHIFT;
	        final int pointerId = ev.getPointerId(pointerIndex);
	        if (pointerId == mActivePointerId) {
	            // This was our active pointer going up. Choose a new
	            // active pointer and adjust accordingly.
	            final int newPointerIndex = pointerIndex == 0 ? 1 : 0;
	            mLastTouchX = ev.getX(newPointerIndex);
	            mLastTouchY = ev.getY(newPointerIndex);
	            mActivePointerId = ev.getPointerId(newPointerIndex);
	        }
	        break;
	    }
	    }
	    
	    return true;
	}
	
	public void setParent(DrawActivity p){
		parent = p;
	}
	
	protected void onSizeChanged(int w, int h, int oldw, int oldh){
		width = w;
		height = h;
		setDrawingArea(32,32);
	}
	
	private void setDrawingArea(int x, int y){
		numPxX = x;
		numPxY = y;
		szX = width/x;
		if(szX*y > height) szY = szX = height/y;
		else szY = szX;
	}
	
	protected void onDraw(Canvas canvas){
		super.onDraw(canvas);
		
		canvas.save();
		canvas.scale(mScaleFactor, mScaleFactor, width/2, height/2);
		canvas.translate(mPosX, mPosY);
		canvas.drawRect(0, 0, width, height, bgPaint);
		for(int i = 0; i <= width; i += szX){
			canvas.drawLine(i, 0, i, height, linePaint);
			Log.d("DEBUG", "" + i);
		}
		for(int i = 0; i <= height; i += szY){
			canvas.drawLine(0, i, width, i, linePaint);
		}
		canvas.restore();
		
		invalidate();
	}
	
	private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
	    @Override
	    public boolean onScale(ScaleGestureDetector detector) {
	    	mScaleFactor *= detector.getScaleFactor();
	        
	        // Don't let the object get too small or too large.
	        mScaleFactor = Math.max((8/numPxX), Math.min(mScaleFactor, 5f));

	        invalidate();
	        return true;
	    }
	}
	
}


