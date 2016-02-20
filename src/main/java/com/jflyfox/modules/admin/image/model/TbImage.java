package com.jflyfox.modules.admin.image.model;

import com.jflyfox.component.base.BaseProjectModel;
import com.jflyfox.jfinal.component.annotation.ModelBind;

@ModelBind(table = "tb_image")
public class TbImage extends BaseProjectModel<TbImage> {

	private static final long serialVersionUID = 1L;
	public static final TbImage dao = new TbImage();

	// columns START
	private String ID = "id"; // 主键
	private String ALBUM_ID = "album_id"; // 相册ID
	private String ALBUM_NAME = "album_name"; // 相册名称
	private String NAME = "name"; // 图片名称
	private String LINKURL = "linkurl"; // 链接地址
	private String CDNURL = "cdnurl"; // CDN地址
	private String IMAGE_URL = "image_url"; // 图片路径
	private String IMAGE_NET_URL = "image_net_url"; // 网络图片路径
	private String EXT = "ext"; // 扩展名
	private String WIDTH = "width"; // 宽
	private String HEIGHT = "height"; // 高
	private String STATUS = "status"; // 状态//radio/2,隐藏,1,显示
	private String IS_COMMENT = "is_comment"; // 是否评论//radio/2,否,1,是
	private String IS_RECOMMEND = "is_recommend"; // 是否推荐：2 否 1 是
	private String SORT = "sort"; // 排序
	private String REMARK = "remark"; // 备注
	private String PUBLISH_TIME = "publish_time"; // 发布时间
	private String PUBLISH_USER = "publish_user"; // 发布者
	private String UPDATE_TIME = "update_time"; // 更新时间
	private String UPDATE_ID = "update_id"; // 创建者
	private String CREATE_TIME = "create_time"; // 创建时间
	private String CREATE_ID = "create_id"; // 创建者

	public TbImage setId(java.lang.Integer value) {
		set(ID, value);
		return this;
	}

	public java.lang.Integer getId() {
		return get(ID);
	}

	public TbImage setAlbumId(java.lang.Integer value) {
		set(ALBUM_ID, value);
		return this;
	}

	public java.lang.Integer getAlbumId() {
		return get(ALBUM_ID);
	}

	public TbImage setAlbumName(java.lang.String value) {
		set(ALBUM_NAME, value);
		return this;
	}

	public java.lang.String getAlbumName() {
		return get(ALBUM_NAME);
	}

	public TbImage setName(java.lang.String value) {
		set(NAME, value);
		return this;
	}

	public java.lang.String getName() {
		return get(NAME);
	}

	public TbImage setLinkurl(java.lang.String value) {
		set(LINKURL, value);
		return this;
	}

	public java.lang.String getLinkurl() {
		return get(LINKURL);
	}

	public TbImage setCdnurl(java.lang.String value) {
		set(CDNURL, value);
		return this;
	}

	public java.lang.String getCdnurl() {
		return get(CDNURL);
	}

	public TbImage setImageUrl(java.lang.String value) {
		set(IMAGE_URL, value);
		return this;
	}

	public java.lang.String getImageUrl() {
		return get(IMAGE_URL);
	}

	public TbImage setImageNetUrl(java.lang.String value) {
		set(IMAGE_NET_URL, value);
		return this;
	}

	public java.lang.String getImageNetUrl() {
		return get(IMAGE_NET_URL);
	}

	public TbImage setExt(java.lang.String value) {
		set(EXT, value);
		return this;
	}

	public java.lang.String getExt() {
		return get(EXT);
	}

	public TbImage setWidth(java.lang.String value) {
		set(WIDTH, value);
		return this;
	}

	public java.lang.String getWidth() {
		return get(WIDTH);
	}

	public TbImage setHeight(java.lang.String value) {
		set(HEIGHT, value);
		return this;
	}

	public java.lang.String getHeight() {
		return get(HEIGHT);
	}

	public TbImage setStatus(java.lang.Integer value) {
		set(STATUS, value);
		return this;
	}

	public java.lang.Integer getStatus() {
		return get(STATUS);
	}

	public TbImage setIsComment(java.lang.Integer value) {
		set(IS_COMMENT, value);
		return this;
	}

	public java.lang.Integer getIsComment() {
		return get(IS_COMMENT);
	}

	public TbImage setIsRecommend(java.lang.Integer value) {
		set(IS_RECOMMEND, value);
		return this;
	}

	public java.lang.Integer getIsRecommend() {
		return get(IS_RECOMMEND);
	}

	public TbImage setSort(java.lang.Integer value) {
		set(SORT, value);
		return this;
	}

	public java.lang.Integer getSort() {
		return get(SORT);
	}

	public TbImage setRemark(java.lang.String value) {
		set(REMARK, value);
		return this;
	}

	public java.lang.String getRemark() {
		return get(REMARK);
	}

	public TbImage setPublishTime(java.lang.String value) {
		set(PUBLISH_TIME, value);
		return this;
	}

	public java.lang.String getPublishTime() {
		return get(PUBLISH_TIME);
	}

	public TbImage setPublishUser(java.lang.String value) {
		set(PUBLISH_USER, value);
		return this;
	}

	public java.lang.String getPublishUser() {
		return get(PUBLISH_USER);
	}

	public TbImage setUpdateTime(java.lang.String value) {
		set(UPDATE_TIME, value);
		return this;
	}

	public java.lang.String getUpdateTime() {
		return get(UPDATE_TIME);
	}

	public TbImage setUpdateId(java.lang.Integer value) {
		set(UPDATE_ID, value);
		return this;
	}

	public java.lang.Integer getUpdateId() {
		return get(UPDATE_ID);
	}

	public TbImage setCreateTime(java.lang.String value) {
		set(CREATE_TIME, value);
		return this;
	}

	public java.lang.String getCreateTime() {
		return get(CREATE_TIME);
	}

	public TbImage setCreateId(java.lang.Integer value) {
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
		log += "[albumId:" + getAlbumId() + "]";
		log += "[albumName:" + getAlbumName() + "]";
		log += "[name:" + getName() + "]";
		log += "[linkurl:" + getLinkurl() + "]";
		log += "[cdnurl:" + getCdnurl() + "]";
		log += "[imageUrl:" + getImageUrl() + "]";
		log += "[imageNetUrl:" + getImageNetUrl() + "]";
		log += "[ext:" + getExt() + "]";
		log += "[width:" + getWidth() + "]";
		log += "[height:" + getHeight() + "]";
		log += "[status:" + getStatus() + "]";
		log += "[isComment:" + getIsComment() + "]";
		log += "[isRecommend:" + getIsRecommend() + "]";
		log += "[sort:" + getSort() + "]";
		log += "[remark:" + getRemark() + "]";
		log += "[publishTime:" + getPublishTime() + "]";
		log += "[publishUser:" + getPublishUser() + "]";
		log += "[updateTime:" + getUpdateTime() + "]";
		log += "[updateId:" + getUpdateId() + "]";
		log += "[createTime:" + getCreateTime() + "]";
		log += "[createId:" + getCreateId() + "]";
		return log;
	}
}