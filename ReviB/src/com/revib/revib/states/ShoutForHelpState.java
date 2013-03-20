package com.revib.revib.states;

import com.revib.revib.R;

import android.app.Activity;
import android.media.MediaPlayer;
import com.revib.revib.session.SleepThread;
import com.revib.revib.StateActivity;

public class ShoutForHelpState extends State {

	public ShoutForHelpState(Activity activity, State previousState) {
		super(activity, previousState);
	}

	@Override
	public int getInfoResource() {
		return R.string.shout_for_help_info;
	}

	@Override
	public int getAudioResource() {
		return R.raw.bip;
	}

	@Override
	public int getImageResource() {
		return R.drawable.shout;
	}

	@Override
	public int getLeftBtnResource() {
		return -1;
	}

	@Override
	public int getRightBtnResource() {
		return R.string.next;
	}

	@Override
	public int getQuestionResource() {
		return -1;
	}

	@Override
	public int getTitleResource() {
		return R.string.shout_for_help_title;
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
		State	nextState	=	getNextState(-1);
		SleepThread.getInstance().start(
				((StateActivity) activity),
				nextState,
				2000
		);
	}
}
