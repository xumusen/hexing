package com.entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.utils.DBUtil;

public class OrderFoo {

	String storeid,originalPrice,merchantBearPrice,billDate,caculateMerchanPrice,extOrderId,createtime,station;
	
	
	
	
	

public String getStation() {
		return station;
	}

	public void setStation(String station) {
		this.station = station;
	}

public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

public String getExtOrderId() {
		return extOrderId;
	}

	public void setExtOrderId(String extOrderId) {
		this.extOrderId = extOrderId;
	}

public String getStoreid() {
		return storeid;
	}

	public void setStoreid(String storeid) {
		this.storeid = storeid;
	}

	public String getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(String originalPrice) {
		this.originalPrice = originalPrice;
	}

	public String getMerchantBearPrice() {
		return merchantBearPrice;
	}

	public void setMerchantBearPrice(String merchantBearPrice) {
		this.merchantBearPrice = merchantBearPrice;
	}

	public String getBillDate() {
		return billDate;
	}

	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}

	public String getCaculateMerchanPrice() {
		return caculateMerchanPrice;
	}

	public void setCaculateMerchanPrice(String caculateMerchanPrice) {
		this.caculateMerchanPrice = caculateMerchanPrice;
	}

public static void insertFooOrder(OrderFoo orderfoo)throws SQLException{
  
	
    Connection conn=DBUtil.getConnection();
   String sql="\r\n" + 
   		"INSERT INTO [dbo].[order_foo]\r\n" + 
   		"           ([storeid]\r\n" + 
   		"           ,[extOrderId]\r\n" + 
   		"           ,[billDate]\r\n" + 
   		"           ,[createtime]\r\n" + 
   		"           ,[originalPrice]\r\n" + 
   		"           ,[merchantBearPrice]\r\n" + 
   		"           ,[caculateMerchanPrice]\r\n" + 
   		"           ,[station])\r\n" + 
   		"     VALUES\r\n" + 
   		"            (?,?,?,?,?,?,?,?)";
    PreparedStatement psmt = conn.prepareStatement(sql);
    //先对应SQL语句，给SQL语句传递参数
    psmt.setString(1,  orderfoo.getStoreid());
    psmt.setString(2,  orderfoo.getExtOrderId());
    psmt.setString(3,  orderfoo.getBillDate());
    psmt.setString(4,  orderfoo.getCreatetime());
    psmt.setString(5,  orderfoo.getOriginalPrice());
    psmt.setString(6,  orderfoo.getMerchantBearPrice());
    psmt.setString(7, orderfoo.getCaculateMerchanPrice());
    psmt.setString(8, orderfoo.getStation());

 


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

public static int deleteOrderFoo(String orderdate,String storeid,String station)throws SQLException{
	  
    Connection conn=DBUtil.getConnection();
    String sql="DELETE FROM order_foo WHERE billDate>='"+orderdate+"' and storeid='"+storeid+"'  AND station='"+station+"' ";
    System.out.println(sql);
 
    PreparedStatement psmt = conn.prepareStatement(sql);

 


    //执行SQL语句
    int result=psmt.executeUpdate();
   // conn.close();
    System.out.println(" 已经删除对应的 order_foo记录了");
    return result;
}


public static int truncateOrderFoo()throws SQLException{
	  
    Connection conn=DBUtil.getConnection();
   String sql="TRUNCATE TABLE order_foo";
    PreparedStatement psmt = conn.prepareStatement(sql);
    //执行SQL语句
    int result=psmt.executeUpdate();
   // conn.close();
    System.out.println("order_foo 已经被清空了");
    return result;
}

public static void main(String[] args) throws SQLException {
	//OrderFoo.truncateOrderFoo();
}

}
