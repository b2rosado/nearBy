package com.example.nearby;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SettingsActivity extends Activity {
	private int about_count = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
	}
	
	public void openFilters(View v){
		Intent myIntent = new Intent(this, FiltersActivity.class);
		startActivity(myIntent);
	}
	
	public void aboutClicked(View v){
		about_count++;
		
		if(about_count==15){
			TextView text = new TextView(this);
			text.setText("Best App Ever...");
			RelativeLayout layout = (RelativeLayout) findViewById(R.id.main_screen);
			layout.addView(text);
		}
	}

}
