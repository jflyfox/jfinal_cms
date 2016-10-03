package com.jflyfox.component.util;

import java.io.File;
import java.security.SecureRandom;

import com.jfinal.kit.PathKit;
import com.jfinal.upload.UploadFile;
import com.jflyfox.modules.admin.site.TbSite;
import com.jflyfox.util.DateUtils;
import com.jflyfox.util.StrUtils;

public class JFlyfoxUpload {

	public static final int UPLOAD_MAX = 10 * 1024 * 1024;

	/**
	 * 基础目录
	 */
	public static final String BASE_PATH = PathKit.getWebRootPath() + File.separator;

	/**
	 * 基础JFLYFOX目录
	 */
	private static final String JFLYFOX_PATH = "jflyfox" + File.separator;

	/**
	 * 上传临时目录
	 */
	private static final String UPLOAD_TMP_PATH = "tmp";

	/**
	 * 临时路径
	 * 
	 * 2016年4月27日 下午4:07:23 flyfox 369191470@qq.com
	 * 
	 * @param site
	 * @param type
	 * @return
	 */
	public static String getUploadTmpPath(TbSite site) {
		String sitePath = site == null || StrUtils.isEmpty(site.getTemplate()) ? ""
				: (site.getTemplate() + File.separator);
		String path = JFLYFOX_PATH + sitePath + UPLOAD_TMP_PATH;

		return path;
	}

	/**
	 * 上传路径
	 * 
	 * 2016年4月27日 下午4:07:28 flyfox 369191470@qq.com
	 * 
	 * @param site
	 * @param type
	 * @return
	 */
	public static String getUploadPath(TbSite site, String type) {
		String sitePath = site == null || StrUtils.isEmpty(site.getTemplate()) ? ""
				: (site.getTemplate() + File.separator);
		String path = JFLYFOX_PATH + sitePath + type;
		File file = new File(BASE_PATH + path);
		if (!file.exists()) {
			file.mkdirs();
		}

		return path;
	}

	/**
	 * 上传绝对路径
	 * 
	 * 2016年4月27日 下午4:07:34 flyfox 369191470@qq.com
	 * 
	 * @param site
	 * @param type
	 * @return
	 */
	public static String getUploadFilePath(TbSite site, String type) {
		String sitePath = site == null || StrUtils.isEmpty(site.getTemplate()) ? ""
				: (site.getTemplate() + File.separator);
		String path = BASE_PATH + JFLYFOX_PATH + sitePath + type;
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}

		return path;
	}

	/**
	 * 重命名
	 * 
	 * 2015年9月25日 下午10:37:55 flyfox 369191470@qq.com
	 * 
	 * @param uploadFile
	 * @return
	 */
	public static String renameFile(String path, UploadFile uploadFile) {
		File uploadPath = new File(path);
		if (!uploadPath.exists()) {
			uploadPath.mkdirs();
		}

		String suf = "";
		if (uploadFile.getFileName().lastIndexOf(".") >= 0) {
			suf = uploadFile.getFileName().substring(uploadFile.getFileName().lastIndexOf("."));
		}
		String fileName = DateUtils.getNow("yyyyMMdd_HHmmss") + "_" + new SecureRandom().nextInt(999999) + suf;
		// 改名,避免重复以及中文问题
		uploadFile.getFile().renameTo(new File(path + File.separator + fileName));
		return fileName;
	}
}
