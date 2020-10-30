package com.ddl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.entity.OrderInfo;
import com.entity.OrderInfoDQ;
import com.entity.OrderInfoSum;
import com.entity.OrderInfoSumWm;

import net.sf.json.JSONObject;

public class GetOrderInfoSumWm {
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
    
    public void orderinfoWm() throws Exception {
    	OrderInfoSumWm.truncateOrderInfoSumWm();
    	
      //  String sql = "show databases";
      //  String order_info="select * from test.order_info limit 10";
    	 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
		   Calendar c = Calendar.getInstance();    
	        c.add(Calendar.MONTH, 0);
	        c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
	        String first = format.format(c.getTime());
	        System.out.println("===============first:"+first);
	        Calendar ca = Calendar.getInstance();    
	        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));  
	        String last = format.format(ca.getTime());
	        System.out.println("===============last:"+last);
       String checkEveryDay="select storeid,count(orderid) as TC , sum(merchantprice) as price,orderdate  from test.order_info \r\n" + 
       		"where trim(orderdate) >='"+first+"' and trim(orderdate) <='"+last+"'   \r\n" + 
       		" and iscustomordernopush =false\r\n" + 
       		"and brandid in (4,10007011,10006795)\r\n" + 
       		"group by storeid ,orderdate \r\n" + 
       		"UNION \r\n" + 
       		"select storeid,count(orderid) as TC ,  sum(merchantprice) as price,orderdate  from test.order_info \r\n" + 
       		"where  trim(orderdate) >='"+first+"' and trim(orderdate) <='"+last+"'   \r\n" + 
       		"and brandid in (26002055,26002056)\r\n" + 
       		"group by storeid ,orderdate";
       
       // System.out.println(checkEveryDay);
        //System.out.println("Running: " + sql);
        rs = stmt.executeQuery(checkEveryDay);
        while (rs.next()) {
        	OrderInfoSumWm orderInfoSumWm=new OrderInfoSumWm();
        	orderInfoSumWm.setStoreid(rs.getString("storeid"));
        	orderInfoSumWm.setTC(rs.getInt("tc"));
        	orderInfoSumWm.setPrice(rs.getFloat("price"));
        	orderInfoSumWm.setOrderdate(rs.getString("orderdate"));
        	OrderInfoSumWm.insertOrderInfoSumWm(orderInfoSumWm);
        	}
        System.out.println("当前月的外卖销售数据已经写入了sqlserver，执行完毕");
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
		GetOrderInfoSumWm getOrderInfoWm=new GetOrderInfoSumWm();
		getOrderInfoWm.init();
		getOrderInfoWm.orderinfoWm();
		getOrderInfoWm.destory();
	}
}
