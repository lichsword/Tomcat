package com.lichsword.nextbrain.business.servlet.webpage;


import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created with IntelliJ IDEA.
 * User: lichsword
 * Date: 14-3-18
 * Time: 下午4:14
 * <p/>
 * TODO
 */
public class TestPageServlet implements Servlet {

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        // TODO
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;  // TODO
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String type = response.getContentType();
//        out.println("type=" + type);
//        response.setContentType("text/html");
        response.setContentType("text/html;charset=UTF-8");
        // html struct
        out.println("<!DOCTYPE HTML>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta http-equiv=\"content-type\" content=\"text/html;charset=utf-8\" >");
        out.println("<title>测试页面</title>");
        out.println("</head>");
        // header--end
        out.println("<body>");
        out.println("<p>hello word</p>");
        // button --- end
        // body--end
        out.println("</body>");
        out.println("</html>");

        out.flush();
        out.close();
    }


    @Override
    public String getServletInfo() {
        return null;  // TODO
    }

    @Override
    public void destroy() {
        // TODO
    }
}
