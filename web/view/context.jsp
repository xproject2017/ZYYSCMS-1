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
    <c:if test="${paramobj.navigationid2 eq 15}">
        <script src="<s:url value="/webjs/cms.js"/>"></script>
        <script src="<s:url value="/webjs/pop.js"/>"></script>
        <script src="<s:url value="/webjs/jalert.js"/>"></script>
        <!-- 日历控件引用 -->
        <link rel="stylesheet" href="<s:url value="/webjs/jquery-ui/themes/smoothness/jquery-ui.min.css"/>"/>
        <link rel="stylesheet" href="<s:url value="/webjs/jquery-ui/themes/base/all.css"/>">
        <script src="<s:url value="/webjs/jquery-ui/ui/datepicker.js"/>"></script>
        <script src="<s:url value="/webjs/jquery-ui/ui/timepicker.js"/>"></script>
        <script src="<s:url value="/webjs/jquery-ui/ui/i18n/datepicker-zh-CN.js"/>"></script>
        <%--表单验证--%>
        <link rel="stylesheet" href="<s:url value="/css/validationEngine.jquery.css"/>"/>
        <script src="<s:url value="/webjs/validationEngine/jquery.validationEngine-zh_CN.js"/>"></script>
        <script src="<s:url value="/webjs/validationEngine/jquery.validationEngine.js"/>"></script>
    </c:if>

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
                  <div style="float: left;width: 100%;text-align: center;font-weight: bold;height: 50px;line-height: 50px;">${data.mtitle}</div>
                  <c:choose>
                      <c:when test="${code==0}">
                          ${data.mes}
                      </c:when>
                      <c:otherwise>
                          暂无数据
                      </c:otherwise>
                  </c:choose>
                  <c:if test="${paramobj.navigationid2 eq 15}">
                      <div class="add-btn"><input type="button" value="我要应聘" onclick="getApplyPage()"/></div>
                  </c:if>
              </div>
              <div class="page-div">
                  <div class="pre-next">上一条：<a href="/cms/fwcontext?navigationid1=${data.ngid1}&navigationid2=${data.ngid2}&mesgid=${data.prev_id}">${data.prev_title}</a>
                      <c:if test="${data.prev_title == null}">
                          没有啦
                      </c:if>
                  </div>
                  <div class="pre-next">
                      下一条：<a href="/cms/fwcontext?navigationid1=${data.ngid1}&navigationid2=${data.ngid2}&mesgid=${data.next_id}">${data.next_title}</a>
                      <c:if test="${data.next_title == null}">
                            没有啦
                      </c:if>
                  </div>
              </div>
          </div>
      </div>
    <jsp:include page="bottom.jsp"/>
</body>
</html>
