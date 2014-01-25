package com.ripecho.hacktech2014;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.*;


public class ColorPaletteView extends View implements OnClickListener, OnLongClickListener{

	public static final int MAX_PALETTE_COLORS = 16;
	private DrawActivity parent;
	private boolean isExpanded = false;
	private int dispWidth,dispHeight;

	private int[] paletteColors = new int[MAX_PALETTE_COLORS];
	
	public ColorPaletteView(Context context, AttributeSet attrs) {
		super(context, attrs);
		for(int i =0;i<MAX_PALETTE_COLORS;i++){
			paletteColors[i] = Color.TRANSPARENT;
		}
		
		Display display = ((Activity)context).getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		dispWidth = size.x;
		dispHeight = size.y;
	}
	
	public void setParent(DrawActivity parent){
		this.parent = parent;
	}
	
	protected void onDraw(Canvas paint){
		super.onDraw(paint);
		paint.drawColor(Color.CYAN);
		
		if(!isExpanded){
			Paint textColor = new Paint();
			textColor.setColor(Color.DKGRAY);
			paint.drawText("Color Palette", .1f*getWidth(), getHeight(), textColor);
		}
		else{
			drawColorWheel(getWidth()/2,getHeight()/3,paint);
		}
	}
	
	public void onClick(View click){
		if(isExpanded)
			parent.setColor(paletteColors[getPaletteIndex(click)]);
		else{
			setLayoutParams(new ViewGroup.LayoutParams(getWidth(),(int)(dispHeight*.25) ));
			isExpanded=true;
		}
			
		
	}
	
	public boolean onLongClick(View click){
		if(isExpanded){
			
			paletteColors[getPaletteIndex(click)] = 1; //FIX ME PLACEHOLDER
		}
		else
			isExpanded=true;
		
		return true;
	}
	
	private int getPaletteIndex(View click){
		int paletteX=0;
		int paletteY=0;
		
		if(click.getY()<this.getHeight()/2)
			paletteY=0;
		else
			paletteY=1;
		
		paletteX = (int) (click.getX()*8/this.getWidth());
		if(paletteX>8) paletteX=8;
		
		return paletteX+8*paletteY;
	}
	
	private void drawColorWheel(int x, int y, Canvas paint){
		Paint tempColour = new Paint();
		tempColour.setColor(Color.WHITE);
		tempColour.setStrokeWidth(y/8.0f);
		RectF bounds = new RectF(x-y/4,y-y/4,x+y/4,y+y/4);
		paint.drawOval(bounds, tempColour);
	}
	
}
