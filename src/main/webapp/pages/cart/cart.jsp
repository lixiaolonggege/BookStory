<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
<base href="http://localhost:8080/Book_war_exploded/">
<link type="text/css" rel="stylesheet" href="static/css/style.css" >
	<script type="text/javascript" src="static/js/jquery.min.js"></script>
	<script type="text/javascript">
		$(function () {
			$("a.deleteItem").click(function () {
				return confirm("确定删除"+$(this).parent().parent().find("td:first").text()+"？");
			})

			$("a.clean").click(function () {
				return confirm("确定清空购物车？");
			})

			$(".update").change(function () {
				var name = $(this).parent().parent().find("td:first").text();
				var count = this.value;
				if( confirm("确定将【"+name+"】数量修改为:"+count+" ?")){
					location.href="http://localhost:8080/Book_war_exploded/cartServlet?action=update&count="+count+"&id="+this.id;
				}else {
					this.value=this.defaultValue;
				}
			})
		})
	</script>
</head>
<body>
	
	<div id="header">
			<!--<img class="logo_img" alt="" src="static/img/logo.gif" >-->
			<span class="wel_word">购物车</span>
			<div>
				<span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临书城</span>
				<a href="pages/order/order.jsp">我的订单</a>
				<a href="userServlet?action=logout">注销</a>
				<a href="index.jsp">返回</a>
			</div>
	</div>
	
	<div id="main">
	
		<table>
			<tr>
				<td>商品名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
				<td>操作</td>
			</tr>
			<c:forEach items="${sessionScope.cart.items}" var="entry">
				<tr>
					<td>${entry.value.name}</td>
					<td>
					<input id="${entry.value.id}" class="update" type="text" value="${entry.value.count}" style="width: 80px"></td>
					<td>${entry.value.price}</td>
					<td>${entry.value.totalPrice}</td>
					<td><a class="deleteItem" href="cartServlet?action=deleteItem&id=${entry.value.id}">删除</a></td>
				</tr>
			</c:forEach>
		</table>
		<c:if test="${not empty sessionScope.cart.items}">
			<div class="cart_info">
				<span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
				<span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
				<span class="cart_span"><a class="clean" href="cartServlet?action=clean">清空购物车</a></span>
				<span class="cart_span"><a href="orderServlet?action=createOrder">去结账</a></span>
			</div>
		</c:if>

		<c:if test="${ empty sessionScope.cart.items}">
			<div align="center" style="color: red">购物车中暂无商品</div>
		</c:if>

		

	
	</div>
	
	<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2015
		</span>
	</div>
</body>
</html>