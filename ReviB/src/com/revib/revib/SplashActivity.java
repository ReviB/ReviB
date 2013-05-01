package com.revib.revib;

import com.revib.revib.locale.LocaleFunctions;
import com.revib.revib.session.SessionVariables;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends Activity {
    private final int SPLASH_DISPLAY_LENGHT = 3000;
    
    @Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Portrait for phones, landscape for tablets
		if (!SessionVariables.isScreenBig(this)){
		    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		}else{
		    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		}
		// Volume control keys change Stream Music volume
		setVolumeControlStream(AudioManager.STREAM_MUSIC);
		
		// Get current locale
		Context context		=	this.getBaseContext();
		String locale_code	=	LocaleFunctions.getLocaleCodeVariable(context);
		LocaleFunctions.changeCurrentLocale(context, locale_code);
		
		// Set Content View
		setContentView(R.layout.activity_splash);
	}
    
    @Override
    public void onStart() {
    	super.onStart();
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				/* Create an Intent that will start the MainActivity. */
				Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
				SplashActivity.this.startActivity(mainIntent);
				SplashActivity.this.finish();
			}
		}, SPLASH_DISPLAY_LENGHT);
    }
}
