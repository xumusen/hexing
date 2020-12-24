package com.ddl;

import java.io.RandomAccessFile;

import java.net.SocketException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;

import javax.activation.MimetypesFileTypeMap;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.poi.util.IOUtils;

import com.entity.FileTime;
import com.entity.OTime;
import com.entity.PTime;
import com.entity.Tstore;
import com.utils.TimeUtils;

public class DataGather {

	// private static final String path = "src/resource/test";
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

	public void loadFile(String path, String filename) {

		try {

			RandomAccessFile raf = new RandomAccessFile(path, openFileStyle);

			String line_record = raf.readLine();

			while (line_record != null) {

				// 解析每一条记录

				parseRecord(line_record, path, filename);

				line_record = raf.readLine();

			}

			System.out.println("共有合法的记录" + count + "条");

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	public void loadFile(String path) {

		try {

			RandomAccessFile raf = new RandomAccessFile(path, openFileStyle);

			String line_record = raf.readLine();

			while (line_record != null) {

				// 解析每一条记录

				parseRecord(line_record, path);

				line_record = raf.readLine();

			}

			System.out.println("共有合法的记录" + count + "条");

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	/*
	 * 
	 * 功能：具体解析每一条记录，这里可以增加很多对记录的解析判断条件，如是否为字母、
	 * 
	 * 数字、email等。
	 * 
	 */

	private void parseRecord(String line_record, String path, String filename) throws Exception {

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
			tstore.setStorename(filename.substring(1, 9));
			tstore.setUploadDatetime(filename.substring(9, 21));
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
			tstore.setUploadDate(
					filename.substring(9, 13) + "-" + filename.substring(13, 15) + "-" + filename.substring(15, 17));
			tstore.setUploadTime(filename.substring(17, 21));
			Tstore.insertTstore(tstore);

			count++;

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

	public static ArrayList<String> getFiles(String filepath) {
		ArrayList<String> files = new ArrayList<String>();
		File file = new File(filepath);
		File[] tempLists = file.listFiles();
		for (int i = 0; i < tempLists.length; i++) {
			if (tempLists[i].isFile()) {
				files.add(tempLists[i].getName());
			}
		}

		for (int i = 0; i < files.size(); i++) {
			// System.out.println(files.get(i));
			String contentType = new MimetypesFileTypeMap().getContentType(tempLists[i]);
			System.out.println(contentType);
			// if(contentType.equals("text/plain"))
			{
				// System.out.println(files.get(i));
			}
		}
		return files;
	}

	private static boolean isText(File file) {
		boolean isText = true;
		try {
			FileInputStream fin = new FileInputStream(file);
			long len = file.length();
			for (int j = 0; j < (int) len; j++) {
				int t = fin.read();
				if (t < 32 && t != 9 && t != 10 && t != 13) {
					isText = false;
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isText;
	}

	public static FTPClient ftp(String ip, String user, String password) {

		FTPClient ftpClient = new FTPClient();
		try {
			ftpClient.connect(ip);
			ftpClient.login(user, password);
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (!ftpClient.isConnected()) {
			ftpClient = null;
		}

		return ftpClient;
	}

	/**
	 * 打开FTP服务链接
	 * 
	 * @param ftpHost
	 * @param ftpPort
	 * @param ftpUserName
	 * @param ftpPassword
	 */
	public static FTPClient getFTPClient(String ftpHost, Integer ftpPort, String ftpUserName, String ftpPassword) {
		FTPClient ftpClient = null;
		try {
			ftpClient = new FTPClient();
			ftpClient.setConnectTimeout(60000);
			if (ftpPort != null) {
				ftpClient.connect(ftpHost, ftpPort);// 连接FTP服务器
			} else {
				ftpClient.connect(ftpHost);// 连接FTP服务器
			}
			if (FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
				if (ftpClient.login(ftpUserName, ftpPassword)) {// 登陆FTP服务器
					if (FTPReply.isPositiveCompletion(ftpClient.sendCommand("OPTS UTF8", "ON"))) {// 开启服务器对UTF-8的支持，如果服务器支持就用UTF-8编码，否则就使用本地编码（GBK）.
						ftpClient.setControlEncoding("UTF-8");
					} else {
						ftpClient.setControlEncoding("GBK");
					}
					ftpClient.enterLocalPassiveMode();// 设置被动模式
					ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);// 设置传输的模式，以二进制流的方式读取
					ftpClient.enterLocalPassiveMode();
					System.out.println("FTP服务连接成功！");
				} else {
					System.out.println("FTP服务用户名或密码错误！");
					disConnection(ftpClient);
				}
			} else {
				System.out.println("连接到FTP服务失败！");
				disConnection(ftpClient);
			}
		} catch (SocketException e) {
			e.printStackTrace();
			disConnection(ftpClient);
			System.out.println("FTP的IP地址可能错误，请正确配置。");
		} catch (IOException e) {
			e.printStackTrace();
			disConnection(ftpClient);
			System.out.println("FTP的端口错误,请正确配置。");
		}
		return ftpClient;
	}

	public static void disConnection(FTPClient ftpClient) {
		try {
			if (ftpClient.isConnected()) {
				ftpClient.disconnect();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static FTPFile[] getFTPDirectoryFiles(FTPClient ftpClient, String path) {
		FTPFile[] files = null;
		try {
			ftpClient.changeWorkingDirectory(path);
			files = ftpClient.listFiles();
		} catch (Exception e) {
			e.printStackTrace();
			// 关闭连接
			disConnection(ftpClient);
			System.out.println("FTP读取数据异常！");
		}
		return files;
	}

	public static void closeServer(FTPClient ftpClient) {
		if (ftpClient.isConnected()) {
			try {
				ftpClient.logout();
				ftpClient.disconnect();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public static void readFile(FTPClient ftpClient, String fileName,String uploadtime) throws ParseException {
		InputStream ins = null;
		try {
			// 从服务器上读取指定的文件
			InputStream in = null;
			BufferedReader reader = null;
			ins = ftpClient.retrieveFileStream(fileName);
			reader = new BufferedReader(new InputStreamReader(ins, "UTF-8"));
			String line;
			// String temp;
             //StringBuffer buffer = new StringBuffer();
			while ((line = reader.readLine()) != null) {

				String[] fields = line.split(fieldLimitChar);
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

					Tstore tstore = new Tstore();
					tstore.setStorename(fileName.substring(1, 9));
					tstore.setUploadDatetime(fileName.substring(9, 21));
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
					tstore.setUploadDate(fileName.substring(9, 13) + "-" + fileName.substring(13, 15) + "-"
							+ fileName.substring(15, 17));
					//tstore.setUploadTime(fileName.substring(17, 21));
					tstore.setUploadTime(uploadtime);
					Tstore.insertTstore(tstore);

					count++;

				}
				

				// 主动调用一次getReply()把接下来的226消费掉. 这样做是可以解决这个返回null问题
				//ftpClient.getReply();

			}
			 if(reader!=null){
                 reader.close();
             }
			if (ins != null) {
				ins.close();
			}
			   ftpClient.completePendingCommand();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static  void getSt()  throws SocketException, IOException, ParseException, SQLException {
		FileTime.truncatefiletime();
		FTPClient ftpClient = new FTPClient();
		ftpClient.connect("ftp.hophingfood.com", 21);
		ftpClient.login("candao", "candao");//
		ftpClient.setDefaultTimeout(20000);
		ftpClient.setConnectTimeout(50000);
		ftpClient.setSoTimeout(50000);
		ftpClient.setDataTimeout(50000);
		
		if (FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
			Timestamp ts = new Timestamp(System.currentTimeMillis());
			DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String begintime = sdf.format(ts);
			System.out.println("连接成功，开始读取世通文件夹  "+begintime);
			ftpClient.changeWorkingDirectory("st");// 找到指定目录
			ftpClient.enterLocalPassiveMode();
			// FTPFile[] file = ftpClient.listFiles();
			FTPFile[] file = getFTPDirectoryFiles(ftpClient, "st");
			if (file != null && file.length > 0) {
				for (int i = 0; i < file.length; i++) {
					if (file[i].isFile() && file[i].getName().substring(0, 1).equals("T")) {						
						  Long uploadtime = file[i].getTimestamp().getTimeInMillis();
						  FileTime fileTime=new FileTime();
						  fileTime.setStoreid(file[i].getName().substring(1,9));
						  fileTime.setOrderdate(file[i].getName().substring(9,17));
						  fileTime.setOrdertime(file[i].getName().substring(17,21));
						 // FileTime.insertFileTime(fileTime);
						  
						  
						  String lastModifyTimeStr = ftpClient.getModificationTime(file[i].getName());
						  fileTime.setUploadtime(Long.parseLong(lastModifyTimeStr));
						  DateFormat sdf1 =  new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
						  fileTime.setTxttime(sdf1.format(uploadtime));
						  fileTime.setFilename(file[i].getName());
						  FileTime.insertFileTime(fileTime);
						 
					       
						/*
						 * SimpleDateFormat sdf1 = new SimpleDateFormat( "yyyyMMddHHmmss"); Date
						 * startTimeDate = sdf1.parse(lastModifyTimeStr); Long lastModifyTime =
						 * startTimeDate.getTime() + file[0].getTimestamp().getTimeZone().getOffset(0);
						 * fileTime.setUploadtime(lastModifyTime); FileTime.insertFileTime(fileTime);
						 */
						  
			
						  
						  //readFile(ftpClient, file[i].getName());
					}
				}
			}
			Timestamp ts2 = new Timestamp(System.currentTimeMillis());
			DateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String endtime = sdf2.format(ts2);
			System.out.println("执行成功    "+endtime);
		} else
			System.out.println("连接失败");
	}
	

public static  void getDq()  throws SocketException, IOException, ParseException, SQLException {
	FileTime.truncatefiletimeYbT();
	FTPClient ftpClient = new FTPClient();
	ftpClient.connect("ftp.hophingfood.com", 21);
	ftpClient.login("newdq", "newdq");//
	ftpClient.setDefaultTimeout(20000);
	ftpClient.setConnectTimeout(50000);
	ftpClient.setSoTimeout(50000);
	ftpClient.setDataTimeout(50000);
	
	if (FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String begintime = sdf.format(ts);
		System.out.println("连接成功，开始读取银豹文件夹  "+begintime);
		ftpClient.changeWorkingDirectory("/");// 找到指定目录
		ftpClient.enterLocalPassiveMode();
		// FTPFile[] file = ftpClient.listFiles();
		FTPFile[] file = getFTPDirectoryFiles(ftpClient, "/");
		if (file != null && file.length > 0) {
			for (int i = 0; i < file.length; i++) {
				if (file[i].isFile() && file[i].getName().substring(0, 2).equals("TD")) {						
					  Long uploadtime = file[i].getTimestamp().getTimeInMillis();
					  FileTime fileTime=new FileTime();
					  fileTime.setStoreid(file[i].getName().substring(1,9));
					  fileTime.setOrderdate(file[i].getName().substring(9,17));
					  fileTime.setOrdertime(file[i].getName().substring(17,21));
					 // FileTime.insertFileTime(fileTime);
					  
					  
					  String lastModifyTimeStr = ftpClient.getModificationTime(file[i].getName());
					  fileTime.setUploadtime(Long.parseLong(lastModifyTimeStr));
					  DateFormat sdf1 =  new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
					  fileTime.setTxttime(sdf1.format(uploadtime));
					  fileTime.setFilename(file[i].getName());
					  FileTime.insertFileTimeYbT(fileTime);
					 
				       
					/*
					 * SimpleDateFormat sdf1 = new SimpleDateFormat( "yyyyMMddHHmmss"); Date
					 * startTimeDate = sdf1.parse(lastModifyTimeStr); Long lastModifyTime =
					 * startTimeDate.getTime() + file[0].getTimestamp().getTimeZone().getOffset(0);
					 * fileTime.setUploadtime(lastModifyTime); FileTime.insertFileTime(fileTime);
					 */
					  
		
					  
					  //readFile(ftpClient, file[i].getName());
				}
			}
		}
		Timestamp ts2 = new Timestamp(System.currentTimeMillis());
		DateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String endtime = sdf2.format(ts2);
		System.out.println("执行成功    "+endtime);
	} else
		System.out.println("连接失败");
	}

public static  void getDqO()  throws SocketException, IOException, ParseException, SQLException {
	OTime.truncatefiletime();
	FTPClient ftpClient = new FTPClient();
	ftpClient.connect("ftp.hophingfood.com", 21);
	ftpClient.login("newdq", "newdq");//
	ftpClient.setDefaultTimeout(20000);
	ftpClient.setConnectTimeout(50000);
	ftpClient.setSoTimeout(50000);
	ftpClient.setDataTimeout(50000);
	
	if (FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String begintime = sdf.format(ts);
		System.out.println("连接成功，开始读取银豹文件夹  "+begintime);
		ftpClient.changeWorkingDirectory("/");// 找到指定目录
		ftpClient.enterLocalPassiveMode();
		// FTPFile[] file = ftpClient.listFiles();
		FTPFile[] file = getFTPDirectoryFiles(ftpClient, "/");
		if (file != null && file.length > 0) {
			for (int i = 0; i < file.length; i++) {
				if (file[i].isFile() && file[i].getName().substring(0, 2).equals("OD")) {						
					  Long uploadtime = file[i].getTimestamp().getTimeInMillis();
					  OTime fileTime=new OTime();
					  fileTime.setStoreid(file[i].getName().substring(1,9));
					  fileTime.setOrderdate(file[i].getName().substring(9,17));
					  fileTime.setOrdertime(file[i].getName().substring(17,21));
					 // FileTime.insertFileTime(fileTime);
					  
					  
					  String lastModifyTimeStr = ftpClient.getModificationTime(file[i].getName());
					  fileTime.setUploadtime(Long.parseLong(lastModifyTimeStr));
					  DateFormat sdf1 =  new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
					  fileTime.setTxttime(sdf1.format(uploadtime));
					  fileTime.setFilename(file[i].getName());
					  OTime.insertFileTime(fileTime);
					 
				       
					/*
					 * SimpleDateFormat sdf1 = new SimpleDateFormat( "yyyyMMddHHmmss"); Date
					 * startTimeDate = sdf1.parse(lastModifyTimeStr); Long lastModifyTime =
					 * startTimeDate.getTime() + file[0].getTimestamp().getTimeZone().getOffset(0);
					 * fileTime.setUploadtime(lastModifyTime); FileTime.insertFileTime(fileTime);
					 */
					  
		
					  
					  //readFile(ftpClient, file[i].getName());
				}
			}
		}
		Timestamp ts2 = new Timestamp(System.currentTimeMillis());
		DateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String endtime = sdf2.format(ts2);
		System.out.println("执行成功    "+endtime);
	} else
		System.out.println("连接失败");
	}


public static  void getDqP()  throws SocketException, IOException, ParseException, SQLException {
	PTime.truncatefiletime();
	FTPClient ftpClient = new FTPClient();
	ftpClient.connect("ftp.hophingfood.com", 21);
	ftpClient.login("newdq", "newdq");//
	ftpClient.setDefaultTimeout(20000);
	ftpClient.setConnectTimeout(50000);
	ftpClient.setSoTimeout(50000);
	ftpClient.setDataTimeout(50000);
	
	if (FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String begintime = sdf.format(ts);
		System.out.println("连接成功，开始读取银豹文件夹  "+begintime);
		ftpClient.changeWorkingDirectory("/");// 找到指定目录
		ftpClient.enterLocalPassiveMode();
		// FTPFile[] file = ftpClient.listFiles();
		FTPFile[] file = getFTPDirectoryFiles(ftpClient, "/");
		if (file != null && file.length > 0) {
			for (int i = 0; i < file.length; i++) {
				if (file[i].isFile() && file[i].getName().substring(0, 2).equals("PD")) {						
					  Long uploadtime = file[i].getTimestamp().getTimeInMillis();
					  PTime fileTime=new PTime();
					  fileTime.setStoreid(file[i].getName().substring(1,9));
					  fileTime.setOrderdate(file[i].getName().substring(9,17));
					  fileTime.setOrdertime(file[i].getName().substring(17,21));
					 // FileTime.insertFileTime(fileTime);
					  
					  
					  String lastModifyTimeStr = ftpClient.getModificationTime(file[i].getName());
					  fileTime.setUploadtime(Long.parseLong(lastModifyTimeStr));
					  DateFormat sdf1 =  new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
					  fileTime.setTxttime(sdf1.format(uploadtime));
					  fileTime.setFilename(file[i].getName());
					  PTime.insertFileTime(fileTime);
					 
				       
					/*
					 * SimpleDateFormat sdf1 = new SimpleDateFormat( "yyyyMMddHHmmss"); Date
					 * startTimeDate = sdf1.parse(lastModifyTimeStr); Long lastModifyTime =
					 * startTimeDate.getTime() + file[0].getTimestamp().getTimeZone().getOffset(0);
					 * fileTime.setUploadtime(lastModifyTime); FileTime.insertFileTime(fileTime);
					 */
					  
		
					  
					  //readFile(ftpClient, file[i].getName());
				}
			}
		}
		Timestamp ts2 = new Timestamp(System.currentTimeMillis());
		DateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String endtime = sdf2.format(ts2);
		System.out.println("执行成功    "+endtime);
	} else
		System.out.println("连接失败");
	}


	public static void main(String[] args) throws SocketException, IOException, ParseException, SQLException {
		
		getDqO();
		//getSt();
		//getDq();
		
		
		/*
		 * String name="TUF020020202007122330"; System.out.println(name.substring(1,9));
		 * System.out.println(name.substring(17,21));
		 */
	}

}