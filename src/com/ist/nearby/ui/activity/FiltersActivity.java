package com.ist.nearby.ui.activity;

import com.example.nearby.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class FiltersActivity extends Activity {
	private final String FILTER_TYPE = "FILTER_TYPE";
	private final String TRANSPORTS = "TRANSPORTS";
	private final String RESTAURANTS = "RESTAURANTS";
	private final String INTEREST_POINTS = "INTEREST POINTS";	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_filters);
	}
	
	public void openTransportsFilters(View v){
		Intent myIntent = new Intent(this, FiltersTypeActivity.class);
		myIntent.putExtra(FILTER_TYPE, TRANSPORTS);
		startActivity(myIntent);
	}
	
	public void openRestaurantsFilters(View v){
		Intent myIntent = new Intent(this, FiltersTypeActivity.class);
		myIntent.putExtra(FILTER_TYPE, RESTAURANTS);
		startActivity(myIntent);
	}
	
	public void openInterestPointsFilters(View v){
		Intent myIntent = new Intent(this, FiltersTypeActivity.class);
		myIntent.putExtra(FILTER_TYPE, INTEREST_POINTS);
		startActivity(myIntent);
	}

}
