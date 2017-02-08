/*
 *	Filemanager.java utility class for for filemanager.jsp
 *
 *	@license	MIT License
 *	@author		Dick Toussaint <d.tricky@gmail.com>
 *	@copyright	Authors
 */
package com.jflyfox.modules.filemanager;

import java.awt.Dimension;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.ImageIcon;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.kit.PathKit;
import com.jfinal.log.Log;
import com.jflyfox.util.Config;
import com.jflyfox.util.NumberUtils;
import com.jflyfox.util.StrUtils;

public class FileManager {

	private final static Log logger = Log.getLog(FileManager.class);

	private static final String BAKUP = getConfig("bakup");
	private static final String ROOT_PATH = getConfig("root-path");
	private static final String ACTION_PATH = getConfig("action-path");
	private static final String MAX_SIZE = getConfig("max-size");
	private static final String TMP_PATH = getConfig("tmp-path");

	private static final boolean BAKUP_FLAG = Boolean.valueOf(getConfig("bakup-flag"));

	protected static JSONObject language = null;
	protected Map<String, String> get = new HashMap<String, String>();
	protected Map<String, String> properties = new HashMap<String, String>();
	protected Map<String, Object> item = new HashMap<String, Object>();
	protected Map<String, String> params = new HashMap<String, String>();
	protected String fileRoot = "";
	protected String referer = "";

	protected JSONObject error = null;

	SimpleDateFormat dateFormat;
	List<?> files = null;

	public FileManager(HttpServletRequest request) {
		// get document root like in php
		String documentRoot = PathKit.getWebRootPath() + "/";

		// get uploaded file list
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			if (ServletFileUpload.isMultipartContent(request))
				files = upload.parseRequest(request);
		} catch (Exception e) {
			e.printStackTrace();
		}

		this.properties.put("Date Created", null);
		this.properties.put("Date Modified", null);
		this.properties.put("Height", null);
		this.properties.put("Width", null);
		this.properties.put("Size", null);

		if (getConfig("doc_root") != null)
			this.fileRoot = getConfig("doc_root");
		else
			this.fileRoot = documentRoot;

		dateFormat = new SimpleDateFormat(getConfig("date"));

		this.setParams();

		loadLanguageFile();
	}

	public JSONObject error(String msg) {
		JSONObject errorInfo = new JSONObject();
		try {
			errorInfo.put("Error", msg);
			errorInfo.put("Code", "-1");
			errorInfo.put("Properties", this.properties);
		} catch (JSONException e) {
			this.error("JSONObject error");
		}
		this.error = errorInfo;
		return error;
	}

	public JSONObject getError() {
		return error;
	}

	public String lang(String key) {
		String text = "";
		try {
			text = language.getString(key);
		} catch (Exception e) {
		}
		if (text == null || text == "")
			text = "Language string error on " + key;
		return text;
	}

	public boolean setGetVar(String var, String value) {
		boolean retval = false;
		if (value == null || value == "") {
			this.error(sprintf(lang("INVALID_VAR"), var));
		} else {
			this.get.put(var, sanitize(value));
			retval = true;
		}
		return retval;
	}

	public boolean setGetContent(String var, String value) {
		boolean retval = false;
		if (value == null || value == "") {
			this.error(sprintf(lang("INVALID_VAR"), var));
		} else {
			this.get.put(var, value);
			retval = true;
		}
		return retval;
	}

	public JSONObject getInfo() {
		this.item = new HashMap<String, Object>();
		this.item.put("properties", this.properties);
		this.getFileInfo("");
		JSONObject array = new JSONObject();

		try {
			array.put("Path", this.get.get("path"));
			array.put("Filename", this.item.get("filename"));
			array.put("File Type", this.item.get("filetype"));
			array.put("Preview", this.item.get("preview"));
			array.put("Properties", this.item.get("properties"));
			// TODO 文件权限
			array.put("Protected", 0);
			array.put("Error", "");
			array.put("Code", 0);
		} catch (JSONException e) {
			this.error("JSONObject error");
		}
		return array;
	}

	public JSONObject editFile() {
		JSONObject array = new JSONObject();

		// 读取文件信息
		try {
			String content = FileManagerUtils.readString(getRealFilePath());

			content = FileManagerUtils.encodeContent(content);

			array.put("Path", this.get.get("path"));
			array.put("Content", content);
			array.put("Error", "");
			array.put("Code", 0);
		} catch (JSONException e) {
			this.error("JSONObject error");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return array;
	}

	public JSONObject saveFile() {
		JSONObject array = new JSONObject();

		try {
			String content = this.get.get("content");
			content = FileManagerUtils.decodeContent(content);

			// before bakup
			bakupFile(new File(getRealFilePath()));

			FileManagerUtils.writeString(getRealFilePath(), content);
			array.put("Path", this.get.get("path"));
			array.put("Error", "");
			array.put("Code", 0);
		} catch (JSONException e) {
			logger.error("JSONObject error", e);
			this.error("JSONObject error");
		} catch (IOException e) {
			logger.error("IOException error", e);
			this.error("IOException error");
		}
		return array;
	}

	public JSONObject getFolder() {
		JSONObject array = null;
		File dir = new File(getRealFilePath());

		File file = null;
		if (!dir.isDirectory()) {
			this.error(sprintf(lang("DIRECTORY_NOT_EXIST"), this.get.get("path")));
		} else {
			if (!dir.canRead()) {
				this.error(sprintf(lang("UNABLE_TO_OPEN_DIRECTORY"), this.get.get("path")));
			} else {
				array = new JSONObject();
				String[] files = dir.list();
				JSONObject data = null;
				JSONObject props = null;
				for (int i = 0; i < files.length; i++) {
					data = new JSONObject();
					props = new JSONObject();
					file = new File(getRealFilePath() + files[i]);
					if (file.isDirectory() && !contains(getConfig("unallowed_dirs"), files[i])) {
						try {
							props.put("Date Created", (String) null);
							props.put("Date Modified", (String) null);
							props.put("Height", (String) null);
							props.put("Width", (String) null);
							props.put("Size", (String) null);
							data.put("Path", this.get.get("path") + files[i] + "/");
							data.put("Filename", files[i]);
							data.put("File Type", "dir");
							data.put("Preview", getConfig("icons-path") + getConfig("icons-directory"));

							// TODO 文件权限
							data.put("Protected", 0);
							data.put("Error", "");
							data.put("Code", 0);
							data.put("Properties", props);

							array.put(this.get.get("path") + files[i] + "/", data);
						} catch (JSONException e) {
							this.error("JSONObject error");
						}

					} else if (!contains(getConfig("unallowed_files"), files[i])) {
						this.item = new HashMap<String, Object>();
						this.item.put("properties", this.properties);
						this.getFileInfo(getFilePath() + files[i]);

						if (this.params.get("type") == null
								|| (this.params.get("type") != null && (!this.params.get("type").equals("Image") || this.params
										.get("type").equals("Image")
										&& contains(getConfig("images"), (String) this.item.get("filetype"))))) {
							try {
								data.put("Path", this.get.get("path") + files[i]);
								data.put("Filename", this.item.get("filename"));
								data.put("File Type", this.item.get("filetype"));

								// TODO 文件权限
								data.put("Protected", 0);
								data.put("Preview", this.item.get("preview"));
								data.put("Properties", this.item.get("properties"));
								data.put("Error", "");
								data.put("Code", 0);

								array.put(this.get.get("path") + files[i], data);
							} catch (JSONException e) {
								this.error("JSONObject error");
							}
						}
					}
				}
			}
		}
		return array;
	}

	public JSONObject rename() {
		String oldFile = this.get.get("old");
		String newFile = this.get.get("new");
		oldFile = getFilePath(oldFile);

		if (oldFile.endsWith("/")) {
			this.get.put("old", oldFile.substring(0, oldFile.length() - 1));
		}
		boolean error = false;
		JSONObject array = null;
		String tmp[] = oldFile.split("/");
		String filename = tmp[tmp.length - 1];
		int pos = oldFile.lastIndexOf("/");
		String path = oldFile.substring(0, pos + 1);
		File fileFrom = null;
		File fileTo = null;
		try {
			fileFrom = new File(this.fileRoot + oldFile);
			fileTo = new File(this.fileRoot + path + newFile);
			if (fileTo.exists()) {
				if (fileTo.isDirectory()) {
					this.error(sprintf(lang("DIRECTORY_ALREADY_EXISTS"), newFile));
					error = true;
				} else { // fileTo.isFile
					this.error(sprintf(lang("FILE_ALREADY_EXISTS"), newFile));
					error = true;
				}
			} else if (!fileFrom.renameTo(fileTo)) {
				this.error(sprintf(lang("ERROR_RENAMING_DIRECTORY"), filename + "#" + newFile));
				error = true;
			}
		} catch (Exception e) {
			if (fileFrom.isDirectory()) {
				this.error(sprintf(lang("ERROR_RENAMING_DIRECTORY"), filename + "#" + newFile));
			} else {
				this.error(sprintf(lang("ERROR_RENAMING_FILE"), filename + "#" + newFile));
			}
			error = true;
		}
		if (!error) {
			array = new JSONObject();
			try {
				array.put("Error", "");
				array.put("Code", 0);
				array.put("Old Path", this.get.get("old"));
				array.put("Old Name", filename);
				array.put("New Path", path + this.get.get("new"));
				array.put("New Name", this.get.get("new"));
			} catch (JSONException e) {
				this.error("JSONObject error");
			}
		}
		return array;
	}

	public JSONObject delete() {
		JSONObject array = null;
		File file = new File(getRealFilePath());
		if (file.isDirectory()) {
			array = new JSONObject();
			this.unlinkRecursive(getRealFilePath(), true);
			try {
				array.put("Error", "");
				array.put("Code", 0);
				array.put("Path", this.get.get("path"));
			} catch (Exception e) {
				this.error("JSONObject error");
			}
		} else if (file.exists()) {
			array = new JSONObject();

			try {
				// before bakup
				bakupFile(file);

				if (file.delete()) {
					try {
						array.put("Error", "");
						array.put("Code", 0);
						array.put("Path", this.get.get("path"));
					} catch (JSONException e) {
						this.error("JSONObject error");
					}
				} else {
					this.error(sprintf(lang("ERROR_DELETING FILE"), this.get.get("path")));
				}
			} catch (IOException e) {
				logger.error(sprintf(lang("ERROR_DELETING FILE"), this.get.get("path")), e);
				this.error(sprintf(lang("ERROR_DELETING FILE"), this.get.get("path")));
			}

			return array;
		} else {
			this.error(lang("INVALID_DIRECTORY_OR_FILE"));
		}
		return array;
	}

	public JSONObject add() {
		Iterator<?> it = this.files.iterator();
		if (!it.hasNext()) {
			this.error(lang("INVALID_FILE_UPLOAD"));
			return null;
		}

		JSONObject fileInfo = null;
		Map<String, String> params = new HashMap<String, String>();
		File tmpFile = null;
		boolean error = false;

		// file field operate
		try {
			FileItem item = null;
			while (it.hasNext()) {
				item = (FileItem) it.next();
				if (item.isFormField()) {
					params.put(item.getFieldName(), item.getString());
				} else {
					params.put("_fileFieldName", item.getFieldName());
					params.put("_fileName", item.getName());
					params.put(item.getFieldName(), item.getName());

					long maxSize = NumberUtils.parseLong(MAX_SIZE);
					if (getConfig("upload-size") != null) {
						maxSize = Integer.parseInt(getConfig("upload-size"));
						if (maxSize != 0 && item.getSize() > (maxSize * 1024 * 1024)) {
							this.error(sprintf(lang("UPLOAD_FILES_SMALLER_THAN"), maxSize + "Mb"));
							error = true;
						}
					}

					if (!isImage(item.getName())
							&& (getConfig("upload-imagesonly") != null && getConfig("upload-imagesonly").equals("true") || this.params
									.get("type") != null && this.params.get("type").equals("Image"))) {
						this.error(lang("UPLOAD_IMAGES_ONLY"));
						error = true;
					}

					if (error) {
						break;
					}

					tmpFile = new File(this.fileRoot + TMP_PATH + "filemanager_" + System.currentTimeMillis() + ".tmp");
					File filePath = tmpFile.getParentFile();
					if (!filePath.exists()) {
						filePath.mkdirs();
					}
					item.write(tmpFile);
				}
			}

		} catch (Exception e) {
			logger.error("INVALID_FILE_UPLOAD", e);
			this.error(lang("INVALID_FILE_UPLOAD"));
		}

		// file rename
		try {
			if (!error && tmpFile != null) {
				String allowed[] = { ".", "-" };

				if ("add".equals(params.get("mode"))) {
					fileInfo = new JSONObject();
					String respPath = "";

					String currentPath = "";
					String fileName = params.get("_fileName");
					String filePath = "";
					try {
						currentPath = params.get("currentpath");
						respPath = currentPath;
						currentPath = new String(currentPath.getBytes("ISO8859-1"), "UTF-8"); // 中文转码
						currentPath = getFilePath(currentPath);
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
					filePath = FileManagerUtils.rebulid(this.fileRoot + currentPath);

					LinkedHashMap<String, String> strList = new LinkedHashMap<String, String>();
					strList.put("fileName", fileName);
					fileName = (String) cleanString(strList, allowed).get("fileName");

					if (getConfig("upload-overwrite").equals("false")) {
						fileName = this.checkFilename(filePath, fileName, 0);
					}

					File saveFile = new File(filePath + fileName);
					tmpFile.renameTo(saveFile);

					fileInfo.put("Path", respPath);
					fileInfo.put("Name", fileName);
					fileInfo.put("Error", "");
					fileInfo.put("Code", 0);
				} else if ("replace".equals(params.get("mode"))) {
					fileInfo = new JSONObject();
					String respPath = "";

					String fileName = "";
					String newFilePath = "";
					String saveFilePath = "";

					try {
						newFilePath = params.get("newfilepath");
						newFilePath = new String(newFilePath.getBytes("ISO8859-1"), "UTF-8"); // 中文转码
						respPath = newFilePath.substring(0, newFilePath.lastIndexOf("/") + 1);
						fileName = newFilePath.substring(newFilePath.lastIndexOf("/") + 1);
						newFilePath = getFilePath(newFilePath);
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}

					saveFilePath = FileManagerUtils.rebulid(this.fileRoot + newFilePath);
					File saveFile = new File(saveFilePath);

					LinkedHashMap<String, String> strList = new LinkedHashMap<String, String>();
					strList.put("fileName", fileName);
					fileName = (String) cleanString(strList, allowed).get("fileName");

					if (getConfig("upload-overwrite").equals("false")) {
						fileName = this.checkFilename(saveFile.getParent(), fileName, 0);
					}

					if (saveFile.exists()) {
						// before bakup
						bakupFile(saveFile);
						// delete src file
						saveFile.delete();
					}

					tmpFile.renameTo(saveFile);

					fileInfo.put("Path", respPath);
					fileInfo.put("Name", fileName);
					fileInfo.put("Error", "");
					fileInfo.put("Code", 0);
				} else {
					this.error(lang("INVALID_FILE_UPLOAD"));
				}

			}
		} catch (Exception e) {
			logger.error("INVALID_FILE_UPLOAD", e);
			this.error(lang("INVALID_FILE_UPLOAD"));
		}

		// 临时文件处理
		if (tmpFile.exists()) {
			tmpFile.delete();
		}

		return fileInfo;

	}

	public JSONObject addFolder() {
		JSONObject array = null;
		String allowed[] = { "-", " " };
		LinkedHashMap<String, String> strList = new LinkedHashMap<String, String>();
		strList.put("fileName", this.get.get("name"));
		String filename = (String) cleanString(strList, allowed).get("fileName");
		if (filename.length() == 0) // the name existed of only special
									// characters
			this.error(sprintf(lang("UNABLE_TO_CREATE_DIRECTORY"), this.get.get("name")));
		else {
			File file = new File(getRealFilePath() + filename);
			if (file.isDirectory()) {
				this.error(sprintf(lang("DIRECTORY_ALREADY_EXISTS"), filename));
			} else if (!file.mkdir()) {
				this.error(sprintf(lang("UNABLE_TO_CREATE_DIRECTORY"), filename));
			} else {
				try {
					array = new JSONObject();
					array.put("Parent", this.get.get("path"));
					array.put("Name", filename);
					array.put("Error", "");
					array.put("Code", 0);
				} catch (JSONException e) {
					this.error("JSONObject error");
				}
			}
		}
		return array;
	}

	public void download(HttpServletResponse resp) {
		File file = new File(getRealFilePath());
		if (this.get.get("path") != null && file.exists()) {
			resp.setHeader("Content-type", "application/force-download");
			resp.setHeader("Content-Disposition", "inline;filename=\"" + fileRoot + this.get.get("path") + "\"");
			resp.setHeader("Content-Transfer-Encoding", "Binary");
			resp.setHeader("Content-length", "" + file.length());
			resp.setHeader("Content-Type", "application/octet-stream");
			resp.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");
			readFile(resp, file);
		} else {
			this.error(sprintf(lang("FILE_DOES_NOT_EXIST"), this.get.get("path")));
		}
	}

	private void readFile(HttpServletResponse resp, File file) {
		OutputStream os = null;
		FileInputStream fis = null;
		try {
			os = resp.getOutputStream();
			fis = new FileInputStream(file);
			byte fileContent[] = new byte[(int) file.length()];
			fis.read(fileContent);
			os.write(fileContent);
		} catch (Exception e) {
			this.error(sprintf(lang("INVALID_DIRECTORY_OR_FILE"), file.getName()));
		} finally {
			try {
				if (os != null)
					os.close();
			} catch (Exception e2) {
			}
			try {
				if (fis != null)
					fis.close();
			} catch (Exception e2) {
			}
		}
	}

	public void preview(HttpServletResponse resp) {
		File file = new File(getRealFilePath());
		if (this.get.get("path") != null && file.exists()) {
			resp.setHeader("Content-type", "image/" + getFileExtension(file.getName()));
			resp.setHeader("Content-Transfer-Encoding", "Binary");
			resp.setHeader("Content-length", "" + file.length());
			resp.setHeader("Content-Disposition", "inline; filename=\"" + getFileBaseName(file.getName()) + "\"");
			readFile(resp, file);
		} else {
			error(sprintf(lang("FILE_DOES_NOT_EXIST"), this.get.get("path")));
		}
	}

	private String getFileBaseName(String filename) {
		String retval = filename;
		int pos = filename.lastIndexOf(".");
		if (pos > 0)
			retval = filename.substring(0, pos);
		return retval;
	}

	private String getFileExtension(String filename) {
		String retval = filename;
		int pos = filename.lastIndexOf(".");
		if (pos > 0)
			retval = filename.substring(pos + 1);
		return retval;
	}

	private void setParams() {
		String[] tmp = this.referer.split("\\?");
		String[] params_tmp = null;
		LinkedHashMap<String, String> params = new LinkedHashMap<String, String>();
		if (tmp.length > 1 && tmp[1] != "") {
			params_tmp = tmp[1].split("&");
			for (int i = 0; i < params_tmp.length; i++) {
				tmp = params_tmp[i].split("=");
				if (tmp.length > 1 && tmp[1] != "") {
					params.put(tmp[0], tmp[1]);
				}
			}
		}
		this.params = params;
	}

	public String getConfigString(String key) {
		return getConfig(key);
	}

	public String getDocumentRoot() {
		return this.fileRoot;
	}

	private void getFileInfo(String path) {
		String pathTmp = path;
		if (pathTmp == "") {
			pathTmp = getFilePath();
		}
		String[] tmp = pathTmp.split("/");
		File file = new File(this.fileRoot + pathTmp);
		this.item = new HashMap<String, Object>();
		String fileName = tmp[tmp.length - 1];
		this.item.put("filename", fileName);
		if (file.isFile())
			this.item.put("filetype", fileName.substring(fileName.lastIndexOf(".") + 1));
		else
			this.item.put("filetype", "dir");
		this.item.put("filemtime", "" + file.lastModified());
		this.item.put("filectime", "" + file.lastModified());

		this.item.put("preview", getConfig("icons-path") + "/" + getConfig("icons-default")); // @simo

		HashMap<String, String> props = new HashMap<String, String>();
		if (file.isDirectory()) {

			this.item.put("preview", getConfig("icons-path") + getConfig("icons-directory"));

		} else if (isImage(pathTmp)) {
			this.item.put("preview", ACTION_PATH + "?mode=preview&path=" + pathTmp);
			Dimension imgData = getImageSize(fileRoot + pathTmp);
			props.put("Height", "" + imgData.height);
			props.put("Width", "" + imgData.width);
			props.put("Size", "" + file.length());
		} else {
			File icon = new File(fileRoot + getConfig("icons-path")
					+ ((String) this.item.get("filetype")).toLowerCase() + ".png");
			if (icon.exists()) {
				this.item.put("preview", getConfig("icons-path") + ((String) this.item.get("filetype")).toLowerCase()
						+ ".png");
				props.put("Size", "" + file.length());
			}
		}

		props.put("Date Modified", dateFormat.format(new Date(new Long((String) this.item.get("filemtime")))));
		this.item.put("properties", props);
	}

	private boolean isImage(String fileName) {
		boolean isImage = false;
		String ext = "";
		int pos = fileName.lastIndexOf(".");
		if (pos > 1 && pos != fileName.length()) {
			ext = fileName.substring(pos + 1);
			isImage = contains(getConfig("images"), ext);
		}
		return isImage;
	}

	public boolean contains(String where, String what) {
		boolean retval = false;

		String[] tmp = where.split(",");
		for (int i = 0; i < tmp.length; i++) {
			if (what.equalsIgnoreCase(tmp[i])) {
				retval = true;
				break;
			}
		}
		return retval;
	}

	private Dimension getImageSize(String path) {
		Dimension imgData = new Dimension();
		Image img = new ImageIcon(path).getImage();
		imgData.height = img.getHeight(null);
		imgData.width = img.getWidth(null);
		return imgData;
	}

	private void unlinkRecursive(String dir, boolean deleteRootToo) {
		File dh = new File(dir);
		File fileOrDir = null;

		if (dh.exists()) {
			String[] objects = dh.list();
			for (int i = 0; i < objects.length; i++) {
				fileOrDir = new File(dir + "/" + objects[i]);
				if (fileOrDir.isDirectory()) {
					if (!objects[i].equals(".") && !objects[i].equals("..")) {
						unlinkRecursive(dir + "/" + objects[i], true);
					}
				}
				fileOrDir.delete();

			}
			if (deleteRootToo) {
				dh.delete();
			}
		}
	}

	private HashMap<String, String> cleanString(HashMap<String, String> strList, String[] allowed) {
		String allow = "";
		HashMap<String, String> cleaned = null;
		Iterator<String> it = null;
		String cleanStr = null;
		String key = null;
		for (int i = 0; i < allowed.length; i++) {
			allow += "\\" + allowed[i];
		}

		if (strList != null) {
			cleaned = new HashMap<String, String>();
			it = strList.keySet().iterator();
			while (it.hasNext()) {
				key = it.next();
				if (Boolean.valueOf(getConfig("chars_only_latin"))) {
					// 去除特殊字符和中文
					cleanStr = strList.get(key).replaceAll("[^{" + allow + "}_a-zA-Z0-9]", "");
				}
				cleanStr = strList.get(key);
				cleaned.put(key, cleanStr);
			}
		}
		return cleaned;
	}

	private String sanitize(String var) {
		String sanitized = var.replaceAll("\\<.*?>", "");
		sanitized = sanitized.replaceAll("http://", "");
		sanitized = sanitized.replaceAll("https://", "");
		sanitized = sanitized.replaceAll("\\.\\./", "");
		return sanitized;
	}

	private String checkFilename(String path, String filename, int i) {
		File file = new File(path + filename);
		String i2 = "";
		String[] tmp = null;
		if (!file.exists()) {
			return filename;
		} else {
			if (i != 0)
				i2 = "" + i;
			tmp = filename.split(i2 + "\\.");
			i++;
			filename = filename.replace(i2 + "." + tmp[tmp.length - 1], i + "." + tmp[tmp.length - 1]);
			return this.checkFilename(path, filename, i);
		}
	}

	private void loadLanguageFile() {

		// we load langCode var passed into URL if present
		// else, we use default configuration var
		if (language == null) {
			String lang = "";
			if (params.get("langCode") != null)
				lang = this.params.get("langCode");
			else
				lang = getConfig("culture");
			BufferedReader br = null;
			InputStreamReader isr = null;
			String text;
			StringBuffer contents = new StringBuffer();
			try {
				isr = new InputStreamReader(new FileInputStream(this.fileRoot + ROOT_PATH + "scripts/languages/" + lang
						+ ".js"), "UTF-8");
				br = new BufferedReader(isr);
				while ((text = br.readLine()) != null)
					contents.append(text);
				language = JSONObject.parseObject(contents.toString());
			} catch (Exception e) {
				this.error("Fatal error: Language file not found.");
			} finally {
				try {
					if (br != null)
						br.close();
				} catch (Exception e2) {
				}
				try {
					if (isr != null)
						isr.close();
				} catch (Exception e2) {
				}
			}
		}
	}

	/**
	 * bakup file,before save file,replace file and delete file
	 * 
	 * 2016年2月26日 下午3:47:37 flyfox 369191470@qq.com
	 * 
	 * @return
	 * @throws IOException
	 */
	protected void bakupFile(File src) throws IOException {
		if (!BAKUP_FLAG) {
			return;
		}

		String targetPath = FileManagerUtils.rebulid(src.getPath());
		String rootPath = FileManagerUtils.rebulid(this.fileRoot);
		if (!targetPath.startsWith(rootPath)) {
			logger.warn("bakup dir error:" + src.getPath());
			return;
		}

		// 备份目录下面文件不需要再进行备份
//		String srcPath = FileManagerUtils.rebulid(src.getParent());
//		if (srcPath.startsWith(rootPath + BAKUP)) {
//			logger.debug("file is bakup:" + src.getPath());
//			return;
//		}

		targetPath = rootPath + BAKUP //
				+ targetPath.substring(targetPath.indexOf(this.fileRoot) + this.fileRoot.length());
		File target = new File(targetPath);
		// not exist dir, create it
		if (!target.getParentFile().exists()) {
			target.getParentFile().mkdirs();
		}
		// exist file, delete it
		if (target.exists()) {
			target.delete();
		}
		FileManagerUtils.copyFile(src, target);
	}

	/**
	 * get Real File Path
	 * 
	 * 2016年2月26日 下午3:47:37 flyfox 369191470@qq.com
	 * 
	 * @return
	 */
	protected String getRealFilePath() {
		String path = this.fileRoot + getFilePath();
		return FileManagerUtils.rebulid(path);
	}

	/**
	 * get File Path
	 * 
	 * 2016年2月26日 下午3:47:37 flyfox 369191470@qq.com
	 * 
	 * @return
	 */
	protected String getFilePath() {
		String path = this.get.get("path");
		return getFilePath(path);
	}

	/**
	 * get File Path
	 * 
	 * 2016年2月26日 下午3:47:37 flyfox 369191470@qq.com
	 * 
	 * @return
	 */
	protected String getFilePath(String path) {
		String contextPath = this.get.get("contextPath");
		// 根目录
		if (StrUtils.isEmpty(contextPath)) {
			return path;
		}
		
		if (path.startsWith(contextPath)) {
			path = path.replaceFirst(contextPath, "");
		}
		return path;
	}

	public String sprintf(String text, String params) {
		String retText = text;
		String[] repl = params.split("#");
		for (int i = 0; i < repl.length; i++) {
			retText = retText.replaceFirst("%s", repl[i]);
		}
		return retText;
	}

	public static String getConfig(String msg) {
		return Config.getStr("filemanager." + msg);
	}

	public void log(String filename, String msg) {
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(filename, true));
			out.append(msg + "\r\n");
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
