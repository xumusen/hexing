package com.entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.utils.DBUtil;

public class OrderDiff {

	String PosStore
    ,PosUploaddatetime
    ,PosRef
    ,PosAmt
    ,PosType
    ,ZtStore
    ,ZtBrandName
    ,ZtOrderid
    ,ZtThirdSn
    ,ZtMerchantPrice
    ,ZtExtorderid
    ,ZtOrderdate;

	public String getPosStore() {
		return PosStore;
	}

	public void setPosStore(String posStore) {
		PosStore = posStore;
	}

	public String getPosUploaddatetime() {
		return PosUploaddatetime;
	}

	public void setPosUploaddatetime(String posUploaddatetime) {
		PosUploaddatetime = posUploaddatetime;
	}

	public String getPosRef() {
		return PosRef;
	}

	public void setPosRef(String posRef) {
		PosRef = posRef;
	}

	public String getPosAmt() {
		return PosAmt;
	}

	public void setPosAmt(String posAmt) {
		PosAmt = posAmt;
	}

	public String getPosType() {
		return PosType;
	}

	public void setPosType(String posType) {
		PosType = posType;
	}

	public String getZtStore() {
		return ZtStore;
	}

	public void setZtStore(String ztStore) {
		ZtStore = ztStore;
	}

	public String getZtBrandName() {
		return ZtBrandName;
	}

	public void setZtBrandName(String ztBrandName) {
		ZtBrandName = ztBrandName;
	}

	public String getZtOrderid() {
		return ZtOrderid;
	}

	public void setZtOrderid(String ztOrderid) {
		ZtOrderid = ztOrderid;
	}

	public String getZtThirdSn() {
		return ZtThirdSn;
	}

	public void setZtThirdSn(String ztThirdSn) {
		ZtThirdSn = ztThirdSn;
	}

	public String getZtMerchantPrice() {
		return ZtMerchantPrice;
	}

	public void setZtMerchantPrice(String ztMerchantPrice) {
		ZtMerchantPrice = ztMerchantPrice;
	}

	public String getZtExtorderid() {
		return ZtExtorderid;
	}

	public void setZtExtorderid(String ztExtorderid) {
		ZtExtorderid = ztExtorderid;
	}

	public String getZtOrderdate() {
		return ZtOrderdate;
	}

	public void setZtOrderdate(String ztOrderdate) {
		ZtOrderdate = ztOrderdate;
	}
	
	public static void insertOrderDiff(OrderDiff orderDiff)throws SQLException{
	    //首先拿到数据库的连接
	    Connection conn=DBUtil.getConnection();
	    String sql="INSERT INTO [dbo].[orderDiff]\r\n" + 
	    		"           ([PosStore]\r\n" + 
	    		"           ,[PosUploaddatetime]\r\n" + 
	    		"           ,[PosRef]\r\n" + 
	    		"           ,[PosAmt]\r\n" + 
	    		"           ,[PosType]\r\n" + 
	    		"           ,[ZtStore]\r\n" + 
	    		"           ,[ZtBrandName]\r\n" + 
	    		"           ,[ZtOrderid]\r\n" + 
	    		"           ,[ZtThirdSn]\r\n" + 
	    		"           ,[ZtMerchantPrice]\r\n" + 
	    		"           ,[ZtExtorderid]\r\n" + 
	    		"           ,[ZtOrderdate])\r\n" + 
	    		"     VALUES\r\n" + 
	    		"           (?\r\n" + 
	    		"           ,?\r\n" + 
	    		"           ,?\r\n" + 
	    		"           ,?\r\n" + 
	    		"           ,?\r\n" + 
	    		"           ,?\r\n" + 
	    		"           ,?\r\n" + 
	    		"           ,?\r\n" + 
	    		"           ,?\r\n" + 
	    		"           ,?\r\n" + 
	    		"           ,?\r\n" + 
	    		"           ,?\r\n" + 
	    		"           )";
	    PreparedStatement psmt = conn.prepareStatement(sql);
	    //先对应SQL语句，给SQL语句传递参数
	    psmt.setString(1,  orderDiff.getPosStore());
	    psmt.setString(2,  orderDiff.getPosUploaddatetime());
	    psmt.setString(3, orderDiff.getPosRef());
	    psmt.setString(4, orderDiff.getPosAmt());
	    psmt.setString(5,  orderDiff.getPosType());
	    psmt.setString(6, orderDiff.getZtStore());
	    psmt.setString(7,  orderDiff.getZtBrandName());
	    psmt.setString(8,  orderDiff.getZtOrderid());
	    psmt.setString(9, orderDiff.getZtThirdSn());
	    psmt.setString(10,  orderDiff.getZtMerchantPrice());
	    psmt.setString(11,  orderDiff.getZtExtorderid());
	    psmt.setString(12,  orderDiff.getZtOrderdate());



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
