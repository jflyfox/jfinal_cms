ALTER TABLE `sys_config`
MODIFY COLUMN `name`  varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称' AFTER `id`;

INSERT INTO `sys_config` VALUES ('15', '文件备份参数', 'backupParam', '0', null, '0', '10', '2017-04-05 03:49:21', '1', '2017-04-05 03:49:21', '1');
INSERT INTO `sys_config` VALUES ('16', '文件备份类型名称', 'backup.name', 'filemanger', null, '15', '10', '2017-04-05 03:50:12', '1', '2017-04-05 03:50:12', '1');
INSERT INTO `sys_config` VALUES ('17', '文件系统备份路径', 'backup.filemanger.path', 'D:\\test', null, '15', '10', '2017-04-05 03:50:42', '1', '2017-04-05 03:50:42', '1');
INSERT INTO `sys_config` VALUES ('18', '阿里云存储bucketname', 'backup.oss.bucketname', 'jflyfox', null, '15', '10', '2017-04-05 22:58:05', '1', '2017-04-05 22:58:05', '1');
INSERT INTO `sys_config` VALUES ('19', '阿里云存储endpoint', 'backup.oss.endpoint', 'http://oss-cn-beijing.aliyuncs.com', null, '15', '10', '2017-04-05 22:59:01', '1', '2017-04-05 22:59:01', '1');
INSERT INTO `sys_config` VALUES ('20', '阿里云存储ID', 'backup.oss.id', '', null, '15', '10', '2017-04-05 22:59:24', '1', '2017-04-05 22:59:24', '1');
INSERT INTO `sys_config` VALUES ('21', '阿里云存储KEY', 'backup.oss.key', '', null, '15', '10', '2017-04-05 22:59:51', '1', '2017-04-05 22:59:51', '1');


DROP TABLE IF EXISTS `sys_file_upload`;
CREATE TABLE `sys_file_upload` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(256) NOT NULL DEFAULT '' COMMENT '名称',
  `path` varchar(512) NOT NULL DEFAULT '' COMMENT '文件路径',
  `factpath` varchar(512) NOT NULL COMMENT '实际路径',
  `ext` varchar(32) NOT NULL COMMENT '后缀',
  `originalname` varchar(256) NOT NULL COMMENT '原名称',
  `type` int(11) NOT NULL DEFAULT '9' COMMENT '类型',
  `size` int(11) NOT NULL DEFAULT '0' COMMENT '大小',
  `remark` varchar(256) DEFAULT NULL COMMENT '描述',
  `business_type` int(11) NOT NULL DEFAULT '1' COMMENT '业务类型',
  `update_time` varchar(64) DEFAULT NULL COMMENT '更新时间',
  `update_id` int(11) DEFAULT '0' COMMENT '更新人',
  `create_time` varchar(64) DEFAULT NULL COMMENT '创建时间',
  `create_id` int(11) DEFAULT '0' COMMENT '创建者',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文件上传记录';
