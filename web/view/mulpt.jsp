<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>中新环球投资有限公司</title>
    <link rel="中新环球投资有限公司" href="/image/favicon.ico" />
    <script src="<s:url value="/webjs/jquery-1.11.1.js"/>"></script>
    <link href="<s:url value="/css/cms.css"/>" rel="stylesheet">
    <link href="<s:url value="/css/bootstrap.min.css"/>" rel="stylesheet" media="screen">
    <script src="<s:url value="/webjs/jquery-1.11.1.js"/>"></script>
    <script src="<s:url value="/webjs/bootstrap.min.js"/>"></script>
    <script src="<s:url value="/webjs/extendPagination.js"/>"></script>
    <script src="<s:url value="/webjs/jalert.js"/>"></script>
    <script>
        function fwcontext(navigationid1,navigationid2,nodetext,mesgid){
            window.location.href='/cms/fwcontext?navigationid1='+navigationid1+'&navigationid2='+navigationid2+'&nodetext='+nodetext+"&mesgid="+mesgid;
        }
        var totalCount = 0;
        var pageSize = 10;
        function callMulptPagin(currPage, _falg) {
            $.ajax({
                type: "POST",
                dataType: "json",
                data: {"currentPage": (currPage - 1), "pageSize": pageSize,"navigationid1":${paramobj.navigationid1},"navigationid2":${paramobj.navigationid2}},
                url: '/cms/getMulptListData',
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    $(".list-context").html("<div class='none-data-tips'>查询数据失败</div>");
                    timerClearDiv();
                },
                success: function (datalist) {
                    var html = [];
                    if (datalist.code == 0) {
                        totalCount = datalist.result[0].totalNum;
                        if (_falg == 0) {
                            $('#callBackPager').extendPagination({
                                totalCount: totalCount,
                                limit: pageSize,
                                callback: function (curr) {
                                    callMulptPagin(curr, 1);
                                }
                            });
                        }
                        $.each(datalist.result, function (i, item) {
                            if(i==0){
                                html.push('<div class="mulpt-first" onclick="fwcontext('+item.navigationid1+','+item.navigationid2+',\'${nodetext}\','+item.mesgid+')">');
                                html.push('<div class="pic-div"><img src="/view/getImage?navigationid1='+item.navigationid1+'&navigationid2='+item.navigationid2+'&mesgid='+item.mesgid+'" style="width: 100%;max-height:99%;"/></div>');
//                                html.push('<div class="title-div">'+item.mtitle+'</div>');
                                html.push('<div class="elliptical-div"><span class="title-div">'+item.mtitle+'</span>'+item.elliptical+'</div>');
                                html.push('</div>');
                            }else{
                                html.push('<div class="mulpt-list">');
                                html.push('<div class="con-date">'+FormatDate(item.createtime)+'</div>');
                                html.push('<div class="con-news" onclick="fwcontext('+item.navigationid1+','+item.navigationid2+',\'${nodetext}\','+item.mesgid+')">');
                                html.push('<span>'+item.mtitle+'</span>');
                                html.push('<div class="con-mes">'+item.elliptical+'</div>');
                                html.push('</div>');
                                html.push('</div>');
                            }
                        });
                    } else {
                        html.push("<div class='none-data-tips'>暂无数据</div>");
                        $('#callBackPager').extendPagination({
                            totalCount: 0,
                            limit: pageSize,
                            callback: function (curr) {
                                callMulptPagin(curr, 1);
                            }
                        });
                    }
                    $(".list-context").html(html.join(''));
                }
            });
        }
        function FormatDate (strTime) {
            var date = new Date(strTime);
            return date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
        }
    </script>
</head>
<body onload="callMulptPagin(1,0)">
    <jsp:include page="top.jsp"/>
      <div class="middle-context">
          <div class="left-div">
              <div id="title-menu">${leftmenu.get(0).nodetext}</div>
              <ul>
                  <c:forEach var="data" items="${leftmenu}" varStatus="i">
                      <c:if test="${!i.first}">
                            <li <c:if test="${data.nodetext eq nodetext}">class="activeA"</c:if> ><a href="${data.path}?navigationid1=${data.navigationid1}&navigationid2=${data.navigationid2}&nodetext=${data.nodetext}">${data.nodetext}<span> > </span></a></li>
                      </c:if>
                  </c:forEach>
                  <div class="menu-bottom">&nbsp;</div>
              </ul>
          </div>

          <div class="right-div">
              <div class="content-main-top">
                  <div class="right-content-main-title">${nodetext}</div>
                  <div class="content-main-nav">
                      <a href="/cms/fwindex"> 首页 ></a>
                      <a href="${leftmenu.get(1).path}?navigationid1=${leftmenu.get(1).navigationid1}&navigationid2=${leftmenu.get(1).navigationid2}&nodetext=${leftmenu.get(1).nodetext}"> ${leftmenu.get(0).nodetext}></a>
                      <span>${nodetext}</span>
                  </div>
              </div>
              <div class="about-main">
                  <div id="callBackPagination" style="border: 0;">
                      <div class='list-context'></div>
                      <div id="callBackPager"></div>
                  </div>
              </div>
          </div>
      </div>
    <jsp:include page="bottom.jsp"/>
</body>
</html>
