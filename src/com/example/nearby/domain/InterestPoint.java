package com.example.nearby.domain;

import java.util.ArrayList;

public class InterestPoint {
	private String name;
	private String description;
	private float price;
	private String schedule;
	private int numberOfVotes;
	private float rating;
	
	public InterestPoint(){}
	
	public InterestPoint(String name, String description, float price, String schedule, float rating, int numberOfVotes){
		this.name = name;
		this.description = description;
		this.price = price;
		this.schedule = schedule;
		this.rating = rating;
		this.numberOfVotes = numberOfVotes;
	}

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

	public String getSchedules() {
		return schedule;
	}

	public void setSchedules(String schedule) {
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
}
