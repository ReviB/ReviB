package com.revib.revib.states;

import com.revib.revib.R;
import com.revib.revib.session.SessionVariables;

import android.app.Activity;

public class CallState extends State {

	public CallState(Activity activity, State previousState) {
		super(activity, previousState);
	}

	@Override
	public int getInfoResource() {
		return R.string.call_info;
	}

	@Override
	public int getAudioResource() {
		return R.raw.bip;
	}

	@Override
	public int getImageResource() {
		if(AGE==SessionVariables.ADULT){
			return R.drawable.adult_call;
		}else{
			return R.drawable.call;
		}
	}

	@Override
	public int getLeftBtnResource() {
		return R.string.call_left;
	}

	@Override
	public int getRightBtnResource() {
		return R.string.call_right;
	}

	@Override
	public int getQuestionResource() {
		return -1;
	}

	@Override
	public int getTitleResource() {
		return R.string.call_title;
	}

	@Override
	public State getNextState(int buttonRes) {
		State nextState	=	this;
		if(buttonRes==R.id.state_right_btn){
			SessionVariables.getInstance().callEmergencyNumber(activity);
		}
		
		if(super.getPreviousStateClass()==LateralRecoveryPositionState.class)
			nextState	=	new	StayWithVictimState(activity,this);
		else if(AGE==SessionVariables.ADULT){
			nextState	=	new ExplainCompressionsState(activity,this);
		}else{
			nextState	=	new CompressionsState(activity,this);
		}
		return nextState;
	}

	public void beforeGoingBack()		{SessionVariables.getInstance().setAlreadyCalled(false);}
	public void beforeGoingForward()	{SessionVariables.getInstance().setAlreadyCalled(false);}
}
