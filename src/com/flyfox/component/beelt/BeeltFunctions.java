package com.flyfox.component.beelt;

import java.util.Date;

import com.flyfox.front.articlelike.ArticleLikeCache;
import com.flyfox.jfinal.template.TemplateFunctions;
import com.flyfox.modules.article.TbArticle;
import com.flyfox.modules.front.service.FrontCacheService;
import com.flyfox.system.dict.DictCache;
import com.flyfox.system.user.SysUser;
import com.flyfox.system.user.UserCache;
import com.flyfox.util.DateUtils;
import com.flyfox.util.NumberUtils;
import com.flyfox.util.StrUtils;
import com.flyfox.util.extend.HtmlUtils;

public class BeeltFunctions extends TemplateFunctions {

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

	// //////////////////////自定义方法///////////////////////////
	public static int countView(int articleId) {
		TbArticle article = new FrontCacheService().getArticleCount(articleId);
		return article == null ? 0 : article.getCountView();
	}

	public static int countComment(int articleId) {
		TbArticle article = new FrontCacheService().getArticleCount(articleId);
		return article == null ? 0 : article.getCountComment();
	}

	public static boolean isLike(int userId, int articleId) {
		return new ArticleLikeCache().isLike(userId, articleId);
	}
	
	/**
	 * 字符串截取
	 * 
	 * 2015年5月25日 下午3:58:45 flyfox 330627517@qq.com
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
	 * html预览
	 * 
	 * 2015年2月2日 下午3:40:34 flyfox 330627517@qq.com
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
	 * 2015年2月26日 下午4:24:39 flyfox 330627517@qq.com
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
	 * 2015年2月26日 下午4:24:39 flyfox 330627517@qq.com
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
	 * 2015年5月11日 下午2:07:40 flyfox 330627517@qq.com
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
