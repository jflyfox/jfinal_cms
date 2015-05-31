package com.flyfox.system.role;

import com.flyfox.jfinal.base.BaseModel;
import com.flyfox.jfinal.component.annotation.ModelBind;

@ModelBind(table = "sys_role")
public class SysRole extends BaseModel<SysRole> {

	private static final long serialVersionUID = 1L;
	public static final SysRole dao = new SysRole();

}