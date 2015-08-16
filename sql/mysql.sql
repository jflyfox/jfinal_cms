/**
 * 异常数据记录表
 */ 
drop table if exists tb_error;

CREATE TABLE tb_error (
  id  int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  type int(11) DEFAULT NULL COMMENT '类型',
  ip varchar(64) NOT NULL  COMMENT 'IP地址',
  userid int(11) DEFAULT NULL COMMENT '用户ID',
  content text comment '描述',
  remark text comment '备注',
  create_time  varchar(64) DEFAULT NULL COMMENT '创建时间',
  create_id  int(11) DEFAULT 0 COMMENT '创建者',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='异常数据';

/**
 * 访问量统计
 */ 
drop table if exists tb_pageview;

CREATE TABLE tb_pageview (
  id  int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  ip varchar(64) NOT NULL  COMMENT 'IP地址',
  userid int(11) DEFAULT NULL COMMENT '用户ID',
  create_day  varchar(64) NOT NULL COMMENT '创建时间到天',
  create_time  varchar(64) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='访问量统计';

/**
 * 目录表
 */
drop table if exists tb_folder;

CREATE TABLE tb_folder (
  id int(11) not null auto_increment comment '目录id',
  name varchar(100) not null default '' comment '中文名',
  path varchar(200) not null default '' comment '路径',
  content text comment '描述',
  sort  int(11) default '1' comment '排序',
  status varchar(20) DEFAULT '1' comment '状态//radio/2,隐藏,1,显示',
  type  int(11) DEFAULT '1' comment '类型//select/1,普通目录,2,a标签,3,a标签_blank,4,直接加载url信息',
  jump_url varchar(200) DEFAULT NULL comment '跳转地址',
  update_time varchar(64) DEFAULT NULL COMMENT '更新时间',
  create_time  varchar(64) DEFAULT NULL COMMENT '创建时间',
  create_id  int(11) DEFAULT 0 COMMENT '创建者',
  primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='目录';

/**
 * 文章表
 */
drop table if exists tb_article;
CREATE TABLE tb_article
(
  id int(11) not null auto_increment comment 'id',
  folder_id integer default 1 comment '目录id',
  title varchar(200) default '' comment '文章名称',
  content text comment '文件内容', 
  count_view int(11) default '0' comment '浏览数',
  count_comment int(11) default '0' comment '评论数',
  type  int(11) default '1' comment '类型//select/1,正常,2,预览展示概述,3,程序调用处理',
  status varchar(20) default '1' comment '状态//radio/2,隐藏,1,显示',
  is_comment varchar(20) default '1' comment '是否评论//radio/2,否,1,是',
  is_recommend int(11) DEFAULT '2' COMMENT '是否推荐：2 否 1 是',
  sort  int(11) default '1' comment '排序',
  image_url varchar(256) default null comment '图片路径',
  image_net_url varchar(256) DEFAULT NULL COMMENT '网络图片路径',
  publish_time varchar(64) DEFAULT NULL COMMENT '发布时间',
  publish_user varchar(64) DEFAULT '1' COMMENT '发布者',
  start_time varchar(64) DEFAULT NULL COMMENT '开始时间',
  end_time varchar(64) DEFAULT NULL COMMENT '结束时间',
  update_time varchar(64) DEFAULT NULL COMMENT '更新时间',
  create_time  varchar(64) DEFAULT NULL COMMENT '创建时间',
  create_id  int(11) DEFAULT 0 COMMENT '创建者',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文章';
-- 主键从200开始
alter table tb_article AUTO_INCREMENT=200;

/**
 * 联系人表
 */ 
drop table if exists tb_contact;

CREATE TABLE tb_contact
(
  id  int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  name varchar(256) NOT NULL COMMENT '姓名',
  phone varchar(32) COMMENT '手机号',
  email varchar(32) COMMENT 'Email',
  addr varchar(256) COMMENT '地址', 
  birthday varchar(32) COMMENT '生日',
  remark varchar(256) COMMENT '说明',
  create_time  varchar(64) DEFAULT NULL COMMENT '创建时间',
  create_id  int(11) DEFAULT 0 COMMENT '创建者',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='联系人';


/**
 * 评论表
 */ 
drop table if exists tb_comment;


CREATE TABLE tb_comment (
  id  int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  fatherId int(11) DEFAULT NULL COMMENT '父评论ID',
  article_id int(11) DEFAULT NULL COMMENT '文章ID',
  content text NOT NULL COMMENT '内容',
  status int(11) DEFAULT 11 COMMENT '状态//select/11,评论未读,12,评论已读,21,回复未读,22,回复已读',
  reply_userid int(11) DEFAULT 0 COMMENT '回复者',
  create_time  varchar(64) DEFAULT NULL COMMENT '创建时间',
  create_id  int(11) DEFAULT 0 COMMENT '创建者 评论者',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='评论';

/**
 * 标签
 */
drop table if exists tb_tags;
CREATE TABLE tb_tags
(
  id int(11) not null auto_increment comment 'id',
  article_id int(11) DEFAULT NULL COMMENT '文章ID',
  tagname varchar(200) default '' comment '标签内容',
  create_time  varchar(64) DEFAULT NULL COMMENT '创建时间',
  create_id  int(11) DEFAULT 0 COMMENT '创建者',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='标签';

/**
 * 友情链接
 */ 
drop table if exists tb_friendlylink;

/**
 * 友情链接表
 */
create table tb_friendlylink
(
  id      int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  name    varchar(256) NOT NULL COMMENT '名称',
  url     varchar(256) NOT NULL COMMENT 'URL',
  sort    int(11) NOT NULL COMMENT '排序号',
  state   int(11) DEFAULT 0 COMMENT '是否显示//radio/1,显示,2,不显示',
  type   int(11) DEFAULT 0 COMMENT '类型//select/1,见数据字典',
  remark  varchar(256)  DEFAULT NULL COMMENT '备注//textarea',
  create_time  varchar(64) DEFAULT NULL COMMENT '创建时间',
  create_id  int(11) DEFAULT 0 COMMENT '创建者',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='友情链接表';

/**
 * 喜欢的文章
 */
drop table if exists tb_articlelike;
CREATE TABLE tb_articlelike
(
  id int(11) not null auto_increment comment 'id',
  article_id int(11) DEFAULT NULL COMMENT '文章ID',
  create_time  varchar(64) DEFAULT NULL COMMENT '创建时间',
  create_id  int(11) DEFAULT 0 COMMENT '创建者',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='喜欢的文章';

/************************ 系统表分界线 **********************************/
/**
 * 数据字典主表
 */
drop table if exists sys_dict;

CREATE TABLE sys_dict
(
  dict_id int(11) NOT NULL AUTO_INCREMENT COMMENT '主键', 
  dict_name   VARCHAR(256) not null COMMENT '名称',
  dict_type  VARCHAR(64) not null COMMENT '类型',
  dict_remark VARCHAR(256) COMMENT '备注',
  PRIMARY KEY (dict_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据字典主表';
alter table sys_dict add unique UK_SYS_DICT_TYPE (dict_type);

/**
 * 数据字典明细表
 */
drop table if exists sys_dict_detail;

create table sys_dict_detail
(
  detail_id      int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  dict_type      varchar(64) NOT NULL COMMENT '数据字典类型',
  detail_name    varchar(256) COMMENT '名称',
  detail_code    varchar(32) COMMENT '代码',
  detail_sort    varchar(32) COMMENT '排序号',
  detail_type    varchar(32) COMMENT '类型',
  detail_state   varchar(32) COMMENT '状态',
  detail_content varchar(256) COMMENT '内容',
  detail_remark  varchar(256) COMMENT '备注',
  create_time  varchar(64) DEFAULT NULL COMMENT '创建时间',
  create_id  int(11) DEFAULT 0 COMMENT '创建者',
  PRIMARY KEY (detail_id)
  -- ,foreign key (dict_type) references sys_dict (dict_type)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据字典';

/**
 * 部门表
 */
drop table if exists sys_department;

create table sys_department
(
  id      int(11) NOT NULL AUTO_INCREMENT,
  name    varchar(32) NOT NULL COMMENT '部门/11111',
  sort int(11) DEFAULT 0 COMMENT '序号',
  linkman varchar(64) DEFAULT NULL COMMENT '联系人',
  linkman_no varchar(32) DEFAULT NULL COMMENT '联系人电话',
  create_time  varchar(64) DEFAULT NULL COMMENT '创建时间',
  create_id  int(11) DEFAULT 0 COMMENT '创建者',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门';

/**
 * 用户表
 */
drop table if exists sys_user;

create table sys_user
(
  userid      int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  username    varchar(32) NOT NULL COMMENT '用户名/11111',
  password    varchar(32) NOT NULL COMMENT '密码',
  realname    varchar(32) DEFAULT NULL COMMENT '真实姓名',
  departid    int(11) default 0 COMMENT '部门/11111/dict',
  usertype    int(11) DEFAULT '2' comment '类型//select/1,管理员,2,普通用户,3,前台用户,4,第三方用户',
  state       int(11) DEFAULT '10' comment '状态',
  thirdid	  varchar(200) DEFAULT NULL COMMENT '第三方ID',
  endtime     varchar(32) DEFAULT NULL COMMENT '结束时间',
  email       varchar(64) DEFAULT NULL COMMENT 'email',
  tel         varchar(32) DEFAULT NULL COMMENT '手机号',
  address     varchar(32) DEFAULT NULL COMMENT '地址',
  title_url   varchar(200) DEFAULT NULL COMMENT '头像地址',
  remark      varchar(1000) DEFAULT NULL COMMENT '说明',
  create_time  varchar(64) DEFAULT NULL COMMENT '创建时间',
  create_id  int(11) DEFAULT 0 COMMENT '创建者',
  PRIMARY KEY (userid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户';

/**
 * 菜单表
 */
drop table if exists sys_menu;
CREATE TABLE sys_menu
(
  id int(11) not null auto_increment comment 'id',
  parentid int(11) not null default 0 comment '父id',
  name varchar(200) not null default '' comment '名称/11111',
  urlkey varchar(256) default null comment '菜单key',
  url varchar(256) default null comment '链接地址',
  status int(11) default '1' comment '状态//radio/2,隐藏,1,显示',
  type int(11) DEFAULT '1' COMMENT '类型//select/1,根目录,2,a标签,3,a标签_blank,4,外部url',
  sort  int(11) default '1' comment '排序',
  level int(11) default 1 comment '级别', 
  create_time  varchar(64) DEFAULT NULL COMMENT '创建时间',
  create_id  int(11) DEFAULT 0 COMMENT '创建者',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单';

/**
 * 角色和菜单关联表
 */
drop table if exists sys_role;
CREATE TABLE sys_role
(
  id int(11) not null auto_increment comment 'id',
  name varchar(200) not null default '' comment '名称/11111/',
  status int(11) default 1 comment '状态//radio/2,隐藏,1,显示',
  sort  int(11) default '1' comment '排序', 
  remark text default null comment '说明//textarea',
  create_time  varchar(64) DEFAULT NULL COMMENT '创建时间',
  create_id  int(11) DEFAULT 0 COMMENT '创建者',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色';

/**
 * 角色菜单关联表
 */
drop table if exists sys_role_menu;
CREATE TABLE sys_role_menu
(
  id int(11) not null auto_increment comment 'id',
  roleid int(11) not null comment '角色id',
  menuid int(11) not null comment '菜单id', 
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色和菜单关联';

/**
 * 用户和角色关联表
 */
drop table if exists sys_user_role;
CREATE TABLE sys_user_role
(
  id int(11) not null auto_increment comment 'id',
  userid int(11) not null comment '用户id', 
  roleid int(11) not null comment '角色id',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户和角色关联';


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

/************************ 系统表分界线 **********************************/

/**
 * 数据
 */
-- 部门
INSERT INTO `sys_department` VALUES ('1', '系统承建单位', '99', 'system', '15888888888', '2016-06-06 06:06:06', '1');
INSERT INTO `sys_department` VALUES ('2', '注册用户', '88', '无人', '15888888888', '2015-04-28 22:39:34', '1');
INSERT INTO `sys_department` VALUES ('3', '第三方用户', '90', '无', null, '2015-06-01 12:39:41', '1');

-- 用户
INSERT INTO `sys_user` VALUES ('1', 'admin', 'fermMZHOFmIGqw+NL0Ddcw==', '系统管理员', 1,1, 0, null, null, null, null, null, null, null, '2016-06-06 06:06:06',1);
-- 数据字典
INSERT INTO `sys_dict` VALUES ('2', '文章类型', 'articleType', null);
INSERT INTO `sys_dict` VALUES ('11', '目录类型', 'folderType', null);
INSERT INTO `sys_dict` VALUES ('100', '系统参数', 'systemParam', null);
INSERT INTO `sys_dict` VALUES ('101', '友情链接类型', 'friendlyLinkType', null);

INSERT INTO `sys_dict_detail` VALUES ('1', 'folderType', '目录', '1', '1', null, null, null, null, '2015-01-30', '1');
INSERT INTO `sys_dict_detail` VALUES ('2', 'folderType', 'a标签', '2', '2', null, null, null, null, '2015-01-30', '1');
INSERT INTO `sys_dict_detail` VALUES ('3', 'folderType', 'a标签target', '3', '3', null, null, null, null, '2015-01-30', '1');
INSERT INTO `sys_dict_detail` VALUES ('4', 'folderType', 'html标签', '4', '4', null, null, null, null, '2015-01-30', '1');
INSERT INTO `sys_dict_detail` VALUES ('11', 'articleType', '正常', '1', '1', null, null, null, null, '2015-01-30', '1');
INSERT INTO `sys_dict_detail` VALUES ('12', 'articleType', '预览', '2', '2', null, null, null, null, '2015-01-30', '1');
INSERT INTO `sys_dict_detail` VALUES ('13', 'articleType', '程序', '3', '3', null, null, null, null, '2015-01-30', '1');
INSERT INTO `sys_dict_detail` VALUES ('21', 'friendlyLinkType', '友情链接', null, '1', null, null, null, null, '2015-05-06 15:18:59', '1');
INSERT INTO `sys_dict_detail` VALUES ('22', 'friendlyLinkType', '关于我们', null, '2', null, null, null, null, '2015-05-06 15:19:20', '1');
INSERT INTO `sys_dict_detail` VALUES ('101', 'systemParam', 'FLY的狐狸', '1', '1', null, null, null, null, '2015-01-30', '1');
INSERT INTO `sys_dict_detail` VALUES ('102', 'systemParam', 'Jflyfox博客', '2', '1', null, null, null, null, '2015-01-30', '1');
-- 菜单
INSERT INTO `sys_menu` VALUES ('1', '0', '系统管理', 'system', null, '1', '1', '99', '1', '2015-04-27 17:28:06', '1');
INSERT INTO `sys_menu` VALUES ('2', '1', '组织机构', 'department', 'department/list', '1', '1', '10', '2', '2015-04-27 17:28:25', '1');
INSERT INTO `sys_menu` VALUES ('3', '1', '用户管理', 'user', 'user/list', '1', '1', '12', '2', '2015-04-27 17:28:46', '1');
INSERT INTO `sys_menu` VALUES ('4', '1', '角色管理', 'role', 'role/list', '1', '1', '14', '2', '2015-04-27 17:29:13', '1');
INSERT INTO `sys_menu` VALUES ('5', '1', '菜单管理', 'menu', 'menu/list', '1', '1', '16', '2', '2015-04-27 17:29:43', '1');
INSERT INTO `sys_menu` VALUES ('6', '1', '数据字典', 'dict', 'dict/list', '1', '1', '18', '2', '2015-04-27 17:30:05', '1');
INSERT INTO `sys_menu` VALUES ('7', '0', '联系人管理', 'contact', 'contact/list', '1', '1', '19', '1', '2015-04-28 12:38:04', '1');
INSERT INTO `sys_menu` VALUES ('8', '0', '目录管理', 'folder', 'folder/list', '1', '1', '10', '1', '2015-04-28 22:34:46', '1');
INSERT INTO `sys_menu` VALUES ('9', '0', '文章管理', 'article', 'article/list', '1', '1', '12', '1', '2015-04-28 22:35:24', '1');
INSERT INTO `sys_menu` VALUES ('10', '0', '友情链接', 'friendlylink', 'friendlylink/list', '1', '1', '14', '1', '2015-04-28 22:35:56', '1');
INSERT INTO `sys_menu` VALUES ('11', '0', '访问量统计', 'pageview', 'pageview', '1', '1', '16', '1', '2015-04-28 22:36:34', '1');
INSERT INTO `sys_menu` VALUES ('12', '0', '回复管理', 'comment', 'comment/list', '1', '1', '13', '1', '2015-04-28 22:36:34', '1');
INSERT INTO `sys_menu` VALUES ('13', 1, '系统操作', 'operation', 'operation', '1', '1', '20', '2', '2015-05-06 11:41:33', '1');

-- 友情链接
INSERT INTO `tb_friendlylink` VALUES ('1', '网站首页', 'http://www.jflyfox.com/mtg', '1', '1', '22', null, '2015-04-24 15:03:02', '1');
INSERT INTO `tb_friendlylink` VALUES ('2', '捐赠我们', 'http://www.jflyfox.com/mtg/front/about/351.html', '2', '1', '22', null, '2015-04-24 15:27:36', '1');
INSERT INTO `tb_friendlylink` VALUES ('3', '关于我们', 'http://www.jflyfox.com/mtg/front/about/352.html', '3', '1', '22', null, '2015-04-24 15:28:56', '1');
INSERT INTO `tb_friendlylink` VALUES ('4', '给我写信', 'http://mail.qq.com/cgi-bin/qm_share?t=qm_mailme&email=allcU1tTW15dWiobG0QJBQc', '4', '1', '22', null, '2015-04-24 15:29:12', '1');
INSERT INTO `tb_friendlylink` VALUES ('5', 'Jfinal', 'http://www.jfinal.com/', '12', '1', '21', null, '2015-05-06 16:13:40', '1');
INSERT INTO `tb_friendlylink` VALUES ('6', 'Beetl', 'http://www.ibeetl.com/', '14', '1', '21', null, '2015-05-06 16:14:37', '1');
INSERT INTO `tb_friendlylink` VALUES ('7', 'OsChina', 'http://www.oschina.net/', '11', '1', '21', null, '2015-05-06 16:15:03', '1');

-- 目录数据
INSERT INTO `tb_folder` VALUES ('1', '首页', '', '门头沟信息网', '1', '1', '1', null, '2015-01-28 16:54:03', '2015-01-30 13:24:58', '1');
INSERT INTO `tb_folder` VALUES ('6', '后台管理', '', null, '90', '1', '3', 'admin', '2015-05-24 15:47:32', '2015-01-30 13:24:58', '1');
INSERT INTO `tb_folder` VALUES ('90', '关于我们', '', null, '81', '1', '1', null, '2015-05-26 16:40:46', '2015-05-26 10:36:30', '1');