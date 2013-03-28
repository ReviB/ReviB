package com.revib.revib.state;

import com.revib.revib.R;
import com.revib.revib.dialog.ExitDialog;
import com.revib.revib.session.SessionVariables;

import android.app.Activity;

public class StayWithVictimState extends State {

	public StayWithVictimState(Activity activity, State previousState) {
		super(activity, previousState);
	}

	@Override
	public int getInfoResource() {
		return R.string.stay_with_victim_info;
		/*switch(AGE){
			case SessionVariables.ADULT:
				return R.string.stay_with_victim_info;
			case SessionVariables.CHILD:
				return R.string.stay_with_victim_info_child;
			case SessionVariables.BABY:
				return R.string.stay_with_victim_info_baby;
		}
		return -1;*/
	}

	@Override
	public int getAudioResource() {
		return R.raw.stay_until_ambulance;
	}

	@Override
	public int getImageResource() {
		int	res	=	R.drawable.no_image;
		switch(AGE){
			case SessionVariables.ADULT:
				return R.drawable.lateral_recovery_position_4;
			case SessionVariables.CHILD:
				return R.drawable.child_stay;
			case SessionVariables.BABY:		
				return R.drawable.baby_stay;	
		}
		return res;
	}

	@Override
	public int getLeftBtnResource() {
		return R.string.stay_with_victim_left;
	}

	@Override
	public int getRightBtnResource() {
		return R.string.stay_with_victim_right;
	}

	@Override
	public int getQuestionResource() {
		return -1;
	}

	@Override
	public int getTitleResource() {
		return R.string.stay_with_victim_title;
	}

	@Override
	public State getNextState(int buttonRes) {
		State nextState	=	this;
		if(buttonRes==R.id.state_left_btn){
			if(AGE==SessionVariables.ADULT){
				nextState	=	new ExplainCompressionsState(activity,this);
			}else{
				nextState	=	new ExplainInflationsState(activity,this);
			}
		}else if(buttonRes==R.id.state_right_btn){
			nextState	=	null;
			ExitDialog ed	=	new ExitDialog(activity);
			ed.startDialog();
		}
		return nextState;
	}

}
