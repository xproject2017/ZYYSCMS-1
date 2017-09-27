/**
 * Created by 浩 on 2017/9/13.
 */
var totalCount = 0;
var pageSize = 10;

/**
 * 构造list数据
 * @param currPage 当前页数(从1开始)
 * @param _falg 0表示第一次加载，需要构造分页控件。1表示不需要
 */
function callBackPagination(currPage,_falg) {
    var queryFlag = $("#queryFlag").val();
    var cname = $("#cpname").val();
    $.ajax({
        type: "POST",
        dataType:"json",
        data:{"queryUname":cname,"currentPage":(currPage-1),"roleids":queryFlag,"pageSize":pageSize},
        url:'/view/admin/vip/getVipListData',
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            $(".list-context").html("<div class='none-data-tips'>查询数据失败</div>");
            timerClearDiv();
        },
        success: function(datalist) {
            var html = [];
            if(datalist.code==0){
                $.each(datalist.result,function(i,item){
                    if(i==0){
                        totalCount = item.totalNum;
                        if(_falg==0){
                            $('#callBackPager').extendPagination({
                                totalCount: totalCount,
                                limit: pageSize,
                                callback: function (curr) {
                                    callBackPagination(curr,1);
                                }
                            });
                        }
                    }
                    html.push('<div class="list-tr"><div class="plan-cell" style="cursor: pointer;text-decoration:underline;" onclick="gotoVipDetailPage('+item.vipid+')">' + item.username + '</div>');
                    html.push('<div class="plan-cell">'+item.usertelephone+'&nbsp;</div>');
                    html.push('<div class="plan-cell">'+item.usertelephone+'&nbsp;</div>');
                    html.push('<div class="plan-cell">'+item.ustatus+'</div>');
                    html.push('</div>');
                });
            }else{
                html.push("<div class='none-data-tips'>暂无数据</div>");
                totalCount = 0;
                $('#callBackPager').extendPagination({
                    totalCount: totalCount,
                    limit: pageSize,
                    callback: function (curr) {
                        callBackPagination(curr,1);
                    }
                });
            }
            $(".list-context").html(html.join(''));
            timerClearDiv();
        }
    });
}

/*转跳到新增账户页面*/
function getAddNationalPage(){
    window.location.href="/view/admin/system/getAddNationalPage";
}

/*转跳到会员详情页面*/
function gotoVipDetailPage(vipid){
    if(_falg){
        jConfirm("您有未保存内容，是否放弃操作？", "提示", function (r) {
            if (r) {
                window.location.href="/view/admin/vip/getVipDetailPage?userid="+userid+"&roleids="+roleids;
            }
        },1);
    }else{
        window.location.href="/view/admin/vip/getVipDetailPage?vipid="+vipid;
    }
}

/*返回账户列表页面*/
function gotoNationalListPage(){
    window.location.href="/view/admin/system/getNationalListPage";
}

/*跳转到修改页面*/
function gotoUpdateNationalPage(vipid){
    window.location.href="/view/admin/system/getUpdateNationalPage?vipid="+vipid+"&roleids="+roleids;
}

/**
 * 表单验证提交
 * @param _id 用户id,为空表示是添加提交
 */
function validFormData(_id){
    var _form = $("#national_form");
    if(_form.validationEngine('validate')){

        if(_id==""){
            $("input[name='userpassword']").val($.md5($("[name='upassword']").val()));
            $.ajax({
                type: "POST",
                dataType:"json",
                url:'/view/admin/system/addNationalUser',
                data:_form.serialize(),// 你的formid
                error: function(XMLHttpRequest, textStatus, errorThrown) {
                    jAlert("添加失败", "提示");
                },
                success: function(data) {
                    if(data.code == 0) {
                        jAlert("添加成功", "提示",function(r){
                            if(r){
                                gotoNationalListPage();
                            }
                        });
                    }else{
                        jAlert(data.failinfo,"提示");
                    }
                }
            });
        }else{
            $.ajax({
                type: "POST",
                dataType:"json",
                url:'/view/admin/system/updateNationalUser',
                data:_form.serialize(),// 你的formid
                error: function(XMLHttpRequest, textStatus, errorThrown) {
                    jAlert("修改失败", "提示");
                },
                success: function(data) {
                    if(data.code == 0) {
                        jAlert("修改成功", "提示",function(r){
                            if(r){
                                gotoNationalListPage();
                            }
                        });
                    }else{
                        jAlert(data.failinfo,"提示");
                    }
                }
            });
        }
    }
}
