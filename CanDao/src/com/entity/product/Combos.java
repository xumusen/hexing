package com.entity.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.utils.DBUtil;

public class Combos {

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

public List<Skus> getSkus() {
	return skus;
}
public void setSkus(List<Skus> skus) {
	this.skus = skus;
}
public List<Propertys> getPropertys() {
	return propertys;
}
public void setPropertys(List<Propertys> propertys) {
	this.propertys = propertys;
}
String pid;
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
public String getAddPrice() {
	return addPrice;
}
public void setAddPrice(String addPrice) {
	this.addPrice = addPrice;
}
String name;
String num;
String price;
String addPrice;
List<Skus> skus;
List<Propertys> propertys;
String orderid;


public String getOrderid() {
	return orderid;
}
public void setOrderid(String orderid) {
	this.orderid = orderid;
}
public static void insertCombos(String orderid,Combos combos,String fid,String fnum,String storeid)throws SQLException{
    //首先拿到数据库的连接
    Connection conn=DBUtil.getConnection();
 /*   String sql="" + 
            "insert into status"+
            "(title,value,dateTime) "+
            "values(?,?,?)";//参数用?表示，相当于占位符;
*/	        
    String sql=""+
    		"INSERT INTO combos"+
    		"(pid,name,num,price,addPrice,orderid,fid,fnum,storeid)"+
    		"values(?,?,?,?,?,?,?,?,?)";
    PreparedStatement psmt = conn.prepareStatement(sql);
    
    //先对应SQL语句，给SQL语句传递参数
    psmt.setString(1,  combos.getPid());
    psmt.setString(2, combos.getName());
    psmt.setString(3, combos.getNum());
    psmt.setString(4, combos.getPrice());
    psmt.setString(5, combos.getAddPrice());
    psmt.setString(6,orderid);
    psmt.setString(7,fid);
    psmt.setString(8,fnum);
    psmt.setString(9,storeid);


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
