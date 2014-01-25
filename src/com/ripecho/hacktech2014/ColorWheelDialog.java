package com.ripecho.hacktech2014;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;


public class ColorWheelDialog extends DialogFragment {

	DialogInterface.OnClickListener listener;
	public ColorWheelDialog(){
		super();
	}
	/*
	public ColorWheelDialog(DialogInterface.OnClickListener l){
		super();
		listener = l;
	}*/
	
	public void setListener(DialogInterface.OnClickListener l){
		listener = l;
	}
	public Dialog onCreateDialog(Bundle savedInstance){
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		LayoutInflater inflate = getActivity().getLayoutInflater();
		builder.setView(inflate.inflate(R.layout.color_wheel, null)).setPositiveButton(R.string.ok,listener)
		.setNegativeButton(R.string.cancel,listener);
		
		return builder.create();
	}
	
	
}
