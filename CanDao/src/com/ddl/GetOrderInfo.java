package com.ddl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.entity.OrderInfo;

import net.sf.json.JSONObject;

public class GetOrderInfo {
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
    
    public void orderinfo() throws Exception {
      //  String sql = "show databases";
      //  String order_info="select * from test.order_info limit 10";
    	String storeid="'UF755002',	'UF020013',	'UF020020',	'YS371002',	'UF020003',	'UF020028',	'YS022027',	'YS451022',	'UF020006',	'UF020012',	'UF020016',	'YS024025',	'UF020002',	'UF020014',	'UF020001',	'UF020008'";
    	String begindate="2020-10-11";
    	String enddate="2020-10-11";
        String checkEveryDay="select *from  test.order_info  where fromtype not in ('180','247') and iscustomordernopush =TRUE and "
        		+ "storeid in ("+storeid+") and "
        		+ "trim(orderdate)>='"+begindate+"' and trim(orderdate)<='"+enddate+"'";
        
        System.out.println("Running: " + checkEveryDay);
        rs = stmt.executeQuery(checkEveryDay);
        while (rs.next()) {
        	OrderInfo orderInfo=new OrderInfo();
        	orderInfo.setStoreid(rs.getString("storeid"));
        	orderInfo.setBrandid(rs.getFloat("brandid"));
        	orderInfo.setBrandname(rs.getString("brandname"));
        	orderInfo.setOrderid(rs.getString("orderid"));

        	orderInfo.setThirdsn(rs.getString("thirdsn"));
        	orderInfo.setRegisteredamount(rs.getString("registeredamount"));
        	orderInfo.setIscustomordernopush(rs.getBoolean("iscustomordernopush"));
        	orderInfo.setRealtimeproductprice(rs.getString("realtimeproductprice"));

        	orderInfo.setMealfee(rs.getString("mealfee"));
        	orderInfo.setDeliveryfee(rs.getString("deliveryfee"));
        	orderInfo.setFromtype(rs.getString("fromtype"));
        	orderInfo.setMerchantprice(rs.getString("merchantprice"));
        	orderInfo.setExtorderid(rs.getString("extorderid"));
        	orderInfo.setFromname(rs.getString("fromname"));
        	orderInfo.setFlag(rs.getString("flag"));
        	orderInfo.setProvinceid(rs.getString("provinceid"));

        	orderInfo.setProvincename(rs.getString("provincename"));
        	orderInfo.setCityid(rs.getString("cityid"));
        	orderInfo.setCityname(rs.getString("cityname"));
        	orderInfo.setCdbrandid(rs.getString("cdbrandid"));

        	orderInfo.setCdbrandname(rs.getString("cdbrandname"));
        	orderInfo.setCdplatformkey(rs.getString("cdplatformkey"));
        	orderInfo.setCdplatformname(rs.getString("cdplatformname"));
        	orderInfo.setPaasstoreid(rs.getString("paasstoreid"));
        	orderInfo.setCdstorename(rs.getString("cdstorename"));
        	orderInfo.setExtstoreid(rs.getString("extstoreid"));
        	orderInfo.setExtstorename(rs.getString("extstorename"));
        	orderInfo.setOrderdate(rs.getString("orderdate"));

        	orderInfo.setTc("");
        	orderInfo.setPrice("");
        	//System.out.println(rs.getString("extorderid"));
        	OrderInfo.insertOrderInfo(orderInfo);
        	
        }
        
        System.out.println(rs.getString("storeid")+"已经写入了sqlserver");
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
		GetOrderInfo getOrderInfo=new GetOrderInfo();
		getOrderInfo.init();
		getOrderInfo.orderinfo();
		getOrderInfo.destory();
	}
}
