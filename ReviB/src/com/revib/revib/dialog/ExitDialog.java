package com.revib.revib.dialog;

import com.revib.revib.MainActivity;
import com.revib.revib.R;
import com.revib.revib.StateActivity;
import com.revib.revib.session.SessionVariables;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;

public class ExitDialog implements OnClickListener {

	private Activity activity;
	public ExitDialog(Activity activity){
		this.activity	=	activity;
	}
	public void startDialog(){
		AlertDialog.Builder builder;
		final AlertDialog alertDialog;
		builder = new AlertDialog.Builder(activity);

		builder.setMessage(R.string.exit_message);
		builder.setPositiveButton(R.string.yes,this);
		builder.setNegativeButton(R.string.no, this);
		alertDialog = builder.create();

		
		//Mostramos el alertdialog
		alertDialog.show();
	}
	@Override
	public void onClick(DialogInterface dialog, int which) {
		if(which==-1){
			Intent i = new Intent(activity,MainActivity.class);
			if(activity.getClass()==StateActivity.class)
				((StateActivity)activity).stopAudio();
			SessionVariables.getInstance().setExit(true);
			activity.startActivity(i); 
			
	        activity.finish();
		}else{
			dialog.cancel();
		}
		
	}
}
