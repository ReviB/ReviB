package com.revib.revib.about;

import com.revib.revib.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AboutDialog implements OnClickListener {

	private Activity activity;
	public AboutDialog(Activity activity){
		this.activity	=	activity;
	}
	public void startDialog(){
		AlertDialog.Builder builder;
		final AlertDialog alertDialog;
		LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		
		View layout = inflater.inflate(R.layout.dialog_about, (ViewGroup) activity.findViewById(R.id.about_root));
		builder = new AlertDialog.Builder(activity);

		builder.setView(layout);
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
