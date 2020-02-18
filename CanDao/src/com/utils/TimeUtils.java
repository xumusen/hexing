package com.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


import md5.MD5;

public class TimeUtils {
	
	
	public static String getTimeStamptoString(long timestamp) {
	//	Timestamp ts =new Timestamp(System.currentTimeMillis());
		Timestamp ts =new Timestamp(timestamp);
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time=sdf.format(ts);
		//System.out.println(time);
		return time;
	}

	public static void main(String[] args) {
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

		Timestamp ts = new Timestamp(System.currentTimeMillis());
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = sdf.format(ts);
		String accessKey = "125b03eccf81cbd4";
		String actionName = "candao.order.postDineInOrder";
		String secret = "f5eba5c8d37e4f03d30cf8ed41b050a9";
		long timestamp = System.currentTimeMillis();
		System.out.println(timestamp);
		//String xiucandata = "{\"fromType\":\"xiucan\",\"orderId\":\"15151505090115814049856760000008\",\"storeId\":\"0109999\",\"userNote\":\"\",\"orderTime\":\"2020-02-11 15:09:53\",\"orderDate\":\"2020-02-11\",\"orderStatus\":7,\"orderType\":3,\"payType\":2,\"isPayed\":true,\"paymentDetails\":[{\"payType\":2,\"money\":28,\"type\":2,\"typeName\":\"微信\"}],\"discounts\":[],\"isInvoice\":true,\"price\":28,\"mealFee\":0,\"discountPrice\":0,\"merchantBearPrice\":0,\"merchantPrice\":28,\"products\":[{\"pid\":\"32101900\",\"name\":\"赤金牛肉面\",\"num\":1,\"price\":28}]}";
		String xiucandata="{\"fromType\":\"xiucan\",\"orderId\":\"15151505090115814049856760000009\",\"storeId\":\"0109999\",\"userNote\":\"\",\"orderTime\":\"2020-02-11 15:09:53\",\"orderDate\":\"2020-02-11\",\"orderStatus\":7,\"orderType\":3,\"payType\":2,\"isPayed\":true,\"paymentDetails\":[{\"payType\":2,\"money\":28,\"type\":2,\"typeName\":\"微信\"}],\"discounts\":[],\"isInvoice\":true,\"price\":28,\"mealFee\":0,\"discountPrice\":0,\"merchantBearPrice\":0,\"merchantPrice\":28,\"products\":[{\"pid\":\"32101900\",\"name\":\"赤金牛肉面\",\"num\":1,\"price\":28}]}";
		String sign = MD5.getMD5Str(
				accessKey + actionName + secret + timestamp + (StringUtil.isNullOrBlank(xiucandata) ? "" : xiucandata));
		System.out.println(sign);
		 
		
		
	
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
		
		
	}

}
