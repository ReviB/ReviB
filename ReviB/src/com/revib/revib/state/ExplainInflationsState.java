package com.revib.revib.state;

import com.revib.revib.R;
import com.revib.revib.StateActivity;
import com.revib.revib.session.SessionVariables;
import com.revib.revib.session.SleepThread;

import android.app.Activity;
import android.media.MediaPlayer;

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
		step=0;
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
	
	@Override
	public void onCompletion(MediaPlayer mp) {
		super.onCompletion(mp);
		switch(AGE){
			case SessionVariables.ADULT:
				step++;
				if(step==1){
					startAudio(R.raw.adult_explain_inflations_2);
					break;
				}else{
					SleepThread.getInstance().start(
							((StateActivity) activity),
							getNextState(-1),
							500);
				}
			case SessionVariables.CHILD:
				step++;
				if(step==1){
					startAudio(R.raw.child_explain_inflations_2);
					break;
				}else{
					SleepThread.getInstance().start(
							((StateActivity) activity),
							getNextState(-1),
							500);
				}
			case SessionVariables.BABY:
				SleepThread.getInstance().start(
						((StateActivity) activity),
						getNextState(-1),
						500);
		}
	}
}
