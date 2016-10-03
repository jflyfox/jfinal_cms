package com.jflyfox.api.service;

import com.jflyfox.api.form.ApiResp;
import com.jflyfox.api.form.BaseApiForm;

/**
 * api实现接口
 * 
 * 2016年9月29日 上午11:45:08 flyfox 369191470@qq.com
 */
public interface IApiLogic {

	/**
	 * 获取配置信息
	 * 
	 * 2016年10月1日 下午9:20:12 flyfox 369191470@qq.com
	 * 
	 * @param form
	 * @return
	 */
	public ApiResp config(BaseApiForm form);

	/**
	 * 返回栏目列表
	 * 
	 * 2016年9月29日 上午10:35:28 flyfox 369191470@qq.com
	 * 
	 * @param form
	 * @return ApiResp
	 */
	public ApiResp folders(BaseApiForm form);

	/**
	 * 返回文章列表
	 * 
	 * 2016年9月29日 上午10:36:00 flyfox 369191470@qq.com
	 * 
	 * @param form
	 * @return ApiResp
	 */
	public ApiResp pageArticleSite(BaseApiForm form);

	/**
	 * 返回文章列表
	 * 
	 * 2016年9月29日 上午10:36:06 flyfox 369191470@qq.com
	 * 
	 * @param form
	 * @return ApiResp
	 */
	public ApiResp pageArticle(BaseApiForm form);

	/**
	 * 返回对应文章
	 * 
	 * 2016年9月29日 上午10:36:11 flyfox 369191470@qq.com
	 * 
	 * @param form
	 * @return ApiResp
	 */
	public ApiResp article(BaseApiForm form);
}
