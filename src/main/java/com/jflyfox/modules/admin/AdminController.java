package com.jflyfox.modules.admin;

import com.jflyfox.component.base.BaseProjectController;
import com.jflyfox.component.util.ImageCode;
import com.jflyfox.component.util.JFlyFoxUtils;
import com.jflyfox.jfinal.component.annotation.ControllerBind;
import com.jflyfox.system.log.SysLog;
import com.jflyfox.system.user.SysUser;
import com.jflyfox.util.Config;
import com.jflyfox.util.StrUtils;

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
		String encryptPassword = JFlyFoxUtils.passwordEncrypt(password); // 加密
		SysUser user = SysUser.dao.findFirstByWhere(" where username = ? and password = ? " //
				+ " and usertype in ( " + JFlyFoxUtils.USER_TYPE_ADMIN + "," + JFlyFoxUtils.USER_TYPE_NORMAL + ")",
				username, encryptPassword);
		if (user == null || user.getInt("userid") <= 0) {
			setAttr("msg", "认证失败，请您重新输入。");
			render(loginPage);
			return;
		} else {
			setSessionUser(user);
		}

		// 添加日志
		user.put("update_id", user.getUserid());
		user.put("update_time", getNow());
		saveLog(user, SysLog.SYSTEM_LOGIN);

		redirect(homePage);
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
