package com.revib.revib;

import com.revib.revib.dialog.AboutDialog;
import com.revib.revib.dialog.ExitDialog;
import com.revib.revib.dialog.LocaleDialog;
import com.revib.revib.locale.LocaleFunctions;
import com.revib.revib.session.SessionVariables;

import android.media.AudioManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        // Audio buttons changes multimedia volume
        setVolumeControlStream(AudioManager.STREAM_MUSIC);

		Context context		=	this.getBaseContext();
		String locale_code	=	LocaleFunctions.getLocaleCodeVariable(context);
		LocaleFunctions.changeCurrentLocale(context, locale_code);

		initView();
	}
	
	@Override
	public void onRestart(){
		super.onRestart();
		if(SessionVariables.getInstance().getExit()){
			finish();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.revib_menu, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int itemId	=	item.getItemId();
		switch (itemId) {
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
		}
		return false;
	}

	public void goToAge(View v) {
		Boolean	reality	=	null;
		int viewId	=	v.getId();
		switch (viewId) {
		case R.id.practice_cpr_btn:
			reality=false;
			break;
		case R.id.real_cpr_btn:
			reality=true;
			break;
		}
		if(reality!=null){
			SessionVariables	sv	=	SessionVariables.getInstance();
			sv.setReality(reality);
			Intent intent = new Intent(this, AgeActivity.class);
		    startActivity(intent);
		}
	}

	public void initView(){
		setContentView(R.layout.activity_main);
		setTitle(getResources().getString(R.string.app_name));
	}
}
