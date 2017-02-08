package com.jflyfox.modules.front.service;

import java.util.List;

import com.jfinal.plugin.activerecord.Page;
import com.jflyfox.component.util.JFlyFoxUtils;
import com.jflyfox.jfinal.base.BaseService;
import com.jflyfox.jfinal.base.Paginator;
import com.jflyfox.modules.admin.article.ArticleConstant;
import com.jflyfox.modules.admin.article.TbArticle;
import com.jflyfox.modules.admin.foldernotice.TbFolderNotice;
import com.jflyfox.modules.admin.folderrollpicture.TbFolderRollPicture;
import com.jflyfox.modules.admin.tags.TbTags;
import com.jflyfox.util.cache.Cache;
import com.jflyfox.util.cache.CacheManager;

public class FrontCacheService extends BaseService {

	private final static String cacheName = "FrontCacheService";

	/**
	 * 更新缓存,清空
	 * 
	 * 2015年4月29日 下午4:37:40 flyfox 369191470@qq.com
	 */
	public void clearCache() {
		 CacheManager.get(cacheName).clear();
	}

	/**
	 * 加入文章访问量和评论数缓存
	 * 
	 * 2015年5月26日 上午8:53:27 flyfox 369191470@qq.com
	 * 
	 * @param article
	 * @return
	 */
	public TbArticle addArticleCount(TbArticle article) {
		String key = ("articleCount_" + article.getId());
		TbArticle articleCount = new TbArticle().setId(article.getId()) //
				.setCountView(article.getCountView()) //
				.setCountComment(article.getCountComment());
		Cache cache = CacheManager.get(cacheName);
		cache.add(key, articleCount);
		return articleCount;
	}

	/**
	 * 获取缓存量
	 * 
	 * 2015年5月26日 上午8:56:26 flyfox 369191470@qq.com
	 * 
	 * @param articleId
	 * @return
	 */
	public TbArticle getArticleCount(int articleId) {
		String key = ("articleCount_" + articleId);
		// 推荐文章列表
		TbArticle articleCount = TbArticle.dao.findFirstCache(cacheName, key, //
				"select id,count_view,count_comment " //
						+ "from tb_article where id = ?", articleId);
		return articleCount;
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
	public List<TbTags> getTagsByArticle(int articleId) {
		String key = ("tagsArticle_" + articleId);
		List<TbTags> taglist = TbTags.dao.findCache(cacheName, key, //
				"select * from tb_tags where article_id = ? order by  create_time desc ", articleId);
		return taglist;
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
	public Page<TbTags> getTagsByFolder(Paginator paginator, int folderId) {
		String key = ("tagsFolder_" + folderId + "_" + paginator.getPageNo() + "_" + paginator.getPageSize());
		Page<TbTags> taglist = TbTags.dao.paginateCache(cacheName, key, paginator, " select tagname ", //
				"from tb_tags where article_id in (select id from tb_article where folder_id = ? )" //
						+ " group by tagname order by max(create_time) desc ", folderId);
		return taglist;
	}

	/**
	 * 获取标签信息
	 * 
	 * 2015年5月25日 下午11:49:03 flyfox 369191470@qq.com
	 * 
	 * @param paginator
	 * @return
	 */
	public Page<TbTags> getTags(Paginator paginator, int siteId) {
		String key = ("tags_" + paginator.getPageNo() + "_" + paginator.getPageSize() + "_" + siteId);
		Page<TbTags> taglist = TbTags.dao.paginateCache(cacheName, key, paginator, " select t.tagname ", //
				"from tb_tags t" //
						+ " left join tb_article ta on ta.id = t.article_id " //
						+ " left join tb_folder tf on tf.id = ta.folder_id " //
						+ " where tf.site_id = ? " //
						+ " group by t.tagname order by max(t.create_time) desc ", siteId);
		return taglist;
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
	public Page<TbArticle> getRecommendArticle(Paginator paginator, int siteId) {
		String key = ("recommendArticle_" + paginator.getPageNo() + "_" + paginator.getPageSize() + "_" + siteId);
		Page<TbArticle> articles = TbArticle.dao.paginateCache(cacheName, key, paginator, "select t.* " //
				, " from tb_article t " //
						+ " left join tb_folder tf on tf.id = t.folder_id " //
						+ " where " + getPublicWhere() + " and t.is_recommend = 1 " // 推荐文章
						+ " and  tf.site_id = ? " //
						+ " order by t.sort,t.create_time desc", siteId);
		return articles;
	}

	/**
	 * 返回最新文章
	 * 
	 * 2015年5月24日 下午10:52:05 flyfox 369191470@qq.com
	 * 
	 * @param paginator
	 * @return
	 */
	public Page<TbArticle> getNewArticle(Paginator paginator, int siteId) {
		String key = ("newArticle_" + paginator.getPageNo() + "_" + paginator.getPageSize() + "_" + siteId);
		// 推荐文章列表
		Page<TbArticle> articles = TbArticle.dao.paginateCache(cacheName, key, paginator, "select t.* " //
				, " from tb_article t " //
						+ " left join tb_folder tf on tf.id = t.folder_id " //
						+ "where " + getPublicWhere() //
						+ " and  tf.site_id = ? " //
						+ " order by t.publish_time desc,t.update_time desc", siteId);
		return articles;
	}

	/**
	 * 返回对应文章列表
	 * 
	 * 2015年5月24日 下午10:52:05 flyfox 369191470@qq.com
	 * 
	 * @param paginator
	 * @param folderId
	 * @return
	 */
	public Page<TbArticle> getArticleBySiteId(Paginator paginator, int siteId) {
		String key = ("articleSite_" + paginator.getPageNo() + "_" + paginator.getPageSize() + "_" + siteId);
		String fromSql = " from tb_article t " //
				+ " left join tb_folder tf on tf.id = t.folder_id " //
				+ " where " + getPublicWhere() //
				+ " and tf.site_id = ? " + " order by t.sort,t.create_time desc";
		// 推荐文章列表
		Page<TbArticle> articles = TbArticle.dao.paginateCache(cacheName, key, paginator, "select t.* ", fromSql, siteId);
		return articles;
	}

	/**
	 * 返回对应文章列表
	 * 
	 * 2015年5月24日 下午10:52:05 flyfox 369191470@qq.com
	 * 
	 * @param paginator
	 * @param folderId
	 * @return
	 */
	public Page<TbArticle> getArticleByOrder(Paginator paginator, int siteId, int orderType) {
		String key = ("articleOrder_" + paginator.getPageNo() + "_" + paginator.getPageSize() + "_" + siteId + "_" + orderType);
		String fromSql = " from tb_article t " //
				+ " left join tb_folder tf on tf.id = t.folder_id " //
				+ " where " + getPublicWhere() //
				+ " and tf.site_id = ? ";
		if (orderType==1) { // 默认
			fromSql +=" order by t.sort,t.create_time desc";
		} else if (orderType==2){ // 最新
			fromSql +=" order by t.create_time desc";
		} else if (orderType==3){ // 精品
			fromSql +=" order by (t.count_comment*10+t.count_view) desc";
		} else if (orderType==4){ // 待回复的
			fromSql +=" and t.count_comment = 0 order by t.create_time desc";
		}
		// 推荐文章列表
		Page<TbArticle> articles = TbArticle.dao.paginateCache(cacheName, key, paginator, "select t.* ", fromSql, siteId);
		return articles;
	}
	
	/**
	 * 返回对应文章列表
	 * 
	 * 2015年5月24日 下午10:52:05 flyfox 369191470@qq.com
	 * 
	 * @param paginator
	 * @param folderId
	 * @return
	 */
	public Page<TbArticle> getArticle(Paginator paginator, int folderId) {
		String key = ("article_" + folderId + "_" + paginator.getPageNo() + "_" + paginator.getPageSize());
		String fromSql = " from tb_article t where " + getPublicWhere() + " and t.folder_id =  ? " //
				+ " order by t.sort,t.create_time desc";
		// 推荐文章列表
		Page<TbArticle> articles = TbArticle.dao.paginateCache(cacheName, key, paginator, "select * " //
				, fromSql, folderId);
		return articles;
	}

	/**
	 * 返回对应文章列表
	 * 
	 * 2015年5月24日 下午10:52:05 flyfox 369191470@qq.com
	 * 
	 * @param paginator
	 * @param folderId
	 * @return
	 */
	public Page<TbArticle> getArticleByNoCache(Paginator paginator, int folderId) {
		String fromSql = " from tb_article t where " + getPublicWhere() + " and folder_id =  ? " //
				+ " order by sort,create_time desc";
		// 推荐文章列表
		Page<TbArticle> articles = TbArticle.dao.paginate(paginator, "select * ", fromSql, folderId);
		return articles;
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
	public TbArticle getArticle(int articleId) {
		String key = ("article_" + articleId);
		TbArticle articles = TbArticle.dao.findFirstCache(cacheName, key, //
				"select * from tb_article where id = ?", articleId);
		return articles;
	}

	/**
	 * 获取栏目滚动图片
	 * 
	 * 2016年1月28日 下午5:28:25 flyfox 369191470@qq.com
	 * 
	 * @param folderId
	 * @return
	 */
	public List<TbFolderRollPicture> getRollPicture(int folderId) {
		String key = ("getRollPicture_" + folderId);
		String fromSql = "select * from tb_folder_roll_picture t " //
				+ " where is_deleted = " + JFlyFoxUtils.IS_DELETED_NO //
				+ " and folder_id = ? order by sort,create_time desc";
		List<TbFolderRollPicture> list = TbFolderRollPicture.dao.findCache(cacheName, key, fromSql, folderId);
		return list;
	}

	/**
	 * 获取公告信息
	 * 
	 * 2016年1月28日 下午5:29:47 flyfox 369191470@qq.com
	 * 
	 * @param folderId
	 * @return
	 */
	public List<TbFolderNotice> getNotice(int folderId) {
		String key = ("getNotice_" + folderId);
		String sql = "select * from tb_folder_notice t " //
				+ " where is_deleted = " + JFlyFoxUtils.IS_DELETED_NO //
				+ " and folder_id = ? order by sort,create_time desc";
		List<TbFolderNotice> list = TbFolderNotice.dao.findCache(cacheName, key, sql, folderId);
		return list;
	}

	/**
	 * 公共文章查询sql
	 * 
	 * 2016年3月19日 下午7:03:11 flyfox 369191470@qq.com
	 * 
	 * @return
	 */
	public String getPublicWhere() {
		return " t.status =  " + JFlyFoxUtils.STATUS_SHOW //
				+ " and t.approve_status = " + ArticleConstant.APPROVE_STATUS_PASS // 审核通过
				+ " and t.type in (11,12) " // 查询状态为显示，类型是预览和正常的文章
		;
	}
}
