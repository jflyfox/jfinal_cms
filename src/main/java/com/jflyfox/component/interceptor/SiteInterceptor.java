package com.jflyfox.component.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.jflyfox.component.util.JFlyFoxUtils;
import com.jflyfox.modules.admin.site.SessionSite;
import com.jflyfox.modules.admin.site.SiteConstant;
import com.jflyfox.modules.admin.site.SiteService;
import com.jflyfox.modules.admin.site.TbSite;

import java.util.List;

/**
 * 站点认证拦截器
 * 
 * @author flyfox 2014-2-11
 */
public class SiteInterceptor implements Interceptor {

	// private static final Logger log =
	// Logger.getLogger(SiteInterceptor.class);
	private SiteService siteSvc = new SiteService();

	public void intercept(Invocation ai) {
		Controller controller = ai.getController();

		SessionSite sessionSite = controller.getSessionAttr(SiteConstant.getSessionSite());
		TbSite defaultSite = siteSvc.getDefaultSite();
		if (defaultSite == null) {
			throw new RuntimeException("nout set default site");
		}

		// 如果修改了默认站点，重新设置site session
		if (sessionSite != null && defaultSite.getId() != sessionSite.getSiteDefalutId()) {
			sessionSite = null;
		}

		// 单站点设置
		if (!SiteConstant.isMultiSite()) {
			if (sessionSite == null) {
				sessionSite = new SessionSite();
				sessionSite.setSiteDefalutId(defaultSite.getId());
				sessionSite.setSiteId(defaultSite.getId());
				sessionSite.setModel(defaultSite);
				controller.setSessionAttr(SiteConstant.getSessionSite(), sessionSite);
			}
			ai.invoke();
			return;
		}

		// 请求路径
		String tmpPath = ai.getActionKey();
		tmpPath = JFlyFoxUtils.handlerPath(tmpPath);
		boolean isBack = JFlyFoxUtils.isBack(tmpPath);

		// 单站点不返回站点列表
		List<TbSite> sites = siteSvc.getSiteList();
		if (isBack) {
			controller.setAttr(SiteConstant.getSessionSites(), sites);
		} else {
			// 前台站点地址解析
			if (sessionSite == null) {
				sessionSite = new SessionSite();
			}

			// 通过site匹配站点
			String siteServer = controller.getRequest().getServerName();
			int siteId = 0;
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
			if (siteId == 0) {
				sessionSite.setSiteId(defaultSite.getId());
				sessionSite.setModel(defaultSite);
			}

			sessionSite.setSiteDefalutId(defaultSite.getId());
			controller.setSessionAttr(SiteConstant.getSessionSite(), sessionSite);
		}

		ai.invoke();
	}

}
