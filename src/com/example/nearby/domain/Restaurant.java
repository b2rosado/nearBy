package com.example.nearby.domain;

import android.database.Cursor;

import com.example.nearby.storage.NearByDBAdapter;

public class Restaurant {
	
	private String name;
	private String type;
	private float price;
	private String schedule;
	private int numberOfVotes;
	private float rating;
	
	//Constructors
	
	public Restaurant() {}
	
	public Restaurant(String name, String type, float price, String schedule, int numberOfVotes, float rating) {
		this.name = name;
		this.type = type;
		this.price = price;
		this.schedule = schedule;
		this.numberOfVotes = numberOfVotes;
		this.rating = rating;
	}
	
	//Getters and setters
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getSchedule() {
		return schedule;
	}
	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}
	public int getNumberOfVotes() {
		return numberOfVotes;
	}
	public void setNumberOfVotes(int numberOfVotes) {
		this.numberOfVotes = numberOfVotes;
	}
	public float getRating() {
		return rating;
	}
	public void setRating(float rating) {
		this.rating = rating;
	}
	
	/**
     * This method converts a Cursor to a Restaurant object
     *
     * @param cursor The cursor
     * @return Restaurant object
     */
	public static Restaurant cursorToRestaurant(Cursor cursor) {
		
		if(!cursor.isAfterLast()) {
			String name = cursor.getString(cursor.getColumnIndex(NearByDBAdapter.KEY_RESTAURANT_NAME));
			String type = cursor.getString(cursor.getColumnIndex(NearByDBAdapter.KEY_RESTAURANT_TYPE));
			float price = Float.parseFloat(cursor.getString(cursor.getColumnIndex(NearByDBAdapter.KEY_RESTAURANT_PRICE)));
			String schedule = cursor.getString(cursor.getColumnIndex(NearByDBAdapter.KEY_RESTAURANT_SCHEDULE));
			int numberOfVotes = cursor.getInt(cursor.getColumnIndex(NearByDBAdapter.KEY_RESTAURANT_VOTES));
			float rating = Float.parseFloat(cursor.getString(cursor.getColumnIndex(NearByDBAdapter.KEY_RESTAURANT_RATING)));
					
			return new Restaurant(name, type, price, schedule, numberOfVotes, rating);
		} else {
			return null;
		}
	}
}
