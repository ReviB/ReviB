package com.revib.revib.session;

import com.revib.revib.R;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

public class SessionVariables {
	private static		SessionVariables	instance			=	null;
	
	public static final int BABY	=	0;
	public static final int CHILD	=	1;
	public static final int ADULT	=	2;
	
	private 	Boolean	reality				=	false;
	private 	Boolean	audioDialogShown	=	false;
	private 	Boolean	alreadyCalled		=	false;
	private		Boolean	exit				=	false;
	private		Boolean	paused				=	false;

	private 	int		age					=	ADULT;
	private 	int		inflations			=	0;
	
	protected SessionVariables() {}
	
	public static SessionVariables getInstance() {
		if(instance == null)
			instance = new SessionVariables();
		return instance;
   }

	public Boolean isReal() {
		return reality;
	}

	public void setReality(Boolean reality) {
		this.reality = reality;
	}

	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public void callEmergencyNumber(Activity activity){
		if(isReal()){
			try{
				Uri callUri = Uri.parse("tel://112");
				Intent callIntent = new Intent(Intent.ACTION_CALL,callUri);
				callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_NO_USER_ACTION);
				activity.startActivity(callIntent);
			}catch(Exception e){
				
			}
		}else{
			Toast.makeText(activity, R.string.false_phone_call, Toast.LENGTH_LONG).show();
		}
	}

	public Boolean isAudioDialogShown() {
		return audioDialogShown;
	}

	public void setAudioDialogShown(Boolean audioDialogShown) {
		this.audioDialogShown = audioDialogShown;
	}

	public int getInflations() {
		return inflations;
	}

	public void sumInflations() {
		this.inflations++;
	}

	public void restInflations() {
		this.inflations--;
	}

	public void restartInflations() {
		this.inflations=0;
	}

	public Boolean hasAlreadyCalled() {
		return alreadyCalled;
	}

	public void setAlreadyCalled(Boolean alreadyCalled) {
		this.alreadyCalled = alreadyCalled;
	}

	public Boolean getExit() {
		return exit;
	}

	public void setExit(Boolean exit) {
		this.exit = exit;
	}

	public Boolean isPaused() {
		return paused;
	}

	public void pause(){
		this.paused = true;
	}
	public void resume() {
		this.paused = false;
	}
}
