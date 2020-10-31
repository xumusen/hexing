package com.entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.utils.DBUtil;

public class TableDiff {
	String uniteStoreId,	orderDate	;
	Long 散件及跟餐	,付款汇总	,食品销售;

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

	public long get散件及跟餐() {
		return 散件及跟餐;
	}

	public void set散件及跟餐(Long 散件及跟餐) {
		this.散件及跟餐 = 散件及跟餐;
	}

	public Long get付款汇总() {
		return 付款汇总;
	}

	public void set付款汇总(Long 付款汇总) {
		this.付款汇总 = 付款汇总;
	}

	public Long get食品销售() {
		return 食品销售;
	}

	public void set食品销售(Long 食品销售) {
		this.食品销售 = 食品销售;
	}

	public static void insertTableDiff(TableDiff tableDiff)throws SQLException{
	    //首先拿到数据库的连接
	    Connection conn=DBUtil.getConnection();
	    String sql="INSERT INTO [dbo].[tablediff]\r\n" + 
	    		"           ([uniteStoreId]\r\n" + 
	    		"           ,[orderDate]\r\n" + 
	    		"           ,[散件及跟餐]\r\n" + 
	    		"           ,[付款汇总]\r\n" + 
	    		"           ,[食品销售])\r\n" + 
	    		"     VALUES\r\n" + 
	    		"           (?,?,?,?,?)";
	    PreparedStatement psmt = conn.prepareStatement(sql);
	    //先对应SQL语句，给SQL语句传递参数
	    psmt.setString(1,  tableDiff.getUniteStoreId());
	    psmt.setString(2,  tableDiff.getOrderDate());
	    psmt.setLong(3,  tableDiff.get散件及跟餐());
	    psmt.setLong(4, tableDiff.get付款汇总());
	    psmt.setLong(5, tableDiff.get食品销售());
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

public static int truncateTableDiff()throws SQLException{
	  
    Connection conn=DBUtil.getConnection();
   String sql="TRUNCATE TABLE tablediff";
    PreparedStatement psmt = conn.prepareStatement(sql);
    //执行SQL语句
    int result=psmt.executeUpdate();
   // conn.close();
    System.out.println("tablediff 已经被清空了");
    return result;
}
}
