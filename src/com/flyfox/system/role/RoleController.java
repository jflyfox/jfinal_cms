package com.flyfox.system.role;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.flyfox.jfinal.base.BaseController;
import com.flyfox.jfinal.component.db.SQLUtils;
import com.flyfox.system.menu.MenuSvc;
import com.flyfox.system.menu.SysMenu;
import com.flyfox.util.StrUtils;
import com.jfinal.plugin.activerecord.Page;

/**
 * 角色
 * 
 * @author flyfox 2014-4-24
 */
public class RoleController extends BaseController {

	private static final String path = "/pages/system/role/role_";

	public void list() {
		SysRole model = getModelByAttr(SysRole.class);

		SQLUtils sql = new SQLUtils(" from sys_role t where 1=1 ");
		if (model.getAttrValues().length != 0) {
			sql.setAlias("t");
			// 查询条件
			sql.whereLike("name", model.getStr("name"));
		}

		Page<SysRole> page = SysRole.dao.paginate(getPaginator(), "select t.* ", //
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
		SysRole.dao.deleteById(getParaToInt());
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

	/**
	 * 跳转到授权页面
	 * 
	 * 2015年4月28日 下午12:00:05 flyfox 330627517@qq.com
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
		setAttr("menus",menus );
		// 根结点
		setAttr("rootList", rootList);
		// 二级目录
		setAttr("map", map);
		render(path + "auth.html");
	}

	/**
	 * 保存授权信息
	 * 
	 * 2015年4月28日 下午3:18:33 flyfox 330627517@qq.com
	 */
	public void auth_save() {
		int roleid = getParaToInt("roleid");
		String menus = getPara("menus");

		if (StrUtils.isNotEmpty(menus)) {
			new RoleSvc().saveAuth(roleid, menus);
		} else {
			renderMessageByFailed("保存失败");
			return;
		}
		renderMessage("保存成功");
	}

}
