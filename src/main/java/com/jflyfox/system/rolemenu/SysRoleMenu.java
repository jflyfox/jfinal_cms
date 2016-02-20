package com.jflyfox.system.rolemenu;

import com.jflyfox.component.base.BaseProjectModel;
import com.jflyfox.jfinal.component.annotation.ModelBind;

@ModelBind(table = "sys_role_menu")
public class SysRoleMenu extends BaseProjectModel<SysRoleMenu> {

	private static final long serialVersionUID = 1L;
	public static final SysRoleMenu dao = new SysRoleMenu();

}