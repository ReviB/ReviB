package com.revib.revib.session;

public class SessionVariables {
	public static final int BABY	=	0;
	public static final int CHILD	=	1;
	public static final int ADULT	=	2;
	
	private static SessionVariables instance = null;
	private Boolean		reality		=	false;
	private int		age			=	ADULT;
	
	protected SessionVariables() {}
	
	public static SessionVariables getInstance() {
		if(instance == null)
			instance = new SessionVariables();
		return instance;
   }

	public Boolean issReal() {
		return reality;
	}

	public void setReality(Boolean reality) {
		this.reality = reality;
	}

	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	   
	   
}
