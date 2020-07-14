
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%pageContext.setAttribute("APP_PATH", request.getContextPath());%>
<%--自定义标签--%>
<%@taglib uri="/userTag" prefix="userTag" %>
<!DOCTYPE html>
<script type="text/javascript" src="${APP_PATH}/static/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="${APP_PATH}/static/bootstrap-3.3.7-dist/js/bootstrap.js"></script>
<link rel="stylesheet" type="text/css" href="${APP_PATH}/static/bootstrap-3.3.7-dist/css/bootstrap.css"/>
<link rel="stylesheet" type="text/css" href="${APP_PATH}/static/bootstrap-3.3.7-dist/css/bootstrap-theme.css"/>
<html lang="en">
<head>
    <title>Hello</title>
</head>
<body>
<h1 align="center">欢迎您：<%=request.getParameter("username")%>,当前在线人数：<%=application.getAttribute("count")%> 人
</h1>
<h2 align="center"> 在线用户有：<userTag:userTag/>
</h2>
<%--<h2><%= request.getSession().getAttribute("users")%>
</h2>
<h3>${users}</h3>--%>
<p class="text-center container" style="font-size: 30px;margin-top: 50px">员工列表</p>
<div clas="row" style="margin-top: 30px;margin-left: 560px">
    <div class="form-group">
        <input type="text" class="text-primary" style="width: 150px" placeholder="请输入用户姓名" id="name">
        <button type="button" class="btn btn-primary" style="margin-left: 20px" id="findByNameBtn">查找</button>
        <button type="button" class="btn btn-primary" style="margin-left: 50px" id="addUserBtn">新增</button>
    </div>
</div>
<div class="row" style="margin-top: 50px;">
    <table class="table table-bordered table-hover" style="width: 450px;margin-left: 550px" id="userList">
        <caption>员工列表</caption>
        <thead>
        <tr>
            <th scope="col"
                style="border-width:1px;border-color:Gainsboro;border-bottom-style:solid;font-size: 15px;width: 50px">
                ID
            </th>
            <th scope="col"
                style="border-width:1px;border-color:Gainsboro;border-bottom-style:solid;font-size: 15px;width: 50px">
                姓名
            </th>
            <th scope="col"
                style="border-width:1px;border-color:Gainsboro;border-bottom-style:solid;font-size: 15px;width: 50px">
                用户名
            </th>
            <th scope="col"
                style="border-width:1px;border-color:Gainsboro;border-bottom-style:solid;font-size: 15px;width: 50px">
                密码
            </th>
            <th scope="col"
                style="border-width:1px;border-color:Gainsboro;border-bottom-style:solid;font-size: 15px;width: 250px">
                操作
            </th>
        </tr>
        </thead>
        <tbody>
        <%--注释一下 --%>
        <%--        <c:forEach items="${users}" var="user">
                    <tr>
                        <td>${user.getId()}</td>
                        <td>${user.getName()}</td>
                        <td>${user.getCode()}</td>
                        <td>${user.getPassword()}</td>
                    </tr>
                </c:forEach>--%>
        </tbody>
    </table>

    <!-- 修改或者新增 -->
    <div id="userModel" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true">
        <div class="modal-dialog ">
            <div class="modal-content radius">
                <div class="modal-header">
                    <h3 class="modal-title" align="center">用户信息</h3>
                    <a class="close" data-dismiss="modal" aria-hidden="true">×</a>
                </div>
                <div class="modal-body">
                    <div class="row cl" style="margin-left: 150px">
                        <label class="form-label col-xs-3">用户名字</label>
                        <div class="formControls col-xs-5">
                            <input type="text" class="form-control" value="" id="userName" placeholder="请输入真实姓名">
                        </div>
                    </div>
                    <div class="row cl" style="margin-top: 30px;margin-left: 150px">
                        <label class="form-label col-xs-3">用户名Code</label>
                        <div class="formControls col-xs-5">
                            <input type="text" class="form-control" value="" id="userCode" placeholder="请输入用户名">
                        </div>
                    </div>
                    <div class="row cl" style="margin-top: 30px;margin-left: 150px">
                        <label class="form-label col-xs-3">用户密码Password</label>
                        <div class="formControls col-xs-5">
                            <input type="text" class="form-control" value="" id="userPassword" placeholder="请输入密码">
                        </div>
                    </div>
                    <div class="modal-body" style="margin-top: 20px;margin-left: 190px">
                        <button class="btn btn-primary" id="modifyOrCreateUser" type="submit">确定</button>
                        <button class="btn" data-dismiss="modal" aria-hidden="true" style="margin-left: 90px">关闭
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
<script>
    /*
    * 开始的时候加载所有
    * */
    $(document).ready(function () {
        //展示所有
        findUsers("");
    })
    // 点击搜索按钮
    $("#findByNameBtn").click(function () {
        var name = $("#name").val();
        //如果name为空name默认查询所有
        findUsers(name)
    })

    // 根据名字模糊搜索
    function findUsers(name) {
        $("#userList tbody").empty()
        $.ajax({
            url: "${APP_PATH}/findUsers",
            type: "GET",
            data: {name: name},
            dataType: "json",
            success: function (result) {
                $.each(result, function () {
                    var id = $("<td></td>").append(this.id)
                    var name = $("<td></td>").append(this.name)
                    var code = $("<td></td>").append(this.code)
                    var password = $("<td></td>").append(this.password)
                    //删除
                    var delBtn = $("<button></button>").addClass(
                        "btn btn-danger btn-sm delete_btn").append(
                        $("<span></span>")
                            .addClass("glyphicon glyphicon-trash")).append(
                        "删除");
                    //为删除按钮添加属性表示id
                    delBtn.attr({"type": "button"});//防止删除自动刷新界面
                    delBtn.attr("id", this.id);
                    //修改
                    var modifyBtn = $("<button></button>").addClass(
                        "btn btn-primary btn-sm modify_btn").append(
                        $("<span></span>")
                            .addClass("glyphicon glyphicon-pencil")).append(
                        "修改");
                    //为修改按钮添加属性表示id
                    modifyBtn.attr({"type": "button"});//防止修改自动刷新界面
                    modifyBtn.attr("id", this.id);

                    var btns = $("<td></td>").append(delBtn).append(modifyBtn)
                    $("<tr></tr>")
                        .append(id)
                        .append(name)
                        .append(code)
                        .append(password)
                        .append(btns)
                        .appendTo("#userList tbody")
                })
            }
        })
    }

    /**
     * 删除按钮
     */
    $(document).on('click', ".delete_btn", function () {
        var id = $(this).attr("id");
        $.ajax({
            url: "${APP_PATH}/deleteUser",
            type: "POST",
            data: {id: id},
            dataType: "json",
            success: function (result) {
                //加载
                findUsers("")
                if (result[0]) {
                    alert("删除成功！")
                } else {
                    alert("删除失败...")
                }
            }
        })
    })

    //展示模态框
    function showModal(userId, userName, userCode, userPassword) {
        //先清空
        $("#userName").val(userName)
        $("#userCode").val(userCode)
        $("#userPassword").val(userPassword)
        $("#userName").attr("userId", userId)
        $("#userModel").modal("show")
    }

    /**
     * 新增按钮
     */
    $("#addUserBtn").click(function () {
        showModal("", "", "", "")

    })
    /**
     * 修改按钮
     */
    $(document).on("click", ".modify_btn", function () {
        var id = $(this).parents("tr").find("td:eq(0)").text();
        var name = $(this).parents("tr").find("td:eq(1)").text();
        var code = $(this).parents("tr").find("td:eq(2)").text();
        var password = $(this).parents("tr").find("td:eq(3)").text();
        showModal(id, name, code, password)
    })


    $("#modifyOrCreateUser").click(function () {
        var userName = $("#userName").val();
        var userCode = $("#userCode").val();
        var userPassword = $("#userPassword").val();
        var userId = $("#userName").attr("userId");
        if (userId == "") {
            //新增
            addUser(userName, userCode, userPassword)
        } else {
            //修改
            modifyUser(userId, userName, userCode, userPassword)
        }
    })

    // 新加
    function addUser(userName, userCode, userPassword) {
        if (userName != "" && userCode != "" && userPassword != "") {
            $.ajax({
                url: "${APP_PATH}/addUser",
                type: "POST",
                data: {userName: userName, userCode: userCode, userPassword: userPassword},
                dataType: "json",
                success: function (result) {
                    findUsers("");
                    $("#userModel").modal("hide")
                }
            })
        } else {
            alert("请完善信息")
        }

    }

    // 修改
    function modifyUser(userId, userName, userCode, userPassword) {
        if (userName != "" && userCode != "" && userPassword != "") {
            $.ajax({
                url: "${APP_PATH}/modifyUser",
                type: "POST",
                data: {userId: userId, userName: userName, userCode: userCode, userPassword: userPassword},
                dataType: "json",
                success: function (result) {
                    findUsers("");
                    $("#userModel").modal("hide")
                }
            })
        } else {
            alert("请完善信息")
        }
    }

</script>