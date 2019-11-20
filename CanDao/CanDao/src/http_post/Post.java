package http_post;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Properties;

import org.apache.commons.lang.StringEscapeUtils;

import md5.Md5Encrypt;
import net.sf.json.JSONObject;

public class Post {
	private static String username="test";
	private static String password="9vbftd6C";

	public static void main(String[] args) throws Exception {

		doSendsms("11");				//发送短信

//		 doGetmomessage();			//获取上行信息

		// doGetresponse();			//获取提交报告
		// doGetreport();			//获取状态报告

		// doGetacountinfo();		//获取账号信息
		// doGetbusinesstype();		//获取业务类型

	}

	public static void doSendsms(String content)  throws Exception{
		/**HTTP-POST请求参数：发送短信
		 * 	密码可明文，可密文：version为空，密码明文传输，为1.0，密码MD5加密传输
		 *	短信内容，可GBK，UTF8传输：encode为空或0,内容gbk编码传输；encode为1，内容以utf-8编码传输
		 *	msgtype=1为短短信，4为长短信，长短信包含短短信
		 * 
		 */
		System.out.println(StringEscapeUtils.unescapeJava(content));
		
		
		Properties prop = new Properties();
		String accessKey ="";
		String actionName  ="";
		String secret  ="";
        try{
            //读取属性文件a.properties
        	File file=new File("C:\\apache-tomcat-7.0.94\\webapps\\CanDao\\web.properties");
            InputStream in = new BufferedInputStream (new FileInputStream(file));
            prop.load(in);     ///加载属性列表        
            accessKey=prop.getProperty("accessKey");
            actionName=prop.getProperty("actionName");
            secret=prop.getProperty("secret");
            in.close();
 
        }
        catch(Exception e){
            System.out.println(e);
        }
		
		
		long  timestamp=System.currentTimeMillis();
		//String content="";
		String sign=Md5Encrypt.md5(accessKey+actionName+secret+timestamp+content);
		String phone="18601070550";
//		String text="你好！";
		String text=URLEncoder.encode("你好", "gbk");
	
		JSONObject postdata=new JSONObject();
		
		postdata.put("actionName", actionName);
	//	postdata.put("password", passwordMD5);
		//postdata.put("content", StringEscapeUtils.unescapeJava(content)+"\"");
		postdata.put("content", content);
		postdata.put("accessKey", accessKey);
		postdata.put("sign", sign);
	//	postdata.put("subID", "");
		postdata.put("timestamp", timestamp);
	//	postdata.put("encode", 1);
	//	postdata.put("version", "1.0");
		
		System.out.println(postdata);
		
		String sr = HttpRequest.sendPost(
					"http://qc.can-dao.com:9982/OpenAction",postdata);

		System.out.println("result ----->  "+sr);


	}

	public static void doGetmomessage() throws Exception{
		 /**HTTP-POST请求参数：获取上行信息 
		 * 密码必须为MD5密文 
		 * pagesize为一次获取最大条数，最大为500
		 */
		
		String passwordMD5=Md5Encrypt.md5(password);
		
		JSONObject postdata=new JSONObject();
		postdata.put("username", username);
		postdata.put("password", passwordMD5);
		postdata.put("pagesize", 20);
		
		
		String sr = HttpRequest
				.sendPost(
						"http://211.147.239.62:18088/cgi-bin/getresponse",postdata);

		System.out.println("result ----->  "+sr);
	}

	public static void doGetresponse() throws Exception{
		/**	HTTP-POST请求参数：获取提交报告
		 * 	密码必须为MD5密文	
		 * 	pagesize为一次获取最大条数，最大为500
		 * 
		 */

		String passwordMD5=Md5Encrypt.md5(password);
		
		JSONObject postdata=new JSONObject();
		postdata.put("username", username);
		postdata.put("password", passwordMD5);
		postdata.put("pagesize", 20);
		
		String sr = HttpRequest
				.sendPost(
						"http://211.147.239.62:18088/cgi-bin/getresponse",postdata);

		System.out.println("result ----->  "+sr);
	}

	public static void doGetreport() throws Exception{
		/**	HTTP-POST请求参数：获取状态报告
		 * 	密码必须为MD5密文	
		 * 	pagesize为一次获取最大条数，最大为500
		 * 
		 */
		
		String passwordMD5=Md5Encrypt.md5(password);
		
		JSONObject postdata=new JSONObject();
		postdata.put("username", username);
		postdata.put("password", passwordMD5);
		postdata.put("pagesize", 20);
		
		String sr = HttpRequest
				.sendPost(
						"http://211.147.239.62:18088/cgi-bin/getresponse",postdata);

		System.out.println("result ----->  "+sr);
	}

	public static void doGetacountinfo() throws Exception{
		/**
		 * HTTP-POST请求参数：获取账号信息 
		 * 密码必须为MD5密文
		 * 
		 */
		
		String passwordMD5=Md5Encrypt.md5(password);
		
		JSONObject postdata=new JSONObject();
		postdata.put("username", username);
		postdata.put("password", passwordMD5);
		
		String sr = HttpRequest
				.sendPost(
						"http://211.147.239.62:18088/cgi-bin/getbusinesstype",postdata);

		System.out.println("result ----->  "+sr);
	}

	public static void doGetbusinesstype() throws Exception{
		/**POST请求参数：获取业务类型
		 * 密码必须为MD5密文
		 * 
		 */
		
		String passwordMD5=Md5Encrypt.md5(password);
		
		JSONObject postdata=new JSONObject();
		postdata.put("username", username);
		postdata.put("password", passwordMD5);
		
		String sr = HttpRequest
				.sendPost(
						"http://211.147.239.62:18088/cgi-bin/getbusinesstype",postdata);
		System.out.println("result ----->  "+sr);
	}

}
