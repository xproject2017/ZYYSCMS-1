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
        function submitData(){
            var result = [];
            $(".account-div").find("input[type='text']").each(function(){
                var _v = $(this).val()+','+$(this).data("navigationid1")+","+$(this).data("navigationid2");
                result.push('<input type="hidden" name="nodetext" value="'+_v+'"/>');
            });
            $(".right-inblock-div").append(result.join(''));
            $.ajax({
                type: "POST",
                dataType:"json",
                url:'/view/admin/index/updateMenuData',
                data:$("#data_form").serialize(),// 你的formid
                error: function(XMLHttpRequest, textStatus, errorThrown) {
                    jAlert("修改失败", "提示");
                },
                success: function(data) {
                    if(data.code == 0) {
                        jAlert("修改成功", "提示",function(r){
                            if(r){
                                window.location.href = "/view/admin/index/getMenuConfigPage?nodetext=${bean.nodetext}";
                            }
                        });
                    }else{
                        jAlert(data.failinfo,"提示");
                    }
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
        <form role="form" class="form-inline" id="data_form">
            <div class="right-inblock-div">
                <div class="account-div">
                    <c:forEach items="${MenuList}" var="first">
                        <div class="block-account">
                            <div class="first-level"><input type="text" value="${first.nodetext}" data-navigationid2="${first.navigationid2}" data-navigationid1="${first.navigationid1}" validrule="validate[required,maxSize[10]]"/></div>
                            <c:forEach items="${first.sonMenus}" var="son">
                                <div class="second-level"><input type="text" value="${son.nodetext}" data-navigationid2="${son.navigationid2}" data-navigationid1="${son.navigationid1}" validrule="validate[required,maxSize[10]]" style="z-index: 1;position: absolute;width: 100px;height: 20px;"/></div>
                            </c:forEach>
                        </div>
                    </c:forEach>
                </div>
            </div>
            <div class="right-btn">
                <button class="btn btn-default" onclick="submitData()" type="button">提交</button>
                <button class="btn btn-default" onclick="history.go(-1)" style="margin-left: 20px;" type="button">取消</button>
            </div>
        </form>
    </div>
</div>
</body>
</html>