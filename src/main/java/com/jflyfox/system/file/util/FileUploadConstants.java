package com.jflyfox.system.file.util;

public class FileUploadConstants {

	/**
	 * 类型：文本
	 */
	public static final int FILE_TYPE_TXT = 1;
	/**
	 * 类型：图片
	 */
	public static final int FILE_TYPE_IMAGE = 2;
	/**
	 * 类型：视频
	 */
	public static final int FILE_TYPE_AUDIO = 3;
	/**
	 * 类型：视频
	 */
	public static final int FILE_TYPE_VIDEO = 4;
	/**
	 * 类型：压缩包
	 */
	public static final int FILE_TYPE_PACKAGE = 8;
	/**
	 * 类型：其他
	 */
	public static final int FILE_TYPE_OTHER = 9;

	/**
	 * 业务类型：上传记录
	 */
	public static final int BUSINESS_TYPE_RECORD = 1;

	/**
	 * 获取文件类型
	 * 
	 * 2017年4月5日 上午2:04:10 flyfox 369191470@qq.com
	 * 
	 * @param ext
	 * @return
	 */
	public static int getFileType(String ext) {
		if ("doc".equals(ext) || "docx".equals(ext) || "xls".equals(ext) || "xlsx".equals(ext) //
				|| "ppt".equals(ext) || "pptx".equals(ext) || "pdf".equals(ext) || "xml".equals(ext) //
				|| "json".equals(ext) || "txt".equals(ext) || "log".equals(ext) || "md".equals(ext)) {
			return FILE_TYPE_TXT;
		} else if ("bmp".equals(ext) || "jpg".equals(ext) || "jpeg".equals(ext) || "gif".equals(ext) //
				|| "png".equals(ext)) {
			return FILE_TYPE_IMAGE;
		} else if ("mp3".equals(ext) || "wav".equals(ext) || "mid".equals(ext) || "aif".equals(ext)) {
			return FILE_TYPE_AUDIO;
		} else if ("flv".equals(ext) || "swf".equals(ext) || "mkv".equals(ext) || "avi".equals(ext) //
				|| "rm".equals(ext) || "rmvb".equals(ext) || "mpeg".equals(ext) || "mpg".equals(ext) //
				|| "ogg".equals(ext) || "ogv".equals(ext) || "mov".equals(ext) || "wmv".equals(ext) //
				|| "mp4".equals(ext) || "webm".equals(ext)) {
			return FILE_TYPE_VIDEO;
		} else if ("rar".equals(ext) || "zip".equals(ext) || "tar".equals(ext) || "gz".equals(ext) //
				|| "7z".equals(ext) || "bz2".equals(ext) || "cab".equals(ext) || "iso".equals(ext)) {
			return FILE_TYPE_PACKAGE;
		}
		return FILE_TYPE_OTHER;
	}

	// "fileAllowFiles": [
	// ".png", ".jpg", ".jpeg", ".gif", ".bmp",
	// ".flv", ".swf", ".mkv", ".avi", ".rm", ".rmvb", ".mpeg", ".mpg",
	// ".ogg", ".ogv", ".mov", ".wmv", ".mp4", ".webm", ".mp3", ".wav", ".mid",
	// ], /* 上传文件格式显示 */
}
