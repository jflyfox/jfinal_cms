package com.jflyfox.modules.admin.site;

import com.jflyfox.component.base.BaseProjectModel;
import com.jflyfox.jfinal.component.annotation.ModelBind;

@ModelBind(table = "tb_site")
public class TbSite extends BaseProjectModel<TbSite> {

	private static final long serialVersionUID = 1L;
	public static final TbSite dao = new TbSite();

	// columns START
	private String ID = "id"; // id
	private String NAME = "name"; // 名称
	private String TEMPLATE = "template"; // 模板名称
	private String TEMPLATE_MOBILE = "template_mobile"; //
	private String DOMAIN_PC = "domain_pc"; // pc端域名
	private String DOMAIN_MOBILE = "domain_mobile"; // 移动端域名
	private String DOMAIN_OTHERS = "domain_others"; // 其他域名
	private String SITE_TITLE = "site_title"; // 标题
	private String SITE_FOLDER_ID = "site_folder_id"; // 默认标题ID
	private String SITE_ARTICLE_ID = "site_article_id"; // 默认文章ID
	private String DB_URL = "db_url"; // 数据库
	private String DB_USER = "db_user"; // 数据库用户
	private String DB_PWD = "db_pwd"; // 数据库密码
	private String DB_DRIVER = "db_driver"; // 数据库驱动
	private String SORT = "sort"; // 序号
	private String STATUS = "status"; // 状态//radio/2,禁用,1,启用
	private String SITE_DEFALUT = "site_defalut"; // 默认站点：1,是,2,否
	private String UPDATE_TIME = "update_time"; // 更新时间
	private String UPDATE_ID = "update_id"; // 更新人
	private String CREATE_TIME = "create_time"; // 创建时间
	private String CREATE_ID = "create_id"; // 创建者

	public TbSite setId(java.lang.Integer value) {
		set(ID, value);
		return this;
	}

	public java.lang.Integer getId() {
		return get(ID);
	}

	public TbSite setName(java.lang.String value) {
		set(NAME, value);
		return this;
	}

	public java.lang.String getName() {
		return get(NAME);
	}

	public TbSite setTemplate(java.lang.String value) {
		set(TEMPLATE, value);
		return this;
	}

	public java.lang.String getTemplate() {
		return get(TEMPLATE);
	}

	public TbSite setTemplateMobile(java.lang.String value) {
		set(TEMPLATE_MOBILE, value);
		return this;
	}

	public java.lang.String getTemplateMobile() {
		return get(TEMPLATE_MOBILE);
	}

	public TbSite setDomainPc(java.lang.String value) {
		set(DOMAIN_PC, value);
		return this;
	}

	public java.lang.String getDomainPc() {
		return get(DOMAIN_PC);
	}

	public TbSite setDomainMobile(java.lang.String value) {
		set(DOMAIN_MOBILE, value);
		return this;
	}

	public java.lang.String getDomainMobile() {
		return get(DOMAIN_MOBILE);
	}

	public TbSite setDomainOthers(java.lang.String value) {
		set(DOMAIN_OTHERS, value);
		return this;
	}

	public java.lang.String getDomainOthers() {
		return get(DOMAIN_OTHERS);
	}

	public TbSite setSiteTitle(java.lang.String value) {
		set(SITE_TITLE, value);
		return this;
	}

	public java.lang.String getSiteTitle() {
		return get(SITE_TITLE);
	}

	public TbSite setSiteFolderId(java.lang.Integer value) {
		set(SITE_FOLDER_ID, value);
		return this;
	}

	public java.lang.Integer getSiteFolderId() {
		return get(SITE_FOLDER_ID);
	}
	
	public TbSite setSiteArticleId(java.lang.Integer value) {
		set(SITE_ARTICLE_ID, value);
		return this;
	}

	public java.lang.Integer getSiteArticleId() {
		return get(SITE_ARTICLE_ID);
	}

	public TbSite setDbUrl(java.lang.String value) {
		set(DB_URL, value);
		return this;
	}

	public java.lang.String getDbUrl() {
		return get(DB_URL);
	}

	public TbSite setDbUser(java.lang.String value) {
		set(DB_USER, value);
		return this;
	}

	public java.lang.String getDbUser() {
		return get(DB_USER);
	}

	public TbSite setDbPwd(java.lang.String value) {
		set(DB_PWD, value);
		return this;
	}

	public java.lang.String getDbPwd() {
		return get(DB_PWD);
	}

	public TbSite setDbDriver(java.lang.String value) {
		set(DB_DRIVER, value);
		return this;
	}

	public java.lang.String getDbDriver() {
		return get(DB_DRIVER);
	}

	public TbSite setSort(java.lang.Integer value) {
		set(SORT, value);
		return this;
	}

	public java.lang.Integer getSort() {
		return get(SORT);
	}

	public TbSite setStatus(java.lang.Integer value) {
		set(STATUS, value);
		return this;
	}

	public java.lang.Integer getStatus() {
		return get(STATUS);
	}

	public TbSite setSiteDefalut(java.lang.Integer value) {
		set(SITE_DEFALUT, value);
		return this;
	}

	public java.lang.Integer getSiteDefalut() {
		return get(SITE_DEFALUT);
	}
	
	public TbSite setUpdateTime(java.lang.String value) {
		set(UPDATE_TIME, value);
		return this;
	}

	public java.lang.String getUpdateTime() {
		return get(UPDATE_TIME);
	}

	public TbSite setUpdateId(java.lang.Integer value) {
		set(UPDATE_ID, value);
		return this;
	}

	public java.lang.Integer getUpdateId() {
		return get(UPDATE_ID);
	}

	public TbSite setCreateTime(java.lang.String value) {
		set(CREATE_TIME, value);
		return this;
	}

	public java.lang.String getCreateTime() {
		return get(CREATE_TIME);
	}

	public TbSite setCreateId(java.lang.Integer value) {
		set(CREATE_ID, value);
		return this;
	}

	public java.lang.Integer getCreateId() {
		return get(CREATE_ID);
	}

	@Override
	public String toString() {
		String log = "";
		log += "[id:" + getId() + "]";
		log += "[name:" + getName() + "]";
		log += "[template:" + getTemplate() + "]";
		log += "[templateMobile:" + getTemplateMobile() + "]";
		log += "[domainPc:" + getDomainPc() + "]";
		log += "[domainMobile:" + getDomainMobile() + "]";
		log += "[domainOthers:" + getDomainOthers() + "]";
		log += "[siteTitle:" + getSiteTitle() + "]";
		log += "[siteFolderId:" + getSiteFolderId() + "]";
		log += "[siteArticleId:" + getSiteArticleId() + "]";
		log += "[dbUrl:" + getDbUrl() + "]";
		log += "[dbUser:" + getDbUser() + "]";
		log += "[dbPwd:" + getDbPwd() + "]";
		log += "[dbDriver:" + getDbDriver() + "]";
		log += "[sort:" + getSort() + "]";
		log += "[status:" + getStatus() + "]";
		log += "[updateTime:" + getUpdateTime() + "]";
		log += "[updateId:" + getUpdateId() + "]";
		log += "[createTime:" + getCreateTime() + "]";
		log += "[createId:" + getCreateId() + "]";
		return log;
	}
}