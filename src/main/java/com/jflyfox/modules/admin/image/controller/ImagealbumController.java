package com.jflyfox.modules.admin.image.controller;

import java.util.List;

import com.jfinal.plugin.activerecord.Page;
import com.jflyfox.component.base.BaseProjectController;
import com.jflyfox.jfinal.component.annotation.ControllerBind;
import com.jflyfox.jfinal.component.db.SQLUtils;
import com.jflyfox.modules.admin.image.model.TbImage;
import com.jflyfox.modules.admin.image.model.TbImageAlbum;
import com.jflyfox.util.StrUtils;

/**
 * 目录
 * 
 * 2016年2月5日 上午11:23:29
 * flyfox 369191470@qq.com
 */
@ControllerBind(controllerKey = "/admin/imagealbum")
public class ImagealbumController extends BaseProjectController {

	private static final String path = "/pages/admin/imagealbum/imagealbum_";

	public void list() {
		TbImageAlbum model = getModelByAttr(TbImageAlbum.class);

		SQLUtils sql = new SQLUtils(" from tb_image_album t "
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
				
		Page<TbImageAlbum> page = TbImageAlbum.dao.paginate(getPaginator(), "select t.*,f.name as parentName ", //
				sql.toString().toString());

		// 下拉框
		setAttr("page", page);
		setAttr("attr", model);
		render(path + "list.html");
	}

	public void add() {
		setAttr("selectParentFolder", selectParentFolder(0, 0));
		
		render(path + "add.html");
	}

	public void view() {
		TbImageAlbum model = TbImageAlbum.dao.findById(getParaToInt());
		
		TbImageAlbum album = TbImageAlbum.dao.findById(model.getParentId());
		model.put("parentName", album != null ? album.getName() : null);
		
		setAttr("model", model);
		render(path + "view.html");
	}

	public void delete() {
		// 日志添加
		TbImageAlbum model = new TbImageAlbum();
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
		TbImage imag = TbImage.dao.findFirstByWhere(" where album_id = ? ", id);
		if (imag != null) {
			renderMessage("相册下存在图片，不能删除");
			return;
		}
		
		// 日志添加
		TbImageAlbum model = new TbImageAlbum();
		Integer userid = getSessionUser().getUserID();
		String now = getNow();
		model.put("update_id", userid);
		model.put("update_time", now);
		model.deleteById(id);
				
		renderMessage("删除成功");
	}

	public void edit() {
		TbImageAlbum model = TbImageAlbum.dao.findById(getParaToInt());
		setAttr("model", model);
		
		// 下拉框
		setAttr("selectParentFolder", selectParentFolder(model.getParentId(), model.getId()));
				
		render(path + "edit.html");
	}

	public void save() {
		Integer pid = getParaToInt();
		TbImageAlbum model = getModel(TbImageAlbum.class);
		
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
		List<TbImageAlbum> list = TbImageAlbum.dao.find(" select id,name from tb_image_album " //
				+ " where id != ? order by sort,create_time desc ", id);
		StringBuffer sb = new StringBuffer("");
		if (list != null && list.size() > 0) {
			for (TbImageAlbum album : list) {
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
