package com.example.nearby;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
	}
	
	public void openTransports(View v){
		Intent myIntent = new Intent(this, TransportsActivity.class);
		startActivity(myIntent);
	}
	
	public void openRestaurantes(View v){
		Intent myIntent = new Intent(this, RestaurantsActivity.class);
		startActivity(myIntent);
	}
	
	public void openSettings(View v){
		Intent myIntent = new Intent(this, SettingsActivity.class);
		startActivity(myIntent);
	}
	
	public void openMonuments(View v){
		Intent myIntent = new Intent(this, MonumentsActivity.class);
		startActivity(myIntent);
		
	}

}
