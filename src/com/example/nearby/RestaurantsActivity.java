package com.example.nearby;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class RestaurantsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_restaurants);
	}
	
	public void openRestaurantInfo(View v){
		Intent myIntent = new Intent(this, RestaurantInfoActivity.class);
		startActivity(myIntent);
	}

}
