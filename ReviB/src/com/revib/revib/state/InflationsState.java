package com.revib.revib.state;

import com.revib.revib.R;
import com.revib.revib.StateActivity;
import com.revib.revib.session.SessionVariables;
import com.revib.revib.session.SleepThread;

import android.app.Activity;
import android.media.MediaPlayer;
import android.widget.ImageButton;

public class InflationsState extends State {
	private		int		nofinflations			=	0;
	public InflationsState(Activity activity, State previousState) {
		super(activity, previousState);
		this.nofinflations	=	SessionVariables.getInstance().getInflations();
	}

	@Override
	public int getInfoResource() {
		if(AGE!=SessionVariables.ADULT && nofinflations==0){
			return	R.string.inflations_info_recovery;		
		}else{
			return R.string.inflations_info;
		}
	}

	@Override
	public int getAudioResource() {
		if(AGE!=SessionVariables.ADULT && nofinflations==0){
			return R.raw.inflations_5; 		
		}else{
			return R.raw.inflations_2;
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
		return R.string.inflations_title;
	}
	
	@Override
	public int getPlayBtnVisibility(){
		return ImageButton.VISIBLE;
	}

	@Override
	public int getPauseBtnVisibility(){
		return ImageButton.VISIBLE;
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
		super.beforeGoingBack();
		SessionVariables.getInstance().restInflations();
	}
	
	@Override
	public void beforeGoingForward(){
		super.beforeGoingForward();
		SessionVariables.getInstance().sumInflations();
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
