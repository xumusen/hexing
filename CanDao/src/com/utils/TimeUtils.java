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
		
		Timestamp ts =new Timestamp(System.currentTimeMillis());
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time=sdf.format(ts);
	//System.out.println(System.currentTimeMillis());
		String accessKey="125b03eccf81cbd4";
		String actionName="candao.order.postDineInOrder";
		String secret="f5eba5c8d37e4f03d30cf8ed41b050a9";
		long timestamp=System.currentTimeMillis();
		System.out.println(timestamp);
		String data="{\"fromType\":\"xiucan\",\"orderId\":\"15151505090115814049856760000008\",\"storeId\":\"YS010204\",\"userNote\":\"\",\"orderTime\":\"2020-02-11 15:09:53\",\"orderDate\":\"2020-02-11\",\"orderStatus\":7,\"orderType\":3,\"payType\":2,\"isPayed\":true,\"paymentDetails\":[{\"payType\":2,\"money\":28,\"type\":2,\"typeName\":\"微信\"}],\"discounts\":[],\"isInvoice\":true,\"price\":28,\"mealFee\":0,\"discountPrice\":0,\"merchantBearPrice\":0,\"merchantPrice\":28,\"products\":[{\"pid\":\"32101900\",\"name\":\"赤金牛肉面\",\"num\":1,\"price\":28}]}";
		String sign = MD5.getMD5Str(accessKey + actionName + secret + timestamp  + (StringUtil.isNullOrBlank(data)?"" : data));
		System.out.println(sign);		
	}

}
