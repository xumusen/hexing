package com.entity.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.utils.DBUtil;

public class SubDept {
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	String id;
	String title;
	String fid,orderid;
	public String getFid() {
		return fid;
	}
	public void setFid(String fid) {
		this.fid = fid;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public static void insertSubDept(String orderid,SubDept subDept,String fid,String pid)throws SQLException{
	    //首先拿到数据库的连接
	    Connection conn=DBUtil.getConnection();
	 /*   String sql="" + 
	            "insert into status"+
	            "(title,value,dateTime) "+
	            "values(?,?,?)";//参数用?表示，相当于占位符;
	*/	        
	    String sql=""+
	    		"insert into subdept"+
	    		"(id,title,fid,pid,orderid)"+
	    		"values (?,?,?,?,?)";
	   // System.out.println(sql);
	    PreparedStatement psmt = conn.prepareStatement(sql);
	    
	    //先对应SQL语句，给SQL语句传递参数
	    psmt.setString(1, subDept.getId());
	    psmt.setString(2, subDept.getTitle() );
	    psmt.setString(3, fid );
	    psmt.setString(4, pid);
	    psmt.setString(5, orderid );
	
	    
	    


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

}
