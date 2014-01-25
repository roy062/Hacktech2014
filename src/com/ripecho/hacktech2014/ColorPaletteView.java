package com.ripecho.hacktech2014;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
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
		
		Display display = parent.getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		dispWidth = size.x;
		dispHeight = size.y;
	}
	
	public void setParent(DrawActivity parent){
		this.parent = parent;
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
	
}
