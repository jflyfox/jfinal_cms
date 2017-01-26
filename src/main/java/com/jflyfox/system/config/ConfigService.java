package com.jflyfox.system.config;

import java.util.ArrayList;
import java.util.List;

public class ConfigService {

	public String selectType(Integer selected) {
		List<SysConfig> list = new ArrayList<SysConfig>();
		list = SysConfig.dao.findByWhere(" where type = 0 order by sort ");
		StringBuffer sb = new StringBuffer();
		for (SysConfig config : list) {
			sb.append("<option value=\"");
			sb.append(config.getId());
			sb.append("\" ");
			sb.append((selected != null && config.getId() == selected) ? "selected" : "");
			sb.append(">");
			sb.append(config.getName());
			sb.append("</option>");
		}
		return sb.toString();
	}
}
