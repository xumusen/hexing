package com.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import com.ddl.Test;

public class MysqlJdbc {
	public static void main(String args[]) {
		try {
			Class.forName("com.mysql.jdbc.Driver"); // 加载MYSQL JDBC驱动程序
			// Class.forName("org.gjt.mm.mysql.Driver");
			System.out.println("Success loading Mysql Driver!");
		} catch (Exception e) {
			System.out.print("Error loading Mysql Driver!");
			e.printStackTrace();
		}
		try {
			ResourceBundle resource = ResourceBundle.getBundle("web");
			String mysqlconn = resource.getString("mysqlconn");
			Connection connect = DriverManager.getConnection(
					// "jdbc:mysql://192.168.5.144:3306/candao?serverTimezone=UTC","candao","xwumpmysql,./");
					mysqlconn);
			// 连接URL为 jdbc:mysql//服务器地址/数据库名 ，后面的2个参数分别是登陆用户名和密码

			System.out.println("Success connect Mysql server!");
			Statement stmt = connect.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM api_log WHERE rsp LIKE '%OK%'");
			// user 为你表的名称
			while (rs.next()) {
				// System.out.println(rs.getString("req"));
				Test.postDineorder(rs.getString("req"));
			}
		} catch (Exception e) {
			System.out.print("get data error!");
			e.printStackTrace();
		}
	}
}
