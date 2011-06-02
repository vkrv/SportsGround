package com.vemind.sportsground;

import com.vemind.gwcounter.R;

import android.app.Activity;
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
	private final int EXIT_ID = CLEAR_ID + 1;
	private final int SAVE_ID = EXIT_ID + 1;

	private DudeProfile currentProf;
	private ProfileManager profManager;
	
	private TextView textName;
	private TextView textDips;
	private TextView textPulls;
	private TextView textOverall;
	private EditText dipsEdit;
	private EditText pullsEdit;
//	private Button dipsButton;
//	private Button pullsButton;
	
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        dipsEdit = (EditText) findViewById (R.id.dips_edit);
        pullsEdit = (EditText) findViewById (R.id.pulls_edit);
//        dipsButton = (Button) findViewById (R.id.dips_button);
//        pullsButton = (Button) findViewById (R.id.pulls_button);
        textName = (TextView) findViewById (R.id.name_text);
        textDips = (TextView) findViewById (R.id.dips_text);
        textPulls = (TextView) findViewById (R.id.pulls_text);
        textOverall = (TextView) findViewById (R.id.overall_text);
        
        profManager = ProfileManager.getInstance();
        
    }
    
    @Override
    public void onResume() {
    	super.onResume();
    	profManager.setContext(this);
        currentProf = profManager.getCurrentProfile();
    	refreshStatus();
    }
    
    @Override
    public void onPause() {
    	super.onResume();
    	profManager.save(currentProf);
    }
    
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
    	menu.clear();
        menu.add(0, SAVE_ID, 0, R.string.save_session);
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

	private void switchProfiles() {
		// TODO Auto-generated method stub
		
	}
	
	public void addDips(View v) {
		try {
			int dips = Integer.decode(dipsEdit.getText().toString());
			currentProf.add(DudeProfile.DIPS, dips);
			dipsEdit.setText("");
			refreshStatus();
		} catch (NumberFormatException e){
			Toast.makeText(this, R.string.wrong_data, Toast.LENGTH_SHORT).show();
		}
	}
	
	public void addPulls(View v) {
		try { 
			int pulls = Integer.decode(pullsEdit.getText().toString());
			currentProf.add(DudeProfile.PULLS, pulls);
			pullsEdit.setText("");
			refreshStatus();
		} catch (NumberFormatException e){
			Toast.makeText(this, R.string.wrong_data, Toast.LENGTH_SHORT).show();
		}
	}

	private void refreshStatus() {
		textName.setText("Name: " + currentProf.getName());
		textDips.setText("Dips: " + currentProf.getDips().toString());
		textPulls.setText("Pulls: " + currentProf.getPulls().toString());
		textOverall.setText("Overall: " + currentProf.getTotal().toString());
	}
}