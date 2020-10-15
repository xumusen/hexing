package com.ddl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.entity.OrderInfo;
import com.entity.OrderInfoDQ;

import net.sf.json.JSONObject;

public class GetOrderInfoDq {
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
    	String storeid="DQ010137";
    	String begindate="2020-09-30";
    	String enddate="2020-09-30";
       String checkEveryDay="select * from test.order_info  where storeid ='"+storeid+"' and orderdate >='"+begindate+"' and orderdate <='"+enddate+"'";
        //String checkEveryDay="select * from test.order_info  where storeid in ('DQ010023','DQ010064','DQ010066','DQ010094','DQ010122','DQ010137','DQ010143','DQ010145','DQ010151','DQ022023','DQ022029','DQ022034','DQ024018') and orderdate ='2020-09-30' ";
        /*
		 * String
		 * checkEveryDay="select * from test.order_info where  trim(orderdate) ='2020-09-29' and storeid  in ('DQ010121',	'DQ010125',	'DQ022015',	'DQ427002')\r\n"
		 * + "";
		 */
        System.out.println(checkEveryDay);
        //System.out.println("Running: " + sql);
        rs = stmt.executeQuery(checkEveryDay);
        while (rs.next()) {
        	OrderInfoDQ orderInfo=new OrderInfoDQ();
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
        	OrderInfoDQ.insertOrderInfoDQ(orderInfo);
        }
        System.out.println(rs.getString("storeid")+"已经写入了sqlserver");
    }
    public static void orderinfoDq(String storeid,String orderdate) throws Exception {

        String checkEveryDay="select * from test.order_info where storeid ='"+storeid+"' and trim(orderdate) ='"+orderdate+"'  ";
        
        System.out.println("Running: " + checkEveryDay);
        rs = stmt.executeQuery(checkEveryDay);
        while (rs.next()) {
        	OrderInfoDQ orderInfo=new OrderInfoDQ();
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
        	OrderInfoDQ.insertOrderInfoDQ(orderInfo);
        	
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
    	GetOrderInfoDq getOrderInfoDq=new GetOrderInfoDq();
    	getOrderInfoDq.init();
		GetOrderInfoDq.orderinfoDq(storeid,orderdate);
		getOrderInfoDq.destory();
    }
    public static void main(String[] args) throws Exception {
		GetOrderInfoDq getOrderInfo=new GetOrderInfoDq();
		getOrderInfo.init();
		getOrderInfo.orderinfo();
		getOrderInfo.destory();
	}
}
