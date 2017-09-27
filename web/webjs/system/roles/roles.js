var totalCount = 0;
var pageSize = 10;

/**
 * 构造list数据
 * @param currPage 当前页数(从1开始)
 * @param _falg 0表示第一次加载，需要构造分页控件。1表示不需要
 */
function callBackPagination(currPage,_falg) {
    var cname = $("#cpname").val();
    $.ajax({
        type: "POST",
        dataType:"json",
        data:{"rolename":cname,"currentPage":(currPage-1),"pageSize":pageSize},
        url:'/view/admin/system/getRolesListData',
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
                    html.push('<div class="list-tr"><div class="role-name" style="cursor: pointer;text-decoration:underline;" onclick="gotoRolesDetailPage('+item.roleid+')">' + item.rolename + '</div>');
                    html.push('<div class="role-authority">'+item.menuNames+'</div>');
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
function getAddRolesPage(){
    window.location.href="/view/admin/system/getAddRolesPage";
}

/*转跳到账户详情页面*/
function gotoRolesDetailPage(userid,_falg){
    if(_falg){
        jConfirm("您有未保存内容，是否放弃操作？", "提示", function (r) {
            if (r) {
                window.location.href="/view/admin/system/getRolesDetailPage?roleid="+userid;
            }
        },1);
    }else{
        window.location.href="/view/admin/system/getRolesDetailPage?roleid="+userid;
    }
}

/*返回账户列表页面*/
function gotoRolesListPage(){
    window.location.href="/view/admin/system/getRolesListPage";
}

/*跳转到修改页面*/
function gotoUpdateRolesPage(userid){
    window.location.href="/view/admin/system/getUpdateRolesPage?roleid="+userid;
}

/**
 * 表单验证提交
 * @param _id 用户id,为空表示是添加提交
 */
function validFormData(_id){
    var _form = $("#national_form");
    if(_form.validationEngine('validate')){
        var menuids = "";
        $(".block-account").each(function(){
            var second = "";
            $(this).find(".second-level").each(function(){
                if($(this).find("input[type='checkbox']").is(":checked")){
                    second += $(this).find("input[type='checkbox']").val()+",";
                }
            });
            if(second!=""){
                second += $(this).find(".first-level").find("input[type='checkbox']").val()+",";
                menuids += second;
            }
        });

        if(menuids==""){
            jAlert("至少选择一个权限","提示");
            return false;
        }
        $("input[name='menuids']").val(menuids);
        if(_id==""){
            $.ajax({
                type: "POST",
                dataType:"json",
                url:'/view/admin/system/addRole',
                data:_form.serialize(),// 你的formid
                error: function(XMLHttpRequest, textStatus, errorThrown) {
                    jAlert("添加失败", "提示");
                },
                success: function(data) {
                    if(data.code == 0) {
                        jAlert("添加成功", "提示",function(r){
                            if(r){
                                gotoRolesListPage();
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
                url:'/view/admin/system/updateRole',
                data:_form.serialize(),// 你的formid
                error: function(XMLHttpRequest, textStatus, errorThrown) {
                    jAlert("修改失败", "提示");
                },
                success: function(data) {
                    if(data.code == 0) {
                        jAlert("修改成功", "提示",function(r){
                            if(r){
                                gotoRolesListPage();
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

