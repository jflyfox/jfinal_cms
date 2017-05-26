package com.jflyfox.modules.front.controller;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jflyfox.component.base.BaseProjectController;
import com.jflyfox.component.beelt.BeeltFunctions;
import com.jflyfox.component.util.JFlyFoxUtils;
import com.jflyfox.jfinal.base.Paginator;
import com.jflyfox.jfinal.component.annotation.ControllerBind;
import com.jflyfox.modules.CommonController;
import com.jflyfox.modules.admin.article.ArticleConstant;
import com.jflyfox.modules.admin.article.TbArticle;
import com.jflyfox.modules.admin.comment.CommentService;
import com.jflyfox.modules.admin.tags.TbTags;
import com.jflyfox.modules.front.interceptor.FrontInterceptor;
import com.jflyfox.modules.front.service.FrontCacheService;
import com.jflyfox.system.user.SysUser;
import com.jflyfox.system.user.UserCache;
import com.jflyfox.util.DateUtils;
import com.jflyfox.util.StrUtils;
import com.jflyfox.util.extend.HtmlUtils;

/**
 * 个人信息
 * 
 * 2015年3月10日 下午5:36:22 flyfox 369191470@qq.com
 */
@ControllerBind(controllerKey = "/front/person")
public class PersonController extends BaseProjectController {

	public static final String path = "/person/";

	/**
	 * 个人信息
	 */
	@Before(FrontInterceptor.class)
	public void index() {
		SysUser user = (SysUser) getSessionUser();
		if (user == null) {
			redirect(CommonController.firstPage);
			return;
		}

		// 活动目录
		setAttr("folders_selected", "personhome");

		setAttr("user", user);

		// 数据列表,只查询展示的和类型为11,12的
		Page<TbArticle> articles = TbArticle.dao.paginate(getPaginator(), "select t.* ", //
				" from tb_article t" //
						+ " left join tb_folder tf on tf.id = t.folder_id " //
						+ " where " + getPublicWhere() //
						+ " and t.create_id = ? and tf.site_id = ? " //
						+ " order by t.sort,t.create_time desc", user.getUserid(), getSessionSite().getSiteId());
		setAttr("page", articles);

		// 显示50个标签
		if (articles.getTotalRow() > 0) {
			Page<TbTags> taglist = new FrontCacheService().getTagsByFolder(new Paginator(1, 50), articles.getList()
					.get(0).getFolderId());
			setAttr("taglist", taglist.getList());
		} else {
			Page<TbTags> taglist = tags();
			setAttr("taglist", taglist.getList());
		}

		common(user);

		renderAuto(path + "workspace.html");
	}

	/**
	 * 个人信息
	 */
	@Before(FrontInterceptor.class)
	public void article() {
		SysUser user = (SysUser) getSessionUser();
		if (user == null) {
			redirect(CommonController.firstPage);
			return;
		}

		// 活动目录
		setAttr("folders_selected", "personhome");

		setAttr("user", user);

		// 数据列表,只查询展示的和类型为11,12的
		Page<TbArticle> articles = TbArticle.dao.paginate(getPaginator(), "select t.* ", //
				" from tb_article t" + " left join tb_folder tf on tf.id = t.folder_id " //
						+ " where " + getPublicWhere() //
						+ " and t.create_id = ? and tf.site_id = ? " //
						+ " order by t.sort,t.create_time desc", user.getUserid(), getSessionSite().getSiteId());
		setAttr("page", articles);

		// 显示50个标签
		if (articles.getTotalRow() > 0) {
			Page<TbTags> taglist = new FrontCacheService().getTagsByFolder(new Paginator(1, 50), articles.getList()
					.get(0).getFolderId());
			setAttr("taglist", taglist.getList());
		} else {
			Page<TbTags> taglist = tags();
			setAttr("taglist", taglist.getList());
		}

		common(user);

		renderAuto(path + "article.html");
	}

	/**
	 * 我喜欢的文章
	 */
	@Before(FrontInterceptor.class)
	public void articlelike() {
		SysUser user = (SysUser) getSessionUser();
		if (user == null) {
			redirect(CommonController.firstPage);
			return;
		}

		// 活动目录
		setAttr("folders_selected", "personhome");

		setAttr("user", user);

		// 数据列表,只查询展示的和类型为11,12的
		Page<TbArticle> articles = TbArticle.dao.paginate(getPaginator(), "select t.* ", //
				" from tb_article t " //
						+ " left join tb_folder tf on tf.id = t.folder_id " //
						+ " left join tb_articlelike al on al.article_id = t.id" + " where " + getPublicWhere() //
						+ " and al.create_id = ? and tf.site_id = ? " //
						+ " order by t.sort,t.create_time desc", user.getUserid(), getSessionSite().getSiteId());
		setAttr("page", articles);

		// 显示50个标签
		if (articles.getTotalRow() > 0) {
			Page<TbTags> taglist = new FrontCacheService().getTagsByFolder(new Paginator(1, 50), articles.getList()
					.get(0).getFolderId());
			setAttr("taglist", taglist.getList());
		} else {
			Page<TbTags> taglist = tags();
			setAttr("taglist", taglist.getList());
		}

		common(user);

		renderAuto(path + "articlelike.html");
	}

	/**
	 * 列表公共方法，展示文章和喜欢文章数量以及SEO title
	 * 
	 * 2015年8月16日 下午8:48:35 flyfox 369191470@qq.com
	 * 
	 * @param user
	 */
	private void common(SysUser user) {
		// 文章数量和喜欢数量
		Record record = Db.findFirst("select count(t.id) as artcount,count(al.id) as artlikecount from tb_article t" //
				+ " left join tb_folder tf on tf.id = t.folder_id " //
				+ " left join (select * from tb_articlelike where create_id = ? ) al on al.article_id = t.id"
				+ " where  " + getPublicWhere() //
				+ " and t.create_id = ? and tf.site_id = ? " //
				+ " order by t.sort,t.create_time desc" //
		, user.getUserid(), user.getUserid(), getSessionSite().getSiteId());
		setAttr("userarticle", record.get("artcount"));
		setAttr("userlike", record.get("artlikecount"));

		// title展示
		setAttr(JFlyFoxUtils.TITLE_ATTR, "空间 - " + BeeltFunctions.getUserName(user.getUserid()) + " - "
				+ getAttr(JFlyFoxUtils.TITLE_ATTR));
	}

	/**
	 * 跳转到发布博文页面
	 * 
	 * 2015年6月17日 下午9:53:04 flyfox 369191470@qq.com
	 */
	@Before(FrontInterceptor.class)
	public void newblog() {
		SysUser user = (SysUser) getSessionUser();
		if (user == null) {
			redirect(CommonController.firstPage);
			return;
		}

		setAttr("user", user);

		// 活动目录
		setAttr("folders_selected", "personhome");

		renderAuto(path + "new_blog.html");
	}

	/**
	 * 跳转到编辑博文页面
	 * 
	 * 2015年6月17日 下午9:53:04 flyfox 369191470@qq.com
	 */
	@Before(FrontInterceptor.class)
	public void editblog() {
		SysUser user = (SysUser) getSessionUser();
		if (user == null) {
			redirect(CommonController.firstPage);
			return;
		}

		setAttr("user", user);

		// 活动目录
		setAttr("folders_selected", "personhome");

		TbArticle model = TbArticle.dao.findById(getParaToInt());
		setAttr("model", model);
		// 不是自己的文章也想修改,总有不怀好意的人哦
		if (model.getCreateId() != user.getUserid()) {
			System.err.println("####userid(" + user.getUserid() + ")非法编辑内容");
			redirect(CommonController.firstPage);
			return;
		}

		// 设置标签
		String tags = Db.findFirst("select group_concat(tagname) tags " //
				+ " from tb_tags where article_id = ? order by id", model.getInt("id")).getStr("tags");
		setAttr("tags", tags);

		renderAuto(path + "edit_blog.html");
	}

	/**
	 * 保存博文
	 * 
	 * 2015年6月17日 下午10:12:18 flyfox 369191470@qq.com
	 */
	public void saveblog() {
		JSONObject json = new JSONObject();
		json.put("status", 2);// 失败

		SysUser user = (SysUser) getSessionUser();
		if (user == null) {
			json.put("msg", "没有登录，不能提交博文！");
			renderJson(json.toJSONString());
			return;
		}

		Integer pid = getParaToInt();
		TbArticle model = getModel(TbArticle.class);
		// 验证题目，内容
		String content = model.getContent();
		String title = model.getTitle();
		String tags = getPara("tags");
		// 删除侵入脚本
		content = JFlyFoxUtils.delScriptTag(content);
		title = HtmlUtils.delHTMLTag(title);
		tags = HtmlUtils.delHTMLTag(tags);

		// 这里没有必要提示太精准~因为前台有验证~绕过的都不是好人哦
		if (content == null || HtmlUtils.delHTMLTag(content).length() > 2000 //
				|| title == null || title.length() > 200 //
				|| tags == null || tags.length() > 200 //
		) {
			json.put("msg", "博客信息错误，请输入正确数据！");
			renderJson(json.toJSONString());
			return;
		}

		model.setUpdateTime(getNow());
		if (pid != null && pid > 0) { // 更新
			// 管理员或者自己才能修改
			if (isAdmin(user) //
					|| model.getCreateId().intValue() == user.getUserid().intValue()) {
				json.put("msg", "你没有权限修改博文！");
				renderJson(json.toJSONString());
				return;
			}
			
			model.update();
		} else { // 新增
			model.remove("id");
			if (model.getFolderId() == null || model.getFolderId() <= 0) {
				model.setFolderId(JFlyFoxUtils.MENU_BLOG); // 博文目录
			}
			model.setStatus("1"); // 显示
			model.setType(11);
			model.setIsComment(1); // 能评论
			model.setIsRecommend(2);// 不推荐
			model.setSort(20); // 排序
			model.set("approve_status", ArticleConstant.APPROVE_STATUS_PASS); // 需要审核改为update
			model.setPublishTime(DateUtils.getNow("yyyy-MM-dd")); // 发布时间
			model.setPublishUser(user.getUserName()); // 发布人
			model.setCreateId(getSessionUser().getUserid());
			model.setCreateTime(getNow());
			model.save();
		}

		// 保存tags
		Db.update(" delete from tb_tags where article_id = ?", model.getInt("id"));
		if (StrUtils.isNotEmpty(tags)) {
			String[] tagsArr = tags.split(",");
			for (int i = 0; i < tagsArr.length; i++) {
				String tagname = tagsArr[i];
				// 最多5个
				if (i >= 5) {
					break;
				}
				if (StrUtils.isEmpty(tagname)) {
					continue;
				}
				TbTags tbTags = new TbTags();
				tbTags.put("tagname", tagname);
				tbTags.put("article_id", model.getInt("id"));
				tbTags.put("create_id", getSessionUser().getUserid());
				tbTags.put("create_time", getNow());
				tbTags.save();

			}
		}

		json.put("status", 1);// 成功
		renderJson(json.toJSONString());
	}

	/**
	 * 跳转到编辑博文页面
	 * 
	 * 2015年6月17日 下午9:53:04 flyfox 369191470@qq.com
	 */
	@Before(FrontInterceptor.class)
	public void delblog() {
		SysUser user = (SysUser) getSessionUser();
		Integer id = getParaToInt();
		if (user == null || id == null) {
			redirect(CommonController.firstPage);
			return;
		}

		TbArticle model = TbArticle.dao.findById(getParaToInt());
		setAttr("model", model);
		// 不是自己的文章也想修改,总有不怀好意的人哦
		if (model.getCreateId() != user.getUserid()) {
			System.err.println("####userid(" + user.getUserid() + ")非法编辑内容");
			redirect(CommonController.firstPage);
			return;
		}

		// 删除评论~
		new CommentService().deleteComment(id);
		// 删除文章
		TbArticle.dao.deleteById(id);
		redirect("/front/person");
	}

	/**
	 * 个人信息编辑
	 */
	@Before(FrontInterceptor.class)
	public void profile() {
		SysUser user = (SysUser) getSessionUser();
		if (user == null) {
			redirect(CommonController.firstPage);
			return;
		}

		setAttr("model", user);

		// TODO 用户信息展示
		setAttr("user", user);

		// 活动目录
		setAttr("folders_selected", "person");

		renderAuto(path + "show_person.html");
	}

	/**
	 * 个人信息保存
	 */
	public void save() {
		JSONObject json = new JSONObject();
		json.put("status", 2);// 失败

		SysUser user = (SysUser) getSessionUser();
		int userid = user.getInt("userid");
		SysUser model = getModel(SysUser.class);

		if (userid != model.getInt("userid")) {
			json.put("msg", "提交数据错误！");
			renderJson(json.toJSONString());
			return;
		}

		// 第三方用户不需要密码
		if (user.getInt("usertype") != 4) {
			String oldPassword = getPara("old_password");
			String newPassword = getPara("new_password");
			String newPassword2 = getPara("new_password2");
			if (!user.getStr("password").equals(JFlyFoxUtils.passwordEncrypt(oldPassword))) {
				json.put("msg", "密码错误！");
				renderJson(json.toJSONString());
				return;
			}
			if (StrUtils.isNotEmpty(newPassword) && !newPassword.equals(newPassword2)) {
				json.put("msg", "两次新密码不一致！");
				renderJson(json.toJSONString());
				return;
			} else if (StrUtils.isNotEmpty(newPassword)) { // 输入密码并且一直
				model.set("password", JFlyFoxUtils.passwordEncrypt(newPassword));
			}
		}

		if (StrUtils.isNotEmpty(model.getStr("email")) && model.getStr("email").indexOf("@") < 0) {
			json.put("msg", "email格式错误！");
			renderJson(json.toJSONString());
			return;
		}

		model.update();
		UserCache.init(); // 设置缓存
		SysUser newUser = SysUser.dao.findById(userid);
		setSessionUser(newUser); // 设置session
		json.put("status", 1);// 成功

		renderJson(json.toJSONString());
	}

	/**
	 * 查看文章某用户发布文章
	 * 
	 * 2015年2月26日 下午1:46:14 flyfox 369191470@qq.com
	 */
	@Before(FrontInterceptor.class)
	public void view() {
		Integer userid = getParaToInt();

		SysUser session_user = (SysUser) getSessionUser();
		if (session_user != null && session_user.getUserid() == userid) {
			// 如果是自己，就跳到自己的空间
			redirect("/front/person");
			return;
		}

		// 活动目录
		setAttr("folders_selected", 0);

		SysUser user = UserCache.getUser(userid);
		setAttr("user", user);

		// 数据列表,只查询展示的和类型为11,12的
		Page<TbArticle> articles = TbArticle.dao.paginate(getPaginator(), "select t.* ", //
				" from tb_article t " //
						+ " left join tb_folder tf on tf.id = t.folder_id " //
						+ " where  " + getPublicWhere() //
						+ " and t.create_id = ? and tf.site_id = ? " //
						+ " order by t.sort,t.create_time desc", userid, getSessionSite().getSiteId());
		setAttr("page", articles);

		// 显示50个标签
		if (articles.getTotalRow() > 0) {
			Page<TbTags> taglist = new FrontCacheService().getTagsByFolder(new Paginator(1, 50), articles.getList()
					.get(0).getFolderId());
			setAttr("taglist", taglist.getList());
		} else {
			Page<TbTags> taglist = tags();
			setAttr("taglist", taglist.getList());
		}

		// title展示
		setAttr(JFlyFoxUtils.TITLE_ATTR, BeeltFunctions.getUserName(userid) + "的空间 - " + getAttr(JFlyFoxUtils.TITLE_ATTR));

		renderAuto(path + "view_person.html");

	}

	protected Page<TbTags> tags() {
		return new FrontCacheService().getTags(new Paginator(1, 50), getSessionSite().getSiteId());
	}
}
