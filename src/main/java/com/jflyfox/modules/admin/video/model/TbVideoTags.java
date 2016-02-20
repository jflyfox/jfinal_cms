package com.jflyfox.modules.admin.video.model;

import com.jflyfox.component.base.BaseProjectModel;
import com.jflyfox.jfinal.component.annotation.ModelBind;

@ModelBind(table = "tb_video_tags")
public class TbVideoTags extends BaseProjectModel<TbVideoTags> {

	private static final long serialVersionUID = 1L;
	public static final TbVideoTags dao = new TbVideoTags();

    //columns START
    private String ID = "id";  // id
    private String VIDEO_ID = "video_id";  // 视频ID
    private String TAGNAME = "tagname";  // 标签内容
    private String CREATE_TIME = "create_time";  // 创建时间
    private String CREATE_ID = "create_id";  // 创建者
    
    public TbVideoTags setId(java.lang.Integer value) {
        set(ID, value);
        return this;
    }

	public java.lang.Integer getId() {
		return get(ID);
	}
    public TbVideoTags setVideoId(java.lang.Integer value) {
        set(VIDEO_ID, value);
        return this;
    }

	public java.lang.Integer getVideoId() {
		return get(VIDEO_ID);
	}
    public TbVideoTags setTagname(java.lang.String value) {
        set(TAGNAME, value);
        return this;
    }

	public java.lang.String getTagname() {
		return get(TAGNAME);
	}
    public TbVideoTags setCreateTime(java.lang.String value) {
        set(CREATE_TIME, value);
        return this;
    }

	public java.lang.String getCreateTime() {
		return get(CREATE_TIME);
	}
    public TbVideoTags setCreateId(java.lang.Integer value) {
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
		log += "[videoId:" + getVideoId() + "]";
		log += "[tagname:" + getTagname() + "]";
		log += "[createTime:" + getCreateTime() + "]";
		log += "[createId:" + getCreateId() + "]";
		return log;
	}
}