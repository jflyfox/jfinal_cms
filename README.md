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


项目源码地址：
------------------------

github地址：https://github.com/jflyfox/jfinal_cms

码云地址：https://git.oschina.net/flyfox/jfinal_cms

API Clinet 项目源码地址：
------------------------

github地址：https://github.com/jflyfox/jfinal_cms_api_client

码云地址：https://git.oschina.net/jflyfox/jfinal_cms_api_client

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

support
------------------------

项目的发展，离不开大家得支持~！~

![jflyfox](http://blog.jflyfox.com/static/images/common/pay_weixin.jpg "Open source support(alipay)")

![jflyfox](http://blog.jflyfox.com/static/images/common/pay_alipay.jpg "Open source support(weixin)")


捐赠名单
------------------------

| 名字      | 金额   |  备注  | 时间  |
| :-------: |:----: | :-----:|----- |-----|
| 弱水穿云天  | ￥50.00  | 支付宝捐赠    | 2017-04-28 10:17|
| 牛牛  | ￥100.00  | 微信捐赠    | 2017-04-17 17:36|
| 2001来北京的麦田  | ￥50.00  | 微信捐赠    | 2017-03-09 16:58|
| 今生的你  | ￥20.00  | 支付宝捐赠    | 2017-02-13 12:32|
| 建强  | ￥500.00  | 支付宝捐赠    | 2017-01-19 23:04|
| 小军  | ￥10.00  | 支付宝捐赠    | 2016-11-30 22:58|
| 小军  | ￥10.00  | 支付宝捐赠    | 2016-11-19 09:34|
| 郑州誉品电子商务有限公司  | ￥300.00  | 支付宝捐赠    | 2016-09-23 14:13|
| 周克涛  | ￥10.00  | 支付宝捐赠    | 2016-08-12 19:43|
| 扬某   | ￥1.00  | 支付宝捐赠    |  2016-06-29  14:12|
| magicbug   | ￥500.00  | 支付宝捐赠    |  2016-06-20  15:14|
| 杜育轩   | ￥100.00  | 支付宝捐赠    |  2016-05-29  10:48|
| 谢协湃  | ￥20.00  | 支付宝捐赠    | 2016-05-01 22:33|
| 粪发涂墙  | ￥1.00  | 微信捐赠    | 2016-04-17 21:18|
| 胡海峰  | ￥10.00  | 微信捐赠    | 2016-04-12 15:23|
| 李守敬 | ￥10.00  | 支付宝捐赠    | 2016-03-10 17:20|
| 韩千叶  | ￥20.00  | 支付宝捐赠    | 2016-03-05 18:35|
| 神仙下凡  | ￥1.00  | 微信捐赠    | 2016-03-03 18:30|
| 张润佘  | ￥1.00  | 支付宝捐赠    | 2016-03-01 21:18|
| 李胜发  | ￥20.00  | 支付宝捐赠    | 2016-02-23 22:25|
| 贾小龙  | ￥20.00  | 微信捐赠    | 2016-02-20 15:20|
| 韩刚龙  | ￥20.00  | 支付宝捐赠    | 2016-02-10 16:17|
| 黄颖 | ￥10.00  | 支付宝捐赠    | 2016-02-08 16:25|
| 孔维源  | ￥20.00  | 支付宝捐赠    | 2016-02-07 18:40|
| 小猪  | ￥10.00  | 微信捐赠    | 2016-02-07 18:20|
| 田野  | ￥20.00  | 支付宝捐赠    | 2016-02-07 16:15|
| Allen  | ￥10.00  | 支付宝捐赠    | 2016-02-07 15:25|
| 飞龙在天  | ￥20.00  | 微信捐赠    | 2016-02-05 15:20|
| 仇国林  | ￥5.00  | 支付宝捐赠    | 2016-02-05 16:17|
| 李荣富  | ￥50.00  | 支付宝捐赠    | 2016-01-05 14:15|
| 夏舒征  | ￥1.00  | 微信捐赠    | 2015-12-03 18:30|
| 郭俊立  | ￥10.00  | 支付宝捐赠    | 2015-11-23 21:25|
| 侯善稚  | ￥5.00  | 支付宝捐赠    | 2015-11-10 12:30|
| Dave  | ￥20.00  | 微信捐赠    | 2015-09-05 15:10|
| 李具匡  | ￥1.00  | 支付宝捐赠    | 2015-08-03 23:30|
| 文子隐  | ￥20.00  | 微信捐赠    | 2015-07-23 19:20|
| 何望  | ￥10.00  | 支付宝捐赠    | 2015-07-10 17:29|
| 李新革  | ￥20.00  | 支付宝捐赠    | 2015-05-07 20:00|
| 苏某  | ￥20.00  | 支付宝捐赠    | 2015-04-01 20:18|
| 寒云  | ￥10.00  | 支付宝捐赠    | 2015-02-01 20:18|
| lucky  | ￥10.00  | 支付宝捐赠    | 2015-01-20 15:10|
| 王锋  | ￥10.00  | 支付宝捐赠    | 2015-01-10 22:00|