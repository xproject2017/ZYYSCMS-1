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
        function fwcontext(navigationid1,navigationid2,mesgid){
            var _url='/cms/fwcontext?navigationid1='+navigationid1+'&navigationid2='+navigationid2+"&mesgid="+mesgid;
            window.open(_url);
        }
        var totalCount = 0;
        var pageSize = 10;
        function callSearchResultPagin(currPage, _falg) {
            $.ajax({
                type: "POST",
                dataType: "json",
                data: {"currentPage": (currPage - 1), "pageSize": pageSize,"mtags":"${mtags}"},
                url: '/cms/getSearchResultData',
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    $(".list-context").html("<div class='none-data-tips'>查询数据失败</div>");
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
                                    callSearchResultPagin(curr, 1);
                                }
                            });
                        }
                        $.each(datalist.result, function (i, item) {
                            html.push('<div class="mulpt-list">');
                            html.push('<div class="con-date">'+FormatDate(item.createtime)+'</div>');
                            html.push('<div class="con-news" onclick="fwcontext('+item.navigationid1+','+item.navigationid2+','+item.mesgid+')">');
                            html.push('<span>'+item.mtitle+'</span>');
                            html.push('<div class="con-mes">'+item.elliptical+'</div>');
                            html.push('</div>');
                            html.push('</div>');
                        });
                    } else {
                        html.push("<div class='none-data-tips'>暂无数据</div>");
                        $('#callBackPager').extendPagination({
                            totalCount: 0,
                            limit: pageSize,
                            callback: function (curr) {
                                callSearchResultPagin(curr, 1);
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
<body onload="callSearchResultPagin(1,0)">
    <jsp:include page="top.jsp"/>
      <div class="middle-context">
          <div class="content-main-top">
              <div class="right-content-main-title">搜索</div>
          </div>
          <div class="about-main" style="margin: 50px 100px 50px 100px;">
              <div id="callBkPagination" style="border: 0;">
                  <div class='list-context'></div>
                  <div id="callBackPager"></div>
              </div>
          </div>
      </div>
    <jsp:include page="bottom.jsp"/>
</body>
</html>
