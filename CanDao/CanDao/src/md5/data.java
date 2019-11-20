package md5;

import java.io.InputStream;
import java.util.Properties;

import net.sf.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream; 
import java.util.Iterator;
import java.util.Properties; 

public class data {

	/**
	 * @param args
	 */
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
/*
		String data = Md5Encrypt.md5("�й�");
		System.out.println(data);
		*/
		
		
		System.out.println("java代码封装为json字符串");
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("username", "宋发元");
		jsonObject.put("age", 24);
		jsonObject.put("sex", "男");
		System.out.println("java--->json \n " + JSONObject.fromObject(jsonObject).toString());
		String a="";
		System.out.println();
		
		
		
		String yesterday="today";
		String today="0513";
					String params = "{\"request\": { " +
	       "\"ReportName\":\"SA_SaleOrderDetailRpt\"," +
	       "\"PageIndex\":\"1\"," +
	       "\"PageSize\":\"200000\"," +
	       "\"SearchItems\": [{ " +
	       	            "\"ColumnName\":\"voucherdate\"," +
	       	            "\"BeginDefault\":\"" +yesterday+"\","+
	       	            "\"BeginDefaultText\":\"" +yesterday+"\","+
	       	            "\"EndDefault\":\"" +today+"\","+
	       	            "\"EndDefaultText\":\"" +today+"\""+
	       "}],"+
	       "\"ReportTableColNames\":\"VoucherDate,saleDeliveryCode,partnerCode,partnerName,origTaxAmount\"" +	            
	"}}";
					
					System.out.println(params.toString().toString());
/*		
		Properties prop = new Properties();     
        try{
            //��ȡ�����ļ�a.properties
            InputStream in = new BufferedInputStream (new FileInputStream("web.properties"));
            prop.load(in);     ///���������б�
            Iterator<String> it=prop.stringPropertyNames().iterator();
            System.out.println("aaaa="+prop.getProperty("actionName"));
            while(it.hasNext()){
                String key=it.next();
                System.out.println(key+":"+prop.getProperty(key));
            }
            in.close();
            
            ///�������Ե�b.properties�ļ�
            FileOutputStream oFile = new FileOutputStream("web.properties", true);//true��ʾ׷�Ӵ�
            prop.setProperty("phone", "10086");
            prop.store(oFile, "The New properties file");
            oFile.close();
        }
        catch(Exception e){
            System.out.println(e);
        }*/
    } 
		
	}
	
	
	
	

