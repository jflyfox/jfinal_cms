package com.jflyfox.modules.admin.image.model;

import com.jflyfox.component.base.BaseProjectModel;
import com.jflyfox.jfinal.component.annotation.ModelBind;

@ModelBind(table = "tb_image_album")
public class TbImageAlbum extends BaseProjectModel<TbImageAlbum> {

	private static final long serialVersionUID = 1L;
	public static final TbImageAlbum dao = new TbImageAlbum();

	// columns START
	private String ID = "id"; // 目录id
	private String PARENT_ID = "parent_id"; // 父ID
	private String NAME = "name"; // 相册名称
	private String REMARK = "remark"; // 描述
	private String SORT = "sort"; // 排序
	private String STATUS = "status"; // 状态//radio/2,隐藏,1,显示
	private String UPDATE_TIME = "update_time"; // 更新时间
	private String UPDATE_ID = "update_id"; // 更新人
	private String CREATE_TIME = "create_time"; // 创建时间
	private String CREATE_ID = "create_id"; // 创建者

	public TbImageAlbum setId(java.lang.Integer value) {
		set(ID, value);
		return this;
	}

	public java.lang.Integer getId() {
		return get(ID);
	}

	public TbImageAlbum setParentId(java.lang.Integer value) {
		set(PARENT_ID, value);
		return this;
	}

	public java.lang.Integer getParentId() {
		return get(PARENT_ID);
	}

	public TbImageAlbum setName(java.lang.String value) {
		set(NAME, value);
		return this;
	}

	public java.lang.String getName() {
		return get(NAME);
	}

	public TbImageAlbum setRemark(java.lang.String value) {
		set(REMARK, value);
		return this;
	}

	public java.lang.String getRemark() {
		return get(REMARK);
	}

	public TbImageAlbum setSort(java.lang.Integer value) {
		set(SORT, value);
		return this;
	}

	public java.lang.Integer getSort() {
		return get(SORT);
	}

	public TbImageAlbum setStatus(java.lang.Integer value) {
		set(STATUS, value);
		return this;
	}

	public java.lang.Integer getStatus() {
		return get(STATUS);
	}

	public TbImageAlbum setUpdateTime(java.lang.String value) {
		set(UPDATE_TIME, value);
		return this;
	}

	public java.lang.String getUpdateTime() {
		return get(UPDATE_TIME);
	}

	public TbImageAlbum setUpdateId(java.lang.Integer value) {
		set(UPDATE_ID, value);
		return this;
	}

	public java.lang.Integer getUpdateId() {
		return get(UPDATE_ID);
	}

	public TbImageAlbum setCreateTime(java.lang.String value) {
		set(CREATE_TIME, value);
		return this;
	}

	public java.lang.String getCreateTime() {
		return get(CREATE_TIME);
	}

	public TbImageAlbum setCreateId(java.lang.Integer value) {
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
		log += "[remark:" + getRemark() + "]";
		log += "[sort:" + getSort() + "]";
		log += "[status:" + getStatus() + "]";
		log += "[updateTime:" + getUpdateTime() + "]";
		log += "[updateId:" + getUpdateId() + "]";
		log += "[createTime:" + getCreateTime() + "]";
		log += "[createId:" + getCreateId() + "]";
		return log;
	}
}