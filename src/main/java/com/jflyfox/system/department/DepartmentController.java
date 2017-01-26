package com.jflyfox.system.department;

import java.util.List;

import com.jfinal.plugin.activerecord.Page;
import com.jflyfox.component.base.BaseProjectController;
import com.jflyfox.jfinal.component.annotation.ControllerBind;
import com.jflyfox.jfinal.component.db.SQLUtils;
import com.jflyfox.util.StrUtils;

/**
 * 部门
 * 
 * @author flyfox 2014-4-24
 */
@ControllerBind(controllerKey = "/system/department")
public class DepartmentController extends BaseProjectController {

	private static final String path = "/pages/system/department/department_";

	public void index() {
		list();
	}
	
	public void list() {
		SysDepartment model = getModelByAttr(SysDepartment.class);

		SQLUtils sql = new SQLUtils(" from sys_department t where 1=1 ");
		if (model.getAttrValues().length != 0) {
			sql.setAlias("t");
			// 查询条件
			sql.whereLike("name", model.getStr("name"));
		}

		// 排序
		String orderBy = getBaseForm().getOrderBy();
		if (StrUtils.isEmpty(orderBy)) {
			sql.append(" order by t.sort,t.id desc ");
		} else {
			sql.append(" order by ").append(orderBy);
		}

		Page<SysDepartment> page = SysDepartment.dao.paginate(getPaginator(), "select t.* ", //
				sql.toString().toString());

		// 下拉框
		setAttr("page", page);
		setAttr("attr", model);

		List<SysDepartment> departments = SysDepartment.dao.find("select * from sys_department order by sort,id ");
		setAttr("departments", departments);

		render(path + "list.html");
	}

	public void add() {
		// 获取页面信息,设置目录传入
		SysDepartment model = SysDepartment.dao.findById(getParaToInt());
		setAttr("selectParentDepartments", selectFolder(model == null ? 0 : model.getId(), 0));
				
		render(path + "add.html");
	}

	public void view() {
		SysDepartment model = SysDepartment.dao.findById(getParaToInt());
		setAttr("model", model);
		
		SysDepartment department = SysDepartment.dao.findById(model.getParentId());
		model.put("parentName", department != null ? department.getName() : null);
		
		render(path + "view.html");
	}

	public void delete() {

		// 日志添加
		SysDepartment model = new SysDepartment();
		Integer userid = getSessionUser().getUserID();
		String now = getNow();
		model.put("update_id", userid);
		model.put("update_time", now);

		model.deleteById(getParaToInt());
		list();
	}

	public void edit() {
		SysDepartment model = SysDepartment.dao.findById(getParaToInt());
		setAttr("model", model);
		
		// 下拉框
		setAttr("selectParentDepartments", selectFolder(model.getParentId(), model.getId()));
				
		render(path + "edit.html");
	}

	public void save() {
		Integer pid = getParaToInt();
		SysDepartment model = getModel(SysDepartment.class);

		// 日志添加
		Integer userid = getSessionUser().getUserID();
		String now = getNow();
		model.set("update_id", userid);
		model.set("update_time", now);
		if (pid != null && pid > 0) { // 更新
			model.update();
		} else { // 新增
			model.remove("id");
			model.set("create_id", userid);
			model.set("create_time", now);
			model.save();
		}
		renderMessage("保存成功");
	}

	/**
	 * 目录复选框
	 * 
	 * 2015年1月28日 下午5:28:40 flyfox 369191470@qq.com
	 * 
	 * @return
	 */
	public String selectFolder(Integer selected, Integer selfId) {
		String where = " where 1 = 1 ";
		if (selfId != null && selfId > 0) {
			where += "and id !=" + selfId;
		}
		List<SysDepartment> list = SysDepartment.dao.find(" select id,name from sys_department " //
				+ where + " order by sort,create_time desc ");
		StringBuffer sb = new StringBuffer("");
		if (list != null && list.size() > 0) {
			for (SysDepartment department : list) {
				sb.append("<option value=\"");
				sb.append(department.getInt("id"));
				sb.append("\" ");
				sb.append((selected != null && department.getInt("id").intValue() == selected) ? "selected" : "");
				sb.append(">");
				sb.append(department.getStr("name"));
				sb.append("</option>");
			}
		}
		return sb.toString();
	}
}
