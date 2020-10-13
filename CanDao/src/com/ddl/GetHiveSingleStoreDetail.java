package com.ddl;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;

import com.entity.OrderDiff;
import com.entity.OrderInfoSum;

import net.sf.json.JSONObject;

public class GetHiveSingleStoreDetail {
  	private static String driverName = "org.apache.hive.jdbc.HiveDriver";
    private static String url = "jdbc:hive2://192.168.212.2:10000";
    private static String user = "hive";
    private static String password = "";

    private static Connection hiveconn = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;

    // 加载驱动、创建连接

    public Statement  init() throws Exception {
        Class.forName(driverName);
        hiveconn = DriverManager.getConnection(url,user,password);
        stmt = hiveconn.createStatement();
        return stmt;
    }
    
   
    public void destory() throws Exception {
        if ( rs != null) {
            rs.close();
        }
        if (stmt != null) {
            stmt.close();
        }
        if (conn != null) {
            conn.close();
        }
    }
	/*
	 * public static void main(String[] args) throws Exception { GetOrderInfoSum
	 * getOrderInfo=new GetOrderInfoSum(); getOrderInfo.init();
	 * getOrderInfo.orderinfo(); getOrderInfo.destory(); }
	 */

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
	

	// 测试用例
	public static void main(String[] args) throws Exception {

		// 3.通过数据库的连接操作数据库，实现增删改查
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT *FROM v_compare_sum AS cs WHERE\r\n" + 
				"((cs.storeid NOT LIKE 'D%' AND cs.storeid NOT IN ('YJ314002','YJ314003','YJ010002')) OR cs.storeid IS NULL)\r\n" + 
				"AND \r\n" + 
				"(cs.orderdate<convert(varchar(10),getdate(),120)  OR cs.orderdate IS NULL)");
		while (rs.next()) {
			// System.out.println(rs.getString("req"));
			// Test.postDineorder(rs.getString("req"));
			String storeid = rs.getString("storeid");
			String orderdate=rs.getString("orderdate");
			System.out.println(storeid+" "+orderdate);
			//getDiffDetail(storeid, orderdate);
			GetOrderInfo.excute(storeid, orderdate);
			
		}
		System.out.println("大数据的门店记录都写入完毕");
	}

}