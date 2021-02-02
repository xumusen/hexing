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
		 ResultSet rs = stmt.executeQuery(" SELECT *  FROM order_info AS oi WHERE oi.storeid='YS010196' AND oi.thirdsn LIKE '018%'");
		//ResultSet rs = stmt.executeQuery("EXEC p_compare_single_seito 'YS022025','2020-10-01'");
		while (rs.next()) {
			// System.out.println(rs.getString("req"));
			// Test.postDineorder(rs.getString("req"));
			String cell = rs.getString("extorderid");
			System.out.println(cell);
			orderCancel(cell,-1,cancelreason);
		}
		
	}
	

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		//取消单个订单
		PostOrder.orderCancel("YS024061001202101150329337438",-1,"东北要求删除"); 
		String totalNum="{\"serviceType\":\"pos\",\"data\":{\"merchantBearPrice\":12.55,\"orderType\":1,\"orderId\":\"DQ411007202101312057244240092\",\"counts\":5,\"userNote\":\"依据餐量提供餐具\",\"discountPrice\":-12.55,\"invoiceDesc\":\"\",\"orderStatus\":100,\"originPrice\":44.0,\"point\":\"\",\"posOrderType\":\"F\",\"products\":[{\"itemDisc\":0.9,\"price\":3.0,\"num\":1.0,\"name\":\"布朗尼（不可以单点，只适用于暴风雪）\",\"pid\":\"2011183000655\"},{\"itemDisc\":0.9,\"price\":3.0,\"num\":1.0,\"name\":\"芝士松糕（不可以单点，只适用于暴风雪）\",\"pid\":\"2011183000662\"},{\"itemDisc\":1.79,\"price\":3.0,\"num\":2.0,\"name\":\"M豆（不可以单点，只适用于暴风雪）\",\"pid\":\"2011183000631\"},{\"itemDisc\":8.96,\"price\":30.0,\"num\":1.0,\"name\":\"（有料大杯）奥利奥\",\"pid\":\"2011183000020\"},{\"itemDisc\":0.0,\"price\":2.0,\"num\":1.0,\"name\":\"餐盒费\",\"pid\":\"package\"}],\"orderTime\":\"2021-01-31 20:57:24\",\"payType\":2,\"takeNo\":\"亿达广场2号楼亿达广场2幢2单元602。正仁街4号。亿达广场有很多入口，在正仁街可以看到三栋一样的楼，中间的那栋楼是入口大堂。不要走黄河路或者东北路入口。进大堂直走，靠近门口的那个电梯间就是2单元2幢，在门禁上按602#\",\"discounts\":[{\"totalAmount\":-7.0,\"childType\":0,\"code\":\"business\",\"price\":-7.0,\"vendor\":\"pospal\",\"num\":1,\"thirdSubsidy\":0.0,\"disProducts\":[{\"itemDisc\":0.5,\"price\":3.0,\"num\":1.0,\"name\":\"布朗尼（不可以单点，只适用于暴风雪）\",\"pid\":\"2011183000655\"},{\"itemDisc\":0.5,\"price\":3.0,\"num\":1.0,\"name\":\"芝士松糕（不可以单点，只适用于暴风雪）\",\"pid\":\"2011183000662\"},{\"itemDisc\":1.0,\"price\":3.0,\"num\":2.0,\"name\":\"M豆（不可以单点，只适用于暴风雪）\",\"pid\":\"2011183000631\"},{\"itemDisc\":5.0,\"price\":30.0,\"num\":1.0,\"name\":\"（有料大杯）奥利奥\",\"pid\":\"2011183000020\"}],\"title\":\"商家承担折扣\",\"type\":3,\"content\":\"商家承担折扣\",\"merchantSubsidy\":0.0},{\"totalAmount\":-5.55,\"childType\":0,\"code\":\"commission\",\"price\":-5.55,\"vendor\":\"pospal\",\"num\":1,\"thirdSubsidy\":0.0,\"disProducts\":[{\"itemDisc\":0.4,\"price\":3.0,\"num\":1.0,\"name\":\"布朗尼（不可以单点，只适用于暴风雪）\",\"pid\":\"2011183000655\"},{\"itemDisc\":0.4,\"price\":3.0,\"num\":1.0,\"name\":\"芝士松糕（不可以单点，只适用于暴风雪）\",\"pid\":\"2011183000662\"},{\"itemDisc\":0.79,\"price\":3.0,\"num\":2.0,\"name\":\"M豆（不可以单点，只适用于暴风雪）\",\"pid\":\"2011183000631\"},{\"itemDisc\":3.96,\"price\":30.0,\"num\":1.0,\"name\":\"（有料大杯）奥利奥\",\"pid\":\"2011183000020\"}],\"title\":\"佣金折扣\",\"type\":3,\"content\":\"佣金折扣\",\"merchantSubsidy\":0.0}],\"fromType\":\"pos\",\"price\":31.45,\"extra\":\"\",\"merchantPrice\":31.45,\"commission\":5.55,\"paymentDetails\":[{\"posName\":\"饿了么应收\",\"payType\":\"40\",\"money\":31.45,\"typeName\":\"口碑\",\"type\":15,\"posType\":\"1006\"}],\"taxNo\":\"\",\"memberId\":\"\",\"tableNo\":\"\",\"isInvoice\":false,\"staffNo\":\"\",\"thirdSn\":\"21013120563317964082\",\"mealFee\":0.0,\"deviceNo\":\"\",\"storeId\":\"DQ411007\",\"peopleNum\":0,\"deliveryFee\":0.0,\"thirdPlatformBearPrice\":2.0,\"phone\":\"17084172879,907\",\"name\":\"梁**\",\"tableNum\":\"\",\"staffBane\":\"\",\"isPayed\":true,\"orderDate\":\"2021-01-31\",\"productPrice\":44.0,\"staffId\":\"\",\"status\":[{\"dateTime\":\"2021-01-31 20:57:26\",\"title\":\"支付完成（快餐）\",\"value\":\"115\"}]},\"ticket\":\"04559ae7-fc2a-49b8-8a6e-380ebc8c52d8\",\"accessKey\":\"900b38f00bb77813\",\"vendor\":\"pospal\",\"sign\":\"6bda6a6259b6866dc305aab7acfcd6d5\",\"storeId\":\"DQ411007\",\"actionName\":\"candao.order.postDineInOrder\",\"timestamp\":1612097846974}";

		//postOldOrder("http://zt_qc.can-dao.com:81/api", json, "2b4449d3603b9b7e", "3057147bfa1749bd9e1d51ff18635cdd");//pos测试订单
		//批量取消订单
		// getOrderCancel("资讯上传018机号的小程序订单");
		//String jason="{\"accessKey\":\"900b38f00bb77813\",\"actionName\":\"candao.order.postDineInOrder\",\"timestamp\":1606185205085,\"ticket\":\"c57c2739-2480-492e-b973-c7180eea1020\",\"sign\":\"9c8d6eae759adbd7d06b3a1d4e5df9c2\",\"serviceType\":\"pos\",\"vendor\":\"pospal\",\"storeId\":\"DQ999999\",\"data\":{\"merchantBearPrice\":-29.9,\"orderType\":3,\"orderId\":\"202011241033196250001:4196816\",\"counts\":0,\"discountPrice\":0.0,\"orderStatus\":100,\"originPrice\":0.0,\"posOrderType\":\"1\",\"products\":[],\"orderTime\":\"2020-11-24 10:33:22\",\"payType\":1,\"fromType\":\"pos\",\"price\":29.9,\"merchantPrice\":29.9,\"commission\":0.0,\"paymentDetails\":[{\"posName\":\"现金\",\"payType\":1,\"money\":29.9,\"typeName\":\"现金\",\"type\":1,\"posType\":\"1\"}],\"thirdSn\":\"65605827362070261\",\"mealFee\":0.0,\"storeId\":\"DQ999999\",\"deliveryFee\":0.0,\"thirdPlatformBearPrice\":0.0,\"isPayed\":true,\"orderDate\":\"2020-11-24\",\"productPrice\":0.0,\"status\":[{\"dateTime\":\"2020-11-24 10:33:25\",\"title\":\"支付完成（快餐）\",\"value\":\"115\"}]}}";
		//postOldOrder(jason);	
		//postOldOrder(totalNum, "900b38f00bb77813", "be5c7db333dfc6dab19afdab2055ebe3");//银豹？
		//postOldOrder(json, "4ca533f4b7f5da07", "2482df65c5ecb83be8166805e9dc5c3b");//NCR
		
	}

}
