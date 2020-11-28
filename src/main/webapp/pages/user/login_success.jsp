<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
<meta charset="UTF-8">
<title>会员注册页面</title>
	<base href="http://localhost:8080/Book_war_exploded/"/>
<link type="text/css" rel="stylesheet" href="static/css/style.css" >
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
	
	h1 a {
		color:red;
	}
</style>
</head>
<body>
		<div id="header">
				<!--<img class="logo_img" alt="" src="static/img/logo.gif" >-->
				<div>
					<span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临书城</span>
					<a href="pages/cart/cart.jsp">购物车</a>
					<a href="order/order.jsp">我的订单</a>
					<a href="userServlet?action=logout">注销</a>&nbsp;&nbsp;
					<a href="index.jsp">返回</a>
				</div>
		</div>
		
		<div id="main">
		
			<h1>欢迎回来 <a href="index.jsp">转到主页</a></h1>
	
		</div>
		
		<div id="bottom">
			<span>
				书城.Copyright &copy;2015
			</span>
		</div>
</body>
</html>