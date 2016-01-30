/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50610
Source Host           : localhost:3306
Source Database       : jfinal_cms

Target Server Type    : MYSQL
Target Server Version : 50610
File Encoding         : 65001

Date: 2016-01-31 04:39:41
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
INSERT INTO `sys_dict_detail` VALUES ('101', 'systemParam', 'jfinal cms', '1', '1', null, null, null, null, '2015-01-30', '1');

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
) ENGINE=InnoDB AUTO_INCREMENT=10415 DEFAULT CHARSET=utf8 COMMENT='日志';

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
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COMMENT='菜单';

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
INSERT INTO `sys_user` VALUES ('1', 'admin', '1RHFCLt64uOOViCTzgSaww==', '系统管理员', '1', '1', '1', null, null, 'zcool321@sina.com', null, null, null, '时间是最好的老师，但遗憾的是——最后他把所有的学生都弄死了', 'flat-ui', '2016-06-06 06:06:06', '1');
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
) ENGINE=InnoDB AUTO_INCREMENT=408 DEFAULT CHARSET=utf8 COMMENT='文章';

-- ----------------------------
-- Records of tb_article
-- ----------------------------
INSERT INTO `tb_article` VALUES ('1', '1', '内容管理平台', '<p>内容管理平台</p>', '124', '123', '12', '2', '1', '1', '1', 'download/image_url/20150529_102007_298104.jpg', null, null, null, '2014-03-05', '系统管理员', '2015-01-29', '2015-01-23', '2015-01-28 17:29:55', '2015-01-28', '1');
INSERT INTO `tb_article` VALUES ('105', '2', '测试4', '<p><span style=\"white-space: normal;\">测试</span></p>', '20', '0', '11', '1', '1', '2', '10', null, null, null, null, '2015-04-30', '测试', null, null, '2015-01-28 17:48:26', '2015-01-28', '1');
INSERT INTO `tb_article` VALUES ('111', '2', '测试3', '<p><span style=\"white-space: normal;\">测试</span></p>', '27', '0', '11', '1', '1', '2', '10', null, null, null, null, '2015-02-04', '测试', null, null, '2015-02-04 08:47:31', '2015-02-04', '1');
INSERT INTO `tb_article` VALUES ('112', '2', 'S1线6号线西延 明年年底开通', '<p>&nbsp;&nbsp;S1线预计明年年底将与6号线西延一起开通，同时长安街西延工程预计2017年12月全线通车。未来门头沟也将成为距离中心城区最近、交通最便利的新城。这是门头沟区区长王洪钟今天上午做客北京城市广播“市民对话一把手”栏目时透露的。</p><p>　　<strong>S1线与六号线西延一起开通</strong></p><p>　　提到门头沟区的交通发展，王洪钟表示，S1线现在已经开工建设，将在明年年底和地铁6号线西延一起开通。据介绍，S1线磁悬浮铁路在门头沟设了6个站点，届时从门头沟石门营到苹果园仅需10分钟左右。</p><p>　　另外长安街延长线从去年下半年也已经开工建设，预计将在2017年建成。据介绍，西延工程中还将架设永定河跨河大桥。在S1线和长安街延长线两条交通线建设完成后，门头沟区和市区的交通会更加便捷，这两条线也将与阜石路二期以及莲石路到潭柘寺的108国道、未来即将修建的109高速公路等形成一个总的大交通体系。</p>', '224', '3', '11', '1', '1', '1', '9', 'download/image_url/20150525_233451_151572.jpg', null, null, null, '2015-04-20', '中新网', null, null, '2015-02-06 09:18:50', '2015-02-06', '1');
INSERT INTO `tb_article` VALUES ('206', '2', '测试2', '<p><span style=\"white-space: normal;\">测试</span></p>', '79', '0', '11', '1', '1', '2', '10', null, null, null, null, '2015-02-04', '测试', null, null, '2015-02-26 09:14:47', '2015-02-26', '1');
INSERT INTO `tb_article` VALUES ('212', '7', '各大音乐社区api接口（MP3&LRC）', '<p style=\"margin-top: 0px; margin-bottom: 8px; line-height: 26px; letter-spacing: 0.5px; font-size: 14px; white-space: normal; font-family: Arial;\"><strong>百度：</strong><br/>sound:<br/><a href=\"http://box.zhangmen.baidu.com/x?op=12&count=1&title=%B2%BB%B5%C3%B2%BB%B0%AE$$%C5%CB%E7%E2%B0%D8$$$$\" target=\"_blank\" rel=\"nofollow\" style=\"color: rgb(255, 131, 115); outline: 0px; font-size: 12px;\">http://box.zhangmen.baidu.com/x?op=12&amp;count=1&amp;title=不得不爱$$潘玮柏$$$$</a><br/>lrc:<br/><a href=\"http://box.zhangmen.baidu.com/bdlrc/86/8654.lrc\" target=\"_blank\" rel=\"nofollow\" style=\"color: rgb(255, 131, 115); outline: 0px; font-size: 12px;\">http://box.zhangmen.baidu.com/bdlrc/86/8654.lrc</a><br/>这个地址解析下:&nbsp;<br/>http://box.zhangmen.baidu.com/bdlrc/&nbsp;这个是百度lrc歌词存放地址,后面的496是一个的不定的,民就是说歌曲不同那个目录名也不同,它的算法是拿歌词文件名(也就是上面说的&nbsp;8654)&nbsp;除以一百,然后取小于等于其结果的最大整数,如上面的:8654/100&nbsp;=86.54取小于等于86.54&nbsp;的最大整数就是86,于是这首歌完整的歌词地址:<a href=\"http://box.zhangmen.baidu.com/bdlrc/86/8654.lrc\" target=\"_blank\" rel=\"nofollow\" style=\"color: rgb(255, 131, 115); outline: 0px; font-size: 12px;\">http://box.zhangmen.baidu.com/bdlrc/86/8654.lrc</a></p><p style=\"margin-top: 0px; margin-bottom: 8px; line-height: 17.142858505249px; letter-spacing: 0.5px; font-size: 12px; white-space: normal; color: rgb(61, 68, 80); font-family: &#39;Microsoft Yahei&#39;, Helvetica, Arial, sans-serif;\"><strong>QQ音乐：</strong><br/>sound:<br/><a href=\"http://qqmusic.qq.com/fcgi-bin/qm_getLyricId.fcg?name=%B2%BB%B5%C3%B2%BB%B0%AE&singer=%C5%CB%E7%E2%B0%D8&from=qqplayer\" target=\"_blank\" rel=\"nofollow\" style=\"color: rgb(255, 131, 115); outline: 0px;\">http://qqmusic.qq.com/fcgi-bin/qm_getLyricId.fcg?name=不得不爱&amp;singer=潘玮柏&amp;from=qqplayer</a><br/>lrc:<br/><a href=\"http://music.qq.com/miniportal/static/lyric/90/95690.xml\" target=\"_blank\" rel=\"nofollow\" style=\"color: rgb(255, 131, 115); outline: 0px;\">http://music.qq.com/miniportal/static/lyric/90/95690.xml</a></p><p style=\"margin-top: 0px; margin-bottom: 8px; line-height: 26px; letter-spacing: 0.5px; font-size: 14px; white-space: normal; font-family: Arial;\">&nbsp;</p><p style=\"margin-top: 0px; margin-bottom: 8px; line-height: 26px; letter-spacing: 0.5px; font-size: 14px; white-space: normal; font-family: Arial;\">说明：lrc的歌词地址&nbsp;lyric/90/95690.xm 其中的90是上面sound中出来的id95690的最后面两位数字、。<br/>&nbsp;<br/><strong>搜搜音乐：</strong><br/>sound:<br/><a href=\"http://cgi.music.soso.com/fcgi-bin/m.q?w=%B2%BB%B5%C3%B2%BB%B0%AE&p=1&t=0\" target=\"_blank\" rel=\"nofollow\" style=\"color: rgb(255, 131, 115); outline: 0px; font-size: 12px;\">http://cgi.music.soso.com/fcgi-bin/m.q?w=<span style=\"color: rgb(0, 0, 0);\">不得不爱</span>&amp;p=1&amp;t=0</a><br/>格式:&nbsp;<span style=\"color: rgb(192, 0, 0);\">1</span>-全部&nbsp;<span style=\"color: rgb(192, 0, 0);\">0</span>-MP3&nbsp;<span style=\"color: rgb(192, 0, 0);\">1</span>-RM&nbsp;，<span style=\"color: rgb(192, 0, 0);\">2</span>-WMA<br/>lrc:(注意！歌曲名和歌手名字一定要准确)<br/><a href=\"http://cgi.music.soso.com/fcgi-bin/fcg_download_lrc.q?song=%B2%BB%B5%C3%B2%BB%B0%AE&singer=%C5%CB%E7%E2%B0%D8%26%CF%D2%D7%D3&down=1\" target=\"_blank\" rel=\"nofollow\" style=\"color: rgb(255, 131, 115); outline: 0px; font-size: 12px;\">http://cgi.music.soso.com/fcgi-bin/fcg_download_lrc.q?song=不得不爱&amp;singer=潘玮柏%26弦子&amp;down=1</a></p><p style=\"margin-top: 0px; margin-bottom: 8px; line-height: 17.142858505249px; letter-spacing: 0.5px; font-size: 12px; white-space: normal; color: rgb(61, 68, 80); font-family: &#39;Microsoft Yahei&#39;, Helvetica, Arial, sans-serif;\">&nbsp;<br/><strong>8BOX</strong><br/>sound:<br/><a href=\"http://api.8box.com/get/search/song?api_key=%7Bapi_key%7D&title=%7B%B8%E8%C7%FA%C3%FB%7D&artist=%7B%B8%E8%CA%D6%7D\" target=\"_blank\" rel=\"nofollow\" style=\"color: rgb(255, 131, 115); outline: 0px;\">http://api.8box.com/get/search/song?api_key={api_key}&amp;title={歌曲名<span style=\"color: rgb(0, 0, 0);\">（URL16进制加密）</span>}&amp;artist={歌手<span style=\"color: rgb(0, 0, 0);\">（URL16进制加密）</span>}</a><br/><span style=\"font-family: 宋体;\">&nbsp;&nbsp;&nbsp;&nbsp;</span><br/>&nbsp;lrc:(2010年8月19日更新)<br/><a href=\"http://www.8box.com/feed/radio/s?type=widget&param\" target=\"_blank\" rel=\"nofollow\" style=\"color: rgb(255, 131, 115); outline: 0px;\">http://www.8box.com/feed/radio/s?type=widget&amp;param</a>={歌曲ID(数字)}<br/><a href=\"http://www.8box.com/pictures/lyrics/83/591383.lrc\" target=\"_blank\" rel=\"nofollow\" style=\"color: rgb(255, 131, 115); outline: 0px;\">http://www.8box.com/pictures/lyrics/83/591383.lrc</a></p><p><br/></p>', '2', '0', '11', '1', '1', '2', '10', '', null, null, null, '2015-03-13', '系统管理员', '', '', '2015-03-13 09:26:25', '2015-03-13', '1');
INSERT INTO `tb_article` VALUES ('314', '13', '爨底下', '<p>http://www.jflyfox.com/mtg/front/article/330.html</p>', '2', '0', '11', '1', '1', '2', '10', 'download/image_url/20150524_165122_812162.jpg', 'http://i1.tietuku.com/35171f11a5ec9c51.jpg', null, null, '2015-05-24', '系统管理员', '', '', '2015-05-24 16:50:26', '2015-05-24 16:50:26', '1');
INSERT INTO `tb_article` VALUES ('315', '13', '永定塔', '<p>http://www.jflyfox.com/mtg/front/article/406.html</p>', '2', '0', '11', '1', '1', '2', '10', 'download/image_url/20150524_165116_706479.jpg', 'http://i1.tietuku.com/fab40b501ece3fcf.jpg', null, null, '2015-05-24', '系统管理员', '', '', '2015-05-24 16:50:34', '2015-05-24 16:50:34', '1');
INSERT INTO `tb_article` VALUES ('316', '13', '美丽门城', '<p>#</p>', '1', '0', '11', '1', '1', '2', '10', 'download/image_url/20150524_165111_42992.jpg', 'http://i1.tietuku.com/6f139452bedaefed.jpg', null, null, '2015-05-24', '系统管理员', '', '', '2015-05-24 16:50:42', '2015-05-24 16:50:42', '1');
INSERT INTO `tb_article` VALUES ('317', '13', '百花山', '<p>http://www.jflyfox.com/mtg/front/article/329.html</p>', '6', '0', '11', '1', '1', '2', '10', 'download/image_url/20150524_165105_854676.jpg', 'http://i1.tietuku.com/3951e9cb262621b6.jpg', null, null, '2015-05-24', '系统管理员', '', '', '2015-05-24 23:36:47', '2015-05-24 23:36:47', '1');
INSERT INTO `tb_article` VALUES ('318', '2', '测试1', '<p>测试</p>', '1', '0', '11', '1', '1', '2', '10', null, null, null, null, '2015-05-06', '测试', null, null, '2015-05-24 22:17:25', '2015-05-24 22:17:25', '1');
INSERT INTO `tb_article` VALUES ('319', '2', '红枣银耳汤', '<p style=\"white-space: normal; margin-top: 0px; margin-bottom: 0px; padding: 10px 20px 10px 100px; border: 0px; outline: 0px; font-size: 14px; vertical-align: baseline; line-height: 24px; color: rgb(102, 102, 102); position: relative; font-family: Arial, Helvetica, sans-serif; background-image: initial; background-attachment: initial; background-color: rgb(252, 248, 233); background-size: initial; background-origin: initial; background-clip: initial; background-position: initial; background-repeat: initial;\"><em class=\"step\" style=\"border: 0px; outline: 0px; font-size: 50px; vertical-align: baseline; color: rgb(255, 50, 50); text-align: center; font-family: arial; position: absolute; left: 0px; top: -6px; height: 100px; width: 100px; display: block; line-height: 52px; background: transparent;\">1.</em>银耳放入开水中浸泡20分钟，泡发后取出洗净，并去除黄根，掰成小朵；红枣洗净去核，枸杞放入清水中浸软备用</p><p style=\"white-space: normal; margin-top: 0px; margin-bottom: 0px; padding: 10px 20px 10px 100px; border: 0px; outline: 0px; font-size: 14px; vertical-align: baseline; line-height: 24px; color: rgb(102, 102, 102); position: relative; font-family: Arial, Helvetica, sans-serif; background-image: initial; background-attachment: initial; background-color: rgb(252, 248, 233); background-size: initial; background-origin: initial; background-clip: initial; background-position: initial; background-repeat: initial;\"><em class=\"step\" style=\"border: 0px; outline: 0px; font-size: 50px; vertical-align: baseline; color: rgb(255, 50, 50); text-align: center; font-family: arial; position: absolute; left: 0px; top: -6px; height: 100px; width: 100px; display: block; line-height: 52px; background: transparent;\">2.</em>汤锅中倒入适量清水，大火烧开后，转小火放入银耳熬煮30分钟，待汤汁变得黏稠后，放入红枣、枸杞继续熬煮10分钟</p><p style=\"white-space: normal; margin-top: 0px; margin-bottom: 0px; padding: 10px 20px 10px 100px; border: 0px; outline: 0px; font-size: 14px; vertical-align: baseline; line-height: 24px; color: rgb(102, 102, 102); position: relative; font-family: Arial, Helvetica, sans-serif; background-image: initial; background-attachment: initial; background-color: rgb(252, 248, 233); background-size: initial; background-origin: initial; background-clip: initial; background-position: initial; background-repeat: initial;\"><em class=\"step\" style=\"border: 0px; outline: 0px; font-size: 50px; vertical-align: baseline; color: rgb(255, 50, 50); text-align: center; font-family: arial; position: absolute; left: 0px; top: -6px; height: 100px; width: 100px; display: block; line-height: 52px; background: transparent;\">3.</em>10分钟后，将黄冰糖放入锅中，搅拌至冰糖融化即可</p><p style=\"white-space: normal; margin-top: 0px; margin-bottom: 0px; padding: 10px 20px 10px 100px; border: 0px; outline: 0px; font-size: 14px; vertical-align: baseline; line-height: 24px; color: rgb(102, 102, 102); position: relative; font-family: Arial, Helvetica, sans-serif; background-image: initial; background-attachment: initial; background-color: rgb(252, 248, 233); background-size: initial; background-origin: initial; background-clip: initial; background-position: initial; background-repeat: initial;\"><img class=\"conimg\" height=\"690\" width=\"460\" alt=\"红枣银耳汤Hs.jpg\" src=\"http://images.meishij.net/p/20110714/a691de05714c9bb940791709d67e1e53.jpg\" style=\"margin-right: auto; margin-left: auto; outline: 0px; vertical-align: baseline; display: block; max-width: 550px; background: transparent;\"/></p><p style=\"white-space: normal; margin-top: 0px; margin-bottom: 0px; padding: 10px 20px 10px 100px; border: 0px; outline: 0px; font-size: 14px; vertical-align: baseline; line-height: 24px; color: rgb(102, 102, 102); position: relative; font-family: Arial, Helvetica, sans-serif; background-image: initial; background-attachment: initial; background-color: rgb(252, 248, 233); background-size: initial; background-origin: initial; background-clip: initial; background-position: initial; background-repeat: initial;\"></p><h2 class=\"cpc_h2\" style=\"white-space: normal; margin-top: 0px; margin-bottom: 0px; border-width: 0px 0px 1px; border-bottom-style: solid; border-bottom-color: rgb(238, 238, 238); outline: 0px; font-size: 24px; vertical-align: baseline; color: rgb(51, 51, 51); height: 66px; line-height: 66px; text-indent: 24px; font-family: &#39;Microsoft Yahei&#39;; background-image: initial; background-attachment: initial; background-color: rgb(252, 248, 233); background-size: initial; background-origin: initial; background-clip: initial; background-position: initial; background-repeat: initial;\">厨房小常识</h2><p style=\"white-space: normal; margin-top: 0px; margin-bottom: 0px; padding: 10px 20px 10px 100px; border: 0px; outline: 0px; font-size: 14px; vertical-align: baseline; line-height: 24px; color: rgb(102, 102, 102); position: relative; font-family: Arial, Helvetica, sans-serif; background-image: initial; background-attachment: initial; background-color: rgb(252, 248, 233); background-size: initial; background-origin: initial; background-clip: initial; background-position: initial; background-repeat: initial;\"></p><p style=\"white-space: normal; margin-top: 0px; margin-bottom: 0px; padding: 10px 20px 10px 100px; border: 0px; outline: 0px; font-size: 14px; vertical-align: baseline; line-height: 24px; color: rgb(102, 102, 102); position: relative; font-family: Arial, Helvetica, sans-serif; background-image: initial; background-attachment: initial; background-color: rgb(252, 248, 233); background-size: initial; background-origin: initial; background-clip: initial; background-position: initial; background-repeat: initial;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 质量较好的银耳呈淡黄色，水发后手感柔嫩，劣质银耳水发后较脆。另外不要购买太大朵的银耳，大朵银耳的黄根很大，清理时要去掉的部分就很多，小朵的银耳相对要好些。</p><p style=\"white-space: normal; margin-top: 0px; margin-bottom: 0px; padding: 10px 20px 10px 100px; border: 0px; outline: 0px; font-size: 14px; vertical-align: baseline; line-height: 24px; color: rgb(102, 102, 102); position: relative; font-family: Arial, Helvetica, sans-serif; background-image: initial; background-attachment: initial; background-color: rgb(252, 248, 233); background-size: initial; background-origin: initial; background-clip: initial; background-position: initial; background-repeat: initial;\"></p><h2 class=\"cpc_h2\" style=\"white-space: normal; margin-top: 0px; margin-bottom: 0px; border-width: 0px 0px 1px; border-bottom-style: solid; border-bottom-color: rgb(238, 238, 238); outline: 0px; font-size: 24px; vertical-align: baseline; color: rgb(51, 51, 51); height: 66px; line-height: 66px; text-indent: 24px; font-family: &#39;Microsoft Yahei&#39;; background-image: initial; background-attachment: initial; background-color: rgb(252, 248, 233); background-size: initial; background-origin: initial; background-clip: initial; background-position: initial; background-repeat: initial;\">营养功效</h2><p style=\"white-space: normal; margin-top: 0px; margin-bottom: 0px; padding: 10px 20px 10px 100px; border: 0px; outline: 0px; font-size: 14px; vertical-align: baseline; line-height: 24px; color: rgb(102, 102, 102); position: relative; font-family: Arial, Helvetica, sans-serif; background-image: initial; background-attachment: initial; background-color: rgb(252, 248, 233); background-size: initial; background-origin: initial; background-clip: initial; background-position: initial; background-repeat: initial;\"></p><p style=\"white-space: normal; margin-top: 0px; margin-bottom: 0px; padding: 10px 20px 10px 100px; border: 0px; outline: 0px; font-size: 14px; vertical-align: baseline; line-height: 24px; color: rgb(102, 102, 102); position: relative; font-family: Arial, Helvetica, sans-serif; background-image: initial; background-attachment: initial; background-color: rgb(252, 248, 233); background-size: initial; background-origin: initial; background-clip: initial; background-position: initial; background-repeat: initial;\"><strong style=\"padding: 10px 20px; border: 0px; outline: 0px; vertical-align: baseline; background: transparent;\">好汤益容颜：</strong></p><p style=\"white-space: normal; margin-top: 0px; margin-bottom: 0px; padding: 10px 20px 10px 100px; border: 0px; outline: 0px; font-size: 14px; vertical-align: baseline; line-height: 24px; color: rgb(102, 102, 102); position: relative; font-family: Arial, Helvetica, sans-serif; background-image: initial; background-attachment: initial; background-color: rgb(252, 248, 233); background-size: initial; background-origin: initial; background-clip: initial; background-position: initial; background-repeat: initial;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 银耳是极好的美容品，其含有天然植物性胶质，长期食用可滋润肌肤，更有减轻面部黄褐斑、雀斑的功效。银耳汤营养丰富，但是不能隔夜饮用，所以，一定要饮用新鲜、美味的银耳汤。除此之外，银耳中还含有大量的维生素D，可有效地防止人体钙元素的流失，对人体骨骼也很有好处。</p><p style=\"white-space: normal; margin-top: 0px; margin-bottom: 0px; padding: 10px 20px 10px 100px; border: 0px; outline: 0px; font-size: 14px; vertical-align: baseline; line-height: 24px; color: rgb(102, 102, 102); position: relative; font-family: Arial, Helvetica, sans-serif; background-image: initial; background-attachment: initial; background-color: rgb(252, 248, 233); background-size: initial; background-origin: initial; background-clip: initial; background-position: initial; background-repeat: initial;\"></p><h2 class=\"cpc_h2\" style=\"white-space: normal; margin-top: 0px; margin-bottom: 0px; border-width: 0px 0px 1px; border-bottom-style: solid; border-bottom-color: rgb(238, 238, 238); outline: 0px; font-size: 24px; vertical-align: baseline; color: rgb(51, 51, 51); height: 66px; line-height: 66px; text-indent: 24px; font-family: &#39;Microsoft Yahei&#39;; background-image: initial; background-attachment: initial; background-color: rgb(252, 248, 233); background-size: initial; background-origin: initial; background-clip: initial; background-position: initial; background-repeat: initial;\">饮食小常识</h2><p style=\"white-space: normal; margin-top: 0px; margin-bottom: 0px; padding: 10px 20px 10px 100px; border: 0px; outline: 0px; font-size: 14px; vertical-align: baseline; line-height: 24px; color: rgb(102, 102, 102); position: relative; font-family: Arial, Helvetica, sans-serif; background-image: initial; background-attachment: initial; background-color: rgb(252, 248, 233); background-size: initial; background-origin: initial; background-clip: initial; background-position: initial; background-repeat: initial;\"></p><p style=\"white-space: normal; margin-top: 0px; margin-bottom: 0px; padding: 10px 20px 10px 100px; border: 0px; outline: 0px; font-size: 14px; vertical-align: baseline; line-height: 24px; color: rgb(102, 102, 102); position: relative; font-family: Arial, Helvetica, sans-serif; background-image: initial; background-attachment: initial; background-color: rgb(252, 248, 233); background-size: initial; background-origin: initial; background-clip: initial; background-position: initial; background-repeat: initial;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;女人爱美丽，而日复一日忙碌于工作、家庭之中的女人，历经了岁月的洗礼，不经意间失去了一些花容月色，美丽元素的消耗也在与日俱增。纵然女人是水做的，也少不了一碗好汤给女人带来的温情与呵护。那就大胆运用每一个养颜公式，将美丽定格于此时此刻吧——从现在开始，时常为自己、为家人精心熬煮一碗养颜好汤，让容颜不再跟随着时光的脚步匆匆流逝——多一份眷顾，多一份妙色……</p><p><br/></p>', '1', '0', '11', '1', '1', '2', '10', null, null, null, null, '2015-05-08', '系统管理员', null, null, '2015-05-24 22:17:31', '2015-05-24 22:17:31', '1');
INSERT INTO `tb_article` VALUES ('320', '2', '三种人', '<p><span style=\"background-color: rgb(255, 255, 255);\"><span style=\"white-space: normal; letter-spacing: 0.5px; font-family: SimSun, Verdana, Arial, Helvetica, sans-serif; font-size: 14px; line-height: 21px;\">人的一生总是要学习各种知识。人人都在学习，但学习方法却各有不同。</span><br style=\"white-space: normal; font-family: 微软雅黑, Verdana, sans-serif, 宋体; font-size: 13px; letter-spacing: 0.5px; line-height: 22.5px;\"/><span style=\"white-space: normal; letter-spacing: 0.5px; font-family: SimSun, Verdana, Arial, Helvetica, sans-serif; font-size: 14px; line-height: 21px;\">1 绝大多数人是闭门学习受填鸭式学习教育遗毒影响，独自一个人挑灯苦灯，学习吸收各种知识。</span><br style=\"white-space: normal; font-family: 微软雅黑, Verdana, sans-serif, 宋体; font-size: 13px; letter-spacing: 0.5px; line-height: 22.5px;\"/><span style=\"white-space: normal; letter-spacing: 0.5px; font-family: SimSun, Verdana, Arial, Helvetica, sans-serif; font-size: 14px; line-height: 21px;\">这种学习方式比较枯燥，需要比较好的耐力，毅力。</span><br style=\"white-space: normal; font-family: 微软雅黑, Verdana, sans-serif, 宋体; font-size: 13px; letter-spacing: 0.5px; line-height: 22.5px;\"/><span style=\"white-space: normal; letter-spacing: 0.5px; font-family: SimSun, Verdana, Arial, Helvetica, sans-serif; font-size: 14px; line-height: 21px;\">2 一部分重视通过交流学习现在网络比较发达了，许多人自觉不自觉得把自己的学习心得记录下来，并发表在网上。</span><br style=\"white-space: normal; font-family: 微软雅黑, Verdana, sans-serif, 宋体; font-size: 13px; letter-spacing: 0.5px; line-height: 22.5px;\"/><span style=\"white-space: normal; letter-spacing: 0.5px; font-family: SimSun, Verdana, Arial, Helvetica, sans-serif; font-size: 14px; line-height: 21px;\">3 也有人通过启发别人来启发自己其中的哲学原理，按古代说法是天人合一，物我同体；按现代说法是信息相通，沟通无极限。</span><br style=\"white-space: normal; font-family: 微软雅黑, Verdana, sans-serif, 宋体; font-size: 13px; letter-spacing: 0.5px; line-height: 22.5px;\"/><br style=\"white-space: normal; font-family: 微软雅黑, Verdana, sans-serif, 宋体; font-size: 13px; letter-spacing: 0.5px; line-height: 22.5px;\"/><span style=\"white-space: normal; letter-spacing: 0.5px; font-family: SimSun, Verdana, Arial, Helvetica, sans-serif; font-size: 14px; line-height: 21px;\">通过帮助别人来帮助自己。通过启发别人来启发自己。凡有所得，必须分享。只有分享了的知识，才是真正的知识。</span><br style=\"white-space: normal; font-family: 微软雅黑, Verdana, sans-serif, 宋体; font-size: 13px; letter-spacing: 0.5px; line-height: 22.5px;\"/><br style=\"white-space: normal; font-family: 微软雅黑, Verdana, sans-serif, 宋体; font-size: 13px; letter-spacing: 0.5px; line-height: 22.5px;\"/><span style=\"white-space: normal; letter-spacing: 0.5px; font-family: SimSun, Verdana, Arial, Helvetica, sans-serif; font-size: 14px; line-height: 21px;\">上面三种人，对于知识而言，</span><br style=\"white-space: normal; font-family: 微软雅黑, Verdana, sans-serif, 宋体; font-size: 13px; letter-spacing: 0.5px; line-height: 22.5px;\"/><span style=\"white-space: normal; letter-spacing: 0.5px; font-family: SimSun, Verdana, Arial, Helvetica, sans-serif; font-size: 14px; line-height: 21px;\">第一种人是只进不出，只是获取别人总结的知识，而基本不去帮助别人获取知识</span><br style=\"white-space: normal; font-family: 微软雅黑, Verdana, sans-serif, 宋体; font-size: 13px; letter-spacing: 0.5px; line-height: 22.5px;\"/><span style=\"white-space: normal; letter-spacing: 0.5px; font-family: SimSun, Verdana, Arial, Helvetica, sans-serif; font-size: 14px; line-height: 21px;\">第二种人是多进少出，在学习知识的同时，也把自己的一些心得分享出来</span><br style=\"white-space: normal; font-family: 微软雅黑, Verdana, sans-serif, 宋体; font-size: 13px; letter-spacing: 0.5px; line-height: 22.5px;\"/><span style=\"white-space: normal; letter-spacing: 0.5px; font-family: SimSun, Verdana, Arial, Helvetica, sans-serif; font-size: 14px; line-height: 21px;\">第三种人趋于得失平衡，认为只有分享的知识才是真正的知识，只有帮助别人也掌握的知识才是真正的知识</span></span></p>', '5', '0', '11', '1', '1', '2', '10', null, null, null, null, '2015-05-10', '三种人', null, null, '2015-05-25 22:17:38', '2015-05-25 22:17:38', '1');
INSERT INTO `tb_article` VALUES ('321', '2', 'J2Cache', '<p><span style=\"font-family: &#39;Microsoft YaHei&#39;, Verdana, sans-serif, 宋体; font-size: 13.3333330154419px; line-height: 21.3333320617676px; white-space: normal;\">J2Cache 是 OSChina 目前正在使用的两级缓存框架。第一级缓存使用&nbsp;</span><a target=\"_blank\" href=\"http://www.oschina.net/p/ehcache\" style=\"margin: 0px; padding: 0px; color: rgb(62, 98, 166); outline: 0px; font-family: &#39;Microsoft YaHei&#39;, Verdana, sans-serif, 宋体; font-size: 13.3333330154419px; line-height: 21.3333320617676px; white-space: normal;\">Ehcache</a><span style=\"font-family: &#39;Microsoft YaHei&#39;, Verdana, sans-serif, 宋体; font-size: 13.3333330154419px; line-height: 21.3333320617676px; white-space: normal;\">，第二级缓存使用&nbsp;</span><a target=\"_blank\" href=\"http://www.oschina.net/p/redis\" style=\"margin: 0px; padding: 0px; color: rgb(62, 98, 166); outline: 0px; font-family: &#39;Microsoft YaHei&#39;, Verdana, sans-serif, 宋体; font-size: 13.3333330154419px; line-height: 21.3333320617676px; white-space: normal;\">Redis</a><span style=\"font-family: &#39;Microsoft YaHei&#39;, Verdana, sans-serif, 宋体; font-size: 13.3333330154419px; line-height: 21.3333320617676px; white-space: normal;\">&nbsp;。由于大量的缓存读取会导致 L2 的网络成为整个系统的瓶颈，因此 L1 的目标是降低对 L2 的读取次数。该缓存框架主要用于集群环境中。单机也可使用，用于避免应用重启导致的 Ehcache 缓存数据丢失。</span></p>', '25', '0', '11', '1', '1', '2', '10', null, null, null, null, '2015-05-23', '红薯', null, null, '2015-05-24 22:17:47', '2015-05-24 22:17:47', '1');
INSERT INTO `tb_article` VALUES ('322', '2', 'beetl', '<p><span style=\"color: rgb(51, 51, 51); font-family: arial, 宋体, sans-serif; line-height: 24px; text-indent: 32px; white-space: normal;\">Beetl,是Bee Template Language的缩写，它绝不是简单的另外一种模板引擎，而是新一代的</span><a target=\"_blank\" href=\"http://baike.baidu.com/view/4258079.htm\" style=\"color: rgb(19, 110, 194); text-decoration: none; font-family: arial, 宋体, sans-serif; line-height: 24px; text-indent: 32px; white-space: normal;\">模板引擎</a><span style=\"color: rgb(51, 51, 51); font-family: arial, 宋体, sans-serif; line-height: 24px; text-indent: 32px; white-space: normal;\">，它功能强大，性能良好，秒杀当前流行的模板引擎。而且还易学易用。</span></p>', '2', '0', '11', '1', '1', '1', '10', null, null, null, null, '2015-05-24', '系统管理员', null, null, '2015-05-24 22:17:53', '2015-05-24 22:17:53', '1');
INSERT INTO `tb_article` VALUES ('323', '2', 'Jfinal', '<p><span style=\"color: rgb(51, 51, 51); font-family: &#39;Lucida Grande&#39;, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, &#39;WenQuanYi Micro Hei&#39;, sans-serif; font-size: 13px; line-height: 23.3999996185303px; white-space: normal;\">JFinal 是基于 Java 语言的极速 WEB + ORM 框架，其核心设计目标是开发迅速、代码量少、学习简单、功能强大、轻量级、易扩展、Restful。 在拥有Java语言所有优势的同时再拥有ruby、python、php等动态语言的开发效率！为您节约更多时间，去陪恋人、家人和朋友 :)</span></p>', '7', '0', '11', '1', '1', '2', '10', null, null, null, null, '2015-05-26', '系统管理员', null, null, '2015-05-24 22:17:59', '2015-05-24 22:17:59', '1');
INSERT INTO `tb_article` VALUES ('324', '2', 'OsChina', '<p><span style=\"color: rgb(51, 51, 51); font-family: arial; font-size: 13px; line-height: 20.0200004577637px; white-space: normal;\">开源中国 www.</span><span style=\"color: rgb(204, 0, 0); font-family: arial; font-size: 13px; line-height: 20.0200004577637px; white-space: normal;\">oschina</span><span style=\"color: rgb(51, 51, 51); font-family: arial; font-size: 13px; line-height: 20.0200004577637px; white-space: normal;\">.net 是目前中国最大的开源技术社区。我们传播开源的理念,推广开源项目,为 IT 开发者提供了一个发现、使用、并交流开源技术的平台。</span></p>', '15', '1', '11', '1', '1', '2', '10', null, null, null, null, '2015-05-19', '系统管理员', null, null, '2015-05-24 22:18:07', '2015-05-24 22:18:07', '1');
INSERT INTO `tb_article` VALUES ('325', '4', '定都阁', null, '1', '0', '11', '1', '1', '2', '10', 'download/image_url/20150524_224542_933745.jpg', null, null, null, '2015-05-24', '系统管理员', null, null, '2015-05-24 22:45:42', '2015-05-24 22:45:42', '1');
INSERT INTO `tb_article` VALUES ('326', '4', '戒台寺', null, '1', '0', '11', '1', '1', '2', '10', 'download/image_url/20150524_224606_653614.jpg', null, null, null, '2015-05-24', '系统管理员', null, null, '2015-05-24 22:46:06', '2015-05-24 22:46:06', '1');
INSERT INTO `tb_article` VALUES ('327', '4', '潭柘寺', null, '1', '0', '11', '1', '1', '2', '10', 'download/image_url/20150524_224618_396989.jpg', null, null, null, '2015-05-24', '系统管理员', null, null, '2015-05-24 22:46:18', '2015-05-24 22:46:18', '1');
INSERT INTO `tb_article` VALUES ('328', '4', '双龙峡', '<p><span>双龙峡自然风景区是京西新开发的一个风景区，位于门头沟区斋堂镇火村南2.5公里的青山翠谷中。双龙峡自然风景区主体形象概括为六句话应该是：十里溪流 百潭瀑布 千亩红杏 万顷林海 青山翠谷 世外桃源 苍山如海，峰峦巍峨俊秀，被喻为“小九寨”、“百瀑布”。</span></p><p><span><br/></span></p><p><span>可以欣赏的主要景点有：玉龙湖、清幽湖、坐听双琴、仙女湾、第一瀑布（高29.7米、宽5米）、千蛙 蜂、七音瀑、双龙入水、小九寨沟、琴玉潭、三百年野生猕猴桃、仙女湾、双龙戏龟、青蛙石、二百年野生猕猴桃仙聚柳、送仙松、第二瀑布（高30米、宽10米）、原始森林。第一 瀑布至第二瀑布山谷内，沿溪流、山径，藤蔓植物与灌木、乔木纠缠盘结，形成约五公里长的天然植物走廊，郁郁葱葱，号为“藤萝谷”，为双龙峡一大奇观。景区概括为：“十里溪流、百潭瀑布、千亩红杏、万顷林海。”真可谓青山翠谷，峰峦巍峨俊秀。到了夏季山花遍地，溪水潺潺好一派北国秀色，所以又有“小九寨”之称。</span></p><p><span><br/></span></p><p><span><strong>第一瀑布（日月潭）</strong></span></p><p><span>双龙峡 （原名水湖子）：在京西的景点中，还是第一次发现这样大瀑布，落差是 35 米，瀑宽 5 米。是名副其实的“京西第一瀑”。在远处看，像银河落地，如山风摧树，倒近处看到的是圆圆的太阳潭和半边下弦月，所以称之“北国日月潭”。还有一条绿色长龙，隐于潭壁上。似要出水之状，并且有曾经出水时走过的脚印。</span></p><p><span><br/></span></p><p><span><strong>青蛙石</strong></span></p><p><span>双龙峡 牛公山、牛蛙山－话说此地有一个泉眼，千万蛙靠此生存，牛公山在此 放牧，牛马千头几乎天天在此集饮。忽然，一年夜间大雨从天而降，洪水顺山而流，眼看山中万物，顷刻间即殁 , 就连都会洗劫一空。恰逢卯日星君知晓，站在其山岭上猛叫三声，把牛群惊动，牛群用身体筑起防护墙，保护所有生灵跳散，青蛙逃上山坡了望，牛公撇下牛群逃到半坡之上幸免遇难。牛救了全部生灵牺牲了自己。所以自今上面为金鸡岭，下面为牛公坡。东坡上的每一个山包，都是一个青蛙头在睁大眼睛目视谷中。</span></p><p><span>谷中稀有植物上百种。有二级保护植物 200 年生野生猕猴桃，盘缠其间，摘一颗清喉醒脑 。碗口粗的野葡萄、灵绡树不分 你我的缠在一起，不情愿的给行人腾出了一条绿色胡同。不时葡萄株能伸到你的口边。</span></p><p><span><br/></span></p><p><span><strong>双龙峡</strong></span></p><p><span>火村为门头沟区斋堂镇辖村。位于镇域东部，东南距区政府驻地 33 公里，西距镇驻地2公里，北邻高铺（新）村 1.5 公里，村域面积 14.56 平方公里。火村地势自南向北渐低，土壤为山地淋溶褐土。植被为低山（海拔 400 米）旱生山杏、荆条灌丛和人工林。</span></p><p><span>村域有南山溪流，注入清水河。东山有泉水供民饮用，村中地下水埋深 10 余米。村民多以农副业为计，种植玉米、谷子等作物 760 亩，年产粮 6.8 万公斤；栽植核桃、杏等干鲜果树，果品年产量 46.9 吨 . 火树红杏果质优异 , 闻名京西 . 火村在抗日战争期间 , 建立抗日革命根据地 , 与侵华日寇浴血奋战 , 先后被日寇烧村七次。</span></p><p><span><br/></span></p><p><span></span></p><p><img src=\"http://d.hiphotos.baidu.com/baike/c0%3Dbaike92%2C5%2C5%2C92%2C30/sign=09cc2953a8d3fd1f2204aa6851274e7a/9f2f070828381f30626337d4a9014c086e06f051.jpg\" _src=\"http://d.hiphotos.baidu.com/baike/c0%3Dbaike92%2C5%2C5%2C92%2C30/sign=09cc2953a8d3fd1f2204aa6851274e7a/9f2f070828381f30626337d4a9014c086e06f051.jpg\" style=\"\"/></p><p><img src=\"http://b.hiphotos.baidu.com/baike/c0%3Dbaike92%2C5%2C5%2C92%2C30/sign=507aff823bc79f3d9becec62dbc8a674/63d9f2d3572c11df30ab055e632762d0f703c251.jpg\" _src=\"http://b.hiphotos.baidu.com/baike/c0%3Dbaike92%2C5%2C5%2C92%2C30/sign=507aff823bc79f3d9becec62dbc8a674/63d9f2d3572c11df30ab055e632762d0f703c251.jpg\" style=\"\"/></p><p><span><br/></span><br/></p><p><span><br/></span></p><p><span>地址：北京市门头沟区斋堂镇火村双龙峡景区&nbsp;</span></p><p><span>电话：(010)69819310</span></p><p><br/></p>', '3', '0', '11', '1', '1', '2', '10', 'download/image_url/20150524_224630_442929.jpg', null, null, null, '2015-05-24', '系统管理员', null, null, '2015-05-24 22:46:30', '2015-05-24 22:46:30', '1');
INSERT INTO `tb_article` VALUES ('329', '4', '百花山', '<p><span style=\"white-space: nowrap;\">百花山国家级自然保护区地处北京西部，位于北京市门头沟区清水镇境内，2008年经国务院批准在北京百花山自然保护区基础上晋升为国家级自然保护区。</span></p><p><span style=\"white-space: nowrap;\">保护区是以保护暖温带华北石质山地次生落叶阔叶林生态系统为主的自然保护区，总面积为21743.1公顷。</span></p><p><span style=\"white-space: nowrap;\"><img src=\"http://img1.qunarzz.com/sight/p0/1410/de/4eb87a813329d1d568ed5c88bca99659.water.jpg_1190x550_16e25e47.jpg\" _src=\"http://img1.qunarzz.com/sight/p0/1410/de/4eb87a813329d1d568ed5c88bca99659.water.jpg_1190x550_16e25e47.jpg\"/></span></p><p><span style=\"white-space: nowrap;\"><img src=\"http://img1.qunarzz.com/sight/p0/201403/07/8dce2e3d9fc87b4e5e3ace7b0c78a5ef.jpg_1190x550_6607f387.jpg\" _src=\"http://img1.qunarzz.com/sight/p0/201403/07/8dce2e3d9fc87b4e5e3ace7b0c78a5ef.jpg_1190x550_6607f387.jpg\"/></span></p><p><span style=\"white-space: nowrap;\">地址： 北京市门头沟区清水镇百花山林场（门头沟与房山交接处）&nbsp;</span></p><p><span style=\"white-space: nowrap;\">电话： (010)60390257</span></p><p><br/></p>', '8', '0', '11', '1', '1', '2', '10', 'download/image_url/20150524_224645_154137.jpg', null, null, null, '2015-05-24', '系统管理员', null, null, '2015-05-24 22:46:45', '2015-05-24 22:46:45', '1');
INSERT INTO `tb_article` VALUES ('330', '4', '爨底下', '<p>爨底下村位于北京西郊门头沟区斋堂镇，川底下村，实名爨底下。因在明代“爨里安口”（当地人称爨头）下方得名。位于京西斋堂西北狭谷中部，解放前属宛平县八区，现属斋堂镇所辖。距京90公里，海拔650米，村域面积5.3平方公里，清水河流域，温带季风气候，年平均气温10.1℃，自然植被良好，适合养羊，养蜜蜂。爨底下是国家A级景区。</p><p>爨底下村人（户主及子女）全姓韩。相传是明代由山西洪洞县大槐树下移民而来，原村址在村西北老坟处，后因山洪暴发，将整个村庄摧毁。只有一对青年男女，外出幸免遇难。为延续韩族后代，二人以推磨为媒而成婚，并在现址立村，婚后所生三子，为：韩福金、韩福银、韩福苍。三子分三门，即：东门、中门、西门。始以福字为第一辈序排20辈：福景自守玉、有明万宏思、义巨晓怀孟、永茂广连文，至今已发展到17辈，茂，字辈。</p><p>还相传是因为爨被村民们编成了顺口溜：兴字头，林字腰，大字下面架火烧，大火烧林烧的兴，岂不很热？所以姓韩，谐音寒。</p><p>全村现有人口29户，93人，土地280亩全村院落74个，房屋689间。大部分为清后期所建（少量建于民国时期）的四合院、三合院。依山而建，依势而就，高低错落，以村后龙头为圆心，南北为轴线呈扇面形展于两侧。村上、村下被一条长200米，最高处20米的弧形大墙分开，村前又被一条长170米&nbsp;</p><p>的弓形墙围绕，使全村形不散而神更聚，三条通道惯穿上下，而更具防洪、防匪之功能。</p><p>爨底下是国家3A级景区，市级文明单位，市级民俗旅游专业村，2003年被国家建设部，国家文物局评为首批中国历史文化名村，区级革命传统教育基地。著名的专家哲文先生讲：爨底下古山村是一颗中国古典建筑瑰宝的明珠，它蕴含着深厚的北方建筑文化内涵，就其历史，文化艺术价值来说，不仅在北京，就是在全国也属于珍贵之列，公之于世，功莫大焉。</p><p><a class=\"image-link nslog:9317\" href=\"http://baike.baidu.com/picture/509635/509635/0/a08b87d6277f9e2f4914e1881f30e924b899f309.html?fr=lemma&ct=single\" target=\"_blank\" title=\"爨底下村全景\" style=\"color: rgb(19, 110, 194); display: block; font-family: arial, 宋体, sans-serif; line-height: 24px; white-space: normal; width: 500px; height: 373px; background-color: rgb(255, 255, 255);\"><img class=\"\" alt=\"爨底下村全景\" src=\"http://g.hiphotos.baidu.com/baike/s%3D500/sign=0cbb1083c895d143de76e42343f18296/a08b87d6277f9e2f4914e1881f30e924b899f309.jpg\" style=\"text-align: center; display: block; margin-right: auto; margin-left: auto; width: 500px; height: 373px;\"/></a></p><p>村名</p><p>爨底下的“爨”字，共有三十笔，发cuàn音，为了方便记忆可拆开说：兴字头，林字腰、大字下面加火烧，大火烧林，越烧越兴，岂不很热，而爨底下人全姓韩，取谐音（寒）则为冷意，冷与热在五行之中可以互补，宇宙万物有天就有地，有日就有月，有男必然有女，有冷就得有热，故而爨底下村在历史上曾辉煌过。</p><p>“爨”字从字意解释为：家，永不分爨，即永不分家。为：灶，烧火煮饭。为：姓，陕西省歧山县有爨家庄，全村千口余人皆姓爨。此字难写难认，会写则成爨，不会写则成一片，故而用谐音“川”字代之，但仍发爨音。最早是在1942年为方便抗日干部特别是外地抗日干部通讯联系，将“爨”改成“川”，爨与川并用至五十年代末，基本就不用爨字了，1995年搞旅游开发后，爨字又大放熠彩。</p><p><br/></p><p>地址： 北京市门头沟区爨底下景区内&nbsp;</p>', '2', '0', '11', '1', '1', '2', '10', 'download/image_url/20150524_224700_823152.jpg', null, null, null, '2015-05-24', '系统管理员', null, null, '2015-05-24 22:47:00', '2015-05-24 22:47:00', '1');
INSERT INTO `tb_article` VALUES ('331', '4', '灵山', '<p>&nbsp; &nbsp; 盛夏避暑的好去所。夏季，这里“丰草绿缛而争茂，佳木葱茏而可悦”，走在林中，让人备感舒适。自然山水自然景，自然空调自然风，凉风习习，心惬意，来到灵山方知城乡大不同。入夜，遥望星空，这里视野开阔，可以看到“天阶月色凉如水，卧看牵牛织女星”的美景。</p><p>&nbsp; &nbsp; 灵山自然风景区距京城122公里，其顶峰海拔2303米，是北京的第一峰。西与龙门森林公园毗邻；东与龙门涧景区相连；南与109国道相通。由于其海拔高度所致，使灵山在方圆25平方公里内形成北京地区集断层山、褶皱山为一体，奇峰峻俏、花卉无垠的自然风景区。</p><p>这里既有暖温带植被，又有西伯利亚寒冷地带亲缘植被。生长着杜鹃、丁香、白桦林和榛子、黄花、等植物，尤以高山草甸最为著名，因此，它是新疆细毛羊、伊犁马、青藏牦牛在北京唯一的天然繁衍养殖场，也是野生动物的乐园。</p><p>&nbsp; &nbsp; 灵山风景区内有旅游饭店3座，可同时接待就餐1200人，住宿500人以上。此外，村内有农家旅店，如有兴趣住进“山兄弟”、“山里人”，定能享受一下山里人的烤全羊、烤野兔和手扒羊肉等野味。</p><p>成人门票：35元</p><p>地址：门头沟区灵山景区管理处</p><p>公交车：乘坐公交汽车、电车到苹果园下车前200米转乘929支线至双塘涧（灵山脚下）每天四趟。</p><p><br/></p><p>电话：010-61827994、61827028</p><p>门票：35元/人，老人、学生、残疾人士凭有效证件17.5元/人，军人凭有效证件免费。</p><p>地址：北京市门头沟区灵山景区</p>', '5', '0', '11', '1', '1', '2', '10', 'download/image_url/20150524_224714_571477.jpg', null, null, null, '2015-05-24', '系统管理员', null, null, '2015-05-24 22:47:14', '2015-05-24 22:47:14', '1');
INSERT INTO `tb_article` VALUES ('332', '4', '龙门涧', '<p>&nbsp; &nbsp; 龙门涧位于门头沟区清水乡燕家台西北侧。有长江三峡之峻拔，桂林山水之秀美，又有匡庐之飞瀑，黄山之叠泉。因此人们以“燕京小三峡”、“京西小桂林”、“大自然中的山水盆景”等赞语赞美龙门涧。</p><p>&nbsp; &nbsp; 龙门涧分为东西两涧，各绵延十余里。由于这里聚集了我国几类著名风景区的景色，诸如“三峡之气势”、“桂林之秀美”、“匡庐之飞瀑”、“黄 山之叠泉”，都可以在这里看到缩影，因此，龙门涧得到了许多 如“燕京小三峡”，“京西小桂林”、“京西小黄山”等美誉。 进入龙门涧峡谷，两侧山峰对峙，高耸碧空，如斧劈成。涧内泉水涓涓，清澈碧透，溪水潺潺，奔腾飞溅。冬日崖挂冰凌，夏日绿枝俏控。还有将军石、一线天、试剑峰、黑龙潭、听音阁、祭天台等景观。半山腰上，在一簇簇鲜花掩映下，十几个巨大的石灰岩洞若隐若现，为游人增添了无穷乐趣。由于山高谷深，日照温度低，盛夏酷暑时节，涧内却凉爽宜人，是旅游度假的好去处。</p><p><br/></p><p>地址：门头沟区西部清水镇燕家台村</p><p>电话：(010)61828185,(010)61828185</p>', '4', '0', '11', '1', '1', '2', '10', 'download/image_url/20150524_224755_937404.jpg', null, null, null, '2015-05-24', '系统管理员', null, null, '2015-05-24 22:47:55', '2015-05-24 22:47:55', '1');
INSERT INTO `tb_article` VALUES ('333', '4', '妙峰山', '<p><span>　　国家3A级旅游景区。位于北京市门头沟区妙峰山镇。109国道34公里处。距北京市区55公里，是集人文和自然风光于一体的自然风景旅游景区。妙峰山惠济祠等庙宇群始建于辽金时代，后经历次战争运动仅存少量遗址，成为北京红色景点之一。1986年由有关部门组织重建，1988年成立景区重新对游人开放。</span></p><p><span><br/></span></p><p><span><strong>山庙会</strong></span></p><p><span>　　北京的庙会起源于辽代，称“上巳春游”。元、明两代庙会进一步兴起。妙峰山庙会始于明代，每年农历四月初一至十八妙峰山开山半月余，日以万计的香客络绎于途，有的一步一揖，三步一扣首；有的竟以背鞍、滚砖、耳箭、悬灯等方式进香以示虔诚。各种民间香会边走边练，幡旗飘扬，鼓磬齐鸣，观者如潮。妙峰山庙会期，京都万人空巷，其规模堪称华北之首。</span></p><p><span>妙峰山传统庙会始于明代崇祯年间，距今已有300余年的历史。每年的农历四月初一至十五，来自全国各地数十万善男信女，几百档民间花会汇聚妙峰山，朝顶进香，酬山赛会，施粥布茶，场面之壮观，信众之虔诚实属罕见。清《燕京岁时记》载：“妙峰山每属四月，自初 一开庙半月，香火极盛，人烟幅辏，车马喧闹，夜间灯火之繁灿如列宿，香火实可甲于天下矣”。1925年，北京大学国学门研究所对妙峰山庙会进行了专门调查，出版了《妙峰山进香专号》，此次调查开创了我国民俗学田野调查的先河，妙峰山因此成为中国民俗文化的发祥地。</span></p><p><span>妙峰山庙会在文革和抗战期间中断了数十年，一九九零年京城老香会“秘密”到妙峰山朝顶进香，酬山赛会。一九九三年政府正式批准妙峰山举办首届春香庙会，古老的妙峰山庙会文化又焕发出新的生机。如今的妙峰山庙会除完整保留了明清时期香客朝顶，香会酬山，施粥、布茶、舍馒头等传统形式外，还增加了商品交易、民俗展示、文艺演出等新内容，形成京城独具魅力、积极健康的民俗活动。</span></p><p><span><br/></span></p><p><span><strong>山花会</strong></span></p><p><span>　　花会最早称香会，因解放后破出迷信，改香会为花会。花会属于民间组织，始见于明朝中叶，花会有“文会”和“武会”之分。“文会”：各种行业都有自己的会，如“粥茶会”、“面茶会”、“青菜会”、“献盐会”、“缝绽会”等几十种名目。“武会”：门内有杠箱会、狮子会、中幡会、杠子会、石锁会、双石会、吵子会、花坛会、花钹大鼓、开路会、五虎棍、秧歌会、太平会等十三种名目。门外有旱船、踏车、云车、小车等各名目。此外还有吏部杠子会、户部秧歌会、礼部大执事会、兵部杠箱会、刑部石锁会、工部石锁会、太子府花坛会、掌礼司太狮会、翰林院五虎棍会、雪池五虎打路会等十堂官会。</span></p><p><span>旧时花会献艺的场所主要是“三山”“五顶”。“三山”既平谷的丫髻山、门头沟的妙峰山、石景山的天台山。“五顶”既京城五座比较闻名的娘娘庙：东直门外的称东顶、海淀蓝靛厂的称西顶、永外大红门的称南顶、安定门外的称北顶、丰台草桥的称中顶。乾隆皇帝封妙峰山娘娘庙为金顶，地位在“五顶”之上。</span></p><p><span><br/></span></p><p><span><strong>花会表演</strong></span></p><p><span>　　清朝前妙峰山每年两次庙会，农历四月初一至十八的春香庙会和农历七月初一至十五的秋香庙会，庙会期间花会必来朝顶进香，各会以到妙峰山朝顶为荣，并规定“未到妙峰山朝顶的花会不为正宗会”，各会严格遵循“车笼自备，茶水不扰”的会规，提倡一秉虔心和无私奉献精神。</span></p><p><span>　　妙峰山庙会兴盛时期花会多达几百档，自1990年恢复妙峰山庙会以来，已有130余档民间花会遵循传统会规到妙峰山朝顶献艺。</span></p><p><span><br/></span></p><p><span><strong>慈禧与娘娘庙</strong></span></p><p><span>　　北京西部的妙峰山，盛产玫瑰，据说是中国面积最大、品种最好的玫瑰养殖地；妙峰山的玫瑰虽好，但不如山上的娘娘庙名声大。每年农历的四月初一至十五，是妙峰山朝山之日，来自各地上百档花会云集，成千上万香客来往于香道之间，人山人海，蔚为壮观。民俗学家将妙峰山庙会誉为中国民俗文化研究的发祥地。老北京人甚至有“妙峰山的娘娘——照远不照近”歇后语。</span></p><p><span>　　妙峰山娘娘庙肇始何年不详，笔者分析，至少在明代就已经存在。据清代史料记载，有相当一段时间，朝廷对妙峰山庙会是禁止的。妙峰山庙会在清代末期名声远播，影响华北乃至全国，是与西太后慈禧的影响不无关系。</span></p><p><span><br/></span></p><p><span><strong>慈禧与眺远斋</strong></span></p><p><span>　　在颐和园内谐趣园北侧，高台之上，有一座巍峨颐和园眺远斋的大殿，五楹卷棚歇山式，前廊后厦，颇为气派。坐在殿内，对颐和园外的景致一览无遗，这就是眺远斋，是专门为慈禧观看前往妙峰山进香花会修建的。</span></p><p><span>　　却说这一年四月，春暖花开，慈禧来到谐趣园观花赏景，忽然听见园外锣鼓喧天，喝彩声不断，便问身边的李莲英：“园外这么热闹是干什么呢？”李莲英急忙差人出去察看，回来禀报：“是去妙峰山进香的走会。”</span></p><p><span>　　慈禧一向喜欢热闹，便对李莲英说：“我想看看走会的。”李莲英哪敢答应，叩头回奏说：“老佛爷出园看走会事小，可惊了凤驾奴才担当不起。今年的香会说话就过完了，回头在这谐趣园内专修一座宝殿，供老佛爷赏花看会，岂不是两全其美。”慈禧点头应允。于是，谐趣园内大兴土木，赶在第二年的四月初一，眺远斋大功告成。慈禧每每登上大殿，观看各路香会表演，看得兴起，少不了发个懿旨，赏赐哪一个香会点什么物件。这一来，赴妙峰山的香会纷纷来到颐和园外，给老佛爷献上绝技，指望得到封号或者赏赐。</span></p><p><span>　　妙峰山的香会分为“武会”和“文会”两种，并有“老会”和“圣会”之称。“老会”是百年以上的&nbsp;</span></p><p><span>香会，而“圣会”则不到百年。据《京都风物志》载：“城内诸般歌舞之会，必于此月登山酬赛，谓之‘朝顶进香’。如开路(要耍三股叉)、秧歌、挎鼓、高跷、太少师、五虎棍、扛箱会”等。“武会”大多从京城菜市口的山西洪洞会馆、白纸坊纸业公会、德胜门外松林闸三处出发，途中，锣鼓齐奏，每到茶棚，都要表演，“观者如堵，使进香沿途十分热闹壮观”。“文会”又为“善会”，是北京、天津地区百姓自发组织起来为进香人服务的慈善组织，如“茶棚老会”(供茶)、“施粥老会”(供粥)、“献盐老会”(供盐)、“拜席老会”(供席棚)、“燃灯老会”(供灯火)等。</span></p><p><span>　　据说，自打慈禧在眺远斋观赏香会以后，便有了“皇会”之说，也就是得到慈禧赏赐的香会有了可以炫耀的新旗号。</span></p><p><span><br/></span></p><p><span><strong>慈禧与“金阶”</strong></span></p><p><span>　　去妙峰山进香的路，原为崎岖小径，陡峭艰险。清同治年间，慈禧太后提出，准备去妙峰山进香。太监刘诚印闻言，便以素云道人名义，会同大太监安德海出资重修香道。将山道拓宽7尺，选取天然青石板，一层层铺砌，绵延数里。工程浩大，完全凭借人力，加之工期紧，气候恶劣，造价昂贵。据说每铺一级石阶，造价就是一两白银，因而得“金阶”之名。</span></p><p><span><br/></span></p><p><span><strong>慈禧与“头炷香”</strong></span></p><p><span>　　到妙峰山娘娘庙进香，过去讲究抢烧头一炷香。据说，谁得以烧得四月初一头炷香，便会“如愿以偿“。于是香客们千方百计纷纷争先，早早上山，争抢头柱香。</span></p><p><span>　　身居紫禁城皇宫内的娘娘们也相信头炷香的魅力，于是，在一个</span></p><p><span>善来金阶时期，头炷香便成了皇室的“专利”。到了清同治、光绪年间，慈禧也要去妙峰山进香，这头炷香便被慈禧垄断了。她曾下懿旨：“先期预诏庙祝，必须宫中进香后，始行开庙，谓之头香。”有宫词云：“昨夜慈宁亲诏下，妙高峰里进头香。”(故宫里的慈宁宫，是太后居住的地方。这里指慈禧。)</span></p><p><span>慈禧手书匾额</span></p><p><span>　　妙峰山娘娘庙正殿是灵感宫，有正殿三间，供奉“天仙圣母碧霞元君”。门檐原来悬挂三块匾额，分别是“慈光普照”、“功侔富媪”、“泰云垂荫”。据说原来均为慈禧所书，现已无存。</span></p><p><br/></p><p>地址： 北京门头沟区妙峰山镇</p><p>电话：(010)61882936</p>', '12', '0', '11', '1', '1', '2', '10', 'download/image_url/20150524_224808_352554.jpg', null, null, null, '2015-05-24', '系统管理员', null, null, '2015-05-24 22:48:08', '2015-05-24 22:48:08', '1');
INSERT INTO `tb_article` VALUES ('334', '3', '大鸭梨', '<p>地址：北京近郊门头沟区新桥南大街49号(月季园小区对面)</p><p>电话：(010)69834563</p>', '7', '0', '11', '1', '1', '1', '9', 'download/image_url/20150524_231027_730846.jpg', null, null, null, '2015-05-24', '系统管理员', null, null, '2015-05-24 23:04:05', '2015-05-24 23:04:05', '1');
INSERT INTO `tb_article` VALUES ('335', '3', '福华肥牛', '<p>地址：北京近郊门头沟区新桥南大街49号(月季园小区对面)</p><p>电话：(010)69846725</p>', '30', '0', '11', '1', '1', '2', '8', 'download/image_url/20150524_231021_399721.jpg', null, null, null, '2015-05-24', '系统管理员', null, null, '2015-05-24 23:04:49', '2015-05-24 23:04:49', '1');
INSERT INTO `tb_article` VALUES ('340', '3', '新新饭庄', '<p>地址：近郊门头沟区新桥大街49号(北京市门头沟区政府)</p><p>电话：(010)69862198</p><p><br/></p><p>门头沟区政府对面，有一家新新饭庄，这里有我魂牵梦绕的肉包子。</p><p style=\"text-align: center;\"><img src=\"http://localhost/mtg/umeditor/upload/20150526/33181432627216804.jpg\" _src=\"http://localhost/mtg/umeditor/upload/20150526/33181432627216804.jpg\" style=\"\"/></p><p style=\"text-align: center;\"><img src=\"http://localhost/mtg/umeditor/upload/20150526/91231432627220249.jpg\" _src=\"http://localhost/mtg/umeditor/upload/20150526/91231432627220249.jpg\" style=\"\"/></p><p style=\"text-align: center;\"><img src=\"http://localhost/mtg/umeditor/upload/20150526/97221432627222876.jpg\" _src=\"http://localhost/mtg/umeditor/upload/20150526/97221432627222876.jpg\" style=\"\"/></p><p style=\"text-align: center;\"><img src=\"http://localhost/mtg/umeditor/upload/20150526/65691432627225647.jpg\" _src=\"http://localhost/mtg/umeditor/upload/20150526/65691432627225647.jpg\" style=\"\"/></p><p style=\"text-align: center;\"><img src=\"http://localhost/mtg/umeditor/upload/20150526/62861432627228276.jpg\" _src=\"http://localhost/mtg/umeditor/upload/20150526/62861432627228276.jpg\" style=\"\"/></p><p style=\"text-align: center;\"><br/></p>', '3', '0', '11', '1', '1', '2', '10', 'download/image_url/20150526_155952_365641.jpg', null, null, null, '2015-05-24', '系统管理员', null, null, '2015-05-24 23:07:41', '2015-05-24 23:07:41', '1');
INSERT INTO `tb_article` VALUES ('343', '5', '大峪中学', '<p>北京市大峪中学始建于1946年，1978年列为北京市重点中学，2004年进入北京市普通高中示范校行列。是一所校风优良、质量上乘的北京市重点中学，莘莘学子向往的京西名校。学校位于九龙山下，永定河畔，北京门头沟城镇中心的滨河德露苑18号，新的开发区内。</p><p><br/></p><p>学校地址：门头沟区滨河德露苑18号</p><p>邮政编码：102300</p><p>联系电话：61864110</p><p>学校网址：http://www.dyzx-bj.com/</p>', '12', '0', '11', '1', '1', '2', '8', 'download/image_url/20150526_140229_544065.jpg', null, null, null, '2015-05-24', '系统管理员', null, null, '2015-05-24 23:22:24', '2015-05-24 23:22:24', '1');
INSERT INTO `tb_article` VALUES ('351', '90', '捐赠我们', '<p style=\"box-sizing: border-box; padding: 0px; color: rgb(51, 51, 51); line-height: 22.8571434020996px; white-space: normal; text-align: center; background-color: rgb(255, 255, 255);\">支付宝捐赠二维码 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 微信捐赠二维码</p><p style=\"box-sizing: border-box; padding: 0px; color: rgb(51, 51, 51); line-height: 22.8571434020996px; white-space: normal; text-align: center; background-color: rgb(255, 255, 255);\"><img src=\"http://ww1.sinaimg.cn/mw690/3fc7e281jw1eqec436tzwj2074074mxr.jpg\" style=\"box-sizing: border-box; margin: 0px; padding: 0px; border: 0px; vertical-align: middle;\"/>&nbsp; &nbsp;&nbsp;<img src=\"http://ww1.sinaimg.cn/mw690/3fc7e281jw1es3jr0k25xj20a50a5q3v.jpg\" style=\"box-sizing: border-box; margin: 0px; padding: 0px; border: 0px; vertical-align: middle; line-height: 1.42857143; width: 256px; height: 256px;\"/></p><p style=\"box-sizing: border-box; padding: 0px; color: rgb(51, 51, 51); line-height: 22.8571434020996px; white-space: normal; text-align: center; background-color: rgb(255, 255, 255);\"><br/></p><br/><p><br/></p>', '0', '0', '11', '1', '1', '2', '19', null, null, null, null, '2015-05-26', '系统管理员', null, null, '2015-05-26 10:39:11', '2015-05-26 10:39:11', '1');
INSERT INTO `tb_article` VALUES ('352', '90', '关于我们', '<p style=\"white-space: normal; box-sizing: border-box; padding: 0px; color: rgb(51, 51, 51); line-height: 22.8571434020996px; background-color: rgb(255, 255, 255);\"><span style=\"line-height: 22.8571434020996px;\"></span></p><p>可通过如下方式联系我们：<br/></p><p>联系方式：qq(369191470)</p><p style=\"white-space: normal; box-sizing: border-box; padding: 0px; color: rgb(51, 51, 51); line-height: 22.8571434020996px; background-color: rgb(255, 255, 255);\"><a target=\"_blank\" href=\"http://mail.qq.com/cgi-bin/qm_share?t=qm_mailme&email=_crKyc-LzszIzrmIiNealpQ\" style=\"box-sizing: border-box; margin: 0px; padding: 0px; color: rgb(66, 139, 202); text-decoration: none; background: 0px 0px;\"></a></p><p style=\"white-space: normal;\"><a target=\"_blank\" href=\"http://mail.qq.com/cgi-bin/qm_share?t=qm_mailme&email=allcU1tTW15dWiobG0QJBQc\" style=\"text-decoration: none;\"><img src=\"http://rescdn.qqmail.com/zh_CN/htmledition/images/function/qm_open/ico_mailme_02.png\"/></a></p><p><br/></p>', '1', '0', '11', '1', '1', '2', '13', null, null, null, null, '2015-05-26', '系统管理员', null, null, '2015-05-26 10:39:24', '2015-05-26 10:39:24', '1');
INSERT INTO `tb_article` VALUES ('353', '90', '联系我们', '<p style=\"white-space: normal; box-sizing: border-box; padding: 0px; color: rgb(51, 51, 51); line-height: 22.8571434020996px; background-color: rgb(255, 255, 255);\"><span style=\"line-height: 22.8571434020996px;\">地址：北京市</span><br/></p><p style=\"white-space: normal; box-sizing: border-box; padding: 0px; color: rgb(51, 51, 51); line-height: 22.8571434020996px; background-color: rgb(255, 255, 255);\">联系方式：qq(369191470)</p><p style=\"white-space: normal; box-sizing: border-box; padding: 0px; color: rgb(51, 51, 51); line-height: 22.8571434020996px; background-color: rgb(255, 255, 255);\"><a target=\"_blank\" href=\"http://mail.qq.com/cgi-bin/qm_share?t=qm_mailme&email=_crKyc-LzszIzrmIiNealpQ\" style=\"box-sizing: border-box; margin: 0px; padding: 0px; color: rgb(66, 139, 202); text-decoration: none; background: 0px 0px;\"></a></p><p><a target=\"_blank\" href=\"http://mail.qq.com/cgi-bin/qm_share?t=qm_mailme&email=allcU1tTW15dWiobG0QJBQc\" style=\"text-decoration:none;\"><img src=\"http://rescdn.qqmail.com/zh_CN/htmledition/images/function/qm_open/ico_mailme_02.png\"/></a></p>', '0', '0', '11', '1', '1', '2', '15', null, null, null, null, '2015-05-26', '系统管理员', null, null, '2015-05-26 10:39:53', '2015-05-26 10:39:53', '1');
INSERT INTO `tb_article` VALUES ('354', '90', '免责声明', '<p>本网站对本网站上所有由第三方提供的信息、内容和服务，不提供明示或暗示的担保。本网站对使用上述信息、内容和服务所造成的任何损失不承担责任，包括直接损失和间接损失。<br/></p><p><br/></p>', '0', '0', '11', '1', '1', '2', '20', null, null, null, null, '2015-05-26', '系统管理员', null, null, '2015-05-26 10:40:04', '2015-05-26 10:40:04', '1');
INSERT INTO `tb_article` VALUES ('355', '90', '广告服务', '<p>广告服务请通过以下方式联系：</p><p style=\"box-sizing: border-box; margin-top: 0px; margin-bottom: 10px; color: rgb(51, 51, 51); font-family: &#39;Helvetica Neue&#39;, Helvetica, Arial, sans-serif; font-size: 14px; white-space: normal; padding: 0px; line-height: 22.8571434020996px; background-color: rgb(255, 255, 255);\">联系方式：qq(369191470)</p><p style=\"box-sizing: border-box; margin-top: 0px; margin-bottom: 10px; color: rgb(51, 51, 51); font-family: &#39;Helvetica Neue&#39;, Helvetica, Arial, sans-serif; font-size: 14px; white-space: normal; padding: 0px; line-height: 22.8571434020996px; background-color: rgb(255, 255, 255);\"><a target=\"_blank\" href=\"http://mail.qq.com/cgi-bin/qm_share?t=qm_mailme&email=_crKyc-LzszIzrmIiNealpQ\" style=\"box-sizing: border-box; color: rgb(66, 139, 202); text-decoration: none; margin: 0px; padding: 0px; background: 0px 0px;\"></a></p><p style=\"box-sizing: border-box; margin-top: 0px; margin-bottom: 10px; color: rgb(51, 51, 51); font-family: &#39;Helvetica Neue&#39;, Helvetica, Arial, sans-serif; font-size: 14px; line-height: 20px; white-space: normal; background-color: rgb(255, 255, 255);\"><a target=\"_blank\" href=\"http://mail.qq.com/cgi-bin/qm_share?t=qm_mailme&email=allcU1tTW15dWiobG0QJBQc\" style=\"box-sizing: border-box; color: rgb(51, 122, 183); text-decoration: none; background-color: transparent;\"><img src=\"http://rescdn.qqmail.com/zh_CN/htmledition/images/function/qm_open/ico_mailme_02.png\" style=\"box-sizing: border-box; border: 0px; vertical-align: middle;\"/></a></p>', '0', '0', '11', '1', '1', '2', '17', null, null, null, null, '2015-05-26', '系统管理员', null, null, '2015-05-26 10:40:30', '2015-05-26 10:40:30', '1');
INSERT INTO `tb_article` VALUES ('378', '5', '金钱小学', null, '3', '0', '11', '1', '1', '2', '9', 'download/image_url/20160117_001411_381494.jpg', null, null, null, '2015-05-26', '系统管理员', null, null, '2015-05-26 13:58:48', '2015-05-26 13:58:48', '1');
INSERT INTO `tb_article` VALUES ('381', '100', '我的博文', '<p>测试</p>', '1', '0', '11', '1', '1', '2', '20', null, null, null, null, '2015-06-04', '系统管理员', null, null, null, '2015-06-17 22:23:52', '1');
INSERT INTO `tb_article` VALUES ('403', '3', '测试1', null, '0', '0', '11', '1', '1', '2', '10', null, null, null, null, '2016-01-17', '系统管理员', null, null, '2016-01-17 00:31:02', '2016-01-17 00:31:02', '1');
INSERT INTO `tb_article` VALUES ('404', '3', '测试2', null, '0', '0', '11', '1', '1', '2', '10', null, null, null, null, '2016-01-17', '系统管理员', null, null, '2016-01-17 00:31:10', '2016-01-17 00:31:10', '1');
INSERT INTO `tb_article` VALUES ('405', '5', '测试1', null, '0', '0', '11', '1', '1', '2', '10', null, null, null, null, '2016-01-17', '系统管理员', null, null, '2016-01-17 00:32:06', '2016-01-17 00:32:06', '1');
INSERT INTO `tb_article` VALUES ('406', '5', '测试2', null, '0', '0', '11', '1', '1', '2', '10', null, null, null, null, '2016-01-17', '系统管理员', null, null, '2016-01-17 00:32:12', '2016-01-17 00:32:12', '1');
INSERT INTO `tb_article` VALUES ('407', '5', '测试3', null, '0', '0', '11', '1', '1', '2', '10', null, null, null, null, '2016-01-17', '系统管理员', null, null, '2016-01-17 00:32:16', '2016-01-17 00:32:16', '1');

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
) ENGINE=InnoDB AUTO_INCREMENT=190 DEFAULT CHARSET=utf8 COMMENT='评论';

-- ----------------------------
-- Records of tb_comment
-- ----------------------------
INSERT INTO `tb_comment` VALUES ('55', '0', '101', '这是一个java新人开发的金钱管理软件，内容比较简单，本着帮助新人以及为学习Jfinal的态度。\n\nJFinal-Money采用了简洁强大的JFinal作为web框架，模板引擎用的是jsp，数据库用mysql，前端bootstrap框架。\n\n运行效果：http://jmoney.jd-app.com/\n', '22', '2', '2015-03-16 17:19:33', '3');
INSERT INTO `tb_comment` VALUES ('101', '0', '1', '101', '12', '1', '2015-03-24 22:29:42', '1');
INSERT INTO `tb_comment` VALUES ('102', '0', '1', '102', '12', '1', '2015-03-24 22:29:42', '1');
INSERT INTO `tb_comment` VALUES ('103', '0', '1', '103', '12', '1', '2015-03-24 22:29:42', '1');
INSERT INTO `tb_comment` VALUES ('104', '0', '1', '104', '12', '1', '2015-03-24 22:29:42', '1');
INSERT INTO `tb_comment` VALUES ('105', '0', '1', '105', '12', '1', '2015-03-24 22:29:42', '1');
INSERT INTO `tb_comment` VALUES ('106', '0', '1', '106', '12', '1', '2015-03-24 22:29:42', '1');
INSERT INTO `tb_comment` VALUES ('107', '0', '1', '107', '12', '1', '2015-03-24 22:29:42', '1');
INSERT INTO `tb_comment` VALUES ('108', '0', '1', '108', '12', '1', '2015-03-24 22:29:42', '1');
INSERT INTO `tb_comment` VALUES ('109', '0', '1', '109', '12', '1', '2015-03-24 22:29:42', '1');
INSERT INTO `tb_comment` VALUES ('110', '0', '1', '110', '12', '1', '2015-03-24 22:29:42', '1');
INSERT INTO `tb_comment` VALUES ('111', '0', '1', '111', '12', '1', '2015-03-24 22:29:42', '1');
INSERT INTO `tb_comment` VALUES ('112', '0', '1', '112', '12', '1', '2015-03-24 22:29:42', '1');
INSERT INTO `tb_comment` VALUES ('113', '0', '1', '113', '12', '1', '2015-03-24 22:29:42', '1');
INSERT INTO `tb_comment` VALUES ('114', '0', '1', '114', '12', '1', '2015-03-24 22:29:42', '1');
INSERT INTO `tb_comment` VALUES ('115', '0', '1', '115', '12', '1', '2015-03-24 22:29:42', '1');
INSERT INTO `tb_comment` VALUES ('116', '0', '1', '116', '12', '1', '2015-03-24 22:29:42', '1');
INSERT INTO `tb_comment` VALUES ('117', '0', '1', '117', '12', '1', '2015-03-24 22:29:42', '1');
INSERT INTO `tb_comment` VALUES ('118', '0', '1', '118', '12', '1', '2015-03-24 22:29:42', '1');
INSERT INTO `tb_comment` VALUES ('119', '0', '1', '119', '12', '1', '2015-03-24 22:29:42', '1');
INSERT INTO `tb_comment` VALUES ('120', '0', '1', '120', '12', '1', '2015-03-24 22:29:42', '1');
INSERT INTO `tb_comment` VALUES ('121', '0', '1', '121', '12', '1', '2015-03-24 22:29:42', '1');
INSERT INTO `tb_comment` VALUES ('122', '0', '1', '122', '12', '1', '2015-03-24 22:29:42', '1');
INSERT INTO `tb_comment` VALUES ('123', '0', '1', '123', '12', '1', '2015-03-24 22:29:42', '1');
INSERT INTO `tb_comment` VALUES ('124', '0', '1', '124', '12', '1', '2015-03-24 22:29:42', '1');
INSERT INTO `tb_comment` VALUES ('125', '0', '1', '125', '12', '1', '2015-03-24 22:29:42', '1');
INSERT INTO `tb_comment` VALUES ('126', '0', '1', '126', '12', '1', '2015-03-24 22:29:42', '1');
INSERT INTO `tb_comment` VALUES ('127', '0', '1', '127', '12', '1', '2015-03-24 22:29:42', '1');
INSERT INTO `tb_comment` VALUES ('128', '0', '1', '128', '12', '1', '2015-03-24 22:29:42', '1');
INSERT INTO `tb_comment` VALUES ('129', '0', '1', '129', '12', '1', '2015-03-24 22:29:42', '1');
INSERT INTO `tb_comment` VALUES ('130', '0', '1', '130', '12', '1', '2015-03-24 22:29:42', '1');
INSERT INTO `tb_comment` VALUES ('131', '0', '1', '131', '12', '1', '2015-03-24 22:29:42', '1');
INSERT INTO `tb_comment` VALUES ('132', '0', '1', '132', '12', '1', '2015-03-24 22:29:42', '1');
INSERT INTO `tb_comment` VALUES ('133', '0', '1', '133', '12', '1', '2015-03-24 22:29:42', '1');
INSERT INTO `tb_comment` VALUES ('134', '0', '1', '134', '12', '1', '2015-03-24 22:29:42', '1');
INSERT INTO `tb_comment` VALUES ('135', '0', '1', '135', '12', '1', '2015-03-24 22:29:42', '1');
INSERT INTO `tb_comment` VALUES ('136', '0', '1', '136', '12', '1', '2015-03-24 22:29:42', '1');
INSERT INTO `tb_comment` VALUES ('137', '0', '1', '137', '12', '1', '2015-03-24 22:29:42', '1');
INSERT INTO `tb_comment` VALUES ('138', '0', '1', '138', '12', '1', '2015-03-24 22:29:42', '1');
INSERT INTO `tb_comment` VALUES ('139', '0', '1', '139', '12', '1', '2015-03-24 22:29:42', '1');
INSERT INTO `tb_comment` VALUES ('140', '0', '1', '140', '12', '1', '2015-03-24 22:29:42', '1');
INSERT INTO `tb_comment` VALUES ('141', '0', '1', '141', '12', '1', '2015-03-24 22:29:42', '1');
INSERT INTO `tb_comment` VALUES ('142', '0', '1', '142', '12', '1', '2015-03-24 22:29:42', '1');
INSERT INTO `tb_comment` VALUES ('143', '0', '1', '143', '12', '1', '2015-03-24 22:29:42', '1');
INSERT INTO `tb_comment` VALUES ('144', '0', '1', '144', '12', '1', '2015-03-24 22:29:42', '1');
INSERT INTO `tb_comment` VALUES ('145', '0', '1', '145', '12', '1', '2015-03-24 22:29:42', '1');
INSERT INTO `tb_comment` VALUES ('146', '0', '1', '146', '12', '1', '2015-03-24 22:29:42', '1');
INSERT INTO `tb_comment` VALUES ('147', '0', '1', '147', '12', '1', '2015-03-24 22:29:42', '1');
INSERT INTO `tb_comment` VALUES ('148', '0', '1', '148', '12', '1', '2015-03-24 22:29:42', '1');
INSERT INTO `tb_comment` VALUES ('149', '0', '1', '149', '12', '1', '2015-03-24 22:29:42', '1');
INSERT INTO `tb_comment` VALUES ('150', '0', '1', '150', '12', '1', '2015-03-24 22:29:42', '1');
INSERT INTO `tb_comment` VALUES ('151', '0', '1', '151', '12', '1', '2015-03-24 22:29:42', '1');
INSERT INTO `tb_comment` VALUES ('152', '0', '1', '152', '12', '1', '2015-03-24 22:29:42', '1');
INSERT INTO `tb_comment` VALUES ('153', '0', '1', '153', '12', '1', '2015-03-24 22:29:42', '1');
INSERT INTO `tb_comment` VALUES ('154', '0', '1', '154', '12', '1', '2015-03-24 22:29:42', '1');
INSERT INTO `tb_comment` VALUES ('155', '0', '1', '155', '12', '1', '2015-03-24 22:29:42', '1');
INSERT INTO `tb_comment` VALUES ('156', '0', '1', '156', '12', '1', '2015-03-24 22:29:42', '1');
INSERT INTO `tb_comment` VALUES ('157', '0', '1', '157', '12', '1', '2015-03-24 22:29:42', '1');
INSERT INTO `tb_comment` VALUES ('158', '0', '1', '158', '12', '1', '2015-03-24 22:29:42', '1');
INSERT INTO `tb_comment` VALUES ('159', '0', '1', '159', '12', '1', '2015-03-24 22:29:42', '1');
INSERT INTO `tb_comment` VALUES ('160', '0', '1', '160', '12', '1', '2015-03-24 22:29:42', '1');
INSERT INTO `tb_comment` VALUES ('161', '0', '1', '161', '12', '1', '2015-03-24 22:29:42', '1');
INSERT INTO `tb_comment` VALUES ('162', '0', '1', '162', '12', '1', '2015-03-24 22:29:42', '1');
INSERT INTO `tb_comment` VALUES ('163', '0', '1', '163', '12', '1', '2015-03-24 22:29:42', '1');
INSERT INTO `tb_comment` VALUES ('164', '0', '1', '164', '12', '1', '2015-03-24 22:29:42', '1');
INSERT INTO `tb_comment` VALUES ('165', '0', '1', '165', '12', '1', '2015-03-24 22:29:42', '1');
INSERT INTO `tb_comment` VALUES ('166', '0', '1', '166', '12', '1', '2015-03-24 22:29:42', '1');
INSERT INTO `tb_comment` VALUES ('167', '0', '1', '167', '12', '1', '2015-03-24 22:29:42', '1');
INSERT INTO `tb_comment` VALUES ('168', '0', '1', '168', '12', '1', '2015-03-24 22:29:42', '1');
INSERT INTO `tb_comment` VALUES ('169', '0', '1', '169', '12', '1', '2015-03-24 22:29:42', '1');
INSERT INTO `tb_comment` VALUES ('170', '0', '1', '170', '12', '1', '2015-03-24 22:29:42', '1');
INSERT INTO `tb_comment` VALUES ('171', '0', '1', '171', '12', '1', '2015-03-24 22:29:42', '1');
INSERT INTO `tb_comment` VALUES ('172', '0', '1', '172', '12', '1', '2015-03-24 22:29:42', '1');
INSERT INTO `tb_comment` VALUES ('173', '0', '1', '173', '12', '1', '2015-03-24 22:29:42', '1');
INSERT INTO `tb_comment` VALUES ('174', '0', '1', '174', '12', '1', '2015-03-24 22:29:42', '1');
INSERT INTO `tb_comment` VALUES ('175', '0', '1', '175', '12', '1', '2015-03-24 22:29:42', '1');
INSERT INTO `tb_comment` VALUES ('176', '0', '1', '176', '12', '1', '2015-03-24 22:29:42', '1');
INSERT INTO `tb_comment` VALUES ('177', '0', '1', '177', '12', '1', '2015-03-24 22:29:42', '1');
INSERT INTO `tb_comment` VALUES ('178', '0', '1', '178', '12', '1', '2015-03-24 22:29:42', '1');
INSERT INTO `tb_comment` VALUES ('179', '0', '1', '179', '12', '1', '2015-03-24 22:29:42', '1');
INSERT INTO `tb_comment` VALUES ('180', '0', '1', '180', '12', '1', '2015-03-24 22:29:42', '1');
INSERT INTO `tb_comment` VALUES ('181', '0', '1', '181', '12', '1', '2015-03-24 22:29:42', '1');
INSERT INTO `tb_comment` VALUES ('182', '0', '1', '182', '12', '1', '2015-03-24 22:29:42', '1');
INSERT INTO `tb_comment` VALUES ('183', '0', '1', '183', '12', '1', '2015-03-24 22:29:42', '1');
INSERT INTO `tb_comment` VALUES ('184', '0', '1', '184', '12', '1', '2015-03-24 22:29:42', '1');
INSERT INTO `tb_comment` VALUES ('185', '0', '1', '185', '12', '1', '2015-03-24 22:29:42', '1');
INSERT INTO `tb_comment` VALUES ('186', '0', '1', '186', '12', '1', '2015-03-24 22:29:42', '1');
INSERT INTO `tb_comment` VALUES ('187', '0', '1', '187', '12', '1', '2015-03-24 22:29:42', '1');
INSERT INTO `tb_comment` VALUES ('188', '0', '1', '188', '12', '1', '2015-03-24 22:29:42', '1');
INSERT INTO `tb_comment` VALUES ('189', '0', '1', '189', '12', '1', '2015-03-24 22:29:42', '1');

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
INSERT INTO `tb_folder` VALUES ('2', '0', '新闻', 'news', '', null, '2', '1', '1', null, null, null, '2015-05-24 15:46:40', '0', '2015-01-30 13:24:58', '1');
INSERT INTO `tb_folder` VALUES ('3', '0', '美食', 'food', '', null, '3', '1', '1', null, null, null, '2015-05-24 15:46:54', '0', '2015-01-30 13:24:58', '1');
INSERT INTO `tb_folder` VALUES ('4', '0', '旅游', 'travel', '', null, '5', '1', '1', null, null, null, '2015-05-24 15:47:43', '0', '2015-05-24 15:47:43', '1');
INSERT INTO `tb_folder` VALUES ('5', '0', '教育', 'education', '', null, '7', '1', '1', null, null, null, '2015-05-24 15:47:55', '0', '2015-05-24 15:47:55', '1');
INSERT INTO `tb_folder` VALUES ('6', '0', '后台管理', null, '', null, '90', '1', '3', 'admin', null, null, '2015-05-24 15:47:32', '0', '2015-01-30 13:24:58', '1');
INSERT INTO `tb_folder` VALUES ('7', '0', '标签查询', '', '', null, '80', '1', '2', 'front/tags/all', null, null, '2015-05-27 23:34:38', '0', '2015-05-18 09:12:57', '1');
INSERT INTO `tb_folder` VALUES ('13', '1', '首页图片', 'topPic', '', null, '101', '2', '1', null, null, null, '2015-05-24 16:33:06', '0', '2015-05-24 16:33:06', '1');
INSERT INTO `tb_folder` VALUES ('90', '0', '关于我们', 'about', '', null, '81', '1', '1', null, null, null, '2015-05-26 16:40:46', '0', '2015-05-26 10:36:30', '1');
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='栏目轮播图';

-- ----------------------------
-- Records of tb_folder_roll_picture
-- ----------------------------
INSERT INTO `tb_folder_roll_picture` VALUES ('3', '1', '百花山', null, '1', '1', null, 'http://i1.tietuku.com/3951e9cb262621b6.jpg', 'http://www.jflyfox.com/mtg/front/article/329.html', '1', '2016-01-28 17:40:22', '1', '2016-01-28 17:40:22', '1');
INSERT INTO `tb_folder_roll_picture` VALUES ('4', '1', '美丽门城', null, '2', '1', null, 'http://i1.tietuku.com/6f139452bedaefed.jpg', '#', '1', '2016-01-28 17:41:13', '1', '2016-01-28 17:41:13', '1');
INSERT INTO `tb_folder_roll_picture` VALUES ('5', '1', '永定塔', null, '3', '1', null, 'http://i1.tietuku.com/fab40b501ece3fcf.jpg', 'http://www.jflyfox.com/mtg/front/article/406.html', '1', '2016-01-28 17:42:12', '1', '2016-01-28 17:42:12', '1');
INSERT INTO `tb_folder_roll_picture` VALUES ('6', '1', '爨底下', null, '4', '1', null, 'http://i1.tietuku.com/35171f11a5ec9c51.jpg', 'http://www.jflyfox.com/mtg/front/article/330.html', '1', '2016-01-28 17:42:40', '1', '2016-01-28 17:42:40', '1');

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
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8 COMMENT='访问量统计';

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
