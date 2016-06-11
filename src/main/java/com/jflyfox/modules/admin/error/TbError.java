package com.jflyfox.modules.admin.error;

import com.jflyfox.component.base.BaseProjectModel;
import com.jflyfox.jfinal.component.annotation.ModelBind;

@ModelBind(table = "tb_error")
public class TbError extends BaseProjectModel<TbError> {

	private static final long serialVersionUID = 1L;

	/**
	 * 上传文件过多
	 */
	public static final int FILE_UPLOAD_MORE = 1;
	
	public static final TbError dao = new TbError();

    //columns START
    private String ID = "id";  // 主键
    private String TYPE = "type";  // 类型
    private String IP = "ip";  // IP地址
    private String USERID = "userid";  // 用户ID
    private String CONTENT = "content";  // 描述
    private String REMARK = "remark";  // 备注
    private String CREATE_TIME = "create_time";  // 创建时间
    private String CREATE_ID = "create_id";  // 创建者
    
    public TbError setId(java.lang.Integer value) {
        set(ID, value);
        return this;
    }

	public java.lang.Integer getId() {
		return get(ID);
	}
    public TbError setType(java.lang.Integer value) {
        set(TYPE, value);
        return this;
    }

	public java.lang.Integer getType() {
		return get(TYPE);
	}
    public TbError setIp(java.lang.String value) {
        set(IP, value);
        return this;
    }

	public java.lang.String getIp() {
		return get(IP);
	}
    public TbError setUserid(java.lang.Integer value) {
        set(USERID, value);
        return this;
    }

	public java.lang.Integer getUserid() {
		return get(USERID);
	}
    public TbError setContent(java.lang.String value) {
        set(CONTENT, value);
        return this;
    }

	public java.lang.String getContent() {
		return get(CONTENT);
	}
    public TbError setRemark(java.lang.String value) {
        set(REMARK, value);
        return this;
    }

	public java.lang.String getRemark() {
		return get(REMARK);
	}
    public TbError setCreateTime(java.lang.String value) {
        set(CREATE_TIME, value);
        return this;
    }

	public java.lang.String getCreateTime() {
		return get(CREATE_TIME);
	}
    public TbError setCreateId(java.lang.Integer value) {
        set(CREATE_ID, value);
        return this;
    }

	public java.lang.Integer getCreateId() {
		return get(CREATE_ID);
	}
}