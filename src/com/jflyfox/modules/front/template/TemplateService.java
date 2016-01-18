package com.jflyfox.modules.front.template;

import java.util.List;

import com.jfinal.plugin.activerecord.Page;
import com.jflyfox.component.util.JFlyFoxUtils;
import com.jflyfox.jfinal.base.BaseService;
import com.jflyfox.jfinal.base.Paginator;
import com.jflyfox.modules.admin.article.TbArticle;
import com.jflyfox.modules.admin.tags.TbTags;
import com.jflyfox.util.cache.Cache;
import com.jflyfox.util.cache.CacheManager;

/**
 * 模板方法
 * 
 * 2016年1月18日 下午6:05:54
 * flyfox 330627517@qq.com
 */
public class TemplateService extends BaseService {

	private final static String cacheName = "TemplateService";
	
	/**
	 * 模板缓存
	 */
	private static Cache cache = CacheManager.get(cacheName);

	/**
	 * 更新缓存,清空
	 * 
	 * 2015年4月29日 下午4:37:40 flyfox 330627517@qq.com
	 */
	public void clearCache() {
		cache.clear();
	}

	/**
	 * 加入文章访问量和评论数缓存
	 * 
	 * 2015年5月26日 上午8:53:27 flyfox 330627517@qq.com
	 * 
	 * @param article
	 * @return
	 */
	public static TbArticle addArticleCount(TbArticle article) {
		String key = ("articleCount_" + article.getId());
		TbArticle articleCount = new TbArticle().setId(article.getId()) //
				.setCountView(article.getCountView()) //
				.setCountComment(article.getCountComment());
		cache.add(key, articleCount);
		return articleCount;
	}

	/**
	 * 获取缓存量
	 * 
	 * 2015年5月26日 上午8:56:26 flyfox 330627517@qq.com
	 * 
	 * @param articleId
	 * @return
	 */
	public static TbArticle articleCount(int articleId, boolean updateCache) {
		String key = ("articleCount_" + articleId);
		if (!updateCache) {
			cache.remove(key);
		}
		
		// 查询查看和浏览
		TbArticle articleCount = TbArticle.dao.findFirstCache(cacheName, key, //
				"select id,count_view,count_comment " //
						+ "from tb_article where id = ?", articleId);
		return articleCount;
	}

	/**
	 * 获取标签信息
	 * 
	 * 2015年5月25日 下午11:49:58 flyfox 330627517@qq.com
	 * 
	 * @param paginator
	 * @param folderId
	 *            目录
	 * @return
	 */
	public static List<TbTags> listTagsByArticle(int articleId, boolean updateCache) {
		String key = ("tagsArticle_" + articleId);
		if (!updateCache) {
			cache.remove(key);
		}
		
		List<TbTags> taglist = TbTags.dao.findCache(cacheName, key, //
				"select * from tb_tags where article_id = ? order by  create_time desc ", articleId);
		return taglist;
	}

	/**
	 * 获取标签信息
	 * 
	 * 2015年5月25日 下午11:49:58 flyfox 330627517@qq.com
	 * 
	 * @param paginator
	 * @param folderId
	 *            目录
	 * @return
	 */
	public Page<TbTags> pageTagsByFolder(Paginator paginator, int folderId, boolean updateCache) {
		String key = ("tagsFolder_" + folderId + "_" + paginator.getPageNo() + "_" + paginator.getPageSize());
		if (!updateCache) {
			cache.remove(key);
		}
		
		Page<TbTags> taglist = TbTags.dao.paginateCache(cacheName, key, paginator, " select tagname ", //
				"from tb_tags where article_id in (select id from tb_article where folder_id = ? )" //
						+ " group by tagname order by create_time desc ", folderId);
		return taglist;
	}

	/**
	 * 获取标签信息
	 * 
	 * 2015年5月25日 下午11:49:03 flyfox 330627517@qq.com
	 * 
	 * @param paginator
	 * @return
	 */
	public Page<TbTags> pageTags(Paginator paginator, boolean updateCache) {
		String key = ("tags_" + paginator.getPageNo() + "_" + paginator.getPageSize());
		if (!updateCache) {
			cache.remove(key);
		}
		
		Page<TbTags> taglist = TbTags.dao.paginateCache(cacheName, key, paginator, " select tagname ", //
				"from tb_tags group by tagname order by create_time desc ");
		return taglist;
	}

	/**
	 * 查询文章，展示的和类型为11,12的推荐文件,前10个
	 * 
	 * 2015年4月29日 下午4:48:24 flyfox 330627517@qq.com
	 * 
	 * @param paginator
	 * @param folder_id
	 * @return
	 */
	public Page<TbArticle> pageArticleByRecommend(Paginator paginator, boolean updateCache) {
		String key = ("recommendArticle_" + paginator.getPageNo() + "_" + paginator.getPageSize());
		if (!updateCache) {
			cache.remove(key);
		}
		
		Page<TbArticle> articles = TbArticle.dao.paginateCache(cacheName, key, paginator, "select * " //
				, " from tb_article  where status = 1 and type in (11,12) " //
						+ " and is_recommend = 1 " // 推荐文章
						+ " and folder_id != " + JFlyFoxUtils.MENU_TOPPIC // 不搜索首页图片
						+ " order by sort,create_time desc");
		return articles;
	}

	/**
	 * 返回最新文章
	 * 
	 * 2015年5月24日 下午10:52:05 flyfox 330627517@qq.com
	 * 
	 * @param paginator
	 * @return
	 */
	public Page<TbArticle> getNewArticle(Paginator paginator, boolean updateCache) {
		String key = ("newArticle_" + paginator.getPageNo() + "_" + paginator.getPageSize());
		if (!updateCache) {
			cache.remove(key);
		}
		
		// 推荐文章列表
		Page<TbArticle> articles = TbArticle.dao.paginateCache(cacheName, key, paginator, "select * " //
				, " from tb_article where status = 1 and type in (11,12) " // 查询状态为显示，类型是预览和正常的文章
						+ " order by publish_time desc,update_time desc");
		return articles;
	}

	/**
	 * 返回对应文章
	 * 
	 * 2015年5月24日 下午10:52:05 flyfox 330627517@qq.com
	 * 
	 * @param paginator
	 * @param folderId
	 * @return
	 */
	public Page<TbArticle> pageArticle(Paginator paginator, int folderId, boolean updateCache) {
		String key = ("article_" + folderId + "_" + paginator.getPageNo() + "_" + paginator.getPageSize());
		if (!updateCache) {
			cache.remove(key);
		}
		
		// 推荐文章列表
		Page<TbArticle> articles = TbArticle.dao.paginateCache(cacheName, key, paginator, "select * " //
				, " from tb_article " //
						+ " where status = 1 and type in (11,12) " // 查询状态为显示，类型是预览和正常的文章
						+ " and folder_id =  ? " //
						+ " order by sort,create_time desc", folderId);
		return articles;
	}

	/**
	 * 返回对应文章
	 * 
	 * 2015年5月24日 下午10:52:05 flyfox 330627517@qq.com
	 * 
	 * @param paginator
	 * @param folderId
	 * @return
	 */
	public TbArticle article(int articleId, boolean updateCache) {
		String key = ("article_" + articleId);
		if (!updateCache) {
			cache.remove(key);
		}
		
		TbArticle articles = TbArticle.dao.findFirstCache(cacheName, key, //
				"select * from tb_article where id = ?", articleId);
		return articles;
	}

}
