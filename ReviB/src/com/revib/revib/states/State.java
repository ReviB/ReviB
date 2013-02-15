package com.revib.revib.states;

import com.revib.revib.R;
import com.revib.revib.session.SessionVariables;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.AnimationDrawable;
import android.util.Log;
import android.widget.ImageView;

public abstract class State {
	public		int			AGE;
	public		Boolean		reality;
	public		String		TAG;
	public		Activity	activity		=	null;
	private 	State		previousState	=	null;
	
	public State(Activity activity,State previousState){
		SessionVariables sv	=	SessionVariables.getInstance();
		
		this.AGE			=	sv.getAge();
		this.reality		=	sv.isReal();
		this.TAG			=	this.getClass().getName();
		this.activity		=	activity;
		this.previousState	=	previousState;
	}
	
	public abstract void setStateView();
	
	public void setInfoDialog(){
		try{
			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
					activity);
	 
			// set title
			alertDialogBuilder.setTitle(R.string.dialog_info);
 
			// set dialog message
			alertDialogBuilder.setMessage(getInfo());
			alertDialogBuilder.setCancelable(false);
			alertDialogBuilder.setNeutralButton(R.string.dialog_ok, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog,int id) {
					dialog.cancel();
				}
			  });
 
				// create alert dialog
				AlertDialog alertDialog = alertDialogBuilder.create();
 
				// show it
				alertDialog.show();
		}catch(Exception e){
			Log.w(TAG, "Info dialog couldn't be set: "+e.getMessage());
		}
	}
	
	public String getInfo(){
		return	activity.getResources().getString(
				getInfoResource()
		);
	}
	
	abstract public int getInfoResource();
	
	public void startAnimation(){
		try{
			ImageView iv = (ImageView) activity.findViewById(R.id.state_iv);
			AnimationDrawable animation = (AnimationDrawable) iv.getDrawable();
			animation.start();
		}catch(Exception e){
			Log.w(TAG, "Drawable animation can not be started: "+e.getMessage());
		}
	}

	public State getPreviousState() {
		return previousState;
	}
	
	public abstract State getNextState(int buttonRes);
	
	public void reloadState(){
		setStateView();
		startAnimation();
	}
}
