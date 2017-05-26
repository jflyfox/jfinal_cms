/**
 * Copyright 2015-2025 FLY的狐狸(email:jflyfox@sina.com qq:369191470).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

package com.jflyfox.component.controller.base;

import com.jflyfox.component.base.BaseProjectController;
import com.jflyfox.util.Config;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.*;
import java.util.*;

/**
 * Ueditor 封装
 * 
 * @author flyfox
 * 2014-1-20
 */
public class BaseUmeditor extends BaseProjectController {

	/**
	 * 涂鸦
	 * 
	 * @author flyfox 2014-1-20
	 */
	public void scrawlup() {
		String[] fileType = { ".gif", ".png", ".jpg", ".jpeg", ".bmp" };
		String param = getPara("action");
		Uploader up = new Uploader(getRequest());
		String path = "upload";
		up.setSavePath(path);
		up.setAllowFiles(fileType);
		up.setMaxSize(10000); // 单位KB

		if (param != null && param.equals("tmpImg")) {
			try {
				up.upload();
			} catch (Exception e) {
				e.printStackTrace();
			}
			renderJavascript("parent.ue_callback('" + up.getUrl() + "','" + up.getState() + "')");
		} else {
			up.uploadBase64("content");
			renderText("{'url':'" + up.getUrl() + "',state:'" + up.getState() + "'}");
		}

	}

	/**
	 * 图片上传
	 * 
	 * @author flyfox 2014-1-20
	 */
	public void imageup() {
		String[] fileType = { ".gif", ".png", ".jpg", ".jpeg", ".bmp" };

		List<String> savePath = Arrays.asList(Config.getStr("savePath").split(","));

		// 获取存储目录结构
		if (getPara("fetch") != null) {
			// 构造json数据
			Iterator<String> iterator = savePath.iterator();
			String dirs = "[";
			while (iterator.hasNext()) {
				dirs += "'" + iterator.next() + "'";
				if (iterator.hasNext()) {
					dirs += ",";
				}

			}
			dirs += "]";
			renderJavascript("updateSavePath( " + dirs + " );");
			return;
		}

		Uploader up = new Uploader(getRequest());
		// 获取前端提交的path路径
		String dir = getPara("dir");
		// 普通请求中拿不到参数， 则从上传表单中拿
		if (dir == null) {
			dir = up.getParameter("dir");
		}

		if (dir == null || "".equals(dir)) {
			// 赋予默认值
			dir = savePath.get(0);
			// 安全验证
		} else if (!savePath.contains(dir)) {
			renderText("{'state':'\\u975e\\u6cd5\\u4e0a\\u4f20\\u76ee\\u5f55'}");
			return;

		}

		try {
			up.setSavePath(dir);
			up.setAllowFiles(fileType);
			up.setMaxSize(500 * 1024); // 单位KB
			up.upload();
		} catch (Exception e) {
			e.printStackTrace();
		}
		renderText("{'original':'" + up.getOriginalName() + "','url':'" + up.getUrl() + "','title':'" + up.getTitle()
				+ "','state':'" + up.getState() + "'}");
	}

	/**
	 * 图片管理
	 * 
	 * @author flyfox 2014-1-20
	 */
	public void imagemanager() {
		// 仅做示例用，请自行修改
		String path = "upload";
		String imgStr = "";
		String realpath = getRealPath(getRequest(), path) + "/" + path;
		List<File> files = getFiles(realpath, new ArrayList<File>());
		for (File file : files) {
			imgStr += file.getPath().replace(getRealPath(getRequest(), path), "") + "ue_separate_ue";
		}
		if (imgStr != "") {
			imgStr = imgStr.substring(0, imgStr.lastIndexOf("ue_separate_ue")).replace(File.separator, "/").trim();
		}
		renderText(imgStr);
	}

	protected List<File> getFiles(String realpath, List<File> files) {
		File realFile = new File(realpath);
		if (realFile.isDirectory()) {
			File[] subfiles = realFile.listFiles();
			for (File file : subfiles) {
				if (file.isDirectory()) {
					getFiles(file.getAbsolutePath(), files);
				} else {
					if (!getFileTypeByimageManager(file.getName()).equals("")) {
						files.add(file);
					}
				}
			}
		}
		return files;
	}

	protected String getRealPath(HttpServletRequest request, String path) {
		ServletContext application = request.getSession().getServletContext();
		String str = application.getRealPath(request.getServletPath());
		return new File(str).getParent();
	}

	protected String getFileTypeByimageManager(String fileName) {
		String[] fileType = { ".gif", ".png", ".jpg", ".jpeg", ".bmp" };
		Iterator<String> type = Arrays.asList(fileType).iterator();
		while (type.hasNext()) {
			String t = type.next();
			if (fileName.toLowerCase().endsWith(t)) {
				return t;
			}
		}
		return "";
	}

	/**
	 * 附件上传
	 * 
	 * @author flyfox 2014-1-20
	 */
	public void fileup() {
		String[] fileType = { ".rar", ".doc", ".docx", ".zip", ".pdf", ".txt", ".swf", ".wmv", ".avi", ".rm", ".rmvb",
				".mpeg", ".mpg", ".ogg", ".mov", ".wmv", ".mp4" }; // 允许的文件类型
		Uploader up = new Uploader(getRequest());

		up.setSavePath("upload"); // 保存路径
		up.setAllowFiles(fileType);
		up.setMaxSize(500 * 1024); // 允许的文件最大尺寸，单位KB
		try {
			up.upload();
		} catch (Exception e) {
			e.printStackTrace();
		}
		renderText("{'url':'" + up.getUrl() + "','fileType':'" + up.getType() + "','state':'" + up.getState()
				+ "','original':'" + up.getOriginalName() + "'}");

	}

	/**
	 * 视频上传
	 * 
	 * @author flyfox 2014-1-20
	 */
	public void getMovie() {
		StringBuffer readOneLineBuff = new StringBuffer();
		String content = "";
		String searchkey = getPara("searchKey");
		String videotype = getPara("videoType");
		try {
			searchkey = URLEncoder.encode(searchkey, "utf-8");
			URL url = new URL("http://api.tudou.com/v3/gw?method=item.search&appKey=myKey&format=json&kw=" + searchkey
					+ "&pageNo=1&pageSize=20&channelId=" + videotype + "&inDays=7&media=v&sort=s");
			URLConnection conn = url.openConnection();
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
			String line = "";
			while ((line = reader.readLine()) != null) {
				readOneLineBuff.append(line);
			}
			content = readOneLineBuff.toString();
			reader.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		renderText(content);
	}

	/**
	 * 网络图片
	 * 
	 * @author flyfox 2014-1-20
	 */
	public void getremoteimage() {
		String url = getPara("upfile");
		String state = "远程图片抓取成功！";

		String filePath = "upload";
		String[] arr = url.split("ue_separate_ue");
		String[] outSrc = new String[arr.length];
		for (int i = 0; i < arr.length; i++) {
			// 保存文件路径
			@SuppressWarnings("deprecation")
			String str = getRequest().getRealPath(getRequest().getServletPath());
			File f = new File(str);
			String savePath = f.getParent() + "/" + filePath;
			// 格式验证
			String type = getFileTypeByRemoteImage(arr[i]);
			if (type.equals("")) {
				state = "图片类型不正确！";
				continue;
			}
			String saveName = Long.toString(new Date().getTime()) + type;
			// 大小验证
			HttpURLConnection.setFollowRedirects(false);
			HttpURLConnection conn = null;
			try {
				conn = (HttpURLConnection) new URL(arr[i]).openConnection();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (conn.getContentType().indexOf("image") == -1) {
				state = "请求地址头不正确";
				continue;
			}
			try {
				if (conn.getResponseCode() != 200) {
					state = "请求地址不存在！";
					continue;
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			File dir = new File(savePath);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			File savetoFile = new File(savePath + "/" + saveName);
			outSrc[i] = filePath + "/" + saveName;
			try {
				InputStream is = conn.getInputStream();
				OutputStream os = new FileOutputStream(savetoFile);
				int b;
				while ((b = is.read()) != -1) {
					os.write(b);
				}
				os.close();
				is.close();
				// 这里处理 inputStream
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println("页面无法访问");
			}
		}
		String outstr = "";
		for (int i = 0; i < outSrc.length; i++) {
			outstr += outSrc[i] + "ue_separate_ue";
		}
		outstr = outstr.substring(0, outstr.lastIndexOf("ue_separate_ue"));
		renderText("{'url':'" + outstr + "','tip':'" + state + "','srcUrl':'" + url + "'}");

	}

	protected String getFileTypeByRemoteImage(String fileName) {
		String[] fileType = { ".gif", ".png", ".jpg", ".jpeg", ".bmp" };
		Iterator<String> type = Arrays.asList(fileType).iterator();
		while (type.hasNext()) {
			String t = type.next();
			if (fileName.endsWith(t)) {
				return t;
			}
		}
		return "";
	}
}
