package com.jflyfox.component.config;

import org.beetl.core.GroupTemplate;
import org.beetl.ext.jfinal3.JFinal3BeetlRenderFactory;

import com.beetl.functions.BeetlStrUtils;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jflyfox.component.beelt.BeeltFunctions;
import com.jflyfox.component.interceptor.CommonInterceptor;
import com.jflyfox.component.interceptor.PageViewInterceptor;
import com.jflyfox.component.interceptor.SiteInterceptor;
import com.jflyfox.component.interceptor.UpdateCacheInterceptor;
import com.jflyfox.component.interceptor.UserKeyInterceptor;
import com.jflyfox.component.util.JFlyFoxCache;
import com.jflyfox.jfinal.component.handler.HtmlHandler;
import com.jflyfox.jfinal.config.JflyfoxConfig;
import com.jflyfox.modules.front.template.TemplateImageService;
import com.jflyfox.modules.front.template.TemplateService;
import com.jflyfox.modules.front.template.TemplateVideoService;
import com.jflyfox.system.user.UserInterceptor;

/**
 * API引导式配置
 */
public class BaseConfig extends JflyfoxConfig {

	public void configConstant(com.jfinal.config.Constants me) {
		super.configConstant(me);
		// 开启日志
		// SqlReporter.setLog(true);

		JFinal3BeetlRenderFactory rf = new JFinal3BeetlRenderFactory();
		rf.config();
		me.setRenderFactory(rf);

		// 获取GroupTemplate ,可以设置共享变量等操作
		GroupTemplate groupTemplate = rf.groupTemplate;
		groupTemplate.registerFunctionPackage("strutil", BeetlStrUtils.class);
		groupTemplate.registerFunctionPackage("flyfox", BeeltFunctions.class);
		groupTemplate.registerFunctionPackage("temp", TemplateService.class);
		groupTemplate.registerFunctionPackage("tempImage", TemplateImageService.class);
		groupTemplate.registerFunctionPackage("tempVideo", TemplateVideoService.class);

	};

	@Override
	public void configHandler(Handlers me) {
		// Beelt
		// me.add(new BeeltHandler());
		// me.add(new ImageHandler());

		me.add(new HtmlHandler());
		super.configHandler(me);
	}

	/**
	 * 配置全局拦截器
	 */
	public void configInterceptor(Interceptors me) {
		// session model转换
		super.configInterceptor(me);
		// 用户Key设置
		me.add(new UserKeyInterceptor());
		// page view 统计
		me.add(new PageViewInterceptor());
		// 缓存更新
		me.add(new UpdateCacheInterceptor());
		// 用户认证
		me.add(new UserInterceptor());
		// 站点拦截
		me.add(new SiteInterceptor());
		// 公共属性
		me.add(new CommonInterceptor());
	}

	/**
	 * 初始化
	 */
	@Override
	public void afterJFinalStart() {
		super.afterJFinalStart();

		JFlyFoxCache.init();
		System.out.println("##################################");
		System.out.println("############系统启动完成##########");
		System.out.println("##################################");
	}

	@Override
	public void beforeJFinalStop() {
		super.beforeJFinalStop();

		// 关闭模板
		// BeetlRenderFactory.groupTemplate.close();

		System.out.println("##################################");
		System.out.println("############系统停止完成##########");
		System.out.println("##################################");
	}
}
