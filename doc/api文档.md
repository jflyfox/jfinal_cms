## 接口文档
------------------------

* 接口方法扩展方便。只需要在接口IApiLogic中添加方法，完善各个版本实现即可。
* 支持多版本并行。只需要实现接口IApiLogic，集成上一个版本，将需要修改的方法重写即可。
* 支持接口开关、黑名单、版本控制功能。
* 支持接口登陆验证以及验证开关。
* 系统管理-》参数配置中，配置API.PARAM.ENCRYPT改为true，加入参数加密
* 系统管理-》参数配置中，配置API.LOGIN.VALID改为true，加入登陆验证
* 系统管理-》用户管理中，配置API账号密码，类型选择API用户，查看页面查看秘钥

## 接口使用说明
------------------------

* 公共请求参数

```java
String apiNo; // 接口码，匹配输出输入
Integer pageNo; // 页数
Integer pageSize; // 页码
String method; // 方法名
String version; // 版本
String apiUser; // 调用用户
String time; //  时间戳，年月日时分秒
String checkSum; // 校验和
String p; // 参数，需要先base64加密，再进行URL编码，编码格式使用utf-8
```

加密URLEncoder.encode(Base64.encode(p),"UTF-8");
解密URLDecoder.decode(Base64.decode(p),"utf-8")

* 参数说明及示例

```
1. 访问必须携带版本号
/api?version=1.0.0

2. 以下两种访问方式效果相同，建议使用第二种方法
/api/action?version=1.0.1&apiNo=1000000&pageNo=1&pageSize=1&method=pageArticleSite&time=20170314160401&p={siteId:1}
与
/api/action/pageArticleSite?version=1.0.1&apiNo=1000000&pageNo=1&pageSize=1&time=20170314160401&p={siteId:1}

3. 分页有默认值pageNo=1,pageSize=20

4. 登陆验证功能说明
> 1）通过login接口进行登陆，获取key。
> 2）其他接口调用需要携带两个公共请求参数：apiUser为用户名，checkSum为登陆接口返回key。
> 3）如果退出调用logout接口。

5. p为json参数，携带我们接口想要的自定义参数。
p={siteId:1,test:"ok"}
```

* 文档说明

```
暂无
```

## 接口
------------------------

####  测试接口

* 接口说明：测试
* 请求方式： **_GET/POST_**
* 请求地址：**_/api_**
* 请求参数

```
无
```

* 示例

```
/api?version=1.0.0
```

* 返回结果

```json
{
	data: {
		notice: "api is ok!"
	},
	code: 0,
	msg: "success"
}
```

####  调试接口

* 接口说明：开关调试日志
* 请求方式： **_GET/POST_**
* 请求地址：**_/api/debug_**
* 请求参数

```
无
```

* 示例

```
/api/debug?version=1.0.0&apiNo=1000000&time=20170314160401
```

* 返回结果

```json
{
	data: {
		debug: true
	},
	code: 0,
	msg: "success"
}
```
####  登陆接口

* 接口说明：获取配置信息
* 请求方式： **_GET/POST_**
* 请求地址：**_/api/action/login_**
* 请求参数

```
username:用户名
password:密码
```

* 示例

```
/api/action/login?version=1.0.1&apiNo=1000000&time=20170314160401&p={username:"admin",password:"123"}
```

* 返回结果

```json
{
	data: {
		key: "oTkt"
	},
	code: 0,
	msg: "success"
}
```

####  登出接口

* 接口说明：获取配置信息
* 请求方式： **_GET/POST_**
* 请求地址：**_/api/action/logout_**
* 请求参数

```
无
```

* 示例

```
/api/action/logout?version=1.0.1&apiNo=1000000&time=20170314160401&apiUser=admin&checkSum=YBrs
```

* 返回结果

```json
{
	data: {
		r: "ok"
	},
	code: 0,
	msg: "success"
}
```

####  配置接口

* 接口说明：获取配置信息
* 请求方式： **_GET/POST_**
* 请求地址：**_/api/action/config_**
* 请求参数

```
无
```

* 示例

```
/api/action/config?version=1.0.0&apiNo=1000000&time=20170314160401&apiUser=admin&checkSum=YBrs
```

* 返回结果

```json
{
	data: {
		test: "ok"
	},
	code: 0,
	msg: "success"
}
```

####  栏目列表接口

* 接口说明：根据站点ID获取所有栏目
* 请求方式： **_GET/POST_**
* 请求地址：**_/api/action/folders_**
* 请求参数

```
version:1.0.0 版本号
p:{siteId:1}
siteId:1 站点ID
```

* 示例

```
/api/action/folders?version=1.0.1&apiNo=1000000&time=20170314160401&apiUser=admin&checkSum=YBrs&p={siteId:2}
```

* 返回结果

```json
{
    data: {
        list: [
            {
                sort: 10,
                jump_url: null,
                status: 1,
                material_type: 102,
                site_id: 1,
                seo_title: "FLY的狐狸",
                type: 1,
                content: null,
                id: 251,
                update_id: 1,
                seo_description: "FLY的狐狸",
                update_time: "2016-04-07 01:13:40",
                create_id: 1,
                name: "首页",
                path: "home/home.html",
                create_time: "2016-04-07 01:13:40",
                seo_keywords: "FLY的狐狸",
                key: "home",
                parent_id: 0
            }
        ]
    },
    code: 0,
    msg: "success"
}
```

####  文章分页列表接口

* 接口说明：根据站点ID获取所有文章
* 请求方式： **_GET/POST_**
* 请求地址：**_/api/action/pageArticleSite_**
* 请求参数

```
version:1.0.0 版本号
pageNo:1 分页页码
pageSize:20 分页大小
p:{siteId:1}
siteId:1 站点ID
```

* 示例

```
/api/action/pageArticleSite?version=1.0.1&apiNo=1000000&time=20170314160401&apiUser=admin&checkSum=YBrs&pageNo=1&pageSize=20&p={siteId:2}
```

* 返回结果

```json
{
    data: {
        total: 5,
        list: [
            {
                count_comment: 0,
                is_comment: 1,
                publish_user: "系统管理员",
                sort: 2,
                jump_url: "http://mtg.jflyfox.com",
                status: "1",
                count_view: 1,
                type: 11,
                file_name: null,
                file_url: null,
                approve_status: 10,
                content: "<h3>门头沟信息网</h3> <p>全面的生活、新闻、美食、旅游、教育资讯</p>",
                id: 3276,
                folder_id: 251,
                title: "门头沟信息网",
                publish_time: "2016-04-07",
                update_time: "2016-04-07 01:17:15",
                create_id: 1,
                image_url: null,
                end_time: null,
                create_time: "2016-04-07 01:17:15",
                start_time: null,
                is_recommend: 2,
                image_net_url: "http://i4.tietuku.cn/6979a4ded13e456e.jpg"
            }
        ]
    },
    code: 0,
    msg: "success"
}
```

####  文章分页列表接口

* 接口说明：根据栏目ID获取所有文章
* 请求方式： **_GET/POST_**
* 请求地址：**_/api/action/pageArticle_**
* 请求参数

```
version:1.0.0 版本号
pageNo:1 分页页码
pageSize:20 分页大小
p:{folderId:1}
siteId:1 站点ID
```

* 示例

```
/api/action/pageArticle?version=1.0.1&apiNo=1000000&time=20170314160401&apiUser=admin&checkSum=YBrs&pageNo=1&pageSize=1&p={folderId:2}
```

* 返回结果

```json
{
    data: {
        total: 5,
        list: [
            {
                count_comment: 0,
                is_comment: 1,
                publish_user: "系统管理员",
                sort: 2,
                jump_url: "http://mtg.jflyfox.com",
                status: "1",
                count_view: 1,
                type: 11,
                file_name: null,
                file_url: null,
                approve_status: 10,
                content: "<h3>门头沟信息网</h3> <p>全面的生活、新闻、美食、旅游、教育资讯</p>",
                id: 3276,
                folder_id: 251,
                title: "门头沟信息网",
                publish_time: "2016-04-07",
                update_time: "2016-04-07 01:17:15",
                create_id: 1,
                image_url: null,
                end_time: null,
                create_time: "2016-04-07 01:17:15",
                start_time: null,
                is_recommend: 2,
                image_net_url: "http://i4.tietuku.cn/6979a4ded13e456e.jpg"
            }
        ]
    },
    code: 0,
    msg: "success"
}
```

####  文章接口

* 接口说明：根据栏目ID获取所有文章
* 请求方式： **_GET/POST_**
* 请求地址：**_/api/action/article_**
* 请求参数

```
version:1.0.0 版本号
pageNo:1 分页页码
pageSize:20 分页大小
p:{folderId:1}
siteId:1 站点ID
```

* 示例

```
/api/action/article?version=1.0.1&apiNo=1000000&time=20170314160401&apiUser=admin&checkSum=YBrs&p={articleId:1}
```

* 返回结果

```json
{
    data: {
        article: {
            count_comment: 123,
            publish_user: "系统管理员",
            is_comment: 1,
            sort: 1,
            jump_url: null,
            status: "2",
            count_view: 122,
            file_name: null,
            type: 12,
            approve_status: 10,
            file_url: null,
            id: 1,
            content: "<p>门头沟</p>",
            folder_id: 1,
            title: "门头沟",
            create_id: 1,
            update_time: "2015-01-28 17:29:55",
            publish_time: "2014-03-05",
            end_time: "2015-01-23",
            image_url: "download/image_url/20150529_102007_298104.jpg",
            create_time: "2015-01-28",
            start_time: "2015-01-29",
            is_recommend: 1,
            image_net_url: null
        }
    },
    code: 0,
    msg: "success"
}
```