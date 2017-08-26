<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/8/23
  Time: 13:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>员工列表</title>
   <%-- 拿到当前项目路径 以/开始不以/结束--%>
    <%
        pageContext.setAttribute("APP_PATH",request.getContextPath());
    %>
    <%--web路径  不以/开始的相对路径，找资源，以当前资源的路径为基准，经常容易出问题
            以/开始的相对路径，找资源，以服务器的根路径为标准(http://localhost:3306)
            http://localhost:3306/MybatisDemo1--%>
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <!-- 可选的 Bootstrap 主题文件（一般不用引入） -->
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
</head>
<body>
    <%--搭建显示页面--%>
    <div class="container ">
        <%--标题行--%>
        <div class="row ">
            <div class="col-md-12">
                    <h1>员工管理系统</h1>
            </div>
        </div>
         <%--按钮--%>
        <div class="row ">
            <div class="col-md-4 col-md-offset-8">
                <button class="btn btn-primary">新增</button>
                <button class="btn btn-danger">删除</button>
            </div>
        </div>
         <%--表格数据--%>
        <div class="row ">
            <div class="col-md-12">
                <table class="table table-hover">
                    <tr>
                        <th>员工ID</th>
                        <th>员工姓名</th>
                        <th>员工性别</th>
                        <th>员工邮箱</th>
                        <th>所在部门</th>
                        <th>#操作</th>
                    </tr>
                    <c:forEach items="${pageInfo.list}" var="emp">
                        <tr>
                            <th>${emp.id}</th>
                            <th>${emp.name}</th>
                            <th>${emp.gender=="M"?"男":"女"}</th>
                            <th>${emp.email}</th>
                            <th>开发部</th>
                            <th>
                                <button class="btn btn-primary btn-xs">
                                    <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                                    编辑
                                </button>
                                <button class="btn btn-danger btn-xs">
                                    <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                                    删除
                                </button>
                            </th>
                        </tr>

                    </c:forEach>

                </table>
            </div>
        </div>
        <%--显示分页信息--%>
        <div class="row ">
            <%--分页文字信息--%>
            <div class="col-md-6">
                当前页码${pageInfo.pageNum},总共${pageInfo.pages}页,总共${pageInfo.total}条记录
            </div>
            <%--分页条--%>
            <div class="col-md-6">
                <nav aria-label="Page navigation">
                    <ul class="pagination">
                        <li><a href="${APP_PATH}/emps?pn=1">首页</a></li>
                        <li>
                            <c:if test="${pageInfo.hasPreviousPage}">
                                <a href="${APP_PATH}/emps?pn=${pageInfo.pageNum-1}" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </c:if>
                        </li>

                        <c:forEach items="${pageInfo.navigatepageNums}" var="pageNumber">
                            <c:if test="${pageNumber==pageInfo.pageNum}">
                                <li class="active"><a href="">${pageNumber}</a></li>
                            </c:if>
                            <c:if test="${pageNumber!=pageInfo.pageNum}">
                                <li><a href="${APP_PATH}/emps?pn=${pageNumber}">${pageNumber}</a></li>
                            </c:if>
                        </c:forEach>

                        <li>
                            <c:if test="${pageInfo.hasNextPage}">
                                <a href="${APP_PATH}/emps?pn=${pageInfo.pageNum+1}" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </c:if>
                        </li>
                        <li><a href="${APP_PATH}/emps?pn=${pageInfo.pages}">末页</a></li>
                    </ul>
                </nav>
            </div>
        </div>
    </div>
</body>
</html>
