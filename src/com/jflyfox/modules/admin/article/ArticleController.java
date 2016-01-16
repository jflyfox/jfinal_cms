package com.jflyfox.modules.admin.article;

import java.io.File;
import java.security.SecureRandom;
import java.util.List;

import com.jfinal.kit.PathKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.upload.UploadFile;
import com.jflyfox.component.base.BaseProjectController;
import com.jflyfox.jfinal.component.annotation.ControllerBind;
import com.jflyfox.jfinal.component.db.SQLUtils;
import com.jflyfox.modules.admin.comment.CommentService;
import com.jflyfox.modules.admin.folder.TbFolder;
import com.jflyfox.modules.admin.tags.TbTags;
import com.jflyfox.util.DateUtils;
import com.jflyfox.util.StrUtils;

/**
 * 联系人管理
 * 
 * @author flyfox 2014-2-11
 */
@ControllerBind(controllerKey = "/admin/article")
public class ArticleController extends BaseProjectController {

	private static final String path = "/pages/admin/article/article_";

	/**
	 * 上传临时目录
	 */
	public static final String UPLOAD_TMP_PATH = PathKit.getWebRootPath() + File.separator + "download" + File.separator + "tmp";
	/**
	 * 图片目录
	 */
	public static final String IMAGE_PATH = "download" + File.separator + "image_url";
	/**
	 * 图片全目录
	 */
	public static final String UPLOAD_IMAGE_PATH = PathKit.getWebRootPath() + File.separator + IMAGE_PATH;
	/**
	 * 文件目录
	 */
	public static final String FILE_PATH = "download" + File.separator + "file_url";
	/**
	 * 文件全目录
	 */
	public static final String UPLOAD_FILE_PATH = PathKit.getWebRootPath() + File.separator + FILE_PATH;

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
		Integer userid= getSessionUser().getUserID();
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
		
		UploadFile uploadImage = getFile("model.image_url", UPLOAD_TMP_PATH, 10 * 1024 * 1024);

		UploadFile uploadFile = getFile("model.file_url", UPLOAD_TMP_PATH, 10 * 1024 * 1024);
		
		Integer pid = getParaToInt();
		TbArticle model = getModel(TbArticle.class);

		// 图片附件
		if (uploadImage != null) {
			String fileName = renameFile(UPLOAD_IMAGE_PATH,uploadImage);
			model.set("image_url", fileName);
			// model.set("image_url", uploadFile.getFileName());
		}
		
		// 文件附件
		if (uploadFile != null) {
			String oldFileName = uploadFile.getFileName();
			String fileName = renameFile(UPLOAD_FILE_PATH, uploadFile);
			model.set("file_url", fileName);
			model.set("file_name", oldFileName); //原文件名
		} else {
			//  删除标记
			Integer file_flag = getParaToInt("file_url_flag");
			if (file_flag != null && file_flag == 1) {
				model.set("file_url", "");
				model.set("file_name", "");
			}
		}
		
		Integer userid= getSessionUser().getUserID();
		String now = getNow();
		model.put("update_id", userid);
		model.put("update_time", now);
		if (pid != null && pid > 0) { // 更新
			model.update();
		} else { // 新增
			model.remove("id");
			model.put("create_id", userid);
			model.put("create_time", now);
			if (model.get("sort") == null)
				model.put("sort", 1);
			model.save();
		}

		renderMessage("保存成功");
	}
	
	/**
	 * 重命名
	 * 
	 * 2015年9月25日 下午10:37:55
	 * flyfox 330627517@qq.com
	 * @param uploadFile
	 * @return
	 */
	protected String renameFile(String path,UploadFile uploadFile) {
		File uploadPath = new File(path);
		if (!uploadPath.exists()) {
			uploadPath.mkdirs();
		}
		
		String suf = "";
		if (uploadFile.getFileName().lastIndexOf(".") >= 0) {
			suf = uploadFile.getFileName().substring(uploadFile.getFileName().lastIndexOf("."));
		}
		String fileName = DateUtils.getNow("yyyyMMdd_HHmmss") + "_" + new SecureRandom().nextInt(999999) + suf;
		// 改名,避免重复以及中文问题
		uploadFile.getFile().renameTo(new File(path + File.separator + fileName));
		return fileName;
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
