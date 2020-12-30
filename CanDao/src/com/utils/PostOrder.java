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
		PostOrder.orderCancel("DQ999999202012291311446950001",-1,"银豹测试"); 
		String json="{\"actionName\":\"candao.order.postDineInOrder\",\"ticket\":\"95914883-b1bf-4b1f-a65d-258371cb1b37\",\"accessKey\":\"4ca533f4b7f5da07\",\"vendor\":\"ncr\",\"storeId\":\"DQ316006\",\"data\":{\"orderTime\":\"2020-12-28 10:53:49\",\"orderStatus\":100,\"status\":[{\"dateTime\":\"2020-12-28 10:53:16\",\"value\":105,\"title\":\"下单（快餐）\"},{\"dateTime\":\"2020-12-28 10:53:49\",\"value\":115,\"title\":\"支付完成（快餐）\"}],\"storeId\":\"DQ316006\",\"orderDate\":\"2020-12-28\",\"thirdSn\":\"1004\",\"orderId\":\"DQ3160062012281004\",\"fromType\":\"pos\",\"deviceNo\":\"1\",\"staffId\":\"00\",\"staffBane\":\"王悦聪\",\"orderType\":3,\"posOrderType\":1,\"payType\":1,\"isPayed\":true,\"paymentDetails\":[{\"type\":15,\"posType\":\"49\",\"typeName\":\"微信线下支付\",\"tradeNo\":\"DQ316006202012281053251004\",\"posName\":\"微信当面付\",\"money\":21.9,\"payType\":1}],\"price\":21.9,\"deliveryFee\":0,\"mealFee\":0,\"discountPrice\":-2.1,\"thirdPlatformBearPrice\":0,\"merchantBearPrice\":2.1,\"merchantPrice\":21.9,\"commission\":0,\"discounts\":[{\"content\":\"热饮减2.1元\",\"childType\":3,\"merchantSubsidy\":0,\"totalAmount\":-2.1,\"vendor\":\"ncr\",\"title\":\"热饮减2.1元\",\"code\":\"22698\",\"type\":3,\"num\":1,\"thirdSubsidy\":0,\"disProducts\":[{\"types\":{\"bigType\":\"1\",\"extra\":\"冰淇淋\"},\"pid\":\"1170117\",\"name\":\"大红袍红豆奶茶(热)\",\"itemDisc\":2.1,\"dept\":{\"id\":\"17\",\"title\":\"热饮系列\"},\"num\":1,\"productTaxRate\":\"6%\",\"price\":12.0}],\"price\":-2.1}],\"products\":[{\"types\":{\"bigType\":\"1\",\"extra\":\"冰淇淋\"},\"pid\":\"1170117\",\"name\":\"大红袍红豆奶茶(热)\",\"itemDisc\":0.0,\"dept\":{\"id\":\"17\",\"title\":\"热饮系列\"},\"num\":1,\"productTaxRate\":\"6%\",\"price\":12.0},{\"types\":{\"bigType\":\"1\",\"extra\":\"冰淇淋\"},\"pid\":\"1170117\",\"name\":\"大红袍红豆奶茶(热)\",\"itemDisc\":2.1,\"dept\":{\"id\":\"17\",\"title\":\"热饮系列\"},\"num\":1,\"productTaxRate\":\"6%\",\"price\":12.0}],\"originPrice\":24.0,\"productPrice\":24.0,\"counts\":2},\"sign\":\"0bb5bfebccdbbd903b2633505cee9102\",\"serviceType\":\"pos\",\"timestamp\":1609124098313}\r\n" + 
				"";
		//postOldOrder("http://zt_qc.can-dao.com:81/api", json, "2b4449d3603b9b7e", "3057147bfa1749bd9e1d51ff18635cdd");//pos测试订单
		//批量取消订单
		// getOrderCancel("银豹测试单");
		//String jason="{\"accessKey\":\"900b38f00bb77813\",\"actionName\":\"candao.order.postDineInOrder\",\"timestamp\":1606185205085,\"ticket\":\"c57c2739-2480-492e-b973-c7180eea1020\",\"sign\":\"9c8d6eae759adbd7d06b3a1d4e5df9c2\",\"serviceType\":\"pos\",\"vendor\":\"pospal\",\"storeId\":\"DQ999999\",\"data\":{\"merchantBearPrice\":-29.9,\"orderType\":3,\"orderId\":\"202011241033196250001:4196816\",\"counts\":0,\"discountPrice\":0.0,\"orderStatus\":100,\"originPrice\":0.0,\"posOrderType\":\"1\",\"products\":[],\"orderTime\":\"2020-11-24 10:33:22\",\"payType\":1,\"fromType\":\"pos\",\"price\":29.9,\"merchantPrice\":29.9,\"commission\":0.0,\"paymentDetails\":[{\"posName\":\"现金\",\"payType\":1,\"money\":29.9,\"typeName\":\"现金\",\"type\":1,\"posType\":\"1\"}],\"thirdSn\":\"65605827362070261\",\"mealFee\":0.0,\"storeId\":\"DQ999999\",\"deliveryFee\":0.0,\"thirdPlatformBearPrice\":0.0,\"isPayed\":true,\"orderDate\":\"2020-11-24\",\"productPrice\":0.0,\"status\":[{\"dateTime\":\"2020-11-24 10:33:25\",\"title\":\"支付完成（快餐）\",\"value\":\"115\"}]}}";
		//postOldOrder(jason);	
		//postOldOrder(json, "900b38f00bb77813", "be5c7db333dfc6dab19afdab2055ebe3");//银豹？
		//postOldOrder(json, "4ca533f4b7f5da07", "2482df65c5ecb83be8166805e9dc5c3b");//NCR
		
	}

}
