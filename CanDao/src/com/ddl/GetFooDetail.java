package com.ddl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import com.entity.OrderFoo;
import com.entity.OrderInfoSum;

import net.sf.json.JSONObject;

public class GetFooDetail {
	
	
	public static void getFooDetail(String storeid,String orderdate) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Success loading Mysql Driver!");
		} catch (Exception e) {
			System.out.print("Error loading Mysql Driver!");
			e.printStackTrace();
		}
		try {
			ResourceBundle resource = ResourceBundle.getBundle("web");
			String mysqlconn = resource.getString("jdeconn");
			String mysqluser = resource.getString("jdeuser");
			String mysqlpassword = resource.getString("jdepassword");
			Connection connect = DriverManager.getConnection(mysqlconn, mysqluser, mysqlpassword);
			System.out.println("Success connect Mysql server!");
			Statement stmt = connect.createStatement();
			stmt.setFetchSize(Integer.MIN_VALUE);
			ResultSet rs = stmt.executeQuery("SELECT uniteStoreId AS storeid,extOrderId, originalPrice/100 AS originalPrice,merchantBearPrice/100 AS merchantBearPrice,billDate,(originalPrice-merchantBearPrice)/100 AS caculateMerchanPrice FROM orderdetail\r\n" + 
					"WHERE billDate='"+orderdate+"' \r\n" + 
					"AND uniteStoreId='"+storeid+"'\r\n" + 
					"AND orderStatus=100\r\n" + 
					"AND isCustomOrderNoPush=0\r\n" + 
					"ORDER BY uniteStoreId,billDate");
			
			while (rs.next()) {
				OrderFoo orderfoo=new OrderFoo();
				orderfoo.setStoreid(rs.getString("storeid"));
				orderfoo.setExtOrderId(rs.getString("extOrderId"));
				orderfoo.setOriginalPrice(rs.getString("originalPrice"));
				orderfoo.setMerchantBearPrice(rs.getString("merchantBearPrice"));
				orderfoo.setBillDate(rs.getString("billDate"));
				orderfoo.setCaculateMerchanPrice(rs.getString("caculateMerchanPrice"));			
				OrderFoo.insertFooOrder(orderfoo);	
			}
		} catch (Exception e) {
			System.out.print("get data error!");
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		getFooDetail("YS010199","2020-12-19");
		
	}

}
