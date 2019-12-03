package com.entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.utils.DBUtil;

public class OrderCollect {

	String columnNum;
	String dm5u_prefix_paas_data_analyse_calculate_count;
	String dm5u_prefix_paas_data_analyse_calculate_discountPrice;
	String dm5u_prefix_paas_data_analyse_calculate_mealFee;
	String dm5u_prefix_paas_data_analyse_calculate_merchantPrice;
	String dm5u_prefix_paas_data_analyse_calculate_productPrice;
	String dm5u_prefix_paas_data_analyse_data_json;
	String dm5u_prefix_paas_data_analyse_errorMsg;
	String dm5u_prefix_paas_data_analyse_is_actived;
	String dm5u_prefix_paas_data_analyse_is_deleted;
	String dm5u_prefix_paas_data_analyse_json_count;
	String dm5u_prefix_paas_data_analyse_json_deliveryFee;
	String dm5u_prefix_paas_data_analyse_json_discountPrice;
	String dm5u_prefix_paas_data_analyse_json_mealFee;
	String dm5u_prefix_paas_data_analyse_json_merchantPrice;
	String dm5u_prefix_paas_data_analyse_json_price;
	String dm5u_prefix_paas_data_analyse_json_productPrice;
	String dm5u_prefix_paas_data_analyse_orderId;
	String dm5u_prefix_paas_data_analyse_orderStatus;
	String dm5u_prefix_paas_data_analyse_orderdate;
	String dm5u_prefix_paas_data_analyse_sort_num;
	String dm5u_prefix_paas_data_analyse_status;
	String dm5u_prefix_paas_data_analyse_storeId;
	String dm5u_prefix_paas_data_analyse_vendor;
	String id;
	
	public String getColumnNum() {
		return columnNum;
	}
	public void setColumnNum(String columnNum) {
		this.columnNum = columnNum;
	}
	public String getDm5u_prefix_paas_data_analyse_calculate_count() {
		return dm5u_prefix_paas_data_analyse_calculate_count;
	}
	public void setDm5u_prefix_paas_data_analyse_calculate_count(String dm5u_prefix_paas_data_analyse_calculate_count) {
		this.dm5u_prefix_paas_data_analyse_calculate_count = dm5u_prefix_paas_data_analyse_calculate_count;
	}
	public String getDm5u_prefix_paas_data_analyse_calculate_discountPrice() {
		return dm5u_prefix_paas_data_analyse_calculate_discountPrice;
	}
	public void setDm5u_prefix_paas_data_analyse_calculate_discountPrice(
			String dm5u_prefix_paas_data_analyse_calculate_discountPrice) {
		this.dm5u_prefix_paas_data_analyse_calculate_discountPrice = dm5u_prefix_paas_data_analyse_calculate_discountPrice;
	}
	public String getDm5u_prefix_paas_data_analyse_calculate_mealFee() {
		return dm5u_prefix_paas_data_analyse_calculate_mealFee;
	}
	public void setDm5u_prefix_paas_data_analyse_calculate_mealFee(String dm5u_prefix_paas_data_analyse_calculate_mealFee) {
		this.dm5u_prefix_paas_data_analyse_calculate_mealFee = dm5u_prefix_paas_data_analyse_calculate_mealFee;
	}
	public String getDm5u_prefix_paas_data_analyse_calculate_merchantPrice() {
		return dm5u_prefix_paas_data_analyse_calculate_merchantPrice;
	}
	public void setDm5u_prefix_paas_data_analyse_calculate_merchantPrice(
			String dm5u_prefix_paas_data_analyse_calculate_merchantPrice) {
		this.dm5u_prefix_paas_data_analyse_calculate_merchantPrice = dm5u_prefix_paas_data_analyse_calculate_merchantPrice;
	}
	public String getDm5u_prefix_paas_data_analyse_calculate_productPrice() {
		return dm5u_prefix_paas_data_analyse_calculate_productPrice;
	}
	public void setDm5u_prefix_paas_data_analyse_calculate_productPrice(
			String dm5u_prefix_paas_data_analyse_calculate_productPrice) {
		this.dm5u_prefix_paas_data_analyse_calculate_productPrice = dm5u_prefix_paas_data_analyse_calculate_productPrice;
	}
	public String getDm5u_prefix_paas_data_analyse_data_json() {
		return dm5u_prefix_paas_data_analyse_data_json;
	}
	public void setDm5u_prefix_paas_data_analyse_data_json(String dm5u_prefix_paas_data_analyse_data_json) {
		this.dm5u_prefix_paas_data_analyse_data_json = dm5u_prefix_paas_data_analyse_data_json;
	}
	public String getDm5u_prefix_paas_data_analyse_errorMsg() {
		return dm5u_prefix_paas_data_analyse_errorMsg;
	}
	public void setDm5u_prefix_paas_data_analyse_errorMsg(String dm5u_prefix_paas_data_analyse_errorMsg) {
		this.dm5u_prefix_paas_data_analyse_errorMsg = dm5u_prefix_paas_data_analyse_errorMsg;
	}
	public String getDm5u_prefix_paas_data_analyse_is_actived() {
		return dm5u_prefix_paas_data_analyse_is_actived;
	}
	public void setDm5u_prefix_paas_data_analyse_is_actived(String dm5u_prefix_paas_data_analyse_is_actived) {
		this.dm5u_prefix_paas_data_analyse_is_actived = dm5u_prefix_paas_data_analyse_is_actived;
	}
	public String getDm5u_prefix_paas_data_analyse_is_deleted() {
		return dm5u_prefix_paas_data_analyse_is_deleted;
	}
	public void setDm5u_prefix_paas_data_analyse_is_deleted(String dm5u_prefix_paas_data_analyse_is_deleted) {
		this.dm5u_prefix_paas_data_analyse_is_deleted = dm5u_prefix_paas_data_analyse_is_deleted;
	}
	public String getDm5u_prefix_paas_data_analyse_json_count() {
		return dm5u_prefix_paas_data_analyse_json_count;
	}
	public void setDm5u_prefix_paas_data_analyse_json_count(String dm5u_prefix_paas_data_analyse_json_count) {
		this.dm5u_prefix_paas_data_analyse_json_count = dm5u_prefix_paas_data_analyse_json_count;
	}
	public String getDm5u_prefix_paas_data_analyse_json_deliveryFee() {
		return dm5u_prefix_paas_data_analyse_json_deliveryFee;
	}
	public void setDm5u_prefix_paas_data_analyse_json_deliveryFee(String dm5u_prefix_paas_data_analyse_json_deliveryFee) {
		this.dm5u_prefix_paas_data_analyse_json_deliveryFee = dm5u_prefix_paas_data_analyse_json_deliveryFee;
	}
	public String getDm5u_prefix_paas_data_analyse_json_discountPrice() {
		return dm5u_prefix_paas_data_analyse_json_discountPrice;
	}
	public void setDm5u_prefix_paas_data_analyse_json_discountPrice(
			String dm5u_prefix_paas_data_analyse_json_discountPrice) {
		this.dm5u_prefix_paas_data_analyse_json_discountPrice = dm5u_prefix_paas_data_analyse_json_discountPrice;
	}
	public String getDm5u_prefix_paas_data_analyse_json_mealFee() {
		return dm5u_prefix_paas_data_analyse_json_mealFee;
	}
	public void setDm5u_prefix_paas_data_analyse_json_mealFee(String dm5u_prefix_paas_data_analyse_json_mealFee) {
		this.dm5u_prefix_paas_data_analyse_json_mealFee = dm5u_prefix_paas_data_analyse_json_mealFee;
	}
	public String getDm5u_prefix_paas_data_analyse_json_merchantPrice() {
		return dm5u_prefix_paas_data_analyse_json_merchantPrice;
	}
	public void setDm5u_prefix_paas_data_analyse_json_merchantPrice(
			String dm5u_prefix_paas_data_analyse_json_merchantPrice) {
		this.dm5u_prefix_paas_data_analyse_json_merchantPrice = dm5u_prefix_paas_data_analyse_json_merchantPrice;
	}
	public String getDm5u_prefix_paas_data_analyse_json_price() {
		return dm5u_prefix_paas_data_analyse_json_price;
	}
	public void setDm5u_prefix_paas_data_analyse_json_price(String dm5u_prefix_paas_data_analyse_json_price) {
		this.dm5u_prefix_paas_data_analyse_json_price = dm5u_prefix_paas_data_analyse_json_price;
	}
	public String getDm5u_prefix_paas_data_analyse_json_productPrice() {
		return dm5u_prefix_paas_data_analyse_json_productPrice;
	}
	public void setDm5u_prefix_paas_data_analyse_json_productPrice(String dm5u_prefix_paas_data_analyse_json_productPrice) {
		this.dm5u_prefix_paas_data_analyse_json_productPrice = dm5u_prefix_paas_data_analyse_json_productPrice;
	}
	public String getDm5u_prefix_paas_data_analyse_orderId() {
		return dm5u_prefix_paas_data_analyse_orderId;
	}
	public void setDm5u_prefix_paas_data_analyse_orderId(String dm5u_prefix_paas_data_analyse_orderId) {
		this.dm5u_prefix_paas_data_analyse_orderId = dm5u_prefix_paas_data_analyse_orderId;
	}
	public String getDm5u_prefix_paas_data_analyse_orderStatus() {
		return dm5u_prefix_paas_data_analyse_orderStatus;
	}
	public void setDm5u_prefix_paas_data_analyse_orderStatus(String dm5u_prefix_paas_data_analyse_orderStatus) {
		this.dm5u_prefix_paas_data_analyse_orderStatus = dm5u_prefix_paas_data_analyse_orderStatus;
	}
	public String getDm5u_prefix_paas_data_analyse_orderdate() {
		return dm5u_prefix_paas_data_analyse_orderdate;
	}
	public void setDm5u_prefix_paas_data_analyse_orderdate(String dm5u_prefix_paas_data_analyse_orderdate) {
		this.dm5u_prefix_paas_data_analyse_orderdate = dm5u_prefix_paas_data_analyse_orderdate;
	}
	public String getDm5u_prefix_paas_data_analyse_sort_num() {
		return dm5u_prefix_paas_data_analyse_sort_num;
	}
	public void setDm5u_prefix_paas_data_analyse_sort_num(String dm5u_prefix_paas_data_analyse_sort_num) {
		this.dm5u_prefix_paas_data_analyse_sort_num = dm5u_prefix_paas_data_analyse_sort_num;
	}
	public String getDm5u_prefix_paas_data_analyse_status() {
		return dm5u_prefix_paas_data_analyse_status;
	}
	public void setDm5u_prefix_paas_data_analyse_status(String dm5u_prefix_paas_data_analyse_status) {
		this.dm5u_prefix_paas_data_analyse_status = dm5u_prefix_paas_data_analyse_status;
	}
	public String getDm5u_prefix_paas_data_analyse_storeId() {
		return dm5u_prefix_paas_data_analyse_storeId;
	}
	public void setDm5u_prefix_paas_data_analyse_storeId(String dm5u_prefix_paas_data_analyse_storeId) {
		this.dm5u_prefix_paas_data_analyse_storeId = dm5u_prefix_paas_data_analyse_storeId;
	}
	public String getDm5u_prefix_paas_data_analyse_vendor() {
		return dm5u_prefix_paas_data_analyse_vendor;
	}
	public void setDm5u_prefix_paas_data_analyse_vendor(String dm5u_prefix_paas_data_analyse_vendor) {
		this.dm5u_prefix_paas_data_analyse_vendor = dm5u_prefix_paas_data_analyse_vendor;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	
	public static void insertPaymentDetails(OrderCollect orderCollect)throws SQLException{
        //首先拿到数据库的连接
        Connection conn=DBUtil.getConnection();
     /*   String sql="" + 
                "insert into status"+
                "(title,value,dateTime) "+
                "values(?,?,?)";//参数用?表示，相当于占位符;
*/	        
        String sql=""+
        		"INSERT INTO [dbo].[orderCollection]"+
        		           "([columnNum],[dm5u_prefix_paas_data_analyse_calculate_count],[dm5u_prefix_paas_data_analyse_calculate_discountPrice],[dm5u_prefix_paas_data_analyse_calculate_mealFee],[dm5u_prefix_paas_data_analyse_calculate_merchantPrice],[dm5u_prefix_paas_data_analyse_calculate_productPrice],[dm5u_prefix_paas_data_analyse_data_json],[dm5u_prefix_paas_data_analyse_errorMsg],[dm5u_prefix_paas_data_analyse_is_actived],[dm5u_prefix_paas_data_analyse_is_deleted],[dm5u_prefix_paas_data_analyse_json_count],[dm5u_prefix_paas_data_analyse_json_deliveryFee],[dm5u_prefix_paas_data_analyse_json_discountPrice],[dm5u_prefix_paas_data_analyse_json_mealFee],[dm5u_prefix_paas_data_analyse_json_merchantPrice],[dm5u_prefix_paas_data_analyse_json_price],[dm5u_prefix_paas_data_analyse_json_productPrice],[dm5u_prefix_paas_data_analyse_orderId],[dm5u_prefix_paas_data_analyse_orderStatus],[dm5u_prefix_paas_data_analyse_orderdate],[dm5u_prefix_paas_data_analyse_sort_num],[dm5u_prefix_paas_data_analyse_status],[dm5u_prefix_paas_data_analyse_storeId],[dm5u_prefix_paas_data_analyse_vendor],[id])"+
        		           "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement psmt = conn.prepareStatement(sql);
        
        //先对应SQL语句，给SQL语句传递参数
        psmt.setString(1,  orderCollect.getColumnNum());
        psmt.setString(2, orderCollect.getDm5u_prefix_paas_data_analyse_calculate_count());
        psmt.setString(3, orderCollect.getDm5u_prefix_paas_data_analyse_calculate_discountPrice());
        psmt.setString(4, orderCollect.getDm5u_prefix_paas_data_analyse_calculate_mealFee());
        psmt.setString(5, orderCollect.getDm5u_prefix_paas_data_analyse_calculate_merchantPrice());
        psmt.setString(6, orderCollect.getDm5u_prefix_paas_data_analyse_calculate_productPrice());
        psmt.setString(7, orderCollect.getDm5u_prefix_paas_data_analyse_data_json());
        psmt.setString(8, orderCollect.getDm5u_prefix_paas_data_analyse_errorMsg());
        psmt.setString(9, orderCollect.getDm5u_prefix_paas_data_analyse_is_actived());
        psmt.setString(10, orderCollect.getDm5u_prefix_paas_data_analyse_is_deleted());
        psmt.setString(11, orderCollect.getDm5u_prefix_paas_data_analyse_json_count());
        psmt.setString(12, orderCollect.getDm5u_prefix_paas_data_analyse_json_deliveryFee());
        psmt.setString(13, orderCollect.getDm5u_prefix_paas_data_analyse_json_discountPrice());
        psmt.setString(14, orderCollect.getDm5u_prefix_paas_data_analyse_json_mealFee());
        psmt.setString(15, orderCollect.getDm5u_prefix_paas_data_analyse_json_merchantPrice());
        psmt.setString(16, orderCollect.getDm5u_prefix_paas_data_analyse_json_price());
        psmt.setString(17, orderCollect.getDm5u_prefix_paas_data_analyse_json_productPrice());
        psmt.setString(18, orderCollect.getDm5u_prefix_paas_data_analyse_orderId());
        psmt.setString(19, orderCollect.getDm5u_prefix_paas_data_analyse_orderStatus());
        psmt.setString(20, orderCollect.getDm5u_prefix_paas_data_analyse_orderdate());
        psmt.setString(21, orderCollect.getDm5u_prefix_paas_data_analyse_sort_num());
        psmt.setString(22, orderCollect.getDm5u_prefix_paas_data_analyse_status());
        psmt.setString(23, orderCollect.getDm5u_prefix_paas_data_analyse_storeId());
        psmt.setString(24, orderCollect.getDm5u_prefix_paas_data_analyse_vendor());
        psmt.setString(25, orderCollect.getId());
        
        
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
