package com.jflyfox.system.file.util;

import java.io.File;

import com.jfinal.kit.PathKit;
import com.jflyfox.modules.admin.site.TbSite;
import com.jflyfox.util.PathUtils;
import com.jflyfox.util.StrUtils;

public class FileUploadUtils {

	public static final int UPLOAD_MAX = 10 * 1024 * 1024;

	/**
	 * 基础目录
	 */
	private static final String BASE_PATH = PathKit.getWebRootPath() + File.separator;

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
		String sitePath = getSitePath(site);
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
		String sitePath = getSitePath(site);
		String path = JFLYFOX_PATH + sitePath + type;
		File file = new File(BASE_PATH + path);
		if (!file.exists()) {
			file.mkdirs();
		}

		return path;
	}

	/**
	 * 获取站点路径
	 * 
	 * 2017年4月5日 下午2:23:42 flyfox 369191470@qq.com
	 * 
	 * @param site
	 * @return
	 */
	public static String getSitePath(TbSite site) {
		String sitePath = site == null || StrUtils.isEmpty(site.getTemplate()) ? ""
				: (site.getTemplate() + File.separator);
		return rebuild(sitePath);
	}

	/**
	 * 获取根路径
	 * 
	 * 2017年4月5日 下午1:57:33 flyfox 369191470@qq.com
	 * 
	 * @return
	 */
	public static String getBasePath() {
		return rebuild(File.separator + JFLYFOX_PATH);
	}

	/**
	 * 获取路径根路径
	 * 
	 * 2017年4月5日 下午1:54:45 flyfox 369191470@qq.com
	 * 
	 * @return
	 */
	public static String getRootPath() {
		return rebuild(PathKit.getWebRootPath());
	}

	/**
	 * 重构路径
	 * 
	 * 2017年4月5日 下午1:55:59 flyfox 369191470@qq.com
	 * 
	 * @param path
	 * @return
	 */
	public static String rebuild(String path) {
		return PathUtils.rebuildNoDecode(path);
	}

}
