package com.revib.revib.states;

import com.revib.revib.R;
import com.revib.revib.session.SessionVariables;

import android.app.Activity;
import android.media.MediaPlayer;

public class BreathingManeuverState extends State {

	public BreathingManeuverState(Activity activity, State previousState) {
		super(activity, previousState);
	}

	@Override
	public State getNextState(int buttonRes) {
		State nextState	=	this;
		switch(buttonRes){
			case R.id.state_left_btn:
			case R.id.state_right_btn:
			case -1:
				nextState	=	new BreathingCheckState(activity,this);
				break;
		}
		return nextState;
	}

	@Override
	public int getInfoResource() {
		int $ret=-1;
		switch(AGE){
		case SessionVariables.ADULT:
		case SessionVariables.CHILD:
			$ret = R.string.breathing_maneuver_info;
			break;
		case SessionVariables.BABY:
			$ret = R.string.breathing_maneuver_info_baby;
			break;
		}
		return $ret;
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
			resource	=	R.drawable.animation_adult_breathing_maneuver;
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
		//return R.string.breathing_maneuver_left;
		return -1;
	}

	@Override
	public int getRightBtnResource() {
		//return R.string.breathing_maneuver_right;
		return R.string.next;
	}

	@Override
	public int getQuestionResource() {
		//return R.string.breathing_maneuver_question;
		return -1;
	}

	@Override
	public int getTitleResource() {
		return R.string.breathing_maneuver_title;
	}

	@Override
	public void onCompletion(MediaPlayer mp) {
		super.onCompletion(mp);
	}

}
