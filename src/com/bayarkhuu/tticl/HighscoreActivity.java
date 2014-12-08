
package com.bayarkhuu.tticl;


import org.json.JSONException;

import com.bayarkhuu.tticl.entities.Highscore;
import com.bayarkhuu.tticl.utils.Prefs;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


public class HighscoreActivity extends Activity {

	
    static final int MAX_HIGHSCORE_ENTRIES = 10;

	private final static int MENU_CLEAR_HIGHSCORE  = 1;
	
	private TableLayout mHighscoreTable;
	TextView tvTitle;
	Button btOK;
	private String mLevelName;
	
    /** Called with the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		if (mLevelName == null)
			mLevelName = "easy";

		// create view from xml
        setContentView(R.layout.highscore);

        Prefs persist = new Prefs(this, "Scores-"+mLevelName);
//        String lastName = persist.getString("lastname", "");
        Highscore highscore = new Highscore(MAX_HIGHSCORE_ENTRIES);
        try {
			String hs = persist.getString("highscore", "");
			highscore.fromJSON(hs);
		} catch (JSONException ignore) {}

        // find buttons in view
        btOK = ((Button) findViewById(R.id.btOk));
        tvTitle = (TextView) findViewById(R.id.title);
        tvTitle.setText("Highscore "+mLevelName);
        mHighscoreTable = ((TableLayout) findViewById(R.id.table));

        setHighscoreText(highscore);

        // set actions for buttons
        btOK.setOnClickListener(OkListener);
    }

	private void setHighscoreText(Highscore highscore) {
        for (int i = 0; i < highscore.getNumEntries(); i++) {
        	TableRow row = (TableRow)mHighscoreTable.getChildAt(i+1);
        	TextView tvName = (TextView)row.getChildAt(1);
        	tvName.setText(highscore.getEntry(i).name);
        	TextView tvLevel = (TextView)row.getChildAt(2);
        	tvLevel.setText(Integer.toString(highscore.getEntry(i).level));
        	TextView tvScore = (TextView)row.getChildAt(3);
        	tvScore.setText(Integer.toString(highscore.getEntry(i).score));
		}
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(Menu.NONE, MENU_CLEAR_HIGHSCORE,  Menu.NONE, "Clear Highscore");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	if (item.getItemId() == MENU_CLEAR_HIGHSCORE) {
            Prefs persist = new Prefs(this, "Scores-"+mLevelName);
            persist.remove("highscore");
            persist.commit();
            setHighscoreText(new Highscore(MAX_HIGHSCORE_ENTRIES));
    	}
        return true;
    }
    
    
    /**
     * A call-back for when the user presses the back button.
     */
    OnClickListener OkListener = new OnClickListener() {
        public void onClick(View v) {
            finish();
        }
    };
	
}
