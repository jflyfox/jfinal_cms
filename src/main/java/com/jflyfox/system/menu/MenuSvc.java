package com.jflyfox.system.menu;

import java.util.List;

import com.jflyfox.jfinal.base.BaseService;

/**
 * 数据字典service
 * 
 * @author flyfox 2014-2-11
 */
public class MenuSvc extends BaseService {

	/**
	 * 获取根目录下拉框
	 * 
	 * 2015年4月28日 上午11:42:54 flyfox 369191470@qq.com
	 * 
	 * @param selected
	 * @return
	 */
	public String selectMenu(Integer selected) {
		List<SysMenu> list = SysMenu.dao.findByWhere(" where status = 1 and parentid = 0 order by sort ");
		StringBuffer sb = new StringBuffer();
		for (SysMenu menu : list) {
			sb.append("<option value=\"");
			sb.append(menu.getInt("id"));
			sb.append("\" ");
			if (selected != null) {
				sb.append(menu.getInt("id") == selected ? "selected" : "");
			}
			sb.append(">");
			sb.append(menu.getStr("name"));
			sb.append("</option>");
		}
		return sb.toString();
	}

	/**
	 * 获取父节点名称
	 * 
	 * 2015年4月28日 上午11:43:07 flyfox 369191470@qq.com
	 * 
	 * @param model
	 * @return
	 */
	public String getParentName(SysMenu model) {
		Integer parentid = model.getInt("parentid");
		if (parentid == null || parentid == 0) {
			return "根目录";
		}
		String parentName = SysMenu.dao.findById(model.getInt("parentid")).getStr("name");
		return parentName;
	}

	/**
	 * 根据父节点获取List
	 * 
	 * 2015年4月28日 上午11:43:07 flyfox 369191470@qq.com
	 * 
	 * @param parentid
	 * @return
	 */
	public List<SysMenu> getListByParentid(int parentid) {
		List<SysMenu> list = SysMenu.dao.findByWhere(" where  status = 1 and parentid = ? order by sort ", parentid);
		return list;
	}

}
