package com.jflyfox.api.service.impl;

import java.util.List;

import com.jfinal.plugin.activerecord.Page;
import com.jflyfox.api.form.ApiResp;
import com.jflyfox.api.form.BaseApiForm;
import com.jflyfox.api.service.IApiLogic;
import com.jflyfox.modules.admin.article.TbArticle;
import com.jflyfox.modules.admin.folder.FolderService;
import com.jflyfox.modules.admin.folder.TbFolder;
import com.jflyfox.modules.front.service.FrontCacheService;

/**
 * Api基础方法
 * 
 * 2016年9月29日 上午11:19:00 flyfox 369191470@qq.com
 */
public class ApiV100Logic extends BaseApiLogic implements IApiLogic {

	private FrontCacheService service = new FrontCacheService();
	private FolderService folderServer = new FolderService();

	@Override
	public ApiResp config(BaseApiForm form) {
		return new ApiResp().addData("test", "ok");
	}

	@Override
	public ApiResp folders(BaseApiForm form) {
		List<TbFolder> list = folderServer.getFolders(form.getInt("siteId"));
		return new ApiResp().addData("list", list);
	}

	@Override
	public ApiResp pageArticleSite(BaseApiForm form) {
		Page<TbArticle> page = service.getArticleBySiteId(form.getPaginator(), form.getInt("siteId"));
		return new ApiResp().addData("list", page.getList()).addData("total", page.getTotalRow());
	}

	@Override
	public ApiResp pageArticle(BaseApiForm form) {
		Page<TbArticle> page = service.getArticle(form.getPaginator(), form.getInt("folderId"));
		return new ApiResp().addData("list", page.getList()).addData("total", page.getTotalRow());
	}

	@Override
	public ApiResp article(BaseApiForm form) {
		TbArticle article = service.getArticle(form.getInt("articleId"));
		return new ApiResp().addData("article", article);
	}

}
