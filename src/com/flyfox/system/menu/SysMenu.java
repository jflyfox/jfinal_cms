package com.flyfox.system.menu;

import com.flyfox.jfinal.base.BaseModel;
import com.flyfox.jfinal.component.annotation.ModelBind;

@ModelBind(table = "sys_menu")
public class SysMenu extends BaseModel<SysMenu> {

	private static final long serialVersionUID = 1L;
	public static final SysMenu dao = new SysMenu();

}