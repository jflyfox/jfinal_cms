package com.jflyfox.modules.admin.article;

import com.jflyfox.util.DateUtils;
import com.jflyfox.util.NumberUtils;

public class ArticleService {

	public void copy(int id, Integer userid, String folders) {
		TbArticle model = TbArticle.dao.findById(id);
		for (String folderStr : folders.split(",")) {
			String now = DateUtils.getNow(DateUtils.DEFAULT_REGEX_YYYY_MM_DD_HH_MIN_SS);
			model.remove("id");
			model.setFolderId(NumberUtils.parseInt(folderStr));
			model.set("approve_status", ArticleConstant.APPROVE_STATUS_INIT);
			model.set("create_id", userid);
			model.set("create_time", now);
			if (model.get("sort") == null)
				model.put("sort", 10);
			model.save();
		}
	}
}
