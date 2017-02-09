package com.jflyfox.modules.front.controller;

import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Page;
import com.jflyfox.component.base.BaseProjectController;
import com.jflyfox.component.util.ArticleCountCache;
import com.jflyfox.component.util.JFlyFoxUtils;
import com.jflyfox.jfinal.base.Paginator;
import com.jflyfox.jfinal.component.annotation.ControllerBind;
import com.jflyfox.modules.admin.article.TbArticle;
import com.jflyfox.modules.admin.comment.TbComment;
import com.jflyfox.modules.admin.folder.FolderService;
import com.jflyfox.modules.admin.tags.TbTags;
import com.jflyfox.modules.front.interceptor.FrontInterceptor;
import com.jflyfox.modules.front.service.FrontCacheService;
import com.jflyfox.util.StrUtils;

/**
 * 文章管理
 * 
 * @author flyfox 2014-2-11
 */
@ControllerBind(controllerKey = "/front/article")
public class ArticleController extends BaseProjectController {

	public static final String path = "/article/";

	/**
	 * 查看文章
	 * 
	 * @see 不用缓存便于实时更新，访问量大再优化
	 * 
	 *      2015年2月26日 下午1:46:14 flyfox 369191470@qq.com
	 */
	@Before(FrontInterceptor.class)
	public void index() {
		// 数据列表
		int articleId = getParaToInt();

		// 文章
		// TbArticle article = new FrontCacheService().getArticle(articleId);
		TbArticle article = TbArticle.dao.findById(articleId);

		// 新增链接跳转
		if (article != null) {
			String jumpUrl = article.getStr("jump_url");
			if (StrUtils.isNotEmpty(jumpUrl)) { // jump url
				redirect(jumpUrl);
				return;
			}
		}

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
			setAttr(JFlyFoxUtils.TITLE_ATTR, article.getTitle() + " - " + getAttr(JFlyFoxUtils.TITLE_ATTR));

			// 标签
			// List<TbTags> taglist = new FrontCacheService().getTagsByArticle(articleId);
			List<TbTags> taglist = TbTags.dao.find("select * from tb_tags " //
					+ "where article_id = ? order by  create_time desc ", articleId);
			setAttr("taglist", taglist);

			// 评论
			String sql = " from tb_comment t" //
					+ " left join tb_article art on art.id = t.article_id " //
					+ " where article_id = ? order by t.create_time desc ";
			Page<TbComment> comments = TbComment.dao.paginate(getPaginator() //
					, "select t.*,art.title,art.create_id as article_create_id ", sql, articleId);
			setAttr("page", comments);
		}

		// 活动目录
		setAttr("folders_selected", article.getFolderId());

		// 同类文章
		setAttr("folder", new FolderService().getFolder(article.getFolderId()));

		Page<TbArticle> articles;
		if (article.getFolderId() == JFlyFoxUtils.MENU_BLOG) { // 博客会经常被删除，缓存不靠谱
			articles = new FrontCacheService().getArticleByNoCache(new Paginator(1, 10), article.getFolderId());
		} else {
			articles = new FrontCacheService().getArticle(new Paginator(1, 10), article.getFolderId());
		}
		setAttr("articles", articles);

		renderAuto(path + "show_article.html");

	}

	/**
	 * 查看文章某用户发布文章
	 * 
	 * 2015年2月26日 下午1:46:14 flyfox 369191470@qq.com
	 */
	@Before(FrontInterceptor.class)
	public void recommend() {
		// 活动目录,哪个都不是
		setAttr("folders_selected", 0);

		// 推荐文章列表，缓存
		Page<TbArticle> pages = new FrontCacheService().getRecommendArticle(getPaginator() //
				, getSessionSite().getSiteId());
		setAttr("page", pages);

		// 显示50个标签
		Page<TbTags> taglist = new FrontCacheService().getTags(new Paginator(1, 50), getSessionSite().getSiteId());
		setAttr("taglist", taglist.getList());

		// seo：title优化
		setAttr(JFlyFoxUtils.TITLE_ATTR, "推荐文章 - " + getAttr(JFlyFoxUtils.TITLE_ATTR));

		renderAuto(path + "show_recommend.html");
	}

}
