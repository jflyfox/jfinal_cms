ALTER TABLE `tb_article`
ADD COLUMN `file_url`  varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL AFTER `image_net_url`,
ADD COLUMN `file_name`  varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL AFTER `file_url`;