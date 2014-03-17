import com.lichsword.nextbrain.business.ArticleManager;
import com.lichsword.nextbrain.db.table.NBArticle;
import com.lichsword.nextbrain.webpage.MainPage;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: lichsword
 * Date: 14-3-11
 * Time: 下午2:36
 * To change this template use File | Settings | File Templates.
 */
public class DBServlet implements Servlet {

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("[INFO]init");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("[INFO]from service");
        PrintWriter out = servletResponse.getWriter();
        ArrayList<NBArticle> list = ArticleManager.getInstance().queryAllArticle();
        if (null == list || 0 == list.size()) return;

        out.println("size=" + list.size());
        for (NBArticle item : list) {
            System.out.println(item.dump());
            out.println(item.dump());
        }

        MainPage page = new MainPage(servletResponse);
        // TODO
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
