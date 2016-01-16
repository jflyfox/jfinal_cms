package com.jflyfox.modules.admin.tags;

import com.jflyfox.component.base.BaseProjectModel;
import com.jflyfox.jfinal.component.annotation.ModelBind;

@ModelBind(table = "tb_tags")
public class TbTags extends BaseProjectModel<TbTags> {

	private static final long serialVersionUID = 1L;
	public static final TbTags dao = new TbTags();

	// columns START
	private String ID = "id"; // id
	private String ARTICLE_ID = "article_id"; // 文章ID
	private String TAGNAME = "tagname"; // 标签内容
	private String CREATE_TIME = "create_time"; // 创建时间
	private String CREATE_ID = "create_id"; // 创建者

	public TbTags setId(java.lang.Integer value) {
		set(ID, value);
		return this;
	}

	public java.lang.Integer getId() {
		return get(ID);
	}

	public TbTags setArticleId(java.lang.Integer value) {
		set(ARTICLE_ID, value);
		return this;
	}

	public java.lang.Integer getArticleId() {
		return get(ARTICLE_ID);
	}

	public TbTags setTagname(java.lang.String value) {
		set(TAGNAME, value);
		return this;
	}

	public java.lang.String getTagname() {
		return get(TAGNAME);
	}

	public TbTags setCreateTime(java.lang.String value) {
		set(CREATE_TIME, value);
		return this;
	}

	public java.lang.String getCreateTime() {
		return get(CREATE_TIME);
	}

	public TbTags setCreateId(java.lang.Integer value) {
		set(CREATE_ID, value);
		return this;
	}

	public java.lang.Integer getCreateId() {
		return get(CREATE_ID);
	}
}