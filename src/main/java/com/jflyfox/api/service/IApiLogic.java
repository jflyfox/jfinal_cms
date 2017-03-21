package com.jflyfox.api.service;

import com.jflyfox.api.form.ApiResp;
import com.jflyfox.api.form.ApiForm;

/**
 * api实现接口
 * 
 * 2016年9月29日 上午11:45:08 flyfox 369191470@qq.com
 */
public interface IApiLogic extends IApiCommon {

	/**
	 * 返回栏目列表
	 * 
	 * 2016年9月29日 上午10:35:28 flyfox 369191470@qq.com
	 * 
	 * @param form
	 * @return ApiResp
	 */
	public ApiResp folders(ApiForm form);

	/**
	 * 返回文章列表
	 * 
	 * 2016年9月29日 上午10:36:00 flyfox 369191470@qq.com
	 * 
	 * @param form
	 * @return ApiResp
	 */
	public ApiResp pageArticleSite(ApiForm form);

	/**
	 * 返回文章列表
	 * 
	 * 2016年9月29日 上午10:36:06 flyfox 369191470@qq.com
	 * 
	 * @param form
	 * @return ApiResp
	 */
	public ApiResp pageArticle(ApiForm form);

	/**
	 * 返回对应文章
	 * 
	 * 2016年9月29日 上午10:36:11 flyfox 369191470@qq.com
	 * 
	 * @param form
	 * @return ApiResp
	 */
	public ApiResp article(ApiForm form);
}
