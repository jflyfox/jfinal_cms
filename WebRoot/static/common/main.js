/*********************************************/
/* Trim the both side blank of the string    */
/*********************************************/
function trim(s)
{
	return trimRight(trimLeft(s));
}
/****************************************/
/* Trim the left blank of the string    */
/****************************************/
function trimLeft(s) {
	while (s.charAt(0) ==" " ||s.charAt(0) =="" ){
		s = s.substr(1,s.length-1);
	}
	return s;
}
/*****************************************/
/* Trim the right blank of the string    */
/*****************************************/
function trimRight(s) {
	while (s.charAt(s.length-1) == " " || s.charAt(s.length-1) == "")	{
		s = s.substr(0,s.length-1);
	}
	return s;
}

/**
 * 获取窗体高度
 */
function getWindowHeight() {
	var windowHeight = 0;
	if (typeof (window.innerHeight) == "number") {
		windowHeight = window.innerHeight;
	} else {
		if (document.documentElement && document.documentElement.clientHeight) {
			windowHeight = document.documentElement.clientHeight;
		} else {
			if (document.body && document.body.clientHeight) {
				windowHeight = document.body.clientHeight;
			}
		}
	}
	return windowHeight;
}
/**
 * 获取窗体宽度
 */
function getWindowWidth() {
	var windowWidth = 0;
	if (typeof (window.innerWidth) == "number") {
		windowWidth = window.innerWidth;
	} else {
		if (document.documentElement && document.documentElement.clientWidth) {
			windowWidth = document.documentElement.clientWidth;
		} else {
			if (document.body && document.body.clientWidth) {
				windowWidth = document.body.clientWidth;
			}
		}
	}
	return windowWidth;
}
/************************ jquery **************************/

/**
 * 使用ajax提交数据
 */
function ajax_post(the_url,the_param,succ_callback){
	jQuery.ajax({
		type:'POST',
		url:the_url,
		data:the_param,
		success:succ_callback,
		error:function(html){
			var flag = (typeof console != 'undefined');
			if(flag) console.log("服务器忙，提交数据失败，代码:" +html.status+ "，请联系管理员！");
			//alert("服务器忙，提交数据失败，代码:" +html.status+ "，请联系管理员！");
		}
	});
}

/**
 * 避免传递空""值过来value时判断为是取值操作而不是赋值操作
 */
function jqName(name,value){
	if(value === undefined) {
		return jQuery(":input[name='"+name+"']").val()||'';
	}else{
		return jQuery(":input[name='"+name+"']").val(value);
	}
}

/************************** form处理 *****************************/
/**
 * 重置表单
 */
function resetForm(){
	$(".tableSearch input:not(.btn1)").val("");
	$(".tableSearch select").val("-1");
}

function console_log(str){
	var flag = (typeof console != 'undefined');
	if(flag) console.log('flyfox--> '+str);
}

/////////////////////////bootstrap 扩展////////////////////////////
var modal = {
		iframe:function (url,height,title){
			title = title || '信息';
			height = height || '450';
			$('#modal-iframe').attr("src",url); 
			$('#modal-iframe').height(height); 
			$('.modal #myModalLabel').html(title);
			$('#myModal').modal('show');
		}
};

