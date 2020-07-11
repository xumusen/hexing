package com.ddl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestUpdateOneTableToAnother {
	static String mysqlUrl = "jdbc:mysql://30.16.9.32:3306/mario?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull";
	static String mysqlUser = "mario";
	static String mysqlPassword = "Paic!@#123";
	static String mysqlDriverName = "com.mysql.jdbc.Driver";

	// static String
	// mysqlUrl="jdbc:mysql://10.20.20.176:3306/mario?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull";
	// static String mysqlUser="mario";
	// static String mysqlPassword="mario";
	// static String mysqlDriverName="com.mysql.jdbc.Driver";

	public static void main(String[] args) {

		List<Map<String, Object>> mo_paf_indexmid_indexnameList = null;
		String selectSql = "select index_id,index_name from mo_paf_indexmid_indexname";
		// String selectSql="select index_id,index_name from mo_indexes limit 2";
		mo_paf_indexmid_indexnameList = QueryDBHelperList(mysqlUrl, mysqlDriverName, mysqlUser, mysqlPassword,
				selectSql);

		for (int i = 0; i < mo_paf_indexmid_indexnameList.size(); i++) {
			String index_name = mo_paf_indexmid_indexnameList.get(i).get("index_name").toString();
			int index_id = Integer.parseInt(mo_paf_indexmid_indexnameList.get(i).get("index_id").toString());
			// String TempSql="update mo_indexes set index_name = '%s' where hash_key like
			// 'PAF_%' and index_id = %d";
			String TempSql = "update mo_indexes set index_name = '" + index_name
					+ "' where hash_key like 'PAF_%' and index_id = " + String.valueOf(index_id);
			System.out.println("index_name:" + index_name);
			System.out.println("index_id:" + index_id);
			// String updateSql=String.format(TempSql,index_name,index_id);
			String updateSql = TempSql;
			System.out.println("updateSql:" + updateSql);
			int count = UpdateDBHelperCount(mysqlUrl, mysqlDriverName, mysqlUser, mysqlPassword, updateSql);
			System.out.println("已经更新了--------：" + count);
			System.out.println("------------------------");

		}
	}

	/**
	 * 操作mysql 返回 IndexJsonBean的list
	 */

	public static List<Map<String, Object>> QueryDBHelperList(String url, String driverName, String user,
			String password, String mysql) {

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet ret = null;
		// 构造泛型结果集
		List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();

		try {
			Class.forName(driverName);// 指定连接类型  

			conn = DriverManager.getConnection(url, user, password);// 获取连接  
			pst = conn.prepareStatement(mysql);// 准备执行语句  

			ret = pst.executeQuery();// 执行语句，得到结果集  

			ResultSetMetaData rsmd = pst.getMetaData();
			// 取得结果集列数
			int columnCount = rsmd.getColumnCount();
			Map<String, Object> data = null;
			// 循环结果集
			while (ret.next()) {
				data = new HashMap<String, Object>();
				// 每循环一条将列名和列值存入Map
				for (int i = 1; i <= columnCount; i++) {
					data.put(rsmd.getColumnLabel(i), ret.getObject(rsmd.getColumnLabel(i)));
				}
				// 将整条数据的Map存入到List中
				datas.add(data);
			}
		} catch (Exception e) {
			System.out.println("QueryDBHelperList error:");
			// e.printStackTrace();
		} finally {
			try {
				conn.close();
				pst.close();
			} catch (SQLException e) {
				System.out.println("QueryDBHelperList error:");
			}
			return datas;
		}
	}

	public static int UpdateDBHelperCount(String url, String driverName, String user, String password, String mysql) {

		Connection conn = null;
		PreparedStatement pst = null;
		int ret = 0;

		try {
			Class.forName(driverName);// 指定连接类型  

			conn = DriverManager.getConnection(url, user, password);// 获取连接  
			pst = conn.prepareStatement(mysql);// 准备执行语句  

			ret = pst.executeUpdate(mysql);// 执行语句，得到结果集  

		} catch (Exception e) {
			System.out.println("UpdateDBHelperCount error:");
//e.printStackTrace();  
		} finally {
			try {
				conn.close();
				pst.close();
			} catch (SQLException e) {
				System.out.println("UpdateDBHelperCount error:");
//e.printStackTrace();  
			}
		}
		return ret;
	}
}
