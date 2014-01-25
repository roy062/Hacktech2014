package com.ripecho.hacktech2014;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class ColorWheelView extends View{

	public ColorWheelView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
	}
	
	private void drawColorWheel(int x, int y, float r,Canvas paint){
		Paint tempColour = new Paint();
		tempColour.setColor(Color.WHITE);
		tempColour.setStrokeWidth(y/8.0f);
		RectF bounds = new RectF(x-r,y-r,x+r,y+r);
		paint.drawOval(bounds, tempColour);
	}
	
	protected void onDraw(Canvas paint){
		drawColorWheel(getWidth(),getHeight(),getWidth()*.8f,paint);
	}

}
