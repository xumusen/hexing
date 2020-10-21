package com.utils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteExcel {
    private static final String EXCEL_XLS = "xls";
    private static final String EXCEL_XLSX = "xlsx";
	static ResourceBundle resource = ResourceBundle.getBundle("web");
	private final static String URL = resource.getString("URL");
	private static final String USER = resource.getString("USER");
	private static final String PASSWORD = resource.getString("PASSWORD");

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
    
    public static void main(String[] args) throws SQLException {
    	Statement stmt = conn.createStatement();
    	Map<String, String> dataMap=new HashMap<String, String>();
		ResultSet rs = stmt.executeQuery("SELECT ss.分店,cs.* FROM v_compare_sum AS cs\r\n" + 
				"LEFT JOIN seitoStore AS ss ON cs.storeid=ss.内部编号\r\n" + 
				"WHERE\r\n" + 
				"((cs.storeid NOT LIKE 'D%' ) OR cs.storeid IS NULL)\r\n" + 
				"AND \r\n" + 
				"(cs.orderdate<convert(varchar(10),getdate(),120)  OR cs.orderdate IS NULL)\r\n" + 
				"ORDER BY cs.orderdate ASC");
		while(rs.next()) {
			   dataMap.put("分店", rs.getString("分店"));
			   dataMap.put("store", rs.getString("store"));
			   dataMap.put("uploaddate", rs.getString("uploaddate"));
			   dataMap.put("POS_TC", rs.getString("POS_TC"));
			   dataMap.put("POS实收金额", rs.getString("POS实收金额"));
			   dataMap.put("storeid", rs.getString("storeid"));
			   dataMap.put("tc", rs.getString("tc"));
			   dataMap.put("price", rs.getString("price"));
			   dataMap.put("orderdate", rs.getString("orderdate"));
			   dataMap.put("tc差值", rs.getString("tc差值"));
			   dataMap.put("金额差值", rs.getString("金额差值"));
			     
		        List<Map> list=new ArrayList<Map>();
		        list.add(dataMap);
				
				  for(int i=0;i<list.size();i++) { System.out.println(list.get(i).toString());
				  }
				 
		        writeExcel(list, 11, "C:/postemp/writeExcel.xlsx");
		}
       

        
    }
    
    public static  Object getResult(Object o) {
    	if(o != null)
    		return o;
    		else return "";
    }

    public static void writeExcel(List<Map> dataList, int cloumnCount,String finalXlsxPath){
        OutputStream out = null;
        try {
            // 获取总列数
            int columnNumCount = cloumnCount;
            // 读取Excel文档
            File finalXlsxFile = new File(finalXlsxPath);
            Workbook workBook = getWorkbok(finalXlsxFile);
            // sheet 对应一个工作页
            Sheet sheet = workBook.getSheetAt(0);
            /**
             * 删除原有数据，除了属性列
             */
            int rowNumber = sheet.getLastRowNum();    // 第一行从0开始算
            System.out.println("原始数据总行数，除属性列：" + rowNumber);
            for (int i = 1; i <= rowNumber; i++) {
                Row row = sheet.getRow(i);
              //  sheet.removeRow(row);
            }
            // 创建文件输出流，输出电子表格：这个必须有，否则你在sheet上做的任何操作都不会有效
            out =  new FileOutputStream(finalXlsxPath);
            workBook.write(out);
            /**
             * 往Excel中写新数据
             */
            for (int j = rowNumber; j < dataList.size(); j++) {
                // 创建一行：从第二行开始，跳过属性列
                Row row = sheet.createRow(j + 1);
                // 得到要插入的每一条记录
                Map dataMap = dataList.get(j);
                String 分店 = getResult(dataMap.get("分店")).toString();
                String store = getResult(dataMap.get("store")).toString();
                String uploaddate = getResult(dataMap.get("uploaddate")).toString();
                String POS_TC = getResult(dataMap.get("POS_TC")).toString();
                String POS实收金额 = getResult(dataMap.get("POS实收金额")).toString();
                String storeid = getResult(dataMap.get("storeid")).toString();
                String tc = getResult(dataMap.get("tc")).toString();
                String price = getResult(dataMap.get("price")).toString();
                String orderdate = getResult(dataMap.get("orderdate")).toString();
                String tc差值 =getResult( dataMap.get("tc差值")).toString();
                String 金额差值 = getResult(dataMap.get("金额差值")).toString();
                for (int k = 0; k <= columnNumCount; k++) {
                // 在一行内循环
                Cell first = row.createCell(0);
                first.setCellValue(分店);
        
                Cell second = row.createCell(1);
                second.setCellValue(store);
        
                Cell third = row.createCell(2);
                third.setCellValue(uploaddate);
                Cell fourth = row.createCell(3);
                fourth.setCellValue(POS_TC);
                Cell fifth = row.createCell(4);
                fifth.setCellValue(POS实收金额);
                Cell sixth = row.createCell(5);
                sixth.setCellValue(storeid);
                Cell seventh = row.createCell(6);
                seventh.setCellValue(tc);
                Cell eightth = row.createCell(7);
                eightth.setCellValue(price);
                Cell nineth = row.createCell(8);
                nineth.setCellValue(orderdate);
                Cell tenth = row.createCell(9);
                tenth.setCellValue(tc差值);
                Cell eleventh = row.createCell(10);
                eleventh.setCellValue(金额差值);
                }
            }
            // 创建文件输出流，准备输出电子表格：这个必须有，否则你在sheet上做的任何操作都不会有效
            out =  new FileOutputStream(finalXlsxPath);
            workBook.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            try {
                if(out != null){
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("数据导出成功");
    }

    /**
     * 判断Excel的版本,获取Workbook
     * @param in
     * @param filename
     * @return
     * @throws IOException
     */
    public static Workbook getWorkbok(File file) throws IOException{
        Workbook wb = null;
        FileInputStream in = new FileInputStream(file);
        if(file.getName().endsWith(EXCEL_XLS)){     //Excel 2003
            wb = new HSSFWorkbook(in);
        }else if(file.getName().endsWith(EXCEL_XLSX)){    // Excel 2007/2010
            wb = new XSSFWorkbook(in);
        }
        return wb;
    }
}