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
	
	    public Statement  init() throws Exception {
	        Class.forName(driverName);
	        conn = DriverManager.getConnection(url,user,password);
	        stmt = conn.createStatement();
	        return stmt;
	    }
	    
	    public void getOdsOrder() throws Exception {
	      //  String sql = "show databases";
	      //  String order_info="select * from test.order_info limit 10";
	        String checkEveryDay="select * from middle_log.ods_log20201007" ;//+ 
	        	//	"where action_name ='candao.order.postDineInOrder'  ";
	        
	        //System.out.println("Running: " + sql);
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
	    public static void main(String[] args) throws Exception {
			GetOdsOrder testhive=new GetOdsOrder();
			testhive.init();
			testhive.getOdsOrder();
			testhive.destory();
		}
}
