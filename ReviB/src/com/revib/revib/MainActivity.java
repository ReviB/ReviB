package com.revib.revib;

import com.revib.revib.locale.LocaleFunctions;
import com.revib.revib.settings.SettingsActivity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Context context		=	this.getBaseContext();
		String locale_code	=	LocaleFunctions.getLocaleCodeVariable(context);
		LocaleFunctions.changeCurrentLocale(context, locale_code);

		setContentView(R.layout.activity_main);

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
		switch (item.getItemId()) {
		case R.id.menu_settings:
			Intent intent = new Intent(this, SettingsActivity.class);
		    startActivity(intent);
			return true;
		}
		return false;
	}

}
