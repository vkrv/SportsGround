package com.vemind.sportsground;


public class SessionData {
	public final static int PULLS = 0;
	public final static int DIPS = 1;

	public long id;
	public int pulls;
	public int dips;
	public long started; // Date in millis
	public int dudeId;
	
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
	
	public void add (int type, int value) {
		switch (type){
		case PULLS: 
			pulls += value;
			return;
		case DIPS:
			dips += value;
			return;
		}
	}
	
	public void increment (int type) {
		switch (type){
		case DIPS:
			dips++;
			return;
		case PULLS:
			pulls++;
			return;
		}
	}
	
	public void decrement (int type) {
		switch (type) {
		case DIPS: 
			dips--;
			return;
		case PULLS:
			pulls--;
			return;
		}
	}

	public Integer getPulls() {
		return pulls;
	}
	
	public Integer getDips() {
		return dips;
	}
	
	public Integer getTotal() {
		return dips + pulls;
	}
	
	public void clearSession() {
		dips = 0;
		pulls = 0;
		started = 0;
	}

}
