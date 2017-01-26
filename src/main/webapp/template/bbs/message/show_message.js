var comm_reply_userid = 0;
var comm_reply_username = '';

$(function() {
	
	// 回复内容为空时清除to_user
	$('[name="comment"]').change(function(){
		if($(this).val()=='') {
			comm_reply_userid = 0;
			comm_reply_username = '';
		}
	});
	
	// 点击为已读
	$('.item-top').click(function(){
		$(this).removeClass('comment-no-read');
	});
	
	
});
