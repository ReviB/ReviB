package com.revib.revib;

import com.revib.revib.session.SessionVariables;

import android.os.Build;
import android.os.Bundle;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.support.v4.app.NavUtils;

public class AgeActivity extends Activity implements OnClickListener {

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_age);
		
		// Add button listeners
		Button button = (Button) findViewById(R.id.age_adult_btn);
		button.setOnClickListener(this);
		button = (Button) findViewById(R.id.age_child_btn);
		button.setOnClickListener(this);
		button = (Button) findViewById(R.id.age_baby_btn);
		button.setOnClickListener(this);

		// Show the Up button in the action bar.
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_age, menu);
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
	public void onClick(View v) {
		int	age	=	-1;
		Intent intent	=	null;
		int viewId	=	v.getId();
		switch (viewId) {
		case R.id.age_adult_btn:
			age=SessionVariables.ADULT;
			intent = new Intent(this, AgeActivity.class);
			break;
		case R.id.age_child_btn:
			age=SessionVariables.CHILD;
			intent = new Intent(this, AgeActivity.class);
			break;
		case R.id.age_baby_btn:
			age=SessionVariables.BABY;
			intent = new Intent(this, AgeActivity.class);
			break;
		}
		if(age!=-1){
			SessionVariables	sv	=	SessionVariables.getInstance();
			sv.setAge(age);
		    startActivity(intent);
		}
	}

}
