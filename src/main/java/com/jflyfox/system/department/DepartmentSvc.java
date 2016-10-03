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
		List<SysDepartment> list = SysDepartment.dao.findByWhere(" order by sort");
		StringBuffer sb = new StringBuffer();
		for (SysDepartment department : list) {
			sb.append("<option value=\"");
			sb.append(department.getInt("id"));
			sb.append("\" ");
			if (selected != null) {
				sb.append(department.getInt("id") == selected ? "selected" : "");
			}
			sb.append(">");
			sb.append(department.getStr("name"));
			sb.append("</option>");
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
