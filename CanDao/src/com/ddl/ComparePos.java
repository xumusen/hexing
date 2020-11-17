package com.ddl;

import java.io.IOException;
import java.net.SocketException;
import java.sql.SQLException;
import java.text.ParseException;

import com.entity.Tstore;
import com.utils.DatetoExcel;
import com.utils.SendEmail;

public class ComparePos {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("请确认好已经下载好t表及导入了最新的ncr报表数据");
		DataGatherLocal.reLoadFile("2020-11-17 09:00:00");
		/*删除t表*/
		//Tstore.truncateT();//中午别执行
		/*重写t表*/
		//DataGatherLocal.main(args);//中午别执行
		/*获取大数据hive*/
		GetOrderInfoSum.getorderinfo();
		//更新txt的最新时间
		//DataGather.getSt();//如执行了reload方法，可不执行
		/*获取大数据门店明细*/
		GetHiveSingleStoreDetail.getDetail();
		/*对比形成差异数据*/
		GetOrderDiff.getorderdifff();
		/*生成excel报表*/
		DatetoExcel.datetoexcel();
		/*发送邮件*/
		SendEmail.sendemail();
	}

}
