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

public class PixelGridView extends View {

	private DrawActivity parent;
	private Display display;
	private int cnvHeight;
	private int cnvWidth;
	private Bitmap bitmap;
	private Paint linePaint;
	private Paint toolPaint;
	private Paint bgPaint;
	
	public PixelGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		Point scrnSize = new Point();
		display.getSize(scrnSize);
		cnvHeight = (int)(scrnSize.y - 0.15*scrnSize.y);
		cnvWidth = scrnSize.x;
		bitmap = parent.getBitmap();
		linePaint.setColor(Color.BLACK);
		toolPaint.setColor(parent.getColor());
		bgPaint.setColor(Color.GRAY);
		
	}

	public void setParent(DrawActivity p){
		parent = p;
	}
	
	protected void onDraw(Canvas canvas){
		super.onDraw(canvas);
		canvas.drawRect(0,0,cnvWidth,cnvWidth/4, bgPaint);
		canvas.drawRect(0,3*cnvWidth/4,cnvWidth,cnvHeight,bgPaint);
		if(isDirty()){
			
		}
	}
	
}
