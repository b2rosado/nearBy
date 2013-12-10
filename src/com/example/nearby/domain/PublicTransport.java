package com.example.nearby.domain;

import java.util.ArrayList;

public class PublicTransport {
	
	private String company;
	private String type;
	private int price;
	private ArrayList<String> horarios;
	
	public PublicTransport() {}
	
	public PublicTransport(String company, String type, int price, ArrayList<String> horarios) {
		this.company = company;
		this.type = type;
		this.price = price;
		this.horarios = horarios;	
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
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public ArrayList<String> getHorarios() {
		return horarios;
	}
	public void setHorarios(ArrayList<String> horarios) {
		this.horarios = horarios;
	}
}
