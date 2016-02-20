// 现在应该没地方用了~！~ 所有方法示例页面
common = {
	list_url : '{project}/list',
	add_url : '{project}/add',
	edit_url : '{project}/edit/{1}',
	view_url : '{project}/view/{1}',
	delete_url : '{project}/delete/{1}',
	save_url : '{project}/save/{1}',
	save_add_url : '{project}/save_add/{1}',
	save_edit_url : '{project}/save_edit/{1}',
	add_name : '添加{project_name}',
	edit_name : '修改{project_name}',
	view_name : '查看{project_name}',
	delete_name : '确认要删除该{project_name}信息？',
	version : 1
};

flyfox = function(project, project_name, form) {
	this.project = project;
	this.project_name = project_name;
	this.form = getForm(form);
	this.width = 400; // 默认宽度
	this.height = 300; // 默认高度
	
	// ajax后就可以抛弃form了
	function getForm(form){
		// 如果form为空，那么默认为form1
		form = form || '';
		if(form==''){
			form = document.form1;	
		}
		return form;
	}
};

flyfox.prototype.list = function() {
	var url = common.list_url.replace('{project}', this.project);
	this.form.action = url;
	this.form.submit();
};

flyfox.prototype.view = function(pid) {
	var url = common.view_url.replace('{project}', this.project).replace('{1}', pid);
	var title = common.view_name.replace('{project_name}', this.project_name);
	Iframe(url, this.width, this.height, title, false, false, false, EmptyFunc);
};

flyfox.prototype.add = function() {
	var url = common.add_url.replace('{project}', this.project);
	var title = common.add_name.replace('{project_name}', this.project_name);
	Iframe(url, this.width, this.height, title);
};

flyfox.prototype.edit = function(pid) {
	var url = common.edit_url.replace('{project}', this.project).replace('{1}', pid);
	var title = common.edit_name.replace('{project_name}', this.project_name);
	Iframe(url, this.width, this.height, title);
};

flyfox.prototype.del = function(pid) {
	var url = common.delete_url.replace('{project}', this.project).replace('{1}', pid);
	var title = common.delete_name.replace('{project_name}', this.project_name);
	Confirm(title, function() {
		form1.action = url;
		form1.submit();
	});
};

flyfox.prototype.save = function(pid) {
	pid = pid || '0';
	var url = common.save_url.replace('{project}', this.project).replace('{1}', pid);
	form1.action = url;
	form1.submit();
};

flyfox.prototype.save_edit = function(pid) {
	pid = pid || '0';
	var url = common.save_edit_url.replace('{project}', this.project).replace('{1}', pid);
	this.form.action = url;
	this.form.submit();
};

flyfox.prototype.save_add = function(pid) {
	pid = pid || '0';
	var url = common.save_add_url.replace('{project}', this.project).replace('{1}', pid);
	this.form.action = url;
	this.form.submit();
};