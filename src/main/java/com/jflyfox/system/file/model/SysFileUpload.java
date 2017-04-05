package com.jflyfox.system.file.model;

import com.jflyfox.component.base.BaseProjectModel;
import com.jflyfox.jfinal.component.annotation.ModelBind;

@ModelBind(table = "sys_file_upload")
public class SysFileUpload extends BaseProjectModel<SysFileUpload> {

	private static final long serialVersionUID = 1L;
	public static final SysFileUpload dao = new SysFileUpload();

	// columns START
	private String ID = "id"; // id
	private String NAME = "name"; // 名称
	private String PATH = "path"; // 路径
	private String FACTPATH = "factpath"; // 实际路径
	private String EXT = "ext"; // 后缀
	private String ORIGINALNAME = "originalname"; // 原名称
	private String TYPE = "type"; // 类型
	private String SIZE = "size"; // 大小
	private String REMARK = "remark"; // 描述
	private String BUSINESS_TYPE = "business_type"; // 业务类型
	private String UPDATE_TIME = "update_time"; // 更新时间
	private String UPDATE_ID = "update_id"; // 更新人
	private String CREATE_TIME = "create_time"; // 创建时间
	private String CREATE_ID = "create_id"; // 创建者

	public SysFileUpload setId(java.lang.Integer value) {
		set(ID, value);
		return this;
	}

	public java.lang.Integer getId() {
		return get(ID);
	}

	public SysFileUpload setName(java.lang.String value) {
		set(NAME, value);
		return this;
	}

	public java.lang.String getName() {
		return get(NAME);
	}

	public SysFileUpload setPath(java.lang.String value) {
		set(PATH, value);
		return this;
	}

	public java.lang.String getPath() {
		return get(PATH);
	}

	public SysFileUpload setFactpath(java.lang.String value) {
		set(FACTPATH, value);
		return this;
	}

	public java.lang.String getFactpath() {
		return get(FACTPATH);
	}

	public SysFileUpload setExt(java.lang.String value) {
		set(EXT, value);
		return this;
	}

	public java.lang.String getExt() {
		return get(EXT);
	}

	public SysFileUpload setOriginalname(java.lang.String value) {
		set(ORIGINALNAME, value);
		return this;
	}

	public java.lang.String getOriginalname() {
		return get(ORIGINALNAME);
	}

	public SysFileUpload setType(java.lang.Integer value) {
		set(TYPE, value);
		return this;
	}

	public java.lang.Integer getType() {
		return get(TYPE);
	}

	public SysFileUpload setSize(java.lang.Long value) {
		set(SIZE, value);
		return this;
	}

	public java.lang.Long getSize() {
		return get(SIZE);
	}

	public SysFileUpload setRemark(java.lang.String value) {
		set(REMARK, value);
		return this;
	}

	public java.lang.String getRemark() {
		return get(REMARK);
	}

	public SysFileUpload setBusinessType(java.lang.Integer value) {
		set(BUSINESS_TYPE, value);
		return this;
	}

	public java.lang.Integer getBusinessType() {
		return get(BUSINESS_TYPE);
	}

	public SysFileUpload setUpdateTime(java.lang.String value) {
		set(UPDATE_TIME, value);
		return this;
	}

	public java.lang.String getUpdateTime() {
		return get(UPDATE_TIME);
	}

	public SysFileUpload setUpdateId(java.lang.Integer value) {
		set(UPDATE_ID, value);
		return this;
	}

	public java.lang.Integer getUpdateId() {
		return get(UPDATE_ID);
	}

	public SysFileUpload setCreateTime(java.lang.String value) {
		set(CREATE_TIME, value);
		return this;
	}

	public java.lang.String getCreateTime() {
		return get(CREATE_TIME);
	}

	public SysFileUpload setCreateId(java.lang.Integer value) {
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
		log += "[name:" + getName() + "]";
		log += "[ext:" + getExt() + "]";
		log += "[originalname:" + getOriginalname() + "]";
		log += "[type:" + getType() + "]";
		log += "[size:" + getSize() + "]";
		log += "[remark:" + getRemark() + "]";
		log += "[businessType:" + getBusinessType() + "]";
		log += "[updateTime:" + getUpdateTime() + "]";
		log += "[updateId:" + getUpdateId() + "]";
		log += "[createTime:" + getCreateTime() + "]";
		log += "[createId:" + getCreateId() + "]";
		return log;
	}
}