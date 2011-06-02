package com.vemind.sportsground;

import java.util.Date;

public class DudeProfile {
	public final static int PULLS = 0;
	public final static int DIPS = 1;
	
	
	private Date dateCreated;
	private String name;
	private int dudeId;
	
	private SessionData sData;
	
	public DudeProfile (int nId, String nName, Date nDate) {
		dudeId = nId;
		name = nName;
		dateCreated = nDate;
	}
	
	public void setSession (SessionData session) {
		sData = session;
	}
	
	public void add (int type, int value) {
		switch (type){
		case PULLS: 
			sData.pulls += value;
			return;
		case DIPS:
			sData.dips += value;
		}
	}

	public CharSequence getTextStatus() {
		String ret;
		ret = "Name: " + name + "\n";
		ret += "Pulls: " + sData.pulls.toString() + "\n";
		ret += "Dips: " + sData.dips.toString() + "\n";
		ret += "Overall: " + sData.getTotalCount().toString();
		return ret;
	}
	
	public Integer getPulls() {
		return sData.pulls;
	}
	
	public Integer getDips() {
		return sData.dips;
	}
	
	public Integer getTotal() {
		return sData.getTotalCount();
	}
	
	public String getName() {
		return name;
	}

	public SessionData getSession() {
		return sData;
	}

}
