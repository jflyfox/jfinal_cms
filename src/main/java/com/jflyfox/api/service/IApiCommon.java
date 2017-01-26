package com.jflyfox.api.service;

import com.jflyfox.api.form.ApiResp;
import com.jflyfox.api.form.BaseApiForm;

/**
 * API通用接口
 * 
 * 2016年11月15日 下午9:43:49 flyfox 369191470@qq.com
 */
public interface IApiCommon {

	/**
	 * 登陆接口
	 * 
	 * 2016年10月1日 下午9:20:12 flyfox 369191470@qq.com
	 * 
	 * @param form
	 * @return
	 */
	public ApiResp login(BaseApiForm form);

	/**
	 * 登陆后，验证接口
	 * 
	 * 2016年10月1日 下午9:20:12 flyfox 369191470@qq.com
	 * 
	 * @param form
	 * @return
	 */
	public ApiResp valid(BaseApiForm form);

	/**
	 * 登出接口
	 * 
	 * 2016年10月1日 下午9:20:12 flyfox 369191470@qq.com
	 * 
	 * @param form
	 * @return
	 */
	public ApiResp logout(BaseApiForm form);

	/**
	 * 获取配置信息
	 * 
	 * 2016年10月1日 下午9:20:12 flyfox 369191470@qq.com
	 * 
	 * @param form
	 * @return
	 */
	public ApiResp config(BaseApiForm form);

}
