<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link rel="中新环球投资有限公司" href="/image/favicon.ico" />
    <script>
        var _index = 1;
        $(function(){
            $("#menulist li").hover(function(){
            $(this).find(".nav-scroll").show();
            $(this).siblings().find(".nav-scroll").hide();
          },function(){
            $(this).find(".nav-scroll").slideUp();
            $(this).find(".nav-scroll").hide();
          });
            setInterval("picloop()",5000);
        });
        function picloop(){
            $(".picloop img").each(function(){
                if($(this).is(":visible")){
                    if($(this).data("tag")==$(".picloop img").size()){
                        _index = 1;
                    }else{
                        _index ++;
                    }
                }
            });
            $(".picloop img").hide();
            var vx = _index -1;
            $(".picloop img").eq(vx).show();

            $("#rad-jq div").siblings().css("background-color","#fff").eq(vx).css("background-color","#d72a2e");
        }

        function checkRad(_index){
            $(".picloop img").hide();
            var vx = _index -1;
            $(".picloop img").eq(vx).show();
            $("#rad-jq div").siblings().css("background-color","#fff").eq(vx).css("background-color","#d72a2e");
        }

        function search(){
            window.location.href="/cms/search?mtags="+$("#mtags").val();
        }

        function fwloopimagecontext(navigationid1,navigationid2,mesgid){
            window.location.href='/cms/fwcontext?navigationid1='+navigationid1+'&navigationid2='+navigationid2+"&mesgid="+mesgid;
        }
    </script>
</head>
<body>
  <div class="header">
      <div class="base-reg">
          <span style="font-size: 18px;line-height: 55px;">财富热线：</span>
          <span style="font-size: 18px;margin-right: 20px;line-height: 55px;">400000000</span>
          <c:choose>
              <c:when test="${empty cmsinfo}">
                  <a href="/view/glogin.jsp">登录</a>
                  <span style="margin: 0 10px;">|</span>
                  <a href="/view/gregister.jsp">注册</a>
              </c:when>
              <c:otherwise>
                  欢迎您，${cmsinfo.username}
              </c:otherwise>
          </c:choose>
          <input type="text" class="search-input" placeholder="站内信息搜索" id="mtags" />
          <input type="button" value="&nbsp;&nbsp;&nbsp;" class="iconsearch" onclick="search()">
      </div>
      <div class="menu">
          <div style="float: left;width: 600px;text-align: center;height: 100%;margin: -40px 0 0 0;">
              <img src="/image/cmslogo.png" style="width: 221px;height: 65px;">
          </div>
          <ul id="menulist">
              <c:forEach var="data" items="${result}">
                  <li><a href="${data.path}?navigationid1=${data.navigationid1}&navigationid2=${data.navigationid2}">${data.nodetext}</a>
                  <c:forEach var="son" items="${data.sonMenus}" varStatus="i">
                      <c:if test="${i.first}">
                          <div class="nav-scroll">
                              <div class="nav-top">&nbsp;</div>
                              <div class="nav-secmen">
                      </c:if>
                      <a href="${son.path}?navigationid1=${son.navigationid1}&navigationid2=${son.navigationid2}">${son.nodetext}</a>
                      <c:if test="${i.last}">
                            </div>
                          </div>
                      </c:if>
                  </c:forEach>
                  </li>
              </c:forEach>
          </ul>
      </div>
  </div>
  <div class="picloop">
      <c:forEach var="iobj" items="${banner}" varStatus="i">
          <img onclick="fwloopimagecontext(${iobj.navigationid1},${iobj.navigationid2},${iobj.mesgid})" src="/view/getImage?navigationid1=${iobj.navigationid1}&navigationid2=${iobj.navigationid2}&mesgid=${iobj.mesgid}" data-tag="${i.index+1}" <c:if test="${!i.first}">style="display: none;" </c:if>>
      </c:forEach>
  </div>
    <div style="position: absolute;width: 100%;text-align: center;top:520px;" id="rad-jq">
        <c:forEach var="iobj" items="${banner}" varStatus="i">
            <div data-rad="${i.index+1}" onclick="checkRad(${i.index+1})" style="position: absolute;width: 12px;height: 12px;border-radius: 10px;border: 1px solid #ccc;left:${i.index*2+44}%;cursor: pointer;<c:if test="${i.first}"> background-color: #d72a2e; </c:if>">&nbsp;</div>
        </c:forEach>
    </div>
</body>
</html>
