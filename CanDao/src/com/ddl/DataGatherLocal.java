package com.ddl;

import java.io.RandomAccessFile;

import java.net.SocketException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.nio.*;

import javax.activation.MimetypesFileTypeMap;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.poi.util.IOUtils;

import com.entity.DiffSeitoSum;
import com.entity.Tstore;

public class DataGatherLocal {
	static ResourceBundle resource = ResourceBundle.getBundle("web");
	private final static String URL = resource.getString("URL");
	private static final String USER = resource.getString("USER");
	private static final String PASSWORD = resource.getString("PASSWORD");
	private final static String reportPath = resource.getString("reportPath");

	private static Connection conn = null;
	// 静态代码块（将加载驱动、连接数据库放入静态块中）
	static {
		try {
			// 1.加载驱动程序
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			// 2.获得数据库的连接
			conn = (Connection) DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 对外提供一个方法来获取数据库连接
	public static Connection getConnection() {
		return conn;
	}


	//private static final String path = "V://";
	private static final String path = "C://postxt";
	// private static final String path = "h://TYS010255202007040145.txt";

	public static final String openFileStyle = "r";

	public static final String fieldLimitChar = ",";

	public static final int fieldAllCount = 11;

	private static int count;

	private static String ref;

	private static String type;

	private static String mode;

	private static String date;

	private static String time;

	private static String staff;

	private static String disc;

	private static String amt;

	private static String reason;

	private static String tax;

	private static String aftertax;

	/*
	 * 
	 * 功能：解析文本文件
	 * 
	 */

	public void loadFile() {

		try {

			File[] files = new File(path).listFiles();
		
			for (File file : files) {
				/*
				 * Path pathnio=Paths.get(file.getAbsolutePath()); BasicFileAttributes attr =
				 * Files.readAttributes(pathnio, BasicFileAttributes.class);
				 * System.out.println("creationTime     = " + attr.creationTime());
				 * System.out.println("lastAccessTime   = " + attr.lastAccessTime());
				 * System.out.println("lastModifiedTime = " + attr.lastModifiedTime());
				 * 
				 * System.out.println("isDirectory      = " + attr.isDirectory());
				 * System.out.println("isOther          = " + attr.isOther());
				 * System.out.println("isRegularFile    = " + attr.isRegularFile());
				 * System.out.println("isSymbolicLink   = " + attr.isSymbolicLink());
				 * System.out.println("size             = " + attr.size());
				 */
				if (file.isFile() && file.exists() && file.getName().substring(0, 1).equals("T")) {

					RandomAccessFile raf = new RandomAccessFile(file.getAbsolutePath(), openFileStyle);
					//System.out.println("file.getsolutepath" + file.getAbsolutePath());

					String line_record = raf.readLine();

					while (line_record != null) {

						// 解析每一条记录

						parseRecord(line_record,file.getName());
						//System.out.println("file.getname  " +file.getName());

						line_record = raf.readLine();

					}

					System.out.println("共有合法的记录" + count + "条");

				}
			}
			System.out.println("全部执行完毕");

		} catch (Exception e) {

			e.printStackTrace();

		}
		/*
		 * try { DataGather.getSt(); } catch (SocketException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); } catch (IOException e) { //
		 * TODO Auto-generated catch block e.printStackTrace(); } catch (ParseException
		 * e) { // TODO Auto-generated catch block e.printStackTrace(); } catch
		 * (SQLException e) { // TODO Auto-generated catch block e.printStackTrace(); }
		 */
	}

	public  void reloadFile(String begintime) throws Exception {
		DataGather.getSt();
		
	 	Statement stmt = conn.createStatement();
    	List <DiffSeitoSum> list=new ArrayList<DiffSeitoSum>();
    	ResultSet rs = stmt.executeQuery("SELECT v.storeid, v.orderdate, v.ordertime, v.uploadtime,f.[filename],f.txttime\r\n" + 
    			"  FROM v_validfile AS v LEFT JOIN filetime AS f ON f.storeid = v.storeid AND f.orderdate = v.orderdate AND f.ordertime = v.ordertime AND f.uploadtime = v.uploadtime \r\n" + 
    			"   WHERE f.txttime>'"+begintime+"'\r\n" + 
    			"ORDER BY txttime DESC");
    	
    	while(rs.next()) {
    		Tstore.deleteT(rs.getString("storeid"), rs.getString("orderdate"));
    		RandomAccessFile raf = new RandomAccessFile(path+"//"+rs.getString("filename"), openFileStyle);

			String line_record = raf.readLine();

			while (line_record != null) {

				// 解析每一条记录

				parseRecord(line_record,rs.getString("filename"));

				line_record = raf.readLine();

			}

			System.out.println("共有合法的记录" + count + "条");

    	}
    	


	}

	private void parseRecord(String line_record, String path) throws Exception {

		// 拆分记录

		String[] fields = line_record.split(fieldLimitChar);

		if (fields.length == fieldAllCount) {

			ref = tranStr(fields[0]);

			type = tranStr(fields[1]);

			mode = tranStr(fields[2]);

			date = tranStr(fields[3]);

			time = tranStr(fields[4]);

			staff = tranStr(fields[5]);

			disc = tranStr(fields[6]);

			amt = tranStr(fields[7]);

			reason = tranStr(fields[8]);

			tax = tranStr(fields[9]);

			aftertax = tranStr(fields[10]);

			/*
			 * System.out.println(ref + " " + type + " " + mode + " "
			 * 
			 * + date + " " + time + " " + staff + " " + disc
			 * 
			 * + " " + amt + " " + reason + " " + tax + " " + aftertax);
			 */

			Tstore tstore = new Tstore();
			tstore.setStorename(path.substring(1, 9));
			tstore.setUploadDatetime(path.substring(9, 21));
			tstore.setRef(ref);
			tstore.setType(type);
			tstore.setMode(mode);
			tstore.setDate(date);
			tstore.setTime(time);
			tstore.setStaff(staff);
			tstore.setDisc(disc);
			tstore.setAmt(amt);
			tstore.setReason(reason);
			tstore.setTax(tax);
			tstore.setAftertax(aftertax);
			tstore.setUploadDate(path.substring(9, 13) + "-" + path.substring(13, 15) + "-" + path.substring(15, 17));
			tstore.setUploadTime(path.substring(17, 21));
			Tstore.insertTstore(tstore);

			count++;

		}

	}

	private static String tranStr(String oldstr) {

		String newstr = "";

		try {

			newstr = new String(oldstr.getBytes("ISO-8859-1"), "GBK");

		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();

		}

		return newstr;

	}
	
	public static void reLoadFile(String begintime) throws Exception {
		DataGatherLocal dataGatherLocal = new DataGatherLocal();
		//dataGatherLocal.loadFile();
		dataGatherLocal.reloadFile(begintime);
	}

	public static void main(String[] args) throws Exception {

		/*
		 * String name="TUF020020202007122330"; System.out.println(name.substring(1,9));
		 * System.out.println(name.substring(17,21));
		 */
		DataGatherLocal dataGatherLocal = new DataGatherLocal();
		//dataGatherLocal.loadFile();
		dataGatherLocal.reloadFile("2020-11-02 00:00:00");

	}

}