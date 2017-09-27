<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<div class="showmenu">
    <c:forEach var="data" items="${result}">
        <c:if test="${data.nodecode!='010000'}">
            <div class="show-four">
                <a href="${data.path}?navigationid1=${data.navigationid1}&navigationid2=${data.navigationid2}">${data.nodetext}</a>
                <c:forEach var="son" items="${data.sonMenus}" varStatus="i">
                    <a href="${son.path}?navigationid1=${son.navigationid1}&navigationid2=${son.navigationid2}">${son.nodetext}</a>
                </c:forEach>
            </div>
        </c:if>
    </c:forEach>
</div>
<div style="float:left;width:100%;text-align: center;height: 80px;padding:20px 0;">
    CopyRight©2014 cnzxhq.com All right reserved 中新环球投资有限公司 版权所有
    <br/><br/>
    浙ICP备12345678号
    <br/>
    <br/>
</div>
</body>
</html>
