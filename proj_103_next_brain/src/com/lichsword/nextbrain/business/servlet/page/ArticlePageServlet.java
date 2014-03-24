package com.lichsword.nextbrain.business.servlet.page;

import com.lichsword.nextbrain.core.page.HttpPage;

import java.io.PrintWriter;

/**
 * Created with IntelliJ IDEA.
 * User: lichsword
 * Date: 14-3-18
 * Time: 下午6:23
 * <p/>
 * TODO
 */
public class ArticlePageServlet extends HttpPage {

    public ArticlePageServlet() {
        setContentLength(1000); // TODO must be right real count.
        setContentType("text/html");
    }

    @Override
    public void renderPage(PrintWriter out) {

        // html struct
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">");
        out.println("<title>调研跟踪系统</title>");
//        out.println("<script src=\"http://ajax.googleapis.com/ajax/libs/jquery/1.8.0/jquery.min.js\">"); // google-js
        out.println("<script src=\"http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.0.js\">");// microsoft-js
        out.println("</script>");
        out.print("<script type=\"text/javascript\" src=\"./js/myscript.js\"></script>");
        // js import --- end
        out.println("<script type=\"text/javascript\">");

        out.println("$(document).ready(function(){");
        out.println("\t$(\"#btn_get\").click(function(){");
        out.println("\t\t$.get(\"/servlet/db\", \nfunction(data,status){\n"
                + "\t\t\tvar element=document.getElementById(\"div1\");\n"
//                + "\t\t\telement.innerHTML = data.outerHTML;\n"
//                + "\t\t\telement.innerHTML = data.documentElement.outerHTML;\n"
                + "\t\t\t$(\".div2\").append(data);\n"
                + "\t\t\tconsole.log(data);\n"
                + "\t\t\tconsole.log(status);\n"
//                + "\t\t\talert(\"Data: \" + data + \"\\nStatus: \" + status);\n"
                + "\t\t});");
        out.println("\t});");
        out.println("});");
        out.println("</script>");

        // js fun define.
        out.println("</head>");
        // header--end
        out.println("<body>");
        out.println("<button id=\"btn_get\" type=\"button\">查询</button>");
        out.println("<button id=\"btn_add\" type=\"button\">新增</button>");
        out.println("<button id=\"btn_delete\" type=\"button\">删除</button>");
        out.println("<div id=\"div1\"></div>");
        out.println("<div class=\"div2\"></div>");
        // button --- end
        // body--end
        out.println("</body>");
        out.println("</html>");

        out.flush();
    }
}