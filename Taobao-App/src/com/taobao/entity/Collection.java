package com.taobao.entity;

public class Collection {
	public int id ;
	public int shopId;
	public String userName;
	public String img;
	public String img2;
	public String descp;
	public String price;
	public int num;
	public Collection() {
		super();
	}
	
	public Collection(String userName, String img, String img2, String descp,
			String price,int num,int shopId) {
		super();
		this.userName = userName;
		this.img = img;
		this.img2 = img2;
		this.descp = descp;
		this.price = price;
		this.num = num;
		this.shopId = shopId;
	}

	public int getShopId() {
		return shopId;
	}

	public void setShopId(int shopId) {
		this.shopId = shopId;
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getImg2() {
		return img2;
	}

	public void setImg2(String img2) {
		this.img2 = img2;
	}

	public String getDescp() {
		return descp;
	}

	public void setDescp(String descp) {
		this.descp = descp;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
}	
