package com.jflyfox.modules.admin.pageview;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.log.Log;
import com.jflyfox.util.DateUtils;

public class PageViewCache {

	private final static Log log = Log.getLog(PageViewCache.class);
	private final static List<String> cacheList = new ArrayList<String>();
	private static String nowDay = null;

	private PageViewCache() {
	}

	/**
	 * 初始化
	 * 
	 * 2015年3月2日 上午10:46:52 flyfox 369191470@qq.com
	 */
	public static void init() {
		initNowDay();

		log.info("####PageViewCache初始化......");
		// 去当天数据
		List<TbPageView> pageViewList = TbPageView.dao.findByWhere(" where create_day = ?", DateUtils.getNow());
		for (TbPageView pageView : pageViewList) {
			cacheList.add(pageView.getStr("ip"));
		}

	}

	/**
	 * 添加PV，需进行代码同步
	 * 
	 * 2015年3月2日 上午10:46:29 flyfox 369191470@qq.com
	 * 
	 * @param ip
	 */
	public static synchronized void add(String ip) {
		initNowDay();
		if (cacheList.contains(ip))
			return;

		log.debug("####PageViewCache 来了个新用户：" + ip);
		cacheList.add(ip);
		TbPageView pageView = new TbPageView();
		pageView.set("ip", ip);
		pageView.set("userid", 0); // 暂时无用
		pageView.set("create_day", DateUtils.getNow());
		pageView.set("create_time", DateUtils.getNow(DateUtils.DEFAULT_REGEX_YYYY_MM_DD_HH_MIN_SS));
		pageView.save();
	}

	/**
	 * 今日访问量
	 * 
	 * 2015年3月2日 上午11:10:11 flyfox 369191470@qq.com
	 */
	public static Integer size() {
		return cacheList.size();
	}

	/**
	 * 当前日期处理
	 * 
	 * 2015年3月2日 上午10:19:19 flyfox 369191470@qq.com
	 */
	private static void initNowDay() {
		if (nowDay == null) {
			nowDay = DateUtils.getNow();
		} else if (!nowDay.equals(DateUtils.getNow())) { // 如果不是当天数据清除再来
			nowDay = DateUtils.getNow();
			cacheList.clear();
		}
	}

}