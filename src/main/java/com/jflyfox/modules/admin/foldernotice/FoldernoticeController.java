package com.jflyfox.modules.admin.foldernotice;

import com.jfinal.plugin.activerecord.Page;
import com.jflyfox.component.base.BaseProjectController;
import com.jflyfox.component.util.JFlyFoxUtils;
import com.jflyfox.jfinal.component.annotation.ControllerBind;
import com.jflyfox.jfinal.component.db.SQLUtils;
import com.jflyfox.modules.admin.folder.TbFolder;
import com.jflyfox.util.StrUtils;

/**
 * 栏目公告
 * 
 * @author flyfox 2014-4-24
 */
@ControllerBind(controllerKey = "/admin/foldernotice")
public class FoldernoticeController extends BaseProjectController {

	private static final String path = "/pages/admin/foldernotice/foldernotice_";

	public void list() {
		TbFolderNotice model = getModelByAttr(TbFolderNotice.class);

		SQLUtils sql = new SQLUtils(" from tb_folder_notice t " //
				+ " left join tb_folder f on f.id = t.folder_id " //
				+ " where is_deleted = " + JFlyFoxUtils.IS_DELETED_NO + " ");
		if (model.getAttrValues().length != 0) {
			sql.setAlias("t");
			// 查询条件
			sql.whereEquals("folder_id", model.getInt("folder_id"));
		}
		// 站点设置
		sql.append(" and site_id = " + getSessionSite().getBackSiteId());

		// 排序
		String orderBy = getBaseForm().getOrderBy();
		if (StrUtils.isEmpty(orderBy)) {
			sql.append(" order by t.folder_id,t.sort,t.id desc ");
		} else {
			sql.append(" order by ").append(orderBy);
		}

		Page<TbFolderNotice> page = TbFolderNotice.dao.paginate(getPaginator(), "select t.*,f.name as folderName ", //
				sql.toString().toString());

		// 查询下拉框
		setAttr("selectFolder", selectFolder(model.getInt("folder_id")));

		setAttr("page", page);
		setAttr("attr", model);
		render(path + "list.html");
	}

	public void add() {
		// 查询下拉框
		setAttr("selectFolder", selectFolder(0));

		render(path + "add.html");
	}

	public void view() {
		TbFolderNotice model = TbFolderNotice.dao.findById(getParaToInt());
		TbFolder folder = TbFolder.dao.findById(model.getInt("folder_id"));
		model.put("folderName", folder.getStr("name"));

		setAttr("model", model);
		render(path + "view.html");
	}

	public void delete() {
		TbFolderNotice model = TbFolderNotice.dao.findById(getParaToInt());
		Integer userid = getSessionUser().getUserID();
		String now = getNow();
		model.put("update_id", userid);
		model.put("update_time", now);
		model.setIsDeleted(JFlyFoxUtils.IS_DELETED_YES);
		model.update();
		// model.deleteById(getParaToInt());

		list();
	}

	public void edit() {
		TbFolderNotice model = TbFolderNotice.dao.findById(getParaToInt());
		// 查询下拉框
		setAttr("selectFolder", selectFolder(model.getInt("folder_id")));

		setAttr("model", model);
		render(path + "edit.html");
	}

	public void save() {
		Integer pid = getParaToInt();
		TbFolderNotice model = getModel(TbFolderNotice.class);

		Integer userid = getSessionUser().getUserID();
		String now = getNow();
		model.put("update_id", userid);
		model.put("update_time", now);
		if (pid != null && pid > 0) { // 更新
			model.update();
		} else { // 新增
			model.remove("id");
			model.setIsDeleted(JFlyFoxUtils.IS_DELETED_NO);
			model.put("create_id", userid);
			model.put("create_time", now);
			model.save();
		}
		renderMessage("保存成功");
	}
}
