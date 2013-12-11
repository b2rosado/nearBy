package com.example.nearby.domain;

import com.example.nearby.storage.NearByDBAdapter;
import android.database.Cursor;

public class PublicTransport {
	
	private String company;
	private String type;
	private float price;
	private int identifier;
	private String schedule;
	
	//Constructors
	
	public PublicTransport() {}
	
	public PublicTransport(String company, String type, float price, int identifier, String schedule) {
		this.company = company;
		this.type = type;
		this.price = price;
		this.identifier = identifier;
		this.schedule = schedule;	
	}
	
	//Getters and setters
	
	public int getIdentifier() {
		return identifier;
	}

	public void setIdentifier(int identifier) {
		this.identifier = identifier;
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
	public void setHorarios(String schedule) {
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
			String company = cursor.getString(cursor.getColumnIndex(NearByDBAdapter.KEY_TRANSPORTS_COMPANY));
			String type = cursor.getString(cursor.getColumnIndex(NearByDBAdapter.KEY_TRANSPORTS_TYPE));
			float price = Float.parseFloat(cursor.getString(cursor.getColumnIndex(NearByDBAdapter.KEY_TRANSPORTS_PRICE)));
			int identifier = cursor.getInt(cursor.getColumnIndex(NearByDBAdapter.KEY_TRANSPORTS_NUMBER));
			String schedule = cursor.getString(cursor.getColumnIndex(NearByDBAdapter.KEY_TRANSPORTS_SCHEDULE));
			
			return new PublicTransport(company, type, price, identifier, schedule);
		} else {
			return null;
		}
	}
}
