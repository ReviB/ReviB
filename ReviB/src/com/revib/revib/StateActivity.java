package com.revib.revib;

import com.revib.revib.R;
import com.revib.revib.audio.AudioFunctions;
import com.revib.revib.dialog.AboutDialog;
import com.revib.revib.dialog.ExitDialog;
import com.revib.revib.dialog.LocaleDialog;
import com.revib.revib.locale.LocaleFunctions;
import com.revib.revib.session.SessionVariables;
import com.revib.revib.session.SleepThread;
import com.revib.revib.state.ConsCheckState;
import com.revib.revib.state.State;

import android.media.AudioManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.support.v4.app.NavUtils;

public class StateActivity extends Activity {
	private State currentState			=	null;
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		if (!SessionVariables.isScreenBig(this)){
		    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		}else{
		    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		}
		// Screen never sleeps
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        // Audio buttons changes multimedia volume
        setVolumeControlStream(AudioManager.STREAM_MUSIC);

		Context context		=	this.getBaseContext();
		String locale_code	=	LocaleFunctions.getLocaleCodeVariable(context);
		LocaleFunctions.changeCurrentLocale(context, locale_code);
        
		setContentView(R.layout.state_template);
		
		if(currentState == null){
			currentState	=	new ConsCheckState(this,null);
		}
		initView();
		
		// Show the Up button in the action bar.
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}
	
	public void onPause(){
		super.onPause();
		SessionVariables.getInstance().pause();
		try{
			if(currentState.mediaPlayer.isPlaying())
				currentState.mediaPlayer.pause();
		}catch(Exception e){}
	}
	
	public void onResume(){
		super.onResume();
		SessionVariables.getInstance().resume();
		try{
			if(!currentState.mediaPlayer.isPlaying())
				currentState.mediaPlayer.start();
		}catch(Exception e){}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.revib_menu, menu);
		return true;
	}
	
	@Override
	public void onWindowFocusChanged (boolean hasFocus){
		super.onWindowFocusChanged(hasFocus);
		View decorView	=	getWindow().getDecorView();
		if(hasFocus){
			currentState.startAnimation();
			
			// Add Immersive mode
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
				decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
												| View.SYSTEM_UI_FLAG_FULLSCREEN
												| View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
			}
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		boolean $ret	=	false;
		switch (item.getItemId()) {
		case android.R.id.home:
			try{
				State previousState	=	currentState.getPreviousState();
	
				currentState.beforeGoingBack();
				if(previousState==null)
					NavUtils.navigateUpFromSameTask(this);
				else{
					changeState(previousState);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			$ret = true;
			break;
		case R.id.menu_rate:
			startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id="+getPackageName())));
			return true;
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
		State nextState;
		switch(viewId){
			case R.id.state_left_btn:
			case R.id.state_right_btn:
				nextState	=	currentState.getNextState(viewId);
				if(nextState!=null){
					currentState.beforeGoingForward();
					changeState(nextState);
				}
				break;
			case R.id.state_reload_btn:
				// Reload same state;
				SleepThread.getInstance().interrupt();
				initView();
				break;
			case R.id.state_pause_btn:
				currentState.pauseMediaPlayer();
				SleepThread.getInstance().interrupt();
				((ImageButton)	findViewById(R.id.state_play_btn)).setEnabled(true);
				((ImageButton)	findViewById(R.id.state_pause_btn)).setEnabled(false);
				break;
			case R.id.state_play_btn:
				if(!currentState.resumeMediaPlayer()){
					nextState	=	currentState.getNextState(-1);
					if(nextState!=null){
						currentState.beforeGoingForward();
						changeState(nextState);
					}
				}
				((ImageButton)	findViewById(R.id.state_play_btn)).setEnabled(false);
				((ImageButton)	findViewById(R.id.state_pause_btn)).setEnabled(true);
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
        	try{
	        	State previousState	=	currentState.getPreviousState();
	        	if(previousState!=null){
	        		currentState.beforeGoingBack();
	        		changeState(previousState);
	        		return false;
	        	}
        	}catch(Exception e){
        		e.printStackTrace();
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
		currentState.beforeGoingForward();
		currentState	=	state;
		initView();
	}

	public void stopAudio(){
		currentState.stopMediaPlayer();
	}
}
