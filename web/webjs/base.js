
//下载失败错误提示信息
function failtips(_failinfo){
    jAlert(_failinfo);
}

function exitPopTip(){
    var result = [];
    result.push('您确定退出系统吗？');
    jConfirm(result.join(''),"提示",function(r){
        if(r){
            $.post("/logout");
            window.location.href="/index.jsp";
        }
    });
}

function getUserinfo(){
    $.post("/view/system/personalDetail",{"userid":g_userid},function(data){
        if(data.code==0){
            var result = [];
            result.push('<div class="right-inblock-div" style="border-radius: 0;border-bottom: none;">');
            result.push('<div class="cell"><label>姓名：</label>'+data.users.uname+'</div>');
            result.push('<div class="cell"><label>账户：</label>'+data.users.username+'</div>');
            result.push('<div class="cell"><label>密码：</label>****** &nbsp;<button type="button" class="btn btn-default btn-sm" onclick="pwdUpdate('+data.users.userid+',\''+data.users.savedateTimeStr+'\')">修改密码</button></div>');
            result.push('<div class="splitline">&nbsp;</div>');
            result.push('<div class="cell"><label>部门：</label>'+data.users.department+'</div>');
            result.push('<div class="cell"><label>成员角色：</label>'+data.users.rolenames+'</div>');
            result.push('<div class="cell"><label>职务：</label>'+data.users.post+'</div>');
            result.push('<div class="splitline">&nbsp;</div>');
            result.push('<div class="cell"><label>手机号：</label>'+data.users.usermobilephone+'</div>');
            result.push('<div class="cell"><label>邮箱：</label>'+data.users.useremail+'</div>');
            result.push('<div class="cell"><label>固定电话：</label>'+data.users.usertelephone+'</div>');
            result.push('<input type="hidden" id="userpwd" value="'+data.users.userpassword+'"/>');
            result.push('</div>');
            alertWin(result.join(''),'用户信息',700,280,"updateContextPop()","修改","","关闭");
        }
    });
}

function updateContextPop(){
    $.post("/view/system/personalDetail",{"userid":g_userid},function(data){
        if(data.code==0){
            closePop();
            var result = [];
            result.push('<form class="form-inline" role="form" id="updateuser_form">');
            result.push('<div class="right-inblock-div" style="border-radius: 0;border-bottom: none;">');
            result.push('<div class="cell"><label>姓名：</label><input type="text" validrule="validate[required,maxSize[20]]" name="uname" value="'+data.users.uname+'"></div>');
            result.push('<div class="cell"><label>账户：</label>'+data.users.username+'</div>');
            result.push('<div class="cell"><label>密码：</label>******</div>');
            result.push('<div class="splitline">&nbsp;</div>');
            result.push('<div class="cell"><label>部门：</label><input type="text" validrule="validate[required,maxSize[20]]" name="department" value="'+data.users.department+'"></div>');
            result.push('<div class="cell"><label>成员角色：</label>'+data.users.rolenames+'</div>');
            result.push('<div class="cell"><label>职务：</label><input type="text" validrule="validate[required,maxSize[20]]" name="post" value="'+data.users.post+'"></div>');
            result.push('<div class="splitline">&nbsp;</div>');
            result.push('<div class="cell"><label>手机号：</label><input type="text" validrule="validate[required,custom[phone]]" name="usermobilephone" value="'+data.users.usermobilephone+'"></div>');
            result.push('<div class="cell"><label>邮箱：</label><input type="text" validrule="validate[required,custom[email]]" name="useremail" value="'+data.users.useremail+'"></div>');
            result.push('<div class="cell"><label>固定电话：</label><input type="text" validrule="validate[required,custom[telphone]]" name="usertelephone" value="'+data.users.usertelephone+'"></div>');
            result.push('<input type="hidden" name="userid" value="'+data.users.userid+'"/><input type="hidden" name="savedateTimeStr" value="'+data.users.savedateTimeStr+'"/>');
            result.push('</div></form>');
            alertWin(result.join(''),'用户信息',700,280,"submitUsersFormData()","完成","goBack()","取消");
        }
    });
    $("#updateuser_form").validationEngine("attach", {
        'promptPosition': 'topLeft',
        autoHidePrompt: true,
        autoHideDelay: 3000
    });
}

function goBack(){
    closePop();
    getUserinfo();
}

function submitUsersFormData(){
    var _form = $("#updateuser_form");
    if(_form.validationEngine('validate')){
        $.ajax({
            type: "POST",
            dataType:"json",
            url:'/view/system/updateNationalUser',
            data:_form.serialize(),// 你的formid
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                jAlert("修改失败", "提示");
            },
            success: function(data) {
                if(data.code == 0) {
                    jAlert("修改成功", "提示",function(r){
                        if(r){
                            closePop();
                            //getUserinfo();
                        }
                    });
                }else{
                    jAlert(data.failinfo,"提示");
                }
            }
        });
    }
}

/*修改密码页面*/
function pwdUpdate(_userid,_savetime){
    var result = [];
    result.push('<form class="form-inline" role="form" id="updatepwd_form">');
    result.push('<div class="right-inblock-div" style="border-radius: 0;border-bottom: none;">');
    result.push('<div style="float: left;width: 70%;margin-top:20px;height: 43px;padding-left: 30%;"><label style="min-width: 120px;">原始密码：</label><input type="password" validrule="validate[required,maxSize[20]]" id="oldpwd"></div>');
    result.push('<div style="float: left;width: 70%;height: 43px;padding-left: 30%;"><label style="min-width: 120px;">新密码：</label><input type="password" validrule="validate[required,maxSize[20]]" id="pwd"></div>');
    result.push('<div style="float: left;width: 70%;height: 43px;padding-left: 30%;"><label style="min-width: 120px;">确认新密码：</label><input type="password" validrule="validate[required,maxSize[20]]" id="confirmpwd"></div>');
    result.push('</div></form>');
    alertPwdWin(result.join(''),'修改密码',700,280,"submitPwd("+_userid+",'"+_savetime+"')");
    $("#updatepwd_form").validationEngine("attach", {
        'promptPosition': 'topLeft',
        autoHidePrompt: true,
        autoHideDelay: 3000
    });
}

function submitPwd(_userid,_savetime){
    var _form = $("#updatepwd_form");
    if(_form.validationEngine('validate')){
        var _user = $("#userpwd");
        if(_user.val()!= $.md5($("#oldpwd").val())){
            jAlert("原始密码输入错误");
            return false;
        }
        var _pwd = $("#pwd").val();
        if(_pwd!=$("#confirmpwd").val()){
            jAlert("输入的新密码不一致");
            return false;
        }
        $.post("/view/admin/system/updatePwd",{"userid":_userid,"savedateTimeStr":_savetime,"userpassword": $.md5(_pwd)},function(data){
            if(data.code==0){
                closePwdPop();
                _user.val($.md5(_pwd));
                jAlert("修改成功","提示");
                return false;
            }else{
                jAlert(data.failinfo,"提示");
                return false;
            }
        });
    }
}

/*密码重置*/
function pwdReset(_userid,_savetime){
    jConfirm("确定要重置密码？","提示",function(r){
       if(r){
           $.post("/view/admin/system/resetPwd",{"userid":_userid,"savedateTimeStr":_savetime},function(data){
               if(data.code==0){
                   jAlert("重置成功","提示");
                   return false;
               }else{
                   jAlert(data.failinfo,"提示");
                   return false;
               }
           });
       }
    });
}

function setMenu(){
    //菜单宽度100%显示。
    var _w = $(window).width();
    if(_w>1024){
        var _p = (_w/7-73)/2;
        $(".second a").css("padding","0 "+_p+"px");
    }
    $.post("/view/getMenuData","",function(data){
        if(data.code==0){
            $(".second li").each(function(){
                $(this).click(function(){
                    var rightContext = $("#rightContext");
                    $(this).addClass("active").siblings().removeClass();
                    var result = [];
                    result.push('<h3>门户后台管理系统</h3>');
                    var i = $(this).data("platform");

                    var falg = 0;//判断是不是第一次获取
                    $.each(data.result,function(idx,item){
                        var _firstpath = item.path;
                        if(_firstpath=="#"){
                            _firstpath = "/developing.jsp";
                        }
                        if(item.nodecode==i){
                            $.each(item.sonMenus,function(i,son){
                                var _secpath = son.path;
                                if(_secpath=="#"){
                                    _secpath = "/developing.jsp";
                                }
                                _secpath += '?navigationid1='+son.navigationid1+'&navigationid2='+son.navigationid2+"&nodetext="+son.nodetext;
                                if(falg==0){
                                    _firstpath += '?navigationid1='+son.navigationid1+'&navigationid2='+son.navigationid2+"&nodetext="+son.nodetext;
                                    result.push('<a href="'+_firstpath+'" class="hover01" target="iframe">'+son.nodetext+'</a>');
                                    rightContext.attr("src",_firstpath);
                                    falg =1;
                                }else{
                                    result.push('<a href="'+_secpath+'" target="iframe">'+son.nodetext+'</a>');
                                }
                            });
                        }
                    });
                    $(".foldNav").html(result.join(''));
                    setLeftMenu();
                });
            });
            $(".second a").eq(0).trigger("click");
            setLeftMenu();
        }
    });
}

//show()方法默认是从左上角开始显示。slideDown()方法是从上到下开始显示
function setLeftMenu(){
    var rightContext = $("#rightContext");
    var foldNavA = $('.foldNav a');
    var _c = $('.foldNav a:first').next();
    if(_c.attr("class") == "foldSec"){
        _c.slideDown().find("a").eq(0).addClass('hover01');
    }
    foldNavA.bind('click',function(){
        var _nextclass = $(this).next().attr("class");
        if($(this).parent().attr("class")!="foldSec"){
            var _o = $(".foldSec:visible");
            if(_o.size()>0){
                if(_nextclass=="foldSec"){
                    return ;
                }
            }
            _o.slideUp();
            foldNavA.removeClass(function(){
                return "hover01 hover02";
            });
            $(this).next().slideDown();
        }else{
            $('.foldSec a').removeClass('hover01');
        }

        if(_nextclass=="foldSec"){
            $(this).addClass('hover02');
        }else{
            $(this).addClass('hover01');
        }

        rightContext.attr("src",$(this)[0].href);
        if(_nextclass=="foldSec"){
            $(this).next().find("a").eq(0).addClass('hover01');
        }

        return false ;
    });
}
//全选功能
function checkAllBox(_id,_name){
    var _flg = $("#"+_id).is(':checked');
    $("input[name='"+_name+"']").each(function () {
        if(!$(this).attr("disabled"))
            $(this).prop("checked", _flg);
    });
}

function checkCity(_this){
    var _flg = $(_this).is(":checked");
    $(_this).siblings().prop("checked",_flg);
}

//收缩功能
function toggleData(_this,_type,_risk){
    if(undefined!=_risk && 0==_risk){
        $(_this).parent().next().toggle();
    }
    else{
        _risk = 1;
        $(_this).parent().siblings().toggle();
    }

    if(_type==1){
        $(_this).attr("src","/image/down.png").attr("onclick","toggleData(this,0,"+_risk+")");
    }else{
        $(_this).attr("src","/image/up.png").attr("onclick","toggleData(this,1,"+_risk+")");
    }
}


function downloadRisk(riskid){
    $("#download").attr("src",'/view/checknetwork/fileOperate/downloadRisk?riskid='+riskid);
}