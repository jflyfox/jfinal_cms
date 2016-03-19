package com.jflyfox.modules.front.template;

import java.util.List;

import com.jfinal.plugin.activerecord.Page;
import com.jflyfox.jfinal.base.BaseService;
import com.jflyfox.jfinal.base.Paginator;
import com.jflyfox.modules.admin.article.TbArticle;
import com.jflyfox.modules.admin.foldernotice.TbFolderNotice;
import com.jflyfox.modules.admin.folderrollpicture.TbFolderRollPicture;
import com.jflyfox.modules.admin.tags.TbTags;
import com.jflyfox.modules.front.articlelike.ArticleLikeCache;
import com.jflyfox.modules.front.service.FrontCacheService;

/**
 * 模板方法接口
 * 
 * 2016年1月18日 下午6:05:54 flyfox 330627517@qq.com
 */
public class TemplateService extends BaseService {

	private final static FrontCacheService service = new FrontCacheService();

	private final static ArticleLikeCache articleLikeservice = new ArticleLikeCache();

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
		return articleLikeservice.isLike(userId, articleId);
	}

	public List<TbTags> tagsListByArticle(int articleId) {
		return service.getTagsByArticle(articleId);
	}

	public Page<TbTags> tagsPageByFolder(int pageNo, int pageSize, int folderId) {
		return service.getTagsByFolder(new Paginator(pageNo, pageSize), folderId);
	}

	public Page<TbTags> tagsPage(int pageNo, int pageSize) {
		return service.getTags(new Paginator(pageNo, pageSize));
	}

	public Page<TbArticle> articlePageRecommend(int pageNo, int pageSize) {
		return service.getRecommendArticle(new Paginator(pageNo, pageSize));
	}

	public Page<TbArticle> articlePageTop(int pageNo, int pageSize) {
		return service.getNewArticle(new Paginator(pageNo, pageSize));
	}

	public Page<TbArticle> articlePage(int pageNo, int pageSize) {
		return service.getArticle(new Paginator(pageNo, pageSize));
	}
	
	public Page<TbArticle> articlePage(int pageNo, int pageSize, int folderId) {
		return service.getArticle(new Paginator(pageNo, pageSize), folderId);
	}

	public Page<TbArticle> articlePageNoCache(int pageNo, int pageSize, int folderId) {
		return service.getArticleByNoCache(new Paginator(pageNo, pageSize), folderId);
	}

	public TbArticle article(int articleId) {
		return service.getArticle(articleId);
	}

	public List<TbFolderRollPicture> rollPicture(int folderId) {
		return service.getRollPicture(folderId);
	}

	public List<TbFolderNotice> notice(int folderId) {
		return service.getNotice(folderId);
	}

}