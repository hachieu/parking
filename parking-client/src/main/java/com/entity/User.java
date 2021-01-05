package com.entity;

import java.util.List;

public class User {
	private int id;
	private String email;
	private String password;
	private String active;
	private List<Role> roles;
	
	public User() {
		super();
	}

	public User(String email, String password, String active, List<Role> roles) {
		super();
		this.email = email;
		this.password = password;
		this.active = active;
		this.roles = roles;
	}

	public User(int id, String email, String password, String active, List<Role> roles) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.active = active;
		this.roles = roles;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
}
