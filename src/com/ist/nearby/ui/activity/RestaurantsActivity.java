package com.ist.nearby.ui.activity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.example.nearby.R;
import com.ist.nearby.domain.Restaurant;
import com.ist.nearby.storage.NearByDBAdapter;
import com.ist.nearby.ui.adapter.Item;
import com.ist.nearby.ui.adapter.ItemAdapter;
import com.ist.nearby.ui.adapter.RestaurantItem;
import com.ist.nearby.ui.adapter.SectionItem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class RestaurantsActivity extends Activity {

	private ListView mRestaurants;
	private NearByDBAdapter mDbHelper;
	private SparseArray<Restaurant> mItemToRestaurant;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_restaurants);
		
		mRestaurants = (ListView) findViewById(R.id.lv_content);
	}
	
	@Override
	public void onResume() {
		super.onResume();
		setupListeners();
		updateRestaurants(fetchRestaurants());
	}
	
	private ArrayList<Restaurant> fetchRestaurants() {
		
		ArrayList<Restaurant> restaurants;
		mDbHelper = NearByDBAdapter.getInstance(getApplicationContext());
		restaurants = mDbHelper.fetchRestaurants();
		
		mDbHelper.close();
		
		return restaurants;
	}
	
	private void updateRestaurants(ArrayList<Restaurant> restaurants) {
		
		ArrayList<Item> items = new ArrayList<Item>();
		mItemToRestaurant = new SparseArray<Restaurant>();
		Collections.sort(restaurants, new RestaurantTypeComparator());
		
		//Initial restaurant type to set the cycle comparison
		String auxType = restaurants.get(0).getType();
		
		items.add(new SectionItem(auxType));
		
		int index = 0;
		
		for(Restaurant restaurant : restaurants) {
			
			String type = restaurant.getType();
			
			if(!type.equals(auxType)) {
				items.add(new SectionItem(type));
				auxType = type;
				index++;
			}
			
			index++;
			mItemToRestaurant.put(index, restaurant);
			items.add(new RestaurantItem(restaurant));
		}
		
		ItemAdapter itemAdapter = new ItemAdapter(getApplicationContext(), items);
		mRestaurants.setAdapter(itemAdapter);	
	}
	
	class RestaurantTypeComparator implements Comparator<Restaurant> {

		@Override
		public int compare(Restaurant restaurant1, Restaurant restaurant2) {
			return restaurant1.getType().compareTo(restaurant2.getType());
		}
	}
	
	private void setupListeners() {
		
		mRestaurants.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
				
				Restaurant restaurant = mItemToRestaurant.get(position);
				Intent intent = new Intent(getApplicationContext(), RestaurantInfoActivity.class);
				intent.putExtra("RESTAURANT_NAME_ID", restaurant.getName());
				startActivity(intent);
			}
		});
	}
}
