package com.jflyfox.component.beelt;

import java.util.Date;

import com.jflyfox.jfinal.base.BaseModel;
import com.jflyfox.jfinal.template.TemplateFunctions;
import com.jflyfox.modules.admin.folder.FolderService;
import com.jflyfox.system.config.ConfigCache;
import com.jflyfox.system.config.SysConfig;
import com.jflyfox.system.dict.DictCache;
import com.jflyfox.system.file.service.FileUploadService;
import com.jflyfox.system.user.SysUser;
import com.jflyfox.system.user.UserCache;
import com.jflyfox.util.DateUtils;
import com.jflyfox.util.NumberUtils;
import com.jflyfox.util.StrUtils;
import com.jflyfox.util.extend.HtmlUtils;

public class BeeltFunctions extends TemplateFunctions {

	private static FileUploadService uploadSvc = new FileUploadService();

	// //////////////////////////数据字典///////////////////////////////////////////

	public static String dictSelect(String type, int selected_value) {
		return DictCache.getSelect(type, selected_value);
	}

	public static String dictSelect(String type, String selected_value) {
		return dictSelect(type, NumberUtils.parseInt(selected_value));
	}

	public static String dictValue(int key) {
		return DictCache.getValue(key);
	}

	public static String dictValue(String key) {
		return dictValue(NumberUtils.parseInt(key));
	}

	public static String dictCode(int key) {
		return DictCache.getCode(key);
	}

	public static String dictCode(String key) {
		return dictCode(NumberUtils.parseInt(key));
	}

	// //////////////////////////系统参数配置///////////////////////////////////////////

	public static SysConfig getConfig(String key) {
		return ConfigCache.getSysConfig(key);
	}

	// //////////////////////自定义方法///////////////////////////
	/**
	 * 获取文件
	 * 
	 * 2017年4月5日 下午10:08:57 flyfox 369191470@qq.com
	 * 
	 * @param article
	 * @param attrName
	 * @return
	 */
	public static String getFile(BaseModel<?> article, String attrName) {
		String fileName = article.getStr(attrName);

		if (StrUtils.isNotEmpty(fileName)) {
			uploadSvc.restore(fileName);
		}

		return fileName;
	}

	/**
	 * 获取图片路径
	 * 
	 * 2015年6月2日 下午6:36:37 flyfox 369191470@qq.com
	 * 
	 * @param article
	 * @return
	 */
	public static String getImage(BaseModel<?> article) {
		String imageUrl = article.getStr("image_net_url");
		if (StrUtils.isEmpty(imageUrl)) {
			imageUrl = article.getStr("image_url");
		}

		uploadSvc.restore(imageUrl);

		return imageUrl;
	}

	/**
	 * 获取视频路径
	 * 
	 * 2015年6月2日 下午6:36:37 flyfox 369191470@qq.com
	 * 
	 * @param video
	 * @return
	 */
	public static String getVideo(BaseModel<?> video) {
		String videoUrl = video.getStr("video_net_url");
		if (StrUtils.isEmpty(videoUrl)) {
			videoUrl = video.getStr("video_url");
		}
		videoUrl = videoUrl.replaceAll("\\\\", "\\/");

		uploadSvc.restore(videoUrl);

		return videoUrl;
	}

	/**
	 * 获取菜单，数字和字符串转换
	 * 
	 * 2015年5月26日 上午8:57:48 flyfox 369191470@qq.com
	 * 
	 * @param key
	 * @return
	 */
	public static String getMenu(String key) {
		return FolderService.getMenu(key);
	}

	/**
	 * 获取菜单，数字和字符串转换
	 * 
	 * 2015年5月26日 上午8:57:48 flyfox 369191470@qq.com
	 * 
	 * @param key
	 * @return
	 */
	public static String getMenu(int key) {
		return FolderService.getMenu(key + "");
	}

	/**
	 * 字符串截取
	 * 
	 * 2015年5月25日 下午3:58:45 flyfox 369191470@qq.com
	 * 
	 * @param str
	 * @param start
	 * @param end
	 * @return
	 */
	public static String substr(String str, int start, int end) {
		return str == null ? null : str.substring(start, end);
	}

	public static String getNow() {
		return DateUtils.getNow();
	}

	public static String getNow(String regex) {
		return DateUtils.getNow(regex);
	}

	public static String suojin(String str, int length) {
		return StrUtils.suojin(str, length);
	}

	/**
	 * split
	 * 
	 * 2015年5月17日 下午11:03:39 flyfox 369191470@qq.com
	 * 
	 * @param str
	 * @param split
	 * @return
	 */
	public static String[] split(String str, String split) {
		if (StrUtils.isEmpty(str)) {
			return null;
		}
		return str.split(split);
	}

	/**
	 * html预览
	 * 
	 * 2015年2月2日 下午3:40:34 flyfox 369191470@qq.com
	 * 
	 * @param htmlStr
	 * @return
	 */
	public static String showHTML(String htmlStr, int num, String endStr) {
		String tmpStr = HtmlUtils.delHTMLTag(htmlStr);
		tmpStr = StrUtils.suojin(tmpStr, num + endStr.length(), endStr);
		return tmpStr;
	}

	/**
	 * 获取用户
	 * 
	 * 2015年2月26日 下午4:24:39 flyfox 369191470@qq.com
	 * 
	 * @param pid
	 * @return
	 */
	public static SysUser getUser(Integer pid) {
		SysUser user = UserCache.getUser(pid);
		return user;
	}

	/**
	 * 获取用户名
	 * 
	 * 2015年2月26日 下午4:24:39 flyfox 369191470@qq.com
	 * 
	 * @param pid
	 * @return
	 */
	public static String getUserName(Integer pid) {
		SysUser user = UserCache.getUser(pid);
		if (user == null) {
			return "";
		}
		if (StrUtils.isNotEmpty(user.getStr("realname"))) {
			return user.getStr("realname");
		}
		return user.getStr("username");
	}

	/**
	 * 判断date距当前时间是否相差before天
	 * 
	 * 2015年5月11日 下午2:07:40 flyfox 369191470@qq.com
	 * 
	 * @param date
	 * @param before
	 * @return
	 */
	public static boolean isNew(String date, int before) {
		DateUtils.parseByAll(date).getTime();
		Date d1 = new Date();
		Date d2 = DateUtils.parse(date, DateUtils.DEFAULT_REGEX_YYYY_MM_DD_HH_MIN_SS);
		long diff = d1.getTime() - d2.getTime();
		long days = diff / (1000 * 60 * 60 * 24);
		return days - 7 <= 0;
	}

}
