package com.jflyfox.system.user;

import com.jflyfox.jfinal.base.SessionUser;
import com.jflyfox.jfinal.component.annotation.ModelBind;

@ModelBind(table = "sys_user", key = "userid")
public class SysUser extends SessionUser<SysUser> {

	private static final long serialVersionUID = 1L;
	public static final SysUser dao = new SysUser();
	
}
