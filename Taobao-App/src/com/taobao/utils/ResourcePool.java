package com.taobao.utils;

import java.util.List;

import com.taobao.entity.Collection;
import com.taobao.entity.Torll;
import com.taobao.entity.User;

public class ResourcePool {
	private static ResourcePool resourcePool;
	private List<Torll> buyList ;
	private List<Collection> buyCollectionList ;
	private User user ;
	private ResourcePool(){
	}
	public static ResourcePool getInstance(){
		if(resourcePool==null){
			resourcePool = new ResourcePool();
		}
		return resourcePool;
	}
	public List<Torll> getBuyList() {
		return buyList;
	}
	public void setBuyList(List<Torll> buyList) {
		this.buyList = buyList;
	}
	public List<Collection> getBuyCollectionList() {
		return buyCollectionList;
	}
	public void setBuyCollectionList(List<Collection> buyCollectionList) {
		this.buyCollectionList = buyCollectionList;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}
