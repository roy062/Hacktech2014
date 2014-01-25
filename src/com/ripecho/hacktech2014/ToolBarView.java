package com.ripecho.hacktech2014;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.*;

public class ToolBarView extends View {

	private DrawActivity parent;
	public Tool toolSelect;
	
	public ToolBarView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		Button pencilbtn = new Button(parent);
		pencilbtn.setText("Pencil");
		pencilbtn.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
		pencilbtn.setOnClickListener(new View.OnClickListener() 
		{
			public void onClick(View v)
			{
				toolSelect = setTool(PENCIL);
			}
		});
	
		
		Button dropperbtn = new Button(parent);
		dropperbtn.setText("Dropper");
		dropperbtn.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
		dropperbtn.setOnClickListener(new View.OnClickListener() 
		{
			public void onClick(View v)
			{
				toolSelect = setTool(EYE_DROPPER);
			}
		});
		
		Button eraserbtn = new Button(parent);
		eraserbtn.setText("Eraser");
		eraserbtn.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
		eraserbtn.setOnClickListener(new View.OnClickListener() 
		{
			public void onClick(View v)
			{
				toolSelect = setTool(ERASER);
			}
		});
		
		Button bucketbtn = new Button(parent);
		bucketbtn.setText("Bucket");
		bucketbtn.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
		bucketbtn.setOnClickListener(new View.OnClickListener() 
		{
			public void onClick(View v)
			{
				toolSelect = setTool(BUCKET);
			}
		});
		
		Button filebtn = new Button(parent);
		filebtn.setText("Files");
		filebtn.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
		filebtn.setOnClickListener(new View.OnClickListener() 
		{
			public void onClick(View v)
			{
				toolSelect = setTool(NULL);
			}
		});
		
	}
	
	public Tool setTool(Tool c)
	{
		return toolSelect;
	}

	public void setParent(DrawActivity parent)
	{
		this.parent = parent;
	}
}
