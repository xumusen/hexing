package com.ddl;

import java.sql.SQLException;

import com.entity.PaymentDetails;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Testpay {

	
	public static void postDineorder(String orderId,String storeId,String payment,String orderDate,String orderTime ) throws SQLException{

		JSONArray jsonArray = JSONArray.fromObject(payment); // 位于data下面

		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject paymentDetail = jsonArray.getJSONObject(i);// 获取数组
			PaymentDetails details = (PaymentDetails) JSONObject.toBean(paymentDetail, PaymentDetails.class);

			try {
				details.insertPaymentDetails(orderId, details,orderDate,orderTime,"",storeId);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/*
			 * System.out.println("payType   " + details.getPayType());
			 * System.out.println("type   " + details.getType());
			 * System.out.println("money   " + details.getMoney());
			 * System.out.println("typeName   " + details.getTypeName());
			 */
		}

	}
}
