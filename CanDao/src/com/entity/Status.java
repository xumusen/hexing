package com.entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.utils.DBUtil;

public class Status {

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	String title;
	String value;
	String dateTime;
	String orderid;
	
	
	 public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public static void updateStatus(String orderid,Status status)throws SQLException{
	        //首先拿到数据库的连接
	        Connection conn=DBUtil.getConnection();
	        String sql="" + 
	                "insert into status"+
	                "(title,value,dateTime,orderid) "+
	                "values(?,?,?,?)";//参数用?表示，相当于占位符;
	        

	        PreparedStatement psmt = conn.prepareStatement(sql);
	        
	        //先对应SQL语句，给SQL语句传递参数
	        psmt.setString(1,  status.getTitle());
	        psmt.setString(2, status.getValue());
	        psmt.setString(3, status.getDateTime());
	        psmt.setString(4, orderid);
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
	 
	 
/*	    public void updateStatus(String orderid,Status status)throws SQLException{
	        //首先拿到数据库的连接
	        Connection conn=DBUtil.getConnection();
	        String sql="" + 
	                "update BasicInfo set name = ?,age = ? where id = ?";//参数用?表示，相当于占位符
	        
	        //预编译sql语句
	        PreparedStatement psmt = conn.prepareStatement(sql);
	        
	        //先对应SQL语句，给SQL语句传递参数
	        psmt.setString(1, person.getName());
	        psmt.setInt(2, person.getAge());
	        psmt.setInt(3, person.getId());
	        
	        //执行SQL语句
	        psmt.execute();
	    }*/
	    //------------------------------------------update--------------------------------------------------------
	    //------------------------------------------delete--------------------------------------------------------
	    
/*	    public void deletePerson(int id) throws SQLException{
	        Connection conn=DBUtil.getConnection();
	        String sql="" + 
	                "delete from BasicInfo where id = ?";
	        PreparedStatement psmt = conn.prepareStatement(sql);
	        psmt.setInt(1,id);
	        
	        //执行SQL语句
	        psmt.execute();
	    }*/
	    //------------------------------------------delete--------------------------------------------------------
	    //------------------------------------------SearchOne--------------------------------------------------------
	    
/*	    public Person SearchOne(int id) throws SQLException{
	        Person p = null;
	        Connection conn=DBUtil.getConnection();
	        String sql="" + 
	                "select * from BasicInfo where id = ?";
	        PreparedStatement psmt = conn.prepareStatement(sql);
	        psmt.setInt(1,id);
	        
	        //执行SQL语句
	        ResultSet rs = psmt.executeQuery();
	        while(rs.next()){
	            p = new Person();
	            p.setId(rs.getInt("id"));
	            p.setName(rs.getString("name"));
	            p.setAge(rs.getInt("age"));
	        }
	        return p;
	    }*/
	    //------------------------------------------SearchOne--------------------------------------------------------
	    //------------------------------------------Search--------------------------------------------------------
/*	    
	    public List<Person> Search() throws SQLException{
	        Connection conn = DBUtil.getConnection();
	        Statement stmt = conn.createStatement();
	        ResultSet rs =  stmt.executeQuery("select id,name,age from BasicInfo");
	        List<Person> people = new ArrayList<Person>();
	        Person p = null;
	        while(rs.next()){//如果对象中有数据，就会循环打印出来
	            p = new Person();
	            p.setId(rs.getInt("id"));
	            p.setName(rs.getString("name"));
	            p.setAge(rs.getInt("age"));
	            people.add(p);
	        }
	        return people;
	        
	    }*/
	
}
