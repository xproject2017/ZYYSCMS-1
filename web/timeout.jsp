<%@ page import="com.cms.util.ResourceUtil" %>
<!DOCTYPE HTML>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<html>
    <head>
        <title><%=ResourceUtil.getSystem("index.title")%></title>
        <link rel="中新环球投资有限公司" href="/image/favicon.ico" />
        <link href="<s:url value="/css/bootstrap.min.css"/>" rel="stylesheet" media="screen">
        <script>
            function goLogin(){
                top.window.location="/index.jsp";
            }
        </script>
    </head>
    <body>
    <div style="float:left;width: 100%;text-align: center;height: 500px;line-height: 500px;font-size: 14px;">
        <div style="float:left;width: 100%;height: 100px;">系统超时，请稍后再试</div>
        <div style="float:left;width: 100%;height: 30px;"><input type="button" class="btn btn-default" value="返回登录" onclick="goLogin()" /></div>
    </div>
    </body>
</html>