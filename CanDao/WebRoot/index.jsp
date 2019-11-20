<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	request.setCharacterEncoding("UTF-8");
	out.println(basePath);
	
%>





<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'index.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

<script language="javascript">
	function sel() {
		
		var time1=document.thisform.extOrderId.value;
		//alert(time1);
		document.thisform.submit();
	}
</script>
<span style="font-size:14px;"><script language="javascript" type="text/javascript" src="Js/WdatePicker.js"></script>
</head>

<body>
<!-- 
	This is my JSP page.
	<br>
	<%=System.currentTimeMillis() / 1000%>
	<input type="text" size="20" name="Field" value=" ">
	<br>
	<input type="submit" name="submit" value="submit">
	-->
	<form name="thisform" method="post" action="/CanDao/servlet/Showall">
	<!-- 	方法名<input type="text" name="actionName" value="candao.orderStandard.postOrder"><br/> --> 
		订单号<input type="text" name="extOrderId" value="m01">
		流水号<input type="text" name="thirdSn" value="001"><br/>
		经度<input type="text" name="longitude" value="116.4543">
		纬度<input type="text" name="latitude" value="39.936314"><br/>
		门店ID<input type="text" name="extStoreId" value="123">
		姓名<input type="text" name="name" value="jason"><br/>
		电话<input type="text" name="phone" value="13800138000">
		<!-- 备注<input type="text" name="userNote" value="milk,sugar"><br/> -->
		类型<input type="text" name="type" value="1" >
		<!-- 自取时间 <span style="font-size:14px;"> <input class="Wdate" name="gettime" type="text" value="2019-01-01 01:01:01" onClick="WdatePicker({dateFmt:'yyyy-MM-dd H:mm:ss'})"></span><br/> -->
		预送达时间 <span style="font-size:14px;"> <input class="Wdate" name="selfGetTime" type="text" value="2019-02-01 01:01:01" onClick="WdatePicker({dateFmt:'yyyy-MM-dd H:mm:ss'})"></span><br/>
		<!-- 支付方式<input type="text" name="payType" value="1"> -->
		是否结账<input type="text" name="isPayed" value="false"><br/>
		方法名<input type="text" name="isInvoice" value="1"> 
		<!-- 订单号<input type="text" name="invoiceDesc" value="candao.cd"><br/> -->
		<!-- 税号<input type="text" name="taxNo" value="456123789123"> -->
		总价格<input type="text" name="totalPrice" value="100"><br/>
		<!-- 配送费<input type="text" name="deliveryFee" value="8"> -->
<!-- 		餐盒费<input type="text" name="mealFee" value="2"><br/> -->
		<!-- 优惠总金额<input type="text" name="preferentialPrice" value="10"> 优惠总金额
		商家承担金额 <input type="text" name="merchantBearPrice" value="30"><br/>优惠活动 商家承担金额 传正数动
		第三方平台补助<input type="text" name="thirdPlatformBearPrice" value="5">优惠活动 第三方平台承担金额 传正数
		商户实收金额<input type="text" name="merchantPrice" value="33"><br/>商户实收金额
		用餐人数<input type="text" name="peopleNum" value="2"> -->
		是否平台配送<input type="text" name="isThirdDistribute" value="true"><br/>
		<!-- 是否支持第三方ID<input type="text" name="isSupportExtId" value="false"> -->
		<!-- 是否开发票<input type="text" name="isInvoice" value="false"><br/> --> 
		是否容错<input type="text" name="isSupportEmptyProductId" value="true">
		<!-- 地址<input type="text" name="address" value="jintaidasha"><br/> -->
		餐品1<br/>
		餐品ID<input type="text" name="pid" value="9684"><br/>
		餐品名称<input type="text" name="cai_name" value="bread"><br/>
		餐品数量<input type="text" name="num" value="1"><br/>
		餐品价格<input type="text" name="price" value="100"><br/>
		
	<%-- 	<li><label>餐品</label>
<select name="educa" >

<option value="a" <c:if test="${educa=='a'}"></c:if>>餐品1</option>
<option value="b" <c:if test="${educa=='b'}"></c:if>>餐品2</option>
<option value="c" <c:if test="${educa=='c'}"></c:if>>餐品3</option>
<option value="d" <c:if test="${educa=='d'}"></c:if>>餐品4</option>

</select> --%>

</li> 




		
		
<br/>
<br/>
		<input type="button"
			value="submit" onclick="sel();">
	 		
			
	</form>


</body>
</html>
