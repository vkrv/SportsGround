package com.vemind.sportsground;

import android.graphics.Color;
import android.os.Handler;
import android.widget.TextView;


public class TimerManager {
	public static final int STOPPED = 0;
	public static final int STARTED = 1;
	public static final int PAUSED = 2;
	
	private static TimerManager instance;
	private Handler mHandler;

	private long mStartTime;
	private long mTimePaused;
	private long mTimeDelay;
	private int state;
	private TextView timeLabel;
	private String strValue;
	
	private Runnable mUpdateTimeTask = new Runnable() {
		   public void run() {
			   timeLabel.setText(getString());
		       mHandler.postDelayed(mUpdateTimeTask, 100);
		   }
	};
	
	public String getString() {
		if (state == STARTED) {
		    long millis = System.currentTimeMillis() - mStartTime - mTimeDelay;
		    int tens = (int) (millis/100);
		    int seconds = tens / 10;
		    int minutes = seconds / 60;
		    seconds = seconds % 60;
		    tens = tens % 10;
		      
		    strValue = "" + minutes;
		    if (seconds < 10) {
		    	strValue += ":0" + seconds;
		    } else {
		    	strValue += ":" + seconds;
		    }
		    strValue += "." + tens;
		}
	    return strValue;
	}
	
	public static synchronized TimerManager getInstance() {
		if (instance == null) {
			instance = new TimerManager();
		}
		return instance;
	}
	
	private TimerManager() {
		mHandler = new Handler();
		state = STOPPED;
		strValue = "0:00.0";
	}
	
	public void bindTextView(TextView tv) {
		timeLabel = tv;
	}
	
	public void init() {
		timeLabel.setText(strValue);
	}
	
	public void start() {
        if (state == STOPPED) {
        	mStartTime = System.currentTimeMillis();
        } else if (state == PAUSED) {
        	mTimeDelay += System.currentTimeMillis() - mTimePaused;
        }        	
        mHandler.removeCallbacks(mUpdateTimeTask);
        mHandler.postDelayed(mUpdateTimeTask, 100);
    	timeLabel.setTextColor(Color.WHITE);
        state = STARTED;
    } 
	
	public void pause() {
    	mHandler.removeCallbacks(mUpdateTimeTask);
    	timeLabel.setTextColor(Color.YELLOW);
    	mTimePaused = System.currentTimeMillis();
    	state = PAUSED;
	}
	
	public void stop() {
		mHandler.removeCallbacks(mUpdateTimeTask);
		timeLabel.setText("0:00");
		mStartTime = 0;
		mTimePaused = 0;
		mTimeDelay = 0;
		state = STOPPED;
	}
	
	public int getState() {
		return state;
	}
}
