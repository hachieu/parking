package com.entity;

import java.util.Date;

public class CheckOut {
	private String id;
	private Date dateOut;
	private int price;
	private CheckIn checkIn;
	private Employee employee;
	
	public CheckOut() {
		super();
	}

	public CheckOut(String id, Date dateOut, int price, CheckIn checkIn, Employee employee) {
		super();
		this.id = id;
		this.dateOut = dateOut;
		this.price = price;
		this.checkIn = checkIn;
		this.employee = employee;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getDateOut() {
		return dateOut;
	}

	public void setDateOut(Date dateOut) {
		this.dateOut = dateOut;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public CheckIn getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(CheckIn checkIn) {
		this.checkIn = checkIn;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}
