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
  <link rel="中新环球投资有限公司" href="/image/favicon.ico" />
  <script src="<s:url value="/webjs/jquery-1.11.1.js"/>"></script>
  <script>
      window.location.href="/cms/fwindex";
  </script>
</head>
<body>

</body>
</html>