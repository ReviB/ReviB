package com.revib.revib.states;

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
					
					nextState	=	new RescueInflationsState(activity,this);
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
		/*int $ret=-1;
		switch(AGE){
		case SessionVariables.ADULT:
			$ret = R.string.breathing_check_info;
			break;
		case SessionVariables.CHILD:
			$ret = R.string.breathing_check_info_child;
			break;
		case SessionVariables.BABY:
			$ret = R.string.breathing_check_info_baby;
			break;
		}*/
		return R.string.breathing_check_info;
	}
	
	@Override
	public void startAnimation(){
		//There is no animation, so don't do anything
	}

	@Override
	public int getAudioResource() {
		return R.raw.bip;
	}

	@Override
	public int getImageResource() {
		int resource	=	R.drawable.no_image;
		switch(AGE){
		case SessionVariables.ADULT:
			resource	=	R.drawable.animation_adult_breathing;
			break;
		case SessionVariables.CHILD:
			break;
		case SessionVariables.BABY:
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
}
