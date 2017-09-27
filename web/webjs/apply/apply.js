var totalCount = 0;
var pageSize = 10;
/**
 * 构造list数据
 * @param currPage 当前页数(从1开始)
 * @param _falg 0表示第一次加载，需要构造分页控件。1表示不需要
 */
function callBackPagination(currPage, _falg) {
    $.ajax({
        type: "POST",
        dataType: "json",
        data: {"currentPage": (currPage - 1), "pageSize": pageSize},
        url: '/view/admin/apply/getApplyListData',
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
                            callBackPagination(curr, 1);
                        }
                    });
                }
                $.each(datalist.result, function (i, item) {
                    html.push('<div class="list-tr">');
                    html.push('<div class="plan-cell"><span style="cursor: pointer;text-decoration: underline;" onclick="getUpdateDataPage('+item.id+')">'+item.applyname+'</span></div>');
                    html.push('<div class="plan-cell">' + item.applyphone + '</div>');
                    html.push('<div class="plan-cell">' + item.educationstr +'</div>');
                    html.push('<div class="plan-cell">' + item.applyposition +'</div>');
                    html.push('<div class="plan-cell">' +item.flagstr+ '</div>');
                    html.push('</div>');
                });
            } else {
                html.push("<div class='none-data-tips'>暂无数据</div>");
                $('#callBackPager').extendPagination({
                    totalCount: 0,
                    limit: pageSize,
                    callback: function (curr) {
                        callBackPagination(curr, 1);
                    }
                });
            }
            $(".list-context").html(html.join(''));
            timerClearDiv();
        }
    });
}

function getUpdateDataPage(id){
    window.location.href="/view/admin/apply/updateApplyPage?id="+id+"&nodetext="+$("h4").html();
}

function updateFormData(){
    var _form = $("#data_form");
    if(_form.validationEngine('validate')){
        $.ajax({
            type: "POST",
            dataType:"json",
            url:'/view/admin/apply/updateData',
            data:_form.serialize(),// 你的formid
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                jAlert("修改失败", "提示");
            },
            success: function(data) {
                if(data.code == 0) {
                    jAlert("修改成功", "提示",function(r){
                        if(r){
                            window.location.href = "/view/admin/apply/getApplyListPage?nodetext="+$("h4").html();
                        }
                    });
                }else{
                    jAlert(data.failinfo,"提示");
                }
            }
        });
    }
}

function downloadFile(_id){
    $("#download").attr("src",'/view/downloadFile?id='+_id);
}

function initData(){
    $("#data_form").validationEngine("attach", {
        'promptPosition': 'topLeft',
        autoHidePrompt: true,
        autoHideDelay: 3000
    });
}

