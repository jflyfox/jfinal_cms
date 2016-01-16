package com.jflyfox.modules.admin.comment;

import com.jflyfox.component.base.BaseProjectModel;
import com.jflyfox.jfinal.component.annotation.ModelBind;

@ModelBind(table = "tb_comment")
public class TbComment extends BaseProjectModel<TbComment> {
	private static final long serialVersionUID = 1L;

	public static final TbComment dao = new TbComment();
	// columns START
	private String ID = "id"; // 主键
	private String FATHERID = "fatherid"; // 父评论ID
	private String ARTICLE_ID = "article_id"; // 文章ID
	private String CONTENT = "content"; // 内容
	private String STATUS = "status"; // 状态//select/11,评论未读,12,评论已读,21,回复未读,22,回复已读
	private String REPLY_USERID = "reply_userid"; //
	private String CREATE_TIME = "create_time"; // 创建时间
	private String CREATE_ID = "create_id"; // 创建者 评论者

	public TbComment setId(java.lang.Integer value) {
		set(ID, value);
		return this;
	}

	public java.lang.Integer getId() {
		return get(ID);
	}

	public TbComment setFatherId(java.lang.Integer value) {
		set(FATHERID, value);
		return this;
	}

	public java.lang.Integer getFatherId() {
		return get(FATHERID);
	}

	public TbComment setArticleId(java.lang.Integer value) {
		set(ARTICLE_ID, value);
		return this;
	}

	public java.lang.Integer getArticleId() {
		return get(ARTICLE_ID);
	}

	public TbComment setContent(java.lang.String value) {
		set(CONTENT, value);
		return this;
	}

	public java.lang.String getContent() {
		return get(CONTENT);
	}

	public TbComment setStatus(java.lang.Integer value) {
		set(STATUS, value);
		return this;
	}

	public java.lang.Integer getStatus() {
		return get(STATUS);
	}

	public TbComment setReplyUserid(java.lang.Integer value) {
		set(REPLY_USERID, value);
		return this;
	}

	public java.lang.Integer getReplyUserid() {
		return get(REPLY_USERID);
	}

	public TbComment setCreateTime(java.lang.String value) {
		set(CREATE_TIME, value);
		return this;
	}

	public java.lang.String getCreateTime() {
		return get(CREATE_TIME);
	}

	public TbComment setCreateId(java.lang.Integer value) {
		set(CREATE_ID, value);
		return this;
	}

	public java.lang.Integer getCreateId() {
		return get(CREATE_ID);
	}
}
