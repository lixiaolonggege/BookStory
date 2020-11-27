<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: lxl
  Date: 2020/11/26
  Time: 14:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>图书管理</title>
    <base href="http://localhost:8080/Book_war_exploded/"/>
    <link type="text/css" rel="stylesheet" href="static/css/style.css" >
    <script type="text/javascript" src="static/js/jquery.min.js"></script>
    <script type="text/javascript">
        $(function () {
            $("a.deleteClass").click(function () {
               return  confirm("确定删除["+$(this).parent().parent().find("td:first").text()+"]?");
            })
        })
    </script>
</head>
<body>

<div id="header">
    <span class="wel_word">图书管理系统</span>
    <div>
        <a href=manager/bookServlet?action=page>图书管理</a>
        <a href="pages/manager/order_manager.html">订单管理</a>
        <a href="index.html">返回商城</a>
    </div>
</div>

<div id="main">
    <table>
            <tr>
                <td>名称</td>
                <td>价格</td>
                <td>作者</td>
                <td>销量</td>
                <td>库存</td>
                <td colspan="2">操作</td>
            </tr>
        <c:forEach items="${requestScope.page.items}" var="book">
            <tr>
                <td>${book.name}</td>
                <td>${book.price}</td>
                <td>${book.author}</td>
                <td>${book.sales}</td>
                <td>${book.stock}</td>
                <td><a href=manager/bookServlet?action=getBook&id=${book.id}>修改</a></td>
                <td><a class="deleteClass" href=manager/bookServlet?action=delete&id=${book.id}>删除</a></td>
            </tr>
        </c:forEach>




        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td><a href="pages/manager/book_edit.html">添加图书</a></td>
        </tr>
    </table>
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
                location.href = "${pageScope.basePath}${ requestScope.page.url }&pageNum=" + pageNum;

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
