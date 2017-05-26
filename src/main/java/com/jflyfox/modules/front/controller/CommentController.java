package com.jflyfox.modules.front.controller;

import com.alibaba.fastjson.JSONObject;
import com.jflyfox.component.base.BaseProjectController;
import com.jflyfox.jfinal.component.annotation.ControllerBind;
import com.jflyfox.modules.admin.comment.CommentService;
import com.jflyfox.modules.admin.comment.TbComment;
import com.jflyfox.system.user.SysUser;
import com.jflyfox.system.user.UserCache;
import com.jflyfox.util.StrUtils;

@ControllerBind(controllerKey = "/front/comment")
public class CommentController extends BaseProjectController {

	/**
	 * 删除评论
	 */
	public void del() {
		JSONObject json = new JSONObject();
		json.put("status", 2);// 失败

		SysUser user = (SysUser) getSessionUser();

		if (user == null) {
			json.put("msg", "没有登录，无法进行删除！");
			renderJson(json.toJSONString());
			return;
		}

		TbComment comment = getModel(TbComment.class);
		int id = comment.getInt("id");
		if (id <= 0) {
			json.put("msg", "提交数据错误，无法删除！");
			renderJson(json.toJSONString());
			return;
		}
		// 确认数据创建人和当前用户一致
		TbComment test = TbComment.dao.findById(id);
		if (test.getInt("create_id") != user.getUserid()) {
			json.put("msg", "不能删除他人提交数据！");
			renderJson(json.toJSONString());
			return;
		}

		// 删除评论
		new CommentService().deleteComment(comment);

		json.put("status", 1);// 成功

		renderJson(json.toJSONString());
	}

	/**
	 * 保存评论
	 */
	public void save() {
		JSONObject json = new JSONObject();
		json.put("status", 2);// 失败

		SysUser user = (SysUser) getSessionUser();

		if (user == null) {
			json.put("msg", "没有登录，无法进行评论！");
			renderJson(json.toJSONString());
			return;
		}

		TbComment comment = getModel(TbComment.class);
		if (StrUtils.isEmpty(comment.getStr("content"))) {
			json.put("msg", "发布内容不能为空！");
			renderJson(json.toJSONString());
			return;
		}
		// 保存评论
		new CommentService().saveComment(user, comment);

		// 设置返回json
		json.put("comment_id", comment.getInt("id"));
		json.put("content", comment.getStr("content"));
		json.put("title_url", user.getStr("title_url"));
		json.put("reply_userid", comment.getInt("reply_userid"));
		json.put("reply_username", UserCache.getUser(comment.getInt("reply_userid")).getUserName());
		json.put("create_id", user.getUserid());
		json.put("create_name", user.getUserName());
		json.put("create_time", comment.getStr("create_time"));
		json.put("status", 1);// 成功

		renderJson(json.toJSONString());
	}

	/**
	 * 获取最新评论数
	 * 
	 * 2015年3月16日 下午5:33:55 flyfox 369191470@qq.com
	 */
	public void count() {
		JSONObject json = new JSONObject();
		json.put("status", 2);// 失败

		SysUser user = (SysUser) getSessionUser();
		if (user == null) {
			json.put("msg", "没有登录，无法获取评论数！");
			renderJson(json.toJSONString());
			return;
		}

		// 获取未读数量
		Object cnt = new CommentService().getCommentUnreadCount(user.getUserid());
		if (cnt == null) {
			json.put("msg", "没有登录，评论数获取失败！");
			renderJson(json.toJSONString());
			return;
		}

		json.put("count", cnt.toString());
		json.put("status", 1);// 成功
		renderJson(json.toJSONString());
	}

}
