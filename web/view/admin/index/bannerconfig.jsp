<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <meta charset="UTF-8">
  <title></title>
  <link href="<s:url value="/css/bootstrap.min.css"/>" rel="stylesheet" media="screen">
  <link href="<s:url value="/css/base.css"/>" rel="stylesheet" media="screen">
  <script src="<s:url value="/webjs/jquery-1.11.1.js"/>"></script>
  <script src="<s:url value="/webjs/bootstrap.min.js"/>"></script>
  <script src="<s:url value="/webjs/extendPagination.js"/>"></script>
  <script src="<s:url value="/webjs/apply/apply.js"/>"></script>
  <script src="<s:url value="/webjs/jalert.js"/>"></script>
  <script>
      function bannerconfig(_nav1,_nav2,_msgid,_mbanner,savetimestr){
        $.post("/view/admin/index/setbanner",{"navigationid1":_nav1,"navigationid2":_nav2,"mesgid":_msgid,"mbanner":_mbanner,"savetimestr":savetimestr},function(data){
          if(data.code==0){
              jAlert("设置成功","",function(r){
                if(r){
                  location.reload();
                }
            });
          }else{
            jAlert("设置失败");
          }
        });
      }
  </script>
</head>
<body>
<div class="right-main-context">
  <div class="right-dy-data">
    <div class="right-cond-div">
      <div class="title"><h4>${bean.nodetext}</h4></div>
    </div>
    <div id="callBackPagination">
      <div id="mainContent">
        <div class="list-table">
          <div class="list-th">
            <div class="plan-cell">标题</div>
            <div class="plan-cell">关键字</div>
            <div class="plan-cell">是否上大banner条</div>
            <div class="plan-cell">菜单分类</div>
            <div class="plan-cell">设置</div>
          </div>
          <div class='list-context'>
            <c:forEach var="data" items="${list}">
              <div class="list-tr">
                <div class="plan-cell">${data.mtitle}</div>
                <div class="plan-cell">${data.mtags}</div>
                <div class="plan-cell">${data.mbannerstr}</div>
                <div class="plan-cell">${data.nodetext}</div>
                <div class="plan-cell">
                  <c:if test="${data.mbanner eq 0}">
                    <a href="javascript:bannerconfig(${data.navigationid1},${data.navigationid2},${data.mesgid},1,${data.savetimestr})">上banner条</a>
                  </c:if>
                  <c:if test="${data.mbanner eq 1}">
                    <a href="javascript:bannerconfig(${data.navigationid1},${data.navigationid2},${data.mesgid},0,${data.savetimestr})">不上banner条</a>
                  </c:if>
                </div>
              </div>
            </c:forEach>
          </div>
        </div>
      </div>
      <div id="callBackPager"></div>
    </div>
  </div>
</div>
</body>
</html>
