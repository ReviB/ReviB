package com.revib.revib.states;

import com.revib.revib.R;

import android.app.Activity;

public class ConsciousState extends State {

	public ConsciousState(Activity activity, State previousState) {
		super(activity, previousState);
	}

	@Override
	public int getInfoResource() {
		return R.string.conscious_info;
	}

	@Override
	public int getAudioResource() {
		return R.raw.bip;
	}

	@Override
	public int getImageResource() {
		return R.drawable.no_image;
	}

	@Override
	public int getLeftBtnResource() {
		return R.string.conscious_left_btn;
	}

	@Override
	public int getRightBtnResource() {
		return R.string.conscious_right_btn;
	}

	@Override
	public int getQuestionResource() {
		return -1;
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
