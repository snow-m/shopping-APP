package com.taobao.entity;

import java.io.Serializable;

public class OrderShop implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7273075969866325223L;
	public int shopId;
	public int num;
	
	public OrderShop() {
		super();
	}

	public OrderShop(int shopId, int num) {
		super();
		this.shopId = shopId;
		this.num = num;
	}
	
}
