package com.entity.goods;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import com.utils.DBUtil;

public class Propertys {
	String pid;
public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}


String title;
String propertyId;
String name;
String orderid;

public String getOrderid() {
	return orderid;
}
public void setOrderid(String orderid) {
	this.orderid = orderid;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getPropertyId() {
	return propertyId;
}
public void setPropertyId(String propertyId) {
	this.propertyId = propertyId;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}


public static void insertPropertys(String orderid,Propertys propertys,String pid)throws SQLException{
    //首先拿到数据库的连接
    Connection conn=DBUtil.getConnection();
 /*   String sql="" + 
            "insert into status"+
            "(title,value,dateTime) "+
            "values(?,?,?)";//参数用?表示，相当于占位符;
*/	        
    String sql=""+
    		"INSERT INTO propertys"+
    		"(title,propertyId,name,orderid,pid)"+
    		"values(?,?,?,?,?)";
    PreparedStatement psmt = conn.prepareStatement(sql);
    
    //先对应SQL语句，给SQL语句传递参数

    psmt.setString(1, propertys.getTitle());
    psmt.setString(2, propertys.getPropertyId());
    psmt.setString(3, propertys.getName());
    psmt.setString(4, orderid);
    psmt.setString(5, pid);
    
    


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

public static void insertComPropertys(String orderid,Propertys propertys,String pid,String cpid)throws SQLException{
    //首先拿到数据库的连接
    Connection conn=DBUtil.getConnection();
 /*   String sql="" + 
            "insert into status"+
            "(title,value,dateTime) "+
            "values(?,?,?)";//参数用?表示，相当于占位符;
*/	        
    String sql=""+
    		"INSERT INTO propertys"+
    		"(title,propertyId,name,orderid,pid,cpid)"+
    		"values(?,?,?,?,?,?)";
    PreparedStatement psmt = conn.prepareStatement(sql);
    
    //先对应SQL语句，给SQL语句传递参数

    psmt.setString(1, propertys.getTitle());
    psmt.setString(2, propertys.getPropertyId());
    psmt.setString(3, propertys.getName());
    psmt.setString(4, orderid);
    psmt.setString(5, pid);
    psmt.setString(6, cpid);
    


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
