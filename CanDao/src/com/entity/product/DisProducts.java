package com.entity.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.utils.DBUtil;

public class DisProducts {

	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	String pid;
	String name ;
	String  num;
	String price;
	String orderid;
	String itemDisc;
	public String getItemDisc() {
		return itemDisc;
	}
	public void setItemDisc(String itemDisc) {
		this.itemDisc = itemDisc;
	}
	Dept dept;
	Types types;
	
	

	public Dept getDept() {
		return dept;
	}
	public void setDept(Dept dept) {
		this.dept = dept;
	}
	public Types getTypes() {
		return types;
	}
	public void setTypes(Types types) {
		this.types = types;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public static void insertDisProducts(String orderid,DisProducts disproducts,String storeid,String orderDate,String orderTime)throws SQLException{
	    //首先拿到数据库的连接
	    Connection conn=DBUtil.getConnection();
	 /*   String sql="" + 
	            "insert into status"+
	            "(title,value,dateTime) "+
	            "values(?,?,?)";//参数用?表示，相当于占位符;
	*/	        
	    String sql=""+
	    		"INSERT INTO disProducts"+
	    		"(pid,name,num,price,itemDisc,orderid,storeid,orderDate,orderTime)"+
	    		"values (?,?,?,?,?,?,?,?,?)";
	    System.out.println(sql);
	    PreparedStatement psmt = conn.prepareStatement(sql);
	    
	    //先对应SQL语句，给SQL语句传递参数
	    psmt.setString(1,  disproducts.getPid());
	    psmt.setString(2, disproducts.getName());
	    psmt.setString(3, disproducts.getNum());
	    psmt.setString(4, disproducts.getPrice());
	    psmt.setString(5, disproducts.getItemDisc());
	    psmt.setString(6, orderid);
	    psmt.setString(7, storeid);
	    psmt.setString(8, orderDate);
	    psmt.setString(9, orderTime);
	    
	    


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
