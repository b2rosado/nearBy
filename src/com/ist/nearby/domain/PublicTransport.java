package com.ist.nearby.domain;

import com.ist.nearby.storage.NearByDBAdapter;

import android.database.Cursor;

public class PublicTransport {
	
	private int _id; //Unique _id
	private String company;
	private String type;
	private float price;
	private String schedule;
	
	//Constructors
	
	public PublicTransport() {}
	
	public PublicTransport(int id, String company, String type, float price, String schedule) {
		this._id = id;
		this.company = company;
		this.type = type;
		this.price = price;
		this.schedule = schedule;	
	}
	
	//Getters and setters
	
	public int getId() {
		return _id;
	}

	public void setId(int id) {
		this._id = id;
	}

	public String getCompany() {
		return company;
	}
	
	public void setCompany(String company) {
		this.company = company;
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
	
	/**
     * This method converts a Cursor to a PublicTransport object
     *
     * @param cursor The cursor
     * @return PublicTransport object
     */
	public static PublicTransport cursorToPublicTransport(Cursor cursor) {
	
		if(!cursor.isAfterLast()) {
			int id = cursor.getInt(cursor.getColumnIndex(NearByDBAdapter.KEY_TRANSPORTS_ID));
			String company = cursor.getString(cursor.getColumnIndex(NearByDBAdapter.KEY_TRANSPORTS_COMPANY));
			String type = cursor.getString(cursor.getColumnIndex(NearByDBAdapter.KEY_TRANSPORTS_TYPE));
			float price = Float.parseFloat(cursor.getString(cursor.getColumnIndex(NearByDBAdapter.KEY_TRANSPORTS_PRICE)));
			String schedule = cursor.getString(cursor.getColumnIndex(NearByDBAdapter.KEY_TRANSPORTS_SCHEDULE));
			
			return new PublicTransport(id, company, type, price, schedule);
		} else {
			return null;
		}
	}
}
