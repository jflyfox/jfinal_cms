package com.flyfox.modules.front;

import com.alibaba.fastjson.JSONObject;
import com.flyfox.component.util.ImageCode;
import com.flyfox.component.util.JFlyFoxUtils;
import com.flyfox.jfinal.base.BaseController;
import com.flyfox.jfinal.component.annotation.ControllerBind;
import com.flyfox.jfinal.component.util.Attr;
import com.flyfox.modules.front.interceptor.FrontInterceptor;
import com.flyfox.system.user.SysUser;
import com.flyfox.system.user.UserCache;
import com.flyfox.util.StrUtils;
import com.jfinal.aop.Before;

@ControllerBind(controllerKey = "/front/regist")
public class RegistController extends BaseController {

	public static final String path = "/pages/front/regist/";
	
	/**
	 * 注册
	 */
	@Before(FrontInterceptor.class)
	public void index() {
		// 目录列表
		setAttr("folders_selected", "regist");

		String prePage = getPara("pre_page");
		if (StrUtils.isEmpty(prePage)) {
			prePage = getPrePage();
		}
		setAttr("pre_page", prePage);

		SysUser user = getSessionAttr(Attr.SESSION_NAME);
		// 如果已经登陆了~您就别注册啦
		if (user != null) {
			redirect(prePage);
		} else {
			renderAuto(path + "show_regist.html");
		}
	}

	/**
	 * 注册信息保存
	 */
	public void save() {
		JSONObject json = new JSONObject();
		json.put("status", 2);// 失败

		SysUser user = getModel(SysUser.class);
		String password = getPara("password");
		String password2 = getPara("password2");
		String key = user.getStr("email");

		// 获取验证码
		String imageCode = getSessionAttr(ImageCode.class.getName());
		String checkCode = this.getPara("imageCode");

		if (StrUtils.isEmpty(imageCode) || !imageCode.equalsIgnoreCase(checkCode)) {
			json.put("msg", "验证码错误！");
			renderJson(json.toJSONString());
			return;
		}

		if (StrUtils.isEmpty(key) || key.indexOf("@") < 0) {
			json.put("msg", "email格式错误！");
			renderJson(json.toJSONString());
			return;
		}

		if (user.getInt("userid") != null || StrUtils.isEmpty(user.getStr("realname")) //
				|| StrUtils.isEmpty(password) || StrUtils.isEmpty(password2) //
				|| password.length() < 6 || !password.equals(password2)) {
			json.put("msg", "提交数据错误！");
			renderJson(json.toJSONString());
			return;
		}

		SysUser newUser = SysUser.dao.findFirstByWhere("where username = ? ", key);
		if (newUser != null) {
			json.put("msg", "邮箱已存在，请重新输入");
			renderJson(json.toJSONString());
			return;
		}

		user.set("username", key);
		user.set("password", JFlyFoxUtils.passwordEncrypt(password));
		user.set("usertype", 3);
		user.set("departid", 2);
		user.put("create_time", getNow());
		user.put("create_id", 1);
		user.save();
		UserCache.init(); // 设置缓存
		setSessionUser(user); // 设置session
		json.put("status", 1);// 成功

		renderJson(json.toJSONString());
	}
}
