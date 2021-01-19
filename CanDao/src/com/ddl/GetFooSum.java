package com.ddl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import com.entity.OrderInfoSum;

import net.sf.json.JSONObject;

public class GetFooSum {
	
	
	public static void getFooSum(String begindate,String lastdate,String storeid,String station) {
		
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
			if(station.equals("06")) {
				OrderInfoSum.truncateOrderInfoFoo( begindate, lastdate, storeid, station);//删除原有数据
				String select="SELECT uniteStoreId AS storeid,COUNT(1)AS tc, SUM((\r\n" + 
						"CASE\r\n" + 
						" WHEN isThirdDistribute=0 THEN (originalPrice-merchantBearPrice)/100  \r\n" + 
						" WHEN isThirdDistribute=1 THEN (originalPrice-merchantBearPrice-deliveryFee)/100 \r\n" + 
						" END )) AS price , billDate AS orderdate FROM orderdetail ";
				String where="";
				if (storeid.equals("")) 
				 where="WHERE billDate>='"+begindate+"' AND billDate<='"+lastdate+"'\r\n" + 
						"AND orderStatus=100\r\n" + 
						"AND isCustomOrderNoPush=0";
				else 
				 where ="WHERE billDate>='"+begindate+"' AND billDate<='"+lastdate+"'\r\n" + 
				 		"AND orderStatus=100\r\n" + 
				 		"AND isCustomOrderNoPush=0\r\n" + 
				 		"AND uniteStoreId='"+storeid+"'";
				String group=" GROUP BY uniteStoreId,billDate\r\n" + 
						"ORDER BY uniteStoreId,billDate";
				String runningsql=select+where+group; 
				System.out.println(runningsql);
				
				  ResultSet rs = stmt.executeQuery(runningsql);
				  
				  
				  while (rs.next()) {
				  
				  OrderInfoSum orderInfoSum=new OrderInfoSum();
				  orderInfoSum.setStation("06");
				  orderInfoSum.setStoreid(rs.getString("storeid"));
				  orderInfoSum.setTC(rs.getInt("tc"));
				  orderInfoSum.setPrice(rs.getFloat("price"));
				  orderInfoSum.setOrderdate(rs.getString("orderdate"));
				  OrderInfoSum.insertOrderInfoFoo(orderInfoSum); }
			}if(station.equals("22")) {
				OrderInfoSum.truncateOrderInfoFoo( begindate, lastdate, storeid, station);//删除原有数据
				String select="SELECT uniteStoreId AS storeid,COUNT(1)AS tc, SUM(\r\n" + 
						"merchantPrice)/100 AS price , billDate AS orderdate FROM orderdetail ";
				String where="";
				if (storeid.equals("")) 
				 where="WHERE billDate>='"+begindate+"' AND billDate<='"+lastdate+"'\r\n" + 
				 		"AND orderStatus=100\r\n" + 
				 		"AND isCustomOrderNoPush=1\r\n" + 
				 		"AND fromType=180 ";
				else 
				 where ="WHERE billDate>='"+begindate+"' AND billDate<='"+lastdate+"'\r\n" + 
				 		"AND orderStatus=100\r\n" + 
				 		"AND isCustomOrderNoPush=1\r\n" + 
				 		"AND fromType=180\r\n" + 
				 		"AND uniteStoreId='"+storeid+"' ";
				String group=" GROUP BY uniteStoreId,billDate\r\n" + 
						"ORDER BY uniteStoreId,billDate";
				String runningsql=select+where+group; 
				System.out.println(runningsql);
				
				  ResultSet rs = stmt.executeQuery(runningsql);
				  
				  
				  while (rs.next()) {
				  
				  OrderInfoSum orderInfoSum=new OrderInfoSum();
				  orderInfoSum.setStation("22");
				  orderInfoSum.setStoreid(rs.getString("storeid"));
				  orderInfoSum.setTC(rs.getInt("tc"));
				  orderInfoSum.setPrice(rs.getFloat("price"));
				  orderInfoSum.setOrderdate(rs.getString("orderdate"));
				  OrderInfoSum.insertOrderInfoFoo(orderInfoSum); }
			}			 
		} catch (Exception e) {
			System.out.print("get data error!");
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		OrderInfoSum.truncateOrderInfoFoo("2020-12-01", "2020-12-31", "", "06");
		OrderInfoSum.truncateOrderInfoFoo("2020-12-01", "2020-12-31", "", "22");
		getFooSum("2020-12-01","2020-12-31","YS010106","22");  
		getFooSum("2020-12-01","2020-12-31","YS010106","06");  
		//getFooSum("2020-12-30","2020-12-30","YS010199","022");  //读取中台外卖订单
		//getFooSum("2020-12-30","2020-12-30","YS010199","006");  //读取中台外卖订单
	}

}
