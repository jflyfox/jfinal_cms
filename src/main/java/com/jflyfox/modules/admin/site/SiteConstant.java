package com.jflyfox.modules.admin.site;

import com.jflyfox.util.Config;

public class SiteConstant {

	public final static int DEFAULT_SITE_ID = Config.getToInt("SITE.DEFAULT.ID");
	public final static String TEMPLATE_PATH = Config.getStr("SITE.TEMPLATE.PATH");
	public final static String SESSION_SITES = Config.getStr("SITE.SESSION.SITES");
	public final static String SESSION_SITE = Config.getStr("SITE.SESSION.SITE");

	/**
	 * 多站点判断
	 * 
	 * 2016年4月25日 下午5:41:35 flyfox 330627517@qq.com
	 * 
	 * @return
	 */
	public static boolean isMultiSite() {
		return Config.getToBoolean("SITE.MULTI.FLAG");
	}
}
