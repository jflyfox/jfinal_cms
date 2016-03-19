ALTER TABLE `tb_article`
ADD COLUMN `jump_url`  varchar(256) NULL COMMENT '跳转地址' AFTER `sort`;

ALTER TABLE `tb_article`
ADD COLUMN `approve_status`  int(11) NULL COMMENT '审核状态' AFTER `file_name`;

update `tb_article` set approve_status = 10;

INSERT INTO `sys_menu` VALUES ('29', '18', '文章审核', 'article_approve', 'admin/article/list_approve', '1', '1', '15', '2', '2016-03-16 00:21:12', '1');
