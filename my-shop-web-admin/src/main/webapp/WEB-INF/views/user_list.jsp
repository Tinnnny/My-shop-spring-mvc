<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--jstl提供的专门用来格式化的工具--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>


<!DOCTYPE html>
<head>
    <title>我的商城| 用户管理</title>
    <jsp:include page="../includes/header.jsp"/>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <jsp:include page="../includes/nav.jsp"/>
    <jsp:include page="../includes/menu.jsp"/>
    <div class="content-wrapper">
        <section class="content-header">
            <h1>
                <small>用户管理</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> 首页</a></li>
                <li class="active">控制面板</li>
            </ol>
        </section>
        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-xs-12">
                    <c:if test="${baseResult !=  null}">
                        <div class="alert alert-${baseResult.status==200?"success":"danger"} alert-dismissible">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                            <h4><i class="icon fa fa-ban"></i> Alert!</h4>
                                ${baseResult.message}
                        </div>
                    </c:if>
                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title">用户列表</h3>


                            <div class="box box-info-search" style="padding-top: 15px;display: none">
                                <div class="box-header ">
                                    <h3 class="box-title">高级搜索</h3>


                                    <div class="box-body">
                                        <div class="row form-horizontal" style="margin-top: 20px">
                                            <div class="col-xs-12 col-sm-3">
                                                <div class="form-group">
                                                    <label for="username" class="col-sm-2 control-label">姓名</label>
                                                    <div class="col-xs-8">
                                                        <input id="username" Class="form-control" placeholder="姓名"/>

                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-xs-12 col-sm-3">
                                                <div class="form-group">
                                                    <label for="email" class="col-sm-2 control-label">邮箱</label>
                                                    <div class="col-xs-8">
                                                        <input id="email" Class="form-control" placeholder="邮箱"/>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-xs-12 col-sm-3">
                                                <div class="form-group">
                                                    <label for="phone" class="col-sm-2 control-label">手机</label>
                                                    <div class="col-xs-8">
                                                        <input id="phone" Class="form-control" placeholder="手机"/>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="input-group-btn">
                                                <button type="button" class="btn btn-info pull-right" onclick="search()">搜索</button>
                                            </div>


                                        </div>
                                    </div>

                                </div>

                            </div>

                            <div class="box-body">
                                <a href="/user/form" type="button" class="btn btn-sm  btn-default"><i
                                        class="fa fa-plus"></i>新增</a>&nbsp;&nbsp;&nbsp;
                                <button type="button" class="btn btn-sm  btn-default "
                                        onclick="App.deleteMulti('/user/delete')"><i class="fa fa-trash-o"></i>删除
                                </button>&nbsp;&nbsp;&nbsp;
                                <a href="#" type="button" class="btn btn-sm  btn-default"><i class="fa fa-download"></i>导入</a>&nbsp;&nbsp;&nbsp;
                                <a href="#" type="button" class="btn btn-sm  btn-default"><i class="fa fa-upload"></i>导出</a>&nbsp;&nbsp;&nbsp;
                                <a href="#" type="button" class="btn btn-sm  btn-primary"
                                   onclick="$('.box-info-search').css('display')=='none'?$('.box-info-search').show('fast'):$('.box-info-search').hide('fast')"><i
                                        class="fa fa-search"></i>搜索</a>
                            </div>
                        </div>

                        <!-- /.box-header -->
                        <div class="box-body table-responsive">
                            <table id="dataTable" class="table table-hover">
                                <thead>
                                <tr>
                                    <th><input type="checkbox" class="minimal icheck_master"/></th>
                                    <th>ID</th>
                                    <th>用户名</th>
                                    <th>手机</th>
                                    <th>邮箱</th>
                                    <th>更新时间</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <%--<c:forEach items="${tbUsers}" var="tbUser">--%>
                                <%--<tr>--%>
                                <%--<td><input id="${tbUser.id}" type="checkbox" class="minimal" /></td>--%>
                                <%--<td>${tbUser.id}</td>--%>
                                <%--<td>${tbUser.username}</td>--%>
                                <%--<td>${tbUser.phone}</td>--%>
                                <%--<td>${tbUser.email}</td>--%>
                                <%--<td><fmt:formatDate value="${tbUser.updated}" pattern="yyyy-MM-dd HH:mm:ss" /></td>--%>
                                <%--<td>--%>
                                <%--<a href="#" type="button" class="btn btn-sm  btn-primary"><i class="fa fa-search"></i> 查看</a>&nbsp;&nbsp;&nbsp;--%>
                                <%--<a href="#" type="button" class="btn btn-sm  btn-success"><i class="fa fa-edit"></i>编辑</a>&nbsp;&nbsp;&nbsp;--%>
                                <%--<a href="#" type="button" class="btn btn-sm  btn-danger" ><i class="fa fa-trash-o"></i>删除</a>&nbsp;&nbsp;&nbsp;--%>
                                <%--</td>--%>
                                <%--</tr>--%>
                                <%--</c:forEach>--%>
                                </tbody>
                            </table>
                        </div>
                        <!-- /.box-body -->
                    </div>
                    <!-- /.box -->
                </div>
            </div>

        </section>
    </div>
    <jsp:include page="../includes/copyright.jsp"/>
</div>

<jsp:include page="../includes/footer.jsp"/>
<%--自定义模态框--%>
<sys:modal/>
<script>
    var _dataTable;
    $(function () {
        var _columns = [
            {
                "data": function (row, type, val, meta) {
                    return '<input id="' + row.id + '" type="checkbox" class="minimal" />';
                }
            },
            {"data": "id"},
            {"data": "username"},
            {"data": "phone"},
            {"data": "email"},
            {"data": "updated"},
            {
                "data": function (row, type, val, meta) {
                    var detailUrl="/user/detail?id="+row.id;
                    return '<button type="button" id="' + row.id + '" class="btn btn-sm  btn-primary" onclick="App.showDetail(\''+detailUrl+'\');"><i class="fa fa-search"></i> 查看</button >&nbsp;&nbsp;&nbsp;' +
                           '<a href="/user/form?id=' + row.id + '" type="button"  class="btn btn-sm  btn-success"><i class="fa fa-edit"></i>编辑</a>&nbsp;&nbsp;&nbsp;' +
                           '<a href="#" type="button" id="' + row.id + '" class="btn btn-sm  btn-danger" ><i class="fa fa-trash-o"></i>删除</a>&nbsp;&nbsp;&nbsp;';
                }
            }
        ]
         _dataTable=App.initDataTables("/user/page", _columns);

    });

    function search() {
        var username = $("#username").val();
        var phone = $("#phone").val();
        var email = $("#email").val();
        var param = {
            "username": username,
            "phone": phone,
            "email": email
    };
        _dataTable.settings()[0].ajax.data = param;
        _dataTable.ajax.reload();
    }


</script>
</body>
</html>
