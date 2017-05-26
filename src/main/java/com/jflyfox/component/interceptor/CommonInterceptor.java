package com.jflyfox.component.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.jflyfox.component.util.JFlyFoxUtils;
import com.jflyfox.jfinal.base.SessionUser;
import com.jflyfox.jfinal.component.util.Attr;
import com.jflyfox.modules.admin.site.SessionSite;
import com.jflyfox.modules.admin.site.SiteConstant;
import com.jflyfox.modules.admin.site.SiteService;
import com.jflyfox.modules.admin.site.TbSite;
import com.jflyfox.system.config.ConfigCache;

/**
 * 公共属性拦截器
 * 
 * @author flyfox 2014-2-11
 */
public class CommonInterceptor implements Interceptor {

	private SiteService siteSvc = new SiteService();

	@SuppressWarnings("rawtypes")
	public void intercept(Invocation ai) {

		Controller controller = ai.getController();

		// 请求路径
		String tmpPath = ai.getActionKey();
		tmpPath = JFlyFoxUtils.handlerPath(tmpPath);
		boolean isBack = JFlyFoxUtils.isBack(tmpPath);

		if (isBack) { // 后台
			SessionUser user = controller.getSessionAttr(Attr.SESSION_NAME);
			if (user == null) {
				String title = ConfigCache.getValue("SYSTEM.TITLE");
				// 设置公共属性
				controller.setAttr(JFlyFoxUtils.WEBSITE_TITLE, title);
				controller.setAttr(JFlyFoxUtils.TITLE_ATTR, title);
				controller.setAttr(JFlyFoxUtils.KEYWORDS_ATTR, title);
				controller.setAttr(JFlyFoxUtils.DESCRIPTION_ATTR, title);
			} else {
				TbSite site = siteSvc.getSite(user.getBackSiteId());
				controller.setAttr(JFlyFoxUtils.WEBSITE_TITLE, site.getSiteTitle());
				controller.setAttr(JFlyFoxUtils.TITLE_ATTR, site.getSiteTitle());
				controller.setAttr(JFlyFoxUtils.KEYWORDS_ATTR, site.getSiteTitle());
				controller.setAttr(JFlyFoxUtils.DESCRIPTION_ATTR, site.getSiteTitle());
			}
		} else {
			SessionSite sessionSite = controller.getSessionAttr(SiteConstant.getSessionSite());
			if (sessionSite == null) {
				String title = ConfigCache.getValue("SYSTEM.TITLE");
				// 设置公共属性
				controller.setAttr(JFlyFoxUtils.WEBSITE_TITLE, title);
				controller.setAttr(JFlyFoxUtils.TITLE_ATTR, title);
				controller.setAttr(JFlyFoxUtils.KEYWORDS_ATTR, title);
				controller.setAttr(JFlyFoxUtils.DESCRIPTION_ATTR, title);
			} else if (sessionSite.getModel() != null) {
				TbSite site = sessionSite.getModel();
				controller.setAttr(JFlyFoxUtils.WEBSITE_TITLE, site.getSiteTitle());
				controller.setAttr(JFlyFoxUtils.TITLE_ATTR, site.getSiteTitle());
				controller.setAttr(JFlyFoxUtils.KEYWORDS_ATTR, site.getSiteTitle());
				controller.setAttr(JFlyFoxUtils.DESCRIPTION_ATTR, site.getSiteTitle());
			}
		}

		ai.invoke();
	}
}
