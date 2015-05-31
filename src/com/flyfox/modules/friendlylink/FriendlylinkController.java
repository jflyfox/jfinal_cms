package com.flyfox.modules.friendlylink;

import com.flyfox.jfinal.base.BaseController;
import com.flyfox.jfinal.component.db.SQLUtils;
import com.jfinal.plugin.activerecord.Page;

/**
 * 友情链接管理
 * 
 * @author flyfox 2014-4-24
 */
public class FriendlylinkController extends BaseController {

	private static final String path = "/pages/friendlylink/friendlylink_";

	public void list() {
		TbFriendlylink model = getModelByAttr(TbFriendlylink.class);

		SQLUtils sql = new SQLUtils(" from tb_friendlylink t where 1=1 ");
		if (model.getAttrValues().length != 0) {
			sql.setAlias("t");
			sql.whereLike("name", model.getStr("name"));
		}
		sql.append(" order by sort,id ");

		Page<TbFriendlylink> page = TbFriendlylink.dao.paginate(getPaginator(), "select t.* ", //
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
		TbFriendlylink model = TbFriendlylink.dao.findById(getParaToInt());
		setAttr("model", model);
		render(path + "view.html");
	}

	public void delete() {
		TbFriendlylink.dao.deleteById(getParaToInt());
		FriendlylinkCache.update();
		list();
	}

	public void edit() {
		TbFriendlylink model = TbFriendlylink.dao.findById(getParaToInt());
		setAttr("model", model);
		render(path + "edit.html");
	}

	public void save() {
		Integer pid = getParaToInt();
		TbFriendlylink model = getModel(TbFriendlylink.class);
		if (pid != null && pid > 0) { // 更新
			model.update();
		} else { // 新增
			model.remove("id");
			model.put("create_id", getSessionUser().getUserID());
			model.put("create_time", getNow());
			model.save();
		}
		FriendlylinkCache.update();
		renderMessage("保存成功");
	}
}
