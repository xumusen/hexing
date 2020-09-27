package com.entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.utils.DBUtil;

public class OrderInfo {

	float brandid;
	boolean iscustomordernopush;
String storeid,
brandname,
orderid,
thirdsn,
registeredamount,
realtimeproductprice,
mealfee,
deliveryfee,
fromtype,
merchantprice,
extorderid,
fromname,
flag,
provinceid,
provincename,
cityid,
cityname,
cdbrandid,
cdbrandname,
cdplatformkey,
cdplatformname,
paasstoreid,
cdstorename,
extstoreid,
extstorename,
orderdate,
tc,
price;

public String getStoreid() {
	return storeid;
}

public void setStoreid(String storeid) {
	this.storeid = storeid;
}

public float getBrandid() {
	return brandid;
}

public void setBrandid(float brandid) {
	this.brandid = brandid;
}

public String getBrandname() {
	return brandname;
}

public void setBrandname(String brandname) {
	this.brandname = brandname;
}

public String getOrderid() {
	return orderid;
}

public void setOrderid(String orderid) {
	this.orderid = orderid;
}

public String getThirdsn() {
	return thirdsn;
}

public void setThirdsn(String thirdsn) {
	this.thirdsn = thirdsn;
}

public String getRegisteredamount() {
	return registeredamount;
}

public void setRegisteredamount(String registeredamount) {
	this.registeredamount = registeredamount;
}

public boolean getIscustomordernopush() {
	return iscustomordernopush;
}

public void setIscustomordernopush(boolean iscustomordernopush) {
	this.iscustomordernopush = iscustomordernopush;
}

public String getRealtimeproductprice() {
	return realtimeproductprice;
}

public void setRealtimeproductprice(String realtimeproductprice) {
	this.realtimeproductprice = realtimeproductprice;
}

public String getMealfee() {
	return mealfee;
}

public void setMealfee(String mealfee) {
	this.mealfee = mealfee;
}

public String getDeliveryfee() {
	return deliveryfee;
}

public void setDeliveryfee(String deliveryfee) {
	this.deliveryfee = deliveryfee;
}

public String getFromtype() {
	return fromtype;
}

public void setFromtype(String fromtype) {
	this.fromtype = fromtype;
}

public String getMerchantprice() {
	return merchantprice;
}

public void setMerchantprice(String merchantprice) {
	this.merchantprice = merchantprice;
}

public String getExtorderid() {
	return extorderid;
}

public void setExtorderid(String extorderid) {
	this.extorderid = extorderid;
}

public String getFromname() {
	return fromname;
}

public void setFromname(String fromname) {
	this.fromname = fromname;
}

public String getFlag() {
	return flag;
}

public void setFlag(String flag) {
	this.flag = flag;
}

public String getProvinceid() {
	return provinceid;
}

public void setProvinceid(String provinceid) {
	this.provinceid = provinceid;
}

public String getProvincename() {
	return provincename;
}

public void setProvincename(String provincename) {
	this.provincename = provincename;
}

public String getCityid() {
	return cityid;
}

public void setCityid(String cityid) {
	this.cityid = cityid;
}

public String getCityname() {
	return cityname;
}

public void setCityname(String cityname) {
	this.cityname = cityname;
}

public String getCdbrandid() {
	return cdbrandid;
}

public void setCdbrandid(String cdbrandid) {
	this.cdbrandid = cdbrandid;
}

public String getCdbrandname() {
	return cdbrandname;
}

public void setCdbrandname(String cdbrandname) {
	this.cdbrandname = cdbrandname;
}

public String getCdplatformkey() {
	return cdplatformkey;
}

public void setCdplatformkey(String cdplatformkey) {
	this.cdplatformkey = cdplatformkey;
}

public String getCdplatformname() {
	return cdplatformname;
}

public void setCdplatformname(String cdplatformname) {
	this.cdplatformname = cdplatformname;
}

public String getPaasstoreid() {
	return paasstoreid;
}

public void setPaasstoreid(String paasstoreid) {
	this.paasstoreid = paasstoreid;
}

public String getCdstorename() {
	return cdstorename;
}

public void setCdstorename(String cdstorename) {
	this.cdstorename = cdstorename;
}

public String getExtstoreid() {
	return extstoreid;
}

public void setExtstoreid(String extstoreid) {
	this.extstoreid = extstoreid;
}

public String getExtstorename() {
	return extstorename;
}

public void setExtstorename(String extstorename) {
	this.extstorename = extstorename;
}

public String getOrderdate() {
	return orderdate;
}

public void setOrderdate(String orderdate) {
	this.orderdate = orderdate;
}

public String getTc() {
	return tc;
}

public void setTc(String tc) {
	this.tc = tc;
}

public String getPrice() {
	return price;
}

public void setPrice(String price) {
	this.price = price;
}


public static void insertOrderInfo(OrderInfo orderinfo)throws SQLException{
  
    Connection conn=DBUtil.getConnection();
   String sql="INSERT INTO  dbo . order_info \r\n" + 
   		"           ( storeid \r\n" + 
   		"           , brandid \r\n" + 
   		"           , brandname \r\n" + 
   		"           , orderid \r\n" + 
   		"           , thirdsn \r\n" + 
   		"           , registeredamount \r\n" + 
   		"           , iscustomordernopush \r\n" + 
   		"           , realtimeproductprice \r\n" + 
   		"           , mealfee \r\n" + 
   		"           , deliveryfee \r\n" + 
   		"           , fromtype \r\n" + 
   		"           , merchantprice \r\n" + 
   		"           , extorderid \r\n" + 
   		"           , fromname \r\n" + 
   		"           , flag \r\n" + 
   		"           , provinceid \r\n" + 
   		"           , provincename \r\n" + 
   		"           , cityid \r\n" + 
   		"           , cityname \r\n" + 
   		"           , cdbrandid \r\n" + 
   		"           , cdbrandname \r\n" + 
   		"           , cdplatformkey \r\n" + 
   		"           , cdplatformname \r\n" + 
   		"           , paasstoreid \r\n" + 
   		"           , cdstorename \r\n" + 
   		"           , extstoreid \r\n" + 
   		"           , extstorename \r\n" + 
   		"           , orderdate \r\n" + 
   		"           , tc \r\n" + 
   		"           , price )\r\n" + 
   		"     VALUES\r\n" + 
   		"(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    PreparedStatement psmt = conn.prepareStatement(sql);
    //先对应SQL语句，给SQL语句传递参数
    psmt.setString(1,  orderinfo.getStoreid());
    psmt.setFloat(2,  orderinfo.getBrandid());
    psmt.setString(3,  orderinfo.getBrandname());
    psmt.setString(4,  orderinfo.getOrderid());
    psmt.setString(5,  orderinfo.getThirdsn());
    psmt.setString(6,  orderinfo.getRegisteredamount());
    psmt.setBoolean(7,  orderinfo.getIscustomordernopush());
    psmt.setString(8,  orderinfo.getRealtimeproductprice());
    psmt.setString(9,  orderinfo.getMealfee());
    psmt.setString(10,  orderinfo.getDeliveryfee());
    psmt.setString(11,  orderinfo.getFromtype());
    psmt.setString(12,  orderinfo.getMerchantprice());
    psmt.setString(13,  orderinfo.getExtorderid());
    psmt.setString(14,  orderinfo.getFromname());
    psmt.setString(15,  orderinfo.getFlag());
    psmt.setString(16,  orderinfo.getProvinceid());
    psmt.setString(17,  orderinfo.getProvincename());
    psmt.setString(18,  orderinfo.getCityid());
    psmt.setString(19,  orderinfo.getCityname());
    psmt.setString(20,  orderinfo.getCdbrandid());
    psmt.setString(21,  orderinfo.getCdbrandname());
    psmt.setString(22,  orderinfo.getCdplatformkey());
    psmt.setString(23,  orderinfo.getCdplatformname());
    psmt.setString(24,  orderinfo.getPaasstoreid());
    psmt.setString(25,  orderinfo.getCdstorename());
    psmt.setString(26,  orderinfo.getExtstoreid());
    psmt.setString(27,  orderinfo.getExtstorename());
    psmt.setString(28,  orderinfo.getOrderdate());
    psmt.setString(29,  orderinfo.getTc());
    psmt.setString(30,  orderinfo.getPrice());
  


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
