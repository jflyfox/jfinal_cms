package com.flyfox.modules.front;

import java.util.List;

import com.flyfox.component.util.ArticleCountCache;
import com.flyfox.component.util.JFlyFoxCache;
import com.flyfox.component.util.JFlyFoxUtils;
import com.flyfox.jfinal.base.BaseController;
import com.flyfox.jfinal.base.Paginator;
import com.flyfox.jfinal.component.annotation.ControllerBind;
import com.flyfox.modules.article.TbArticle;
import com.flyfox.modules.comment.TbComment;
import com.flyfox.modules.folder.FolderService;
import com.flyfox.modules.front.interceptor.FrontInterceptor;
import com.flyfox.modules.front.service.FrontCacheService;
import com.flyfox.modules.tags.TbTags;
import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Page;

/**
 * 文章管理
 * 
 * @author flyfox 2014-2-11
 */
@ControllerBind(controllerKey = "/front/article")
public class ArticleController extends BaseController {

	public static final String path = "/pages/front/article/";

	/**
	 * 查看文章
	 * 
	 * 2015年2月26日 下午1:46:14 flyfox 330627517@qq.com
	 */
	@Before(FrontInterceptor.class)
	public void index() {

		// 数据列表
		int articleId = getParaToInt();
		TbArticle article = TbArticle.dao.findById(articleId);
		if (article != null) {
			// 更新浏览量
			String key = getSessionAttr(JFlyFoxUtils.USER_KEY);
			if (key != null) {
				ArticleCountCache.addCountView(article, key);
				// 缓存访问量和评论数
				new FrontCacheService().addArticleCount(article);
			}

			setAttr("item", article);

			// seo：title优化
			setAttr(JFlyFoxUtils.TITLE_ATTR, article.getTitle() + " - " + JFlyFoxCache.getHeadTitle());

			// 标签
			List<TbTags> taglist = new FrontCacheService().getTagsByArticle(article.getId());
			setAttr("taglist", taglist);

			// 评论
			Page<TbComment> comments = TbComment.dao.paginate(getPaginator(), "select * ", //
					" from tb_comment where article_id = ? order by create_time desc ", articleId);
			setAttr("page", comments);
		}

		// 活动目录
		setAttr("folders_selected", article.getFolderId());

		// 同类文章
		setAttr("folder", new FolderService().getFolder(article.getFolderId()));

		Page<TbArticle> articles = new FrontCacheService().getArticle(new Paginator(1, 8), article.getFolderId());
		setAttr("articles", articles);

		renderAuto(path + "show_article.html");

	}

}
