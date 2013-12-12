package com.ist.nearby.domain;

import android.database.Cursor;

import com.ist.nearby.storage.NearByDBAdapter;

public class InterestPoint {
	
	private String _name; //Unique _id
	private String description;
	private String type;
	private float price;
	private String schedule;
	private int numberOfVotes;
	private float rating;
	
	//Constructors
	
	public InterestPoint(){}
	
	public InterestPoint(String name, String description, String type, float price, String schedule, int numberOfVotes, float rating){
		this._name = name;
		this.description = description;
		this.setType(type);
		this.price = price;
		this.schedule = schedule;
		this.numberOfVotes = numberOfVotes;
		this.rating = rating;
	}

	//Getters and setters

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		this._name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
     * This method converts a Cursor to a InterestPoint object
     *
     * @param cursor The cursor
     * @return InterestPoint object
     */
	public static InterestPoint cursorToInterestPoint(Cursor cursor) {
		
		if(!cursor.isAfterLast()) {
			String name = cursor.getString(cursor.getColumnIndex(NearByDBAdapter.KEY_INTEREST_POINTS_NAME_ID));
			String description = cursor.getString(cursor.getColumnIndex(NearByDBAdapter.KEY_INTEREST_POINTS_DESCRIPTION));
			String type = cursor.getString(cursor.getColumnIndex(NearByDBAdapter.KEY_INTEREST_POINTS_TYPE));
			float price = Float.parseFloat(cursor.getString(cursor.getColumnIndex(NearByDBAdapter.KEY_INTEREST_POINTS_PRICE)));
			String schedule = cursor.getString(cursor.getColumnIndex(NearByDBAdapter.KEY_INTEREST_POINTS_SCHEDULE));
			int numberOfVotes = cursor.getInt(cursor.getColumnIndex(NearByDBAdapter.KEY_INTEREST_POINTS_VOTES));
			float rating = Float.parseFloat(cursor.getString(cursor.getColumnIndex(NearByDBAdapter.KEY_INTEREST_POINTS_RATING)));
					
			return new InterestPoint(name, description, type, price, schedule, numberOfVotes, rating);
		} else {
			return null;
		}
	}
}
