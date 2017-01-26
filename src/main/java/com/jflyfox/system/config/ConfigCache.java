package com.jflyfox.system.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.log.Log;
import com.jflyfox.system.user.UserCache;
import com.jflyfox.util.NumberUtils;
import com.jflyfox.util.cache.Cache;
import com.jflyfox.util.cache.CacheManager;

/**
 * 配置管理缓存
 * 
 * 2016年12月17日 下午11:26:25 flyfox 369191470@qq.com
 */
public class ConfigCache {

	private final static Log log = Log.getLog(UserCache.class);
	private final static String cacheName = "ConfigCache";
	private static Cache cache;

	private ConfigCache() {
	}

	public static void init() {
		if (cache == null) {
			cache = CacheManager.get(cacheName);
		}
		log.info("####参数配置Cache初始化......");
		Map<String, SysConfig> cacheMap = new HashMap<String, SysConfig>();
		List<SysConfig> userList = SysConfig.dao.findByWhere(" order by sort,id ");
		for (SysConfig config : userList) {
			cacheMap.put(config.getKey(), config);
		}
		cache.add("cacheMap", cacheMap);
	}

	public static void update() {
		init();
	}

	public static SysConfig getSysConfig(String key) {
		return getSysConfigMap().get(key);
	}

	public static String getValue(String key) {
		return getSysConfig(key) == null ? null : getSysConfig(key).getValue();
	}

	public static int getValueToInt(String key) {
		return NumberUtils.parseInt(getValue(key));
	}

	public static Boolean getValueToBoolean(String key) {
		String val = getValue(key);
		try {
			return Boolean.valueOf(val);
		} catch (Exception e) {
			return false;
		}
	}

	public static String getCode(String key) {
		return getSysConfig(key) == null ? null : getSysConfig(key).getCode();
	}

	private static Map<String, SysConfig> getSysConfigMap() {
		return cache.get("cacheMap");
	}

}
