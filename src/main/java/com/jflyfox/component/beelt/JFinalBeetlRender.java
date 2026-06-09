package com.jflyfox.component.beelt;

import com.jfinal.render.Render;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;

import java.io.IOException;
import java.io.Writer;
import java.util.Enumeration;

/**
 * beetl render
 *
 * @author jflyfox
 * @date 2019/10/30
 */
public class JFinalBeetlRender extends Render {
    GroupTemplate groupTemplate;

    public JFinalBeetlRender(GroupTemplate groupTemplate, String view) {
        super.setView(view);
        this.groupTemplate = groupTemplate;
    }

    @Override
    public void render() {
        this.response.setContentType("text/html; charset=" + getEncoding());
        Template template = groupTemplate.getTemplate(this.view);
        template.binding("request", this.request);
        template.binding("response", this.response);
        template.binding("session", this.request.getSession());
        template.binding("requestParameters", this.request.getParameterMap());
        template.binding("parameter", this.request.getParameterMap());
        Enumeration<String> attrs = this.request.getAttributeNames();
        while (attrs.hasMoreElements()) {
            String name = attrs.nextElement();
            template.binding(name, this.request.getAttribute(name));
        }
        try (Writer writer = this.response.getWriter()) {
            template.renderTo(writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
