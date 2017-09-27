<%@ page import="com.cms.util.ResourceUtil" %>
<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2016/2/24
  Time: 9:00
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<% String title = ResourceUtil.getSystem("index.title");%>
<html>
<head>
  <meta charset="utf-8">
  <title><%=title%></title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="中新环球投资有限公司" href="/image/favicon.ico" />
  <link href="<s:url value="/css/adminlogin.css"/>" rel="stylesheet">
  <link href="<s:url value="/css/bootstrap.min.css"/>" rel="stylesheet" media="screen">
  <script src="<s:url value="/webjs/jquery-1.11.1.js"/>"></script>
  <script src="<s:url value="/webjs/jQuery.md5.js"/>"></script>
  <script src="<s:url value="/webjs/jquery.JPlaceholder.js"/>"></script>
  <script src="<s:url value="/webjs/login.js"/>"></script>
</head>
<body id="loginbg">
<div id="l-divbg">
  <div style="height: 270px;width: 100%;">&nbsp;</div>
  <div class="l-login-div">
    <input type="text" id="form-username"  placeholder="用户名" maxlength="16">
    <input type="password" id="form-password"  placeholder="密码" maxlength="16">
  </div>
  <div id="errormsg"></div>
  <div style="padding: 0 68px;"><input type="button" id="loginbtn" class="loginbtn" value="登录" ></div>
</div>
</body>
</html>