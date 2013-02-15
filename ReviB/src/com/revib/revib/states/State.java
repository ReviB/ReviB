package com.revib.revib.states;

import com.revib.revib.R;
import com.revib.revib.session.SessionVariables;

import android.app.Activity;
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
