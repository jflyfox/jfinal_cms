package com.jflyfox.modules.admin.advicefeedback;

import com.jfinal.plugin.activerecord.Page;
import com.jflyfox.component.base.BaseProjectController;
import com.jflyfox.jfinal.component.annotation.ControllerBind;
import com.jflyfox.jfinal.component.db.SQLUtils;
import com.jflyfox.util.StrUtils;

/**
 * 意见反馈
 * 
 * @author flyfox 2014-4-24
 */
@ControllerBind(controllerKey = "/admin/advicefeedback")
public class AdvicefeedbackController extends BaseProjectController {

	private static final String path = "/pages/admin/advicefeedback/advicefeedback_";

	public void list() {
		TbAdviceFeedback model = getModelByAttr(TbAdviceFeedback.class);

		SQLUtils sql = new SQLUtils(" from tb_advice_feedback t where 1=1 ");
		if (model.getAttrValues().length != 0) {
			sql.setAlias("t");
			// 查询条件
			sql.whereLike("username", model.getStr("username"));
			sql.whereLike("qq", model.getStr("qq"));
			sql.whereLike("email", model.getStr("email"));
			sql.whereLike("telphone", model.getStr("telphone"));
		}

		// 排序
		String orderBy = getBaseForm().getOrderBy();
		if (StrUtils.isEmpty(orderBy)) {
			sql.append(" order by t.id desc ");
		} else {
			sql.append(" order by ").append(orderBy);
		}

		Page<TbAdviceFeedback> page = TbAdviceFeedback.dao.paginate(getPaginator(), "select t.* ", //
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
		TbAdviceFeedback model = TbAdviceFeedback.dao.findById(getParaToInt());
		setAttr("model", model);
		render(path + "view.html");
	}

	public void delete() {
		TbAdviceFeedback model = new TbAdviceFeedback();
		Integer userid= getSessionUser().getUserID();
		String now = getNow();
		model.put("update_id", userid);
		model.put("update_time", now);
		model.deleteById(getParaToInt());
		
		list();
	}

	public void edit() {
		TbAdviceFeedback model = TbAdviceFeedback.dao.findById(getParaToInt());
		setAttr("model", model);
		render(path + "edit.html");
	}

	public void save() {
		Integer pid = getParaToInt();
		TbAdviceFeedback model = getModel(TbAdviceFeedback.class);

		Integer userid = getSessionUser().getUserID();
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
		renderMessage("保存成功");
	}
}
