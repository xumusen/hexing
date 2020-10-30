package com.entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.utils.DBUtil;

public class Tstore {
String ref,type,mode,date,time,staff,disc,amt,reason,tax,aftertax,storename,uploadDatetime,uploadDate,uploadTime;

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

public String getTime() {
	return time;
}

public void setTime(String time) {
	this.time = time;
}

public String getStaff() {
	return staff;
}

public void setStaff(String staff) {
	this.staff = staff;
}

public String getDisc() {
	return disc;
}

public void setDisc(String disc) {
	this.disc = disc;
}

public String getAmt() {
	return amt;
}

public void setAmt(String amt) {
	this.amt = amt;
}

public String getReason() {
	return reason;
}

public void setReason(String reason) {
	this.reason = reason;
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

public static void insertTstore(Tstore tstore)throws SQLException{
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
    String sql=""+
    		"INSERT INTO [t]"+
    				"(store,uploaddatetime,ref,type,mode,date,time,staff,disc,amt,reason,tax,aftertax,uploaddate,uploadtime)"+
    				"VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    PreparedStatement psmt = conn.prepareStatement(sql);
    //先对应SQL语句，给SQL语句传递参数
    psmt.setString(1,  tstore.getStorename());
    psmt.setString(2, tstore.getUploadDatetime());
    psmt.setString(3,  tstore.getRef());
    psmt.setString(4, tstore.getType());
    psmt.setString(5,  tstore.getMode());
    psmt.setString(6,  tstore.getDate());
    psmt.setString(7,  tstore.getTime());
    psmt.setString(8,  tstore.getStaff());
    psmt.setString(9,  tstore.getDisc());
    psmt.setString(10,  tstore.getAmt());
    psmt.setString(11,  tstore.getReason());
    psmt.setString(12,  tstore.getTax());
    psmt.setString(13,  tstore.getAftertax());
    psmt.setString(14,  tstore.getUploadDate());
    psmt.setString(15,  tstore.getUploadTime());

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

public static int truncateT()throws SQLException{
	  
    Connection conn=DBUtil.getConnection();
   String sql="TRUNCATE TABLE t";
    PreparedStatement psmt = conn.prepareStatement(sql);
    //执行SQL语句
    int result=psmt.executeUpdate();
   // conn.close();
    System.out.println("T表 已经被清空了");
    return result;
}

public static int deleteT(String storeid,String orderdate)throws SQLException{
	  
    Connection conn=DBUtil.getConnection();
   String sql="delete from t where store='"+storeid+"' and uploaddatetime like '"+orderdate+"%'";
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
