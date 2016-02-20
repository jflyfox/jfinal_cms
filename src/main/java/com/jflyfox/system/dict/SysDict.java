package com.jflyfox.system.dict;

import com.jflyfox.component.base.BaseProjectModel;
import com.jflyfox.jfinal.component.annotation.ModelBind;

@ModelBind(table = "sys_dict", key = "dict_id")
public class SysDict extends BaseProjectModel<SysDict> {

	private static final long serialVersionUID = 1L;
	public static final SysDict dao = new SysDict();

}