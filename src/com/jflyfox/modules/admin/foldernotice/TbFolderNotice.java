package com.jflyfox.modules.admin.foldernotice;

import com.jflyfox.component.base.BaseProjectModel;
import com.jflyfox.jfinal.component.annotation.ModelBind;

@ModelBind(table = "tb_folder_notice")
public class TbFolderNotice extends BaseProjectModel<TbFolderNotice> {

	private static final long serialVersionUID = 1L;
	public static final TbFolderNotice dao = new TbFolderNotice();

	// columns START
	private String ID = "id"; // 主键
	private String FOLDER_ID = "folder_id"; // 目录id
	private String TYPE = "type"; // 类型
	private String ICON = "icon"; // 图标
	private String CONTENT = "content"; // 内容
	private String URL = "url"; // 链接地址
	private String SORT = "sort"; // 排序
	private String STATUS = "status"; // 状态//radio/2,隐藏,1,显示
	private String IS_DELETED = "is_deleted"; // 是否已删除
	private String UPDATE_TIME = "update_time"; // 更新时间
	private String UPDATE_ID = "update_id"; // 更新人
	private String CREATE_TIME = "create_time"; // 创建时间
	private String CREATE_ID = "create_id"; // 创建者

	public TbFolderNotice setId(java.lang.Integer value) {
		set(ID, value);
		return this;
	}

	public java.lang.Integer getId() {
		return get(ID);
	}

	public TbFolderNotice setFolderId(java.lang.Integer value) {
		set(FOLDER_ID, value);
		return this;
	}

	public java.lang.Integer getFolderId() {
		return get(FOLDER_ID);
	}

	public TbFolderNotice setType(java.lang.Integer value) {
		set(TYPE, value);
		return this;
	}

	public java.lang.Integer getType() {
		return get(TYPE);
	}

	public TbFolderNotice setIcon(java.lang.String value) {
		set(ICON, value);
		return this;
	}

	public java.lang.String getIcon() {
		return get(ICON);
	}

	public TbFolderNotice setContent(java.lang.String value) {
		set(CONTENT, value);
		return this;
	}

	public java.lang.String getContent() {
		return get(CONTENT);
	}

	public TbFolderNotice setUrl(java.lang.String value) {
		set(URL, value);
		return this;
	}

	public java.lang.String getUrl() {
		return get(URL);
	}

	public TbFolderNotice setSort(java.lang.Integer value) {
		set(SORT, value);
		return this;
	}

	public java.lang.Integer getSort() {
		return get(SORT);
	}

	public TbFolderNotice setStatus(java.lang.Integer value) {
		set(STATUS, value);
		return this;
	}

	public java.lang.Integer getStatus() {
		return get(STATUS);
	}

	public TbFolderNotice setIsDeleted(java.lang.Integer value) {
		set(IS_DELETED, value);
		return this;
	}

	public java.lang.Integer getIsDeleted() {
		return get(IS_DELETED);
	}

	public TbFolderNotice setUpdateTime(java.lang.String value) {
		set(UPDATE_TIME, value);
		return this;
	}

	public java.lang.String getUpdateTime() {
		return get(UPDATE_TIME);
	}

	public TbFolderNotice setUpdateId(java.lang.Integer value) {
		set(UPDATE_ID, value);
		return this;
	}

	public java.lang.Integer getUpdateId() {
		return get(UPDATE_ID);
	}

	public TbFolderNotice setCreateTime(java.lang.String value) {
		set(CREATE_TIME, value);
		return this;
	}

	public java.lang.String getCreateTime() {
		return get(CREATE_TIME);
	}

	public TbFolderNotice setCreateId(java.lang.Integer value) {
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
		log += "[folderId:" + getFolderId() + "]";
		log += "[type:" + getType() + "]";
		log += "[icon:" + getIcon() + "]";
		log += "[content:" + getContent() + "]";
		log += "[url:" + getUrl() + "]";
		log += "[sort:" + getSort() + "]";
		log += "[status:" + getStatus() + "]";
		log += "[isDeleted:" + getIsDeleted() + "]";
		log += "[updateTime:" + getUpdateTime() + "]";
		log += "[updateId:" + getUpdateId() + "]";
		log += "[createTime:" + getCreateTime() + "]";
		log += "[createId:" + getCreateId() + "]";
		return log;
	}
}