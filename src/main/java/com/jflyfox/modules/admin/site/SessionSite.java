package com.jflyfox.modules.admin.site;

import java.io.Serializable;

public class SessionSite implements Serializable {

	private static final long serialVersionUID = 1L;

	private int siteDefalutId; // 默认站点ID
	private String lastSite; // 上一次访问的域名
	private int siteId; // 前端站点ID
	private int backSiteId; // 后端站点ID
	private TbSite model; // 站点对象
	private TbSite backModel; // 后台站点对象

	public int getSiteDefalutId() {
		return siteDefalutId;
	}

	public void setSiteDefalutId(int siteDefalutId) {
		this.siteDefalutId = siteDefalutId;
	}
	
	public String getLastSite() {
		return lastSite;
	}

	public void setLastSite(String lastSite) {
		this.lastSite = lastSite;
	}

	public int getSiteId() {
		return siteId;
	}

	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}

	public int getBackSiteId() {
		return backSiteId;
	}

	public void setBackSiteId(int backSiteId) {
		this.backSiteId = backSiteId;
	}

	public TbSite getModel() {
		return model;
	}

	public void setModel(TbSite model) {
		this.model = model;
	}

	public TbSite getBackModel() {
		return backModel;
	}

	public void setBackModel(TbSite backModel) {
		this.backModel = backModel;
	}

}
