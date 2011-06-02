package com.vemind.gwcounter;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SportsGround extends Activity {
    private final int PROFILES_ID = Menu.FIRST;
	private final  int SETTINGS_ID = PROFILES_ID + 1;
	private final int EXIT_ID = SETTINGS_ID + 1;
	private final int SAVE_ID = EXIT_ID + 1;

	private DudeProfile currentProf;
	private ProfileManager profManager;
	
	private TextView textStatus;
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
        textStatus = (TextView) findViewById (R.id.text_status);
        
        profManager = ProfileManager.getInstance();
        
        currentProf = profManager.getCurrentProfile();
    }
    
    @Override
    public void onResume() {
    	super.onResume();
    	refreshStatus();
    }
    
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
    	menu.clear();
        menu.add(0, SAVE_ID, 0, R.string.save_session);
        menu.add(0, PROFILES_ID, 0, R.string.switch_profiles);
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
            case SAVE_ID:
            	showSettings();
            	return true;
            case EXIT_ID:
            	this.finish();
                return true;
            }

        return super.onMenuItemSelected(featureId, item);
    }
    
    

	private void showSettings() {
		// TODO Auto-generated method stub
		
	}

	private void switchProfiles() {
		// TODO Auto-generated method stub
		
	}
	
	public void addDips(View v) {
		int dips = Integer.decode(dipsEdit.getText().toString());
		currentProf.add(DudeProfile.DIPS, dips);
		dipsEdit.setText("");
		refreshStatus();
	}
	
	public void addPulls(View v) {
		int pulls = Integer.decode(pullsEdit.getText().toString());
		currentProf.add(DudeProfile.PULLS, pulls);
		pullsEdit.setText("");
		refreshStatus();
	}

	private void refreshStatus() {
		textStatus.setText(currentProf.getTextStatus());
	}
}