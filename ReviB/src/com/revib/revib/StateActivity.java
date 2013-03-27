package com.revib.revib;

import com.revib.revib.R;
import com.revib.revib.audio.AudioFunctions;
import com.revib.revib.dialog.AboutDialog;
import com.revib.revib.dialog.ExitDialog;
import com.revib.revib.dialog.LocaleDialog;
import com.revib.revib.locale.LocaleFunctions;
import com.revib.revib.session.SleepThread;
import com.revib.revib.state.ConsCheckState;
import com.revib.revib.state.State;

import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.support.v4.app.NavUtils;

public class StateActivity extends Activity {
	private State currentState			=	null;
	
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Screen never sleeps
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        // Audio buttons changes multimedia volume
        setVolumeControlStream(AudioManager.STREAM_MUSIC);

		Context context		=	this.getBaseContext();
		String locale_code	=	LocaleFunctions.getLocaleCodeVariable(context);
		LocaleFunctions.changeCurrentLocale(context, locale_code);
        
		setContentView(R.layout.state_template);
		
		if(currentState == null)
			currentState	=	new ConsCheckState(this,null);
		initView();
		
		// Show the Up button in the action bar.
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.revib_menu, menu);
		return true;
	}
	
	@Override
	public void onWindowFocusChanged (boolean hasFocus){
		if(hasFocus)
			currentState.startAnimation();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		boolean $ret	=	false;
		switch (item.getItemId()) {
		case android.R.id.home:
			
			State previousState	=	currentState.getPreviousState();
			
			if(previousState==null)
				NavUtils.navigateUpFromSameTask(this);
			else{
				currentState.beforeGoingBack();
				changeState(previousState);
			}
			$ret = true;
			break;
		case R.id.menu_language:
			LocaleDialog ld	=	new LocaleDialog(this);
			ld.startDialog();
			return true;
		case R.id.menu_about:
			AboutDialog ad	=	new AboutDialog(this);
			ad.startDialog();
		    return true;
		case R.id.menu_exit:
			ExitDialog ed	=	new ExitDialog(this);
			ed.startDialog();
		    return true;
		default:
			return super.onOptionsItemSelected(item);
		}
		
		return $ret;
	}
	
	public void onStateBtnClicked(View v) {
		int viewId		=	v.getId();
		switch(viewId){
			case R.id.state_left_btn:
			case R.id.state_right_btn:
				currentState.beforeGoingForward();
				changeState(currentState.getNextState(viewId));
				break;
			case R.id.state_reload_btn:
				// Reload same state;
				SleepThread.getInstance().interrupt();
				initView();
				break;
			case R.id.state_iv:
			case R.id.state_rl:
			case R.id.state_view_btn:
				// Show info dialog
				currentState.setInfoDialog();
				break;
			case R.id.state_audio_btn:
				// Show audio warning dialog
				AudioFunctions.setAudioDialog(this);
				break;
		}
	}

	/**
	 * (Re)Loads currentState
	 */
	public void initView(){
		currentState.reloadState();
	}
	
	@Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_VOLUME_UP)){
        	int volume	=	AudioFunctions.getVolume(this);
            if(volume==1)
				currentState.reloadState();
        }
        if ((keyCode == KeyEvent.KEYCODE_VOLUME_DOWN)){
        	int volume	=	AudioFunctions.getVolume(this);
            if(volume==0)
				currentState.reloadState();
            return super.onKeyUp(keyCode, event);
        }
        if ((keyCode == KeyEvent.KEYCODE_BACK)){
        	State previousState	=	currentState.getPreviousState();
        	if(previousState!=null){
        		currentState.beforeGoingBack();
        		changeState(previousState);
        		return false;
        	}
        }
        return super.onKeyUp(keyCode, event);
    }
	
	public void changeState(State state){
		currentState	=	state;
		SleepThread.getInstance().interrupt();
		initView();
	}
	
	public void autoChangeState(State state){
		currentState	=	state;
		initView();
	}
}
