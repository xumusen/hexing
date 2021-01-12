package com.ddl;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;

import com.entity.OrderDiff;
import com.entity.OrderFoo;
import com.entity.OrderInfo;
import com.entity.OrderInfoDQ;
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
	
	public static void  getSeitoOrderDetail(String first,String last) throws Exception{
		OrderInfo.truncateOrderInfo();
		OrderDiff.truncateOrderDiff();
		//DataGather.getSt();
		// 3.通过数据库的连接操作数据库，实现增删改查
		Statement stmt = conn.createStatement();
		/*
		 * ResultSet rs =
		 * stmt.executeQuery("SELECT ss.分店,cs.* FROM v_compare_sum AS cs\r\n" +
		 * "LEFT JOIN seitoStore AS ss ON cs.storeid=ss.内部编号\r\n" + "WHERE\r\n" +
		 * "((cs.storeid NOT LIKE 'D%' ) OR cs.storeid IS NULL)\r\n" + "AND \r\n" +
		 * "(cs.orderdate<convert(varchar(10),getdate(),120)  OR cs.orderdate IS NULL)\r\n"
		 * + "ORDER BY cs.orderdate ASC");
		 */
		String sql="EXEC p_compare_seito '"+first+"','"+last+"'";
		ResultSet rs=stmt.executeQuery(sql);
		
		while (rs.next()) {

			String storeid = rs.getString("storeid");
			String orderdate=rs.getString("orderdate");
			System.out.println(storeid+" "+orderdate);
			//getDiffDetail(storeid, orderdate);
			GetOrderInfo.excute(storeid, orderdate);
			
		}
		System.out.println("大数据的吉野家门店记录都写入完毕，执行完毕");
	}
	
	public static void  getFooOrderDetail(String first,String last,String station) throws Exception{
		//以下代码都不对
		Statement stmt = conn.createStatement();
		String sql="";
		if(station.equals("06")) {
			sql="EXEC p_compare_seito_foo '"+first+"','"+last+"'";
			ResultSet rs=stmt.executeQuery(sql);
			while (rs.next()) {
				String storeid=rs.getString("storeid");
				String orderdate=rs.getString("orderdate");
				GetFooDetail.getFooDetail(storeid, orderdate,"006");	
			}
			System.out.println("FOO的外卖订单已经写入明细表，执行完毕");
		}if(station.equals("22")){
			sql="EXEC p_compare_seito_xiucan '"+first+"','"+last+"'";
			ResultSet rs=stmt.executeQuery(sql);
			while (rs.next()) {
				String storeid=rs.getString("storeid");
				String orderdate=rs.getString("orderdate");
				GetFooDetail.getFooDetail(storeid, orderdate,"022");	
			}
			System.out.println("FOO的小程序堂食订单已经写入明细表，执行完毕");
		}
		
	}
	
	
	public static void  compare0622towrite() throws Exception{
		
		Statement stmt = conn.createStatement();
		String sql="SELECT * FROM  v_compare_0622";
		ResultSet rs=stmt.executeQuery(sql);
		while (rs.next()) {
			String station=rs.getString("station");
			String storeid=rs.getString("storeid");
			String orderdate=rs.getString("orderdate");
			GetFooDetail.getFooDetail(storeid, orderdate,station);	
		}
		System.out.println("FOO的小程序堂食和外卖订单已经写入明细表，执行完毕");
	}
	
	
	public static void  getFooXiuCanOrderDetail(String first,String last) throws Exception{

		Statement stmt = conn.createStatement();
		String sql="EXEC p_compare_seito_xiucan '"+first+"','"+last+"'";
		ResultSet rs=stmt.executeQuery(sql);
		while (rs.next()) {
			String storeid=rs.getString("storeid");
			String orderdate=rs.getString("orderdate");
			GetFooDetail.getFooDetail(storeid, orderdate,"022");	
		}
		System.out.println("FOO的小程序堂食订单已经写入明细表，执行完毕");
	}
	
	public static void  getNcrOrderDetail(String first,String last) throws Exception{
		OrderInfoDQ.truncateOrderInfoDq();
		//OrderDiff.truncateOrderDiff();
		// 3.通过数据库的连接操作数据库，实现增删改查
		Statement stmt = conn.createStatement();
		//ResultSet rs = stmt.executeQuery("SELECT * FROM v_diffdq AS d ORDER BY d.DQorderdate,d.DQstoreid ");
		String sql="exec p_compare_ncr '"+first+"','"+last+"' ";
		ResultSet rs=stmt.executeQuery(sql);
		while (rs.next()) {
			// System.out.println(rs.getString("req"));
			// Test.postDineorder(rs.getString("req"));
			String storeid = rs.getString("storeid");
			String orderdate=rs.getString("orderdate");
			System.out.println(storeid+" "+orderdate);
			//getDiffDetail(storeid, orderdate);
			GetOrderInfoDq.excute(storeid, orderdate);
			
		}
		System.out.println("大数据的DQ门店记录都写入完毕，执行完毕");
	}
	public static void  getYbOrderDetail(String first,String last) throws Exception{
		OrderInfoDQ.truncateOrderInfoYb();
		//OrderDiff.truncateOrderDiff();
		// 3.通过数据库的连接操作数据库，实现增删改查
		Statement stmt = conn.createStatement();
		//ResultSet rs = stmt.executeQuery("SELECT * FROM v_diff_sum_yb AS dsy");
		String sql=" EXEC [p_compare_pospol] '"+first+"','"+last+"'";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			// System.out.println(rs.getString("req"));
			// Test.postDineorder(rs.getString("req"));
			String storeid = rs.getString("storeid");
			String orderdate=rs.getString("orderdate");
			System.out.println(storeid+" "+orderdate);
			//getDiffDetail(storeid, orderdate);
			GetOrderInfoDq.excuteyb(storeid, orderdate);
			
		}
		System.out.println("大数据的DQ门店记录都写入完毕，执行完毕");
	}
	public static void getDetail(String first,String last) throws Exception {
		
		//getNcrOrderDetail(first,last);
		getSeitoOrderDetail(first,last);
		getYbOrderDetail(first,last);
	}

	// 测试用例
	public static void main(String[] args) throws Exception {
		
		//getYbOrderDetail("2020-12-01","2020-12-31");
		compare0622towrite() ;
	}

}
