package com.jflyfox.modules.admin.contact;

import com.jflyfox.component.base.BaseProjectModel;
import com.jflyfox.jfinal.component.annotation.ModelBind;

@ModelBind(table = "tb_contact")
public class TbContact extends BaseProjectModel<TbContact> {

	private static final long serialVersionUID = 1L;
	public static final TbContact dao = new TbContact();

	// columns START
	private String ID = "id"; // 主键
	private String NAME = "name"; // 姓名
	private String PHONE = "phone"; // 手机号
	private String EMAIL = "email"; // Email
	private String ADDR = "addr"; // 地址
	private String BIRTHDAY = "birthday"; // 生日
	private String REMARK = "remark"; // 说明
	private String CREATE_TIME = "create_time"; // 创建时间
	private String CREATE_ID = "create_id"; // 创建者

	public TbContact setId(java.lang.Integer value) {
		set(ID, value);
		return this;
	}

	public java.lang.Integer getId() {
		return get(ID);
	}

	public TbContact setName(java.lang.String value) {
		set(NAME, value);
		return this;
	}

	public java.lang.String getName() {
		return get(NAME);
	}

	public TbContact setPhone(java.lang.String value) {
		set(PHONE, value);
		return this;
	}

	public java.lang.String getPhone() {
		return get(PHONE);
	}

	public TbContact setEmail(java.lang.String value) {
		set(EMAIL, value);
		return this;
	}

	public java.lang.String getEmail() {
		return get(EMAIL);
	}

	public TbContact setAddr(java.lang.String value) {
		set(ADDR, value);
		return this;
	}

	public java.lang.String getAddr() {
		return get(ADDR);
	}

	public TbContact setBirthday(java.lang.String value) {
		set(BIRTHDAY, value);
		return this;
	}

	public java.lang.String getBirthday() {
		return get(BIRTHDAY);
	}

	public TbContact setRemark(java.lang.String value) {
		set(REMARK, value);
		return this;
	}

	public java.lang.String getRemark() {
		return get(REMARK);
	}

	public TbContact setCreateTime(java.lang.String value) {
		set(CREATE_TIME, value);
		return this;
	}

	public java.lang.String getCreateTime() {
		return get(CREATE_TIME);
	}

	public TbContact setCreateId(java.lang.Integer value) {
		set(CREATE_ID, value);
		return this;
	}

	public java.lang.Integer getCreateId() {
		return get(CREATE_ID);
	}
}