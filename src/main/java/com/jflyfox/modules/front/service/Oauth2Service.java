package com.jflyfox.modules.front.service;

import com.jflyfox.jfinal.component.oauth.OauthBaidu;
import com.jflyfox.jfinal.component.oauth.OauthQQ;
import com.jflyfox.jfinal.component.oauth.OauthSina;
import com.jflyfox.jfinal.component.oauth.util.OathConfig;
import com.jflyfox.modules.admin.site.SiteConstant;
import com.jflyfox.modules.admin.site.TbSite;
import com.jflyfox.util.cache.Cache;
import com.jflyfox.util.cache.CacheManager;

public class Oauth2Service {

	private final static String cacheName = "Oauth2Controller";
	/**
	 * 目录缓存
	 */
	private static Cache cache = CacheManager.get(cacheName);

	public void clearCahce() {
		cache.clear();
	}

	public OauthBaidu getBaidu(TbSite site) {
		OauthBaidu oauth = cache.get("baidu_" + site.getId());
		if (oauth == null) {
			oauth = new OauthBaidu();
			if (site.getId() != SiteConstant.DEFAULT_SITE_ID) {
				oauth.setClientId(OathConfig.getValue(site.getTemplate() + "." + "openid_baidu"));
				oauth.setClientSecret(OathConfig.getValue(site.getTemplate() + "." + "openkey_baidu"));
				oauth.setRedirectUri(OathConfig.getValue(site.getTemplate() + "." + "redirect_baidu"));
			}
			cache.add("baidu_" + site.getId(), oauth);
		}

		return oauth;
	}

	public OauthSina getSina(TbSite site) {
		OauthSina oauth = cache.get("sina_" + site.getId());
		if (oauth == null) {
			oauth = new OauthSina();
			if (site.getId() != SiteConstant.DEFAULT_SITE_ID) {
				oauth.setClientId(OathConfig.getValue(site.getTemplate() + "." + "openid_sina"));
				oauth.setClientSecret(OathConfig.getValue(site.getTemplate() + "." + "openkey_sina"));
				oauth.setRedirectUri(OathConfig.getValue(site.getTemplate() + "." + "redirect_sina"));
			}
			cache.add("sina_" + site.getId(), oauth);
		}

		return oauth;
	}

	public OauthQQ getQQ(TbSite site) {
		OauthQQ oauth = cache.get("qq_" + site.getId());
		if (oauth == null) {
			oauth = new OauthQQ();
			if (site.getId() != SiteConstant.DEFAULT_SITE_ID) {
				oauth.setClientId(OathConfig.getValue(site.getTemplate() + "." + "openid_qq"));
				oauth.setClientSecret(OathConfig.getValue(site.getTemplate() + "." + "openkey_qq"));
				oauth.setRedirectUri(OathConfig.getValue(site.getTemplate() + "." + "redirect_qq"));
			}
			cache.add("qq_" + site.getId(), oauth);
		}
		return oauth;
	}
}
