<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>中新环球投资有限公司</title>
    <link rel="中新环球投资有限公司" href="/image/favicon.ico" />
    <script src="<s:url value="/webjs/jquery-1.11.1.js"/>"></script>
    <link href="<s:url value="/css/cms.css"/>" rel="stylesheet">
    <script>
        function fwcontext(navigationid1,navigationid2,mesgid){
            window.location.href='/cms/fwcontext?navigationid1='+navigationid1+'&navigationid2='+navigationid2+"&mesgid="+mesgid;
        }
    </script>
</head>
<body>
    <jsp:include page="top.jsp"/>
      <div class="hotproduct">
          <div class="hot-data">
              <c:forEach items="${hotPproductList}" var="hot" varStatus="i">
                  <c:if test="${i.first}">
                      <div class="three-div"><span style="font-size: 16px;font-weight: bold;">热门产品</span><a href="/cms/fwsimple?navigationid1=${hot.navigationid1}&navigationid2=${hot.navigationid2}" style="float: right;">更多</a></div>
                      <div class="first-div" onclick="fwcontext(${hot.navigationid1},${hot.navigationid2},${hot.mesgid})">
                          <img src="/view/getImage?navigationid1=${hot.navigationid1}&navigationid2=${hot.navigationid2}&mesgid=${hot.mesgid}" style="width: 130px;height: 80px;float: left;">
                          <div style="float: left;width: calc(100% - 135px);width: -moz-calc(100% - 135px);height: 80px;margin: 0 0 0 4px;">
                              <span style="overflow: hidden;text-overflow: ellipsis;white-space: nowrap;float: left;width: 100%;height: 30px;font-size: 14px;font-weight: bold;">${hot.mtitle}</span>
                              <div style="float: left;width: 100%;height: 50px;text-indent: 2em;overflow:hidden;">${hot.elliptical}</div>
                          </div>
                      </div>
                  </c:if>
                  <c:if test="${!i.first}">
                      <div class="other-div" onclick="fwcontext(${hot.navigationid1},${hot.navigationid2},${hot.mesgid})">
                          ${hot.mtitle}<span style="float:right;"><fmt:formatDate value="${hot.createtime}" pattern="yyyy-MM-dd" type="date"/></span>
                      </div>
                  </c:if>
              </c:forEach>
          </div>
          <div class="hot-data">
              <c:forEach items="${hotPnesList}" var="hot" varStatus="i">
                  <c:if test="${i.first}">
                      <div class="three-div"><span style="font-size: 16px;font-weight: bold;">新闻中心</span><a href="/cms/fwsimple?navigationid1=3&navigationid2=7" style="float: right;">更多</a></div>
                      <div class="first-div" onclick="fwcontext(${hot.navigationid1},${hot.navigationid2},${hot.mesgid})">
                          <img src="/view/getImage?navigationid1=${hot.navigationid1}&navigationid2=${hot.navigationid2}&mesgid=${hot.mesgid}" style="width: 130px;height: 80px;float: left;">
                          <div style="float: left;width: calc(100% - 135px);width: -moz-calc(100% - 135px);height: 80px;margin: 0 0 0 4px;">
                              <span style="overflow: hidden;text-overflow: ellipsis;white-space: nowrap;float: left;width: 100%;height: 30px;font-size: 14px;font-weight: bold;">${hot.mtitle}</span>
                              <div style="float: left;width: 100%;height: 50px;text-indent: 2em;overflow:hidden;">${hot.elliptical}</div>
                          </div>
                      </div>
                  </c:if>
                  <c:if test="${!i.first}">
                      <div class="other-div" onclick="fwcontext(${hot.navigationid1},${hot.navigationid2},${hot.mesgid})">
                              ${hot.mtitle}<span style="float:right;"><fmt:formatDate value="${hot.createtime}" pattern="yyyy-MM-dd" type="date"/></span>
                      </div>
                  </c:if>
              </c:forEach>
          </div>
          <div class="hot-data">
              <c:forEach items="${hotPnoticeList}" var="hot" varStatus="i">
                  <c:if test="${i.first}">
                      <div class="three-div"><span style="font-size: 16px;font-weight: bold;">公司公告</span><a href="/cms/fwsimple?navigationid1=3&navigationid2=6" style="float: right;">更多</a></div>
                      <div class="first-div" onclick="fwcontext(${hot.navigationid1},${hot.navigationid2},${hot.mesgid})">
                          <img src="/view/getImage?navigationid1=${hot.navigationid1}&navigationid2=${hot.navigationid2}&mesgid=${hot.mesgid}" style="width: 130px;height: 80px;float: left;">
                          <div style="float: left;width: calc(100% - 135px);width: -moz-calc(100% - 135px);height: 80px;margin: 0 0 0 4px;">
                              <span style="overflow: hidden;text-overflow: ellipsis;white-space: nowrap;float: left;width: 100%;height: 30px;font-size: 14px;font-weight: bold;">${hot.mtitle}</span>
                              <div style="float: left;width: 100%;height: 50px;text-indent: 2em;overflow:hidden;">${hot.elliptical}</div>
                          </div>
                      </div>
                  </c:if>
                  <c:if test="${!i.first}">
                      <div class="other-div" onclick="fwcontext(${hot.navigationid1},${hot.navigationid2},${hot.mesgid})">
                              ${hot.mtitle}<span style="float:right;"><fmt:formatDate value="${hot.createtime}" pattern="yyyy-MM-dd" type="date"/></span>
                      </div>
                  </c:if>
              </c:forEach>
          </div>
      </div>
    <div class="cooperative">
      合作伙伴
    </div>
    <div class="cooppic">
        <marquee behavior="scroll" direction="left" onMouseOut="this.start();" onMouseOver="this.stop();">
            <c:forEach items="${list}" var="data">
                <img src="/cms/getImage?mesgid=${data}" width="200px" height="150px">
            </c:forEach>
        </marquee>
    </div>
    <jsp:include page="bottom.jsp"/>
</body>
</html>
