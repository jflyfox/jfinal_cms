package com.jflyfox.modules.front;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Db;
import com.jflyfox.component.base.BaseProjectController;
import com.jflyfox.component.util.JFlyFoxUtils;
import com.jflyfox.jfinal.component.annotation.ControllerBind;
import com.jflyfox.modules.admin.video.model.TbVideo;
import com.jflyfox.modules.admin.video.model.TbVideoAlbum;
import com.jflyfox.modules.front.interceptor.FrontInterceptor;
import com.jflyfox.modules.front.service.FrontVideoService;
import com.jflyfox.util.NumberUtils;

@ControllerBind(controllerKey = "/album/video")
public class FrontAlbumVideoController extends BaseProjectController {

	public static final String path = "/album/";

	/**
	 * 视频专辑
	 * 
	 * 2016年2月10日 上午3:43:39 flyfox 369191470@qq.com
	 */
	@Before(FrontInterceptor.class)
	public void index() {
		String albumIdStr = getPara();
		albumIdStr = albumIdStr.substring(5);
		int albumId = NumberUtils.parseInt(albumIdStr);
		// 活动目录
		setAttr("album_selected", albumId);

		TbVideoAlbum album = new FrontVideoService().getAlbum(albumId);
		setAttr("album", album);

		setAttr("paginator", getPaginator());

		// seo：title优化
		String albumName = (album == null ? "" : album.getName() + " - ");
		setAttr(JFlyFoxUtils.TITLE_ATTR, albumName + getAttr(JFlyFoxUtils.TITLE_ATTR));

		renderAuto(path + "common_album.html");
	}

	/**
	 * 视频
	 * 
	 * 2016年2月10日 上午3:43:47 flyfox 369191470@qq.com
	 */
	@Before(FrontInterceptor.class)
	public void img() {
		int videoId = getParaToInt();
		// 活动目录
		setAttr("videoId", videoId);

		TbVideo video = new FrontVideoService().getVideo(videoId);
		setAttr("video", video);

		// 设置标签
		String tags = Db.findFirst("select group_concat(tagname) tags " //
				+ " from tb_video_tags where video_id = ? order by id", video.getId()).getStr("tags");
		setAttr("tags", tags);
				
		setAttr("paginator", getPaginator());

		// seo：title优化
		String videoName = (video == null ? "" : video.getName() + " - ");
		setAttr(JFlyFoxUtils.TITLE_ATTR, videoName + getAttr(JFlyFoxUtils.TITLE_ATTR));

		renderAuto(path + "common_video.html");
	}
}
