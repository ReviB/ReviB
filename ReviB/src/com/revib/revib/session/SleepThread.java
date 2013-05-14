package com.revib.revib.session;

import com.revib.revib.StateActivity;
import com.revib.revib.state.State;

public class SleepThread implements Runnable {

	private static SleepThread instance	=	null; 
	
	Thread runner;
	private StateActivity	activity	=	null;
	private int 			time		=	0;
	private State			state		=	null;
	//private Boolean			canceled	=	false;
	
	public static SleepThread getInstance(){
		if(instance==null){
			instance	=	new SleepThread();
		}
		return instance;
	}
	private SleepThread() {}
	
	public void run() {
		try {
			Thread.sleep(time);
			//Only UI Thread can change view
			activity.runOnUiThread(
				new Runnable() {
				     public void run() {
						if(activity!=null && state!=null)
							activity.autoChangeState(state);
		
				    }
				}
			);
				
		} catch (Exception e) {
			//Toast.makeText(activity, "SleepThread run exception: "+e.getMessage(), Toast.LENGTH_LONG).show();
			e.printStackTrace();
		}
	}
	public void start(StateActivity activity,State state,int time){
		this.activity	=	activity;
		this.state		=	state;
		this.time		=	time;
		runner			=	new Thread(this, "SleepThread");
		runner.start();
	}

	public void interrupt(){
		if(runner!= null && runner.isAlive()){
			try{
				runner.interrupt();
				runner=null;
			}catch(Exception e){
				//Toast.makeText(activity, "SleepThread interrupt error: "+e.getMessage(), Toast.LENGTH_LONG).show();
				e.printStackTrace();
			}
		}
	}
}
