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
        function updateDataPage(){
            window.location.href="/view/admin/index/getUpdateMenuConfigPage?nodetext=${bean.nodetext}";
        }
    </script>
</head>
<body>
<div class="right-main-context">
    <div class="right-dy-data">
        <div class="right-cond-div">
            <div class="title"><h4>${bean.nodetext}</h4></div>
        </div>
        <div class="right-inblock-div">
            <div class="account-div">
                <c:forEach items="${MenuList}" var="first">
                    <div class="block-account">
                        <div class="first-level">${first.nodetext}</div>
                        <c:forEach items="${first.sonMenus}" var="son">
                            <div class="second-level">${son.nodetext}</div>
                            <div class="second-level">${son.path}</div>
                        </c:forEach>
                    </div>
                </c:forEach>
            </div>
        </div>
        <input type="hidden" name="menuids">
        <div class="right-btn">
            <button class="btn btn-default" onclick="updateDataPage('')" type="button">修改</button>
        </div>
    </div>
</div>
</body>
</html>