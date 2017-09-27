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
    <script src="<s:url value="/webjs/jalert.js"/>"></script>
    <script>
        function uploadFile(){
            var $file=$("#file");
            $file.click();
            if(!$file.attr("onchange")){
                $file.on("change",submitImage);
            }
        }

        function submitImage(){
            var formData = new FormData($("#data_form")[0]);
            $.ajax({
                url: '/view/admin/index/upload',
                type: 'POST',
                dataType:"json",
                data: formData,
                processData: false,
                contentType: false,
                success: function (data) {
                    if(data.code==0){
                        jAlert("上传成功！","提示",function(r){
                            if(r){
                                location.reload();
                            }
                        });
                    }else{
                        jAlert(data.failinfo,"提示");
                    }
                },
                error: function (errordata) {
                    jAlert("上传失败！","提示");
                }
            });
        }

        $(function(){
            $(".cooper-div").hover(function(){
                $(this).find(".cooper-data").show();
            },function(){
                $(this).find(".cooper-data").hide();
            });
        });

        function deleteImg(){
            var ids = "";
            $("[name='checkimg']:checked").each(function(){
                ids +=$(this).val()+",";
            });
            if(ids==""){
                jAlert("请选择要删除的图片");
            }else{
                jConfirm("确定删除图片？","",function(r){
                   if(r){
                       $.post("/view/admin/index/delimage",{"navigationid1":$("[name='navigationid1']").val(),"navigationid2":$("[name='navigationid2']").val(),"ids":ids},function(data){
                           if(data.code==0){
                               location.reload();
                           }else{
                               jAlert("删除失败");
                           }
                       });
                   }
                });
            }
        }

        function checkBorder(_this){
            if($(_this).is(":checked")){
                $(_this).parent().parent().css("border","1px solid red");
            }else{
                $(_this).parent().parent().css("border","1px solid #dedede");
            }
        }
    </script>
</head>
<body>
<div class="right-main-context">
    <div class="right-dy-data">
        <div class="right-cond-div">
            <div class="title"><h4>${bean.nodetext}</h4></div>
            <form role="form" class="form-inline" enctype="multipart/form-data" id="data_form">
                <input type="file" name="file" id="file" accept="image/*" style="display: none;">
                <input type="hidden" name="navigationid1" value="${bean.navigationid1}"/>
                <input type="hidden" name="navigationid2" value="${bean.navigationid2}"/>
                <div class="right"><input class="add-btn" type="button" onclick="deleteImg()" value=" 删除图片"></div>
                <div class="right"><input class="add-btn" type="button" onclick="uploadFile()" value=" 上传图片"></div>
            </form>
        </div>
        <div class="cooper-list">
            <c:forEach items="${list}" var="data" varStatus="i">
                <div class="cooper-div">
                    <img src="/view/getImage?navigationid1=${bean.navigationid1}&navigationid2=${bean.navigationid2}&mesgid=${data}" width="100%" height="99%"/>
                    <div class="cooper-data"><input type="checkbox" onclick="checkBorder(this)" name="checkimg" value="${data}"/>是否删除图片？</div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
</body>
</html>