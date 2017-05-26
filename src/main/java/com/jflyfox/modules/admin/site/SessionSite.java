package com.jflyfox.modules.admin.site;

import java.io.Serializable;

public class SessionSite implements Serializable {

	private static final long serialVersionUID = 1L;

	private int siteDefalutId; // 默认站点ID
	private String lastSite; // 上一次访问的域名+"_"+是否是后台
	private int siteId; // 前端站点ID
	private TbSite model; // 站点对象

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

	public TbSite getModel() {
		return model;
	}

	public void setModel(TbSite model) {
		this.model = model;
	}

}
