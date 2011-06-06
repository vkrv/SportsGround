package com.vemind.sportsground;

import com.vemind.gwcounter.R;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SportsGround extends Activity {
	private final int PROFILES_ID = Menu.FIRST;
	private final int SETTINGS_ID = PROFILES_ID + 1;
    private final int CLEAR_ID = SETTINGS_ID + 1;
	private final int NEW_ID = CLEAR_ID + 1;
	private final int EXIT_ID = NEW_ID + 1;
	private final int SAVE_ID = EXIT_ID + 1;

	private DudeProfile currentProf;
	private ProfileManager profManager;
	private SessionManager sManager;
	
	private TextView textName;
	private TextView textDips;
	private TextView valDips;
	private TextView textPulls;
	private TextView valPulls;
	private TextView valTotal;
	private EditText valueEdit;
	
	private int activeType;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        valueEdit = (EditText) findViewById (R.id.add_edit);
//        dipsButton = (Button) findViewById (R.id.dips_button);
//        pullsButton = (Button) findViewById (R.id.pulls_button);
        textName = (TextView) findViewById (R.id.name_text);
        textDips = (TextView) findViewById (R.id.dips_text);
        textPulls = (TextView) findViewById (R.id.pulls_text);
        valDips = (TextView) findViewById (R.id.dips_val);
        valPulls = (TextView) findViewById (R.id.pulls_val);
        valTotal = (TextView) findViewById (R.id.total_val);
        
        profManager = ProfileManager.getInstance();
		sManager = SessionManager.getInstance();

        
    }
    
    @Override
    public void onResume() {
    	super.onResume();
    	profManager.setContext(this);
		sManager.openDB(this);
        currentProf = profManager.getCurrentProfile();
        currentProf.setSession(sManager.getCurrentSession());
    	activeType = profManager.getActive();
    	if (activeType == DudeProfile.DIPS) dipsClicked(textDips);
    	else if (activeType == DudeProfile.PULLS) pullsClicked(textPulls);
    	refreshStatus();
    }
    
    @Override
    public void onPause() {
    	super.onResume();
		sManager.saveSession(currentProf.getSession());
		sManager.close();
    	profManager.setActive(activeType);
    }
    
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
    	menu.clear();
        menu.add(0, SAVE_ID, 0, R.string.save_session);
        menu.add(0, NEW_ID, 0, R.string.new_session);
        menu.add(0, PROFILES_ID, 0, R.string.switch_profiles);
        menu.add(0, CLEAR_ID, 0, R.string.clear);
        menu.add(0, SETTINGS_ID, 0, R.string.settings);
        menu.add(0, EXIT_ID, 0, R.string.exit);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        switch(item.getItemId()) {
            case PROFILES_ID:
                switchProfiles();                
                return true;
            case SETTINGS_ID:
            	showSettings();
            	return true;
            case CLEAR_ID:
            	clearSession();
            	return true;
            case SAVE_ID:
            	showSettings();
            	return true;
            case NEW_ID:
            	newSession();
            	return true;
            case EXIT_ID:
            	this.finish();
                return true;
            }

        return super.onMenuItemSelected(featureId, item);
    }
    
	private void clearSession() {
		currentProf.clearSession();
		refreshStatus();
	}

	private void showSettings() {
		// TODO Auto-generated method stub
		
	}
	
	public SessionData newSession() { //TODO Doesn't work!!!
		sManager.saveSession(currentProf.getSession());
		SessionData ret = sManager.newSession();
		refreshStatus();
		return ret;
	}


	private void switchProfiles() {
		// TODO Auto-generated method stub
		
	}
	
	public void addValue(View v) {
		try {
			int val = Integer.decode(valueEdit.getText().toString());
			currentProf.add(activeType, val);
			valueEdit.setText("");
			refreshStatus();
		} catch (NumberFormatException e){
			Toast.makeText(this, R.string.wrong_data, Toast.LENGTH_SHORT).show();
		}
	}
	
	public void dipsClicked(View v) {
		textPulls.setBackgroundResource(R.drawable.center_left);
		valPulls.setBackgroundResource(R.drawable.center_right);
		textDips.setBackgroundResource(R.drawable.left_active);
		valDips.setBackgroundResource(R.drawable.right_active);
		valueEdit.setHint(R.string.dips_desc);
		activeType = DudeProfile.DIPS;
	}
	
	public void pullsClicked(View v) {
		textPulls.setBackgroundResource(R.drawable.left_active);
		valPulls.setBackgroundResource(R.drawable.right_active);
		textDips.setBackgroundResource(R.drawable.center_left);
		valDips.setBackgroundResource(R.drawable.center_right);
		valueEdit.setHint(R.string.pulls_desc);
		activeType = DudeProfile.PULLS;
	}
	
	public void incValue(View v) {
		currentProf.increment(activeType);
		refreshStatus();
	}
	
	public void decValue(View v) {
		currentProf.decrement(activeType);
		refreshStatus();
	}

	private void refreshStatus() {
		textName.setText("Name: " + currentProf.getName());
		valDips.setText(currentProf.getDips().toString());
		valPulls.setText(currentProf.getPulls().toString());
		valTotal.setText(currentProf.getTotal().toString());
	}
}