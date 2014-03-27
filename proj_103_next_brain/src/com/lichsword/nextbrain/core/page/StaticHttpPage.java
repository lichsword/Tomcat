package com.lichsword.nextbrain.core.page;

import com.lichsword.nextbrain.core.res.HtmlResource;

import javax.servlet.ServletRequest;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created with IntelliJ IDEA.
 * User: lichsword
 * Date: 14-3-24
 * Time: 上午11:07
 * <p/>
 * Static means html page content come from webroot/html/ directory.
 */
public class StaticHttpPage extends HttpPage {


    protected String uri;

    private HtmlResource html;

    public StaticHttpPage(String uri) {
        this.uri = uri;
        html = new HtmlResource(uri);
        try {
            setContentLength(html.getContentLength());
            setContentType("text/html");
        } catch (IOException e) {
            e.printStackTrace(); // TODO
            setContentLength(0);
        }
    }

    @Override
    public void onPreRender(ServletRequest servletRequest) {
        // TODO
    }

    @Override
    public void onRenderPage(PrintWriter out) {
        html.write(out);
    }
}
