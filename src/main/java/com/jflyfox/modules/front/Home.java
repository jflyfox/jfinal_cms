package com.jflyfox.modules.front;

import java.io.IOException;

import javax.servlet.ServletException;

import com.jfinal.aop.Before;
import com.jflyfox.component.base.BaseProjectController;
import com.jflyfox.component.util.ImageCode;
import com.jflyfox.jfinal.component.annotation.ControllerBind;
import com.jflyfox.modules.CommonController;
import com.jflyfox.modules.front.interceptor.FrontInterceptor;

/**
 * 
 * 2015年5月11日 下午4:11:02 flyfox 369191470@qq.com
 */
@ControllerBind(controllerKey = "/front")
public class Home extends BaseProjectController {

	public static final String PATH = "/home/";

	/**
	 * 登录
	 */
	@Before(FrontInterceptor.class)
	public void login() {
		setAttr("pre_page", getPrePage());
		renderAuto(CommonController.loginPage);
	}

	/**
	 * 登出
	 */
	public void logout() {
		removeSessionUser();
		redirect(CommonController.firstPage);
	}

	/**
	 * 生成注册码
	 * 
	 * 2015年2月28日 下午1:50:25 flyfox 369191470@qq.com
	 */
	public void image_code() {
		try {
			new ImageCode().doGet(getRequest(), getResponse());
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		renderNull();
	}

}
