$(function() {
	// 回顶部
	$(window).scroll( function() { 
		if($(this).scrollTop() >= 100 ){
			$("#scrollTop").show();
		} else {
			$("#scrollTop").hide();
		}
	} );
	
	if($(window).scrollTop() >= 100 ){
		$("#scrollTop").show();
	} else {
		$("#scrollTop").hide();
	}
	
	$("#scrollTop").click(function(){$(window).scrollTop(0);return false;});
	
	
	// 回车绑定查询按钮
	$('[name="search_header"]').on('keydown', function (e) {
        var key = e.which;
        if (key == 13 ) {
            e.preventDefault();
            search_form.action = jflyfox.BASE_PATH + "front/tags/"+ $('[name="search_header"]').val();
    		search_form.submit();
        }
    });
	
	$('#search_btn').on('click', function (e) {
		search_form.action = jflyfox.BASE_PATH + "front/tags/"+ $('[name="search_header"]').val();
		search_form.submit();
    });
	
	
	// 评论数获取
	comment.count();
});




function delblog(id) {
	var url = jflyfox.BASE_PATH + 'front/person/delblog/'+id;
	var title = '确认要删除该博文？';
	Confirm(title, function() {
		form1.action = url;
		form1.submit();
	});
}