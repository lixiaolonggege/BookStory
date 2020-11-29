<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
<meta charset="UTF-8">
<title>书城首页</title>
	<base href="http://localhost:8080/Book_war_exploded/">
<link type="text/css" rel="stylesheet" href="static/css/style.css" >
	<link type="text/css" rel="stylesheet" href="static/css/style.css" >
	<script type="text/javascript" src="static/js/jquery.min.js"></script>

	<script type="text/javascript">
		$(function () {
			$("button.addToCart").click(function () {

				//location.href="http://localhost:8080/Book_war_exploded/cartServlet?action=addItem&id="+this.id;
				var bookId=$(this).attr("id");
				$.getJSON("http://localhost:8080/Book_war_exploded/cartServlet","action=addItemAjax&id="+bookId,function (msg) {
					$("#cartTotalCount").text("您的购物车中有"+msg.totalCount+"件商品");
					$("#cartLastName").text(msg.lastName);
				})
			})
		})
	</script>
</head>
<body>
	
	<div id="header">
			<!--<img class="logo_img" alt="" src="static/img/logo.gif" >-->
			<span class="wel_word">网上书城</span>
			<div>
				<c:if test="${empty sessionScope.user}">
					<a href="pages/user/login.html">登录</a>
					<a href="pages/user/regist.html">注册</a>
					<a href="pages/manager/manager.html">后台管理</a>
				</c:if>&nbsp;&nbsp;
				<c:if test="${not empty sessionScope.user}">
					<div>
						<span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临书城</span>
						<a href="pages/cart/cart.jsp">购物车</a>
						<a href="pages/manager/manager.html">后台管理</a>
						<a href="pages/order/order.jsp">我的订单</a>
						<a href="userServlet?action=logout">注销</a>&nbsp;&nbsp;
						<a href="index.jsp">返回</a>
					</div>
				</c:if>

			</div>
	</div>
	<div id="main">
		<div id="book">
			<div class="book_cond">
				<form action="clientServlet" method="get">
					<input type="hidden" name="action" value="pageByPrice">
					价格：<input id="min" type="text" name="min" value=""> 元 - 
						<input id="max" type="text" name="max" value=""> 元 
						<input type="submit" value="查询" />
				</form>
			</div>
			<div style="text-align: center">
				<c:if test="${empty sessionScope.cart.items}">
					<%--购物车为空的输出--%>
					<span id="cartTotalCount"> </span>
					<div>
						<span style="color: red" id="cartLastName">当前购物车为空</span>
					</div>
				</c:if>
				<c:if test="${not empty sessionScope.cart.items}">
					<%--购物车非空的输出--%>
					<span id="cartTotalCount">您的购物车中有 ${sessionScope.cart.totalCount} 件商品</span>
					<div>
						您刚刚将<span style="color: red" id="cartLastName">${sessionScope.lastName}</span>加入到了购物车中
					</div>
				</c:if>
			</div>
			<c:forEach items="${requestScope.page.items}" var="book">
				<div class="b_list">
					<div class="img_div">
						<img class="book_img" alt="" src="${book.imgPath}" />
					</div>
					<div class="book_info">
						<div class="book_name">
							<span class="sp1">书名:</span>
							<span class="sp2">${book.name}</span>
						</div>
						<div class="book_author">
							<span class="sp1">作者:</span>
							<span class="sp2">${book.author}</span>
						</div>
						<div class="book_price">
							<span class="sp1">价格:</span>
							<span class="sp2">${book.price}</span>
						</div>
						<div class="book_sales">
							<span class="sp1">销量:</span>
							<span class="sp2">${book.sales}</span>
						</div>
						<div class="book_amount">
							<span class="sp1">库存:</span>
							<span class="sp2">${book.stock}</span>
						</div>
						<c:if test="${not empty sessionScope.user}">
							<div class="book_add">
								<button class="addToCart" id="${book.id}"">加入购物车</button>
							</div>
						</c:if>
					</div>
				</div>
			</c:forEach>



		</div>

		<%--分页条的开始--%>
		<div id="page_nav">
			<%--大于首页，才显示--%>
			<c:if test="${requestScope.page.pageNum > 1}">
				<a href="${ requestScope.page.url }&pageNum=1">首页</a>
				<a href="${ requestScope.page.url }&pageNum=${requestScope.page.pageNum-1}">上一页</a>
			</c:if>
			<%--页码输出的开始--%>
			<c:choose>
				<%--情况1：如果总页码小于等于5的情况，页码的范围是：1-总页码--%>
				<c:when test="${ requestScope.page.pageTotal <= 5 }">
					<c:set var="begin" value="1"/>
					<c:set var="end" value="${requestScope.page.pageTotal}"/>
				</c:when>
				<%--情况2：总页码大于5的情况--%>
				<c:when test="${requestScope.page.pageTotal > 5}">
					<c:choose>
						<%--小情况1：当前页码为前面3个：1，2，3的情况，页码范围是：1-5.--%>
						<c:when test="${requestScope.page.pageNum <= 3}">
							<c:set var="begin" value="1"/>
							<c:set var="end" value="5"/>
						</c:when>
						<%--小情况2：当前页码为最后3个，8，9，10，页码范围是：总页码减4 - 总页码--%>
						<c:when test="${requestScope.page.pageNum > requestScope.page.pageTotal-3}">
							<c:set var="begin" value="${requestScope.page.pageTotal-4}"/>
							<c:set var="end" value="${requestScope.page.pageTotal}"/>
						</c:when>
						<%--小情况3：4，5，6，7，页码范围是：当前页码减2 - 当前页码加2--%>
						<c:otherwise>
							<c:set var="begin" value="${requestScope.page.pageNum-2}"/>
							<c:set var="end" value="${requestScope.page.pageNum+2}"/>
						</c:otherwise>
					</c:choose>
				</c:when>
			</c:choose>

			<c:forEach begin="${begin}" end="${end}" var="i">
				<c:if test="${i == requestScope.page.pageNum}">
					【${i}】
				</c:if>
				<c:if test="${i != requestScope.page.pageNum}">
					<a href="${ requestScope.page.url }&pageNum=${i}">${i}</a>
				</c:if>
			</c:forEach>
			<%--页码输出的结束--%>


			<%-- 如果已经 是最后一页，则不显示下一页，末页 --%>
			<c:if test="${requestScope.page.pageNum < requestScope.page.pageTotal}">
				<a href="${ requestScope.page.url }&pageNum=${requestScope.page.pageNum+1}">下一页</a>
				<a href="${ requestScope.page.url }&pageNum=${requestScope.page.pageTotal}">末页</a>
			</c:if>

			共${ requestScope.page.pageTotal }页，${ requestScope.page.pageTotalCount }条记录
			到第<input value="${param.pageNum}" name="pn" id="pn_input"/>页
			<input id="searchPageBtn" type="button" value="确定">

			<script type="text/javascript">

				$(function () {
					// 跳到指定的页码
					$("#searchPageBtn").click(function () {

						var pageNum = $("#pn_input").val();

						<%--var pageTotal = ${requestScope.page.pageTotal};--%>
						<%--alert(pageTotal);--%>

						// javaScript语言中提供了一个location地址栏对象
						// 它有一个属性叫href.它可以获取浏览器地址栏中的地址
						// href属性可读，可写
						if(pageNum>0&&pageNum<=${requestScope.page.pageTotal})
							location.href="http://localhost:8080/Book_war_exploded/${ requestScope.page.url }&pageNum="+pageNum;
						else
							alert("页码不合法");


					});
				});

			</script>

		</div>
		<%--分页条的结束--%>




		<div id="bottom">
		<span>
			书城.Copyright &copy;2015
		</span>
	</div>
</body>
</html>