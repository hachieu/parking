package com.entity;

import java.util.List;


public class Ticket {
	private int id; 
	private String vehicle;
	private int price;
	
	private List<Register> register;
	private List<CheckIn> checkIn;
	
	public Ticket() {
		super();
	}
	
	public Ticket(String vehicle, int price) {
		super();
		this.vehicle = vehicle;
		this.price = price;
	}

	public Ticket(int id, String vehicle, int price) {
		super();
		this.id = id;
		this.vehicle = vehicle;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getVehicle() {
		return vehicle;
	}

	public void setVehicle(String vehicle) {
		this.vehicle = vehicle;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public List<Register> getRegister() {
		return register;
	}

	public void setRegister(List<Register> register) {
		this.register = register;
	}

	public List<CheckIn> getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(List<CheckIn> checkIn) {
		this.checkIn = checkIn;
	}
}
