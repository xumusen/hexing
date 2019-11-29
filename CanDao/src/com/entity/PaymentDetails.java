package com.entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.utils.DBUtil;

public class PaymentDetails {
	int payType;
	
	public int getPayType() {
		return payType;
	}

	public void setPayType(int payType) {
		this.payType = payType;
	}

	int type;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getPosType() {
		return posType;
	}

	public void setPosType(String posType) {
		this.posType = posType;
	}



	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}


	String typeName;
	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}


	String money;
	
	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getPosName() {
		return posName;
	}

	public void setPosName(String posName) {
		this.posName = posName;
	}

	String tradeNo;
	String posName;
	String posType;
	String code;
	String orderDate,orderTime;
	
	 public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public static void insertPaymentDetails(String orderid,PaymentDetails paymentDetails,String orderDate,String orderTime,String originOrderId,String storeid)throws SQLException{
	        //首先拿到数据库的连接
	        Connection conn=DBUtil.getConnection();
	     /*   String sql="" + 
	                "insert into status"+
	                "(title,value,dateTime) "+
	                "values(?,?,?)";//参数用?表示，相当于占位符;
*/	        
	        String sql=""+
	        		"INSERT INTO paymentDetails"+
	        		"(orderid,payType,[type],posType,posName,typeName,[money],tradeNo,code,orderDate,orderTime,originOrderId,storeid)"
	        		+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
	        PreparedStatement psmt = conn.prepareStatement(sql);
	        
	        //先对应SQL语句，给SQL语句传递参数
	        psmt.setString(1,  orderid);
	        psmt.setLong(2, paymentDetails.getPayType());
	        psmt.setLong(3, paymentDetails.getType());
	        psmt.setString(4, paymentDetails.getPosType());
	        psmt.setString(5, paymentDetails.getPosName());
	        psmt.setString(6, paymentDetails.getTypeName());
	        psmt.setString(7, paymentDetails.getMoney());
	        psmt.setString(8, paymentDetails.getTradeNo());
	        psmt.setString(9, paymentDetails.getCode());
	        psmt.setString(10, orderDate);
	        psmt.setString(11, orderTime);
	        psmt.setString(12, originOrderId);
	        psmt.setString(13, storeid);
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
