var comm_reply_userid = 0;
var comm_reply_username = '';

var ue;

$(document).ready(function(){
	ue = UE.getEditor('editor',{
		initialFrameHeight:100  //初始化编辑器高度,默认200
		,compressSide:1
		,imageScaleEnabled:false // 图片不允许拖拽
		,autoHeightEnabled:true // 自动长高
   		, toolbars: [[
                      'source', '|',
                     'bold', 'italic', 'underline', 'strikethrough', 'insertorderedlist', 'insertunorderedlist', 'paragraph', '|',
                     'justifyleft', 'justifycenter', 'justifyright', 'justifyjustify', '|',
                     'link', 'unlink', '|',
                     'simpleupload', 'insertimage', 'emotion' , 'insertcode'
                 ]]
	});
	
});

$(function() {
	// 回复
	$('#comment-btn').click(function(){
		// var comment_content = $('[name="comment"]').val();
		var comment_content = ue.getContent();
		if(!ue.hasContents()){
			Alert('请输入评论内容');
			return false;
		}
		if(ue.getContentTxt().length > 5000){
			Alert('评论（'+ue.getContentTxt().length+'字）文字过长，请输入小于5000个字');
			return false;
		}
		var create_id = $('[name="create_id"]').val();
		var article_id = $('[name="article_id"]').val();
		// 去除回复提示
		if(comm_reply_username!='' && comment_content.indexOf(comm_reply_username) == 0){
			comment_content = comment_content.substr(comm_reply_username.length);
		}
		
		// 空字符串处理
		comment_content = comment_content || '';
		comment_content = comment_content.replace(/(^\s*)|(\s*$)/g, "");
		
		if(comment_content==''){
			alert('发布内容不能为空！');
			return;
		} else if (comment_content.length > 5000) {
			alert('发布内容长度不能大于5000！');
			return;
		}
		
		comment.oper_save(comment_content,article_id,comm_reply_userid,create_id);
		
		// 清空内容
		ue.setContent("");
	});
	
	// 回复内容为空时清除to_user
	$('[name="comment"]').change(function(){
		if($(this).val()=='') {
			comm_reply_userid = 0;
			comm_reply_username = '';
		}
	});
	
	
});


