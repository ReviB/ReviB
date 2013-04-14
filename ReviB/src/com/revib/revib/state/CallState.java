package com.revib.revib.state;

import com.revib.revib.R;
import com.revib.revib.dialog.NoTelephonyDialog;
import com.revib.revib.session.SessionVariables;

import android.app.Activity;
import android.content.pm.PackageManager;

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
		return R.raw.call;
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
		if(activity.getPackageManager().hasSystemFeature(PackageManager.FEATURE_TELEPHONY)){
			return R.string.call_right;
		}else{
			return -1;
		}
		
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
		State nextState	=	null;
		if(!activity.getPackageManager().hasSystemFeature(PackageManager.FEATURE_TELEPHONY)&&(buttonRes==R.id.state_right_btn)){
			NoTelephonyDialog ntd	=	new NoTelephonyDialog(activity);
			ntd.startDialog();
		}else{
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
		}
		return nextState;
	}

	public void beforeGoingBack()		{
		super.beforeGoingBack();
		SessionVariables.getInstance().setAlreadyCalled(false);}
	public void beforeGoingForward()	{
		super.beforeGoingForward();
		SessionVariables.getInstance().setAlreadyCalled(true);
	}
}
