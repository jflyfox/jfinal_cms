package com.flyfox.modules.front.service;

import com.flyfox.component.util.JFlyFoxCache;
import com.flyfox.component.util.JFlyFoxUtils;
import com.flyfox.jfinal.base.BaseController;
import com.flyfox.jfinal.base.BaseService;
import com.flyfox.jfinal.base.Paginator;
import com.flyfox.modules.article.TbArticle;
import com.flyfox.modules.folder.FolderService;
import com.flyfox.modules.folder.TbFolder;
import com.flyfox.modules.front.Home;
import com.flyfox.modules.tags.TbTags;
import com.jfinal.plugin.activerecord.Page;

public class FrontService extends BaseService {

	public void menu(BaseController controller) {
		Integer folderId = controller.getParaToInt();
		if (folderId == null || folderId <= 0) {
			folderId = TbFolder.ROOT;
		}
		// 活动目录
		controller.setAttr("folders_selected", folderId);

		if (folderId == TbFolder.ROOT) {
			home(controller);
		} else if (folderId == 90) {
			controller.redirect("/front/about");
		} else {
			// 其他
			otherMenu(controller, folderId);
		}

	}

	/**
	 * 其他菜单
	 * 
	 * 2015年5月25日 下午11:55:43 flyfox 330627517@qq.com
	 * 
	 * @param controller
	 * @param folderId
	 */
	protected void otherMenu(BaseController controller, int folderId) {
		// 当前目录
		TbFolder folder = new FolderService().getFolder(folderId);

		// 没有对应目录~返回首页吧
		if (folder == null) {
			home(controller);
			return;
		}

		controller.setAttr("folder", folder);

		// 列表数据
		Page<TbArticle> news = new FrontCacheService().getArticle(controller.getPaginator(), folderId);
		controller.setAttr("page", news);

		// 显示50个标签
		Page<TbTags> taglist = new FrontCacheService().getTagsByFolder(new Paginator(1, 50), folderId);
		controller.setAttr("taglist", taglist.getList());

		// seo：title优化
		controller.setAttr(JFlyFoxUtils.TITLE_ATTR, folder.getStr("name") + " - " + JFlyFoxCache.getHeadTitle());

		controller.renderAuto(Home.PATH + "common_menu.html");
	}

	/**
	 * 首页
	 * 
	 * 2015年5月25日 下午11:55:53 flyfox 330627517@qq.com
	 * 
	 * @param controller
	 */
	protected void home(BaseController controller) {
		// 首页图片 13
		Page<TbArticle> topPics = new FrontCacheService().getArticle(new Paginator(1, 4), 13);
		controller.setAttr("topPics", topPics);

		// 最新动态
		Page<TbArticle> newArticle = new FrontCacheService().getNewArticle(new Paginator(1, 10));
		controller.setAttr("newArticles", newArticle);

		// 新闻 2
		Page<TbArticle> news = new FrontCacheService().getArticle(new Paginator(1, 10), 2);
		controller.setAttr("news", news);

		// 美食 3
		Page<TbArticle> foods = new FrontCacheService().getArticle(new Paginator(1, 10), 3);
		controller.setAttr("foods", foods);

		// 旅游 4
		Page<TbArticle> travels = new FrontCacheService().getArticle(new Paginator(1, 9), 4);
		controller.setAttr("travels", travels);

		// 教育 5
		Page<TbArticle> educations = new FrontCacheService().getArticle(new Paginator(1, 10), 5);
		controller.setAttr("educations", educations);

		// seo：title优化
		controller
				.setAttr(JFlyFoxUtils.TITLE_ATTR, JFlyFoxCache.getHeadTitle() + " - " + "北京市门头沟区最全面的生活、新闻、美食、旅游、教育资讯");

		controller.renderAuto(Home.PATH + "home.html");
	}
}
