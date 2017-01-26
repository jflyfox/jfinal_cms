package com.jflyfox.modules.admin.article;

import java.io.File;
import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.upload.UploadFile;
import com.jflyfox.component.base.BaseProjectController;
import com.jflyfox.component.util.JFlyFoxUtils;
import com.jflyfox.component.util.JFlyfoxUpload;
import com.jflyfox.jfinal.component.annotation.ControllerBind;
import com.jflyfox.jfinal.component.db.SQLUtils;
import com.jflyfox.modules.admin.comment.CommentService;
import com.jflyfox.modules.admin.folder.FolderService;
import com.jflyfox.modules.admin.folder.TbFolder;
import com.jflyfox.modules.admin.site.TbSite;
import com.jflyfox.modules.admin.tags.TbTags;
import com.jflyfox.util.StrUtils;

/**
 * 文章管理管理
 * 
 * @author flyfox 2014-2-11
 */
@ControllerBind(controllerKey = "/admin/article")
public class ArticleController extends BaseProjectController {

	private static final String path = "/pages/admin/article/article_";

	public void index() {
		list();
	}

	public void list() {
		TbArticle model = getModelByAttr(TbArticle.class);

		SQLUtils sql = new SQLUtils(" from tb_article t " //
				+ " left join tb_folder f on f.id = t.folder_id " //
				+ " where 1 = 1 ");
		if (model.getAttrValues().length != 0) {
			sql.setAlias("t");
			sql.whereLike("title", model.getStr("title"));
			sql.whereEquals("folder_id", model.getInt("folder_id"));
			sql.whereEquals("status", model.getInt("status"));
		}
		// 站点设置
		int siteId = getSessionSite().getBackSiteId();
		sql.append(" and site_id = " + siteId);

		// 排序
		String orderBy = getBaseForm().getOrderBy();
		if (StrUtils.isEmpty(orderBy)) {
			sql.append(" order by t.folder_id,t.sort,t.create_time desc ");
		} else {
			sql.append(" order by ").append(orderBy);
		}

		Page<TbArticle> page = TbArticle.dao.paginate(getPaginator(), "select t.*,f.name as folderName ", //
				sql.toString().toString());

		// 查询下拉框
		setAttr("selectFolder", selectFolder(model.getInt("folder_id")));

		setAttr("page", page);
		setAttr("attr", model);

		setAttr("folders", new FolderService().getFolders(siteId));
		render(path + "list.html");
	}

	public void add() {
		// 获取页面信息,设置目录传入
		TbArticle attr = getModel(TbArticle.class);
		setAttr("model", attr);

		// 查询下拉框
		setAttr("selectFolder", selectFolder(attr.getInt("folder_id")));

		render(path + "add.html");
	}

	public void view() {
		TbArticle model = TbArticle.dao.findById(getParaToInt());
		TbFolder folder = TbFolder.dao.findById(model.getInt("folder_id"));
		model.put("folderName", folder.getStr("name"));

		setAttr("model", model);
		render(path + "view.html");
	}

	public void delete() {
		Integer id = getParaToInt();
		// 删除评论~
		new CommentService().deleteComment(id);
		// 删除文章
		TbArticle model = new TbArticle();
		Integer userid = getSessionUser().getUserID();
		String now = getNow();
		model.put("update_id", userid);
		model.put("update_time", now);
		model.deleteById(id);

		list();
	}

	public void edit() {
		TbArticle model = TbArticle.dao.findById(getParaToInt());
		setAttr("model", model);

		// 查询下拉框
		setAttr("selectFolder", selectFolder(model.getInt("folder_id")));

		render(path + "edit.html");
	}

	public void save() {
		TbSite site = getSessionSite().getBackModel();
		UploadFile uploadImage = getFile("model.image_url", JFlyfoxUpload.getUploadTmpPath(site), JFlyfoxUpload.UPLOAD_MAX);

		UploadFile uploadFile = getFile("model.file_url", JFlyfoxUpload.getUploadTmpPath(site), JFlyfoxUpload.UPLOAD_MAX);

		Integer pid = getParaToInt();
		TbArticle model = getModel(TbArticle.class);

		// 图片附件
		if (uploadImage != null) {
			String fileName = JFlyfoxUpload.renameFile(JFlyfoxUpload.getUploadFilePath(site, "article_image"), uploadImage);
			model.set("image_url", JFlyfoxUpload.getUploadPath(site, "article_image")+ File.separator + fileName);
			// model.set("image_url", uploadFile.getFileName());
		}

		// 文件附件
		if (uploadFile != null) {
			String oldFileName = uploadFile.getFileName();
			String fileName = JFlyfoxUpload.renameFile(JFlyfoxUpload.getUploadFilePath(site, "file_url"), uploadFile);
			model.set("file_url", JFlyfoxUpload.getUploadPath(site, "file_url") + File.separator + fileName);
			model.set("file_name", oldFileName); // 原文件名
		} else {
			// 删除标记
			Integer file_flag = getParaToInt("file_url_flag");
			if (file_flag != null && file_flag == 1) {
				model.set("file_url", "");
				model.set("file_name", "");
			}
		}

		Integer userid = getSessionUser().getUserID();
		String now = getNow();
		model.put("update_id", userid);
		model.put("update_time", now);
		if (pid != null && pid > 0) { // 更新
			if (JFlyFoxUtils.ARTICLE_APPROVE) {
				model.set("approve_status", ArticleConstant.APPROVE_STATUS_UPDATE);
			} else {
				model.set("approve_status", ArticleConstant.APPROVE_STATUS_PASS);
			}
			model.update();
		} else { // 新增
			model.remove("id");
			if (JFlyFoxUtils.ARTICLE_APPROVE) {
				model.set("approve_status", ArticleConstant.APPROVE_STATUS_UPDATE);
			} else {
				model.set("approve_status", ArticleConstant.APPROVE_STATUS_PASS);
			}
			model.put("create_id", userid);
			model.put("create_time", now);
			if (model.get("sort") == null)
				model.put("sort", 1);
			model.save();
		}

		renderMessage("保存成功");
	}

	public void edit_content() {
		TbArticle model = TbArticle.dao.findById(getParaToInt());
		setAttr("model", model);

		String tags = new ArticleService().getTags(model);
		setAttr("tags", tags);

		super.render(path + "edit_content.html");
	}

	public void edit_content_ue() {
		TbArticle model = TbArticle.dao.findById(getParaToInt());
		setAttr("model", model);

		// 设置标签
		String tags = new ArticleService().getTags(model);
		setAttr("tags", tags);

		super.render(path + "edit_content_ue.html");
	}
	
	public void edit_content_textarea() {
		TbArticle model = TbArticle.dao.findById(getParaToInt());
		setAttr("model", model);

		// 设置标签
		String tags = new ArticleService().getTags(model);
		setAttr("tags", tags);

		super.render(path + "edit_content_textarea.html");
	}

	public void view_content() {
		// 根目录
		setAttr("model", TbFolder.dao.findById(TbFolder.ROOT));
		// 数据列表
		TbArticle article = TbArticle.dao.findById(getParaToInt());
		if (article != null) {
			setAttr("item", article);
		}

		renderAuto(path + "view_content.html");

	}

	public void save_content() {
		TbArticle model = getModel(TbArticle.class);
		if (JFlyFoxUtils.ARTICLE_APPROVE) {
			model.set("approve_status", ArticleConstant.APPROVE_STATUS_UPDATE);
		} else {
			model.set("approve_status", ArticleConstant.APPROVE_STATUS_PASS);
		}
		model.update();

		// 保存tags
		Db.update(" delete from tb_tags where article_id = ?", model.getInt("id"));
		String tags = getPara("tags");
		if (StrUtils.isNotEmpty(tags)) {
			String[] tagsArr = tags.split(",");
			for (int i = 0; i < tagsArr.length; i++) {
				String tagname = tagsArr[i];
				// 最多5个
				if (i >= 5) {
					break;
				}
				if (StrUtils.isEmpty(tagname)) {
					continue;
				}
				TbTags tbTags = new TbTags();
				tbTags.put("tagname", tagname);
				tbTags.put("article_id", model.getInt("id"));
				tbTags.put("create_id", getSessionUser().getUserID());
				tbTags.put("create_time", getNow());
				tbTags.save();

			}
		}

		renderMessage("保存成功");
	}

	public void list_approve() {
		TbArticle model = getModelByAttr(TbArticle.class);

		SQLUtils sql = new SQLUtils(" from tb_article t " //
				+ " left join tb_folder f on f.id = t.folder_id " //
				+ " where approve_status in ( " //
				+ ArticleConstant.APPROVE_STATUS_INIT + "," + ArticleConstant.APPROVE_STATUS_UPDATE + " ) ");
		if (model.getAttrValues().length != 0) {
			sql.setAlias("t");
			sql.whereLike("title", model.getStr("title"));
			sql.whereEquals("folder_id", model.getInt("folder_id"));
			sql.whereEquals("status", model.getInt("status"));

		}
		
		// 站点设置
		int siteId = getSessionSite().getBackSiteId();
		sql.append(" and site_id = " + siteId);

		// 排序
		String orderBy = getBaseForm().getOrderBy();
		if (StrUtils.isEmpty(orderBy)) {
			sql.append(" order by t.folder_id,t.sort,t.create_time desc ");
		} else {
			sql.append(" order by ").append(orderBy);
		}

		Page<TbArticle> page = TbArticle.dao.paginate(getPaginator(), "select t.*,f.name as folderName ", //
				sql.toString().toString());

		// 查询下拉框
		setAttr("selectFolder", selectFolder(model.getInt("folder_id")));

		setAttr("page", page);
		setAttr("attr", model);

		List<TbFolder> folders = new FolderService().getFolders(getSessionSite().getBackSiteId());
		setAttr("folders", folders);

		render(path + "list_approve.html");
	}

	public void save_approve() {
		TbArticle model = TbArticle.dao.findById(getParaToInt());
		int approveStatus = getParaToInt("approve_status");
		Integer userid = getSessionUser().getUserID();
		String now = getNow();
		model.put("update_id", userid);
		model.put("update_time", now);
		model.set("approve_status", approveStatus);
		model.update();

		// renderMessage("审核成功","javascript:window.top.location.href = 'admin/article/list_approve';");
		redirect("/admin/article/list_approve");
	}

	public void tocopy() {
		TbArticle model = TbArticle.dao.findById(getParaToInt());

		// 查询下拉框
		setAttr("selectFolder", selectFolder(0));

		setAttr("model", model);
		render(path + "copy.html");
	}

	public void copy() {
		int id = getParaToInt();
		Integer userid = getSessionUser().getUserID();
		String folders = getPara("folders");
		// 复制
		new ArticleService().copy(id, userid, folders);

		renderMessage("复制完成");
	}

}
