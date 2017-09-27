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
    <script charset="utf-8" src="<s:url value="/webjs/exercises/exercises.js"/>"></script>
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
                <div class="cell"><label>题目类型：</label><input type="radio" name="etype" value="1" <c:if test="${bean.etype eq 1}">checked</c:if>/>公司 <input type="radio" name="etype" value="2" <c:if test="${bean.etype eq 2}">checked</c:if>/>个人</div>
                <div style="float: left;width: 90%;height: 40px;padding:5px 0 0 0;"><label>题目：</label><input style="width: 600px;" type="text" validrule="validate[required,maxSize[100]]" name="etitle" value="${bean.etitle}"></div>
                <div class="cell" style="width: 70%;"><label>选项一：</label><input type="text" style="width: 60%;"  validrule="validate[required]" name="option1" value="${bean.option1}"></div>
                <div class="cell" style="width: 30%;"><label>分值一：</label><input type="text" style="width: 30%;" validrule="validate[required]" name="option1score" value="${bean.option1score}"></div>
                <div class="cell" style="width: 70%;"><label>选项二：</label><input type="text" style="width: 60%;" validrule="validate[required]" name="option2"  value="${bean.option2}"></div>
                <div class="cell" style="width: 30%;"><label>分值二：</label><input type="text" style="width: 30%;" validrule="validate[required]" name="option2score"  value="${bean.option2score}"> </div><br>
                <div class="cell" style="width: 70%;"><label>选项三：</label><input type="text" style="width: 60%;" validrule="validate[required]" name="option3"  value="${bean.option3}"></div>
                <div class="cell" style="width: 30%;"><label>分值三：</label><input type="text" style="width: 30%;" validrule="validate[required]" name="option3score"  value="${bean.option3score}"></div><br>
                <div class="cell" style="width: 70%;"><label>选项四：</label><input type="text" style="width: 60%;" validrule="validate[required]" name="option4"  value="${bean.option4}"></div>
                <div class="cell" style="width: 30%;"><label>分值四：</label><input type="text" style="width: 30%;" validrule="validate[required]" name="option4score"  value="${bean.option4score}"></div>
            </div>

            <input type="hidden" name="eid" value="${bean.eid}"/>
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
