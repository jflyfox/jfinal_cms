package com.jflyfox.component.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.jflyfox.component.util.JFlyFoxUtils;
import com.jflyfox.modules.admin.site.SessionSite;
import com.jflyfox.modules.admin.site.SiteConstant;
import com.jflyfox.modules.admin.site.TbSite;

/**
 * 公共属性拦截器
 * 
 * @author flyfox 2014-2-11
 */
public class CommonInterceptor implements Interceptor {

	private static final String TITLE = "FLY的狐狸";

	public void intercept(Invocation ai) {

		Controller controller = ai.getController();

		SessionSite sessionSite = controller.getSessionAttr(SiteConstant.getSessionSite());
		if (sessionSite == null) {
			// 设置公共属性
			controller.setAttr(JFlyFoxUtils.WEBSITE_TITLE, TITLE);
			controller.setAttr(JFlyFoxUtils.TITLE_ATTR, TITLE);
			controller.setAttr(JFlyFoxUtils.KEYWORDS_ATTR, TITLE);
			controller.setAttr(JFlyFoxUtils.DESCRIPTION_ATTR, TITLE);
		} else if (sessionSite.getModel() != null) {
			TbSite site = sessionSite.getModel();
			controller.setAttr(JFlyFoxUtils.WEBSITE_TITLE, site.getSiteTitle());
			controller.setAttr(JFlyFoxUtils.TITLE_ATTR, site.getSiteTitle());
			controller.setAttr(JFlyFoxUtils.KEYWORDS_ATTR, site.getSiteTitle());
			controller.setAttr(JFlyFoxUtils.DESCRIPTION_ATTR, site.getSiteTitle());
		} else if (sessionSite.getBackModel() != null) {
			TbSite site = sessionSite.getBackModel();
			controller.setAttr(JFlyFoxUtils.WEBSITE_TITLE, site.getSiteTitle());
			controller.setAttr(JFlyFoxUtils.TITLE_ATTR, site.getSiteTitle());
			controller.setAttr(JFlyFoxUtils.KEYWORDS_ATTR, site.getSiteTitle());
			controller.setAttr(JFlyFoxUtils.DESCRIPTION_ATTR, site.getSiteTitle());
		}

		ai.invoke();
	}
}
