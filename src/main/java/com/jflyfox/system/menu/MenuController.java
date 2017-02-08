package com.jflyfox.system.menu;

import com.jfinal.plugin.activerecord.Page;
import com.jflyfox.component.base.BaseProjectController;
import com.jflyfox.jfinal.component.annotation.ControllerBind;
import com.jflyfox.jfinal.component.db.SQLUtils;
import com.jflyfox.util.StrUtils;

/**
 * 菜单
 * 
 * @author flyfox 2014-4-24
 */
@ControllerBind(controllerKey = "/system/menu")
public class MenuController extends BaseProjectController {

	private static final String path = "/pages/system/menu/menu_";
	MenuSvc svc = new MenuSvc();

	public void index() {
		list();
	}
	
	public void list() {
		SysMenu model = getModelByAttr(SysMenu.class);

		SQLUtils sql = new SQLUtils(" from sys_menu t left join sys_menu d on d.id = t.parentid where 1=1 ");
		if (model.getAttrValues().length != 0) {
			sql.setAlias("t");
			// 查询条件
			sql.whereLike("name", model.getStr("name"));
		}
		
		// 排序
		String orderBy = getBaseForm().getOrderBy();
		if (StrUtils.isEmpty(orderBy)) {
			sql.append(" order by t.sort,t.id desc");
		} else {
			sql.append(" order by t.").append(orderBy);
		}

		Page<SysMenu> page = SysMenu.dao.paginate(getPaginator(), "select t.*,ifnull(d.name,'根目录') as parentname ", //
				sql.toString().toString());

		// 下拉框
		setAttr("parentSelect", svc.selectMenu(model.getInt("parentid")));
		setAttr("page", page);
		setAttr("attr", model);
		render(path + "list.html");
	}

	public void add() {
		setAttr("parentSelect", svc.selectMenu(0));
		render(path + "add.html");
	}

	public void view() {
		SysMenu model = SysMenu.dao.findById(getParaToInt());
		String parent = new MenuSvc().getParentName(model);
		model.put("parentname", parent);
		setAttr("model", model);
		render(path + "view.html");
	}

	public void delete() {
		// 日志添加
		SysMenu model = new SysMenu();
		Integer userid = getSessionUser().getUserID();
		String now = getNow();
		model.put("update_id", userid);
		model.put("update_time", now);

		model.deleteById(getParaToInt());
		list();
	}

	public void edit() {
		SysMenu model = SysMenu.dao.findById(getParaToInt());
		setAttr("parentSelect", svc.selectMenu(model.getInt("parentid")));
		setAttr("model", model);
		render(path + "edit.html");
	}

	public void save() {
		Integer pid = getParaToInt();
		SysMenu model = getModel(SysMenu.class);

		// 根目录级别为1
		Integer parentid = model.getInt("parentid");
		if (parentid != null) {
			model.set("level", parentid == 0 ? 1 : 2);
		}

		// 日志添加
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
		renderMessage("保存成功");
	}

}
