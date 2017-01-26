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
 * 2016年1月18日 下午6:05:54 flyfox 369191470@qq.com
 */
public class TemplateService extends BaseService {

	private final static FrontCacheService service = new FrontCacheService();

	private final static ArticleLikeCache articleLikeservice = new ArticleLikeCache();

	/**
	 * 获取浏览数
	 * 
	 * 2015年6月2日 下午6:30:56 flyfox 369191470@qq.com
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
	 * 2015年6月2日 下午6:30:56 flyfox 369191470@qq.com
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
	 * 2015年6月2日 下午6:30:56 flyfox 369191470@qq.com
	 * 
	 * @param articleId
	 * @return
	 */
	public static boolean isLike(int userId, int articleId) {
		return articleLikeservice.isLike(userId, articleId);
	}

	/**
	 * 获取标签信息
	 * 
	 * 2015年5月25日 下午11:49:58 flyfox 369191470@qq.com
	 * 
	 * @param paginator
	 * @param folderId
	 *            目录
	 * @return
	 */
	public List<TbTags> tagsListByArticle(int articleId) {
		return service.getTagsByArticle(articleId);
	}

	/**
	 * 获取标签信息
	 * 
	 * 2015年5月25日 下午11:49:58 flyfox 369191470@qq.com
	 * 
	 * @param paginator
	 * @param folderId
	 *            目录
	 * @return
	 */
	public Page<TbTags> tagsPageByFolder(int pageNo, int pageSize, int folderId) {
		return service.getTagsByFolder(new Paginator(pageNo, pageSize), folderId);
	}

	/**
	 * 获取标签信息
	 * 
	 * 2015年5月25日 下午11:49:03 flyfox 369191470@qq.com
	 * 
	 * @param paginator
	 * @return
	 */
	public Page<TbTags> tagsPage(int pageNo, int pageSize, int siteId) {
		return service.getTags(new Paginator(pageNo, pageSize), siteId);
	}

	/**
	 * 查询文章，展示的和类型为11,12的推荐文件,前10个
	 * 
	 * 2015年4月29日 下午4:48:24 flyfox 369191470@qq.com
	 * 
	 * @param paginator
	 * @param folder_id
	 * @return
	 */
	public Page<TbArticle> articlePageRecommend(int pageNo, int pageSize, int siteId) {
		return service.getRecommendArticle(new Paginator(pageNo, pageSize), siteId);
	}

	/**
	 * 返回最新文章
	 * 
	 * 2015年5月24日 下午10:52:05 flyfox 369191470@qq.com
	 * 
	 * @param paginator
	 * @return
	 */
	public Page<TbArticle> articlePageTop(int pageNo, int pageSize, int siteId) {
		return service.getNewArticle(new Paginator(pageNo, pageSize), siteId);
	}

	/**
	 * 返回文章列表
	 * 
	 * 2015年5月24日 下午10:52:05 flyfox 369191470@qq.com
	 * 
	 * @param paginator
	 * @param folderId
	 * @return
	 */
	public Page<TbArticle> articlePageSite(int pageNo, int pageSize, int siteId) {
		return service.getArticleBySiteId(new Paginator(pageNo, pageSize), siteId);
	}
	
	/**
	 * 按照特定排序，返回文章列表
	 * 
	 * 2017年1月17日 下午5:37:16
	 * flyfox 330627517@qq.com
	 * @param pageNo
	 * @param pageSize
	 * @param siteId
	 * @param orderType
	 * @return
	 */
	public Page<TbArticle> articleOrder(int pageNo, int pageSize, int siteId, int orderType) {
		return service.getArticleByOrder(new Paginator(pageNo, pageSize), siteId, orderType);
	}

	/**
	 * 返回文章列表
	 * 
	 * 2015年5月24日 下午10:52:05 flyfox 369191470@qq.com
	 * 
	 * @param paginator
	 * @param folderId
	 * @return
	 */
	public Page<TbArticle> articlePage(int pageNo, int pageSize, int folderId) {
		return service.getArticle(new Paginator(pageNo, pageSize), folderId);
	}

	/**
	 * 返回文章列表不走缓存
	 * 
	 * 2015年5月24日 下午10:52:05 flyfox 369191470@qq.com
	 * 
	 * @param paginator
	 * @param folderId
	 * @return
	 */
	public Page<TbArticle> articlePageNoCache(int pageNo, int pageSize, int folderId) {
		return service.getArticleByNoCache(new Paginator(pageNo, pageSize), folderId);
	}

	/**
	 * 返回对应文章
	 * 
	 * 2015年5月24日 下午10:52:05 flyfox 369191470@qq.com
	 * 
	 * @param paginator
	 * @param folderId
	 * @return
	 */
	public TbArticle article(int articleId) {
		return service.getArticle(articleId);
	}

	/**
	 * 获取栏目滚动图片
	 * 
	 * 2016年1月28日 下午5:28:25 flyfox 369191470@qq.com
	 * 
	 * @param folderId
	 * @return
	 */
	public List<TbFolderRollPicture> rollPicture(int folderId) {
		return service.getRollPicture(folderId);
	}

	/**
	 * 获取公告信息
	 * 
	 * 2016年1月28日 下午5:29:47 flyfox 369191470@qq.com
	 * 
	 * @param folderId
	 * @return
	 */
	public List<TbFolderNotice> notice(int folderId) {
		return service.getNotice(folderId);
	}

}