package com.flyfox.modules.friendlylink;

import java.util.List;

import org.apache.log4j.Logger;

import com.flyfox.jfinal.base.BaseService;
import com.flyfox.system.dict.SysDictDetail;
import com.flyfox.util.cache.Cache;
import com.flyfox.util.cache.CacheManager;

/**
 * 友情链接管理
 * 
 * @author flyfox 2014-4-24
 */
public class FriendlylinkCache extends BaseService {

	private final static Logger log = Logger.getLogger(FriendlylinkCache.class);
	private final static String cacheName = "FriendlylinkCache";
	private static Cache cache = CacheManager.get(cacheName);

	public static void init() {
		log.info("####FriendlylinkCache初始化......");
		update();
	}

	/**
	 * 更新友情链接缓存
	 * 
	 * 2015年4月24日 下午3:11:40 flyfox 330627517@qq.com
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

}
