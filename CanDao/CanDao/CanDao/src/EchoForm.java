import http_post.HttpRequest;
import http_post.Post;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import md5.Md5Encrypt;
import net.sf.json.JSONObject;

import sun.org.mozilla.javascript.internal.regexp.SubString;

public class EchoForm extends HttpServlet {

	public void service(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		req.setCharacterEncoding("UTF-8"); 
		res.setContentType("text/html;charset=UTF-8");   
		res.setCharacterEncoding("utf-8");
		PrintWriter out = res.getWriter();
		Enumeration flds = req.getParameterNames();
		//StringBuilder buffer=new StringBuilder();
		if (!flds.hasMoreElements()) {

			out.print("<html>");
			out.print("<form method=\"POST\"" + "action=\"EchoForm\">");
			for (int i = 0; i < 10; i++)
				out.print("<b>Field" + i + "</b> " + "<input type=\"text\""
						+ " size=\"20\" name=\"Field" + i + "\" value=\"Value"
						+ i + "\"><br>");
			out.print("<INPUT TYPE=submit name=submit Value=\"Submit\"></form></html>");

		} else {

			out.print("<h1>Your form contained:</h1>");
			Map<String, Object> content = new HashMap<String,Object>();
			Map<String, String> products = new HashMap<String, String>();
		/*	products.put("pid", "9684");
			products.put("number", "1");
			products.put("price", "100");*/
			while (flds.hasMoreElements()) {
				String field = (String) flds.nextElement();
				String value = req.getParameter(field);
				//buffer.append( "\\\"" + field + "\\\"" + ":" + "\\\"" + value+ "\\\"" + ",");				
				content.put(field, value);
				if(field.equals("pid")) products.put(field, value);
				if(field.equals("number")) products.put(field, value);
				if(field.equals("price")) products.put(field, value);
			}
			
			File file=new File("C:\\apache-tomcat-7.0.94\\webapps\\CanDao\\web.properties");
            InputStream in = new BufferedInputStream (new FileInputStream(file));
            Properties prop = new Properties();
            prop.load(in);     ///加载属性列表            
//            content.put("products", products);
			JSONObject jsonObj = new JSONObject();
		/*	products.put("pid", "9684");
			products.put("number", "1");
			products.put("price", "100");*/
			content.put("products", products);// 增加产品
			jsonObj.put("actionName", prop.getProperty("actionName"));
			jsonObj.put("content", content);
			jsonObj.put("accessKey", prop.getProperty("accessKey"));
			jsonObj.put("secret", prop.getProperty("secret"));
			jsonObj.put("sign", Md5Encrypt.md5(prop.getProperty("accessKey")+prop.getProperty("actionName")+prop.getProperty("secret")+System.currentTimeMillis()+jsonObj.put("content", content)));
			jsonObj.put("timestamp", System.currentTimeMillis());
			out.println(jsonObj);
			
			String sr = HttpRequest.sendPost(
					"http://qc.can-dao.com:9982/OpenAction",jsonObj);			
			byte[] bytes=sr.getBytes("gbk");			 
			String name=new String(bytes,"utf-8");
			out.println(name);
			in.close();
		}
	
		out.close();	
	}

	/**
	 * Constructor of the object.
	 */
	public EchoForm() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		/*
		 * response.setContentType("text/html"); PrintWriter out =
		 * response.getWriter(); out.println(
		 * "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		 * out.println("<HTML>");
		 * out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		 * out.println("  <BODY>"); out.print("    This is ");
		 * out.print(this.getClass()); out.println(", using the POST method");
		 * out.println("  </BODY>"); out.println("</HTML>"); out.flush();
		 * out.close();
		 */

		/*
		 * res.setContentType("text/html");
		 * 
		 * PrintWriter out = res.getWriter();
		 * 
		 * Enumeration flds = req.getParameterNames();
		 * 
		 * if (!flds.hasMoreElements()) {
		 * 
		 * out.print("<html>"); out.print("<form method=\"POST\"" +
		 * "action=\"EchoForm\">"); for (int i = 0; i < 10; i++)
		 * out.print("<b>Field" + i + "</b> " + "<input type=\"text\"" +
		 * " size=\"20\" name=\"Field" + i + "\" value=\"Value" + i +
		 * "\"><br>"); out.print(
		 * "<INPUT TYPE=submit name=submit Value=\"Submit\"></form></html>");
		 * 
		 * } else {
		 * 
		 * out.print("<h1>Your form contained:</h1>");
		 * 
		 * while (flds.hasMoreElements()) { String field = (String)
		 * flds.nextElement(); String value = req.getParameter(field);
		 * out.print(field + " = " + value + "<br>"); } }
		 * 
		 * out.close();
		 */

	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here

	}

}
