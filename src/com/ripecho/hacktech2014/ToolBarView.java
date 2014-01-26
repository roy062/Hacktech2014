package com.ripecho.hacktech2014;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.*;
import android.os.Bundle;

public class ToolBarView extends View {

	private DrawActivity parent;
	public Tool toolSelect;
	
	public void setParent(DrawActivity parent)
	{
		this.parent = parent;
	}
	
	public ToolBarView(Context context, AttributeSet attrs) {
		super(context, attrs);
		parent = (DrawActivity)context;
		
		LinearLayout ll = new LinearLayout(parent);
		Button pencilbtn = new Button(parent);
		pencilbtn.setText("Pencil");
		pencilbtn.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
		pencilbtn.setOnClickListener(new View.OnClickListener() 
		{
			public void onClick(View v)
			{
				toolSelect = setTool(Tool.PENCIL);
			}
		});
	
		
		Button dropperbtn = new Button(parent);
		dropperbtn.setText("Dropper");
		dropperbtn.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
		dropperbtn.setOnClickListener(new View.OnClickListener() 
		{
			public void onClick(View v)
			{
				toolSelect = setTool(Tool.EYE_DROPPER);
			}
		});
		
		Button eraserbtn = new Button(parent);
		eraserbtn.setText("Eraser");
		eraserbtn.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
		eraserbtn.setOnClickListener(new View.OnClickListener() 
		{
			public void onClick(View v)
			{
				toolSelect = setTool(Tool.ERASER);
			}
		});
		
		Button bucketbtn = new Button(parent);
		bucketbtn.setText("Bucket");
		bucketbtn.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
		bucketbtn.setOnClickListener(new View.OnClickListener() 
		{
			public void onClick(View v)
			{
				toolSelect = setTool(Tool.BUCKET);
			}
		});
		
		Button filebtn = new Button(parent);
		filebtn.setText("Files");
		filebtn.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
		filebtn.setOnClickListener(new View.OnClickListener() 
		{
			public void onClick(View v)
			{
				toolSelect = setTool(Tool.NULL);
			}
		});
		
		ll.addView(pencilbtn);
		ll.addView(dropperbtn);
		ll.addView(eraserbtn);
		ll.addView(bucketbtn);
		ll.addView(filebtn);
		
		parent.setContentView(ll);
		
	}
	
	public Tool setTool(Tool c)
	{
		return toolSelect;
	}

}
