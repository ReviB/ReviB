package com.revib.revib.audio;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
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
		AudioManager	mAudioManager	=	(AudioManager)activity.getSystemService(Context.AUDIO_SERVICE);
		
	    int vol	=	mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
	    
	    if(vol==0)	audioOn	=false;
	    return audioOn;
	}
	
	public static void setAudioDialog(Activity activity){
		try{
			AudioListener listener	=	new AudioListener(activity);
			SessionVariables	sv	=	SessionVariables.getInstance();
			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
					activity);
			
			alertDialogBuilder.setMessage(R.string.audio_dialog_message);
			
			// set title
			alertDialogBuilder.setTitle(R.string.audio_dialog_title);
 			
			// set dialog message
			alertDialogBuilder.setNeutralButton(R.string.dialog_ok,listener);
 
			// create alert dialog
			AlertDialog alertDialog = alertDialogBuilder.create();
 
			// show it
			alertDialog.show();
			sv.setAudioDialogShown(true);
		}catch(Exception e){
			Log.w("SoundConfig", "Info dialog couldn't be set: "+e.getMessage());
		}
	}
	
	public static int getVolume(Activity activity){
		AudioManager	audioManager		= 	(AudioManager)activity.getSystemService(Context.AUDIO_SERVICE);
		return audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
	}
	
	/*public static int getMaxVolume(Activity activity){
		int maxVolume	=	0;
		try{
			AudioManager am	=	(AudioManager)activity.getSystemService(Context.AUDIO_SERVICE);
			maxVolume		=	am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		}catch(Exception e){
			e.printStackTrace();
		}
		return maxVolume;
	}
	
	public static void setVolume(Activity activity,int vol){
		AudioManager am	=	(AudioManager)activity.getSystemService(Context.AUDIO_SERVICE);
	    am.setStreamVolume(AudioManager.STREAM_MUSIC, vol, 0);
	}*/
}
