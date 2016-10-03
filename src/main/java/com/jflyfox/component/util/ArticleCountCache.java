package com.jflyfox.component.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.jflyfox.modules.admin.article.TbArticle;

/**
 * 浏览数量缓存
 * 
 * @see 1.同一用户访问同一文章，一段时间（MAX_TIME）内只记录一次。
 * 
 *      2015年2月26日 上午11:35:56 flyfox 369191470@qq.com
 */
public class ArticleCountCache {

	/**
	 * 同一用户最大记录时间
	 */
	private final static long MAX_TIME = 3;// 3秒
	private final static Map<String, Long> cacheUserKeyMap = new ConcurrentHashMap<String, Long>();

	private ArticleCountCache() {
	}

	/**
	 * 传入的必须是数据库时间article对象，这里会更新
	 * 
	 * 2015年2月26日 上午11:45:31 flyfox 369191470@qq.com
	 * 
	 * @param article
	 */
	public static void addCountView(TbArticle article, String uuid) {
		Integer key = article.getInt("id");
		Integer countView = article.getCountView();
		long nowTime = System.currentTimeMillis() / 1000;

		Long userTime = cacheUserKeyMap.get(key + "_" + uuid);
		if (userTime == null) { // 第一次访问
			cacheUserKeyMap.put(key + "_" + uuid, nowTime);
		} else if (nowTime - userTime > MAX_TIME) { // 访问时间超过最大时间
			cacheUserKeyMap.put(key + "_" + uuid, nowTime);
		} else {
			// 展示缓存浏览量
			return;
		}

		article.setCountView(countView + 1);
		article.update();

	}
}
