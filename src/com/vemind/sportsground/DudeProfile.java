package com.vemind.sportsground;

import java.util.Date;

public class DudeProfile {
	private Date dateCreated;
	private String name;
	private int dudeId;
	
	public DudeProfile (int nId, String nName, Date nDate) {
		dudeId = nId;
		name = nName;
		dateCreated = nDate;
	}
	
	public String getName() {
		return name;
	}
}
