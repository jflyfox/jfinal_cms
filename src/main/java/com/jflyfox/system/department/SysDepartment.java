package com.jflyfox.system.department;

import com.jflyfox.component.base.BaseProjectModel;
import com.jflyfox.jfinal.component.annotation.ModelBind;

@ModelBind(table = "sys_department")
public class SysDepartment extends BaseProjectModel<SysDepartment> {

	private static final long serialVersionUID = 1L;
	public static final SysDepartment dao = new SysDepartment();

}