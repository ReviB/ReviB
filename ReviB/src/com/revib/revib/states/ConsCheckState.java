package com.revib.revib.states;

import com.revib.revib.R;
import com.revib.revib.session.SessionVariables;

import android.app.Activity;
import android.content.res.Resources;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class ConsCheckState extends State {
	
	public ConsCheckState(Activity activity, State previousState) {
		super(activity, previousState);
	}

	@Override
	public void setStateView() {
		try{
			Resources	res		=	activity.getResources();
			
			// Set title
			activity.setTitle(res.getString(R.string.cons_check_activity));
			
			// Set Question
			TextView	question_tv	=	(TextView) activity.findViewById(R.id.state_question_tv);
			question_tv.setText(R.string.cons_check_question);
			
			// Set image
			ImageView 	image 	=	(ImageView) activity.findViewById(R.id.state_iv);
			switch(AGE){
			case SessionVariables.ADULT:
				image.setImageDrawable(res.getDrawable(R.drawable.shake_victim));
				break;
			case SessionVariables.CHILD:
				image.setImageDrawable(res.getDrawable(R.drawable.warning));
			case SessionVariables.BABY:
				image.setImageDrawable(res.getDrawable(R.drawable.warning));
			}
			
			// Set title (for multi-lingual issues)
			activity.setTitle(R.string.cons_check_activity);
		}catch(Exception e){
			Log.w(TAG, "State view could not be set: "+e.getMessage());
		}
	}

	@Override
	public State getNextState(int buttonRes) {
		State nextState	=	this;
		switch(buttonRes){
			case R.id.state_left_btn:
				//nextState	=	new ScreamForHelpState(activity,this);
				break;
			case R.id.state_right_btn:
				if(AGE==SessionVariables.BABY){
					//nextState	=	new KeepConsCheckState(activity,this);
				}else{
					//nextState	=	new LSPState(activity,this);
				}
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

}
