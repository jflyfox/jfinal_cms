package com.flyfox.system.role;

import com.flyfox.jfinal.base.BaseService;
import com.flyfox.system.rolemenu.SysRoleMenu;
import com.flyfox.util.NumberUtils;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

public class RoleSvc extends BaseService {

	
	/**
	 * 获取角色授权的菜单
	 * 
	 * 2015年4月28日 下午5:01:54
	 * flyfox 330627517@qq.com
	 * @param roleid
	 * @return
	 */
	public String getMemus(int roleid) {
		String sql = " select group_concat(menuid) as menus from sys_role_menu where roleid = ?";
		Record record = Db.findFirst(sql, roleid);
		String menus = record.getStr("menus");
		return menus;
	}
	
	/**
	 * 保存授权信息
	 * 
	 * 2015年4月28日 下午5:00:30
	 * flyfox 330627517@qq.com
	 * @param roleid
	 * @param menus
	 */
	public void saveAuth(int roleid, String menus) {
		// 删除原有数据库
		Db.update("delete from sys_role_menu where roleid = ? ", roleid);

		String[] arr = menus.split(",");
		for (String menuid : arr) {
			SysRoleMenu roleMenu = new SysRoleMenu();
			roleMenu.set("roleid", roleid);
			roleMenu.set("menuid", NumberUtils.parseInt(menuid));
			roleMenu.save();
		}
	}
}
