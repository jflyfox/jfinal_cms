package com.flyfox.modules.article;

import java.io.File;
import java.security.SecureRandom;
import java.util.List;

import com.flyfox.jfinal.base.BaseController;
import com.flyfox.jfinal.component.db.SQLUtils;
import com.flyfox.modules.folder.TbFolder;
import com.flyfox.modules.tags.TbTags;
import com.flyfox.util.DateUtils;
import com.flyfox.util.StrUtils;
import com.jfinal.kit.PathKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.upload.UploadFile;

/**
 * 联系人管理
 * 
 * @author flyfox 2014-2-11
 */
public class ArticleController extends BaseController {

	private static final String path = "/pages/article/article_";

	/**
	 * 图片目录
	 */
	public static final String IMAGE_PATH = "download" + File.separator + "image_url";
	/**
	 * 图片全目录
	 */
	public static final String UPLOAD_PATH = PathKit.getWebRootPath() + File.separator + IMAGE_PATH;

	public void index() {
		list();
	}

	public void list() {
		TbArticle model = getModelByAttr(TbArticle.class);

		SQLUtils sql = new SQLUtils(" from tb_article t " //
				+ " left join tb_folder f on f.id = t.folder_id " + " where 1 = 1 ");
		if (model.getAttrValues().length != 0) {
			sql.setAlias("t");
			sql.whereLike("title", model.getStr("title"));
			sql.whereEquals("folder_id", model.getInt("folder_id"));
			sql.whereEquals("status", model.getInt("status"));
		}
		sql.append(" order by t.folder_id,t.sort,t.create_time desc ");

		Page<TbArticle> page = TbArticle.dao.paginate(getPaginator(), "select t.*,f.name as folderName ", //
				sql.toString().toString());

		// 查询下拉框
		setAttr("selectFolder", selectFolder(model.getInt("folder_id")));

		setAttr("page", page);
		setAttr("attr", model);
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
		TbArticle.dao.deleteById(getParaToInt());

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
		UploadFile uploadFile = getFile("model.image_url", UPLOAD_PATH, 10 * 1024 * 1024);

		Integer pid = getParaToInt();
		TbArticle model = getModel(TbArticle.class);

		// 附件
		if (uploadFile != null) {
			String suf = "";
			if (uploadFile.getFileName().lastIndexOf(".") >= 0) {
				suf = uploadFile.getFileName().substring(uploadFile.getFileName().lastIndexOf("."));
			}
			String fileName = DateUtils.getNow("yyyyMMdd_HHmmss") + "_" + new SecureRandom().nextInt(999999) + suf;
			// 改名,避免重复以及中文问题
			uploadFile.getFile().renameTo(new File(UPLOAD_PATH + File.separator + fileName));

			model.set("image_url", fileName);
			// model.set("image_url", uploadFile.getFileName());
		}

		model.put("update_time", getNow());
		if (pid != null && pid > 0) { // 更新
			model.update();
		} else { // 新增
			model.remove("id");
			model.put("create_id", getSessionUser().getUserID());
			model.put("create_time", getNow());
			if (model.get("sort") == null)
				model.put("sort", 1);
			model.save();
		}

		renderMessage("保存成功");
	}

	public void edit_content() {
		TbArticle model = TbArticle.dao.findById(getParaToInt());
		setAttr("model", model);

		// 设置标签
		String tags = Db.findFirst("select group_concat(tagname) tags " //
				+ " from tb_tags where article_id = ? order by id", model.getInt("id")).getStr("tags");
		setAttr("tags", tags);

		super.render(path + "edit_content.html");
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
	
	/**
	 * 目录复选框
	 * 
	 * 2015年1月28日 下午5:28:40 flyfox 330627517@qq.com
	 * 
	 * @return
	 */
	private String selectFolder(Integer selected) {
		List<TbFolder> list = TbFolder.dao.find(" select id,name from tb_folder order by sort,create_time desc ");
		StringBuffer sb = new StringBuffer("");
		if (list != null && list.size() > 0) {
			for (TbFolder folder : list) {
				sb.append("<option value=\"");
				sb.append(folder.getInt("id"));
				sb.append("\" ");
				sb.append(folder.getInt("id") == selected ? "selected" : "");
				sb.append(">");
				sb.append(folder.getStr("name"));
				sb.append("</option>");
			}
		}
		return sb.toString();
	}
}
