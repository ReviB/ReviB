package com.revib.revib.states;

import com.revib.revib.R;
import com.revib.revib.session.SessionVariables;

import android.app.Activity;

public class ConsciousState extends State {

	public ConsciousState(Activity activity, State previousState) {
		super(activity, previousState);
	}

	@Override
	public int getInfoResource() {
		int $ret=-1;
		switch(AGE){
		case SessionVariables.ADULT:
			$ret = R.string.conscious_info;
			break;
		case SessionVariables.CHILD:
			$ret = R.string.conscious_info_child;
			break;
		case SessionVariables.BABY:
			$ret = R.string.conscious_info_baby;
			break;
		}
		return $ret;
	}

	@Override
	public int getAudioResource() {
		return -1;
	}

	@Override
	public int getImageResource() {
		return R.drawable.no_image;
	}

	@Override
	public int getLeftBtnResource() {
		return R.string.conscious_left;
	}

	@Override
	public int getRightBtnResource() {
		return R.string.conscious_right;
	}

	@Override
	public int getQuestionResource() {
		return R.string.conscious_question;
	}

	@Override
	public int getTitleResource() {
		return R.string.conscious_title;
	}

	@Override
	public State getNextState(int buttonRes) {
		State nextState	=	this;
		switch(buttonRes){
			case R.id.state_left_btn:
				nextState	=	new ScreamForHelpState(activity,this);
				break;
			case R.id.state_right_btn:
				nextState	=	this;
				break;
		}
		return nextState;
	}

}
