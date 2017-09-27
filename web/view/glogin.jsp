<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>中新环球投资有限公司</title>
    <link rel="中新环球投资有限公司" href="/image/favicon.ico" />
    <script src="<s:url value="/webjs/jquery-1.11.1.js"/>"></script>
    <link href="<s:url value="/css/cms.css"/>" rel="stylesheet">
    <script src="<s:url value="/webjs/jQuery.md5.js"/>"></script>
    <script src="<s:url value="/webjs/cms.js"/>"></script>
    <script>
        $(function(){
            $("#menulist li").hover(function(){
                $(this).find(".nav-scroll").show();
                $(this).siblings().find(".nav-scroll").hide();
            },function(){
                $(this).find(".nav-scroll").slideUp();
                $(this).find(".nav-scroll").hide();
            });
        });
    </script>
</head>
<body>
    <div class="header">
        <div class="base-reg">
            <span style="font-size: 18px;line-height: 55px;">财富热线：</span>
            <span style="font-size: 18px;margin-right: 20px;line-height: 55px;">400000000</span>
            <a href="/view/glogin.jsp">登录</a>
            <span style="margin: 0 10px;">|</span>
            <a href="/view/gregister.jsp">注册</a>
            <input type="text" class="search-input" placeholder="站内信息搜索"/>
            <input type="button" value="&nbsp;&nbsp;&nbsp;" class="iconsearch">
        </div>
        <div class="menu">
            <div style="float: left;width: 600px;text-align: center;height: 100%;margin: -40px 0 0 0;">
                <img src="/image/cmslogo.png" style="width: 221px;height: 65px;">
            </div>
            <ul id="menulist">
                <c:forEach var="data" items="${result}">
                    <li><a href="${data.path}?navigationid1=${data.navigationid1}&navigationid2=${data.navigationid2}&nodetext=${data.nodetext}">${data.nodetext}</a>
                        <c:forEach var="son" items="${data.sonMenus}" varStatus="i">
                            <c:if test="${i.first}">
                                <div class="nav-scroll">
                                <div class="nav-top">&nbsp;</div>
                                <div class="nav-secmen">
                            </c:if>
                            <a href="${son.path}?navigationid1=${son.navigationid1}&navigationid2=${son.navigationid2}&nodetext=${son.nodetext}">${son.nodetext}</a>
                            <c:if test="${i.last}">
                                </div>
                                </div>
                            </c:if>
                        </c:forEach>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
    <div class="middle-context">
        <div class="login-title">
            <h1>会员登录</h1>
            <a href="/view/gregister.jsp">免费注册</a>
        </div>
        <div class="login-content">
            <form id="user_form" method="post">
                <div class="cell"><label>用户名<span>*</span>：</label><input type="text" id="funame" maxlength="16"/></div>
                <div class="cell"><label>密码<span>*</span>：</label><input type="password" id="fpword" maxlength="12"/></div>
                <div id="tips">&nbsp;</div>
                <div class="cell"><input type="button" value="登 录" onclick="loginValidate()"/></div>
            </form>
        </div>
    </div>
    <jsp:include page="bottom.jsp"/>
</body>
</html>
