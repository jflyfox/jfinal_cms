package com.jflyfox.modules.admin.comment;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jflyfox.component.util.JFlyFoxUtils;
import com.jflyfox.jfinal.base.BaseService;
import com.jflyfox.modules.admin.article.TbArticle;
import com.jflyfox.modules.front.service.FrontCacheService;
import com.jflyfox.system.user.SysUser;
import com.jflyfox.util.DateUtils;
import com.jflyfox.util.cache.Cache;
import com.jflyfox.util.cache.CacheManager;

public class CommentService extends BaseService {

	/**
	 * 未读评论缓存
	 */
	private final static String cacheName = "CommentService";

	/**
	 * 更新缓存,清空
	 * 
	 * 2015年4月29日 下午4:37:40 flyfox 369191470@qq.com
	 */
	public void clearCache() {
		CacheManager.get(cacheName).clear();
	}

	/**
	 * 保存评论
	 * 
	 * 2015年4月29日 下午3:11:37 flyfox 369191470@qq.com
	 * 
	 * @param user
	 * @param comment
	 */
	public void saveComment(SysUser user, TbComment comment) {
		// 评论
		// 删除标签
//		String content = HtmlUtils.delHTMLTag(comment.getStr("content"));
//		content = HtmlUtils.changeTag(content);
		String content = JFlyFoxUtils.delScriptTag(comment.getStr("content"));

		comment.put("content", content);
		// 评论
		int status;
		if (comment.getInt("reply_userid") == 0) {
			// 评论自己文章 标记为已读
			status = (user.getUserid() == comment.getInt("create_id") ? CommentContants.STATUS_READ
					: CommentContants.STATUS_NO_READ);
			// 设置 回复人为文章创建者
			comment.put("reply_userid", comment.getInt("create_id"));
		} else { // 回复
			status = CommentContants.STATUS_REPLY_NO_READ;
		}
		comment.put("status", status);

		// comment.put("fatherId", 0);
		comment.put("create_id", user.getUserid());
		comment.put("create_time", DateUtils.getNow(DateUtils.DEFAULT_REGEX_YYYY_MM_DD_HH_MIN_SS));
		comment.save();

		// 更新评论数
		updateArticleCommentCount(comment.getArticleId());
		// 更新评论数缓存
		new FrontCacheService().addArticleCount(TbArticle.dao.findById(comment.getArticleId()));
		// 更新未读消息缓存
		getAndUpdateCommentUnreadCount(comment.getReplyUserid(), true);
	}

	/**
	 * 删除评论
	 * 
	 * TODO 删除评论~这里是个大工程，估计需要优化
	 * 
	 * 2015年4月29日 下午3:14:27 flyfox 369191470@qq.com
	 * 
	 * @param comment
	 */
	public void deleteComment(Integer id) {
		List<TbComment> list = TbComment.dao.findByWhere(" where article_id = ?", id);
		CommentService commentSer = new CommentService();
		for (TbComment comment : list) {
			commentSer.deleteComment(comment);
		}
	}

	/**
	 * 删除评论
	 * 
	 * 2015年4月29日 下午3:14:27 flyfox 369191470@qq.com
	 * 
	 * @param comment
	 */
	public void deleteComment(TbComment comment) {
		// 获取原始数据
		comment = TbComment.dao.findById(comment.getId());
		// 删除
		TbComment.dao.deleteById(comment.getId());
		// 更新评论数
		updateArticleCommentCount(comment.getArticleId());
		// 更新评论数缓存
		new FrontCacheService().addArticleCount(TbArticle.dao.findById(comment.getArticleId()));
		// 更新未读消息缓存
		getAndUpdateCommentUnreadCount(comment.getReplyUserid(), true);
	}

	/**
	 * 更新文章评论数据
	 * 
	 * 2015年3月10日 下午3:25:46 flyfox 369191470@qq.com
	 * 
	 * @param article_id
	 */
	public void updateArticleCommentCount(int article_id) {
		String sql = "update tb_article set count_comment = " //
				+ "(select count(*) from tb_comment where article_id = ? ) where id = ? ";
		Db.update(sql, article_id, article_id);
	}

	/**
	 * 更新状态
	 * 
	 * 2015年3月10日 下午4:10:00 flyfox 369191470@qq.com
	 * 
	 * @param userid
	 * @param status
	 */
	public void updateCommentStatusRead(Integer userid) {
		String sql = "update tb_comment set status = status + 1 " //
				+ " where reply_userid = ? and " //
				+ "status in (" + CommentContants.STATUS_NO_READ //
				+ "," + CommentContants.STATUS_REPLY_NO_READ + ") ";
		Db.update(sql, userid);
		// 更新未读消息缓存，标记为已读
		getAndUpdateCommentUnreadCount(userid, true);
	}

	/**
	 * 获取未读数量
	 * 
	 * 由于评论数量会有大量请求，通过缓存实现
	 * 
	 * 2015年4月29日 下午2:53:23 flyfox 369191470@qq.com
	 * 
	 * @param userid
	 * @return
	 */
	public Object getCommentUnreadCount(int userid) {
		return getAndUpdateCommentUnreadCount(userid, false);
	}

	/**
	 * 获取未读数量
	 * 
	 * 由于评论数量会有大量请求，通过缓存实现
	 * 
	 * 2015年4月29日 下午2:53:23 flyfox 369191470@qq.com
	 * 
	 * @param userid
	 * @param userCache
	 *            是否强行更新缓存
	 * @return
	 */
	public Object getAndUpdateCommentUnreadCount(int userid, boolean updateCache) {
		Cache cache = CacheManager.get(cacheName);
		Object cnt = cache.get(userid + "");
		if (cnt == null || updateCache) {
			String sql = "select count(*) AS cnt from tb_comment t " //
					+ " where t.reply_userid = ? " //
					+ " and status in (" + CommentContants.STATUS_NO_READ //
					+ "," + CommentContants.STATUS_REPLY_NO_READ + ") ";
			TbComment obj = TbComment.dao.findFirst(sql, userid);
			// 更新状态为已读
			cnt = obj.get("cnt");
			cache.add(userid + "", cnt);
		}

		return cnt;
	}
}
