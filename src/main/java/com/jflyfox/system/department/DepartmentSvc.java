package com.jflyfox.system.department;

import java.util.List;

import com.jflyfox.jfinal.base.BaseService;

/**
 * 数据字典service
 * 
 * @author flyfox 2014-2-11
 */
public class DepartmentSvc extends BaseService {

	/**
	 * 获取部门下拉框
	 * 
	 * 2015年4月28日 上午11:42:54 flyfox 369191470@qq.com
	 * 
	 * @param selected
	 * @return
	 */
	public String selectDepart(Integer selected) {
		return selectDepart(selected, 0);
	}

	/**
	 * 获取部门下拉框
	 * 
	 * 2015年1月28日 下午5:28:40 flyfox 369191470@qq.com
	 * 
	 * @return
	 */
	public String selectDepart(Integer selected, Integer selfId) {
		String where = " where 1 = 1 ";
		if (selfId != null && selfId > 0) {
			where += "and id !=" + selfId;
		}
		List<SysDepartment> list = SysDepartment.dao.find(" select * from sys_department " //
				+ where + " order by sort,create_time desc ");
		StringBuffer sb = new StringBuffer("");
		if (list != null && list.size() > 0) {
			for (SysDepartment department : list) {
				int parentId = department.getInt("parent_id");
				if (parentId == 0) {
					int id = department.getInt("id");
					sb.append("<option value=\"");
					sb.append(id);
					sb.append("\" ");
					boolean flag = (selected != null && id == selected);
					sb.append(flag ? "selected" : "");
					sb.append(">");
					sb.append(department.getStr("name"));
					sb.append("</option>");

					sb.append(recursionTree(id, selected, 1, list));
				}
			}
		}
		return sb.toString();
	}
	
	/**
	 * 递归树
	 * 
	 * 2017年3月14日 上午10:39:29
	 * flyfox 330627517@qq.com
	 * @return
	 */
	public String recursionTree(int rootId, Integer selected, int level, List<SysDepartment> list) {
		if (list == null || list.size() <= 0) {
			return "";
		}

		// 级别太多了
		if (level >= 20) {
			return "";
		}

		StringBuffer sb = new StringBuffer("");
		for (SysDepartment department : list) {
			int parentId = department.getInt("parent_id");
			if (parentId == rootId) {
				int id = department.getInt("id");
				sb.append("<option value=\"");
				sb.append(id);
				sb.append("\" ");
				boolean flag = (selected != null && id == selected);
				sb.append(flag ? "selected" : "");
				sb.append(">");
				for (int i = 0; i < level; i++) {
					if (i == (level -1)) {
						sb.append("&nbsp;|--");
					} else {
						sb.append("&nbsp;&nbsp;&nbsp;");
					}
				}
				sb.append(department.getStr("name"));
				sb.append("</option>");

				sb.append(recursionTree(id, selected, (level + 1), list));
			}
		}

		return sb.toString();
	}
	
	/**
	 * 获取名称
	 * 
	 * 2015年4月28日 上午11:43:07 flyfox 369191470@qq.com
	 * 
	 * @param model
	 * @return
	 */
	public String getDepartName(Integer id) {
		if (id == null || id == 0) {
			return "";
		}
		String name = SysDepartment.dao.findById(id).getStr("name");
		return name;
	}

}
