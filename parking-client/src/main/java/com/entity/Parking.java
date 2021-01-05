package com.entity;

import java.util.List;

public class Parking {
	private int id;
	private String parkingName;
	private int area;
	private int slot;
	
	private List<CheckIn> checkIn;

	public Parking() {
		super();
	}

	public Parking(String parkingName, int area, int slot) {
		super();
		this.parkingName = parkingName;
		this.area = area;
		this.slot = slot;
	}

	public Parking(int id, String parkingName, int area, int slot) {
		super();
		this.id = id;
		this.parkingName = parkingName;
		this.area = area;
		this.slot = slot;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getParkingName() {
		return parkingName;
	}

	public void setParkingName(String parkingName) {
		this.parkingName = parkingName;
	}

	public int getArea() {
		return area;
	}

	public void setArea(int area) {
		this.area = area;
	}

	public int getSlot() {
		return slot;
	}

	public void setSlot(int slot) {
		this.slot = slot;
	}

	public List<CheckIn> getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(List<CheckIn> checkIn) {
		this.checkIn = checkIn;
	}
}
