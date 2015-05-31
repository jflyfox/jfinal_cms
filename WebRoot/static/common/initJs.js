jQuery(function($) {
	// 回车绑定查询按钮
	$(document).on('keydown', function (e) {
        var key = e.which;
        if (key == 13 && $(":input[name='search']").length > 0) {
            e.preventDefault();
            $(":input[name='search']").click();
        }
    });
	
	myInit();
});

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
			next: '>', prev: '<', first: '<<', last: '>>', theme: 'blue', 
			controlsalways: true, onchange: function (newPage) {
				$('#paginator_pageno').val(newPage);
				if(typeof paginator!='undefined'&paginator instanceof Function) paginator(newPage);
	    	}
	    });
	}
}
