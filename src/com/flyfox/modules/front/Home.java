package com.flyfox.modules.front;

import java.io.IOException;

import javax.servlet.ServletException;

import com.flyfox.component.util.ImageCode;
import com.flyfox.jfinal.base.BaseController;
import com.flyfox.jfinal.component.annotation.ControllerBind;
import com.flyfox.modules.CommonController;
import com.flyfox.modules.front.interceptor.FrontInterceptor;
import com.jfinal.aop.Before;

/**
 * 
 * 2015年5月11日 下午4:11:02 flyfox 330627517@qq.com
 */
@ControllerBind(controllerKey = "/front")
public class Home extends BaseController {

	public static final String PATH = "/pages/front/home/";

	/**
	 * 登录
	 */
	@Before(FrontInterceptor.class)
	public void login() {
		setAttr("pre_page", getPrePage());
		render(CommonController.loginPage);
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
	 * 2015年2月28日 下午1:50:25 flyfox 330627517@qq.com
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
