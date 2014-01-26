package com.ripecho.hacktech2014;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.PopupMenu;


public class ColorPaletteView extends View implements View.OnClickListener, View.OnLongClickListener {

	public static final int MAX_PALETTE_COLORS = 16;
	int currentIndex = 0;
	private DrawActivity parent;
	private boolean isExpanded = false;
	private int dispWidth,dispHeight;
	private boolean isColorWheel = false;
	private Paint color;
	ColorWheelDialog cwheel;
	private int lastColorIndex;
	private PopupMenu colorMenu;

	private int[] paletteColors = new int[MAX_PALETTE_COLORS];
	
	public ColorPaletteView(Context context, AttributeSet attrs) {
		super(context, attrs);
		for(int i =0;i<MAX_PALETTE_COLORS;i++){
			paletteColors[i] = Color.TRANSPARENT;
		}
		cwheel = new ColorWheelDialog();
		//DUMMY TIME
		for(int i =0; i<16; i++){
			paletteColors[i]=Color.rgb((int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255));
		}
		color = new Paint();
		Display display = ((Activity)context).getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		dispWidth = size.x;
		dispHeight = size.y;
		this.setOnClickListener(this);
		parent = (DrawActivity)context;
		isExpanded = true;
	}
	
	public void setParent(DrawActivity parent){
		this.parent = parent;
	}
	
	protected void onDraw(Canvas paint){
		super.onDraw(paint);
		paint.drawColor(parent.getColor());
		
		/*if(!isExpanded){
			
			color.setColor(Color.DKGRAY);
			paint.drawText("Color Palette", .1f*getWidth(), getHeight(), color);
		}*/
		//else{
			
			
			for(int i=0; i<8;i++){
				color.setColor(paletteColors[i]);
				paint.drawRect(i*getWidth()/8, 0, (i+1)*getWidth()/8, getHeight()/2, color);
				color.setColor(paletteColors[i+8]);
				paint.drawRect(i*getWidth()/8, getHeight()/2, (i+1)*getWidth()/8, getHeight(), color);
			}
		//}
	}
	
	public boolean onTouchEvent(MotionEvent e)
	{
		//if(isExpanded)
		//{
		lastColorIndex = getPaletteIndex(e);
			
	
			if(paletteColors[lastColorIndex]!=Color.TRANSPARENT)
			{
				parent.setColor(paletteColors[lastColorIndex]);
			}
			//else if (isExpanded){
				
				//cwheel.setListener(this);
				//cwheel.show(cwheel.getFragmentManager(),"ColorWheel");
				//isColorWheel = true;
			//}
			//else{
				//setLayoutParams(new RelativeLayout.LayoutParams(getWidth(),(int)(dispHeight*.125) ));
			//isExpanded=false;
		//}
		//else
			//isExpanded = true;
		return true;
	}
	
	public boolean onLongClick(View click){
		//if(isExpanded){
			//cwheel.show(cwheel.getFragmentManager(),"ColorWheel");
			//isColorWheel = true;
			
		//}
		//else
			//isExpanded=true;
		AlertDialog.Builder builder = new AlertDialog.Builder(parent);
		builder.setTitle(R.string.dialog_title);
		builder.setView(parent.getLayoutInflater().inflate(R.layout.color_slider, null));
		
		builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
	           public void onClick(DialogInterface dialog, int id) {
	               // User clicked OK button
	           }
	       });
		builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
	           public void onClick(DialogInterface dialog, int id) {
	               // User cancelled the dialog
	           }
	       });
		
		AlertDialog dialog = builder.create();
		return true;
	}
	
	private int getPaletteIndex(MotionEvent e){
		int paletteX=0;
		int paletteY=0;
		
		if(e.getY()<this.getHeight()/2)
			paletteY=0;
		else
			paletteY=1;
		
		paletteX = (int) (e.getX()*8/this.getWidth());
		if(paletteX>8) paletteX=8;
		
		return paletteX+8*paletteY;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

	//@Override
	/*public void onClick(DialogInterface dialog, int which) {
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
		
	}*/
	
	
	
}
