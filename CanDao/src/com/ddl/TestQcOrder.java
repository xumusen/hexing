package com.ddl;

import java.sql.SQLException;
import java.util.LinkedList;

import com.entity.OrderCollect;
import com.entity.QcOrder;

public class TestQcOrder {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub

		String filename="/Users/jason/OneDrive - cd/项目资料/吉野家/order/1206/response-NCR.json";
		
		LinkedList<QcOrder> returnList=(LinkedList<QcOrder>) Test3.getOrderObjectsByClass("",filename,QcOrder.class);
		System.out.println("一共多少个 "+returnList.size());
		
		for(int i=0;i<returnList.size();i++){
			//OrderCollect.insertOrderCollect(returnList.get(i));
			//System.out.println(returnList.size());
			System.out.println(returnList.get(i).getMsg());
			QcOrder.insertQcOrder(returnList.get(i));
		}
		
	}

}
