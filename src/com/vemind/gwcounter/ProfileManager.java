package com.vemind.gwcounter;

import java.util.Date;


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
		DudeProfile ret = new DudeProfile(1, "Filya", new Date (System.currentTimeMillis())); 
		return ret;
	}

	public void saveProfile(DudeProfile currentProf) {
		// TODO Auto-generated method stub
		
	}

}
