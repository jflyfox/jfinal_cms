package com.jflyfox.modules.admin.video.controller;

import java.io.File;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.upload.UploadFile;
import com.jflyfox.component.base.BaseProjectController;
import com.jflyfox.component.util.JFlyfoxUpload;
import com.jflyfox.jfinal.component.annotation.ControllerBind;
import com.jflyfox.jfinal.component.db.SQLUtils;
import com.jflyfox.modules.admin.site.TbSite;
import com.jflyfox.modules.admin.video.model.TbVideo;
import com.jflyfox.modules.admin.video.model.TbVideoTags;
import com.jflyfox.modules.admin.video.service.VideoAlbumService;
import com.jflyfox.util.StrUtils;

/**
 * 视频
 * 
 * @author flyfox 2014-4-24
 */
@ControllerBind(controllerKey = "/admin/video")
public class VideoController extends BaseProjectController {

	private static final String path = "/pages/admin/video/video_";

	public void list() {
		TbVideo model = getModelByAttr(TbVideo.class);

		SQLUtils sql = new SQLUtils(" from tb_video t where 1=1 ");
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

		Page<TbVideo> page = TbVideo.dao.paginate(getPaginator(), "select t.* ", //
				sql.toString().toString());

		// 下拉框
		setAttr("selectAlbum", new VideoAlbumService().selectAlbum(model.getAlbumId()));

		setAttr("page", page);
		setAttr("attr", model);
		render(path + "list.html");
	}

	public void add() {
		// 获取页面信息,设置目录传入
		TbVideo attr = getModel(TbVideo.class);
		setAttr("model", attr);

		// 查询下拉框
		setAttr("selectAlbum", new VideoAlbumService().selectAlbum(attr.getAlbumId()));

		render(path + "add.html");
	}

	public void view() {
		TbVideo model = TbVideo.dao.findById(getParaToInt());
		setAttr("model", model);

		// 设置标签
		String tags = Db.findFirst("select group_concat(tagname) tags " //
				+ " from tb_video_tags where video_id = ? order by id", model.getInt("id")).getStr("tags");
		setAttr("tags", tags);

		render(path + "view.html");
	}
	
	public void show() {
		TbVideo model = TbVideo.dao.findById(getParaToInt());
		setAttr("model", model);

		render(path + "show.html");
	}

	public void delete() {
		// 日志添加
		TbVideo model = new TbVideo();
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
	 * 2016年2月5日 下午5:51:03 flyfox 369191470@qq.com
	 */
	public void del() {
		int id = getParaToInt();
		// 日志添加
		TbVideo model = new TbVideo();
		Integer userid = getSessionUser().getUserID();
		String now = getNow();
		model.put("update_id", userid);
		model.put("update_time", now);
		model.deleteById(id);

		renderMessage("删除成功");
	}

	public void edit() {
		TbVideo model = TbVideo.dao.findById(getParaToInt());
		setAttr("model", model);

		// 查询下拉框
		setAttr("selectAlbum", new VideoAlbumService().selectAlbum(model.getAlbumId()));

		// 设置标签
		String tags = Db.findFirst("select group_concat(tagname) tags " //
				+ " from tb_video_tags where video_id = ? order by id", model.getInt("id")).getStr("tags");
		setAttr("tags", tags);

		render(path + "edit.html");
	}

	public void save() {
		TbSite site = getSessionSite().getBackModel();
		UploadFile uploadVideo = getFile("model.video_url", JFlyfoxUpload.getUploadTmpPath(site), JFlyfoxUpload.UPLOAD_MAX);
		
		Integer pid = getParaToInt();
		TbVideo model = getModel(TbVideo.class);
		
		// 视频附件
		if (uploadVideo != null) {
			String fileName = JFlyfoxUpload.renameFile(JFlyfoxUpload.getUploadFilePath(site, "video"), uploadVideo);
			model.set("video_url", JFlyfoxUpload.getUploadPath(site, "video") + File.separator + fileName);
		}
		
		// 设置图片信息
		if (StrUtils.isNotEmpty(model.getVideoNetUrl())) {
			String file = model.getVideoNetUrl();
			model.setExt(file.substring(file.lastIndexOf(".") + 1));
		} else if (StrUtils.isNotEmpty(model.getVideoUrl())) {
			String file = model.getVideoUrl();
			model.setExt(file.substring(file.lastIndexOf(".") + 1));
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
			Db.update(" delete from tb_video_tags where video_id = ?", model.getInt("id"));
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
				TbVideoTags tag = new TbVideoTags();
				tag.put("tagname", tagname);
				tag.put("video_id", model.getInt("id"));
				tag.put("create_id", getSessionUser().getUserID());
				tag.put("create_time", getNow());
				tag.save();

			}
		}
		
		renderMessage("保存成功");
	}
}
