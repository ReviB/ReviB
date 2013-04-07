package com.revib.revib.state;

import com.revib.revib.R;
import com.revib.revib.StateActivity;
import com.revib.revib.session.SessionVariables;
import com.revib.revib.session.SleepThread;

import android.app.Activity;
import android.media.MediaPlayer;

public class BreathingManeuverState extends State {
	public BreathingManeuverState(Activity activity, State previousState) {
		super(activity, previousState);
	}

	@Override
	public State getNextState(int buttonRes) {
		return new BreathingCheckState(activity,this);
	}

	@Override
	public int getInfoResource() {
		int $ret=-1;
		switch(AGE){
		case SessionVariables.ADULT:
		case SessionVariables.CHILD:
			$ret = R.string.breathing_maneuver_info;
			break;
		case SessionVariables.BABY:
			$ret = R.string.breathing_maneuver_info_baby;
			break;
		}
		return $ret;
	}

	@Override
	public int getAudioResource() {
		step	=	0;
		return R.raw.breathing_maneuver_1;
	}

	@Override
	public int getImageResource() {
		int resource	=	R.drawable.no_image;
		switch(AGE){
		case SessionVariables.ADULT:
			resource	=	R.drawable.adult_breathing_maneuver_animation;
			break;
		case SessionVariables.CHILD:
			resource	=	R.drawable.child_breathing_maneuver_animation;
			break;
		case SessionVariables.BABY:
			resource	=	R.drawable.baby_breathing_maneuver_animation;
			break;
		}
		return resource;
	}

	@Override
	public int getLeftBtnResource() {
		//return R.string.breathing_maneuver_left;
		return -1;
	}

	@Override
	public int getRightBtnResource() {
		//return R.string.breathing_maneuver_right;
		return R.string.next;
	}

	@Override
	public int getQuestionResource() {
		//return R.string.breathing_maneuver_question;
		return -1;
	}

	@Override
	public int getTitleResource() {
		return R.string.breathing_maneuver_title;
	}

	@Override
	public void onCompletion(MediaPlayer mp) {
		super.onCompletion(mp);
		if(step==0){
			step++;
			if(AGE==SessionVariables.BABY){
				startAudio(R.raw.baby_breathing_maneuver_2);
			}else{
				startAudio(R.raw.breathing_maneuver_2);
			}
		}else{
			SleepThread.getInstance().start(
					((StateActivity) activity),
					getNextState(-1),
					500
			);
		}
	}

}
