package com.flyfox.system.rolemenu;

import com.flyfox.jfinal.base.BaseModel;
import com.flyfox.jfinal.component.annotation.ModelBind;

@ModelBind(table = "sys_role_menu")
public class SysRoleMenu extends BaseModel<SysRoleMenu> {

	private static final long serialVersionUID = 1L;
	public static final SysRoleMenu dao = new SysRoleMenu();

}