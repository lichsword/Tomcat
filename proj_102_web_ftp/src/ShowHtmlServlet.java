import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created with IntelliJ IDEA.
 * User: lichsword
 * Date: 14-3-11
 * Time: 下午2:53
 * To change this template use File | Settings | File Templates.
 */
public class ShowHtmlServlet implements Servlet {

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("page enter...");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
//        servletResponse.setContentType("text/html"); //解决中文乱码，这里还是有" "（双引号）
//        servletResponse.setCharacterEncoding("UTF-8");

//        PrintWriter out = servletResponse.getWriter();
        servletResponse.setContentType("text/html");
        servletResponse.setCharacterEncoding("utf-8");

        PrintWriter out = servletResponse.getWriter();
        // html struct
//      out.println("<!DOCTYPE html>");
        out.println("<!DOCTYPE HTML PUBLIC\"-//W3C//DTD HTML 4.01 Transitional//EN\">");
        out.println("<html>");

        out.println("<head>");
        out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">");
        out.println("<title>firefox显示不出来呢？</title>");
        out.println("</head>");
        // header--end

        out.println("<body>");

        out.println("<table border=\"1\">");
        out.println("<tr>");
        out.println("<th>Heading</th>");
        out.println("<th>Another Heading</th>");
        out.println("</tr>");
        out.println("<tr>");
        out.println("<td>row 1, cell 1</td>");
        out.println("<td>row 1, cell 2</td>");
        out.println("</tr>");
        out.println("<tr>");
        out.println("<td>row 2, cell 1</td>");
        out.println("<td>row 2, cell 2</td>");
        out.println("</tr>");
        out.println("</table>");
        // body--end
        out.println("</body>");
        out.println("</html>");
    }

    @Override
    public String getServletInfo() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void destroy() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
