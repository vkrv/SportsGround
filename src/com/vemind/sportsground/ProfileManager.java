package com.vemind.sportsground;

import java.util.Date;


public class ProfileManager {
	private static ProfileManager instance;
	
	private SessionManager sManager;
	
	public static synchronized ProfileManager getInstance() {
		if (instance == null) {
			instance = new ProfileManager();
		}
		return instance;
	}
	
	private ProfileManager () {
		sManager = SessionManager.getInstance();
	}

	public DudeProfile getCurrentProfile() {
		DudeProfile ret = new DudeProfile(1, "Filya", new Date (System.currentTimeMillis()));
		ret.setSession(sManager.getCurrentSession());
		return ret;
	}

	public void save(DudeProfile cProf) {
		sManager.saveSession(cProf.getSession());
		
	}

}
