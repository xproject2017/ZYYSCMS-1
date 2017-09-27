<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<html xmlns:v-bind="http://www.w3.org/1999/xhtml" xmlns:v-on="http://www.w3.org/1999/xhtml">
<head>
    <title>中驿源晟</title>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href="/css/common.css">
    <link rel="stylesheet" type="text/css" href="/css/login.css">
</head>
<body>
<div class="header" id="header" style="background: #efefef">
    <div class="center">
        <a href="index.html"><img class="logo" src="/image/logo.png" title="logo"></a>
    </div>
</div>
<div class="container" id="app">
    <div class="center">
        <div class="box">
            <div class="title">
                <span v-bind:class="{ current : nowModal !== 'regist'}" v-on:click="changeModal('login')">会员登录</span>
                <span v-bind:class="{ current : nowModal === 'regist'}" v-on:click="changeModal('regist')">快速注册</span>
            </div>
            <div class="subbox loginbox" v-if="nowModal !== 'regist'">
                <div class="text">
                    <img src="/image/icon-user.png"/>
                    <input type="text" placeholder="输入手机号">
                </div>
                <div class="text">
                    <img src="/image/icon-pwd.png"/>
                    <input type="password" placeholder="输入密码">
                </div>
                <div class="qrcode-text">
                    <input type="text" placeholder="输入图形验证码">
                    <img src="/code?v=1" id="vcode" onclick="changeCode()">
                </div>
                <input class="submit-btn" v-on:click="login" type="button" value="登录">
                <div class="forget"><a href="forgetpwd.html">忘记密码?</a></div>
            </div>
            <div class="subbox regbox" v-if="nowModal === 'regist'">
                <div class="text">
                    <img src="/image/icon-user.png"/>
                    <input type="text" name="phone" placeholder="输入手机号">
                </div>
                <div class="qrcode-text">
                    <input type="text" name="qrcode" placeholder="输入图形验证码">
                    <img src="/code?v=3" id="ovcode" onclick="ochangeCode()">
                </div>
                <div class="phone-qrcode-text">
                    <input type="text" name="phone_qrcode" placeholder="输入手机验证码">
                    <button v-on:click="phoneClick">点击获取手机验证码</button>
                </div>
                <div class="text">
                    <img src="/image/icon-pwd.png"/>
                    <input type="password" name="password" placeholder="输入登录密码">
                </div>
                <input class="submit-btn" v-on:click="regist" type="button" value="注册">
            </div>
        </div>
        <div class="clear"></div>
    </div>
</div>
<div class="alert-shadow"></div>
<div class="alert-modal">
    <img class="close" src="/image/icon-close.png"/>
    <div class="message">修改成功</div>
    <div class="btns">
        <a href="login.html" class="retlogin">返回登录</a>
        <a href="index.html" class="rethome">回到首页</a>
    </div>
</div>
<script src="/webjs/plugins/vue.min.js" type="application/javascript"></script>
<script src="/webjs/plugins/jquery-1.11.0.min.js" type="text/javascript"></script>
<script src="/webjs/common/config.js" type="text/javascript" ></script>
<script src="/webjs/common/util.js" type="text/javascript"></script>
<script src="<s:url value="/webjs/jQuery.md5.js"/>"></script>
<script>
    function changeCode(){
       $("#vcode").attr("src", "/code?uuuy="+Math.random());
    }

    function ochangeCode(){
        $("#ovcode").attr("src", "/code?uuuy="+Math.random());

    }


    new Vue({
        el: '#app',
        data: {
            nowModal:"",
        },
        methods: {
            changeModal: function (modal,event) {
                this.nowModal = modal;
            },
            phoneClick: function (){
                var url = "cms/register";
                var phone = $(".regbox input[name=phone]").val();
                if(!(/^1[34578]\d{9}$/.test(phone))){
                    alert("手机号码有误，请重填");
                    return false;
                }else {
                    //获取手机验证码
                }
            },
            login: function () {

            },
            regist: function () {
                var url = "/cms/register";
                var phone = $(".regbox input[name=phone]").val();
                var qrcode = $(".regbox input[name=qrcode]").val();
                var phoneQrcode = $(".regbox input[name=phone_qrcode]").val();
                var password = $(".regbox input[name=password]").val();
                if(!(/^1[34578]\d{9}$/.test(phone))){
                    alert("手机号码有误，请重填");
                    return false;
                }
                if (phone.trim() == "" || qrcode.trim() == "" || phoneQrcode.trim() == "" || password == ""){
                    alert("不能为空");
                }else {
                    var datas = {username:phone,userpassword:$.md5(password),usertelephone:phone,utype:'',code:''};
                    $.ajax({
                        url:url,
                        type:"post",
                        contentType : "application/x-www-form-urlencoded",
                        data:datas,
                        dataType: "json",
                        success:function(data){
                            if (data.code == 0){
                                var user = data.data;
                                setCookie("userid",user.userid,1);
                                setCookie("username",user.username,1);
                                //注册成功，去首页填写调查表
                                window.location.href = "/index.html?regsuccess";
                            }else {
                                if (data.failinfo) {
                                    alert(data.failinfo);
                                }
                            }
                        },
                        error : function(response) {
                            alert(response);
                        }
                    });
                }
            }
        },
        mounted : function () {
            var str=location.href;
            var num=str.indexOf("?");
            str=str.substr(num+1).replace("#","");
            this.nowModal = str;

            var h = $(window).height();
            var w = $(window).width();
            $(".alert-shadow").css("height",h).css("width",w);
        }

    })
</script>
</body>
</html>