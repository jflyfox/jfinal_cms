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
package com.jflyfox.jfinal.base;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jfinal.core.Controller;
import com.jfinal.log.Log;
import com.jflyfox.jfinal.component.util.Attr;
import com.jflyfox.util.Config;
import com.jflyfox.util.DateUtils;
import com.jflyfox.util.HandlerUtils;
import com.jflyfox.util.StrUtils;

/**
 * Controller 不能初始化
 * 
 * @author flyfox
 * @date 2013-10-20
 */
public abstract class BaseController extends Controller {

	protected static final String page_message = Config.getStr("PAGES.MESSAGE");
	protected static final Log log = Log.getLog(BaseController.class);

	protected void renderMessage(String message) {
		renderMessage(message, "closeIframe();");
	}

	protected void renderMessageByFailed(String message) {
		renderMessage(message, "history.back();");
	}

	protected void renderMessage(String message, String obj) {
		String script = "";
		if (StrUtils.isEmpty(obj)) { // 关闭页面
			script = "closeIframe();";
		} else if (script.endsWith(".jsp")) { // 页面跳转
			script = "window.location.href = \"" + obj + "\"";
		} else { // 直接执行JS
			script = obj;
		}
		setAttr("msg", message);
		setAttr("script", script);
		render(page_message);
	}

	protected void render500(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.getRequestDispatcher("/pages/error/500.jsp").forward(request, response);
		} catch (Exception e) {
			log.error("500 page -->", e);
		}
	}

	/**
	 * 获取前一个页面
	 * 
	 * 2015年3月25日 下午3:47:55 flyfox 330627517@qq.com
	 * 
	 * @return
	 */
	protected String getPrePage() {
		return getRequest().getHeader("Referer");
	}

	/**
	 * 获取当前时间，保存创建时间使用
	 * 
	 * 2015年3月25日 下午3:48:02 flyfox 330627517@qq.com
	 * 
	 * @return
	 */
	protected String getNow() {
		return DateUtils.getNow(DateUtils.DEFAULT_REGEX_YYYY_MM_DD_HH_MIN_SS);
	}

	protected <T> T handler(Class<T> modelClass) {
		Object o = null;
		try {
			o = modelClass.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return handler(o);
	}

	@SuppressWarnings("unchecked")
	protected <T> T handler(Object o) {
		if (o == null) {
			return null;
		}
		return (T) HandlerUtils.handler(getRequest(), o);
	}

	public void renderAuto(String view) {
		String path = getAutoPath(view);

		super.render(path);
	}

	public void redirectAuto(String view) {
		String path = getAutoPath(view);

		super.redirect(path);
	}

	protected String getAutoPath(String view) {
		String path = view;

		if (!view.startsWith("/")) {
			path = "/" + path;
		}

		path = (isMoblie() ? Attr.PATH_MOBILE : Attr.PATH_PC) + path;

		if (view.startsWith("/")) {
			path = "/" + path;
		}

		path = path.replace("//", "/");
		return path;
	}

	public boolean isMoblie() {
		return getSessionAttr(Attr.SESSION_IS_MOILE);
	}

	@SuppressWarnings("rawtypes")
	public SessionUser getSessionUser() {
		return getSessionAttr(Attr.SESSION_NAME);
	}

	@SuppressWarnings("rawtypes")
	public SessionUser setSessionUser(SessionUser user) {
		setSessionAttr(Attr.SESSION_NAME, user);
		return user;
	}

	public void removeSessionUser() {
		removeSessionAttr(Attr.SESSION_NAME);
	}

	public Paginator getPaginator() {
		Paginator paginator = new Paginator();
		Integer pageNo = getParaToInt("pageNo");
		if (pageNo != null && pageNo > 0) {
			paginator.setPageNo(pageNo);
		}
		Integer pageSize = getParaToInt("recordsperpage");
		if (pageSize != null && pageSize > 0) {
			paginator.setPageSize(pageSize);
		}
		return paginator;
	}

	public Object[] toArray(List<Object> list) {
		return list.toArray(new Object[list.size()]);
	}

	public BaseForm getBaseForm() {
		BaseForm form = super.getAttr("form");
		return form == null ? new BaseForm() : form;
	}

	/**
	 * 原始的GetModel方法
	 * 
	 * 2015年2月25日 上午11:02:37 flyfox 330627517@qq.com
	 * 
	 * @param modelClass
	 * @return
	 */
	public <T> T getModelByOld(Class<T> modelClass) {
		return super.getModel(modelClass);
	}

	/**
	 * 覆盖原始方法，采用PAGE_MODEL_NAME做为前缀
	 */
	@Override
	public <T> T getModel(Class<T> modelClass) {
		return super.getModel(modelClass, Attr.PAGE_MODEL_NAME);
	}

	/**
	 * 新GetModel方法，采用PAGE_ATTR_NAME，作为前缀
	 * 
	 * 2015年2月25日 上午11:03:45 flyfox 330627517@qq.com
	 * 
	 * @param modelClass
	 * @return
	 */
	public <T> T getModelByAttr(Class<T> modelClass) {
		return super.getModel(modelClass, Attr.PAGE_ATTR_NAME);
	}

	/**
	 * 新GetModel方法，采用PAGE_FORM_NAME，作为前缀
	 * 
	 * 2015年10月23日 上午11:03:45 flyfox 330627517@qq.com
	 * 
	 * @param modelClass
	 * @return
	 */
	public <T> T getModelByForm(Class<T> modelClass) {
		return super.getBean(modelClass, Attr.PAGE_FORM_NAME);
	}
}
