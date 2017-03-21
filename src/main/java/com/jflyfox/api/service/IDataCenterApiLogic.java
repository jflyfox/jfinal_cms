package com.jflyfox.api.service;

import com.jflyfox.api.form.ApiResp;
import com.jflyfox.api.form.ApiForm;

/**
 * 数据中心实现接口
 * 
 * 2016年9月29日 上午11:45:08 flyfox 369191470@qq.com
 */
public interface IDataCenterApiLogic extends IApiCommon {
	/**
	 * 获取数据标签信息
	 * @param form
	 * @return
	 */
	public ApiResp getDataLabel(ApiForm form);
	/**
	 * 获取数据信息
	 * @param form
	 * @return
	 */
	public ApiResp getDataInfo(ApiForm form);
	/**
	 * 批量导入数据信息
	 * @param form
	 * @return
	 */
	public ApiResp importDataInfo(ApiForm form);
	/**
	 * 更新数据信息
	 * @param form
	 * @return
	 */
	public ApiResp updateDataInfo(ApiForm form);
}
