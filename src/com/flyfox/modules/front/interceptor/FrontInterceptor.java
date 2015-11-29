package com.flyfox.modules.front.interceptor;

import java.util.List;

import com.flyfox.jfinal.base.Paginator;
import com.flyfox.modules.article.TbArticle;
import com.flyfox.modules.folder.FolderService;
import com.flyfox.modules.folder.TbFolder;
import com.flyfox.modules.friendlylink.FriendlylinkCache;
import com.flyfox.modules.front.service.FrontCacheService;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;

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
