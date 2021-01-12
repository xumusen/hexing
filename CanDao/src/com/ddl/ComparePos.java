package com.ddl;

import java.io.IOException;
import java.net.SocketException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ResourceBundle;

import com.entity.Tstore;
import com.utils.DatetoExcel;
import com.utils.SendEmail;
import com.utils.TimeUtils;

public class ComparePos {
	static ResourceBundle resource = ResourceBundle.getBundle("web");
	private final static String stPath = resource.getString("stPath");
	private final static String ybPath = resource.getString("ybPath");
	//private final static String first="2020-12-01";
	//private final static String last="2020-12-31";
	private final static String first=TimeUtils.getFirstDay("yyyy-MM-dd");
	private final static String last=TimeUtils.getYesterday("yyyy-MM-dd");
	private final static String yesterday=TimeUtils.getYesterday("yyyy-MM-dd");
	private final static String firstpatten=first.substring(5, 7)+first.substring(8, 10);
	private final static String lastpatten=yesterday.substring(5, 7)+yesterday.substring(8, 10);
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("请确认好已经下载好世通和银豹的t表");
		String begintime=TimeUtils.getYesterday("yyyy-MM-dd")+" 09:00:00";
		//DataGatherLocal.reLoadFile("2021-01-07 09:00:00");
		DataGatherLocal.reLoadFile(begintime);
		//DataGatherLocal.reLoadFile("2020-12-10 07:00:00",ybPath,"2");
		/*删除t表*/
		//Tstore.truncateT();//中午别执行
		/*重写t表*/
		//DataGatherLocal.main(args);//中午别执行
		/*获取大数据hive*/
		GetOrderInfoSum.getorderinfo(first,last);
		GetOrderInfoSum.getorderinfoYb(first,last);
		//更新txt的最新时间
		//DataGather.getSt();//如执行了reload方法，可不执行
		/*获取大数据门店明细*/
		GetHiveSingleStoreDetail.getDetail(first,last);
		/*对比形成差异数据*/
		GetOrderDiff.getorderdifff(first,last);
		GetOrderDiff.getYborderdifff(first,last);
		/*对比外卖和小程序差异数据*/
		GetFooSum.getFooSum(first,last,"","22");
		GetFooSum.getFooSum(first,last,"","06");
		/*生成excel报表*/
		DatetoExcel.datetoexcel(first,last);
		/*发送邮件*/
		//SendEmail.sendemail();
	}

}
