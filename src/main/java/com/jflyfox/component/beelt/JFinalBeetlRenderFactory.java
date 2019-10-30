package com.jflyfox.component.beelt;

import com.jfinal.kit.PathKit;
import com.jfinal.render.Render;
import com.jfinal.render.RenderFactory;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.ResourceLoader;
import org.beetl.core.resource.WebAppResourceLoader;

import java.io.IOException;

/**
 * beetl render factory
 *
 * @author jflyfox
 * @date 2019/10/30
 */
public class JFinalBeetlRenderFactory extends RenderFactory {
    public GroupTemplate groupTemplate = null;

    public JFinalBeetlRenderFactory() {
    }

    @Override
    public Render getRender(String view) {
        return new JFinalBeetlRender(this.groupTemplate, view);
    }

    public void config() {
        String root = PathKit.getWebRootPath();
        WebAppResourceLoader resourceLoader = new WebAppResourceLoader(root);
        this.config(resourceLoader);
    }

    public void config(String root) {
        WebAppResourceLoader resourceLoader = new WebAppResourceLoader(root);
        this.config(resourceLoader);
    }

    public void config(ResourceLoader rs) {
        if (this.groupTemplate != null) {
            this.groupTemplate.close();
        }

        try {
            Configuration cfg = Configuration.defaultConfiguration();
            this.groupTemplate = new GroupTemplate(rs, cfg);
        } catch (IOException var3) {
            throw new RuntimeException("加载GroupTemplate失败", var3);
        }
    }
}
