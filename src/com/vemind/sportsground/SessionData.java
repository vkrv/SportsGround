package com.vemind.sportsground;

import java.util.Date;

public class SessionData {
	public int id;
	public Integer pulls;
	public Integer dips;
	public Date started;
	
	public SessionData (int sId) {
		id = sId;
		pulls = 0;
		dips = 0;
		started = null;
	}
	
	public Integer getTotalCount() {
		return dips + pulls;
	}

}
