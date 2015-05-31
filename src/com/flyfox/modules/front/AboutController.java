package com.flyfox.modules.front;

import com.flyfox.component.util.JFlyFoxCache;
import com.flyfox.component.util.JFlyFoxUtils;
import com.flyfox.jfinal.base.BaseController;
import com.flyfox.jfinal.base.Paginator;
import com.flyfox.jfinal.component.annotation.ControllerBind;
import com.flyfox.modules.article.TbArticle;
import com.flyfox.modules.front.interceptor.FrontInterceptor;
import com.flyfox.modules.front.service.FrontCacheService;
import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Page;

/**
 * 关于我们
 * 
 * 2015年5月26日 上午10:42:54 flyfox 330627517@qq.com
 */
@ControllerBind(controllerKey = "/front/about")
public class AboutController extends BaseController {

	public static final String path = "/pages/front/about/";

	/**
	 * 关于我们
	 */
	@Before(FrontInterceptor.class)
	public void index() {
		Integer articleId = getParaToInt();

		setAttr("folders_selected", 90);
		
		Page<TbArticle> pages = new FrontCacheService().getArticle(new Paginator(1, 100), 90);
		setAttr("pages", pages);

		TbArticle article = null;
		if (articleId == null || articleId <= 0) {
			article = pages.getList().get(0);
		} else {
			article = new FrontCacheService().getArticle(articleId);
		}
		setAttr("article", article);

		// seo：title优化
		setAttr(JFlyFoxUtils.TITLE_ATTR, article.getTitle() + " - " + "关于我们 - " + JFlyFoxCache.getHeadTitle());

		renderAuto(path + "show_about.html");
	}
}
