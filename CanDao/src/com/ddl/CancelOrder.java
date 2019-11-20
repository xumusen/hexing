package com.ddl;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.entity.Order;
import com.utils.DBUtil;
import com.utils.ExcelData;

import net.sf.json.JSONObject;

public class CancelOrder {
String orderId,status,cancelReason,cancelNote,updateTime;

public String getOrderId() {
	return orderId;
}

public void setOrderId(String orderId) {
	this.orderId = orderId;
}

public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}

public String getCancelReason() {
	return cancelReason;
}

public void setCancelReason(String cancelReason) {
	this.cancelReason = cancelReason;
}

public String getCancelNote() {
	return cancelNote;
}

public void setCancelNote(String cancelNote) {
	this.cancelNote = cancelNote;
}

public String getUpdateTime() {
	return updateTime;
}

public void setUpdateTime(String updateTime) {
	this.updateTime = updateTime;
}

public static void CancelOrder(CancelOrder cancelOrder) throws SQLException {
	
	// TODO Auto-generated constructor stub
	 Connection conn=DBUtil.getConnection();
	String sql=""+
			"insert into cancelorder(orderId,status,cancelReason,cancelNote,updateTime)"+
    		//"(code,pid,vendor,num,[type],childType,title,[content],price,totalAmount,thirdSubsidy,merchantSubsidy,orderid)"+
    		"values (?,?,?,?,?)";
   // System.out.println(sql);
    PreparedStatement psmt = conn.prepareStatement(sql);
    
    //先对应SQL语句，给SQL语句传递参数
    psmt.setString(1, cancelOrder.getOrderId());
    psmt.setString(2,  cancelOrder.getStatus());
    psmt.setString(3, cancelOrder.getCancelReason());
    psmt.setString(4,  cancelOrder.getCancelNote());
    psmt.setString(5,  cancelOrder.getUpdateTime());
/*    psmt.setInt(6,  discounts.getChildType());
    psmt.setString(7,  discounts.getTitle());
    psmt.setString(8,  discounts.getContent());
    psmt.setString(9,  discounts.getPrice());
    psmt.setString(10,  discounts.getTotalAmount());
    psmt.setString(11,  discounts.getThirdSubsidy());
    psmt.setString(12,  discounts.getMerchantSubsidy());
    psmt.setString(13,orderid);*/
    
    


    //执行SQL语句
    psmt.execute();
}
	public static void CancelOrder(String cancelOrder) throws SQLException {
		
		JSONObject jsonobj = JSONObject.fromObject(cancelOrder);
		jsonobj.getString("data");
		JSONObject data = JSONObject.fromObject(jsonobj.getString("data"));
		String orderId=data.getString("orderId");
		String status=data.getString("status");
		String cancelReason=data.getString("cancelReason");
		String cancelNote=data.getString("cancelNote");
		String updateTime=data.getString("updateTime");
		CancelOrder cancelOrder2 = (CancelOrder) JSONObject.toBean(data, CancelOrder.class);
		CancelOrder.CancelOrder(cancelOrder2);
		
	}




}
