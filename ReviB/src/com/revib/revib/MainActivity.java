package com.revib.revib;

import com.revib.revib.locale.LocaleFunctions;
import com.revib.revib.session.SessionVariables;
import com.revib.revib.settings.SettingsActivity;

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

		Context context		=	this.getBaseContext();
		String locale_code	=	LocaleFunctions.getLocaleCodeVariable(context);
		LocaleFunctions.changeCurrentLocale(context, locale_code);

		setContentView(R.layout.activity_main);
		
		// Add button listeners
		/*Button button = (Button) findViewById(R.id.practice_cpr_btn);
		button.setOnClickListener(this);
		button = (Button) findViewById(R.id.real_cpr_btn);
		button.setOnClickListener(this);*/
		

		setTitle(getResources().getString(R.string.app_name));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int itemId	=	item.getItemId();
		switch (itemId) {
		case R.id.menu_settings:
			Intent intent = new Intent(this, SettingsActivity.class);
		    startActivity(intent);
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

}
