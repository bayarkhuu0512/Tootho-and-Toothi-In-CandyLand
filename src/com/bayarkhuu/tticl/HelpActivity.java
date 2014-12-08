
package com.bayarkhuu.tticl;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

public class HelpActivity extends Activity {

	Button btBack;
	Button btReleaseNotes;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.help);
    }
	
}
