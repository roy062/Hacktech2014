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
	private int width = 1;
	private int height = 1;

	
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
	
	protected void onSizeChanged(int w, int h, int oldw, int oldh){
		width = w;
		height = h;
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
		for(int i = szX; i < width; i += szX){
			canvas.drawLine(i, 0, i, height, linePaint);
		}
		for(int i = szY; i < height; i += szY){
			canvas.drawLine(0, i, width, i, linePaint);
		}
	}
	
}
