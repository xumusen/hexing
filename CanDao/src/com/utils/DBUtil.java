package com.utils;
import java.sql.*;
import java.util.ResourceBundle;

import com.ddl.CancelOrder;
import com.ddl.Test;
import com.ddl.Test2;
import com.ddl.Testpay;
import com.ddl.Testxc;

import net.sf.json.JSONObject;



	public class DBUtil {

	    //这里可以设置数据库名称
/*	    private final static String URL = "jdbc:sqlserver://www.baofengrose.com:2433;DatabaseName=hexing";
	    private static final String USER="sa";
	    private static final String PASSWORD="Xuc2008@126.com";*/
		static ResourceBundle resource = ResourceBundle.getBundle("web");
		private final static String URL = resource.getString("URL");
		private static final String USER = resource.getString("USER");
		private static final String PASSWORD = resource.getString("PASSWORD");
	    
	    private static Connection conn=null;
	    //静态代码块（将加载驱动、连接数据库放入静态块中）
	    static{
	        try {
	            //1.加载驱动程序
	            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	            //2.获得数据库的连接
	            conn=(Connection)DriverManager.getConnection(URL,USER,PASSWORD);
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    //对外提供一个方法来获取数据库连接
	    public static Connection getConnection(){
	        return conn;
	    }
	    
	    
	    //测试用例
	    public static void main(String[] args) throws Exception{
	        
	        //3.通过数据库的连接操作数据库，实现增删改查
	        Statement stmt = conn.createStatement();
	        //ResultSet executeQuery(String sqlString)：执行查询数据库的SQL语句   ，返回一个结果集（ResultSet）对象。
	        //ResultSet rs = stmt.executeQuery("SELECT * from orderCancelCollect ");
	        //ResultSet rs = stmt.executeQuery("SELECT * from orderCollect ");
	        ResultSet rs = stmt.executeQuery("select *  FROM xiaochengxunew");
	       // ResultSet rs = stmt.executeQuery("SELECT  x.ods_order_postdineinorder_orderid ,x.ods_order_postdineinorder_products,x.ods_order_postdineinorder_storeid,x.ods_order_postdineinorder_ordertime,x.ods_order_postdineinorder_paymentdetails, x.ods_order_postdineinorder_orderdate FROM xiaochengxu AS x");

	        while(rs.next()){//如果对象中有数据，就会循环打印出来
	        	String ods_order_postdineinorder_orderid=rs.getString("ods_order_postdineinorder_orderid");
	        	System.out.println(ods_order_postdineinorder_orderid);
	        	String ods_order_postdineinorder_products=rs.getString("ods_order_postdineinorder_products");
	        	System.out.println(ods_order_postdineinorder_products);
	        	String ods_order_postdineinorder_storeid=rs.getString("ods_order_postdineinorder_storeid");
	        	System.out.println(ods_order_postdineinorder_storeid); 
	        	//Testxc.postDineorder(ods_order_postdineinorder_orderid, ods_order_postdineinorder_storeid, ods_order_postdineinorder_products);
	        	
	        	
	        	String ods_order_postdineinorder_ordertime=rs.getString("ods_order_postdineinorder_ordertime");
	        	System.out.println(ods_order_postdineinorder_ordertime);
	        	String ods_order_postdineinorder_paymentdetails=rs.getString("ods_order_postdineinorder_paymentdetails");
	        	System.out.println(ods_order_postdineinorder_paymentdetails);
	        	String ods_order_postdineinorder_orderdate=rs.getString("ods_order_postdineinorder_orderdate");
	        	System.out.println(ods_order_postdineinorder_orderdate); 
	        	Testpay.postDineorder(ods_order_postdineinorder_orderid, ods_order_postdineinorder_storeid,ods_order_postdineinorder_paymentdetails, ods_order_postdineinorder_orderdate,ods_order_postdineinorder_ordertime);
	        	 
	        	
	        	
	        	
	           // JSONObject jsonobj = JSONObject.fromObject(cell);// 将字符串转化成json对象
				//JSONObject jsonobject = JSONObject.fromObject(jsonobj.getString("data"));// 将字符串转化成json对象
				//String actionName=jsonobj.getString("actionName");
			/*
			 * if(actionName.equals("candao.order.postDineInOrder")){
			 * Test.postDineorder(cell); }else if(actionName.equals("candao.retail.order")){
			 * Test2.retailOrder(cell); }else
			 * if(actionName.equals("candao.order.postDineInStatus")){
			 * CancelOrder.CancelOrder(cell); }else
			 * if(actionName.equals("candao.retail.updateOrderStatus")){
			 * System.out.println("更新新零售订单状态啦啦啦啦啦"); }
			 */
	        }
	        
	        
	    }



}
