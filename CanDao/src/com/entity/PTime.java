package com.entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.utils.DBUtil;

public class PTime {
	String storeid,orderdate,ordertime,filename,txttime;


	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getStoreid() {
		return storeid;
	}

	public String getTxttime() {
		return txttime;
	}

	public void setTxttime(String txttime) {
		this.txttime = txttime;
	}

	public void setStoreid(String storeid) {
		this.storeid = storeid;
	}

	public String getOrderdate() {
		return orderdate;
	}

	public void setOrderdate(String orderdate) {
		this.orderdate = orderdate;
	}

	public String getOrdertime() {
		return ordertime;
	}

	public void setOrdertime(String ordertime) {
		this.ordertime = ordertime;
	}

	Long uploadtime;



	public Long getUploadtime() {
		return uploadtime;
	}

	public void setUploadtime(Long uploadtime) {
		this.uploadtime = uploadtime;
	}
	public static void insertFileTime(PTime fileTime)throws SQLException{
	    //首先拿到数据库的连接
	    Connection conn=DBUtil.getConnection();
	    String sql="INSERT INTO [dbo].[ptime]\r\n" + 
	    		"           ([storeid]\r\n" + 
	    		"           ,[orderdate]\r\n" + 
	    		"           ,[ordertime]\r\n" + 
	    		"           ,[uploadtime]\r\n" + 
	    		"           ,[filename]\r\n" + 
	    		"           ,[txttime])\r\n" + 
	    		"     VALUES\r\n" + 
	    		"           (?,?,?,?,?,?)";
	    PreparedStatement psmt = conn.prepareStatement(sql);
	    //先对应SQL语句，给SQL语句传递参数
	    psmt.setString(1,  fileTime.getStoreid());
	    psmt.setString(2,  fileTime.getOrderdate());
	    psmt.setString(3,  fileTime.getOrdertime());
	    psmt.setLong(4, fileTime.getUploadtime());
	    psmt.setString(5, fileTime.getFilename());
	    psmt.setString(6, fileTime.getTxttime());
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

public static int truncatefiletime()throws SQLException{
	  
    Connection conn=DBUtil.getConnection();
   String sql="TRUNCATE TABLE ptime";
    PreparedStatement psmt = conn.prepareStatement(sql);
    //执行SQL语句
    int result=psmt.executeUpdate();
   // conn.close();
    System.out.println("ptime 已经被清空了");
    return result;
}
}
