<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <script src="<s:url value="/webjs/base.js"/>"></script>
    <script src="<s:url value="/webjs/system/roles/roles.js"/>"></script>
    <script src="<s:url value="/webjs/jalert.js"/>"></script>
</head>
<body>
<div class="right-main-context">
    <div class="right-dy-data">
        <div class="right-cond-div">
            <div class="title"><h4>角色详情</h4></div>
        </div>
        <div class="right-inblock-div">
            <div class="title">基本信息<img src="/image/up.png" width="20" height="20" onclick="toggleData(this,1)"/></div>
            <div class="cell"><label>角色名称：</label>${Role.rolename}</div>
        </div>

        <div class="right-inblock-div">
            <div class="title">权限</div>
            <div class="account-div">
                <c:forEach items="${MenuList}" var="first">
                    <div class="block-account">
                        <div class="first-level"><input type="checkbox" value="${first.nodeid}" disabled <c:if test="${first.checked}">checked</c:if> >${first.nodetext}</div>
                        <c:forEach items="${first.sonMenus}" var="son">
                            <div class="second-level"><input type="checkbox" value="${son.nodeid}" disabled <c:if test="${son.checked}">checked</c:if>  name="checknet${first.nodecode}">${son.nodetext}</div>
                        </c:forEach>
                    </div>
                </c:forEach>
            </div>
        </div>

        <div class="right-btn">
            <c:if test="${userinfo.permissionflag=='111' && canModify}">
                <button class="btn btn-default" onclick="gotoUpdateRolesPage(${Role.roleid})" type="button">修改</button>
            </c:if>
            <button class="btn btn-default" onclick="gotoRolesListPage()" style="margin-left: 20px;" type="button">返回</button>
        </div>
    </div>
</div>
</body>
</html>