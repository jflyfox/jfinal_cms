var comm_reply_userid = 0;
var comm_reply_username = '';

$(function() {
	// 回复
	$('#comment-btn').click(function(){
		var comment_content = $('[name="comment"]').val();
		var create_id = $('[name="create_id"]').val();
		var article_id = $('[name="article_id"]').val();
		// 去除回复提示
		if(comm_reply_username!='' && comment_content.indexOf(comm_reply_username) == 0){
			comment_content = comment_content.substr(comm_reply_username.length);
		}
		
		if(comment_content==''){
			alert('发布内容不能为空！');
			return;
		} else if (comment_content.length > 400) {
			alert('发布内容长度不能大于400！');
			return;
		}
		
		comment.oper_save(comment_content,article_id,comm_reply_userid,create_id);
	});
	
	// 回复内容为空时清除to_user
	$('[name="comment"]').change(function(){
		if($(this).val()=='') {
			comm_reply_userid = 0;
			comm_reply_username = '';
		}
	});
	
	
});

/**
 * 删除评论
 * 
 * @param comment_id
 */
function oper_del_comment(comment_id) {
	var article_id = $('[name="article_id"]').val();
	comment.oper_del(comment_id, article_id);
}


/**
 * 回复评论
 * @param comment_id
 * @param yo_userid
 */
function oper_reply_comment(userid,username){
	comm_reply_userid = userid;
	comm_reply_username = '回复 '+ username+ '：';
	$('[name="reply_userid"]').val(comm_reply_userid); // 设置回复人ID
	$('[name="comment"]').val(comm_reply_username);
	$('[name="comment"]').focus();
}
