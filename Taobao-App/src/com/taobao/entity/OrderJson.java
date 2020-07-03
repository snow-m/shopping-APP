package com.taobao.entity;

import java.io.Serializable;
import java.util.Date;

public class OrderJson implements Serializable{

	private static final long serialVersionUID = 1L;

	private int id;
	//订单编号
	private String number;
	//订单用户
	private String userName;
	//订单备注
	private String detail;
	//订单总额
	private String total;
	//订单商品
	private String shopNameSet;
	//订单状态：1、待付款，2、已付款，3、已取消
	private String state;
	private User user;
	//订单日期
	private String time;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getShopNameSet() {
		return shopNameSet;
	}
	public void setShopNameSet(String shopNameSet) {
		this.shopNameSet = shopNameSet;
	}
	
}
