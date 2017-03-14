package com.jflyfox.modules.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jflyfox.component.base.BaseProjectController;
import com.jflyfox.component.util.ImageCode;
import com.jflyfox.component.util.JFlyFoxUtils;
import com.jflyfox.jfinal.component.annotation.ControllerBind;
import com.jflyfox.system.log.SysLog;
import com.jflyfox.system.menu.SysMenu;
import com.jflyfox.system.user.SysUser;
import com.jflyfox.util.Config;
import com.jflyfox.util.StrUtils;
import com.jflyfox.util.encrypt.Md5Utils;

/**
 * adminController
 */
@ControllerBind(controllerKey = "/admin")
public class AdminController extends BaseProjectController {

	public static final String loginPage = "/pages/admin/login.html";
	public static final String homePage = "/admin/home";

	public void index() {
		if (getSessionUser() != null) {
			// 如果session存在，不再验证
			redirect(homePage);
		} else {
			render(loginPage);
		}

	}

	/**
	 * 登录
	 * 
	 * @author flyfox 2013-11-11
	 */
	public void login() {
		// 获取验证码
		String imageCode = getSessionAttr(ImageCode.class.getName());
		String checkCode = this.getPara("imageCode");

		if (StrUtils.isEmpty(imageCode) || !imageCode.equalsIgnoreCase(checkCode)) {
			setAttr("msg", "验证码错误！");
			render(loginPage);
			return;
		}

		// 初始化数据字典Map
		String username = getPara("username");
		String password = getPara("password");

		if (StrUtils.isEmpty(username)) {
			setAttr("msg", "用户名不能为空");
			render(loginPage);
			return;
		} else if (StrUtils.isEmpty(password)) {
			setAttr("msg", "密码不能为空");
			render(loginPage);
			return;
		}
		// String encryptPassword = JFlyFoxUtils.passwordEncrypt(password); //
		// 前台md5加密
		String encryptPassword = password;

		SysUser user = SysUser.dao.findFirstByWhere(" where username = ? " //
				+ " and usertype in ( " + JFlyFoxUtils.USER_TYPE_ADMIN + "," + JFlyFoxUtils.USER_TYPE_NORMAL + ")",
				username);
		if (user == null || user.getInt("userid") <= 0) {
			setAttr("msg", "认证失败，请您重新输入。");
			render(loginPage);
			return;
		}
		
		String md5Password = "";
		try {
			String userPassword = user.get("password");
			String decryptPassword = JFlyFoxUtils.passwordDecrypt(userPassword);
			md5Password = new Md5Utils().getMD5(decryptPassword).toLowerCase();
		} catch (Exception e) {
			log.error("认证异常", e);
			setAttr("msg", "认证异常，请您重新输入。");
			render(loginPage);
			return;
		}
		
		if (!md5Password.equals(encryptPassword)) {
			setAttr("msg", "认证错误，请您重新输入。");
			render(loginPage);
			return;
		}

		if (!(user.getInt("usertype") == 1 || user.getInt("usertype") == 2)) {
			setAttr("msg", "您没有登录权限，请您重新输入。");
			render(loginPage);
			return;
		}
		
		setSessionUser(user);

		// 第一个页面跳转
		String tmpMainPage = setFirstPage();

		if (tmpMainPage == null) {
			setAttr("msg", "没有权限，请联系管理员。");
			render(loginPage);
			return;
		}

		// 添加日志
		user.put("update_id", user.getUserid());
		user.put("update_time", getNow());
		saveLog(user, SysLog.SYSTEM_LOGIN);

		redirect(tmpMainPage);
	}

	/**
	 * 获取第一个跳转页面
	 * 
	 * 2015年10月30日 上午8:51:14 flyfox 330627517@qq.com
	 * 
	 * @param map
	 * @return
	 */
	protected String setFirstPage() {
		Map<Integer, List<SysMenu>> map = getSessionAttr("menu");
		if (map == null || map.size() <= 0) {
			return null;
		}

		String tmpMainPage = "";
		List<String> menuList = new ArrayList<String>();

		List<SysMenu> list = map.get(0);
		for (SysMenu menu : list) {
			if (StrUtils.isNotEmpty(menu.getStr("url"))) {
				menuList.add("/" + menu.getStr("url"));
			}
			List<SysMenu> list2 = map.get(menu.getInt("id"));
			if (list2 == null || list2.size() < 0) {
				continue;
			}

			for (SysMenu menu2 : list2) {
				if (StrUtils.isNotEmpty(menu2.getStr("url"))) {
					menuList.add("/" + menu2.getStr("url"));
				}
			}

		}

		if (menuList.size() <= 0) {
			return null;
		}

		if (menuList.contains(homePage)) {
			tmpMainPage = homePage;
		} else {
			tmpMainPage = menuList.get(0);
		}

		if (!tmpMainPage.startsWith("/")) {
			tmpMainPage = "/" + tmpMainPage;
		}
		return tmpMainPage;
	}
	
	/**
	 * 登出
	 * 
	 * @author flyfox 2013-11-13
	 */
	public void logout() {
		SysUser user = (SysUser) getSessionUser();
		if (user != null) {
			// 添加日志
			user.put("update_id", user.getUserid());
			user.put("update_time", getNow());
			saveLog(user, SysLog.SYSTEM_LOGOUT);
			// 删除session
			removeSessionUser();
		}

		setAttr("msg", "您已退出");
		render(loginPage);
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
