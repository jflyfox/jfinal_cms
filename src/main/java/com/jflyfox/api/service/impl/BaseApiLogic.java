package com.jflyfox.api.service.impl;

import java.util.TreeMap;

import com.jflyfox.api.constant.ApiConstant;
import com.jflyfox.api.form.ApiForm;
import com.jflyfox.api.form.ApiResp;
import com.jflyfox.api.service.ApiCache;
import com.jflyfox.api.service.IApiLogic;
import com.jflyfox.api.util.ApiUtils;
import com.jflyfox.component.util.JFlyFoxUtils;
import com.jflyfox.system.user.SysUser;
import com.jflyfox.util.StrUtils;
import com.jflyfox.util.encrypt.Md5Utils;
import com.jflyfox.util.extend.RandomStrUtils;

/**
 * API基础类
 * 
 * 2016年11月15日 下午9:48:27 flyfox 369191470@qq.com
 */
public abstract class BaseApiLogic implements IApiLogic {

	@Override
	public ApiResp login(ApiForm form) {
		// 初始化数据字典Map
		String username = form.get("username");
		String password = form.get("password");
		try {
			if (StrUtils.isEmpty(username)) {
				return new ApiResp(form).setCode(-1001).setMsg("用户名不能为空");
			} else if (StrUtils.isEmpty(password)) {
				return new ApiResp(form).setCode(-1002).setMsg("密码不能为空");
			}
			String sql = " where username = ? and usertype = " + JFlyFoxUtils.USER_TYPE_API;
			SysUser user = SysUser.dao.findFirstByWhere(sql, username);

			if (user == null || user.getInt("userid") <= 0) {
				return new ApiResp(form).setCode(-1003).setMsg("认证失败");
			}

			if (!(user.getInt("usertype") == JFlyFoxUtils.USER_TYPE_API)) {
				return new ApiResp(form).setCode(-1004).setMsg("您没有登录权限");
			}

			if (!(user.getInt("state") == JFlyFoxUtils.USER_STATE_NORMAL)) {
				return new ApiResp(form).setCode(-1005).setMsg("您的帐号没有API权限");
			}

			String encryptPassword = password; // 加密
			String md5Password = "";
			String userPassword = user.get("password");
			md5Password = new Md5Utils().getMD5(userPassword).toLowerCase();

			if (!md5Password.equals(encryptPassword)) {
				return new ApiResp(form).setCode(-1006).setMsg("认证错误");
			}
		} catch (Exception e) {
			return new ApiResp(form).setCode(-1099).setMsg("认证异常");
		}
		
		// 验证成功加入缓存，返回key
		String key = RandomStrUtils.randomAlphabetic(6);
		ApiCache.addCache(username, key);

		return new ApiResp(form).addData("key", key);
	}

	@Override
	public ApiResp valid(ApiForm form) {
		// 不需要验证的方法
		if (notValid(form)) {
			return new ApiResp(form);
		}

		String checkSum = form.getCheckSum();
		String apiUser = form.getApiUser();

		if (StrUtils.isEmpty(apiUser)) {
			return new ApiResp(form).setCode(ApiConstant.CODE_LOGIN_VALID_ERROR).setMsg(ApiConstant.MSG_LOGIN_VALID_ERROR);
		}

		Object value = ApiCache.getCache(apiUser);
		if (value == null) {
			return new ApiResp(form).setCode(ApiConstant.CODE_LOGIN_VALID_ERROR).setMsg(ApiConstant.MSG_LOGIN_VALID_ERROR);
		}
		
		TreeMap<String, String> tree = form.getTreeMap();
		String serverSign = ApiUtils.getSign(tree, String.valueOf(value));
		if (!serverSign.equals(checkSum)) {
			return new ApiResp(form).setCode(ApiConstant.CODE_CHECKSUM_VALID_ERROR).setMsg(ApiConstant.MSG_CHECKSUM_VALID_ERROR);
		}
		
		return new ApiResp(form);
	}
	
	/**
	 * 不需要验证的方法
	 * 
	 * 2016年11月15日 下午11:03:01 flyfox 369191470@qq.com
	 * 
	 * @param form
	 * @return
	 */
	protected boolean notValid(ApiForm form) {
		return form.getMethod().equals("login");
	}

	@Override
	public ApiResp logout(ApiForm form) {
		String apiUser = form.getApiUser();
		// 如果验证失败，直接返回错误
		ApiResp validResp = valid(form);
		if (validResp.getCode() != ApiConstant.CODE_SUCCESS) {
			return validResp;
		}

		ApiCache.removeCache(apiUser);
		return new ApiResp(form).addData("r", "ok");
	}

	@Override
	public ApiResp config(ApiForm form) {
		return null;
	}

}
