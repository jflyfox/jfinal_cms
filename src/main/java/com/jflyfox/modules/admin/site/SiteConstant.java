package com.jflyfox.modules.admin.site;

import com.jflyfox.system.config.ConfigCache;

public class SiteConstant {

	public final static int SITE_DEFAULT_YES = 1;
	public final static int SITE_DEFAULT_NO = 2;

	/**
	 * 多站点判断
	 * 
	 * 2016年4月25日 下午5:41:35 flyfox 369191470@qq.com
	 * 
	 * @return
	 */
	public static boolean isMultiSite() {
		return ConfigCache.getValueToBoolean("SITE.MULTI.FLAG");
	}
	
	/**
	 * 获取模板路径
	 * 
	 * 2016年12月31日 下午9:07:09
	 * flyfox 369191470@qq.com
	 * @return
	 */
	public static String getTemplatePath() {
		return ConfigCache.getValue("SITE.TEMPLATE.PATH");
	}
	
	/**
	 * 获取session中站点列表
	 * 
	 * 2016年12月31日 下午9:07:15
	 * flyfox 369191470@qq.com
	 * @return
	 */
	public static String getSessionSites() {
		return ConfigCache.getValue("SITE.SESSION.SITES");
	}
	
	/**
	 * 获取用户session的站点
	 * 
	 * 2016年12月31日 下午9:07:30
	 * flyfox 369191470@qq.com
	 * @return
	 */
	public static String getSessionSite() {
		return ConfigCache.getValue("SITE.SESSION.SITE");
	}
}
