package com.jflyfox.modules.admin.friendlylink;

import com.jflyfox.component.base.BaseProjectModel;
import com.jflyfox.jfinal.component.annotation.ModelBind;

@ModelBind(table = "tb_friendlylink")
public class TbFriendlylink extends BaseProjectModel<TbFriendlylink> {

	private static final long serialVersionUID = 1L;
	public static final TbFriendlylink dao = new TbFriendlylink();

	// columns START
	private String ID = "id"; // 主键
	private String NAME = "name"; // 名称/11111/
	private String URL = "url"; // URL
	private String SORT = "sort"; // 排序号
	private String STATE = "state"; // 是否显示//radio/1,显示,2,不显示
	private String TYPE = "type"; // 类型//select/1,见数据字典
	private String REMARK = "remark"; // 备注//textarea
	private String CREATE_TIME = "create_time"; // 创建时间
	private String CREATE_ID = "create_id"; // 创建者

	public TbFriendlylink setId(java.lang.Integer value) {
		set(ID, value);
		return this;
	}

	public java.lang.Integer getId() {
		return get(ID);
	}

	public TbFriendlylink setName(java.lang.String value) {
		set(NAME, value);
		return this;
	}

	public java.lang.String getName() {
		return get(NAME);
	}

	public TbFriendlylink setUrl(java.lang.String value) {
		set(URL, value);
		return this;
	}

	public java.lang.String getUrl() {
		return get(URL);
	}

	public TbFriendlylink setSort(java.lang.Integer value) {
		set(SORT, value);
		return this;
	}

	public java.lang.Integer getSort() {
		return get(SORT);
	}

	public TbFriendlylink setState(java.lang.Integer value) {
		set(STATE, value);
		return this;
	}

	public java.lang.Integer getState() {
		return get(STATE);
	}

	public TbFriendlylink setType(java.lang.Integer value) {
		set(TYPE, value);
		return this;
	}

	public java.lang.Integer getType() {
		return get(TYPE);
	}

	public TbFriendlylink setRemark(java.lang.String value) {
		set(REMARK, value);
		return this;
	}

	public java.lang.String getRemark() {
		return get(REMARK);
	}

	public TbFriendlylink setCreateTime(java.lang.String value) {
		set(CREATE_TIME, value);
		return this;
	}

	public java.lang.String getCreateTime() {
		return get(CREATE_TIME);
	}

	public TbFriendlylink setCreateId(java.lang.Integer value) {
		set(CREATE_ID, value);
		return this;
	}

	public java.lang.Integer getCreateId() {
		return get(CREATE_ID);
	}
}