package com.example.nearby;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SettingsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
	}
	
	public void openFilters(View v){
		Intent myIntent = new Intent(this, FiltersActivity.class);
		startActivity(myIntent);
	}
	
	public void openAbout(View v){
		Intent myIntent = new Intent(this, AboutActivity.class);
		startActivity(myIntent);
	}
}
