package com.entity.goods;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.entity.PaymentDetails;
import com.utils.DBUtil;

public class Products_goods {

	String pid,subPid,isDiscount,name,price,totalPrice,realTimePrice,realTimeTotalPrice,num,taxRate,taxCategoryCode;
	List<Skus> skus;
	List<Propertys> propertys;
	List<Combos> combos;
	
	
public String getPid() {
		return pid;
	}


	public void setPid(String pid) {
		this.pid = pid;
	}


	public String getSubPid() {
		return subPid;
	}


	public void setSubPid(String subPid) {
		this.subPid = subPid;
	}


	public String getIsDiscount() {
		return isDiscount;
	}


	public void setIsDiscount(String isDiscount) {
		this.isDiscount = isDiscount;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPrice() {
		return price;
	}


	public void setPrice(String price) {
		this.price = price;
	}


	public String getTotalPrice() {
		return totalPrice;
	}


	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}


	public String getRealTimePrice() {
		return realTimePrice;
	}


	public void setRealTimePrice(String realTimePrice) {
		this.realTimePrice = realTimePrice;
	}


	public String getRealTimeTotalPrice() {
		return realTimeTotalPrice;
	}


	public void setRealTimeTotalPrice(String realTimeTotalPrice) {
		this.realTimeTotalPrice = realTimeTotalPrice;
	}


	public String getNum() {
		return num;
	}


	public void setNum(String num) {
		this.num = num;
	}


	public String getTaxRate() {
		return taxRate;
	}


	public void setTaxRate(String taxRate) {
		this.taxRate = taxRate;
	}


	public String getTaxCategoryCode() {
		return taxCategoryCode;
	}


	public void setTaxCategoryCode(String taxCategoryCode) {
		this.taxCategoryCode = taxCategoryCode;
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


	public List<Combos> getCombos() {
		return combos;
	}


	public void setCombos(List<Combos> combos) {
		this.combos = combos;
	}
String orderid;


public String getOrderid() {
	return orderid;
}


public void setOrderid(String orderid) {
	this.orderid = orderid;
}


public static void insertProducts(String orderid,Products_goods products,String originOrderId)throws SQLException{
    //首先拿到数据库的连接
    Connection conn=DBUtil.getConnection();
 /*   String sql="" + 
            "insert into status"+
            "(title,value,dateTime) "+
            "values(?,?,?)";//参数用?表示，相当于占位符;
*/	        
    String sql=""+
    		"INSERT INTO products_goods"+
    		"(pid,subPid,isDiscount,name,price,totalPrice,realTimePrice,realTimeTotalPrice,num,taxRate,taxCategoryCode,orderid,originOrderId)"+
    		"values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
    PreparedStatement psmt = conn.prepareStatement(sql);
    
    //先对应SQL语句，给SQL语句传递参数
    psmt.setString(1,  products.getPid());
    psmt.setString(2,products.getSubPid());
    psmt.setString(3, products.getIsDiscount());
    psmt.setString(4, products.getName());
    psmt.setString(5, products.getPrice());
    psmt.setString(6, products.getTotalPrice());
    psmt.setString(7, products.getRealTimePrice());
    psmt.setString(8, products.getRealTimeTotalPrice());
    psmt.setString(9, products.getNum());
    psmt.setString(10, products.getTaxRate());
    psmt.setString(11, products.getTaxCategoryCode());
psmt.setString(12, orderid);
psmt.setString(13, originOrderId);

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
