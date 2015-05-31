package com.flyfox.system.department;

import com.flyfox.jfinal.base.BaseModel;
import com.flyfox.jfinal.component.annotation.ModelBind;

@ModelBind(table = "sys_department")
public class SysDepartment extends BaseModel<SysDepartment> {

	private static final long serialVersionUID = 1L;
	public static final SysDepartment dao = new SysDepartment();

}