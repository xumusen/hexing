package com.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.UUID;

import com.alibaba.fastjson.JSONArray;

import http_post.HttpRequest;
import md5.MD5;
import net.sf.json.JSONObject;

public class PostOrder {
	
	
	public static void postOldOrder(String json) {

		String accessKey="4ca533f4b7f5da07";
		String secret="2482df65c5ecb83be8166805e9dc5c3b";
		JSONObject jsonObject = JSONObject.fromObject(json);
		String orderdata = jsonObject.getString("data");
		String actionName=jsonObject.getString("actionName");
		long timestamps = System.currentTimeMillis();
		String Ordersign = MD5.getMD5Str(
				accessKey + actionName + secret + timestamps + (StringUtil.isNullOrBlank(orderdata) ? "" : orderdata));

		JSONObject jsonNewObj = new JSONObject();
		jsonNewObj.put("actionName", jsonObject.getString("actionName"));
		jsonNewObj.put("data", orderdata);
		jsonNewObj.put("accessKey", accessKey);
		jsonNewObj.put("sign", Ordersign);
		jsonNewObj.put("timestamp", timestamps);
		jsonNewObj.put("serviceType", jsonObject.getString("serviceType"));
		jsonNewObj.put("ticket", UUID.randomUUID().toString());
		jsonNewObj.put("vendor", jsonObject.getString("vendor"));
		jsonNewObj.put("storeId", jsonObject.getString("storeId"));
		System.out.println(jsonNewObj);
		
		String sr = HttpRequest.sendPost("http://open-api.hophing.cn/api",jsonNewObj);

		System.out.println("result ----->  "+sr);
		
	}
	public static void postOldOrder(String json,String accessKey,String secret ) {

		//String accessKey="4ca533f4b7f5da07";
		//String secret="2482df65c5ecb83be8166805e9dc5c3b";
		JSONObject jsonObject = JSONObject.fromObject(json);
		String orderdata = jsonObject.getString("data");
		String actionName=jsonObject.getString("actionName");
		long timestamps = System.currentTimeMillis();
		String Ordersign = MD5.getMD5Str(
				accessKey + actionName + secret + timestamps + (StringUtil.isNullOrBlank(orderdata) ? "" : orderdata));

		JSONObject jsonNewObj = new JSONObject();
		jsonNewObj.put("actionName", jsonObject.getString("actionName"));
		jsonNewObj.put("data", orderdata);
		jsonNewObj.put("accessKey", accessKey);
		jsonNewObj.put("sign", Ordersign);
		jsonNewObj.put("timestamp", timestamps);
		jsonNewObj.put("serviceType", jsonObject.getString("serviceType"));
		jsonNewObj.put("ticket", UUID.randomUUID().toString());
		jsonNewObj.put("vendor", jsonObject.getString("vendor"));
		jsonNewObj.put("storeId", jsonObject.getString("storeId"));
		System.out.println(jsonNewObj);
		
		String sr = HttpRequest.sendPost("http://open-api.hophing.cn/api",jsonNewObj);

		System.out.println("result ----->  "+sr);
		
	}
	
	public static void postOldOrder(String url,String json,String accessKey,String secret ) {

		//String accessKey="4ca533f4b7f5da07";
		//String secret="2482df65c5ecb83be8166805e9dc5c3b";
		JSONObject jsonObject = JSONObject.fromObject(json);
		String orderdata = jsonObject.getString("data");
		String actionName=jsonObject.getString("actionName");
		long timestamps = System.currentTimeMillis();
		String Ordersign = MD5.getMD5Str(
				accessKey + actionName + secret + timestamps + (StringUtil.isNullOrBlank(orderdata) ? "" : orderdata));

		JSONObject jsonNewObj = new JSONObject();
		jsonNewObj.put("actionName", jsonObject.getString("actionName"));
		jsonNewObj.put("data", orderdata);
		jsonNewObj.put("accessKey", accessKey);
		jsonNewObj.put("sign", Ordersign);
		jsonNewObj.put("timestamp", timestamps);
		jsonNewObj.put("serviceType", jsonObject.getString("serviceType"));
		jsonNewObj.put("ticket", UUID.randomUUID().toString());
		jsonNewObj.put("vendor", jsonObject.getString("vendor"));
		jsonNewObj.put("storeId", jsonObject.getString("storeId"));
		System.out.println(jsonNewObj);
		
		String sr = HttpRequest.sendPost(url,jsonNewObj);

		System.out.println("result ----->  "+sr);
		
	}
	
	public static void orderCancel(String orderId,int status,String cancelNote) {

	
		JSONObject jsonNewObj = new JSONObject();
		JSONArray brandIds=new JSONArray();
		brandIds.add( 26002055);
		jsonNewObj.put("accessKey", "e0c308be8c579adc");
		jsonNewObj.put("ticket", "b19cbedd-e90a-4a7a-9567-2ccc6dcfec42");
		jsonNewObj.put("actionName", "candao.order.ztPostDineInStatus");
		jsonNewObj.put("fromType", 45);
		jsonNewObj.put("platformKey", "b93a3d359a795d9c");
		jsonNewObj.put("langType", 0);
		jsonNewObj.put("ip", "10.238.184.1");
		jsonNewObj.put("brandIds", brandIds);
		
		JSONObject content=new JSONObject();
		content.put("orderId", orderId);
		content.put("status", status);
		content.put("cancelReason", 3);
		content.put("cancelNote", cancelNote);
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = sdf.format(ts);
		content.put("updateTime", time);
		content.put("orderTime", "2020-10-10 17:41:47");
		jsonNewObj.put("content", content);
		System.out.println(jsonNewObj);
		
		String sr = HttpRequest.sendPost("http://101.132.90.161:80/ZtApiAction",jsonNewObj);

		System.out.println("result ----->  "+sr);
		
	}
	
	public static void  getOrderPost() throws SQLException {
		Statement stmt = DBUtil.getConnection().createStatement();
		// ResultSet executeQuery(String sqlString)：执行查询数据库的SQL语句 ，返回一个结果集（ResultSet）对象。
		// ResultSet rs = stmt.executeQuery("SELECT * from orderCancelCollect ");
		// ResultSet rs = stmt.executeQuery("SELECT * from orderCollect ");
		ResultSet rs = stmt.executeQuery("SELECT  o.json\r\n" + 
				"  FROM orders AS o WHERE o.storeId='DQ451005'\r\n" + 
				"AND o.orderDate='2020-09-13' --AND o.orderDate<='2020-09-13'\r\n" + 
				"AND o.iscancel=0\r\n" + 
				"AND LEN(o.thirdSn)=4\r\n" + 
				"ORDER BY o.thirdSn");
		while (rs.next()) {
			// System.out.println(rs.getString("req"));
			// Test.postDineorder(rs.getString("req"));
			String cell = rs.getString("json");
			//System.out.println(cell);
			postOldOrder(cell);
		}
		
	}
	
	public static void  getOrderCancel(String cancelreason) throws SQLException {
		Statement stmt = DBUtil.getConnection().createStatement();
		// ResultSet executeQuery(String sqlString)：执行查询数据库的SQL语句 ，返回一个结果集（ResultSet）对象。
		// ResultSet rs = stmt.executeQuery("SELECT * from orderCancelCollect ");
		 ResultSet rs = stmt.executeQuery("SELECT * FROM testcancel ");
		//ResultSet rs = stmt.executeQuery("EXEC p_compare_single_seito 'YS022025','2020-10-01'");
		while (rs.next()) {
			// System.out.println(rs.getString("req"));
			// Test.postDineorder(rs.getString("req"));
			String cell = rs.getString("orderid");
			System.out.println(cell);
			orderCancel(cell,-1,cancelreason);
		}
		
	}
	

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		//取消单个订单
		PostOrder.orderCancel("YS451023002202012120309362531",-1,"东北资讯要求删除"); 
		//String json="{\"serviceType\":\"pos\",\"data\":{\"merchantBearPrice\":148.95,\"orderType\":1,\"orderId\":\"DQ010001202012121725021270086\",\"counts\":2,\"userNote\":\"手机号 187****9907 环保单 [[收餐人隐私号 13164282366_4138 备份隐私号18612451434_6695]]\",\"discountPrice\":-148.95,\"orderStatus\":100,\"posOrderType\":\"4\",\"products\":[{\"itemDisc\":0.0,\"price\":0.0,\"num\":1.0,\"name\":\"餐具\",\"pid\":\"2011143000640\"},{\"itemDisc\":148.95,\"price\":258.0,\"num\":1.0,\"name\":\"圣诞快乐冰淇淋蛋糕（小号）\",\"pid\":\"2011271138350\"},{\"itemDisc\":0.0,\"price\":2.0,\"num\":2.0,\"name\":\"餐盒费\",\"pid\":\"package\"}],\"orderTime\":\"2020-12-12 17:24:34\",\"payType\":2,\"takeNo\":\"羊房胡同 (34号)@#北京市北京市西城区羊房胡同羊房胡同23号后海公园\",\"discounts\":[{\"totalAmount\":-129.0,\"childType\":0,\"code\":\"business\",\"price\":-129.0,\"vendor\":\"pospal\",\"num\":1,\"thirdSubsidy\":0.0,\"disProducts\":[{\"itemDisc\":0.0,\"price\":0.0,\"num\":1.0,\"name\":\"餐具\",\"pid\":\"2011143000640\"},{\"itemDisc\":129.0,\"price\":258.0,\"num\":1.0,\"name\":\"圣诞快乐冰淇淋蛋糕（小号）\",\"pid\":\"2011271138350\"}],\"title\":\"商家承担折扣\",\"type\":3,\"content\":\"商家承担折扣\",\"merchantSubsidy\":0.0},{\"totalAmount\":-19.95,\"childType\":0,\"code\":\"commission\",\"price\":-19.95,\"vendor\":\"pospal\",\"num\":1,\"thirdSubsidy\":0.0,\"disProducts\":[{\"itemDisc\":0.0,\"price\":0.0,\"num\":1.0,\"name\":\"餐具\",\"pid\":\"2011143000640\"},{\"itemDisc\":19.95,\"price\":258.0,\"num\":1.0,\"name\":\"圣诞快乐冰淇淋蛋糕（小号）\",\"pid\":\"2011271138350\"}],\"title\":\"佣金折扣\",\"type\":3,\"content\":\"佣金折扣\",\"merchantSubsidy\":0.0}],\"fromType\":\"pos\",\"price\":113.05,\"merchantPrice\":113.05,\"commission\":19.95,\"paymentDetails\":[{\"posName\":\"美团应收\",\"payType\":\"39\",\"money\":113.05,\"typeName\":\"美团\",\"type\":17,\"posType\":\"17\"}],\"isInvoice\":false,\"thirdSn\":\"20121217243631475038\",\"mealFee\":0.0,\"storeId\":\"DQ010001\",\"peopleNum\":99,\"deliveryFee\":0.0,\"thirdPlatformBearPrice\":0.0,\"phone\":\"13164282366_4138\",\"name\":\"王丹(女士)\",\"isPayed\":true,\"orderDate\":\"2020-12-12\",\"productPrice\":262.0,\"status\":[{\"dateTime\":\"2020-12-17 20:59:33\",\"title\":\"支付完成（快餐）\",\"value\":\"115\"}]},\"ticket\":\"7e4990fa-4e91-45c8-b1fa-96b8749248bf\",\"accessKey\":\"900b38f00bb77813\",\"vendor\":\"pospal\",\"sign\":\"c734757ebd21321fd9df33bbf8a1f45f\",\"storeId\":\"DQ010001\",\"actionName\":\"candao.order.postDineInOrder\",\"timestamp\":1608209973568}";
		//postOldOrder("http://zt_qc.can-dao.com:81/api", json, "2b4449d3603b9b7e", "3057147bfa1749bd9e1d51ff18635cdd");//pos测试订单
		//批量取消订单
		// getOrderCancel("银豹测试单");
		//String jason="{\"accessKey\":\"900b38f00bb77813\",\"actionName\":\"candao.order.postDineInOrder\",\"timestamp\":1606185205085,\"ticket\":\"c57c2739-2480-492e-b973-c7180eea1020\",\"sign\":\"9c8d6eae759adbd7d06b3a1d4e5df9c2\",\"serviceType\":\"pos\",\"vendor\":\"pospal\",\"storeId\":\"DQ999999\",\"data\":{\"merchantBearPrice\":-29.9,\"orderType\":3,\"orderId\":\"202011241033196250001:4196816\",\"counts\":0,\"discountPrice\":0.0,\"orderStatus\":100,\"originPrice\":0.0,\"posOrderType\":\"1\",\"products\":[],\"orderTime\":\"2020-11-24 10:33:22\",\"payType\":1,\"fromType\":\"pos\",\"price\":29.9,\"merchantPrice\":29.9,\"commission\":0.0,\"paymentDetails\":[{\"posName\":\"现金\",\"payType\":1,\"money\":29.9,\"typeName\":\"现金\",\"type\":1,\"posType\":\"1\"}],\"thirdSn\":\"65605827362070261\",\"mealFee\":0.0,\"storeId\":\"DQ999999\",\"deliveryFee\":0.0,\"thirdPlatformBearPrice\":0.0,\"isPayed\":true,\"orderDate\":\"2020-11-24\",\"productPrice\":0.0,\"status\":[{\"dateTime\":\"2020-11-24 10:33:25\",\"title\":\"支付完成（快餐）\",\"value\":\"115\"}]}}";
		//postOldOrder(jason);	
		//postOldOrder(json, "900b38f00bb77813", "be5c7db333dfc6dab19afdab2055ebe3");//银豹？
		//postOldOrder(json, "4ca533f4b7f5da07", "2482df65c5ecb83be8166805e9dc5c3b");//NCR
		
	}

}
