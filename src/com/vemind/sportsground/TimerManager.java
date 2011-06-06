package com.vemind.sportsground;

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
	
	private Runnable mUpdateTimeTask = new Runnable() {
		   public void run() {
		       final long start = mStartTime;
		       long millis = System.currentTimeMillis() - start - mTimeDelay;
		       int seconds = (int) (millis / 1000);
		       int minutes = seconds / 60;
		       seconds     = seconds % 60;
		       
		       if (timeLabel != null) {
			       if (seconds < 10) {
			           timeLabel.setText("" + minutes + ":0" + seconds);
			       } else {
			           timeLabel.setText("" + minutes + ":" + seconds);            
			       }
		       }
		       mHandler.postDelayed(mUpdateTimeTask, 100);
		   }
		};
	
	public static synchronized TimerManager getInstance() {
		if (instance == null) {
			instance = new TimerManager();
		}
		return instance;
	}
	
	private TimerManager() {
		mHandler = new Handler();
		state = STOPPED;
	}
	
	public void bindTextView(TextView tv) {
		timeLabel = tv;
	}
	
	public void init() {
		if (state == STOPPED) {
			timeLabel.setText("0:00");
		}
	}
	
	public void start() {
        if (state == STOPPED) {
        	mStartTime = System.currentTimeMillis();
        } else if (state == PAUSED) {
        	mTimeDelay += System.currentTimeMillis() - mTimePaused;
        }        	
        mHandler.removeCallbacks(mUpdateTimeTask);
        mHandler.postDelayed(mUpdateTimeTask, 100);
        state = STARTED;
    } 
	
	public void pause() {
    	mHandler.removeCallbacks(mUpdateTimeTask);
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
