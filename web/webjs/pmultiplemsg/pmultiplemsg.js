var totalCount = 0;
var pageSize = 10;
/**
 * 构造list数据
 * @param currPage 当前页数(从1开始)
 * @param _falg 0表示第一次加载，需要构造分页控件。1表示不需要
 */
function callBackPagination(currPage, _falg) {
    var navigationid1 = $("[name='navigationid1']").val();
    var navigationid2 = $("[name='navigationid2']").val();
    $.ajax({
        type: "POST",
        dataType: "json",
        data: {"currentPage": (currPage - 1), "pageSize": pageSize,"navigationid1":navigationid1,"navigationid2":navigationid2},
        url: '/view/admin/pmultiplemsg/getPmultiplemsgListData',
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            $(".list-context").html("<div class='none-data-tips'>查询数据失败</div>");
            timerClearDiv();
        },
        success: function (datalist) {
            var html = [];
            if (datalist.code == 0) {
                totalCount = datalist.result[0].totalNum;
                if (_falg == 0) {
                    if(!ifOneSign()) {
                        $('#callBackPager').extendPagination({
                            totalCount: totalCount,
                            limit: pageSize,
                            callback: function (curr) {
                                callBackPagination(curr, 1);
                            }
                        });
                    }else{
                        $(".add-btn").hide();
                    }
                }
                $.each(datalist.result, function (i, item) {
                    html.push('<div class="list-tr">');
                    if(navigationid2 == 1 || navigationid2 == 14) {
                        html.push('<div class="uneed"><span style="cursor: pointer;text-decoration: underline;" onclick="getUpdateDataPage('+item.mesgid+')">'+item.mtitle+'</span></div>');
                        html.push('<div class="uneed">' + item.mtags +'</div>');
                        html.push('<div class="uneed"><a href="javascript:delData('+item.mesgid+')">删除</a> </div>');
                        html.push('</div>');
                    }else if(navigationid2 ==2 || navigationid2 ==3 || navigationid2 ==4){
                        html.push('<div class="needbanner"><span style="cursor: pointer;text-decoration: underline;" onclick="getUpdateDataPage('+item.mesgid+')">'+item.mtitle+'</span></div>');
                        html.push('<div class="needbanner">' + item.mtags +'</div>');
                        var _str =  item.mbanner==0?'否':'是';
                        html.push('<div class="needbanner">' +_str + '</div>');
                        html.push('<div class="needbanner"><a href="javascript:delData('+item.mesgid+')">删除</a> </div>');
                        html.push('</div>');
                    }else{
                        html.push('<div class="plan-cell"><span style="cursor: pointer;text-decoration: underline;" onclick="getUpdateDataPage('+item.mesgid+')">'+item.mtitle+'</span></div>');
                        html.push('<div class="plan-cell">' + item.mtags +'</div>');
                        var _str =  item.mbanner==0?'否':'是';
                        html.push('<div class="plan-cell">' +_str + '</div>');
                        _str = item.mmsgc==0?'否':'是';
                        html.push('<div class="plan-cell">' + _str +'</div>');
                        html.push('<div class="plan-cell"><a href="javascript:delData('+item.mesgid+')">删除</a> </div>');
                        html.push('</div>');
                    }
                });
            } else {
                html.push("<div class='none-data-tips'>暂无数据</div>");
                $(".add-btn").show();
                if(!ifOneSign()){
                    $('#callBackPager').extendPagination({
                        totalCount: 0,
                        limit: pageSize,
                        callback: function (curr) {
                            callBackPagination(curr, 1);
                        }
                    });
                }
            }
            $(".list-context").html(html.join(''));
            timerClearDiv();
        }
    });
}

//true表示需要显示添加按钮，false表示不需要显示
function ifOneSign(){
    var navigationid1 = $("[name='navigationid1']").val();
    var navigationid2 = $("[name='navigationid2']").val();
    if(navigationid1==2 ){
        if(navigationid2==0 || navigationid2==1 || navigationid2==2){
            return true;
        }
    }else if(navigationid1==5){
        if(navigationid2==0 || navigationid2==14){
            return true;
        }
    }
    return false;
}

function delData(_mesgid){
    $.post("/view/admin/pmultiplemsg/delData",{"mesgid":_mesgid},function(data){
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
    window.location.href="/view/admin/pmultiplemsg/updatePmultiplemsgPage?mesgid="+mesgid+"&nodetext="+$("h4").html();
}

function getAddDataPage(){
    window.location.href="/view/admin/pmultiplemsg/addPmultiplemsgPage?navigationid1="+$("[name='navigationid1']").val()+"&navigationid2="+$("[name='navigationid2']").val()+"&nodetext="+$("h4").html();
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
            url:'/view/admin/pmultiplemsg/updateData',
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
                            window.location.href = "/view/admin/pmultiplemsg/getPmultiplemsgListPage?navigationid1="+$("[name='navigationid1']").val()+"&navigationid2="+$("[name='navigationid2']").val()+"&nodetext="+$("h4").html();
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
            url:'/view/admin/pmultiplemsg/addData',
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
                            window.location.href = "/view/admin/pmultiplemsg/getPmultiplemsgListPage?navigationid1="+$("[name='navigationid1']").val()+"&navigationid2="+$("[name='navigationid2']").val()+"&nodetext="+$("h4").html();
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