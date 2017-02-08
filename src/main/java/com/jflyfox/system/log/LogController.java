package com.jflyfox.system.log;

import com.jfinal.plugin.activerecord.Page;
import com.jflyfox.component.base.BaseProjectController;
import com.jflyfox.jfinal.component.annotation.ControllerBind;
import com.jflyfox.jfinal.component.db.SQLUtils;
import com.jflyfox.util.StrUtils;

/**
 * 操作日志
 * 
 * @author flyfox 2014-4-24
 */
@ControllerBind(controllerKey = "/system/log")
public class LogController extends BaseProjectController {

	private static final String path = "/pages/system/log/log_";

	public void index() {
		list();
	}
	
	public void list() {
		SysLog model = getModelByAttr(SysLog.class);

		SQLUtils sql = new SQLUtils(" from sys_log t where 1=1 ");
		if (model.getAttrValues().length != 0) {
			sql.setAlias("t");
			// 查询条件
			sql.whereEquals("log_type", model.getLogType());
		}
		// 排序
		String orderBy = getBaseForm().getOrderBy();
		if (StrUtils.isEmpty(orderBy)) {
			sql.append(" order by id desc");
		} else {
			sql.append(" order by ").append(orderBy);
		}

		Page<SysLog> page = SysLog.dao.paginate(getPaginator(), "select t.* ", //
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
		SysLog model = SysLog.dao.findById(getParaToInt());
		setAttr("model", model);
		render(path + "view.html");
	}

	public void delete() {
		SysLog.dao.deleteById(getParaToInt());
		list();
	}

	public void edit() {
		SysLog model = SysLog.dao.findById(getParaToInt());
		setAttr("model", model);
		render(path + "edit.html");
	}

	public void save() {
		Integer pid = getParaToInt();
		SysLog model = getModel(SysLog.class);
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
