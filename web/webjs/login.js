$(function(){
	$("#loginbtn").click(function(){submitValidate()});
	//不兼容placeholder属性处理
	JPlaceHolder.init();
});

/**
 * 用户登录按钮
 * @returns {boolean}
 */
function submitValidate(){
	var _username = $("#form-username").val();
	if(_username==""){
		$("#errormsg").html("请输入您的用户名");
		return false;
	}
	var _pwd = $("#form-password").val();
	if(_pwd==""){
		$("#errormsg").html("请输入您的密码");
		return false;
	}
	$.post('/login',{"username":_username,"userpassword": $.md5(_pwd)},function(state){
		if(state.code==0){
			window.location.href="/view/admin/index/forwardMainPage";
		}else{
			$("#errormsg").html(state.failinfo);
			return false;
		}
	});
}