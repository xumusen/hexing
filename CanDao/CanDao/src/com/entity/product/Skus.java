package com.entity.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.utils.DBUtil;

public class Skus {
String title;
String skuId;
String price;
String name;


public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getSkuId() {
	return skuId;
}
public void setSkuId(String skuId) {
	this.skuId = skuId;
}
public String getPrice() {
	return price;
}
public void setPrice(String price) {
	this.price = price;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}



public static void insertSkus(String orderid,Skus skus)throws SQLException{
    //首先拿到数据库的连接
    Connection conn=DBUtil.getConnection();
 /*   String sql="" + 
            "insert into status"+
            "(title,value,dateTime) "+
            "values(?,?,?)";//参数用?表示，相当于占位符;
*/	        
    String sql=""+
    		"INSERT INTO skus"+
    		"(orderid,title,skuId,price,name)"+
    		"VALUES(?,?,?,?,?)";
    PreparedStatement psmt = conn.prepareStatement(sql);
    
    //先对应SQL语句，给SQL语句传递参数
    psmt.setString(1,  orderid);
    psmt.setString(2, skus.getTitle());
    psmt.setString(3, skus.getSkuId());
    psmt.setString(4,skus.getPrice());
    psmt.setString(5, skus.getName());

    
    


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
