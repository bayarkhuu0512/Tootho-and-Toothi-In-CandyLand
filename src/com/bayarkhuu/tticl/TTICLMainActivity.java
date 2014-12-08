
package com.bayarkhuu.tticl;

import com.bayarkhuu.tticl.utils.Prefs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;


public class TTICLMainActivity extends Activity {

	public final static boolean DEFAULT_PLAY_MUSIC_VALUE = true;

	protected static final int HISC_LEVELSET_SELECTION = 2;
	
	
	CheckBox cbPlayMusic;
	CheckBox cbPlaySound;
	Button   btStart;
	Button   btHighscore;
	Button   btHelp;
	Button   btExit;
	
	Prefs persist; 
	public final static String PREFS_NAME = "TTICLPREF";
	private final static String PREFS_PLAY_MUSIC = "play_music";
	private final static String PREFS_PLAY_SOUND = "play_sound";
	
    /** Called with the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

		// create view from xml
        setContentView(R.layout.main);

        // checkboxes
        cbPlayMusic = (CheckBox) findViewById(R.id.cbPlayMusic);
        cbPlaySound = (CheckBox) findViewById(R.id.cbPlaySound);
        
        // find buttons in view
        btStart = ((Button) findViewById(R.id.btStart));
        btHighscore = ((Button) findViewById(R.id.btHighscore));
        btHelp = ((Button) findViewById(R.id.btHelp));
        btExit = ((Button) findViewById(R.id.btExit));

        // load persisted values
        persist = new Prefs(this, PREFS_NAME);
    	cbPlayMusic.setChecked(true);
    	cbPlaySound.setChecked(true);
        
        // set actions for buttons
        btStart.setOnClickListener(StartListener);
        btHighscore.setOnClickListener(HighscoreListener);
        btHelp.setOnClickListener(HelpListener);
        btExit.setOnClickListener(ExitListener);
    }


    @Override
    protected void onRestart() {
    	// TODO Auto-generated method stub
    	super.onRestart();
    }
    
	@Override
    protected void onStop() {
    	super.onStop();
    	persistValues();
    }

	private void persistValues() {
    	boolean playMusic = cbPlayMusic.isChecked();
    	boolean playSound = cbPlaySound.isChecked();
    	persist.putBoolean(PREFS_PLAY_MUSIC, playMusic);
    	persist.putBoolean(PREFS_PLAY_SOUND, playSound);
    	persist.commit();
	}
    
    @Override
    protected void onResume() {
    	super.onResume();
    	// do updates
    }
    

    
//    OnClickListener StartListener = new OnClickListener() {
//        public void onClick(View v) {
//	    	Intent intent = new Intent(AndroMazeActivity.this, AndroMazeLevelActivity.class);
//        	startActivity(intent);
//        }
//    };
    OnClickListener StartListener = new OnClickListener() {
        public void onClick(View v) {
	    	Intent intent = new Intent(TTICLMainActivity.this, ToothEatingAaruul.class);
	    	startActivity(intent);

        }
    };
	
    OnClickListener HighscoreListener = new OnClickListener() {
        public void onClick(View v) {
            showDialog(HISC_LEVELSET_SELECTION);
	    	//Intent intent = new Intent(AndroFishMainActivity.this, AndroFishHighscoreActivity.class);
        	//startActivity(intent);
        }
    };
	
    OnClickListener HelpListener = new OnClickListener() {
        public void onClick(View v) {
	    	Intent intent = new Intent(TTICLMainActivity.this, HelpActivity.class);
        	startActivity(intent);
        }
    };
    /**
     * A call-back for when the user presses the back button.
     */
    OnClickListener ExitListener = new OnClickListener() {
        public void onClick(View v) {
            finish();
        }
    };

    
    
}
