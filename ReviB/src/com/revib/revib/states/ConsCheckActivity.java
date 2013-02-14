package com.revib.revib.states;

import com.revib.revib.R;
import com.revib.revib.session.SessionVariables;

import android.os.Build;
import android.os.Bundle;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.support.v4.app.NavUtils;

public class ConsCheckActivity extends Activity {
	private static final String TAG = "ConsCheckActivity";
	
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.state_template);
		
		setViewVariables();
		
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
		animateManos();
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

	public void setViewVariables(){
		Resources	res		=	getResources();
		Drawable	draw	=	null;
		int 		age		=	SessionVariables.getInstance().getAge();
		ImageView 	image 	=	(ImageView) findViewById(R.id.state_iv);

		switch(age){
		case SessionVariables.ADULT:
			draw=res.getDrawable(R.drawable.manos_animation);
			break;
		case SessionVariables.CHILD:
			draw=res.getDrawable(R.drawable.gallery_negative);
			break;
		case SessionVariables.BABY:
			draw=res.getDrawable(R.drawable.warning);
			break;
		}
		image.setImageDrawable(draw);
	}
	public void animateManos(){
		try{
			ImageView iv = (ImageView) findViewById(R.id.state_iv);
			AnimationDrawable animation = (AnimationDrawable) iv.getDrawable();
			animation.start();
		}catch(Exception e){
			Log.w(TAG, "Drawable animation can not be started: "+e.getMessage());
		}
	}
	
	public void onStateBtnClicked(View v) {
	
	}
}
