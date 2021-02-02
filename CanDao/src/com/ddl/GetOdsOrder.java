package com.ddl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import net.sf.json.JSONObject;

public class GetOdsOrder {

	private static String driverName = "org.apache.hive.jdbc.HiveDriver";
	private static String url = "jdbc:hive2://192.168.212.2:10000";
	private static String user = "hive";
	private static String password = "";

	private static Connection conn = null;
	private static Statement stmt = null;
	private static ResultSet rs = null;

	// 加载驱动、创建连接

	public Statement init() throws Exception {
		Class.forName(driverName);
		conn = DriverManager.getConnection(url, user, password);
		stmt = conn.createStatement();
		return stmt;
	}

	public void getOdsOrder() throws Exception {
		// String sql = "show databases";
		// String order_info="select * from test.order_info limit 10";
		String checkEveryDay = "select * from middle_log.ods_log20201004 ";// +
		// "where action_name ='candao.order.postDineInOrder' ";

		// System.out.println("Running: " + sql);
		rs = stmt.executeQuery(checkEveryDay);
		while (rs.next()) {
			String cell = rs.getString("msg");
			System.out.println(cell);
			JSONObject jsonobj = JSONObject.fromObject(cell);// 将字符串转化成json对象
			String actionName = jsonobj.getString("actionName");
			if (actionName.equals("candao.order.postDineInOrder")) {
				Test.postDineorder(cell);
			} else if (actionName.equals("candao.retail.order")) {
				Test2.retailOrder(cell);
			} else if (actionName.equals("candao.order.postDineInStatus")) {
				CancelOrder.CancelOrder(cell);
			} else if (actionName.equals("candao.retail.updateOrderStatus")) {
				System.out.println("更新新零售订单状态啦啦啦啦啦");
			}
		}
	}

	public void getOdsOrder(String storeid, String orderdate,String datetime) throws Exception {
		// String sql = "show databases";
		// String order_info="select * from test.order_info limit 10";
		String checkEveryDay = "select * from middle_log.ods_log" + orderdate + " where msg like '%" + storeid + "%' and proc_time >='"+datetime+"' ";// +
		// "where action_name ='candao.order.postDineInOrder' ";

		// System.out.println("Running: " + sql);
		rs = stmt.executeQuery(checkEveryDay);
		while (rs.next()) {
			String cell = rs.getString("msg");
//不打印sql			System.out.println(cell);
			JSONObject jsonobj = JSONObject.fromObject(cell);// 将字符串转化成json对象
			String actionName = jsonobj.getString("actionName");
			if (actionName.equals("candao.order.postDineInOrder")) {
				Test.postDineorder(cell);
			} else if (actionName.equals("candao.retail.order")) {
				Test2.retailOrder(cell);
			} else if (actionName.equals("candao.order.postDineInStatus")) {
				CancelOrder.CancelOrder(cell);
			} else if (actionName.equals("candao.retail.updateOrderStatus")) {
				System.out.println("更新新零售订单状态啦啦啦啦啦");
			}
		}
	}

	public void getOdsOrder(String storeid, String orderdate) throws Exception {
		// String sql = "show databases";
		// String order_info="select * from test.order_info limit 10";
		String checkEveryDay = "select * from middle_log.ods_log" + orderdate + " where msg like '%" + storeid + "%'  ";// +
		// "where action_name ='candao.order.postDineInOrder' ";

		// System.out.println("Running: " + sql);
		rs = stmt.executeQuery(checkEveryDay);
		while (rs.next()) {
			String cell = rs.getString("msg");
			System.out.println(cell);
			JSONObject jsonobj = JSONObject.fromObject(cell);// 将字符串转化成json对象
			String actionName = jsonobj.getString("actionName");
			if (actionName.equals("candao.order.postDineInOrder")) {
				Test.postDineorder(cell);
			} else if (actionName.equals("candao.retail.order")) {
				Test2.retailOrder(cell);
			} else if (actionName.equals("candao.order.postDineInStatus")) {
				CancelOrder.CancelOrder(cell);
			} else if (actionName.equals("candao.retail.updateOrderStatus")) {
				System.out.println("更新新零售订单状态啦啦啦啦啦");
			}
		}
	}


	public void destory() throws Exception {
		if (rs != null) {
			rs.close();
		}
		if (stmt != null) {
			stmt.close();
		}
		if (conn != null) {
			conn.close();
		}
	}

	public static void getdiscountdiff(String storeid, String orderdate) throws Exception {
		GetOdsOrder testhive = new GetOdsOrder();
		testhive.init();
		testhive.getOdsOrder(storeid, orderdate);
		testhive.destory();
	}

	public static void main(String[] args) throws Exception {
		GetOdsOrder testhive = new GetOdsOrder();
		testhive.init();
		// testhive.getOdsOrder();
		//testhive.getOdsOrder("pospal", "20201224","2020-12-24 15:00:00");
		//782b5654d5f7a26e  北京吉野家
		//fb4fda21afeddcf4     东北吉野家
		//c19e41576f61aafd  郑州吉野家
		
		/*
		 * testhive.getOdsOrder("none-code", "20210101","2021-01-01 00:00:00");
		 * testhive.getOdsOrder("none-code", "20210102","2021-01-02 00:00:00");
		 * testhive.getOdsOrder("none-code", "20210103","2021-01-03 00:00:00");
		 * testhive.getOdsOrder("none-code", "20210104","2021-01-04 00:00:00");
		 * testhive.getOdsOrder("none-code", "20210105","2021-01-05 00:00:00");
		 * testhive.getOdsOrder("none-code", "20210106","2021-01-06 00:00:00");
		 * testhive.getOdsOrder("none-code", "20210107","2021-01-07 00:00:00");
		 * testhive.getOdsOrder("none-code", "20210108","2021-01-08 00:00:00");
		 * testhive.getOdsOrder("none-code", "20210109","2021-01-09 00:00:00");
		 * testhive.getOdsOrder("none-code", "20210110","2021-01-10 00:00:00");
		 * testhive.getOdsOrder("none-code", "20210111","2021-01-11 00:00:00");
		 * testhive.getOdsOrder("none-code", "20210112","2021-01-12 00:00:00");
		 * testhive.getOdsOrder("none-code", "20210113","2021-01-13 00:00:00");
		 * testhive.getOdsOrder("none-code", "20210114","2021-01-14 00:00:00");
		 * testhive.getOdsOrder("none-code", "20210115","2021-01-15 00:00:00");
		 * 
		 * 
		 * testhive.getOdsOrder("none-code", "20210116","2021-01-16 00:00:00");
		 * testhive.getOdsOrder("none-code", "20210117","2021-01-17 00:00:00");
		 * testhive.getOdsOrder("none-code", "20210118","2021-01-18 00:00:00");
		 * testhive.getOdsOrder("none-code", "20210119","2021-01-19 00:00:00");
		 * testhive.getOdsOrder("none-code", "20210120","2021-01-20 00:00:00");
		 * testhive.getOdsOrder("none-code", "20210121","2021-01-21 00:00:00");
		 * testhive.getOdsOrder("none-code", "20210122","2021-01-22 00:00:00");
		 * testhive.getOdsOrder("none-code", "20210123","2021-01-23 00:00:00");
		 * testhive.getOdsOrder("none-code", "20210124","2021-01-24 00:00:00");
		 * testhive.getOdsOrder("none-code", "20210125","2021-01-25 00:00:00");
		 * testhive.getOdsOrder("none-code", "20210126","2021-01-26 00:00:00");
		 * testhive.getOdsOrder("none-code", "20210127","2021-01-27 00:00:00");
		 * testhive.getOdsOrder("none-code", "20210128","2021-01-28 00:00:00");
		 * testhive.getOdsOrder("none-code", "20210129","2021-01-29 00:00:00");
		 * testhive.getOdsOrder("none-code", "20210130","2021-01-30 00:00:00");
		 * testhive.getOdsOrder("none-code", "20210131","2021-01-31 00:00:00");
		 */
		 
		//testhive.getOdsOrder("YS471010", "20210131", "2021-01-31 00:00:00");
		//testhive.getOdsOrder("YS010196", "20210201", "2021-02-01 00:00:00");
		//testhive.getOdsOrder("YS022003", "20210131", "2021-01-31 00:00:00");
		testhive.getOdsOrder("DQ022028", "20210102", "2021-01-02 00:00:00");
		testhive.destory();
	}
}
