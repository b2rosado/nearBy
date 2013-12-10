package com.example.nearby.domain;

import java.util.ArrayList;

public class PublicTransport {
	
	private String company;
	private String type;
	private float price;
	private int identifier;
	private ArrayList<String> schedules;
	
	public PublicTransport() {}
	
	public PublicTransport(String company, String type, int identifier,int price, ArrayList<String> horarios) {
		this.company = company;
		this.identifier = identifier;
		this.type = type;
		this.price = price;
		this.schedules = horarios;	
	}
	
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
	public void setPrice(int price) {
		this.price = price;
	}
	public ArrayList<String> getSchedules() {
		return schedules;
	}
	public void setHorarios(ArrayList<String> schedules) {
		this.schedules = schedules;
	}
}
