package md5;

import java.io.InputStream;
import java.util.Properties;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream; 
import java.util.Iterator;
import java.util.Properties; 

public class data {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
/*
		String data = Md5Encrypt.md5("�й�");
		System.out.println(data);
		*/
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
            
        /*    ///�������Ե�b.properties�ļ�
            FileOutputStream oFile = new FileOutputStream("web.properties", true);//true��ʾ׷�Ӵ�
            prop.setProperty("phone", "10086");
            prop.store(oFile, "The New properties file");
            oFile.close();*/
        }
        catch(Exception e){
            System.out.println(e);
        }
    } 
		
	}
	
	
	
	

