package com.vemind.sportsground;

public class SessionManager {
	private static SessionManager instance;
	
	public static synchronized SessionManager getInstance() {
		if (instance == null) {
			instance = new SessionManager();
		}
		return instance;
	}

	public SessionData getCurrentSession() {
		return new SessionData(0);
	}

	public void saveSession(SessionData session) {
		// TODO Auto-generated method stub
	}
	
	public SessionData openSession (int sId) {
		return null;
	}
	
	
}
