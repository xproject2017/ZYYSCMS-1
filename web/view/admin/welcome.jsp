<%@ page import="com.cms.util.ResourceUtil" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String title = ResourceUtil.getSystem("index.title");
%>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8">
    <title><%=title%></title>
    <link rel="stylesheet" href="<s:url value="/css/layout.css"/>"/>
    <link rel="stylesheet" href="<s:url value="/css/base.css"/>"/>
    <link href="<s:url value="/css/bootstrap.min.css"/>" rel="stylesheet" media="screen">
    <script src="<s:url value="/webjs/jquery-1.11.1.js"/>"></script>
    <script src="<s:url value="/webjs/base.js"/>"></script>
    <script src="<s:url value="/webjs/jalert.js"/>"></script>
    <script src="<s:url value="/webjs/pop.js"/>"></script>
    <script src="<s:url value="/webjs/updatepwd.js"/>"></script>
    <script src="<s:url value="/webjs/jQuery.md5.js"/>"></script>

    <%--表单验证--%>
    <link rel="stylesheet" href="<s:url value="/css/validationEngine.jquery.css"/>"/>
    <script src="<s:url value="/webjs/validationEngine/jquery.validationEngine-zh_CN.js"/>"></script>
    <script src="<s:url value="/webjs/validationEngine/jquery.validationEngine.js"/>"></script>

    <script>
        var g_userid = "${userinfo.userid}";
    </script>
</head>
<body>
<div class='header'>
    <div class="first">
        <%--<img src="/image/cmslogo.png" style="width: 100px;height: 40px;">--%>
        <%=title%>
        <b onclick="javascript:exitPopTip()">退出</b>
        <span class="userspan" onclick="getUserinfo()">${userinfo.username}</span>
    </div>
    <div class="second">
        <ul>
            <c:forEach items="${level}" var="map" varStatus="i">
                <li <c:if test="${i.first}">class="active"</c:if> data-platform="${map.key}"><a href="#">${map.value}</a></li>
            </c:forEach>
        </ul>
    </div>
</div>
<div class="iframeWrap">
    <!--菜单-->
    <div class="iframeLeft">
        <div class="zindex-div">&nbsp;</div>
        <div class="foldNav"></div>
    </div>
    <!--内容-->
    <div class="iframeRight">
        <iframe id="rightContext" name="iframe" src="" frameborder="0"></iframe>
    </div>
</div>
<div class="boby-bottom"><%=title%></div>
<script type="text/javascript">
    setMenu();
</script>
</body>
</html>