package com.flyfox.component.interceptor;

import com.flyfox.modules.comment.CommentService;
import com.flyfox.modules.folder.FolderService;
import com.flyfox.modules.front.service.FrontCacheService;
import com.flyfox.util.Config;
import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;

/**
 * 缓存更新拦截器
 * 
 * @author flyfox 2015-9-21
 */
public class UpdateCacheInterceptor implements Interceptor {

	/**
	 * 缓存更新时间
	 */
	public static final long UPDATE_TIME = Config.getToLong("MTG.UPDATE_TIME");

	public static long lastUpdateTime = System.currentTimeMillis();

	public void intercept(ActionInvocation ai) {

		// 如果想让体验更好，可以调用一遍所有缓存方法（比较麻烦）
		long now = System.currentTimeMillis();
		if (UPDATE_TIME > 0 && now - lastUpdateTime > UPDATE_TIME) {
			// 更新目录缓存
			new FolderService().updateCache();
			// 清除回复数缓存
			new CommentService().clearCache();
			// 清除所有前台缓存
			new FrontCacheService().clearCache();

			lastUpdateTime = now;
		}
		ai.invoke();

	}
}
