package com.flyfox.system.dict;

import com.flyfox.jfinal.base.BaseController;
import com.flyfox.util.StrUtils;
import com.jfinal.plugin.activerecord.Page;

/**
 * 数据字典
 * 
 * @author flyfox 2014-2-11
 */
public class DictController extends BaseController {

	private static final String path = "/pages/system/dict/dict_";
	DictSvc svc = new DictSvc();

	public void list() {
		SysDictDetail attr = getModelByAttr(SysDictDetail.class);
		StringBuffer sql = new StringBuffer(" from sys_dict_detail t,sys_dict d where t.dict_type = d.dict_type ");
		String attrVal = attr.getStr("dict_type");
		if (StrUtils.isNotEmpty(attrVal)) {
			sql.append(" AND t.dict_type = '").append(attrVal).append("'");
		}
		sql.append(" order by t.dict_type,t.detail_id desc ");
		Page<SysDictDetail> page = SysDictDetail.dao
				.paginate(getPaginator(), "select t.*,d.dict_name ", sql.toString());

		// 下拉框
		setAttr("optionList", svc.selectDictType(attr.getStr("dict_type")));
		setAttr("attr", attr);
		setAttr("page", page);
		render(path + "list.html");
	}

	public void add() {
		String dictType = getPara("dict_type");
		setAttr("optionList", svc.selectDictType(dictType));
		render(path + "add.html");
	}

	public void view() {
		SysDictDetail item = SysDictDetail.dao.findById(getParaToInt());
		setAttr("optionList", svc.selectDictType(item.getStr("dict_type")));
		setAttr("item", item);
		render(path + "view.html");
	}

	public void delete() {
		svc.deleteDetail(getParaToInt());
		list();
	}

	public void edit() {
		SysDictDetail item = SysDictDetail.dao.findById(getParaToInt());
		setAttr("optionList", svc.selectDictType(item.getStr("dict_type")));
		setAttr("item", item);
		render(path + "edit.html");
	}

	public void save() {
		Integer pid = getParaToInt();
		if (pid != null && pid > 0) { // 更新
			SysDictDetail model = getModel(SysDictDetail.class);
			svc.updateDetail(model);
		} else { // 新增
			SysDictDetail model = getModel(SysDictDetail.class);
			model.remove("detail_id");
			model.put("create_id", getSessionUser().getUserID());
			model.put("create_time", getNow());
			svc.addDetail(model);
		}
		renderMessage("保存成功");
	}

	public void edit_dict() {
		SysDict item = SysDict.dao.findFirstByWhere(" where dict_type = ? ", getPara());
		setAttr("item", item);
		render(path + "edit_dict.html");
	}

	public void save_dict() {
		Integer pid = getParaToInt();
		if (pid != null && pid > 0) { // 更新
			SysDict model = getModel(SysDict.class);
			model.update();
		} else { // 新增
			SysDict model = getModel(SysDict.class, "model");
			model.remove("dict_id");
			model.save();
		}
		renderMessage("保存成功");
	}

	public void delete_dict() {
		SysDict.dao.deleteById(getParaToInt());
		renderMessage("删除成功");
	}
}
