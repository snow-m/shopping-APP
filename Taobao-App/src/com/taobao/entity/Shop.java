package com.taobao.entity;

import java.io.Serializable;

public class Shop implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6150986894262835155L;
	public int id;
	public String img;
	public String img2;
	public String descp;
	public String price;
	public String barcode;
	public String type;//种类
	public int num = 1;//购买数量
	public long number = 0;//订单号
	public String shopType;//(热门、最新、优惠、推荐、其他)
	
	public Shop() {
		super();
	}

	public Shop(String img, String img2,String descp, String price, String type, String shopType,int num) {
		super();
		this.img = img;
		this.img2 = img2;
		this.descp = descp;
		this.price = price;
		this.type = type;
		this.shopType = shopType;
		this.num = num;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getDescp() {
		return descp;
	}

	public void setDescp(String descp) {
		this.descp = descp;
	}

	public String getImg2() {
		return img2;
	}

	public void setImg2(String img2) {
		this.img2 = img2;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getShopType() {
		return shopType;
	}

	public void setShopType(String shopType) {
		this.shopType = shopType;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	
}
