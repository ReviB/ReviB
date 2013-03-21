package com.revib.revib.states;

import com.revib.revib.R;
import com.revib.revib.session.SessionVariables;

import android.app.Activity;

public class InflationsState extends State {

	private		int		nofinflations			=	0;
	public InflationsState(Activity activity, State previousState) {
		super(activity, previousState);
		this.nofinflations	=	SessionVariables.getInstance().getInflations();
	}

	@Override
	public int getInfoResource() {
		int	res	=	R.string.inflations_info;
		if(AGE!=SessionVariables.ADULT && nofinflations==0){
			res	=	R.string.inflations_info_recovery;		
		}
		return res;
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
		return R.string.inflations_title;
	}

	@Override
	public State getNextState(int buttonRes) {
		Boolean alreadyCalled	=	SessionVariables.getInstance().hasAlreadyCalled();
		if(AGE!=SessionVariables.ADULT){
			if(nofinflations==0){
				return	new	ExplainCompressionsState(activity,this);
			}else if(nofinflations==3&&!alreadyCalled){
				return	new CallState(activity,this);
			}
		}
		//Else
		return new	CompressionsState(activity,this);
	}
	
	@Override
	public void beforeGoingBack(){
		SessionVariables.getInstance().restInflations();
	}
	
	@Override
	public void beforeGoingForward(){
		SessionVariables.getInstance().sumInflations();
	}

}
