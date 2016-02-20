INSERT INTO `sys_menu` VALUES ('26', '22', '视频专辑管理', 'videoalbum', 'admin/videoalbum/list', '1', '1', '19', '2', '2016-02-16 16:25:08', '1');
INSERT INTO `sys_menu` VALUES ('27', '22', '视频管理', 'video', 'admin/video/list', '1', '1', '20', '2', '2016-02-16 16:25:37', '1');

SET FOREIGN_KEY_CHECKS=0;

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
