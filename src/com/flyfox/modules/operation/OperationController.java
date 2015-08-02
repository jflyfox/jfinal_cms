package com.flyfox.modules.operation;

import com.alibaba.fastjson.JSONObject;
import com.flyfox.jfinal.base.BaseController;
import com.flyfox.modules.comment.CommentService;
import com.flyfox.modules.folder.FolderService;
import com.flyfox.modules.front.service.FrontCacheService;
import com.flyfox.system.user.SysUser;

/**
 * 友情链接管理
 * 
 * @author flyfox 2014-4-24
 */
public class OperationController extends BaseController {

	private static final String path = "/pages/operation/";

	/**
	 * 跳转到操作页面
	 * 
	 * 2015年3月16日 下午5:33:55 flyfox 330627517@qq.com
	 */
	public void index() {
		render(path + "operation.html");
	}

	/**
	 * 更新缓存
	 * 
	 * 2015年3月16日 下午5:33:55 flyfox 330627517@qq.com
	 */
	public void updateCache() {
		JSONObject json = new JSONObject();
		json.put("status", 2);// 失败

		SysUser user = (SysUser) getSessionUser();
		if (user == null || user.getInt("usertype") != 1) {
			json.put("msg", "您不是管理员，无法操作！");
			renderJson(json.toJSONString());
			return;
		}

		// 更新目录缓存
		new FolderService().updateCache();
		// 清除回复数缓存
		new CommentService().clearCache();
		// 清除所有前台缓存
		new FrontCacheService().clearCache();

		json.put("status", 1);// 成功
		renderJson(json.toJSONString());
	}
}
