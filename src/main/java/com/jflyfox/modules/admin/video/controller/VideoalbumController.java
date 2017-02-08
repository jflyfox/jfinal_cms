package com.jflyfox.modules.admin.video.controller;

import java.util.List;

import com.jfinal.plugin.activerecord.Page;
import com.jflyfox.component.base.BaseProjectController;
import com.jflyfox.jfinal.component.annotation.ControllerBind;
import com.jflyfox.jfinal.component.db.SQLUtils;
import com.jflyfox.modules.admin.video.model.TbVideo;
import com.jflyfox.modules.admin.video.model.TbVideoAlbum;
import com.jflyfox.util.StrUtils;

/**
 * 专辑
 * 
 * @author flyfox 2014-4-24
 */
@ControllerBind(controllerKey = "/admin/videoalbum")
public class VideoalbumController extends BaseProjectController {

	private static final String path = "/pages/admin/videoalbum/videoalbum_";

	public void list() {
		TbVideoAlbum model = getModelByAttr(TbVideoAlbum.class);

		SQLUtils sql = new SQLUtils(" from tb_video_album t "
				+ " left join tb_image_album f  on f.id = t.parent_id  where 1=1 ");
		if (model.getAttrValues().length != 0) {
			sql.setAlias("t");
			sql.whereLike("name", model.getStr("name"));
			sql.whereEquals("status", model.getInt("status"));
		}

		// 排序
		String orderBy = getBaseForm().getOrderBy();
		if (StrUtils.isEmpty(orderBy)) {
			sql.append(" order by t.sort,t.id desc");
		} else {
			sql.append(" order by t.").append(orderBy);
		}

		Page<TbVideoAlbum> page = TbVideoAlbum.dao.paginate(getPaginator(), "select t.*,f.name as parentName ", //
				sql.toString().toString());

		// 下拉框
		setAttr("page", page);
		setAttr("attr", model);
		render(path + "list.html");
	}

	public void add() {
		setAttr("selectParentAlbum", selectParentFolder(0, 0));
		
		render(path + "add.html");
	}

	public void view() {
		TbVideoAlbum model = TbVideoAlbum.dao.findById(getParaToInt());
		
		TbVideoAlbum album = TbVideoAlbum.dao.findById(model.getParentId());
		model.put("parentName", album != null ? album.getName() : null);
		
		setAttr("model", model);
		render(path + "view.html");
	}

	public void delete() {
		// 日志添加
		TbVideoAlbum model = new TbVideoAlbum();
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
		TbVideo imag = TbVideo.dao.findFirstByWhere(" where album_id = ? ", id);
		if (imag != null) {
			renderMessage("专辑下存在视频，不能删除");
			return;
		}
		
		// 日志添加
		TbVideoAlbum model = new TbVideoAlbum();
		Integer userid = getSessionUser().getUserID();
		String now = getNow();
		model.put("update_id", userid);
		model.put("update_time", now);
		model.deleteById(id);
				
		renderMessage("删除成功");
	}
	
	public void edit() {
		TbVideoAlbum model = TbVideoAlbum.dao.findById(getParaToInt());
		setAttr("model", model);
		
		// 下拉框
		setAttr("selectParentAlbum", selectParentFolder(model.getParentId(), model.getId()));
				
		render(path + "edit.html");
	}

	public void save() {
		Integer pid = getParaToInt();
		TbVideoAlbum model = getModel(TbVideoAlbum.class);

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
		renderMessage("保存成功");
	}
	
	/**
	 * 目录复选框
	 * 
	 * 2015年1月28日 下午5:28:40 flyfox 369191470@qq.com
	 * 
	 * @return
	 */
	private String selectParentFolder(Integer selected, Integer id) {
		List<TbVideoAlbum> list = TbVideoAlbum.dao.find(" select id,name from tb_video_album " //
				+ " where id != ? order by sort,create_time desc ", id);
		StringBuffer sb = new StringBuffer("");
		if (list != null && list.size() > 0) {
			for (TbVideoAlbum album : list) {
				sb.append("<option value=\"");
				sb.append(album.getInt("id"));
				sb.append("\" ");
				sb.append(album.getInt("id") == selected ? "selected" : "");
				sb.append(">");
				sb.append(album.getStr("name"));
				sb.append("</option>");
			}
		}
		return sb.toString();
	}
}
