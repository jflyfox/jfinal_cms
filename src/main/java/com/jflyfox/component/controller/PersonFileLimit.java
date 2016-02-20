package com.jflyfox.component.controller;

import java.util.concurrent.atomic.AtomicLong;

import com.jflyfox.util.DateUtils;
import com.jflyfox.util.StrUtils;

public class PersonFileLimit {

	public static final int nowHourMaxCount = 50;
	public static final int nowDayMaxCount = 200;

	public static final String OK = "ok";
	public static final String ERROR_NOWDAY = "nowDayError";
	public static final String ERROR_NOWHOUR = "nowHourError";

	/**
	 * 用户ID
	 */
	private int id;
	/**
	 * 当前天
	 */
	private String nowDay = "";
	/**
	 * 当前小时
	 */
	private String nowHour = "";
	/***
	 * 当前天数上传次数
	 */
	private AtomicLong nowDayCount = new AtomicLong(0);
	/**
	 * 当前小时上传次数
	 */
	private AtomicLong nowHourCount = new AtomicLong(0);
	
	private String legal = OK;
	
	/**
	 * 入库
	 */
	private boolean insert = false;

	public boolean isLegal() {
		return OK.equals(legal);
	}

	public void add() {
		// 用户不合法了~你就什么都别干了~
		if (!OK.equals(legal)) {
			return;
		}
		
		String tmpDay = DateUtils.getNow("yyyyMMdd");
		if (StrUtils.isEmpty(nowDay)) {
			nowDay = tmpDay;
		} else if(!tmpDay.equals(nowDay)){
			nowDay = tmpDay;
			nowDayCount.set(0); // 清0从算
		}
		nowDayCount.incrementAndGet();
		if (nowDayCount.get() > nowDayMaxCount) {
			legal = ERROR_NOWDAY;
		}
		
		String tmpHour = DateUtils.getNow("yyyyMMddHH");
		if (StrUtils.isEmpty(nowHour)) {
			nowHour = tmpHour;
		} else if(!tmpHour.equals(nowHour)){
			nowHour = tmpHour;
			nowHourCount.set(0); // 清0从算
		}
		nowHourCount.incrementAndGet();
		if (nowHourCount.get() > nowHourMaxCount) {
			legal = ERROR_NOWHOUR;
		}
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNowDay() {
		return nowDay;
	}

	public void setNowDay(String nowDay) {
		this.nowDay = nowDay;
	}

	public String getNowHour() {
		return nowHour;
	}

	public void setNowHour(String nowHour) {
		this.nowHour = nowHour;
	}

	public AtomicLong getNowDayCount() {
		return nowDayCount;
	}

	public void setNowDayCount(AtomicLong nowDayCount) {
		this.nowDayCount = nowDayCount;
	}

	public AtomicLong getNowHourCount() {
		return nowHourCount;
	}

	public void setNowHourCount(AtomicLong nowHourCount) {
		this.nowHourCount = nowHourCount;
	}

	public String getLegal() {
		return legal;
	}

	public void setLegal(String legal) {
		this.legal = legal;
	}

	public boolean isInsert() {
		return insert;
	}

	public void setInsert(boolean insert) {
		this.insert = insert;
	}
}
