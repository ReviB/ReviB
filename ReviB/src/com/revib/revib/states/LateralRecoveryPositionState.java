package com.revib.revib.states;

import com.revib.revib.R;
import com.revib.revib.session.SessionVariables;

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

		if(AGE==SessionVariables.ADULT){
			return R.string.lateral_recovery_position_title_adult;
		}else{
			return R.string.lateral_recovery_position_title;
		}
	}

	@Override
	public int getInfoResource() {
		switch(AGE){
			case SessionVariables.ADULT:
				return R.string.lateral_recovery_position_info;
			case SessionVariables.CHILD:
				return R.string.lateral_recovery_position_info_child;
			case SessionVariables.BABY:		
				return R.string.lateral_recovery_position_info_baby;
		}
		return -1;
	}

	@Override
	public int getAudioResource() {
		switch(AGE){
		case SessionVariables.ADULT:
			return R.raw.lateral_recovery_position_0;
		case SessionVariables.CHILD:
			return R.raw.lateral_recovery_position_0;
		case SessionVariables.BABY:		
			return R.raw.lateral_recovery_position_0;
	}
	return -1;
	}

	@Override
	public int getImageResource() {
		int	res	=	R.drawable.no_image;
		switch(AGE){
			case SessionVariables.ADULT:
				return R.drawable.lateral_recovery_position_animation;
			case SessionVariables.CHILD:
				return R.drawable.child_stay;
			case SessionVariables.BABY:		
				return R.drawable.baby_stay;	
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
	public void onCompletion(MediaPlayer mp) {
		super.onCompletion(mp);
	}

}
