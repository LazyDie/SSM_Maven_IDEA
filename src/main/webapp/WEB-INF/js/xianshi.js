//页面加载完成后，直接去发送一个ajax请求，要到分页数据
$(function(){
    to_page(1);
});

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

function build_emp_table(result) {
    //清空table
    $("#emps_table tbody").empty();
    var emps = result.extend.pageInfo.list;
    $.each(emps,function(index,item){
        var empIdTd = $("<td></td>").append(item.id);
        var empNameTd = $("<td></td>").append(item.name);
        var empGenderTd =$("<td></td>").append(item.gender=="M"?"男":"女");
        var empEmailTd=$("<td></td>").append(item.email);
        var detpNameTd=$("<td></td>").append("开发部");
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