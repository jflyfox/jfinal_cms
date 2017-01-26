package com.jflyfox.component.interceptor;

import java.util.List;

import org.apache.log4j.Logger;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.jflyfox.component.util.JFlyFoxUtils;
import com.jflyfox.jfinal.base.BaseController;
import com.jflyfox.jfinal.component.util.Attr;
import com.jflyfox.modules.admin.site.SessionSite;
import com.jflyfox.modules.admin.site.SiteConstant;
import com.jflyfox.modules.admin.site.SiteService;
import com.jflyfox.modules.admin.site.TbSite;
import com.jflyfox.system.user.SysUser;

/**
 * 站点认证拦截器
 * 
 * @author flyfox 2014-2-11
 */
public class SiteInterceptor implements Interceptor {

	private static final Logger log = Logger.getLogger(SiteInterceptor.class);

	public void intercept(Invocation ai) {
		Controller controller = ai.getController();
		
		SessionSite sessionSite = controller.getSessionAttr(SiteConstant.getSessionSite());
		TbSite defaultSite = new SiteService().getDefaultSite();
		// 如果修改了默认站点，重新设置site session
		if (sessionSite != null && defaultSite.getId() != sessionSite.getSiteDefalutId()) {
			sessionSite = null;
		}
		
		// 单站点设置
		if (!SiteConstant.isMultiSite()) {
			if (sessionSite == null) {
				sessionSite = new SessionSite();
				sessionSite.setSiteDefalutId(defaultSite.getId());
				sessionSite.setBackSiteId(defaultSite.getId());
				sessionSite.setSiteId(defaultSite.getId());
				sessionSite.setBackModel(defaultSite);
				sessionSite.setModel(defaultSite);
				controller.setSessionAttr(SiteConstant.getSessionSite(), sessionSite);
			}
			ai.invoke();
			return;
		}
		
		// 返回list
		List<TbSite> sites = new SiteService().getSiteList();
		controller.setAttr(SiteConstant.getSessionSites(), sites);

		// 请求路径
		String tmpPath = ai.getActionKey();
		if (tmpPath.startsWith("/")) {
			tmpPath = tmpPath.substring(1, tmpPath.length());
		}
		if (tmpPath.endsWith("/")) {
			tmpPath = tmpPath.substring(0, tmpPath.length() - 1);
		}
		boolean isBack = JFlyFoxUtils.isBack(tmpPath);

		if (sessionSite == null) {
			sessionSite = new SessionSite();
		}

		if (isBack) { // 后台
			// 每次访问获取session，没有可以从cookie取~
			SysUser user = null;
			if (controller instanceof BaseController) {
				user = (SysUser) ((BaseController) controller).getSessionUser();
			} else {
				user = controller.getSessionAttr(Attr.SESSION_NAME);
			}
			
			// 权限应该是用户拦截器处理
			if (user == null) {
				log.error("session user is null!");
				ai.invoke();
				return;
			}
			
			// 获取用户设置的SITE对象，设置默认
			if (sessionSite.getBackSiteId() <= 0) {
				int backSiteId = user.getInt("back_site_id");
				backSiteId = backSiteId > 0 ? backSiteId : defaultSite.getId();

				sessionSite.setBackSiteId(backSiteId);

				TbSite tmpSite = getSite(sites, backSiteId);
				if (tmpSite != null) {
					sessionSite.setBackSiteId(tmpSite.getId());
					sessionSite.setBackModel(tmpSite);
				}

				sessionSite.setSiteDefalutId(defaultSite.getId());
				controller.setSessionAttr(SiteConstant.getSessionSite(), sessionSite);
			}
		} else {
			// 通过site匹配站点
			String siteServer = controller.getRequest().getServerName();
			int siteId = 0;
			// if (StrUtils.isEmpty(sessionSite.getLastSite()) ||
			// !site.equals(sessionSite.getLastSite())) {
			sessionSite.setLastSite(siteServer);
			sessionSite.setSiteId(siteId);
			for (TbSite tmpSite : sites) {
				if (siteServer.equals(tmpSite.getDomainPc()) //
						|| siteServer.equals(tmpSite.getDomainMobile())) {
					siteId = tmpSite.getId();
					sessionSite.setSiteId(siteId);
					sessionSite.setModel(tmpSite);
					break;
				}
			}

			// 没有就用默认的,设置站点对象
			if (siteId == 0 && defaultSite != null) {
				sessionSite.setSiteId(defaultSite.getId());
				sessionSite.setModel(defaultSite);
			}

			sessionSite.setSiteDefalutId(defaultSite.getId());
			controller.setSessionAttr(SiteConstant.getSessionSite(), sessionSite);
			// }
		}

		ai.invoke();
	}

	protected TbSite getSite(List<TbSite> sites, int siteId) {
		// 设置站点对象
		for (TbSite tmpSite : sites) {
			if (siteId == tmpSite.getId()) {
				return tmpSite;
			}
		}
		return null;
	}

}
