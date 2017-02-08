package com.jflyfox.modules.front.service;

import java.util.List;

import com.jfinal.plugin.activerecord.Page;
import com.jflyfox.jfinal.base.BaseService;
import com.jflyfox.jfinal.base.Paginator;
import com.jflyfox.modules.admin.video.model.TbVideo;
import com.jflyfox.modules.admin.video.model.TbVideoAlbum;
import com.jflyfox.util.cache.CacheManager;

public class FrontVideoService extends BaseService {

	private final static String cacheName = "FrontVideoService";

	/**
	 * 更新缓存,清空
	 * 
	 * 2015年4月29日 下午4:37:40 flyfox 369191470@qq.com
	 */
	public void clearCache() {
		CacheManager.get(cacheName).clear();
	}

	/**
	 * 返回相册列表
	 * 
	 * 2016年2月10日 上午3:05:07 flyfox 369191470@qq.com
	 * 
	 * @return
	 */
	public List<TbVideoAlbum> getAlbumList() {
		String key = "albumList";
		String sql = "select * from tb_video_album t where  status = 1 order by sort,id desc";
		return TbVideoAlbum.dao.findCache(cacheName, key, sql);
	}

	/**
	 * 查询相册
	 * 
	 * 2016年2月10日 上午2:51:57 flyfox 369191470@qq.com
	 * 
	 * @param albumId
	 * @return
	 */
	public TbVideoAlbum getAlbum(Integer albumId) {
		String key = "album_" + albumId;
		String sql = "select * from tb_video_album t where id = ? ";
		return TbVideoAlbum.dao.findFirstCache(cacheName, key, sql, albumId);
	}

	/**
	 * 查询图片
	 * 
	 * 2016年2月10日 上午3:45:05
	 * flyfox 369191470@qq.com
	 * @param paginator
	 * @return
	 */
	public Page<TbVideo> getVideo(Paginator paginator) {
		String key = ("article_" + paginator.getPageNo() + "_" + paginator.getPageSize());
		Page<TbVideo> videos = TbVideo.dao.paginateCache(cacheName, key, paginator, "select * " //
				, " from tb_video " //
						+ " where status = 1 " // 查询状态为显示
						+ " order by sort,create_time desc");
		return videos;
	}

	/**
	 * 查询图片
	 * 
	 * 2016年2月10日 上午3:45:18
	 * flyfox 369191470@qq.com
	 * @param paginator
	 * @param albumId
	 * @return
	 */
	public Page<TbVideo> getVideo(Paginator paginator, int albumId) {
		String key = ("article_" + albumId + "_" + paginator.getPageNo() + "_" + paginator.getPageSize());
		Page<TbVideo> videos = TbVideo.dao.paginateCache(cacheName, key, paginator, "select * " //
				, " from tb_video " //
						+ " where status = 1 " // 查询状态为显示
						+ " and album_id =  ? " //
						+ " order by sort,create_time desc", albumId);
		return videos;
	}

	/**
	 * 查询图片
	 * 
	 * 2016年2月10日 上午3:45:26
	 * flyfox 369191470@qq.com
	 * @param videoId
	 * @return
	 */
	public TbVideo getVideo(Integer videoId) {
		String key = "video_" + videoId;
		String sql = "select * from tb_video t where id = ? ";
		return TbVideo.dao.findFirstCache(cacheName, key, sql, videoId);
	}

	/**
	 * 查询推荐图片
	 * 
	 * 2015年4月29日 下午4:48:24 flyfox 369191470@qq.com
	 * 
	 * @param paginator
	 * @param folder_id
	 * @return
	 */
	public Page<TbVideo> getRecommendVideos(Paginator paginator) {
		String key = ("recommendVideo_" + paginator.getPageNo() + "_" + paginator.getPageSize());
		Page<TbVideo> articles = TbVideo.dao.paginateCache(cacheName, key, paginator, "select * " //
				, " from tb_video  where status = 1 " //
						+ " and is_recommend = 1 " // 推荐文章
						+ " order by sort,create_time desc");
		return articles;
	}
}
