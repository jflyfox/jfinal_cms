package com.jflyfox.api.form;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.log.Log;
import com.jflyfox.jfinal.base.Paginator;
import com.jflyfox.util.NumberUtils;

/**
 * api 基础form
 * 
 * 2016年9月29日 上午10:29:27 flyfox 369191470@qq.com
 */
public class BaseApiForm {

	private final static Log log = Log.getLog(BaseApiForm.class);

	private Integer pageNo; // 页数
	private Integer pageSize; // 页码
	private String method; // 方法名
	private String version; // 版本
	private String apiUser; // 调用用户
	private String checkSum; // 校验和
	private String p; // 参数

	/**
	 * 获取分页信息
	 * 
	 * 2016年10月3日 下午9:03:02 flyfox 369191470@qq.com
	 * 
	 * @return
	 */
	public Paginator getPaginator() {
		Paginator paginator = new Paginator();
		if (this.pageNo != null && this.pageNo.intValue() > 0)
			paginator.setPageNo(this.pageNo.intValue());
		if (this.pageSize != null && this.pageSize.intValue() > 0)
			paginator.setPageSize(this.pageSize.intValue());
		return paginator;
	}

	/**
	 * 获取参数
	 * 
	 * 2016年10月3日 下午9:02:45 flyfox 369191470@qq.com
	 * 
	 * @param key
	 * @return
	 */
	public double getDouble(String key) {
		return NumberUtils.parseDbl(get(key));
	}

	/**
	 * 获取参数
	 * 
	 * 2016年10月3日 下午9:02:45 flyfox 369191470@qq.com
	 * 
	 * @param key
	 * @return
	 */
	public long getLong(String key) {
		return NumberUtils.parseLong(get(key));
	}

	/**
	 * 获取参数
	 * 
	 * 2016年10月3日 下午9:02:45 flyfox 369191470@qq.com
	 * 
	 * @param key
	 * @return
	 */
	public int getInt(String key) {
		return NumberUtils.parseInt(get(key));
	}

	/**
	 * 获取参数
	 * 
	 * 2016年10月3日 下午9:02:45 flyfox 369191470@qq.com
	 * 
	 * @param key
	 * @return
	 */
	public String get(String key) {
		return getParams().getString(key);
	}

	/**
	 * 获取参数
	 * 
	 * 2016年10月3日 下午9:02:45 flyfox 369191470@qq.com
	 * 
	 * @param key
	 * @return
	 */
	public JSONObject getJSONObject(String key) {
		return getParams().getJSONObject(key);
	}

	/**
	 * 获取参数
	 * 
	 * 2016年10月3日 下午9:02:45 flyfox 369191470@qq.com
	 * 
	 * @param key
	 * @return
	 */
	public JSONArray getJSONArray(String key) {
		return getParams().getJSONArray(key);
	}

	/**
	 * 获取P参数
	 * 
	 * 2016年10月3日 下午9:02:45 flyfox 369191470@qq.com
	 * 
	 * @param key
	 * @return
	 */
	private JSONObject getParams() {
		JSONObject json = null;
		try {
			json = JSON.parseObject(p);
		} catch (Exception e) {
			log.error("apiform json parse fail:" + p);
			return new JSONObject();
		}

		return json;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getApiUser() {
		return apiUser;
	}

	public void setApiUser(String apiUser) {
		this.apiUser = apiUser;
	}

	public String getCheckSum() {
		return checkSum;
	}

	public void setCheckSum(String checkSum) {
		this.checkSum = checkSum;
	}

	public String getP() {
		return p;
	}

	public void setP(String p) {
		this.p = p;
	}

	@Override
	public String toString() {
		return "[page=" + this.pageNo + "/" + this.pageSize + "]" //
				+ "[method=" + this.method + "]" //
				+ "[version=" + this.version + "]" //
				+ "[apiUser=" + this.apiUser + "]" //
				+ "[checkSum=" + this.checkSum + "]" //
				+ "[p=" + this.p + "]";
	}

}
