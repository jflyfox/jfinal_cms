package com.jflyfox.system.log;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.log.Log;
import com.jflyfox.component.base.BaseProjectModel;
import com.jflyfox.jfinal.component.annotation.ModelBind;
import com.jflyfox.system.dict.SysDictDetail;

@ModelBind(table = "sys_log")
public class SysLog extends BaseProjectModel<SysLog> {

	private static final long serialVersionUID = 1L;

	private final static Log log = Log.getLog(SysLog.class);
	
	public static final SysLog dao = new SysLog();
	/**
	 * 表中文转换
	 */
	private static final Map<String, String> tableMap = new HashMap<String, String>();

	public static void init() {
		log.info("####日志配置初始化......");
		tableMap.clear();
		List<SysDictDetail> list = SysDictDetail.dao.findByWhere(" where dict_type = 'systemLog' order by detail_sort ");
		for (SysDictDetail detail : list) {
			tableMap.put(detail.getStr("detail_name"), detail.getStr("detail_code"));
		}

	}

	/**
	 * 获取表中文备注
	 * 
	 * 2015年10月16日 上午11:59:45 flyfox 369191470@qq.com
	 * 
	 * @param tableName
	 * @return
	 */
	public static String getTableRemark(String tableName) {
		return tableMap.get(tableName);
	}

	public static final Integer TYPE_MODEL = 1;
	public static final Integer TYPE_SYSTEM = 2;

	public static final String MODEL_SAVE = "添加";
	public static final String MODEL_UPDATE = "更新";
	public static final String MODEL_DELETE = "删除";
	public static final String SYSTEM_LOGIN = "登入";
	public static final String SYSTEM_LOGOUT = "登出";

	// columns START
	private String ID = "id"; //
	private String LOG_TYPE = "log_type"; // 类型
	private String OPER_OBJECT = "oper_object"; // 操作对象
	private String OPER_TABLE = "oper_table"; // 操作表
	private String OPER_ID = "oper_id"; // 操作主键
	private String OPER_TYPE = "oper_type"; // 操作类型
	private String OPER_REMARK = "oper_remark"; // 操作备注
	private String CREATE_TIME = "create_time"; // 创建时间
	private String CREATE_ID = "create_id"; // 创建者

	public SysLog setId(java.lang.Integer value) {
		set(ID, value);
		return this;
	}

	public java.lang.Integer getId() {
		return get(ID);
	}

	public SysLog setLogType(java.lang.Integer value) {
		set(LOG_TYPE, value);
		return this;
	}

	public java.lang.Integer getLogType() {
		return get(LOG_TYPE);
	}

	public SysLog setOperObject(java.lang.String value) {
		set(OPER_OBJECT, value);
		return this;
	}

	public java.lang.String getOperObject() {
		return get(OPER_OBJECT);
	}

	public SysLog setOperTable(java.lang.String value) {
		set(OPER_TABLE, value);
		return this;
	}

	public java.lang.String getOperTable() {
		return get(OPER_TABLE);
	}

	public SysLog setOperId(java.lang.Integer value) {
		set(OPER_ID, value);
		return this;
	}

	public java.lang.Integer getOperId() {
		return get(OPER_ID);
	}

	public SysLog setOperType(java.lang.String value) {
		set(OPER_TYPE, value);
		return this;
	}

	public java.lang.String getOperType() {
		return get(OPER_TYPE);
	}

	public SysLog setOperRemark(java.lang.String value) {
		set(OPER_REMARK, value);
		return this;
	}

	public java.lang.String getOperRemark() {
		return get(OPER_REMARK);
	}

	public SysLog setCreateTime(java.lang.String value) {
		set(CREATE_TIME, value);
		return this;
	}

	public java.lang.String getCreateTime() {
		return get(CREATE_TIME);
	}

	public SysLog setCreateId(java.lang.Integer value) {
		set(CREATE_ID, value);
		return this;
	}

	public java.lang.Integer getCreateId() {
		return get(CREATE_ID);
	}
}