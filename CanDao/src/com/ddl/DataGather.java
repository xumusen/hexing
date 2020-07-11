package com.ddl;


import java.io.RandomAccessFile;

import java.io.UnsupportedEncodingException;
import java.awt.List;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

import javax.activation.MimetypesFileTypeMap;

import com.entity.Tstore;

public class DataGather {

	// private static final String path = "src/resource/test";
	//private static final String path = "h://TYS010255202007040145.txt";

	public static final String openFileStyle = "r";

	public static final String fieldLimitChar = ",";

	public static final int fieldAllCount = 11;

	private int count;

	private String ref;

	private String type;

	private String mode;

	private String date;

	private String time;

	private String staff;

	private String disc;

	private String amt;

	private String reason;

	private String tax;

	private String aftertax;

	/*
	 * 
	 * 功能：解析文本文件
	 * 
	 */

	public void loadFile(String path,String filename) {

		try {

			RandomAccessFile raf = new RandomAccessFile(path, openFileStyle);

			String line_record = raf.readLine();

			while (line_record != null) {

				// 解析每一条记录

				parseRecord(line_record,path,filename);

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

	private void parseRecord(String line_record,String path,String filename) throws Exception {

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

			Tstore tstore=new Tstore();
			tstore.setStorename(filename.substring(1, 9));
			tstore.setUploadDatetime(filename.substring(9,20));
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
			tstore.setUploadDate(filename.substring(9, 13)+"-"+filename.substring(13, 15)+"-"+filename.substring(15, 17));
			tstore.setUploadTime(filename.substring(17,20));
			Tstore.insertTstore(tstore);
		

			count++;

		}

	}

	private String tranStr(String oldstr) {

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
			if (tempLists[i].isFile() ) {
				files.add(tempLists[i].getName());
			}
		}

		for (int i = 0; i < files.size(); i++) {
			//System.out.println(files.get(i));
			String contentType=new MimetypesFileTypeMap().getContentType(tempLists[i]);
			System.out.println(contentType);
			//if(contentType.equals("text/plain"))
			{
				//System.out.println(files.get(i));
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
	
	public static void main(String[] args) {
		
		// getFiles("H:\\");
		 
		 
		 
			ArrayList<String> files = new ArrayList<String>();
			File file = new File("G:\\st");
			File[] tempLists = file.listFiles();
			for (int i = 0; i < tempLists.length; i++) {
				if (tempLists[i].isFile() ) {
					files.add(tempLists[i].getName());
					String contentType=new MimetypesFileTypeMap().getContentType(tempLists[i]);
					if (contentType.equals("text/plain")&& tempLists[i].getName().substring(0,1).equals("T"))
						{System.out.println(tempLists[i].getName());

					try {

						DataGather gather = new DataGather();

						gather.loadFile(tempLists[i].toString(),tempLists[i].getName());

					} catch (Exception e) {

						e.printStackTrace();

					}

				}
				}
			}


		


	}

}