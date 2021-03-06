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
    <script src="<s:url value="/webjs/jquery-1.11.1.js"/>"></script>
    <script src="<s:url value="/webjs/bootstrap.min.js"/>"></script>
    <script src="<s:url value="/webjs/extendPagination.js"/>"></script>
    <script src="<s:url value="/webjs/pmultiplemsg/pmultiplemsg.js"/>"></script>
    <script src="<s:url value="/webjs/jalert.js"/>"></script>
    <script>
        $(function(){
            callBackPagination(1,0);
        });
    </script>
</head>
<body>
<div class="right-main-context">
    <script>
        $(".right-main-context").hide();
        addBgDiv("读取中...");
    </script>
    <div class="right-dy-data">
        <div class="right-cond-div">
            <div class="title"><h4>${bean.nodetext}</h4></div>
            <div class="right"><input class="add-btn" type="button" onclick="getAddDataPage()" value=" 新增数据"></div>
        </div>
        <div id="callBackPagination">
            <div id="mainContent">
                <div class="list-table">
                    <div class="list-th">
                        <c:choose>
                            <c:when test="${bean.navigationid2==1 || bean.navigationid2==14}">
                                <div class="uneed">标题</div>
                                <div class="uneed">关键字</div>
                                <div class="uneed">操作</div>
                            </c:when>
                            <c:when test="${bean.navigationid2==2 || bean.navigationid2==3 || bean.navigationid2==4}">
                                <div class="needbanner">标题</div>
                                <div class="needbanner">关键字</div>
                                <div class="needbanner">是否上大banner条</div>
                                <div class="needbanner">操作</div>
                            </c:when>
                            <c:otherwise>
                                <div class="plan-cell">标题</div>
                                <div class="plan-cell">关键字</div>
                                <div class="plan-cell">是否上大banner条</div>
                                <div class="plan-cell">是否上新闻中心</div>
                                <div class="plan-cell">操作</div>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class='list-context'></div>
                </div>
            </div>
            <div id="callBackPager"></div>
        </div>
    </div>
    <input type="hidden" name="navigationid1" value="${bean.navigationid1}"/>
    <input type="hidden" name="navigationid2" value="${bean.navigationid2}"/>
</div>
</body>
</html>