package com.entity;

import java.util.Date;

public class CheckIn {
	private String id;
	private String licencePlate;
	private Date dateIn;
	private Parking parkingId;
	private BuyMonthly buyStamp;
	private Ticket ticketId;
	private String status;
	private Employee employee;
	
	private CheckOut checkOut;

	public CheckIn() {
		super();
	}

	public CheckIn(String id, String licencePlate, Date dateIn, Parking parkingId, BuyMonthly buyStamp, Ticket ticketId,
			String status, Employee employee) {
		super();
		this.id = id;
		this.licencePlate = licencePlate;
		this.dateIn = dateIn;
		this.parkingId = parkingId;
		this.buyStamp = buyStamp;
		this.ticketId = ticketId;
		this.status = status;
		this.employee = employee;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLicencePlate() {
		return licencePlate;
	}

	public void setLicencePlate(String licencePlate) {
		this.licencePlate = licencePlate;
	}

	public Date getDateIn() {
		return dateIn;
	}

	public void setDateIn(Date dateIn) {
		this.dateIn = dateIn;
	}

	public Parking getParkingId() {
		return parkingId;
	}

	public void setParkingId(Parking parkingId) {
		this.parkingId = parkingId;
	}

	public BuyMonthly getBuyStamp() {
		return buyStamp;
	}

	public void setBuyStamp(BuyMonthly buyStamp) {
		this.buyStamp = buyStamp;
	}

	public Ticket getTicketId() {
		return ticketId;
	}

	public void setTicketId(Ticket ticketId) {
		this.ticketId = ticketId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public CheckOut getCheckOut() {
		return checkOut;
	}

	public void setCheckOut(CheckOut checkOut) {
		this.checkOut = checkOut;
	}
}
