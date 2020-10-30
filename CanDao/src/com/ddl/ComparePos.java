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
		/*删除t表*/
		//Tstore.truncateT();//中午别执行
		/*重写t表*/
		//DataGatherLocal.main(args);//中午别执行
		/*获取大数据hive*/
		GetOrderInfoSum.main(args);
		//更新txt的最新时间
		DataGather.getSt();
		/*获取大数据门店明细*/
		GetHiveSingleStoreDetail.main(args);
		/*对比形成差异数据*/
		GetOrderDiff.main(args);
		/*生成excel报表*/
		DatetoExcel.main(args);
		/*发送邮件*/
		SendEmail.main(args);
	}

}
