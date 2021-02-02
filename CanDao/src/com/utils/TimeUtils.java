package com.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.UUID;

import com.alibaba.fastjson.JSONArray;
import com.entity.PaymentDetails;
import com.entity.product.Products;

import http_post.HttpRequest;
import md5.MD5;
import md5.Md5Encrypt;
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
	public static void getTimeStamptoLong(String time) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	Date date = null;
		try {
			date = df.parse(time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(date);
    	long timestamp = cal.getTimeInMillis();
    	System.out.println(timestamp);
	}
	
	
	public static String getFirstDay() {
		  Calendar cale = null;
	        cale = Calendar.getInstance();
	        SimpleDateFormat format = new SimpleDateFormat("MMdd");
	        String firstday, lastday;
	        // 获取本月的第一天
	        cale = Calendar.getInstance();
	        cale.add(Calendar.MONTH, 0);
	        cale.set(Calendar.DAY_OF_MONTH, 1);
	        firstday = format.format(cale.getTime());
	        return firstday;
	}
	
	public static String getLastDay() {
		  Calendar cale = null;
	        cale = Calendar.getInstance();
	        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
	        String  lastday;
	        // 获取本月的最后一天
	        cale = Calendar.getInstance();
	        cale.add(Calendar.MONTH, 1);
	        cale.set(Calendar.DAY_OF_MONTH, 0);
	        lastday = format.format(cale.getTime());
	        return lastday;
	}
	public static String getFirstDay(String patten) {
		Calendar cale = Calendar.getInstance();
		Timestamp ts =new Timestamp(System.currentTimeMillis());
		DateFormat sdf = new SimpleDateFormat(patten);
		String nowday = sdf.format(ts);
		String firstday;
		SimpleDateFormat dayformat = new SimpleDateFormat(patten);
		//System.out.println(nowday.substring(nowday.length()-2));


		if (nowday.substring(nowday.length()-2).equals("01")) {

			cale.add(Calendar.MONTH, -1);
			cale.set(Calendar.DAY_OF_MONTH, 1);
			firstday = dayformat.format(cale.getTime());

		} else {
			cale.add(Calendar.MONTH, 0);
			cale.set(Calendar.DAY_OF_MONTH, 1);
			firstday = dayformat.format(cale.getTime());
		}
	
	        return firstday;
	}
	
	public static String getLastDay(String patten) {
		
		Calendar cale = Calendar.getInstance();
		Timestamp ts =new Timestamp(System.currentTimeMillis());
		DateFormat sdf = new SimpleDateFormat(patten);
		String nowday = sdf.format(ts);
		String lastday;
		SimpleDateFormat dayformat = new SimpleDateFormat(patten);
		//System.out.println(nowday);


		if (nowday.substring(nowday.length()-2).equals("01")) {

			cale.add(Calendar.MONTH, 0);
			cale.set(Calendar.DAY_OF_MONTH, 0);
			lastday = dayformat.format(cale.getTime());
			//System.out.println(lastday);

		} else {
			cale.add(Calendar.MONTH, 1);
			cale.set(Calendar.DAY_OF_MONTH, 0);
			lastday = dayformat.format(cale.getTime());
		//	System.out.println(lastday);
		}
	        return lastday;
	}
	public static String getYesterday() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1); //得到前一天
		Date date1 = calendar.getTime();
		DateFormat df = new SimpleDateFormat("MMdd");
		String yesterday=df.format(date1);
	    return yesterday;
	}
	public static String getYesterday(String patten) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1); //得到前一天
		Date date1 = calendar.getTime();
		DateFormat df = new SimpleDateFormat(patten);
		String yesterday=df.format(date1);
	    return yesterday;
	}
	public static String getToday(String patten) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, 0); //得到前一天
		Date date1 = calendar.getTime();
		DateFormat df = new SimpleDateFormat(patten);
		String yesterday=df.format(date1);
	    return yesterday;
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
	public static void postTest() {
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

		Timestamp ts = new Timestamp(System.currentTimeMillis());
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = sdf.format(ts);
		System.out.println("当前的时间是  "+System.currentTimeMillis());
		/*
		 * String accessKey = "782b5654d5f7a26e"; String secret =
		 * "204739a0b6f0f25cc3c1dee7715f27b8"; String actionName =
		 * "candao.order.postDineInOrder";
		 */
		/*东北吉野家
		String accessKey="fb4fda21afeddcf4";
		String secret="d561cd33e77e43acbcec313f72068e4c";
		*/
		
		/*DQ*/
		String accessKey="4ca533f4b7f5da07";
		String secret="2482df65c5ecb83be8166805e9dc5c3b";
		/*
		 * String serviceType="pos"; String vendor="seito"; String storeId="YS010204";
		 * String actionName = "candao.order.postDineInOrder";
		 */
		/*
		 * long timestamp = System.currentTimeMillis(); System.out.println(timestamp);
		 * // String //String xiucandata =
		 * "{\"fromType\":\"xiucan\",\"orderId\":\"15151505090115814049856760000008\",\"storeId\":\"0109999\",\"userNote\":\"\",\"orderTime\":\"2020-02-11 15:09:53\",\"orderDate\":\"2020-02-11\",\"orderStatus\":7,\"orderType\":3,\"payType\":2,\"isPayed\":true,\"paymentDetails\":[{\"payType\":2,\"money\":28,\"type\":2,\"typeName\":\"微信\"}],\"discounts\":[],\"isInvoice\":true,\"price\":28,\"mealFee\":0,\"discountPrice\":0,\"merchantBearPrice\":0,\"merchantPrice\":28,\"products\":[{\"pid\":\"32101900\",\"name\":\"赤金牛肉面\",\"num\":1,\"price\":28}]}"
		 * ; String
		 * xiucandata="{\"fromType\":\"xiucan\",\"orderId\":\"19341900000115873842079980000910\",\"storeId\":\"YS010204\",\"userNote\":\"\",\"orderTime\":\"2020-04-20 20:03:43\",\"orderDate\":\"2020-04-20\",\"orderStatus\":7,\"orderType\":3,\"payType\":2,\"isPayed\":true,\"paymentDetails\":[{\"payType\":2,\"money\":2.5,\"type\":2,\"typeName\":\"微信\"}],\"discounts\":[],\"isInvoice\":true,\"price\":2.5,\"mealFee\":0,\"discountPrice\":0,\"merchantBearPrice\":0,\"merchantPrice\":2.5,\"products\":[{\"pid\":\"42300006\",\"name\":\"小袋黄瓜\",\"num\":1,\"price\":2.5}],\"counts\":1,\"originPrice\":2.5,\"productPrice\":2.5}"
		 * ; //xiucandata =
		 * "{\"fromType\":\"xiucan\",\"orderId\":\"15151505090115814049856760000010\",\"storeId\":\"YS010204\",\"userNote\":\"\",\"orderTime\":\"2020-02-24 10:00:53\",\"orderDate\":\"2020-02-24\",\"orderStatus\":7,\"orderType\":3,\"payType\":2,\"isPayed\":true,\"paymentDetails\":[{\"payType\":2,\"money\":28,\"type\":2,\"typeName\":\"微信\"}],\"discounts\":[],\"isInvoice\":true,\"price\":28,\"mealFee\":0,\"discountPrice\":0,\"merchantBearPrice\":0,\"merchantPrice\":28,\"products\":[{\"pid\":\"32101900\",\"name\":\"赤金牛肉面\",\"num\":1,\"price\":28}]}"
		 * ; String sign = MD5.getMD5Str( accessKey + actionName + secret + timestamp +
		 * (StringUtil.isNullOrBlank(xiucandata) ? "" : xiucandata));
		 * System.out.println(sign); String
		 * seitoDate="{\"orderId\":\"YS010018008202004260289506829\",\"thirdSn\":\"0080982\",\"storeId\":\"YS010018\",\"count\":3,\"fromType\":\"kiosk\",\"takeNo\":\"0882\",\"peopleNum\":0,\"productPrice\":22.5,\"deviceNo\":\"008\",\"staffId\":\"0000000006\",\"staffBane\":\"kiosk\",\"orderTime\":\"2020-04-26 09:41:59\",\"orderDate\":\"2020-04-26\",\"orderStatus\":100,\"orderType\":2,\"posOrderType\":2,\"isPayed\":true,\"paymentDetails\":[{\"payType\":1,\"type\":15,\"typeName\":\"微信线下支付\",\"money\":22.5,\"tradeNo\":\"YS010018008_0080982_200426094219\",\"posType\":\"WXZF\",\"posName\":\"微信支付\",\"code\":\"YS010018008_0080982_200426094219\",\"num\":1}],\"isInvoice\":false,\"price\":22.5,\"deliveryFee\":0.0,\"mealFee\":0.0,\"discountPrice\":0.0,\"merchantBearPrice\":0.0,\"thirdPlatformBearPrice\":0.0,\"merchantPrice\":22.5,\"originPrice\":22.5,\"commission\":0.0,\"status\":[{\"title\":\"下单（快餐）\",\"value\":\"105\",\"dateTime\":\"2020-04-26 09:41:20\"},{\"title\":\"支付（快餐）\",\"value\":\"110\",\"dateTime\":\"2020-04-26 09:41:59\"},{\"title\":\"支付完成（快餐）\",\"value\":\"115\",\"dateTime\":\"2020-04-26 09:42:21\"}],\"products\":[{\"pid\":\"32100436\",\"name\":\"豚馒小豆浆\",\"num\":2.0,\"price\":6.0,\"boxNum\":0.0,\"boxPrice\":0.0,\"itemDisc\":0.0,\"productTaxRate\":\"6\",\"productCategory\":{\"id\":\"1\",\"title\":\"品牌\"},\"productContent\":{\"id\":\"001\",\"title\":\"吉野家\"},\"dept\":{\"id\":\"0000Y01\",\"title\":\"早餐类\",\"subDept\":{\"id\":\"0000Y13\",\"title\":\"副食\"}},\"category\":{\"id\":\"0000132\",\"title\":\"其他\"},\"types\":{\"bigType\":\"0000132\",\"extra\":\"其他\"},\"combos\":[{\"pid\":\"32100435\",\"name\":\"爆汁豚馒\",\"num\":1.0,\"price\":5.0,\"addPrice\":0.0,\"skus\":[]},{\"pid\":\"15100001\",\"name\":\"原味豆浆/大\",\"num\":1.0,\"price\":9.0,\"addPrice\":1.5,\"skus\":[]}]},{\"pid\":\"32100436\",\"name\":\"豚馒小豆浆\",\"num\":1.0,\"price\":6.0,\"boxNum\":0.0,\"boxPrice\":0.0,\"itemDisc\":0.0,\"productTaxRate\":\"6\",\"productCategory\":{\"id\":\"1\",\"title\":\"品牌\"},\"productContent\":{\"id\":\"001\",\"title\":\"吉野家\"},\"dept\":{\"id\":\"0000Y01\",\"title\":\"早餐类\",\"subDept\":{\"id\":\"0000Y13\",\"title\":\"副食\"}},\"category\":{\"id\":\"0000132\",\"title\":\"其他\"},\"types\":{\"bigType\":\"0000132\",\"extra\":\"其他\"},\"combos\":[{\"pid\":\"32100435\",\"name\":\"爆汁豚馒\",\"num\":1.0,\"price\":5.0,\"addPrice\":0.0,\"skus\":[]},{\"pid\":\"15100001\",\"name\":\"原味豆浆/大\",\"num\":1.0,\"price\":9.0,\"addPrice\":1.5,\"skus\":[]}]}]}"
		 * ;
		 * 
		 * 
		 * JSONObject jsonObj = new JSONObject(); jsonObj.put("actionName", actionName);
		 * jsonObj.put("data", xiucandata); jsonObj.put("accessKey", accessKey);
		 * jsonObj.put("sign", sign); jsonObj.put("timestamp",timestamp);
		 * jsonObj.put("serviceType", serviceType); jsonObj.put("ticket",
		 * UUID.randomUUID().toString()); jsonObj.put("vendor", vendor);
		 * jsonObj.put("storeId", storeId); //String sr = HttpRequest.sendPost(
		 * "http://zt_qc.can-dao.com:81/api",jsonObj); //byte[]
		 * bytes=sr.getBytes("utf-8"); //String name=new String(bytes,"utf-8");
		 */

		// System.out.println(jsonObj);
		// System.out.println(name);
	//	String json = "{\"accessKey\":\"782b5654d5f7a26e\",\"actionName\":\"candao.order.postDineInOrder\",\"timestamp\":1589347083149,\"ticket\":\"098ca637-51d3-45e9-85b2-c7ba0175ca83\",\"sign\":\"3759c159b7004b059d0cb4acc4dec7fa\",\"serviceType\":\"pos\",\"vendor\":\"seito\",\"storeId\":\"YS010999\",\"data\":{\"orderId\":\"YS010999002202005130340400053\",\"thirdSn\":\"0020069\",\"storeId\":\"YS010999\",\"counts\":14,\"fromType\":\"pos\",\"takeNo\":\"0269\",\"peopleNum\":0,\"productPrice\":662.0,\"deviceNo\":\"002\",\"staffId\":\"0000000004\",\"staffBane\":\"白羽\",\"orderTime\":\"2020-05-13 13:17:02\",\"orderDate\":\"2020-05-13\",\"orderStatus\":100,\"orderType\":3,\"posOrderType\":\"0\",\"isPayed\":true,\"paymentDetails\":[{\"payType\":1,\"type\":1,\"typeName\":\"现金\",\"money\":660.0,\"posType\":\"CASH\",\"posName\":\"现金\",\"num\":0}],\"isInvoice\":false,\"price\":660.0,\"deliveryFee\":0.0,\"mealFee\":0.0,\"discountPrice\":-2.0,\"merchantBearPrice\":2.0,\"thirdPlatformBearPrice\":0.0,\"merchantPrice\":660.0,\"originPrice\":662.0,\"commission\":0.0,\"status\":[{\"title\":\"下单（快餐）\",\"value\":\"105\",\"dateTime\":\"2020-05-13 13:14:44\"},{\"title\":\"支付（快餐）\",\"value\":\"110\",\"dateTime\":\"2020-05-13 13:17:02\"},{\"title\":\"支付完成（快餐）\",\"value\":\"115\",\"dateTime\":\"2020-05-13 13:17:11\"}],\"discounts\":[{\"code\":\"Y0000719\",\"pid\":\"Y0000719\",\"vendor\":\"seito\",\"num\":1,\"type\":3,\"childType\":3,\"title\":\"喜羊羊优惠2元\",\"content\":\"\",\"price\":-2.0,\"totalAmount\":-2.0,\"thirdSubsidy\":0.0,\"merchantSubsidy\":2.0,\"disProducts\":[{\"pid\":\"52100162\",\"name\":\"新标牛套\",\"num\":1.0,\"price\":39.0,\"itemDisc\":2.0}]}],\"products\":[{\"pid\":\"32101916\",\"name\":\"招牌牛肉套/大将军碗\",\"num\":1.0,\"price\":45.5,\"boxNum\":0.0,\"boxPrice\":0.0,\"itemDisc\":0.0,\"productTaxRate\":\"6\",\"productCategory\":{\"id\":\"1\",\"title\":\"品牌\"},\"productContent\":{\"id\":\"001\",\"title\":\"吉野家\"},\"dept\":{\"id\":\"0000Y02\",\"title\":\"正餐类\",\"subDept\":{\"id\":\"0000Y21\",\"title\":\"主餐类\"}},\"category\":{\"id\":\"0000211\",\"title\":\"牛肉类\"},\"types\":{\"bigType\":\"0000211\",\"extra\":\"牛肉类\"},\"combos\":[{\"pid\":\"32101914\",\"name\":\"招牌牛/大将军碗\",\"num\":1.0,\"price\":37.0,\"addPrice\":0.0,\"skus\":[]},{\"pid\":\"41400005\",\"name\":\"新合茶碗蒸\",\"num\":1.0,\"price\":7.5,\"addPrice\":2.5,\"skus\":[]},{\"pid\":\"32105095\",\"name\":\"草莓芒芒果饮\",\"num\":1.0,\"price\":12.0,\"addPrice\":3.0,\"skus\":[]}]},{\"pid\":\"52100162\",\"name\":\"新标牛套\",\"num\":1.0,\"price\":39.0,\"boxNum\":0.0,\"boxPrice\":0.0,\"itemDisc\":2.0,\"productTaxRate\":\"6\",\"productCategory\":{\"id\":\"1\",\"title\":\"品牌\"},\"productContent\":{\"id\":\"001\",\"title\":\"吉野家\"},\"dept\":{\"id\":\"0000Y02\",\"title\":\"正餐类\",\"subDept\":{\"id\":\"0000Y21\",\"title\":\"主餐类\"}},\"category\":{\"id\":\"0000211\",\"title\":\"牛肉类\"},\"types\":{\"bigType\":\"0000211\",\"extra\":\"牛肉类\"},\"combos\":[{\"pid\":\"21100026\",\"name\":\"招牌牛肉饭/标\",\"num\":1.0,\"price\":30.5,\"addPrice\":0.0,\"skus\":[]},{\"pid\":\"42200009\",\"name\":\"三色时蔬\",\"num\":1.0,\"price\":6.0,\"addPrice\":1.0,\"skus\":[]},{\"pid\":\"31100002\",\"name\":\"中可乐\",\"num\":1.0,\"price\":8.0,\"addPrice\":0.0,\"skus\":[]}]},{\"pid\":\"52100162\",\"name\":\"新标牛套\",\"num\":1.0,\"price\":39.0,\"boxNum\":0.0,\"boxPrice\":0.0,\"itemDisc\":0.0,\"productTaxRate\":\"6\",\"productCategory\":{\"id\":\"1\",\"title\":\"品牌\"},\"productContent\":{\"id\":\"001\",\"title\":\"吉野家\"},\"dept\":{\"id\":\"0000Y02\",\"title\":\"正餐类\",\"subDept\":{\"id\":\"0000Y21\",\"title\":\"主餐类\"}},\"category\":{\"id\":\"0000211\",\"title\":\"牛肉类\"},\"types\":{\"bigType\":\"0000211\",\"extra\":\"牛肉类\"},\"combos\":[{\"pid\":\"21100026\",\"name\":\"招牌牛肉饭/标\",\"num\":1.0,\"price\":30.5,\"addPrice\":0.0,\"skus\":[]},{\"pid\":\"42200009\",\"name\":\"三色时蔬\",\"num\":1.0,\"price\":6.0,\"addPrice\":1.0,\"skus\":[]},{\"pid\":\"31100002\",\"name\":\"中可乐\",\"num\":1.0,\"price\":8.0,\"addPrice\":0.0,\"skus\":[]}]},{\"pid\":\"32101916\",\"name\":\"招牌牛肉套/大将军碗\",\"num\":2.0,\"price\":45.5,\"boxNum\":0.0,\"boxPrice\":0.0,\"itemDisc\":0.0,\"productTaxRate\":\"6\",\"productCategory\":{\"id\":\"1\",\"title\":\"品牌\"},\"productContent\":{\"id\":\"001\",\"title\":\"吉野家\"},\"dept\":{\"id\":\"0000Y02\",\"title\":\"正餐类\",\"subDept\":{\"id\":\"0000Y21\",\"title\":\"主餐类\"}},\"category\":{\"id\":\"0000211\",\"title\":\"牛肉类\"},\"types\":{\"bigType\":\"0000211\",\"extra\":\"牛肉类\"},\"combos\":[{\"pid\":\"32101914\",\"name\":\"招牌牛/大将军碗\",\"num\":1.0,\"price\":37.0,\"addPrice\":0.0,\"skus\":[]},{\"pid\":\"41400005\",\"name\":\"新合茶碗蒸\",\"num\":1.0,\"price\":7.5,\"addPrice\":2.5,\"skus\":[]},{\"pid\":\"42400298\",\"name\":\"酸菜银鱼汤\",\"num\":1.0,\"price\":9.0,\"addPrice\":1.5,\"skus\":[]}]},{\"pid\":\"32101916\",\"name\":\"招牌牛肉套/大将军碗\",\"num\":1.0,\"price\":45.5,\"boxNum\":0.0,\"boxPrice\":0.0,\"itemDisc\":0.0,\"productTaxRate\":\"6\",\"productCategory\":{\"id\":\"1\",\"title\":\"品牌\"},\"productContent\":{\"id\":\"001\",\"title\":\"吉野家\"},\"dept\":{\"id\":\"0000Y02\",\"title\":\"正餐类\",\"subDept\":{\"id\":\"0000Y21\",\"title\":\"主餐类\"}},\"category\":{\"id\":\"0000211\",\"title\":\"牛肉类\"},\"types\":{\"bigType\":\"0000211\",\"extra\":\"牛肉类\"},\"combos\":[{\"pid\":\"32101914\",\"name\":\"招牌牛/大将军碗\",\"num\":1.0,\"price\":37.0,\"addPrice\":0.0,\"skus\":[]},{\"pid\":\"41400005\",\"name\":\"新合茶碗蒸\",\"num\":1.0,\"price\":7.5,\"addPrice\":2.5,\"skus\":[]},{\"pid\":\"32105094\",\"name\":\"桃桃芒果气泡饮\",\"num\":1.0,\"price\":12.0,\"addPrice\":3.0,\"skus\":[]}]},{\"pid\":\"32101916\",\"name\":\"招牌牛肉套/大将军碗\",\"num\":1.0,\"price\":45.5,\"boxNum\":0.0,\"boxPrice\":0.0,\"itemDisc\":0.0,\"productTaxRate\":\"6\",\"productCategory\":{\"id\":\"1\",\"title\":\"品牌\"},\"productContent\":{\"id\":\"001\",\"title\":\"吉野家\"},\"dept\":{\"id\":\"0000Y02\",\"title\":\"正餐类\",\"subDept\":{\"id\":\"0000Y21\",\"title\":\"主餐类\"}},\"category\":{\"id\":\"0000211\",\"title\":\"牛肉类\"},\"types\":{\"bigType\":\"0000211\",\"extra\":\"牛肉类\"},\"combos\":[{\"pid\":\"32101914\",\"name\":\"招牌牛/大将军碗\",\"num\":1.0,\"price\":37.0,\"addPrice\":0.0,\"skus\":[]},{\"pid\":\"41400005\",\"name\":\"新合茶碗蒸\",\"num\":1.0,\"price\":7.5,\"addPrice\":2.5,\"skus\":[]},{\"pid\":\"32101730\",\"name\":\"冰红茶/中\",\"num\":1.0,\"price\":8.5,\"addPrice\":0.5,\"skus\":[]}]},{\"pid\":\"32101916\",\"name\":\"招牌牛肉套/大将军碗\",\"num\":1.0,\"price\":45.5,\"boxNum\":0.0,\"boxPrice\":0.0,\"itemDisc\":0.0,\"productTaxRate\":\"6\",\"productCategory\":{\"id\":\"1\",\"title\":\"品牌\"},\"productContent\":{\"id\":\"001\",\"title\":\"吉野家\"},\"dept\":{\"id\":\"0000Y02\",\"title\":\"正餐类\",\"subDept\":{\"id\":\"0000Y21\",\"title\":\"主餐类\"}},\"category\":{\"id\":\"0000211\",\"title\":\"牛肉类\"},\"types\":{\"bigType\":\"0000211\",\"extra\":\"牛肉类\"},\"combos\":[{\"pid\":\"32101914\",\"name\":\"招牌牛/大将军碗\",\"num\":1.0,\"price\":37.0,\"addPrice\":0.0,\"skus\":[]},{\"pid\":\"41300001\",\"name\":\"摇滚沙拉\",\"num\":1.0,\"price\":9.0,\"addPrice\":4.0,\"skus\":[]},{\"pid\":\"32101730\",\"name\":\"冰红茶/中\",\"num\":1.0,\"price\":8.5,\"addPrice\":0.5,\"skus\":[]}]},{\"pid\":\"32101916\",\"name\":\"招牌牛肉套/大将军碗\",\"num\":1.0,\"price\":45.5,\"boxNum\":0.0,\"boxPrice\":0.0,\"itemDisc\":0.0,\"productTaxRate\":\"6\",\"productCategory\":{\"id\":\"1\",\"title\":\"品牌\"},\"productContent\":{\"id\":\"001\",\"title\":\"吉野家\"},\"dept\":{\"id\":\"0000Y02\",\"title\":\"正餐类\",\"subDept\":{\"id\":\"0000Y21\",\"title\":\"主餐类\"}},\"category\":{\"id\":\"0000211\",\"title\":\"牛肉类\"},\"types\":{\"bigType\":\"0000211\",\"extra\":\"牛肉类\"},\"combos\":[{\"pid\":\"32101914\",\"name\":\"招牌牛/大将军碗\",\"num\":1.0,\"price\":37.0,\"addPrice\":0.0,\"skus\":[]},{\"pid\":\"41300001\",\"name\":\"摇滚沙拉\",\"num\":1.0,\"price\":9.0,\"addPrice\":4.0,\"skus\":[]},{\"pid\":\"32101732\",\"name\":\"蜂蜜柚子茶\",\"num\":1.0,\"price\":10.0,\"addPrice\":2.0,\"skus\":[]}]},{\"pid\":\"32101916\",\"name\":\"招牌牛肉套/大将军碗\",\"num\":1.0,\"price\":45.5,\"boxNum\":0.0,\"boxPrice\":0.0,\"itemDisc\":0.0,\"productTaxRate\":\"6\",\"productCategory\":{\"id\":\"1\",\"title\":\"品牌\"},\"productContent\":{\"id\":\"001\",\"title\":\"吉野家\"},\"dept\":{\"id\":\"0000Y02\",\"title\":\"正餐类\",\"subDept\":{\"id\":\"0000Y21\",\"title\":\"主餐类\"}},\"category\":{\"id\":\"0000211\",\"title\":\"牛肉类\"},\"types\":{\"bigType\":\"0000211\",\"extra\":\"牛肉类\"},\"combos\":[{\"pid\":\"32101914\",\"name\":\"招牌牛/大将军碗\",\"num\":1.0,\"price\":37.0,\"addPrice\":0.0,\"skus\":[]},{\"pid\":\"42200009\",\"name\":\"三色时蔬\",\"num\":1.0,\"price\":6.0,\"addPrice\":1.0,\"skus\":[]},{\"pid\":\"31100003\",\"name\":\"大可乐\",\"num\":1.0,\"price\":9.5,\"addPrice\":1.5,\"skus\":[]}]},{\"pid\":\"32101916\",\"name\":\"招牌牛肉套/大将军碗\",\"num\":1.0,\"price\":45.5,\"boxNum\":0.0,\"boxPrice\":0.0,\"itemDisc\":0.0,\"productTaxRate\":\"6\",\"productCategory\":{\"id\":\"1\",\"title\":\"品牌\"},\"productContent\":{\"id\":\"001\",\"title\":\"吉野家\"},\"dept\":{\"id\":\"0000Y02\",\"title\":\"正餐类\",\"subDept\":{\"id\":\"0000Y21\",\"title\":\"主餐类\"}},\"category\":{\"id\":\"0000211\",\"title\":\"牛肉类\"},\"types\":{\"bigType\":\"0000211\",\"extra\":\"牛肉类\"},\"combos\":[{\"pid\":\"32101914\",\"name\":\"招牌牛/大将军碗\",\"num\":1.0,\"price\":37.0,\"addPrice\":0.0,\"skus\":[]},{\"pid\":\"42200009\",\"name\":\"三色时蔬\",\"num\":1.0,\"price\":6.0,\"addPrice\":1.0,\"skus\":[]},{\"pid\":\"32100121\",\"name\":\"新橙汁缤纷\",\"num\":1.0,\"price\":10.0,\"addPrice\":2.0,\"skus\":[]}]},{\"pid\":\"32101916\",\"name\":\"招牌牛肉套/大将军碗\",\"num\":1.0,\"price\":45.5,\"boxNum\":0.0,\"boxPrice\":0.0,\"itemDisc\":0.0,\"productTaxRate\":\"6\",\"productCategory\":{\"id\":\"1\",\"title\":\"品牌\"},\"productContent\":{\"id\":\"001\",\"title\":\"吉野家\"},\"dept\":{\"id\":\"0000Y02\",\"title\":\"正餐类\",\"subDept\":{\"id\":\"0000Y21\",\"title\":\"主餐类\"}},\"category\":{\"id\":\"0000211\",\"title\":\"牛肉类\"},\"types\":{\"bigType\":\"0000211\",\"extra\":\"牛肉类\"},\"combos\":[{\"pid\":\"32101914\",\"name\":\"招牌牛/大将军碗\",\"num\":1.0,\"price\":37.0,\"addPrice\":0.0,\"skus\":[]},{\"pid\":\"41200001\",\"name\":\"金枪鱼土豆泥沙拉\",\"num\":1.0,\"price\":5.0,\"addPrice\":0.0,\"skus\":[]},{\"pid\":\"31100002\",\"name\":\"中可乐\",\"num\":1.0,\"price\":8.0,\"addPrice\":0.0,\"skus\":[]}]},{\"pid\":\"32101916\",\"name\":\"招牌牛肉套/大将军碗\",\"num\":1.0,\"price\":45.5,\"boxNum\":0.0,\"boxPrice\":0.0,\"itemDisc\":0.0,\"productTaxRate\":\"6\",\"productCategory\":{\"id\":\"1\",\"title\":\"品牌\"},\"productContent\":{\"id\":\"001\",\"title\":\"吉野家\"},\"dept\":{\"id\":\"0000Y02\",\"title\":\"正餐类\",\"subDept\":{\"id\":\"0000Y21\",\"title\":\"主餐类\"}},\"category\":{\"id\":\"0000211\",\"title\":\"牛肉类\"},\"types\":{\"bigType\":\"0000211\",\"extra\":\"牛肉类\"},\"combos\":[{\"pid\":\"32101914\",\"name\":\"招牌牛/大将军碗\",\"num\":1.0,\"price\":37.0,\"addPrice\":0.0,\"skus\":[]},{\"pid\":\"41200001\",\"name\":\"金枪鱼土豆泥沙拉\",\"num\":1.0,\"price\":5.0,\"addPrice\":0.0,\"skus\":[]},{\"pid\":\"32100120\",\"name\":\"新仙桃蜜语\",\"num\":1.0,\"price\":10.0,\"addPrice\":2.0,\"skus\":[]}]},{\"pid\":\"52100162\",\"name\":\"新标牛套\",\"num\":1.0,\"price\":39.0,\"boxNum\":0.0,\"boxPrice\":0.0,\"itemDisc\":0.0,\"productTaxRate\":\"6\",\"productCategory\":{\"id\":\"1\",\"title\":\"品牌\"},\"productContent\":{\"id\":\"001\",\"title\":\"吉野家\"},\"dept\":{\"id\":\"0000Y02\",\"title\":\"正餐类\",\"subDept\":{\"id\":\"0000Y21\",\"title\":\"主餐类\"}},\"category\":{\"id\":\"0000211\",\"title\":\"牛肉类\"},\"types\":{\"bigType\":\"0000211\",\"extra\":\"牛肉类\"},\"combos\":[{\"pid\":\"21100026\",\"name\":\"招牌牛肉饭/标\",\"num\":1.0,\"price\":30.5,\"addPrice\":0.0,\"skus\":[]},{\"pid\":\"41400005\",\"name\":\"新合茶碗蒸\",\"num\":1.0,\"price\":7.5,\"addPrice\":2.5,\"skus\":[]},{\"pid\":\"31100005\",\"name\":\"中七喜\",\"num\":1.0,\"price\":8.0,\"addPrice\":0.0,\"skus\":[]}]}]}}";
		
		
		//String json="{\"accessKey\":\"fb4fda21afeddcf4\",\"actionName\":\"candao.order.postDineInOrder\",\"timestamp\":1592725424534,\"ticket\":\"41d4db6e-2395-48e5-a017-ce8747b96830\",\"sign\":\"bc5b41b18e851f19eb378aaff6890d6b\",\"serviceType\":\"pos\",\"vendor\":\"seito\",\"storeId\":\"YS411017\",\"data\":{\"orderId\":\"YS411017001202006190292267414\",\"thirdSn\":\"0011359\",\"storeId\":\"YS411017\",\"counts\":-1,\"fromType\":\"pos\",\"takeNo\":\"0159\",\"peopleNum\":0,\"productPrice\":-9.9,\"deviceNo\":\"001\",\"staffId\":\"0006095011\",\"staffBane\":\"\",\"orderTime\":\"2020-06-19 16:19:14\",\"orderDate\":\"2020-06-19\",\"orderStatus\":100,\"orderType\":3,\"posOrderType\":\"0\",\"isPayed\":true,\"paymentDetails\":[{\"payType\":1,\"type\":1,\"typeName\":\"现金\",\"money\":-9.9,\"posType\":\"CASH\",\"posName\":\"现金\",\"num\":0}],\"isInvoice\":false,\"price\":-9.9,\"deliveryFee\":0.0,\"mealFee\":0.0,\"discountPrice\":0.0,\"merchantBearPrice\":0.0,\"thirdPlatformBearPrice\":0.0,\"merchantPrice\":-9.9,\"originPrice\":-9.9,\"commission\":0.0,\"status\":[{\"title\":\"下单（快餐）\",\"value\":\"105\",\"dateTime\":\"2020-06-19 16:19:02\"},{\"title\":\"支付（快餐）\",\"value\":\"110\",\"dateTime\":\"2020-06-19 16:19:14\"},{\"title\":\"支付完成（快餐）\",\"value\":\"115\",\"dateTime\":\"2020-06-19 16:19:15\"}],\"products\":[{\"pid\":\"414A0010\",\"name\":\"加点-卤琵琶腿/个\",\"num\":-1.0,\"price\":9.9,\"boxNum\":0.0,\"boxPrice\":0.0,\"itemDisc\":0.0,\"productTaxRate\":\"6\",\"productCategory\":{\"id\":\"1\",\"title\":\"品牌\"},\"productContent\":{\"id\":\"001\",\"title\":\"吉野家\"},\"dept\":{\"id\":\"0000Y04\",\"title\":\"副食类\",\"subDept\":{\"id\":\"0000Y42\",\"title\":\"副食品系列\"}},\"category\":{\"id\":\"0000422\",\"title\":\"副食品\"},\"types\":{\"bigType\":\"0000422\",\"extra\":\"副食品\"}}]}}";
		String json="{\"timestamp\":1604379043122,\"vendor\":\"ncr\",\"storeId\":\"DQ411007\",\"ticket\":\"65867858-1178-48ba-9af3-910c34dc9f12\",\"actionName\":\"candao.order.postDineInOrder\",\"sign\":\"9e6fce6a17f4c74108c5715b80bc651b\",\"data\":{\"orderTime\":\"2020-11-02 12:32:24\",\"orderStatus\":100,\"status\":[{\"value\":105,\"dateTime\":\"2020-11-02 12:31:58\",\"title\":\"下单（快餐）\"},{\"value\":115,\"dateTime\":\"2020-11-02 12:32:24\",\"title\":\"支付完成（快餐）\"}],\"storeId\":\"DQ411007\",\"orderDate\":\"2020-11-02\",\"thirdSn\":\"1032\",\"orderId\":\"DQ4110072011021032\",\"fromType\":\"pos\",\"deviceNo\":\"1\",\"staffId\":\"113\",\"staffBane\":\"李德毅\",\"orderType\":3,\"posOrderType\":1,\"payType\":1,\"isPayed\":true,\"paymentDetails\":[{\"payType\":1,\"type\":16,\"tradeNo\":\"DQ411007202011021232031032\",\"typeName\":\"支付宝线下支付\",\"posType\":\"44\",\"money\":15.0,\"posName\":\"支付宝应收\"}],\"price\":15.0,\"deliveryFee\":0,\"mealFee\":0,\"discountPrice\":-0.0,\"thirdPlatformBearPrice\":0,\"merchantBearPrice\":0.0,\"merchantPrice\":15.0,\"commission\":0,\"discounts\":[],\"products\":[{\"name\":\"海盐焦糖曲奇奶茶-热\",\"price\":15.0,\"types\":{\"extra\":\"冰淇淋\",\"bigType\":\"1\"},\"pid\":\"1170127\",\"productTaxRate\":\"6%\",\"dept\":{\"title\":\"热饮系列\",\"id\":\"17\"},\"num\":1,\"itemDisc\":0.0}],\"originPrice\":15.0,\"productPrice\":15.0,\"counts\":1},\"serviceType\":\"pos\",\"accessKey\":\"4ca533f4b7f5da07\"}\r\n" + 
				"";
		
		
		
		
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
		
		/*
		 * String sr = HttpRequest.sendPost( "http://zt_qc.can-dao.com:81/api\r\n" +
		 * "",jsonNewObj);
		 */
		
		String sr = HttpRequest.sendPost("http://open-api.hophing.cn/api",jsonNewObj);

		 System.out.println("result ----->  "+sr);

		// 提交正常单

		
		  long l = Long.valueOf("1604238827098").longValue();
		  System.out.println(getTimeStamptoString(l));
	}
	public static void main(String[] args) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub

	
		//System.out.println( getFirstDay("MMdd"));
		//System.out.println( getYesterday("MMdd"));
		String intime="2020-12-01";
		 String first=TimeUtils.getFirstDay("yyyy-MM-dd");
		 String last=TimeUtils.getYesterday("yyyy-MM-dd");
		 String yesterday=TimeUtils.getYesterday("yyyy-MM-dd");
		 String firstpatten=first.substring(5, 7)+first.substring(8, 10);
		 String lastpatten=yesterday.substring(5, 7)+yesterday.substring(8, 10);
		
		//System.out.println(lastpatten);
		 
		 
		 //getTimeStamptoLong("2021-01-18 16:00:00");
		 System.out.println(getToday("yyyy-MM-dd"));

	}
	
	
	

}
