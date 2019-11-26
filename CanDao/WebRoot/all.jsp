<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<link rel="stylesheet"
	href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
<script
	src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<%
	String path = request.getContextPath();

	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	ResourceBundle resource = ResourceBundle.getBundle("web");

	/* 	int currentPage = (Integer) request.getAttribute("cupg");
		int cugg = 3;
	 Integer d=0;
	 	int totalnum = (Integer)request.getAttribute("totalnum");
		int nubtn = (Integer) request.getAttribute("nubtn");
		if (nubtn >= 3)
			cugg = nubtn;
		String a = request.getQueryString();
		if (a != null) {
			String[] ee = a.split("=");
			a = ee[1];
	
			d = Integer.parseInt(a);
		} */
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'all.jsp' starting page</title>

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
	<div class="column">
		<h1 class="column-tit">
			<span><a href="#" title="合兴中台">合兴中台</a> > <a href="#"
				title="订单">订单</a></span>
			<!--订单  -->
		</h1>
		<div class="mailbox mt20">

			<p class="paging">
				<a href="/CanDao/servlet/Showall?page=${paging.indexpage-1}">&lt;&lt;
					首页 </a> <a href="/CanDao/servlet/Showall?page=${paging.page-1 }">
					&lt; 上一页 </a> <strong>第${paging.page+1}页/共${paging.pagenumber}页</strong>
				<a href="/CanDao/servlet/Showall?page=${paging.page+1}">下一页 &gt;</a>
				<a href="/CanDao/servlet/Showall?page=${paging.pagenumber-1}">末页
					&gt;&gt;</a> <a>总计<%
 	out.print(request.getAttribute("totalnum"));
 %>条记录
				</a>
			</p>

			<%-- 			<div class="page">

				<div style="display: none;" id="yc"><%=a%></div>
				<ul id="mu">
					<li class="num"><a href="/CanDao/servlet/Showall?cupg=1">首页</a></li>
					<li class="num"><a
						href="/CanDao/servlet/Showall?cupg=<%=currentPage - 1%>">上一页</a></li>
					<li class="num page-active"><a
						href="/CanDao/servlet/Showall?nubtn=<%=cugg - 2%>"><%=cugg - 2%></a></li>
					<li class="num" style=""><a
						href="/CanDao/servlet/Showall?nubtn=<%=cugg - 1%>"><%=cugg - 1%></a></li>
					<li class="num"><a
						href="/CanDao/servlet/Showall?nubtn=<%=cugg%>"><%=cugg%></a></li>
					<li class="num"><a
						href="/CanDao/servlet/Showall?nubtn=<%=cugg + 1%>"><%=cugg + 1%></a></li>
					<li class="num"><a
						href="/CanDao/servlet/Showall?nubtn=<%=cugg + 2%>"><%=cugg + 2%></a></li>
					<li class="page-speciall">...</li>
					<li class="num">34</li>
					<li class="num"><a
						href="/CanDao/servlet/Showall?cupg=<%=currentPage + 1%>">下一页</a></li>
					<li class="num"><a href=>尾页</a></li>
				</ul>
			</div>
		</div> --%>
		</div>

		<!-- 		
<nav id="navbar-example" class="navbar navbar-default navbar-static" role="navigation">
        <div class="container-fluid">
        <div class="navbar-header">
			<div data-spy="scroll" data-target="#navbar-example" data-offset="0"
     style="height:400px;overflow:auto; position: relative;"> -->


		<style>
.define-table {
	border-collapse: collapse;
	border-spacing: 0;
	border-left: 1px solid #888;
	border-top: 1px solid #888;
}

.define-table th, .define-table td {
	border-right: 1px solid #888;
	border-bottom: 1px solid #888;
	padding: 5px 15px;
}

.define-table th {
	font-weight: bold;
	background: #ccc;
}
</style>

		<table class="define-table" width="800" height="200">


			<tr>
				<th>订单ID</th>
				<!-- orderid -->
				<th>第三方订单号</th>
				<!-- thirdSn -->
				<th>第三方订单号</th>
				<!-- storeId -->
				<th>渠道来源</th>
				<!-- fromType -->
				<th>订单时间</th>
				<!-- orderTime-->
				<th>订单日期</th>
				<!-- orderDate -->


				<th>orderStatus</th>
				<th>orderType</th>
				<th>posOrderType</th>
				<th>isPayed</th>
				<th>payType</th>

				<th>price</th>
				<th>deliveryFee</th>
				<th>mealFee</th>
				<th>productPrice</th>
				<th>discountPrice</th>
				<th>merchantBearPrice</th>
				<th>thirdPlatformBearPrice</th>
				<th>merchantPrice</th>
				<th>commission</th>
				<th>extra</th>
				<th>paymentDetails</th>
				<th>status</th>
				<th>products</th>
				<th>discounts</th>
				<th>createtime</th>
				<th>name</th>
				<th>phone</th>
				<th>takeNo</th>
				<th>tableNum</th>
				<th>userNote</th>
				<th>peopleNum</th>
				<th>tableNo</th>
				<th>deviceNo</th>
				<th>staffId</th>
				<th>staffNo</th>
				<th>staffBane</th>
				<th>memberId</th>
				<th>point</th>
				<th>pointExpiryDate</th>
				<th>isInvoice</th>
				<th>invoiceDesc</th>
				<th>taxNo</th>
			</tr>

			<c:forEach items="${key_list}" var="order" varStatus="idx">
				<tr>
					<td>${order.orderid}</td>
					<td>${order.thirdSn}</td>
					<td>${order.storeId}</td>
					<td>${order.fromType}</td>
					<td>${order.orderTime}</td>
					<td>${order.orderDate}</td>
					<td>${order.orderStatus}</td>
					<td>${order.orderType}</td>
					<td>${order.posOrderType}</td>
					<td>${order.isPayed}</td>
					<td>${order.payType}</td>
					<td>${order.price}</td>
					<td>${order.deliveryFee}</td>
					<td>${order.mealFee}</td>
					<td>${order.productPrice}</td>
					<td>${order.discountPrice}</td>
					<td>${order.merchantBearPrice}</td>
					<td>${order.thirdPlatformBearPrice}</td>
					<td>${order.merchantPrice}</td>
					<td>${order.commission}</td>
					<td>${order.extra}</td>
					<td>${order.paymentDetails}</td>
					<td>${order.status}</td>
					<td>${order.products}</td>
					<td>${order.discounts}</td>
					<td>${order.createtime}</td>
					<td>${order.name}</td>
					<td>${order.phone}</td>
					<td>${order.takeNo}</td>
					<td>${order.tableNum}</td>
					<td>${order.userNote}</td>
					<td>${order.peopleNum}</td>
					<td>${order.tableNo}</td>
					<td>${order.deviceNo}</td>
					<td>${order.staffId}</td>
					<td>${order.staffNo}</td>
					<td>${order.staffBane}</td>
					<td>${order.memberId}</td>
					<td>${order.point}</td>
					<td>${order.pointExpiryDate}</td>
					<td>${order.isInvoice}</td>
					<td>${order.invoiceDesc}</td>
					<td>${order.taxNo}</td>
				</tr>
			</c:forEach>




		</table>
</body>
</html>
