package com.jflyfox.system.role;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jflyfox.component.base.BaseProjectController;
import com.jflyfox.jfinal.component.annotation.ControllerBind;
import com.jflyfox.jfinal.component.db.SQLUtils;
import com.jflyfox.system.menu.MenuSvc;
import com.jflyfox.system.menu.SysMenu;
import com.jflyfox.util.StrUtils;

/**
 * 角色
 * 
 * @author flyfox 2014-4-24
 */
@ControllerBind(controllerKey = "/system/role")
public class RoleController extends BaseProjectController {

	private static final String path = "/pages/system/role/role_";

	public void index() {
		list();
	}
	
	public void list() {
		SysRole model = getModelByAttr(SysRole.class);

		SQLUtils sql = new SQLUtils(" from sys_role t where 1=1 ");
		if (model.getAttrValues().length != 0) {
			sql.setAlias("t");
			// 查询条件
			sql.whereLike("name", model.getStr("name"));
		}
		
		// 排序
		String orderBy = getBaseForm().getOrderBy();
		if (StrUtils.isEmpty(orderBy)) {
			sql.append(" order by sort,id desc");
		} else {
			sql.append(" order by ").append(orderBy);
		}

		String sqlSelect = "select t.* "
				+ ",(select group_concat(m.name) from sys_role_menu rm left JOIN  sys_menu m ON rm.menuid = m.id where rm.roleid = t.id ) as menus ";

		Page<SysRole> page = SysRole.dao.paginate(getPaginator(), sqlSelect, //
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
		SysRole model = SysRole.dao.findById(getParaToInt());
		setAttr("model", model);
		render(path + "view.html");
	}

	public void delete() {
		Integer roleid = getParaToInt();
		// 日志添加
		SysRole model = new SysRole();
		Integer userid = getSessionUser().getUserID();
		String now = getNow();
		model.put("update_id", userid);
		model.put("update_time", now);

		// 删除授权
		Db.update("delete from sys_role_menu where roleid = ? ", roleid);
		
		model.deleteById(roleid);
		
		list();
	}

	public void edit() {
		SysRole model = SysRole.dao.findById(getParaToInt());
		setAttr("model", model);
		render(path + "edit.html");
	}

	public void save() {
		Integer pid = getParaToInt();
		SysRole model = getModel(SysRole.class);
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

	/**
	 * 跳转到授权页面
	 * 
	 * 2015年4月28日 下午12:00:05 flyfox 369191470@qq.com
	 */
	public void auth() {
		int roleid = getParaToInt();
		MenuSvc svc = new MenuSvc();
		// 获取根目录
		List<SysMenu> rootList = svc.getListByParentid(0);
		// 获取子目录
		Map<Integer, List<SysMenu>> map = new HashMap<Integer, List<SysMenu>>();
		for (SysMenu sysMenu : rootList) {
			map.put(sysMenu.getInt("id"), svc.getListByParentid(sysMenu.getInt("id")));
		}

		String menus = new RoleSvc().getMemus(roleid);
		setAttr("roleid", roleid);
		setAttr("menus", menus);
		// 根结点
		setAttr("rootList", rootList);
		// 二级目录
		setAttr("map", map);
		render(path + "auth.html");
	}

	/**
	 * 保存授权信息
	 * 
	 * 2015年4月28日 下午3:18:33 flyfox 369191470@qq.com
	 */
	public void auth_save() {
		int roleid = getParaToInt("roleid");
		String menus = getPara("menus");

		new RoleSvc().saveAuth(roleid, menus, getSessionUser().getUserID());
		renderMessage("保存成功");
	}

}
