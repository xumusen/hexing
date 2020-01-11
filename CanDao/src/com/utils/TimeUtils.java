package com.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class TimeUtils {
	
	
	public static String getTimeStamptoString(long timestamp) {
	//	Timestamp ts =new Timestamp(System.currentTimeMillis());
		Timestamp ts =new Timestamp(timestamp);
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time=sdf.format(ts);
		System.out.println(time);
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
		String t="1574859897966";
		long timestamp= Long.valueOf(t);;
		getTimeStamptoString(timestamp);
	}

}
