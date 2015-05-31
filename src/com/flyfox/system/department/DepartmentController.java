package com.flyfox.system.department;

import com.flyfox.jfinal.base.BaseController;
import com.flyfox.jfinal.component.db.SQLUtils;
import com.jfinal.plugin.activerecord.Page;

/**
 * 部门
 * 
 * @author flyfox 2014-4-24
 */
public class DepartmentController extends BaseController {

	private static final String path = "/pages/system/department/department_";

	public void list() {
		SysDepartment model = getModelByAttr(SysDepartment.class);

		SQLUtils sql = new SQLUtils(" from sys_department t where 1=1 ");
		if (model.getAttrValues().length != 0) {
			sql.setAlias("t");
			// 查询条件
			sql.whereLike("name", model.getStr("name"));
		}

		Page<SysDepartment> page = SysDepartment.dao.paginate(getPaginator(), "select t.* ", //
				sql.toString().toString());

		// 下拉框
		setAttr("page", page);
		setAttr("attr", model);
		render(path + "list.html");
	}

	public void add() {
		render(path + "add.html");
	}

	public void view() {
		SysDepartment model = SysDepartment.dao.findById(getParaToInt());
		setAttr("model", model);
		render(path + "view.html");
	}

	public void delete() {
		SysDepartment.dao.deleteById(getParaToInt());
		list();
	}

	public void edit() {
		SysDepartment model = SysDepartment.dao.findById(getParaToInt());
		setAttr("model", model);
		render(path + "edit.html");
	}

	public void save() {
		Integer pid = getParaToInt();
		SysDepartment model = getModel(SysDepartment.class);
		if (pid != null && pid > 0) { // 更新
			model.update();
		} else { // 新增
			model.remove("id");
			model.put("create_id", getSessionUser().getUserID());
			model.put("create_time", getNow());
			model.save();
		}
		renderMessage("保存成功");
	}
}
