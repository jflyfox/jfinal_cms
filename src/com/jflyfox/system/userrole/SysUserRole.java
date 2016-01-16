package com.jflyfox.system.userrole;

import com.jflyfox.component.base.BaseProjectModel;
import com.jflyfox.jfinal.component.annotation.ModelBind;

@ModelBind(table = "sys_user_role")
public class SysUserRole extends BaseProjectModel<SysUserRole> {

	private static final long serialVersionUID = 1L;
	public static final SysUserRole dao = new SysUserRole();

}