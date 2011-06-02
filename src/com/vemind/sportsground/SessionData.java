package com.vemind.sportsground;


public class SessionData {
	public long id;
	public Integer pulls;
	public Integer dips;
	public Long started; // Date in millis
	
	public SessionData (int sId) {
		id = sId;
		pulls = 0;
		dips = 0;
		started = null;
	}

	public SessionData (long sId, long sDate, int sDips, int sPulls) {
		id = sId;
		pulls = sPulls;
		dips = sDips;
		started = sDate;
	}
	
	public Integer getTotalCount() {
		return dips + pulls;
	}

}
