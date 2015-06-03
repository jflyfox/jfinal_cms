package com.flyfox.modules;

import java.util.List;
import java.util.Map;

import com.flyfox.component.util.JFlyFoxUtils;
import com.flyfox.jfinal.base.BaseController;
import com.flyfox.jfinal.component.annotation.ControllerBind;
import com.flyfox.modules.front.interceptor.FrontInterceptor;
import com.flyfox.modules.front.service.FrontService;
import com.flyfox.system.dict.DictCache;
import com.flyfox.system.menu.SysMenu;
import com.flyfox.system.user.SysUser;
import com.flyfox.system.user.UserCache;
import com.flyfox.system.user.UserSvc;
import com.flyfox.util.Config;
import com.flyfox.util.StrUtils;
import com.jfinal.aop.Before;

/**
 * CommonController
 */
@ControllerBind(controllerKey = "/")
public class CommonController extends BaseController {

	public static final String loginPage = "/login.html";
	public static final String mainPage = "/article/list";
	public static final String firstPage = "/";

	/**
	 * 首页，菜单
	 * 
	 * 2015年5月25日 下午11:00:28
	 * flyfox 330627517@qq.com
	 */
	@Before(FrontInterceptor.class)
	public void index() {
		new FrontService().menu(this);
	}

	public void admin() {
		if (getSessionUser() != null) {
			// 如果session存在，不再验证
			redirect(mainPage);
		} else {
			render(loginPage);
		}

	}

	/**
	 * 登陆
	 * 
	 * @author flyfox 2013-11-11
	 */
	public void login() {
		// 初始化数据字典Map
		String username = getPara("username");
		String password = getPara("password");

		// 新加入，判断是否有上一个页面
		String prePage = getPara("pre_page");
		String toPage = StrUtils.isEmpty(prePage) || prePage.indexOf("login") >= 0 ? firstPage : prePage;
		setAttr("pre_page", prePage); // 如果密码错误还需要用到

		if (StrUtils.isEmpty(username)) {
			setAttr("msg", "用户名不能为空");
			render(loginPage);
			return;
		} else if (StrUtils.isEmpty(password)) {
			setAttr("msg", "密码不能为空");
			render(loginPage);
			return;
		}
		String encryptPassword = JFlyFoxUtils.passwordEncrypt(password); // 加密
		SysUser user = SysUser.dao.findFirstByWhere(" where username = ? and password = ? ", username, encryptPassword);
		if (user == null || user.getInt("userid") <= 0) {
			setAttr("msg", "认证失败，请您重新输入。");
			render(loginPage);
			return;
		} else {
			// 管理员，后台用才需要注册菜单
			if (user.getInt("usertype") == 1 || user.getInt("usertype") == 2) {
				Map<Integer, List<SysMenu>> map = new UserSvc().getAuthMap(user);
				if (map == null) {
					setAttr("msg", "没有权限，请联系管理员");
					render(loginPage);
					return;
				}
				// 注入菜单
				setSessionAttr("menu", map);
			}

			setSessionUser(user);
		}

		redirect(toPage);
	}

	/**
	 * 登出
	 * 
	 * @author flyfox 2013-11-13
	 */
	public void logout() {
		removeSessionUser();
		setAttr("msg", "您已退出");
		render(loginPage);
	}

	public void update_cache() {
		DictCache.init();
		UserCache.init();
		renderHtml("1");
	}

	public void trans() {
		String redirectPath = getPara();
		if (StrUtils.isEmpty(redirectPath)) {
			redirectPath = Config.getStr("PAGES.TRANS");
		} else if (redirectPath.equals("auth")) {
			redirectPath = "/pages/error/trans_no_auth.html";
		}
		render(redirectPath);
	}
}
