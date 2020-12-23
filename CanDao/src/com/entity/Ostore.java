package com.entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.utils.DBUtil;

public class Ostore {
String ref,type,date,mode,item_code,price,disc,coupon,qty,combo,tax,aftertax,storename,uploadDatetime,uploadDate,uploadTime;

public String getUploadDatetime() {
	return uploadDatetime;
}

public void setUploadDatetime(String uploadDatetime) {
	this.uploadDatetime = uploadDatetime;
}

public String getUploadDate() {
	return uploadDate;
}

public void setUploadDate(String uploadDate) {
	this.uploadDate = uploadDate;
}

public String getUploadTime() {
	return uploadTime;
}

public void setUploadTime(String uploadTime) {
	this.uploadTime = uploadTime;
}

public String getStorename() {
	return storename;
}

public void setStorename(String storename) {
	this.storename = storename;
}



public String getRef() {
	return ref;
}

public void setRef(String ref) {
	this.ref = ref;
}

public String getType() {
	return type;
}

public void setType(String type) {
	this.type = type;
}

public String getMode() {
	return mode;
}

public void setMode(String mode) {
	this.mode = mode;
}

public String getDate() {
	return date;
}

public void setDate(String date) {
	this.date = date;
}



public String getDisc() {
	return disc;
}

public void setDisc(String disc) {
	this.disc = disc;
}

public String getTax() {
	return tax;
}

public void setTax(String tax) {
	this.tax = tax;
}

public String getAftertax() {
	return aftertax;
}

public void setAftertax(String aftertax) {
	this.aftertax = aftertax;
}

public static void insertOstore(Ostore tstore)throws SQLException{
    //首先拿到数据库的连接
    Connection conn=DBUtil.getConnection();
 /*   String sql="" + 
            "insert into status"+
            "(title,value,dateTime) "+
            "values(?,?,?)";//参数用?表示，相当于占位符;
*/	        
/*    String sql=""+
    		"INSERT INTO [order]"+
    				"(orderid,thirdSn,storeId,fromType,name,phone,、takeNo,tableNum,userNote,peopleNum,tableNo,deviceNo,staffId,staffNo,staffBane,memberId,point,pointExpiryDate,orderTime,orderDate,orderStatus,orderType,posOrderType,isPayed,payType,isInvoice,invoiceDesc,taxNo,price,deliveryFee,mealFee,productPrice,discountPrice,merchantBearPrice,thirdPlatformBearPrice,merchantPrice,commission,extra,paymentDetails,status,products,discounts)"+
    				"VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";*/
    String sql="INSERT INTO [dbo].[o]\r\n" + 
    		"           ([store]\r\n" + 
    		"           ,[ref]\r\n" + 
    		"           ,[type]\r\n" + 
    		"           ,[date]\r\n" + 
    		"           ,[mode]\r\n" + 
    		"           ,[item_code]\r\n" + 
    		"           ,[price]\r\n" + 
    		"           ,[disc]\r\n" + 
    		"           ,[coupon]\r\n" + 
    		"           ,[qty]\r\n" + 
    		"           ,[combo]\r\n" + 
    		"           ,[tax]\r\n" + 
    		"           ,[aftertax]\r\n" + 
    		"           ,[uploaddatetime]\r\n" + 
    		"           ,[uploaddate]\r\n" + 
    		"           ,[uploadtime])\r\n" + 
    		"     VALUES\r\n" + 
    		"           (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    PreparedStatement psmt = conn.prepareStatement(sql);
    //先对应SQL语句，给SQL语句传递参数
    psmt.setString(1,  tstore.getStorename());
    psmt.setString(2, tstore.getRef());
    psmt.setString(3, tstore.getType());
    psmt.setString(4,  tstore.getDate());
    psmt.setString(5,  tstore.getMode());
    psmt.setString(6,  tstore.getItem_code());
    psmt.setString(7,tstore.getPrice());
    psmt.setString(8,tstore.getDisc());
    psmt.setString(9,  tstore.getCoupon());
    psmt.setString(10,  tstore.getQty());
    psmt.setString(11,  tstore.getCombo());
    psmt.setString(12,  tstore.getTax());
    psmt.setString(13,  tstore.getAftertax());
    psmt.setString(14,  tstore.getUploadDatetime());
    psmt.setString(15,  tstore.getUploadDate());
    psmt.setString(16,  tstore.getUploadTime());

	Date date=new Date();
	SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String dateStr2 = df2.format(date);
	//psmt.setString(41,dateStr2);



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

public String getItem_code() {
	return item_code;
}

public void setItem_code(String item_code) {
	this.item_code = item_code;
}

public String getPrice() {
	return price;
}

public void setPrice(String price) {
	this.price = price;
}

public String getCoupon() {
	return coupon;
}

public void setCoupon(String coupon) {
	this.coupon = coupon;
}

public String getQty() {
	return qty;
}

public void setQty(String qty) {
	this.qty = qty;
}

public String getCombo() {
	return combo;
}

public void setCombo(String combo) {
	this.combo = combo;
}

public static int truncateO()throws SQLException{
	  
    Connection conn=DBUtil.getConnection();
   String sql="TRUNCATE TABLE o";
    PreparedStatement psmt = conn.prepareStatement(sql);
    //执行SQL语句
    int result=psmt.executeUpdate();
   // conn.close();
    System.out.println("O表 已经被清空了");
    return result;
}

public static int deleteO(String storeid,String orderdate)throws SQLException{
	  
    Connection conn=DBUtil.getConnection();
   String sql="delete from o where store='"+storeid+"' and uploaddatetime like '"+orderdate+"%'";
   System.out.println(sql);
    PreparedStatement psmt = conn.prepareStatement(sql);
    //执行SQL语句
    int result=psmt.executeUpdate();
   // conn.close();
   //System.out.println("T表 旧数据已经被删除了");
    return result;
}


	public static void main(String[] args) {
		String filename="TYS010160202006071337";
		System.out.println(filename.substring(1,9));
		System.out.println(filename.substring(9,21));
		System.out.println(filename.substring(9, 13)+"-"+filename.substring(13, 15)+"-"+filename.substring(15, 17));
		System.out.println(filename.substring(17,21));
	}
}
