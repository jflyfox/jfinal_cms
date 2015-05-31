package com.flyfox.component.config;

import org.beetl.core.GroupTemplate;
import org.beetl.ext.jfinal.BeetlRenderFactory;

import com.beetl.functions.BeetlStrUtils;
import com.flyfox.component.beelt.BeeltFunctions;
import com.flyfox.component.interceptor.CommonInterceptor;
import com.flyfox.component.interceptor.PageViewInterceptor;
import com.flyfox.component.interceptor.UserKeyInterceptor;
import com.flyfox.component.util.JFlyFoxCache;
import com.flyfox.jfinal.component.handler.HtmlHandler;
import com.flyfox.jfinal.config.JflyfoxConfig;
import com.flyfox.jfinal.template.TemplateUtils;
import com.flyfox.system.user.UserInterceptor;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;

/**
 * API引导式配置
 */
public class BaseConfig extends JflyfoxConfig {

	public void configConstant(com.jfinal.config.Constants me) {
		super.configConstant(me);
		me.setMainRenderFactory(new BeetlRenderFactory());
		// 获取GroupTemplate ,可以设置共享变量等操作
		GroupTemplate groupTemplate = BeetlRenderFactory.groupTemplate;
		groupTemplate.registerFunctionPackage("strutil", BeetlStrUtils.class);
		groupTemplate.registerFunctionPackage("flyfox", BeeltFunctions.class);

		TemplateUtils.groupTemplate.registerFunctionPackage("strutil", BeetlStrUtils.class);
		TemplateUtils.groupTemplate.registerFunctionPackage("flyfox", BeeltFunctions.class);
	};

	@Override
	public void configHandler(Handlers me) {
		// Beelt
		// me.add(new BeeltHandler());
		me.add(new HtmlHandler());	
		super.configHandler(me);
	}

	/**
	 * 配置全局拦截器
	 */
	public void configInterceptor(Interceptors me) {
		// 公共属性
		me.add(new CommonInterceptor());

		// session model转换
		super.configInterceptor(me);
		// 用户Key设置
		me.add(new UserKeyInterceptor());
		// page view 统计
		me.add(new PageViewInterceptor());
		// 用户认证
		me.add(new UserInterceptor());
	}

	/**
	 * 初始化
	 */
	@Override
	public void afterJFinalStart() {
		JFlyFoxCache.init();
		System.out.println("##################################");
		System.out.println("############系统启动完成##########");
		System.out.println("##################################");
	}

}
