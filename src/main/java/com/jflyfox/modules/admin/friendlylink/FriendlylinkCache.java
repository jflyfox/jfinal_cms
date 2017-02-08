package com.jflyfox.modules.admin.friendlylink;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.log.Log;
import com.jflyfox.jfinal.base.BaseService;
import com.jflyfox.system.dict.SysDictDetail;
import com.jflyfox.util.cache.Cache;
import com.jflyfox.util.cache.CacheManager;

/**
 * 友情链接管理
 * 
 * @author flyfox 2014-4-24
 */
public class FriendlylinkCache extends BaseService {

	private final static Log log = Log.getLog(FriendlylinkCache.class);
	private final static String cacheName = "FriendlylinkCache";
	private static Cache cache;

	public static void init() {
		log.info("####FriendlylinkCache初始化......");
		if (cache == null) {
			cache = CacheManager.get(cacheName);
		}
		update();
	}

	/**
	 * 更新友情链接缓存
	 * 
	 * 2015年4月24日 下午3:11:40 flyfox 369191470@qq.com
	 */
	public static void update() {
		cache.clear();
		List<SysDictDetail> typeList = SysDictDetail.dao.findByWhere(" where dict_type = 'friendlyLinkType'");
		for (SysDictDetail dict : typeList) {
			int type = dict.getInt("detail_id");
			List<TbFriendlylink> list = TbFriendlylink.dao.findByWhere(" where type = ? order by sort ", type);
			cache.add(type + "", list);
		}

	}

	public static List<TbFriendlylink> getList(int type) {
		return cache.get(type + "");
	}

	/**
	 * 友情链接
	 * 
	 * 2016年5月4日 下午2:50:27
	 * flyfox 369191470@qq.com
	 * @param siteId
	 * @return
	 */
	public static List<TbFriendlylink> getFriendlylinkList(int siteId) {
		List<TbFriendlylink> list = cache.get(21 + "");
		
		List<TbFriendlylink> newList =  new ArrayList<TbFriendlylink>();
		for (TbFriendlylink item : list) {
			int tmpSiteId = item.getInt("site_id");
			if (tmpSiteId <= 0 || tmpSiteId == siteId) {
				newList.add(item);
			}
		}
		return newList;
	}

	/**
	 * 关于我们
	 * 
	 * 2016年5月4日 下午2:50:34
	 * flyfox 369191470@qq.com
	 * @param siteId
	 * @return
	 */
	public static List<TbFriendlylink> getAboutList(int siteId) {
		List<TbFriendlylink> list = cache.get(22 + "");
		
		List<TbFriendlylink> newList =  new ArrayList<TbFriendlylink>();
		for (TbFriendlylink item : list) {
			int tmpSiteId = item.getInt("site_id");
			if (tmpSiteId <= 0 || tmpSiteId == siteId) {
				newList.add(item);
			}
		}
		return newList;
	}

}
