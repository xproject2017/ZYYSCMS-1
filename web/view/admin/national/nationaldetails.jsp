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
    <script src="<s:url value="/webjs/system/national/national.js"/>"></script>
    <script src="<s:url value="/webjs/jalert.js"/>"></script>
</head>
<body>
<div class="right-main-context">
    <div class="right-dy-data">
        <div class="right-cond-div">
            <div class="title"><h4>账户详情</h4></div>
        </div>
        <div class="right-inblock-div">
            <div class="title">基本信息<img src="/image/up.png" width="20" height="20" onclick="toggleData(this,1)"/></div>
            <div class="cell"><label>姓名：</label>${users.uname}</div>
            <div class="cell"><label>账户：</label>${users.username}</div>
            <div class="cell"><label>密码：</label>****** &nbsp;<button type="button" class="btn btn-default btn-sm" onclick="pwdReset(${users.userid},'${users.savedateTimeStr}')">重置</button></div>
            <div class="splitline">&nbsp;</div>
            <div class="cell"><label>成员角色：</label>${users.rolenames}</div>
            <div class="cell"><label>手机号：</label>${users.usertelephone}</div>
            <div class="cell"><label>状态：</label>${users.ustatusStr}</div>
            </div>
        <div class="right-btn">
            <button class="btn btn-default" onclick="gotoUpdateNationalPage(${users.userid},${users.roleid})" type="button">修改</button>
            <button class="btn btn-default" onclick="gotoNationalListPage()" style="margin-left: 20px;" type="button">返回</button>
        </div>
    </div>
</div>
</body>
</html>