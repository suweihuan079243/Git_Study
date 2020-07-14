
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%pageContext.setAttribute("APP_PATH", request.getContextPath());%>
<!DOCTYPE html>
<script type="text/javascript" src="${APP_PATH}/static/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="${APP_PATH}/static/bootstrap-3.3.7-dist/js/bootstrap.js"></script>
<link rel="stylesheet" type="text/css" href="${APP_PATH}/static/bootstrap-3.3.7-dist/css/bootstrap.css"/>
<link rel="stylesheet" type="text/css" href="${APP_PATH}/static/bootstrap-3.3.7-dist/css/bootstrap-theme.css"/>
<html lang="en">
<head>
    <title>登录</title>
</head>
<body>

<form class="container">
    <div class="modal-dialog" style="margin-top: 10%;">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title text-center" id="myModalLabel">登录</h4>
            </div>
            <div class="modal-body" id="model-body">
                <div class="row" style="margin-left: 200px">
                    <div class="form-group col-xs-6">
                        <input type="text" class="form-control" placeholder="用户名" id="username">
                    </div>
                </div>
                <div class="row " style="margin-left: 200px">
                    <div class="form-group col-xs-6">
                        <input type="password" class="form-control" placeholder="密码" id="password">
                    </div>
                </div>
            </div>
            <div class="row ">
                <div class="modal-footer">
                    <div class="form-group col-xs-4" style="margin-left: 50px">
                        <button type="button" class="btn btn-primary form-control" id="loginButton">登录</button>
                    </div>
                    <div class="form-group col-xs-4" style="margin-left: 70px">
                        <button type="reset" class="btn btn-default form-control">清空</button>
                    </div>
                </div>
            </div>
        </div>
    </div><!-- /.modal -->
</form>


</body>
</html>
<%--下面写js脚本--%>
<script>
    $("#loginButton").click(function () {
        var username = $("#username").val();
        var password = $("#password").val();
        if (username.length == 0 || password.length == 0) {
            alert("请输入用户名或密码！");
        } else {
            $.ajax({
                url: "${APP_PATH}/login",
                type: "POST",
                data: {username: username, password: password},
                dataType: "json",
                success: function (result) {
                    if (result.message == 1) {
                        window.location.href = "index.jsp?username=" + username
                    } else {
                        alert("用户名或密码错误！")
                    }
                }

            })
        }

    })

</script>