package com.jflyfox.modules.filemanager;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.log.Log;
import com.jflyfox.component.base.BaseProjectController;
import com.jflyfox.jfinal.component.annotation.ControllerBind;

/**
 * 文件管理
 * 
 * 2016年2月26日 下午2:33:03 flyfox 369191470@qq.com
 */
@ControllerBind(controllerKey = "/admin/filemanager")
public class FileManagerController extends BaseProjectController {

	private final static Log log = Log.getLog(FileManagerController.class);

	private static final String path = "/pages/admin/filemanager/";

	public void list() {
		render(path + "index.html");
	}

	/**
	 * 跳转到操作页面
	 * 
	 * 2015年3月16日 下午5:33:55 flyfox 369191470@qq.com
	 */
	public void index() {
		HttpServletRequest request = getRequest();

		try {
			request.setCharacterEncoding("UTF-8");
			getResponse().setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		FileManager fm = new FileManager(getRequest());

		JSONObject responseData = null;

		String mode = "";
		String path = "";
		boolean needPath = false;
		boolean putTextarea = false;
		if (!auth()) {
			fm.error(fm.lang("AUTHORIZATION_REQUIRED"));
		} else {
			String contextPath = request.getContextPath();
			// 设置contextPath
			fm.setGetVar("contextPath", contextPath);

			mode = request.getParameter("mode");
			path = request.getParameter("path");

			if (path != null) {
				try {
					if (request.getMethod().equals("GET"))
						path = new String(path.getBytes("ISO8859-1"), "UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}

				needPath = fm.setGetVar("path", path);
			}

			if (request.getMethod().equals("GET")) {
				if (mode != null && mode != "") {
					if (mode.equals("getinfo")) {
						if (needPath) {
							responseData = fm.getInfo();
						}
					} else if (mode.equals("getfolder")) {
						if (needPath) {
							responseData = fm.getFolder();
						}
					} else if (mode.equals("rename")) {
						String oldFile = request.getParameter("old");
						String newFile = request.getParameter("new");
						try {
							oldFile = new String(oldFile.getBytes("ISO8859-1"), "UTF-8");
							newFile = new String(newFile.getBytes("ISO8859-1"), "UTF-8");
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}

						if (fm.setGetVar("old", oldFile) && fm.setGetVar("new", newFile)) {
							responseData = fm.rename();
						}
					} else if (mode.equals("delete")) {
						if (needPath) {
							responseData = fm.delete();
						}
					} else if (mode.equals("addfolder")) {
						String name = request.getParameter("name");
						try {
							name = new String(name.getBytes("ISO8859-1"), "UTF-8");
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}

						if (needPath && fm.setGetVar("name", name)) {
							responseData = fm.addFolder();
						}
					} else if (mode.equals("download")) {
						if (needPath) {
							fm.download(getResponse());
						}
					} else if (mode.equals("preview")) {
						if (needPath) {
							fm.preview(getResponse());
						}
					} else if (mode.equals("editfile")) {
						if (needPath) {
							responseData = fm.editFile();
						}
					} else {
						fm.error(fm.lang("MODE_ERROR"));
					}
				}
			} else if (request.getMethod().equals("POST")) {

				if (mode == null) {
					mode = "upload";
					responseData = fm.add();
					putTextarea = true;
				} else if (mode.equals("savefile")) {
					if (needPath && fm.setGetContent("content", request.getParameter("content"))) {
						responseData = fm.saveFile();
					}
				}

			}
		}
		if (responseData == null) {
			responseData = fm.getError();
		}
		if (responseData != null) {
			String responseStr = responseData.toString();
			if (putTextarea)
				responseStr = "<textarea>" + responseStr + "</textarea>";
			log.info("mode:" + mode + ",response:" + responseStr);
			renderText(responseStr);
		} else {
			renderNull();
		}

	}

	/**
	 * 管理员才可以
	 * 
	 * 2016年3月6日 上午9:39:10
	 * flyfox 369191470@qq.com
	 * @return
	 */
	public boolean auth() {
		return getSessionUser() != null && getSessionUser().getInt("usertype") == 1;
	}

}
