package com.revib.revib.locale;

import java.util.Locale;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

import com.revib.revib.R;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.content.res.Configuration;

public class LocaleFunctions {
	public static final String FILENAME = "ReviBLangCode";

	public static boolean changeCurrentLocale(Context context, String locale_code){
		Resources res = context.getResources();
	    DisplayMetrics dm = res.getDisplayMetrics();
	    Configuration conf = res.getConfiguration();
	    
		String current_locale_code	=	conf.locale.getLanguage();
		if(!current_locale_code.equals(locale_code)){
			try{
				
			    // Change locale settings in the app.
			    conf.locale = new Locale(locale_code);
			    res.updateConfiguration(conf, dm);
			    
			    LocaleFunctions.setLocaleCodeVariable(context, locale_code);
			    
			    return true;
			}catch(Exception e){
				return false;
			}
		}else{
			return true;
		}
	}
	
	public static String getLocaleCodeVariable(Context context){
		String locale_code	=	"";
		try{
			FileInputStream fIn = context.openFileInput ( FILENAME ) ;
            InputStreamReader isr = new InputStreamReader ( fIn ) ;
            BufferedReader buffreader = new BufferedReader ( isr ) ;

            String readString = buffreader.readLine ( ) ;
            while ( readString != null ) {
            	locale_code = locale_code + readString ;
                readString = buffreader.readLine ( ) ;
            }

            isr.close ( ) ;
		}catch(Exception e){
			locale_code="";
		}
		if(locale_code.equals(""))
			locale_code	=	context.getResources().getConfiguration().locale.getLanguage();
		return locale_code;
	}

	public static void setLocaleCodeVariable(Context context, String locale_code) {
		try{
			FileOutputStream fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
			fos.write(locale_code.getBytes());
			fos.close();
		}catch(Exception e){
			
		}
	}

	public static int getLocaleCodeArrayIndex(Context context, String locale_code) {

		Resources res = context.getResources();
		String[] locale_array	=	res.getStringArray(R.array.language_code_list);
		int index	=	0;
		for(String lc : locale_array){
			if(lc.equals(locale_code))
				return index;
			else
				index++;
		}
		
		//if there is no match, return -1
		return -1;
	}
}
