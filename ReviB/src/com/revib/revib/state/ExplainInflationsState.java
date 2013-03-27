package com.revib.revib.state;

import com.revib.revib.R;
import com.revib.revib.session.SessionVariables;

import android.app.Activity;

public class ExplainInflationsState extends State {

	public ExplainInflationsState(Activity activity, State previousState) {
		super(activity, previousState);
	}

	@Override
	public int getInfoResource() {
		switch(AGE){
			case SessionVariables.ADULT:
				return R.string.explain_inflations_info;
			case SessionVariables.CHILD:
				return R.string.explain_inflations_info_child;
			case SessionVariables.BABY:
				return R.string.explain_inflations_info_baby;				
		}
		return -1;
	}

	@Override
	public int getAudioResource() {
		switch(AGE){
			case SessionVariables.BABY:
				return R.raw.baby_explain_inflations;
			default:
				return R.raw.explain_inflations_1;			
		}
	}

	@Override
	public int getImageResource() {
		int	res	=	R.drawable.no_image;
		switch(AGE){
			case SessionVariables.ADULT:
				return R.drawable.adult_inflations_animation;
			case SessionVariables.CHILD:
				return R.drawable.child_inflations_animation;
			case SessionVariables.BABY:		
				return R.drawable.baby_inflations_animation;	
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
		return R.string.explain_inflations_title;
	}

	@Override
	public State getNextState(int buttonRes) {
		return new	InflationsState(activity,this);
	}

}
