package com.utils;



import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ddl.CancelOrder;
import com.ddl.Test;
import com.ddl.Test2;

import net.sf.json.JSONObject;

import java.io.FileInputStream;
import java.sql.SQLException;
/**
 * Author: 灵枢
 * Date: 2018/12/05
 * Time: 17:21
 * Description:读取Excel数据
 */
public class ExcelData {
	private XSSFSheet sheet;

	/**
	 * 构造函数，初始化excel数据
	 * @param filePath  excel路径
	 * @param sheetName sheet表名
	 */
	 ExcelData(String filePath,String sheetName){
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(filePath);
			XSSFWorkbook sheets = new XSSFWorkbook(fileInputStream);
			//获取sheet
			sheet = sheets.getSheet(sheetName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据行和列的索引获取单元格的数据
	 * @param row
	 * @param column
	 * @return
	 */
	public String getExcelDateByIndex(int row,int column){
		XSSFRow row1 = sheet.getRow(row);
		String cell = row1.getCell(column).toString();
		return cell;
	}

	/**
	 * 根据某一列值为“******”的这一行，来获取该行第x列的值
	 * @param caseName
	 * @param currentColumn 当前单元格列的索引
	 * @param targetColumn 目标单元格列的索引
	 * @return
	 */
	public String getCellByCaseName(String caseName,int currentColumn,int targetColumn){
		String operateSteps="";
			//获取行数
			int rows = sheet.getPhysicalNumberOfRows();
			for(int i=0;i<rows;i++){
				XSSFRow row = sheet.getRow(i);
				String cell = row.getCell(currentColumn).toString();
				if(cell.equals(caseName)){
					operateSteps = row.getCell(targetColumn).toString();
					break;
				}
			}
		return operateSteps;
	}

	//打印excel数据
	public void readExcelData() throws SQLException{
		//获取行数
		int rows = sheet.getPhysicalNumberOfRows();
		for(int i=0;i<rows;i++){
			//获取列数
			XSSFRow row = sheet.getRow(i);
			int columns = row.getPhysicalNumberOfCells();
			for(int j=0;j<columns;j++){
				String cell = row.getCell(j).toString();
				System.out.println(cell);
				JSONObject jsonobj = JSONObject.fromObject(cell);// 将字符串转化成json对象
				//JSONObject jsonobject = JSONObject.fromObject(jsonobj.getString("data"));// 将字符串转化成json对象
				String actionName=jsonobj.getString("actionName");
				if(actionName.equals("candao.order.postDineInOrder")){
					Test.postDineorder(cell);
				}else if(actionName.equals("candao.retail.order")){
					Test2.retailOrder(cell);
				}else if(actionName.equals("candao.order.postDineInStatus")){
					CancelOrder.CancelOrder(cell);
				}else if(actionName.equals("candao.retail.updateOrderStatus")){
					System.out.println("更新新零售订单状态啦啦啦啦啦");
				}
				
				
				
				
				
				
				//Test.postDineorder(cell);
			}
		}
	}
	
	public void readExcelDataRetail(){
		//获取行数
		int rows = sheet.getPhysicalNumberOfRows();
		for(int i=0;i<rows;i++){
			//获取列数
			XSSFRow row = sheet.getRow(i);
			int columns = row.getPhysicalNumberOfCells();
			for(int j=0;j<columns;j++){
				String cell = row.getCell(j).toString();
				System.out.println(cell);
				//Test2.retailOrder(cell);
				Test2.retailOrder(cell);
			}
		}
	}
	
	public void readExcelDataCancel(){
		//获取行数
		int rows = sheet.getPhysicalNumberOfRows();
		for(int i=0;i<rows;i++){
			//获取列数
			XSSFRow row = sheet.getRow(i);
			int columns = row.getPhysicalNumberOfCells();
			for(int j=0;j<columns;j++){
				String cell = row.getCell(j).toString();
				System.out.println(cell);
				//Test2.retailOrder(cell);
				try {
					CancelOrder.CancelOrder(cell);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	//测试方法
	public static void main(String[] args) throws SQLException{
		//ExcelData sheet1 = new ExcelData("H:\\OneDrive - cd\\项目资料\\吉野家\\order\\1103\\20191103-DQ测试用例.xlsx", "Sheet3");

		//ExcelData sheet1 = new ExcelData("/Users/jason/OneDrive - cd/项目资料/吉野家/order/1104/j1.xlsx", "Sheet1");
		/*ExcelData sheet1 = new ExcelData("H:\\OneDrive - cd\\项目资料\\吉野家\\order\\1031\\jian24.xlsx", "Sheet1");
		sheet1.readExcelDataRetail();*/
		
		//ExcelData sheet1 = new ExcelData("/Users/jason/OneDrive - cd/项目资料/吉野家/order/1103/20191103-DQ测试用例.xlsx", "Sheet4");
		//ExcelData sheet1 = new ExcelData("H:\\OneDrive - cd\\项目资料\\吉野家\\order\\1129\\订单数据20191129.xlsx", "大而全");
		ExcelData sheet1 = new ExcelData("E:\\OneDrive - cd\\项目资料\\吉野家\\上线\\报表核对\\xiucan\\xiucan.xlsx", "Sheet1");
		//ExcelData sheet1 = new ExcelData("/Users/jason/OneDrive - cd/项目资料/吉野家/order/1129/订单数据20191129.xlsx", "Sheet1");
		sheet1.readExcelData();
		//sheet1.readExcelDataRetail();
		//获取第二行第4列
		//String cell2 = sheet1.getExcelDateByIndex(1, 3);
		//根据第3列值为“customer23”的这一行，来获取该行第2列的值
		//String cell3 = sheet1.getCellByCaseName("customer23", 2,1);
		//System.out.println(cell2);
		//System.out.println(cell3);
		//ExcelData sheet1 = new ExcelData("/Users/jason/OneDrive - cd/项目资料/吉野家/order/1103/20191103-DQ测试用例.xlsx", "Sheet3");
	/*	ExcelData sheet1 = new ExcelData("H:\\OneDrive - cd\\项目资料\\吉野家\\order\\1103\\20191103-DQ测试用例.xlsx", "Sheet3");
		sheet1.readExcelData();*/
	/*	ExcelData sheet1 = new ExcelData("H:\\OneDrive - cd\\项目资料\\吉野家\\order\\lincoln-验证结果\\yitunnel（1114生产验证结果）.xlsx", "Sheet1");
		sheet1.readExcelDataRetail();*/
		//ExcelData sheet1 = new ExcelData("/Users/jason/OneDrive - cd/项目资料/吉野家/order/1118/st1.xlsx", "Sheet1");
		//sheet1.readExcelData();
	/*	ExcelData sheet1 = new ExcelData("/Users/jason/OneDrive - cd/项目资料/吉野家/order/1108/ncr1108.xlsx", "Sheet1");
		sheet1.readExcelDataCancel();*/
/*		ExcelData sheet1 = new ExcelData("H:\\OneDrive - cd\\项目资料\\吉野家\\order\\1106\\ncr11061627.xlsx", "Sheet1");
		sheet1.readExcelData();*/
		
		//ExcelData sheet2 = new ExcelData("/Users/jason/OneDrive - cd/项目资料/吉野家/order/1107/j241107.xlsx", "Sheet2");
	/*	ExcelData sheet2 = new ExcelData("H:\\OneDrive - cd\\项目资料\\吉野家\\order\\1107\\j241107.xlsx", "Sheet2");
		sheet2.readExcelDataRetail();*/
		//ExcelData sheet2 = new ExcelData("H:\\OneDrive - cd\\项目资料\\吉野家\\order\\1101\\y1.xlsx", "Sheet1");
/*		ExcelData sheet2 = new ExcelData("/Users/jason/OneDrive - cd/项目资料/吉野家/order/1107/j241107.xlsx", "Sheet2");
		sheet2.readExcelDataRetail();*/
	//	ExcelData sheet1 = new ExcelData("/Users/jason/OneDrive - cd/项目资料/吉野家/order/1106/ST11063701.xlsx", "Sheet1");
		/*ExcelData sheet1 = new ExcelData("H:\\OneDrive - cd\\项目资料\\吉野家\\order\\1106\\ST11063701.xlsx", "Sheet1");
		sheet1.readExcelData();*/
	/*	ExcelData sheet1 = new ExcelData("/Users/jason/OneDrive - cd/项目资料/吉野家/order/1106/ST11063701.xlsx", "Sheet3");
		sheet1.readExcelDataCancel();*/
		
		
		
		
		
		
		
	}
}

