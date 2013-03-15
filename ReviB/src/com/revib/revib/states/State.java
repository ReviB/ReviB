package com.revib.revib.states;

import com.revib.revib.R;
import com.revib.revib.audio.AudioFunctions;
import com.revib.revib.session.SessionVariables;
import com.revib.revib.session.SleepThread;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public abstract class State implements OnCompletionListener {
	private 	State		previousState	=	null;
	public		int			AGE;
	public		Boolean		reality;
	public		String		TAG;
	public		Activity	activity		=	null;
	public		MediaPlayer mediaPlayer 	= 	null;
	
	public		Resources	res;
	
	public State(Activity activity,State previousState){
		SessionVariables sv	=	SessionVariables.getInstance();
		
		this.AGE			=	sv.getAge();
		this.reality		=	sv.isReal();
		this.TAG			=	this.getClass().getName();
		this.activity		=	activity;
		this.previousState	=	previousState;

		res		=	activity.getResources();
	}
	
	public void setStateView(){
		try{

			ImageButton	ib	=	(ImageButton)	activity.findViewById(R.id.state_audio_btn);
			if(AudioFunctions.isAudioOn(activity)){
				ib.setVisibility(ImageButton.INVISIBLE);
			}else{
				ib.setVisibility(ImageButton.VISIBLE);
			}
			
			Resources	res		=	activity.getResources();
			
			// Set image
			ImageView 	image 	=	(ImageView) activity.findViewById(R.id.state_iv);
			image.setImageDrawable(res.getDrawable(getImageResource()));

			
			// Set Question
			setTextView(R.id.state_question_tv,getQuestionResource());
			
			// Set buttons text
			setBtn(R.id.state_left_btn,getLeftBtnResource());
			setBtn(R.id.state_right_btn,getRightBtnResource());
			
			// Set title (for multi-lingual issues)
			activity.setTitle(res.getString(getTitleResource()));
		}catch(Exception e){
			Log.w(TAG, "State view could not be set: "+e.getMessage());
		}
	}
	
	private void setBtn(int viewResource,int stringResource){
		Button		btn		=	(Button) activity.findViewById(viewResource);
		if(stringResource!=-1){
			btn.setVisibility(View.VISIBLE);
			btn.setText(stringResource);
		}else{
			btn.setVisibility(View.GONE);
		}
	}
	
	private void setTextView(int viewResource,int stringResource){
		TextView		view	=	(TextView) activity.findViewById(viewResource);
		if(stringResource!=-1){
			view.setVisibility(View.VISIBLE);
			view.setText(stringResource);
		}else{
			view.setVisibility(View.GONE);
		}
	}
	
	public void setInfoDialog(){
		try{
			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
					activity);
	 
			// set title
			alertDialogBuilder.setTitle(R.string.dialog_info);
 
			// set dialog message
			String message	=	activity.getResources().getString(getInfoResource());
			alertDialogBuilder.setMessage(message);
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
	
	// Resources
	abstract public int getInfoResource();
	abstract public int getAudioResource();
	abstract public int getImageResource();
	abstract public int getLeftBtnResource();
	abstract public int getRightBtnResource();
	abstract public int getQuestionResource();
	abstract public int getTitleResource();
	
	// Multimedia
	public void startAnimation(){
		try{
			ImageView iv = (ImageView) activity.findViewById(R.id.state_iv);
			AnimationDrawable animation = (AnimationDrawable) iv.getDrawable();
			animation.start();
		}catch(Exception e){
			Log.w(TAG, "Drawable animation can not be started: "+e.getMessage());
		}
	}
	
	public void startAudio(){
		int audioResource	=	getAudioResource();
		if(audioResource!=-1){
			try{
				AudioFunctions.checkAudio(activity);
				if(mediaPlayer!=null){
					mediaPlayer.release();
				}
				mediaPlayer	=	MediaPlayer.create(activity,getAudioResource());
				mediaPlayer.setOnCompletionListener(this);
				mediaPlayer.start();
			}catch(Exception e){
				Log.w(TAG, "Audio can not be started: "+e.getMessage());
			}
		}
	}

	// States
	public abstract State getNextState(int buttonRes);
	
	public State getPreviousState() {
		return previousState;
	}
	
	public void reloadState(){
		if(mediaPlayer!=null)
			mediaPlayer.release();
		SleepThread.getInstance().interrupt();
		setStateView();
		startAnimation();
		startAudio();
	}

	@Override
	public void onCompletion(MediaPlayer mp) {
		mp.release();
	}
}
