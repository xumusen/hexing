package com.ddl;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Test3 {
	public static String readFileContent(String fileName) {
		//File file = new File(fileName);
		BufferedReader reader = null;
		File file = new File(fileName); 
		StringBuffer sbf = new StringBuffer();
		try {
			//reader = new BufferedReader(new FileReader(file));
			BufferedInputStream fis = new BufferedInputStream(new FileInputStream(file));  
			 reader = new BufferedReader(new InputStreamReader(fis,"utf-8"),1*1024*1024);// 用5M的缓冲读取文本文件  
			 
			String tempStr;
			while ((tempStr = reader.readLine()) != null) {
				sbf.append(tempStr);
			}
			reader.close();
			System.out.print(sbf.toString());
			JSONObject jsonobj = JSONObject.fromObject(sbf.toString());
			JSONArray orders = JSONArray.fromObject(jsonobj.getString("rows"));
			JSONObject orderObject = orders.getJSONObject(1);
			String json=orderObject.getString("/"+"dm5u_prefix_paas_data_analyse_data_json"+"/");
			System.out.print(json);
			
			return sbf.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		return sbf.toString();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String content = Test.readFileContent("/Users/jason/OneDrive - cd/项目资料/吉野家/order/1202/appDataController.json").trim();
		Test3.readFileContent("/Users/jason/OneDrive - cd/项目资料/吉野家/order/1202/appDataController.json");
		//System.out.print(content);
		/*JSONObject jsonobj = JSONObject.fromObject(content);// 将字符串转化成json对象
		JSONArray orders = JSONArray.fromObject(jsonobj.getString("rows"));// 将字符串转化成json对象
		//System.out.print(orders.size());
		JSONObject orderObject = orders.getJSONObject(1);
		String json=orderObject.getString("/"+"dm5u_prefix_paas_data_analyse_data_json"+"/");
		System.out.print(json);*/
		
	}

}
