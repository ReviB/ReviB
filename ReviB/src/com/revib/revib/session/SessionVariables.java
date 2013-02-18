package com.revib.revib.session;

import com.revib.revib.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.net.Uri;
import android.util.Log;

public class SessionVariables {
	public static final int BABY	=	0;
	public static final int CHILD	=	1;
	public static final int ADULT	=	2;
	
	private static SessionVariables instance = null;
	private Boolean		reality		=	false;
	private int		age			=	ADULT;
	
	private Boolean		audioDialogShown	=	false;
	
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
		Uri callUri = Uri.parse("tel://679064051");
		Intent callIntent = new Intent(Intent.ACTION_CALL,callUri);
		callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_NO_USER_ACTION);
		activity.startActivity(callIntent);
	}

	public Boolean isAudioDialogShown() {
		return audioDialogShown;
	}

	public void setAudioDialogShown(Boolean audioDialogShown) {
		this.audioDialogShown = audioDialogShown;
	}
}
