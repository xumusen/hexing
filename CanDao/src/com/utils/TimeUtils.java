package com.utils;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.alibaba.fastjson.JSONArray;
import com.entity.PaymentDetails;
import com.entity.product.Products;

import http_post.HttpRequest;
import md5.MD5;
import net.sf.json.JSONObject;

public class TimeUtils {

	public static String getTimeStamptoString(long timestamp) {
		// Timestamp ts =new Timestamp(System.currentTimeMillis());
		Timestamp ts = new Timestamp(timestamp);
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = sdf.format(ts);
		// System.out.println(time);
		return time;
	}

	public static void submitOrder() throws UnsupportedEncodingException {
		
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		DateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		String time = sdf.format(ts);
		String accessKey = "2b4449d3603b9b7e";
		// String actionName = "candao.order.postDineInStatus";
		String actionName = "candao.order.postDineInOrder";
		String secret = "3057147bfa1749bd9e1d51ff18635cdd";
		long timestamp = System.currentTimeMillis();
		System.out.println(timestamp);
		// String xiucandata =
		// "{\"fromType\":\"xiucan\",\"orderId\":\"15151505090115814049856760000008\",\"storeId\":\"0109999\",\"userNote\":\"\",\"orderTime\":\"2020-02-11
		// 15:09:53\",\"orderDate\":\"2020-02-11\",\"orderStatus\":7,\"orderType\":3,\"payType\":2,\"isPayed\":true,\"paymentDetails\":[{\"payType\":2,\"money\":28,\"type\":2,\"typeName\":\"微信\"}],\"discounts\":[],\"isInvoice\":true,\"price\":28,\"mealFee\":0,\"discountPrice\":0,\"merchantBearPrice\":0,\"merchantPrice\":28,\"products\":[{\"pid\":\"32101900\",\"name\":\"赤金牛肉面\",\"num\":1,\"price\":28}]}";
		// String
		// xiucandata="{\"fromType\":\"xiucan\",\"orderId\":\"19341901080615827879888360000101\",\"storeId\":\"YS010204\",\"userNote\":\"\",\"orderTime\":\"2020-03-02
		// 15:35:00\",\"orderDate\":\"2020-03-02\",\"orderStatus\":7,\"orderType\":2,\"payType\":2,\"isPayed\":true,\"paymentDetails\":[{\"payType\":2,\"money\":1.5,\"type\":3,\"typeName\":\"吉野家礼品卡\",\"num\":1,\"code\":\"00000002110\"}],\"discounts\":[],\"isInvoice\":true,\"price\":1.5,\"mealFee\":0,\"discountPrice\":0,\"merchantBearPrice\":0,\"merchantPrice\":1.5,\"products\":[{\"pid\":\"41100002\",\"name\":\"海带结\",\"num\":1,\"price\":1.5}],\"counts\":1,\"originPrice\":1.5,\"productPrice\":1.5}";
		// String
		// xiucandata="{\"fromType\":\"xiucan\",\"orderId\":\"15151505090115814049856760000010\",\"storeId\":\"YS010204\",\"userNote\":\"\",\"orderTime\":\"2020-02-24
		// 10:00:53\",\"orderDate\":\"2020-02-24\",\"orderStatus\":7,\"orderType\":3,\"payType\":2,\"isPayed\":true,\"paymentDetails\":[{\"payType\":2,\"money\":28,\"type\":2,\"typeName\":\"微信\"}],\"discounts\":[],\"isInvoice\":true,\"price\":28,\"mealFee\":0,\"discountPrice\":0,\"merchantBearPrice\":0,\"merchantPrice\":28,\"products\":[{\"pid\":\"32101900\",\"name\":\"赤金牛肉面\",\"num\":1,\"price\":28}]}";

		JSONObject content = new JSONObject();
		JSONObject product_list = new JSONObject();

		Map<String, Object> contents = new HashMap<String, Object>();
		ArrayList<Products> product_lists = new ArrayList<Products>();
		ArrayList<PaymentDetails> paymentDetailss = new ArrayList<PaymentDetails>();
		Map<String, Object> products_list = new HashMap<String, Object>();
		content.put("fromType", "xiucan");
		content.put("orderId", "19341901080615827879888360000102");
		content.put("storeId", "YS010204");
		content.put("userNote", "");
		content.put("orderTime", sdf.format(ts));
		content.put("orderDate", sd.format(ts));
		content.put("orderStatus", 7);
		content.put("orderType", 2);
		content.put("payType", 2);
		content.put("isPayed", true);
		JSONObject discount = new JSONObject();
		JSONArray discounts = new JSONArray();
		// discounts.add(discount);

		content.put("discounts", discounts);
		content.put("isInvoice", false);
		content.put("price", 1.5);
		content.put("mealFee", 0);
		content.put("discountPrice", 0);
		content.put("merchantBearPrice", 0);
		content.put("merchantPrice", 1.5);
		content.put("counts", 1);
		content.put("originPrice", 1.5);
		content.put("productPrice", 1.5);

		JSONObject details = new JSONObject();
		details.put("money", 1.5);
		details.put("payType", 2);
		details.put("type", 3);
		details.put("typeName", "吉野家礼品");
		details.put("num", 1);
		details.put("code", "00000002110");
		JSONArray arrayDetails = new JSONArray();
		arrayDetails.add(details);
		content.put("paymentDetails", arrayDetails);

		JSONObject products = new JSONObject();
		products.put("pid", "41100002");
		products.put("name", "海带结");
		products.put("price", 1.5);
		products.put("num", 1);
		JSONArray productes = new JSONArray();
		productes.add(products);
		content.put("products", productes);
		System.out.println(content.toString());
		// System.out.println(content.get("content"));
		String sign = MD5.getMD5Str(accessKey + actionName + secret + timestamp
				+ (StringUtil.isNullOrBlank(content.toString()) ? "" : content.toString()));
		System.out.println(sign);
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("accessKey", accessKey);
		jsonObj.put("actionName", actionName);
		jsonObj.put("timestamp", timestamp);
		jsonObj.put("ticket", UUID.randomUUID().toString());
		jsonObj.put("vendor", "seito");
		jsonObj.put("serviceType", "pos");
		jsonObj.put("storeId", "YS010204");
		jsonObj.put("sign", sign);
		jsonObj.put("data", content);

		String sr = HttpRequest.sendPost("http://zt_qc.can-dao.com:81/api", jsonObj);
		byte[] bytes = sr.getBytes("utf-8");

		String name = new String(bytes, "utf-8");
		System.out.println(name);

	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub

		/*
		 * java中如何将string 转化成long 转自：https://www.cnblogs.com/feifeicui/p/8390604.html
		 * 
		 * 1.java中如何将string 转化成long
		 * 
		 * long l = Long.parseLong([String]);
		 * 
		 * 或
		 * 
		 * long l = Long.parseLong([String],[int radix]);
		 * 
		 * long l = Long.valueOf("123").longValue();
		 * 
		 * 2.Long.ValueOf("String")与Long.parseLong("String")的区别
		 * 
		 * Long.ValueOf("String")返回Long包装类型
		 * 
		 * Long.parseLong("String")返回long基本数据类型
		 */
		/*
		 * String t="1574859897966"; long timestamp= Long.valueOf(t);;
		 * getTimeStamptoString(timestamp);
		 */

		/*
		 * Timestamp ts = new Timestamp(System.currentTimeMillis()); DateFormat sdf =
		 * new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); String time = sdf.format(ts);
		 * String accessKey = "125b03eccf81cbd4"; String actionName =
		 * "candao.order.postDineInOrder"; String secret =
		 * "f5eba5c8d37e4f03d30cf8ed41b050a9"; long timestamp =
		 * System.currentTimeMillis(); System.out.println(timestamp); //String
		 * xiucandata =
		 * "{\"fromType\":\"xiucan\",\"orderId\":\"15151505090115814049856760000008\",\"storeId\":\"0109999\",\"userNote\":\"\",\"orderTime\":\"2020-02-11 15:09:53\",\"orderDate\":\"2020-02-11\",\"orderStatus\":7,\"orderType\":3,\"payType\":2,\"isPayed\":true,\"paymentDetails\":[{\"payType\":2,\"money\":28,\"type\":2,\"typeName\":\"微信\"}],\"discounts\":[],\"isInvoice\":true,\"price\":28,\"mealFee\":0,\"discountPrice\":0,\"merchantBearPrice\":0,\"merchantPrice\":28,\"products\":[{\"pid\":\"32101900\",\"name\":\"赤金牛肉面\",\"num\":1,\"price\":28}]}"
		 * ; String
		 * xiucandata="{\"fromType\":\"xiucan\",\"orderId\":\"15151505090115814049856760000009\",\"storeId\":\"0109999\",\"userNote\":\"\",\"orderTime\":\"2020-02-11 15:09:53\",\"orderDate\":\"2020-02-11\",\"orderStatus\":7,\"orderType\":3,\"payType\":2,\"isPayed\":true,\"paymentDetails\":[{\"payType\":2,\"money\":28,\"type\":2,\"typeName\":\"微信\"}],\"discounts\":[],\"isInvoice\":true,\"price\":28,\"mealFee\":0,\"discountPrice\":0,\"merchantBearPrice\":0,\"merchantPrice\":28,\"products\":[{\"pid\":\"32101900\",\"name\":\"赤金牛肉面\",\"num\":1,\"price\":28}]}"
		 * ; String sign = MD5.getMD5Str( accessKey + actionName + secret + timestamp +
		 * (StringUtil.isNullOrBlank(xiucandata) ? "" : xiucandata));
		 * System.out.println(sign);
		 */

		/*
		 * String accessKey2="50ae7f01ac5d0ba5"; String
		 * actionName2="candao.retail.order"; String
		 * secret2="726440f3040232c12f941d0feaafb3fe"; long
		 * timestamp2=System.currentTimeMillis(); System.out.println(timestamp2); String
		 * yitunneldata="{\"storeId\":\"YS010204\",\"counts\":1,\"currency\":1,\"deviceNo\":\"138\",\"discountPrice\":10.9,\"fromType\":\"yitunnel\",\"isPayed\":false,\"merchantBearPrice\":0.0,\"merchantPrice\":0.1,\"name\":null,\"orderDate\":\"2020-02-11\",\"orderId\":\"SW0000020021107545588772\",\"orderNo\":\"SW0000020021107545588772\",\"orderStatus\":100,\"orderTime\":\"2020-02-11 15:54:55\",\"orderType\":5,\"payType\":2,\"price\":0.1,\"productPrice\":11.0,\"realTimeProductPrice\":0.1,\"sn\":\"SW0000020021107545588772\",\"type\":1,\"discounts\":null,\"paymentDetails\":[{\"money\":0.1,\"type\":2,\"typeName\":\"微信\",\"tradeNo\":null}],\"products\":[{\"discount\":false,\"isDiscount\":false,\"name\":\"皇冠丹麦曲奇饼干\",\"num\":1,\"pid\":\"N0000205\",\"price\":11.0,\"subPid\":\"B0000010000010\",\"realTimePrice\":null,\"realTimeTotalPrice\":null}],\"originOrderId\":null,\"originPrice\":11.0}"
		 * ; String
		 * jian24data="{\"counts\":1,\"currency\":1,\"deviceNo\":\"0\",\"discountPrice\":0.00,\"discounts\":[],\"fromType\":\"jian24\",\"isPayed\":true,\"merchantBearPrice\":0.00,\"merchantPrice\":2.00,\"name\":\"姚飞飞\",\"orderDate\":\"2020-02-17\",\"orderId\":\"12074002\",\"orderNo\":\"12074002\",\"orderStatus\":100,\"orderTime\":\"2020-02-17 18:41:02\",\"orderType\":5,\"originPrice\":2.00,\"payType\":2,\"paymentDetails\":[{\"money\":2.00,\"payType\":2,\"tradeNo\":\"12074001163184\",\"type\":3,\"typeName\":\"其他\"}],\"price\":2.00,\"productPrice\":2.00,\"products\":[{\"isDiscount\":false,\"itemDisc\":0.00,\"name\":\"怡宝饮用纯净水 555ML\",\"num\":1,\"pid\":\"ND000069\",\"price\":2.00,\"subPid\":\"159\"}],\"realTimeProductPrice\":2.00,\"sn\":\"12074002\",\"storeId\":\"YS010094\",\"type\":1}"
		 * ; String sign2 = MD5.getMD5Str(accessKey2 + actionName2 + secret2 +
		 * timestamp2 + (StringUtil.isNullOrBlank(jian24data)?"" : jian24data));
		 * System.out.println(sign2);
		 */

		/*
		 * Timestamp ts = new Timestamp(System.currentTimeMillis()); DateFormat sdf =
		 * new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); String time = sdf.format(ts);
		 * String accessKey = "2b4449d3603b9b7e"; String actionName =
		 * "candao.order.postDineInStatus"; //String
		 * actionName="candao.order.postDineInOrder"; String secret =
		 * "3057147bfa1749bd9e1d51ff18635cdd"; long timestamp =
		 * System.currentTimeMillis(); System.out.println(timestamp); //String
		 * xiucandata =
		 * "{\"fromType\":\"xiucan\",\"orderId\":\"15151505090115814049856760000008\",\"storeId\":\"0109999\",\"userNote\":\"\",\"orderTime\":\"2020-02-11 15:09:53\",\"orderDate\":\"2020-02-11\",\"orderStatus\":7,\"orderType\":3,\"payType\":2,\"isPayed\":true,\"paymentDetails\":[{\"payType\":2,\"money\":28,\"type\":2,\"typeName\":\"微信\"}],\"discounts\":[],\"isInvoice\":true,\"price\":28,\"mealFee\":0,\"discountPrice\":0,\"merchantBearPrice\":0,\"merchantPrice\":28,\"products\":[{\"pid\":\"32101900\",\"name\":\"赤金牛肉面\",\"num\":1,\"price\":28}]}"
		 * ; String
		 * xiucandata="{\"orderId\":\"15151505090115814049856760000010\",\"status\":100,\"updateTime\":\"2020-02-24 10:02:45\",\"orderTime\":\"2020-02-24 10:00:53\"}"
		 * ; //String
		 * xiucandata="{\"fromType\":\"xiucan\",\"orderId\":\"15151505090115814049856760000010\",\"storeId\":\"YS010204\",\"userNote\":\"\",\"orderTime\":\"2020-02-24 10:00:53\",\"orderDate\":\"2020-02-24\",\"orderStatus\":7,\"orderType\":3,\"payType\":2,\"isPayed\":true,\"paymentDetails\":[{\"payType\":2,\"money\":28,\"type\":2,\"typeName\":\"微信\"}],\"discounts\":[],\"isInvoice\":true,\"price\":28,\"mealFee\":0,\"discountPrice\":0,\"merchantBearPrice\":0,\"merchantPrice\":28,\"products\":[{\"pid\":\"32101900\",\"name\":\"赤金牛肉面\",\"num\":1,\"price\":28}]}"
		 * ; String sign = MD5.getMD5Str( accessKey + actionName + secret + timestamp +
		 * (StringUtil.isNullOrBlank(xiucandata) ? "" : xiucandata));
		 * System.out.println(sign);
		 */

		// 提交正常单

	}

}
