package com.flyfox.component.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.flyfox.jfinal.component.annotation.ControllerBind;
import com.flyfox.jfinal.component.ueditor.Uploader;
import com.flyfox.jfinal.component.umeditor.UmeditorController;
import com.flyfox.jfinal.component.util.Attr;
import com.flyfox.modules.error.TbError;
import com.flyfox.system.user.SysUser;
import com.flyfox.util.Config;

@ControllerBind(controllerKey = "umeditor")
public class Umeditor extends UmeditorController {

	private static final Map<Integer, PersonFileLimit> map = new HashMap<Integer, PersonFileLimit>();

	/**
	 * 图片上传
	 * 
	 * @see 博文上传，必须严格限制
	 * 
	 */
	public void personimageup() {
		// 文件限制处理
		SysUser user = getSessionAttr(Attr.SESSION_NAME);
		PersonFileLimit fileLimit = map.get(user.getUserid());
		if (fileLimit == null) {
			fileLimit = new PersonFileLimit();
			map.put(user.getUserid(), fileLimit);
		}
		fileLimit.add();

		String result = "";
		if (!fileLimit.isLegal()) {
			if (!fileLimit.isInsert()) {
				TbError error = new TbError();
				error.setUserid(user.getUserid());
				error.setType(TbError.FILE_UPLOAD_MORE);
				error.setIp(getIpAddr(getRequest()));
				error.setContent("上传数量：" //
						+ fileLimit.getNowHour() + "(" + fileLimit.getNowHourCount().get() + ")-" //
						+ fileLimit.getNowDay() + "(" + fileLimit.getNowDayCount().get() + ")");
				error.put("create_id", getSessionUser().getUserID());
				error.put("create_time", getNow());
				error.save();

				fileLimit.setInsert(true);
			}

			String state = "上传文件过快或者过多,如有问题请联系管理员~！~";
			result = "{\"state\": \"" + state + "\"}";
		} else {
			String[] fileType = { ".gif", ".png", ".jpg", ".jpeg", ".bmp" };

			Uploader up = new Uploader(getRequest());
			try {
				up.setSavePath(Config.getStr("savePath"));
				up.setAllowFiles(fileType);
				up.setMaxSize(800); // 单位KB
				up.upload();
			} catch (Exception e) {
				e.printStackTrace();
			}
			result = "{\"name\":\"" + up.getFileName() + "\", \"originalName\": \"" + up.getOriginalName()
					+ "\", \"size\": \"" + up.getSize() + "\", \"state\": \"" + up.getState() + "\", \"type\": \""
					+ up.getType() + "\", \"url\": \"" + up.getUrl() + "\"}";
			result = result.replaceAll( "\\\\", "\\\\" );
		}
		renderHtml(result);
	}

	public String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
}
