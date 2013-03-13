package com.revib.revib.states;

import com.revib.revib.R;
import com.revib.revib.session.SessionVariables;

import android.app.Activity;
import android.media.MediaPlayer;

public class ScreamForHelpState extends State {

	public ScreamForHelpState(Activity activity, State previousState) {
		super(activity, previousState);
	}

	@Override
	public State getNextState(int buttonRes) {
		State nextState	=	this;
		switch(buttonRes){
			case R.id.state_left_btn:
				/*SessionVariables sv	= SessionVariables.getInstance();
				sv.callEmergencyNumber(activity);
				break;*/
			case R.id.state_right_btn:
				if(AGE==SessionVariables.BABY){
					//nextState	=	new KeepConsCheckState(activity,this);
				}else{
					nextState	=	new BreathingCheckState(activity,this);
				}
				break;
		}
		return nextState;
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
		return R.string.scream_for_help_right_btn;
	}

	@Override
	public int getQuestionResource() {
		return R.string.scream_for_help_right_btn;
	}

	@Override
	public int getTitleResource() {
		return R.string.scream_for_help_activity;
	}

	@Override
	public void onCompletion(MediaPlayer mp) {
		super.onCompletion(mp);
	}
}
