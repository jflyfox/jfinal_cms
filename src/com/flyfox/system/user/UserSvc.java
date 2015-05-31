package com.flyfox.system.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.flyfox.jfinal.base.BaseService;
import com.flyfox.system.menu.SysMenu;
import com.flyfox.system.userrole.SysUserRole;
import com.flyfox.util.NumberUtils;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

public class UserSvc extends BaseService {

	public Map<Integer, List<SysMenu>> getAuthMap(SysUser user) {
		String menuids = "select menuid from sys_role_menu where roleid in"
				+ " ( select roleid from sys_user_role where userid = ? ) group by menuid";
		// 管理员
		if (user.getInt("usertype") == 1) {
			menuids = " select id from sys_menu where -1 != ? "; // 所有菜单
		}

		Integer userid = user.getUserid();
		Map<Integer, List<SysMenu>> map = new HashMap<Integer, List<SysMenu>>();

		String sql = " where status = 1 and parentid = ? " //
				+ "and id in (" + menuids + ") order by sort ";
		// 获取根目录
		List<SysMenu> rootList = SysMenu.dao.findByWhere(sql, 0, userid);
		if (rootList == null || rootList.size() == 0) {
			return null;
		}

		map.put(0, rootList);
		// 获取子目录
		for (SysMenu sysMenu : rootList) {
			List<SysMenu> list = SysMenu.dao.findByWhere(sql, sysMenu.getInt("id"), userid);
			map.put(sysMenu.getInt("id"), list);
		}

		return map;
	}

	/**
	 * 获取用户绑定的角色
	 * 
	 * 2015年4月28日 下午5:04:55 flyfox 330627517@qq.com
	 * 
	 * @param userid
	 * @return
	 */
	public String getRoleids(int userid) {
		String sql = " select group_concat(roleid) as roleids from sys_user_role where userid = ?";
		Record record = Db.findFirst(sql, userid);
		String roleids = record.getStr("roleids");
		return roleids;
	}

	/**
	 * 保存用户授权信息
	 * 
	 * 2015年4月28日 下午5:05:05 flyfox 330627517@qq.com
	 * 
	 * @param userid
	 * @param roleids
	 */
	public void saveAuth(int userid, String roleids) {
		// 删除原有数据库
		Db.update("delete from sys_user_role where userid = ? ", userid);

		String[] arr = roleids.split(",");
		for (String roleid : arr) {
			SysUserRole userRole = new SysUserRole();
			userRole.set("userid", userid);
			userRole.set("roleid", NumberUtils.parseInt(roleid));
			userRole.save();
		}
	}
}
