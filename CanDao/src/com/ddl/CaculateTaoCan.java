package com.ddl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import com.entity.product.Combos;
import com.entity.product.Products;
import com.entity.product.Propertys;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class CaculateTaoCan {
	
	public static void main(String args[]) {
		try {
			// Class.forName("com.mysql.jdbc.Driver"); // 加载MYSQL JDBC驱动程序
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
			/*
			 * Connection connect = DriverManager.getConnection( //
			 * "jdbc:mysql://192.168.5.144:3306/candao?serverTimezone=UTC","candao",
			 * "xwumpmysql,./"); mysqlconn);
			 */
			Connection connect = DriverManager.getConnection(mysqlconn, mysqluser, mysqlpassword);
			// 连接URL为 jdbc:mysql//服务器地址/数据库名 ，后面的2个参数分别是登陆用户名和密码

			System.out.println("Success connect Mysql server!");
			Statement stmt = connect.createStatement();
			// ResultSet rs = stmt.executeQuery("SELECT * FROM api_log WHERE rsp LIKE
			// '%OK%'");
			stmt.setFetchSize(Integer.MIN_VALUE);
			//ResultSet rs = stmt.executeQuery("	SELECT * FROM log0325	WHERE log_time<'2020-03-25 08:00:00' AND msg LIKE '%782b5654d5f7a26e%'");
			ResultSet rs = stmt.executeQuery("	SELECT * FROM product_taocan0415 	");
			
			// user 为你表的名称
			while (rs.next()) {
				// System.out.println(rs.getString("req"));
				// Test.postDineorder(rs.getString("req"));
				String combos = rs.getString("combos");
				String order_id=rs.getString("order_id");
				String pid=rs.getString("pid");
				String name=rs.getString("name");
				int num=rs.getInt("num");
				float price=rs.getFloat("price");
				String accesskey=rs.getString("access_key");
				postDineorder(order_id,combos,pid,name,accesskey);

	
			}
		} catch (Exception e) {
			System.out.print("get data error!");
			e.printStackTrace();
		}
	}
	
	public static void postDineorder(String orderId,String combos,String pid,String fname,String accesskey) throws SQLException {
		JSONArray comboses = JSONArray.fromObject(combos);
		for (int i=0;i<comboses.size();i++) {
			Combos combos2=(Combos) JSONObject.toBean(comboses.getJSONObject(i),Combos.class);
			Combos.insertCombos(orderId,combos2,pid,"","","",fname,accesskey);
		}
	
	}
	
	

}
