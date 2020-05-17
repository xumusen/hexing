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
import com.entity.Title;
import com.utils.DBUtil;
import com.utils.ExcelData;
import com.utils.TimeUtils;

import net.sf.json.JSONObject;

public class CancelOrder {
String orderId,status,cancelReason,cancelNote,updateTime,orderTime;




public String getOrderTime() {
	return orderTime;
}

public void setOrderTime(String orderTime) {
	this.orderTime = orderTime;
}

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


public static void InsertCancelOrder(CancelOrder cancelOrder,Title title) throws SQLException {
	
	// TODO Auto-generated constructor stub
	 Connection conn=DBUtil.getConnection();
	String sql=""+
			"insert into cancelorder(orderId,status,cancelReason,cancelNote,updateTime,orderTime,storeId,accessKey,actionName,timestamp,timestamp2,ticket,sign,serviceType,vendor,json)"+
    		//"(code,pid,vendor,num,[type],childType,title,[content],price,totalAmount,thirdSubsidy,merchantSubsidy,orderid)"+
    		"values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
   // System.out.println(sql);
//,accessKey,actionName,timestamp,ticket,sign,serviceType,vendor,json
	//,?,?,?,?,?,?,?,?
    PreparedStatement psmt = conn.prepareStatement(sql);
    
    //先对应SQL语句，给SQL语句传递参数
    psmt.setString(1, cancelOrder.getOrderId());
    psmt.setString(2,  cancelOrder.getStatus());
    psmt.setString(3, cancelOrder.getCancelReason());
    psmt.setString(4,  cancelOrder.getCancelNote());
    psmt.setString(5,  cancelOrder.getUpdateTime());
    psmt.setString(6,  cancelOrder.getOrderTime());
    psmt.setString(7,  title.getStoreId());
    
    psmt.setString(8,  title.getAccessKey());
    psmt.setString(9,  title.getActionName());
    psmt.setLong(10,  title.getTimestamp());
    psmt.setString(11,  title.getTimestamp2());
    psmt.setString(12,  title.getTicket());
    psmt.setString(13,  title.getSign());
    psmt.setString(14,  title.getServiceType());
    psmt.setString(15,title.getVendor());
    psmt.setString(16, title.getJson());
    //执行SQL语句
    psmt.execute();
}



	public static void CancelOrder(String cancelOrder) throws SQLException {
		
		JSONObject jsonobj = JSONObject.fromObject(cancelOrder);
		jsonobj.getString("data");
		
		String accessKey=jsonobj.getString("accessKey");
		String actionName=jsonobj.getString("actionName");
		long timestamp=jsonobj.getLong("timestamp");
		String ticket=jsonobj.getString("ticket");
		String sign=jsonobj.getString("sign");
		String serviceType=jsonobj.getString("serviceType");
		String vendor=jsonobj.getString("vendor");
		String storeId=jsonobj.getString("storeId");
		Title title= new Title();
		title.setAccessKey(accessKey);
		title.setActionName(actionName);
		title.setServiceType(serviceType);
		title.setSign(sign);
		title.setStoreId(storeId);
		title.setTicket(ticket);
		title.setTimestamp(timestamp);
		title.setTimestamp2(TimeUtils.getTimeStamptoString(timestamp));
		title.setVendor(vendor);
		title.setJson(cancelOrder);
		
		
		JSONObject data = JSONObject.fromObject(jsonobj.getString("data"));
		String orderId=data.getString("orderId");
		String status=data.getString("status");

		
		try {
			String cancelReason=data.getString("cancelReason");
		//	System.out.println("cancelNote =" + cancelNote);
		} catch (Exception e) {
			// TODO: handle exception
			String cancelReason = "";
		}
		
		
		try {
			String cancelNote=data.getString("cancelNote");
		//	System.out.println("cancelNote =" + cancelNote);
		} catch (Exception e) {
			// TODO: handle exception
			String cancelNote = "";
		}
		
		String updateTime=data.getString("updateTime");
	
		
		try {
			String orderTime=data.getString("orderTime");
		//	System.out.println("cancelNote =" + cancelNote);
		} catch (Exception e) {
			// TODO: handle exception
			String orderTime = "";
		}
		CancelOrder cancelOrder2 = (CancelOrder) JSONObject.toBean(data, CancelOrder.class);
		CancelOrder.InsertCancelOrder(cancelOrder2,title);
		Connection conn=DBUtil.getConnection();
		String udpateOrder="update orders set iscancel=1 where orderid=? ";
		PreparedStatement psmt = conn.prepareStatement(udpateOrder);
		psmt.setString(1, orderId);
		int r=psmt.executeUpdate();
		
		String udpateProduct="update products set iscancel=1 where orderid=? ";
		PreparedStatement psmtp = conn.prepareStatement(udpateProduct);
		psmtp.setString(1, orderId);
		int r1=psmtp.executeUpdate();
		
		String udpateCombos="update combos set iscancel=1 where orderid=? ";
		PreparedStatement psmtc = conn.prepareStatement(udpateCombos);
		psmtc.setString(1, orderId);
		int r2=psmtc.executeUpdate();
	}

public static void main(String[] args) {
	String cancenlorder="{\"accessKey\":\"782b5654d5f7a26e\",\"actionName\":\"candao.order.postDineInStatus\",\"timestamp\":1578580506485,\"ticket\":\"4863d892-dfc8-41ff-915b-50fd2b9ba582\",\"sign\":\"c7ea8bc8e2ab6c7beb9f58c8c2e28f82\",\"serviceType\":\"pos\",\"vendor\":\"seito\",\"storeId\":\"Y0074\",\"data\":{\"orderId\":\"202001090066843\",\"status\":-1,\"cancelReason\":501,\"updateTime\":\"2020-01-09 11:32:58\"}}";
	try {
		CancelOrder(cancenlorder);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}


}
