package com.entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.entity.product.Discounts;
import com.entity.product.Products;
import com.utils.DBUtil;

public class Order {

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getThirdSn() {
		return thirdSn;
	}

	public void setThirdSn(String thirdSn) {
		this.thirdSn = thirdSn;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getFromType() {
		return fromType;
	}

	public void setFromType(String fromType) {
		this.fromType = fromType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getTakeNo() {
		return takeNo;
	}

	public void setTakeNo(String takeNo) {
		this.takeNo = takeNo;
	}

	public String getTableNum() {
		return tableNum;
	}

	public void setTableNum(String tableNum) {
		this.tableNum = tableNum;
	}

	public String getUserNote() {
		return userNote;
	}

	public void setUserNote(String userNote) {
		this.userNote = userNote;
	}

	public String getPeopleNum() {
		return peopleNum;
	}

	public void setPeopleNum(String peopleNum) {
		this.peopleNum = peopleNum;
	}

	public String getTableNo() {
		return tableNo;
	}

	public void setTableNo(String tableNo) {
		this.tableNo = tableNo;
	}

	public String getDeviceNo() {
		return deviceNo;
	}

	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getStaffNo() {
		return staffNo;
	}

	public void setStaffNo(String staffNo) {
		this.staffNo = staffNo;
	}

	public String getStaffBane() {
		return staffBane;
	}

	public void setStaffBane(String staffBane) {
		this.staffBane = staffBane;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getPoint() {
		return point;
	}

	public void setPoint(String point) {
		this.point = point;
	}

	public String getPointExpiryDate() {
		return pointExpiryDate;
	}

	public void setPointExpiryDate(String pointExpiryDate) {
		this.pointExpiryDate = pointExpiryDate;
	}

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

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getPosOrderType() {
		return posOrderType;
	}

	public void setPosOrderType(String posOrderType) {
		this.posOrderType = posOrderType;
	}

	public String getIsPayed() {
		return isPayed;
	}

	public void setIsPayed(String isPayed) {
		this.isPayed = isPayed;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getIsInvoice() {
		return isInvoice;
	}

	public void setIsInvoice(String isInvoice) {
		this.isInvoice = isInvoice;
	}

	public String getInvoiceDesc() {
		return invoiceDesc;
	}

	public void setInvoiceDesc(String invoiceDesc) {
		this.invoiceDesc = invoiceDesc;
	}

	public String getTaxNo() {
		return taxNo;
	}

	public void setTaxNo(String taxNo) {
		this.taxNo = taxNo;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getDeliveryFee() {
		return deliveryFee;
	}

	public void setDeliveryFee(String deliveryFee) {
		this.deliveryFee = deliveryFee;
	}

	public String getMealFee() {
		return mealFee;
	}

	public void setMealFee(String mealFee) {
		this.mealFee = mealFee;
	}

	public String getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}

	public String getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(String discountPrice) {
		this.discountPrice = discountPrice;
	}

	public String getMerchantBearPrice() {
		return merchantBearPrice;
	}

	public void setMerchantBearPrice(String merchantBearPrice) {
		this.merchantBearPrice = merchantBearPrice;
	}

	public String getThirdPlatformBearPrice() {
		return thirdPlatformBearPrice;
	}

	public void setThirdPlatformBearPrice(String thirdPlatformBearPrice) {
		this.thirdPlatformBearPrice = thirdPlatformBearPrice;
	}

	public String getMerchantPrice() {
		return merchantPrice;
	}

	public void setMerchantPrice(String merchantPrice) {
		this.merchantPrice = merchantPrice;
	}

	public String getCommission() {
		return commission;
	}

	public void setCommission(String commission) {
		this.commission = commission;
	}

	public String getExtra() {
		return extra;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}

	String 	orderid,
	thirdSn,
	storeId,
	fromType,
	name,
	phone,
	takeNo,
	tableNum,
	userNote,
	peopleNum,
	tableNo,
	deviceNo,
	staffId,
	staffNo,
	staffBane,
	memberId,
	point,
	pointExpiryDate,
	orderTime,
	orderDate,
	orderStatus,
	orderType,
	posOrderType,
	isPayed,
	payType,
	isInvoice,
	invoiceDesc,
	taxNo,
	price,
	deliveryFee,
	mealFee,
	productPrice,
	discountPrice,
	merchantBearPrice,
	thirdPlatformBearPrice,
	merchantPrice,
	commission,
	extra;
	public List<PaymentDetails> getPaymentDetails() {
		return paymentDetails;
	}

	public void setPaymentDetails(List<PaymentDetails> paymentDetails) {
		this.paymentDetails = paymentDetails;
	}

	public List<Status> getStatus() {
		return status;
	}

	public void setStatus(List<Status> status) {
		this.status = status;
	}

	public List<Products> getProducts() {
		return products;
	}

	public void setProducts(List<Products> products) {
		this.products = products;
	}

	public List<Discounts> getDiscounts() {
		return discounts;
	}

	public void setDiscounts(List<Discounts> discounts) {
		this.discounts = discounts;
	}

	List<PaymentDetails> paymentDetails;
	List<Status> status;
	List<Products> products;
	List<Discounts> discounts;

	
	

public static void insertOrder(String orderid,Order order)throws SQLException{
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
    		"INSERT INTO [order]"+
    				"(orderid,thirdSn,storeId,fromType,name,phone,takeNo,tableNum,userNote,peopleNum,tableNo,deviceNo,staffId,staffNo,staffBane,memberId,point,pointExpiryDate,orderTime,orderDate,orderStatus,orderType,posOrderType,isPayed,payType,isInvoice,invoiceDesc,taxNo,price,deliveryFee,mealFee,productPrice,discountPrice,merchantBearPrice,thirdPlatformBearPrice,merchantPrice,commission,extra)"+
    				"VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    PreparedStatement psmt = conn.prepareStatement(sql);
    
    //先对应SQL语句，给SQL语句传递参数
    psmt.setString(1,  orderid);
    psmt.setString(2,  order.getThirdSn());
    psmt.setString(3,  order.getStoreId());
    psmt.setString(4,  order.getFromType());
    psmt.setString(5,  order.getName());
    psmt.setString(6,  order.getPhone());
    psmt.setString(7,  order.getTakeNo());
    psmt.setString(8,  order.getTableNum());
    psmt.setString(9,  order.getUserNote());
    psmt.setString(10,  order.getPeopleNum());
    psmt.setString(11,  order.getTableNo());
    psmt.setString(12,  order.getDeviceNo());
    psmt.setString(13,  order.getStaffId());
    psmt.setString(14,  order.getStaffNo());
    psmt.setString(15,  order.getStaffBane());
    psmt.setString(16,  order.getMemberId());
    psmt.setString(17,  order.getPoint());
    psmt.setString(18,  order.getPointExpiryDate());
    psmt.setString(19,  order.getOrderTime());
    psmt.setString(20,  order.getOrderDate());
    psmt.setString(21,  order.getOrderStatus());
    psmt.setString(22,  order.getOrderType());
    psmt.setString(23,  order.getPosOrderType());
    psmt.setString(24,  order.getIsPayed());
    psmt.setString(25,  order.getPayType());
    psmt.setString(26,  order.getIsInvoice());
    psmt.setString(27,  order.getInvoiceDesc());
    psmt.setString(28,  order.getTaxNo());
    psmt.setString(29,  order.getPrice());
    psmt.setString(30,  order.getDeliveryFee());
    psmt.setString(31,  order.getMealFee());
    psmt.setString(32,  order.getProductPrice());
    psmt.setString(33,  order.getDiscountPrice());
    psmt.setString(34,  order.getMerchantBearPrice());
    psmt.setString(35,  order.getThirdPlatformBearPrice());
    psmt.setString(36,  order.getMerchantPrice());
    psmt.setString(37,  order.getCommission());
    psmt.setString(38,  order.getExtra());
/*    psmt.setString(39,  order.getPaymentDetails());
    psmt.setString(40,  order.getStatus());
    psmt.setString(41,  order.getProducts());
    psmt.setString(42,  order.getDiscounts());
    */



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
