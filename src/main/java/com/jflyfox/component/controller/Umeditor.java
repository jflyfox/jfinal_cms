package com.jflyfox.component.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.jflyfox.jfinal.component.annotation.ControllerBind;
import com.jflyfox.jfinal.component.ueditor.Uploader;
import com.jflyfox.jfinal.component.umeditor.UmeditorController;
import com.jflyfox.modules.admin.error.TbError;
import com.jflyfox.system.user.SysUser;
import com.jflyfox.util.Config;

@ControllerBind(controllerKey = "umeditor")
public class Umeditor extends UmeditorController {

	// 文件大小常量, 单位kb
	private static final int MAX_SIZE = Config.getToInt("person.MaxSize");
	// 文件类型限制
	private static final String[] fileType = { ".gif", ".png", ".jpg", ".jpeg", ".bmp" };
	// 文件上传路径umeditor下
	private static final String DIR = Config.getStr("person.SavePath");

	private static final Map<Integer, PersonFileLimit> map = new HashMap<Integer, PersonFileLimit>();

	/**
	 * 图片上传
	 * 
	 * @see 博文上传，必须严格限制
	 * 
	 */
	public void personimageup() {
		// 文件限制处理
		SysUser user = (SysUser) getSessionUser();
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
			Uploader up = new Uploader(getRequest());
			// 获取前端提交的path路径
			try {
				// 知道哪个用户传的便于垃圾数据清理
				up.setPreFileName(user.getUserid() + "_");
				up.setSavePath(DIR);
				up.setAllowFiles(fileType);
				up.setMaxSize(MAX_SIZE); // 单位KB
				up.upload();
			} catch (Exception e) {
				e.printStackTrace();
			}
			result = "{\"name\":\"" + up.getFileName() + "\", \"originalName\": \"" + up.getOriginalName()
					+ "\", \"size\": \"" + up.getSize() + "\", \"state\": \"" + up.getState() + "\", \"type\": \""
					+ up.getType() + "\", \"url\": \"" + up.getUrl() + "\"}";
			result = result.replaceAll("\\\\", "\\\\");
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
