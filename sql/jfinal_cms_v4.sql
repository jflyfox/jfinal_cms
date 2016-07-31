/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50610
Source Host           : localhost:3306
Source Database       : jfinal_cms2

Target Server Type    : MYSQL
Target Server Version : 50610
File Encoding         : 65001

Date: 2016-05-12 20:39:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `sys_department`
-- ----------------------------
DROP TABLE IF EXISTS `sys_department`;
CREATE TABLE `sys_department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) DEFAULT '0' COMMENT '上级机构',
  `name` varchar(32) NOT NULL COMMENT '部门/11111',
  `code` varchar(128) DEFAULT NULL COMMENT '机构编码',
  `sort` int(11) DEFAULT '0' COMMENT '序号',
  `linkman` varchar(64) DEFAULT NULL COMMENT '联系人',
  `linkman_no` varchar(32) DEFAULT NULL COMMENT '联系人电话',
  `remark` varchar(128) DEFAULT NULL COMMENT '机构描述',
  `update_time` varchar(64) DEFAULT NULL COMMENT '更新时间',
  `update_id` int(11) DEFAULT '0' COMMENT '更新者',
  `create_time` varchar(64) DEFAULT NULL COMMENT '创建时间',
  `create_id` int(11) DEFAULT '0' COMMENT '创建者',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='部门';

-- ----------------------------
-- Records of sys_department
-- ----------------------------
INSERT INTO `sys_department` VALUES ('1', '0', '系统承建单位', null, '99', 'system', '15888888888', null, '2016-06-06 06:06:06', '1', '2016-06-06 06:06:06', '1');
INSERT INTO `sys_department` VALUES ('2', '0', '注册用户', null, '97', '无人', '15888888888', null, '2015-04-28 22:39:34', '1', '2015-04-28 22:39:34', '1');
INSERT INTO `sys_department` VALUES ('3', '0', '第三方用户', null, '90', '无人', '15888888888', null, '2015-06-01 12:39:41', '1', '2015-06-01 12:39:41', '1');
INSERT INTO `sys_department` VALUES ('4', '0', 'FLY的狐狸', 'ABC000', '100', null, null, null, '2016-07-31 18:12:30', '1', '2016-07-31 18:12:30', '1');
INSERT INTO `sys_department` VALUES ('5', '4', '开发部', 'ABC001', '101', null, null, null, '2016-07-31 18:15:29', '1', '2016-07-31 18:15:29', '1');
INSERT INTO `sys_department` VALUES ('6', '4', '财务部', 'ABC003', '103', null, null, null, '2016-07-31 18:16:06', '1', '2016-07-31 18:16:06', '1');
INSERT INTO `sys_department` VALUES ('7', '4', '人事部', 'ABC004', '104', null, null, null, '2016-07-31 18:16:30', '1', '2016-07-31 18:16:30', '1');
INSERT INTO `sys_department` VALUES ('8', '5', '测试部', 'ABC0011', '101', null, null, null, '2016-07-31 18:16:52', '1', '2016-07-31 18:16:52', '1');

-- ----------------------------
-- Table structure for `sys_dict`
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `dict_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dict_name` varchar(256) NOT NULL COMMENT '名称',
  `dict_type` varchar(64) NOT NULL COMMENT '类型',
  `dict_remark` varchar(256) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_id`),
  UNIQUE KEY `UK_SYS_DICT_TYPE` (`dict_type`)
) ENGINE=InnoDB AUTO_INCREMENT=103 DEFAULT CHARSET=utf8 COMMENT='数据字典主表';

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES ('1', '日志配置', 'systemLog', null);
INSERT INTO `sys_dict` VALUES ('2', '目录类型', 'articleType', null);
INSERT INTO `sys_dict` VALUES ('11', '目录类型', 'folderType', null);
INSERT INTO `sys_dict` VALUES ('100', '系统参数', 'systemParam', null);
INSERT INTO `sys_dict` VALUES ('101', '友情链接类型', 'friendlyLinkType', null);
INSERT INTO `sys_dict` VALUES ('102', '栏目类型', 'materialType', null);

-- ----------------------------
-- Table structure for `sys_dict_detail`
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_detail`;
CREATE TABLE `sys_dict_detail` (
  `detail_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dict_type` varchar(64) NOT NULL COMMENT '数据字典类型',
  `detail_name` varchar(256) DEFAULT NULL COMMENT '名称',
  `detail_code` varchar(32) DEFAULT NULL COMMENT '代码',
  `detail_sort` varchar(32) DEFAULT NULL COMMENT '排序号',
  `detail_type` varchar(32) DEFAULT NULL COMMENT '类型',
  `detail_state` varchar(32) DEFAULT NULL COMMENT '状态',
  `detail_content` varchar(256) DEFAULT NULL COMMENT '内容',
  `detail_remark` varchar(256) DEFAULT NULL COMMENT '备注',
  `create_time` varchar(64) DEFAULT NULL COMMENT '创建时间',
  `create_id` int(11) DEFAULT '0' COMMENT '创建者',
  PRIMARY KEY (`detail_id`)
) ENGINE=InnoDB AUTO_INCREMENT=108 DEFAULT CHARSET=utf8 COMMENT='数据字典';

-- ----------------------------
-- Records of sys_dict_detail
-- ----------------------------
INSERT INTO `sys_dict_detail` VALUES ('1', 'folderType', '目录', '1', '1', null, null, null, null, '2015-01-30', '1');
INSERT INTO `sys_dict_detail` VALUES ('2', 'folderType', 'a标签', '2', '2', null, null, null, null, '2015-01-30', '1');
INSERT INTO `sys_dict_detail` VALUES ('3', 'folderType', 'a标签target', '3', '3', null, null, null, null, '2015-01-30', '1');
INSERT INTO `sys_dict_detail` VALUES ('4', 'folderType', 'html标签', '4', '4', null, null, null, null, '2015-01-30', '1');
INSERT INTO `sys_dict_detail` VALUES ('11', 'articleType', '正常', '1', '1', null, null, null, null, '2015-01-30', '1');
INSERT INTO `sys_dict_detail` VALUES ('12', 'articleType', '预览', '2', '2', null, null, null, null, '2015-01-30', '1');
INSERT INTO `sys_dict_detail` VALUES ('13', 'articleType', '程序', '3', '3', null, null, null, null, '2015-01-30', '1');
INSERT INTO `sys_dict_detail` VALUES ('21', 'friendlyLinkType', '友情链接', null, '1', null, null, null, null, '2015-05-06 15:18:59', '1');
INSERT INTO `sys_dict_detail` VALUES ('22', 'friendlyLinkType', '关于我们', null, '2', null, null, null, null, '2015-05-06 15:19:20', '1');
INSERT INTO `sys_dict_detail` VALUES ('51', 'systemLog', 'sys_dict', '数据字典主表', '1', null, null, null, null, '2015-01-30', '1');
INSERT INTO `sys_dict_detail` VALUES ('52', 'systemLog', 'sys_dict_detail', '数据字典', '2', null, null, null, null, '2015-01-30', '1');
INSERT INTO `sys_dict_detail` VALUES ('53', 'systemLog', 'sys_menu', '菜单管理', '3', null, null, null, null, '2015-01-30', '1');
INSERT INTO `sys_dict_detail` VALUES ('54', 'systemLog', 'sys_department', '组织机构', '4', null, null, null, null, '2015-01-30', '1');
INSERT INTO `sys_dict_detail` VALUES ('55', 'systemLog', 'sys_user', '用户管理', '5', null, null, null, null, '2015-01-30', '1');
INSERT INTO `sys_dict_detail` VALUES ('56', 'systemLog', 'sys_user_role', '用户角色授权', '6', null, null, null, null, '2015-01-30', '1');
INSERT INTO `sys_dict_detail` VALUES ('57', 'systemLog', 'sys_role', '角色管理', '7', null, null, null, null, '2015-01-30', '1');
INSERT INTO `sys_dict_detail` VALUES ('58', 'systemLog', 'sys_role_folder', '角色目录授权', '8', null, null, null, null, '2015-01-30', '1');
INSERT INTO `sys_dict_detail` VALUES ('59', 'systemLog', 'sys_role_menu', '角色菜单授权', '9', null, null, null, null, '2015-01-30', '1');
INSERT INTO `sys_dict_detail` VALUES ('60', 'systemLog', 'tb_folder', '目录管理', '10', null, null, null, null, '2015-01-30', '1');
INSERT INTO `sys_dict_detail` VALUES ('61', 'systemLog', 'tb_article', '文章管理', '11', null, null, null, null, '2015-01-30', '1');
INSERT INTO `sys_dict_detail` VALUES ('62', 'systemLog', 'tb_articlelike', '喜欢的文章管理', '12', null, null, null, null, '2015-01-30', '1');
INSERT INTO `sys_dict_detail` VALUES ('63', 'systemLog', 'tb_comment', '评论管理', '13', null, null, null, null, '2015-01-30', '1');
INSERT INTO `sys_dict_detail` VALUES ('64', 'systemLog', 'tb_tags', '标签管理', '14', null, null, null, null, '2015-01-30', '1');
INSERT INTO `sys_dict_detail` VALUES ('65', 'systemLog', 'tb_contact', '联系人', '15', null, null, null, null, '2015-01-30', '1');
INSERT INTO `sys_dict_detail` VALUES ('66', 'systemLog', 'tb_error', '错误管理', '16', null, null, null, null, '2015-01-30', '1');
INSERT INTO `sys_dict_detail` VALUES ('67', 'systemLog', 'tb_friendlylink', '友情链接', '17', null, null, null, null, '2015-01-30', '1');
INSERT INTO `sys_dict_detail` VALUES ('68', 'systemLog', 'tb_pageview', '访问量统计', '18', null, null, null, null, '2015-01-30', '1');
INSERT INTO `sys_dict_detail` VALUES ('101', 'systemParam', '门头沟信息网', '1', '1', null, null, null, null, '2015-01-30', '1');
INSERT INTO `sys_dict_detail` VALUES ('102', 'materialType', '文章', '1', '1', null, null, null, null, '2016-03-31 22:35:05', '1');
INSERT INTO `sys_dict_detail` VALUES ('103', 'materialType', '图片', '2', '2', null, null, null, null, '2016-03-31 22:35:17', '1');
INSERT INTO `sys_dict_detail` VALUES ('104', 'materialType', '视频', '3', '3', null, null, null, null, '2016-03-31 22:35:28', '1');
INSERT INTO `sys_dict_detail` VALUES ('105', 'materialType', '其他', '9', '9', null, null, null, null, '2016-03-31 22:35:39', '1');
INSERT INTO `sys_dict_detail` VALUES ('106', 'materialType', '栏目', '6', '6', null, null, null, null, '2016-03-31 23:46:27', '1');
INSERT INTO `sys_dict_detail` VALUES ('107', 'systemLog', 'tb_site', '站点管理', '19', null, null, null, null, '2016-04-14 00:02:45', '1');

-- ----------------------------
-- Table structure for `sys_log`
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `log_type` int(11) NOT NULL COMMENT '类型',
  `oper_object` varchar(64) DEFAULT NULL COMMENT '操作对象',
  `oper_table` varchar(64) NOT NULL COMMENT '操作表',
  `oper_id` int(11) DEFAULT '0' COMMENT '操作主键',
  `oper_type` varchar(64) DEFAULT NULL COMMENT '操作类型',
  `oper_remark` varchar(100) DEFAULT NULL COMMENT '操作备注',
  `create_time` varchar(64) NOT NULL COMMENT '创建时间',
  `create_id` int(11) DEFAULT '0' COMMENT '创建者',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11862 DEFAULT CHARSET=utf8 COMMENT='日志';

-- ----------------------------
-- Records of sys_log
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `parentid` int(11) NOT NULL DEFAULT '0' COMMENT '父id',
  `name` varchar(200) NOT NULL DEFAULT '' COMMENT '名称/11111',
  `urlkey` varchar(256) DEFAULT NULL COMMENT '菜单key',
  `url` varchar(256) DEFAULT NULL COMMENT '链接地址',
  `status` int(11) DEFAULT '1' COMMENT '状态//radio/2,隐藏,1,显示',
  `type` int(11) DEFAULT '1' COMMENT '类型//select/1,根目录,2,a标签,3,a标签_blank,4,外部url',
  `sort` int(11) DEFAULT '1' COMMENT '排序',
  `level` int(11) DEFAULT '1' COMMENT '级别',
  `create_time` varchar(64) DEFAULT NULL COMMENT '创建时间',
  `create_id` int(11) DEFAULT '0' COMMENT '创建者',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8 COMMENT='菜单';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '0', '系统管理', 'system_root', null, '1', '1', '90', '1', '2015-04-27 17:28:06', '1');
INSERT INTO `sys_menu` VALUES ('2', '1', '组织机构', 'department', 'system/department/list', '1', '1', '91', '2', '2015-04-27 17:28:25', '1');
INSERT INTO `sys_menu` VALUES ('3', '1', '用户管理', 'user', 'system/user/list', '1', '1', '92', '2', '2015-04-27 17:28:46', '1');
INSERT INTO `sys_menu` VALUES ('4', '1', '角色管理', 'role', 'system/role/list', '1', '1', '94', '2', '2015-04-27 17:29:13', '1');
INSERT INTO `sys_menu` VALUES ('5', '1', '菜单管理', 'menu', 'system/menu/list', '1', '1', '96', '2', '2015-04-27 17:29:43', '1');
INSERT INTO `sys_menu` VALUES ('6', '1', '数据字典', 'dict', 'system/dict/list', '1', '1', '97', '2', '2015-04-27 17:30:05', '1');
INSERT INTO `sys_menu` VALUES ('7', '20', '联系人管理', 'contact', 'admin/contact/list', '1', '1', '38', '2', '2015-04-28 12:38:04', '1');
INSERT INTO `sys_menu` VALUES ('8', '18', '栏目管理', 'folder', 'admin/folder/list', '1', '1', '11', '2', '2015-04-28 22:34:46', '1');
INSERT INTO `sys_menu` VALUES ('9', '18', '文章管理', 'article', 'admin/article/list', '1', '1', '14', '2', '2015-04-28 22:35:24', '1');
INSERT INTO `sys_menu` VALUES ('10', '20', '友情链接', 'friendlylink', 'admin/friendlylink/list', '1', '1', '32', '2', '2015-04-28 22:35:56', '1');
INSERT INTO `sys_menu` VALUES ('11', '20', '访问量统计', 'pageview', 'admin/pageview', '1', '1', '33', '2', '2015-04-28 22:36:34', '1');
INSERT INTO `sys_menu` VALUES ('12', '19', '回复管理', 'comment', 'admin/comment/list', '1', '1', '21', '2', '2015-05-06 09:40:46', '1');
INSERT INTO `sys_menu` VALUES ('13', '20', '缓存更新', 'operation', 'admin/operation', '1', '1', '31', '2', '2015-05-06 11:41:33', '1');
INSERT INTO `sys_menu` VALUES ('14', '1', '日志管理', 'log', 'system/log/list', '1', '1', '98', '2', '2016-01-03 18:09:18', '1');
INSERT INTO `sys_menu` VALUES ('15', '19', '意见反馈', 'advicefeedback', 'admin/advicefeedback/list', '1', '1', '22', '2', '2016-01-29 01:06:46', '1');
INSERT INTO `sys_menu` VALUES ('16', '18', '栏目公告', 'foldernotice', 'admin/foldernotice/list', '1', '1', '12', '2', '2016-01-29 01:07:35', '1');
INSERT INTO `sys_menu` VALUES ('17', '18', '栏目滚动图片', 'folderrollpicture', 'admin/folderrollpicture/list', '1', '1', '13', '2', '2016-01-29 01:11:48', '1');
INSERT INTO `sys_menu` VALUES ('18', '0', '内容管理', 'folder_root', null, '1', '1', '10', '1', '2016-01-29 04:24:45', '1');
INSERT INTO `sys_menu` VALUES ('19', '0', '评论管理', 'comment_root', null, '1', '1', '20', '1', '2016-01-29 04:26:57', '1');
INSERT INTO `sys_menu` VALUES ('20', '0', '其他管理', 'other_root', null, '1', '1', '30', '1', '2016-01-29 04:29:39', '1');
INSERT INTO `sys_menu` VALUES ('21', '0', '首页', 'home', 'admin/home', '1', '1', '2', '1', '2015-04-27 17:28:06', '1');
INSERT INTO `sys_menu` VALUES ('22', '0', '素材管理', 'material_root', null, '1', '1', '16', '1', '2016-02-05 11:15:25', '1');
INSERT INTO `sys_menu` VALUES ('23', '22', '图片管理', 'image', 'admin/image/list', '1', '1', '18', '2', '2016-02-05 11:15:45', '1');
INSERT INTO `sys_menu` VALUES ('24', '22', '相册管理', 'imagealbum', 'admin/imagealbum/list', '1', '1', '17', '2', '2016-02-05 11:17:57', '1');
INSERT INTO `sys_menu` VALUES ('25', '22', '我的相册', 'imageshow', 'admin/imageshow/list', '1', '1', '16', '2', '2016-02-10 01:22:01', '1');
INSERT INTO `sys_menu` VALUES ('26', '22', '视频专辑管理', 'videoalbum', 'admin/videoalbum/list', '1', '1', '19', '2', '2016-02-16 16:25:08', '1');
INSERT INTO `sys_menu` VALUES ('27', '22', '视频管理', 'video', 'admin/video/list', '1', '1', '20', '2', '2016-02-16 16:25:37', '1');
INSERT INTO `sys_menu` VALUES ('28', '0', '模板管理', 'filemanager', 'admin/filemanager/list', '1', '1', '60', '1', '2016-03-06 09:36:36', '1');
INSERT INTO `sys_menu` VALUES ('29', '18', '文章审核', 'article_approve', 'admin/article/list_approve', '1', '1', '15', '2', '2016-03-16 00:21:12', '1');
INSERT INTO `sys_menu` VALUES ('30', '20', '站点管理', 'site', 'admin/site/list', '1', '1', '31', '2', '2016-04-02 22:26:33', '1');

-- ----------------------------
-- Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(200) NOT NULL DEFAULT '' COMMENT '名称/11111/',
  `status` int(11) DEFAULT '1' COMMENT '状态//radio/2,隐藏,1,显示',
  `sort` int(11) DEFAULT '1' COMMENT '排序',
  `remark` text COMMENT '说明//textarea',
  `create_time` varchar(64) DEFAULT NULL COMMENT '创建时间',
  `create_id` int(11) DEFAULT '0' COMMENT '创建者',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '测试角色', '1', '1', null, '2016-03-31 23:41:59', '1');

-- ----------------------------
-- Table structure for `sys_role_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `roleid` int(11) NOT NULL COMMENT '角色id',
  `menuid` int(11) NOT NULL COMMENT '菜单id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色和菜单关联';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `userid` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(32) NOT NULL COMMENT '用户名/11111',
  `password` varchar(32) NOT NULL COMMENT '密码',
  `realname` varchar(32) DEFAULT NULL COMMENT '真实姓名',
  `departid` int(11) DEFAULT '0' COMMENT '部门/11111/dict',
  `usertype` int(11) DEFAULT '2' COMMENT '类型//select/1,管理员,2,普通用户,3,前台用户,4,第三方用户',
  `state` int(11) DEFAULT '10' COMMENT '状态',
  `thirdid` varchar(200) DEFAULT NULL COMMENT '第三方ID',
  `endtime` varchar(32) DEFAULT NULL COMMENT '结束时间',
  `email` varchar(64) DEFAULT NULL COMMENT 'email',
  `tel` varchar(32) DEFAULT NULL COMMENT '手机号',
  `address` varchar(32) DEFAULT NULL COMMENT '地址',
  `title_url` varchar(200) DEFAULT NULL COMMENT '头像地址',
  `remark` varchar(1000) DEFAULT NULL COMMENT '说明',
  `theme` varchar(64) DEFAULT 'default' COMMENT '主题',
  `back_site_id` int(11) DEFAULT '0' COMMENT '后台选择站点ID',
  `create_site_id` int(11) DEFAULT '1' COMMENT '创建站点ID',
  `create_time` varchar(64) DEFAULT NULL COMMENT '创建时间',
  `create_id` int(11) DEFAULT '0' COMMENT '创建者',
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '1RHFCLt64uOOViCTzgSaww==', '系统管理员', '1', '1', '1', null, null, 'zcool321@sina.com', '123', null, null, '时间是最好的老师，但遗憾的是——最后他把所有的学生都弄死了', 'default', '2', '1', '2016-06-06 06:06:06', '1');

-- ----------------------------
-- Table structure for `sys_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `userid` int(11) NOT NULL COMMENT '用户id',
  `roleid` int(11) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户和角色关联';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_advice_feedback`
-- ----------------------------
DROP TABLE IF EXISTS `tb_advice_feedback`;
CREATE TABLE `tb_advice_feedback` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `userid` int(11) NOT NULL COMMENT '用户ID',
  `username` varchar(32) NOT NULL COMMENT '用户名',
  `qq` varchar(32) DEFAULT NULL COMMENT 'qq',
  `email` varchar(64) DEFAULT NULL COMMENT 'email',
  `telphone` varchar(32) DEFAULT NULL COMMENT '手机号',
  `content` varchar(2000) DEFAULT NULL COMMENT '意见反馈内容',
  `remark` varchar(2000) DEFAULT NULL COMMENT '备注',
  `is_read` int(11) DEFAULT NULL COMMENT '是否已读',
  `create_time` varchar(64) DEFAULT NULL COMMENT '创建时间',
  `create_id` int(11) DEFAULT '0' COMMENT '创建者',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='意见反馈';

-- ----------------------------
-- Records of tb_advice_feedback
-- ----------------------------
INSERT INTO `tb_advice_feedback` VALUES ('1', '1', '系统管理员', '333', '333', '33', null, '333333333', null, '2016-01-29 04:39:43', '1');
INSERT INTO `tb_advice_feedback` VALUES ('2', '1', '系统管理员', '330321321', '223@sina.com', '13983283282', null, '系统很不错~等待升级', null, '2016-01-29 04:41:27', '1');
INSERT INTO `tb_advice_feedback` VALUES ('3', '1', '系统管理员', '123', '123', '123', null, '123', null, '2016-01-29 04:42:14', '1');
INSERT INTO `tb_advice_feedback` VALUES ('4', '1', '系统管理员', '321', '2222', '2222', null, '222', null, '2016-01-29 04:42:59', '1');
INSERT INTO `tb_advice_feedback` VALUES ('5', '1', '系统管理员', '33333333', '33333333333333333', '33333333333', null, '3333333333333333333333', null, '2016-01-29 04:43:51', '1');
INSERT INTO `tb_advice_feedback` VALUES ('6', '1', '系统管理员', '111111111', '11111111111111', '1111111111111', null, '11111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111', null, '2016-01-29 04:46:02', '1');

-- ----------------------------
-- Table structure for `tb_article`
-- ----------------------------
DROP TABLE IF EXISTS `tb_article`;
CREATE TABLE `tb_article` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `folder_id` int(11) DEFAULT '1' COMMENT '目录id',
  `title` varchar(200) DEFAULT '' COMMENT '文章名称',
  `content` longtext COMMENT '文件内容',
  `count_view` int(11) DEFAULT '0' COMMENT '浏览数',
  `count_comment` int(11) DEFAULT '0' COMMENT '评论数',
  `type` int(11) DEFAULT '1' COMMENT '类型：1 正常 2 预览展示概述 3 程序调用处理',
  `status` varchar(20) DEFAULT '1' COMMENT '状态//radio/2,隐藏,1,显示',
  `is_comment` int(11) DEFAULT '1' COMMENT '是否评论：2 否 1 是',
  `is_recommend` int(11) DEFAULT '2' COMMENT '是否推荐：2 否 1 是',
  `sort` int(11) DEFAULT '1' COMMENT '排序',
  `jump_url` varchar(256) DEFAULT NULL COMMENT '跳转地址',
  `image_url` varchar(256) DEFAULT NULL COMMENT '图片路径',
  `image_net_url` varchar(256) DEFAULT NULL COMMENT '网络图片路径',
  `file_url` varchar(256) DEFAULT NULL,
  `file_name` varchar(256) DEFAULT NULL,
  `approve_status` int(11) DEFAULT NULL COMMENT '审核状态',
  `publish_time` varchar(64) DEFAULT NULL COMMENT '发布时间',
  `publish_user` varchar(64) DEFAULT '1' COMMENT '发布者',
  `start_time` varchar(64) DEFAULT NULL COMMENT '开始时间',
  `end_time` varchar(64) DEFAULT NULL COMMENT '结束时间',
  `update_time` varchar(64) DEFAULT NULL COMMENT '更新时间',
  `create_time` varchar(64) DEFAULT NULL COMMENT '创建时间',
  `create_id` int(11) DEFAULT '0' COMMENT '创建者',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2415 DEFAULT CHARSET=utf8 COMMENT='文章';

-- ----------------------------
-- Records of tb_article
-- ----------------------------
INSERT INTO `tb_article` VALUES ('1', '1', '门头沟', '<p>门头沟</p>', '122', '123', '12', '2', '1', '1', '1', null, 'download/image_url/20150529_102007_298104.jpg', null, null, null, '10', '2014-03-05', '系统管理员', '2015-01-29', '2015-01-23', '2015-01-28 17:29:55', '2015-01-28', '1');
INSERT INTO `tb_article` VALUES ('105', '2', '北京门头沟：持京卡10元游京西“三山一湖”', '<p align=\"center\" style=\"margin-top: 0px; margin-bottom: 0px; padding: 5px 25px; font-family: 宋体; color: rgb(0, 0, 0); font-size: 14px; line-height: 28px; white-space: normal;\"><img border=\"0\" src=\"http://acftu.workercn.cn/html/files/2015-04/30/20150430161629353296461.jpg\"/></p><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 5px 25px; font-family: 宋体; color: rgb(0, 0, 0); font-size: 14px; line-height: 28px; text-align: justify; white-space: normal;\">　　中工网讯 近日，由北京市门头沟区总工会、区旅游委和灵山、百花山、妙峰山、珍珠湖景区合作主办的“走进门头沟——十元游京西山水启动仪式”在妙峰山景区举行。区总工会常务副主席杜军、区旅游委主任刘贵清、区总工会副主席郭燕等领导出席启动仪式，启动仪式上职工服务中心负责人与景区代表负责人签署了合作协议。</p><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 5px 25px; font-family: 宋体; color: rgb(0, 0, 0); font-size: 14px; line-height: 28px; text-align: justify; white-space: normal;\">　　10元就可以游京西灵山、百花山、妙峰山、珍珠湖。这是门头沟区总工会为本区工会会员提供的一项优惠活动。据介绍，此项活动开展时间为5月1日至10月7日。活动期间凭本区“京卡·互助服务卡”和预定门票不仅能够以最优惠的价格享受游览“三山一湖”的优质服务，更能够远离雾霾尽享门头沟区秀美风光。</p><p><br/></p>', '21', '0', '11', '1', '1', '2', '10', null, null, null, null, null, '1', '2015-04-30', '中工网', null, null, '2015-01-28 17:48:26', '2015-01-28', '1');
INSERT INTO `tb_article` VALUES ('111', '2', '门头沟定都阁:看“3D北京”', '<p align=\"center\" style=\"margin-top: 10px; margin-bottom: 10px; font-stretch: normal; line-height: 25.2000007629395px; font-family: 宋体; font-size: 1.4rem; word-wrap: break-word; white-space: normal;\"><img alt=\"\" src=\"http://www.people.com.cn/mediafile/pic/20150515/92/11134802480688866980.jpg\" style=\"border-style: none;\"/></p><p style=\"margin-top: 10px; margin-bottom: 10px; font-stretch: normal; line-height: 25.2000007629395px; font-family: 宋体; font-size: 1.4rem; word-wrap: break-word; white-space: normal;\">　　这两天，许多市民驱车赶到门头沟定都阁欣赏初夏“3D北京”，大气磅礴的白云下，视野格外通透，人们肉眼可以清晰分辨数十公里外远的景致。定都阁位于门头沟区潭柘寺镇定都峰上，定都峰，海拔680米，位于门头沟区桑峪村东北狮山山顶，其位置正处在长安街向西的延长线上，是传说中“燕王喜登定都峰，刘伯温一夜建北京”的所在地。</p><p><br/></p>', '27', '0', '11', '1', '1', '2', '10', null, null, null, null, null, '1', '2015-02-04', '人民网', null, null, '2015-02-04 08:47:31', '2015-02-04', '1');
INSERT INTO `tb_article` VALUES ('112', '2', 'S1线6号线西延 明年年底开通', '<p>&nbsp;&nbsp;S1线预计明年年底将与6号线西延一起开通，同时长安街西延工程预计2017年12月全线通车。未来门头沟也将成为距离中心城区最近、交通最便利的新城。这是门头沟区区长王洪钟今天上午做客北京城市广播“市民对话一把手”栏目时透露的。</p><p>　　<strong>S1线与六号线西延一起开通</strong></p><p>　　提到门头沟区的交通发展，王洪钟表示，S1线现在已经开工建设，将在明年年底和地铁6号线西延一起开通。据介绍，S1线磁悬浮铁路在门头沟设了6个站点，届时从门头沟石门营到苹果园仅需10分钟左右。</p><p>　　另外长安街延长线从去年下半年也已经开工建设，预计将在2017年建成。据介绍，西延工程中还将架设永定河跨河大桥。在S1线和长安街延长线两条交通线建设完成后，门头沟区和市区的交通会更加便捷，这两条线也将与阜石路二期以及莲石路到潭柘寺的108国道、未来即将修建的109高速公路等形成一个总的大交通体系。</p>', '226', '3', '11', '1', '1', '1', '9', null, 'download/image_url/20150525_233451_151572.jpg', null, null, null, '10', '2015-04-20', '中新网', null, null, '2015-02-06 09:18:50', '2015-02-06', '1');
INSERT INTO `tb_article` VALUES ('206', '2', '门头沟区召开“十三五”规划公共服务与社会治理专题研讨会', '<p>5月12日，门头沟区召开“十三五”规划公共服务与社会治理专题研讨会。</p><p><br/></p><p>区领导韩子荣、罗斌、丁勇、郑伟革、冯飞、高连发，区相关部门负责同志，北京国际城市发展研究院、北京方迪经济发展研究院相关负责同志参加会议，听取区相关部门关于规划编制有关工作情况的汇报，并就相关问题进行研讨。</p><p><br/></p><p>韩子荣要求：一是要深入研究门头沟区未来人口发展趋势，避免“城市病”的产生。一、区规划分局、国土分局要认真核算已通过审批项目的体量。二、要对我区南部新城、北部棚改7平方公里范围内的人口承载能力、产业配套设施等进行专项研究。三、要以门城地区22万人口为对象研究如何进一步提升公共服务与社会治理水平，同时要研究潭柘寺、军庄、斋堂的小城镇建设发展方向。二是要聚焦农村城市化进程，科学设计服务项目。一、要深入研究农村预留产业用地使用、新型市民培育、劳动力就业等问题。二、要围绕12类人群、农民及在岗职工需求编制项目，确保其享有便利的公共服务。三是要深入推进政府改革，打造服务型政府。一、要通过升级改造提升为民服务平台服务能力，采取APP等方式为群众提供更为便利的智能化公共服务。二、要强化社会组织的培育，通过多样化的社会组织最大程度地满足群众的个性化需求。三、要不断提升政府服务能力，进一步优化提升教育、商业等民生领域配套设施及布局，深入推进医疗领域改革，通过整合部门资源为群众、企业办事打造更加方便快捷的服务通道。四、要体系化研究政府未来发展方向，以综合审批、城市综合管理、综合执法三个体系为重点进行研究，通过体系化联动提升政府工作效率。</p><p><br/></p>', '79', '0', '11', '1', '1', '2', '10', null, null, null, null, null, '10', '2015-02-04', '系统管理员', null, null, '2015-02-26 09:14:47', '2015-02-26', '1');
INSERT INTO `tb_article` VALUES ('314', '13', '爨底下', '<p>http://www.jflyfox.com/mtg/front/article/330.html</p>', '2', '0', '11', '1', '1', '2', '10', null, 'download/image_url/20150524_165122_812162.jpg', 'http://i1.tietuku.com/35171f11a5ec9c51.jpg', null, null, '10', '2015-05-24', '系统管理员', '', '', '2015-05-24 16:50:26', '2015-05-24 16:50:26', '1');
INSERT INTO `tb_article` VALUES ('315', '13', '永定塔', '<p>http://www.jflyfox.com/mtg/front/article/406.html</p>', '2', '0', '11', '1', '1', '2', '10', null, 'download/image_url/20150524_165116_706479.jpg', 'http://i1.tietuku.com/fab40b501ece3fcf.jpg', null, null, '10', '2015-05-24', '系统管理员', '', '', '2015-05-24 16:50:34', '2015-05-24 16:50:34', '1');
INSERT INTO `tb_article` VALUES ('316', '13', '美丽门城', '<p>#</p>', '1', '0', '11', '1', '1', '2', '10', null, 'download/image_url/20150524_165111_42992.jpg', 'http://i1.tietuku.com/6f139452bedaefed.jpg', null, null, '10', '2015-05-24', '系统管理员', '', '', '2015-05-24 16:50:42', '2015-05-24 16:50:42', '1');
INSERT INTO `tb_article` VALUES ('317', '13', '百花山', '<p>http://www.jflyfox.com/mtg/front/article/329.html</p>', '6', '0', '11', '1', '1', '2', '10', null, 'download/image_url/20150524_165105_854676.jpg', 'http://i1.tietuku.com/3951e9cb262621b6.jpg', null, null, '10', '2015-05-24', '系统管理员', '', '', '2015-05-24 23:36:47', '2015-05-24 23:36:47', '1');
INSERT INTO `tb_article` VALUES ('318', '2', '门头沟华润悦景湾30-100平2-3居总价100万起', '<p style=\"text-align: center;\"><img src=\"http://src.house.sina.com.cn/imp/imp/deal/f2/51/e/82508c253d348a23a271ffd0417_p7_mk7_osc75b7a_cm440X330.jpg\" _src=\"http://src.house.sina.com.cn/imp/imp/deal/f2/51/e/82508c253d348a23a271ffd0417_p7_mk7_osc75b7a_cm440X330.jpg\"/></p><p>华润悦景湾以LOFT产品为主，底商为辅。层高4.2米，最高21层，主要户型为30-100平米的两至三居，容积率4.0，地块规划1612户，共6栋楼，毛坯交房，总价100万起。</p><p><span style=\"font-family: Arial, 宋体, sans-serif; font-size: 14px; line-height: 26px; white-space: normal;\">项目位于北京市城西，门头沟新城核心区（永定镇），长安街西延长线南侧。周边重点名校云集，内外部多重园林景观，多维交通，给予居住者真正轻松优享的生活品质。</span></p>', '1', '0', '11', '1', '1', '2', '10', null, null, null, null, null, '10', '2015-05-06', '新浪房产', null, null, '2015-05-24 22:17:25', '2015-05-24 22:17:25', '1');
INSERT INTO `tb_article` VALUES ('319', '2', ' 北京门头沟:首家非京籍未成年人观护基地成立', '<p style=\"margin-top: 10px; margin-bottom: 10px; font-stretch: normal; font-size: 14px; line-height: 25.2000007629395px; font-family: 宋体; word-wrap: break-word; white-space: normal;\">　5月19日,北京市门头沟区雁翅镇益农缘生态农业合作社里热闹非凡。这一天,该区检察院在这里举行“扬帆非京籍未成年人观护基地”正式成立仪式,数十名检察官、观护员、爱心人士都来看望在这里劳动、生活的4名失足未成年人。</p><p style=\"margin-top: 10px; margin-bottom: 10px; font-stretch: normal; font-size: 14px; line-height: 25.2000007629395px; font-family: 宋体; word-wrap: break-word; white-space: normal;\">　　“这里是外地来京失足孩子的‘家’,是检察院帮助这些孩子悔过自新的‘家’,更是我们这个社会关爱、挽救涉罪未成年人的‘家’。”门头沟区检察院检察长杨淑雅介绍,作为北京市首家非京籍附条件不起诉未成年犯罪嫌疑人观护基地,主要解决在京无固定住所、无固定职业、无监护人的涉罪未成年人的观护帮教问题。</p><p style=\"margin-top: 10px; margin-bottom: 10px; font-stretch: normal; font-size: 14px; line-height: 25.2000007629395px; font-family: 宋体; word-wrap: break-word; white-space: normal;\">　　记者了解到,门头沟区检察院一直在积极探索附条件不起诉观护帮教工作。2014年11月,门头沟区检察院联系到了北京市劳模、益农缘生态农业合作社董事长寇红艳。寇红艳义不容辞地担负起了观护责任,她把这些涉罪未成年人当成自己的孩子,教他们干家务活、参加农业劳动,带他们锻炼身体,辅导他们看书学习……孩子们亲切地叫她为“寇妈妈”。</p><p style=\"margin-top: 10px; margin-bottom: 10px; font-stretch: normal; font-size: 14px; line-height: 25.2000007629395px; font-family: 宋体; word-wrap: break-word; white-space: normal;\">　　17岁的小宇从老家来到北京打工。年轻气盛的他为朋友打抱不平,误将他人打伤,涉嫌寻衅滋事罪,被移送到门头沟区检察院审查起诉。考虑到他是未成年人且真诚认罪悔罪,该院依法对其作出附条件不起诉决定,并送到益农缘生态农业合作社接受观护帮教。</p><p style=\"margin-top: 10px; margin-bottom: 10px; font-stretch: normal; font-size: 14px; line-height: 25.2000007629395px; font-family: 宋体; word-wrap: break-word; white-space: normal;\">　　检察官和“寇妈妈”为出身农村家庭的小宇制定了专门观护帮教方案。几个月的时间里,小宇不仅学会了一些日常生活技能,掌握了相关农业技术,还学会了如何待人接物,整个人变得积极向上起来。</p><p style=\"margin-top: 10px; margin-bottom: 10px; font-stretch: normal; font-size: 14px; line-height: 25.2000007629395px; font-family: 宋体; word-wrap: break-word; white-space: normal;\">　　对于这一点,小宇的父亲感触很深。他告诉记者:“小宇变得比以前懂事了,知道关心家人,能主动帮助做家务,我和他妈妈特别感谢检察院。”</p><p><br/></p>', '1', '0', '11', '1', '1', '2', '10', null, null, null, null, null, '10', '2015-05-08', '人民网', null, null, '2015-05-24 22:17:31', '2015-05-24 22:17:31', '1');
INSERT INTO `tb_article` VALUES ('320', '2', '北京遭遇64年最冷5月 昨天门头沟下雪了', '<p align=\"center\" style=\"margin-top: 0px; margin-bottom: 0px; padding: 5px 25px; font-family: 宋体; color: rgb(0, 0, 0); font-size: 14px; line-height: 28px; white-space: normal;\"><img src=\"http://news.workercn.cn/html/files/2015-05/10/20150510111950681533257.jpg\"/></p><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 5px 25px; font-family: 宋体; color: rgb(0, 0, 0); font-size: 14px; line-height: 28px; text-align: justify; white-space: normal;\"></p><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 5px 25px; font-family: 宋体; color: rgb(0, 0, 0); font-size: 14px; line-height: 28px; text-align: justify; white-space: normal;\">　　人民网北京5月10日电 据京华时报微博报道，5月9日中午，北京门头沟灵山气温1℃，下雪了！由于灵山海拔高气温低，所以水蒸气直接凝结成了雪片儿。当北京别的地下雨时，灵山在下雪！今天最高气温12℃。</p><p align=\"center\" style=\"margin-top: 0px; margin-bottom: 0px; padding: 5px 25px; font-family: 宋体; color: rgb(0, 0, 0); font-size: 14px; line-height: 28px; white-space: normal;\"><img src=\"http://news.workercn.cn/html/files/2015-05/10/20150510111950704668741.JPG\"/></p><p><br/></p>', '4', '0', '11', '1', '1', '2', '10', null, null, null, null, null, '10', '2015-05-10', ' 人民网', null, null, '2015-05-24 22:17:38', '2015-05-24 22:17:38', '1');
INSERT INTO `tb_article` VALUES ('321', '2', '北京门头沟区龙山家园安置房墙体多处开裂', '<p align=\"center\" style=\"color: rgb(67, 67, 67); font-family: 宋体; line-height: 28px; white-space: normal;\">　　<img alt=\"\" src=\"http://i.ce.cn/ce/cysc/fdc/fc/201505/26/W020150526282891734311.jpg\" oldsrc=\"W020150526282891734311.jpg\" style=\"border-style: none;\"/></p><p align=\"center\" style=\"color: rgb(67, 67, 67); font-family: 宋体; line-height: 28px; white-space: normal;\">　　工人正在楼体旁进行施工。京华时报记者谭青摄</p><p align=\"center\" style=\"color: rgb(67, 67, 67); font-family: 宋体; line-height: 28px; white-space: normal;\">　　<img alt=\"\" src=\"http://i.ce.cn/ce/cysc/fdc/fc/201505/26/W020150526282891781783.jpg\" oldsrc=\"W020150526282891781783.jpg\" style=\"border-style: none;\"/></p><p align=\"center\" style=\"color: rgb(67, 67, 67); font-family: 宋体; line-height: 28px; white-space: normal;\">　　楼体底部与地面之间的裂缝。京华时报记者谭青摄</p><p style=\"color: rgb(67, 67, 67); font-family: 宋体; line-height: 28px; white-space: normal;\">　　昨天，门头沟区龙山家园5区多名住户反映，他们居住的2号楼1单元定向安置房墙体出现多处裂缝。对此，棚改中心负责人张先生表示，由于室外地坪发生不均匀沉降，造成1单元北侧门厅墙体、室外残疾人坡道及散水开裂，南侧绿化地面沉降。他们已连夜展开施工，同时委托相关单位对该楼进行全面安全检测。对于事发楼居民住宿问题，他们将协调相关部门解决。　　　　　　</p><p style=\"color: rgb(67, 67, 67); font-family: 宋体; line-height: 28px; white-space: normal;\">　　□举报</p><p style=\"color: rgb(67, 67, 67); font-family: 宋体; line-height: 28px; white-space: normal;\">　　墙体出现多处裂缝</p><p style=\"color: rgb(67, 67, 67); font-family: 宋体; line-height: 28px; white-space: normal;\">　　昨天，家住龙山家园5区2号楼1单元8层的刘女士反映，她们居住的2号楼为采空棚户区黑山定向安置房，房子装修完毕18日入住后，当时经过1单元北侧门厅时，就发现墙体有小裂缝，楼后也发现类似现象。刘女士称，当时她并没在意，咨询朋友后得知，一般情况下新楼盘经过下雨后都会有地基稍微下沉现象。</p><p style=\"color: rgb(67, 67, 67); font-family: 宋体; line-height: 28px; white-space: normal;\">　　“当时把我吓了一跳。”刘女士回忆，前天上午她回家时，突然发现门厅倾斜变形和楼后墙体裂缝变大，地基也出现大缝隙。多名住户表示，该楼之前已通过相关部门验收合格，他们才搬进去，有的还没来得及入住，就出现裂缝现象，实属不应该，他们担心存在安全隐患。</p><p style=\"color: rgb(67, 67, 67); font-family: 宋体; line-height: 28px; white-space: normal;\">　　昨天上午8点多，事发楼东侧用蓝色铁皮遮挡，门口一部挖掘机正在往车上转运土料，几名工人在门厅上方作业。楼前空地，多名住户在围观。</p><p style=\"color: rgb(67, 67, 67); font-family: 宋体; line-height: 28px; white-space: normal;\">　　据了解，该栋楼共28层高，涉及3个单元，385套住宅，有20多户已经入住。记者在楼前和楼后侧均发现，墙体与地面连接处出现多处裂缝，有的长达10米左右，宽约10厘米，能容下一个拳头大小。另外还有多处地坪有下沉现象。除此之外，旁边的1号楼前后也存在部分地坪沉降现象。</p><p style=\"color: rgb(67, 67, 67); font-family: 宋体; line-height: 28px; white-space: normal;\">　　记者看到，事发楼1单元门外墙上，贴着北京寓吉物业管理有限公司的紧急通知：因本单位供水管线突发故障，施工方需紧急施工进行工程抢修，施工期间将封闭单元门进出口，出单元门走旁边小门，施工期限预计10天。</p><p style=\"color: rgb(67, 67, 67); font-family: 宋体; line-height: 28px; white-space: normal;\">　　住户商女士提供的住宅工程质量分户验收表上显示，该栋楼建设单位是门头沟区采空棚户区改造建设中心。施工单位为山西建筑工程（集团）总公司。监理单位为北京园磊工程管理有限公司。开工日期为2012年10月12日。2014年12月23日，三方综合验收结论为验收合格。</p><p style=\"color: rgb(67, 67, 67); font-family: 宋体; line-height: 28px; white-space: normal;\">　　□回应</p><p style=\"color: rgb(67, 67, 67); font-family: 宋体; line-height: 28px; white-space: normal;\">　　将对该楼安全检测</p><p style=\"color: rgb(67, 67, 67); font-family: 宋体; line-height: 28px; white-space: normal;\">　　对此，棚改中心负责人张先生称，事发后棚改中心组织设计施工单位、建筑主体施工单位、室外施工单位、第三方检测单位第一时间进行现场勘察。初步判断为室外市政工程冬季回填土不实地坪沉降，对楼前消防管形成压迫，造成管线破裂，加重地坪沉降。目前，棚改中心已安排相关施工单位将沉降区域用围挡进行了封闭，并进行了抢修。</p><p style=\"color: rgb(67, 67, 67); font-family: 宋体; line-height: 28px; white-space: normal;\">　　张先生称，龙山家园A5地块2号楼为采空棚户区黑山定向安置房项目住宅标段之一。截至今年5月24日，已完成全部房屋分配工作，部分安置居民开始进行室内装修，其中22户居民已入住。</p><p style=\"color: rgb(67, 67, 67); font-family: 宋体; line-height: 28px; white-space: normal;\">　　对于事发楼处置方案，张先生称，由棚改中心组织施工单位对1单元北侧门厅进行拆除，进行消防管线抢修，沉降区域回填土重新回填，同时委托国家建设工程质量监督检验中心和沉积观测单位对该栋住宅楼进行全面安全检测，并出具相关检测报告。张先生表示，他们将对整个龙山家园每栋楼进行排查，关于2号楼居民提出的住宿问题由棚改中心负责解决，安排住宿地点，相关费用由政府协调支付。</p><p><br/></p>', '25', '0', '11', '1', '1', '2', '10', null, null, null, null, null, '10', '2015-05-23', '京华时报', null, null, '2015-05-24 22:17:47', '2015-05-24 22:17:47', '1');
INSERT INTO `tb_article` VALUES ('322', '2', '长安街西延将直达门头沟', '<p><span>继游乐园大摩天轮之后，石景山又一地标性建筑—首钢厂东门将暂时离开公众视线。下周一，为让路长安街西延工程，这座屹立了23年的大门启动拆迁，最终会向西挪移一里地。迁建后的厂东门位于首钢北京园区L型景观带，将成为长安街西延长线上具有绿色生态示范展示和工业遗产转型升级特色的城市公共活动休闲区。</span></p><p><span><br/></span></p><p><span>“这大门是首钢人的情结”</span></p><p><span><br/></span></p><p><span>昨天上午，首钢厂东门前人流如织，不少首钢老职工和市民赶来“道别”。朱红色的大门，顶端镶嵌着绿色琉璃瓦，大门两侧悬挂着红底烫金的牌匾，这个1992年建成的仿古大门被一次次定格在了相机镜头里。</span></p><p><span><br/></span></p><p><span>大门一侧，前来合影的全国劳模屠学信和几位同事拍了张“全家福”：“我来这工作33年了，大门建起后每天我都要从这里走上几个来回，厂东门见证了我的青春，说要拆迁，心里总不是滋味。”感到不舍的还有82岁的退休职工马立坤，他特意起了个大早赶到这儿，只为再凝望一眼曾经日夜陪伴的大门：“首钢人都对厂东门感情很深，进了这个门就感觉到家一样，厂东门就是首钢人的情结。”</span></p><p><span><br/></span></p><p><span>特意提前两站下了公交车，市民佟先生夫妇和厂东门拍了最后一张合影。“无论是以前上学还是现在上班，听到公交车报站首钢厂东门到了，心里就暖暖的，因为就要到家了，总会望一眼车窗外的大门。”佟先生的妻子也打开了话匣子：“这个地标建筑见证了我们的生活变迁，要拆了还真是有点留恋”。</span></p><p><span><br/></span></p><p><span>长安街西延预计后年开通</span></p><p><span><br/></span></p><p><span>不过，首钢厂东门只是暂时告别。这个长56.28米、高12.85米的大门，将按1：1的比例迁建至长安街西延长线与晾水池（群明湖）东路交口的东北角。</span></p><p><span><br/></span></p><p><span>作为长安街西延长线的重点工程之一，东门拆除时会保留门上须弥座和木屋顶，并保存到库房，让新的厂东门恢复原貌。记者从首钢园区开发部工程处了解到，建好后的长安街西延长线将把封闭的厂区一分为二，新的路面宽80米，采取先修路北，路南通车；再修路南，路北通车的方式。</span></p><p><span><br/></span></p><p><span>而建成后的厂东门，将成为新首钢“五区两带”规划中长安街以北城市公共活动休闲带的南端起点，沿着厂东门的中心点一路向北将是新首钢工业资源景观的中轴线。这里交通便利，不但南面连接石景山直达永定河、门头沟的跨河大桥将建成，而且将有S1磁浮线路通过，直达新首钢北端后通过地下空间与东侧苹果园交通枢纽相连。“以后这儿也是西长安街，车能从这直接开到门头沟！”一名老职工指着厂东门的方向说道。</span></p><p><span><br/></span></p><p><span>长安街西延项目是本市今年的重点工程，总投资达22亿元。项目西起门头沟区三石路，东至石景山区古城路口，全长约6.4公里，是城市主干道。根据市重大办公布的信息，项目计划2017年10月全线竣工，今年计划完成总工程的31%。该项目涉及首钢区域全长约2.6公里，其中厂东门以内约1.5公里，目前，首钢方面已完成红线范围内除厂东门、国旗杆外的全部拆除工作。</span></p><p><span><br/></span></p><p><span>炼铁料场变身创意广场</span></p><p><span><br/></span></p><p><span>长安街西延项目将成为新首钢高端产业综合服务区的支撑项目和交通干道。</span></p><p><span><br/></span></p><p><span><br/></span></p><p><span>作为本市加快西部地区转型的发展核心区，首钢园区的11个重点项目正在推进。其中，首钢利用工业建筑进行改造的首个项目—四十筒仓项目已经完成主体工程改造及部分配套工程和精装施工。据介绍，已改造完工的6个筒仓和1个料仓占地3万平方米，虽然表面分别还是水泥和钢铁色调，但已初步有了写字楼的模样。首钢园区开发部相关负责人表示，原本的“炼铁料场”变身风貌独特的“创意广场”，已具备客户入驻条件，目前共有几十家文创企业有意入驻。</span></p><p><span><br/></span></p><p><span>另外，位于新首钢高端产业综合服务区南侧的首钢二型材厂，项目定位于为互联网金融、IT产业等企业提供办公及配套服务场所，项目已于今年3月开工，预计2017年建成。</span></p><p><br/></p>', '2', '0', '11', '1', '1', '1', '10', null, null, null, null, null, '10', '2015-05-24', ' 北京日报', null, null, '2015-05-24 22:17:53', '2015-05-24 22:17:53', '1');
INSERT INTO `tb_article` VALUES ('323', '2', '门头沟新城：品牌房企“向前冲', '<p><span>随着京西区域的发展和交通环境的改善，原本属于北京远郊的门头沟区域也开始被房企纳入视线。近几年，已经有融创、电建、华润等多个品牌开发商进入这一区域拿地，而从今年开始，电建·泷悦长安、融创西长安壹号、华润悦景湾等项目的集中入市，也使得门头沟这一京西市场成为业界所关注的焦点。</span></p><p><span><br/></span></p><p><span>　　交通改善推升门头沟区域价值</span></p><p><span><br/></span></p><p><span>　　从北京远郊区到房价推升到3万元/平方米级别的京西新城，门头沟的区域价值在近几年来有着明显增长，而其中最重要的一点在于门头沟区域的交通环境改善。多位业内人士告诉记者，交通改善可以说是目前门头沟区域最大的利好。</span></p><p><span><br/></span></p><p><span>　　随着长安街西延线以及地铁S1号线在今明两年相继贯通，位于门头沟新城的项目也已经开始陆续入市。记者近日实地走访了解到，虽然目前还没有最终确定价格，但是西长安壹号、华润悦景湾等即将开盘的项目，售价普遍在3万-3.5万元/平方米之间。</span></p><p><span><br/></span></p><p><span>　　泷悦长安项目负责人介绍，现在来门头沟置业的人群多元化，也是看到了未来的发展规划。尤其是工作和生活都在西部的购房者，对门头沟地域的距离能够接受。而在西长安壹号的销售现场，项目销售人员也向记者表示：“随着区域价值的不断提升，项目价格预计在7月份还会上调，涨幅在10%左右。”</span></p><p><span><br/></span></p><p><span>　　亚豪机构市场总监郭毅也认为，门头沟虽然看起来距离比较远，但是相对于大兴、通州、房山、昌平等区域内到三环的通行时间是最短的。“以前大家会感觉门头沟的位置相对来说离北京市区没有那么近，但是过去了就会感受到其交通条件真是周边几个行政区里面相对最好的。从阜石路可以直接开到门头沟，而且这条路基本不会出现拥堵。”</span></p><p><span><br/></span></p><p><span>　　商住、别墅各占半壁江山</span></p><p><span><br/></span></p><p><span>　　值得注意的是，在门头沟区域这一批入市的新盘当中，主要以小户型商住类产品和别墅类产品为主。吸引的人群呈现出首次置业的年轻人和高端改善型人群二元化趋势。</span></p><p><span><br/></span></p><p><span>　　“由于这边的房地产开发比较晚，所以这个区域的居住人口密度没有那么高。首钢搬迁之后，门头沟自身的生态优势被更大地放大出来。因此门头沟的一些项目就是依托这样的山景做出了一些高品质的住宅。购房者也比较认可这个区域。”郭毅表示。</span></p><p><span><br/></span></p><p><span>　　区域内一央企人士则告诉记者，造成这一现象的原因主要还是和此前的土地出让及门头沟区域的大环境有关。“门头沟之所以定位较高，一个因素是环境好，另一个因素则是因为土地资源比较稀缺。”她向记者表示，门头沟区域平原面积只占1.5%，山地面积98.5%，适合进行房地产开发的土地稀缺，使得这一区域的地价也不算低。</span></p><p><span><br/></span></p><p><span>　　2013年底，融创、住总、骏洋三家房企组成的联合体以58.66亿元获取门头沟新城地块，成为当年的总价地王。去年10月，城建和保利在门头沟永定镇拿下的两宗地块，溢价率分别为40%和35%。“再加上当时出让的土地都配有相当数量的自住房及公租房配建，使得开发商可售部分房源的楼面价进一步提高。因此，开发别墅类产品无疑是一个比较好的选择。”该人士说。</span></p><p><span><br/></span></p><p><span>　　此外，位于规划中的WSD（西部服务区）区域内的土地则多为商业用地，除了商住产品和写字楼之外，房企也没有打造普通住宅项目的选项。</span></p><p><span><br/></span></p><p><span>　　■ 新盘探访</span></p><p><span><br/></span></p><p><span>　　电建·泷悦长安</span></p><p><span><br/></span></p><p><span>　　边户别墅受青睐八九月推高层住宅</span></p><p><span><br/></span></p><p><span>　　电建·泷悦长安是位于门头沟永定镇长安街西延线南侧的别墅项目。本周二，记者来到项目销售现场，不过由于该区域并不处于传统的豪宅区，项目周边配套尚未成型，售楼处内也没有购房者前来咨询。不过，门头沟新城良好的生态环境可以说是这一项目最大的卖点。</span></p><p><span><br/></span></p><p><span>　　据电建·泷悦长安的销售人员介绍，目前大多来此置业的购房者主要是考虑到养老、休闲的需求。销售人员称：“门头沟的主要卖点是山水环境和未来新城区的发展。项目此次开盘共推出40套别墅，主打240平方米的联排别墅。现在已经有20多组排卡。边户别墅的花园面积较大，优惠后总价约1400万元/套。中户别墅花园面积较小，优惠后约1200万元/套。边户别墅卖得特别火，因为花园面积大，一共12套边户，现在只剩下4套。我们将在2017年交房，那时长安街西延长线会通车，周围相关的配套都将建好。”</span></p><p><span><br/></span></p><p><span>　　记者还了解到，电建·泷悦长安还将在今年8月、9月推出高层住宅，共有568套房源，涵盖120-170平方米的多种户型。不过目前价格还没有最终确定，但预计至少在3.5万/平方米以上。</span></p><p><span><br/></span></p><p><span>　　华润悦景湾</span></p><p><span><br/></span></p><p><span>　　低总价LOFT可定制家装</span></p><p><span><br/></span></p><p><span>　　华润悦景湾同样位于门头沟新城区域。记者驱车从西二环出发，仅需三十分钟左右就抵达华润悦景湾项目所在地。同去年来此地时相比，项目所在区域商业、交通等配套已经明显成熟。而相对于处于亦庄、房山等区域的同类型LOFT产品来说，这样的通勤时间对于在丰台、海淀等区工作的群体来说具有相当的吸引力。</span></p><p><span><br/></span></p><p><span>　　“由于低总价和不限购的特点，项目的定位本身就偏向于首次置业或者首次改善的年轻购房人群。”华润悦景湾项目负责人介绍，该项目将于近期入市，主要产品为33-70平方米的LOFT。“单价会比周边同类型产品低一些，预计会在3万-3.5万元/平方米之间，最低总价预计在100万元左右。”</span></p><p><span><br/></span></p><p><span>　　而针对年轻人追求个性的特点，华润悦景湾还联合小米家装，推出了定制化装修服务，客户可以根据自己喜好的风格定制精装产品。而这也成为了该项目的一大亮点，据该项目负责人介绍，虽然项目是毛坯交房，但是由于同小米爱空间方面已经达成了合作，所以业主可以在几种不同风格的装修套餐中选择一种自己最为中意的产品，其装修标准为988元/平方米。</span></p><p><span><br/></span></p><p><span>　　西长安壹号</span></p><p><span><br/></span></p><p><span>　　精装修LOFT 酝酿涨价</span></p><p><span><br/></span></p><p><span>　　预计本月底开盘的西长安壹号，也是位于门头沟新城的商住项目，但未来是精装修交房。本周二记者以购房者的身份探访了该项目，发现相对于周边同类型产品，融创旗下的西长安壹号的价格也要稍高一些。在售楼处现场，销售人员告诉记者：“项目位于WSD的核心区域，长安线西延线两侧没有住宅用地的规划，所以只能做成商住类项目。拿地时楼面价格是1.85万元/平方米，现在精装修卖3.4万元/平方米左右已是成本价。”</span></p><p><span><br/></span></p><p><span>　　据了解，西长安壹号预计将于月底开盘，共有200余套房源入市，目前已经进入排号阶段，而排号数也已超过200组。7月份，项目价格还会上调10%左右。</span></p><p><span><br/></span></p><p><span>　　“如果周边项目的价格达到4万元/平方米，那我们至少要卖到5万元/平方米，因为我们是在WSD的核心位置，项目旁边就是即将开通的地铁S1号线。”面对显得有些犹豫的记者，销售人员不断强调项目地段的优越性。</span></p><p><br/></p>', '7', '0', '11', '1', '1', '2', '10', null, null, null, null, null, '10', '2015-05-26', '系统管理员', null, null, '2015-05-24 22:17:59', '2015-05-24 22:17:59', '1');
INSERT INTO `tb_article` VALUES ('324', '2', '区城管局开展餐饮业燃气安全检查', '<p><span style=\"color: rgb(102, 102, 102); font-family: 微软雅黑, Arial, sans-serif, 新宋体; font-size: 14px; line-height: 24px; text-indent: 28px; white-space: normal;\">日前区城管执法监察局各属地执法队走访辖区内各餐饮企业，开展餐饮业燃气安全检查。城管队员在检查中细致排查燃气安全隐患，重点检查餐饮企业是否安装燃气浓度监测装置，燃气管路是否老化，排风扇防爆灯是否安装到位等情况。检查中发现有部分餐饮企业燃气使用不规范燃气管存放不规范等问题。城管队员向餐饮企业下发整改通知书，督促被检查单位进行自查整改，落实企业燃气安全主体责任，切实消除燃气安全隐患。</span></p>', '17', '1', '11', '1', '1', '2', '10', null, null, null, null, null, '10', '2015-05-19', '系统管理员', null, null, '2015-05-24 22:18:07', '2015-05-24 22:18:07', '1');
INSERT INTO `tb_article` VALUES ('325', '4', '定都阁', null, '0', '0', '11', '1', '1', '2', '10', null, 'download/image_url/20150524_224542_933745.jpg', null, null, null, '10', '2015-05-24', '系统管理员', null, null, '2015-05-24 22:45:42', '2015-05-24 22:45:42', '1');
INSERT INTO `tb_article` VALUES ('326', '4', '戒台寺', null, '0', '0', '11', '1', '1', '2', '10', null, 'download/image_url/20150524_224606_653614.jpg', null, null, null, '10', '2015-05-24', '系统管理员', null, null, '2015-05-24 22:46:06', '2015-05-24 22:46:06', '1');
INSERT INTO `tb_article` VALUES ('327', '4', '潭柘寺', null, '1', '0', '11', '1', '1', '2', '10', null, 'download/image_url/20150524_224618_396989.jpg', null, null, null, '10', '2015-05-24', '系统管理员', null, null, '2015-05-24 22:46:18', '2015-05-24 22:46:18', '1');
INSERT INTO `tb_article` VALUES ('328', '4', '双龙峡', '<p><span>双龙峡自然风景区是京西新开发的一个风景区，位于门头沟区斋堂镇火村南2.5公里的青山翠谷中。双龙峡自然风景区主体形象概括为六句话应该是：十里溪流 百潭瀑布 千亩红杏 万顷林海 青山翠谷 世外桃源 苍山如海，峰峦巍峨俊秀，被喻为“小九寨”、“百瀑布”。</span></p><p><span><br/></span></p><p><span>可以欣赏的主要景点有：玉龙湖、清幽湖、坐听双琴、仙女湾、第一瀑布（高29.7米、宽5米）、千蛙 蜂、七音瀑、双龙入水、小九寨沟、琴玉潭、三百年野生猕猴桃、仙女湾、双龙戏龟、青蛙石、二百年野生猕猴桃仙聚柳、送仙松、第二瀑布（高30米、宽10米）、原始森林。第一 瀑布至第二瀑布山谷内，沿溪流、山径，藤蔓植物与灌木、乔木纠缠盘结，形成约五公里长的天然植物走廊，郁郁葱葱，号为“藤萝谷”，为双龙峡一大奇观。景区概括为：“十里溪流、百潭瀑布、千亩红杏、万顷林海。”真可谓青山翠谷，峰峦巍峨俊秀。到了夏季山花遍地，溪水潺潺好一派北国秀色，所以又有“小九寨”之称。</span></p><p><span><br/></span></p><p><span><strong>第一瀑布（日月潭）</strong></span></p><p><span>双龙峡 （原名水湖子）：在京西的景点中，还是第一次发现这样大瀑布，落差是 35 米，瀑宽 5 米。是名副其实的“京西第一瀑”。在远处看，像银河落地，如山风摧树，倒近处看到的是圆圆的太阳潭和半边下弦月，所以称之“北国日月潭”。还有一条绿色长龙，隐于潭壁上。似要出水之状，并且有曾经出水时走过的脚印。</span></p><p><span><br/></span></p><p><span><strong>青蛙石</strong></span></p><p><span>双龙峡 牛公山、牛蛙山－话说此地有一个泉眼，千万蛙靠此生存，牛公山在此 放牧，牛马千头几乎天天在此集饮。忽然，一年夜间大雨从天而降，洪水顺山而流，眼看山中万物，顷刻间即殁 , 就连都会洗劫一空。恰逢卯日星君知晓，站在其山岭上猛叫三声，把牛群惊动，牛群用身体筑起防护墙，保护所有生灵跳散，青蛙逃上山坡了望，牛公撇下牛群逃到半坡之上幸免遇难。牛救了全部生灵牺牲了自己。所以自今上面为金鸡岭，下面为牛公坡。东坡上的每一个山包，都是一个青蛙头在睁大眼睛目视谷中。</span></p><p><span>谷中稀有植物上百种。有二级保护植物 200 年生野生猕猴桃，盘缠其间，摘一颗清喉醒脑 。碗口粗的野葡萄、灵绡树不分 你我的缠在一起，不情愿的给行人腾出了一条绿色胡同。不时葡萄株能伸到你的口边。</span></p><p><span><br/></span></p><p><span><strong>双龙峡</strong></span></p><p><span>火村为门头沟区斋堂镇辖村。位于镇域东部，东南距区政府驻地 33 公里，西距镇驻地2公里，北邻高铺（新）村 1.5 公里，村域面积 14.56 平方公里。火村地势自南向北渐低，土壤为山地淋溶褐土。植被为低山（海拔 400 米）旱生山杏、荆条灌丛和人工林。</span></p><p><span>村域有南山溪流，注入清水河。东山有泉水供民饮用，村中地下水埋深 10 余米。村民多以农副业为计，种植玉米、谷子等作物 760 亩，年产粮 6.8 万公斤；栽植核桃、杏等干鲜果树，果品年产量 46.9 吨 . 火树红杏果质优异 , 闻名京西 . 火村在抗日战争期间 , 建立抗日革命根据地 , 与侵华日寇浴血奋战 , 先后被日寇烧村七次。</span></p><p><span><br/></span></p><p><span></span></p><p><img src=\"http://d.hiphotos.baidu.com/baike/c0%3Dbaike92%2C5%2C5%2C92%2C30/sign=09cc2953a8d3fd1f2204aa6851274e7a/9f2f070828381f30626337d4a9014c086e06f051.jpg\" _src=\"http://d.hiphotos.baidu.com/baike/c0%3Dbaike92%2C5%2C5%2C92%2C30/sign=09cc2953a8d3fd1f2204aa6851274e7a/9f2f070828381f30626337d4a9014c086e06f051.jpg\" style=\"\"/></p><p><img src=\"http://b.hiphotos.baidu.com/baike/c0%3Dbaike92%2C5%2C5%2C92%2C30/sign=507aff823bc79f3d9becec62dbc8a674/63d9f2d3572c11df30ab055e632762d0f703c251.jpg\" _src=\"http://b.hiphotos.baidu.com/baike/c0%3Dbaike92%2C5%2C5%2C92%2C30/sign=507aff823bc79f3d9becec62dbc8a674/63d9f2d3572c11df30ab055e632762d0f703c251.jpg\" style=\"\"/></p><p><span><br/></span><br/></p><p><span><br/></span></p><p><span>地址：北京市门头沟区斋堂镇火村双龙峡景区&nbsp;</span></p><p><span>电话：(010)69819310</span></p><p><br/></p>', '1', '0', '11', '1', '1', '2', '10', null, 'download/image_url/20150524_224630_442929.jpg', null, null, null, '10', '2015-05-24', '系统管理员', null, null, '2015-05-24 22:46:30', '2015-05-24 22:46:30', '1');
INSERT INTO `tb_article` VALUES ('329', '4', '百花山', '<p><span style=\"white-space: nowrap;\">百花山国家级自然保护区地处北京西部，位于北京市门头沟区清水镇境内，2008年经国务院批准在北京百花山自然保护区基础上晋升为国家级自然保护区。</span></p><p><span style=\"white-space: nowrap;\">保护区是以保护暖温带华北石质山地次生落叶阔叶林生态系统为主的自然保护区，总面积为21743.1公顷。</span></p><p><span style=\"white-space: nowrap;\"><img src=\"http://img1.qunarzz.com/sight/p0/1410/de/4eb87a813329d1d568ed5c88bca99659.water.jpg_1190x550_16e25e47.jpg\" _src=\"http://img1.qunarzz.com/sight/p0/1410/de/4eb87a813329d1d568ed5c88bca99659.water.jpg_1190x550_16e25e47.jpg\"/></span></p><p><span style=\"white-space: nowrap;\"><img src=\"http://img1.qunarzz.com/sight/p0/201403/07/8dce2e3d9fc87b4e5e3ace7b0c78a5ef.jpg_1190x550_6607f387.jpg\" _src=\"http://img1.qunarzz.com/sight/p0/201403/07/8dce2e3d9fc87b4e5e3ace7b0c78a5ef.jpg_1190x550_6607f387.jpg\"/></span></p><p><span style=\"white-space: nowrap;\">地址： 北京市门头沟区清水镇百花山林场（门头沟与房山交接处）&nbsp;</span></p><p><span style=\"white-space: nowrap;\">电话： (010)60390257</span></p><p><br/></p>', '8', '0', '11', '1', '1', '2', '10', null, 'download/image_url/20150524_224645_154137.jpg', null, null, null, '10', '2015-05-24', '系统管理员', null, null, '2015-05-24 22:46:45', '2015-05-24 22:46:45', '1');
INSERT INTO `tb_article` VALUES ('330', '4', '爨底下', '<p>爨底下村位于北京西郊门头沟区斋堂镇，川底下村，实名爨底下。因在明代“爨里安口”（当地人称爨头）下方得名。位于京西斋堂西北狭谷中部，解放前属宛平县八区，现属斋堂镇所辖。距京90公里，海拔650米，村域面积5.3平方公里，清水河流域，温带季风气候，年平均气温10.1℃，自然植被良好，适合养羊，养蜜蜂。爨底下是国家A级景区。</p><p>爨底下村人（户主及子女）全姓韩。相传是明代由山西洪洞县大槐树下移民而来，原村址在村西北老坟处，后因山洪暴发，将整个村庄摧毁。只有一对青年男女，外出幸免遇难。为延续韩族后代，二人以推磨为媒而成婚，并在现址立村，婚后所生三子，为：韩福金、韩福银、韩福苍。三子分三门，即：东门、中门、西门。始以福字为第一辈序排20辈：福景自守玉、有明万宏思、义巨晓怀孟、永茂广连文，至今已发展到17辈，茂，字辈。</p><p>还相传是因为爨被村民们编成了顺口溜：兴字头，林字腰，大字下面架火烧，大火烧林烧的兴，岂不很热？所以姓韩，谐音寒。</p><p>全村现有人口29户，93人，土地280亩全村院落74个，房屋689间。大部分为清后期所建（少量建于民国时期）的四合院、三合院。依山而建，依势而就，高低错落，以村后龙头为圆心，南北为轴线呈扇面形展于两侧。村上、村下被一条长200米，最高处20米的弧形大墙分开，村前又被一条长170米&nbsp;</p><p>的弓形墙围绕，使全村形不散而神更聚，三条通道惯穿上下，而更具防洪、防匪之功能。</p><p>爨底下是国家3A级景区，市级文明单位，市级民俗旅游专业村，2003年被国家建设部，国家文物局评为首批中国历史文化名村，区级革命传统教育基地。著名的专家哲文先生讲：爨底下古山村是一颗中国古典建筑瑰宝的明珠，它蕴含着深厚的北方建筑文化内涵，就其历史，文化艺术价值来说，不仅在北京，就是在全国也属于珍贵之列，公之于世，功莫大焉。</p><p><a class=\"image-link nslog:9317\" href=\"http://baike.baidu.com/picture/509635/509635/0/a08b87d6277f9e2f4914e1881f30e924b899f309.html?fr=lemma&ct=single\" target=\"_blank\" title=\"爨底下村全景\" style=\"color: rgb(19, 110, 194); display: block; font-family: arial, 宋体, sans-serif; line-height: 24px; white-space: normal; width: 500px; height: 373px; background-color: rgb(255, 255, 255);\"><img class=\"\" alt=\"爨底下村全景\" src=\"http://g.hiphotos.baidu.com/baike/s%3D500/sign=0cbb1083c895d143de76e42343f18296/a08b87d6277f9e2f4914e1881f30e924b899f309.jpg\" style=\"text-align: center; display: block; margin-right: auto; margin-left: auto; width: 500px; height: 373px;\"/></a></p><p>村名</p><p>爨底下的“爨”字，共有三十笔，发cuàn音，为了方便记忆可拆开说：兴字头，林字腰、大字下面加火烧，大火烧林，越烧越兴，岂不很热，而爨底下人全姓韩，取谐音（寒）则为冷意，冷与热在五行之中可以互补，宇宙万物有天就有地，有日就有月，有男必然有女，有冷就得有热，故而爨底下村在历史上曾辉煌过。</p><p>“爨”字从字意解释为：家，永不分爨，即永不分家。为：灶，烧火煮饭。为：姓，陕西省歧山县有爨家庄，全村千口余人皆姓爨。此字难写难认，会写则成爨，不会写则成一片，故而用谐音“川”字代之，但仍发爨音。最早是在1942年为方便抗日干部特别是外地抗日干部通讯联系，将“爨”改成“川”，爨与川并用至五十年代末，基本就不用爨字了，1995年搞旅游开发后，爨字又大放熠彩。</p><p><br/></p><p>地址： 北京市门头沟区爨底下景区内&nbsp;</p>', '2', '0', '11', '1', '1', '2', '10', null, 'download/image_url/20150524_224700_823152.jpg', null, null, null, '10', '2015-05-24', '系统管理员', null, null, '2015-05-24 22:47:00', '2015-05-24 22:47:00', '1');
INSERT INTO `tb_article` VALUES ('331', '4', '灵山', '<p>&nbsp; &nbsp; 盛夏避暑的好去所。夏季，这里“丰草绿缛而争茂，佳木葱茏而可悦”，走在林中，让人备感舒适。自然山水自然景，自然空调自然风，凉风习习，心惬意，来到灵山方知城乡大不同。入夜，遥望星空，这里视野开阔，可以看到“天阶月色凉如水，卧看牵牛织女星”的美景。</p><p>&nbsp; &nbsp; 灵山自然风景区距京城122公里，其顶峰海拔2303米，是北京的第一峰。西与龙门森林公园毗邻；东与龙门涧景区相连；南与109国道相通。由于其海拔高度所致，使灵山在方圆25平方公里内形成北京地区集断层山、褶皱山为一体，奇峰峻俏、花卉无垠的自然风景区。</p><p>这里既有暖温带植被，又有西伯利亚寒冷地带亲缘植被。生长着杜鹃、丁香、白桦林和榛子、黄花、等植物，尤以高山草甸最为著名，因此，它是新疆细毛羊、伊犁马、青藏牦牛在北京唯一的天然繁衍养殖场，也是野生动物的乐园。</p><p>&nbsp; &nbsp; 灵山风景区内有旅游饭店3座，可同时接待就餐1200人，住宿500人以上。此外，村内有农家旅店，如有兴趣住进“山兄弟”、“山里人”，定能享受一下山里人的烤全羊、烤野兔和手扒羊肉等野味。</p><p>成人门票：35元</p><p>地址：门头沟区灵山景区管理处</p><p>公交车：乘坐公交汽车、电车到苹果园下车前200米转乘929支线至双塘涧（灵山脚下）每天四趟。</p><p><br/></p><p>电话：010-61827994、61827028</p><p>门票：35元/人，老人、学生、残疾人士凭有效证件17.5元/人，军人凭有效证件免费。</p><p>地址：北京市门头沟区灵山景区</p>', '4', '0', '11', '1', '1', '2', '10', null, 'download/image_url/20150524_224714_571477.jpg', null, null, null, '10', '2015-05-24', '系统管理员', null, null, '2015-05-24 22:47:14', '2015-05-24 22:47:14', '1');
INSERT INTO `tb_article` VALUES ('332', '4', '龙门涧', '<p>&nbsp; &nbsp; 龙门涧位于门头沟区清水乡燕家台西北侧。有长江三峡之峻拔，桂林山水之秀美，又有匡庐之飞瀑，黄山之叠泉。因此人们以“燕京小三峡”、“京西小桂林”、“大自然中的山水盆景”等赞语赞美龙门涧。</p><p>&nbsp; &nbsp; 龙门涧分为东西两涧，各绵延十余里。由于这里聚集了我国几类著名风景区的景色，诸如“三峡之气势”、“桂林之秀美”、“匡庐之飞瀑”、“黄 山之叠泉”，都可以在这里看到缩影，因此，龙门涧得到了许多 如“燕京小三峡”，“京西小桂林”、“京西小黄山”等美誉。 进入龙门涧峡谷，两侧山峰对峙，高耸碧空，如斧劈成。涧内泉水涓涓，清澈碧透，溪水潺潺，奔腾飞溅。冬日崖挂冰凌，夏日绿枝俏控。还有将军石、一线天、试剑峰、黑龙潭、听音阁、祭天台等景观。半山腰上，在一簇簇鲜花掩映下，十几个巨大的石灰岩洞若隐若现，为游人增添了无穷乐趣。由于山高谷深，日照温度低，盛夏酷暑时节，涧内却凉爽宜人，是旅游度假的好去处。</p><p><br/></p><p>地址：门头沟区西部清水镇燕家台村</p><p>电话：(010)61828185,(010)61828185</p>', '4', '0', '11', '1', '1', '2', '10', null, 'download/image_url/20150524_224755_937404.jpg', null, null, null, '10', '2015-05-24', '系统管理员', null, null, '2015-05-24 22:47:55', '2015-05-24 22:47:55', '1');
INSERT INTO `tb_article` VALUES ('333', '4', '妙峰山', '<p><span>　　国家3A级旅游景区。位于北京市门头沟区妙峰山镇。109国道34公里处。距北京市区55公里，是集人文和自然风光于一体的自然风景旅游景区。妙峰山惠济祠等庙宇群始建于辽金时代，后经历次战争运动仅存少量遗址，成为北京红色景点之一。1986年由有关部门组织重建，1988年成立景区重新对游人开放。</span></p><p><span><br/></span></p><p><span><strong>山庙会</strong></span></p><p><span>　　北京的庙会起源于辽代，称“上巳春游”。元、明两代庙会进一步兴起。妙峰山庙会始于明代，每年农历四月初一至十八妙峰山开山半月余，日以万计的香客络绎于途，有的一步一揖，三步一扣首；有的竟以背鞍、滚砖、耳箭、悬灯等方式进香以示虔诚。各种民间香会边走边练，幡旗飘扬，鼓磬齐鸣，观者如潮。妙峰山庙会期，京都万人空巷，其规模堪称华北之首。</span></p><p><span>妙峰山传统庙会始于明代崇祯年间，距今已有300余年的历史。每年的农历四月初一至十五，来自全国各地数十万善男信女，几百档民间花会汇聚妙峰山，朝顶进香，酬山赛会，施粥布茶，场面之壮观，信众之虔诚实属罕见。清《燕京岁时记》载：“妙峰山每属四月，自初 一开庙半月，香火极盛，人烟幅辏，车马喧闹，夜间灯火之繁灿如列宿，香火实可甲于天下矣”。1925年，北京大学国学门研究所对妙峰山庙会进行了专门调查，出版了《妙峰山进香专号》，此次调查开创了我国民俗学田野调查的先河，妙峰山因此成为中国民俗文化的发祥地。</span></p><p><span>妙峰山庙会在文革和抗战期间中断了数十年，一九九零年京城老香会“秘密”到妙峰山朝顶进香，酬山赛会。一九九三年政府正式批准妙峰山举办首届春香庙会，古老的妙峰山庙会文化又焕发出新的生机。如今的妙峰山庙会除完整保留了明清时期香客朝顶，香会酬山，施粥、布茶、舍馒头等传统形式外，还增加了商品交易、民俗展示、文艺演出等新内容，形成京城独具魅力、积极健康的民俗活动。</span></p><p><span><br/></span></p><p><span><strong>山花会</strong></span></p><p><span>　　花会最早称香会，因解放后破出迷信，改香会为花会。花会属于民间组织，始见于明朝中叶，花会有“文会”和“武会”之分。“文会”：各种行业都有自己的会，如“粥茶会”、“面茶会”、“青菜会”、“献盐会”、“缝绽会”等几十种名目。“武会”：门内有杠箱会、狮子会、中幡会、杠子会、石锁会、双石会、吵子会、花坛会、花钹大鼓、开路会、五虎棍、秧歌会、太平会等十三种名目。门外有旱船、踏车、云车、小车等各名目。此外还有吏部杠子会、户部秧歌会、礼部大执事会、兵部杠箱会、刑部石锁会、工部石锁会、太子府花坛会、掌礼司太狮会、翰林院五虎棍会、雪池五虎打路会等十堂官会。</span></p><p><span>旧时花会献艺的场所主要是“三山”“五顶”。“三山”既平谷的丫髻山、门头沟的妙峰山、石景山的天台山。“五顶”既京城五座比较闻名的娘娘庙：东直门外的称东顶、海淀蓝靛厂的称西顶、永外大红门的称南顶、安定门外的称北顶、丰台草桥的称中顶。乾隆皇帝封妙峰山娘娘庙为金顶，地位在“五顶”之上。</span></p><p><span><br/></span></p><p><span><strong>花会表演</strong></span></p><p><span>　　清朝前妙峰山每年两次庙会，农历四月初一至十八的春香庙会和农历七月初一至十五的秋香庙会，庙会期间花会必来朝顶进香，各会以到妙峰山朝顶为荣，并规定“未到妙峰山朝顶的花会不为正宗会”，各会严格遵循“车笼自备，茶水不扰”的会规，提倡一秉虔心和无私奉献精神。</span></p><p><span>　　妙峰山庙会兴盛时期花会多达几百档，自1990年恢复妙峰山庙会以来，已有130余档民间花会遵循传统会规到妙峰山朝顶献艺。</span></p><p><span><br/></span></p><p><span><strong>慈禧与娘娘庙</strong></span></p><p><span>　　北京西部的妙峰山，盛产玫瑰，据说是中国面积最大、品种最好的玫瑰养殖地；妙峰山的玫瑰虽好，但不如山上的娘娘庙名声大。每年农历的四月初一至十五，是妙峰山朝山之日，来自各地上百档花会云集，成千上万香客来往于香道之间，人山人海，蔚为壮观。民俗学家将妙峰山庙会誉为中国民俗文化研究的发祥地。老北京人甚至有“妙峰山的娘娘——照远不照近”歇后语。</span></p><p><span>　　妙峰山娘娘庙肇始何年不详，笔者分析，至少在明代就已经存在。据清代史料记载，有相当一段时间，朝廷对妙峰山庙会是禁止的。妙峰山庙会在清代末期名声远播，影响华北乃至全国，是与西太后慈禧的影响不无关系。</span></p><p><span><br/></span></p><p><span><strong>慈禧与眺远斋</strong></span></p><p><span>　　在颐和园内谐趣园北侧，高台之上，有一座巍峨颐和园眺远斋的大殿，五楹卷棚歇山式，前廊后厦，颇为气派。坐在殿内，对颐和园外的景致一览无遗，这就是眺远斋，是专门为慈禧观看前往妙峰山进香花会修建的。</span></p><p><span>　　却说这一年四月，春暖花开，慈禧来到谐趣园观花赏景，忽然听见园外锣鼓喧天，喝彩声不断，便问身边的李莲英：“园外这么热闹是干什么呢？”李莲英急忙差人出去察看，回来禀报：“是去妙峰山进香的走会。”</span></p><p><span>　　慈禧一向喜欢热闹，便对李莲英说：“我想看看走会的。”李莲英哪敢答应，叩头回奏说：“老佛爷出园看走会事小，可惊了凤驾奴才担当不起。今年的香会说话就过完了，回头在这谐趣园内专修一座宝殿，供老佛爷赏花看会，岂不是两全其美。”慈禧点头应允。于是，谐趣园内大兴土木，赶在第二年的四月初一，眺远斋大功告成。慈禧每每登上大殿，观看各路香会表演，看得兴起，少不了发个懿旨，赏赐哪一个香会点什么物件。这一来，赴妙峰山的香会纷纷来到颐和园外，给老佛爷献上绝技，指望得到封号或者赏赐。</span></p><p><span>　　妙峰山的香会分为“武会”和“文会”两种，并有“老会”和“圣会”之称。“老会”是百年以上的&nbsp;</span></p><p><span>香会，而“圣会”则不到百年。据《京都风物志》载：“城内诸般歌舞之会，必于此月登山酬赛，谓之‘朝顶进香’。如开路(要耍三股叉)、秧歌、挎鼓、高跷、太少师、五虎棍、扛箱会”等。“武会”大多从京城菜市口的山西洪洞会馆、白纸坊纸业公会、德胜门外松林闸三处出发，途中，锣鼓齐奏，每到茶棚，都要表演，“观者如堵，使进香沿途十分热闹壮观”。“文会”又为“善会”，是北京、天津地区百姓自发组织起来为进香人服务的慈善组织，如“茶棚老会”(供茶)、“施粥老会”(供粥)、“献盐老会”(供盐)、“拜席老会”(供席棚)、“燃灯老会”(供灯火)等。</span></p><p><span>　　据说，自打慈禧在眺远斋观赏香会以后，便有了“皇会”之说，也就是得到慈禧赏赐的香会有了可以炫耀的新旗号。</span></p><p><span><br/></span></p><p><span><strong>慈禧与“金阶”</strong></span></p><p><span>　　去妙峰山进香的路，原为崎岖小径，陡峭艰险。清同治年间，慈禧太后提出，准备去妙峰山进香。太监刘诚印闻言，便以素云道人名义，会同大太监安德海出资重修香道。将山道拓宽7尺，选取天然青石板，一层层铺砌，绵延数里。工程浩大，完全凭借人力，加之工期紧，气候恶劣，造价昂贵。据说每铺一级石阶，造价就是一两白银，因而得“金阶”之名。</span></p><p><span><br/></span></p><p><span><strong>慈禧与“头炷香”</strong></span></p><p><span>　　到妙峰山娘娘庙进香，过去讲究抢烧头一炷香。据说，谁得以烧得四月初一头炷香，便会“如愿以偿“。于是香客们千方百计纷纷争先，早早上山，争抢头柱香。</span></p><p><span>　　身居紫禁城皇宫内的娘娘们也相信头炷香的魅力，于是，在一个</span></p><p><span>善来金阶时期，头炷香便成了皇室的“专利”。到了清同治、光绪年间，慈禧也要去妙峰山进香，这头炷香便被慈禧垄断了。她曾下懿旨：“先期预诏庙祝，必须宫中进香后，始行开庙，谓之头香。”有宫词云：“昨夜慈宁亲诏下，妙高峰里进头香。”(故宫里的慈宁宫，是太后居住的地方。这里指慈禧。)</span></p><p><span>慈禧手书匾额</span></p><p><span>　　妙峰山娘娘庙正殿是灵感宫，有正殿三间，供奉“天仙圣母碧霞元君”。门檐原来悬挂三块匾额，分别是“慈光普照”、“功侔富媪”、“泰云垂荫”。据说原来均为慈禧所书，现已无存。</span></p><p><br/></p><p>地址： 北京门头沟区妙峰山镇</p><p>电话：(010)61882936</p>', '13', '0', '11', '1', '1', '2', '10', null, 'download/image_url/20150524_224808_352554.jpg', null, null, null, '10', '2015-05-24', '系统管理员', null, null, '2015-05-24 22:48:08', '2015-05-24 22:48:08', '1');
INSERT INTO `tb_article` VALUES ('334', '3', '大鸭梨', '<p>地址：北京近郊门头沟区新桥南大街49号(月季园小区对面)</p><p>电话：(010)69834563</p>', '6', '0', '11', '1', '1', '1', '9', null, 'download/image_url/20150524_231027_730846.jpg', null, null, null, '10', '2015-05-24', '系统管理员', null, null, '2015-05-24 23:04:05', '2015-05-24 23:04:05', '1');
INSERT INTO `tb_article` VALUES ('335', '3', '福华肥牛', '<p>地址：北京近郊门头沟区新桥南大街49号(月季园小区对面)</p><p>电话：(010)69846725</p>', '30', '0', '11', '1', '1', '2', '8', null, 'download/image_url/20150524_231021_399721.jpg', null, null, null, '10', '2015-05-24', '系统管理员', null, null, '2015-05-24 23:04:49', '2015-05-24 23:04:49', '1');
INSERT INTO `tb_article` VALUES ('336', '3', '京西晨光饭店餐厅', '<p>地址： 门头沟区双峪路1号京西晨光饭...</p><p>电话：(010)69858383</p><p><br/></p>', '3', '0', '11', '1', '1', '2', '10', null, 'download/image_url/20150526_161401_987099.jpg', null, null, null, '10', '2015-05-24', '系统管理员', null, null, '2015-05-24 23:06:33', '2015-05-24 23:06:33', '1');
INSERT INTO `tb_article` VALUES ('337', '3', '潭柘嘉福饭店', '<p>地址：北京海淀区门头沟区潭柘寺门口</p><p>电话：(010)60863587,(010)60862781</p><p><br/></p><p>安英客栈位于京城门头沟区西部。那里有许多独特的建筑、塔林。那里的大锅、巨钟、清泉、瓷器都有说不完的传奇故事。等待你去聆听。</p><p>登山，呼吸清新的空气，摆脱憋闷的北京城的繁重的紧张、压力。</p><p><span style=\"line-height: 1.42857143;\">周末，清晨在潭柘寺公园里溜溜早，看看青青的松，翠绿的竹，喝上一壶清茶。实在爽极了，美极了。</span></p><p>回程的路上逛逛戒台寺。看看那巨大的戒台，沧桑的松柏。找个石台坐坐，反思一下这喧嚣的、无味的生活，或许能获得些须灵感与思绪。给自己一个约会。</p><p><span style=\"line-height: 1.42857143;\">有的到潭柘寺为的是礼佛；有的是欣赏古老的建筑美；有的是呼吸新鲜的空气，强身健体。而我觉得她真正的美，需要你用心去衡量。不要匆匆的去，匆匆的回，那样你看到的只是空荡的，陈旧的外表。建议你，周末去，住上一宿，清晨的时候，走在那幽静的神秘的寺院里。感受心灵的洗礼。如果能带上几本书，那才是一次丰富的精神旅游。</span></p><p><br/></p>', '2', '0', '11', '1', '1', '2', '10', null, 'download/image_url/20150526_161220_275205.jpg', null, null, null, '10', '2015-05-24', '系统管理员', null, null, '2015-05-24 23:06:44', '2015-05-24 23:06:44', '1');
INSERT INTO `tb_article` VALUES ('338', '3', '龙泉宾馆西餐厅', '<p>地址：水闸北路21号龙泉宾馆</p><p>电话：(010)69843366</p>', '0', '0', '11', '1', '1', '2', '10', null, 'download/image_url/20150526_160834_985862.jpg', null, null, null, '10', '2015-05-24', '系统管理员', null, null, '2015-05-24 23:07:02', '2015-05-24 23:07:02', '1');
INSERT INTO `tb_article` VALUES ('339', '3', '今天假日酒店', '<p>电话：010-69826822</p><p>地址：门头沟双峪路39号</p><p><br/></p>', '0', '0', '11', '1', '1', '2', '10', null, 'download/image_url/20150526_160348_11856.jpg', null, null, null, '10', '2015-05-24', '系统管理员', null, null, '2015-05-24 23:07:16', '2015-05-24 23:07:16', '1');
INSERT INTO `tb_article` VALUES ('340', '3', '新新饭庄', '<p>地址：近郊门头沟区新桥大街49号(北京市门头沟区政府)</p><p>电话：(010)69862198</p><p><br/></p><p>门头沟区政府对面，有一家新新饭庄，这里有我魂牵梦绕的肉包子。</p><p style=\"text-align: center;\"><img src=\"http://localhost/mtg/umeditor/upload/20150526/33181432627216804.jpg\" _src=\"http://localhost/mtg/umeditor/upload/20150526/33181432627216804.jpg\" style=\"\"/></p><p style=\"text-align: center;\"><img src=\"http://localhost/mtg/umeditor/upload/20150526/91231432627220249.jpg\" _src=\"http://localhost/mtg/umeditor/upload/20150526/91231432627220249.jpg\" style=\"\"/></p><p style=\"text-align: center;\"><img src=\"http://localhost/mtg/umeditor/upload/20150526/97221432627222876.jpg\" _src=\"http://localhost/mtg/umeditor/upload/20150526/97221432627222876.jpg\" style=\"\"/></p><p style=\"text-align: center;\"><img src=\"http://localhost/mtg/umeditor/upload/20150526/65691432627225647.jpg\" _src=\"http://localhost/mtg/umeditor/upload/20150526/65691432627225647.jpg\" style=\"\"/></p><p style=\"text-align: center;\"><img src=\"http://localhost/mtg/umeditor/upload/20150526/62861432627228276.jpg\" _src=\"http://localhost/mtg/umeditor/upload/20150526/62861432627228276.jpg\" style=\"\"/></p><p style=\"text-align: center;\"><br/></p>', '2', '0', '11', '1', '1', '2', '10', null, 'download/image_url/20150526_155952_365641.jpg', null, null, null, '10', '2015-05-24', '系统管理员', null, null, '2015-05-24 23:07:41', '2015-05-24 23:07:41', '1');
INSERT INTO `tb_article` VALUES ('341', '3', '顶风针西饼店', '<p>地址： 冯村商业街2014号</p><p>电话：(010)60869774</p><p>顶风针食品有限公司创立于2005年9月10日，主要经营产 品包括生日蛋糕、面包、冷热饮品、中西式点心等。集“生产”“配送”“销售”为一体，以高品质的产品，高档次的装修，平民化的价格，亲切友善的服务，便利的连锁店铺，赢得了消费者的赞赏与好评！&nbsp;</p><p>公司自成立以来一直坚持“立足以人本，以‘高效率、高标准、高品质&#39;严格要求企业全体员工，力争为广大消费者提供最完善的产品与服务”。</p><p><br/></p>', '3', '0', '11', '1', '1', '2', '10', null, 'download/image_url/20150526_145553_259711.jpg', null, null, null, '10', '2015-05-24', '系统管理员', null, null, '2015-05-24 23:07:51', '2015-05-24 23:07:51', '1');
INSERT INTO `tb_article` VALUES ('342', '3', '老家肉饼', '<p>地址：近郊门头沟区新桥南大街14号(近物美新隆店)</p><p>电话：(010)69842527</p>', '3', '0', '11', '1', '1', '2', '10', null, null, null, null, null, '10', '2015-05-24', '系统管理员', null, null, '2015-05-24 23:08:10', '2015-05-24 23:08:10', '1');
INSERT INTO `tb_article` VALUES ('343', '5', '大峪中学', '<p>北京市大峪中学始建于1946年，1978年列为北京市重点中学，2004年进入北京市普通高中示范校行列。是一所校风优良、质量上乘的北京市重点中学，莘莘学子向往的京西名校。学校位于九龙山下，永定河畔，北京门头沟城镇中心的滨河德露苑18号，新的开发区内。</p><p><br/></p><p>学校地址：门头沟区滨河德露苑18号</p><p>邮政编码：102300</p><p>联系电话：61864110</p><p>学校网址：http://www.dyzx-bj.com/</p>', '11', '0', '11', '1', '1', '2', '9', null, 'download/image_url/20150526_140229_544065.jpg', null, null, null, '10', '2015-05-24', '系统管理员', null, null, '2015-05-24 23:22:24', '2015-05-24 23:22:24', '1');
INSERT INTO `tb_article` VALUES ('344', '5', '育园中学', '<p><span style=\"color: rgb(51, 51, 51); font-family: &#39;Helvetica Neue&#39;, Helvetica, Arial, sans-serif; font-size: 14px; line-height: 20px; background-color: rgb(255, 255, 255);\">育园中学</span></p>', '0', '0', '11', '1', '1', '2', '10', null, null, null, null, null, '10', '2015-05-24', '系统管理员', null, null, '2015-05-24 23:22:50', '2015-05-24 23:22:50', '1');
INSERT INTO `tb_article` VALUES ('345', '5', '首都师范大学附属中学永定分校', '<p><span style=\"color: rgb(51, 51, 51); font-family: &#39;Helvetica Neue&#39;, Helvetica, Arial, sans-serif; font-size: 14px; line-height: 20px; background-color: rgb(249, 249, 249);\">首都师范大学附属中学，永定中学</span></p>', '0', '0', '11', '1', '1', '2', '10', null, null, null, null, null, '10', '2015-05-24', '系统管理员', null, null, '2015-05-24 23:23:03', '2015-05-24 23:23:03', '1');
INSERT INTO `tb_article` VALUES ('346', '5', '大峪中学分校', '<p><span style=\"color: rgb(51, 51, 51); font-family: &#39;Helvetica Neue&#39;, Helvetica, Arial, sans-serif; font-size: 14px; line-height: 20px; background-color: rgb(245, 245, 245);\">大峪中学分校</span></p>', '3', '0', '11', '1', '1', '2', '10', null, null, null, null, null, '10', '2015-05-24', '系统管理员', null, null, '2015-05-24 23:23:22', '2015-05-24 23:23:22', '1');
INSERT INTO `tb_article` VALUES ('347', '5', '新桥路中学', '<p><span style=\"color: rgb(51, 51, 51); font-family: &#39;Helvetica Neue&#39;, Helvetica, Arial, sans-serif; font-size: 14px; line-height: 20px; background-color: rgb(245, 245, 245);\">新桥路中学</span></p>', '1', '0', '11', '1', '1', '2', '10', null, null, null, null, null, '10', '2015-05-24', '系统管理员', null, null, '2015-05-24 23:23:47', '2015-05-24 23:23:47', '1');
INSERT INTO `tb_article` VALUES ('348', '5', '育新中学', '<p><span style=\"color: rgb(51, 51, 51); font-family: &#39;Helvetica Neue&#39;, Helvetica, Arial, sans-serif; font-size: 14px; line-height: 20px; background-color: rgb(255, 255, 255);\">育新中学</span></p>', '2', '0', '11', '1', '1', '2', '10', null, null, null, null, null, '10', '2015-05-24', '系统管理员', null, null, '2015-05-24 23:23:59', '2015-05-24 23:23:59', '1');
INSERT INTO `tb_article` VALUES ('349', '5', '三家店铁路中学', '<p><span style=\"color: rgb(51, 51, 51); font-family: &#39;Helvetica Neue&#39;, Helvetica, Arial, sans-serif; font-size: 14px; line-height: 20px; background-color: rgb(245, 245, 245);\">三家店铁路中学</span></p>', '3', '0', '11', '1', '1', '2', '10', null, null, null, null, null, '10', '2015-05-24', '系统管理员', null, null, '2015-05-24 23:24:10', '2015-05-24 23:24:10', '1');
INSERT INTO `tb_article` VALUES ('350', '5', '坡头中学', '<p>坡头中学</p>', '1', '0', '11', '1', '1', '2', '10', null, null, null, null, null, '10', '2015-05-24', '系统管理员', null, null, '2015-05-24 23:24:34', '2015-05-24 23:24:34', '1');
INSERT INTO `tb_article` VALUES ('351', '90', '捐赠我们', '<p style=\"box-sizing: border-box; padding: 0px; color: rgb(51, 51, 51); line-height: 22.8571434020996px; white-space: normal; text-align: center; background-color: rgb(255, 255, 255);\">支付宝捐赠二维码 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 微信捐赠二维码</p><p style=\"box-sizing: border-box; padding: 0px; color: rgb(51, 51, 51); line-height: 22.8571434020996px; white-space: normal; text-align: center; background-color: rgb(255, 255, 255);\"><img src=\"http://ww1.sinaimg.cn/mw690/3fc7e281jw1eqec436tzwj2074074mxr.jpg\" style=\"box-sizing: border-box; margin: 0px; padding: 0px; border: 0px; vertical-align: middle;\"/>&nbsp; &nbsp;&nbsp;<img src=\"http://ww1.sinaimg.cn/mw690/3fc7e281jw1es3jr0k25xj20a50a5q3v.jpg\" style=\"box-sizing: border-box; margin: 0px; padding: 0px; border: 0px; vertical-align: middle; line-height: 1.42857143; width: 256px; height: 256px;\"/></p><p style=\"box-sizing: border-box; padding: 0px; color: rgb(51, 51, 51); line-height: 22.8571434020996px; white-space: normal; text-align: center; background-color: rgb(255, 255, 255);\"><br/></p><br/><p><br/></p>', '0', '0', '11', '1', '1', '2', '19', null, null, null, null, null, '10', '2015-05-26', '系统管理员', null, null, '2015-05-26 10:39:11', '2015-05-26 10:39:11', '1');
INSERT INTO `tb_article` VALUES ('352', '90', '关于我们', '<p style=\"white-space: normal; box-sizing: border-box; padding: 0px; color: rgb(51, 51, 51); line-height: 22.8571434020996px; background-color: rgb(255, 255, 255);\"><span style=\"line-height: 22.8571434020996px;\"></span></p><p>门头沟信息网（http://www.jflyfox.com/mtg以下简称“本网站”）是由个人承建的为人民服务的门头沟信息网站。</p><p>门头沟信息网主要提供了全面的生活，新闻，美食，旅游、教育等各个方面的关于门头沟的资讯。</p><p><br/></p><p>可通过如下方式联系我们：</p><p>联系方式：qq(369191470)</p><p style=\"white-space: normal; box-sizing: border-box; padding: 0px; color: rgb(51, 51, 51); line-height: 22.8571434020996px; background-color: rgb(255, 255, 255);\"><a target=\"_blank\" href=\"http://mail.qq.com/cgi-bin/qm_share?t=qm_mailme&email=_crKyc-LzszIzrmIiNealpQ\" style=\"box-sizing: border-box; margin: 0px; padding: 0px; color: rgb(66, 139, 202); text-decoration: none; background: 0px 0px;\"></a></p><p style=\"white-space: normal;\"><a target=\"_blank\" href=\"http://mail.qq.com/cgi-bin/qm_share?t=qm_mailme&email=allcU1tTW15dWiobG0QJBQc\" style=\"text-decoration: none;\"><img src=\"http://rescdn.qqmail.com/zh_CN/htmledition/images/function/qm_open/ico_mailme_02.png\"/></a></p><p><br/></p>', '1', '0', '11', '1', '1', '2', '13', null, null, null, null, null, '10', '2015-05-26', '系统管理员', null, null, '2015-05-26 10:39:24', '2015-05-26 10:39:24', '1');
INSERT INTO `tb_article` VALUES ('353', '90', '联系我们', '<p style=\"white-space: normal; box-sizing: border-box; padding: 0px; color: rgb(51, 51, 51); line-height: 22.8571434020996px; background-color: rgb(255, 255, 255);\"><span style=\"line-height: 22.8571434020996px;\">地址：北京市门头沟</span><br/></p><p style=\"white-space: normal; box-sizing: border-box; padding: 0px; color: rgb(51, 51, 51); line-height: 22.8571434020996px; background-color: rgb(255, 255, 255);\">联系方式：qq(369191470)</p><p style=\"white-space: normal; box-sizing: border-box; padding: 0px; color: rgb(51, 51, 51); line-height: 22.8571434020996px; background-color: rgb(255, 255, 255);\"><a target=\"_blank\" href=\"http://mail.qq.com/cgi-bin/qm_share?t=qm_mailme&email=_crKyc-LzszIzrmIiNealpQ\" style=\"box-sizing: border-box; margin: 0px; padding: 0px; color: rgb(66, 139, 202); text-decoration: none; background: 0px 0px;\"></a></p><p><a target=\"_blank\" href=\"http://mail.qq.com/cgi-bin/qm_share?t=qm_mailme&email=allcU1tTW15dWiobG0QJBQc\" style=\"text-decoration:none;\"><img src=\"http://rescdn.qqmail.com/zh_CN/htmledition/images/function/qm_open/ico_mailme_02.png\"/></a></p>', '0', '0', '11', '1', '1', '2', '15', null, null, null, null, null, '10', '2015-05-26', '系统管理员', null, null, '2015-05-26 10:39:53', '2015-05-26 10:39:53', '1');
INSERT INTO `tb_article` VALUES ('354', '90', '免责声明', '<p>门头沟信息网（http://www.jflyfox.com/mtg以下简称“本网站”）是由个人承建的为人民服务的门头沟信息网站。</p><p><br/></p><p>门头沟信息网主要提供了全面的生活，新闻，美食，旅游、教育等各个方面的关于门头沟的资讯。</p><p><br/></p><p>请用户使用本网站前仔细阅读本法律声明。您一旦使用本网站则表明您已明知并接受这些条款。用户必须按照法律法规的规定和本声明的要求使用本网站的信息和服务，否则本网站将依法追究有关当事人的法律责任。</p><p><br/></p><p><strong>一、知识产权</strong><br/></p><p>域名、标识及专有名称</p><p>本网站所使用的www.jflyfox.com、Logo等专有标识，未经网站所有者许可，任何人不得使用。</p><p><br/></p><p>版权</p><p>本网站包含的所有内容（包括但不限于：文本、图形、图片、视像及声音内容、LOGO标识、版面设计、专栏目录与名称、内容分类）的所有权归网站承办人所有。</p><p><br/></p><p>本网站的内容和软件均受《中华人民共和国著作权法》及其它相关法律的保护。任何单位或个人将本网站提供的内容与服务用于商业、盈利、广告性等目的时，需征得本网站承办人许可；将本网站提供的内容与服务用于非商业用途时，应遵守著作权法以及其他相关法律的规定，不得侵犯网站所有者及相关权利人的权益。</p><p><br/></p><p>版权异议</p><p>如权利人认为本网站内容中所涉及的文字作品、图片和音视频资料（以下简称“作品”），侵犯其著作权的，请及时书面通知本网站，本网站将依法删除被指侵权的作品或断开相应的链接；但权利人不能出示有效身份证明、著作权权属证明及侵权情况证明的，视为未提出异议。因权利人的通知导致本网站错误删除作品，或者错误断开与作品的链接的，本网站不承担任何责任。</p><p><br/></p><p><strong>二、用户信息</strong></p><p>信息采集</p><p>当用户浏览本网站时，本网站的系统将自动收集用户的IP地址及网站浏览信息。这些信息有助于我们在整体基础上评估我们的网站浏览者以及用户如何使用、浏览我们的网站，包括浏览者和用户对每一网页的访问次数、频率和浏览时间。通过收集上述信息，我们将进行流量统计，从而改进网站的管理和服务。</p><p><br/></p><p>信息使用</p><p>用户向本网站提供的个人信息将可能用于下列用途：</p><p>（1）核实用户身份，并提供相应的服务；</p><p>（2）用于编制有关网站使用的流量统计数据；</p><p>（3）通过发送电子邮件或以其它方式，告知用户相关信息。</p><p><br/></p><p>在未得到用户许可前，本网站不会将用户的任何个人信息提供给任何第三方，但发生以下事由的除外：</p><p>（1）按照本网站制订的规则和程序，本网站有充分的理由相信已经获得用户的授权；</p><p>（2）按照中华人民共和国的法律、法规、政策等规范性法律文件规定，要求本网站提供的；</p><p>（3）不能归咎于本网站的客观情势，所导致的个人资料的公开；</p><p>（4）因不可抗力，所导致的个人资料的公开；</p><p>（5）由于用户自身过错，而导致的个人资料的公开；</p><p>（6）超出本网站使用的硬件和软件的技术能力范围，所导致的个人资料的公开；</p><p>（7）紧急情况下为维护用户个人或社会大众的隐私和安全的；</p><p>（8）为维护本网站的所有权及相关权利的。</p><p><br/></p><p>信息安全</p><p>本网站将对用户所提供的资料进行严格的管理及保护，并将使用相应的技术措施（例如设置服务器备份数据和对用户密码加密等），防止用户的个人资料丢失、被盗用或遭窜改。如因不可抗力或计算机病毒感染、黑客攻击等特殊原因，导致用户信息被破坏、泄密并受到损失的，本网站将采取必要措施尽力减少用户的损失，但本网站对此不承担任何责任。</p><p><br/></p><p>用户权利</p><p>用户可通过本网站的相关网页查看、更新并修改提供给本网站的个人信息，也可以要求本网站删除该信息。</p><p><br/></p><p><strong>三、免责</strong></p><p>本网站对本网站上所有由第三方提供的信息、内容和服务，不提供明示或暗示的担保。本网站对使用上述信息、内容和服务所造成的任何损失不承担责任，包括直接损失和间接损失。</p><p><br/></p>', '0', '0', '11', '1', '1', '2', '20', null, null, null, null, null, '10', '2015-05-26', '系统管理员', null, null, '2015-05-26 10:40:04', '2015-05-26 10:40:04', '1');
INSERT INTO `tb_article` VALUES ('355', '90', '广告服务', '<p>广告服务请通过以下方式联系：</p><p style=\"box-sizing: border-box; margin-top: 0px; margin-bottom: 10px; color: rgb(51, 51, 51); font-family: &#39;Helvetica Neue&#39;, Helvetica, Arial, sans-serif; font-size: 14px; white-space: normal; padding: 0px; line-height: 22.8571434020996px; background-color: rgb(255, 255, 255);\">联系方式：qq(369191470)</p><p style=\"box-sizing: border-box; margin-top: 0px; margin-bottom: 10px; color: rgb(51, 51, 51); font-family: &#39;Helvetica Neue&#39;, Helvetica, Arial, sans-serif; font-size: 14px; white-space: normal; padding: 0px; line-height: 22.8571434020996px; background-color: rgb(255, 255, 255);\"><a target=\"_blank\" href=\"http://mail.qq.com/cgi-bin/qm_share?t=qm_mailme&email=_crKyc-LzszIzrmIiNealpQ\" style=\"box-sizing: border-box; color: rgb(66, 139, 202); text-decoration: none; margin: 0px; padding: 0px; background: 0px 0px;\"></a></p><p style=\"box-sizing: border-box; margin-top: 0px; margin-bottom: 10px; color: rgb(51, 51, 51); font-family: &#39;Helvetica Neue&#39;, Helvetica, Arial, sans-serif; font-size: 14px; line-height: 20px; white-space: normal; background-color: rgb(255, 255, 255);\"><a target=\"_blank\" href=\"http://mail.qq.com/cgi-bin/qm_share?t=qm_mailme&email=allcU1tTW15dWiobG0QJBQc\" style=\"box-sizing: border-box; color: rgb(51, 122, 183); text-decoration: none; background-color: transparent;\"><img src=\"http://rescdn.qqmail.com/zh_CN/htmledition/images/function/qm_open/ico_mailme_02.png\" style=\"box-sizing: border-box; border: 0px; vertical-align: middle;\"/></a></p>', '0', '0', '11', '1', '1', '2', '17', null, null, null, null, null, '10', '2015-05-26', '系统管理员', null, null, '2015-05-26 10:40:30', '2015-05-26 10:40:30', '1');
INSERT INTO `tb_article` VALUES ('356', '10', '滨河公园', '<p>中文名称：滨河公园</p><p>面 &nbsp; 积：48000平方米</p><p>建成时间：1988年</p><p>北京市门头沟区滨河公园建于1988年，建设前曾经是蚊蝇遍布的黑河污水沟河道。该公园总面积48000平方米，其中绿地面积36000平方米，南至双峪路环岛，北至河滩东路绿岛家园，南北长1610米，东西宽30米，贯穿6个居民小区，覆盖约2万人口，为开放性公园。</p><p>该公园以道路为中心，从南到北依次为自然景观、煤矿工人雕塑主题广场区、运动健身区、幼儿园、门球场和服务区，形成了“绿树成荫，鸟语花香，三季有花，四季常绿”的景观风貌。</p><p><br/></p>', '0', '0', '11', '1', '1', '2', '10', null, 'download/image_url/20150526_133245_578545.jpg', null, null, null, '10', '2015-05-26', '系统管理员', null, null, '2015-05-26 12:19:59', '2015-05-26 12:19:59', '1');
INSERT INTO `tb_article` VALUES ('357', '10', '晨曦公园', '<p>中文名称：晨曦公园</p><p>地理位置：北京市门头沟区黑河沟畔</p><p>建地面积：0.45万平方米</p><p>绿地面积：0.35万平方米</p><p>晨曦公园位于北京市门头沟区黑河沟畔，为城子大街与河滩邮政局东侧的滨河带状绿地，总面积0.45万平方米，其中绿地面积0.35万平方米。</p><p>为与河道治理景观相互呼应，园内设置标志景观石一块，建有休闲长廊一座，栽植各类树木，设置景观栏杆等，使该公园成为连接河与岸的介质，展示区域形象的载体。</p>', '0', '0', '11', '1', '1', '2', '10', null, 'download/image_url/20150526_133239_940979.jpg', null, null, null, '10', '2015-05-26', '系统管理员', null, null, '2015-05-26 12:20:21', '2015-05-26 12:20:21', '1');
INSERT INTO `tb_article` VALUES ('358', '10', '东辛房公园', '<p>中文名称：东辛房公园</p><p>建 &nbsp;于：1954年</p><p>占地面积：17000平方米</p><p>地面铺装：1200平米</p><p>东辛房公园建于1954年，位于北京市门头沟区东辛房大街南侧，总占地面积17000平方米。原公园内只是简单的种植了一些树木，后又进行了造景、喷泉、广场、园路的建设，使其满足周边城市居民休闲娱乐的需求。</p><p>2010年，新建仿古亭一座，地面铺装1200平米，建管理用房一处，并增设座椅、石桌、小品等。极大改善了城市形象、提升了城市品位，为东辛房地区广大市民创建了更加优美舒适的生活、休闲和娱乐环境。</p><p><br/></p>', '0', '0', '11', '1', '1', '2', '10', null, 'download/image_url/20150526_133231_831983.jpg', null, null, null, '10', '2015-05-26', '系统管理员', null, null, '2015-05-26 12:20:39', '2015-05-26 12:20:39', '1');
INSERT INTO `tb_article` VALUES ('359', '10', '福鼎公园', '<p>中文名称：福鼎公园</p><p>地理位置：北京市门头沟区</p><p>占地面积：21550平方米</p><p>建 &nbsp; 于：2003年</p><p>福鼎公园位于北京市门头沟区门城新城北部城子大街和滨河路交汇处，西至城子大街，北至水闸公路桥，东至滨河路，南至铁路护坡，总面积3.3万平方米。公园种植各类苗木52000余株，绿化面积近1.2万平方米。园内主要以植物造景为主，小品景致有“福亭”、“福鼎”、“福桥”、健身广场、假山、喷泉、叠水、景观灯等。</p><p>公园里还有一处承载着历史印记的建筑——桥头碉堡，它见证了日本侵略中国的历史。该公园已与建于2003年占地21550平方米的在水一方公园连成一体。</p><p><br/></p>', '0', '0', '11', '1', '1', '2', '10', null, 'download/image_url/20150526_133221_52915.jpg', null, null, null, '10', '2015-05-26', '系统管理员', null, null, '2015-05-26 12:28:34', '2015-05-26 12:28:34', '1');
INSERT INTO `tb_article` VALUES ('360', '10', '光荣园', '<p><span>光荣园位于门头沟区光荣院，北邻门头沟区档案馆，依山傍水、交通便利。公园修建于2010年10月，占地面积6500平方米，其中绿地面积3500平方米。</span></p><p><span>绿地内建花岗岩步道270平方米，景观石2块，修建京西革命光荣墙1面，缅怀门头沟区战争年代的革命先驱。</span></p><p><span>目前工程主体已全部完工，它在为广大市民提供良好城市居住环境的同时，承载着弘扬门头沟区革命老区光荣传统、记载京西革命文化的重要使命，是青少年爱国主义教育基地。光荣园的修建得到全区人民的大力拥护和社会各界的普遍关注，接受社会捐助500万元。</span></p>', '0', '0', '11', '1', '1', '2', '10', null, 'download/image_url/20150526_133213_637400.jpg', null, null, null, '10', '2015-05-26', '系统管理员', null, null, '2015-05-26 12:28:42', '2015-05-26 12:28:42', '1');
INSERT INTO `tb_article` VALUES ('361', '10', '黑山公园', '<p>中文名称：黑山公园</p><p>地理位置：中国北京</p><p>开放时间：6：00——8：30</p><p>景点级别：北京市精品公园</p><p>黑山公园历史上曾是清代葛布喇墓地，1956年辟为公园，是解放初期北京市修建的少数几个公园之一。公园北部为自然山丘，园内百年松柏苍劲，林木花疏扶摇，具备良好的园林基础。</p><p>多年来疏于管理，特别是“文化大革命”遭遇破坏，部分用地被挤占，园容每况逾下。上世纪90年代曾做小规模整修，2004年作为门头沟区重点工程全面改造。仅半年时间，公园完成了拆墙透绿、景区建设、平面布局和竖向的调整。增加了广场、园门、景亭、喷泉、健身及儿童游憩园地、园林服务设施等，同时对湖面、水榭、山体和洞门做了适度完善，使公园功能和景观都有了大幅度的提升。2004年被评为北京市精品公园。</p><p>地址：北京市门头沟区黑山大街12号</p><p><br/></p>', '0', '0', '11', '1', '1', '2', '10', null, 'download/image_url/20150526_133202_670496.jpg', null, null, null, '10', '2015-05-26', '系统管理员', null, null, '2015-05-26 12:28:49', '2015-05-26 12:28:49', '1');
INSERT INTO `tb_article` VALUES ('362', '10', '京浪岛文化体育公园', '<p>中文名称：京浪岛文化体育公园</p><p>建造时间：2010年</p><p>占地面积：18.3万平方米</p><p>京浪岛文化体育公园建于2010年，位于门头沟区三家店水库上游，西临水担路，东邻109国道和北京六环路，总面积18.3万平方米，其中绿地面积12.16万平方米，绿地率达到95%以上。公园内建有棒球场、垒球场、羽毛球场、网球场和拉膜亭等体育休闲设施。公园的建设使该岛成为一个花团锦簇、四季常青的生态绿岛，对涵养水源、美化环境、提高居民生活质量起到巨大的提升作用。</p><p><br/></p>', '1', '0', '11', '1', '1', '2', '10', null, 'download/image_url/20150526_133147_592081.jpg', null, null, null, '10', '2015-05-26', '系统管理员', null, null, '2015-05-26 12:28:58', '2015-05-26 12:28:58', '1');
INSERT INTO `tb_article` VALUES ('363', '10', '京门铁路遗址公园', '<p>中文名称：京门铁路遗址公园</p><p>地理位置：北京市</p><p>气候类型：温带季风气候</p><p>占地面积：1.2万平方米</p><p>京门铁路遗址公园位于北京市门头沟区城子大街北侧，北接京门铁路，南至排洪沟，东至城子大街，西至铁路挡墙，总面积1.2万平方米，其中绿地面积0.8万平方米。该公园以铁路建设为主题，采用现代与传统相结合的设计手法，设置了风车、火车头等标志性景观构筑，着力打造出了京西独具特色的公园景观，形成景观亮点，在充分融合京西生态环境特色的基础上，满足了游人赏景休憩的游园要求，营造出实用美观，舒适宜人的公园环境。</p><p><br/></p>', '0', '0', '11', '1', '1', '2', '10', null, 'download/image_url/20150526_133135_163999.jpg', null, null, null, '10', '2015-05-26', '系统管理员', null, null, '2015-05-26 12:29:06', '2015-05-26 12:29:06', '1');
INSERT INTO `tb_article` VALUES ('364', '10', '立思辰公园', '<p>中文名：立思辰公园</p><p>总面积：0.5万平方米</p><p>绿地面积：0.35万平方米</p><p>立思辰公园位于北京市门头沟区德露苑小区南侧，属社会性捐助公园，公园西至滨河路，南至规划中门寺沟，北临区法院，总面积0.5万平方米，其中绿地面积0.35万平方米。</p><p>该公园周边多为居住区，公园设计上在延续场地绿地为主的现状布局下，将现代简约的设计元素融入场地。园区内设置了大象雕塑、健身器械、休闲广场等小品和设施，营造出风格简约、温馨舒适的社区公园环境。</p><p><br/></p>', '0', '0', '11', '1', '1', '2', '10', null, 'download/image_url/20150526_133124_592594.jpg', null, null, null, '10', '2015-05-26', '系统管理员', null, null, '2015-05-26 12:29:14', '2015-05-26 12:29:14', '1');
INSERT INTO `tb_article` VALUES ('365', '10', '门矿遗址公园', '<p>中文名：门矿遗址公园</p><p>位 &nbsp;于：北京市门头沟区</p><p>总面积：约887亩</p><p>原 &nbsp;为：门头沟煤矿</p><p>门矿遗址公园位于北京市门头沟区门城新城龙泉镇西北部九龙山下，总面积887亩。园区主体原为门头沟煤矿、城子煤矿近百年堆积的300余万立方的三座煤矸石山。</p><p>多年来，煤矸石山裸露的山体，每遇刮风，黑色扬沙肆虐之极，对门城地区乃至北京城区都造成很大的空气污染，是一个巨大的污染源。遇到雨季，山洪冲刷下的煤矸石沫形成的污水更是严重影响着当地居民的正常生活。</p><p>为改善地区居民生活环境，打造宜居城市，工程已于2010年9月开始施工，先后完成了土方清运、管线铺设及苗木种植等。</p><p>工程还将规划设计健身、休闲、娱乐等基础设施，并计划把周边矿区纳入到改造范围，提升改建成集居民休闲、健身、娱乐、避险为一体的门矿遗址公园。</p><p><br/></p>', '2', '0', '11', '1', '1', '2', '10', null, null, null, null, null, '10', '2015-05-26', '系统管理员', null, null, '2015-05-26 12:29:21', '2015-05-26 12:29:21', '1');
INSERT INTO `tb_article` VALUES ('366', '10', '门头沟新城滨河森林公园', '<p>中文名<span style=\"line-height: 22.8571434020996px; white-space: normal;\">称</span>：门头沟新城滨河森林公园</p><p>面 &nbsp; 积：586.68万平方米</p><p>地理区域：北京市门头沟区新城南部规划绿线范围内</p><p>绿地面积：524.5万平方米</p><p>门头沟新城滨河森林公园，位于北京市门头沟区新城南部规划绿线范围内，总面积586.68万平方米，其中绿地面积524.5万平方米，已于2010年下半年开工，预计2012年6月建成投入使用。</p><p>该公园地处著名的永定镇“定都峰”山脚下，处于长安街西延长线终点，传说元末明初第一谋士——姚广孝曾登顶此山，勘测地形再建北京城，此后该山被称为定都峰。定都峰四周群山绵延逶迤，山峰上巨石嶙峋，有“京西观景第一峰”之称。</p><p>山下龙口水库目前仅有少量水面，通过改造水库、种植景观林，恢复生态景观，最终环绕水库形成万亩森林公园，将有效地改善门头沟新城的生态环境，带动周边地区休闲度假产业的发展。</p><p><br/></p>', '0', '0', '11', '1', '1', '2', '10', null, 'download/image_url/20150526_133107_587987.jpg', null, null, null, '10', '2015-05-26', '系统管理员', null, null, '2015-05-26 12:29:38', '2015-05-26 12:29:38', '1');
INSERT INTO `tb_article` VALUES ('367', '10', '葡东公园', '<p>中文名：葡东公园</p><p>位 &nbsp;置：门头沟区葡萄嘴环岛东侧</p><p>占地面积：16000平方米</p><p>绿地面积：12500平方米</p><p>葡东公园位于门头沟区葡萄嘴环岛东侧，南至葡东小区入口，北与中门寺生态沟相连，是专供周边居民休闲、娱乐、健身的公共场所。公园占地面积16000平方米，其中绿地面积12500平方米。园内休闲步道3560平方米，人工地形设计精巧，大型景观灯亮丽多彩，整体突显绿色、舒适、健康的生活品质，令人流连忘返。</p><p><br/></p>', '0', '0', '11', '1', '1', '2', '10', null, 'download/image_url/20150526_133054_81675.jpg', null, null, null, '10', '2015-05-26', '系统管理员', null, null, '2015-05-26 12:29:45', '2015-05-26 12:29:45', '1');
INSERT INTO `tb_article` VALUES ('368', '10', '葡山公园', '<p>中文名称：葡山公园</p><p>地理位置：北京市</p><p>占地面积：92000平方米</p><p>建立时间：2003年</p><p>葡山公园位于门头沟区南北城区交接处，紧邻葡萄嘴环岛，北倚美丽的京西葡山，建于2003年，占地面积92000平方米，其中绿地面积7万平方米。园内主要以植物造景为主，设有仿古牌楼、健身广场、观景亭、湖区、长廊、水榭等。公园周边绿山环绕，环境清幽，令人心旷神怡。</p><p><br/></p>', '0', '0', '11', '1', '1', '2', '10', null, 'download/image_url/20150526_133014_302376.jpg', null, null, null, '10', '2015-05-26', '系统管理员', null, null, '2015-05-26 12:29:53', '2015-05-26 12:29:53', '1');
INSERT INTO `tb_article` VALUES ('369', '10', '石龙公园', '<p>中文名：<span style=\"line-height: 22.8571434020996px; white-space: normal;\">石</span>龙公园</p><p>建设面积：2200平方米</p><p>地 &nbsp;点：北京市门头沟区石龙医院</p><p>特 &nbsp;点：为游人增添了游览情趣</p><p>石龙公园位于北京市门头沟区石龙医院院内南侧（原锅炉房拆除地块），建设面积2200平方米。</p><p>通过精心的规划设计和景观改造，公园内建有简洁的休闲广场、八角重檐亭、古建廊架和休憩小品，为游人增添了游览情趣。</p><p>场地绿化以乔、灌、草复层生态搭配种植。利用常绿和落叶植物相互搭配，形成四季有景，层次丰富的景观环境。植物造景以春花植物为主，利用丰富多变的植物颜色，为医院环境增添勃勃生机，愉悦病人身心。</p><p><br/></p>', '0', '0', '11', '1', '1', '2', '10', null, 'download/image_url/20150526_133002_123029.jpg', null, null, null, '10', '2015-05-26', '系统管理员', null, null, '2015-05-26 12:30:02', '2015-05-26 12:30:02', '1');
INSERT INTO `tb_article` VALUES ('370', '10', '石门营公园', '<p>中文名称：石门营公园</p><p>地理位置：北京市</p><p>建 &nbsp; 于：2008年</p><p>建设面积：56000平方米</p><p>石门营公园建于2008年7月，建设面积56000平方米，其中绿地面积51000平方米。地处北京市门头沟区永定镇南部，是展示门城面貌的“门城区级休闲公园”。全年免费对群众开放。</p><p>石门营公园地处北京市门头沟区永定镇南部，西邻石门营环岛，与采空棚户区改造石门营安置房社区仅一路之隔，是展示门城面貌的“门城区级休闲公园”，也是门头沟区承载群众休闲的重点公园，为改善棚户区改造安置房社区的居住环境发挥了重要作用。</p><p>在整体风格上，该公园强调场地脉落空间的延续，彰显总体自然和谐的视觉效果，突出石门营的“石”文化，以石溪、石雾、石门、石制小品等表现手法，强调质朴之感。</p><p><br/></p>', '2', '0', '11', '1', '1', '2', '10', null, 'download/image_url/20150526_132552_746440.jpg', null, null, null, '10', '2015-05-26', '系统管理员', null, null, '2015-05-26 12:30:08', '2015-05-26 12:30:08', '1');
INSERT INTO `tb_article` VALUES ('371', '10', '西苑公园', '<p>西苑公园位于北京市门头沟区葡萄嘴环岛东南角，西至三石路，南邻路政局，总面积0.56万平方米，其中绿地面积0.4万平方米。</p><p>该公园为城市街旁绿地，选用多种乔灌木进行搭配，做到层次分明、错落有致、丰富多彩，形成三季有花、四季长青的景观效果。</p><p>公园设计建造的休闲文化亭廊和现代艺术景墙，体现了门头沟新时代的城市风貌，给人们提供了休闲散步、交往娱乐等公共活动的场所。</p>', '1', '0', '11', '1', '1', '2', '10', null, 'download/image_url/20150526_132320_829676.jpg', null, null, null, '10', '2015-05-26', '系统管理员', null, null, '2015-05-26 12:30:15', '2015-05-26 12:30:15', '1');
INSERT INTO `tb_article` VALUES ('372', '10', '新桥公园', '<p>新桥公园位于北京市门头沟区新桥家园北侧，占地3600平方米，公园紧邻新桥大街，周边遍布多个住宅区，地处闹市，乱中取静，精巧雅致。</p><p>2010年，对该公园进行了升级改造。通过种植景观林、草本花卉和铺装步道进一步改善园区周边环境。内部建有景观厅、喷泉水池、主题雕塑等小品，完善了休闲座椅、垃圾桶等基础设施，为当地居民提供休闲、娱乐、小憩等良好的健身场所。</p>', '0', '0', '11', '1', '1', '2', '10', null, 'download/image_url/20150526_132049_749662.jpg', null, null, null, '10', '2015-05-26', '系统管理员', null, null, '2015-05-26 12:30:21', '2015-05-26 12:30:21', '1');
INSERT INTO `tb_article` VALUES ('373', '10', '永定河公园', '<p>中文名称：永定河公园</p><p>地理位置：北京市门头沟区门城湖畔</p><p>占地面积：1.5万平方米</p><p>著名景点：母亲雕塑</p><p>永定河公园位于北京市门头沟区门城湖畔，西至滨河路，东临永定河，南至北京市永定河管理处，北至城子东街，总面积1.5万平方米，其中绿地面积0.96万平方米，总投资2180万元。园区内建有一座占地1128平方米、高6.5米的三层大型观景台和突出永定河厚重历史文化的母亲雕塑，既满足了周边居民的休闲健身需求，也实现了空间功能与审美需求的整合，是永定河绿色生态廊道城市段起点的滨河文化休闲公园，具有独特的地域性质文化内涵。</p><p><br/></p>', '0', '0', '11', '1', '1', '2', '10', null, 'download/image_url/20150526_131826_480861.jpg', null, null, null, '10', '2015-05-26', '系统管理员', null, null, '2015-05-26 12:30:32', '2015-05-26 12:30:32', '1');
INSERT INTO `tb_article` VALUES ('374', '10', '永定河森林公园', '<p><span>中文名：永定河森林公园</span></p><p><span>免费开放：2013年4月28日</span></p><p><span>绿化面积：175万平方米</span></p><p><span>永定河森林公园位于门头沟区永定镇与石景山区、丰台区交界处，紧临108国道。这里经过几十年采挖砂石形成的深达42米的大砂坑曾是北京西部最大的沙尘污染源。为消除这一污染源，落实“生态发展涵养区”的功能定位，市、区政府加大投资力度，对这里实施生态修复工程。</span></p><p><span>永定河森林公园是北京市委、市政府关于永定河绿色生态发展带“五园一带”项目首个启动建设的公园，已于2013年4月28日向社会免费开放。公园将首钢钢渣铁路线改造为观光线路，引进观光小火车和铁路自行车，开创了北京市乘火车游公园之先河，市民可以乘小火车赏花、观湖、隔河远眺园博园，体验永定河文化。</span></p><p><span><br/></span></p><p><span>截止2010年，累计完成投资1.1亿元，绿化面积达175万平方米。栽植乔、灌、花木40余个品种共计163万余株，种植地被植物98万平方米、水生植物1.5万平方米 。</span></p><p><span>通过大面积营造景观生态林，彻底改变了大沙坑的整体面貌，成为集观景、休憩健身、集雨、防风固沙等多项功能于一身的生态型公园。公园建成以来，党和国家领导人、共和国将军、部长都曾到此参加全民义务植树活动。</span></p><p><span>永定河休闲森林公园位于石景山西南部，永定河左岸南大荒滩地上，距市中心18公里。公园东邻五环，北接莲石西路，与园博园隔河相对。</span></p><p><span>根据2009年北京市委、市政府提出的《北京市永定河生态走廊建设规划》和《永定河绿色生态发展带“五园一带”项目规划方案》，本公园于2012年开始建设，2013年4月建成，是“五园一带”首个开放的公园。</span></p><p><span>公园占地121公顷，因地制宜，建成“两核”（主门区和永定河文化广场）、“一带”（滨河风景带）、“十大景点”（镇水牛、十八磴、海棠谷、桃李园等）的景观格局，将昔日黄沙漫天的荒滩地变为空气清新、鸟语花香的公园。</span></p><p><span>公园用雕塑、石碑、景墙、铺装等形式，将永定河文化的内涵点缀其中，在和谐、自然的休憩环境中融入文化的气息。</span></p><p><span>公园将首钢钢渣铁路线改造为观光车道，引进观光小火车和铁路自行车，供市民赏花、看湖、眺园博，体验永定河文化。</span></p><p><span>永定河休闲森林公园为公益性城市公园，面向社会免费开放，以其优美的环境为广大市民营造了一个愉悦身心、亲近自然的健身休闲活动场所</span></p><p><br/></p>', '2', '0', '11', '1', '1', '2', '10', null, 'download/image_url/20150526_131132_167617.jpg', null, null, null, '10', '2015-05-26', '系统管理员', null, null, '2015-05-26 12:30:39', '2015-05-26 12:30:39', '1');
INSERT INTO `tb_article` VALUES ('375', '10', '永定河文化广场', '<p>中文名：永定河文化广场</p><p>建 于：1998年</p><p>总面积：34万平方米</p><p>原 则：因地制宜</p><p>北京市门头沟区永定河文化广场公园建于1998年，总面积34万平方米，是在挖沙形成的一处人工沙坑上建设而成的，是集绿化美化、休闲健身、节水环保、应急避险等多种功能于一体的城市精品公园。</p><p>公园建设遵循因地制宜的原则，园内种植的各类乔灌花草形成了明显色彩和季相变化；西南部有一处大型音乐喷泉；东南部为健身广场，设有儿童、青年、中老年健身区和乒乓球场、篮球场等；在沙坑中心区，通过收集雨水、保水的节水环保设施建设，形成了叠水景观；该公园为周边多个大型社区的两万多市民提供休闲娱乐健身的场地。</p>', '4', '0', '11', '1', '1', '2', '10', null, 'download/image_url/20150526_123423_350923.jpg', null, null, null, '10', '2015-05-26', '系统管理员', null, null, '2015-05-26 12:30:46', '2015-05-26 12:30:46', '1');
INSERT INTO `tb_article` VALUES ('376', '5', '大峪第一小学', null, '0', '0', '11', '1', '1', '2', '10', null, null, null, null, null, '10', '2015-05-26', '系统管理员', null, null, '2015-05-26 13:58:21', '2015-05-26 13:58:21', '1');
INSERT INTO `tb_article` VALUES ('377', '5', '大峪第二小学', null, '0', '0', '11', '1', '1', '2', '10', null, null, null, null, null, '10', '2015-05-26', '系统管理员', null, null, '2015-05-26 13:58:32', '2015-05-26 13:58:32', '1');
INSERT INTO `tb_article` VALUES ('378', '5', '城子小学', null, '2', '0', '11', '1', '1', '2', '10', null, null, 'http://i11.tietuku.cn/e91de2ba989474a1.jpg', null, null, '10', '2015-05-26', '系统管理员', null, null, '2015-05-26 13:58:48', '2015-05-26 13:58:48', '1');
INSERT INTO `tb_article` VALUES ('379', '4', '门头沟区博物馆', null, '0', '0', '11', '1', '1', '2', '10', null, null, null, null, null, '10', '2015-05-26', '系统管理员', null, null, '2015-05-26 15:49:35', '2015-05-21 15:49:35', '1');
INSERT INTO `tb_article` VALUES ('381', '100', '我的博文', '<p>测试</p>', '0', '0', '11', '1', '1', '2', '20', null, null, null, null, null, '10', '2015-06-04', '系统管理员', null, null, null, '2015-06-17 22:23:52', '1');
INSERT INTO `tb_article` VALUES ('382', '100', '测试博文', '<p>哈哈 我来了</p>', '15', '0', '11', '1', '1', '2', '20', null, null, null, null, null, '10', '2015-06-04', '系统管理员', null, null, null, '2015-06-17 22:27:55', '2');
INSERT INTO `tb_article` VALUES ('2301', '230', '英雄联盟', '<p>内容管理平台</p>', '124', '123', '12', '2', '1', '1', '1', null, 'download/image_url/20150529_102007_298104.jpg', 'http://i4.tietuku.cn/0bf9b53228782326.png', null, null, '10', '2014-03-05', '系统管理员', '2015-01-29', '2015-01-23', '2015-01-28 17:29:55', '2015-01-28', '1');
INSERT INTO `tb_article` VALUES ('2351', '231', '捐赠我们', '<p style=\"box-sizing: border-box; padding: 0px; color: rgb(51, 51, 51); line-height: 22.8571434020996px; white-space: normal; text-align: center; background-color: rgb(255, 255, 255);\">支付宝捐赠二维码 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 微信捐赠二维码</p><p style=\"box-sizing: border-box; padding: 0px; color: rgb(51, 51, 51); line-height: 22.8571434020996px; white-space: normal; text-align: center; background-color: rgb(255, 255, 255);\"><img src=\"http://ww1.sinaimg.cn/mw690/3fc7e281jw1eqec436tzwj2074074mxr.jpg\" style=\"box-sizing: border-box; margin: 0px; padding: 0px; border: 0px; vertical-align: middle;\"/>&nbsp; &nbsp;&nbsp;<img src=\"http://ww1.sinaimg.cn/mw690/3fc7e281jw1es3jr0k25xj20a50a5q3v.jpg\" style=\"box-sizing: border-box; margin: 0px; padding: 0px; border: 0px; vertical-align: middle; line-height: 1.42857143; width: 256px; height: 256px;\"/></p><p style=\"box-sizing: border-box; padding: 0px; color: rgb(51, 51, 51); line-height: 22.8571434020996px; white-space: normal; text-align: center; background-color: rgb(255, 255, 255);\"><br/></p><br/><p><br/></p>', '0', '0', '11', '1', '1', '2', '19', null, null, null, null, null, '10', '2015-05-26', '系统管理员', null, null, '2015-05-26 10:39:11', '2015-05-26 10:39:11', '1');
INSERT INTO `tb_article` VALUES ('2352', '231', '关于我们', '<p style=\"white-space: normal; box-sizing: border-box; padding: 0px; color: rgb(51, 51, 51); line-height: 22.8571434020996px; background-color: rgb(255, 255, 255);\"><span style=\"line-height: 22.8571434020996px;\"></span></p><p>可通过如下方式联系我们：<br/></p><p>联系方式：qq(369191470)</p><p style=\"white-space: normal; box-sizing: border-box; padding: 0px; color: rgb(51, 51, 51); line-height: 22.8571434020996px; background-color: rgb(255, 255, 255);\"><a target=\"_blank\" href=\"http://mail.qq.com/cgi-bin/qm_share?t=qm_mailme&email=_crKyc-LzszIzrmIiNealpQ\" style=\"box-sizing: border-box; margin: 0px; padding: 0px; color: rgb(66, 139, 202); text-decoration: none; background: 0px 0px;\"></a></p><p style=\"white-space: normal;\"><a target=\"_blank\" href=\"http://mail.qq.com/cgi-bin/qm_share?t=qm_mailme&email=allcU1tTW15dWiobG0QJBQc\" style=\"text-decoration: none;\"><img src=\"http://rescdn.qqmail.com/zh_CN/htmledition/images/function/qm_open/ico_mailme_02.png\"/></a></p><p><br/></p>', '1', '0', '11', '1', '1', '2', '13', null, null, null, null, null, '10', '2015-05-26', '系统管理员', null, null, '2015-05-26 10:39:24', '2015-05-26 10:39:24', '1');
INSERT INTO `tb_article` VALUES ('2353', '231', '联系我们', '<p style=\"white-space: normal; box-sizing: border-box; padding: 0px; color: rgb(51, 51, 51); line-height: 22.8571434020996px; background-color: rgb(255, 255, 255);\"><span style=\"line-height: 22.8571434020996px;\">地址：北京市</span><br/></p><p style=\"white-space: normal; box-sizing: border-box; padding: 0px; color: rgb(51, 51, 51); line-height: 22.8571434020996px; background-color: rgb(255, 255, 255);\">联系方式：qq(369191470)</p><p style=\"white-space: normal; box-sizing: border-box; padding: 0px; color: rgb(51, 51, 51); line-height: 22.8571434020996px; background-color: rgb(255, 255, 255);\"><a target=\"_blank\" href=\"http://mail.qq.com/cgi-bin/qm_share?t=qm_mailme&email=_crKyc-LzszIzrmIiNealpQ\" style=\"box-sizing: border-box; margin: 0px; padding: 0px; color: rgb(66, 139, 202); text-decoration: none; background: 0px 0px;\"></a></p><p><a target=\"_blank\" href=\"http://mail.qq.com/cgi-bin/qm_share?t=qm_mailme&email=allcU1tTW15dWiobG0QJBQc\" style=\"text-decoration:none;\"><img src=\"http://rescdn.qqmail.com/zh_CN/htmledition/images/function/qm_open/ico_mailme_02.png\"/></a></p>', '0', '0', '11', '1', '1', '2', '15', null, null, null, null, null, '10', '2015-05-26', '系统管理员', null, null, '2015-05-26 10:39:53', '2015-05-26 10:39:53', '1');
INSERT INTO `tb_article` VALUES ('2354', '231', '免责声明', '<p>本网站对本网站上所有由第三方提供的信息、内容和服务，不提供明示或暗示的担保。本网站对使用上述信息、内容和服务所造成的任何损失不承担责任，包括直接损失和间接损失。<br/></p><p><br/></p>', '0', '0', '11', '1', '1', '2', '20', null, null, null, null, null, '10', '2015-05-26', '系统管理员', null, null, '2015-05-26 10:40:04', '2015-05-26 10:40:04', '1');
INSERT INTO `tb_article` VALUES ('2355', '231', '广告服务', '<p>广告服务请通过以下方式联系：</p><p style=\"box-sizing: border-box; margin-top: 0px; margin-bottom: 10px; color: rgb(51, 51, 51); font-family: &#39;Helvetica Neue&#39;, Helvetica, Arial, sans-serif; font-size: 14px; white-space: normal; padding: 0px; line-height: 22.8571434020996px; background-color: rgb(255, 255, 255);\">联系方式：qq(369191470)</p><p style=\"box-sizing: border-box; margin-top: 0px; margin-bottom: 10px; color: rgb(51, 51, 51); font-family: &#39;Helvetica Neue&#39;, Helvetica, Arial, sans-serif; font-size: 14px; white-space: normal; padding: 0px; line-height: 22.8571434020996px; background-color: rgb(255, 255, 255);\"><a target=\"_blank\" href=\"http://mail.qq.com/cgi-bin/qm_share?t=qm_mailme&email=_crKyc-LzszIzrmIiNealpQ\" style=\"box-sizing: border-box; color: rgb(66, 139, 202); text-decoration: none; margin: 0px; padding: 0px; background: 0px 0px;\"></a></p><p style=\"box-sizing: border-box; margin-top: 0px; margin-bottom: 10px; color: rgb(51, 51, 51); font-family: &#39;Helvetica Neue&#39;, Helvetica, Arial, sans-serif; font-size: 14px; line-height: 20px; white-space: normal; background-color: rgb(255, 255, 255);\"><a target=\"_blank\" href=\"http://mail.qq.com/cgi-bin/qm_share?t=qm_mailme&email=allcU1tTW15dWiobG0QJBQc\" style=\"box-sizing: border-box; color: rgb(51, 122, 183); text-decoration: none; background-color: transparent;\"><img src=\"http://rescdn.qqmail.com/zh_CN/htmledition/images/function/qm_open/ico_mailme_02.png\" style=\"box-sizing: border-box; border: 0px; vertical-align: middle;\"/></a></p>', '1', '0', '11', '1', '1', '2', '17', null, null, null, null, null, '10', '2015-05-26', '系统管理员', null, null, '2015-05-26 10:40:30', '2015-05-26 10:40:30', '1');
INSERT INTO `tb_article` VALUES ('2408', '230', '英雄联盟', '<p>&nbsp;</p>', '8', '0', '11', '1', '1', '2', '10', null, null, 'http://i4.tietuku.cn/48fb17b99984db50.jpg', null, null, '10', '2016-01-20', '系统管理员', null, null, '2016-01-20 17:02:55', '2016-01-20 17:02:55', '1');
INSERT INTO `tb_article` VALUES ('2409', '230', '德玛西亚皇子', '<p>德玛西亚皇子,别名四阿哥,周杰伦,嘉文四世。</p><p>E-Q技能的存在为嘉文带来了不错的机动性，还附带三种控制技能让他成为一个让人十分头疼的坦克，</p><p>你无法在第一时间秒掉他，却也无法忽视他的存在，是一个团队价值非常高的英雄。</p>', '3', '0', '11', '1', '1', '2', '11', null, null, 'http://i4.tietuku.cn/a1babff3fc98c6c3.jpg', null, null, '10', '2016-01-20', '系统管理员', null, null, '2016-01-20 17:04:15', '2016-01-20 17:04:15', '1');
INSERT INTO `tb_article` VALUES ('2410', '230', '德邦总管', '<p>德邦总管,别名赵信,菊花信。</p><p>赵信很适合做团战发起人，第一时间冲锋加大招尽量多的造成伤害。</p><p>至少加1点战嚎来减少技能的冷却。对于赵信来说，攻速道具是收益最高的选择。缺点赵信是一个只进不退的英雄。</p>', '1', '0', '11', '1', '1', '2', '12', null, null, 'http://i4.tietuku.cn/56d0d1fceb659858.jpg', null, null, '10', '2016-01-20', '系统管理员', null, null, '2016-01-20 17:04:59', '2016-01-20 17:04:59', '1');
INSERT INTO `tb_article` VALUES ('2411', '230', '黑暗之女', '<p>黑暗之女,别名火女,萝莉,安妮。</p><p>有着被动技能和Q技能的回蓝特效让安妮在对线期就能占尽优势，可以在前期保证自己的经济，</p><p>而技能本身的固定伤害让安妮在到达六级后就可以用一套连招给对方带来极大伤害甚至秒掉对手。</p>', '0', '0', '11', '1', '1', '2', '13', null, null, 'http://i4.tietuku.cn/5ec8c315c1f879ec.jpg', null, null, '10', '2016-01-20', '系统管理员', null, null, '2016-01-20 17:05:33', '2016-01-20 17:05:33', '1');
INSERT INTO `tb_article` VALUES ('2412', '230', '皎月女神', '<p>皎月女神,别名皎月,黛安娜。</p><p>戴安娜作为一个近战法师，有着其他AP难以企及的爆发，同时还有突进、控制以及不错的生存能力。</p><p>但缺点也很明显，作为一个法师却是近战，而且又没有逃生技能，如果团战的时机切入时机错误，经常会有去无回。</p>', '2', '0', '11', '1', '1', '2', '14', null, null, 'http://i4.tietuku.cn/ccf60db2767080e8.jpg', null, null, '10', '2016-01-20', '系统管理员', null, null, '2016-01-20 17:05:59', '2016-01-20 17:05:59', '1');
INSERT INTO `tb_article` VALUES ('2413', '230', '寒冰射手', '<p>寒冰射手,别名寒冰,艾希,爱射,冰弓,冰女。</p><p>寒冰射手艾希是一个能力全面的防守型ADCarry，她的W可以进攻性地推线，也是个配合她的Q进行风筝的强力技能。</p>', '0', '0', '11', '1', '1', '2', '15', null, null, 'http://i4.tietuku.cn/9346cade1e9640a5.jpg', null, null, '10', '2016-01-20', '系统管理员', null, null, '2016-01-20 17:06:31', '2016-01-20 17:06:31', '1');
INSERT INTO `tb_article` VALUES ('2414', '230', '联系我们', '<p>作者：Fly的狐狸</p><p>QQ：330627517</p><p><a target=\"_blank\" href=\"http://mail.qq.com/cgi-bin/qm_share?t=qm_mailme&email=_crKyc-LzszIzrmIiNealpQ\" style=\"text-decoration:none;\"><img src=\"http://rescdn.qqmail.com/zh_CN/htmledition/images/function/qm_open/ico_mailme_02.png\"/></a></p>', '1', '0', '11', '1', '1', '2', '16', null, null, 'http://i4.tietuku.cn/400844eb3b32e353.jpg', null, null, '10', '2016-01-20', '系统管理员', null, null, '2016-01-20 17:07:05', '2016-01-20 17:07:05', '1');
INSERT INTO `tb_article` VALUES ('4230', '253', '个人博客jflyfox', '<p style=\"margin-top: 0px; margin-bottom: 10px; color: rgb(0, 0, 0); font-family: 微软雅黑, Verdana, sans-serif, 宋体; font-size: 13px; line-height: 21.3333320617676px; white-space: normal;\"><span style=\"line-height: 1.5; font-size: 10pt;\">这是一个简单不的不能再简单的Blog软件，本着提高自己，帮助新人的态度。</span><span style=\"font-size: 10pt; line-height: 1.5;\">刚刚开始做，还望大家多多包涵。</span></p><p style=\"margin-top: 0px; margin-bottom: 10px; color: rgb(0, 0, 0); font-family: 微软雅黑, Verdana, sans-serif, 宋体; font-size: 13px; line-height: 21.3333320617676px; white-space: normal;\">Jflyfox采用了简洁强大的JFinal作为web框架，模板引擎用的是beelt，数据库用mysql，前端bootstrap框架。</p><p style=\"margin-top: 0px; margin-bottom: 10px; color: rgb(0, 0, 0); font-family: 微软雅黑, Verdana, sans-serif, 宋体; font-size: 13px; line-height: 21.3333320617676px; white-space: normal;\">源码地址：<a href=\"http://git.oschina.net/flyfox/jflyfox\" target=\"_blank\" style=\"color: rgb(62, 98, 166); outline: 0px;\">http://git.oschina.net/flyfox/jflyfox</a></p><p style=\"margin-top: 0px; margin-bottom: 10px; color: rgb(0, 0, 0); font-family: 微软雅黑, Verdana, sans-serif, 宋体; font-size: 13px; line-height: 21.3333320617676px; white-space: normal;\"><span style=\"line-height: 1.5; font-size: 10pt;\">运行效果：<a href=\"http://jflyfox.oschina.mopaas.com/\" target=\"_blank\" style=\"color: rgb(62, 98, 166); outline: 0px;\">http://jflyfox.oschina.mopaas.com/</a></span></p><p style=\"margin-top: 0px; margin-bottom: 10px; color: rgb(0, 0, 0); font-family: 微软雅黑, Verdana, sans-serif, 宋体; font-size: 13px; line-height: 21.3333320617676px; white-space: normal;\"><span style=\"line-height: 1.5; font-size: 10pt;\">首页效果图如下：</span></p><p style=\"margin-top: 0px; margin-bottom: 10px; color: rgb(0, 0, 0); font-family: 微软雅黑, Verdana, sans-serif, 宋体; font-size: 13px; line-height: 21.3333320617676px; white-space: normal;\"><span style=\"line-height: 1.5; font-size: 10pt;\"><img src=\"http://ww4.sinaimg.cn/mw690/3fc7e281jw1eqebcg72juj20s00ftaf9.jpg\"/></span></p><p style=\"margin-top: 0px; margin-bottom: 10px; color: rgb(0, 0, 0); font-family: 微软雅黑, Verdana, sans-serif, 宋体; font-size: 13px; line-height: 21.3333320617676px; white-space: normal;\">后台登陆：<a href=\"http://jflyfox.oschina.mopaas.com/admin\" target=\"_blank\" style=\"color: rgb(62, 98, 166); outline: 0px;\">http://jflyfox.oschina.mopaas.com/admin</a></p><p style=\"margin-top: 0px; margin-bottom: 10px; color: rgb(0, 0, 0); font-family: 微软雅黑, Verdana, sans-serif, 宋体; font-size: 13px; line-height: 21.3333320617676px; white-space: normal;\"><img src=\"http://ww2.sinaimg.cn/mw690/3fc7e281jw1eqebchm1j4j20h00ee0x5.jpg\"/></p><p style=\"margin-top: 0px; margin-bottom: 10px; color: rgb(0, 0, 0); font-family: 微软雅黑, Verdana, sans-serif, 宋体; font-size: 13px; line-height: 21.3333320617676px; white-space: normal;\">账号密码：admin/admin123,test/123456</p><p style=\"margin-top: 0px; margin-bottom: 10px; color: rgb(0, 0, 0); font-family: 微软雅黑, Verdana, sans-serif, 宋体; font-size: 13px; line-height: 21.3333320617676px; white-space: normal;\">后台页面页面效果图如下：</p><p style=\"margin-top: 0px; margin-bottom: 10px; color: rgb(0, 0, 0); font-family: 微软雅黑, Verdana, sans-serif, 宋体; font-size: 13px; line-height: 21.3333320617676px; white-space: normal;\"><img src=\"http://ww2.sinaimg.cn/mw690/3fc7e281jw1eqebcfhs3nj210v0diwjx.jpg\"/></p>', '5', '0', '11', '1', '1', '2', '9', null, 'jflyfox/project/article_image/20160617_164916_643251.jpg', null, null, null, '10', '2016-06-17', '系统管理员', null, null, '2016-06-17 16:49:16', '2016-06-17 16:49:16', '1');
INSERT INTO `tb_article` VALUES ('4231', '253', '内容管理平台jfinal+beetl 开发的jfinal cms', '<p style=\"margin-top: 0px; margin-bottom: 10px; padding: 0px; font-family: &quot;Microsoft YaHei&quot;, Verdana, sans-serif, 宋体; font-size: 13.3333px; line-height: 21.3333px; white-space: normal; background-color: rgb(255, 255, 255);\">jfinal cms，采用了简洁强大的<a href=\"http://www.oschina.net/p/jfinal\" target=\"_blank\" style=\"margin: 0px; padding: 0px; color: rgb(62, 98, 166); outline: 0px;\">JFinal</a>作为web框架，模板引擎用的是<a href=\"http://www.oschina.net/p/beetl\" target=\"_blank\" style=\"margin: 0px; padding: 0px; color: rgb(62, 98, 166); outline: 0px;\">beetl</a>，数据库用mysql，前端<a href=\"http://www.oschina.net/p/bootstrap\" target=\"_blank\" style=\"margin: 0px; padding: 0px; color: rgb(62, 98, 166); outline: 0px;\">bootstrap</a>框架。 支持多站点、oauth2认证、帐号注册、密码加密、评论及回复，消息提示，网站访问量统计，文章评论数和浏览量统计，回复管理，支持权限管理。</p><p style=\"margin-top: 0px; margin-bottom: 10px; padding: 0px; font-family: &quot;Microsoft YaHei&quot;, Verdana, sans-serif, 宋体; font-size: 13.3333px; line-height: 21.3333px; white-space: normal; background-color: rgb(255, 255, 255);\"><span style=\"margin: 0px; padding: 0px; font-size: 13.3333px; line-height: 1.5;\">后台模块包含：栏目管理，栏目公告，栏目滚动图片，文章管理，回复管理，意见反馈，我的相册，相册管理，图片管理，专辑管理、视频管理、缓存更新，友情链接，访问统计，联系人管理，模板管理，组织机构管理，用户管理，角色管理，菜单管理，数据字典管理，站点管理。</span></p><p style=\"margin-top: 0px; margin-bottom: 10px; padding: 0px; font-family: &quot;Microsoft YaHei&quot;, Verdana, sans-serif, 宋体; font-size: 13.3333px; line-height: 21.3333px; white-space: normal; background-color: rgb(255, 255, 255);\"><span style=\"margin: 0px; padding: 0px; color: rgb(51, 51, 51); font-size: 13.3333px; line-height: 1.5;\"><span style=\"margin: 0px; padding: 0px; font-size: 13.3333px; line-height: 20px;\"><span style=\"margin: 0px; padding: 0px; font-size: 13.3333px;\">jfinal cms交流群：</span><span style=\"margin: 0px; padding: 0px; font-size: 13.3333px;\">568909653</span><br style=\"margin: 0px; padding: 0px;\"/></span></span></p><p style=\"margin-top: 0px; margin-bottom: 10px; padding: 0px; font-family: &quot;Microsoft YaHei&quot;, Verdana, sans-serif, 宋体; font-size: 13.3333px; line-height: 21.3333px; white-space: normal; background-color: rgb(255, 255, 255);\"><span style=\"margin: 0px; padding: 0px; color: rgb(51, 51, 51); font-size: 13.3333px; line-height: 1.5;\"><span style=\"margin: 0px; padding: 0px; font-size: 13.3333px; line-height: 20px;\">源码地址：</span><a href=\"http://git.oschina.net/flyfox/jfinal_cms\" target=\"_blank\" style=\"margin: 0px; padding: 0px; color: rgb(62, 98, 166); outline: 0px;\">http://git.oschina.net/flyfox/jfinal_cms</a><br style=\"margin: 0px; padding: 0px;\"/></span></p><p style=\"margin-top: 0px; margin-bottom: 10px; padding: 0px; font-family: &quot;Microsoft YaHei&quot;, Verdana, sans-serif, 宋体; font-size: 13.3333px; line-height: 21.3333px; white-space: normal; background-color: rgb(255, 255, 255);\"><span style=\"margin: 0px; padding: 0px; color: rgb(51, 51, 51); font-size: 13.3333px; line-height: 1.5;\">默认模板运行效果：<a href=\"http://mtg.jflyfox.com/\" target=\"_blank\" style=\"margin: 0px; padding: 0px; color: rgb(62, 98, 166); outline: 0px;\">http://mtg.jflyfox.com/</a></span></p><p style=\"margin-top: 0px; margin-bottom: 10px; padding: 0px; font-family: &quot;Microsoft YaHei&quot;, Verdana, sans-serif, 宋体; white-space: normal; font-size: 13.3333px; color: rgb(51, 51, 51); background-color: rgb(255, 255, 255);\"><span style=\"margin: 0px; padding: 0px; font-size: 13.3333px;\">官网模板运行效果：<a href=\"http://website.jflyfox.com/\" target=\"_blank\" style=\"margin: 0px; padding: 0px; color: rgb(62, 98, 166); outline: 0px;\">http://website.jflyfox.com/</a></span></p><p style=\"margin-top: 0px; margin-bottom: 10px; padding: 0px; font-family: &quot;Microsoft YaHei&quot;, Verdana, sans-serif, 宋体; white-space: normal; font-size: 13.3333px; color: rgb(51, 51, 51); background-color: rgb(255, 255, 255);\">相册模板运行效果：<a href=\"http://photo.jflyfox.com/\" target=\"_blank\" style=\"margin: 0px; padding: 0px; color: rgb(62, 98, 166); outline: 0px;\">http://photo.jflyfox.com/</a></p><p style=\"margin-top: 0px; margin-bottom: 10px; padding: 0px; font-family: &quot;Microsoft YaHei&quot;, Verdana, sans-serif, 宋体; white-space: normal; font-size: 13.3333px; color: rgb(51, 51, 51); background-color: rgb(255, 255, 255);\"><span style=\"margin: 0px; padding: 0px; font-size: 13.3333px;\">视频模板运行效果：<a href=\"http://video.jflyfox.com/\" target=\"_blank\" style=\"margin: 0px; padding: 0px; color: rgb(62, 98, 166); outline: 0px;\">http://video.jflyfox.com/</a></span></p><p style=\"margin-top: 0px; margin-bottom: 10px; padding: 0px; font-family: &quot;Microsoft YaHei&quot;, Verdana, sans-serif, 宋体; white-space: normal; font-size: 13.3333px; color: rgb(51, 51, 51); background-color: rgb(255, 255, 255);\"><span style=\"margin: 0px; padding: 0px; font-size: 13.3333px; line-height: 1.5;\">登陆页面：</span><span style=\"margin: 0px; padding: 0px; line-height: 1.5; font-size: 10pt;\">管理员登陆账号密码：admin/admin123</span></p><p style=\"margin-top: 0px; margin-bottom: 10px; padding: 0px; font-family: &quot;Microsoft YaHei&quot;, Verdana, sans-serif, 宋体; white-space: normal; font-size: 13.3333px; color: rgb(51, 51, 51); background-color: rgb(255, 255, 255);\">欢迎页：<a href=\"http://www.jflyfox.com/\" target=\"_blank\" style=\"margin: 0px; padding: 0px; color: rgb(62, 98, 166); outline: 0px;\">http://www.jflyfox.com</a></p><p style=\"margin-top: 0px; margin-bottom: 10px; padding: 0px; font-family: &quot;Microsoft YaHei&quot;, Verdana, sans-serif, 宋体; white-space: normal; font-size: 13.3333px; color: rgb(51, 51, 51); background-color: rgb(255, 255, 255);\"><span style=\"margin: 0px; padding: 0px; font-size: 12.5px; line-height: 22.5px;\">模板功能：通过配置config.properties中ATTR.PATH_PC进行修改，demo中的数据库数据需要对应上。</span></p><p style=\"margin-top: 0px; margin-bottom: 10px; padding: 0px; font-family: &quot;Microsoft YaHei&quot;, Verdana, sans-serif, 宋体; white-space: normal; font-size: 13.3333px; color: rgb(51, 51, 51); background-color: rgb(255, 255, 255);\">代码生成：通过本人<a href=\"http://www.oschina.net/p/AutoCreate\" target=\"_blank\" style=\"margin: 0px; padding: 0px; color: rgb(62, 98, 166); outline: 0px;\">AutoCreate</a>项目可实现代码自动生成功能；新增模板，创建好表以及注释，增删改查排序等功能一键搞定。模板可根据自己项目定制完善。</p><p style=\"margin-top: 0px; margin-bottom: 10px; padding: 0px; font-family: &quot;Microsoft YaHei&quot;, Verdana, sans-serif, 宋体; white-space: normal; font-size: 13.3333px; color: rgb(51, 51, 51); background-color: rgb(255, 255, 255);\"><span style=\"margin: 0px; padding: 0px; line-height: 1.5; font-size: 10pt;\"><span style=\"margin: 0px; padding: 0px; font-size: 12.5px; line-height: 22.5px;\"></span></span><span style=\"margin: 0px; padding: 0px; font-size: 13.3333px; line-height: 1.5;\">前台默认模板：</span></p><p style=\"margin-top: 0px; margin-bottom: 10px; padding: 0px; font-family: &quot;Microsoft YaHei&quot;, Verdana, sans-serif, 宋体; white-space: normal; font-size: 13.3333px; color: rgb(51, 51, 51); background-color: rgb(255, 255, 255);\"><a href=\"http://static.oschina.net/uploads/img/201601/21022316_Nk5M.gif\" target=\"_blank\" style=\"margin: 0px; padding: 0px; color: rgb(62, 98, 166); outline: 0px;\"></a><a href=\"http://static.oschina.net/uploads/img/201601/21022316_Nk5M.gif\" target=\"_blank\" style=\"margin: 0px; padding: 0px; color: rgb(62, 98, 166); outline: 0px;\"><img src=\"/jflyfox/ueditor/image/20160617/1466154129826030943.gif\" width=\"678\" height=\"261\" data-bd-imgshare-binded=\"1\" style=\"margin: 0px; padding: 0px; border: 1px solid rgb(221, 221, 221); max-width: 700px;\"/></a></p><p style=\"margin-top: 0px; margin-bottom: 10px; padding: 0px; font-family: &quot;Microsoft YaHei&quot;, Verdana, sans-serif, 宋体; white-space: normal; font-size: 13.3333px; color: rgb(51, 51, 51); background-color: rgb(255, 255, 255);\"><span style=\"margin: 0px; padding: 0px; font-size: 13.3333px;\">前台官网</span>模板：</p><p style=\"margin-top: 0px; margin-bottom: 10px; padding: 0px; font-family: &quot;Microsoft YaHei&quot;, Verdana, sans-serif, 宋体; white-space: normal; font-size: 13.3333px; color: rgb(51, 51, 51); background-color: rgb(255, 255, 255);\"><a href=\"http://static.oschina.net/uploads/img/201601/21022316_XkxY.gif\" target=\"_blank\" style=\"margin: 0px; padding: 0px; color: rgb(62, 98, 166); outline: 0px;\"></a><a href=\"http://static.oschina.net/uploads/img/201601/21022316_XkxY.gif\" target=\"_blank\" style=\"margin: 0px; padding: 0px; color: rgb(62, 98, 166); outline: 0px;\"><img src=\"/jflyfox/ueditor/image/20160617/1466154130077025812.gif\" width=\"678\" height=\"317\" data-bd-imgshare-binded=\"1\" style=\"margin: 0px; padding: 0px; border: 1px solid rgb(221, 221, 221); max-width: 700px;\"/></a></p><p style=\"margin-top: 0px; margin-bottom: 10px; padding: 0px; font-family: &quot;Microsoft YaHei&quot;, Verdana, sans-serif, 宋体; white-space: normal; font-size: 13.3333px; color: rgb(51, 51, 51); background-color: rgb(255, 255, 255);\">前台相册模板：</p><p style=\"margin-top: 0px; margin-bottom: 10px; padding: 0px; font-family: &quot;Microsoft YaHei&quot;, Verdana, sans-serif, 宋体; white-space: normal; font-size: 13.3333px; color: rgb(51, 51, 51); background-color: rgb(255, 255, 255);\"><img src=\"/jflyfox/ueditor/image/20160617/1466154130331096951.gif\" alt=\"\" data-bd-imgshare-binded=\"1\" style=\"margin: 0px; padding: 0px; border: 0px; max-width: 700px;\"/></p><p style=\"margin-top: 0px; margin-bottom: 10px; padding: 0px; font-family: &quot;Microsoft YaHei&quot;, Verdana, sans-serif, 宋体; white-space: normal; font-size: 13.3333px; color: rgb(51, 51, 51); background-color: rgb(255, 255, 255);\"><span style=\"margin: 0px; padding: 0px; font-size: 13.3333px;\">前台视频模板：</span></p><p style=\"margin-top: 0px; margin-bottom: 10px; padding: 0px; font-family: &quot;Microsoft YaHei&quot;, Verdana, sans-serif, 宋体; white-space: normal; font-size: 13.3333px; color: rgb(51, 51, 51); background-color: rgb(255, 255, 255);\"><img src=\"/jflyfox/ueditor/image/20160617/1466154130418010839.gif\" alt=\"\" data-bd-imgshare-binded=\"1\" style=\"margin: 0px; padding: 0px; border: 0px; max-width: 700px;\"/></p><p style=\"margin-top: 0px; margin-bottom: 10px; padding: 0px; font-family: &quot;Microsoft YaHei&quot;, Verdana, sans-serif, 宋体; white-space: normal; font-size: 13.3333px; color: rgb(51, 51, 51); background-color: rgb(255, 255, 255);\">后端模板：</p><p style=\"margin-top: 0px; margin-bottom: 10px; padding: 0px; font-family: &quot;Microsoft YaHei&quot;, Verdana, sans-serif, 宋体; white-space: normal; font-size: 13.3333px; color: rgb(51, 51, 51); background-color: rgb(255, 255, 255);\"><a href=\"http://static.oschina.net/uploads/img/201601/28091447_rQtD.gif\" target=\"_blank\" style=\"margin: 0px; padding: 0px; color: rgb(62, 98, 166); outline: 0px;\"><img src=\"/jflyfox/ueditor/image/20160617/1466154130502035443.gif\" alt=\"\" data-bd-imgshare-binded=\"1\" style=\"margin: 0px; padding: 0px; border: 1px solid rgb(221, 221, 221); max-width: 700px;\"/></a></p><p style=\"margin-top: 0px; margin-bottom: 10px; padding: 0px; font-family: &quot;Microsoft YaHei&quot;, Verdana, sans-serif, 宋体; white-space: normal; font-size: 13.3333px; color: rgb(51, 51, 51); background-color: rgb(255, 255, 255);\"><a href=\"http://static.oschina.net/uploads/space/2015/0202/132559_bxcq_166354.jpg\" target=\"_blank\" style=\"margin: 0px; padding: 0px; color: rgb(62, 98, 166); outline: 0px;\"></a><a href=\"http://static.oschina.net/uploads/space/2015/0202/132559_bxcq_166354.jpg\" target=\"_blank\" style=\"margin: 0px; padding: 0px; color: rgb(62, 98, 166); outline: 0px;\"><img src=\"/jflyfox/ueditor/image/20160617/1466154130614082371.jpg\" alt=\"\" data-bd-imgshare-binded=\"1\" style=\"margin: 0px; padding: 0px; border: 1px solid rgb(221, 221, 221); max-width: 700px;\"/></a></p><p style=\"margin-top: 0px; margin-bottom: 10px; padding: 0px; font-family: &quot;Microsoft YaHei&quot;, Verdana, sans-serif, 宋体; font-size: 13.3333px; line-height: 21.3333px; white-space: normal; background-color: rgb(255, 255, 255);\">支持在线编辑模板：</p><p style=\"margin-top: 0px; margin-bottom: 10px; padding: 0px; font-family: &quot;Microsoft YaHei&quot;, Verdana, sans-serif, 宋体; font-size: 13.3333px; line-height: 21.3333px; white-space: normal; background-color: rgb(255, 255, 255);\"><img src=\"/jflyfox/ueditor/image/20160617/1466154130900043350.gif\" alt=\"\" data-bd-imgshare-binded=\"1\" style=\"margin: 0px; padding: 0px; border: 0px; max-width: 700px;\"/></p><p><br/></p>', '17', '0', '11', '1', '1', '2', '9', null, 'jflyfox/project/article_image/20160621_122708_874079.png', null, null, null, '10', '2016-06-17', '系统管理员', null, null, '2016-06-17 17:01:42', '2016-06-17 17:01:42', '1');
INSERT INTO `tb_article` VALUES ('4232', '253', '财务管理软件 jfinal money', '<p style=\"margin-top: 0px; margin-bottom: 10px; padding: 0px; font-size: 13.3333px; line-height: 21.3333px; white-space: normal; font-family: 微软雅黑, Verdana, sans-serif, 宋体; background-color: rgb(255, 255, 255);\"><span style=\"margin: 0px; padding: 0px; line-height: 1.5; font-size: 10pt;\">这是一个java开发的财务管理软件，本着帮助新人以及为<span style=\"margin: 0px; padding: 0px;\">学习新技术</span>的态度。</span></p><p style=\"margin-top: 0px; margin-bottom: 10px; padding: 0px; font-size: 13.3333px; line-height: 21.3333px; white-space: normal; font-family: 微软雅黑, Verdana, sans-serif, 宋体; background-color: rgb(255, 255, 255);\">JFinal-Money采用了简洁强大的JFinal作为web框架,前台使用beetl模板，数据库为mysql。</p><p style=\"margin-top: 0px; margin-bottom: 10px; padding: 0px; font-size: 13.3333px; line-height: 21.3333px; white-space: normal; font-family: 微软雅黑, Verdana, sans-serif, 宋体; background-color: rgb(255, 255, 255);\">模板引擎用：Beetl</p><p style=\"margin-top: 0px; margin-bottom: 10px; padding: 0px; font-size: 13.3333px; line-height: 21.3333px; white-space: normal; font-family: 微软雅黑, Verdana, sans-serif, 宋体; background-color: rgb(255, 255, 255);\">数据库：mysql</p><p style=\"margin-top: 0px; margin-bottom: 10px; padding: 0px; font-size: 13.3333px; line-height: 21.3333px; white-space: normal; font-family: 微软雅黑, Verdana, sans-serif, 宋体; background-color: rgb(255, 255, 255);\">前端<span style=\"margin: 0px; padding: 0px;\">框架：</span>bootstrap3，<span style=\"margin: 0px; padding: 0px;\">移动端Jquery Mobile</span></p><p style=\"margin-top: 0px; margin-bottom: 10px; padding: 0px; font-size: 13.3333px; line-height: 21.3333px; white-space: normal; font-family: 微软雅黑, Verdana, sans-serif, 宋体; background-color: rgb(255, 255, 255);\"><span style=\"margin: 0px; padding: 0px; line-height: 1.5; font-size: 10pt;\">运行效果：<a href=\"http://www.jflyfox.com/jmoney\" target=\"_blank\" style=\"margin: 0px; padding: 0px; color: rgb(62, 98, 166); outline: 0px;\">http://www.jflyfox.com/jmoney</a></span></p><p style=\"margin-top: 0px; margin-bottom: 10px; padding: 0px; font-size: 13.3333px; line-height: 21.3333px; white-space: normal; font-family: 微软雅黑, Verdana, sans-serif, 宋体; background-color: rgb(255, 255, 255);\">源码地址：<a href=\"http://git.oschina.net/flyfox/jmoney\" target=\"_blank\" style=\"margin: 0px; padding: 0px; color: rgb(62, 98, 166); outline: 0px;\">http://git.oschina.net/flyfox/jmoney</a></p><p style=\"margin-top: 0px; margin-bottom: 10px; padding: 0px; font-size: 13.3333px; line-height: 21.3333px; white-space: normal; font-family: 微软雅黑, Verdana, sans-serif, 宋体; background-color: rgb(255, 255, 255);\"><span style=\"margin: 0px; padding: 0px; line-height: 1.5; font-size: 10pt;\">首页效果图如下：</span></p><p style=\"margin-top: 0px; margin-bottom: 10px; padding: 0px; font-size: 13.3333px; line-height: 21.3333px; white-space: normal; font-family: 微软雅黑, Verdana, sans-serif, 宋体; background-color: rgb(255, 255, 255);\"><img src=\"/jflyfox/ueditor/image/20160617/1466154234042051193.png\" alt=\"\" data-bd-imgshare-binded=\"1\" style=\"margin: 0px; padding: 0px; border: 0px; max-width: 700px;\"/></p><p style=\"margin-top: 0px; margin-bottom: 10px; padding: 0px; font-family: &quot;Microsoft YaHei&quot;, Verdana, sans-serif, 宋体; font-size: 13.3333px; line-height: 21.3333px; white-space: normal; background-color: rgb(255, 255, 255);\">部署后默认账号密码：admin/admin123</p><p><br/></p>', '3', '0', '11', '1', '1', '2', '10', null, null, '/jflyfox/ueditor/image/20160617/1466154234042051193.png', null, null, '10', '2016-06-17', '系统管理员', null, null, '2016-06-17 17:02:52', '2016-06-17 17:02:52', '1');
INSERT INTO `tb_article` VALUES ('4233', '253', 'MP3音乐播放器 FFPlayer', '<p style=\"margin-top: 0px; margin-bottom: 10px; padding: 0px; font-family: &quot;Microsoft YaHei&quot;, Verdana, sans-serif, 宋体; font-size: 13.3333px; line-height: 21.3333px; white-space: normal; background-color: rgb(255, 255, 255);\">FFPlayer 是一个通过&nbsp;<a target=\"_blank\" href=\"http://www.oschina.net/p/javafx\" style=\"margin: 0px; padding: 0px; color: rgb(62, 98, 166); outline: 0px;\">JavaFX</a>&nbsp;实现的MP3音乐播放器。</p><p style=\"margin-top: 0px; margin-bottom: 10px; padding: 0px; font-family: &quot;Microsoft YaHei&quot;, Verdana, sans-serif, 宋体; font-size: 13.3333px; line-height: 21.3333px; white-space: normal; background-color: rgb(255, 255, 255);\">功能列表如下</p><ol class=\" list-paddingleft-2\" style=\"margin-top: 0.5em; margin-bottom: 0.5em; margin-left: 1.5em; padding: 0px; list-style-position: inside; font-family: &quot;Microsoft YaHei&quot;, Verdana, sans-serif, 宋体; font-size: 13.3333px; line-height: 21.3333px; white-space: normal; background-color: rgb(255, 255, 255);\"><li><p style=\"margin-top: 0px; margin-bottom: 10px; padding: 0px; display: inline;\"><span style=\"margin: 0px; padding: 0px; line-height: 1.5; font-size: 10pt;\">支持播放、暂停、上一首、下一首，时间轨，播放模式，音量调节；</span></p></li><li><p style=\"margin-top: 0px; margin-bottom: 10px; padding: 0px; display: inline;\"><span style=\"margin: 0px; padding: 0px; line-height: 1.5; font-size: 10pt;\">播放列表暂时只支持拖拽添加，右键可以删除列表音乐；</span></p></li><li><p style=\"margin-top: 0px; margin-bottom: 10px; padding: 0px; display: inline;\"><span style=\"margin: 0px; padding: 0px; line-height: 1.5; font-size: 10pt;\">歌词展示通过网络获取，自动展示，但是由于许多MP3不是标准格式无法获取歌曲信息的会导致下载歌词失败。</span></p></li></ol><p style=\"margin-top: 0px; margin-bottom: 10px; padding: 0px; font-family: &quot;Microsoft YaHei&quot;, Verdana, sans-serif, 宋体; font-size: 13.3333px; line-height: 21.3333px; white-space: normal; background-color: rgb(255, 255, 255);\">源码地址：<a href=\"http://git.oschina.net/flyfox/FFPlayer\" target=\"_blank\" style=\"margin: 0px; padding: 0px; color: rgb(62, 98, 166); outline: 0px;\">http://git.oschina.net/flyfox/FFPlayer</a></p><p style=\"margin-top: 0px; margin-bottom: 10px; padding: 0px; font-family: &quot;Microsoft YaHei&quot;, Verdana, sans-serif, 宋体; font-size: 13.3333px; line-height: 21.3333px; white-space: normal; background-color: rgb(255, 255, 255);\">界面如下图：</p><p style=\"margin-top: 0px; margin-bottom: 10px; padding: 0px; font-family: &quot;Microsoft YaHei&quot;, Verdana, sans-serif, 宋体; font-size: 13.3333px; line-height: 21.3333px; white-space: normal; background-color: rgb(255, 255, 255);\"><img src=\"/jflyfox/ueditor/image/20160617/1466154303364028114.jpg\" alt=\"\" data-bd-imgshare-binded=\"1\" style=\"margin: 0px; padding: 0px; border: 0px; max-width: 700px;\"/></p><p><br/></p>', '3', '0', '11', '1', '1', '2', '10', null, null, '/jflyfox/ueditor/image/20160617/1466154303364028114.jpg', null, null, '10', '2016-06-17', '系统管理员', null, null, '2016-06-17 17:04:52', '2016-06-17 17:04:52', '1');
INSERT INTO `tb_article` VALUES ('4234', '253', '代码自动生成器 AutoCreate', '<p style=\"margin-top: 0px; margin-bottom: 10px; padding: 0px; font-family: &quot;Microsoft YaHei&quot;, Verdana, sans-serif, 宋体; font-size: 13.3333px; line-height: 21.3333px; white-space: normal; background-color: rgb(255, 255, 255);\">autocreate 是 数据库链接采用Jfinal ActiveRecordPlugin，模板配置采用beetl，实现根据模板自动生成项目代码。</p><p style=\"margin-top: 0px; margin-bottom: 10px; padding: 0px; font-family: &quot;Microsoft YaHei&quot;, Verdana, sans-serif, 宋体; font-size: 13.3333px; line-height: 21.3333px; white-space: normal; background-color: rgb(255, 255, 255);\">可以根据自己项目代码结构，定制属于自己的模板，甚至可以通过备注配置，实现select，radio，date等组件生成；模板配置完成，<span style=\"margin: 0px; padding: 0px; line-height: 1.5; font-size: 10pt;\">再也不用写那些重复的代码了。</span></p><ul style=\"margin-top: 0.5em; margin-bottom: 0.5em; margin-left: 1.5em; padding: 0px; list-style-position: inside; font-family: &quot;Microsoft YaHei&quot;, Verdana, sans-serif, 宋体; font-size: 13.3333px; line-height: 21.3333px; white-space: normal; background-color: rgb(255, 255, 255);\" class=\" list-paddingleft-2\"><li><p style=\"margin-top: 0px; margin-bottom: 10px; padding: 0px; display: inline;\"><span style=\"margin: 0px; padding: 0px; font-size: 10pt; line-height: 1.5;\"></span><span style=\"margin: 0px; padding: 0px; font-size: 10pt; line-height: 1.5;\">默认模板目录：/autopath/template/project/</span></p></li><li><p style=\"margin-top: 0px; margin-bottom: 10px; padding: 0px; display: inline;\"><span style=\"margin: 0px; padding: 0px; font-size: 10pt; line-height: 1.5;\">自带三套模板beetl（生成beetl文件）、jsp（生成jsp文件）、jflyfox（生成本人jflyfox个人博客项目文件）</span></p></li><li><p style=\"margin-top: 0px; margin-bottom: 10px; padding: 0px; display: inline;\"><span style=\"margin: 0px; padding: 0px; font-size: 10pt; line-height: 1.5;\">默认自动生成输出目录：/autopath/output/</span></p></li><li><p style=\"margin-top: 0px; margin-bottom: 10px; padding: 0px; display: inline;\"><span style=\"margin: 0px; padding: 0px; font-size: 10pt; line-height: 1.5;\">启动文件：com.flyfox.client.AutoCreateClient</span></p></li><li><p style=\"margin-top: 0px; margin-bottom: 10px; padding: 0px; display: inline;\"><span style=\"margin: 0px; padding: 0px; font-size: 10pt; line-height: 1.5;\">生成表需要有表注释和字段注释。（写注释也是个好习惯哦）<br style=\"margin: 0px; padding: 0px;\"/></span></p></li></ul><p style=\"margin-top: 0px; margin-bottom: 10px; padding: 0px; font-family: &quot;Microsoft YaHei&quot;, Verdana, sans-serif, 宋体; font-size: 13.3333px; line-height: 21.3333px; white-space: normal; background-color: rgb(255, 255, 255);\"><strong style=\"margin: 0px; padding: 0px;\">配置说明：</strong></p><ul style=\"margin-top: 0.5em; margin-bottom: 0.5em; margin-left: 1.5em; padding: 0px; list-style-position: inside; font-family: &quot;Microsoft YaHei&quot;, Verdana, sans-serif, 宋体; font-size: 13.3333px; line-height: 21.3333px; white-space: normal; background-color: rgb(255, 255, 255);\" class=\" list-paddingleft-2\"><li><p style=\"margin-top: 0px; margin-bottom: 10px; padding: 0px; display: inline;\"><span style=\"margin: 0px; padding: 0px; line-height: 1.5; font-size: 10pt;\"></span><span style=\"margin: 0px; padding: 0px; line-height: 1.5; font-size: 10pt;\">src/main/java/conf/db.properties 配置链接的数据库信息</span></p></li><li><p style=\"margin-top: 0px; margin-bottom: 10px; padding: 0px; display: inline;\"><span style=\"margin: 0px; padding: 0px; line-height: 1.5; font-size: 10pt;\">src/main/java/conf/template.properties 配置使用模板，生成路径和生成那些表</span></p></li><li><p style=\"margin-top: 0px; margin-bottom: 10px; padding: 0px; display: inline;\"><span style=\"margin: 0px; padding: 0px; line-height: 1.5; font-size: 10pt;\">template.selected参数 制定下面已经存在的模板key</span></p></li><li><p style=\"margin-top: 0px; margin-bottom: 10px; padding: 0px; display: inline;\"><span style=\"margin: 0px; padding: 0px; line-height: 1.5; font-size: 10pt;\">template.tables参数 设定生成那些表；不填和all会生成数据库所有表；多个表明用逗号分隔</span></p></li><li><p style=\"margin-top: 0px; margin-bottom: 10px; padding: 0px; display: inline;\"><span style=\"margin: 0px; padding: 0px; line-height: 1.5; font-size: 10pt;\">src/main/java/conf/config.properties 配置beetl模板参数</span></p></li></ul><p style=\"margin-top: 0px; margin-bottom: 10px; padding: 0px; font-family: &quot;Microsoft YaHei&quot;, Verdana, sans-serif, 宋体; font-size: 13.3333px; line-height: 21.3333px; white-space: normal; background-color: rgb(255, 255, 255);\"><strong style=\"margin: 0px; padding: 0px;\">示例说明：</strong></p><p style=\"margin-top: 0px; margin-bottom: 10px; padding: 0px; font-family: &quot;Microsoft YaHei&quot;, Verdana, sans-serif, 宋体; font-size: 13.3333px; line-height: 21.3333px; white-space: normal; background-color: rgb(255, 255, 255);\">本人的博客<a href=\"http://www.oschina.net/p/jflyfox\" target=\"_blank\" style=\"margin: 0px; padding: 0px; color: rgb(62, 98, 166); outline: 0px;\">http://www.oschina.net/p/jflyfox</a>和内容管理<a href=\"http://www.oschina.net/p/mtgxxw\" target=\"_blank\" style=\"margin: 0px; padding: 0px; color: rgb(62, 98, 166); outline: 0px;\">http://www.oschina.net/p/mtgxxw</a>项目<span style=\"margin: 0px; padding: 0px;\">，都是使用的这个代码生成器。</span></p><p style=\"margin-top: 0px; margin-bottom: 10px; padding: 0px; font-family: &quot;Microsoft YaHei&quot;, Verdana, sans-serif, 宋体; font-size: 13.3333px; line-height: 21.3333px; white-space: normal; background-color: rgb(255, 255, 255);\"><strong style=\"margin: 0px; padding: 0px;\">1.数据库表</strong><strong style=\"margin: 0px; padding: 0px;\">，</strong><strong style=\"margin: 0px; padding: 0px;\">如下：</strong></p><p style=\"margin-top: 0px; margin-bottom: 10px; padding: 0px; font-family: &quot;Microsoft YaHei&quot;, Verdana, sans-serif, 宋体; font-size: 13.3333px; line-height: 21.3333px; white-space: normal; background-color: rgb(255, 255, 255);\"><strong style=\"margin: 0px; padding: 0px;\"><img src=\"/jflyfox/ueditor/image/20160617/1466154343984040251.png\" alt=\"\" data-bd-imgshare-binded=\"1\" style=\"margin: 0px; padding: 0px; border: 0px; max-width: 700px;\"/><br style=\"margin: 0px; padding: 0px;\"/></strong></p><p style=\"margin-top: 0px; margin-bottom: 10px; padding: 0px; font-family: &quot;Microsoft YaHei&quot;, Verdana, sans-serif, 宋体; font-size: 13.3333px; line-height: 21.3333px; white-space: normal; background-color: rgb(255, 255, 255);\"><strong style=\"margin: 0px; padding: 0px;\">2.修改配置</strong></p><p style=\"margin-top: 0px; margin-bottom: 10px; padding: 0px; font-family: &quot;Microsoft YaHei&quot;, Verdana, sans-serif, 宋体; font-size: 13.3333px; line-height: 21.3333px; white-space: normal; background-color: rgb(255, 255, 255);\">首先，配置数据库信息db.properties</p><p style=\"margin-top: 0px; margin-bottom: 10px; padding: 0px; font-family: &quot;Microsoft YaHei&quot;, Verdana, sans-serif, 宋体; font-size: 13.3333px; line-height: 21.3333px; white-space: normal; background-color: rgb(255, 255, 255);\">然后，配置表名和模板template.properties</p><p style=\"margin-top: 0px; margin-bottom: 10px; padding: 0px; font-family: &quot;Microsoft YaHei&quot;, Verdana, sans-serif, 宋体; font-size: 13.3333px; line-height: 21.3333px; white-space: normal; background-color: rgb(255, 255, 255);\">#选择模板路径<br style=\"margin: 0px; padding: 0px;\"/>template.selected=template.path.jflyfox<br style=\"margin: 0px; padding: 0px;\"/>#all或者不填，为生成全部；多个表已逗号分隔<br style=\"margin: 0px; padding: 0px;\"/>template.tables=sys_dict,sys_dict_detail</p><p style=\"margin-top: 0px; margin-bottom: 10px; padding: 0px; font-family: &quot;Microsoft YaHei&quot;, Verdana, sans-serif, 宋体; font-size: 13.3333px; line-height: 21.3333px; white-space: normal; background-color: rgb(255, 255, 255);\"><strong style=\"margin: 0px; padding: 0px;\">3.运行AutoCreateClient文件，运行日志如下</strong></p><p style=\"margin-top: 0px; margin-bottom: 10px; padding: 0px; font-family: &quot;Microsoft YaHei&quot;, Verdana, sans-serif, 宋体; font-size: 13.3333px; line-height: 21.3333px; white-space: normal; background-color: rgb(255, 255, 255);\"><strong style=\"margin: 0px; padding: 0px;\"><img src=\"/jflyfox/ueditor/image/20160617/1466154344089038523.png\" alt=\"\" data-bd-imgshare-binded=\"1\" style=\"margin: 0px; padding: 0px; border: 0px; max-width: 700px;\"/><br style=\"margin: 0px; padding: 0px;\"/></strong></p><p style=\"margin-top: 0px; margin-bottom: 10px; padding: 0px; font-family: &quot;Microsoft YaHei&quot;, Verdana, sans-serif, 宋体; font-size: 13.3333px; line-height: 21.3333px; white-space: normal; background-color: rgb(255, 255, 255);\"><strong style=\"margin: 0px; padding: 0px;\">4.然后，会在autopath/output目录下生产对应的文件：</strong></p><p style=\"margin-top: 0px; margin-bottom: 10px; padding: 0px; font-family: &quot;Microsoft YaHei&quot;, Verdana, sans-serif, 宋体; font-size: 13.3333px; line-height: 21.3333px; white-space: normal; background-color: rgb(255, 255, 255);\"><strong style=\"margin: 0px; padding: 0px;\"><img src=\"/jflyfox/ueditor/image/20160617/1466154344184003731.png\" alt=\"\" data-bd-imgshare-binded=\"1\" style=\"margin: 0px; padding: 0px; border: 0px; max-width: 700px;\"/><br style=\"margin: 0px; padding: 0px;\"/></strong></p><p style=\"margin-top: 0px; margin-bottom: 10px; padding: 0px; font-family: &quot;Microsoft YaHei&quot;, Verdana, sans-serif, 宋体; font-size: 13.3333px; line-height: 21.3333px; white-space: normal; background-color: rgb(255, 255, 255);\"><strong style=\"margin: 0px; padding: 0px;\">5.列表代码片段如下：</strong></p><p style=\"margin-top: 0px; margin-bottom: 10px; padding: 0px; font-family: &quot;Microsoft YaHei&quot;, Verdana, sans-serif, 宋体; font-size: 13.3333px; line-height: 21.3333px; white-space: normal; background-color: rgb(255, 255, 255);\"><strong style=\"margin: 0px; padding: 0px;\"><img src=\"/jflyfox/ueditor/image/20160617/1466154344288013427.png\" alt=\"\" data-bd-imgshare-binded=\"1\" style=\"margin: 0px; padding: 0px; border: 0px; max-width: 700px;\"/></strong></p><p><br/></p>', '2', '0', '11', '1', '1', '2', '10', null, 'jflyfox/project/article_image/20160621_123633_738200.jpg', null, null, null, '10', '2016-06-17', '系统管理员', null, null, '2016-06-17 17:05:32', '2016-06-17 17:05:32', '1');
INSERT INTO `tb_article` VALUES ('4235', '253', '贪吃蛇游戏 JavaFX Snake', '<p style=\"margin-top: 0px; margin-bottom: 10px; padding: 0px; font-family: &quot;Microsoft YaHei&quot;, Verdana, sans-serif, 宋体; font-size: 13.3333px; line-height: 21.3333px; white-space: normal; background-color: rgb(255, 255, 255);\">JavaFX Snake是一个通过JavaFX实现的贪吃蛇游戏，游戏比较简单就不用再介绍了。</p><p style=\"margin-top: 0px; margin-bottom: 10px; padding: 0px; font-family: &quot;Microsoft YaHei&quot;, Verdana, sans-serif, 宋体; font-size: 13.3333px; line-height: 21.3333px; white-space: normal; background-color: rgb(255, 255, 255);\">源码地址：<a href=\"http://git.oschina.net/flyfox/GameSnake\" target=\"_blank\" style=\"margin: 0px; padding: 0px; color: rgb(62, 98, 166); outline: 0px;\">http://git.oschina.net/flyfox/GameSnake</a></p><p style=\"margin-top: 0px; margin-bottom: 10px; padding: 0px; font-family: &quot;Microsoft YaHei&quot;, Verdana, sans-serif, 宋体; font-size: 13.3333px; line-height: 21.3333px; white-space: normal; background-color: rgb(255, 255, 255);\">界面比较简陋凑合看吧，如下图：</p><p style=\"margin-top: 0px; margin-bottom: 10px; padding: 0px; font-family: &quot;Microsoft YaHei&quot;, Verdana, sans-serif, 宋体; font-size: 13.3333px; line-height: 21.3333px; white-space: normal; background-color: rgb(255, 255, 255);\"><img src=\"/jflyfox/ueditor/image/20160617/1466154494359099247.jpg\" alt=\"\" data-bd-imgshare-binded=\"1\" style=\"margin: 0px; padding: 0px; border: 0px; max-width: 700px;\"/></p><p><br/></p>', '2', '0', '11', '1', '1', '2', '10', null, null, '/jflyfox/ueditor/image/20160617/1466154494359099247.jpg', null, null, '10', '2016-06-17', '系统管理员', null, null, '2016-06-17 17:07:13', '2016-06-17 17:07:13', '1');
INSERT INTO `tb_article` VALUES ('4236', '253', 'JavaFX游戏 打砖块', '<p style=\"white-space: normal; margin-top: 8px; margin-bottom: 8px; line-height: 22.5px; letter-spacing: 0.5px; font-size: 13px; font-family: 微软雅黑, Verdana, sans-serif, 宋体; \">今天学习了一下JavaFX游戏部分<br/></p><p style=\"white-space: normal; margin-top: 8px; margin-bottom: 8px; line-height: 22.5px; letter-spacing: 0.5px; font-size: 13px; font-family: 微软雅黑, Verdana, sans-serif, 宋体; \">参考博客：<a href=\"http://blog.csdn.net/ml3947\" rel=\"nofollow\" style=\"text-decoration: none; color: rgb(255, 131, 115); outline: 0px; font-size: 12px;\">http://blog.csdn.net/ml3947</a></p><p style=\"white-space: normal; margin-top: 8px; margin-bottom: 8px; line-height: 22.5px; letter-spacing: 0.5px; font-size: 13px; font-family: 微软雅黑, Verdana, sans-serif, 宋体; \">本人源码地址：<a href=\"http://git.oschina.net/flyfox/GameBrickBlock\" rel=\"nofollow\" style=\"text-decoration: none; color: rgb(255, 131, 115); outline: 0px; font-size: 12px;\">http://git.oschina.net/flyfox/GameBrickBlock</a></p><p style=\"white-space: normal; margin-top: 8px; margin-bottom: 8px; line-height: 22.5px; letter-spacing: 0.5px; font-size: 13px; font-family: 微软雅黑, Verdana, sans-serif, 宋体; \">里面有两种效果实现，一种是参考博客的Pane实现，一种是自己学习修改的Canvas实现。</p><p style=\"white-space: normal; margin-top: 8px; margin-bottom: 8px; line-height: 22.5px; letter-spacing: 0.5px; font-size: 13px; font-family: 微软雅黑, Verdana, sans-serif, 宋体; \">效果图演示：</p><p style=\"white-space: normal; margin-top: 8px; margin-bottom: 8px; line-height: 22.5px; letter-spacing: 0.5px; font-size: 13px; font-family: 微软雅黑, Verdana, sans-serif, 宋体; \"><img src=\"/jflyfox/ueditor/image/20160617/1466154554078056899.jpg\"/></p><p><br/></p>', '2', '0', '11', '1', '1', '2', '10', null, null, '/jflyfox/ueditor/image/20160617/1466154554078056899.jpg', null, null, '10', '2016-06-17', '系统管理员', null, null, '2016-06-17 17:08:57', '2016-06-17 17:08:57', '1');
INSERT INTO `tb_article` VALUES ('4237', '253', '通过mina进行文件传输工具', '<p style=\"font-family: Simsun; font-size: 12px; white-space: normal; \"><strong>FileSocket</strong></p><p style=\"font-family: Simsun; font-size: 12px; white-space: normal; \">通过mina进行文件传输</p><p style=\"font-family: Simsun; font-size: 12px; white-space: normal; \">传入目录readfile</p><p style=\"font-family: Simsun; font-size: 12px; white-space: normal; \">接收目录writefile</p><p style=\"font-family: Simsun; font-size: 12px; white-space: normal; \">日志目录logs</p><p style=\"font-family: Simsun; font-size: 12px; white-space: normal; \">配置文件config/config.properties</p><p style=\"font-family: Simsun; font-size: 12px; white-space: normal; \"><br/></p><p style=\"font-family: Simsun; font-size: 12px; white-space: normal; \"><strong>发送流程</strong></p><p style=\"font-family: Simsun; font-size: 12px; white-space: normal; \">1. 定时读取发送路径文件</p><p style=\"font-family: Simsun; font-size: 12px; white-space: normal; \">2. 将文件名称加入.tmp后缀，进行流读取发送</p><p style=\"font-family: Simsun; font-size: 12px; white-space: normal; \">3. 发送后将文件放入备份路径（backup）</p><p style=\"font-family: Simsun; font-size: 12px; white-space: normal; \">4. 服务器端返回成功，放入成功路径（finished）；返回失败，放入失败路径（failed）。</p><p style=\"font-family: Simsun; font-size: 12px; white-space: normal; \"><br/></p><p style=\"font-family: Simsun; font-size: 12px; white-space: normal; \"><strong>注：由于是个人项目未进行开源</strong></p><p><br/></p>', '2', '0', '11', '1', '1', '2', '10', null, 'jflyfox/project/article_image/20160621_123715_121257.jpg', null, null, null, '10', '2016-06-17', '系统管理员', null, null, '2016-06-17 17:10:16', '2016-06-17 17:10:16', '1');
INSERT INTO `tb_article` VALUES ('4238', '253', '财务管理 Android项目', '<p style=\"font-family: Simsun; font-size: 12px; white-space: normal; margin-top: 8px; line-height: 22.8571px; letter-spacing: 0.5px; \">DataAnalysis</p><p style=\"font-family: Simsun; font-size: 12px; white-space: normal; margin-top: 8px; line-height: 22.8571px; letter-spacing: 0.5px; \"><span style=\"line-height: 22.8571px;\">主要是记录个人收入、支出，输出月报表，年报表。</span></p><p style=\"white-space: normal; margin-top: 8px; margin-bottom: 8px; line-height: 22.5px; letter-spacing: 0.5px; font-size: 13px; font-family: 微软雅黑, Verdana, sans-serif, 宋体; \"><a href=\"http://static.oschina.net/uploads/space/2015/0213/121009_XZVN_166354.png\" target=\"_blank\" style=\"text-decoration: none; color: rgb(255, 131, 115); outline: 0px; font-size: 12px;\"><img src=\"http://ww3.sinaimg.cn/mw690/3fc7e281jw1eqeblvgkroj20e80e8aby.jpg\" style=\"width: 335px; height: 340px;\"/></a><br/></p><p style=\"white-space: normal; margin-top: 8px; margin-bottom: 8px; line-height: 22.5px; letter-spacing: 0.5px; font-size: 13px; font-family: 微软雅黑, Verdana, sans-serif, 宋体; \">界面如下：</p><p style=\"white-space: normal; margin-top: 8px; margin-bottom: 8px; line-height: 22.5px; letter-spacing: 0.5px; font-size: 13px; font-family: 微软雅黑, Verdana, sans-serif, 宋体; \"><br/></p><p style=\"white-space: normal; margin-top: 8px; margin-bottom: 8px; line-height: 22.5px; letter-spacing: 0.5px; font-size: 13px; font-family: 微软雅黑, Verdana, sans-serif, 宋体; \"><br/></p><p style=\"white-space: normal; margin-top: 8px; margin-bottom: 8px; line-height: 22.5px; letter-spacing: 0.5px; font-size: 13px; font-family: 微软雅黑, Verdana, sans-serif, 宋体; \"><a href=\"http://static.oschina.net/uploads/space/2015/0213/121010_i34R_166354.png\" target=\"_blank\" style=\"text-decoration: none; color: rgb(255, 131, 115); outline: 0px; font-size: 12px;\"><img src=\"http://ww3.sinaimg.cn/mw690/3fc7e281jw1eqeblvshmsj20dc0m8q4t.jpg\"/></a></p><p style=\"white-space: normal; margin-top: 8px; margin-bottom: 8px; line-height: 22.5px; letter-spacing: 0.5px; font-size: 13px; font-family: 微软雅黑, Verdana, sans-serif, 宋体; \"><img src=\"http://ww1.sinaimg.cn/mw690/3fc7e281jw1eqeblw5dz3j20dc0m8wfc.jpg\"/></p><p style=\"white-space: normal; margin-top: 8px; margin-bottom: 8px; line-height: 22.5px; letter-spacing: 0.5px; font-size: 13px; font-family: 微软雅黑, Verdana, sans-serif, 宋体; \"><img src=\"http://ww4.sinaimg.cn/mw690/3fc7e281jw1eqeblwdpsej20dc0m8wfr.jpg\"/></p><p style=\"font-family: Simsun; font-size: 12px; white-space: normal; \"><span style=\"line-height: 22.8571px;\"></span><br/></p><p style=\"font-family: Simsun; font-size: 12px; white-space: normal; \"><span style=\"font-weight: 700; line-height: 22.8571px;\"><br/></span></p><p style=\"font-family: Simsun; font-size: 12px; white-space: normal; \"><span style=\"font-weight: 700; line-height: 22.8571px;\">注：由于是个人项目未进行开源</span></p><p><br/></p>', '2', '0', '11', '1', '1', '2', '10', null, null, 'http://ww3.sinaimg.cn/mw690/3fc7e281jw1eqeblvgkroj20e80e8aby.jpg', null, null, '10', '2016-06-17', '系统管理员', null, null, '2016-06-17 17:11:15', '2016-06-17 17:11:15', '1');
INSERT INTO `tb_article` VALUES ('4239', '253', '微信抽奖lottery', '<p><strong>微信九宫格抽奖</strong></p><p><br/></p><p>主要实现微信页面抽奖，积分展示，奖品及中奖奖品展示，中奖名单等；后台实现抽奖概率和优先级设置，奖品配置及个数统计，中奖名单，人员信息等。</p><p><br/></p>', '3', '0', '11', '1', '1', '2', '10', null, 'jflyfox/project/article_image/20160621_122855_293772.jpg', null, null, null, '10', '2016-06-17', '系统管理员', null, null, '2016-06-17 17:12:14', '2016-06-17 17:12:14', '1');
INSERT INTO `tb_article` VALUES ('4240', '253', '基础库封装jflyfox_base', '<p style=\"font-family: Simsun; font-size: 12px; white-space: normal; \"><strong>源码地址：<a href=\"http://git.oschina.net/flyfox/jflyfox_base\" target=\"_blank\" title=\"http://git.oschina.net/flyfox/jflyfox_base\" style=\"text-decoration: none; color: rgb(63, 167, 203);\">http://git.oschina.net/flyfox/jflyfox_base</a></strong></p><p style=\"font-family: Simsun; font-size: 12px; white-space: normal; \">flyfox基础库(jflyfox_base)</p><p style=\"font-family: Simsun; font-size: 12px; white-space: normal; \">作者：FLY的狐狸</p><p style=\"font-family: Simsun; font-size: 12px; white-space: normal; \">功能：基础库。实现String、Number、Date、加密算法，序列化、缓存管理等封装。</p><p><br/></p>', '1', '0', '11', '1', '1', '2', '10', null, 'jflyfox/project/article_image/20160621_123504_752718.jpg', null, null, null, '10', '2016-06-17', '系统管理员', null, null, '2016-06-17 17:12:54', '2016-06-17 17:12:54', '1');
INSERT INTO `tb_article` VALUES ('4241', '253', '基础库Jfinal封装jflyfox_jfinal', '<p style=\"font-family: Simsun; font-size: 12px; white-space: normal; \"><strong>源码地址：</strong><a href=\"http://git.oschina.net/flyfox/jflyfox_jfinal\" target=\"_blank\" title=\"http://git.oschina.net/flyfox/jflyfox_jfinal\" style=\"text-decoration: none; color: rgb(63, 167, 203);\">http://git.oschina.net/flyfox/jflyfox_jfinal</a></p><p style=\"font-family: Simsun; font-size: 12px; white-space: normal; \">jflyfox_jfinal是对Jfinal和beetl进行封装。</p><p style=\"font-family: Simsun; font-size: 12px; white-space: normal; \">1. 包含controller，model，form，service基础类封装。</p><p style=\"font-family: Simsun; font-size: 12px; white-space: normal; \">2. 对分页进行了后台和前台的实现。</p><p style=\"font-family: Simsun; font-size: 12px; white-space: normal; \">3. 加入了自动扫描model和controller以及注解支持。</p><p style=\"font-family: Simsun; font-size: 12px; white-space: normal; \">4. 实现了ueditor后台Controller代码。</p><p style=\"font-family: Simsun; font-size: 12px; white-space: normal; \">5. 加入了页面增删改查代码自动生成功能，可通过beetl模板进行配置。</p><p style=\"font-family: Simsun; font-size: 12px; white-space: normal; \">6. 实现了SessionAttrInterceptor、页面和手机设备判断拦截器以及BasePathHandler。</p><p><br/></p>', '1', '0', '11', '1', '1', '2', '10', null, 'jflyfox/project/article_image/20160621_123454_938123.png', null, null, null, '10', '2016-06-17', '系统管理员', null, null, '2016-06-17 17:13:32', '2016-06-17 17:13:32', '1');
INSERT INTO `tb_article` VALUES ('4242', '253', '其他项目', '<p style=\"font-family: Simsun; font-size: 12px; white-space: normal; \">其他项目</p><p style=\"font-family: Simsun; font-size: 12px; white-space: normal; \"><strong>一、人员信息记录</strong></p><p style=\"font-family: Simsun; font-size: 12px; white-space: normal; \">主要是记录人员信息，可定制化人员信息字段，可进行excel导入导出的系统。</p><p style=\"font-family: Simsun; font-size: 12px; white-space: normal; \"><br/></p><p style=\"font-family: Simsun; font-size: 12px; white-space: normal; \"><strong>二、工资管理</strong></p><p style=\"font-family: Simsun; font-size: 12px; white-space: normal; \">主要是人员信息、部门管理，以及工资导入、导出。本来还想加入工资编辑和计算，但是由于各种原因放弃了。</p><p style=\"font-family: Simsun; font-size: 12px; white-space: normal; \"><br/></p><p style=\"font-family: Simsun; font-size: 12px; white-space: normal; \"><strong>三、website_jfinal类似于个人博客</strong></p><p style=\"font-family: Simsun; font-size: 12px; white-space: normal; \">这个是比较早的项目通过jfinal和jsp实现，通过后台配置实现网站首页更新。</p><p style=\"font-family: Simsun; font-size: 12px; white-space: normal; \">和现在的blog差不多就是代码和页面都比较老了。</p><p style=\"font-family: Simsun; font-size: 12px; white-space: normal; \">源码地址：<a href=\"http://git.oschina.net/flyfox/website_jfinal\" target=\"_blank\" title=\"http://git.oschina.net/flyfox/website_jfinal\" style=\"text-decoration: none; color: rgb(63, 167, 203);\">http://git.oschina.net/flyfox/website_jfinal</a></p><p style=\"font-family: Simsun; font-size: 12px; white-space: normal; \"><br/></p><p style=\"font-family: Simsun; font-size: 12px; white-space: normal; \"><strong>四、JavaFX</strong></p><p style=\"font-family: Simsun; font-size: 12px; white-space: normal; \">有一个JavaFX工具类项目和处理文件名称个性化批量修改工具以及学习JavaFX的大量DEMO。</p><p style=\"font-family: Simsun; font-size: 12px; white-space: normal; \">JavaFX Demo地址：<a href=\"http://git.oschina.net/flyfox/JavaFXDemo\" target=\"_blank\" title=\"http://git.oschina.net/flyfox/JavaFXDemo\" style=\"text-decoration: none; color: rgb(63, 167, 203);\">http://git.oschina.net/flyfox/JavaFXDemo</a></p><p style=\"font-family: Simsun; font-size: 12px; white-space: normal; \"><br/></p><p style=\"font-family: Simsun; font-size: 12px; white-space: normal; \"><strong>五、仓储管理：jfinal+jsp</strong></p><p style=\"font-family: Simsun; font-size: 12px; white-space: normal; \">（1）管理员登录模块&nbsp;</p><p style=\"font-family: Simsun; font-size: 12px; white-space: normal; \">包括对管理员的用户名密码进行匹配性验证，以及登录验证码，防止暴力破解登录。&nbsp;</p><p style=\"font-family: Simsun; font-size: 12px; white-space: normal; \">（2）物资管理模块&nbsp;</p><p style=\"font-family: Simsun; font-size: 12px; white-space: normal; \">能够对新物资进行建档，管理员可以查看所有已建档物资信息并可以修改或删除。&nbsp;</p><p style=\"font-family: Simsun; font-size: 12px; white-space: normal; \">（3）入库管理模块&nbsp;</p><p style=\"font-family: Simsun; font-size: 12px; white-space: normal; \">管理员可以进行入库登记，可以修改或删除入库记录，也可以按照时间段进行入库情况统计。&nbsp;</p><p style=\"font-family: Simsun; font-size: 12px; white-space: normal; \">（4）出库管理模块&nbsp;</p><p style=\"font-family: Simsun; font-size: 12px; white-space: normal; \">管理员可以查看所有出库信息，可以进行出库登记，可以修改或删除出库记录。可以按时间段进行统计查询。&nbsp;</p><p style=\"font-family: Simsun; font-size: 12px; white-space: normal; \">（5）盘存管理模块&nbsp;</p><p style=\"font-family: Simsun; font-size: 12px; white-space: normal; \">管理员可以查看所有物资的库存情况。可以进行商品查询，可以按名称查询单一商品，也可以按种类查询某一种类物资。</p><p style=\"font-family: Simsun; font-size: 12px; white-space: normal; \">（6）系统管理模块&nbsp;</p><p style=\"font-family: Simsun; font-size: 12px; white-space: normal; \">包括供货单位管理、收货单位管理、物资种类管理、经手人管理等。</p><p style=\"font-family: Simsun; font-size: 12px; white-space: normal; \">（7）用户管理模块 &nbsp;</p><p style=\"font-family: Simsun; font-size: 12px; white-space: normal; \">主要提供用户修改密码的功能。</p><p style=\"font-family: Simsun; font-size: 12px; white-space: normal; \"><br/></p><p style=\"font-family: Simsun; font-size: 12px; white-space: normal; \"><strong>六、低值易耗品信息管理<span style=\"line-height: 22.8571px;\">：jfinal+jsp</span></strong></p><p style=\"font-family: Simsun; font-size: 12px; white-space: normal; \">类似与仓储管理</p><p style=\"font-family: Simsun; font-size: 12px; white-space: normal; \">（1）用户管理</p><p style=\"font-family: Simsun; font-size: 12px; white-space: normal; \">1)登录；2)注册；3)修改员工个人信息；4)修改员工密码；5)管理员工</p><p style=\"font-family: Simsun; font-size: 12px; white-space: normal; \">（2）低值易耗品信息管理</p><p style=\"font-family: Simsun; font-size: 12px; white-space: normal; \">1)分类：低值易耗品分类表中添加、删除、修改、查看分类。</p><p style=\"font-family: Simsun; font-size: 12px; white-space: normal; \">2)信息增加：员工或管理员登陆后可以将采购到的低值易耗品信息添加到信息表中，并生成凭单。</p><p style=\"font-family: Simsun; font-size: 12px; white-space: normal; \">（3）凭单管理</p><p style=\"font-family: Simsun; font-size: 12px; white-space: normal; \">1)凭单查看，编辑；2)凭单打印：</p><p style=\"font-family: Simsun; font-size: 12px; white-space: normal; \">（4）报表管理</p><p style=\"font-family: Simsun; font-size: 12px; white-space: normal; \">1)报表生成；2)报表打印</p><p style=\"font-family: Simsun; font-size: 12px; white-space: normal; \"><br/></p><p style=\"font-family: Simsun; font-size: 12px; white-space: normal; \"><strong>七、任务管理<span style=\"line-height: 22.8571px;\"><span style=\"line-height: 22.8571px;\">：jfinal+jsp</span></span></strong></p><p style=\"font-family: Simsun; font-size: 12px; white-space: normal; \"><strong><span style=\"line-height: 22.8571px;\"><span style=\"line-height: 22.8571px;\"></span></span></strong></p><p style=\"font-family: Simsun; font-size: 12px; white-space: normal; \">（1）系统用户登录及注册</p><p style=\"font-family: Simsun; font-size: 12px; white-space: normal; \">1）角色管理； 2）用户注册；3）信息维护</p><p style=\"font-family: Simsun; font-size: 12px; white-space: normal; \">（2）公告模块</p><p style=\"font-family: Simsun; font-size: 12px; white-space: normal; \">1）公告展示进行权限划分；2）公告编辑，实现公告发布</p><p style=\"font-family: Simsun; font-size: 12px; white-space: normal; \">（3）任务管理</p><p style=\"font-family: Simsun; font-size: 12px; white-space: normal; \">1）核心模块，任务信息维护；2）实现流转-》处理-》结束流程；3）实现任务权限管理，按照部门和用户角色划分</p><p style=\"font-family: Simsun; font-size: 12px; white-space: normal; \"><strong><span style=\"line-height: 22.8571px;\"><span style=\"line-height: 22.8571px;\"><br/></span></span></strong><strong>八、文献管理<span style=\"line-height: 22.8571px;\"><span style=\"line-height: 22.8571px;\"><span style=\"line-height: 22.8571px;\">：jfinal+jsp</span></span></span></strong></p><p style=\"font-family: Simsun; font-size: 12px; white-space: normal; \"><span style=\"line-height: 22.8571px;\"><span style=\"line-height: 22.8571px;\"><span style=\"line-height: 22.8571px;\">实现文献信息编辑，文献上传、以及根据文献文本自动解析标题、作者、摘要、内容等信息。</span></span></span></p><p style=\"font-family: Simsun; font-size: 12px; white-space: normal; \"><span style=\"line-height: 22.8571px;\"><span style=\"line-height: 22.8571px;\"><span style=\"line-height: 22.8571px;\"><br/></span></span></span></p><p style=\"font-family: Simsun; font-size: 12px; white-space: normal; \"><span style=\"line-height: 22.8571px;\"><span style=\"line-height: 22.8571px;\"><span style=\"line-height: 22.8571px;\"><strong>九、仓库管理<span style=\"line-height: 22.8571px;\">：springmvc3+hibernate4</span></strong></span></span></span></p><p style=\"font-family: Simsun; font-size: 12px; white-space: normal; \"><span style=\"line-height: 22.8571px;\">类似仓储管理，修改实现技术</span></p><p style=\"font-family: Simsun; font-size: 12px; white-space: normal; \"><span style=\"line-height: 22.8571px;\"><br/></span></p><p style=\"font-family: Simsun; font-size: 12px; white-space: normal; \"><span style=\"line-height: 22.8571px;\"><span style=\"line-height: 22.8571px;\"><span style=\"line-height: 22.8571px;\"><strong>十、企业库存管理<span style=\"line-height: 22.8571px;\"><span style=\"line-height: 22.8571px;\"><span style=\"line-height: 22.8571px;\"><span style=\"line-height: 22.8571px;\"><span style=\"line-height: 22.8571px;\">：jfinal+jsp</span></span></span></span></span></strong></span></span></span></p><p style=\"font-family: Simsun; font-size: 12px; white-space: normal; \"><span style=\"line-height: 22.8571px;\"><span style=\"line-height: 22.8571px;\"><span style=\"line-height: 22.8571px;\"><span style=\"line-height: 22.8571px;\">类似仓储管理，实现</span><span style=\"line-height: 22.8571px;\">用户模块，库存模块，退货模块，订货模块以及报表管理</span></span></span></span></p><p><br/></p>', '6', '0', '11', '1', '1', '2', '99', null, null, null, null, null, '10', '2016-06-16', '系统管理员', null, null, '2016-06-17 17:14:11', '2016-06-17 17:14:11', '1');
INSERT INTO `tb_article` VALUES ('4243', '253', '短信接口开发', '<p>熟悉三大运营商接口开发：CMPP3.0，SMGP，SGIP1.2。</p><p>直接接入运行商，可以保证接口稳定性，提交速度基本受限于运营商限速。并支持发送数据数据历史查询、黑白名单、流控、发送量限制等功能。</p><p>第三方短信接口开发：阿里大鱼、haoservice、创蓝等接口。</p>', '2', '0', '11', '1', '1', '2', '11', null, 'jflyfox/project/article_image/20160621_123305_245463.jpg', null, null, null, '10', '2016-06-21', '系统管理员', null, null, '2016-06-21 12:29:26', '2016-06-21 12:29:26', '1');

-- ----------------------------
-- Table structure for `tb_articlelike`
-- ----------------------------
DROP TABLE IF EXISTS `tb_articlelike`;
CREATE TABLE `tb_articlelike` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `article_id` int(11) DEFAULT NULL COMMENT '文章ID',
  `create_time` varchar(64) DEFAULT NULL COMMENT '创建时间',
  `create_id` int(11) DEFAULT '0' COMMENT '创建者',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='喜欢的文章';

-- ----------------------------
-- Records of tb_articlelike
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_comment`
-- ----------------------------
DROP TABLE IF EXISTS `tb_comment`;
CREATE TABLE `tb_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `fatherId` int(11) DEFAULT NULL COMMENT '父评论ID',
  `article_id` int(11) DEFAULT NULL COMMENT '文章ID',
  `content` text NOT NULL COMMENT '内容',
  `status` int(11) DEFAULT '11' COMMENT '状态//select/11,评论未读,12,评论已读,21,回复未读,22,回复已读',
  `reply_userid` int(11) DEFAULT NULL,
  `create_time` varchar(64) DEFAULT NULL COMMENT '创建时间',
  `create_id` int(11) DEFAULT '0' COMMENT '创建者 评论者',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='评论';

-- ----------------------------
-- Records of tb_comment
-- ----------------------------
INSERT INTO `tb_comment` VALUES ('2', '0', '3001', '123123', '12', '1', '2016-04-19 21:38:02', '1');

-- ----------------------------
-- Table structure for `tb_contact`
-- ----------------------------
DROP TABLE IF EXISTS `tb_contact`;
CREATE TABLE `tb_contact` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(256) NOT NULL COMMENT '姓名',
  `phone` varchar(32) DEFAULT NULL COMMENT '手机号',
  `email` varchar(32) DEFAULT NULL COMMENT 'Email',
  `addr` varchar(256) DEFAULT NULL COMMENT '地址',
  `birthday` varchar(32) DEFAULT NULL COMMENT '生日',
  `remark` varchar(256) DEFAULT NULL COMMENT '说明',
  `create_time` varchar(64) DEFAULT NULL COMMENT '创建时间',
  `create_id` int(11) DEFAULT '0' COMMENT '创建者',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='联系人';

-- ----------------------------
-- Records of tb_contact
-- ----------------------------
INSERT INTO `tb_contact` VALUES ('1', '张三', '15812345678', 'zhangsan@sina.com', '北京市', '2015-04-28', null, '2015-01-27', '1');

-- ----------------------------
-- Table structure for `tb_error`
-- ----------------------------
DROP TABLE IF EXISTS `tb_error`;
CREATE TABLE `tb_error` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `type` int(11) DEFAULT NULL COMMENT '类型',
  `ip` varchar(64) NOT NULL COMMENT 'IP地址',
  `userid` int(11) DEFAULT NULL COMMENT '用户ID',
  `content` text COMMENT '描述',
  `remark` text COMMENT '备注',
  `create_time` varchar(64) DEFAULT NULL COMMENT '创建时间',
  `create_id` int(11) DEFAULT '0' COMMENT '创建者',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='异常数据';

-- ----------------------------
-- Records of tb_error
-- ----------------------------
INSERT INTO `tb_error` VALUES ('1', '1', '127.0.0.1', '2', '上传数量：2015062100(11)-20150621(11)', null, '2015-06-21 00:24:30', '2');
INSERT INTO `tb_error` VALUES ('2', '1', '127.0.0.1', '2', '上传数量：2015062100(11)-20150621(11)', null, '2015-06-21 00:26:32', '2');

-- ----------------------------
-- Table structure for `tb_folder`
-- ----------------------------
DROP TABLE IF EXISTS `tb_folder`;
CREATE TABLE `tb_folder` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '目录id',
  `parent_id` int(11) DEFAULT '0' COMMENT '父ID',
  `name` varchar(100) NOT NULL DEFAULT '' COMMENT '中文名',
  `key` varchar(100) DEFAULT '' COMMENT 'URL KEY',
  `path` varchar(200) DEFAULT '' COMMENT '模板路径',
  `content` text COMMENT '描述',
  `sort` int(11) DEFAULT '1' COMMENT '排序',
  `status` int(11) DEFAULT '1' COMMENT '状态//radio/2,隐藏,1,显示',
  `type` int(11) DEFAULT '1' COMMENT '类型 1 普通目录 2 a标签 3 a标签_blank 4 直接加载url信息',
  `jump_url` varchar(200) DEFAULT NULL COMMENT '跳转地址',
  `material_type` int(11) DEFAULT NULL COMMENT '素材类型',
  `site_id` int(11) DEFAULT NULL COMMENT '站点ID',
  `seo_title` varchar(200) DEFAULT NULL COMMENT 'SEO title',
  `seo_keywords` varchar(200) DEFAULT NULL COMMENT 'SEO keywords',
  `seo_description` varchar(200) DEFAULT NULL COMMENT 'SEO description',
  `update_time` varchar(64) DEFAULT NULL COMMENT '更新时间',
  `update_id` int(11) DEFAULT '0' COMMENT '更新人',
  `create_time` varchar(64) DEFAULT NULL COMMENT '创建时间',
  `create_id` int(11) DEFAULT '0' COMMENT '创建者',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=232 DEFAULT CHARSET=utf8 COMMENT='目录';

-- ----------------------------
-- Records of tb_folder
-- ----------------------------
INSERT INTO `tb_folder` VALUES ('1', '0', '首页', 'home', '', '门头沟信息网', '1', '1', '1', null, '102', '2', null, null, null, '2015-01-28 16:54:03', '0', '2015-01-30 13:24:58', '1');
INSERT INTO `tb_folder` VALUES ('2', '0', '新闻', 'news', null, null, '2', '1', '1', null, '102', '2', null, null, null, '2015-05-24 15:46:40', '0', '2015-01-30 13:24:58', '1');
INSERT INTO `tb_folder` VALUES ('3', '0', '美食', 'food', '', null, '3', '1', '1', null, '102', '2', null, null, null, '2015-05-24 15:46:54', '0', '2015-01-30 13:24:58', '1');
INSERT INTO `tb_folder` VALUES ('4', '0', '旅游', 'travel', '', null, '5', '1', '1', null, '102', '2', null, null, null, '2015-05-24 15:47:43', '0', '2015-05-24 15:47:43', '1');
INSERT INTO `tb_folder` VALUES ('5', '0', '教育', 'education', '', null, '7', '1', '1', null, '102', '2', null, null, null, '2015-05-24 15:47:55', '0', '2015-05-24 15:47:55', '1');
INSERT INTO `tb_folder` VALUES ('6', '103', '后台管理', null, null, null, '90', '2', '3', 'admin', '102', '2', null, null, null, '2015-05-24 15:47:32', '0', '2015-01-30 13:24:58', '1');
INSERT INTO `tb_folder` VALUES ('7', '103', '标签查询', null, null, null, '80', '1', '2', 'front/tags/all', '102', '2', null, null, null, '2015-05-27 23:34:38', '0', '2015-05-18 09:12:57', '1');
INSERT INTO `tb_folder` VALUES ('10', '103', '公园', 'park', null, null, '51', '1', '1', null, '102', '2', null, null, null, '2015-05-24 15:49:35', '0', '2015-05-24 15:49:11', '1');
INSERT INTO `tb_folder` VALUES ('13', '0', '首页图片', 'topPic', '', null, '101', '2', '1', null, '102', '2', null, null, null, '2015-05-24 16:33:06', '0', '2015-05-24 16:33:06', '1');
INSERT INTO `tb_folder` VALUES ('90', '103', '关于我们', 'about', null, null, '81', '1', '2', 'front/about.html', '102', '2', null, null, null, '2015-05-26 16:40:46', '0', '2015-05-26 10:36:30', '1');
INSERT INTO `tb_folder` VALUES ('100', '0', '博文目录', '', '', null, '99', '2', '1', null, '102', '2', null, null, null, '2015-06-17 22:29:44', '0', '2015-06-17 22:29:44', '2');
INSERT INTO `tb_folder` VALUES ('101', '103', '意见反馈', 'advice', null, null, '82', '1', '1', null, '102', '2', null, null, null, '2016-01-29 01:13:16', '1', '2016-01-29 01:13:16', '1');
INSERT INTO `tb_folder` VALUES ('103', '0', '其他', 'others', null, null, '10', '1', '1', null, '102', '2', null, null, null, '2016-03-31 23:50:26', '1', '2016-03-31 23:50:26', '1');
INSERT INTO `tb_folder` VALUES ('230', '0', '首页', 'home', 'home/home.html', null, '1', '1', '1', null, '102', '3', 'FLY的狐狸', 'FLY的狐狸，门头沟信息，生活，新闻，美食，旅游，教育，公园，商场，房产，生活记录,开发记录', 'FLY的狐狸，门头沟信息，生活，新闻，美食，旅游，教育，公园，商场，房产，生活记录,开发记录', '2016-04-07 01:13:40', '1', '2016-04-07 01:13:40', '1');
INSERT INTO `tb_folder` VALUES ('231', '0', '关于我们', 'about', '', null, '81', '1', '1', null, '1', '3', null, null, null, '2015-05-26 16:40:46', '0', '2015-05-26 10:36:30', '1');
INSERT INTO `tb_folder` VALUES ('253', '0', '首页', 'index', 'index.html', null, '10', '1', '1', null, '102', '8', null, null, null, '2016-06-17 16:48:11', '1', '2016-06-17 16:48:11', '1');
INSERT INTO `tb_folder` VALUES ('254', '0', '意见反馈', null, null, null, '10', '1', '3', 'http://mtg.jflyfox.com/advice.html', '102', '8', null, null, null, '2016-06-21 12:21:19', '1', '2016-06-21 12:21:19', '1');
INSERT INTO `tb_folder` VALUES ('255', '0', '关于我们', null, null, null, '10', '1', '3', 'http://blog.jflyfox.com/245.html', '102', '8', null, null, null, '2016-06-21 12:21:58', '1', '2016-06-21 12:21:58', '1');

-- ----------------------------
-- Table structure for `tb_folder_notice`
-- ----------------------------
DROP TABLE IF EXISTS `tb_folder_notice`;
CREATE TABLE `tb_folder_notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `folder_id` int(11) NOT NULL COMMENT '目录id',
  `type` int(11) NOT NULL DEFAULT '0' COMMENT '类型',
  `icon` varchar(255) DEFAULT NULL COMMENT '图标',
  `content` varchar(2000) DEFAULT NULL COMMENT '内容',
  `url` varchar(255) DEFAULT NULL COMMENT '链接地址',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `status` int(11) DEFAULT '1' COMMENT '状态//radio/2,隐藏,1,显示',
  `is_deleted` int(11) NOT NULL DEFAULT '0' COMMENT '是否已删除',
  `update_time` varchar(64) DEFAULT NULL COMMENT '更新时间',
  `update_id` int(11) DEFAULT '0' COMMENT '更新人',
  `create_time` varchar(64) DEFAULT NULL COMMENT '创建时间',
  `create_id` int(11) DEFAULT '0' COMMENT '创建者',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='栏目公告';

-- ----------------------------
-- Records of tb_folder_notice
-- ----------------------------
INSERT INTO `tb_folder_notice` VALUES ('1', '1', '0', null, '擦擦擦擦擦擦擦擦擦擦擦擦擦擦擦擦擦擦擦', null, '20', '1', '1', '2016-01-31 02:52:56', '1', '2016-01-31 02:52:56', '1');
INSERT INTO `tb_folder_notice` VALUES ('2', '2', '0', null, '擦擦擦擦擦擦擦擦擦擦擦擦擦擦擦擦擦擦擦擦擦擦擦擦擦擦擦擦擦', 'http://www.baidu.com', '20', '1', '1', '2016-01-31 02:53:22', '1', '2016-01-31 02:53:22', '1');

-- ----------------------------
-- Table structure for `tb_folder_roll_picture`
-- ----------------------------
DROP TABLE IF EXISTS `tb_folder_roll_picture`;
CREATE TABLE `tb_folder_roll_picture` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `folder_id` int(11) NOT NULL COMMENT '目录id',
  `title` varchar(200) DEFAULT '' COMMENT '题目',
  `content` varchar(2000) DEFAULT NULL COMMENT '内容',
  `sort` int(11) DEFAULT '1' COMMENT '排序',
  `status` int(11) DEFAULT '1' COMMENT '状态//radio/2,隐藏,1,显示',
  `image_url` varchar(256) DEFAULT NULL COMMENT '图片路径',
  `image_net_url` varchar(256) DEFAULT NULL COMMENT '网络图片路径',
  `url` varchar(255) DEFAULT NULL COMMENT '链接地址',
  `is_deleted` int(11) NOT NULL DEFAULT '0' COMMENT '是否已删除',
  `update_time` varchar(64) DEFAULT NULL COMMENT '更新时间',
  `update_id` int(11) DEFAULT '0' COMMENT '更新人',
  `create_time` varchar(64) DEFAULT NULL COMMENT '创建时间',
  `create_id` int(11) DEFAULT '0' COMMENT '创建者',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='栏目轮播图';

-- ----------------------------
-- Records of tb_folder_roll_picture
-- ----------------------------
INSERT INTO `tb_folder_roll_picture` VALUES ('3', '1', '百花山', null, '1', '1', null, 'http://i1.tietuku.cn/3951e9cb262621b6.jpg', 'http://www.jflyfox.com/mtg/front/article/329.html', '1', '2016-01-28 17:40:22', '1', '2016-01-28 17:40:22', '1');
INSERT INTO `tb_folder_roll_picture` VALUES ('4', '1', '美丽门城', null, '2', '1', null, 'http://i1.tietuku.cn/6f139452bedaefed.jpg', '#', '1', '2016-01-28 17:41:13', '1', '2016-01-28 17:41:13', '1');
INSERT INTO `tb_folder_roll_picture` VALUES ('5', '1', '永定塔', null, '3', '1', null, 'http://i1.tietuku.cn/fab40b501ece3fcf.jpg', 'http://www.jflyfox.com/mtg/front/article/406.html', '1', '2016-01-28 17:42:12', '1', '2016-01-28 17:42:12', '1');
INSERT INTO `tb_folder_roll_picture` VALUES ('6', '1', '爨底下', null, '4', '1', null, 'http://i1.tietuku.cn/35171f11a5ec9c51.jpg', 'http://www.jflyfox.com/mtg/front/article/330.html', '1', '2016-01-28 17:42:40', '1', '2016-01-28 17:42:40', '1');

-- ----------------------------
-- Table structure for `tb_friendlylink`
-- ----------------------------
DROP TABLE IF EXISTS `tb_friendlylink`;
CREATE TABLE `tb_friendlylink` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(256) NOT NULL COMMENT '名称/11111/',
  `url` varchar(256) NOT NULL COMMENT 'URL',
  `sort` int(11) NOT NULL COMMENT '排序号',
  `state` int(11) DEFAULT '0' COMMENT '是否显示//radio/1,显示,2,不显示',
  `type` int(11) DEFAULT '21' COMMENT '类型//select/1,见数据字典',
  `remark` varchar(256) DEFAULT NULL COMMENT '备注//textarea',
  `site_id` int(11) DEFAULT '0' COMMENT '站点ID',
  `create_time` varchar(64) DEFAULT NULL COMMENT '创建时间',
  `create_id` int(11) DEFAULT '0' COMMENT '创建者',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='友情链接表';

-- ----------------------------
-- Records of tb_friendlylink
-- ----------------------------
INSERT INTO `tb_friendlylink` VALUES ('1', '意见反馈', 'http://www.jflyfox.com/mtg/advice', '16', '1', '22', null, '0', '2015-04-24 15:03:02', '1');
INSERT INTO `tb_friendlylink` VALUES ('2', '捐赠我们', 'http://www.jflyfox.com/mtg/front/about/351.html', '13', '1', '22', null, '0', '2015-04-24 15:27:36', '1');
INSERT INTO `tb_friendlylink` VALUES ('3', '关于我们', 'http://www.jflyfox.com/mtgfront/about/352.html', '2', '1', '22', null, '0', '2015-04-24 15:28:56', '1');
INSERT INTO `tb_friendlylink` VALUES ('4', '给我写信', 'http://mail.qq.com/cgi-bin/qm_share?t=qm_mailme&email=allcU1tTW15dWiobG0QJBQc', '15', '1', '22', null, '0', '2015-04-24 15:29:12', '1');
INSERT INTO `tb_friendlylink` VALUES ('5', '大峪中学', 'http://www.dyzx-bj.com/', '112', '1', '21', null, '0', '2015-05-06 16:13:40', '1');
INSERT INTO `tb_friendlylink` VALUES ('6', 'Jflyfox博客', 'http://www.jflyfox.com/', '114', '1', '21', null, '0', '2015-05-06 16:14:37', '1');
INSERT INTO `tb_friendlylink` VALUES ('7', '门头沟介绍', 'http://baike.baidu.com/view/193726.htm?fromtitle=%E9%97%A8%E5%A4%B4%E6%B2%9F&fromid=1055081&type=syn', '111', '1', '21', null, '0', '2015-05-06 16:15:03', '1');
INSERT INTO `tb_friendlylink` VALUES ('8', '联系我们', 'http://www.jflyfox.com/mtgfront/about/353.html', '3', '1', '22', null, '0', '2015-05-26 11:26:57', '1');
INSERT INTO `tb_friendlylink` VALUES ('9', '免责声明', 'http://www.jflyfox.com/mtgfront/about/354.html', '20', '1', '22', null, '0', '2015-05-26 11:27:18', '1');
INSERT INTO `tb_friendlylink` VALUES ('10', '广告服务', 'http://www.jflyfox.com/mtgfront/about/355.html', '11', '1', '22', null, '0', '2015-05-26 11:28:42', '1');

-- ----------------------------
-- Table structure for `tb_image`
-- ----------------------------
DROP TABLE IF EXISTS `tb_image`;
CREATE TABLE `tb_image` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `album_id` int(11) DEFAULT '1' COMMENT '相册ID',
  `album_name` varchar(200) DEFAULT '' COMMENT '相册名称',
  `name` varchar(200) DEFAULT '' COMMENT '图片名称',
  `linkurl` varchar(400) DEFAULT '' COMMENT '链接地址',
  `cdnurl` varchar(400) DEFAULT '' COMMENT 'CDN地址',
  `image_url` varchar(256) DEFAULT NULL COMMENT '图片路径',
  `image_net_url` varchar(256) DEFAULT NULL COMMENT '网络图片路径',
  `ext` varchar(20) DEFAULT '' COMMENT '扩展名',
  `width` varchar(20) DEFAULT '' COMMENT '宽',
  `height` varchar(20) DEFAULT '' COMMENT '高',
  `status` int(11) DEFAULT '1' COMMENT '状态//radio/2,隐藏,1,显示',
  `is_comment` int(11) DEFAULT '1' COMMENT '是否评论//radio/2,否,1,是',
  `is_recommend` int(11) DEFAULT '2' COMMENT '是否推荐：2 否 1 是',
  `sort` int(11) DEFAULT '1' COMMENT '排序',
  `remark` varchar(400) DEFAULT NULL COMMENT '备注',
  `publish_time` varchar(64) DEFAULT NULL COMMENT '发布时间',
  `publish_user` varchar(64) DEFAULT '1' COMMENT '发布者',
  `update_time` varchar(64) DEFAULT NULL COMMENT '更新时间',
  `update_id` int(11) DEFAULT '0' COMMENT '更新者',
  `create_time` varchar(64) DEFAULT NULL COMMENT '创建时间',
  `create_id` int(11) DEFAULT '0' COMMENT '创建者',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8 COMMENT='图片';

-- ----------------------------
-- Records of tb_image
-- ----------------------------
INSERT INTO `tb_image` VALUES ('2', '1', '风景', '风景1', 'http://image226-c.poco.cn/mypoco/myphoto/20140121/16/6164818720140121160407052_640.jpg?1024x453_120', '', null, 'http://image226-c.poco.cn/mypoco/myphoto/20140121/16/6164818720140121160407052_640.jpg?1024x453_120', 'jpg?1024x453_120', '750', '331', '1', '2', '2', '10', null, '2016-02-10', '系统管理员', '2016-02-10 00:41:10', '1', '2016-02-10 00:41:10', '1');
INSERT INTO `tb_image` VALUES ('3', '3', '动漫', '动漫1', 'http://thumb.webps.cn/to/img/4/T1DTF3Fc4dXXXXXXXX_!!0-item_pic.jpg', '', null, 'http://thumb.webps.cn/to/img/4/T1DTF3Fc4dXXXXXXXX_!!0-item_pic.jpg', 'jpg', '500', '747', '1', '2', '2', '10', null, '2016-02-10', '系统管理员', '2016-02-10 00:41:51', '1', '2016-02-10 00:41:51', '1');
INSERT INTO `tb_image` VALUES ('4', '2', '美女', '美女1', 'http://i4.pdim.gs/t01d4f8e84b1434c8e3.jpg', '', null, 'http://i4.pdim.gs/t01d4f8e84b1434c8e3.jpg', 'jpg', '800', '566', '1', '2', '1', '2', null, '2016-02-10', '系统管理员', '2016-02-10 00:43:20', '1', '2016-02-10 00:43:20', '1');
INSERT INTO `tb_image` VALUES ('5', '4', '游戏', '游戏1', 'http://news.waigame.cn/static/newsimage/20140603/14017632866819.jpg', '', null, 'http://news.waigame.cn/static/newsimage/20140603/14017632866819.jpg', 'jpg', '1400', '933', '1', '2', '2', '10', null, '2016-02-10', '系统管理员', '2016-02-10 00:44:14', '1', '2016-02-10 00:44:14', '1');
INSERT INTO `tb_image` VALUES ('7', '2', '美女', '美女2', 'http://i12.tietuku.cn/15a72119633c812d.jpg', '', null, 'http://i12.tietuku.cn/15a72119633c812d.jpg', null, '0', '0', '1', '2', '1', '8', null, '2016-02-10', '系统管理员', '2016-02-10 01:25:41', '1', '2016-02-10 01:25:41', '1');
INSERT INTO `tb_image` VALUES ('8', '2', '美女', '美女3', 'http://i12.tietuku.cn/ccb2e30c9fda92f6.jpg', '', null, 'http://i12.tietuku.cn/ccb2e30c9fda92f6.jpg', 'jpg', '580', '870', '1', '2', '2', '8', null, '2016-02-10', '系统管理员', '2016-02-10 01:30:59', '1', '2016-02-10 01:30:59', '1');
INSERT INTO `tb_image` VALUES ('9', '2', '美女', '美女4', 'http://i12.tietuku.cn/2329cc70e32d51c7.jpg', '', null, 'http://i12.tietuku.cn/2329cc70e32d51c7.jpg', 'jpg', '480', '800', '1', '1', '2', '10', null, '2016-02-10', '系统管理员', '2016-02-10 01:31:16', '1', '2016-02-10 01:31:16', '1');
INSERT INTO `tb_image` VALUES ('10', '2', '美女', '美女5', 'http://i12.tietuku.cn/f2229247cf39609c.jpg', '', null, 'http://i12.tietuku.cn/f2229247cf39609c.jpg', 'jpg', '375', '550', '1', '1', '2', '10', null, '2016-02-10', '系统管理员', '2016-02-10 01:31:30', '1', '2016-02-10 01:31:30', '1');
INSERT INTO `tb_image` VALUES ('11', '2', '美女', '美女6', 'http://i12.tietuku.cn/53cdf688c2c938f0.jpg', '', null, 'http://i12.tietuku.cn/53cdf688c2c938f0.jpg', 'jpg', '610', '833', '1', '2', '2', '8', null, '2016-02-10', '系统管理员', '2016-02-10 01:31:45', '1', '2016-02-10 01:31:45', '1');
INSERT INTO `tb_image` VALUES ('12', '2', '美女', '美女7', 'http://i12.tietuku.cn/9c83f4d0809adcbc.jpg', '', null, 'http://i12.tietuku.cn/9c83f4d0809adcbc.jpg', 'jpg', '532', '600', '1', '1', '2', '10', null, '2016-02-10', '系统管理员', '2016-02-10 01:31:59', '1', '2016-02-10 01:31:59', '1');
INSERT INTO `tb_image` VALUES ('13', '2', '美女', '美女8', 'http://i4.tietuku.cn/691d3f2a37fb0ba9.jpg', '', null, 'http://i4.tietuku.cn/691d3f2a37fb0ba9.jpg', 'jpg', '1024', '1536', '1', '2', '2', '10', null, '2016-02-10', '系统管理员', '2016-02-10 01:33:40', '1', '2016-02-10 01:33:40', '1');
INSERT INTO `tb_image` VALUES ('14', '2', '美女', '美女9', 'http://i4.tietuku.cn/1fd3bb6d9a0d5901.jpg', '', null, 'http://i4.tietuku.cn/1fd3bb6d9a0d5901.jpg', 'jpg', '682', '1024', '1', '2', '2', '9', null, '2016-02-10', '系统管理员', '2016-02-10 01:34:45', '1', '2016-02-10 01:34:45', '1');
INSERT INTO `tb_image` VALUES ('15', '2', '美女', '美女10', 'http://i13.tietuku.cn/bff63c26bea99e8d.jpg', '', null, 'http://i13.tietuku.cn/bff63c26bea99e8d.jpg', 'jpg', '900', '1200', '1', '1', '2', '10', null, '2016-02-10', '系统管理员', '2016-02-10 01:35:16', '1', '2016-02-10 01:35:16', '1');
INSERT INTO `tb_image` VALUES ('16', '2', '美女', '美女11', 'http://i13.tietuku.cn/8484c08a850a74c8.jpg', '', null, 'http://i13.tietuku.cn/8484c08a850a74c8.jpg', 'jpg', '768', '1152', '1', '2', '2', '9', null, '2016-02-10', '系统管理员', '2016-02-10 01:35:55', '1', '2016-02-10 01:35:55', '1');
INSERT INTO `tb_image` VALUES ('17', '2', '美女', '美女12', 'http://i4.tietuku.cn/4231f8da8b09331c.jpg', '', null, 'http://i4.tietuku.cn/4231f8da8b09331c.jpg', 'jpg', '640', '960', '1', '2', '2', '8', null, '2016-02-10', '系统管理员', '2016-02-10 01:36:09', '1', '2016-02-10 01:36:09', '1');
INSERT INTO `tb_image` VALUES ('18', '2', '美女', '美女13', 'http://i13.tietuku.cn/a5d2a6478c6ed758.jpg', '', null, 'http://i13.tietuku.cn/a5d2a6478c6ed758.jpg', 'jpg', '496', '750', '1', '2', '2', '10', null, '2016-02-09', '系统管理员', '2016-02-10 01:36:29', '1', '2016-02-10 01:36:29', '1');
INSERT INTO `tb_image` VALUES ('19', '4', '游戏', '游戏2', 'http://i4.tietuku.cn/c34fc8e188fd210c.jpg', '', null, 'http://i4.tietuku.cn/c34fc8e188fd210c.jpg', 'jpg', '1920', '1080', '1', '1', '2', '10', null, '2016-02-11', '系统管理员', '2016-02-11 02:57:23', '1', '2016-02-11 02:57:23', '1');
INSERT INTO `tb_image` VALUES ('20', '4', '游戏', '游戏3', 'http://i8.tietuku.cn/ca9d3311b35856b7.jpg', '', null, 'http://i8.tietuku.cn/ca9d3311b35856b7.jpg', 'jpg', '658', '823', '1', '1', '2', '10', null, '2016-02-11', '系统管理员', '2016-02-11 02:58:35', '1', '2016-02-11 02:58:35', '1');
INSERT INTO `tb_image` VALUES ('21', '4', '游戏', '游戏5', 'http://i4.tietuku.cn/a8ebfc61acef58ae.jpg', '', null, 'http://i4.tietuku.cn/a8ebfc61acef58ae.jpg', 'jpg', '1600', '1200', '1', '2', '2', '10', null, '2016-02-11', '系统管理员', '2016-02-11 02:59:46', '1', '2016-02-11 02:59:46', '1');
INSERT INTO `tb_image` VALUES ('22', '4', '游戏', '游戏4', 'http://i4.tietuku.cn/51c82af7824bac4c.jpg', '', null, 'http://i4.tietuku.cn/51c82af7824bac4c.jpg', 'jpg', '1600', '1200', '1', '1', '2', '10', null, '2016-02-11', '系统管理员', '2016-02-11 03:00:11', '1', '2016-02-11 03:00:11', '1');
INSERT INTO `tb_image` VALUES ('23', '4', '游戏', '游戏6', 'http://i8.tietuku.cn/cc4329e67481d4f0.png', '', null, 'http://i8.tietuku.cn/cc4329e67481d4f0.png', 'png', '400', '580', '1', '1', '2', '10', null, '2016-02-11', '系统管理员', '2016-02-11 03:00:50', '1', '2016-02-11 03:00:50', '1');
INSERT INTO `tb_image` VALUES ('24', '4', '游戏', '游戏7', 'http://i8.tietuku.cn/fa672748521bb33a.jpg', '', null, 'http://i8.tietuku.cn/fa672748521bb33a.jpg', 'jpg', '600', '400', '1', '1', '2', '10', null, '2016-02-11', '系统管理员', '2016-02-11 03:01:30', '1', '2016-02-11 03:01:30', '1');
INSERT INTO `tb_image` VALUES ('25', '4', '游戏', '游戏8', 'http://i4.tietuku.cn/7a22444c704bdb8c.jpg', '', null, 'http://i4.tietuku.cn/7a22444c704bdb8c.jpg', 'jpg', '751', '1000', '1', '1', '2', '10', null, '2016-02-11', '系统管理员', '2016-02-11 03:04:52', '1', '2016-02-11 03:04:52', '1');
INSERT INTO `tb_image` VALUES ('26', '3', '动漫', '动漫2', 'http://i12.tietuku.cn/c4b206990bea4877.jpg', '', null, 'http://i12.tietuku.cn/c4b206990bea4877.jpg', 'jpg', '429', '600', '1', '1', '2', '10', null, '2016-02-11', '系统管理员', '2016-02-11 03:06:29', '1', '2016-02-11 03:06:29', '1');
INSERT INTO `tb_image` VALUES ('27', '3', '动漫', '动漫3', 'http://i11.tietuku.cn/97ec25026f8526ea.jpg', '', null, 'http://i11.tietuku.cn/97ec25026f8526ea.jpg', 'jpg', '188', '266', '1', '1', '2', '10', null, '2016-02-11', '系统管理员', '2016-02-11 03:06:48', '1', '2016-02-11 03:06:48', '1');
INSERT INTO `tb_image` VALUES ('28', '3', '动漫', '动漫4', 'http://i8.tietuku.cn/99bed4ee4e00683b.jpg', '', null, 'http://i8.tietuku.cn/99bed4ee4e00683b.jpg', 'jpg', '609', '800', '1', '1', '2', '10', null, '2016-02-11', '系统管理员', '2016-02-11 03:07:03', '1', '2016-02-11 03:07:03', '1');
INSERT INTO `tb_image` VALUES ('29', '3', '动漫', '动漫5', 'http://i4.tietuku.cn/c18889b9f5fcb0f6.jpg', '', null, 'http://i4.tietuku.cn/c18889b9f5fcb0f6.jpg', 'jpg', '637', '900', '1', '1', '2', '10', null, '2016-02-11', '系统管理员', '2016-02-11 03:07:59', '1', '2016-02-11 03:07:59', '1');
INSERT INTO `tb_image` VALUES ('30', '3', '动漫', '动漫6', 'http://i4.tietuku.cn/e37d596427ae17f8.jpg', '', null, 'http://i4.tietuku.cn/e37d596427ae17f8.jpg', 'jpg', '210', '280', '1', '1', '2', '10', null, '2016-02-11', '系统管理员', '2016-02-11 03:08:31', '1', '2016-02-11 03:08:31', '1');

-- ----------------------------
-- Table structure for `tb_image_album`
-- ----------------------------
DROP TABLE IF EXISTS `tb_image_album`;
CREATE TABLE `tb_image_album` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parent_id` int(11) DEFAULT '0' COMMENT '父ID',
  `name` varchar(100) NOT NULL DEFAULT '' COMMENT '相册名称',
  `remark` text COMMENT '描述',
  `sort` int(11) DEFAULT '1' COMMENT '排序',
  `status` int(11) DEFAULT '1' COMMENT '状态//radio/2,隐藏,1,显示',
  `update_time` varchar(64) DEFAULT NULL COMMENT '更新时间',
  `update_id` int(11) DEFAULT '0' COMMENT '更新人',
  `create_time` varchar(64) DEFAULT NULL COMMENT '创建时间',
  `create_id` int(11) DEFAULT '0' COMMENT '创建者',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='相册';

-- ----------------------------
-- Records of tb_image_album
-- ----------------------------
INSERT INTO `tb_image_album` VALUES ('1', '0', '风景', null, '1', '1', '2016-02-10 00:28:19', '1', '2016-02-10 00:28:19', '1');
INSERT INTO `tb_image_album` VALUES ('2', '0', '美女', null, '2', '1', '2016-02-10 00:28:43', '1', '2016-02-10 00:28:43', '1');
INSERT INTO `tb_image_album` VALUES ('3', '0', '动漫', null, '3', '1', '2016-02-10 00:28:55', '1', '2016-02-10 00:28:55', '1');
INSERT INTO `tb_image_album` VALUES ('4', '0', '游戏', null, '4', '1', '2016-02-10 00:29:42', '1', '2016-02-10 00:29:42', '1');

-- ----------------------------
-- Table structure for `tb_image_tags`
-- ----------------------------
DROP TABLE IF EXISTS `tb_image_tags`;
CREATE TABLE `tb_image_tags` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `image_id` int(11) DEFAULT NULL COMMENT '图片ID',
  `tagname` varchar(200) DEFAULT '' COMMENT '标签内容',
  `create_time` varchar(64) DEFAULT NULL COMMENT '创建时间',
  `create_id` int(11) DEFAULT '0' COMMENT '创建者',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8 COMMENT='标签';

-- ----------------------------
-- Records of tb_image_tags
-- ----------------------------
INSERT INTO `tb_image_tags` VALUES ('1', null, 'a', '2016-02-10 02:09:39', '1');
INSERT INTO `tb_image_tags` VALUES ('2', null, 'b', '2016-02-10 02:09:39', '1');
INSERT INTO `tb_image_tags` VALUES ('3', null, 'b', '2016-02-10 02:09:39', '1');
INSERT INTO `tb_image_tags` VALUES ('4', null, 'a', '2016-02-10 02:09:48', '1');
INSERT INTO `tb_image_tags` VALUES ('5', null, 'c', '2016-02-10 02:09:48', '1');
INSERT INTO `tb_image_tags` VALUES ('6', null, 'a', '2016-02-10 02:10:43', '1');
INSERT INTO `tb_image_tags` VALUES ('7', null, 'b', '2016-02-10 02:10:45', '1');
INSERT INTO `tb_image_tags` VALUES ('42', '4', '美女', '2016-02-11 03:12:30', '1');
INSERT INTO `tb_image_tags` VALUES ('43', '4', '图片', '2016-02-11 03:12:30', '1');
INSERT INTO `tb_image_tags` VALUES ('44', '4', '视频', '2016-02-11 03:12:31', '1');

-- ----------------------------
-- Table structure for `tb_pageview`
-- ----------------------------
DROP TABLE IF EXISTS `tb_pageview`;
CREATE TABLE `tb_pageview` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ip` varchar(64) NOT NULL COMMENT 'IP地址',
  `userid` int(11) DEFAULT NULL COMMENT '用户ID',
  `create_day` varchar(64) NOT NULL COMMENT '创建时间到天',
  `create_time` varchar(64) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=110 DEFAULT CHARSET=utf8 COMMENT='访问量统计';

-- ----------------------------
-- Records of tb_pageview
-- ----------------------------
INSERT INTO `tb_pageview` VALUES ('87', '127.0.0.1', '0', '2016-04-04', '2016-04-04 19:53:45');
INSERT INTO `tb_pageview` VALUES ('88', '127.0.0.1', '0', '2016-04-05', '2016-04-05 00:03:09');
INSERT INTO `tb_pageview` VALUES ('89', '127.0.0.1', '0', '2016-04-06', '2016-04-06 00:06:37');
INSERT INTO `tb_pageview` VALUES ('90', '127.0.0.1', '0', '2016-04-07', '2016-04-07 00:09:26');
INSERT INTO `tb_pageview` VALUES ('91', '0:0:0:0:0:0:0:1', '0', '2016-04-13', '2016-04-13 23:26:45');
INSERT INTO `tb_pageview` VALUES ('92', '127.0.0.1', '0', '2016-04-13', '2016-04-13 23:55:23');
INSERT INTO `tb_pageview` VALUES ('93', '0:0:0:0:0:0:0:1', '0', '2016-04-14', '2016-04-14 00:00:30');
INSERT INTO `tb_pageview` VALUES ('94', '0:0:0:0:0:0:0:1', '0', '2016-04-16', '2016-04-16 02:01:06');
INSERT INTO `tb_pageview` VALUES ('95', '127.0.0.1', '0', '2016-04-16', '2016-04-16 02:02:32');
INSERT INTO `tb_pageview` VALUES ('96', '0:0:0:0:0:0:0:1', '0', '2016-04-17', '2016-04-17 05:19:38');
INSERT INTO `tb_pageview` VALUES ('97', '127.0.0.1', '0', '2016-04-17', '2016-04-17 05:20:06');
INSERT INTO `tb_pageview` VALUES ('98', '0:0:0:0:0:0:0:1', '0', '2016-04-18', '2016-04-18 00:05:27');
INSERT INTO `tb_pageview` VALUES ('99', '127.0.0.1', '0', '2016-04-18', '2016-04-18 00:14:24');
INSERT INTO `tb_pageview` VALUES ('100', '127.0.0.1', '0', '2016-04-19', '2016-04-19 00:02:25');
INSERT INTO `tb_pageview` VALUES ('101', '0:0:0:0:0:0:0:1', '0', '2016-04-19', '2016-04-19 21:28:43');
INSERT INTO `tb_pageview` VALUES ('102', '127.0.0.1', '0', '2016-04-20', '2016-04-20 00:00:09');
INSERT INTO `tb_pageview` VALUES ('103', '127.0.0.1', '0', '2016-04-21', '2016-04-21 01:47:03');
INSERT INTO `tb_pageview` VALUES ('104', '0:0:0:0:0:0:0:1', '0', '2016-05-07', '2016-05-07 21:53:05');
INSERT INTO `tb_pageview` VALUES ('105', '127.0.0.1', '0', '2016-05-07', '2016-05-07 22:25:34');
INSERT INTO `tb_pageview` VALUES ('106', '0:0:0:0:0:0:0:1', '0', '2016-05-08', '2016-05-08 00:01:17');
INSERT INTO `tb_pageview` VALUES ('107', '0:0:0:0:0:0:0:1', '0', '2016-05-11', '2016-05-11 23:44:10');
INSERT INTO `tb_pageview` VALUES ('108', '0:0:0:0:0:0:0:1', '0', '2016-05-12', '2016-05-12 00:00:04');
INSERT INTO `tb_pageview` VALUES ('109', '127.0.0.1', '0', '2016-05-12', '2016-05-12 00:06:36');

-- ----------------------------
-- Table structure for `tb_site`
-- ----------------------------
DROP TABLE IF EXISTS `tb_site`;
CREATE TABLE `tb_site` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(32) NOT NULL COMMENT '名称',
  `template` varchar(32) DEFAULT NULL COMMENT '模板名称',
  `template_mobile` varchar(32) DEFAULT NULL,
  `domain_pc` varchar(64) DEFAULT NULL COMMENT 'pc端域名',
  `domain_mobile` varchar(64) DEFAULT NULL COMMENT '移动端域名',
  `domain_others` varchar(400) DEFAULT NULL COMMENT '其他域名',
  `site_title` varchar(256) DEFAULT NULL COMMENT '标题',
  `site_folder_id` int(11) DEFAULT NULL COMMENT '默认标题ID',
  `site_article_id` int(11) DEFAULT NULL COMMENT '默认文章ID',
  `db_url` varchar(200) DEFAULT NULL COMMENT '数据库',
  `db_user` varchar(64) DEFAULT NULL COMMENT '数据库用户',
  `db_pwd` varchar(64) DEFAULT NULL COMMENT '数据库密码',
  `db_driver` varchar(64) DEFAULT NULL COMMENT '数据库驱动',
  `sort` int(11) DEFAULT '10' COMMENT '序号',
  `status` int(11) DEFAULT '1' COMMENT '状态//radio/2,禁用,1,启用',
  `update_time` varchar(64) DEFAULT NULL COMMENT '更新时间',
  `update_id` int(11) DEFAULT '0' COMMENT '更新人',
  `create_time` varchar(64) DEFAULT NULL COMMENT '创建时间',
  `create_id` int(11) DEFAULT '0' COMMENT '创建者',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='站点';

-- ----------------------------
-- Records of tb_site
-- ----------------------------
INSERT INTO `tb_site` VALUES ('1', '视频管理', 'video', 'video', 'video.demo.com', 'video.demo.com', null, 'FLY的狐狸', null, null, null, null, null, null, '12', '1', '2016-04-04 19:57:22', '1', '2016-04-04 19:57:22', '1');
INSERT INTO `tb_site` VALUES ('2', '门头沟', 'mtg', 'mtg', 'localhost', 'localhost', null, '门头沟信息网', '1', '1', null, null, null, null, '8', '1', '2016-04-07 01:10:22', '1', '2016-04-07 01:10:22', '1');
INSERT INTO `tb_site` VALUES ('3', '网站', 'website', 'website', '127.0.0.11', '127.0.0.11', null, 'FLY的狐狸', '230', '2301', 'jdbc:mysql://127.0.0.1:3306/jfinal_cms_website?characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull', 'root', '123456', 'com.mysql.jdbc.Driver', '10', '1', '2016-03-21 23:58:54', '1', '2016-03-21 23:58:54', '1');
INSERT INTO `tb_site` VALUES ('4', '照片管理', 'photo', 'photo', 'photo.demo.com', 'photo.demo.com', null, 'FLY的狐狸', null, null, 'jdbc:mysql://127.0.0.1:3306/jfinal_cms_photo?characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull', 'root', '123456', 'com.mysql.jdbc.Driver', '11', '1', '2016-03-22 22:25:21', '1', '2016-03-22 22:25:21', '1');
INSERT INTO `tb_site` VALUES ('8', '项目', 'project', 'project', '127.0.0.1', '127.0.0.1', null, 'FLY的狐狸', '253', null, null, null, null, null, '20', '1', '2016-06-17 16:47:44', '1', '2016-06-17 16:47:44', '1');

-- ----------------------------
-- Table structure for `tb_tags`
-- ----------------------------
DROP TABLE IF EXISTS `tb_tags`;
CREATE TABLE `tb_tags` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `article_id` int(11) DEFAULT NULL COMMENT '文章ID',
  `tagname` varchar(200) DEFAULT '' COMMENT '标签内容',
  `create_time` varchar(64) DEFAULT NULL COMMENT '创建时间',
  `create_id` int(11) DEFAULT '0' COMMENT '创建者',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=157 DEFAULT CHARSET=utf8 COMMENT='标签';

-- ----------------------------
-- Records of tb_tags
-- ----------------------------
INSERT INTO `tb_tags` VALUES ('6', '343', '学校', '2015-05-24 23:26:54', '1');
INSERT INTO `tb_tags` VALUES ('7', '343', '大峪中学', '2015-05-24 23:26:54', '1');
INSERT INTO `tb_tags` VALUES ('8', '350', '学校', '2015-05-25 14:05:41', '1');
INSERT INTO `tb_tags` VALUES ('9', '350', '坡头中学', '2015-05-25 14:05:41', '1');
INSERT INTO `tb_tags` VALUES ('10', '349', '学校', '2015-05-25 14:05:51', '1');
INSERT INTO `tb_tags` VALUES ('11', '349', '三家店铁路中学', '2015-05-25 14:05:51', '1');
INSERT INTO `tb_tags` VALUES ('12', '348', '学校', '2015-05-25 14:06:01', '1');
INSERT INTO `tb_tags` VALUES ('13', '348', '育新中学', '2015-05-25 14:06:02', '1');
INSERT INTO `tb_tags` VALUES ('14', '347', '学校', '2015-05-25 14:06:13', '1');
INSERT INTO `tb_tags` VALUES ('15', '347', '新桥路中学', '2015-05-25 14:06:13', '1');
INSERT INTO `tb_tags` VALUES ('16', '346', '学校', '2015-05-25 14:06:24', '1');
INSERT INTO `tb_tags` VALUES ('17', '346', '大峪中学分校', '2015-05-25 14:06:24', '1');
INSERT INTO `tb_tags` VALUES ('18', '345', '学校', '2015-05-25 14:06:42', '1');
INSERT INTO `tb_tags` VALUES ('19', '345', '首都师范大学附属中学', '2015-05-25 14:06:42', '1');
INSERT INTO `tb_tags` VALUES ('20', '345', '永定中学', '2015-05-25 14:06:42', '1');
INSERT INTO `tb_tags` VALUES ('21', '344', '学校', '2015-05-25 14:06:53', '1');
INSERT INTO `tb_tags` VALUES ('22', '344', '育园中学', '2015-05-25 14:06:53', '1');
INSERT INTO `tb_tags` VALUES ('23', '375', '公园', '2015-05-26 13:19:08', '1');
INSERT INTO `tb_tags` VALUES ('24', '375', '永定河文化广场', '2015-05-26 13:19:08', '1');
INSERT INTO `tb_tags` VALUES ('25', '374', '永定河森林公园', '2015-05-26 13:19:17', '1');
INSERT INTO `tb_tags` VALUES ('26', '374', '公园', '2015-05-26 13:19:17', '1');
INSERT INTO `tb_tags` VALUES ('27', '373', '永定河森林公园', '2015-05-26 13:19:25', '1');
INSERT INTO `tb_tags` VALUES ('28', '373', '公园', '2015-05-26 13:19:25', '1');
INSERT INTO `tb_tags` VALUES ('29', '372', '新桥公园', '2015-05-26 13:20:12', '1');
INSERT INTO `tb_tags` VALUES ('30', '372', '公园', '2015-05-26 13:20:12', '1');
INSERT INTO `tb_tags` VALUES ('31', '371', '西苑公园', '2015-05-26 13:22:48', '1');
INSERT INTO `tb_tags` VALUES ('32', '371', '公园', '2015-05-26 13:22:48', '1');
INSERT INTO `tb_tags` VALUES ('33', '370', '石门营公园', '2015-05-26 13:25:00', '1');
INSERT INTO `tb_tags` VALUES ('34', '370', '公园', '2015-05-26 13:25:00', '1');
INSERT INTO `tb_tags` VALUES ('35', '369', '石龙公园', '2015-05-26 13:37:24', '1');
INSERT INTO `tb_tags` VALUES ('36', '369', '公园', '2015-05-26 13:37:24', '1');
INSERT INTO `tb_tags` VALUES ('37', '368', '葡山公园', '2015-05-26 13:39:14', '1');
INSERT INTO `tb_tags` VALUES ('38', '368', '公园', '2015-05-26 13:39:14', '1');
INSERT INTO `tb_tags` VALUES ('39', '367', '葡东公园', '2015-05-26 13:40:26', '1');
INSERT INTO `tb_tags` VALUES ('40', '367', '公园', '2015-05-26 13:40:26', '1');
INSERT INTO `tb_tags` VALUES ('41', '366', '门头沟新城滨河森林公园', '2015-05-26 13:42:29', '1');
INSERT INTO `tb_tags` VALUES ('42', '366', '公园', '2015-05-26 13:42:29', '1');
INSERT INTO `tb_tags` VALUES ('43', '365', '门矿遗址公园', '2015-05-26 13:43:30', '1');
INSERT INTO `tb_tags` VALUES ('44', '365', '公园', '2015-05-26 13:43:30', '1');
INSERT INTO `tb_tags` VALUES ('45', '364', '立思辰公园', '2015-05-26 13:44:43', '1');
INSERT INTO `tb_tags` VALUES ('46', '364', '公园', '2015-05-26 13:44:43', '1');
INSERT INTO `tb_tags` VALUES ('47', '363', '京门铁路遗址公园', '2015-05-26 13:45:35', '1');
INSERT INTO `tb_tags` VALUES ('48', '363', '公园', '2015-05-26 13:45:35', '1');
INSERT INTO `tb_tags` VALUES ('49', '362', '京浪岛文化体育公园', '2015-05-26 13:46:57', '1');
INSERT INTO `tb_tags` VALUES ('50', '362', '公园', '2015-05-26 13:46:57', '1');
INSERT INTO `tb_tags` VALUES ('51', '361', '黑山公园', '2015-05-26 13:48:00', '1');
INSERT INTO `tb_tags` VALUES ('52', '361', '公园', '2015-05-26 13:48:00', '1');
INSERT INTO `tb_tags` VALUES ('53', '360', '光荣园', '2015-05-26 13:49:15', '1');
INSERT INTO `tb_tags` VALUES ('54', '360', '公园', '2015-05-26 13:49:16', '1');
INSERT INTO `tb_tags` VALUES ('57', '358', '东辛房公园', '2015-05-26 13:50:45', '1');
INSERT INTO `tb_tags` VALUES ('58', '358', '公园', '2015-05-26 13:50:45', '1');
INSERT INTO `tb_tags` VALUES ('59', '357', '晨曦公园', '2015-05-26 13:51:47', '1');
INSERT INTO `tb_tags` VALUES ('60', '357', '公园', '2015-05-26 13:51:47', '1');
INSERT INTO `tb_tags` VALUES ('61', '356', '滨河公园', '2015-05-26 13:52:42', '1');
INSERT INTO `tb_tags` VALUES ('62', '356', '公园', '2015-05-26 13:52:42', '1');
INSERT INTO `tb_tags` VALUES ('63', '324', '新闻', '2015-05-26 14:05:51', '1');
INSERT INTO `tb_tags` VALUES ('64', '323', '新闻', '2015-05-26 14:08:41', '1');
INSERT INTO `tb_tags` VALUES ('65', '322', '新闻', '2015-05-26 14:10:33', '1');
INSERT INTO `tb_tags` VALUES ('66', '321', '新闻', '2015-05-26 14:12:12', '1');
INSERT INTO `tb_tags` VALUES ('67', '320', '新闻', '2015-05-26 14:13:58', '1');
INSERT INTO `tb_tags` VALUES ('68', '319', '新闻', '2015-05-26 14:37:57', '1');
INSERT INTO `tb_tags` VALUES ('69', '318', '新闻', '2015-05-26 14:40:09', '1');
INSERT INTO `tb_tags` VALUES ('70', '111', '新闻', '2015-05-26 14:43:28', '1');
INSERT INTO `tb_tags` VALUES ('71', '111', '定都阁', '2015-05-26 14:43:28', '1');
INSERT INTO `tb_tags` VALUES ('72', '105', '新闻', '2015-05-26 14:44:49', '1');
INSERT INTO `tb_tags` VALUES ('77', '335', '福华肥牛', '2015-05-26 14:52:49', '1');
INSERT INTO `tb_tags` VALUES ('78', '335', '饭店', '2015-05-26 14:52:49', '1');
INSERT INTO `tb_tags` VALUES ('79', '335', '美食', '2015-05-26 14:52:49', '1');
INSERT INTO `tb_tags` VALUES ('80', '334', '大鸭梨', '2015-05-26 14:52:54', '1');
INSERT INTO `tb_tags` VALUES ('81', '334', '饭店', '2015-05-26 14:52:54', '1');
INSERT INTO `tb_tags` VALUES ('82', '334', '美食', '2015-05-26 14:52:54', '1');
INSERT INTO `tb_tags` VALUES ('83', '342', '美食', '2015-05-26 14:53:01', '1');
INSERT INTO `tb_tags` VALUES ('84', '342', '老家肉饼', '2015-05-26 14:53:01', '1');
INSERT INTO `tb_tags` VALUES ('85', '342', '饭店', '2015-05-26 14:53:01', '1');
INSERT INTO `tb_tags` VALUES ('88', '341', '顶风针', '2015-05-26 14:55:38', '1');
INSERT INTO `tb_tags` VALUES ('89', '341', '蛋糕店', '2015-05-26 14:55:38', '1');
INSERT INTO `tb_tags` VALUES ('96', '340', '饭店', '2015-05-26 16:01:38', '1');
INSERT INTO `tb_tags` VALUES ('97', '340', '美食', '2015-05-26 16:01:38', '1');
INSERT INTO `tb_tags` VALUES ('98', '340', '新新包子铺', '2015-05-26 16:01:38', '1');
INSERT INTO `tb_tags` VALUES ('99', '339', '酒店', '2015-05-26 16:03:40', '1');
INSERT INTO `tb_tags` VALUES ('100', '339', '今天假日酒店', '2015-05-26 16:03:40', '1');
INSERT INTO `tb_tags` VALUES ('101', '339', '饭店', '2015-05-26 16:03:40', '1');
INSERT INTO `tb_tags` VALUES ('102', '338', '宾馆', '2015-05-26 16:09:18', '1');
INSERT INTO `tb_tags` VALUES ('103', '338', '龙泉宾馆', '2015-05-26 16:09:18', '1');
INSERT INTO `tb_tags` VALUES ('104', '336', '晨光饭店', '2015-05-26 16:09:58', '1');
INSERT INTO `tb_tags` VALUES ('105', '336', '饭店', '2015-05-26 16:09:58', '1');
INSERT INTO `tb_tags` VALUES ('108', '337', '潭柘嘉福饭店', '2015-05-26 16:12:13', '1');
INSERT INTO `tb_tags` VALUES ('109', '337', '饭店', '2015-05-26 16:12:13', '1');
INSERT INTO `tb_tags` VALUES ('126', '329', '百花山', '2015-05-26 16:31:32', '1');
INSERT INTO `tb_tags` VALUES ('127', '329', '旅游', '2015-05-26 16:31:32', '1');
INSERT INTO `tb_tags` VALUES ('128', '330', '爨底下', '2015-05-26 16:32:10', '1');
INSERT INTO `tb_tags` VALUES ('129', '330', '旅游', '2015-05-26 16:32:10', '1');
INSERT INTO `tb_tags` VALUES ('130', '331', '灵山', '2015-05-26 16:34:18', '1');
INSERT INTO `tb_tags` VALUES ('131', '331', '旅游', '2015-05-26 16:34:18', '1');
INSERT INTO `tb_tags` VALUES ('132', '332', '旅游', '2015-05-26 16:34:54', '1');
INSERT INTO `tb_tags` VALUES ('133', '332', '龙门涧', '2015-05-26 16:34:54', '1');
INSERT INTO `tb_tags` VALUES ('134', '332', '旅游', '2015-05-26 16:34:54', '1');
INSERT INTO `tb_tags` VALUES ('137', '333', '妙峰山', '2015-05-26 16:35:46', '1');
INSERT INTO `tb_tags` VALUES ('138', '333', '旅游', '2015-05-26 16:35:46', '1');
INSERT INTO `tb_tags` VALUES ('139', '328', '双龙峡', '2015-05-26 16:44:36', '1');
INSERT INTO `tb_tags` VALUES ('140', '328', '旅游', '2015-05-26 16:44:36', '1');
INSERT INTO `tb_tags` VALUES ('141', '391', '2. 博文内容尽量控制在1200个文字内', '2015-06-20 16:59:29', '2');
INSERT INTO `tb_tags` VALUES ('142', '391', '内容合法健康', '2015-06-20 16:59:29', '2');
INSERT INTO `tb_tags` VALUES ('143', '391', '否则可能会被删除。2. 博文内容尽量控制在1200个文字内', '2015-06-20 16:59:29', '2');
INSERT INTO `tb_tags` VALUES ('144', '391', '内容合法健康', '2015-06-20 16:59:29', '2');
INSERT INTO `tb_tags` VALUES ('145', '391', '否则可能会被删除。2. 博文内容尽量控制在1200个文字内', '2015-06-20 16:59:29', '2');
INSERT INTO `tb_tags` VALUES ('146', '392', '123', '2015-06-20 18:31:13', '2');
INSERT INTO `tb_tags` VALUES ('147', '383', '123', '2015-06-20 18:33:14', '2');
INSERT INTO `tb_tags` VALUES ('151', '396', '测44', '2015-06-20 21:48:59', '2');
INSERT INTO `tb_tags` VALUES ('152', '397', '测试', '2015-06-20 21:52:02', '2');
INSERT INTO `tb_tags` VALUES ('153', '112', 'S1线', '2015-08-16 19:24:15', '1');
INSERT INTO `tb_tags` VALUES ('155', '359', '福鼎公园', '2015-09-21 22:37:54', '1');
INSERT INTO `tb_tags` VALUES ('156', '359', '公园', '2015-09-21 22:37:54', '1');

-- ----------------------------
-- Table structure for `tb_video`
-- ----------------------------
DROP TABLE IF EXISTS `tb_video`;
CREATE TABLE `tb_video` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `album_id` int(11) DEFAULT '1' COMMENT '专辑ID',
  `album_name` varchar(200) DEFAULT '' COMMENT '专辑名称',
  `name` varchar(200) DEFAULT '' COMMENT '视频名称',
  `video_url` varchar(256) DEFAULT NULL COMMENT '点播视频路径',
  `video_net_url` varchar(256) DEFAULT NULL COMMENT '网络视频路径',
  `thumbnail` varchar(256) DEFAULT '' COMMENT '缩略图',
  `ext` varchar(20) DEFAULT '' COMMENT '扩展名',
  `resolution` varchar(20) DEFAULT '' COMMENT '分辨率',
  `status` int(11) DEFAULT '1' COMMENT '状态//ra dio/2,隐藏,1,显示',
  `is_comment` int(11) DEFAULT '1' COMMENT '是否评论//radio/2,否,1,是',
  `is_recommend` int(11) DEFAULT '2' COMMENT '是否推荐：2 否 1 是',
  `sort` int(11) DEFAULT '1' COMMENT '排序',
  `remark` varchar(400) DEFAULT NULL COMMENT '备注',
  `publish_time` varchar(64) DEFAULT NULL COMMENT '发布时间',
  `publish_user` varchar(64) DEFAULT '1' COMMENT '发布者',
  `update_time` varchar(64) DEFAULT NULL COMMENT '更新时间',
  `update_id` int(11) DEFAULT '0' COMMENT '更新者',
  `create_time` varchar(64) DEFAULT NULL COMMENT '创建时间',
  `create_id` int(11) DEFAULT '0' COMMENT '创建者',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COMMENT='视频';

-- ----------------------------
-- Records of tb_video
-- ----------------------------
INSERT INTO `tb_video` VALUES ('1', '5', '娱乐', '娱乐', 'download\\video\\20160217_094403_874395.mp4', null, 'http://i11.tietuku.cn/80643b0edf91c5d5.jpg', 'mp4', '', '1', '2', '1', '9', null, '2016-02-17', '系统管理员', '2016-02-17 09:35:53', '1', '2016-02-17 09:35:53', '1');
INSERT INTO `tb_video` VALUES ('2', '5', '娱乐', '娱乐1', 'download\\video\\20160217_094403_874395.mp4', 'http://content.12530.com/media/v/20080416/100221.flv', 'http://i11.tietuku.cn/3705a49b952e95f0.jpg', 'flv', '', '1', '1', '1', '10', null, '2016-02-19', '系统管理员', '2016-02-19 17:32:20', '1', '2016-02-19 17:32:20', '1');
INSERT INTO `tb_video` VALUES ('3', '5', '娱乐', '娱乐2', 'download\\video\\20160217_094403_874395.mp4', 'http://content.12530.com/media/v/20080416/100222.flv', 'http://i11.tietuku.cn/d00be751ab792bae.jpg', 'flv', '', '1', '1', '2', '10', null, '2016-02-19', '系统管理员', '2016-02-19 17:32:31', '1', '2016-02-19 17:32:31', '1');
INSERT INTO `tb_video` VALUES ('4', '5', '娱乐', '娱乐3', 'download\\video\\20160217_094403_874395.mp4', 'http://content.12530.com/media/v/20080416/100373.flv', 'http://i11.tietuku.cn/d261bf2348cbcec1.jpg', 'flv', '', '1', '1', '2', '10', null, '2016-02-19', '系统管理员', '2016-02-19 17:32:38', '1', '2016-02-19 17:32:38', '1');
INSERT INTO `tb_video` VALUES ('5', '5', '娱乐', '娱乐4', 'download\\video\\20160217_094403_874395.mp4', 'http://content.12530.com/media/v/20080416/100225.flv', 'http://i13.tietuku.cn/a1a829bd0ec99b57.jpg', 'flv', '', '1', '1', '2', '10', null, '2016-02-19', '系统管理员', '2016-02-19 17:32:43', '1', '2016-02-19 17:32:43', '1');
INSERT INTO `tb_video` VALUES ('6', '5', '娱乐', '娱乐5', 'download\\video\\20160217_094403_874395.mp4', 'http://content.12530.com/media/v/20080416/100235.flv', 'http://i13.tietuku.cn/638dbfb6ddcede91.jpg', 'flv', '', '1', '1', '2', '9', null, '2016-02-19', '系统管理员', '2016-02-19 17:32:50', '1', '2016-02-19 17:32:50', '1');
INSERT INTO `tb_video` VALUES ('7', '5', '娱乐', '娱乐6', 'download\\video\\20160217_094403_874395.mp4', 'http://content.12530.com/media/v/20080416/100236.flv', 'http://i5.tietuku.cn/150941a559f04067.jpg', 'flv', '', '1', '1', '2', '10', null, '2016-02-19', '系统管理员', '2016-02-19 17:32:57', '1', '2016-02-19 17:32:57', '1');
INSERT INTO `tb_video` VALUES ('8', '5', '娱乐', '娱乐7', 'download\\video\\20160217_094403_874395.mp4', 'http://content.12530.com/media/v/20080416/100237.flv', 'http://i11.tietuku.cn/84668a447c6effbc.jpg', 'flv', '', '1', '1', '2', '10', null, '2016-02-19', '系统管理员', '2016-02-19 17:33:02', '1', '2016-02-19 17:33:02', '1');
INSERT INTO `tb_video` VALUES ('9', '5', '娱乐', '娱乐8', 'download\\video\\20160217_094403_874395.mp4', 'http://content.12530.com/media/v/20080416/100238.flv', 'http://i11.tietuku.cn/cb883f775bf9f86b.jpg', 'flv', '', '1', '1', '2', '10', null, '2016-02-19', '系统管理员', '2016-02-19 17:33:08', '1', '2016-02-19 17:33:08', '1');
INSERT INTO `tb_video` VALUES ('10', '6', '新闻', '新闻1', null, 'http://content.12530.com/media/v/20080416/100061.flv', 'http://i4.tietuku.cn/4ff3ca1b9cf0358a.jpg', 'flv', '', '1', '1', '2', '10', null, '2016-02-20', '系统管理员', '2016-02-20 09:28:19', '1', '2016-02-20 09:28:19', '1');
INSERT INTO `tb_video` VALUES ('11', '2', '电影', '电影1', null, 'http://content.12530.com/media/v/20080416/100051.flv', 'http://i12.tietuku.cn/e11c25401d5a72ef.jpg', 'flv', '', '1', '1', '2', '10', null, '2016-02-20', '系统管理员', '2016-02-20 09:41:58', '1', '2016-02-20 09:41:58', '1');
INSERT INTO `tb_video` VALUES ('12', '2', '电影', '电影2', null, 'http://content.12530.com/media/v/20080416/100052.flv', 'http://i13.tietuku.cn/4512392c7337a433.jpg', 'flv', '', '1', '1', '2', '10', null, '2016-02-20', '系统管理员', '2016-02-20 09:42:52', '1', '2016-02-20 09:42:52', '1');
INSERT INTO `tb_video` VALUES ('13', '1', '电视剧', '电视剧1', null, 'http://content.12530.com/media/v/20080416/100067.flv', 'http://i13.tietuku.cn/1254065a8c0200d3.jpg', 'flv', '', '1', '1', '2', '10', null, '2016-02-20', '系统管理员', '2016-02-20 09:43:36', '1', '2016-02-20 09:43:36', '1');
INSERT INTO `tb_video` VALUES ('14', '3', '综艺', '综艺1', null, 'http://content.12530.com/media/v/20080416/100071.flv', 'http://i13.tietuku.cn/440847c1aa76fc5e.jpg', 'flv', '', '1', '1', '2', '10', null, '2016-02-20', '系统管理员', '2016-02-20 09:44:54', '1', '2016-02-20 09:44:54', '1');
INSERT INTO `tb_video` VALUES ('15', '4', '动漫', '动漫1', null, 'http://content.12530.com/media/v/20080416/100081.flv', 'http://i11.tietuku.cn/11591386b92cb57b.png', 'flv', '', '1', '1', '2', '10', null, '2016-02-20', '系统管理员', '2016-02-20 09:45:38', '1', '2016-02-20 09:45:38', '1');
INSERT INTO `tb_video` VALUES ('16', '4', '动漫', '动漫2', null, 'http://content.12530.com/media/v/20080416/100082.flv', 'http://i13.tietuku.cn/f61ae07bf891e96d.jpg', 'flv', '', '1', '1', '2', '10', null, '2016-02-20', '系统管理员', '2016-02-20 09:46:01', '1', '2016-02-20 09:46:01', '1');
INSERT INTO `tb_video` VALUES ('17', '4', '动漫', '动漫3', null, 'http://content.12530.com/media/v/20080416/100083.flv', 'http://i13.tietuku.cn/e067a3e087c927cf.jpg', 'flv', '', '1', '1', '2', '10', null, '2016-02-20', '系统管理员', '2016-02-20 09:47:23', '1', '2016-02-20 09:47:23', '1');
INSERT INTO `tb_video` VALUES ('18', '4', '动漫', '动漫4', null, 'http://content.12530.com/media/v/20080416/100084.flv', 'http://i11.tietuku.cn/d93e14328746610a.jpg', 'flv', '', '1', '1', '2', '10', null, '2016-02-20', '系统管理员', '2016-02-20 09:47:58', '1', '2016-02-20 09:47:58', '1');
INSERT INTO `tb_video` VALUES ('19', '4', '动漫', '动漫5', null, 'http://content.12530.com/media/v/20080416/100085.flv', 'http://i11.tietuku.cn/950af81428ab6a9c.jpg', 'flv', '', '1', '1', '2', '10', null, '2016-02-20', '系统管理员', '2016-02-20 09:48:16', '1', '2016-02-20 09:48:16', '1');
INSERT INTO `tb_video` VALUES ('20', '4', '动漫', '动漫6', null, 'http://content.12530.com/media/v/20080416/100086.flv', 'http://i12.tietuku.cn/ec8dc8f804b43ba9.jpg', 'flv', '', '1', '1', '2', '10', null, '2016-02-20', '系统管理员', '2016-02-20 09:49:30', '1', '2016-02-20 09:49:30', '1');
INSERT INTO `tb_video` VALUES ('21', '2', '电影', '电影3', null, 'http://content.12530.com/media/v/20080416/100053.flv', 'http://i11.tietuku.cn/118422ba30d4e86f.jpg', 'flv', '', '1', '1', '2', '10', null, '2016-02-20', '系统管理员', '2016-02-20 09:50:50', '1', '2016-02-20 09:50:50', '1');
INSERT INTO `tb_video` VALUES ('22', '2', '电影', '电影4', null, 'http://content.12530.com/media/v/20080416/100054.flv', 'http://i11.tietuku.cn/ff6674776e64019b.jpg', 'flv', '', '1', '1', '2', '10', null, '2016-02-20', '系统管理员', '2016-02-20 09:51:00', '1', '2016-02-20 09:51:00', '1');
INSERT INTO `tb_video` VALUES ('23', '2', '电影', '电影5', null, 'http://content.12530.com/media/v/20080416/100055.flv', 'http://i11.tietuku.cn/3f2ff3cf11bb5761.jpg', 'flv', '', '1', '1', '2', '10', null, '2016-02-20', '系统管理员', '2016-02-20 09:51:31', '1', '2016-02-20 09:51:31', '1');

-- ----------------------------
-- Table structure for `tb_video_album`
-- ----------------------------
DROP TABLE IF EXISTS `tb_video_album`;
CREATE TABLE `tb_video_album` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parent_id` int(11) DEFAULT '0' COMMENT '父ID',
  `name` varchar(100) NOT NULL DEFAULT '' COMMENT '专辑名称',
  `remark` text COMMENT '描述',
  `sort` int(11) DEFAULT '1' COMMENT '排序',
  `status` int(11) DEFAULT '1' COMMENT '状态//radio/2,隐藏,1,显示',
  `update_time` varchar(64) DEFAULT NULL COMMENT '更新时间',
  `update_id` int(11) DEFAULT '0' COMMENT '更新人',
  `create_time` varchar(64) DEFAULT NULL COMMENT '创建时间',
  `create_id` int(11) DEFAULT '0' COMMENT '创建者',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='专辑';

-- ----------------------------
-- Records of tb_video_album
-- ----------------------------
INSERT INTO `tb_video_album` VALUES ('1', '0', '电视剧', null, '10', '1', '2016-02-16 16:57:54', '1', '2016-02-16 16:57:54', '1');
INSERT INTO `tb_video_album` VALUES ('2', '0', '电影', null, '10', '1', '2016-02-16 16:58:00', '1', '2016-02-16 16:58:00', '1');
INSERT INTO `tb_video_album` VALUES ('3', '0', '综艺', null, '10', '1', '2016-02-16 16:58:09', '1', '2016-02-16 16:58:09', '1');
INSERT INTO `tb_video_album` VALUES ('4', '0', '动漫', null, '10', '1', '2016-02-16 16:58:17', '1', '2016-02-16 16:58:17', '1');
INSERT INTO `tb_video_album` VALUES ('5', '0', '娱乐', null, '10', '1', '2016-02-16 16:58:22', '1', '2016-02-16 16:58:22', '1');
INSERT INTO `tb_video_album` VALUES ('6', '0', '新闻', null, '10', '1', '2016-02-16 16:58:26', '1', '2016-02-16 16:58:26', '1');

-- ----------------------------
-- Table structure for `tb_video_tags`
-- ----------------------------
DROP TABLE IF EXISTS `tb_video_tags`;
CREATE TABLE `tb_video_tags` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `video_id` int(11) DEFAULT NULL COMMENT '视频ID',
  `tagname` varchar(200) DEFAULT '' COMMENT '标签内容',
  `create_time` varchar(64) DEFAULT NULL COMMENT '创建时间',
  `create_id` int(11) DEFAULT '0' COMMENT '创建者',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='视频标签';

-- ----------------------------
-- Records of tb_video_tags
-- ----------------------------
