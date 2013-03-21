package com.revib.revib.states;

import com.revib.revib.R;
import com.revib.revib.session.SessionVariables;

import android.app.Activity;
import android.widget.Chronometer;

public class ConsCheckState extends State {
	
	public ConsCheckState(Activity activity, State previousState) {
		super(activity, previousState);

		Chronometer time_full;
		time_full	=	(Chronometer) activity.findViewById(R.id.time_full);
		time_full.start();
	}

	@Override
	public State getNextState(int buttonRes) {
		State nextState	=	this;
		switch(buttonRes){
			case R.id.state_left_btn:
				nextState	=	new ShoutForHelpState(activity,this);
				break;
			case R.id.state_right_btn:
				nextState	=	new ConsciousState(activity,this);
				break;
		}
		return nextState;
	}

	@Override
	public int getInfoResource() {
		int $ret=-1;
		switch(AGE){
		case SessionVariables.ADULT:
			$ret = R.string.cons_check_info;
			break;
		case SessionVariables.CHILD:
			$ret = R.string.cons_check_info_child;
			break;
		case SessionVariables.BABY:
			$ret = R.string.cons_check_info_baby;
			break;
		}
		return $ret;
	}
	
	@Override
	public void startAnimation(){
		//There is no animation, so don't do anything
	}

	@Override
	public int getAudioResource() {
		/*int $ret=-1;
		switch(AGE){
		case SessionVariables.ADULT:
			$ret = R.raw.cons_check_info;
			break;
		case SessionVariables.CHILD:
			$ret = R.raw.cons_check_info_child;
			break;
		case SessionVariables.BABY:
			$ret = R.raw.cons_check_info_baby;
			break;
		}
		return $ret;*/
		return R.raw.message;
	}

	@Override
	public int getImageResource() {
		int resource	=	R.drawable.no_image;
		switch(AGE){
		case SessionVariables.ADULT:
			resource	=	R.drawable.adult_conscience_checking;
			break;
		case SessionVariables.CHILD:
			resource	=	R.drawable.child_conscience_checking;
			break;
		case SessionVariables.BABY:
			resource	=	R.drawable.baby_conscience_checking;
			break;
		}
		return resource;
	}

	@Override
	public int getLeftBtnResource() {
		return R.string.no;
	}

	@Override
	public int getRightBtnResource() {
		return R.string.yes;
	}

	@Override
	public int getQuestionResource() {

		int $ret=-1;
		switch(AGE){
		case SessionVariables.ADULT:
		case SessionVariables.CHILD:
			$ret = R.string.cons_check_question;
			break;
		case SessionVariables.BABY:
			$ret = R.string.cons_check_question_baby;
			break;
		}
		return $ret;
	}

	@Override
	public int getTitleResource() {
		return R.string.cons_check_title;
	}
}
