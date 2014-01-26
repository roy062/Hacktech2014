package com.ripecho.hacktech2014;

import java.util.Timer;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.Display;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.SeekBar;


public class ColorPaletteView extends View implements GestureDetector.OnGestureListener {

	public static final int MAX_PALETTE_COLORS = 16;
	int currentIndex = 0;
	private DrawActivity parent;
	private boolean isExpanded = false;
	private int dispWidth,dispHeight;
	private boolean isColorWheel = false;
	private Paint color;
	ColorWheelDialog cwheel;
	private int lastColorIndex;
	private int finalizedColorIndex;
	private PopupMenu colorMenu;
	private Timer t;
	private Bitmap randomButton;

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
		parent = (DrawActivity)context;
		isExpanded = true;
		randomButton = BitmapFactory.decodeResource(parent.getResources(), R.drawable.random);
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
				if (i != 7)
					paint.drawRect(i*getWidth()/8, getHeight()/2, (i+1)*getWidth()/8, getHeight(), color);
				else
					paint.drawBitmap(randomButton, new Rect(0, 0, 1080, 1080), new Rect(7 * getWidth() / 8, getHeight()/2, getWidth(), getHeight()), color);
			}
		//}
	}
	
	public boolean onTouchEvent(MotionEvent e)
	{
		//if(isExpanded)
		//{
		lastColorIndex = getPaletteIndex(e);
			
			if(lastColorIndex == 15) // randomize
			{
				for (int i = 0; i < 15; i++)
					paletteColors[i] = 0xFF000000 + (int)(Math.random() * 0x00FFFFFF);
				invalidate();
			}
			else if (paletteColors[lastColorIndex]!=Color.TRANSPARENT)
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
		/*if (e.getAction() == MotionEvent.ACTION_DOWN && t == null)
		{
			t = new Timer();
			t.schedule(new TimerTask() { public void run() { onLongClick(null); } }, 1000);
		}
		else if (e.getAction() == MotionEvent.ACTION_UP && t != null)
		{
			t.cancel();
			t = null;
		}*/
		return true;
	}
	
	public void onLongPress(MotionEvent e){
		//if(isExpanded){
			//cwheel.show(cwheel.getFragmentManager(),"ColorWheel");
			//isColorWheel = true;
			
		//}
		//else
			//isExpanded=true;
		AlertDialog.Builder builder = new AlertDialog.Builder(parent);
		builder.setTitle(R.string.dialog_title);
		builder.setView(parent.getLayoutInflater().inflate(R.layout.color_slider, null));
		finalizedColorIndex = lastColorIndex;
		
		builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
	           public void onClick(DialogInterface dialog, int id) {
	        	  int r = ((SeekBar)findViewById(R.id.red)).getThumbOffset();
		       		int g = ((SeekBar)findViewById(R.id.green)).getThumbOffset();
		       		int b = ((SeekBar)findViewById(R.id.blue)).getThumbOffset();
		       		paletteColors[finalizedColorIndex] = 0xFF000000 + r << 4 + g << 2 + b;
	           }
	       });
		builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
	           public void onClick(DialogInterface dialog, int id) {
	               // User cancelled the dialog
	           }
	       });
		
		AlertDialog dialog = builder.create();
		((SeekBar)dialog.findViewById(R.id.red)).setThumbOffset(paletteColors[finalizedColorIndex] & 0x00FF0000 >> 4);
		((SeekBar)dialog.findViewById(R.id.green)).setThumbOffset(paletteColors[finalizedColorIndex] & 0x0000FF00 >> 2);
		((SeekBar)dialog.findViewById(R.id.blue)).setThumbOffset(paletteColors[finalizedColorIndex] & 0x000000FF);
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
	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
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
