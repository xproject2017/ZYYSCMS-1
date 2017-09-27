var totalCount = 0;
var pageSize = 10;
/**
 * 构造list数据
 * @param currPage 当前页数(从1开始)
 * @param _falg 0表示第一次加载，需要构造分页控件。1表示不需要
 */
function callBespokePagin(currPage, _falg) {
    $.ajax({
        type: "POST",
        dataType: "json",
        data: {"currentPage": (currPage - 1), "pageSize": pageSize},
        url: '/cms/getBespkeListData',
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
                            callBespokePagin(curr, 1);
                        }
                    });
                }
                $.each(datalist.result, function (i, item) {
                    html.push('<div class="list-title">');
                    html.push('<div class="cell-data"><span style="cursor: pointer;text-decoration: underline;" onclick="fwcontext('+item.navigationid1+','+item.navigationid2+','+item.bespokeid+')">'+item.productname+'</span></div>');
                    html.push('<div class="cell-data">' + item.pbespoketimestr +'</div>');
                    html.push('<div class="cell-data">' +item.flagstr+ '</div>');
                    html.push('</div>');
                });
            } else {
                html.push("<div class='none-data-tips'>暂无数据</div>");
                $('#callBackPager').extendPagination({
                    totalCount: 0,
                    limit: pageSize,
                    callback: function (curr) {
                        callBespokePagin(curr, 1);
                    }
                });
            }
            $(".list-context").html(html.join(''));
        }
    });
}


/**
 * 构造list数据
 * @param currPage 当前页数(从1开始)
 * @param _falg 0表示第一次加载，需要构造分页控件。1表示不需要
 */
function callConsultationPagin(currPage, _falg) {
    $.ajax({
        type: "POST",
        dataType: "json",
        data: {"currentPage": (currPage - 1), "pageSize": pageSize},
        url: '/cms/getConsultationListData',
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
                            callConsultationPagin(curr, 1);
                        }
                    });
                }
                $.each(datalist.result, function (i, item) {
                    html.push('<div class="list-title">');
                    html.push('<div class="cell-data"><span style="cursor: pointer;text-decoration: underline;" onclick="fwcontext('+item.navigationid1+','+item.navigationid2+','+item.consid+')">'+item.mes+'</span></div>');
                    html.push('<div class="cell-data">' + item.constimestr +'</div>');
                    html.push('<div class="cell-data">' +item.flagstr+ '</div>');
                    html.push('</div>');
                });
            } else {
                html.push("<div class='none-data-tips'>暂无数据</div>");
                $('#callBackPager').extendPagination({
                    totalCount: 0,
                    limit: pageSize,
                    callback: function (curr) {
                        callConsultationPagin(curr, 1);
                    }
                });
            }
            $(".list-context").html(html.join(''));
        }
    });
}

function fwcontext(navigationid1,navigationid2,mesgid){
    window.location.href='/cms/fwcontext?navigationid1='+navigationid1+'&navigationid2='+navigationid2+'&nodetext='+_nodetext+"&mesgid="+mesgid;
}

function loginValidate(){
    var _username = $("#funame").val();
    if(_username==""){
        $("#tips").html("请输入您的用户名");
        return false;
    }
    var _pwd = $("#fpword").val();
    if(_pwd==""){
        $("#tips").html("请输入您的密码");
        return false;
    }
    $.post('/cms/login',{"username":_username,"userpassword": $.md5(_pwd)},function(state){
        if(state.code==0){
            window.location.href="/cms/fwindex";
        }else{
            $("#tips").html(state.failinfo);
            return false;
        }
    });
}

function registerValidate(){
    var _username = $("#funame").val();
    if(_username==""){
        $("#tips").html("请输入您的用户名");
        return false;
    }
    var _pwd = $("#fpword").val();
    if(_pwd==""){
        $("#tips").html("请输入您的密码");
        return false;
    }
    if(_pwd != $("#fagpword").val()){
        $("#tips").html("您输入的密码不一致");
        return false;
    }
    var datas = {"username":_username,"userpassword": $.md5(_pwd)};
    $.ajax({
        url:"/cms/register",
        type : "POST",
        dataType : "json",
        contentType : "application/x-www-form-urlencoded",
        data : datas,
        cache: false,
        async : false,
        success : function(state) {
            if(state.code==0){
                window.location.href="/cms/fwindex";
            }else{
                $("#tips").html(state.failinfo);
                return false;
            }
        },
        error : function(response) {
        }


    })
}

//新增预约记录
function getBespokePage(){
    var result = [];
    result.push('<div style="float: left;width: 100%;padding: 12px 0 0 0;">');
    result.push('<form id="pro_form">');
    result.push('<div class="add-zx"><label>产品名称<span>*</span>：</label><input type="text" name="productname" validrule="validate[required,maxSize[30]]" /></div>');
    result.push('<div class="add-zx"><label>姓名<span>*</span>：</label><input type="text" name="pbespokename" validrule="validate[required,maxSize[20]]" /></div>');
    result.push('<div class="add-zx"><label>手机号<span>*</span>：</label><input type="text" name="pbespokephone" validrule="validate[required,custom[phone]]" /></div>');
    result.push('<div class="add-zx"><label>预约时间<span>*</span>：</label><input type="text" name="pbespoketimestr" readonly validrule="validate[required,maxSize[20]]"/></div>');
    result.push('</form>');
    result.push('</div>');
    alertWin(result.join(''),'新增预约',450,290,"addBespoke()","确定","","取消");
    $("#pro_form").validationEngine("attach", {
        'promptPosition': 'topLeft',
        autoHidePrompt: true,
        autoHideDelay: 3000
    });
    //初始化日期控件
    $("input[name='pbespoketimestr']").each(function(){
        $(this).datepicker({
            changeMonth: true,
            changeYear: true
        });
    });
}

function addBespoke(){
    var _form = $("#pro_form");
    if(_form.validationEngine('validate')){
        $.ajax({
            type: "POST",
            dataType:"json",
            url:'/cms/admin/addBespoke',
            data:_form.serialize(),// 你的formid
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                jAlert("添加失败", "提示");
            },
            success: function(data) {
                if(data.code == 0) {
                    jAlert("添加成功", "提示",function(r){
                        if(r){
                            closePop();
                            callBespokePagin(1,0);
                        }
                    });
                }else{
                    jAlert(data.failinfo,"提示");
                }
            }
        });
    }
}

//新增咨询
function getConsultationPage(){
    var result = [];
    result.push('<div style="float: left;width: 100%;padding: 12px 0 0 0;">');
    result.push('<form id="pro_form">');
    result.push('<div class="add-zx"><label>姓名<span>*</span>：</label><input type="text" name="consname" validrule="validate[required,maxSize[20]]" /></div>');
    result.push('<div class="add-zx"><label>手机号<span>*</span>：</label><input type="text" name="consphone" validrule="validate[required,custom[phone]]" /></div>');
    result.push('<div class="add-zx"><label>咨询内容<span>*</span>：</label><input type="text" name="mes" validrule="validate[required,maxSize[30]]" /></div>');
    result.push('</form>');
    result.push('</div>');
    alertWin(result.join(''),'新增咨询',450,250,"addConsultation()","确定","","取消");
    $("#pro_form").validationEngine("attach", {
        'promptPosition': 'topLeft',
        autoHidePrompt: true,
        autoHideDelay: 3000
    });
    //初始化日期控件
    $("input[name='pbespoketimestr']").each(function(){
        $(this).datepicker({
            changeMonth: true,
            changeYear: true
        });
    });
}

function addConsultation(){
    var _form = $("#pro_form");
    if(_form.validationEngine('validate')){
        $.ajax({
            type: "POST",
            dataType:"json",
            url:'/cms/admin/addConsultation',
            data:_form.serialize(),// 你的formid
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                jAlert("添加失败", "提示");
            },
            success: function(data) {
                if(data.code == 0) {
                    jAlert("添加成功", "提示",function(r){
                        if(r){
                            closePop();
                            callConsultationPagin(1,0);
                        }
                    });
                }else{
                    jAlert(data.failinfo,"提示");
                }
            }
        });
    }
}
//我要应聘
function getApplyPage(){
    var result = [];
    result.push('<div style="float: left;width: 100%;padding: 12px 0 0 0;">');
    result.push('<form id="pro_form">');
    result.push('<div class="add-zx"><label>应聘职位<span>*</span>：</label><input type="text" name="applyposition" validrule="validate[required,maxSize[30]]" /></div>');
    result.push('<div class="add-zx"><label>姓名<span>*</span>：</label><input type="text" name="applyname" validrule="validate[required,maxSize[30]]" /></div>');
    result.push('<div class="add-zx"><label>性别<span>*</span>：</label><select name="gender"><option value="0">男</option><option value="1">女</option></select></div>');
    result.push('<div class="add-zx"><label>出生年月日<span>*</span>：</label><input type="text" name="birthday" readonly validrule="validate[required,maxSize[30]]" /></div>');
    result.push('<div class="add-zx"><label>毕业院校<span>*</span>：</label><input type="text" name="graduateschool"  validrule="validate[required,maxSize[30]]"/></div>');
    result.push('<div class="add-zx"><label>学历<span>*</span>：</label><select name="education"><option value="0">初中</option><option value="1">高中</option><option value="2">大专</option><option value="3">本科</option><option value="4">硕士</option><option value="5">博士</option></select></div>');
    result.push('<div class="add-zx"><label>专业<span>*</span>：</label><input type="text" name="profession"  validrule="validate[required,maxSize[30]]"/></div>');
    result.push('<div class="add-zx"><label>毕业时间<span>*</span>：</label><input type="text" name="graduatetime"  readonly validrule="validate[required,maxSize[20]]"/></div>');
    result.push('<div class="add-zx"><label>电话<span>*</span>：</label><input type="text" name="applyphone"   validrule="validate[required,custom[phone]]"/></div>');
    result.push('<div class="add-zx"><label>邮箱<span>*</span>：</label><input type="text" name="applyemail"  validrule="validate[required,custom[email]]"/></div>');
    result.push('<div class="add-zx"><label>联系地址<span>*</span>：</label><input type="text" name="applyaddress"  validrule="validate[required,maxSize[100]]"/></div>');
    result.push('<div class="add-zx"><label>简历上传<span>*</span>：</label><input type="file" name="applyfile"  validrule="validate[required]"/></div>');
    result.push('</form>');
    result.push('</div>');
    alertWin(result.join(''),'应聘信息',450,595,"addApply()","确定","","取消");
    $("#pro_form").validationEngine("attach", {
        'promptPosition': 'topLeft',
        autoHidePrompt: true,
        autoHideDelay: 3000
    });
    //初始化日期控件
    $("input[name='birthday']").each(function(){
        $(this).datepicker({
            changeMonth: true,
            changeYear: true
        });
    });
    $("input[name='graduatetime']").each(function(){
        $(this).datepicker({
            changeMonth: true,
            changeYear: true
        });
    });
}

function addApply(){
    var _form = $("#pro_form");
    var formData = new FormData(_form[0]);
    if(_form.validationEngine('validate')){
        $.ajax({
            type: "POST",
            dataType:"json",
            url:'/cms/addApply',
            data:formData,// 你的formid
            processData: false,
            contentType: false,
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                jAlert("添加失败", "提示");
            },
            success: function(data) {
                if(data.code == 0) {
                    jAlert("添加成功", "提示",function(r){
                        if(r){
                            closePop();
                            callConsultationPagin(1,0);
                        }
                    });
                }else{
                    jAlert(data.failinfo,"提示");
                }
            }
        });
    }
}