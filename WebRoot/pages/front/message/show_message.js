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

function oper_reply_comment(comment_id,article_id,reply_userid,create_id,article_title){
	var comment_content = $("#reply_"+comment_id+"_"+article_id+" [name='comment']").val();
	if(comment_content==''){
		alert('发布内容不能为空！');
		return;
	} else if (comment_content.length > 400) {
		alert('发布内容长度不能大于400！');
		return;
	}
	// 去除回复提示
	var tmpContent = "回复：";
	if(comment_content.indexOf(tmpContent) == 0){
		comment_content = comment_content.substr(tmpContent.length);
	}
	
	
	comment.oper_save(comment_content,article_id,reply_userid,create_id,article_title);
	
	$('#reply_'+comment_id+"_"+article_id).hide();
}
/**
 * 展示回复操作
 * 
 * @param comment_id
 * @param article_id
 */
function show_reply_comment(comment_id,article_id){
	var com_id = '#reply_'+comment_id+"_"+article_id;
	if($(com_id).is(":hidden")){
	   $(com_id).show();    //如果元素为隐藏,则将它显现
	   $(com_id + ' [name="comment"]').val('回复：');
	   $(com_id + ' [name="comment"]').focus();
	}else{
	  $(com_id).hide();     //如果元素为显现,则将其隐藏
	}
}
