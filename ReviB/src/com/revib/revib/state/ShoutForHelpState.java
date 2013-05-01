package com.revib.revib.state;

import com.revib.revib.R;

import android.app.Activity;

import com.revib.revib.session.SessionVariables;

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
		return R.raw.shout_for_help;
	}

	@Override
	public int getImageResource() {
		int	res;
		if(AGE==SessionVariables.ADULT){
			res	=	R.drawable.adult_shout_for_help;
		}else{
			res	=	R.drawable.shout_for_help;
		}
		return res;
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
		return new BreathingManeuverState(activity,this);
	}
	
	/*
	@Override
	public void onCompletion(MediaPlayer mp) {
		super.onCompletion(mp);
		/*SleepThread.getInstance().start(
				((StateActivity) activity),
				getNextState(-1),
				2000
		);
	}*/
}
