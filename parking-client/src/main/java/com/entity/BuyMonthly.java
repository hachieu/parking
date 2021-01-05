package com.entity;

import java.util.Date;
import java.util.List;

public class BuyMonthly {
	private String stamp;
	private Date buyDate;
	private Register register;
	
	private List<CheckIn> checkIn;

	public BuyMonthly() {
		super();
	}

	public BuyMonthly(String stamp, Date buyDate, Register register) {
		super();
		this.stamp = stamp;
		this.buyDate = buyDate;
		this.register = register;
	}

	public String getStamp() {
		return stamp;
	}

	public void setStamp(String stamp) {
		this.stamp = stamp;
	}

	public Date getBuyDate() {
		return buyDate;
	}

	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}

	public Register getRegister() {
		return register;
	}

	public void setRegister(Register register) {
		this.register = register;
	}

	public List<CheckIn> getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(List<CheckIn> checkIn) {
		this.checkIn = checkIn;
	}
}
