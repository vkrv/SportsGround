package com.vemind.gwcounter;


public class ProfileManager {
	private static ProfileManager instance;
	
	public static synchronized ProfileManager getInstance() {
		if (instance == null) {
			instance = new ProfileManager();
		}
		return instance;
	}
	
	private ProfileManager () {
		
	}

	public DudeProfile getCurrentProfile() {
		DudeProfile ret = new DudeProfile(); 
		return ret;
	}

}
