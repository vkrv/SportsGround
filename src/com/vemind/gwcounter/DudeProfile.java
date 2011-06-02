package com.vemind.gwcounter;

import java.util.Date;

public class DudeProfile {
	public final static int PULLS = 0;
	public final static int DIPS = 1;
	
	
	private Date dateCreated;
	private String name;
	private int _id;
	
	private SessionData sData;
	
	public DudeProfile () {
		name = "Vasya";
		dateCreated = new Date (System.currentTimeMillis());
		sData = new SessionData();
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

}
