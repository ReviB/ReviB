package com.revib.revib.states;

import com.revib.revib.R;
import com.revib.revib.session.SessionVariables;

import android.app.Activity;

public class CallState extends State {

	public CallState(Activity activity, State previousState) {
		super(activity, previousState);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getInfoResource() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getAudioResource() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getImageResource() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getLeftBtnResource() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getRightBtnResource() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getQuestionResource() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTitleResource() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public State getNextState(int buttonRes) {
		State nextState	=	this;
		switch(buttonRes){
			case R.id.state_left_btn:
				SessionVariables sv	= SessionVariables.getInstance();
				sv.callEmergencyNumber(activity);
				break;
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

}
