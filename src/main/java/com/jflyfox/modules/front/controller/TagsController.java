package com.jflyfox.modules.front.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jflyfox.component.base.BaseProjectController;
import com.jflyfox.component.util.JFlyFoxCache;
import com.jflyfox.component.util.JFlyFoxUtils;
import com.jflyfox.jfinal.base.Paginator;
import com.jflyfox.jfinal.component.annotation.ControllerBind;
import com.jflyfox.modules.admin.article.TbArticle;
import com.jflyfox.modules.admin.tags.TbTags;
import com.jflyfox.modules.front.interceptor.FrontInterceptor;
import com.jflyfox.modules.front.service.FrontCacheService;
import com.jflyfox.util.extend.HtmlUtils;

@ControllerBind(controllerKey = "/front/tags")
public class TagsController extends BaseProjectController {

	public static final String path = "/tags/";

	@Before(FrontInterceptor.class)
	public void index() {
		String tagName = getPara();
		try {
			tagName = (tagName == null ? "" : tagName);
			tagName = URLDecoder.decode(tagName, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		// 去除标签
		tagName = HtmlUtils.delHTMLTag(tagName);
		// 更新tag
		tagName = HtmlUtils.delSpecialCode(tagName);
		
		setAttr("tagName", tagName);

		// 数据列表,只查询展示的和类型为11,12的
		Page<TbArticle> articles = TbArticle.dao.paginate(getPaginator(), " select a.*", //
				" from (select distinct t.*  from tb_article t " //
						+ " left join tb_tags tag on tag.article_id = t.id " //
						+ " where (tag.tagname like ? or t.title like ?) " //
						+ " and t.status = 1 and t.type in (11,12)  " // 查询状态为显示，类型是预览和正常的文章
						+ " and t.folder_id != " + JFlyFoxUtils.MENU_TOPPIC // 不搜索首页图片
						+ " order by t.sort,t.create_time desc ) a", "%" + tagName + "%", "%" + tagName + "%");
		setAttr("page", articles);

		// 显示50个标签
		if (articles.getTotalRow() > 0) {
			Page<TbTags> taglist = new FrontCacheService().getTagsByFolder(new Paginator(1, 50), articles.getList()
					.get(0).getFolderId());
			setAttr("taglist", taglist.getList());
		} else {
			Page<TbTags> taglist = new FrontCacheService().getTags(new Paginator(1, 50));
			setAttr("taglist", taglist.getList());
		}

		// seo：title优化
		setAttr(JFlyFoxUtils.TITLE_ATTR, tagName + " - 搜索 - " + JFlyFoxCache.getHeadTitle());

		renderAuto(path + "search.html");
	}

	@Before(FrontInterceptor.class)
	public void all() {
		// 目录列表
		int tagsAllId = Db.findFirst(" SELECT id FROM tb_folder where jump_url = 'front/tags/all'").getInt("id");
		// 活动目录
		setAttr("folders_selected", tagsAllId);

		List<TbTags> taglist = TbTags.dao.find(" select tagname from tb_tags group by tagname order by id ");
		setAttr("taglist", taglist);

		// seo：title优化
		setAttr(JFlyFoxUtils.TITLE_ATTR, "标签 - " + JFlyFoxCache.getHeadTitle());

		renderAuto(path + "show_tags.html");
	}

}
