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
    <script src="<s:url value="/webjs/jquery.JPlaceholder.js"/>"></script>
    <script src="<s:url value="/webjs/bootstrap.min.js"/>"></script>
    <script src="<s:url value="/webjs/extendPagination.js"/>"></script>
    <script src="<s:url value="/webjs/vip/vip.js"/>"></script>
    <script src="<s:url value="/webjs/jalert.js"/>"></script>
    <script>
        $(function(){
            //不兼容placeholder属性处理
            JPlaceHolder.init();
            callBackPagination(1,0);
        });
    </script>
</head>
<body>
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
                    <input type="text" class="iconsearch" id="cpname" maxlength="20" placeholder="请输入姓名查询">
                    <span class="input-group-btn">
                        <select class="form-control" style="width:auto" id="queryFlag">
                            <option value="">全部</option>
                            <c:forEach items="${roleList}" var="item">
                                <option value="${item.roleid}">${item.rolename}</option>
                            </c:forEach>
                        </select>
                    </span>
                    <span class="input-group-btn"><button class="btn btn-default" type="button" onclick="callBackPagination(1,0)">搜索</button></span>
                </div>
            </div>--%>
            <c:if test="${userinfo.permissionflag=='111'}">
                <div class="right"><input class="add-btn" type="button" onclick="getAddNationalPage()" value=" 新增账户"></div>
            </c:if>
        </div>
        <div id="callBackPagination">
            <div id="mainContent">
                <div class="list-table">
                    <div class="list-th">
                        <div class="plan-cell">员工姓名</div>
                        <div class="plan-cell">登录名</div>
                        <div class="plan-cell">手机号</div>
                        <div class="plan-cell">状态</div>
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