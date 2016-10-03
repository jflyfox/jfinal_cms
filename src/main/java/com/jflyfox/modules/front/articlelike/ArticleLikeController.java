package com.jflyfox.modules.front.articlelike;

import com.alibaba.fastjson.JSONObject;
import com.jflyfox.component.base.BaseProjectController;
import com.jflyfox.jfinal.component.annotation.ControllerBind;
import com.jflyfox.system.user.SysUser;

/**
 * 文章喜欢
 * 
 * @author flyfox 2015-08-16
 */
@ControllerBind(controllerKey = "/front/articlelike")
public class ArticleLikeController extends BaseProjectController {

	/**
	 * 喜欢
	 * 
	 * 2015年8月16日 下午4:20:10
	 * flyfox 369191470@qq.com
	 */
	public void yes() {
		Integer articleId = getParaToInt();

		JSONObject json = new JSONObject();
		json.put("status", 2);// 失败
		SysUser user = (SysUser) getSessionUser();
		if (user == null) {
			json.put("msg", "没有登录，不能操作！");
			renderJson(json.toJSONString());
			return;
		}

		if (articleId == null || articleId <= 0) {
			json.put("msg", "操作异常");
			renderJson(json.toJSONString());
			return;
		}

		// 不存在就创建
		TbArticleLike model = TbArticleLike.dao.findFirstByWhere(" where article_id = ? and create_id = ? " //
				, articleId, user.getUserID());
		if (model == null) {
			model = new TbArticleLike();
			model.setArticleId(articleId);
			model.setCreateId(getSessionUser().getUserID());
			model.setCreateTime(getNow());
			model.save();
			// 添加缓存
			new ArticleLikeCache().add(user.getUserID(), articleId);
		}

		json.put("status", 1);// 成功
		renderJson(json.toJSONString());
	}

	/**
	 * 取消喜欢
	 * 
	 * 2015年8月16日 下午4:20:27
	 * flyfox 369191470@qq.com
	 */
	public void no() {
		Integer articleId = getParaToInt();

		JSONObject json = new JSONObject();
		json.put("status", 2);// 失败
		SysUser user = (SysUser) getSessionUser();
		if (user == null) {
			json.put("msg", "没有登录，不能操作！");
			renderJson(json.toJSONString());
			return;
		}

		if (articleId == null || articleId <= 0) {
			json.put("msg", "操作异常");
			renderJson(json.toJSONString());
			return;
		}

		// 存在删除对象
		TbArticleLike model = TbArticleLike.dao.findFirstByWhere(" where article_id = ? and create_id = ? " //
				, articleId, user.getUserID());
		if (model != null) {
			model.delete();
			// 删除缓存
			new ArticleLikeCache().delete(user.getUserID(), articleId);
		}

		json.put("status", 1);// 成功
		renderJson(json.toJSONString());
	}
}
