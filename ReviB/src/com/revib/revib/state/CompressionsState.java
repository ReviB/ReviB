package com.revib.revib.state;

import com.revib.revib.R;
import com.revib.revib.StateActivity;
import com.revib.revib.session.SessionVariables;
import com.revib.revib.session.SleepThread;

import android.app.Activity;
import android.media.MediaPlayer;

public class CompressionsState extends State {
	public CompressionsState(Activity activity, State previousState) {
		super(activity, previousState);
	}

	@Override
	public int getInfoResource() {
		switch(AGE){
			case SessionVariables.ADULT:
				return R.string.compressions_info;
			case SessionVariables.CHILD:
				return R.string.compressions_info_child;
			case SessionVariables.BABY:
				return R.string.compressions_info_baby;	
		}
		return -1;
	}

	@Override
	public int getAudioResource() {
		switch(AGE){
			case SessionVariables.ADULT:
				return R.raw.adult_compressions;
			case SessionVariables.CHILD:
			case SessionVariables.BABY:
				return R.raw.compressions_15;	
		}
		return -1;
	}

	@Override
	public int getImageResource() {
		switch(AGE){
			case SessionVariables.ADULT:
				return R.drawable.adult_compressions_animation;
			case SessionVariables.CHILD:
				return R.drawable.child_compressions_animation;
			case SessionVariables.BABY:
				return R.drawable.baby_compressions_animation;
		}
		return -1;
	}

	@Override
	public int getLeftBtnResource() {
		return -1;
	}

	@Override
	public int getRightBtnResource() {
		if(SessionVariables.getInstance().isReal()){
			return -1;
		}else{
			return R.string.skip;
		}
	}

	@Override
	public int getQuestionResource() {
		return -1;
	}

	@Override
	public int getTitleResource() {
		return R.string.compressions_title;
	}

	@Override
	public State getNextState(int buttonRes) {
		if(AGE==SessionVariables.ADULT && super.getPreviousStateClass()==ExplainCompressionsState.class){
			return new ExplainInflationsState(activity,this);
		}else{
			return new InflationsState(activity,this);
		}
	}

	@Override
	public void onCompletion(MediaPlayer mp) {
		super.onCompletion(mp);
		SleepThread.getInstance().start(
				((StateActivity) activity),
				getNextState(-1),
				500);
	}
}
