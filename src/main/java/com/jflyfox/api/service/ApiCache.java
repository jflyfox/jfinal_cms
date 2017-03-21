package com.jflyfox.api.service;

import com.jfinal.log.Log;
import com.jflyfox.util.StrUtils;
import com.jflyfox.util.cache.Cache;
import com.jflyfox.util.cache.CacheManager;

public class ApiCache {

	private final static Log log = Log.getLog(ApiCache.class);
	private final static String cacheName = "ApiCache";
	private static Cache cache;

	public static void init() {
		if (cache == null) {
			log.info("####API Cache初始化......");
			cache = CacheManager.get(cacheName);
		}
	}

	public static <T> T getCache(String key) {
		if (StrUtils.isEmpty(key)) {
			return null;
		}

		init();
		return cache.get(key);
	}

	@SuppressWarnings("unchecked")
	public static <T> T removeCache(String key) {
		if (StrUtils.isEmpty(key)) {
			return null;
		}

		init();
		return (T) cache.remove(key);
	}

	public static Cache addCache(String key, Object value) {
		if (StrUtils.isEmpty(key)) {
			return null;
		}

		init();
		cache.add(key, value);
		return cache;
	}
}
