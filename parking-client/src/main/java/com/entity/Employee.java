package com.entity;

import java.util.Date;

public class Employee {
	private String id;
	private String fullName;
	private Date birthDate;
	private String gender;
	private String identity;
	private String phone;
	private String address;
	private User user;
	
	public Employee() {
		super();
	}
	
	public Employee(String id, String fullName, Date birthDate, String gender, String identity, String phone,
			String address) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.birthDate = birthDate;
		this.gender = gender;
		this.identity = identity;
		this.phone = phone;
		this.address = address;
	}



	public Employee(String id, String fullName, Date birthDate, String gender, String identity, String phone,
			String address, User user) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.birthDate = birthDate;
		this.gender = gender;
		this.identity = identity;
		this.phone = phone;
		this.address = address;
		this.user = user;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
