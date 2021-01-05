package com.entity;

import java.util.Date;
import java.util.List;

public class Register {
	private String licencePlate;
	private String nameCustomer;
	private Date birthDate;
	private Date dateIssue;
	private Ticket ticket;
	
	private List<BuyMonthly> buyMonthly;
	
	public Register() {
		super();
	}

	public Register(String licencePlate, String nameCustomer, Date birthDate, Date dateIssue,
			Ticket ticket) {
		super();
		this.licencePlate = licencePlate;
		this.nameCustomer = nameCustomer;
		this.birthDate = birthDate;
		this.dateIssue = dateIssue;
		this.ticket = ticket;
	}

	public String getLicencePlate() {
		return licencePlate;
	}

	public void setLicencePlate(String licencePlate) {
		this.licencePlate = licencePlate;
	}

	public String getNameCustomer() {
		return nameCustomer;
	}

	public void setNameCustomer(String nameCustomer) {
		this.nameCustomer = nameCustomer;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Date getDateIssue() {
		return dateIssue;
	}

	public void setDateIssue(Date dateIssue) {
		this.dateIssue = dateIssue;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public List<BuyMonthly> getBuyMonthly() {
		return buyMonthly;
	}

	public void setBuyMonthly(List<BuyMonthly> buyMonthly) {
		this.buyMonthly = buyMonthly;
	}
	
}
