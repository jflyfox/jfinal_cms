package com.jflyfox.system.user;

import javax.servlet.http.HttpServletRequest;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.jfinal.log.Log;
import com.jflyfox.jfinal.base.BaseController;
import com.jflyfox.jfinal.component.util.Attr;
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

		String path_tmp = ai.getActionKey();

		if (path_tmp.startsWith("/")) {
			path_tmp = path_tmp.substring(1, path_tmp.length());
		}
		if (path_tmp.endsWith("/")) {
			path_tmp = path_tmp.substring(0, path_tmp.length() - 1);
		}

		// 每次访问获取session，没有可以从cookie取~
		SysUser user = null;
		if (controller instanceof BaseController) {
			user = (SysUser) ((BaseController) controller).getSessionUser();
		} else {
			user = controller.getSessionAttr(Attr.SESSION_NAME);
		}

		// if ((user == null || user.getUserid() <= 0) //
		// && JFinal.me().getConstants().getDevMode()) { // 开发模式
		// user =
		// SysUser.dao.findFirst("select * from sys_user where userid = 1");
		// controller.setSessionAttr(Attr.SESSION_NAME, user);
		// }

		if (isAuth(path_tmp)) {
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

		}

		ai.invoke();
	}

	/**
	 * 认证方法
	 * 
	 * 2015年2月27日 上午11:38:37 flyfox 330627517@qq.com
	 * 
	 * @param path_tmp
	 * @return
	 */
	protected boolean isAuth(String path_tmp) {
		return StrUtils.isNotEmpty(path_tmp) // 空是首页
				&& (path_tmp.startsWith("system/") // 系统管理
				|| path_tmp.startsWith("admin/") // 后台管理
				);
	}
}
