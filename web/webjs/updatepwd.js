var pwdObj ="";
var pwdBgObj ="";

/**
 * 弹出框
 * @param _context 弹出内容
 * @param _title 弹出标题
 * @param _w 弹出高度
 * @param _h 弹出宽度
 * @param _callback 点确定后回调方法
 */
function alertPwdWin(_context,_title,_w,_h,_callback){
    var iWidth =  $(window).width();
    var iHeight =  $(window).height();
    var iTop = $(window).scrollTop();
    var iLeft = $(window).scrollLeft();
    pwdObj = document.createElement('div');
    pwdBgObj = document.createElement('div');
    pwdObj.style.cssText="width:"+$(window).width()+"px;height:"+$(document).height()+"px;background:#000;position:absolute;top:0;left:0;z-index:400;opacity:0.2;filter:alpha(opacity =20);";
    document.body.appendChild(pwdObj);
    pwdBgObj.style.cssText = "position:absolute;top:" + (iTop + Math.abs((iHeight - _h) / 2)) + "px;left:" + (iLeft + Math.abs((iWidth - _w-100) / 2))  + "px;width:" + _w + "px;height:" + _h + "px;z-index:402;border:1px solid #D3D6DD;border-radius:6px;background-color:#fff;";

    var result = [];
    result.push('<div style="float: left;width: 100%;height: 40px;line-height: 40px;/*border-bottom: 1px solid #f1f1f1;*/">');
    //result.push('<div style="float: left;width: 95%;"><span style="padding-left: 15px;">'+_title+'</span></div>');
    result.push('<div style="float: left;width: 95%;">&nbsp;</div>');
    result.push('<div style="float: left;width: 5%;cursor: pointer;" onclick="closePwdPop()"> &times; </div>');
    result.push('</div>');
    result.push('<div id="popContext" style="float: left;width: 100%;padding-left:15px;overflow:auto;height: '+(_h-100)+'px">'+_context+'</div>');
    result.push('<div style="float: left;width: 100%;text-align: center;/*border-top: 1px solid #f1f1f1;*/height: 50px;line-height: 50px;">');
    result.push('<button type="button" class="btn btn-primary" onclick="'+_callback+'">确定</button>');
    result.push('<button type="button" class="btn btn-default" style="margin-left: 20px;" onclick="closePwdPop()"> 取消</button>');
    result.push('</div>');
    pwdBgObj.innerHTML= result.join('');
    document.body.appendChild(pwdBgObj);
}

/**
 * 关闭pop弹出框
 */
function closePwdPop(){
    if(pwdObj!="")
        document.body.removeChild(pwdObj);
    if(pwdBgObj!="")
        document.body.removeChild(pwdBgObj);
}
