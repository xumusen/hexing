package com.ddl;

import java.sql.*;
import java.util.ResourceBundle;

import com.entity.OrderDiff;

import net.sf.json.JSONObject;

public class GetOrderDiff {

	// 这里可以设置数据库名称
	/*
	 * private final static String URL =
	 * "jdbc:sqlserver://www.baofengrose.com:2433;DatabaseName=hexing"; private
	 * static final String USER="sa"; private static final String
	 * PASSWORD="Xuc2008@126.com";
	 */
	static ResourceBundle resource = ResourceBundle.getBundle("web");
	private final static String URL = resource.getString("URL");
	private static final String USER = resource.getString("USER");
	private static final String PASSWORD = resource.getString("PASSWORD");

	private static Connection conn = null;
	// 静态代码块（将加载驱动、连接数据库放入静态块中）
	static {
		try {
			// 1.加载驱动程序
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			// 2.获得数据库的连接
			conn = (Connection) DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 对外提供一个方法来获取数据库连接
	public static Connection getConnection() {
		return conn;
	}
	
	public static void getDiffDetail(String storeid,String orderdate) throws SQLException {
	
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("EXEC p_compare_single_seito '"+storeid+"','"+orderdate+"'");
		while(rs.next()) {
			OrderDiff diff=new OrderDiff();
			diff.setPosStore(rs.getString("store"));
			diff.setPosUploaddatetime(rs.getString("uploaddate"));
			diff.setPosRef(rs.getString("ref"));
			diff.setPosAmt(rs.getString("amt"));
			diff.setPosType(rs.getString("type"));
			diff.setZtStore(rs.getString("storeid"));
			diff.setZtBrandName(rs.getString("brandname"));
			diff.setZtOrderid(rs.getString("orderid"));
			diff.setZtThirdSn(rs.getString("thirdsn"));
			diff.setZtMerchantPrice(rs.getString("merchantprice"));
			diff.setZtExtorderid(rs.getString("extorderid"));
			diff.setZtOrderdate(rs.getString("orderdate"));
			OrderDiff.insertOrderDiff(diff);
		}
	}

	// 测试用例
	public static void main(String[] args) throws Exception {
		
		OrderDiff.truncateOrderDiff();
		// 3.通过数据库的连接操作数据库，实现增删改查
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT *FROM v_compare_sum AS cs WHERE\r\n" + 
				"((cs.storeid NOT LIKE 'D%' ) OR cs.storeid IS NULL)\r\n" + 
				"AND \r\n" + 
				"(cs.orderdate<convert(varchar(10),getdate(),120)  OR cs.orderdate IS NULL)");
		while (rs.next()) {
			// System.out.println(rs.getString("req"));
			// Test.postDineorder(rs.getString("req"));
			String storeid = rs.getString("storeid");
			String orderdate=rs.getString("orderdate");
			System.out.println(storeid+" "+orderdate);
			getDiffDetail(storeid, orderdate);
		}
		System.out.println("所有的门店异常记录都写入完毕");
	}

}
