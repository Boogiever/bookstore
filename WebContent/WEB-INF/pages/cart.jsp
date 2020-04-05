<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/commons/common.jsp" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="script/jquery.js"></script>
<%@ include file="/commons/queryCondition.jsp"%>
<script type="text/javascript">
	$(function(){
		$(".delete").click(function(){
			var $tr=$(this).parent().parent();
			var title=$.trim($tr.find("td:first").text());
			var flag=confirm("确定要删除"+title+"的信息吗?");
			
			if(flag){
				return true;
			}
			
			return false;
		});
		//ajax 修改单个商品的数量
		//1,获取页面中所有的text，并为其添加onchange响应函数。弹出确认对话框：确认修改吗
		$(":text").change(function(){
		
			var quantityVal=$.trim(this.value);
			
			var flag=false;
			var reg=/^\d+$/g;
			var quantity=-1;
			if(reg.test(quantityVal)){
				 quantity=parseInt(quantityVal);
				if(quantity>=0){
				 flag=true;
				}
				
			}
			
			if(!flag){
				alert("输入的数量不合法");
				$(this).val($(this).attr("class"));
				return;
			}
			var $tr=$(this).parent().parent();
			var title=$.trim($tr.find("td:first").text());
			if(quantity==0){
				var flag2=confirm("确定要删除"+title+"的信息吗");
				if(flag2){
					//得到了a结点
					var $a=$tr.find("td:last").find("a");
					//执行a结点的onclick响应函数
					//alert($a[0].onclick);
					$a[0].click();
					return;
				}
				
			}
		
			var flag=confirm("确定要修改"+title+"的数量吗");
			
			if(!flag){
				$(this).val($(this).attr("class"));
				return;
			}
		//	debugger;
		//2.请求地址为：bookServlet
		var url="bookServlet";
		
		//3,请求参数为：method：updateItemQuantity，id：name属性值,quantity:val,time=new Date()
		var idVal=$.trim(this.name);
		var args={"method":"updateItemQuantity","id":idVal,"quantity":quantityVal,"time":new Date()};
		//4,在updateItemQuantity方法中，获取quantity，id，调用service方法修改，
		
		//5,传回json数据：bookNumber：XX，totalMoney
		
		//6.更新当前页面bookNumber和totalMoney
		$.post(url,args,function(data){
			var bookNumber=data.bookNumber;
			var totalMoney=data.totalMoney;
			
			$("#totalMoney").text("总金额：￥ "+totalMoney);
			$("#bookNumber").text("您的购物车共有"+bookNumber+"本书");
		},"JSON");
		});
		
	})
		
	</script>		
</head>
<body style=" background:url(backImage10.jpg);background-size:100%; background-attachment: fixed">
		<center>
			<br><br>
			<div id="bookNumber">
				您的购物车共有${sessionScope.ShoppingCart.bookNumber }本书
			</div>
			
			<table cellpadding="10">
				<tr>
					<td>Title</td>
					<td>Quantity</td>
					<td>Price</td>
					<td>&nbsp;</td>
				</tr>
				
				<c:forEach items="${sessionScope.ShoppingCart.items}" var="item">
				<tr>
					<td>${item.book.title }</td>
					<td> 
						<input class="${item.quantity }" type="text" name="${item.book.id }" value=${item.quantity } size="1"/>
					</td>
					<td>${item.book.price }</td>
					<td><a href="bookServlet?method=remove&pageNo=${param.pageNo }&id=${item.book.id }" class="delete">删除</a></td>
				</tr>
				</c:forEach>
				
				
				<tr>
					<td colspan="4" id="totalMoney">总金额：￥ ${sessionScope.ShoppingCart.totalMoney }</td>
				</tr>
				
				<tr>
					<td colspan="4">
						<a href="bookServlet?method=getBooks&pageNo=${param.pageNo }">继续购物</a>
						&nbsp;&nbsp;
						<a href="bookServlet?method=clear">清空购物车</a>
						&nbsp;&nbsp;
						<a href="bookServlet?method=forwardPage&page=cash">结账</a>
						&nbsp;&nbsp;
					</td>
				</tr>
			</table>
			
		</center>
		
		<br><br>
		
		
		<br><br>

</body>
</html>