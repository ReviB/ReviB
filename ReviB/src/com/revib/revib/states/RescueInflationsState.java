package com.revib.revib.states;

import android.app.Activity;

public class RescueInflationsState extends State {

	public RescueInflationsState(Activity activity, State previousState) {
		super(activity, previousState);
	}

	@Override
	public int getInfoResource() {
		return 0;
	}

	@Override
	public int getAudioResource() {
		return 0;
	}

	@Override
	public int getImageResource() {
		return 0;
	}

	@Override
	public int getLeftBtnResource() {
		return 0;
	}

	@Override
	public int getRightBtnResource() {
		return 0;
	}

	@Override
	public int getQuestionResource() {
		return 0;
	}

	@Override
	public int getTitleResource() {
		return 0;
	}

	@Override
	public State getNextState(int buttonRes) {
		return null;
	}

}
