package com.flyfox.modules.front;

import com.alibaba.fastjson.JSONObject;
import com.flyfox.component.beelt.BeeltFunctions;
import com.flyfox.component.util.JFlyFoxCache;
import com.flyfox.component.util.JFlyFoxUtils;
import com.flyfox.jfinal.base.BaseController;
import com.flyfox.jfinal.base.Paginator;
import com.flyfox.jfinal.component.annotation.ControllerBind;
import com.flyfox.jfinal.component.util.Attr;
import com.flyfox.modules.CommonController;
import com.flyfox.modules.article.TbArticle;
import com.flyfox.modules.front.interceptor.FrontInterceptor;
import com.flyfox.modules.front.service.FrontCacheService;
import com.flyfox.modules.tags.TbTags;
import com.flyfox.system.user.SysUser;
import com.flyfox.system.user.UserCache;
import com.flyfox.util.StrUtils;
import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Page;

/**
 * 个人信息
 * 
 * 2015年3月10日 下午5:36:22 flyfox 330627517@qq.com
 */
@ControllerBind(controllerKey = "/front/person")
public class PersonController extends BaseController {

	public static final String path = "/pages/front/person/";
	
	/**
	 * 个人信息
	 */
	@Before(FrontInterceptor.class)
	public void index() {
		SysUser user = getSessionAttr(Attr.SESSION_NAME);
		if (user == null) {
			redirect(CommonController.firstPage);
			return;
		}

		// 活动目录
		setAttr("folders_selected", "personhome");

		setAttr("user", user);

		// 数据列表,只查询展示的和类型为11,12的
		Page<TbArticle> articles = TbArticle.dao.paginate(getPaginator(), "select * ", //
				" from tb_article where status = 1 and type in (11,12) " //
						+ " and create_id = ? " //
						+ " order by sort,create_time desc", user.getUserid());
		setAttr("page", articles);

		// 显示50个标签
		if (articles.getTotalRow() > 0) {
			Page<TbTags> taglist = new FrontCacheService().getTagsByFolder(new Paginator(1, 50), articles.getList()
					.get(0).getFolderId());
			setAttr("taglist", taglist.getList());
		} else {
			Page<TbTags> taglist = new FrontCacheService().getTags(new Paginator(1, 50));
			setAttr("taglist", taglist.getList());
		}

		// title展示
		setAttr(JFlyFoxUtils.TITLE_ATTR,
				"空间 - " + BeeltFunctions.getUserName(user.getUserid()) + " - " + JFlyFoxCache.getHeadTitle());

		renderAuto(path + "workspace.html");
	}

	/**
	 * 跳转到发布博文页面
	 * 
	 * 2015年6月17日 下午9:53:04 flyfox 330627517@qq.com
	 */
	@Before(FrontInterceptor.class)
	public void newblog() {
		SysUser user = getSessionAttr(Attr.SESSION_NAME);
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
	 * 保存博文
	 * 
	 * 2015年6月17日 下午10:12:18 flyfox 330627517@qq.com
	 */
	public void saveblog() {
		JSONObject json = new JSONObject();
		json.put("status", 2);// 失败

		SysUser user = getSessionAttr(Attr.SESSION_NAME);
		if (user == null) {
			json.put("msg", "没有登录，不能提交博文！");
			renderJson(json.toJSONString());
			return;
		}

		TbArticle model = getModel(TbArticle.class);
		model.remove("id");
		// TODO 验证题目，内容

		model.setFolderId(100); // 博文目录
		model.setStatus("1"); // 显示
		model.setType(11);
		model.setIsComment(1); // 能评论
		model.setIsRecommend(2);// 不推荐
		model.setSort(20); // 排序
		model.setCreateId(getSessionUser().getUserID());
		model.setCreateTime(getNow());
		model.save();

		String tags = getPara("tags");
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
				tbTags.put("create_id", getSessionUser().getUserID());
				tbTags.put("create_time", getNow());
				tbTags.save();

			}
		}

		json.put("status", 1);// 成功
		renderJson(json.toJSONString());
	}
	
	/**
	 * 个人信息
	 */
	@Before(FrontInterceptor.class)
	public void profile() {
		SysUser user = getSessionAttr(Attr.SESSION_NAME);
		if (user == null) {
			redirect(CommonController.firstPage);
		} else {
			setAttr("model", user);

			// 活动目录
			setAttr("folders_selected", "person");

			renderAuto(path + "show_person.html");
		}
	}

	/**
	 * 个人信息保存
	 */
	public void save() {
		JSONObject json = new JSONObject();
		json.put("status", 2);// 失败

		SysUser user = getSessionAttr(Attr.SESSION_NAME);
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
	 * 2015年2月26日 下午1:46:14 flyfox 330627517@qq.com
	 */
	@Before(FrontInterceptor.class)
	public void view() {
		Integer userid = getParaToInt();

		// 活动目录
		setAttr("folders_selected", 0);

		SysUser user = UserCache.getUser(userid);
		setAttr("user", user);
		
		// 数据列表,只查询展示的和类型为11,12的
		Page<TbArticle> articles = TbArticle.dao.paginate(getPaginator(), "select * ", //
				" from tb_article where status = 1 and type in (11,12) " //
						+ " and create_id = ? " //
						+ " and folder_id != " + 13 // 不搜索首页图片
						+ " order by sort,create_time desc", userid);
		setAttr("page", articles);

		// 显示50个标签
		if (articles.getTotalRow() > 0) {
			Page<TbTags> taglist = new FrontCacheService().getTagsByFolder(new Paginator(1, 50), articles.getList()
					.get(0).getFolderId());
			setAttr("taglist", taglist.getList());
		} else {
			Page<TbTags> taglist = new FrontCacheService().getTags(new Paginator(1, 50));
			setAttr("taglist", taglist.getList());
		}

		// title展示
		setAttr(JFlyFoxUtils.TITLE_ATTR, BeeltFunctions.getUserName(userid) + "的空间 - " + JFlyFoxCache.getHeadTitle());

		renderAuto(path + "view_person.html");

	}
}
