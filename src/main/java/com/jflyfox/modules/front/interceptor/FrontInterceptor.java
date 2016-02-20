package com.jflyfox.modules.front.interceptor;

import java.util.List;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.jflyfox.jfinal.base.Paginator;
import com.jflyfox.modules.admin.article.TbArticle;
import com.jflyfox.modules.admin.folder.FolderService;
import com.jflyfox.modules.admin.folder.TbFolder;
import com.jflyfox.modules.admin.friendlylink.FriendlylinkCache;
import com.jflyfox.modules.front.service.FrontCacheService;

/**
 * 用户认证拦截器
 * 
 * @author flyfox 2014-2-11
 */
public class FrontInterceptor implements Interceptor {

	public void intercept(Invocation ai) {

		Controller controller = ai.getController();

		// 目录，缓存
		List<TbFolder> folders = new FolderService().getFolderList();
		controller.setAttr("folders", folders);

		List<TbFolder> foldersOther = new FolderService().getFolderListOther();
		controller.setAttr("foldersOther", foldersOther);
		
		// 推荐文章列表，缓存
		Page<TbArticle> recommendArticles = new FrontCacheService().getRecommendArticle(new Paginator(1, 8));
		controller.setAttr("recommendArticles", recommendArticles);

		// 友情链接，缓存
		controller.setAttr("friendlylinkList", FriendlylinkCache.getList(21));
		
		// 底部关于，缓存
		controller.setAttr("aboutList", FriendlylinkCache.getList(22));
		
		ai.invoke();
	}
}
