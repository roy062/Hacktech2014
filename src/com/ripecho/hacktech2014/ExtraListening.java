package com.ripecho.hacktech2014;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.*;

public class ExtraListening extends View implements View.OnClickListener{

	Bitmap gridMap;
	
	public ExtraListening(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.setOnClickListener(this);
		gridMap = parent.getBitmap();
	}
	
	private void updateBitmap(float x, float y, Bitmap bmp, int col){
		bmp.setPixel(convertX(x), convertY(y), col);
	}
	
	public void onClick(View v){
		updateBitmap(v.getX(),v.getY(),gridMap,parent.getColor());
	}

}
