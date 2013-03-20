package com.revib.revib.states;

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
		/*switch(AGE){
			case SessionVariables.ADULT:
				return R.raw.inflations_info;
			case SessionVariables.CHILD:
				if(nofinflations==0)
					return R.raw.inflations_child_1;
				else
					return R.raw.inflations_child_2;
			case SessionVariables.BABY:
				if(nofinflations==0)
					return R.raw.inflations_baby_1;
				else
					return R.raw.inflations_baby_2;				
		}*/
		return R.raw.bip;
	}

	@Override
	public int getImageResource() {
		/*switch(AGE){
		case SessionVariables.ADULT:
			return R.raw.inflations_info;
		case SessionVariables.CHILD:
			if(nofinflations==0)
				return R.raw.inflations_child_1;
			else
				return R.raw.inflations_child_2;
		case SessionVariables.BABY:
			if(nofinflations==0)
				return R.raw.inflations_baby_1;
			else
				return R.raw.inflations_baby_2;				
		}*/
		return R.drawable.breathing_1;
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
