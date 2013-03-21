package com.revib.revib.states;

import com.revib.revib.R;
import com.revib.revib.session.SessionVariables;

import android.app.Activity;

public class ExplainCompressionsState extends State {

	public ExplainCompressionsState(Activity activity, State previousState) {
		super(activity, previousState);
	}

	@Override
	public int getInfoResource() {
		switch(AGE){
			case SessionVariables.ADULT:
				return R.string.explain_compressions_info;
			case SessionVariables.CHILD:
				return R.string.explain_compressions_info_child;
			case SessionVariables.BABY:
				return R.string.explain_compressions_info_baby;				
		}
		return -1;
	}

	@Override
	public int getAudioResource() {
		/*
		switch(AGE){
			case SessionVariables.ADULT:
				return R.raw.compressions;
			case SessionVariables.CHILD:
				if(nofinflations==0)
					return R.raw.compressions_child_1;
				else
					return R.raw.compressions_child_2;
			case SessionVariables.BABY:
				if(nofinflations==0)
					return R.raw.compressions_baby_1;
				else
					return R.raw.compressions_baby_2;				
		}
		 */
		return R.raw.bip;
	}

	@Override
	public int getImageResource() {
		int	res	=	R.drawable.no_image;
		switch(AGE){
			case SessionVariables.ADULT:
				return R.drawable.adult_explain_compressions_animation;
			case SessionVariables.CHILD:
				return R.drawable.child_explain_compressions_1;
			case SessionVariables.BABY:		
				return R.drawable.baby_explain_compressions_1;	
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
		return R.string.explain_compressions_title;
	}

	@Override
	public State getNextState(int buttonRes) {
		return new CompressionsState(activity,this);
	}

}
