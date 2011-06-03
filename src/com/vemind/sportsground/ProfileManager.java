package com.vemind.sportsground;

import java.util.Date;

import android.content.Context;


public class ProfileManager {
	private static ProfileManager instance;
	
	private SessionManager sManager;

	private Context mCtx;
	private int activeType;
	
	public static synchronized ProfileManager getInstance() {
		if (instance == null) {
			instance = new ProfileManager();
		}
		return instance;
	}
	
	private ProfileManager () {
		sManager = SessionManager.getInstance();
		activeType = DudeProfile.DIPS;
	}
	
	public void setActive (int type){
		activeType = type;
	}
	
	public int getActive () {
		return activeType;
	}
	
	public void setContext (Context ctx){
		mCtx = ctx;
	}

	public DudeProfile getCurrentProfile() {
		DudeProfile ret = new DudeProfile(1, "Filya", new Date (System.currentTimeMillis()));
		sManager.openDB(mCtx);
		ret.setSession(sManager.getCurrentSession());
		return ret;
	}

	public void save(DudeProfile cProf) {
		sManager.saveSession(cProf.getSession());
		sManager.close();
	}
	
	public SessionData newSession(DudeProfile cProf) {
		sManager.saveSession(cProf.getSession());
		return sManager.newSession();
	}
}
