package com.jflyfox.modules.admin.folder;

import com.jfinal.plugin.activerecord.Page;
import com.jflyfox.component.base.BaseProjectController;
import com.jflyfox.jfinal.component.annotation.ControllerBind;
import com.jflyfox.jfinal.component.db.SQLUtils;
import com.jflyfox.util.StrUtils;

/**
 * 目录管理
 * 
 * @author flyfox 2014-2-11
 */
@ControllerBind(controllerKey = "/admin/folder")
public class FolderController extends BaseProjectController {

	private static final String path = "/pages/admin/folder/folder_";

	public void index() {
		list();
	}

	public void list() {
		TbFolder model = getModelByAttr(TbFolder.class);

		SQLUtils sql = new SQLUtils(" from tb_folder t  " //
				+ " left join tb_folder f  on f.id = t.parent_id  where 1=1 ");
		sql.setAlias("t");
		if (model.getAttrValues().length != 0) {
			sql.whereLike("name", model.getStr("name"));
			sql.whereEquals("status", model.getInt("status"));
		}
		// 站点设置
		int siteId = getSessionSite().getBackSiteId();
		sql.whereEquals("site_id", siteId);

		// 排序
		String orderBy = getBaseForm().getOrderBy();
		if (StrUtils.isEmpty(orderBy)) {
			sql.append(" order by t.sort,t.id ");
		} else {
			sql.append(" order by t.").append(orderBy);
		}

		Page<TbFolder> page = TbFolder.dao.paginate(getPaginator(), "select t.*,f.name as parentName ", //
				sql.toString().toString());

		// 下拉框
		setAttr("page", page);
		setAttr("attr", model);

		setAttr("folders", new FolderService().getFolders(siteId));
		render(path + "list.html");
	}

	public void add() {
		// 获取页面信息,设置目录传入
		TbFolder model = TbFolder.dao.findById(getParaToInt());
		setAttr("selectParentFolder", selectFolder(model == null ? 0 : model.getId(), 0));

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
		Integer userid = getSessionUser().getUserID();
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

		// 下拉框
		setAttr("selectParentFolder", selectFolder(model.getParentId(), model.getId()));

		render(path + "edit.html");
	}

	public void save() {
		Integer pid = getParaToInt();
		TbFolder model = getModel(TbFolder.class);

		// 日志添加
		Integer userid = getSessionUser().getUserID();
		String now = getNow();
		model.put("update_id", userid);
		model.put("update_time", now);

		if (pid != null && pid > 0) { // 更新
			model.update();
		} else { // 新增
			// 站点设置
			model.setSiteId(getSessionSite().getBackSiteId());

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
