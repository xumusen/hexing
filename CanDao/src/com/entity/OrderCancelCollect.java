package com.entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;

import com.ddl.Test;
import com.ddl.Test3;
import com.utils.DBUtil;

public class OrderCancelCollect {

	String columnNum;//
	String id;//
	String dm5u_prefix_paas_data_analyse_is_actived; //
	String dm5u_prefix_paas_data_analyse_sort_num;//
	String dm5u_prefix_paas_data_analyse_is_deleted;//
	String dm5u_prefix_paas_data_analyse_create_date;
	String dm5u_prefix_paas_data_analyse_data_json;

	public String getColumnNum() {
		return columnNum;
	}

	public void setColumnNum(String columnNum) {
		this.columnNum = columnNum;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDm5u_prefix_paas_data_analyse_is_actived() {
		return dm5u_prefix_paas_data_analyse_is_actived;
	}

	public void setDm5u_prefix_paas_data_analyse_is_actived(String dm5u_prefix_paas_data_analyse_is_actived) {
		this.dm5u_prefix_paas_data_analyse_is_actived = dm5u_prefix_paas_data_analyse_is_actived;
	}

	public String getDm5u_prefix_paas_data_analyse_sort_num() {
		return dm5u_prefix_paas_data_analyse_sort_num;
	}

	public void setDm5u_prefix_paas_data_analyse_sort_num(String dm5u_prefix_paas_data_analyse_sort_num) {
		this.dm5u_prefix_paas_data_analyse_sort_num = dm5u_prefix_paas_data_analyse_sort_num;
	}

	public String getDm5u_prefix_paas_data_analyse_is_deleted() {
		return dm5u_prefix_paas_data_analyse_is_deleted;
	}

	public void setDm5u_prefix_paas_data_analyse_is_deleted(String dm5u_prefix_paas_data_analyse_is_deleted) {
		this.dm5u_prefix_paas_data_analyse_is_deleted = dm5u_prefix_paas_data_analyse_is_deleted;
	}

	public String getDm5u_prefix_paas_data_analyse_create_date() {
		return dm5u_prefix_paas_data_analyse_create_date;
	}

	public void setDm5u_prefix_paas_data_analyse_create_date(String dm5u_prefix_paas_data_analyse_create_date) {
		this.dm5u_prefix_paas_data_analyse_create_date = dm5u_prefix_paas_data_analyse_create_date;
	}

	public String getDm5u_prefix_paas_data_analyse_data_json() {
		return dm5u_prefix_paas_data_analyse_data_json;
	}

	public void setDm5u_prefix_paas_data_analyse_data_json(String dm5u_prefix_paas_data_analyse_data_json) {
		this.dm5u_prefix_paas_data_analyse_data_json = dm5u_prefix_paas_data_analyse_data_json;
	}

	public static void insertOrderCancelCollect(OrderCancelCollect orderCollect) throws SQLException {
		// 首先拿到数据库的连接
		Connection conn = DBUtil.getConnection();
		/*
		 * String sql="" + "insert into status"+ "(title,value,dateTime) "+
		 * "values(?,?,?)";//参数用?表示，相当于占位符;
		 */
		String sql = ""+
					"INSERT INTO [dbo].[orderCancelCollect]"+
		            "([columnNum],[id],[dm5u_prefix_paas_data_analyse_is_actived],[dm5u_prefix_paas_data_analyse_sort_num],[dm5u_prefix_paas_data_analyse_is_deleted],[dm5u_prefix_paas_data_analyse_create_date],[dm5u_prefix_paas_data_analyse_data_json])"
		    			+ "values(?,?,?,?,?,?,?)";
		PreparedStatement psmt = conn.prepareStatement(sql);

		// 先对应SQL语句，给SQL语句传递参数
		psmt.setString(1, orderCollect.getColumnNum());
		psmt.setString(2, orderCollect.getId());
		psmt.setString(3, orderCollect.getDm5u_prefix_paas_data_analyse_is_actived());
		psmt.setString(4, orderCollect.getDm5u_prefix_paas_data_analyse_sort_num());
		psmt.setString(5, orderCollect.getDm5u_prefix_paas_data_analyse_is_deleted());
		psmt.setString(6, orderCollect.getDm5u_prefix_paas_data_analyse_create_date());
		psmt.setString(7, orderCollect.getDm5u_prefix_paas_data_analyse_data_json());
		// 执行SQL语句
		psmt.execute();
		/**
		 * prepareStatement这个方法会将SQL语句加载到驱动程序conn集成程序中，但是并不直接执行
		 * 而是当它调用execute()方法的时候才真正执行；
		 * 
		 * 上面SQL中的参数用?表示，相当于占位符，然后在对参数进行赋值。 当真正执行时，这些参数会加载在SQL语句中，把SQL语句拼接完整才去执行。
		 * 这样就会减少对数据库的操作
		 */
	}
	
	public static void main(String[] args) throws SQLException {
		String filename="/Users/jason/OneDrive - cd/项目资料/吉野家/order/1202/response-cancel1127-1202.json";
		
		LinkedList<OrderCancelCollect> returnList=(LinkedList<OrderCancelCollect>) Test3.getOrderObjectsByClass("",filename,OrderCancelCollect.class);
		System.out.println(returnList.size());
		for(int i=0;i<returnList.size();i++){
			OrderCancelCollect.insertOrderCancelCollect(returnList.get(i));
			System.out.println(returnList.get(i).getDm5u_prefix_paas_data_analyse_data_json());
		}
		
	}

}
