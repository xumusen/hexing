package com.entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.utils.DBUtil;

public class OrderInfoSum {


String storeid;
int TC;
float price;
String orderdate;
String station;



public String getStation() {
	return station;
}



public void setStation(String station) {
	this.station = station;
}



public String getStoreid() {
	return storeid;
}



public void setStoreid(String storeid) {
	this.storeid = storeid;
}



public int getTC() {
	return TC;
}



public void setTC(int tC) {
	TC = tC;
}



public float getPrice() {
	return price;
}



public void setPrice(float price) {
	this.price = price;
}



public String getOrderdate() {
	return orderdate;
}



public void setOrderdate(String orderdate) {
	this.orderdate = orderdate;
}



public static void insertOrderInfoSum(OrderInfoSum orderinfosum)throws SQLException{
  
    Connection conn=DBUtil.getConnection();
   String sql="INSERT INTO dbo.order_info_sum\r\n" + 
   		"           (storeid\r\n" + 
   		"           ,tc\r\n" + 
   		"           ,price\r\n" + 
   		"           ,orderdate)\r\n" + 
   		"     VALUES(?,?,?,?)";
   
    PreparedStatement psmt = conn.prepareStatement(sql);
    //先对应SQL语句，给SQL语句传递参数
    psmt.setString(1,  orderinfosum.getStoreid());
    psmt.setInt(2,  orderinfosum.getTC());
    psmt.setFloat(3,  orderinfosum.getPrice());
    psmt.setString(4,  orderinfosum.getOrderdate());
 
  
    

    //执行SQL语句
    psmt.execute();
   // psmt.close();
    //conn.close();
    
    /**
     * prepareStatement这个方法会将SQL语句加载到驱动程序conn集成程序中，但是并不直接执行
     * 而是当它调用execute()方法的时候才真正执行；
     * 
     * 上面SQL中的参数用?表示，相当于占位符，然后在对参数进行赋值。
     * 当真正执行时，这些参数会加载在SQL语句中，把SQL语句拼接完整才去执行。
     * 这样就会减少对数据库的操作
     */
}


public static void insertOrderInfoFoo(OrderInfoSum orderinfosum)throws SQLException{
  
    Connection conn=DBUtil.getConnection();
   String sql="INSERT INTO [dbo].[order_info_foo]\r\n" + 
   		"           ([station]\r\n" + 
   		"           ,[storeid]\r\n" + 
   		"           ,[tc]\r\n" + 
   		"           ,[price]\r\n" + 
   		"           ,[orderdate])\r\n" + 
   		"     VALUES\r\n" + 
   		"           (?,?,?,?,?)";
   
    PreparedStatement psmt = conn.prepareStatement(sql);
    //先对应SQL语句，给SQL语句传递参数
    psmt.setString(1,  orderinfosum.getStation());
    psmt.setString(2,  orderinfosum.getStoreid());
    psmt.setInt(3,  orderinfosum.getTC());
    psmt.setFloat(4,  orderinfosum.getPrice());
    psmt.setString(5,  orderinfosum.getOrderdate());
 
  
    

    //执行SQL语句
    psmt.execute();
   // psmt.close();
    //conn.close();
    
    /**
     * prepareStatement这个方法会将SQL语句加载到驱动程序conn集成程序中，但是并不直接执行
     * 而是当它调用execute()方法的时候才真正执行；
     * 
     * 上面SQL中的参数用?表示，相当于占位符，然后在对参数进行赋值。
     * 当真正执行时，这些参数会加载在SQL语句中，把SQL语句拼接完整才去执行。
     * 这样就会减少对数据库的操作
     */
}

public static int truncateOrderInfoFoo(String begindate,String lastdate,String storeid,String station)throws SQLException{
  
    Connection conn=DBUtil.getConnection();
    String sql="";
    if(storeid.equals("")) {
        sql="DELETE FROM order_info_foo WHERE orderdate>='"+begindate+"' AND orderdate<='"+lastdate+"'  AND station='"+station+"' ";
        System.out.println(sql);
    }else {
    	sql="DELETE FROM order_info_foo WHERE orderdate>='"+begindate+"' AND orderdate<='"+lastdate+"' AND storeid='"+storeid+"' AND station='"+station+"' ";
        System.out.println(sql);	
    }
    PreparedStatement psmt = conn.prepareStatement(sql);

 


    //执行SQL语句
    int result=psmt.executeUpdate();
   // conn.close();
    System.out.println(" 已经删除对应的 order_info_foo记录了");
    return result;
}


public static int truncateOrderInfoSum()throws SQLException{
	  
    Connection conn=DBUtil.getConnection();
   String sql="TRUNCATE TABLE order_info_sum";
   
    PreparedStatement psmt = conn.prepareStatement(sql);

 


    //执行SQL语句
    int result=psmt.executeUpdate();
   // conn.close();
    System.out.println("order_info_sum 已经被清空了");
    return result;
}


public static void insertOrderInfoXiuCan(OrderInfoSum orderinfosum)throws SQLException{
  
    Connection conn=DBUtil.getConnection();
   String sql="INSERT INTO dbo.order_info_xiucan\r\n" + 
   		"           (storeid\r\n" + 
   		"           ,tc\r\n" + 
   		"           ,price\r\n" + 
   		"           ,orderdate)\r\n" + 
   		"     VALUES(?,?,?,?)";
   
    PreparedStatement psmt = conn.prepareStatement(sql);
    //先对应SQL语句，给SQL语句传递参数
    psmt.setString(1,  orderinfosum.getStoreid());
    psmt.setInt(2,  orderinfosum.getTC());
    psmt.setFloat(3,  orderinfosum.getPrice());
    psmt.setString(4,  orderinfosum.getOrderdate());
 
  
    

    //执行SQL语句
    psmt.execute();
   // psmt.close();
    //conn.close();
    
    /**
     * prepareStatement这个方法会将SQL语句加载到驱动程序conn集成程序中，但是并不直接执行
     * 而是当它调用execute()方法的时候才真正执行；
     * 
     * 上面SQL中的参数用?表示，相当于占位符，然后在对参数进行赋值。
     * 当真正执行时，这些参数会加载在SQL语句中，把SQL语句拼接完整才去执行。
     * 这样就会减少对数据库的操作
     */
}

public static int truncateOrderInfoXiuCan()throws SQLException{
  
    Connection conn=DBUtil.getConnection();
   String sql="TRUNCATE TABLE order_info_xiucan";
   
    PreparedStatement psmt = conn.prepareStatement(sql);

 


    //执行SQL语句
    int result=psmt.executeUpdate();
   // conn.close();
    System.out.println("order_info_xiucan 已经被清空了");
    return result;
}


public static void main(String[] args) {
	try {
		OrderInfoSum.truncateOrderInfoSum();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}


}
