package com.jflyfox.system.role;

import com.jflyfox.component.base.BaseProjectModel;
import com.jflyfox.jfinal.component.annotation.ModelBind;

@ModelBind(table = "sys_role")
public class SysRole extends BaseProjectModel<SysRole> {

	private static final long serialVersionUID = 1L;
	public static final SysRole dao = new SysRole();

}