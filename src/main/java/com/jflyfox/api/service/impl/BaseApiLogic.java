package com.jflyfox.api.service.impl;

import com.jfinal.log.Log;
import com.jflyfox.api.constant.ApiConstant;
import com.jflyfox.api.form.ApiResp;
import com.jflyfox.api.form.BaseApiForm;
import com.jflyfox.api.service.IApiLogic;
import com.jflyfox.component.util.JFlyFoxUtils;
import com.jflyfox.system.user.SysUser;
import com.jflyfox.util.StrUtils;
import com.jflyfox.util.cache.Cache;
import com.jflyfox.util.cache.CacheManager;
import com.jflyfox.util.extend.RandomStrUtils;

/**
 * API基础类
 * 
 * 2016年11月15日 下午9:48:27 flyfox 369191470@qq.com
 */
public abstract class BaseApiLogic implements IApiLogic {

	private final static Log log = Log.getLog(BaseApiLogic.class);
	private final static String cacheName = "BaseApiLogic";
	private static Cache cache;

	public static void init() {
		log.info("####API Cache初始化......");
		if (cache == null) {
			cache = CacheManager.get(cacheName);
		}
	}

	public Object getCache(String key) {
		if (StrUtils.isEmpty(key)) {
			return null;
		}

		init();
		return cache.get(key);
	}

	public Object removeCache(String key) {
		if (StrUtils.isEmpty(key)) {
			return null;
		}

		init();
		return cache.remove(key);
	}

	public Cache addCache(String key, Object value) {
		if (StrUtils.isEmpty(key)) {
			return null;
		}

		init();
		cache.add(key, value);
		return cache;
	}

	@Override
	public ApiResp login(BaseApiForm form) {
		// 初始化数据字典Map
		String username = form.get("username");
		String password = form.get("password");

		if (StrUtils.isEmpty(username)) {
			return new ApiResp().setCode(-1).setMsg("用户名不能为空");
		} else if (StrUtils.isEmpty(password)) {
			return new ApiResp().setCode(-2).setMsg("密码不能为空");
		}
		String encryptPassword = JFlyFoxUtils.passwordEncrypt(password); // 加密
		SysUser user = SysUser.dao.findFirstByWhere(" where username = ? and password = ? ", username, encryptPassword);
		if (user == null || user.getInt("userid") <= 0) {
			return new ApiResp().setCode(-3).setMsg("认证失败，请您重新输入。");
		}
		// 验证成功加入缓存，返回key
		String key = RandomStrUtils.randomAlphabetic(4);
		addCache(username, key);

		return new ApiResp().addData("key", key);
	}

	@Override
	public ApiResp valid(BaseApiForm form) {
		// 不需要验证的方法
		if (notValid(form)) {
			return new ApiResp();
		}

		String checkSum = form.getCheckSum();
		String apiUser = form.getApiUser();

		if (StrUtils.isEmpty(apiUser)) {
			return new ApiResp().setCode(ApiConstant.CODE_LOGIN_VALID_ERROR).setMsg(ApiConstant.MSG_LOGIN_VALID_ERROR);
		}

		Object value = getCache(apiUser);
		if (value != null && value.toString().equals(checkSum)) {
			return new ApiResp();
		}
		return new ApiResp().setCode(ApiConstant.CODE_LOGIN_VALID_ERROR).setMsg(ApiConstant.MSG_LOGIN_VALID_ERROR);
	}

	/**
	 * 不需要验证的方法
	 * 
	 * 2016年11月15日 下午11:03:01 flyfox 369191470@qq.com
	 * 
	 * @param form
	 * @return
	 */
	protected boolean notValid(BaseApiForm form) {
		return form.getMethod().equals("login");
	}

	@Override
	public ApiResp logout(BaseApiForm form) {
		String apiUser = form.getApiUser();
		// 如果验证失败，直接返回错误
		ApiResp validResp = valid(form);
		if (validResp.getCode() != ApiConstant.CODE_SUCCESS) {
			return validResp;
		}

		removeCache(apiUser);
		return new ApiResp().addData("r", "ok");
	}

	@Override
	public ApiResp config(BaseApiForm form) {
		return null;
	}

}
