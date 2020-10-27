package com.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.entity.DiffNcrDetail;
import com.entity.DiffNcrSum;
import com.entity.DiffSeitoDetail;
import com.entity.DiffSeitoSum;

public class DatetoExcel {
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
    public static List<DiffSeitoSum> getDiffSeitoSum() throws SQLException{
    	Statement stmt = conn.createStatement();
    	List<DiffSeitoSum> list=new ArrayList<DiffSeitoSum>();
    	ResultSet rs = stmt.executeQuery("SELECT ss.分店,cs.* FROM v_compare_sum AS cs\r\n" + 
				"LEFT JOIN seitoStore AS ss ON cs.storeid=ss.内部编号\r\n" + 
				"WHERE\r\n" + 
				"((cs.storeid NOT LIKE 'D%' ) OR cs.storeid IS NULL)\r\n" + 
				"AND \r\n" + 
				"(cs.orderdate<convert(varchar(10),getdate(),120)  OR cs.orderdate IS NULL)\r\n" + 
				"ORDER BY cs.orderdate ASC");
    	while(rs.next()) {
    		DiffSeitoSum diffSeitoSum=new DiffSeitoSum();
    		diffSeitoSum.set分店(rs.getString("分店"));
    		diffSeitoSum.setStore(rs.getString("store"));
    		diffSeitoSum.setUploaddate(rs.getString("uploaddate"));
    		diffSeitoSum.setPOS_TC(rs.getString("POS_TC"));
    		diffSeitoSum.setPOS实收金额(rs.getString("POS实收金额"));
    		diffSeitoSum.setStoreid(rs.getString("storeid"));
    		diffSeitoSum.setTc(rs.getString("tc"));
    		diffSeitoSum.setPrice(rs.getString("price"));
    		diffSeitoSum.setOrderdate(rs.getString("orderdate"));
    		diffSeitoSum.setTC差值(rs.getString("TC差值"));
    		diffSeitoSum.set金额差值(rs.getString("金额差值"));
    		list.add(diffSeitoSum);
    	}
    	return list;
    }
    
    public static List<DiffNcrSum> getDiffNcrSum() throws SQLException{
    	Statement stmt = conn.createStatement();
    	List<DiffNcrSum> list=new ArrayList<DiffNcrSum>();
    	//ResultSet rs = stmt.executeQuery("SELECT * FROM diffdq AS d ORDER BY d.DQorderdate,d.DQstoreid ");
    	ResultSet rs = stmt.executeQuery("SELECT * FROM diffdq AS d WHERE d.差异<>-1.4 OR  d.差异 IS NULL ORDER BY d.DQorderdate,d.DQstoreid ");
    	while(rs.next()) {
    		DiffNcrSum diffNcrSum=new DiffNcrSum();
    		diffNcrSum.setDQstoreid(rs.getString("DQstoreid"));
    		diffNcrSum.setDQorderdate(rs.getString("DQorderdate"));
    		diffNcrSum.setDQprice(rs.getString("DQprice"));
    		diffNcrSum.setStoreid(rs.getString("storeid"));
    		diffNcrSum.setTc(rs.getString("tc"));
    		diffNcrSum.setPrice(rs.getString("price"));
    		diffNcrSum.setOrderdate(rs.getString("orderdate"));
    		diffNcrSum.set差异(rs.getString("差异"));
    		list.add(diffNcrSum);
    	}
    	return list;
    }
    public static List<DiffSeitoDetail> getDiffSeitoDetail() throws SQLException{
    	Statement stmt = conn.createStatement();
    	List<DiffSeitoDetail> list=new ArrayList<DiffSeitoDetail>();
    	ResultSet rs = stmt.executeQuery("SELECT * FROM orderDiff");
    	while(rs.next()) {
    		DiffSeitoDetail diffSeitoDetail=new DiffSeitoDetail();
    		diffSeitoDetail.setPosStore(rs.getString("PosStore"));
    		diffSeitoDetail.setPosUploaddatetime(rs.getString("PosUploaddatetime"));
    		diffSeitoDetail.setPosRef(rs.getString("PosRef"));
    		diffSeitoDetail.setPosAmt(rs.getString("PosAmt"));
    		diffSeitoDetail.setPosType(rs.getString("PosType"));
    		diffSeitoDetail.setZtStore(rs.getString("ZtStore"));
    		diffSeitoDetail.setZtBrandname(rs.getString("ZtBrandName"));
    		diffSeitoDetail.setZtOrderid(rs.getString("ZtOrderid"));
    		diffSeitoDetail.setZtThirdSn(rs.getString("ZtThirdSn"));
    		diffSeitoDetail.setZtMerchantPrice(rs.getString("ZtMerchantPrice"));
    		diffSeitoDetail.setZtExtorderid(rs.getString("ZtExtorderid"));
    		diffSeitoDetail.setZtorderdate(rs.getString("ZtOrderdate"));
    		list.add(diffSeitoDetail);
    	}
    	return list;
    }
    

    public static List<DiffNcrDetail> getDiffNcrDetail() throws SQLException{
    	Statement stmt = conn.createStatement();
    	List<DiffNcrDetail> list=new ArrayList<DiffNcrDetail>();
    	ResultSet rs = stmt.executeQuery("SELECT oid.storeid,oid.cdstorename,oid.extorderid,oid.thirdsn, oid.orderdate\r\n" + 
    			"  FROM order_info_dq AS oid");
    	while(rs.next()) {
    		DiffNcrDetail diffNcrDetail=new DiffNcrDetail();
    		diffNcrDetail.setStoreid(rs.getString("storeid"));
    		diffNcrDetail.setCdstorename(rs.getString("cdstorename"));
    		diffNcrDetail.setExtorderid(rs.getString("extorderid"));
    		diffNcrDetail.setThirdsn(rs.getString("thirdsn"));
    		diffNcrDetail.setOrderdate(rs.getString("orderdate"));
    		list.add(diffNcrDetail);
    	}
    	return list;
    }
    
    public static void getSeitoDiff(List<DiffSeitoSum> list,List<DiffSeitoDetail> list2){
        //第一步：创建一个workbook对应一个Excel文件
        HSSFWorkbook workbook=new HSSFWorkbook();
        //第二部：在workbook中创建一个sheet对应Excel中的sheet
        HSSFSheet sheet=workbook.createSheet("汇总");      
        //第三部：在sheet表中添加表头第0行，老版本的poi对sheet的行列有限制
        HSSFRow row=sheet.createRow(0);
        //第四部：创建单元格，设置表头
        HSSFCell cell=row.createCell((short) 0);
        cell.setCellValue("分店");
        cell=row.createCell((short) 1);
        cell.setCellValue("store");
        cell=row.createCell((short) 2);
        cell.setCellValue("uploaddate");
        cell=row.createCell((short) 3);
        cell.setCellValue("POS_TC");
        cell=row.createCell((short) 4);
        cell.setCellValue("POS实收金额");
        cell=row.createCell((short) 5);
        cell.setCellValue("storeid");
        cell=row.createCell((short) 6);
        cell.setCellValue("tc");
        cell=row.createCell((short) 7);
        cell.setCellValue("price");
        cell=row.createCell((short) 8);
        cell.setCellValue("orderdate");
        cell=row.createCell((short) 9);
        cell.setCellValue("tc差值");
        cell=row.createCell((short) 10);
        cell.setCellValue("金额差值");

        
        HSSFSheet sheet1=workbook.createSheet("明细");
        HSSFRow rowd=sheet1.createRow(0);
        HSSFCell cell1=rowd.createCell((short) 0);
        cell1.setCellValue("PosStore");
        cell1=rowd.createCell((short) 1);
        cell1.setCellValue("PosUploaddatetime");
        cell1=rowd.createCell((short) 2);
        cell1.setCellValue("PosRef");
        cell1=rowd.createCell((short) 3);
        cell1.setCellValue("PosAmt");
        cell1=rowd.createCell((short) 4);
        cell1.setCellValue("PosType");
        cell1=rowd.createCell((short) 5);
        cell1.setCellValue("ZtStore");
        cell1=rowd.createCell((short) 6);
        cell1.setCellValue("ZtBrandName");
        cell1=rowd.createCell((short) 7);
        cell1.setCellValue("ZtOrderid");
        cell1=rowd.createCell((short) 8);
        cell1.setCellValue("ZtThirdSn");
        cell1=rowd.createCell((short) 9);
        cell1.setCellValue("ZtMerchantPrice");
        cell1=rowd.createCell((short) 10);
        cell1.setCellValue("ZtExtorderid");
        cell1=rowd.createCell((short) 11);
        cell1.setCellValue("ZtOrderdate");
        //第五部：写入实体数据，实际应用中这些 数据从数据库得到，对象封装数据，集合包对象。对象的属性值对应表的每行的值
        for(int i=0;i<list.size();i++){
            HSSFRow row1=sheet.createRow(i+1);
            DiffSeitoSum diffSeitoSum=list.get(i);
            //创建单元格设值
            row1.createCell((short)0).setCellValue(diffSeitoSum.get分店());
            row1.createCell((short)1).setCellValue(diffSeitoSum.getStore());
            row1.createCell((short)2).setCellValue(diffSeitoSum.getUploaddate());
            row1.createCell((short)3).setCellValue(diffSeitoSum.getPOS_TC());
            row1.createCell((short)4).setCellValue(diffSeitoSum.getPOS实收金额());
            row1.createCell((short)5).setCellValue(diffSeitoSum.getStoreid());
            row1.createCell((short)6).setCellValue(diffSeitoSum.getTc());
            row1.createCell((short)7).setCellValue(diffSeitoSum.getPrice());
            row1.createCell((short)8).setCellValue(diffSeitoSum.getOrderdate());
            row1.createCell((short)9).setCellValue(diffSeitoSum.getTC差值());
            row1.createCell((short)10).setCellValue(diffSeitoSum.get金额差值());
        }
        
        for(int i=0;i<list2.size();i++){
            HSSFRow row1=sheet1.createRow(i+1);
            DiffSeitoDetail diffSeitoDetail=list2.get(i);
            //创建单元格设值
            row1.createCell((short)0).setCellValue(diffSeitoDetail.getPosStore());
            row1.createCell((short)1).setCellValue(diffSeitoDetail.getPosUploaddatetime());
            row1.createCell((short)2).setCellValue(diffSeitoDetail.getPosRef());
            row1.createCell((short)3).setCellValue(diffSeitoDetail.getPosAmt());
            row1.createCell((short)4).setCellValue(diffSeitoDetail.getPosType());
            row1.createCell((short)5).setCellValue(diffSeitoDetail.getZtStore());
            row1.createCell((short)6).setCellValue(diffSeitoDetail.getZtBrandname());
            row1.createCell((short)7).setCellValue(diffSeitoDetail.getZtOrderid());
            row1.createCell((short)8).setCellValue(diffSeitoDetail.getZtThirdSn());
            row1.createCell((short)9).setCellValue(diffSeitoDetail.getZtMerchantPrice());
            row1.createCell((short)10).setCellValue(diffSeitoDetail.getZtExtorderid());
            row1.createCell((short)11).setCellValue(diffSeitoDetail.getZtorderdate());
        }
        //将文件保存到指定的位置
        try {
        	Timestamp ts = new Timestamp(System.currentTimeMillis());
    		DateFormat sdf = new SimpleDateFormat("HHmmss");
    		DateFormat dayf = new SimpleDateFormat("dd");
    		String time = sdf.format(ts);
    		String day=dayf.format(ts);
            FileOutputStream fos=new FileOutputStream(reportPath+"//"+TimeUtils.getFirstDay()+"-"+TimeUtils.getYesterday()+"吉野家差异-"+time+".xls");
            workbook.write(fos);
            System.out.println("吉野家差异报告生成，执行完毕");
            fos.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    

    public static void getNcrDiff(List<DiffNcrSum> list,List<DiffNcrDetail> list2){
        //第一步：创建一个workbook对应一个Excel文件
        HSSFWorkbook workbook=new HSSFWorkbook();
        //第二部：在workbook中创建一个sheet对应Excel中的sheet
        HSSFSheet sheet=workbook.createSheet("汇总");      
        //第三部：在sheet表中添加表头第0行，老版本的poi对sheet的行列有限制
        HSSFRow row=sheet.createRow(0);
        //第四部：创建单元格，设置表头
        HSSFCell cell=row.createCell((short) 0);
        cell.setCellValue("DQstoreid");
        cell=row.createCell((short) 1);
        cell.setCellValue("DQorderdate");
        cell=row.createCell((short) 2);
        cell.setCellValue("DQprice");
        cell=row.createCell((short) 3);
        cell.setCellValue("storeid");
        cell=row.createCell((short) 4);
        cell.setCellValue("tc");
        cell=row.createCell((short) 5);
        cell.setCellValue("price");
        cell=row.createCell((short) 6);
        cell.setCellValue("orderdate");
        cell=row.createCell((short) 7);
        cell.setCellValue("差异");
 

        
        HSSFSheet sheet1=workbook.createSheet("明细");
        HSSFRow rowd=sheet1.createRow(0);
        HSSFCell cell1=rowd.createCell((short) 0);
        cell1.setCellValue("storeid");
        cell1=rowd.createCell((short) 1);
        cell1.setCellValue("cdstorename");
        cell1=rowd.createCell((short) 2);
        cell1.setCellValue("extorderid");
        cell1=rowd.createCell((short) 3);
        cell1.setCellValue("thirdsn");
        cell1=rowd.createCell((short) 4);
        cell1.setCellValue("orderdate");

        //第五部：写入实体数据，实际应用中这些 数据从数据库得到，对象封装数据，集合包对象。对象的属性值对应表的每行的值
        for(int i=0;i<list.size();i++){
            HSSFRow row1=sheet.createRow(i+1);
            DiffNcrSum diffNcrSum=list.get(i);
            //创建单元格设值
            row1.createCell((short)0).setCellValue(diffNcrSum.getDQstoreid());
            row1.createCell((short)1).setCellValue(diffNcrSum.getDQorderdate());
            row1.createCell((short)2).setCellValue(diffNcrSum.getDQprice());
            row1.createCell((short)3).setCellValue(diffNcrSum.getStoreid());
            row1.createCell((short)4).setCellValue(diffNcrSum.getTc());
            row1.createCell((short)5).setCellValue(diffNcrSum.getPrice());
            row1.createCell((short)6).setCellValue(diffNcrSum.getOrderdate());
            row1.createCell((short)7).setCellValue(diffNcrSum.get差异());
        }
        
        for(int i=0;i<list2.size();i++){
            HSSFRow row1=sheet1.createRow(i+1);
            DiffNcrDetail diffNcrDetail=list2.get(i);
            //创建单元格设值
            row1.createCell((short)0).setCellValue(diffNcrDetail.getStoreid());
            row1.createCell((short)1).setCellValue(diffNcrDetail.getCdstorename());
            row1.createCell((short)2).setCellValue(diffNcrDetail.getExtorderid());
            row1.createCell((short)3).setCellValue(diffNcrDetail.getThirdsn());
            row1.createCell((short)4).setCellValue(diffNcrDetail.getOrderdate());
        }
        //将文件保存到指定的位置
        try {
        	Timestamp ts = new Timestamp(System.currentTimeMillis());
    		DateFormat sdf = new SimpleDateFormat("HHmmss");
    		DateFormat dayf = new SimpleDateFormat("dd");
    		String time = sdf.format(ts);
    		String day=dayf.format(ts);   // /home/jasonxu/下载
            FileOutputStream fos=new FileOutputStream(reportPath+"//"+TimeUtils.getFirstDay()+"-"+TimeUtils.getYesterday()+"DQ差异-"+time+".xls");
            //FileOutputStream fos=new FileOutputStream("/home/144c/postemp/1-"+day+"日DQ差异-"+time+".xls");
            workbook.write(fos);
            System.out.println("DQ差异报告生成，执行完毕");
            fos.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


	private static void removeDir(File dir) {
		File[] files=dir.listFiles();
		for(File file:files){
			if(file.isDirectory()){
				removeDir(file);
			}else{
				System.out.println(file+":"+file.delete());
			}
		}
		System.out.println(dir+":"+dir.delete());
	}
    public static boolean delFileOne(String fileUrl){
        boolean delete_flag = false; 
       if (!StringUtils.isEmpty(fileUrl)) {
           File file = new File(fileUrl);  
           if (file.exists()) {
               delete_flag = file.delete(); 
           }else {
               delete_flag = false; 
           }
          
     }
       return  delete_flag;
    }

    public static void deleteDirectory(File file){
        File[] list = file.listFiles();
        Integer i = 0;
        for (File f:list){
            if (f.isDirectory()){
                //删除子文件夹
                deleteDirectory(new File(f.getPath()));
            }else{
                //删除文件
                f.delete();
                i ++;
            }
        }
        //重新遍历一下文件夹内文件是否已删除干净，删除干净后则删除文件夹。
        if (file.listFiles().length <=0 ){
            file.delete();
            
        }
      
    }

    public static void deletefiles(String path ) {
    	
    	String[] cmd = new String[] { "/bin/sh", "-c", "rm -rf "+path }; 
    	try{
    	Process process = Runtime.getRuntime().exec(cmd);
    	}catch(IOException e){
    	e.printStackTrace();
    	}
    }
    
    public static void createfiles(String path) {
    	  File dir = new File(path);
          if(!dir.exists()){
              dir.mkdirs();
          }
    }
    //测试
    public static void main(String[] args) throws SQLException {
    	File dir=new File(reportPath);
		removeDir(dir);
        createfiles(reportPath);
    	getNcrDiff(getDiffNcrSum(),getDiffNcrDetail());
    	getSeitoDiff(getDiffSeitoSum(),getDiffSeitoDetail());
    }

}
