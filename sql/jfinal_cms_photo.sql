/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50610
Source Host           : localhost:3306
Source Database       : jfinal_cms_photo

Target Server Type    : MYSQL
Target Server Version : 50610
File Encoding         : 65001

Date: 2016-02-11 03:45:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `sys_department`
-- ----------------------------
DROP TABLE IF EXISTS `sys_department`;
CREATE TABLE `sys_department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL COMMENT '部门/11111',
  `sort` int(11) DEFAULT '0' COMMENT '序号',
  `linkman` varchar(64) DEFAULT NULL COMMENT '联系人',
  `linkman_no` varchar(32) DEFAULT NULL COMMENT '联系人电话',
  `create_time` varchar(64) DEFAULT NULL COMMENT '创建时间',
  `create_id` int(11) DEFAULT '0' COMMENT '创建者',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='部门';

-- ----------------------------
-- Records of sys_department
-- ----------------------------
INSERT INTO `sys_department` VALUES ('1', '系统承建单位', '99', 'system', '15888888888', '2016-06-06 06:06:06', '1');
INSERT INTO `sys_department` VALUES ('2', '注册用户', '97', '无人', '15888888888', '2015-04-28 22:39:34', '1');

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
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8 COMMENT='数据字典主表';

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES ('1', '日志配置', 'systemLog', null);
INSERT INTO `sys_dict` VALUES ('2', '目录类型', 'articleType', null);
INSERT INTO `sys_dict` VALUES ('11', '目录类型', 'folderType', null);
INSERT INTO `sys_dict` VALUES ('100', '系统参数', 'systemParam', null);
INSERT INTO `sys_dict` VALUES ('101', '友情链接类型', 'friendlyLinkType', null);

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
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8 COMMENT='数据字典';

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
INSERT INTO `sys_dict_detail` VALUES ('101', 'systemParam', '图片管理 - FLY的狐狸', '1', '1', null, null, null, null, '2015-01-30', '1');

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
) ENGINE=InnoDB AUTO_INCREMENT=10504 DEFAULT CHARSET=utf8 COMMENT='日志';

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
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 COMMENT='菜单';

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------

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
  `create_time` varchar(64) DEFAULT NULL COMMENT '创建时间',
  `create_id` int(11) DEFAULT '0' COMMENT '创建者',
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='用户';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '1RHFCLt64uOOViCTzgSaww==', '系统管理员', '1', '1', '1', null, null, 'zcool321@sina.com', null, null, null, '时间是最好的老师，但遗憾的是——最后他把所有的学生都弄死了', 'default', '2016-06-06 06:06:06', '1');
INSERT INTO `sys_user` VALUES ('2', 'test', 'ldKI9edsQVM=', '后台测试用户', '2', '1', '1', null, null, null, null, null, null, null, 'default', '2016-06-06 06:06:06', '1');
INSERT INTO `sys_user` VALUES ('3', 'webtest', 'ldKI9edsQVM=', '前台测试用户', '2', '3', '1', null, null, null, null, null, null, null, 'default', '2016-06-06 06:06:06', '1');

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='意见反馈';

-- ----------------------------
-- Records of tb_advice_feedback
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_article`
-- ----------------------------
DROP TABLE IF EXISTS `tb_article`;
CREATE TABLE `tb_article` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `folder_id` int(11) DEFAULT '1' COMMENT '目录id',
  `title` varchar(200) DEFAULT '' COMMENT '文章名称',
  `content` text COMMENT '文件内容',
  `count_view` int(11) DEFAULT '0' COMMENT '浏览数',
  `count_comment` int(11) DEFAULT '0' COMMENT '评论数',
  `type` int(11) DEFAULT '1' COMMENT '类型：1 正常 2 预览展示概述 3 程序调用处理',
  `status` varchar(20) DEFAULT '1' COMMENT '状态//radio/2,隐藏,1,显示',
  `is_comment` int(11) DEFAULT '1' COMMENT '是否评论：2 否 1 是',
  `is_recommend` int(11) DEFAULT '2' COMMENT '是否推荐：2 否 1 是',
  `sort` int(11) DEFAULT '1' COMMENT '排序',
  `image_url` varchar(256) DEFAULT NULL COMMENT '图片路径',
  `image_net_url` varchar(256) DEFAULT NULL COMMENT '网络图片路径',
  `file_url` varchar(256) DEFAULT NULL,
  `file_name` varchar(256) DEFAULT NULL,
  `publish_time` varchar(64) DEFAULT NULL COMMENT '发布时间',
  `publish_user` varchar(64) DEFAULT '1' COMMENT '发布者',
  `start_time` varchar(64) DEFAULT NULL COMMENT '开始时间',
  `end_time` varchar(64) DEFAULT NULL COMMENT '结束时间',
  `update_time` varchar(64) DEFAULT NULL COMMENT '更新时间',
  `create_time` varchar(64) DEFAULT NULL COMMENT '创建时间',
  `create_id` int(11) DEFAULT '0' COMMENT '创建者',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=415 DEFAULT CHARSET=utf8 COMMENT='文章';

-- ----------------------------
-- Records of tb_article
-- ----------------------------
INSERT INTO `tb_article` VALUES ('1', '1', '图片管理', '<p>内容管理平台</p>', '124', '123', '12', '1', '1', '1', '1', 'download/image_url/20150529_102007_298104.jpg', 'http://i4.tietuku.com/0bf9b53228782326.png', null, null, '2014-03-05', '系统管理员', '2015-01-29', '2015-01-23', '2015-01-28 17:29:55', '2015-01-28', '1');

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='评论';

-- ----------------------------
-- Records of tb_comment
-- ----------------------------

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='异常数据';

-- ----------------------------
-- Records of tb_error
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_folder`
-- ----------------------------
DROP TABLE IF EXISTS `tb_folder`;
CREATE TABLE `tb_folder` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '目录id',
  `parent_id` int(11) DEFAULT '0' COMMENT '父ID',
  `name` varchar(100) NOT NULL DEFAULT '' COMMENT '中文名',
  `key` varchar(100) DEFAULT '' COMMENT 'URL KEY',
  `path` varchar(200) NOT NULL DEFAULT '' COMMENT '路径',
  `content` text COMMENT '描述',
  `sort` int(11) DEFAULT '1' COMMENT '排序',
  `status` varchar(20) DEFAULT '1' COMMENT '状态//radio/2,隐藏,1,显示',
  `type` int(11) DEFAULT '1' COMMENT '类型 1 普通目录 2 a标签 3 a标签_blank 4 直接加载url信息',
  `jump_url` varchar(200) DEFAULT NULL COMMENT '跳转地址',
  `head_keywords` varchar(200) DEFAULT NULL COMMENT 'SEO keywords',
  `head_description` varchar(200) DEFAULT NULL COMMENT 'SEO description',
  `update_time` varchar(64) DEFAULT NULL COMMENT '更新时间',
  `update_id` int(11) DEFAULT '0' COMMENT '更新人',
  `create_time` varchar(64) DEFAULT NULL COMMENT '创建时间',
  `create_id` int(11) DEFAULT '0' COMMENT '创建者',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8 COMMENT='目录';

-- ----------------------------
-- Records of tb_folder
-- ----------------------------
INSERT INTO `tb_folder` VALUES ('1', '0', '首页', 'home', '', 'jfinal cms', '1', '1', '1', null, null, null, '2015-01-28 16:54:03', '0', '2015-01-30 13:24:58', '1');
INSERT INTO `tb_folder` VALUES ('6', '0', '后台管理', null, '', null, '90', '1', '3', 'admin', null, null, '2015-05-24 15:47:32', '0', '2015-01-30 13:24:58', '1');
INSERT INTO `tb_folder` VALUES ('100', '0', '博文目录', '', '', null, '99', '2', '1', null, null, null, '2015-06-17 22:29:44', '0', '2015-06-17 22:29:44', '2');

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='栏目公告';

-- ----------------------------
-- Records of tb_folder_notice
-- ----------------------------

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='栏目轮播图';

-- ----------------------------
-- Records of tb_folder_roll_picture
-- ----------------------------

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
  `create_time` varchar(64) DEFAULT NULL COMMENT '创建时间',
  `create_id` int(11) DEFAULT '0' COMMENT '创建者',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='友情链接表';

-- ----------------------------
-- Records of tb_friendlylink
-- ----------------------------
INSERT INTO `tb_friendlylink` VALUES ('1', '意见反馈', 'advice', '16', '1', '22', null, '2015-04-24 15:03:02', '1');
INSERT INTO `tb_friendlylink` VALUES ('2', '捐赠我们', 'front/about/351.html', '13', '1', '22', null, '2015-04-24 15:27:36', '1');
INSERT INTO `tb_friendlylink` VALUES ('3', '关于我们', 'front/about/352.html', '2', '1', '22', null, '2015-04-24 15:28:56', '1');
INSERT INTO `tb_friendlylink` VALUES ('4', '给我写信', 'http://mail.qq.com/cgi-bin/qm_share?t=qm_mailme&email=allcU1tTW15dWiobG0QJBQc', '15', '1', '22', null, '2015-04-24 15:29:12', '1');
INSERT INTO `tb_friendlylink` VALUES ('5', '大峪中学', 'http://www.dyzx-bj.com/', '112', '1', '21', null, '2015-05-06 16:13:40', '1');
INSERT INTO `tb_friendlylink` VALUES ('6', 'Jflyfox博客', 'http://www.jflyfox.com/', '114', '1', '21', null, '2015-05-06 16:14:37', '1');
INSERT INTO `tb_friendlylink` VALUES ('7', '门头沟介绍', 'http://baike.baidu.com/view/193726.htm?fromtitle=%E9%97%A8%E5%A4%B4%E6%B2%9F&fromid=1055081&type=syn', '111', '1', '21', null, '2015-05-06 16:15:03', '1');
INSERT INTO `tb_friendlylink` VALUES ('8', '联系我们', 'front/about/353.html', '3', '1', '22', null, '2015-05-26 11:26:57', '1');
INSERT INTO `tb_friendlylink` VALUES ('9', '免责声明', 'front/about/354.html', '20', '1', '22', null, '2015-05-26 11:27:18', '1');
INSERT INTO `tb_friendlylink` VALUES ('10', '广告服务', 'front/about/355.html', '11', '1', '22', null, '2015-05-26 11:28:42', '1');

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
INSERT INTO `tb_image` VALUES ('5', '4', '游戏', '游戏1', 'http://news.waigame.com/static/newsimage/20140603/14017632866819.jpg', '', null, 'http://news.waigame.com/static/newsimage/20140603/14017632866819.jpg', 'jpg', '1400', '933', '1', '2', '2', '10', null, '2016-02-10', '系统管理员', '2016-02-10 00:44:14', '1', '2016-02-10 00:44:14', '1');
INSERT INTO `tb_image` VALUES ('7', '2', '美女', '美女2', 'http://i12.tietuku.com/15a72119633c812d.jpg', '', null, 'http://i12.tietuku.com/15a72119633c812d.jpg', 'jpg', '400', '445', '1', '2', '1', '8', null, '2016-02-10', '系统管理员', '2016-02-10 01:25:41', '1', '2016-02-10 01:25:41', '1');
INSERT INTO `tb_image` VALUES ('8', '2', '美女', '美女3', 'http://i12.tietuku.com/ccb2e30c9fda92f6.jpg', '', null, 'http://i12.tietuku.com/ccb2e30c9fda92f6.jpg', 'jpg', '580', '870', '1', '2', '2', '8', null, '2016-02-10', '系统管理员', '2016-02-10 01:30:59', '1', '2016-02-10 01:30:59', '1');
INSERT INTO `tb_image` VALUES ('9', '2', '美女', '美女4', 'http://i12.tietuku.com/2329cc70e32d51c7.jpg', '', null, 'http://i12.tietuku.com/2329cc70e32d51c7.jpg', 'jpg', '480', '800', '1', '1', '2', '10', null, '2016-02-10', '系统管理员', '2016-02-10 01:31:16', '1', '2016-02-10 01:31:16', '1');
INSERT INTO `tb_image` VALUES ('10', '2', '美女', '美女5', 'http://i12.tietuku.com/f2229247cf39609c.jpg', '', null, 'http://i12.tietuku.com/f2229247cf39609c.jpg', 'jpg', '375', '550', '1', '1', '2', '10', null, '2016-02-10', '系统管理员', '2016-02-10 01:31:30', '1', '2016-02-10 01:31:30', '1');
INSERT INTO `tb_image` VALUES ('11', '2', '美女', '美女6', 'http://i12.tietuku.com/53cdf688c2c938f0.jpg', '', null, 'http://i12.tietuku.com/53cdf688c2c938f0.jpg', 'jpg', '610', '833', '1', '2', '2', '8', null, '2016-02-10', '系统管理员', '2016-02-10 01:31:45', '1', '2016-02-10 01:31:45', '1');
INSERT INTO `tb_image` VALUES ('12', '2', '美女', '美女7', 'http://i12.tietuku.com/9c83f4d0809adcbc.jpg', '', null, 'http://i12.tietuku.com/9c83f4d0809adcbc.jpg', 'jpg', '532', '600', '1', '1', '2', '10', null, '2016-02-10', '系统管理员', '2016-02-10 01:31:59', '1', '2016-02-10 01:31:59', '1');
INSERT INTO `tb_image` VALUES ('13', '2', '美女', '美女8', 'http://i4.tietuku.com/691d3f2a37fb0ba9.jpg', '', null, 'http://i4.tietuku.com/691d3f2a37fb0ba9.jpg', 'jpg', '1024', '1536', '1', '2', '2', '10', null, '2016-02-10', '系统管理员', '2016-02-10 01:33:40', '1', '2016-02-10 01:33:40', '1');
INSERT INTO `tb_image` VALUES ('14', '2', '美女', '美女9', 'http://i4.tietuku.com/1fd3bb6d9a0d5901.jpg', '', null, 'http://i4.tietuku.com/1fd3bb6d9a0d5901.jpg', 'jpg', '682', '1024', '1', '2', '2', '9', null, '2016-02-10', '系统管理员', '2016-02-10 01:34:45', '1', '2016-02-10 01:34:45', '1');
INSERT INTO `tb_image` VALUES ('15', '2', '美女', '美女10', 'http://i13.tietuku.com/bff63c26bea99e8d.jpg', '', null, 'http://i13.tietuku.com/bff63c26bea99e8d.jpg', 'jpg', '900', '1200', '1', '1', '2', '10', null, '2016-02-10', '系统管理员', '2016-02-10 01:35:16', '1', '2016-02-10 01:35:16', '1');
INSERT INTO `tb_image` VALUES ('16', '2', '美女', '美女11', 'http://i13.tietuku.com/8484c08a850a74c8.jpg', '', null, 'http://i13.tietuku.com/8484c08a850a74c8.jpg', 'jpg', '768', '1152', '1', '2', '2', '9', null, '2016-02-10', '系统管理员', '2016-02-10 01:35:55', '1', '2016-02-10 01:35:55', '1');
INSERT INTO `tb_image` VALUES ('17', '2', '美女', '美女12', 'http://i4.tietuku.com/4231f8da8b09331c.jpg', '', null, 'http://i4.tietuku.com/4231f8da8b09331c.jpg', 'jpg', '640', '960', '1', '2', '2', '8', null, '2016-02-10', '系统管理员', '2016-02-10 01:36:09', '1', '2016-02-10 01:36:09', '1');
INSERT INTO `tb_image` VALUES ('18', '2', '美女', '美女13', 'http://i13.tietuku.com/a5d2a6478c6ed758.jpg', '', null, 'http://i13.tietuku.com/a5d2a6478c6ed758.jpg', 'jpg', '496', '750', '1', '2', '2', '10', null, '2016-02-09', '系统管理员', '2016-02-10 01:36:29', '1', '2016-02-10 01:36:29', '1');
INSERT INTO `tb_image` VALUES ('19', '4', '游戏', '游戏2', 'http://i4.tietuku.com/c34fc8e188fd210c.jpg', '', null, 'http://i4.tietuku.com/c34fc8e188fd210c.jpg', 'jpg', '1920', '1080', '1', '1', '2', '10', null, '2016-02-11', '系统管理员', '2016-02-11 02:57:23', '1', '2016-02-11 02:57:23', '1');
INSERT INTO `tb_image` VALUES ('20', '4', '游戏', '游戏3', 'http://i8.tietuku.com/ca9d3311b35856b7.jpg', '', null, 'http://i8.tietuku.com/ca9d3311b35856b7.jpg', 'jpg', '658', '823', '1', '1', '2', '10', null, '2016-02-11', '系统管理员', '2016-02-11 02:58:35', '1', '2016-02-11 02:58:35', '1');
INSERT INTO `tb_image` VALUES ('21', '4', '游戏', '游戏5', 'http://i4.tietuku.com/a8ebfc61acef58ae.jpg', '', null, 'http://i4.tietuku.com/a8ebfc61acef58ae.jpg', 'jpg', '1600', '1200', '1', '2', '2', '10', null, '2016-02-11', '系统管理员', '2016-02-11 02:59:46', '1', '2016-02-11 02:59:46', '1');
INSERT INTO `tb_image` VALUES ('22', '4', '游戏', '游戏4', 'http://i4.tietuku.com/51c82af7824bac4c.jpg', '', null, 'http://i4.tietuku.com/51c82af7824bac4c.jpg', 'jpg', '1600', '1200', '1', '1', '2', '10', null, '2016-02-11', '系统管理员', '2016-02-11 03:00:11', '1', '2016-02-11 03:00:11', '1');
INSERT INTO `tb_image` VALUES ('23', '4', '游戏', '游戏6', 'http://i8.tietuku.com/cc4329e67481d4f0.png', '', null, 'http://i8.tietuku.com/cc4329e67481d4f0.png', 'png', '400', '580', '1', '1', '2', '10', null, '2016-02-11', '系统管理员', '2016-02-11 03:00:50', '1', '2016-02-11 03:00:50', '1');
INSERT INTO `tb_image` VALUES ('24', '4', '游戏', '游戏7', 'http://i8.tietuku.com/fa672748521bb33a.jpg', '', null, 'http://i8.tietuku.com/fa672748521bb33a.jpg', 'jpg', '600', '400', '1', '1', '2', '10', null, '2016-02-11', '系统管理员', '2016-02-11 03:01:30', '1', '2016-02-11 03:01:30', '1');
INSERT INTO `tb_image` VALUES ('25', '4', '游戏', '游戏8', 'http://i4.tietuku.com/7a22444c704bdb8c.jpg', '', null, 'http://i4.tietuku.com/7a22444c704bdb8c.jpg', 'jpg', '751', '1000', '1', '1', '2', '10', null, '2016-02-11', '系统管理员', '2016-02-11 03:04:52', '1', '2016-02-11 03:04:52', '1');
INSERT INTO `tb_image` VALUES ('26', '3', '动漫', '动漫2', 'http://i12.tietuku.com/c4b206990bea4877.jpg', '', null, 'http://i12.tietuku.com/c4b206990bea4877.jpg', 'jpg', '429', '600', '1', '1', '2', '10', null, '2016-02-11', '系统管理员', '2016-02-11 03:06:29', '1', '2016-02-11 03:06:29', '1');
INSERT INTO `tb_image` VALUES ('27', '3', '动漫', '动漫3', 'http://i11.tietuku.com/97ec25026f8526ea.jpg', '', null, 'http://i11.tietuku.com/97ec25026f8526ea.jpg', 'jpg', '188', '266', '1', '1', '2', '10', null, '2016-02-11', '系统管理员', '2016-02-11 03:06:48', '1', '2016-02-11 03:06:48', '1');
INSERT INTO `tb_image` VALUES ('28', '3', '动漫', '动漫4', 'http://i8.tietuku.com/99bed4ee4e00683b.jpg', '', null, 'http://i8.tietuku.com/99bed4ee4e00683b.jpg', 'jpg', '609', '800', '1', '1', '2', '10', null, '2016-02-11', '系统管理员', '2016-02-11 03:07:03', '1', '2016-02-11 03:07:03', '1');
INSERT INTO `tb_image` VALUES ('29', '3', '动漫', '动漫5', 'http://i4.tietuku.com/c18889b9f5fcb0f6.jpg', '', null, 'http://i4.tietuku.com/c18889b9f5fcb0f6.jpg', 'jpg', '637', '900', '1', '1', '2', '10', null, '2016-02-11', '系统管理员', '2016-02-11 03:07:59', '1', '2016-02-11 03:07:59', '1');
INSERT INTO `tb_image` VALUES ('30', '3', '动漫', '动漫6', 'http://i4.tietuku.com/e37d596427ae17f8.jpg', '', null, 'http://i4.tietuku.com/e37d596427ae17f8.jpg', 'jpg', '210', '280', '1', '1', '2', '10', null, '2016-02-11', '系统管理员', '2016-02-11 03:08:31', '1', '2016-02-11 03:08:31', '1');

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
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8 COMMENT='访问量统计';

-- ----------------------------
-- Records of tb_pageview
-- ----------------------------
INSERT INTO `tb_pageview` VALUES ('1', '127.0.0.1', '0', '2015-03-01', '2015-03-01 11:09:10');
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
) ENGINE=InnoDB AUTO_INCREMENT=175 DEFAULT CHARSET=utf8 COMMENT='标签';

-- ----------------------------
-- Records of tb_tags
-- ----------------------------
INSERT INTO `tb_tags` VALUES ('163', '324', '新闻', '2016-01-17 00:17:55', '1');
INSERT INTO `tb_tags` VALUES ('164', '323', '新闻', '2016-01-17 00:18:24', '1');
INSERT INTO `tb_tags` VALUES ('165', '322', '新闻', '2016-01-17 00:19:09', '1');
INSERT INTO `tb_tags` VALUES ('166', '321', '新闻', '2016-01-17 00:21:24', '1');
INSERT INTO `tb_tags` VALUES ('167', '320', '新闻', '2016-01-17 00:22:21', '1');
INSERT INTO `tb_tags` VALUES ('168', '319', '新闻', '2016-01-17 00:23:46', '1');
INSERT INTO `tb_tags` VALUES ('169', '318', '新闻', '2016-01-17 00:23:56', '1');
INSERT INTO `tb_tags` VALUES ('173', '105', '新闻', '2016-01-17 00:24:14', '1');
INSERT INTO `tb_tags` VALUES ('174', '111', '新闻', '2016-01-17 00:24:20', '1');
