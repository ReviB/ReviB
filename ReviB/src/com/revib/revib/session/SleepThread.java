package com.revib.revib.session;

import com.revib.revib.StateActivity;
import com.revib.revib.states.State;

public class SleepThread implements Runnable {

	Thread runner;
	private StateActivity	activity	=	null;
	private int 			time		=	0;
	private State			nextState	=	null;
	public SleepThread() {
	}
	public SleepThread(
			String threadName,
			StateActivity activity,
			int time,
			State nextState) {
		runner 			= new Thread(this, threadName);
		this.activity	= activity;
		this.time 		= time;
		this.nextState	= nextState;

	}
	public void run() {
		try {
			Thread.sleep(time);
			if(activity!=null && nextState!=null)
				activity.autoChangeState(nextState);
				
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void start(){
		runner.start();
	}
	public void interrupt(){
		runner.interrupt();
	}
	
	public Boolean isAlive(){
		return runner.isAlive();
	}
	
	/*public void setActivity(StateActivity activity) {
		this.activity = activity;
	}
	
	public void setTime(int time) {
		this.time = time;
	}*/

}
