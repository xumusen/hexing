package com.ddl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import com.entity.TableDiff;
import com.mysql.cj.util.TimeUtil;
import com.utils.TimeUtils;

public class GetTableDiff {

	public static void getMysqlTable(String begintime, String endtime, String brandid) {
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
			ResultSet rs = stmt.executeQuery("SELECT \r\n" + "s.uniteStoreId,\r\n" + "s.orderDate,\r\n"
					+ "a.totalPrice AS \"散件及跟餐\",\r\n" + "b.totalPrice AS \"付款汇总\",\r\n"
					+ "c.totalPrice AS \"食品销售\"\r\n" + "FROM \r\n" + "(\r\n"
					+ "-- 三个表，获取全部的门店信息(去重) 只统计吉野家 6月份 -- 4北京 10007011 郑州  10006795 东北 ,26002056 DQ ，26002055  芳叔\r\n"
					+ "SELECT uniteStoreId,orderDate FROM spareparts_meal_sales \r\n"
					+ "-- WHERE  extBrandId in('4','10007011' ,'10006795','26002056')\r\n" + "WHERE  extBrandId ='"
					+ brandid + "'\r\n" + "AND orderDate >='" + begintime + "' AND orderDate <='" + endtime + "'\r\n"
					+ "GROUP BY uniteStoreId,orderDate \r\n" + "UNION \r\n" + "SELECT uniteStoreId,orderDate\r\n"
					+ "FROM payment_summary \r\n" + "WHERE  extBrandId ='" + brandid + "'\r\n" + "AND orderDate >='"
					+ begintime + "' AND orderDate <='" + endtime + "'\r\n"
					+ "GROUP BY uniteStoreId,orderDate UNION \r\n" + "SELECT uniteStoreId,orderDate \r\n"
					+ "FROM food_sales \r\n" + "WHERE  extBrandId ='" + brandid + "'\r\n" + "AND orderDate >='"
					+ begintime + "' AND orderDate <='" + endtime + "'\r\n" + "GROUP BY uniteStoreId,orderDate\r\n"
					+ ")s \r\n" + "LEFT JOIN \r\n" + "(\r\n" + "-- 散件及跟餐 \r\n"
					+ "SELECT uniteStoreId ,orderDate, SUM(totalPrice) AS totalPrice\r\n"
					+ "FROM spareparts_meal_sales \r\n" + "WHERE  extBrandId ='" + brandid + "'\r\n"
					+ "AND orderDate >='" + begintime + "' AND orderDate <='" + endtime + "'\r\n"
					+ "GROUP BY uniteStoreId,orderDate\r\n"
					+ ")a ON (s.uniteStoreId = a.uniteStoreId AND s.orderDate=a.orderDate)\r\n" + "LEFT JOIN \r\n"
					+ "(\r\n" + "-- 付款汇总 \r\n" + "SELECT uniteStoreId,orderDate,SUM(netamount) AS totalPrice\r\n"
					+ "FROM payment_summary \r\n" + "WHERE  extBrandId ='" + brandid + "'\r\n" + "AND orderDate >='"
					+ begintime + "' AND orderDate <='" + endtime + "'\r\n" + "GROUP BY uniteStoreId,orderDate\r\n"
					+ ") b ON (s.uniteStoreId = b.uniteStoreId AND s.orderDate=b.orderDate)\r\n" + "LEFT JOIN \r\n"
					+ "(\r\n" + "-- 食品销售 \r\n"
					+ "SELECT uniteStoreId,orderDate,SUM(discountedTotalPrice) AS totalPrice\r\n"
					+ "FROM food_sales WHERE extBrandId ='" + brandid + "' \r\n" + "AND orderDate >='" + begintime
					+ "' AND orderDate <='" + endtime + "'\r\n" + "GROUP BY uniteStoreId,orderDate\r\n"
					+ ")c ON (s.uniteStoreId = c.uniteStoreId AND s.orderDate=c.orderDate)\r\n" + "WHERE\r\n"
					+ "1=1\r\n" + "AND \r\n" + "(\r\n" + "-- 或 散件<>付款\r\n" + "a.totalPrice<>b.totalPrice OR \r\n"
					+ "-- 或 散件<>食品\r\n" + "a.totalPrice<>c.totalPrice OR \r\n" + "-- 或 付款<>食品\r\n"
					+ "b.totalPrice<>c.totalPrice \r\n" + ")");

			while (rs.next()) {

				TableDiff tableDiff = new TableDiff();
				tableDiff.setUniteStoreId(rs.getString("uniteStoreId"));
				tableDiff.setOrderDate(rs.getString("orderDate"));
				tableDiff.set散件及跟餐(rs.getLong("散件及跟餐"));
				tableDiff.set付款汇总(rs.getLong("付款汇总"));
				tableDiff.set食品销售(rs.getLong("食品销售"));
				TableDiff.insertTableDiff(tableDiff);
				
				String storeid=rs.getString("uniteStoreId");
				String orderdate = rs.getString("orderDate").replace("-", "");
				System.out.println(orderdate);
				//if (!storeid.equals("DQ999999"))
			//	GetOdsOrder.getdiscountdiff(storeid, orderdate);
			}
		} catch (Exception e) {
			System.out.print("get data error!");
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws SQLException {
		//String first=TimeUtils.getFirstDay("yyyy-MM-dd");
		//String last=TimeUtils.getLastDay("yyyy-MM-dd");
		//String first="2020-12-01";
		//String last="2020-12-31";
		String first=TimeUtils.getFirstDay("yyyy-MM-dd");
		String last=TimeUtils.getYesterday("yyyy-MM-dd");
		String yesterday=TimeUtils.getYesterday("yyyy-MM-dd");
		String firstpatten=first.substring(5, 7)+first.substring(8, 10);
		String lastpatten=yesterday.substring(5, 7)+yesterday.substring(8, 10);
		TableDiff.truncateTableDiff();
		System.out.println("first is "+first);
		System.out.println("last is "+last);
		GetTableDiff.getMysqlTable("2021-01-20","2021-01-31", "4");  //北京
		//GetTableDiff.getMysqlTable(first,last, "10007011"); //郑州
		//GetTableDiff.getMysqlTable(first,last, "10006795");//东北
		//GetTableDiff.getMysqlTable(first,last, "26002056");//DQ
		//GetTableDiff.getMysqlTable(first,last, "26002055");//芳叔
		
		
	}
}
