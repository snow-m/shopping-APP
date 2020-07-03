package com.taobao.bll;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.taobao.entity.OrderJson;
import com.taobao.entity.Result;
import com.taobao.entity.Shop;
import com.taobao.entity.User;
import com.taobao.web.DataRetrieve;



/**
 * @author 作者 :Duncan Wei
 * @version 创建时间：2014-1-15 上午09:42:11 类说明
 */
public class DataProcess
{
	public static boolean test(String url ,List<NameValuePair> params){
		try {
//			String resObjString = "{ \"isSuccess\":\"true\" }";
			String resObjString = DataRetrieve.getSearchData(url, params);
			Result result = JSON.parseObject(resObjString, Result.class);
			if(result==null){
				return false;
			}
			return result.getIsSuccess();
		}
		catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	public static User login(String url ,List<NameValuePair> params){
		User result = null;
		try {
//			String resObjString = "[{ \"address\":\"江苏淮安\", \"id\":1, \"username\":\"sandy\", \"password\":\"123456\", \"phone\":\"15601565705\" }]";
			String resObjString = DataRetrieve.getSearchData(url, params);
			result = JSON.parseObject(new JSONArray(resObjString).get(0).toString(), User.class);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	public static List<Shop> getShops(Context context,String url ,List<NameValuePair> params){
		try {
//			String resObjString = "[{ \"price\":\"200\", \"shopType\":\"热门\", \"img\":\"http://192.168.16.134:8080/upload/2014051520431548448.jpg\", \"img2\":\"http://192.168.16.134:8080/upload/2014051520431574367.jpg\", \"id\":1, \"type\":\"服装内衣\", \"descp\":\"2014年夏装女OL\" }]";
			String resObjString = DataRetrieve.getSearchData(url, params);
			List<Shop> shops = JSON.parseObject(resObjString,new TypeReference<List<Shop>>(){});
			if(shops==null){
				return new ArrayList<Shop>();
			}
			return shops;
		} catch (Exception e) {
			// TODO: handle exception
			return new ArrayList<Shop>();
		}
	}
	public static List<Shop> getCodeShops(Context context,String url ,List<NameValuePair> params){
		try {
//			String resObjString = "[{ \"price\":\"200\", \"shopType\":\"热门\", \"img\":\"http://192.168.16.134:8080/upload/2014051520431548448.jpg\", \"img2\":\"http://192.168.16.134:8080/upload/2014051520431574367.jpg\", \"id\":1, \"type\":\"服装内衣\", \"descp\":\"2014年夏装女OL\" }]";
			String resObjString = DataRetrieve.getSearchData(url, params);
			List<Shop> shops = JSON.parseObject(resObjString,new TypeReference<List<Shop>>(){});
			if(shops==null){
				return new ArrayList<Shop>();
			}
			return shops;
		} catch (Exception e) {
			// TODO: handle exception
			return new ArrayList<Shop>();
		}
	}
	public static List<OrderJson> getOrders(String url ,List<NameValuePair> params){
		List<OrderJson> result = null;
		try {
//			String resObjString = "[{ \"id\":1, \"state\":\"2\", \"number\":\"123456\", \"total\":\"151\", \"time\":\"2014-11-25 14:21:24\", \"user\":{ \"address\":\"wwww\", \"id\":2, \"username\":\"sandyq\", \"password\":\"123\", \"phone\":\"15601565705\" }, \"detail\":\"1231231231\" }]";
			String resObjString = DataRetrieve.getSearchData(url, params);
			result = JSON.parseObject(resObjString,new TypeReference<List<OrderJson>>(){});
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	public static List<Shop> getOrderShops(String url ,List<NameValuePair> params){
		List<Shop> result = null;
		try {
//			String resObjString = "[ { \"id\":1, \"number\":\"123456\", \"price\":\"155\", \"descp\":\"123123\", \"img\":\"http://192.168.16.19:8080/upload/2014111521543317961.jpg\", \"img2\":\"http://192.168.16.19:8080/upload/2014111521543398151.jpg\", \"num\":5 }, { \"id\":2, \"number\":\"123456\", \"price\":\"123\", \"descp\":\"132123\", \"salePrice\":\"111\", \"img\":\"http://192.168.16.19:8080/upload/2014111522065197252.jpg\", \"img2\":\"http://192.168.16.19:8080/upload/2014111522065136053.jpg\", \"num\":2 } ]";
			String resObjString = DataRetrieve.getSearchData(url, params);
			result = JSON.parseObject(resObjString,new TypeReference<List<Shop>>(){});
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
}	
