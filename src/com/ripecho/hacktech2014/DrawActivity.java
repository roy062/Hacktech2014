package com.ripecho.hacktech2014;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

public class DrawActivity extends Activity implements PopupMenu.OnMenuItemClickListener {
	
	private final static int DEFAULT_DIMENSION = 32;
	
	private int curColor = Color.BLACK;
	private boolean colorLock = false;
	private Tool curTool = Tool.PENCIL;
	private Bitmap bitmap;
	private ArrayList<CursorOrigin> cursorOrigins = new ArrayList<CursorOrigin>(10);
	private PopupMenu fileMenu;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.draw_activity);
		
		bitmap = Bitmap.createBitmap(DEFAULT_DIMENSION, DEFAULT_DIMENSION, Bitmap.Config.ARGB_8888);
		
		ColorPaletteView cpv = (ColorPaletteView)findViewById(R.id.color_palette);
		cpv.setParent(this);
		
		PixelGridView pgv = (PixelGridView)findViewById(R.id.pixel_grid);
		pgv.setParent(this);
		
		ToolBarView tbv = (ToolBarView)findViewById(R.id.tool_bar);
		//tbv.setParent(this);
		fileMenu = new PopupMenu(this, tbv);
		fileMenu.inflate(R.menu.draw_activity_menu);
		fileMenu.setOnMenuItemClickListener(this);
	}
	
	public void testFunc(View v){
		fileMenu.show();
	}
	
	protected void onPause() {
		super.onPause();
		getPreferences(MODE_PRIVATE).edit()
			.clear()
			.putInt("curColor",  curColor)
			.putBoolean("colorLock", colorLock)
			.putInt("curTool", curTool.getValue())
			.apply();
		try
		{
			FileOutputStream temp = new FileOutputStream(new File(getFilesDir(), "temp.png"));
			bitmap.compress(Bitmap.CompressFormat.PNG, 100, temp);
		}
		catch (FileNotFoundException e)
		{
			
		}
	}
	
	public void setColor(int a, int r, int g, int b) {	
		curColor = Color.argb(a, r, g, b);
	}
	
	public void setColor(int argb) {
		curColor = argb;
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
		fileMenu.show();
	}
	
	public Bitmap getBitmap() {
		return bitmap;
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

	@Override
	public boolean onMenuItemClick(MenuItem item) {
		switch (item.getItemId())
		{
		case R.id.new_menu:
			return true;
		case R.id.open_menu:
			return true;
		case R.id.save_menu:
			return true;
		case R.id.options_menu:
			Intent intent = new Intent(this, OptionsActivity.class);
			startActivity(intent);
			return true;
		}
		return false;
	}
}
