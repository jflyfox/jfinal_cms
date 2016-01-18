package com.jflyfox.modules.front.template;

import java.util.List;

import com.jfinal.plugin.activerecord.Page;
import com.jflyfox.jfinal.base.BaseService;
import com.jflyfox.jfinal.base.Paginator;
import com.jflyfox.modules.admin.article.TbArticle;
import com.jflyfox.modules.admin.tags.TbTags;
import com.jflyfox.modules.front.articlelike.ArticleLikeCache;
import com.jflyfox.modules.front.service.FrontCacheService;

/**
 * 模板方法
 * 
 * 2016年1月18日 下午6:05:54
 * flyfox 330627517@qq.com
 */
public class TemplateService extends BaseService {

	
	private final static FrontCacheService service = new FrontCacheService();
	
	/**
	 * 获取浏览数
	 * 
	 * 2015年6月2日 下午6:30:56 flyfox 330627517@qq.com
	 * 
	 * @param articleId
	 * @return
	 */
	public static int countView(int articleId) {
		TbArticle article = service.getArticleCount(articleId);
		return article == null ? 0 : article.getCountView();
	}

	/**
	 * 获取评论数
	 * 
	 * 2015年6月2日 下午6:30:56 flyfox 330627517@qq.com
	 * 
	 * @param articleId
	 * @return
	 */
	public static int countComment(int articleId) {
		TbArticle article = service.getArticleCount(articleId);
		return article == null ? 0 : article.getCountComment();
	}

	/**
	 * 获取浏览数
	 * 
	 * 2015年6月2日 下午6:30:56 flyfox 330627517@qq.com
	 * 
	 * @param articleId
	 * @return
	 */
	public static boolean isLike(int userId, int articleId) {
		return new ArticleLikeCache().isLike(userId, articleId);
	}
	
	
	public List<TbTags> getTagsByArticle(int articleId) {
		return service.getTagsByArticle(articleId);
	}
	public Page<TbTags> getTagsByFolder(Paginator paginator, int folderId) {
		return service.getTagsByFolder(paginator, folderId);
	}

	public Page<TbTags> getTags(Paginator paginator) {
		return service.getTags(paginator);
	}

	public Page<TbArticle> getRecommendArticle(Paginator paginator) {
		return service.getRecommendArticle(paginator);
	}

	public Page<TbArticle> getNewArticle(Paginator paginator) {
		return service.getNewArticle(paginator);
	}

	public Page<TbArticle> getArticle(Paginator paginator, int folderId) {
		return service.getArticle(paginator, folderId);
	}

	public Page<TbArticle> getArticleByNoCache(Paginator paginator, int folderId) {
		return service.getArticleByNoCache(paginator, folderId);
	}

	public TbArticle getArticle(int articleId) {
		return service.getArticle(articleId);
	}
	
}