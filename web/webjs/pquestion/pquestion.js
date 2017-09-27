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
        url: '/view/admin/pquestion/getPquestionListData',
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
                    html.push('<div class="needbanner"><span style="cursor: pointer;text-decoration: underline;" onclick="getUpdateDataPage('+item.mesgid+')">'+item.mtitle+'</span></div>');
                    html.push('<div class="needbanner">' + item.mtags +'</div>');
                    var _str =  item.mbanner==0?'否':'是';
                    html.push('<div class="needbanner">' +_str + '</div>');
                    html.push('<div class="needbanner"><a href="javascript:delData('+item.mesgid+')">删除</a> </div>');
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

function delData(_mesgid){
    $.post("/view/admin/pquestion/delData",{"mesgid":_mesgid},function(data){
        if(data.code == 0) {
            jAlert("删除成功","",function(r){
                if(r){
                    callBackPagination(1,0);
                }
            });
        }else{
            jAlert(data.failinfo,"提示");
        }
    });
}

function getUpdateDataPage(mesgid){
    window.location.href="/view/admin/pquestion/updatePquestionPage?mesgid="+mesgid+"&nodetext="+$("h4").html();
}

function getAddDataPage(){
    window.location.href="/view/admin/pquestion/addPquestionPage?navigationid1="+$("[name='navigationid1']").val()+"&navigationid2="+$("[name='navigationid2']").val()+"&nodetext="+$("h4").html();
}

function updateFormData(){
    var _form = $("#data_form");
    if(_form.validationEngine('validate')){
        var editorText = $("iframe").contents().find("body");
        var contents = editorText.html();
        if(contents==""){
            jAlert("内容不能为空");
            return false;
        }
        $("#mes").val(contents);
        var formData = new FormData(_form[0]);
        $.ajax({
            type: "POST",
            dataType:"json",
            url:'/view/admin/pquestion/updateData',
            data:formData,// 你的formid
            processData: false,
            contentType: false,
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                jAlert("修改失败", "提示");
            },
            success: function(data) {
                if(data.code == 0) {
                    jAlert("修改成功", "提示",function(r){
                        if(r){
                            window.location.href = "/view/admin/pquestion/getPquestionListPage?navigationid1="+$("[name='navigationid1']").val()+"&navigationid2="+$("[name='navigationid2']").val()+"&nodetext="+$("h4").html();
                        }
                    });
                }else{
                    jAlert(data.failinfo,"提示");
                }
            }
        });
    }
}

function addFormData(){
    var _form = $("#data_form");
    if(_form.validationEngine('validate')){
        var editorText = $("iframe").contents().find("body");
        var contents = editorText.html();
        if(contents==""){
            jAlert("内容不能为空");
            return false;
        }
        $("#mes").val(contents);
        var formData = new FormData(_form[0]);
        $.ajax({
            type: "POST",
            dataType:"json",
            url:'/view/admin/pquestion/addData',
            data:formData,// 你的formid
            processData: false,
            contentType: false,
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                jAlert("提交失败", "提示");
            },
            success: function(data) {
                if(data.code == 0) {
                    jAlert("提交成功", "提示",function(r){
                        if(r){
                            window.location.href = "/view/admin/pquestion/getPquestionListPage?navigationid1="+$("[name='navigationid1']").val()+"&navigationid2="+$("[name='navigationid2']").val()+"&nodetext="+$("h4").html();
                        }
                    });
                }else{
                    jAlert(data.failinfo,"提示");
                }
            }
        });
    }
}

function initData(){
    $("#data_form").validationEngine("attach", {
        'promptPosition': 'topLeft',
        autoHidePrompt: true,
        autoHideDelay: 3000
    });
}