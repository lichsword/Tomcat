package com.lichsword.nextbrain.business.servlet.webpage;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created with IntelliJ IDEA.
 * User: lichsword
 * Date: 14-3-18
 * Time: 下午6:23
 * <p/>
 * TODO
 */
public class ArticlePageServlet implements Servlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        // TODO
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;  // TODO
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        PrintWriter out = res.getWriter();
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
        out.println("\t$(\"#get\").click(function(){");
        out.println("\t\t$.get(\"/servlet/db\", \nfunction(data,status){\n"
//                + "var node = data;"
                + "\t\t\tvar element=document.getElementById(\"div1\");\n"
                //+ "\t\t\telement.appendChild(data);\n"
                + "element.innerHTML = data.documentElement.outerHTML;"
                + "console.log(data);"
                // dialog
                + "\t\t\talert(\"Data: \" + data + \"\\nStatus: \" + status);\n" +
                "\t\t});");
        out.println("\t});");
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
