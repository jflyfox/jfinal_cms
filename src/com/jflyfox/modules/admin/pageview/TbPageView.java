package com.jflyfox.modules.admin.pageview;

import com.jflyfox.component.base.BaseProjectModel;
import com.jflyfox.jfinal.component.annotation.ModelBind;

@ModelBind(table = "tb_pageview")
public class TbPageView extends BaseProjectModel<TbPageView> {

	private static final long serialVersionUID = 1L;

	public static final TbPageView dao = new TbPageView();
	// columns START
	private String ID = "id"; // 主键
	private String IP = "ip"; // IP地址
	private String USERID = "userid"; // 用户ID
	private String CREATE_DAY = "create_day"; // 创建时间到天
	private String CREATE_TIME = "create_time"; // 创建时间

	public TbPageView setId(java.lang.Integer value) {
		set(ID, value);
		return this;
	}

	public java.lang.Integer getId() {
		return get(ID);
	}

	public TbPageView setIp(java.lang.String value) {
		set(IP, value);
		return this;
	}

	public java.lang.String getIp() {
		return get(IP);
	}

	public TbPageView setUserid(java.lang.Integer value) {
		set(USERID, value);
		return this;
	}

	public java.lang.Integer getUserid() {
		return get(USERID);
	}

	public TbPageView setCreateDay(java.lang.String value) {
		set(CREATE_DAY, value);
		return this;
	}

	public java.lang.String getCreateDay() {
		return get(CREATE_DAY);
	}

	public TbPageView setCreateTime(java.lang.String value) {
		set(CREATE_TIME, value);
		return this;
	}

	public java.lang.String getCreateTime() {
		return get(CREATE_TIME);
	}
}