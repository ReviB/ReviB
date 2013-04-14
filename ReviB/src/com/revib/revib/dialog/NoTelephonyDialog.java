package com.revib.revib.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

import com.revib.revib.R;

public class NoTelephonyDialog implements OnClickListener {

	private Activity activity;
	public NoTelephonyDialog(Activity activity){
		this.activity	=	activity;
	}
	public void startDialog(){
		AlertDialog.Builder builder;
		final AlertDialog alertDialog;
		builder = new AlertDialog.Builder(activity);

		builder.setMessage(R.string.no_telephony_message);
		builder.setNeutralButton(R.string.dialog_ok, this);
		alertDialog = builder.create();

		
		//Mostramos el alertdialog
		alertDialog.show();
	}
	@Override
	public void onClick(DialogInterface dialog, int which) {
		dialog.cancel();
	}

}
