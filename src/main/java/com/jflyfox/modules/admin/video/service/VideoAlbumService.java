package com.jflyfox.modules.admin.video.service;

import java.util.List;

import com.jflyfox.modules.admin.video.model.TbVideoAlbum;

public class VideoAlbumService {

	/**
	 * 查询专辑
	 * 
	 * 2016年2月10日 上午1:13:09
	 * flyfox 369191470@qq.com
	 * @param albumId
	 * @return
	 */
	public TbVideoAlbum getAlbum(Integer albumId) {
		return TbVideoAlbum.dao.findById(albumId);
	}
	
	/**
	 * 专辑复选框
	 * 
	 * 2015年1月28日 下午5:28:40 flyfox 369191470@qq.com
	 * 
	 * @return
	 */
	public String selectAlbum(Integer selected) {
		List<TbVideoAlbum> list = TbVideoAlbum.dao
				.find(" select id,name from tb_video_album order by sort,create_time desc ");
		StringBuffer sb = new StringBuffer("");
		if (list != null && list.size() > 0) {
			for (TbVideoAlbum album : list) {
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
