package com.ripecho.hacktech2014;



import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.EditText;


public class ColorPaletteView extends View implements OnClickListener, OnLongClickListener,DialogInterface.OnClickListener{

	public static final int MAX_PALETTE_COLORS = 16;
	int currentIndex = 0;
	private DrawActivity parent;
	private boolean isExpanded = false;
	private int dispWidth,dispHeight;
	private boolean isColorWheel = false;
	private Paint color;
	ColorWheelDialog cwheel;

	private int[] paletteColors = new int[MAX_PALETTE_COLORS];
	
	public ColorPaletteView(Context context, AttributeSet attrs) {
		super(context, attrs);
		for(int i =0;i<MAX_PALETTE_COLORS;i++){
			paletteColors[i] = Color.TRANSPARENT;
		}
		cwheel = new ColorWheelDialog();
		color = new Paint();
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
			
			color.setColor(Color.DKGRAY);
			paint.drawText("Color Palette", .1f*getWidth(), getHeight(), color);
		}
		else{
			
			
			for(int i=0; i<8;i++){
				color.setColor(paletteColors[i]);
				paint.drawRect(i*getWidth()/8, 0, (i+1)*getWidth()/8, getHeight()/2, color);
				color.setColor(paletteColors[i+8]);
				paint.drawRect(i*getWidth()/8, getHeight()/2, (i+1)*getWidth()/8, getHeight(), color);
			}
		}
	}
	
	public void onClick(View click){
		if(!isColorWheel){
			currentIndex = getPaletteIndex(click);
			if(isExpanded&&paletteColors[getPaletteIndex(click)]!=Color.TRANSPARENT){
				parent.setColor(paletteColors[getPaletteIndex(click)]);
			}
			else if (isExpanded){
				
				cwheel.setListener(this);
				cwheel.show(cwheel.getFragmentManager(),"ColorWheel");
				isColorWheel = true;
			}
			else{
				setLayoutParams(new ViewGroup.LayoutParams(getWidth(),(int)(dispHeight*.25) ));
				isExpanded=true;
			}
				
		}
	}
	
	public boolean onLongClick(View click){
		if(isExpanded){
			cwheel.setListener(this);
			cwheel.show(cwheel.getFragmentManager(),"ColorWheel");
			isColorWheel = true;
			
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

	@Override
	public void onClick(DialogInterface dialog, int which) {
		EditText red = (EditText) findViewById(R.id.red);
		EditText green = (EditText) findViewById(R.id.green);
		EditText blue = (EditText) findViewById(R.id.blue);
		
		switch(which){
		case DialogInterface.BUTTON_POSITIVE:
			int r = Integer.parseInt(red.getText().toString());
			int g = Integer.parseInt(green.getText().toString());
			int b = Integer.parseInt(blue.getText().toString());
			paletteColors[currentIndex] = Color.rgb(r, g, b);
			break;
		case DialogInterface.BUTTON_NEGATIVE:
			break;
		default:
				break;
		}
		isColorWheel = false;
		dialog.dismiss();
		
	}
	
	
	
}
