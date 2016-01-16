package com.jflyfox.modules.front.articlelike;

import com.jflyfox.component.base.BaseProjectModel;
import com.jflyfox.jfinal.component.annotation.ModelBind;

@ModelBind(table = "tb_articlelike")
public class TbArticleLike extends BaseProjectModel<TbArticleLike> {

	private static final long serialVersionUID = 1L;
	public static final TbArticleLike dao = new TbArticleLike();

	// columns START
	private String ID = "id"; // id
	private String ARTICLE_ID = "article_id"; // 文章id
	private String CREATE_TIME = "create_time"; // 创建时间
	private String CREATE_ID = "create_id"; // 创建者

	public TbArticleLike setId(java.lang.Integer value) {
		set(ID, value);
		return this;
	}

	public java.lang.Integer getId() {
		return get(ID);
	}

	public TbArticleLike setArticleId(java.lang.Integer value) {
		set(ARTICLE_ID, value);
		return this;
	}

	public java.lang.Integer getArticleId() {
		return get(ARTICLE_ID);
	}

	public TbArticleLike setCreateTime(java.lang.String value) {
		set(CREATE_TIME, value);
		return this;
	}

	public java.lang.String getCreateTime() {
		return get(CREATE_TIME);
	}

	public TbArticleLike setCreateId(java.lang.Integer value) {
		set(CREATE_ID, value);
		return this;
	}

	public java.lang.Integer getCreateId() {
		return get(CREATE_ID);
	}
}