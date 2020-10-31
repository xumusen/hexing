package com.ddl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import com.ddl.CancelOrder;
import com.ddl.Test;
import com.ddl.Test2;
import com.entity.DiffDiscount;
import com.entity.TableDiff;

import net.sf.json.JSONObject;

public class GetDiscountDiff {
	public static void getdiscountdiff() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Success loading Mysql Driver!");
		} catch (Exception e) {
			System.out.print("Error loading Mysql Driver!");
			e.printStackTrace();
		}
		try {
			ResourceBundle resource = ResourceBundle.getBundle("web");
			String JDE_CONN = resource.getString("JDE_CONN");
			String JDE_USER = resource.getString("JDE_USER");
			String JDE_PASSWORD = resource.getString("JDE_PASSWORD");
			Connection connect = DriverManager.getConnection(JDE_CONN, JDE_USER, JDE_PASSWORD);
			// 连接URL为 jdbc:mysql//服务器地址/数据库名 ，后面的2个参数分别是登陆用户名和密码

			System.out.println("Success connect Mysql server!");
			Statement stmt = connect.createStatement();

			stmt.setFetchSize(Integer.MIN_VALUE);
			ResultSet rs = stmt.executeQuery("SELECT t1.uniteStoreId,t1.orderDate,t1.注册,t1.现金,t1.`注册-现金`,t2.优惠,t1.`注册-现金` -t2.优惠 as 注册现金优惠差值 FROM\r\n" + 
					"( SELECT uniteStoreId,orderDate,SUM(undiscountedTotalPrice) as 注册,SUM(discountedTotalPrice) as 现金,SUM(undiscountedTotalPrice) -SUM(discountedTotalPrice) AS '注册-现金'\r\n" + 
					"FROM   `storesaledetail` \r\n" + 
					"WHERE  orderDate >= DATE_ADD(CURDATE(),INTERVAL -DAY(CURDATE())+1 DAY) AND orderDate <= LAST_DAY(CURDATE()) \r\n" + 
					"GROUP BY uniteStoreId,orderDate \r\n" + 
					"ORDER BY uniteStoreId,orderDate) t1\r\n" + 
					"LEFT JOIN \r\n" + 
					"(SELECT uniteStoreId,statDate,SUM(amount) AS '优惠' FROM   `discountinfo`\r\n" + 
					"WHERE  statDate >= DATE_ADD(CURDATE(),INTERVAL -DAY(CURDATE())+1 DAY) AND statDate <= LAST_DAY(CURDATE()) \r\n" + 
					"GROUP BY uniteStoreId,statDate\r\n" + 
					"ORDER BY uniteStoreId,statDate)t2 \r\n" + 
					"ON  t1.uniteStoreId = t2.uniteStoreId AND t1.orderDate = t2.statDate \r\n" + 
					"WHERE  t1.`注册-现金` -t2.优惠 <> 0");
			while (rs.next()) {
				DiffDiscount diffDiscount=new DiffDiscount();
				diffDiscount.setUniteStoreId(rs.getString("uniteStoreId"));
				diffDiscount.setOrderDate(rs.getString("orderDate"));
				diffDiscount.set优惠(rs.getLong("优惠"));
				diffDiscount.set注册(rs.getLong("注册"));
				diffDiscount.set现金(rs.getLong("现金"));
				diffDiscount.set注册现金差值(rs.getLong("注册-现金"));
				diffDiscount.set注册现金优惠差值(rs.getLong("注册现金优惠差值"));
				DiffDiscount.insertDiscountDiff(diffDiscount);
				
				String storeid=rs.getString("uniteStoreId");
				String orderdate = rs.getString("orderDate").replace("-", "");
				System.out.println(orderdate);
				
				GetOdsOrder.getdiscountdiff(storeid, orderdate);
			}
		} catch (Exception e) {
			System.out.print("get data error!");
			e.printStackTrace();
		}
	}
	public static void main(String args[]) throws SQLException {
		DiffDiscount.truncateDiscountDiff();
		getdiscountdiff();
	}
}
