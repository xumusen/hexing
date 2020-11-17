package com.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.UUID;

import com.alibaba.fastjson.JSONArray;

import http_post.HttpRequest;
import md5.MD5;
import net.sf.json.JSONObject;

public class PostOrder {
	
	
	public static void postOldOrder(String json) {

		String accessKey="4ca533f4b7f5da07";
		String secret="2482df65c5ecb83be8166805e9dc5c3b";
		JSONObject jsonObject = JSONObject.fromObject(json);
		String orderdata = jsonObject.getString("data");
		String actionName=jsonObject.getString("actionName");
		long timestamps = System.currentTimeMillis();
		String Ordersign = MD5.getMD5Str(
				accessKey + actionName + secret + timestamps + (StringUtil.isNullOrBlank(orderdata) ? "" : orderdata));

		JSONObject jsonNewObj = new JSONObject();
		jsonNewObj.put("actionName", jsonObject.getString("actionName"));
		jsonNewObj.put("data", orderdata);
		jsonNewObj.put("accessKey", accessKey);
		jsonNewObj.put("sign", Ordersign);
		jsonNewObj.put("timestamp", timestamps);
		jsonNewObj.put("serviceType", jsonObject.getString("serviceType"));
		jsonNewObj.put("ticket", UUID.randomUUID().toString());
		jsonNewObj.put("vendor", jsonObject.getString("vendor"));
		jsonNewObj.put("storeId", jsonObject.getString("storeId"));
		System.out.println(jsonNewObj);
		
		String sr = HttpRequest.sendPost("http://open-api.hophing.cn/api",jsonNewObj);

		System.out.println("result ----->  "+sr);
		
	}
	
	public static void orderCancel(String orderId,int status,String cancelNote) {

	
		JSONObject jsonNewObj = new JSONObject();
		JSONArray brandIds=new JSONArray();
		brandIds.add( 26002055);
		jsonNewObj.put("accessKey", "e0c308be8c579adc");
		jsonNewObj.put("ticket", "b19cbedd-e90a-4a7a-9567-2ccc6dcfec42");
		jsonNewObj.put("actionName", "candao.order.ztPostDineInStatus");
		jsonNewObj.put("fromType", 45);
		jsonNewObj.put("platformKey", "b93a3d359a795d9c");
		jsonNewObj.put("langType", 0);
		jsonNewObj.put("ip", "10.238.184.1");
		jsonNewObj.put("brandIds", brandIds);
		
		JSONObject content=new JSONObject();
		content.put("orderId", orderId);
		content.put("status", status);
		content.put("cancelReason", 3);
		content.put("cancelNote", cancelNote);
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = sdf.format(ts);
		content.put("updateTime", time);
		content.put("orderTime", "2020-10-10 17:41:47");
		jsonNewObj.put("content", content);
		System.out.println(jsonNewObj);
		
		String sr = HttpRequest.sendPost("http://101.132.90.161:80/ZtApiAction",jsonNewObj);

		System.out.println("result ----->  "+sr);
		
	}
	
	public static void  getOrderPost() throws SQLException {
		Statement stmt = DBUtil.getConnection().createStatement();
		// ResultSet executeQuery(String sqlString)：执行查询数据库的SQL语句 ，返回一个结果集（ResultSet）对象。
		// ResultSet rs = stmt.executeQuery("SELECT * from orderCancelCollect ");
		// ResultSet rs = stmt.executeQuery("SELECT * from orderCollect ");
		ResultSet rs = stmt.executeQuery("SELECT  o.json\r\n" + 
				"  FROM orders AS o WHERE o.storeId='DQ451005'\r\n" + 
				"AND o.orderDate='2020-09-13' --AND o.orderDate<='2020-09-13'\r\n" + 
				"AND o.iscancel=0\r\n" + 
				"AND LEN(o.thirdSn)=4\r\n" + 
				"ORDER BY o.thirdSn");
		while (rs.next()) {
			// System.out.println(rs.getString("req"));
			// Test.postDineorder(rs.getString("req"));
			String cell = rs.getString("json");
			//System.out.println(cell);
			postOldOrder(cell);
		}
		
	}
	
	public static void  getOrderCancel() throws SQLException {
		Statement stmt = DBUtil.getConnection().createStatement();
		// ResultSet executeQuery(String sqlString)：执行查询数据库的SQL语句 ，返回一个结果集（ResultSet）对象。
		// ResultSet rs = stmt.executeQuery("SELECT * from orderCancelCollect ");
		// ResultSet rs = stmt.executeQuery("SELECT * from orderCollect ");
		ResultSet rs = stmt.executeQuery("EXEC p_compare_single_seito 'YS022025','2020-10-01'");
		while (rs.next()) {
			// System.out.println(rs.getString("req"));
			// Test.postDineorder(rs.getString("req"));
			String cell = rs.getString("extorderid");
			System.out.println(cell);
			orderCancel(cell,-1,"9月的订单误上传");
		}
		
	}
	

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		//取消单个订单
	//	PostOrder.orderCancel("YS451022007202010160319211068",-1,"缓存失效单"); 
		
		//批量取消订单
		 //getOrderCancel();
		String jason="{\"accessKey\":\"4ca533f4b7f5da07\",\"actionName\":\"candao.order.postDineInOrder\",\"timestamp\":1603967162191,\"ticket\":\"9a39d7b4-a705-4b90-923f-ec7ad1820754\",\"serviceType\":\"pos\",\"vendor\":\"ncr\",\"storeId\":\"DQ010133\",\"data\":{\"orderTime\":\"2020-10-09 18:30:47\",\"orderStatus\":100,\"status\":[{\"title\":\"下单（快餐）\",\"value\":105,\"dateTime\":\"2020-10-09 18:29:06\"},{\"title\":\"支付完成（快餐）\",\"value\":115,\"dateTime\":\"2020-10-09 18:30:47\"}],\"storeId\":\"DQ010133\",\"orderDate\":\"2020-10-09\",\"thirdSn\":\"1087\",\"orderId\":\"DQ0101332010091087\",\"fromType\":\"pos\",\"deviceNo\":\"1\",\"staffId\":\"21\",\"staffBane\":\"仇译萱（帆帆）\",\"orderType\":3,\"posOrderType\":12,\"payType\":1,\"isPayed\":true,\"paymentDetails\":[{\"payType\":1,\"type\":32,\"typeName\":\"其他\",\"money\":53.9,\"posType\":\"114\",\"posName\":\"X微信应收\"}],\"price\":53.9,\"deliveryFee\":0,\"mealFee\":0,\"discountPrice\":-50.1,\"thirdPlatformBearPrice\":0,\"merchantBearPrice\":50.1,\"merchantPrice\":53.9,\"commission\":0,\"discounts\":[{\"code\":\"22512\",\"vendor\":\"ncr\",\"num\":1,\"type\":3,\"childType\":3,\"title\":\"X香草90g+9.9元\",\"content\":\"X香草90g+9.9元\",\"price\":-20.1,\"totalAmount\":-20.1,\"thirdSubsidy\":0,\"merchantSubsidy\":0,\"disProducts\":[{\"pid\":\"1760020\",\"name\":\"X马达加斯加香草-小\",\"num\":1,\"price\":30.0,\"itemDisc\":20.45,\"types\":{\"bigType\":\"76\",\"extra\":\"硬冰\"},\"dept\":{\"id\":\"76\",\"title\":\"桶装冰淇淋\"},\"productTaxRate\":\"13%\"}]},{\"code\":\"21541\",\"vendor\":\"ncr\",\"num\":1,\"type\":3,\"childType\":2,\"title\":\"X商城-8折\",\"content\":\"X商城-8折\",\"price\":-6.0,\"totalAmount\":-6.0,\"thirdSubsidy\":0,\"merchantSubsidy\":0,\"disProducts\":[{\"pid\":\"1760044\",\"name\":\"X苏丹王榴莲-小\",\"num\":1,\"price\":30.0,\"itemDisc\":6.86,\"types\":{\"bigType\":\"76\",\"extra\":\"硬冰\"},\"dept\":{\"id\":\"76\",\"title\":\"桶装冰淇淋\"},\"productTaxRate\":\"13%\"}]},{\"code\":\"22305\",\"vendor\":\"ncr\",\"num\":1,\"type\":3,\"childType\":3,\"title\":\"X 商城1元优惠券\",\"content\":\"X 商城1元优惠券\",\"price\":-1.0,\"totalAmount\":-1.0,\"thirdSubsidy\":0,\"merchantSubsidy\":0},{\"code\":\"22305\",\"vendor\":\"ncr\",\"num\":1,\"type\":3,\"childType\":3,\"title\":\"X 商城1元优惠券\",\"content\":\"X 商城1元优惠券\",\"price\":-1.0,\"totalAmount\":-1.0,\"thirdSubsidy\":0,\"merchantSubsidy\":0},{\"code\":\"99999\",\"vendor\":\"ncr\",\"num\":1,\"type\":3,\"childType\":2,\"title\":\"DQ折扣(补入)\",\"content\":\"DQ折扣(缺失)\",\"price\":-22.0,\"totalAmount\":-22.0,\"thirdSubsidy\":0,\"merchantSubsidy\":0}],\"products\":[{\"pid\":\"1760020\",\"name\":\"X马达加斯加香草-小\",\"num\":1,\"price\":30.0,\"itemDisc\":20.45,\"types\":{\"bigType\":\"76\",\"extra\":\"硬冰\"},\"dept\":{\"id\":\"76\",\"title\":\"桶装冰淇淋\"},\"productTaxRate\":\"13%\"},{\"pid\":\"1760044\",\"name\":\"X苏丹王榴莲-小\",\"num\":1,\"price\":30.0,\"itemDisc\":6.86,\"types\":{\"bigType\":\"76\",\"extra\":\"硬冰\"},\"dept\":{\"id\":\"76\",\"title\":\"桶装冰淇淋\"},\"productTaxRate\":\"13%\"},{\"pid\":\"1040133\",\"name\":\"X草莓奶昔\",\"num\":1,\"price\":22.0,\"itemDisc\":11.4,\"types\":{\"bigType\":\"1\",\"extra\":\"冰淇淋\"},\"dept\":{\"id\":\"4\",\"title\":\"奶昔系列\"},\"productTaxRate\":\"6%\"},{\"pid\":\"1040134\",\"name\":\"X香草奶昔\",\"num\":1,\"price\":22.0,\"itemDisc\":11.39,\"types\":{\"bigType\":\"1\",\"extra\":\"冰淇淋\"},\"dept\":{\"id\":\"4\",\"title\":\"奶昔系列\"},\"productTaxRate\":\"6%\"},{\"pid\":\"1220582\",\"name\":\"X0元干冰\",\"num\":1,\"price\":0.0,\"itemDisc\":0.0,\"types\":{\"bigType\":\"1\",\"extra\":\"冰淇淋\"},\"dept\":{\"id\":\"22\",\"title\":\"其他\"},\"productTaxRate\":\"6%\"}],\"originPrice\":104.0,\"productPrice\":104.0,\"counts\":5},\"sign\":\"faf5542ffd61779c3e9fe641085b7d0d\"}";
		
		postOldOrder(jason);		
		
	}

}
