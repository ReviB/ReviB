package com.revib.revib.audio;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.AudioManager;
import android.util.Log;

import com.revib.revib.R;
import com.revib.revib.session.SessionVariables;

public class AudioFunctions {
	public static void checkAudio(Activity activity){
		SessionVariables	sv	=	SessionVariables.getInstance();
		
	    if(!isAudioOn(activity)&&!sv.isAudioDialogShown()){
	    	setAudioDialog(activity);
	    }
	}
	
	public static Boolean isAudioOn(Activity activity){
		Boolean audioOn	=	true;
		Context context	=	activity.getApplicationContext();
		AudioManager	mAudioManager	=	(AudioManager)activity.getSystemService(context.AUDIO_SERVICE);
		
	    int vol	=	mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
	    
	    if(vol==0)	audioOn	=false;
	    return audioOn;
	}
	
	public static void setAudioDialog(Activity activity){
		try{
			SessionVariables	sv	=	SessionVariables.getInstance();
			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
					activity);
	 
			// set title
			alertDialogBuilder.setTitle(R.string.audio_dialog_title);
 
			// set dialog message
			alertDialogBuilder.setMessage(R.string.audio_dialog_message);
			alertDialogBuilder.setNeutralButton(R.string.dialog_ok, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog,int id) {
					dialog.cancel();
				}
			  });
 
			// create alert dialog
			AlertDialog alertDialog = alertDialogBuilder.create();
 
			// show it
			alertDialog.show();
			sv.setAudioDialogShown(true);
		}catch(Exception e){
			Log.w("SoundConfig", "Info dialog couldn't be set: "+e.getMessage());
		}
	}
}
