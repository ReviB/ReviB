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
			int 		age		=	SessionVariables.getInstance().getAge();
			
			// Set title
			activity.setTitle(res.getString(R.string.cons_check_activity));
			
			// Set Question
			TextView	question_tv	=	(TextView) activity.findViewById(R.id.state_question_tv);
			question_tv.setText(R.string.cons_check_question);
			
			// Set image
			ImageView 	image 	=	(ImageView) activity.findViewById(R.id.state_iv);

			image.setImageDrawable(res.getDrawable(R.drawable.manos_animation));
		}catch(Exception e){
			Log.w(TAG, "State view could not be set: "+e.getMessage());
		}
	}

	@Override
	public State getNextState(int buttonRes) {
		State nextState	=	null;
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

}
