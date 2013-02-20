package com.revib.revib;

import com.revib.revib.locale.LocaleFunctions;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class LanguageDialogFragment extends DialogFragment {
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
	    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	    builder.setTitle(R.string.language)
	           .setItems(R.array.language_list, new DialogInterface.OnClickListener() {
	               public void onClick(DialogInterface dialog, int pos) {
	            	   Activity activity	=	getActivity();
	            	   Context context			=	activity.getBaseContext();
	            	   Resources res			=	context.getResources();
	            	   String[] language_codes =	res.getStringArray(R.array.language_code_list);
	            	   
	            	   LocaleFunctions.changeCurrentLocale(getActivity(),language_codes[pos]);
	            	   //initView(pos);
	           }
	    });
	    return builder.create();
	}
}
