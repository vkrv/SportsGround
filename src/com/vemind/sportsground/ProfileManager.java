package com.vemind.sportsground;

import java.util.Date;

import android.content.Context;


public class ProfileManager {
	private static ProfileManager instance;
	
	private Context mCtx;
	
	public static synchronized ProfileManager getInstance() {
		if (instance == null) {
			instance = new ProfileManager();
		}
		return instance;
	}
	
	public void setContext (Context ctx){
		mCtx = ctx;
	}

	public DudeProfile getCurrentProfile() {
		DudeProfile ret = new DudeProfile(1, "Filya", new Date (System.currentTimeMillis()));
		return ret;
	}
}
