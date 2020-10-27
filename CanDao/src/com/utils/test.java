package com.utils;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import com.entity.ReportFile;

public class test {

    public static void main(String[] args) throws SQLException {
    	   // 获取当月第一天和最后一天
        Calendar cale = null;
        cale = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String firstday, lastday;
        // 获取本月的第一天
        cale = Calendar.getInstance();
        cale.add(Calendar.MONTH, 0);
        cale.set(Calendar.DAY_OF_MONTH, 1);
        firstday = format.format(cale.getTime());
        // 获取本月的最后一天
        cale = Calendar.getInstance();
        cale.add(Calendar.MONTH, 1);
        cale.set(Calendar.DAY_OF_MONTH, 0);
        lastday = format.format(cale.getTime());
        System.out.println("本月第一天和最后一天分别是 ： " + firstday + " and " + lastday);
    	//获取本月的总天数【会自动计算出来的，不用担心误差】
    	//只需要两行代码即可：
    	java.util.Calendar cal = java.util.Calendar.getInstance();
    	int maxDay = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
    	System.out.println(maxDay);  //输出的即时本月的天数
    	SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
    	Date date = new Date(System.currentTimeMillis());
    	System.out.println(formatter.format(date));
    	
    	Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1); //得到前一天
		Date date1 = calendar.getTime();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(df.format(date1)); 
    }

}
