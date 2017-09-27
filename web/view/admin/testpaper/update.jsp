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

    <script charset="utf-8" src="<s:url value="/kindeditor/kindeditor.js"/>"></script>
    <script charset="utf-8" src="<s:url value="/kindeditor/lang/zh_CN.js"/>"></script>
    <script src="<s:url value="/webjs/testpaper/testpaper.js"/>"></script>
    <script>
        KindEditor.ready(function(K) {
            var editor = K.create('textarea[name="context"]', {
                resizeType : 1,
                allowPreviewEmoticons : false,
                allowImageUpload : true,
                uploadJson : '/kindeditor/jsp/upload_json.jsp',
                fileManagerJson : '/kindeditor/jsp/file_manager_json.jsp',
                items : [
                    'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
                    'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
                    'insertunorderedlist', '|', 'emoticons', 'image', 'link']
            });
        });
    </script>
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
                <div class="cell"><label>标题：</label><input type="text" validrule="validate[required,maxSize[20]]" name="mtitle" value="${bean.mtitle}"></div>
                <div class="cell"><label>是否上大banner条：</label><input type="radio" name="mbanner" value="0" <c:if test="${bean.mbanner eq 0}">checked</c:if> />否 <input type="radio" name="mbanner" value="1" <c:if test="${bean.mbanner eq 1}">checked</c:if>/>是 </div>
                <c:if test="${bean.navigationid2==9}">
                    <div class="cell"><label>是否上热门产品：</label><input type="radio" name="mmsgc" value="0" <c:if test="${bean.mmsgc eq 0}">checked</c:if>/>否 <input type="radio" name="mmsgc" value="1" <c:if test="${bean.mmsgc eq 1}">checked</c:if>/>是 </div>
                </c:if>
                <div class="cell"><label>关键字：</label><input type="text" validrule="validate[required,maxSize[120]]" name="mtags" placeholder="逗号分隔" value="${bean.mtags}"></div>
                <div class="cell"><label>展示图：</label><input type="file" name="fileimage" style="display: inline-block;" accept="image/*" ></div>
                <div class="cell"><a href="javascript:window.open('/view/getImage.action?navigationid1=${bean.navigationid1}&navigationid2=${bean.navigationid2}&mesgid=${bean.mesgid}')">点击查看原图</a></div>
                <div style="float: left;width: 90%;height: 40px;padding:5px 0 0 0;"><label>摘要信息：</label><input type="text" style="width: 600px;" validrule="validate[required,maxSize[100]]" name="elliptical"  value="${bean.elliptical}" placeholder="摘要信息最多100字"></div>
                <textarea name="context" style="width: 90%;height: 350px;">${bean.mes}</textarea>
            </div>
            <input type="hidden" name="mes" id="mes"/>
            <input type="hidden" name="navigationid1" value="${bean.navigationid1}"/>
            <input type="hidden" name="navigationid2" value="${bean.navigationid2}"/>
            <input type="hidden" name="mesgid" value="${bean.mesgid}"/>
            <input type="hidden" name="savetimestr" value="${bean.savetimestr}">
            <div class="right-btn">
                <button class="btn btn-default" onclick="updateFormData()" type="button">保存</button>
                <button class="btn btn-default" onclick="history.go(-1)" style="margin-left: 20px;" type="button">取消</button>
            </div>
        </form>
    </div>
</div>
</body>
</html>
