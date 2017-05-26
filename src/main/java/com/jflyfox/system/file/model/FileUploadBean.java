package com.jflyfox.system.file.model;

import java.io.Serializable;

import com.jflyfox.system.file.util.FileUploadUtils;

public class FileUploadBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id = 0; // 文件ID
	private String name; // 文件名称
	private String factpath; // 文件路径
	private String ext; // 文件后缀
	private String originalName; // 原始文件名
	private int type; // 文件类型
	private long size; // 文件大小（不建议使用）

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return factpath.replace(FileUploadUtils.getRootPath(), "");
	}

	public String getFactpath() {
		return factpath;
	}

	public void setFactpath(String factpath) {
		this.factpath = factpath;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	public String getOriginalName() {
		return originalName;
	}

	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public String toString() {
		String log = "";
		log += "[id:" + getId() + "]";
		log += "[name:" + getName() + "]";
		log += "[path:" + getPath() + "]";
		log += "[factpath:" + getFactpath() + "]";
		log += "[ext:" + getExt() + "]";
		log += "[originalName:" + getOriginalName() + "]";
		log += "[type:" + getType() + "]";
		log += "[size:" + getSize() + "]";
		return log;
	}

}
