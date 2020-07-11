package com.utils;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Rulveli {
	// 儒略历：113260 = 前三位（2013-1900）+后三位（2013年中的第260天）

	// 'yyyy-MM-dd'时间格式转换为六位数字日历
	public static String DateToJuLian(Date date) {
		DecimalFormat df = new DecimalFormat("000");
		Calendar calenda = Calendar.getInstance();
		calenda.setTime(date);
		String SDpmdt1 = String.valueOf(calenda.get(Calendar.YEAR) - 1900);
		String SDpmdt2 = df.format(calenda.get(Calendar.DAY_OF_YEAR));
		String SDdrqj3 = SDpmdt1 + SDpmdt2;

		return SDdrqj3;
	}

	// 儒略历日期（6位数字）转换为('yyyy-MM-dd')日期格式
	public static String JuLianToDate(String date) {

		int i = 0;
		int j = 0;
		// 年 月 日
		int YR = 0;
		int DA = 0;
		// 得到JULian日期串
		if (date == null || date.equals("null") || date.trim().length() == 0) {
			return "";
		}
		int JDate = Integer.parseInt(date);
		String DateString = "";
		int[] MONARR = new int[] { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		DA = JDate % 1000;
		YR = JDate / 1000;
		if (YR > 99) {
			YR = 2000 + (YR % 100);
		} else {
			YR = 1900 + YR;
		}
		if (((YR % 4 == 0) && (YR % 100 != 0)) || (YR % 400 == 0)) {
			MONARR[1] = 29;
		}

		for (j = 0, i = DA; i > MONARR[j]; j++) {
			i -= MONARR[j];
		}
		j = j + 1;

		DateString = YR + "-" + j + "-" + i;

		return DateString;
	}

	// 数字形式时间转换为("hh:mm:ss")
	public static String JuLianTotime(String time) {

		int ss = 0;
		int mm = 0;
		int hh = 0;
		String s = null; // 秒
		String m = null; // 分
		String h = null; // 小时
		String TimeString = "";

		if (time == null || time.equals("null") || time.trim().length() == 0) {
			return "";
		}
		int Jtime = Integer.parseInt(time);
		ss = Jtime % 100;
		if (ss < 10 && ss >= 0) {
			s = String.valueOf(ss);
			s = "0" + s;
		} else {
			s = String.valueOf(ss);
		}

		mm = Jtime / 100 % 100;
		if (mm < 10 && mm >= 0) {
			m = String.valueOf(mm);
			m = "0" + m;
		} else {
			m = String.valueOf(mm);
		}

		hh = Jtime / 10000;
		if (hh < 10 && hh >= 0) {
			h = String.valueOf(hh);
			h = "0" + h;
		} else {
			h = String.valueOf(hh);
		}
		TimeString = h + ":" + m + ":" + s;
		return TimeString;

	}

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		
		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd"); 
		String stime ="2020-06-22";  //120167(15)  120173(21)
		System.out.println(DateToJuLian(format1.parse(stime)));
		
		System.out.println(JuLianToDate("120177"));
	}

}
