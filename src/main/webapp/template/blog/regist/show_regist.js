function loadPicimageCode() {
	document.getElementById("picimageCode").src = jflyfox.BASE_PATH + 'front/image_code?ran=' + Math.random();
}

function oper_save(){
	if($('[name="model.email"]').val()==''){
		alert('邮箱不能为空！');
		return;
	}
	if($('[name="model.realname"]').val()==''){
		alert('昵称不能为空！');
		return;
	}
	var pwd = $('[name="password"]').val();
	var pwd2 = $('[name="password2"]').val();
	if(pwd==''){
		alert('密码不能为空！');
		return;
	}
	if(pwd2==''){
		alert('确认密码不能为空！');
		return;
	}
	if(pwd.length < 6 ){
		alert('密码长度必须大于等于6');
		return;
	}
	if(pwd != pwd2){
		alert('两次密码不一致！');
		return;
	}
	if($('[name="imageCode"]').val()==''){
		alert('验证码不能为空！');
		return;
	}
	
	jQuery.ajax({
		type:'POST',
		url:jflyfox.BASE_PATH + 'front/regist/save',
		data:$("form").serialize(),
		success:function(data){
			if(data.status==1){
				alert('保存成功');
				var prePage = $('[name="pre_page"]').val();
				if (prePage=='') {
					prePage = "/";
				}
				window.top.location.href = prePage;
			} else {
				loadPicimageCode();
				$('[name="imageCode"]').val('');
				$('[name="password"]').val('');
				$('[name="password2"]').val('');
				alert('保存失败：'+data.msg);
			}
		},
		error:function(html){
			var flag = (typeof console != 'undefined');
			if(flag) console.log("服务器忙，提交数据失败，代码:" +html.status+ "，请联系管理员！");
			alert("服务器忙，提交数据失败，请联系管理员！");
		}
	});
}