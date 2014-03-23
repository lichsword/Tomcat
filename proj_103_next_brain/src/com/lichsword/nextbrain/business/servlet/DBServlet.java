package com.lichsword.nextbrain.business.servlet;

import com.lichsword.nextbrain.backup.Response;
import com.lichsword.nextbrain.business.ArticleManager;
import com.lichsword.nextbrain.db.table.NBArticle;
import com.lichsword.nextbrain.dialog.AddArticleDialog;
import com.lichsword.nextbrain.view.LinearLayout;
import com.lichsword.nextbrain.view.TableView;

import javax.servlet.*;
import java.io.IOException;
import java.io.OutputStream;
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
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {

//        Response response = (Response)res;
//        OutputStream outputStream = response.getOutputStreamOrigin();
//        if (null != outputStream) {
//            byte[] bytes = TempHeader.dumpResponseHeaderBytes();
//            outputStream.write(bytes, 0, bytes.length);
//        }// end if

        System.out.println("[INFO]from service");
        PrintWriter out = res.getWriter();
        ArrayList<NBArticle> list = ArticleManager.getInstance().queryAllArticle();
        if (null == list || 0 == list.size()) return;

//        out.println("size=" + list.size());
        for (NBArticle item : list) {
            System.out.println(item.dump());
//            out.println(item.dump());
            System.out.println("[INFO]row=" + item.dump() + "\n");
        }
        // set layout
        LinearLayout linearLayout = new LinearLayout();
        // add dialog
        AddArticleDialog dialog = new AddArticleDialog();
        linearLayout.addChildView(dialog);
        // add table
        TableView<NBArticle> tableView = new TableView<NBArticle>(list);
        linearLayout.addChildView(tableView);
        // out html
        out.print(TempHeader.dumpResponseHeaderString());
        out.print(linearLayout.html());
        out.flush();
        out.close();
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
