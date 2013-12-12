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
		populateDatabase();
		mDbHelper.close();
	}
	
	private void populateDatabase() {
		
		ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();
		ArrayList<InterestPoint> interestPoints = new ArrayList<InterestPoint>();
		ArrayList<PublicTransport> publicTransports = new ArrayList<PublicTransport>();
		
		//Add dummy restaurants
		restaurants.add(new Restaurant("Café da Maria", "Café", 3.5f, "9:00/22:00", 0, 2.5f));
		restaurants.add(new Restaurant("Café do Aires", "Café", 4.0f, "9:00/20:00", 0, 4.5f));
		restaurants.add(new Restaurant("O Fixe", "Snack-Bar", 2.0f, "9:00/19:00", 0, 3.5f));
		restaurants.add(new Restaurant("Bom Garfo", "Restaurante", 10.0f, "11:00/22:00", 0, 4f));
		
		//Add dummy interest points
		interestPoints.add(new InterestPoint("Torre de Belém", "Uma obra histórica..", "Monumento", 12.0f, "10:00/19:00", 0, 3.5f));
		interestPoints.add(new InterestPoint("Mosteiro da Batalha", "Outra obra histórica..", "Monumento", 12.0f, "10:00/19:00", 0, 4f));
		interestPoints.add(new InterestPoint("Jardim Botânico", "A beleza natural", "Jardim", 5.0f, "10:00/18:00", 0, 2.5f));
		interestPoints.add(new InterestPoint("CCB", "Um sítio a visitar", "Museu", 13.0f, "9:00/21:00", 0, 4.5f));
		
		//Add dummy public transports
		publicTransports.add(new PublicTransport(1, "234 Carris", "Autocarro", 1.0f, "00:01:30", "Benfica"));
		publicTransports.add(new PublicTransport(2, "714 Carris", "Autocarro", 1.0f, "00:01:12", "Belém"));
		publicTransports.add(new PublicTransport(3, "Transtejo", "Barco", 1.5f, "00:00:35", "Almada"));
		publicTransports.add(new PublicTransport(4, "CP", "Comboio", 1.5f, "00:00:30", "Cascais"));
		publicTransports.add(new PublicTransport(5, "TAP-140", "Aviso", 1.0f, "00:01:30", "Londres"));
		
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
