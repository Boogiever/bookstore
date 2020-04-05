<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ include file="/commons/common.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>结账</title>
</head>
<body style=" background:url(backImage10.jpg);background-size:100%; background-attachment: fixed">
		<center>
			<br><br>
			您的一共买了${sessionScope.ShoppingCart.bookNumber }本书
			<br><br>
			应付：￥ ${sessionScope.ShoppingCart.totalMoney }
			
			
			<br><br>
			<c:if test="${requestScope.errors != null }">
			<font color="red">${requestScope.errors }</font>
			</c:if>
			
				<form action="bookServlet?method=cash" method="post">
				<tr>
					<td>信用卡姓名：</td>
					<td><input type="text" name="username"/></td>
				</tr>
				
				<tr>
					<td>信用卡账号：</td>
					<td><input type="text" name="accountId"/></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="Submit"/></td>
				</tr>
				</form>
			
		</center>

</body>
</html>