<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
    <link href="<s:url value="/css/bootstrap.min.css"/>" rel="stylesheet" media="screen">
    <link href="<s:url value="/css/base.css"/>" rel="stylesheet" media="screen">
    <link href="<s:url value="/css/national.css"/>" rel="stylesheet" media="screen">
    <script src="<s:url value="/webjs/jquery-1.11.1.js"/>"></script>
    <script src="<s:url value="/webjs/bootstrap.min.js"/>"></script>
    <script src="<s:url value="/webjs/system/roles/roles.js"/>"></script>
    <script src="<s:url value="/webjs/base.js"/>"></script>
    <script src="<s:url value="/webjs/jalert.js"/>"></script>
    <%--表单验证--%>
    <link rel="stylesheet" href="<s:url value="/css/validationEngine.jquery.css"/>"/>
    <script src="<s:url value="/webjs/validationEngine/jquery.validationEngine-zh_CN.js"/>"></script>
    <script src="<s:url value="/webjs/validationEngine/jquery.validationEngine.js"/>"></script>
    <script>
        $(function () {
            $("#national_form").validationEngine("attach", {
                'promptPosition': 'topLeft',
                autoHidePrompt: true,
                autoHideDelay: 3000
            });
        });
    </script>
</head>
<body>
<div class="right-main-context">
    <div class="right-dy-data">
        <div class="right-cond-div">
            <div class="title"><h4>新增角色</h4></div>
        </div>
        <form class="form-inline" role="form" id="national_form">
            <div class="right-inblock-div" id="basic">
                <div class="title">基本信息</div>
                <div class="cell"><label>角色名称：</label><input type="text" validrule="validate[required,maxSize[20]]" name="rolename"></div>
            </div>
            <div class="right-inblock-div">
                <div class="title">权限</div>
                <div class="account-div">
                    <c:forEach items="${MenuList}" var="first">
                        <div class="block-account">
                            <div class="first-level"><input type="checkbox" value="${first.nodeid}" onclick="checkAllBox('checknetall${first.nodeid}','checknet${first.nodeid}')" id="checknetall${first.nodeid}">${first.nodetext}</div>
                            <c:forEach items="${first.sonMenus}" var="son">
                                <div class="second-level"><input type="checkbox" value="${son.nodeid}" name="checknet${first.nodeid}">${son.nodetext}</div>
                            </c:forEach>
                        </div>
                    </c:forEach>
                </div>
            </div>
            <input type="hidden" name="menuids">
            <div class="right-btn">
                <button class="btn btn-default" onclick="validFormData('')" type="button">保存</button>
                <button class="btn btn-default" onclick="gotoRolesListPage()" style="margin-left: 20px;" type="button">取消</button>
            </div>
        </form>
    </div>
</div>
</body>
</html>