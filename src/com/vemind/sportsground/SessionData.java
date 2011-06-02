package com.vemind.sportsground;


public class SessionData {
	public long id;
	public int pulls;
	public int dips;
	public long started; // Date in millis
	
	public SessionData (int sId) {
		id = sId;
		pulls = 0;
		dips = 0;
		started = 0;
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
