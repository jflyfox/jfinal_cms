jfinal cms
------------------------

> 1. jfinal cms，采用了简洁强大的JFinal作为web框架，模板引擎用的是beetl，数据库用mysql，前端bootstrap框架。 
> 2. 后台模块包含：栏目管理，栏目公告，栏目滚动图片，文章管理，回复管理，意见反馈，我的相册，相册管理，图片管理，专辑管理、视频管理、缓存更新，友情链接，访问统计，联系人管理，模板管理，组织机构管理，用户管理，角色管理，菜单管理，数据字典管理。
> 3. 后端模板支持：bootstrap默认样式、bootstrap黑色样式和flat-ui样式
> 4. 前端模板支持：默认内容发布、官网模板、图片模板和视频模板
> 5. jfinal cms交流群：568909653。 文档见doc/jfinal cms文档.docx

* 管理地址：http://${ip:port}/${project_name}/admin
* 测试账号: admin/admin123 或 test/123456

平台部署和配置说明
------------------------

> 1. 下载项目代码，安装jdk、maven、mysql。
> 2. 在项目目录下运行mvn install，提示BUILD SUCCESS即可。
> 3. 创建mysql用户和数据库，运行/jfinal_cms/sql下对应jfinal_cms_v4.sql。
> 4. 数据库配置文件：/jfinal_cms/src/main/resources/conf/db.properties
> 5. 如需要oauth2的，设置src/conf/oauth.properties
> 6. 运行：mvn tomcat:run
> 7. 默认站点通过配置src/conf/sites.properties文件中SITE.DEFAULT.ID即可实现。
> 8. 多站点各个模板的切换已通过系统中“站点管理”模块进行操作。站点管理是通过域名解析实现各个模板的对应。
> 9. 如果只使用单站点，可以将sites.properties文件中SITE.MULTI.FLAG = true改为false。然后通过config.properties的ATTR.PATH_PC=/template/mtg配置模板。


其他说明
------------------------

两个依赖项目jflyfox_base，jflyfox_jfinal。前者是String，Number,Date等公共方法封装，后者是对jfinal和一些公用方法的简单封装

源码地址：
> http://git.oschina.net/flyfox/jflyfox_jfinal 
> http://git.oschina.net/flyfox/jflyfox_base

演示效果截图
------------------------

#### 网站地址：[http://www.jflyfox.com/mtg](http://www.jflyfox.com/mtg) ####
![网站](http://static.oschina.net/uploads/img/201601/21022316_Nk5M.gif "jfinal cms")

#### 网站官网模板：[http://www.jflyfox.com/website](http://www.jflyfox.com/website) ####
![官网](http://static.oschina.net/uploads/img/201601/21022316_XkxY.gif "jfinal cms")

#### 相册管理模板：[http://www.jflyfox.com/photo](http://www.jflyfox.com/photo) ####
![官网](http://i11.tietuku.cn/2428dbfd83dee15b.gif "jfinal cms")

#### 视频管理模板：[http://www.jflyfox.com/video](http://www.jflyfox.com/photo) ####
![官网](http://i11.tietuku.cn/1ef2bd3516244ff3.gif "jfinal cms")

#### 后台页面主题： ####
![后台管理](http://i4.tietuku.cn/3fd03e19f8c4e33b.gif "jfinal cms")

鸣谢
------------------------

 1. [JFinal](http://www.oschina.net/p/jfinal)
 2. [beetl](http://ibeetl.com/community/)
 3. [oschina](http://www.oschina.net/)

开源赞助
------------------------

* 支付宝支付

![jflyfox](http://ww1.sinaimg.cn/mw690/3fc7e281jw1eqec436tzwj2074074mxr.jpg "Open source support(alipay)")

* 微信支付

![jflyfox](http://ww1.sinaimg.cn/mw690/3fc7e281jw1es3jr0k25xj20a50a5q3v.jpg "Open source support(weixin)")
