package com.revib.revib.state;

import com.revib.revib.R;
import com.revib.revib.session.SessionVariables;

import android.app.Activity;
import android.media.MediaPlayer;

public class BreathingCheckState extends State {
	public BreathingCheckState(Activity activity, State previousState) {
		super(activity, previousState);
	}

	@Override
	public State getNextState(int buttonRes) {
		State nextState	=	this;
		switch(buttonRes){
			case R.id.state_left_btn:
				if(AGE==SessionVariables.ADULT){
					// Keep checking cons. periodically
					nextState	=	new CallState(activity,this);
				}else{
					nextState	=	new ExplainInflationsState(activity,this);
				}
				break;
			case R.id.state_right_btn:
				nextState	=	new LateralRecoveryPositionState(activity,this);
				break;
		}
		return nextState;
	}

	@Override
	public int getInfoResource() {
		return R.string.breathing_check_info;
	}
	
	@Override
	public void startAnimation(){
		//There is no animation, so don't do anything
	}

	@Override
	public int getAudioResource() {
		return R.raw.breathing_check;
	}

	@Override
	public int getImageResource() {
		int resource	=	R.drawable.no_image;
		switch(AGE){
		case SessionVariables.ADULT:
			resource	=	R.drawable.adult_breathing_check;
			break;
		case SessionVariables.CHILD:
			resource	=	R.drawable.child_breathing_check;
			break;
		case SessionVariables.BABY:
			resource	=	R.drawable.baby_breathing_check;
			break;
		}
		return resource;
	}

	@Override
	public int getLeftBtnResource() {
		return R.string.no;
	}

	@Override
	public int getRightBtnResource() {
		return R.string.yes;
	}

	@Override
	public int getQuestionResource() {
		return R.string.breathing_check_question;
	}

	@Override
	public int getTitleResource() {
		return R.string.breathing_check_title;
	}

	@Override
	public void onCompletion(MediaPlayer mp) {
		super.onCompletion(mp);
	}
	
	@Override
	public void beforeGoingForward(){
		super.beforeGoingForward();
		SessionVariables.getInstance().restartInflations();
	}
}
