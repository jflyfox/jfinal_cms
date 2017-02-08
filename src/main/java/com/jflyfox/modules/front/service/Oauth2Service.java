package com.jflyfox.modules.front.service;

import com.jflyfox.jfinal.component.oauth.OauthBaidu;
import com.jflyfox.jfinal.component.oauth.OauthQQ;
import com.jflyfox.jfinal.component.oauth.OauthSina;
import com.jflyfox.jfinal.component.oauth.util.OathConfig;
import com.jflyfox.modules.admin.site.SiteService;
import com.jflyfox.modules.admin.site.TbSite;
import com.jflyfox.util.cache.Cache;
import com.jflyfox.util.cache.CacheManager;

public class Oauth2Service {

	/**
	 * oauth2认证
	 */
	private final static String cacheName = "Oauth2Controller";

	public void clearCahce() {
		CacheManager.get(cacheName).clear();
	}

	public OauthBaidu getBaidu(TbSite site) {
		Cache cache = CacheManager.get(cacheName);
		OauthBaidu oauth = cache.get("baidu_" + site.getId());
		if (oauth == null) {
			oauth = new OauthBaidu();
			int defaultSiteId = new SiteService().getDefaultId();
			if (site.getId() != defaultSiteId) {
				oauth.setClientId(OathConfig.getValue(site.getTemplate() + "." + "openid_baidu"));
				oauth.setClientSecret(OathConfig.getValue(site.getTemplate() + "." + "openkey_baidu"));
				oauth.setRedirectUri(OathConfig.getValue(site.getTemplate() + "." + "redirect_baidu"));
			}
			cache.add("baidu_" + site.getId(), oauth);
		}

		return oauth;
	}

	public OauthSina getSina(TbSite site) {
		Cache cache = CacheManager.get(cacheName);
		OauthSina oauth = cache.get("sina_" + site.getId());
		if (oauth == null) {
			oauth = new OauthSina();
			int defaultSiteId = new SiteService().getDefaultId();
			if (site.getId() != defaultSiteId) {
				oauth.setClientId(OathConfig.getValue(site.getTemplate() + "." + "openid_sina"));
				oauth.setClientSecret(OathConfig.getValue(site.getTemplate() + "." + "openkey_sina"));
				oauth.setRedirectUri(OathConfig.getValue(site.getTemplate() + "." + "redirect_sina"));
			}
			cache.add("sina_" + site.getId(), oauth);
		}

		return oauth;
	}

	public OauthQQ getQQ(TbSite site) {
		Cache cache = CacheManager.get(cacheName);
		OauthQQ oauth = cache.get("qq_" + site.getId());
		if (oauth == null) {
			oauth = new OauthQQ();
			int defaultSiteId = new SiteService().getDefaultId();
			if (site.getId() != defaultSiteId) {
				oauth.setClientId(OathConfig.getValue(site.getTemplate() + "." + "openid_qq"));
				oauth.setClientSecret(OathConfig.getValue(site.getTemplate() + "." + "openkey_qq"));
				oauth.setRedirectUri(OathConfig.getValue(site.getTemplate() + "." + "redirect_qq"));
			}
			cache.add("qq_" + site.getId(), oauth);
		}
		return oauth;
	}
}
