package com.jflyfox.modules.admin.image.service;

import java.util.List;

import com.jflyfox.modules.admin.image.model.TbImageAlbum;

public class ImageAlbumService {

	/**
	 * 查询相册
	 * 
	 * 2016年2月10日 上午1:13:09
	 * flyfox 369191470@qq.com
	 * @param albumId
	 * @return
	 */
	public TbImageAlbum getAlbum(Integer albumId) {
		return TbImageAlbum.dao.findById(albumId);
	}
	
	/**
	 * 相册复选框
	 * 
	 * 2015年1月28日 下午5:28:40 flyfox 369191470@qq.com
	 * 
	 * @return
	 */
	public String selectAlbum(Integer selected) {
		List<TbImageAlbum> list = TbImageAlbum.dao
				.find(" select id,name from tb_image_album order by sort,create_time desc ");
		StringBuffer sb = new StringBuffer("");
		if (list != null && list.size() > 0) {
			for (TbImageAlbum album : list) {
				sb.append("<option value=\"");
				sb.append(album.getId());
				sb.append("\" ");
				sb.append(album.getId() == selected ? "selected" : "");
				sb.append(">");
				sb.append(album.getName());
				sb.append("</option>");
			}
		}
		return sb.toString();
	}
}
