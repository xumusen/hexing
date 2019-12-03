package com.ddl;


import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONReader;
import com.alibaba.fastjson.JSONWriter;
import com.entity.OrderCollect;
import com.entity.OrdersTemp;

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
			/*System.out.print(sbf.toString());
			JSONObject jsonobj = JSONObject.fromObject(sbf.toString());
			JSONArray orders = JSONArray.fromObject(jsonobj.getString("rows"));
			JSONObject orderObject = orders.getJSONObject(1);
			String json=orderObject.getString("/"+"dm5u_prefix_paas_data_analyse_data_json"+"/");
			System.out.print(json);*/
			
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
	
	public static <T> List<T> getOrderObjectsByClass(String jsonName ,String jsonPath, Class<T> type){

        LinkedList<T> returnList = new LinkedList<T>();
        File file = new File(jsonPath);
        InputStreamReader isr = null;
        BufferedReader bufferedReader = null;
        try {
            isr = new InputStreamReader(new FileInputStream(file), "utf-8");
            bufferedReader = new BufferedReader(isr);
 
            JSONReader reader = new JSONReader(bufferedReader);
            reader.startArray();
            while (reader.hasNext()) {
                T readObject = reader.readObject(type);
                returnList.add(readObject);
            }
            reader.endArray();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally{
            try {
                if(null != isr){
                    isr.close();
                }
                if(null != bufferedReader){
                    bufferedReader.close();
                }
            } catch (Exception e2) {
            }
        }
        return returnList;

	}
	
	
	public static void main(String[] args) throws IOException, SQLException {
		// TODO Auto-generated method stub
		//String content = Test.readFileContent("/Users/jason/OneDrive - cd/项目资料/吉野家/order/1202/appDataController.json").trim();
		String filename="H:\\OneDrive - cd\\项目资料\\吉野家\\order\\1202\\appDataController - 副本.json";
		String filenames="H:\\OneDrive - cd\\项目资料\\吉野家\\order\\1202\\appDataController.json";
		String writename="H:\\OneDrive - cd\\项目资料\\吉野家\\order\\1202\\appDataWrite.json";
		String content = Test.readFileContent(filename).trim();
		
		LinkedList<OrderCollect> returnList=(LinkedList<OrderCollect>) Test3.getOrderObjectsByClass("",filename,OrderCollect.class);
		System.out.println(returnList.size());
		for(int i=0;i<returnList.size();i++){
			//OrderCollect.insertPaymentDetails(returnList.get(i));
			System.out.println(returnList.get(i).getDm5u_prefix_paas_data_analyse_data_json());
		}
		
		
		

		
		
/*		
		 JSONWriter writer = new JSONWriter(new FileWriter(writename));
		  writer.startObject();
		  for (int i = 0; i < 1000 * 1000; ++i) {
		        writer.writeKey("x" + i);
		        OrderCollect collect=new OrderCollect();
		        writer.writeValue(collect);
		  }
		  writer.endObject();
		  writer.close();
	*/	

/*		
		  JSONReader reader = new JSONReader(new FileReader(filenames));
		  reader.startObject();
		  while(reader.hasNext()) {
			  String key = reader.readString();
			  OrdersTemp ordersTemp=reader.readObject(OrdersTemp.class);
		      System.out.println(ordersTemp.getRows().get(0).getDm5u_prefix_paas_data_analyse_data_json()); 
		  }
		  reader.endObject();
		  reader.close();*/
		  
		  
		  /*
	  	  reader.startArray();
		  while(reader.hasNext()) {
		        
			  System.out.println(reader.readObject(OrderCollect.class));
			  //VO vo = reader.readObject(VO.class);
		        // handle vo ...
		  }
		  reader.endArray();
		  reader.close();*/
		
		
		//System.out.print(content);
		/*JSONObject jsonobj = JSONObject.fromObject(content);// 将字符串转化成json对象
		JSONArray orders = JSONArray.fromObject(jsonobj.getString("rows"));// 将字符串转化成json对象
		//System.out.print(orders.size());
		JSONObject orderObject = orders.getJSONObject(1);
		String json=orderObject.getString("/"+"dm5u_prefix_paas_data_analyse_data_json"+"/");
		System.out.print(json);*/
		
	}

}
