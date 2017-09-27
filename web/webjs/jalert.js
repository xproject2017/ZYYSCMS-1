var alertBgObj ="";
var contextObj ="";

var sm_btn = "width: 46px;height: 30px;border-radius: 3px;cursor:pointer;background-color: #fff;border: 1px solid #ccc;";
var pri_btn = "background-color:#428bca;border: 1px solid #357ebd;color:#fff;min-width: 46px;width:auto;height: 30px;border-radius: 3px;cursor:pointer;";

/**
 * 弹出框
 * @param _context 弹出内容
 * @param _title 弹出标题
 * @param _callback callback
 */
function jAlert(_context,_title,_callback){
    var _w = 300,_h=135;
    var iWidth =  $(window).width();
    var iHeight =  $(window).height();
    var iTop = $(window).scrollTop();
    var iLeft = $(window).scrollLeft();
    alertBgObj = document.createElement('div');
    contextObj = document.createElement('div');
    alertBgObj.style.cssText="width:"+$(window).width()+"px;height:"+$(document).height()+"px;background:#ececec;position:absolute;top:0;left:0;z-index:500;opacity:0.2;filter:alpha(opacity =20);";
    document.body.appendChild(alertBgObj);
    contextObj.style.cssText = "position:absolute;font-size:12px;top:" + (iTop + Math.abs((iHeight - _h) / 2)) + "px;left:" + (iLeft + Math.abs((iWidth - _w-100) / 2))  + "px;width:" + _w + "px;height:" + _h + "px;z-index:501;border:1px solid #D3D6DD;border-radius:6px;background-color:#fff;";
    var result = [];
    if(undefined==_title || ""==_title)
        _title = "提示";
    result.push('<div style="float: left;width: 100%;height: 40px;line-height: 40px;font-weight: bold;/*border-bottom: 1px solid #f1f1f1;*/text-align: center;">'+_title+'</div>');
    result.push('<div id="popContext" style="float: left;width: 100%;height: 50px;/*line-height: 50px;*/padding-top:12px;text-align: center;">'+_context+'</div>');
    result.push('<div style="float: left;width: 100%;text-align: center;/*border-top: 1px solid #f1f1f1;*/height: 40px;">');
    result.push('<button type="button" style="'+sm_btn+'" id="okAlert">确定</button>');
    result.push('</div>');
    contextObj.innerHTML= result.join('');
    document.body.appendChild(contextObj);
    $("#okAlert").click( function() {
        hideAlert();
        if(undefined!=_callback && ""!=_callback)
            _callback(true);
    });
}

function jConfirm(_context,_title,_callback,_falg){
    var _w = 300,_h=135;
    var iWidth =  $(window).width();
    var iHeight =  $(window).height();
    var iTop = $(window).scrollTop();
    var iLeft = $(window).scrollLeft();
    alertBgObj = document.createElement('div');
    contextObj = document.createElement('div');
    alertBgObj.style.cssText="width:"+$(window).width()+"px;height:"+$(document).height()+"px;background:#ececec;position:absolute;top:0;left:0;z-index:500;opacity:0.2;filter:alpha(opacity =20);";
    document.body.appendChild(alertBgObj);
    contextObj.style.cssText = "position:absolute;font-size:12px;top:" + (iTop + Math.abs((iHeight - _h) / 2)) + "px;left:" + (iLeft + Math.abs((iWidth - _w-100) / 2))  + "px;width:" + _w + "px;height:" + _h + "px;z-index:501;border:1px solid #D3D6DD;border-radius:6px;background-color:#fff;";
    var result = [];
    if(undefined==_title || ""==_title)
        _title = "提示";
    result.push('<div style="float: left;width: 100%;height: 40px;line-height: 40px;font-weight: bold;/*border-bottom: 1px solid #f1f1f1;*/text-align: center;">'+_title+'</div>');
    result.push('<div id="popContext" style="float: left;width: 100%;height: 50px;/*line-height: 50px;*/padding-top:12px;text-align: center;">'+_context+'</div>');
    result.push('<div style="float: left;width: 100%;text-align: center;/*border-top: 1px solid #f1f1f1;*/height: 40px;">');
    if(_falg){
        result.push('<button type="button" style="'+sm_btn+'" id="okAlert">放弃</button>');
        result.push('<button type="button" style="'+pri_btn+'margin-left: 20px;width: 75px;"  id="cancelAlert">继续编辑</button>');
    }else{
        result.push('<button type="button" style="'+pri_btn+'" id="okAlert">确定</button>');
        result.push('<button type="button" style="'+sm_btn+'margin-left: 20px;" id="cancelAlert">取消</button>');
    }
    result.push('</div>');
    contextObj.innerHTML= result.join('');
    document.body.appendChild(contextObj);
    $("#okAlert").click( function() {
        hideAlert();
        _callback(true);
    });
    $("#cancelAlert").click( function() {
        hideAlert();
        _callback(false);
    });
}

/**
 * 关闭pop弹出框
 */
function hideAlert(){
    if(alertBgObj!="")
        document.body.removeChild(alertBgObj);
    if(contextObj!="")
        document.body.removeChild(contextObj);
}

var imageBgObj ="";
var loadBgObj ="";
//添加背景遮住层
function addBgDiv(_context){
    var iTop = $(window).scrollTop();
    imageBgObj = document.createElement('div');
    loadBgObj = document.createElement('div');
    imageBgObj.style.cssText="width:"+$(window).width()+"px;height:"+$(document).height()+"px;background:#eaeaea;position:absolute;top:0;left:0;z-index:300;";
    document.body.appendChild(imageBgObj);
    loadBgObj.style.cssText="top:" + ((iTop + Math.abs(($(window).height()-200) / 2))) + "px;left:" + (($(window).width()-200)/ 2) + "px;font-size:12px;background:#fff;width:auto;border:1px solid #777;line-height:25px;height:25px;position:absolute;z-index:301;";
    loadBgObj.innerHTML= "&nbsp;"+_context+"&nbsp;";
    document.body.appendChild(loadBgObj);
}

function removeBgDiv(){
    try{
        if(imageBgObj!="")
            document.body.removeChild(imageBgObj);
        if(loadBgObj!="")
            document.body.removeChild(loadBgObj);
    }catch (e){}

}

//自动隐藏div
function timerClearDiv(_timer){
    /*setTimeout(function(){
     removeBgDiv();
     $(".right-main-context:first-child").show();
     },100);*/
    if(undefined==_timer){
        removeBgDiv();
        $(".right-main-context:hidden").show();
    }else{
        setTimeout(function(){
            removeBgDiv();
            $(".right-main-context:hidden").show();
        },1000);
    }
}
