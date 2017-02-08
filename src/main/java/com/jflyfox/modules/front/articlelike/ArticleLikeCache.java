package com.jflyfox.modules.front.articlelike;

import java.util.ArrayList;
import java.util.List;

import com.jflyfox.jfinal.base.BaseService;
import com.jflyfox.util.cache.Cache;
import com.jflyfox.util.cache.CacheManager;

public class ArticleLikeCache extends BaseService {

	/**
	 * 目录缓存
	 */
	private final static String cacheName = "ArticleLikeCache";

	protected List<String> init(int userId) {
		List<TbArticleLike> listModel = TbArticleLike.dao.findByWhere(" where create_id = ? ", userId);
		List<String> list = new ArrayList<String>();
		for (TbArticleLike tbArticleLike : listModel) {
			list.add(String.valueOf(tbArticleLike.getArticleId()));
		}
		return list;
	}

	/**
	 * 获取缓存量
	 * 
	 * 2015年8月16日 上午8:56:26 flyfox 369191470@qq.com
	 * 
	 * @param articleId
	 * @return
	 */
	public boolean isLike(int userId, int articleId) {
		Cache cache = CacheManager.get(cacheName);
		String key = ("articleLike_" + userId);
		List<String> list = cache.get(key);
		if (list == null) {
			list = init(userId);
			cache.add(key, list);
		}

		return containsLike(list, articleId);
	}

	public void add(int userId, int articleId) {
		Cache cache = CacheManager.get(cacheName);
		String key = ("articleLike_" + userId);
		List<String> list = cache.get(key);
		if (list == null) {
			list = init(userId);
		} else {
			if (!containsLike(list, articleId))
				list.add(String.valueOf(articleId));
		}
		cache.add(key, list);

	}

	public void delete(int userId, int articleId) {
		Cache cache = CacheManager.get(cacheName);
		String key = ("articleLike_" + userId);
		List<String> list = cache.get(key);
		if (list == null) {
			list = init(userId);
		} else {
			if (containsLike(list, articleId))
				list.remove(String.valueOf(articleId));
		}
		cache.add(key, list);

	}

	private boolean containsLike(List<String> list, int articleId) {
		return list.contains(String.valueOf(articleId));
	}
}
