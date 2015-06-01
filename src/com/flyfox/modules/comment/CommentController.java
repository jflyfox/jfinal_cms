package com.flyfox.modules.comment;

import com.flyfox.jfinal.base.BaseController;
import com.flyfox.jfinal.component.db.SQLUtils;
import com.flyfox.modules.article.TbArticle;
import com.jfinal.plugin.activerecord.Page;

/**
 * 评论
 * 
 * @author flyfox 2014-4-24
 */
public class CommentController extends BaseController {

	private static final String path = "/pages/comment/comment_";

	public void list() {
		TbComment model = getModelByAttr(TbComment.class);

		SQLUtils sql = new SQLUtils(" from tb_comment t "
				+ " left join tb_article a on a.id = t.article_id where 1=1 ");
		if (model.getAttrValues().length != 0) {
			sql.setAlias("t");
			// 查询条件
			sql.whereLike("content", model.getStr("content"));
		}
		sql.append(" order by t.id desc ");

		Page<TbComment> page = TbComment.dao.paginate(getPaginator(), "select t.*,a.title articleName ", //
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
		TbComment model = TbComment.dao.findById(getParaToInt());
		TbArticle article = TbArticle.dao.findById(model.getInt("article_id"));
		model.put("articleName", article.getStr("title"));
		setAttr("model", model);
		render(path + "view.html");
	}

	public void delete() {
		TbComment.dao.deleteById(getParaToInt());

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
		if (pid != null && pid > 0) { // 更新
			model.update();
		} else { // 新增
			model.remove("id");
			model.put("create_id", getSessionUser().getUserID());
			model.put("create_time", getNow());
			model.save();
		}
		renderMessage("保存成功");
	}
}
