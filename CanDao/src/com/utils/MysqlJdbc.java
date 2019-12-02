package com.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import com.ddl.CancelOrder;
import com.ddl.Test;
import com.ddl.Test2;

import net.sf.json.JSONObject;

public class MysqlJdbc {
	public static void main(String args[]) {
		try {
			//Class.forName("com.mysql.jdbc.Driver"); // 加载MYSQL JDBC驱动程序
			// Class.forName("org.gjt.mm.mysql.Driver");
			 Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Success loading Mysql Driver!");
		} catch (Exception e) {
			System.out.print("Error loading Mysql Driver!");
			e.printStackTrace();
		}
		try {
			ResourceBundle resource = ResourceBundle.getBundle("web");
			String mysqlconn = resource.getString("mysqlconn");
			String mysqluser = resource.getString("mysqluser");
			String mysqlpassword = resource.getString("mysqlpassword");
		/*	Connection connect = DriverManager.getConnection(
					// "jdbc:mysql://192.168.5.144:3306/candao?serverTimezone=UTC","candao","xwumpmysql,./");
					mysqlconn);*/
			Connection connect=DriverManager.getConnection(mysqlconn,mysqluser,mysqlpassword);
			// 连接URL为 jdbc:mysql//服务器地址/数据库名 ，后面的2个参数分别是登陆用户名和密码

			System.out.println("Success connect Mysql server!");
			Statement stmt = connect.createStatement();
			//ResultSet rs = stmt.executeQuery("SELECT * FROM api_log WHERE rsp LIKE '%OK%'");
			ResultSet rs = stmt.executeQuery("select *  from orderhistory");
			// user 为你表的名称
			while (rs.next()) {
				// System.out.println(rs.getString("req"));
				//Test.postDineorder(rs.getString("req"));
				 String cell=rs.getString("业务json数据");
				JSONObject jsonobj = JSONObject.fromObject(cell);// 将字符串转化成json对象
				//JSONObject jsonobject = JSONObject.fromObject(jsonobj.getString("data"));// 将字符串转化成json对象
				String actionName=jsonobj.getString("actionName");
				if(actionName.equals("candao.order.postDineInOrder")){
					Test.postDineorder(cell);
				}else if(actionName.equals("candao.retail.order")){
					Test2.retailOrder(cell);
				}else if(actionName.equals("candao.order.postDineInStatus")){
					CancelOrder.CancelOrder(cell);
				}else if(actionName.equals("candao.retail.updateOrderStatus")){
					System.out.println("更新新零售订单状态啦啦啦啦啦");
				}
				
			}
		} catch (Exception e) {
			System.out.print("get data error!");
			e.printStackTrace();
		}
	}
}
