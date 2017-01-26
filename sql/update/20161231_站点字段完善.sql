ALTER TABLE `tb_site`
ADD COLUMN `thumbnail`  varchar(256) NULL COMMENT '缩略图' AFTER `site_article_id`,
ADD COLUMN `remark`  varchar(1000) NULL COMMENT '备注' AFTER `status`,
MODIFY COLUMN `status`  int(2) NULL DEFAULT '1' COMMENT '状态//radio/2,禁用,1,启用' AFTER `sort`,
ADD COLUMN `site_defalut`  int(2) NULL DEFAULT '2' COMMENT '默认站点：1,是,2,否' AFTER `status`;

INSERT INTO `sys_config` VALUES ('9', '站点参数', 'siteParam', '0', null, '0', '12', '2016-12-31 16:27:12', '1', '2016-12-31 16:27:12', '1');
INSERT INTO `sys_config` VALUES ('10', '多站点标示', 'SITE.MULTI.FLAG', 'false', null, '9', '211', '2016-12-31 16:28:02', '1', '2016-12-31 16:28:02', '1');
INSERT INTO `sys_config` VALUES ('11', '站点根目录', 'SITE.TEMPLATE.PATH', '/template/', null, '9', '212', '2016-12-31 16:28:43', '1', '2016-12-31 16:28:43', '1');
INSERT INTO `sys_config` VALUES ('12', 'Session站点列表', 'SITE.SESSION.SITES', 'sites', null, '9', '213', '2016-12-31 16:30:17', '1', '2016-12-31 16:30:17', '1');
INSERT INTO `sys_config` VALUES ('13', 'Session站点', 'SITE.SESSION.SITE', 'site', null, '9', '214', '2016-12-31 16:30:38', '1', '2016-12-31 16:30:38', '1');

update tb_site set site_defalut = 1 where id = 5;

INSERT INTO `sys_menu` VALUES ('31', '1', '参数配置', 'config', 'system/config', '1', '1', '97', '2', '2016-12-17 23:34:13', '1');
