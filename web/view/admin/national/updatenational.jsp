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
    <link href="<s:url value="/css/national.css"/>" rel="stylesheet" media="screen">
    <script src="<s:url value="/webjs/jquery-1.11.1.js"/>"></script>
    <script src="<s:url value="/webjs/bootstrap.min.js"/>"></script>
    <script src="<s:url value="/webjs/system/national/national.js"/>"></script>
    <script src="<s:url value="/webjs/base.js"/>"></script>

    <script src="<s:url value="/webjs/jalert.js"/>"></script>
    <%--表单验证--%>
    <link rel="stylesheet" href="<s:url value="/css/validationEngine.jquery.css"/>"/>
    <script src="<s:url value="/webjs/validationEngine/jquery.validationEngine-zh_CN.js"/>"></script>
    <script src="<s:url value="/webjs/validationEngine/jquery.validationEngine.js"/>"></script>
    <script>
        $(function () {
            $("#national_form").validationEngine("attach", {
                'promptPosition': 'topLeft',
                autoHidePrompt: true,
                autoHideDelay: 3000
            });
        });
    </script>
</head>
<body>
<div class="right-main-context">
    <div class="right-dy-data">
        <div class="right-cond-div">
            <div class="title"><h4>账户详情</h4></div>
        </div>
        <form class="form-inline" role="form" id="national_form">
            <div class="right-inblock-div" id="basic">
                <div class="title">基本信息</div>
                <div class="cell"><label>姓名：</label><input type="text" validrule="validate[required,maxSize[20]]" name="uname" value="${users.uname}"></div>
                <div class="cell"><label>账户：</label>${users.username}</div>
                <div class="cell"><label>密码：</label>******</div>
                <div class="splitline">&nbsp;</div>
                <div class="cell"><label>成员角色：</label>
                    <select name="roleid">
                        <c:forEach items="${roleList}" var="item">
                            <option value="${item.roleid}" <c:if test="${users.roleid eq item.roleid}">selected="selected" </c:if>>${item.rolename}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="cell"><label>手机号：</label><input type="text" validrule="validate[required,custom[phone]]" name="usertelephone" value="${users.usertelephone}"></div>
                <div class="cell"><label>状态：</label>
                    <select name="ustatus">
                        <option value="1" <c:if test="${1 eq users.ustatus}">selected</c:if> >正常</option>
                        <option value="0" <c:if test="${0 eq users.ustatus}">selected</c:if> >禁用</option>
                    </select>
                </div>
            </div>
            <input type="hidden" name="savedateTimeStr" value="${users.savedateTimeStr}">
            <input type="hidden" name="userid" value="${users.userid}">
            <div class="right-btn">
                <button class="btn btn-default" onclick="validFormData(${users.userid})" type="button">保存</button>
                <button class="btn btn-default" onclick="gotoNationalDetailPage(${users.userid},${users.roleid},1)" style="margin-left: 20px;" type="button">取消</button>
            </div>
        </form>
    </div>
</div>
</body>
</html>