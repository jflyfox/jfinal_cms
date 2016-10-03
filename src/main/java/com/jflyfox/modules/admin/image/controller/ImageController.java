package com.jflyfox.modules.admin.image.controller;

import java.io.File;

import com.jfinal.kit.PathKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.upload.UploadFile;
import com.jflyfox.component.base.BaseProjectController;
import com.jflyfox.component.util.ImageModel;
import com.jflyfox.component.util.ImageUtils;
import com.jflyfox.component.util.JFlyfoxUpload;
import com.jflyfox.jfinal.component.annotation.ControllerBind;
import com.jflyfox.jfinal.component.db.SQLUtils;
import com.jflyfox.modules.admin.image.model.TbImage;
import com.jflyfox.modules.admin.image.model.TbImageTags;
import com.jflyfox.modules.admin.image.service.ImageAlbumService;
import com.jflyfox.modules.admin.site.TbSite;
import com.jflyfox.util.StrUtils;

/**
 * 图片
 * 
 * 2016年2月5日 上午11:23:24
 * flyfox 369191470@qq.com
 */
@ControllerBind(controllerKey = "/admin/image")
public class ImageController extends BaseProjectController {

	private static final String path = "/pages/admin/image/image_";

	public void list() {
		TbImage model = getModelByAttr(TbImage.class);

		SQLUtils sql = new SQLUtils(" from tb_image t where 1=1 ");
		if (model.getAttrValues().length != 0) {
			sql.setAlias("t");
			sql.whereEquals("album_id", model.getAlbumId());
			sql.whereLike("name", model.getStr("name"));
			sql.whereEquals("status", model.getInt("status"));
		}

		// 排序
		String orderBy = getBaseForm().getOrderBy();
		if (StrUtils.isEmpty(orderBy)) {
			sql.append(" order by sort,id desc");
		} else {
			sql.append(" order by ").append(orderBy);
		}
		
		Page<TbImage> page = TbImage.dao.paginate(getPaginator(), "select t.* ", //
				sql.toString().toString());

		// 下拉框
		setAttr("selectAlbum", new ImageAlbumService().selectAlbum(model.getAlbumId()));
				
		setAttr("page", page);
		setAttr("attr", model);
		render(path + "list.html");
	}

	public void add() {
		// 获取页面信息,设置目录传入
		TbImage attr = getModel(TbImage.class);
		setAttr("model", attr);

		// 查询下拉框
		setAttr("selectAlbum", new ImageAlbumService().selectAlbum(attr.getAlbumId()));
		
		render(path + "add.html");
	}

	public void view() {
		TbImage model = TbImage.dao.findById(getParaToInt());
		setAttr("model", model);
		
		// 设置标签
		String tags = Db.findFirst("select group_concat(tagname) tags " //
				+ " from tb_image_tags where image_id = ? order by id", model.getInt("id")).getStr("tags");
		setAttr("tags", tags);
		
		render(path + "view.html");
	}

	public void delete() {
		// 日志添加
		TbImage model = new TbImage();
		Integer userid = getSessionUser().getUserID();
		String now = getNow();
		model.put("update_id", userid);
		model.put("update_time", now);
		model.deleteById(getParaToInt());
				
		list();
	}
	
	/**
	 * Iframe删除
	 * 
	 * 2016年2月5日 下午5:51:03
	 * flyfox 369191470@qq.com
	 */
	public void del() {
		int id = getParaToInt();
		// 日志添加
		TbImage model = new TbImage();
		Integer userid = getSessionUser().getUserID();
		String now = getNow();
		model.put("update_id", userid);
		model.put("update_time", now);
		model.deleteById(id);
				
		renderMessage("删除成功");
	}

	public void edit() {
		TbImage model = TbImage.dao.findById(getParaToInt());
		setAttr("model", model);
		
		// 查询下拉框
		setAttr("selectAlbum", new ImageAlbumService().selectAlbum(model.getAlbumId()));
				
		// 设置标签
		String tags = Db.findFirst("select group_concat(tagname) tags " //
				+ " from tb_image_tags where image_id = ? order by id", model.getInt("id")).getStr("tags");
		setAttr("tags", tags);
				
		render(path + "edit.html");
	}

	public void save() {
		TbSite site = getSessionSite().getBackModel();
		UploadFile uploadImage = getFile("model.image_url", JFlyfoxUpload.getUploadTmpPath(site), JFlyfoxUpload.UPLOAD_MAX);
		
		Integer pid = getParaToInt();
		TbImage model = getModel(TbImage.class);
		
		// 图片附件
		if (uploadImage != null) {
			String fileName = JFlyfoxUpload.renameFile(JFlyfoxUpload.getUploadFilePath(site, "image"), uploadImage);
			model.set("image_url", JFlyfoxUpload.getUploadPath(site, "image") + File.separator + fileName);
			

		}
		
		// 设置图片信息
		if (StrUtils.isNotEmpty(model.getImageNetUrl())) {
			ImageModel imageModel = ImageUtils.getIamge(model.getImageNetUrl());
			model.setExt(imageModel.getExt());
			model.setWidth(imageModel.getWidth() + "");
			model.setHeight(imageModel.getHeight() + "");
			
			model.setLinkurl(model.getImageNetUrl());
		} else if (StrUtils.isNotEmpty(model.getImageUrl())) {
			ImageModel imageModel = ImageUtils
					.getIamge(PathKit.getWebRootPath() + File.separator + model.getImageUrl());
			model.setExt(imageModel.getExt());
			model.setWidth(imageModel.getWidth() + "");
			model.setHeight(imageModel.getHeight() + "");
			
			String linkUrl = getAttr("BASE_PATH") + model.getImageUrl();
			model.setLinkurl(linkUrl.replace("\\", "/"));
		}
		
		Integer userid = getSessionUser().getUserID();
		String now = getNow();
		model.put("update_id", userid);
		model.put("update_time", now);
		if (pid != null && pid > 0) { // 更新
			model.update();
			
		} else { // 新增
			model.remove("id");
			model.put("create_id", userid);
			model.put("create_time", now);
			model.save();
		}
		
		
		// 保存tags
		if (pid != null && pid > 0) { 
			Db.update(" delete from tb_image_tags where image_id = ?", model.getInt("id"));
		}
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
				TbImageTags tag = new TbImageTags();
				tag.put("tagname", tagname);
				tag.put("image_id", model.getInt("id"));
				tag.put("create_id", getSessionUser().getUserID());
				tag.put("create_time", getNow());
				tag.save();

			}
		}
		
		renderMessage("保存成功");
	}
}
