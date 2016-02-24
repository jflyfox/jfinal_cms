# jfinal cms
------------------------
> 1. jfinal cms，采用了简洁强大的JFinal作为web框架，模板引擎用的是beetl，数据库用mysql，前端bootstrap框架。 
> 2. 后台模块包含：栏目管理，栏目公告，栏目滚动图片，文章管理，回复管理，意见反馈，我的相册，相册管理，图片管理，专辑管理，视频管理，缓存更新，友情链接，访问统计，联系人管理，组织机构管理，用户管理，角色管理，菜单管理，数据字典管理。
> 3. 后端模板支持：bootstrap默认样式、bootstrap黑色样式和flat-ui样式
> 4. 前端模板支持：默认内容发布、官网模板、图片模板和视频模板

#### 管理地址：http://${ip:port}/${project_name}/admin  ####
#### 测试账号: admin/admin123 或 test/123456  ####

# 平台部署和配置说明 #
------------------------
> 1. 下载项目代码，安装jdk、maven、mysql。
> 2. 在项目目录下运行mvn install，提示BUILD SUCCESS即可。
> 3. 创建mysql用户和数据库，运行/jfinal_cms/sql下对应jfinal_cms.sql。
> 4. 数据库配置文件：/jfinal_cms/src/main/resources/conf/db.properties
> 5. 如需要oauth2的，设置src/conf/oauth.properties
> 6. 通过config.properties的ATTR.PATH_PC=/template/mtg配置可以切换模板，记得数据库数据对应哦。
* 如果是/template/mtg模板，请运行jfinal_cms.sql脚本
* 如果是/template/website模板，请运行jfinal_cms_website.sql脚本
* 如果是/template/photo模板，请运行jfinal_cms_photo.sql脚本
* 如果是/template/video模板，请运行jfinal_cms_video.sql脚本
> 7.运行：mvn tomcat:run

#### 网站地址：[http://www.jflyfox.com/mtg](http://www.jflyfox.com/mtg) ####
![网站](http://static.oschina.net/uploads/img/201601/21022316_Nk5M.gif "jfinal cms")
#### 网站官网模板：[http://www.jflyfox.com/website](http://www.jflyfox.com/website) ####
![官网](http://static.oschina.net/uploads/img/201601/21022316_XkxY.gif "jfinal cms")
#### 相册管理模板：[http://www.jflyfox.com/photo](http://www.jflyfox.com/photo) ####
![官网](http://i11.tietuku.com/2428dbfd83dee15b.gif "jfinal cms")
#### 视频管理模板：[http://www.jflyfox.com/video](http://www.jflyfox.com/photo) ####
![官网](http://i11.tietuku.com/1ef2bd3516244ff3.gif "jfinal cms")

#### 后台页面主题： ####
![后台管理](http://i4.tietuku.com/3fd03e19f8c4e33b.gif "jfinal cms")

# 鸣谢
 1. [JFinal](http://www.oschina.net/p/jfinal)
 2. [beetl](http://ibeetl.com/community/)
 3. [oschina](http://www.oschina.net/)

# 开源赞助
#### 支付宝账号：15801297319 张彪  ####
![OSC@GIT](http://static.oschina.net/uploads/space/2015/0213/104940_ZKNb_166354.png "开源赞助我(支付宝)")