package com.lichsword.nextbrain.core.page;

import javax.servlet.ServletRequest;
import java.io.PrintWriter;

/**
 * Created with IntelliJ IDEA.
 * User: lichsword
 * Date: 14-3-23
 * Time: 下午10:17
 * <p/>
 * TODO
 */
public interface IPage {

    public void onPreRender(ServletRequest servletRequest);
    public void onRenderPage(PrintWriter out);

}
