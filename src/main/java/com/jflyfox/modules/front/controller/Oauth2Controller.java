package com.jflyfox.modules.front.controller;

import java.io.UnsupportedEncodingException;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Before;
import com.jflyfox.component.base.BaseProjectController;
import com.jflyfox.component.util.JFlyFoxUtils;
import com.jflyfox.jfinal.component.annotation.ControllerBind;
import com.jflyfox.jfinal.component.oauth.OauthBaidu;
import com.jflyfox.jfinal.component.oauth.OauthQQ;
import com.jflyfox.jfinal.component.oauth.OauthSina;
import com.jflyfox.jfinal.component.oauth.util.TokenUtil;
import com.jflyfox.modules.CommonController;
import com.jflyfox.modules.admin.article.TbArticle;
import com.jflyfox.modules.admin.site.TbSite;
import com.jflyfox.modules.front.interceptor.FrontInterceptor;
import com.jflyfox.modules.front.service.Oauth2Service;
import com.jflyfox.system.user.SysUser;
import com.jflyfox.system.user.UserCache;
import com.jflyfox.util.StrUtils;

@ControllerBind(controllerKey = "/oauth2")
@Before(FrontInterceptor.class)
public class Oauth2Controller extends BaseProjectController {

	private static final String path = "/pages/oauth2/";
	private static final int OAUTH_ID = 10;

	public void index() {
		TbArticle model = TbArticle.dao.findById(OAUTH_ID);
		setAttr("model", model);
		render(path + "index.html");
	}

	public void login() {
		String login_type = getPara("loin_type");
		String url = "";
		// 设置pre_page到session
		String prePage = getPara("pre_page");
		setSessionAttr("pre_page", prePage);

		try {
			Oauth2Service svc = new Oauth2Service();
			TbSite site = getSessionSite().getModel();
			if ("qq".equals(login_type)) {
				OauthQQ qq = svc.getQQ(site); // OauthQQ.me();
				url = qq.getAuthorizeUrl(TokenUtil.randomState());
			} else if ("sina".equals(login_type)) {
				OauthSina sina = svc.getSina(site); // OauthSina.me();
				url = sina.getAuthorizeUrl(TokenUtil.randomState());
			} else if ("baidu".equals(login_type)) {
				OauthBaidu baidu = svc.getBaidu(site); // OauthBaidu.me();
				url = baidu.getAuthorizeUrl(TokenUtil.randomState());
			} else {
				redirect("admin");
				return;
			}
			redirect(url);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * openid是qq的唯一标识
	 * 
	 * 2015年2月25日 下午7:57:19 flyfox 369191470@qq.com
	 */
	public void qq_callback() {
		String code = getPara("code");
		Oauth2Service svc = new Oauth2Service();
		TbSite site = getSessionSite().getModel();
		OauthQQ oauth = svc.getQQ(site); // OauthQQ.me();
		JSONObject json = null;
		try {
			json = oauth.getUserInfoByCode(code);
		} catch (Exception e) {
			e.printStackTrace();
		}

		toMainPage(json, "nickname", "openid");
	}

	/**
	 * uid是sina的唯一标示
	 * 
	 * 2015年2月25日 下午7:57:29 flyfox 369191470@qq.com
	 */
	public void sina_callback() {
		String code = getPara("code");
		Oauth2Service svc = new Oauth2Service();
		TbSite site = getSessionSite().getModel();
		OauthSina oauth = svc.getSina(site); // OauthSina.me();
		JSONObject json = null;
		try {
			json = oauth.getUserInfoByCode(code);
		} catch (Exception e) {
			e.printStackTrace();
		}

		toMainPage(json, "name", "uid");
	}

	/**
	 * userid是百度的唯一标识
	 * 
	 * 2015年2月25日 下午7:57:40 flyfox 369191470@qq.com
	 */
	public void baidu_callback() {
		String code = getPara("code");
		Oauth2Service svc = new Oauth2Service();
		TbSite site = getSessionSite().getModel();
		OauthBaidu oauth = svc.getBaidu(site); // OauthBaidu.me();
		JSONObject json = null;
		try {
			json = oauth.getUserInfoByCode(code);
		} catch (Exception e) {
			e.printStackTrace();
		}

		toMainPage(json, "username", "userid");

	}

	protected void toMainPage(JSONObject json, String username, String openid) {
		if (json == null) {
			setAttr("msg", "认证解析错误，请您重新输入。");
			render(CommonController.loginPage);
			return;
		}

		username = json.getString(username);
		openid = json.getString(openid);

		if (username == null || openid == null) {
			setAttr("msg", "认证信息获取失败，请您重新输入。");
			render(CommonController.loginPage);
			return;
		}

		TbSite site = getSessionSite().getModel();
		SysUser user = SysUser.dao.findFirstByWhere(" where thirdid = ? ", openid);
		if (user == null) {
			user = new SysUser();
			user.set("username", username);
			user.set("realname", username);
			user.set("password", JFlyFoxUtils.getDefaultPassword());
			user.set("usertype", JFlyFoxUtils.USER_TYPE_THIRD); // 第三方用户
			user.set("departid", JFlyFoxUtils.DEPART_THIRD_ID);
			user.set("state", 1);
			user.set("thirdid", openid);
			user.set("back_site_id", 0);
			user.set("create_site_id", site.getId());
			user.set("create_time", getNow());
			user.set("create_id", 0);
			user.save();
			UserCache.init();
		}

		setSessionUser(user);

		// 新加入，判断是否有上一个页面
		String prePage = getSessionAttr("pre_page");
		removeSessionAttr("pre_page");
		String toPage = StrUtils.isEmpty(prePage) ? CommonController.firstPage : prePage;

		redirect(toPage);
	}
}
