package com.jflyfox.modules.admin.site;

import java.util.List;

import com.jflyfox.component.util.JFlyFoxUtils;
import com.jflyfox.jfinal.base.BaseService;
import com.jflyfox.util.cache.CacheManager;

public class SiteService extends BaseService {

	/**
	 * 缓存
	 */
	private final static String cacheName = "SiteService";

	public void clearCache() {
		CacheManager.get(cacheName).clear();
	}

	public int getDefaultId() {
		return getDefaultSite().getId();
	}
	
	public TbSite getDefaultSite() {
		return TbSite.dao.findFirstCache(cacheName, "getDefaultSite" //
				, "select * from tb_site where site_defalut = ?", SiteConstant.SITE_DEFAULT_YES);
	}

	public List<TbSite> getSiteList() {
		return TbSite.dao.findCache(cacheName, "getSiteList", "select * from tb_site where status =  "
				+ JFlyFoxUtils.STATUS_SHOW);
	}

	public TbSite getSite(int siteId) {
		return TbSite.dao.findFirstCache(cacheName, "getSite_" + siteId //
		, "select * from tb_site where id = ? ", siteId);
	}

}
