package com.jflyfox.modules.admin.folder;

import com.jflyfox.jfinal.base.BaseModel;
import com.jflyfox.jfinal.component.annotation.ModelBind;

@ModelBind(table = "tb_folder")
public class TbFolder extends BaseModel<TbFolder> {

	private static final long serialVersionUID = 1L;
	/**
	 * 根结点，首页
	 */
	public static final int ROOT = 1;

	public static final TbFolder dao = new TbFolder();

	// columns START
	private String ID = "id"; // 目录id
	private String PARENT_ID = "parent_id"; // 父目录id
	private String NAME = "name"; // 中文名
	private String KEY = "key"; // URL KEY
	private String PATH = "path"; // 路径
	private String CONTENT = "content"; // 描述
	private String SORT = "sort"; // 排序
	private String STATUS = "status"; // 状态：2 隐藏 1 显示
	private String TYPE = "type"; // 类型 1 普通目录 2 a标签 3 a标签_blank 4 直接加载url信息
	private String JUMP_URL = "jump_url"; // 跳转地址
	private String HEAD_KEYWORDS = "head_keywords"; // keywords
	private String HEAD_DESCRIPTION = "head_description"; // description
	private String UPDATE_TIME = "update_time"; // 更新时间
	private String CREATE_TIME = "create_time"; // 创建时间
	private String CREATE_ID = "create_id"; // 创建者

	public TbFolder setId(java.lang.Integer value) {
		set(ID, value);
		return this;
	}

	public java.lang.Integer getId() {
		return get(ID);
	}
	
	public TbFolder setParentId(java.lang.Integer value) {
		set(PARENT_ID, value);
		return this;
	}

	public java.lang.Integer getParentId() {
		return get(PARENT_ID);
	}

	public TbFolder setName(java.lang.String value) {
		set(NAME, value);
		return this;
	}

	public java.lang.String getName() {
		return get(NAME);
	}
	
	public TbFolder setKey(java.lang.String value) {
		set(KEY, value);
		return this;
	}

	public java.lang.String getKey() {
		return get(KEY);
	}

	public TbFolder setPath(java.lang.String value) {
		set(PATH, value);
		return this;
	}

	public java.lang.String getPath() {
		return get(PATH);
	}

	public TbFolder setContent(java.lang.String value) {
		set(CONTENT, value);
		return this;
	}

	public java.lang.String getContent() {
		return get(CONTENT);
	}

	public TbFolder setSort(java.lang.Integer value) {
		set(SORT, value);
		return this;
	}

	public java.lang.Integer getSort() {
		return get(SORT);
	}

	public TbFolder setStatus(java.lang.String value) {
		set(STATUS, value);
		return this;
	}

	public java.lang.String getStatus() {
		return get(STATUS);
	}

	public TbFolder setType(java.lang.Integer value) {
		set(TYPE, value);
		return this;
	}

	public java.lang.Integer getType() {
		return get(TYPE);
	}

	public TbFolder setJumpUrl(java.lang.String value) {
		set(JUMP_URL, value);
		return this;
	}

	public java.lang.String getJumpUrl() {
		return get(JUMP_URL);
	}

	public TbFolder setHeadKeywords(java.lang.String value) {
		set(HEAD_KEYWORDS, value);
		return this;
	}

	public java.lang.String getHeadKeywords() {
		return get(HEAD_KEYWORDS);
	}
	
	public TbFolder setHeadDescription(java.lang.String value) {
		set(HEAD_DESCRIPTION, value);
		return this;
	}

	public java.lang.String getHeadDescription() {
		return get(HEAD_DESCRIPTION);
	}
	
	public TbFolder setUpdateTime(java.lang.String value) {
		set(UPDATE_TIME, value);
		return this;
	}

	public java.lang.String getUpdateTime() {
		return get(UPDATE_TIME);
	}

	public TbFolder setCreateTime(java.lang.String value) {
		set(CREATE_TIME, value);
		return this;
	}

	public java.lang.String getCreateTime() {
		return get(CREATE_TIME);
	}

	public TbFolder setCreateId(java.lang.Integer value) {
		set(CREATE_ID, value);
		return this;
	}

	public java.lang.Integer getCreateId() {
		return get(CREATE_ID);
	}
}