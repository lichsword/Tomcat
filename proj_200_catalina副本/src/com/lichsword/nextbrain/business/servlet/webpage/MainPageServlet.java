package com.lichsword.nextbrain.business.servlet.webpage;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created with IntelliJ IDEA.
 * User: lichsword
 * Date: 14-3-18
 * Time: 下午2:35
 * <p/>
 * TODO
 */
public class MainPageServlet implements Servlet {
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
        // html struct
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">");
        out.println("<title>调研跟踪系统</title>");
//        out.println("<script src=\"http://ajax.googleapis.com/ajax/libs/jquery/1.8.0/jquery.min.js\">"); // google-js
        out.println("<script src=\"http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.0.js\">");// microsoft-js
        out.println("</script>");
        // js import --- end
        out.println("<script type=\"text/javascript\">");

        out.println("$(document).ready(function(){");
        out.println("$(\"#get\").click(function(){");
        out.println("$.get(\"/servlet/db\", null);");
        out.println("});");
        out.println("});");
        out.println("</script>");
        // js fun define.
        out.println("</head>");
        // header--end
        out.println("<body>");
        out.println("<button id=\"get\" type=\"button\">get请求数据</button>");
        out.println("<div id=\"div1\"></div>");
        // button --- end
        // body--end
        out.println("</body>");
        out.println("</html>");
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
