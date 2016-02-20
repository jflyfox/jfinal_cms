package com.jflyfox.system.user;

import com.jflyfox.jfinal.base.SessionUser;
import com.jflyfox.jfinal.component.annotation.ModelBind;
import com.jflyfox.util.StrUtils;

@ModelBind(table = "sys_user", key = "userid")
public class SysUser extends SessionUser<SysUser> {

	private static final long serialVersionUID = 1L;
	public static final SysUser dao = new SysUser();
	
	public Integer getUserid() {
		return getInt("userid") == null ? -1 : getInt("userid");
	}
	
	public String getUserName() {
		if (StrUtils.isNotEmpty(getStr("realname"))) {
			return getStr("realname");	
		}
		return getStr("username");
	}
}
