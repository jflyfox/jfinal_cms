jfinal cms
------------------------

> 1. jfinal cms，采用了简洁强大的JFinal作为web框架，模板引擎用的是beetl，数据库用mysql，前端bootstrap框架。 
> 2. 后台模块包含：栏目管理，栏目公告，栏目滚动图片，文章管理，回复管理，意见反馈，我的相册，相册管理，图片管理，专辑管理，视频管理，缓存更新，友情链接，访问统计，联系人管理，模板管理，组织机构管理，用户管理，角色管理，菜单管理，参数配置，数据字典管理。
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
> 7. 系统默认采用单站点模式，各个站点可以在“其他管理”下“站点管理”菜单方便的切换。
> 8. 如果使用多站点，可以在“系统管理”下“多站点标示”菜单中，将“多站点标示”项目修改为true。
> 9. 多站点需要设置各个站点对应的域名，通过域名解析到不同的站点模板。


其他说明
------------------------

两个依赖项目jflyfox_base，jflyfox_jfinal。前者是String，Number,Date等公共方法封装，后者是对jfinal和一些公用方法的简单封装

源码地址：
> http://git.oschina.net/flyfox/jflyfox_jfinal 
> http://git.oschina.net/flyfox/jflyfox_base

演示效果截图
------------------------


#### 网站CMS地址：[http://mtg.jflyfox.com/](http://mtg.jflyfox.com/) ####
![网站](http://static.oschina.net/uploads/img/201601/21022316_Nk5M.gif "jfinal cms")

#### 网站官网模板：[http://website.jflyfox.com/](http://website.jflyfox.com/) ####
![官网](http://static.oschina.net/uploads/img/201601/21022316_XkxY.gif "jfinal cms")

#### 博客模板模板：[http://blog.jflyfox.com/](http://blog.jflyfox.com) ####
![官网](http://static.oschina.net/uploads/space/2016/0622/002206_Rla0_166354.jpg "jfinal cms")

#### 相册管理模板：[http://photo.jflyfox.com/](http://photo.jflyfox.com/) ####
![官网](http://static.oschina.net/uploads/space/2016/0306/144741_ldOJ_166354.gif "jfinal cms")

#### 视频管理模板：[http://video.jflyfox.com/](http://video.jflyfox.com/) ####
![官网](http://static.oschina.net/uploads/space/2016/0306/144754_FXhR_166354.gif "jfinal cms")

#### 后台页面主题： ####
![后台管理](http://static.oschina.net/uploads/img/201601/28091447_rQtD.gif "jfinal cms")

鸣谢
------------------------

 1. [JFinal](http://www.oschina.net/p/jfinal)
 2. [beetl](http://ibeetl.com/community/)
 3. [oschina](http://www.oschina.net/)

# support
------------------------

![jflyfox](http://blog.jflyfox.com/static/images/common/pay_weixin.jpg "Open source support(alipay)")

![jflyfox](http://blog.jflyfox.com/static/images/common/pay_alipay.jpg "Open source support(weixin)")
