package com.ripecho.hacktech2014;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Display;
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

	
	public PixelGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
		parent = (DrawActivity) context;
		linePaint = new Paint();
		toolPaint = new Paint();
		bgPaint = new Paint();
		
		linePaint.setColor(Color.BLACK);
		linePaint.setStrokeWidth(0);
		toolPaint.setColor(parent.getColor());
		bgPaint.setColor(Color.GRAY);
		setBackgroundColor(Color.GRAY);
		setDrawingArea(32,32);
		
	}

	public void setParent(DrawActivity p){
		parent = p;
	}
	
	private void setDrawingArea(int x, int y){
		numPxX = x;
		numPxY = y;
		szX = getWidth()/x;
		if(szX*y > getHeight()) szY = szX = getHeight()/y;
		else szY = szX;
	}
	
	protected void onDraw(Canvas canvas){
		super.onDraw(canvas);
		for(int i = szX; i < getWidth(); i += szX){
			canvas.drawLine(i, 0, i, getHeight(), linePaint);
		}
		for(int i = szY; i < getHeight(); i += szY){
			canvas.drawLine(0, i, getWidth(), i, linePaint);
		}
	}
	
}
