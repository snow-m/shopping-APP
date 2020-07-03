package com.taobao.entity;

import java.io.Serializable;

public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 943621565973937812L;
//	[{ \"address\":\"江苏淮安\", \"id\":1, \"username\":\"sandy\", \"password\":\"123456\", \"phone\":\"15601565705\" }]
	public int id;
	public String username;
	public String password;
	public String phone;
	public String address;
	
	public User() {
		super();
	}

	public User(String username, String password, String phone, String address) {
		super();
		this.username = username;
		this.password = password;
		this.phone = phone;
		this.address = address;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
}
