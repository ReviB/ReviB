package com.revib.revib.states;

import com.revib.revib.R;

import android.app.Activity;
import android.media.MediaPlayer;
import com.revib.revib.session.SleepThread;
import com.revib.revib.StateActivity;

public class ScreamForHelpState extends State {

	public ScreamForHelpState(Activity activity, State previousState) {
		super(activity, previousState);
	}

	@Override
	public int getInfoResource() {
		return R.string.scream_for_help_info;
	}

	@Override
	public int getAudioResource() {
		return R.raw.bip;
	}

	@Override
	public int getImageResource() {
		int resource	=	R.drawable.no_image;
		resource		=	R.drawable.animation_scream_for_help;
		return resource;
	}

	@Override
	public int getLeftBtnResource() {
		return -1;
		//return R.string.scream_for_help_left_btn;
	}

	@Override
	public int getRightBtnResource() {
		return R.string.next;
	}

	@Override
	public int getQuestionResource() {
		return -1;
		//return R.string.scream_for_help_question;
	}

	@Override
	public int getTitleResource() {
		return R.string.scream_for_help_activity;
	}

	@Override
	public State getNextState(int buttonRes) {
		State nextState	=	this;
		switch(buttonRes){
			case -1:
			case R.id.state_right_btn:
				nextState	=	new BreathingManeuverState(activity,this);
				break;
		}
		return nextState;
	}
	
	@Override
	public void onCompletion(MediaPlayer mp) {
		super.onCompletion(mp);
		//thread	=	new SleepThread("ScreamForHelp",((StateActivity) activity),2000,this.getNextState(-1));
		//thread.start();
	}
}
