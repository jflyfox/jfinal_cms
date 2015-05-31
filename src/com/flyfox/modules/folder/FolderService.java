package com.flyfox.modules.folder;

import java.util.List;

import com.flyfox.jfinal.base.BaseService;
import com.flyfox.util.cache.Cache;
import com.flyfox.util.cache.CacheManager;

/**
 * 目录管理
 * 
 * @author flyfox 2014-2-11
 */
public class FolderService extends BaseService {

	private final static String cacheName = "HomeService";
	/**
	 * 目录缓存
	 */
	private static Cache cache = CacheManager.get(cacheName);

	/**
	 * 更新缓存
	 * 
	 * 2015年4月29日 下午4:37:40 flyfox 330627517@qq.com
	 */
	public void updateCache() {
		updateCache(1);
		updateCache(2);
		// 更新getFolder 缓存
		List<TbFolder> list = TbFolder.dao.findByWhere("");
		for (TbFolder folder : list) {
			cache.add("folder_" + folder.getInt("id"), folder);
		}
	}

	/**
	 * 获取目录信息
	 * 
	 * 2015年4月29日 下午4:37:55 flyfox 330627517@qq.com
	 * 
	 * @return
	 */
	public TbFolder getFolder(int folderId) {
		TbFolder folder = cache.get("folder_" + folderId);
		// 目录列表
		if (folder == null) {
			folder = TbFolder.dao.findById(folderId);
			cache.add("folder_" + folderId, folder);
		}
		return folder;
	}

	/**
	 * 获取目录信息
	 * 
	 * 2015年4月29日 下午4:37:55 flyfox 330627517@qq.com
	 * 
	 * @return
	 */
	public List<TbFolder> getFolderList() {
		return getFolderList(1);
	}

	/**
	 * 获取目录信息
	 * 
	 * 2015年4月29日 下午4:37:55 flyfox 330627517@qq.com
	 * 
	 * @return
	 */
	public List<TbFolder> getFolderListOther() {
		return getFolderList(2);
	}

	/**
	 * 获取目录信息
	 * 
	 * 2015年4月29日 下午4:37:55 flyfox 330627517@qq.com
	 * 
	 * @return
	 */
	private List<TbFolder> getFolderList(int type) {
		List<TbFolder> folders = cache.get("folderList_" + type);
		// 目录列表
		if (folders == null) {
			updateCache(type);
			folders = cache.get("folderList_" + type);
		}
		return folders;
	}

	/**
	 * 更新缓存
	 * 
	 * 2015年4月29日 下午4:37:40 flyfox 330627517@qq.com
	 */
	public void updateCache(int type) {
		List<TbFolder> folders = null;
		if (type == 1) {
			folders = TbFolder.dao.findByWhere(" where status = 1 and sort <= 10 order by sort");
		} else if (type == 2) {
			folders = TbFolder.dao.findByWhere(" where status = 1 and sort > 10 order by sort");
		}
		cache.add("folderList_" + type, folders);
	}
}
