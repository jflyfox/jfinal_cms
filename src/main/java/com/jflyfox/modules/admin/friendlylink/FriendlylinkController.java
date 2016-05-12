package com.jflyfox.modules.admin.friendlylink;

import com.jfinal.plugin.activerecord.Page;
import com.jflyfox.component.base.BaseProjectController;
import com.jflyfox.jfinal.component.annotation.ControllerBind;
import com.jflyfox.jfinal.component.db.SQLUtils;
import com.jflyfox.util.StrUtils;

/**
 * 友情链接管理
 * 
 * @author flyfox 2014-4-24
 */
@ControllerBind(controllerKey = "/admin/friendlylink")
public class FriendlylinkController extends BaseProjectController {

	private static final String path = "/pages/admin/friendlylink/friendlylink_";

	public void list() {
		TbFriendlylink model = getModelByAttr(TbFriendlylink.class);

		SQLUtils sql = new SQLUtils(" from tb_friendlylink t"
				+ " left join tb_site s on s.id = t.site_id where 1=1 ");
		if (model.getAttrValues().length != 0) {
			sql.setAlias("t");
			sql.whereLike("name", model.getStr("name"));
		}
		
		// 排序
		String orderBy = getBaseForm().getOrderBy();
		if (StrUtils.isEmpty(orderBy)) {
			sql.append(" order by t.sort,t.id ");
		} else {
			sql.append(" order by ").append(orderBy);
		}

		Page<TbFriendlylink> page = TbFriendlylink.dao.paginate(getPaginator(), "select t.*,s.name as siteName ", //
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
		TbFriendlylink model = new TbFriendlylink();
		Integer userid= getSessionUser().getUserID();
		String now = getNow();
		model.put("update_id", userid);
		model.put("update_time", now);
		model.deleteById(getParaToInt());
		
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
		FriendlylinkCache.update();
		renderMessage("保存成功");
	}
}
