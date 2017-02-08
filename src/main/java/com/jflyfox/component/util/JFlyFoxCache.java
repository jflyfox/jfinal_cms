package com.jflyfox.component.util;

import com.jfinal.log.Log;
import com.jflyfox.modules.admin.folder.FolderService;
import com.jflyfox.modules.admin.friendlylink.FriendlylinkCache;
import com.jflyfox.modules.admin.pageview.PageViewCache;
import com.jflyfox.system.config.ConfigCache;
import com.jflyfox.system.dict.DictCache;
import com.jflyfox.system.dict.SysDictDetail;
import com.jflyfox.system.log.SysLog;
import com.jflyfox.system.user.UserCache;
import com.jflyfox.util.cache.Cache;
import com.jflyfox.util.cache.CacheManager;

public class JFlyFoxCache {

	private final static Log log = Log.getLog(JFlyFoxCache.class);
	private final static String cacheName = "JFlyFoxCache";
	private static Cache cache;

	public static void init() {
		log.info("####缓存初始化开始......");
		
		if (cache == null) {
			cache = CacheManager.get(cacheName);
		}
		
		// 系统常量
		JFlyFoxCache.updateCache();
		// 数据字典
		DictCache.init();
		// 用户信息
		UserCache.init();
		// 系统参数配置初始化
		ConfigCache.init();
		// PV缓存绑定
		PageViewCache.init();
		// 友情链接缓存
		FriendlylinkCache.init();
		// URL KEY初始化
		FolderService.initMenuKey();
		// 日志配置初始化
		SysLog.init();
		log.info("####缓存初始化结束......");
	}

	/**
	 * 更新缓存
	 * 
	 * 2015年4月24日 下午3:11:40 flyfox 369191470@qq.com
	 */
	public static void updateCache() {
		cache.clear();

		// 获取head title - html title
		String headTitle = null;
		SysDictDetail headTitleDict = SysDictDetail.dao.findFirst("select detail_name from sys_dict_detail " //
				+ "where  dict_type = 'systemParam' and detail_code = 1");
		if (headTitleDict != null) {
			headTitle = headTitleDict.getStr("detail_name");
		} else {
			headTitle = "门头沟信息网";
		}
		cache.add("headTitle", headTitle);

	}

	public static String getHeadTitle() {
		return cache.get("headTitle");
	}

}
