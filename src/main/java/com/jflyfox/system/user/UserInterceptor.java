package com.jflyfox.system.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.jfinal.log.Log;
import com.jflyfox.component.base.BaseProjectController;
import com.jflyfox.component.util.JFlyFoxUtils;
import com.jflyfox.jfinal.component.util.Attr;
import com.jflyfox.system.menu.SysMenu;
import com.jflyfox.util.StrUtils;

/**
 * 用户认证拦截器
 * 
 * @author flyfox 2014-2-11
 */
public class UserInterceptor implements Interceptor {

	private static final Log log = Log.getLog(UserInterceptor.class);

	public void intercept(Invocation ai) {

		Controller controller = ai.getController();

		HttpServletRequest request = controller.getRequest();
		String referrer = request.getHeader("referer");
		String site = "http://" + request.getServerName();
		log.debug("####IP:" + request.getRemoteAddr() + "\t port:" + request.getRemotePort() + "\t 请求路径:"
				+ request.getRequestURI());
		if (referrer == null || !referrer.startsWith(site)) {
			log.warn("####非法的请求");
		}

		String tmpPath = ai.getActionKey();

		if (tmpPath.startsWith("/")) {
			tmpPath = tmpPath.substring(1, tmpPath.length());
		}
		if (tmpPath.endsWith("/")) {
			tmpPath = tmpPath.substring(0, tmpPath.length() - 1);
		}

		// 每次访问获取session，没有可以从cookie取~
		SysUser user = null;
		if (controller instanceof BaseProjectController) {
			user = (SysUser) ((BaseProjectController) controller).getSessionUser();
		} else {
			user = controller.getSessionAttr(Attr.SESSION_NAME);
		}

		// if ((user == null || user.getUserid() <= 0) //
		// && JFinal.me().getConstants().getDevMode()) { // 开发模式
		// user =
		// SysUser.dao.findFirst("select * from sys_user where userid = 1");
		// controller.setSessionAttr(Attr.SESSION_NAME, user);
		// }

		if (JFlyFoxUtils.isBack(tmpPath)) {
			if (user == null || user.getUserid() <= 0) {
				controller.redirect("/trans");
				return;
			}
			// TODO 这里展示控制第三方用户和前端用户不能登录后台
			int usertype = user.getInt("usertype");
			if (usertype == 4 // 第三方用户
					|| usertype == 3) { // 前端用户
				controller.redirect("/trans/auth");
				return;
			}

			// 判断url是否有权限
			if (!urlAuth(controller, tmpPath)) {
				controller.redirect("/trans/auth");
				return;
			}

		}

		ai.invoke();
	}

	/**
	 * 判断Url是否有权限
	 * 
	 * 2016年12月18日 上午12:12:51 flyfox 369191470@qq.com
	 * 
	 * @param controller
	 * @param tmpPath
	 * @return
	 */
	protected boolean urlAuth(Controller controller, String tmpPath) {
		List<SysMenu> list = controller.getSessionAttr("nomenu");
		// nomenuList 应该是size等于0，而不是空
		if (list == null) {
			return false;
		}
		
		for (SysMenu sysMenu : list) {
			String url = sysMenu.getStr("url");
			if (StrUtils.isEmpty(url)) {
				continue;
			}
			if (tmpPath.startsWith(url)) {
				return false;
			}
		}

		return true;
	}

}
