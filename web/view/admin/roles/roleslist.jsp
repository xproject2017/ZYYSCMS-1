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
    <script src="<s:url value="/webjs/jquery-1.11.1.js"/>"></script>
    <script src="<s:url value="/webjs/bootstrap.min.js"/>"></script>
    <script src="<s:url value="/webjs/extendPagination.js"/>"></script>
    <script src="<s:url value="/webjs/system/roles/roles.js"/>"></script>
    <script src="<s:url value="/webjs/jalert.js"/>"></script>
</head>
<body onload="callBackPagination(1,0);">
<div class="right-main-context">
    <script>
        $(".right-main-context").hide();
        addBgDiv("读取中...");
    </script>
    <div class="right-dy-data">
        <div class="right-cond-div">
            <div class="title"><h4>${bean.nodetext}</h4></div>
            <%--<div class="searchinput">
                <div class="input-group">
                    <input type="text" class="iconsearch" id="cpname" maxlength="20">
                    <span class="input-group-btn"><button class="btn btn-default" type="button" onclick="callBackPagination(1,0)">搜索</button></span>
                </div>
            </div>--%>
            <c:if test="${userinfo.permissionflag=='111'}">
                <div class="right"><input class="add-btn" type="button" onclick="getAddRolesPage()" value=" 新增角色"></div>
            </c:if>
        </div>
        <div id="callBackPagination">
            <div id="mainContent">
                <div class="list-table">
                    <div class="list-th">
                        <div class="role-name">角色名称</div>
                        <div class="role-authority">权限</div>
                    </div>
                    <div class='list-context'></div>
                </div>
            </div>
            <div id="callBackPager"></div>
        </div>
    </div>
</div>
</body>
</html>