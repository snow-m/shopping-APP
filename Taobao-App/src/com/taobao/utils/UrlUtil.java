package com.taobao.utils;

public class UrlUtil {
	private static final String IP = "http://192.168.101.3:8080/Taobao/";
	public static final String REGISTER = IP+"userAction!addUserInfo";//注册添加新用户 传参name 密码password
	public static final String LOGIN = IP+"userAction!checkUserJson";//登录 传参name 密码password
	public static final String GET_SHOPS = IP+"shopAction!getShopListJson";//获得商品列表
	public static final String GET_CODE_SHOPS = IP+"shopAction!getOneJson";//获得商品列表
	public static final String ADD_ORDER = IP+"orderAction!addOrderJson";//添加订单
	public static final String GET_ORDER = IP+"orderAction!getOrderListJson";//添加订单
	public static final String GET_ORDER_SHOPS = IP+"orderAction!getOneOrderJson";//添加订单
}
