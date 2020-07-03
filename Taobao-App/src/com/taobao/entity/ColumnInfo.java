package com.taobao.entity;

import java.io.Serializable;

public class ColumnInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6412258917137864390L;
	private String name;
	private int img;
	
	public ColumnInfo() {
		super();
	}
	
	public ColumnInfo(String name, int img) {
		super();
		this.name = name;
		this.img = img;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int getImg() {
		return img;
	}

	public void setImg(int img) {
		this.img = img;
	}
}
