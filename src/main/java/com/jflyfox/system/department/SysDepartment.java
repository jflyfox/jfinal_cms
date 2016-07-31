package com.jflyfox.system.department;

import com.jflyfox.component.base.BaseProjectModel;
import com.jflyfox.jfinal.component.annotation.ModelBind;

@ModelBind(table = "sys_department")
public class SysDepartment extends BaseProjectModel<SysDepartment> {

	private static final long serialVersionUID = 1L;
	public static final SysDepartment dao = new SysDepartment();

	// columns START
	private String ID = "id"; //
	private String PARENT_ID = "parent_id"; // 上级机构
	private String NAME = "name"; // 部门/11111
	private String CODE = "code"; // 机构编码
	private String SORT = "sort"; // 序号
	private String LINKMAN = "linkman"; // 联系人
	private String LINKMAN_NO = "linkman_no"; // 联系人电话
	private String REMARK = "remark"; // 机构描述
	private String UPDATE_TIME = "update_time"; // 更新时间
	private String UPDATE_ID = "update_id"; // 更新者
	private String CREATE_TIME = "create_time"; // 创建时间
	private String CREATE_ID = "create_id"; // 创建者

	public SysDepartment setId(java.lang.Integer value) {
		set(ID, value);
		return this;
	}

	public java.lang.Integer getId() {
		return get(ID);
	}

	public SysDepartment setParentId(java.lang.Integer value) {
		set(PARENT_ID, value);
		return this;
	}

	public java.lang.Integer getParentId() {
		return get(PARENT_ID);
	}

	public SysDepartment setName(java.lang.String value) {
		set(NAME, value);
		return this;
	}

	public java.lang.String getName() {
		return get(NAME);
	}

	public SysDepartment setCode(java.lang.String value) {
		set(CODE, value);
		return this;
	}

	public java.lang.String getCode() {
		return get(CODE);
	}

	public SysDepartment setSort(java.lang.Integer value) {
		set(SORT, value);
		return this;
	}

	public java.lang.Integer getSort() {
		return get(SORT);
	}

	public SysDepartment setLinkman(java.lang.String value) {
		set(LINKMAN, value);
		return this;
	}

	public java.lang.String getLinkman() {
		return get(LINKMAN);
	}

	public SysDepartment setLinkmanNo(java.lang.String value) {
		set(LINKMAN_NO, value);
		return this;
	}

	public java.lang.String getLinkmanNo() {
		return get(LINKMAN_NO);
	}

	public SysDepartment setRemark(java.lang.String value) {
		set(REMARK, value);
		return this;
	}

	public java.lang.String getRemark() {
		return get(REMARK);
	}

	public SysDepartment setUpdateTime(java.lang.String value) {
		set(UPDATE_TIME, value);
		return this;
	}

	public java.lang.String getUpdateTime() {
		return get(UPDATE_TIME);
	}

	public SysDepartment setUpdateId(java.lang.Integer value) {
		set(UPDATE_ID, value);
		return this;
	}

	public java.lang.Integer getUpdateId() {
		return get(UPDATE_ID);
	}

	public SysDepartment setCreateTime(java.lang.String value) {
		set(CREATE_TIME, value);
		return this;
	}

	public java.lang.String getCreateTime() {
		return get(CREATE_TIME);
	}

	public SysDepartment setCreateId(java.lang.Integer value) {
		set(CREATE_ID, value);
		return this;
	}

	public java.lang.Integer getCreateId() {
		return get(CREATE_ID);
	}

	@Override
	public String toString() {
		String log = "";
		log += "[id:" + getId() + "]";
		log += "[parentId:" + getParentId() + "]";
		log += "[name:" + getName() + "]";
		log += "[code:" + getCode() + "]";
		log += "[sort:" + getSort() + "]";
		log += "[linkman:" + getLinkman() + "]";
		log += "[linkmanNo:" + getLinkmanNo() + "]";
		log += "[remark:" + getRemark() + "]";
		log += "[updateTime:" + getUpdateTime() + "]";
		log += "[updateId:" + getUpdateId() + "]";
		log += "[createTime:" + getCreateTime() + "]";
		log += "[createId:" + getCreateId() + "]";
		return log;
	}
}