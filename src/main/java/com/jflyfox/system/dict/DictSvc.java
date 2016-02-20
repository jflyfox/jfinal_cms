package com.jflyfox.system.dict;

import java.util.ArrayList;
import java.util.List;

import com.jflyfox.jfinal.base.BaseService;

/**
 * 数据字典service
 * 
 * @author flyfox 2014-2-11
 */
public class DictSvc extends BaseService {

	/**
	 * 通过Key获取数据字典名称
	 * 
	 * @param key
	 * @return
	 * @author flyfox 2013-11-19
	 */
	public static SysDictDetail getDictDetail(String key) {
		return DictCache.getCacheMap().get(key);
	}

	/**
	 * 通过Key获取数据字典名称
	 * 
	 * @param key
	 * @return
	 * @author flyfox 2013-11-19
	 */
	public static String getDictName(String key) {
		SysDictDetail detail = DictCache.getCacheMap().get(key);
		return detail == null ? null : detail.getStr("detail_name");
	}

	public String selectDictType(String selected) {
		List<SysDict> list = new ArrayList<SysDict>();
		list = SysDict.dao.find("select * from sys_dict");
		StringBuffer sb = new StringBuffer();
		for (SysDict dict : list) {
			sb.append("<option value=\"");
			sb.append(dict.getStr("dict_type"));
			sb.append("\" ");
			sb.append(dict.getStr("dict_type").equals(selected) ? "selected" : "");
			sb.append(">");
			sb.append(dict.getStr("dict_name"));
			sb.append("</option>");
		}
		return sb.toString();
	}

	public void updateDetail(SysDictDetail model) {
		model.update();
		DictCache.initDict();

	}

	public void addDetail(SysDictDetail model) {
		model.save();
		DictCache.initDict();
	}

	public void deleteDetail(SysDictDetail model) {
		model.deleteById(model.getInt("detail_id"));
		DictCache.initDict();
	}

}
