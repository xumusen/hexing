package com.entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.utils.DBUtil;

public class DiffDiscount {
	
	String uniteStoreId,orderDate;
	Long 注册,现金,注册现金差值,优惠,注册现金优惠差值;
	public String getUniteStoreId() {
		return uniteStoreId;
	}
	public void setUniteStoreId(String uniteStoreId) {
		this.uniteStoreId = uniteStoreId;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public Long get注册() {
		return 注册;
	}
	public void set注册(Long 注册) {
		this.注册 = 注册;
	}
	public Long get现金() {
		return 现金;
	}
	public void set现金(Long 现金) {
		this.现金 = 现金;
	}
	public Long get注册现金差值() {
		return 注册现金差值;
	}
	public void set注册现金差值(Long 注册现金差值) {
		this.注册现金差值 = 注册现金差值;
	}
	public Long get优惠() {
		return 优惠;
	}
	public void set优惠(Long 优惠) {
		this.优惠 = 优惠;
	}
	public Long get注册现金优惠差值() {
		return 注册现金优惠差值;
	}
	public void set注册现金优惠差值(Long 注册现金优惠差值) {
		this.注册现金优惠差值 = 注册现金优惠差值;
	}
	

	public static void insertDiscountDiff(DiffDiscount diffDiscount)throws SQLException{
	    //首先拿到数据库的连接
	    Connection conn=DBUtil.getConnection();
	    String sql="INSERT INTO [dbo].[discountdiff]\r\n" + 
	    		"           ([uniteStoreId]\r\n" + 
	    		"           ,[orderDate]\r\n" + 
	    		"           ,[注册]\r\n" + 
	    		"           ,[现金]\r\n" + 
	    		"           ,[注册-现金]\r\n" + 
	    		"           ,[优惠]\r\n" + 
	    		"           ,[注册现金优惠差值])\r\n" + 
	    		"     VALUES\r\n" + 
	    		"           (?,?,?,?,?,?,?)";
	    PreparedStatement psmt = conn.prepareStatement(sql);
	    //先对应SQL语句，给SQL语句传递参数
	    psmt.setString(1,  diffDiscount.getUniteStoreId());
	    psmt.setString(2,  diffDiscount.getOrderDate());
	    psmt.setLong(3,  diffDiscount.get注册());
	    psmt.setLong(4, diffDiscount.get现金());
	    psmt.setLong(5, diffDiscount.get注册现金差值());
	    psmt.setLong(6, diffDiscount.get优惠());
	    psmt.setLong(7, diffDiscount.get注册现金优惠差值());
	    //执行SQL语句
	    psmt.execute();
	    /**
	     * prepareStatement这个方法会将SQL语句加载到驱动程序conn集成程序中，但是并不直接执行
	     * 而是当它调用execute()方法的时候才真正执行；
	     * 
	     * 上面SQL中的参数用?表示，相当于占位符，然后在对参数进行赋值。
	     * 当真正执行时，这些参数会加载在SQL语句中，把SQL语句拼接完整才去执行。
	     * 这样就会减少对数据库的操作
	     */
	}
	
	public static int truncateDiscountDiff()throws SQLException{
		  
	    Connection conn=DBUtil.getConnection();
	   String sql="TRUNCATE TABLE discountdiff";
	    PreparedStatement psmt = conn.prepareStatement(sql);
	    //执行SQL语句
	    int result=psmt.executeUpdate();
	   // conn.close();
	    System.out.println("discountdiff 已经被清空了");
	    return result;
	}
}
