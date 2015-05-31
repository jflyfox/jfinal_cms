package com.flyfox.system.user;

import java.util.List;

import com.flyfox.component.util.JFlyFoxUtils;
import com.flyfox.jfinal.base.BaseController;
import com.flyfox.jfinal.component.db.SQLUtils;
import com.flyfox.system.department.DepartmentSvc;
import com.flyfox.system.role.SysRole;
import com.flyfox.util.StrUtils;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;

/**
 * 用户管理
 * 
 * @author flyfox 2014-2-11
 */
public class UserController extends BaseController {

	private static final String path = "/pages/system/user/user_";

	public void list() {
		SysUser model = getModelByAttr(SysUser.class);

		SQLUtils sql = new SQLUtils(" from sys_user t " //
				+ " left join sys_department d on d.id = t.departid " //
				+ " where 1 = 1 and userid != 1 ");

		if (model.getAttrValues().length != 0) {
			sql.whereLike("username", model.getStr("username"));
			sql.whereLike("realname", model.getStr("realname"));
			sql.whereEquals("usertype", model.getInt("usertype"));
			sql.whereEquals("departid", model.getInt("departid"));
		}

		sql.append(" order by userid ");
		Page<SysUser> page = SysUser.dao.paginate(getPaginator(), "select t.*,d.name as departname ", sql.toString()
				.toString());
		// 下拉框
		setAttr("departSelect", new DepartmentSvc().selectDepart(model.getInt("departid")));

		setAttr("page", page);
		setAttr("attr", model);
		render(path + "list.html");
	}

	public void add() {
		setAttr("departSelect", new DepartmentSvc().selectDepart(0));

		render(path + "add.html");
	}

	public void view() {
		SysUser model = SysUser.dao.findById(getParaToInt());
		// 部门名称
		model.put("departname", new DepartmentSvc().getDepartName(model.getInt("departid")));
		setAttr("model", model);

		render(path + "view.html");
	}

	public void delete() {
		int userid = getParaToInt();
		SysUser.dao.deleteById(userid);
		// 删除授权
		Db.update("delete from sys_user_role where userid = ? ", userid);
		UserCache.init();
		list();
	}

	public void edit() {
		SysUser model = SysUser.dao.findById(getParaToInt());

		setAttr("departSelect", new DepartmentSvc().selectDepart(model.getInt("departid")));

		setAttr("model", model);
		render(path + "edit.html");
	}

	public void save() {
		Integer pid = getParaToInt();
		SysUser model = getModel(SysUser.class);
		if (pid != null && pid > 0) { // 更新
			model.update();
		} else { // 新增
			model.remove("userid");
			if (StrUtils.isEmpty(model.getStr("password"))) {
				model.put("password", JFlyFoxUtils.getDefaultPassword());
			}
			model.put("create_id", getSessionUser().getUserID());
			model.put("create_time", getNow());
			model.save();
		}
		UserCache.init();
		renderMessage("保存成功");
	}

	/**
	 * 跳转到授权页面
	 * 
	 * 2015年4月28日 下午12:00:05 flyfox 330627517@qq.com
	 */
	public void auth() {
		int userid = getParaToInt();
		List<SysRole> list = SysRole.dao.findByWhere(" where status=1 order by sort ");

		String roleids = new UserSvc().getRoleids(userid);
		setAttr("userid", userid);
		setAttr("roleids", roleids);
		// 根结点
		setAttr("list", list);
		render(path + "auth.html");
	}

	/**
	 * 保存角色信息
	 * 
	 * 2015年4月28日 下午3:18:33 flyfox 330627517@qq.com
	 */
	public void auth_save() {
		int userid = getParaToInt("userid");
		String roleids = getPara("roleids");

		if (StrUtils.isNotEmpty(roleids)) {
			new UserSvc().saveAuth(userid, roleids);
		} else {
			renderMessageByFailed("保存失败");
			return;
		}
		renderMessage("保存成功");
	}

	
}
