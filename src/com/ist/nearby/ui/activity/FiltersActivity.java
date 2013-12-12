package com.ist.nearby.ui.activity;

import com.example.nearby.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class FiltersActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_filters);
	}
	
	public void openRestaurantsFilter(View v){
		Intent myIntent = new Intent(this, RestaurantsFilterActivity.class);
		startActivity(myIntent);
	}

}
