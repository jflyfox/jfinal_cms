package com.jflyfox.modules.admin.folderrollpicture;

import com.jflyfox.component.base.BaseProjectModel;
import com.jflyfox.jfinal.component.annotation.ModelBind;

@ModelBind(table = "tb_folder_roll_picture")
public class TbFolderRollPicture extends BaseProjectModel<TbFolderRollPicture> {

	private static final long serialVersionUID = 1L;
	public static final TbFolderRollPicture dao = new TbFolderRollPicture();

	// columns START
	private String ID = "id"; // id
	private String FOLDER_ID = "folder_id"; // 目录id
	private String TITLE = "title"; // 题目
	private String CONTENT = "content"; // 内容
	private String SORT = "sort"; // 排序
	private String STATUS = "status"; // 状态//radio/2,隐藏,1,显示
	private String IMAGE_URL = "image_url"; // 图片路径
	private String IMAGE_NET_URL = "image_net_url"; // 网络图片路径
	private String URL = "url"; // 链接地址
	private String IS_DELETED = "is_deleted"; // 是否已删除
	private String UPDATE_TIME = "update_time"; // 更新时间
	private String UPDATE_ID = "update_id"; // 更新人
	private String CREATE_TIME = "create_time"; // 创建时间
	private String CREATE_ID = "create_id"; // 创建者

	public TbFolderRollPicture setId(java.lang.Integer value) {
		set(ID, value);
		return this;
	}

	public java.lang.Integer getId() {
		return get(ID);
	}

	public TbFolderRollPicture setFolderId(java.lang.Integer value) {
		set(FOLDER_ID, value);
		return this;
	}

	public java.lang.Integer getFolderId() {
		return get(FOLDER_ID);
	}

	public TbFolderRollPicture setTitle(java.lang.String value) {
		set(TITLE, value);
		return this;
	}

	public java.lang.String getTitle() {
		return get(TITLE);
	}

	public TbFolderRollPicture setContent(java.lang.String value) {
		set(CONTENT, value);
		return this;
	}

	public java.lang.String getContent() {
		return get(CONTENT);
	}

	public TbFolderRollPicture setSort(java.lang.Integer value) {
		set(SORT, value);
		return this;
	}

	public java.lang.Integer getSort() {
		return get(SORT);
	}

	public TbFolderRollPicture setStatus(java.lang.Integer value) {
		set(STATUS, value);
		return this;
	}

	public java.lang.Integer getStatus() {
		return get(STATUS);
	}

	public TbFolderRollPicture setImageUrl(java.lang.String value) {
		set(IMAGE_URL, value);
		return this;
	}

	public java.lang.String getImageUrl() {
		return get(IMAGE_URL);
	}

	public TbFolderRollPicture setImageNetUrl(java.lang.String value) {
		set(IMAGE_NET_URL, value);
		return this;
	}

	public java.lang.String getImageNetUrl() {
		return get(IMAGE_NET_URL);
	}

	public TbFolderRollPicture setUrl(java.lang.String value) {
		set(URL, value);
		return this;
	}

	public java.lang.String getUrl() {
		return get(URL);
	}

	public TbFolderRollPicture setIsDeleted(java.lang.Integer value) {
		set(IS_DELETED, value);
		return this;
	}

	public java.lang.Integer getIsDeleted() {
		return get(IS_DELETED);
	}

	public TbFolderRollPicture setUpdateTime(java.lang.String value) {
		set(UPDATE_TIME, value);
		return this;
	}

	public java.lang.String getUpdateTime() {
		return get(UPDATE_TIME);
	}

	public TbFolderRollPicture setUpdateId(java.lang.Integer value) {
		set(UPDATE_ID, value);
		return this;
	}

	public java.lang.Integer getUpdateId() {
		return get(UPDATE_ID);
	}

	public TbFolderRollPicture setCreateTime(java.lang.String value) {
		set(CREATE_TIME, value);
		return this;
	}

	public java.lang.String getCreateTime() {
		return get(CREATE_TIME);
	}

	public TbFolderRollPicture setCreateId(java.lang.Integer value) {
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
		log += "[title:" + getTitle() + "]";
		log += "[content:" + getContent() + "]";
		log += "[sort:" + getSort() + "]";
		log += "[status:" + getStatus() + "]";
		log += "[imageUrl:" + getImageUrl() + "]";
		log += "[imageNetUrl:" + getImageNetUrl() + "]";
		log += "[url:" + getUrl() + "]";
		log += "[isDeleted:" + getIsDeleted() + "]";
		log += "[updateTime:" + getUpdateTime() + "]";
		log += "[updateId:" + getUpdateId() + "]";
		log += "[createTime:" + getCreateTime() + "]";
		log += "[createId:" + getCreateId() + "]";
		return log;
	}
}