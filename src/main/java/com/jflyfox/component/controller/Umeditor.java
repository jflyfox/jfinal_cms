package com.jflyfox.component.controller;

import com.jflyfox.component.controller.base.BaseUmeditor;
import com.jflyfox.component.controller.base.Uploader;
import com.jflyfox.jfinal.component.annotation.ControllerBind;
import com.jflyfox.modules.admin.error.TbError;
import com.jflyfox.system.user.SysUser;
import com.jflyfox.util.Config;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@ControllerBind(controllerKey = "umeditor")
public class Umeditor extends BaseUmeditor {

    // 文件大小常量, 单位kb
    private static final int MAX_SIZE = Config.getToInt("person.MaxSize");
    // 文件类型限制
    private static final String[] fileType = { ".gif", ".png", ".jpg", ".jpeg", ".bmp" };
    // 文件上传路径umeditor下
    private static final String DIR = Config.getStr("person.SavePath");

    private static final Map<Integer, PersonFileLimit> map = new HashMap<Integer, PersonFileLimit>();

    /**
     * 图片上传
     *
     */
    public void imageup() {
        String[] fileType = { ".gif", ".png", ".jpg", ".jpeg", ".bmp" };

        List<String> savePath = Arrays.asList(Config.getStr("savePath").split(","));

        // 获取存储目录结构
        if (getPara("fetch") != null) {
            // 构造json数据
            Iterator<String> iterator = savePath.iterator();
            String dirs = "[";
            while (iterator.hasNext()) {
                dirs += "'" + iterator.next() + "'";
                if (iterator.hasNext()) {
                    dirs += ",";
                }

            }
            dirs += "]";
            renderJavascript("updateSavePath( " + dirs + " );");
            return;
        }

        Uploader up = new Uploader(getRequest());
        // 获取前端提交的path路径
        String dir = getPara("dir");
        // 普通请求中拿不到参数， 则从上传表单中拿
        if (dir == null) {
            dir = up.getParameter("dir");
        }

        if (dir == null || "".equals(dir)) {
            // 赋予默认值
            dir = savePath.get(0);
            // 安全验证
        } else if (!savePath.contains(dir)) {
            renderText("{'state':'\\u975e\\u6cd5\\u4e0a\\u4f20\\u76ee\\u5f55'}");
            return;

        }

        try {
            up.setSavePath(dir);
            up.setAllowFiles(fileType);
            up.setMaxSize(500 * 1024); // 单位KB
            up.upload();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String result = "{\"name\":\"" + up.getFileName() + "\", \"originalName\": \"" + up.getOriginalName()
                + "\", \"size\": \"" + up.getSize() + "\", \"state\": \"" + up.getState() + "\", \"type\": \""
                + up.getType() + "\", \"url\": \"" + up.getUrl() + "\"}";
        result = result.replaceAll("\\\\", "\\\\");
        renderHtml(result);
    }

    /**
     * 图片上传
     *
     * @see 博文上传，必须严格限制
     *
     */
    public void personimageup() {
        // 文件限制处理
        SysUser user = (SysUser) getSessionUser();
        PersonFileLimit fileLimit = map.get(user.getUserid());
        if (fileLimit == null) {
            fileLimit = new PersonFileLimit();
            map.put(user.getUserid(), fileLimit);
        }
        fileLimit.add();

        String result = "";
        if (!fileLimit.isLegal()) {
            if (!fileLimit.isInsert()) {
                TbError error = new TbError();
                error.setUserid(user.getUserid());
                error.setType(TbError.FILE_UPLOAD_MORE);
                error.setIp(getIpAddr(getRequest()));
                error.setContent("上传数量：" //
                        + fileLimit.getNowHour() + "(" + fileLimit.getNowHourCount().get() + ")-" //
                        + fileLimit.getNowDay() + "(" + fileLimit.getNowDayCount().get() + ")");
                error.put("create_id", getSessionUser().getUserid());
                error.put("create_time", getNow());
                error.save();

                fileLimit.setInsert(true);
            }

            String state = "上传文件过快或者过多,如有问题请联系管理员~！~";
            result = "{\"state\": \"" + state + "\"}";
        } else {
            Uploader up = new Uploader(getRequest());
            // 获取前端提交的path路径
            try {
                // 知道哪个用户传的便于垃圾数据清理
                up.setPreFileName(user.getUserid() + "_");
                up.setSavePath(DIR);
                up.setAllowFiles(fileType);
                up.setMaxSize(MAX_SIZE); // 单位KB
                up.upload();
            } catch (Exception e) {
                e.printStackTrace();
            }
            result = "{\"name\":\"" + up.getFileName() + "\", \"originalName\": \"" + up.getOriginalName()
                    + "\", \"size\": \"" + up.getSize() + "\", \"state\": \"" + up.getState() + "\", \"type\": \""
                    + up.getType() + "\", \"url\": \"" + up.getUrl() + "\"}";
            result = result.replaceAll("\\\\", "\\\\");
        }
        renderHtml(result);
    }

    public String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
