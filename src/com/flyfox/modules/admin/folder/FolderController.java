package com.flyfox.modules.admin.folder;

import com.flyfox.jfinal.base.BaseController;
import com.flyfox.jfinal.component.annotation.ControllerBind;
import com.flyfox.jfinal.component.db.SQLUtils;
import com.jfinal.plugin.activerecord.Page;

/**
 * 目录管理
 * 
 * @author flyfox 2014-2-11
 */
@ControllerBind(controllerKey = "/admin/folder")
public class FolderController extends BaseController {

	private static final String path = "/pages/admin/folder/folder_";

	public void index() {
		list();
	}

	public void list() {
		TbFolder model = getModelByAttr(TbFolder.class);

		SQLUtils sql = new SQLUtils(" from tb_folder t  " //
				+ " left join tb_folder f  on f.id = t.parent_id  where 1=1 ");
		if (model.getAttrValues().length != 0) {
			sql.setAlias("t");
			sql.whereLike("name", model.getStr("name"));
			sql.whereEquals("status", model.getInt("status"));
		} 

		sql.append(" order by sort,id ");
		Page<TbFolder> page = TbFolder.dao.paginate(getPaginator(), "select t.*,f.name as parentName ", //
				sql.toString().toString());

		// 下拉框
		setAttr("page", page);
		setAttr("attr", model);
		render(path + "list.html");
	}

	public void add() {
		render(path + "add.html");
	}

	public void view() {
		TbFolder model = TbFolder.dao.findById(getParaToInt());
		setAttr("model", model);

		TbFolder folder = TbFolder.dao.findById(model.getParentId());
		model.put("parentName", folder != null ? folder.getName() : null);
		render(path + "view.html");
	}

	public void delete() {
		// 不处理首页数据
		if (getParaToInt() == 1) {
			list();
			return;
		}
		
		// 日志添加
		TbFolder model = new TbFolder();
		Integer userid= getSessionUser().getUserID();
		String now = getNow();
		model.put("update_id", userid);
		model.put("update_time", now);
		model.deleteById(getParaToInt());

		// 更新目录缓存
		new FolderService().updateCache();

		list();
	}

	public void edit() {
		TbFolder model = TbFolder.dao.findById(getParaToInt());
		setAttr("model", model);

		render(path + "edit.html");
	}

	public void save() {
		Integer pid = getParaToInt();
		TbFolder model = getModel(TbFolder.class);
		

		// 日志添加
		Integer userid= getSessionUser().getUserID();
		String now = getNow();
		model.put("update_id", userid);
		model.put("update_time", now);
		if (pid != null && pid > 0) { // 更新
			model.update();
		} else { // 新增
			model.remove("id");
			model.put("create_id", userid);
			model.put("create_time", now);
			model.save();
		}

		// 更新目录缓存
		new FolderService().updateCache();
		renderMessage("保存成功");
	}

}
