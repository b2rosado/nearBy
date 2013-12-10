package com.example.nearby.domain;

import java.util.ArrayList;

public class Restaurants {
	private String name;
	private String type;
	private String description;
	private float price;
	private ArrayList<String> schedules;
	
	public Restaurants(){}
	
	public Restaurants(String name, String type, float price, String description,ArrayList<String> schedules){
		this.name = name;
		this.type = type;
		this.price = price;
		this.description = description;
		this.schedules = schedules;
		
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

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
	public ArrayList<String> getSchedules() {
		return schedules;
	}
	public void setSchedules(ArrayList<String> schedules) {
		this.schedules = schedules;
	}
	
	
}
