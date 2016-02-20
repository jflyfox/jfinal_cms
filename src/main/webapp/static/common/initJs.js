jQuery(function($) {
	// 回车绑定查询按钮
	$(document).on('keydown', function (e) {
        var key = e.which;
        if (key == 13 && $(":input[name='search']").length > 0) {
            e.preventDefault();
            $(":input[name='search']").click();
        }
    });
	
	mySort();
	
	// 分页
	myInit();
	
});

function mySort(){
	var $orderColumn = $(":input[name='form.orderColumn']")||'';
	var $orderAsc = $(":input[name='form.orderAsc']")||'';
	if($orderColumn!=''){
		$("th.sorting[name='"+$orderColumn.val()+"']").removeClass('sorting').addClass('sorting_'+$orderAsc.val());
	}
	
	$('.sorting,.sorting_asc,.sorting_desc').on('click',function(){
			var className = $(this).attr('class');
			if(className=='sorting_asc') {
				mySortClear();
				$(this).removeClass('sorting').addClass('sorting_desc');
				$orderColumn.val($(this).attr('name'));
				$orderAsc.val('desc');
			}else if(className=='sorting_desc') {
				mySortClear();
				$(this).removeClass('sorting').addClass('sorting_asc');
				$orderColumn.val($(this).attr('name'));
				$orderAsc.val('asc');
			} else {
				mySortClear();
				$(this).removeClass('sorting').addClass('sorting_asc');
				$orderColumn.val();
				$orderColumn.val($(this).attr('name'));
				$orderAsc.val('asc');
			}
			if(typeof sorting!='undefined'&&sorting instanceof Function) sorting();
			else if(typeof oper.list!='undefined'&&oper.list instanceof Function) oper.list();
	});
}

/**
 * 还原排序状态
 */
function mySortClear(){
	$('[class^="sorting_"]').each(function(i){
		var className = $(this).attr('class');
		$(this).removeClass(className).addClass('sorting');
	});
}

function myInit(){
	var $paginator = $('#paginator');
	if($paginator.length > 0){
		// 分页
		var paginator_totalrecords = $('#paginator_totalrecords').val();
		paginator_totalrecords = parseInt(paginator_totalrecords);
		var paginator_recordsperpage = $('#paginator_recordsperpage').val();
		paginator_recordsperpage = parseInt(paginator_recordsperpage);
		var paginator_length = $('#paginator_length').val();
		paginator_length = parseInt(paginator_length);
		var paginator_pageno = $('#paginator_pageno').val();
		paginator_pageno = parseInt(paginator_pageno);
		// 分页
		$paginator.smartpaginator({ 
			totalrecords: paginator_totalrecords, 
			recordsperpage: paginator_recordsperpage, 
			length: paginator_length, 
			initval: paginator_pageno,
			next: '>', prev: '<', first: '<<', last: '>>', theme: jflyfox_theme, 
			controlsalways: true, onchange: function (newPage) {
				$('#paginator_pageno').val(newPage);
				if(typeof paginator!='undefined'&&paginator instanceof Function) paginator(newPage);
				else if(typeof oper.list!='undefined'&&oper.list instanceof Function) oper.list();
	    	}
	    });
	}
}

