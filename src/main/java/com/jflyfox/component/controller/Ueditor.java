package com.jflyfox.component.controller;

import com.baidu.ueditor.ActionEnter;
import com.jfinal.kit.PathKit;
import com.jflyfox.component.base.BaseProjectController;
import com.jflyfox.jfinal.component.annotation.ControllerBind;

@ControllerBind(controllerKey = "ueditor")
public class Ueditor extends BaseProjectController {

	public void index() {
		String out = new ActionEnter(getRequest(), PathKit.getWebRootPath()).exec();
		renderText(out);
	}
}
