import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created with IntelliJ IDEA.
 * User: lichsword
 * Date: 14-3-11
 * Time: 下午3:14
 * To change this template use File | Settings | File Templates.
 */
public class JSServlet implements Servlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        PrintWriter out = servletResponse.getWriter();
        // html struct
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"/>");
        out.println("<title>调研跟踪系统</title>");
//        out.println("<script src=\"http://ajax.googleapis.com/ajax/libs/jquery/1.8.0/jquery.min.js\">"); // google-js
        out.println("<script src=\"http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.0.js\">");// microsoft-js
        out.println("</script>");
        // js import --- end
        out.println("<script>");
        out.println("document.write(\"<h1>This is a heading</h1>\");");
        out.println("</script>");
        // js fun define.
        out.println("</head>");
        // header--end
        out.println("<body>");
        // nothing in body
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
