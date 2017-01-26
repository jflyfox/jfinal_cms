package com.jflyfox.system.dict;

import com.jfinal.plugin.activerecord.Page;
import com.jflyfox.component.base.BaseProjectController;
import com.jflyfox.jfinal.component.annotation.ControllerBind;
import com.jflyfox.util.StrUtils;

/**
 * 数据字典
 * 
 * @author flyfox 2014-2-11
 */
@ControllerBind(controllerKey = "/system/dict")
public class DictController extends BaseProjectController {

	private static final String path = "/pages/system/dict/dict_";
	DictSvc svc = new DictSvc();

	public void index() {
		list();
	}
	
	public void list() {
		SysDictDetail attr = getModelByAttr(SysDictDetail.class);
		StringBuffer sql = new StringBuffer(" from sys_dict_detail t,sys_dict d where t.dict_type = d.dict_type ");
		String attrVal = attr.getStr("dict_type");
		if (StrUtils.isNotEmpty(attrVal)) {
			sql.append(" AND t.dict_type = '").append(attrVal).append("'");
		}
		
		// 排序
		String orderBy = getBaseForm().getOrderBy();
		if (StrUtils.isEmpty(orderBy)) {
			sql.append(" order by t.dict_type,t.detail_id desc ");
		} else {
			sql.append(" order by ").append(orderBy);
		}
		
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
		// 日志添加
		SysDictDetail model = new SysDictDetail();
		Integer userid = getSessionUser().getUserID();
		String now = getNow();
		model.put("update_id", userid);
		model.put("update_time", now);

		model.set("detail_id", getParaToInt());
		svc.deleteDetail(model);
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

		// 日志添加
		SysDictDetail model = getModel(SysDictDetail.class);
		Integer userid = getSessionUser().getUserID();
		String now = getNow();
		model.put("update_id", userid);
		model.put("update_time", now);
		if (pid != null && pid > 0) { // 更新
			svc.updateDetail(model);
		} else { // 新增
			model.remove("detail_id");
			model.put("create_id", userid);
			model.put("create_time", now);
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
			// 日志添加
			Integer userid = getSessionUser().getUserID();
			String now = getNow();
			model.put("update_id", userid);
			model.put("update_time", now);
			model.update();
		} else { // 新增
			SysDict model = getModel(SysDict.class, "model");
			model.remove("dict_id");
			// 日志添加
			Integer userid = getSessionUser().getUserID();
			String now = getNow();
			model.put("update_id", userid);
			model.put("update_time", now);

			model.put("create_id", userid);
			model.put("create_time", now);
			model.save();
		}
		renderMessage("保存成功");
	}

	public void delete_dict() {
		// 日志添加
		SysDict model = new SysDict();
		Integer userid = getSessionUser().getUserID();
		String now = getNow();
		model.put("update_id", userid);
		model.put("update_time", now);

		model.deleteById(getParaToInt());
		renderMessage("删除成功");
	}
}
