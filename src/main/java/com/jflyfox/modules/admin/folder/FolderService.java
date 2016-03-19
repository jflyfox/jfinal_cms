package com.jflyfox.modules.admin.folder;

import java.util.List;

import com.jfinal.log.Log;
import com.jflyfox.jfinal.base.BaseService;
import com.jflyfox.system.log.SysLog;
import com.jflyfox.util.StrUtils;
import com.jflyfox.util.cache.Cache;
import com.jflyfox.util.cache.CacheManager;

/**
 * 目录管理
 * 
 * @author flyfox 2014-2-11
 */
public class FolderService extends BaseService {

	private final static Log log = Log.getLog(SysLog.class);
	
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
		
		//初始化urlKey
		initMenuKey();
	}

	private final static String urlkeyCacheName = "JFlyFoxUtils";
	private static Cache urlkeyCache = CacheManager.get(urlkeyCacheName);
	
	public static void initMenuKey(){
		log.info("####目录Key初始化......");
		urlkeyCache.clear();
		List<TbFolder> folders = TbFolder.dao.findByWhere(" where status = 1 order by sort");
		for (TbFolder tbFolder : folders) {
			if (StrUtils.isNotEmpty(tbFolder.getKey())) {
				urlkeyCache.add(tbFolder.getKey(), tbFolder.getId()+"");
				urlkeyCache.add(tbFolder.getId()+"", tbFolder.getKey());
			}
		}
	}
	
	public static String getMenu(String key) {
		return (urlkeyCache.get(key) == null) ? key : urlkeyCache.get(key).toString();
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
			folders = TbFolder.dao.findByWhere(" where status = 1 and parent_id = 0 and sort <= 50 order by sort");
		} else if (type == 2) {
			folders = TbFolder.dao.findByWhere(" where status = 1 and parent_id = 0 and sort > 50 order by sort");
		}
		cache.add("folderList_" + type, folders);
	}
	
	/**
	 * 目录复选框
	 * 
	 * 2015年1月28日 下午5:28:40 flyfox 330627517@qq.com
	 * 
	 * @return
	 */
	public String selectFolder(Integer selected) {
		List<TbFolder> list = TbFolder.dao.find(" select id,name from tb_folder order by sort,create_time desc ");
		StringBuffer sb = new StringBuffer("");
		if (list != null && list.size() > 0) {
			for (TbFolder folder : list) {
				sb.append("<option value=\"");
				sb.append(folder.getInt("id"));
				sb.append("\" ");
				sb.append(folder.getInt("id") == selected ? "selected" : "");
				sb.append(">");
				sb.append(folder.getStr("name"));
				sb.append("</option>");
			}
		}
		return sb.toString();
	}
	
	/**
	 * 不通过索引获取所有目录
	 * 
	 * 2016年3月19日 下午6:04:32
	 * flyfox 330627517@qq.com
	 * @return
	 */
	public List<TbFolder> getFolders() {
		return TbFolder.dao.findByWhere(" order by sort,id ");
	}
}
