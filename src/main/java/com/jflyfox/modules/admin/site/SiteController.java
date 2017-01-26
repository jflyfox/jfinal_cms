package com.jflyfox.modules.admin.site;

import java.io.File;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.upload.UploadFile;
import com.jflyfox.component.base.BaseProjectController;
import com.jflyfox.component.util.JFlyfoxUpload;
import com.jflyfox.jfinal.component.annotation.ControllerBind;
import com.jflyfox.jfinal.component.db.SQLUtils;
import com.jflyfox.util.StrUtils;

/**
 * 站点
 * 
 * @author flyfox 2014-4-24
 */
@ControllerBind(controllerKey = "/admin/site")
public class SiteController extends BaseProjectController {

	private static final String path = "/pages/admin/site/site_";

	public void index() {
		list();
	}

	public void list() {
		TbSite model = getModelByAttr(TbSite.class);

		SQLUtils sql = new SQLUtils(" from tb_site t where 1=1 ");
		if (model.getAttrValues().length != 0) {
			sql.setAlias("t");
			// 查询条件
			sql.whereLike("name", model.getStr("name"));
		}
		// 排序
		String orderBy = getBaseForm().getOrderBy();
		if (StrUtils.isEmpty(orderBy)) {
			sql.append(" order by t.sort,t.id ");
		} else {
			sql.append(" order by ").append(orderBy);
		}

		Page<TbSite> page = TbSite.dao.paginate(getPaginator(), "select t.* ", //
				sql.toString().toString());

		// 下拉框
		setAttr("page", page);
		setAttr("attr", model);
		render(path + "list.html");
	}

	public void add() {
		render(path + "add.html");
	}

	public void view() {
		TbSite model = TbSite.dao.findById(getParaToInt());
		setAttr("model", model);
		render(path + "view.html");
	}

	public void delete() {
		Integer pid = getParaToInt();
		TbSite model = new TbSite();
		Integer userid = getSessionUser().getUserID();
		String now = getNow();
		model.put("update_id", userid);
		model.put("update_time", now);
		model.deleteById(pid);

		new SiteService().clearCache();
		
		list();
	}

	public void edit() {
		TbSite model = TbSite.dao.findById(getParaToInt());
		setAttr("model", model);
		render(path + "edit.html");
	}

	public void defalut() {
		Integer pid = getParaToInt();

		if (pid != null && pid > 0) {
			Db.update("update tb_site set site_defalut = ? where 1=1", SiteConstant.SITE_DEFAULT_NO);
			Db.update("update tb_site set site_defalut = ? where id=?", SiteConstant.SITE_DEFAULT_YES, pid);
			new SiteService().clearCache();
		}

		list();
	}
	
	public void save() {
		TbSite site = getSessionSite().getBackModel();
		UploadFile thumbnailFile = getFile("thumbnail_file", JFlyfoxUpload.getUploadTmpPath(site), JFlyfoxUpload.UPLOAD_MAX);
		
		Integer pid = getParaToInt();
		TbSite model = getModel(TbSite.class);
		
		// 缩略图附件
		if (thumbnailFile != null) {
			String fileName = JFlyfoxUpload.renameFile(JFlyfoxUpload.getUploadFilePath(site, "site_thumbnail"), thumbnailFile);
			model.set("thumbnail", JFlyfoxUpload.getUploadPath(site, "site_thumbnail")+ File.separator + fileName);
			// model.set("thumbnail_name", uploadFile.getFileName());
		}

		Integer userid = getSessionUser().getUserID();
		String now = getNow();
		model.put("update_id", userid);
		model.put("update_time", now);
		if (pid != null && pid > 0) { // 更新
			model.update();
		} else { // 新增
			model.set("site_defalut", SiteConstant.SITE_DEFAULT_NO);
			model.remove("id");
			model.put("create_id", userid);
			model.put("create_time", now);
			model.save();
		}
		
		new SiteService().clearCache();
		
		renderMessage("保存成功");
	}

}
