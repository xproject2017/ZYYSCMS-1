var bgObj ="";
var htmlBgObj ="";

var sm_btn = "width: 46px;height: 30px;border-radius: 3px;cursor:pointer;background-color: #fff;border: 1px solid #ccc;";
var pri_btn = "background-color:#428bca;border: 1px solid #357ebd;color:#fff;min-width: 46px;width:auto;height: 30px;border-radius: 3px;cursor:pointer;";


/**
 * 弹出框
 * @param _context 弹出内容
 * @param _title 弹出标题
 * @param _w 弹出高度
 * @param _h 弹出宽度
 * @param _callback 点确定后回调方法
 * @param _btnFont 按钮文字
 * @param _cancelback 取消按钮回调
 * @param _cancelFont 按钮文字
 */
function alertWin(_context,_title,_w,_h,_callback,_btnFont,_cancelback,_cancelFont){
    var iWidth =  $(window).width();
    var iHeight =  $(window).height();
    var iTop = $(window).scrollTop();
    var iLeft = $(window).scrollLeft();
    bgObj = document.createElement('div');
    htmlBgObj = document.createElement('div');
    bgObj.style.cssText="width:"+$(window).width()+"px;height:"+$(document).height()+"px;background:#000;position:absolute;top:0;left:0;z-index:200;opacity:0.2;filter:alpha(opacity =20);";
    document.body.appendChild(bgObj);
    htmlBgObj.style.cssText = "position:absolute;top:" + (iTop + Math.abs((iHeight - _h) / 2)) + "px;left:" + (iLeft + Math.abs((iWidth - _w-100) / 2))  + "px;width:" + _w + "px;height:" + _h + "px;z-index:201;border:1px solid #D3D6DD;border-radius:6px;background-color:#fff;";

    var result = [];
    result.push('<div style="float: left;width: 100%;height: 40px;line-height: 40px;border-bottom: 1px solid #f1f1f1;">');
    result.push('<div style="float: left;width: 95%;"><span style="padding-left: 15px;">'+_title+'</span></div>');
    //result.push('<div style="float: left;width: 95%;">&nbsp;</div>');
    result.push('<div style="float: left;width: 5%;cursor: pointer;" onclick="closePop()"> &times; </div>');
    result.push('</div>');
    result.push('<div id="popContext" style="float: left;width: 100%;padding-left:15px;overflow:auto;height: '+(_h-100)+'px">'+_context+'</div>');
    result.push('<div style="float: left;width: 100%;text-align: center;border-top: 1px solid #f1f1f1;height: 50px;line-height: 50px;">');
    if(_callback!=undefined && _callback!=""){
        if(_btnFont==undefined || _btnFont==""){
            _btnFont = '确定';
        }
        result.push('<button type="button" style="'+pri_btn+'" id="surebut" onclick="'+_callback+'">'+_btnFont+'</button>');
    }

    result.push('<button type="button" id="cancalbut"  style="margin-left: 20px;'+sm_btn+'" ');
    if(_cancelback!=undefined && _cancelback!=""){
        result.push('onclick="'+_cancelback+'"> ');
    }else{
        result.push('onclick="closePop()"> ');
    }
    if(_cancelFont==undefined || _cancelFont ==""){
        _cancelFont = '取消';
    }
    result.push(_cancelFont+'</button>');
    result.push('</div>');
    htmlBgObj.innerHTML= result.join('');
    document.body.appendChild(htmlBgObj);
}

/**
 * 关闭pop弹出框
 */
function closePop(){
    if(bgObj!="")
        document.body.removeChild(bgObj);
    if(htmlBgObj!="")
        document.body.removeChild(htmlBgObj);
}

/**
 * 修改弹出框内容
 * @param _context 内容
 * @param _callback 确定回调
 * @param _btnFont 确定按钮文字
 * @param _cancelback 取消回调
 * @param _cancelFont 取消按钮文字
 */
function updatePopContext(_context,_callback,_btnFont,_cancelback,_cancelFont){
    if(_context!=undefined &&_context!="")
        $("#popContext").html(_context);
    var _btn = $("#surebut");
    if(_callback!=undefined &&_callback!="")
        _btn.attr("onclick",_callback);
    if(_btnFont!=undefined &&_btnFont!="")
        _btn.html(_btnFont);

    var _cancel = $("#cancalbut");
    if(_cancelback!=undefined && _callback!="")
        _cancel.attr("onclick",_callback);
    if(_cancelFont!=undefined && _cancelFont!="")
        _cancel.html(_cancelFont);
}