<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>中新环球投资有限公司</title>
    <link rel="中新环球投资有限公司" href="/image/favicon.ico" />
    <script src="<s:url value="/webjs/jquery-1.11.1.js"/>"></script>
    <link href="<s:url value="/css/cms.css"/>" rel="stylesheet">
</head>
<body>
    <jsp:include page="top.jsp"/>
      <div class="middle-context">
          <div class="left-div">
              <div id="title-menu">${leftmenu.get(0).nodetext}</div>
              <ul>
                  <c:forEach var="data" items="${leftmenu}" varStatus="i">
                      <c:if test="${!i.first}">
                            <li <c:if test="${data.nodetext eq nodetext}">class="activeA"</c:if> ><a href="${data.path}?navigationid1=${data.navigationid1}&navigationid2=${data.navigationid2}&nodetext=${data.nodetext}">${data.nodetext}<span> > </span></a></li>
                      </c:if>
                  </c:forEach>
                  <div class="menu-bottom">&nbsp;</div>
              </ul>
          </div>

          <div class="right-div">
              <div class="content-main-top">
                  <div class="right-content-main-title">${nodetext}</div>
                  <div class="content-main-nav">
                      <a href="/cms/fwindex"> 首页 ></a>
                      <a href="${leftmenu.get(1).path}?navigationid1=${leftmenu.get(1).navigationid1}&navigationid2=${leftmenu.get(1).navigationid2}&nodetext=${leftmenu.get(1).nodetext}"> ${leftmenu.get(0).nodetext}></a>
                      <span>${nodetext}</span>
                  </div>
              </div>
              <div class="about-main">
                  <c:choose>
                      <c:when test="${code==0}">
                          ${data.mes}
                      </c:when>
                      <c:otherwise>
                          暂无数据
                      </c:otherwise>
                  </c:choose>
              </div>
          </div>
      </div>
    <jsp:include page="bottom.jsp"/>
</body>
</html>
