<%--
  Created by IntelliJ IDEA.
  User: lxl
  Date: 2020/11/26
  Time: 15:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>编辑图书</title>
    <base href="http://localhost:8080/Book_war_exploded/"/>
    <link type="text/css" rel="stylesheet" href="static/css/style.css" >
    <style type="text/css">
        h1 {
            text-align: center;..
        margin-top: 200px;
        }

        h1 a {
            color:red;
        }

        input {
            text-align: center;
        }
    </style>
</head>
<body>
<div id="header">
    <span class="wel_word">编辑图书</span>
    <div>
        <a href="pages/manager/book_manager.html">图书管理</a>
        <a href="pages/manager/order_manager.html">订单管理</a>
        <a href="index.html">返回商城</a>
    </div>
</div>

<div id="main">
    <form action="manager/bookServlet">
        <c:if
        <input type="hidden" name="action" value="update"/>
        <input type="hidden" name="id" value="${requestScope.book.id}"/>
        <table>
            <tr>
                <td>名称</td>
                <td>价格</td>
                <td>作者</td>
                <td>销量</td>
                <td>库存</td>
                <td colspan="2">操作</td>
            </tr>
            <tr>
                <td><input name="name" type="text" value="${requestScope.book.name}"/></td>
                <td><input name="price" type="text" value="${requestScope.book.price}"/></td>
                <td><input name="author" type="text" value="${requestScope.book.author}"/></td>
                <td><input name="sales" type="text" value="${requestScope.book.sales}"/></td>
                <td><input name="stock" type="text" value="${requestScope.book.stock}"/></td>
                <td><input type="submit" value="提交"/></td>
            </tr>
        </table>
    </form>


</div>

<div id="bottom">
			<span>
				Copyright &copy;2015
			</span>
</div>
</body>
</html>
