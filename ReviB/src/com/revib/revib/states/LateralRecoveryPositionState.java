package com.revib.revib.states;

import com.revib.revib.R;

import android.app.Activity;
import android.media.MediaPlayer;

public class LateralRecoveryPositionState extends State {

	public LateralRecoveryPositionState(Activity activity, State previousState) {
		super(activity, previousState);
	}

	@Override
	public State getNextState(int buttonRes) {
		return	new CallState(activity,this);
	}
	
	@Override
	public int getTitleResource(){
		return R.string.lateral_recovery_position_title;
	}

	@Override
	public int getInfoResource() {
		return R.string.lateral_recovery_position_info;
	}

	@Override
	public int getAudioResource() {
		return R.raw.bip;
	}

	@Override
	public int getImageResource() {
		return R.drawable.animation_lateral_recovery_position;
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
	public void onCompletion(MediaPlayer mp) {
		super.onCompletion(mp);
	}

}
