package com.entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.utils.DBUtil;

public class QcOrder {
String time,logId,step,ip,clientIp,sysName,flag,createTime,className,methodName,level,msg,costTime,isErr,clientType,actionName,apiName;

public String getTime() {
	return time;
}

public void setTime(String time) {
	this.time = time;
}

public String getLogId() {
	return logId;
}

public void setLogId(String logId) {
	this.logId = logId;
}

public String getStep() {
	return step;
}

public void setStep(String step) {
	this.step = step;
}

public String getIp() {
	return ip;
}

public void setIp(String ip) {
	this.ip = ip;
}

public String getClientIp() {
	return clientIp;
}

public void setClientIp(String clientIp) {
	this.clientIp = clientIp;
}

public String getSysName() {
	return sysName;
}

public void setSysName(String sysName) {
	this.sysName = sysName;
}

public String getFlag() {
	return flag;
}

public void setFlag(String flag) {
	this.flag = flag;
}

public String getCreateTime() {
	return createTime;
}

public void setCreateTime(String createTime) {
	this.createTime = createTime;
}

public String getClassName() {
	return className;
}

public void setClassName(String className) {
	this.className = className;
}

public String getMethodName() {
	return methodName;
}

public void setMethodName(String methodName) {
	this.methodName = methodName;
}

public String getLevel() {
	return level;
}

public void setLevel(String level) {
	this.level = level;
}

public String getMsg() {
	return msg;
}

public void setMsg(String msg) {
	this.msg = msg;
}

public String getCostTime() {
	return costTime;
}

public void setCostTime(String costTime) {
	this.costTime = costTime;
}

public String getIsErr() {
	return isErr;
}

public void setIsErr(String isErr) {
	this.isErr = isErr;
}

public String getClientType() {
	return clientType;
}

public void setClientType(String clientType) {
	this.clientType = clientType;
}

public String getActionName() {
	return actionName;
}

public void setActionName(String actionName) {
	this.actionName = actionName;
}

public String getApiName() {
	return apiName;
}

public void setApiName(String apiName) {
	this.apiName = apiName;
}

public static void insertQcOrder(QcOrder qcOrder)throws SQLException{
    //首先拿到数据库的连接
    Connection conn=DBUtil.getConnection();
 /*   String sql="" + 
            "insert into status"+
            "(title,value,dateTime) "+
            "values(?,?,?)";//参数用?表示，相当于占位符;
*/	        
    String sql=""+
    		"INSERT INTO [dbo].[qcOrder]"+
    	    "([time],[logId],[step],[ip],[clientIp],[sysName],[flag],[createTime],[className],[methodName],[level],[msg],[costTime],[isErr],[clientType],[actionName],[apiName])"+
    		"VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    PreparedStatement psmt = conn.prepareStatement(sql);
    
    //先对应SQL语句，给SQL语句传递参数
    psmt.setString(1,  qcOrder.getTime());
    psmt.setString(2, qcOrder.getLogId());
    psmt.setString(3, qcOrder.getStep());
    psmt.setString(4,qcOrder.getIp() );
    psmt.setString(5, qcOrder.getClientIp());
    psmt.setString(6, qcOrder.getSysName());
    psmt.setString(7, qcOrder.getFlag());
    psmt.setString(8, qcOrder.getCreateTime());
    psmt.setString(9, qcOrder.getClassName());
    psmt.setString(10, qcOrder.getMethodName());
    psmt.setString(11, qcOrder.getLevel());
    psmt.setString(12, qcOrder.getMsg() );
    psmt.setString(13, qcOrder.getCostTime());
    psmt.setString(14,qcOrder.getIsErr() );
    psmt.setString(15, qcOrder.getClientType());
    psmt.setString(16, qcOrder.getActionName());
    psmt.setString(17, qcOrder.getApiName());
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
