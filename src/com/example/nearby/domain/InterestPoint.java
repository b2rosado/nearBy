package com.example.nearby.domain;

import android.database.Cursor;

import com.example.nearby.storage.NearByDBAdapter;

public class InterestPoint {
	
	private String name;
	private String description;
	private float price;
	private String schedule;
	private int numberOfVotes;
	private float rating;
	
	//Constructors
	
	public InterestPoint(){}
	
	public InterestPoint(String name, String description, float price, String schedule, int numberOfVotes, float rating){
		this.name = name;
		this.description = description;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
     * This method converts a Cursor to a InterestPoint object
     *
     * @param cursor The cursor
     * @return InterestPoint object
     */
	public static InterestPoint cursorToInterestPoint(Cursor cursor) {
		
		if(!cursor.isAfterLast()) {
			String name = cursor.getString(cursor.getColumnIndex(NearByDBAdapter.KEY_INTEREST_POINTS_NAME));
			String description = cursor.getString(cursor.getColumnIndex(NearByDBAdapter.KEY_INTEREST_POINTS_DESCRIPTION));
			float price = Float.parseFloat(cursor.getString(cursor.getColumnIndex(NearByDBAdapter.KEY_INTEREST_POINTS_PRICE)));
			String schedule = cursor.getString(cursor.getColumnIndex(NearByDBAdapter.KEY_INTEREST_POINTS_SCHEDULE));
			int numberOfVotes = cursor.getInt(cursor.getColumnIndex(NearByDBAdapter.KEY_INTEREST_POINTS_VOTES));
			float rating = Float.parseFloat(cursor.getString(cursor.getColumnIndex(NearByDBAdapter.KEY_INTEREST_POINTS_RATING)));
					
			return new InterestPoint(name, description, price, schedule, numberOfVotes, rating);
		} else {
			return null;
		}
	}
}
