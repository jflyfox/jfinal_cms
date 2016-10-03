package com.jflyfox.modules.admin.comment;

import java.util.List;

import com.jfinal.plugin.activerecord.Page;
import com.jflyfox.component.base.BaseProjectController;
import com.jflyfox.jfinal.component.annotation.ControllerBind;
import com.jflyfox.jfinal.component.db.SQLUtils;
import com.jflyfox.modules.admin.article.TbArticle;
import com.jflyfox.util.StrUtils;

/**
 * 评论
 * 
 * @author flyfox 2014-4-24
 */
@ControllerBind(controllerKey = "/admin/comment")
public class CommentController extends BaseProjectController {

	private static final String path = "/pages/admin/comment/comment_";

	public void list() {
		TbComment model = getModelByAttr(TbComment.class);

		SQLUtils sql = new SQLUtils(" from tb_comment t " //
				+ " left join tb_article a on a.id = t.article_id where 1=1 ");
		if (model.getAttrValues().length != 0) {
			sql.setAlias("t");
			// 查询条件
			sql.whereLike("content", model.getStr("content"));

			sql.whereEquals("article_id", model.getInt("article_id"));
		}

		// 排序
		String orderBy = getBaseForm().getOrderBy();
		if (StrUtils.isEmpty(orderBy)) {
			sql.append(" order by t.id desc ");
		} else {
			sql.append(" order by ").append(orderBy);
		}

		Page<TbComment> page = TbComment.dao.paginate(getPaginator(), "select t.*,a.title articleName ", //
				sql.toString().toString());

		// 查询下拉框
		setAttr("selectArticle", selectArticle(model.getInt("article_id")));

		setAttr("page", page);
		setAttr("attr", model);
		render(path + "list.html");
	}

	public void add() {
		render(path + "add.html");
	}

	public void view() {
		TbComment model = TbComment.dao.findById(getParaToInt());
		TbArticle article = TbArticle.dao.findById(model.getInt("article_id"));
		model.put("articleName", article.getStr("title"));
		setAttr("model", model);
		render(path + "view.html");
	}

	public void delete() {
		// 日志添加
		TbComment model = new TbComment();
		Integer userid = getSessionUser().getUserID();
		String now = getNow();
		model.put("update_id", userid);
		model.put("update_time", now);
		model.deleteById(getParaToInt());

		list();
	}

	public void edit() {
		TbComment model = TbComment.dao.findById(getParaToInt());
		TbArticle article = TbArticle.dao.findById(model.getInt("article_id"));
		model.put("articleName", article.getStr("title"));
		setAttr("model", model);
		render(path + "edit.html");
	}

	public void save() {
		Integer pid = getParaToInt();
		TbComment model = getModel(TbComment.class);

		// 日志添加
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

	/**
	 * 目录复选框
	 * 
	 * 2015年1月28日 下午5:28:40 flyfox 369191470@qq.com
	 * 
	 * @return
	 */
	private String selectArticle(Integer selected) {
		List<TbArticle> list = TbArticle.dao.find(" select id,title from tb_article " //
				+ " where is_comment = 1 and status = 1 and type in (11,12) " // 显示，可评论，正常
				+ " order by sort,create_time desc ");
		StringBuffer sb = new StringBuffer("");
		if (list != null && list.size() > 0) {
			for (TbArticle article : list) {
				sb.append("<option value=\"");
				sb.append(article.getId());
				sb.append("\" ");
				sb.append(article.getId() == selected ? "selected" : "");
				sb.append(">");
				sb.append(article.getTitle());
				sb.append("</option>");
			}
		}
		return sb.toString();
	}
}
