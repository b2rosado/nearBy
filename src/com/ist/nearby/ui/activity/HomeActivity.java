package com.ist.nearby.ui.activity;

import java.util.ArrayList;

import com.example.nearby.R;
import com.ist.nearby.domain.InterestPoint;
import com.ist.nearby.domain.PublicTransport;
import com.ist.nearby.domain.Restaurant;
import com.ist.nearby.storage.NearByDBAdapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends Activity {

	private NearByDBAdapter mDbHelper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		mDbHelper = NearByDBAdapter.getInstance(getApplicationContext());
		
		if(!mDbHelper.tablesExist()) {
			populateDatabase();
		}
		mDbHelper.close();
	}
	
	
	private void populateDatabase() {
		
		ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();
		ArrayList<InterestPoint> interestPoints = new ArrayList<InterestPoint>();
		ArrayList<PublicTransport> publicTransports = new ArrayList<PublicTransport>();
		
		//Add dummy restaurants
		restaurants.add(new Restaurant("Caf� da Maria", "Caf�", 3.5f, "9:00/22:00", 0, 0f));
		restaurants.add(new Restaurant("Caf� do Aires", "Caf�", 4.0f, "9:00/20:00", 0, 0f));
		restaurants.add(new Restaurant("O Fixe", "Snack-Bar", 2.0f, "9:00/19:00", 0, 0f));
		restaurants.add(new Restaurant("Bom Garfo", "Restaurante", 10.0f, "11:00/22:00", 0, 0f));
		
		//Add dummy interest points
		interestPoints.add(new InterestPoint("Mosteiro dos Jer�nimos", "Uma obra hist�rica..", "Monumento", 12.0f, "10:00/19:00", 0, 0f));
		interestPoints.add(new InterestPoint("Mosteiro da Batalha", "Outra obra hist�rica..", "Monumento", 12.0f, "10:00/19:00", 0, 0f));
		interestPoints.add(new InterestPoint("Jardim Bot�nico", "A beleza natural", "Jardim", 5.0f, "10:00/18:00", 0, 0f));
		interestPoints.add(new InterestPoint("Centro Cultural de Bel�m", "Um s�tio a visitar", "Museu", 13.0f, "9:00/21:00", 0, 0.0f));
		
		//Add dummy public transports
		publicTransports.add(new PublicTransport(1, "234 Carris", "Autocarro", 1.0f, "00:01:30"));
		publicTransports.add(new PublicTransport(2, "714 Carris", "Autocarro", 1.0f, "00:01:12"));
		publicTransports.add(new PublicTransport(3, "CP", "Comboio", 1.5f, "00:00:30"));
		publicTransports.add(new PublicTransport(4, "TAP-140", "Avi�o", 1.0f, "00:01:30"));
		
		//Populate database
		mDbHelper.updateRestaurants(restaurants);
		mDbHelper.updateInterestPoints(interestPoints);
		mDbHelper.updateTransports(publicTransports);	
	}
	
	public void openTransports(View v){
		Intent myIntent = new Intent(this, TransportsActivity.class);
		startActivity(myIntent);
	}
	
	public void openRestaurants(View v){
		Intent myIntent = new Intent(this, RestaurantsActivity.class);
		startActivity(myIntent);
	}
	
	public void openInterestPoints(View v){
		Intent myIntent = new Intent(this, InterestPointsActivity.class);
		startActivity(myIntent);
	}
	
	public void openSettings(View v){
		Intent myIntent = new Intent(this, SettingsActivity.class);
		startActivity(myIntent);
	}
}
