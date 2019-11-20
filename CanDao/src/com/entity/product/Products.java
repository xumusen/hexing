package com.entity.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.entity.PaymentDetails;
import com.utils.DBUtil;

public class Products {

public String getPid() {
	return pid;
}
public void setPid(String pid) {
	this.pid = pid;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}

public ProcuctCategory getProcuctCategory() {
	return procuctCategory;
}
public void setProcuctCategory(ProcuctCategory procuctCategory) {
	this.procuctCategory = procuctCategory;
}
public ProcuctContent getProcuctContent() {
	return procuctContent;
}
public void setProcuctContent(ProcuctContent procuctContent) {
	this.procuctContent = procuctContent;
}
public Dept getDept() {
	return dept;
}
public void setDept(Dept dept) {
	this.dept = dept;
}
public Category getCategory() {
	return category;
}
public void setCategory(Category category) {
	this.category = category;
}
public Types getTypes() {
	return types;
}
public void setTypes(Types types) {
	this.types = types;
}
public List<Skus> getSkus() {
	return skus;
}
public void setSkus(List<Skus> skus) {
	this.skus = skus;
}
public List<Propertys> getPropertys() {
	return propertys;
}
public void setPropertys(List<Propertys> propertys) {
	this.propertys = propertys;
}
public List<Combos> getCombos() {
	return combos;
}
public void setCombos(List<Combos> combos) {
	this.combos = combos;
}
String pid;
public String getNum() {
	return num;
}
public void setNum(String num) {
	this.num = num;
}
public String getPrice() {
	return price;
}
public void setPrice(String price) {
	this.price = price;
}
public String getBoxNum() {
	return boxNum;
}
public void setBoxNum(String boxNum) {
	this.boxNum = boxNum;
}
public String getBoxPrice() {
	return boxPrice;
}
public void setBoxPrice(String boxPrice) {
	this.boxPrice = boxPrice;
}
public String getItemDisc() {
	return itemDisc;
}
public void setItemDisc(String itemDisc) {
	this.itemDisc = itemDisc;
}
String name;
String num;
String price;
String boxNum;
String boxPrice;
String itemDisc;
ProcuctCategory procuctCategory;
ProcuctContent procuctContent;
Dept dept;
Category category;
Types types;
String productTaxRate;

public String getProductTaxRate() {
	return productTaxRate;
}
public void setProductTaxRate(String productTaxRate) {
	this.productTaxRate = productTaxRate;
}
List<Skus> skus;
List<Propertys> propertys;
List<Combos> combos;
String orderid;


public String getOrderid() {
	return orderid;
}
public void setOrderid(String orderid) {
	this.orderid = orderid;
}
public static void insertProducts(String orderid,Products products)throws SQLException{
    //首先拿到数据库的连接
    Connection conn=DBUtil.getConnection();
 /*   String sql="" + 
            "insert into status"+
            "(title,value,dateTime) "+
            "values(?,?,?)";//参数用?表示，相当于占位符;
*/	        
    String sql=""+
    		"INSERT INTO products"+
    		"(pid,name,num,price,boxNum,boxPrice,itemDisc,productTaxRate,orderid)"+
    		"values (?,?,?,?,?,?,?,?,?)";
    PreparedStatement psmt = conn.prepareStatement(sql);
    
    //先对应SQL语句，给SQL语句传递参数
    psmt.setString(1,  products.getPid());
    psmt.setString(2, products.getName());
    psmt.setString(3, products.getNum());
    psmt.setString(4, products.getPrice());
    psmt.setString(5, products.getBoxNum());
    psmt.setString(6, products.getBoxPrice());
    psmt.setString(7, products.getItemDisc());
    psmt.setString(8, products.getProductTaxRate());
    psmt.setString(9, orderid);
    


    //执行SQL语句
    psmt.execute();
    /**
     * prepareStatement这个方法会将SQL语句加载到驱动程序conn集成程序中，但是并不直接执行
     * 而是当它调用execute()方法的时候才真正执行；
     * 
     * 上面SQL中的参数用?表示，相当于占位符，然后在对参数进行赋值。
     * 当真正执行时，这些参数会加载在SQL语句中，把SQL语句拼接完整才去执行。
     * 这样就会减少对数据库的操作
     */
}



}
