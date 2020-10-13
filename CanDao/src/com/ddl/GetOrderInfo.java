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
    	String storeid="'YS010105',	'YS010166',	'YS024025',	'YS010002',	'YS010069',	'YS010210',	'YS010014',	'YS010187',	'YS315004',	'YS010174',	'YS010195',	'YS010227',	'YS010075',	'YS010125',	'YS010180',	'YS010183',	'YS010156',	'YS010246',	'YS022015',	'YS315002',	'YS371007',	'YS010126'";
    	String begindate="2020-10-12";
    	String enddate="2020-10-12";
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
        
        System.out.println(storeid+" 已经写入了sqlserver");
    }
    
    
    public static void orderinfo(String storeid,String orderdate) throws Exception {

        String checkEveryDay="select * from test.order_info where fromtype not in ('180','247') and iscustomordernopush =TRUE and brandid in (4,10007011,10006795) \r\n" + 
        		"and storeid ='"+storeid+"' and trim(orderdate) ='"+orderdate+"'  ";
        
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
        
        System.out.println(storeid+" 已经写入了sqlserver");
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
    public static void excute(String storeid,String orderdate) throws Exception {
    	GetOrderInfo getOrderInfo=new GetOrderInfo();
		getOrderInfo.init();
		GetOrderInfo.orderinfo(storeid,orderdate);
		getOrderInfo.destory();
    }
    
    public static void main(String[] args) throws Exception {
		GetOrderInfo getOrderInfo=new GetOrderInfo();
		getOrderInfo.init();
		getOrderInfo.orderinfo();
		getOrderInfo.destory();
	}
}
