package com.revib.revib.dialog;

import com.revib.revib.MainActivity;
import com.revib.revib.AgeActivity;
import com.revib.revib.StateActivity;
import com.revib.revib.R;
import com.revib.revib.locale.LocaleFunctions;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.res.Resources;
import android.os.Build;

public class LocaleDialog implements OnClickListener {

	private Activity activity;
	public LocaleDialog	(Activity activity){
		this.activity	=	activity;
	}
	
	public void startDialog(){
		AlertDialog.Builder builder = new AlertDialog.Builder(activity);
	    builder.setTitle(R.string.language)
	           .setItems(R.array.language_list, this);
	    
		// create alert dialog
		AlertDialog alertDialog = builder.create();

		// show it
		alertDialog.show();
	}

	@Override
	public void onClick(DialogInterface dialog, int pos) {
 	   Context context			=	activity.getBaseContext();
 	   Resources res			=	context.getResources();
 	   String[] language_codes =	res.getStringArray(R.array.language_code_list);
 	   
 	   
 	   LocaleFunctions.changeCurrentLocale(context,language_codes[pos]);
 	   
 	   if(activity.getClass().equals(MainActivity.class)){
 		   ((MainActivity)activity).initView();
 	   }else if(activity.getClass().equals(AgeActivity.class)){
 		   ((AgeActivity)activity).initView();
 	   }else if(activity.getClass().equals(StateActivity.class)){
 		   ((StateActivity)activity).initView();
 	   }
 	   if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
 		   activity.invalidateOptionsMenu();
 	   }
	}
}
