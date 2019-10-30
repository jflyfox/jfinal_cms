package com.jflyfox.component.beelt;

import com.jfinal.render.Render;
import org.beetl.core.GroupTemplate;
import org.beetl.ext.web.WebRender;

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
        WebRender web = new WebRender(this.groupTemplate);
        web.render(this.view, this.request, this.response, (Object[])null);
    }
}
