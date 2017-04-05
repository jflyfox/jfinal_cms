package com.jflyfox.component.controller;

import java.io.File;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baidu.ueditor.define.ActionMap;
import com.baidu.ueditor.define.BaseState;
import com.baidu.ueditor.define.State;
import com.jfinal.log.Log;
import com.jflyfox.jfinal.base.BaseService;
import com.jflyfox.modules.admin.site.TbSite;
import com.jflyfox.system.file.model.FileUploadBean;
import com.jflyfox.system.file.service.FileUploadService;
import com.jflyfox.system.file.util.FileUploadUtils;
import com.jflyfox.util.StrUtils;

public class UeditorService extends BaseService {

	private static final Log log = Log.getLog(UeditorService.class);

	public String uploadHandle(int actionCode, String outJsonString, String contextPath, TbSite site, int userid) {
		switch (actionCode) {

		case ActionMap.CONFIG:
			break;

		case ActionMap.UPLOAD_IMAGE:
		case ActionMap.UPLOAD_SCRAWL:
		case ActionMap.UPLOAD_VIDEO:
		case ActionMap.UPLOAD_FILE:
			return uploadFileHandler(outJsonString, contextPath, site, userid);

		case ActionMap.CATCH_IMAGE:
			break;

		case ActionMap.LIST_IMAGE:
		case ActionMap.LIST_FILE:
			break;

		}

		// 没做任何处理
		return outJsonString;
	}

	/**
	 * 文件处理
	 * 
	 * 2017年4月5日 下午2:14:42 flyfox 369191470@qq.com
	 * 
	 * @param outJsonString
	 * @param site
	 * @param userid
	 * @return
	 */
	protected String uploadFileHandler(String outJsonString, String contextPath, TbSite site, int userid) {
		State state = null;
		String preContextPath = StrUtils.isEmpty(contextPath) ? "" : contextPath;
		try {
			JSONObject json = JSON.parseObject(outJsonString);
			if (!"SUCCESS".equals(json.getString("state"))) {
				return null;
			}
			// 创建对象
			String webRootPath = FileUploadUtils.getRootPath();
			String sitePath = FileUploadUtils.getSitePath(site); // 站点路径
			String url = FileUploadUtils.rebuild(json.getString("url"));
			url = url.replace(preContextPath, "");
			String newPath = FileUploadUtils.getBasePath() + sitePath + url.replace(FileUploadUtils.getBasePath(), "");
			newPath = newPath.replace(json.getString("title"), "");
			if (newPath.endsWith("/")) {
				newPath = newPath.substring(0, newPath.length() - 1);
			}
			// 原路径
			String oldPath = webRootPath + url;
			// 文件处理
			FileUploadBean bean = new FileUploadService().uploadHandle(newPath, new File(oldPath), userid);

			if (bean != null) {
				state = new BaseState(true);
				state.putInfo("size", json.getString("size"));
				state.putInfo("title", bean.getName());
				state.putInfo("url", preContextPath + bean.getPath());
				state.putInfo("type", json.getString("type"));
				state.putInfo("original", json.getString("original"));
			} else {
				log.error("UeditorService uploadHandle fail , bean is null.");
			}

		} catch (Exception e) {
			log.error("UeditorService uploadHandle fail.", e);
		}
		return state == null ? outJsonString : state.toJSONString();
	}
}
