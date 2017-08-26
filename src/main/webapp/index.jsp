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
    <%--引入jQuery--%>
    <%--
        这个jquery可能版本不对，模态框一直不行，换了一个就行了

    <script type="text/javascript" src="jss/jquery.js"></script>--%>
    <script src="http://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <!-- 可选的 Bootstrap 主题文件（一般不用引入） -->
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

 <%--   <script type="text/javascript" src="jss/xianshi.js"></script>--%>

</head>
<body>
<!-- 员工添加的模态框 -->
<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">新增员工</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">员工姓名</label>
                        <div class="col-sm-10">
                            <input type="text" name="name" class="form-control" id="empName_input" placeholder="empName">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Email</label>
                        <div class="col-sm-10">
                            <input type="text" name="email" class="form-control" id="email_input" placeholder="email@qq.com">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">性别</label>
                            <div class="col-sm-10">
                                <label class="radio-inline">
                                    <input type="radio" name="gender" id="gender1_input" value="M" checked="checked"> 男
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" name="gender" id="gender2_input" value="G"> 女
                                </label>

                            </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">部门名</label>
                        <div class="col-sm-4">
                          <%--  部门提交部门id--%>
                            <select class="form-control" name="dept_id" id="dept_add_select">

                            </select>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary">保存</button>
            </div>
        </div>
    </div>
</div>
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
            <button class="btn btn-primary" id="emp_add_modal_bt">新增</button>
            <button class="btn btn-danger">删除</button>
        </div>
    </div>
    <%--表格数据--%>
    <div class="row ">
        <div class="col-md-12">
            <table class="table table-hover" id="emps_table">
                <thead><%--表头--%>
                    <tr>
                        <th>员工ID</th>
                        <th>员工姓名</th>
                        <th>员工性别</th>
                        <th>员工邮箱</th>
                        <th>所在部门</th>
                        <th>#操作</th>
                    </tr>
                </thead>
                <tbody><%--表格体--%>


                </tbody>

            </table>
        </div>
    </div>
    <%--显示分页信息--%>
    <div class="row ">
        <%--分页文字信息--%>
        <div class="col-md-6" id="page_info">

        </div>
        <%--分页条--%>
        <div class="col-md-6" id="page_nav">

        </div>
    </div>
</div>
<script type="text/javascript">
    //页面加载完成后，直接去发送一个ajax请求，要到分页数据
    $(function(){
       to_page(1);
    });
    //根据页码显示页面
    function to_page(pn){
        $.ajax({
            url:"${APP_PATH}/emps",
            data:"pn="+pn,
            type:"GET",
            success:function (result) {
                console.log(result);
                //解析并显示员工数据
                //解析并显示分页数据
                build_emp_table(result);
                build_page_info(result);
                //解析显示分页条
                build_page_nav(result);
            }
        });

    }
    //填充员工信息到表格中
    function build_emp_table(result) {
        //清空table
        $("#emps_table tbody").empty();
        var emps = result.extend.pageInfo.list;
        $.each(emps,function(index,item){
            var empIdTd = $("<td></td>").append(item.id);
            var empNameTd = $("<td></td>").append(item.name);
            var empGenderTd =$("<td></td>").append(item.gender=="M"?"男":"女");
            var empEmailTd=$("<td></td>").append(item.email);
            var detpNameTd=$("<td></td>").append(item.dept.name);
            /*<button class="btn btn-primary btn-xs">
                <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                编辑
                </button>*/
            var editBtn=$("<button></button>").addClass("btn btn-primary btn-xs")
                .append($("<span></span>").addClass("glyphicon glyphicon-pencil")).append("编辑");
            /*button class="btn btn-danger btn-xs">
                <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                删除
                </button>*/
            var deleteBtn=$("<button></button>").addClass("btn btn-danger btn-xs")
                .append($("<span></span>").addClass("glyphicon glyphicon-trash")).append("删除");
            var btnTd =$("<td></td>").append(editBtn).append(" ").append(deleteBtn);
            /*append方法执行完成之后还是原来的元素*/
            $("<tr></tr>").append(empIdTd)
                .append(empNameTd)
                .append(empGenderTd)
                .append(empEmailTd)
                .append(detpNameTd)
                .append(btnTd)
                .appendTo("#emps_table tbody");
        });
    }
    //解析显示分页信息
    function build_page_info(result){
        $("#page_info").empty();
        $("#page_info").append(" 当前页码"+result.extend.pageInfo.pageNum+",总共"+result.extend.pageInfo.pages+
            "页,总共"+result.extend.pageInfo.total+"条记录")
    }
    //解析显示分页条
    function  build_page_nav(result) {
        $("#page_nav").empty();
        var ul=$("<ul></ul>").addClass("pagination");
        var firstPageLi=$("<li></li>").append($("<a></a>").append("首页").attr("href","#"));
        var prePageLi=$("<li></li>").append($("<a></a>").append("&laquo;").attr("href","#"));
        if(result.extend.pageInfo.hasPreviousPage==false){
            prePageLi.addClass("disabled");
            firstPageLi.addClass("disabled");
        }else{
            prePageLi.click(function () {
                to_page(result.extend.pageInfo.pageNum-1);
            });
            firstPageLi.click(function () {
                to_page(1);
            });
        }
        var nextPageLi=$("<li></li>").append($("<a></a>").append("&raquo;").attr("href","#"));
        var lastPageLi=$("<li></li>").append($("<a></a>").append("末页").attr("href","#"));
        if(result.extend.pageInfo.hasNextPage==false){
            nextPageLi.addClass("disabled");
            lastPageLi.addClass("disabled");
        }else{
            nextPageLi.click(function () {
                to_page(result.extend.pageInfo.pageNum+1);
            });
            lastPageLi.click(function () {
                to_page(result.extend.pageInfo.pages);
            });
        }
        //添加首页和前一页
        ul.append(firstPageLi).append(prePageLi);
        $.each(result.extend.pageInfo.navigatepageNums,function(index,item){
            var numLi=$("<li></li>").append($("<a></a>").append(item).attr("href","#"));
            if(result.extend.pageInfo.pageNum==item){
                numLi.addClass("active");
            }
            numLi.click(function(){
                to_page(item);
            });
           //添加页码号
            ul.append(numLi);
        });
        //添加下一页和末页
        ul.append(nextPageLi).append(lastPageLi);
        var nav =$("<nav></nav>").append(ul);
        $("#page_nav").append(nav);
    }
    //点击新增按钮弹出模态框
    $("#emp_add_modal_bt").click(function () {

        //发送ajax请求，查出部门信息，显示在下拉列表
        getDepts();
        //弹出模态框
        $("#myModal").modal({
            backdrop:"static"
        });
    });
    //查出所有部门信息
    function getDepts(){
        $.ajax({
           url:"${APP_PATH}/depts",
            type:"GET",
            success:function (result) {
               //将部门信息添加到下拉列表

                $.each(result.extend.depts,function(){
                    var optionEle = $("<option></option>").append(this.name).attr("value",this.id);
                    optionEle.appendTo("#dept_add_select");
                });
            }
        });
    }


</script>
</body>

</html>
