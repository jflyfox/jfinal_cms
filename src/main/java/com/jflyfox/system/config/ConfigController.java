package com.jflyfox.system.config;

import com.jfinal.plugin.activerecord.Page;
import com.jflyfox.jfinal.base.BaseController;
import com.jflyfox.jfinal.component.annotation.ControllerBind;
import com.jflyfox.jfinal.component.db.SQLUtils;
import com.jflyfox.util.StrUtils;

/**
 * 系统配置表
 * 
 * @author flyfox 2014-4-24
 */
@ControllerBind(controllerKey = "/system/config")
public class ConfigController extends BaseController {

	private static final String path = "/pages/system/config/config_";
	
	public void index() {
		list();
	}

	public void list() {
		SysConfig model = getModelByAttr(SysConfig.class);
		int operType = getParaToInt("oper_type", 1);

		SQLUtils sql = new SQLUtils(" from sys_config t"
				+ " left join sys_config t2 on t.type = t2.id where 1=1 ");
		if (model.getAttrValues().length != 0) {
			sql.setAlias("t");
			sql.whereLike("name", model.getStr("name"));
			sql.whereLike("key", model.getStr("key"));
			sql.whereEquals("type", model.getInt("type"));
			// 查询条件
		}
		if (operType == 1) {
			sql.append(" and t.type != 0 ");
		} else {
			sql.append(" and t.type = 0 ");
		}

		// 排序
		String orderBy = getBaseForm().getOrderBy();
		if (StrUtils.isEmpty(orderBy)) {
			sql.append(" order by t.sort,t.id desc ");
		} else {
			sql.append(" order by ").append(orderBy);
		}

		Page<SysConfig> page = SysConfig.dao.paginate(getPaginator(), "select t.*,t2.name as typeName ", //
				sql.toString().toString());

		// 参数
		setAttr("selectOption", new ConfigService().selectType(model.getType()));
		setAttr("oper_type", operType);
		setAttr("page", page);
		setAttr("attr", model);
		render(path + "list.html");
	}

	public void add() {
		int operType = getParaToInt("oper_type", 1);
		int type = getParaToInt("type", 1);
		setAttr("selectOption", new ConfigService().selectType(type));
		setAttr("oper_type", operType);
		render(path + "add.html");
	}

	public void view() {
		SysConfig model = SysConfig.dao.findById(getParaToInt());
		setAttr("model", model);
		render(path + "view.html");
	}

	public void delete() {
		Integer pid = getParaToInt();
		SysConfig model = new SysConfig();
		Integer userid = getSessionUser().getUserID();
		String now = getNow();
		model.put("update_id", userid);
		model.put("update_time", now);
		model.deleteById(pid);

		// 更新缓存
		ConfigCache.update();

		list();
	}

	public void edit() {
		int operType = getParaToInt("oper_type", 1);
		SysConfig model = SysConfig.dao.findById(getParaToInt());
		
		setAttr("selectOption", new ConfigService().selectType(model.getType()));
		setAttr("oper_type", operType);
		setAttr("model", model);
		render(path + "edit.html");
	}

	public void save() {
		Integer pid = getParaToInt();
		SysConfig model = getModel(SysConfig.class);

		Integer userid = getSessionUser().getUserID();
		String now = getNow();
		model.put("update_id", userid);
		model.put("update_time", now);
		if (pid != null && pid > 0) { // 更新
			model.update();
		} else { // 新增
			model.remove("id");
			model.put("create_id", userid);
			model.put("create_time", now);
			model.save();
		}

		// 更新缓存
		ConfigCache.update();
		renderMessage("保存成功");
	}

}
