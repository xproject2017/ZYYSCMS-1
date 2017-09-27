<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>项目管理系统</title>
    <link href="<s:url value="/css/bootstrap.min.css"/>" rel="stylesheet" media="screen">
    <link href="<s:url value="/css/base.css"/>" rel="stylesheet" media="screen">
    <link href="<s:url value="/css/national.css"/>" rel="stylesheet" media="screen">
    <script src="<s:url value="/webjs/jquery-1.11.1.js"/>"></script>
    <script src="<s:url value="/webjs/bootstrap.min.js"/>"></script>
    <script src="<s:url value="/webjs/base.js"/>"></script>
    <script src="<s:url value="/webjs/jalert.js"/>"></script>
    <%--表单验证--%>
    <link rel="stylesheet" href="<s:url value="/css/validationEngine.jquery.css"/>"/>
    <script src="<s:url value="/webjs/validationEngine/jquery.validationEngine-zh_CN.js"/>"></script>
    <script src="<s:url value="/webjs/validationEngine/jquery.validationEngine.js"/>"></script>

    <script src="<s:url value="/webjs/apply/apply.js"/>"></script>

</head>
<body onload="initData()">
<div class="right-main-context">
    <div class="right-dy-data">
        <div class="right-cond-div">
            <div class="title"><h4>${bean.nodetext}</h4></div>
        </div>
        <form role="form" class="form-inline" enctype="multipart/form-data" id="data_form">
            <div class="right-inblock-div">
                <div class="title">基本信息</div>
                <div class="cell"><label>应聘人：</label>${bean.applyname}</div>
                <div class="cell"><label>手机号：</label>${bean.applyphone}</div>
                <div class="cell"><label>应聘职位：</label>${bean.applyposition}</div>
                <div class="cell"><label>邮箱：</label>${bean.applyemail}</div>
                <div class="cell"><label>联系住址：</label>${bean.applyaddress}</div>
                <div class="cell"><label>性别：</label>${bean.genderstr}</div>
                <div class="cell"><label>出生年月：</label>${bean.birthday}</div>
                <div class="cell"><label>籍贯：</label>${bean.unative}</div>
                <div class="cell"><label>学历：</label>${bean.educationstr}</div>
                <div class="cell"><label>毕业学校：</label>${bean.graduateschool}</div>
                <div class="cell"><label>专业：</label>${bean.profession}</div>
                <div class="cell"><label>毕业时间：</label>${bean.graduatetime}</div>
                <div class="cell"><label>期望月薪：</label>${bean.expectedsalary}</div>
                <div class="cell"><label>处理状态：</label>${bean.flagstr}</div>
                <div class="cell"><a href="javascript:downloadFile(${bean.id})">查看简历</a></div>
            </div>
            <div class="right-inblock-div">
                备注：<textarea class="form-control" rows="3" style="width: 90%;resize: none;margin-top: 5px;" validrule="validate[required,maxSize[200]]" name="resmes">${bean.resmes}</textarea>
            </div>
            <input type="hidden" name="id" value="${bean.id}"/>
            <input type="hidden" name="savetimestr" value="${bean.savetimestr}">
            <div class="right-btn">
                <button class="btn btn-default" onclick="updateFormData()" type="button">保存</button>
                <button class="btn btn-default" onclick="history.go(-1)" style="margin-left: 20px;" type="button">取消</button>
            </div>
        </form>
    </div>
</div>
<iframe src="" id="download" width="1" height="1" style="display: none;"/>
</body>
</html>
