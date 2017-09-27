<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>

<html xmlns:v-bind="http://www.w3.org/1999/xhtml">
<head>
  <title>中驿源晟</title>
  <meta charset="utf-8">
  <link rel="stylesheet" type="text/css" href="./css/common.css">
  <link rel="stylesheet" type="text/css" href="./css/index.css">
  <style type="text/css"></style>
</head>
<body>
<div class="header" id="header">
  <div class="center">
    <a href="index.html"><img class="logo" src="/image/logo.png" title="logo"></a>
    <ul class="headernav">
      <li v-for="n in navDatas">
        <a href="#">{{n.name}}</a>
        <ul v-if="n.data">
          <li v-for="s in n.data">
            <a v-bind:href="s.url">{{s.name}}</a>
          </li>
        </ul>
      </li>
    </ul>
    <div class="searchbox">
      <div class="searchtext">
        <input type="text" name="search" placeholder="输入您想要搜索的内容">
        <img src="">
      </div>
      <a href="/view/gregister.jsp"><input type="button" id="login" value="登录"></a>
      <a href="/view/gregister.jsp?regist"><input type="button" id="regist" value="注册"></a>
    </div>
  </div>
</div>
<div class="banner" id="banner">
  <div class="slide">
    <ul class="slide-box">
      <li v-for="b in bannerDatas" v-bind:style="'background: url('+b.url+') center'"></li>
    </ul>
    <ul class="slide-btn" v-bind:style="'marginLeft:-'+bannerDatas.length*45/2+'px'">
      <li v-for="(i,index) in bannerDatas" v-bind:class="index==0 ? 'slide-btn-active': ''"></li>
    </ul>
  </div>
</div>
<div class="container" id="app">
  <div class="mainer">
    <div class="info">
      <div class="bannerinfo">
        <div class="title">媒体报道</div>
        <div class="text">新闻标题新闻标题新闻标题新闻标题新闻标题新闻标题新闻标题新闻标题新闻标题新闻标题新闻标题新闻标题新闻标题新闻标题新闻标题新闻标题新闻标题新闻标题新闻标题新闻标题新闻标题新闻标题新闻标题新闻标题新闻标题新闻标题新闻标题新闻标题</div>
        <div class="time">2017-05-04</div>
      </div>
      <div class="infobox infoleft">
        <div class="title">
          <img src="/image/icon-c.png">
          <div class="text">
            公司新闻<br>ompany news
          </div>
          <a class="more" href="index.html">更多></a>
        </div>
        <ul class="news">
          <li v-for="n in newsDatas"><a v-bind:href="n.url" class="text">{{n.title}}</a><span class="time">{{n.time}}</span></li>
        </ul>
      </div>
      <div class="infobox infomid">
        <div class="title">
          <img src="/image/icon-i.png" style="width:10px">
          <div class="text">
            行业资讯<br>ndustry information
          </div>
          <a class="more" href="index.html">更多></a>
        </div>
        <ul class="news">
          <li v-for="n in infoDatas"><a v-bind:href="n.url" class="text">{{n.title}}</a><span class="time">{{n.time}}</span></li>
        </ul>
      </div>
      <div class="infobox inforight">
        <div class="title">
          <img src="/image/icon-c.png">
          <div class="text">
            公司公告<br>ompany announcement
          </div>
          <a class="more" href="index.html">更多></a>
        </div>
        <ul class="news">
          <li v-for="n in anoDatas"><a v-bind:href="n.url" class="text">{{n.title}}</a><span class="time">{{n.time}}</span></li>
        </ul>
      </div>
    </div>
  </div>
</div>
<div class="footer" id="footer">
  <div class="center">
		<span class="left">
			<span class="bl">CopyRight©2016 zyyscn.cn All right reserved 中驿源晟投资管理(杭州)有限公司 版权所有</span>
			<span class="bb">浙ICP备16029552号</span>
		</span>
		<span class="right">
			服务热线：4009199043
		</span>
  </div>
</div>
<div class="alert-shadow"></div>
<div class="alert-exam">
  <h2>私募投资基金投资者风险倾向测试（机构版）</h2>
  <p>风险提示：私募投资基金投资者风险倾向测试（机构版）私募投资基金投资者风险倾向测试（机构版）私募投资基金投资者风险倾向测试（机构版）私募投资基金投资者风险倾向测试（机构版）私募投资基金投资者风险倾向测试（机构版）私募投资基金投资者风险倾向测试（机构版）私募投资基金投资者风险倾向测试（机构版）</p>
  <p>以下一系列问题下一系列问题下一系列问题下一系列问题</p>
  <hr>
  <ul>
    <li>
      <h3>1、私募投资基金投资者风险倾向测试？</h3>
      <div><input name="qus1" type="radio" value="1" checked="checked" />A 私募投资基</div>
      <div><input name="qus1" type="radio" value="2" />B 私募投资基</div>
      <div><input name="qus1" type="radio" value="3" />C 私募投资基</div>
      <div><input name="qus1" type="radio" value="4" />D 私募投资基</div>
    </li>
    <li>
      <h3>2、私募投资基金投资者风险倾向测试？</h3>
      <div><input name="qus2" type="radio" value="1" checked="checked" />A 私募投资基</div>
      <div><input name="qus2" type="radio" value="2" />B 私募投资基</div>
      <div><input name="qus2" type="radio" value="3" />C 私募投资基</div>
      <div><input name="qus2" type="radio" value="4" />D 私募投资基</div>
    </li>
  </ul>
  <button class="submit">提交并查看测试结果</button>
</div>
<script src="/webjs/plugins/vue.min.js" type="application/javascript"></script>
<script src="/webjs/plugins/jquery-1.11.0.min.js" type="text/javascript"></script>
<script src="/webjs/index.js" type="text/javascript"></script>
<script type="text/javascript" src="/webjs/common/common-data.js"></script>
<script src="/webjs/common/common-display.js"></script>
</body>
</html>