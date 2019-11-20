package com.entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.entity.product.Discounts;
import com.entity.product.Products;
import com.utils.DBUtil;

public class Order2 {

	
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getOriginOrderId() {
		return originOrderId;
	}

	public void setOriginOrderId(String originOrderId) {
		this.originOrderId = originOrderId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getSubStoreId() {
		return subStoreId;
	}

	public void setSubStoreId(String subStoreId) {
		this.subStoreId = subStoreId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getBrandId() {
		return brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getCounts() {
		return counts;
	}

	public void setCounts(String counts) {
		this.counts = counts;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getUnionId() {
		return unionId;
	}

	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}

	public String getPaytime() {
		return paytime;
	}

	public void setPaytime(String paytime) {
		this.paytime = paytime;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getRealTimeProductPrice() {
		return realTimeProductPrice;
	}

	public void setRealTimeProductPrice(String realTimeProductPrice) {
		this.realTimeProductPrice = realTimeProductPrice;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getThirdUserId() {
		return thirdUserId;
	}

	public void setThirdUserId(String thirdUserId) {
		this.thirdUserId = thirdUserId;
	}

	public String getRegisterPhone() {
		return registerPhone;
	}

	public void setRegisterPhone(String registerPhone) {
		this.registerPhone = registerPhone;
	}

	public String getIsStoreFirstOrder() {
		return isStoreFirstOrder;
	}

	public void setIsStoreFirstOrder(String isStoreFirstOrder) {
		this.isStoreFirstOrder = isStoreFirstOrder;
	}

	public String getIsBrandFirstOrder() {
		return isBrandFirstOrder;
	}

	public void setIsBrandFirstOrder(String isBrandFirstOrder) {
		this.isBrandFirstOrder = isBrandFirstOrder;
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

	

	public String getUserNote() {
		return userNote;
	}

	public void setUserNote(String userNote) {
		this.userNote = userNote;
	}

	

	public String getDeviceNo() {
		return deviceNo;
	}

	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
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



	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
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



	public String getMerchantPrice() {
		return merchantPrice;
	}

	public void setMerchantPrice(String merchantPrice) {
		this.merchantPrice = merchantPrice;
	}


	String 	orderId,
	orderNo,
	originOrderId,
	type,
	sn,
	storeId,
	subStoreId	,
	storeName,
	brandId,
	brandName,
	counts,
	name,
	openId,
	unionId,
	phone,
	deviceNo,
	fromType,
	orderStatus,
	orderTime,
	orderDate,
	payType,
	isPayed,
	paytime,
	currency,
	price,
	productPrice,
	realTimeProductPrice,
	discountPrice,
	merchantBearPrice,
	merchantPrice,
	userId,
	thirdUserId,
	point,
	pointExpiryDate,
	registerPhone,
	userNote,
	isStoreFirstOrder,
	isBrandFirstOrder;
	public List<PaymentDetails> getPaymentDetails() {
		return paymentDetails;
	}

	public void setPaymentDetails(List<PaymentDetails> paymentDetails) {
		this.paymentDetails = paymentDetails;
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

	List<Products> products;
	List<Discounts> discounts;

	
	

public static void insertOrder(String orderid,Order2 order)throws SQLException{
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
