<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<span style="font-size:14px;"><script language="javascript" type="text/javascript" src="Js/WdatePicker.js"></script>

<script language="javascript">
	function sel() {
		
		var time1=document.thisform.selfGetTime.value;
		alert(time1);
			var hiveBeginTime=document.thisform.hiveBeginTime.value;
		alert(hiveBeginTime);
			var hiveEndTime=document.thisform.hiveEndTime.value;
		alert(hiveEndTime);
		if (hiveEndTime<hiveBeginTime){
			alert("结束时间必须大于开始时间");
			return false;
		}else
		{
			//document.thisform.submit();
			alert("No problem");
		}
		
	}
</script>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'compare.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  	<form name="thisform" method="post" action="/CanDao/servlet/Compare">
    对账. <br>
     对账文件重传时间 <span style="font-size:14px;"> <input class="Wdate" name="selfGetTime" type="text" value="" onClick="WdatePicker({dateFmt:'yyyy-MM-dd H:mm:ss'})"></span>
     	<input type="button"
			value="重新生成" onclick="sel();"><br/>
			
     大数据取数开始时间 <span style="font-size:14px;"> <input class="Wdate" name="hiveBeginTime" type="text" value="" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"></span>
     结束时间<span style="font-size:14px;"> <input class="Wdate" name="hiveEndTime" type="text" value="" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"></span>
     	<input type="button"
			value="获取数据" onclick="sel();"><br/>
	 </form>
  </body>

</html>
