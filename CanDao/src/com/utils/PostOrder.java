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
	
	public static void  getOrderCancel() throws SQLException {
		Statement stmt = DBUtil.getConnection().createStatement();
		// ResultSet executeQuery(String sqlString)：执行查询数据库的SQL语句 ，返回一个结果集（ResultSet）对象。
		// ResultSet rs = stmt.executeQuery("SELECT * from orderCancelCollect ");
		// ResultSet rs = stmt.executeQuery("SELECT * from orderCollect ");
		ResultSet rs = stmt.executeQuery("EXEC p_compare_single_seito 'YS022025','2020-10-01'");
		while (rs.next()) {
			// System.out.println(rs.getString("req"));
			// Test.postDineorder(rs.getString("req"));
			String cell = rs.getString("extorderid");
			System.out.println(cell);
			orderCancel(cell,-1,"9月的订单误上传");
		}
		
	}
	

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		//取消单个订单
		PostOrder.orderCancel("YS010071001202010160357200012",-1,"测试单"); 
		
		//批量取消订单
		 //getOrderCancel();
		
	}

}
