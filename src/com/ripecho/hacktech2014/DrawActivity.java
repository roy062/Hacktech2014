package com.ripecho.hacktech2014;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;

public class DrawActivity extends Activity {
	
	private int curColor = Color.BLACK;
	private boolean colorLock = false;
	private Tool curTool = Tool.PENCIL;
	private Bitmap bitmap;
	private ArrayList<CursorOrigin> cursorOrigins = new ArrayList<CursorOrigin>(10);
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.draw_activity);
		
		ColorPaletteView cpv = (ColorPaletteView)findViewById(R.id.color_palette);
		cpv.setParent(this);
		
		PixelGridView pgv = (PixelGridView)findViewById(R.id.pixel_grid);
		pgv.setParent(this);
		
		ToolBarView tbv = (ToolBarView)findViewById(R.id.tool_bar);
		tbv.setParent(this);
	}
	
	public void setColor(int a, int r, int g, int b) {	
		curColor = Color.argb(a, r, g, b);
	}

	public int getColor() {
		return curColor;
	}
	
	public void setTool(Tool t) {
		curTool = t;
	}
	
	public Tool getTool() {
		return curTool;
	}
	
	public boolean getIsLocked() {
		return colorLock;
	}
	
	public void openFileMenu() {
	}
	
	public Bitmap getBitmap() {
		return null;
	}
	
	public void setBitmap(String pathName) {
		bitmap = BitmapFactory.decodeFile(pathName);
	}
	
	public void drawToBitmap(int x, int y, int argb) {
		bitmap.setPixel(x, y, argb);
	}
	
	public void addCursorOrigin(int id, ViewOrigin origin) {
		for (int i = 0; i < cursorOrigins.size(); i++) {
			if (cursorOrigins.get(i).id == id) {
				cursorOrigins.get(i).origin = origin;
				break;
			}
		}
				
		cursorOrigins.add(new CursorOrigin(id, origin));
	}
	
	public void removeCursorOrigin(int id) {
		for (int i = 0; i < cursorOrigins.size(); i++) {
			if (cursorOrigins.get(i).id == id) {
				cursorOrigins.remove(i);
				break;
			}
		}
	}
	
	public ViewOrigin getCursorOrigin(int id) {
		for (int i = 0; i < cursorOrigins.size(); i++)
			if (cursorOrigins.get(i).id == id)
				return cursorOrigins.get(i).origin;
		return ViewOrigin.NULL;
	}
	
	private class CursorOrigin {
		public int id;
		public ViewOrigin origin;
		
		public CursorOrigin(int id, ViewOrigin origin) {
			this.id = id;
			this.origin = origin;
		}
	}
}
