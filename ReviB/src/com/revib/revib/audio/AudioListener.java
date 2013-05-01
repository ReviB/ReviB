package com.revib.revib.audio;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

public class AudioListener  implements OnClickListener {

	//private Activity activity;
	public AudioListener(Activity activity){
		//this.activity	=	activity;
	}
	/*@Override
	public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {}
	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {}
	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		int vol	=	seekBar.getProgress();
		if(seekBar.getId()==R.id.audioSeekBar){
			AudioFunctions.setVolume(activity,vol);
		}
		((StateActivity)activity).initView();
	}*/
	
	public void onClick(DialogInterface dialog,int id) {
		dialog.cancel();
	}
}
