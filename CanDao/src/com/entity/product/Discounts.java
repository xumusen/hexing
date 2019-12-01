package com.entity.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.utils.DBUtil;

public class Discounts {
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getVendor() {
		return vendor;
	}
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getChildType() {
		return childType;
	}
	public void setChildType(int childType) {
		this.childType = childType;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getThirdSubsidy() {
		return thirdSubsidy;
	}
	public void setThirdSubsidy(String thirdSubsidy) {
		this.thirdSubsidy = thirdSubsidy;
	}
	public String getMerchantSubsidy() {
		return merchantSubsidy;
	}
	public void setMerchantSubsidy(String merchantSubsidy) {
		this.merchantSubsidy = merchantSubsidy;
	}
	public List<DisProducts> getDisProducts() {
		return disProducts;
	}
	public void setDisProducts(List<DisProducts> disProducts) {
		this.disProducts = disProducts;
	}
	String code;
	String pid;
	String vendor;
	int num;
	int type;
	int childType;
	String title;
	String content;
	String price;
	String totalAmount;
	String thirdSubsidy;
	String merchantSubsidy;
	List<DisProducts> disProducts  ;
	String orderid;
	String orderTime,orderDate;
	

	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public static void insertDiscount(String orderid,Discounts discounts,String storeid,String orderDate,String orderTime)throws SQLException{
	    //首先拿到数据库的连接
	    Connection conn=DBUtil.getConnection();
	 /*   String sql="" + 
	            "insert into status"+
	            "(title,value,dateTime) "+
	            "values(?,?,?)";//参数用?表示，相当于占位符;
	*/	        
	    String sql=""+
	    		"INSERT INTO discounts"+
	    		"(code,pid,vendor,num,[type],childType,title,[content],price,totalAmount,thirdSubsidy,merchantSubsidy,orderid,storeid,orderDate,orderTime)"+
	    		"values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	   // System.out.println(sql);
	    PreparedStatement psmt = conn.prepareStatement(sql);
	    
	    //先对应SQL语句，给SQL语句传递参数
	    psmt.setString(1,  discounts.getCode());
	    psmt.setString(2,  discounts.getPid());
	    psmt.setString(3,  discounts.getVendor());
	    psmt.setInt(4,  discounts.getNum());
	    psmt.setInt(5,  discounts.getType());
	    psmt.setInt(6,  discounts.getChildType());
	    psmt.setString(7,  discounts.getTitle());
	    psmt.setString(8,  discounts.getContent());
	    psmt.setString(9,  discounts.getPrice());
	    psmt.setString(10,  discounts.getTotalAmount());
	    psmt.setString(11,  discounts.getThirdSubsidy());
	    psmt.setString(12,  discounts.getMerchantSubsidy());
	    psmt.setString(13,orderid);
	    psmt.setString(14,storeid);
	    psmt.setString(15,orderDate);
	    psmt.setString(16,orderTime);


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
