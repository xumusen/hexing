package com.utils;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import com.ddl.GetOdsOrder;
import com.ddl.GetOrderInfoDq;
import com.entity.ReportFile;

public class test {


    public static void main(String[] args) throws Exception {

		/*
		 * GetOdsOrder.getdiscountdiff("YS010069", "20201010");
		 * GetOdsOrder.getdiscountdiff("YS010069", "20201011");
		 * GetOdsOrder.getdiscountdiff("YS010069", "20201012");
		 * GetOdsOrder.getdiscountdiff("YS010083", "20201005");
		 * GetOdsOrder.getdiscountdiff("YS010083", "20201012");
		 */
    	//GetOdsOrder.getdiscountdiff("4200000769202010116165374126", "20201016");
    	//GetOrderInfoDq.orderinfoDq("YS451018", "2020-10-01", "2020-10-31");
    	Calendar cale = Calendar.getInstance();
		Timestamp ts =new Timestamp(System.currentTimeMillis());
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String nowday = sdf.format(ts);
		String firstday;
		SimpleDateFormat dayformat = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(nowday.length());

		if (nowday.substring(8, 10).equals("01")) 
		{
			cale.add(Calendar.MONTH, -1);
			cale.set(Calendar.DAY_OF_MONTH, 1);
			firstday = dayformat.format(cale.getTime());

			System.out.println(firstday+"-1");

		} 
		else
		{
			cale.add(Calendar.MONTH, 1);
			cale.set(Calendar.DAY_OF_MONTH, 0);
			firstday = dayformat.format(cale.getTime());
			System.out.println(firstday+"-2");
		}
		System.out.println(nowday.substring(nowday.length()-2));
	}
    

}
