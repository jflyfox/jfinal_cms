package com.flyfox.system.userrole;

import com.flyfox.jfinal.base.BaseModel;
import com.flyfox.jfinal.component.annotation.ModelBind;

@ModelBind(table = "sys_user_role")
public class SysUserRole extends BaseModel<SysUserRole> {

	private static final long serialVersionUID = 1L;
	public static final SysUserRole dao = new SysUserRole();

}