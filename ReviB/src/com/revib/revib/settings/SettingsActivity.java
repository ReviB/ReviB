package com.revib.revib.settings;

import com.revib.revib.R;
import com.revib.revib.locale.LocaleFunctions;

import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.support.v4.app.NavUtils;

public class SettingsActivity extends Activity implements OnItemSelectedListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Context context		=	this.getBaseContext();
		String locale_code	=	LocaleFunctions.getLocaleCodeVariable(context);
		LocaleFunctions.changeCurrentLocale(context, locale_code);
		int locale_index	=	LocaleFunctions.getLocaleCodeArrayIndex(context, locale_code);
		
		initView(locale_index);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.activity_settings, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
		switch(parent.getId()){
			case R.id.settings_language_spnr:
				Context context			=	this.getBaseContext();
				Resources res			=	context.getResources();
				String[] language_codes =	res.getStringArray(R.array.language_code_list);
				
				LocaleFunctions.changeCurrentLocale(this,language_codes[pos]);
				
				initView(pos);
				break;
			case R.id.settings_sound_spnr:
				break;
		}
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
	
	private void initView(int locale_index){
		setContentView(R.layout.activity_settings);
		
		// Language Spinner
		Spinner spinner = (Spinner) findViewById(R.id.settings_language_spnr);
		
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
		        R.array.language_list, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		spinner.setSelection(locale_index, false);
		spinner.setOnItemSelectedListener(this); // this activity as listener
		
		// Sound Spinner
		spinner = (Spinner) findViewById(R.id.settings_sound_spnr);
		
		adapter = ArrayAdapter.createFromResource(this,
		        R.array.sound_list, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		
		spinner.setOnItemSelectedListener(this); // this activity as listener
		
		// Set activity title (language changing)
		setTitle(getResources().getString(R.string.settings));
		// Show the Up button in the action bar.
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

}
