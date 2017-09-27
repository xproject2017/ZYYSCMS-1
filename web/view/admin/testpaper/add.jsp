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
                <div class="cell" style="width: 90%;">
                    <label>试卷标题：</label>
                    <input type="text" style="width: 70%;" validrule="validate[required,maxSize[20]]" name="etitle">
                </div>
                <div class="title">保守型</div>
                <div class="cell">
                    <label>最低分:</label>
                    <input type="text" style="width: 60%;"  validrule="validate[required]" name="leveoneMin">

                </div>
                <div class="cell">
                    <label>最高分:</label>
                    <input type="text" style="width: 60%;"  validrule="validate[required]" name="leveoneMax">

                </div>
                <div class="title">稳健性</div>
                <div class="cell">
                    <label>最低分:</label>
                    <input type="text" style="width: 60%;"  validrule="validate[required]" name="levetwoMin">

                </div>
                <div class="cell">
                    <label>最高分:</label>
                    <input type="text" style="width: 60%;"  validrule="validate[required]" name="levetwoMax">

                </div>
                <div class="title">平衡型</div>
                <div class="cell">
                    <label>最低分:</label>
                    <input type="text" style="width: 60%;"  validrule="validate[required]" name="levelthreeMin">

                </div>
                <div class="cell">
                    <label>最高分:</label>
                    <input type="text" style="width: 60%;"  validrule="validate[required]" name="levelthreeMax">

                </div>
                <div class="title">成长型</div>
                <div class="cell">
                    <label>最低分:</label>
                    <input type="text" style="width: 60%;"  validrule="validate[required]" name="levelfourMin">

                </div>
                <div class="cell">
                    <label>最高分:</label>
                    <input type="text" style="width: 60%;"  validrule="validate[required]" name="levelfourMax">

                </div>
                <div class="title">进取型</div>
                <div class="cell">
                    <label>最低分:</label>
                    <input type="text" style="width: 60%;"  validrule="validate[required]" name="levelfiveMin">

                </div>
                <div class="cell">
                    <label>最高分:</label>
                    <input type="text" style="width: 60%;"  validrule="validate[required]" name="levelfiveMax">

                </div>
            </div>

            <div class="right-btn">
                <button class="btn btn-default" onclick="addFormData()" type="button">保存</button>
                <button class="btn btn-default" onclick="history.go(-1)" style="margin-left: 20px;" type="button">取消</button>
            </div>
        </form>
    </div>
</div>
</body>
</html>
