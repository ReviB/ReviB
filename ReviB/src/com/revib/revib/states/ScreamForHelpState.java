package com.revib.revib.states;

import com.revib.revib.R;
import com.revib.revib.session.SessionVariables;

import android.app.Activity;
import android.content.res.Resources;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ScreamForHelpState extends State {

	public ScreamForHelpState(Activity activity, State previousState) {
		super(activity, previousState);
	}

	@Override
	public void setStateView() {
		try{
			super.setStateView();
			
			Resources	res		=	activity.getResources();
			
			// Set Question
			TextView	question_tv	=	(TextView) activity.findViewById(R.id.state_question_tv);
			question_tv.setText(R.string.scream_for_help_question);
			
			// Set image
			ImageView 	image 	=	(ImageView) activity.findViewById(R.id.state_iv);
			image.setImageDrawable(res.getDrawable(R.drawable.scream_for_help_animation));

			// Set buttons text
			Button		btn		=	(Button) activity.findViewById(R.id.state_left_btn);
			btn.setText(R.string.scream_for_help_left_btn);
			btn					=	(Button) activity.findViewById(R.id.state_right_btn);
			btn.setText(R.string.scream_for_help_right_btn);
			
			// Set title (for multi-lingual issues)
			activity.setTitle(R.string.scream_for_help_activity);
			startAudio();
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
		return R.string.scream_for_help_info;
	}

	@Override
	public int getAudioResource() {
		return R.raw.bip;
	}

}
