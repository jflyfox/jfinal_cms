$(function() {
	// 头像展示
	$('[name="model.title_url"]').change(function(){
		var pic = $(this).val() ;
		if(pic == '') {
			pic = jflyfox.BASE_PATH + 'static/images/user/user.png';
		}
		
		$('#title_pic').attr('src',pic);
	});
});

function oper_save(){
	if($('[name="old_password"]').val()==''){
		alert('密码不能为空');
		return;
	}
	
	jQuery.ajax({
		type:'POST',
		url:jflyfox.BASE_PATH + 'front/person/save',
		data:$("form").serialize(),
		success:function(data){
			if(data.status==1){
				alert('保存成功');
			} else {
				alert('保存失败：'+data.msg);
			}
			$('[name="old_password"]').val('');
			$('[name="new_password"]').val('');
			$('[name="new_password2"]').val('');
		},
		error:function(html){
			var flag = (typeof console != 'undefined');
			if(flag) console.log("服务器忙，提交数据失败，代码:" +html.status+ "，请联系管理员！");
			alert("服务器忙，提交数据失败，请联系管理员！");
		}
	});
}