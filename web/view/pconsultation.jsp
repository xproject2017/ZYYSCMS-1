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
    <script src="<s:url value="/webjs/extendPagination.js"/>"></script>
    <script src="<s:url value="/webjs/cms.js"/>"></script>
    <script src="<s:url value="/webjs/pop.js"/>"></script>
    <script src="<s:url value="/webjs/jalert.js"/>"></script>

    <link href="<s:url value="/css/bootstrap.min.css"/>" rel="stylesheet" media="screen">

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
    <script>
        var _nodetext = "${nodetext}";
    </script>
</head>
<body onload="callConsultationPagin(1,0)">
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
                  <c:if test="${null != cmsinfo}">
                      <div class="add-btn"><input type="button" value="新增咨询" onclick="getConsultationPage()"/></div>
                  </c:if>
                  <div id="callBackPagination">
                      <div class="list-title">
                          <div class="cell-data">咨询内容</div>
                          <div class="cell-data">咨询时间</div>
                          <div class="cell-data">处理状态</div>
                      </div>
                      <div class='list-context'></div>
                      <div id="callBackPager"></div>
                  </div>
              </div>
          </div>
      </div>
    <jsp:include page="bottom.jsp"/>
</body>
</html>
