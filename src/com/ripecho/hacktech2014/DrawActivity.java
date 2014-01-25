package com.ripecho.hacktech2014;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;

public class DrawActivity extends Activity {
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.draw_activity);
	}
	
	void setColor(int a, int r, int g, int b) {	
	}

	int getColor() {
		return 0;
	}
	
	void setTool(Tool t) {
	}
	
	Tool getTool() {
		return Tool.PENCIL;
	}
	
	boolean getIsLocked() {
		return true;
	}
	
	void openFileMenu() {
	}
	
	Bitmap getBitmap() {
		return null;
	}
	
	void setBitmap(String pathName) {
	}
	
	void drawToBitmap(int x, int y, int argb) {
	}
	
	void addCursorOrigin(int id, ViewOrigin origin) {
	}
	
	void removeCursorOrigin(int id) {
	}
	
	ViewOrigin getCursorOrigin(int id) {
		return ViewOrigin.COLOR_PALETTE;
	}
}
