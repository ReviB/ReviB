package com.revib.revib;

import com.revib.revib.R;
import com.revib.revib.states.ConsCheckState;
import com.revib.revib.states.State;

import android.os.Build;
import android.os.Bundle;
import android.annotation.TargetApi;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.support.v4.app.NavUtils;

public class StateActivity extends Activity {
	private State currentState			=	null;
	
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.state_template);
		
		if(currentState == null)
			currentState	=	new ConsCheckState(this,null);
		currentState.setStateView();
		
		// Show the Up button in the action bar.
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.state_template, menu);
		return true;
	}
	
	@Override
	public void onWindowFocusChanged (boolean hasFocus){
		if(hasFocus)
			currentState.startAnimation();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			
			currentState	=	currentState.getPreviousState();
			
			if(currentState==null)
				NavUtils.navigateUpFromSameTask(this);
			else{
				currentState.setStateView();
				currentState.startAnimation();
			}
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void onStateBtnClicked(View v) {
		int viewId		=	v.getId();
		switch(viewId){
			case R.id.state_left_btn:
			case R.id.state_right_btn:
				currentState = currentState.getNextState(viewId);
			case R.id.state_reload_btn:
				currentState.reloadState();
				break;
		}
	}
}
