package com.taobao.entity;

public class Order1 {
	public int id ;
	
	public String username;
	public String orderTime;
	public String orderPrice;
	public Order1() {
		super();
	}
	
	public Order1(String username, String orderTime, String orderPrice) {
		super();
		this.username = username;
		this.orderTime = orderTime;
		this.orderPrice = orderPrice;
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

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	public String getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(String orderPrice) {
		this.orderPrice = orderPrice;
	}
	
}	
