<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图书信息</title>
<%@ include file="/commons/common.jsp" %>  
<%@ include file="/commons/queryCondition.jsp"%>
</head>
<body style=" background:url(backImage10.jpg);background-size:100%; background-attachment: fixed">
	
		<center>
		<br><br>
		Title: ${book.title }
		<br><br>
		Author: ${book.author }
		<br><br>
		Price: ${book.price }
		<br><br>
		PublishingDate: ${book.publishingDate }
		<br><br>
		Remark: ${book.remark }
		<br><br>
		
		<a href="bookServlet?method=getBooks&pageNo=${param.pageNo }">继续购物</a>
		</center>
		
		
	

</body>
</html>