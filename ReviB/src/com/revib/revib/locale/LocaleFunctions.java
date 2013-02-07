package com.revib.revib.locale;

import java.util.Locale;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

public class LocaleFunctions {

	public static boolean changeLocale(Context context, String language_code){
		try{
			Resources res = context.getResources();
		    // Change locale settings in the app.
		    DisplayMetrics dm = res.getDisplayMetrics();
		    android.content.res.Configuration conf = res.getConfiguration();
		    conf.locale = new Locale(language_code.toLowerCase());
		    res.updateConfiguration(conf, dm);
		    return true;
		}catch(Exception e){
			return false;
		}
	}
}
