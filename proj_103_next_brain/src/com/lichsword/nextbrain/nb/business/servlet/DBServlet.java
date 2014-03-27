package com.lichsword.nextbrain.nb.business.servlet;

import com.lichsword.nextbrain.backup.Request;
import com.lichsword.nextbrain.core.Log;
import com.lichsword.nextbrain.core.connector.http.IHttpParameter;
import com.lichsword.nextbrain.core.view.LinearLayout;
import com.lichsword.nextbrain.core.view.TableView;
import com.lichsword.nextbrain.nb.business.ArticleManager;
import com.lichsword.nextbrain.nb.table.NBArticle;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: lichsword
 * Date: 14-3-11
 * Time: 下午2:36
 * To change this res use File | Settings | File Templates.
 */
public class DBServlet implements Servlet {

    private static final String TAG = DBServlet.class.getSimpleName();

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
        handleAction(req);
        refreshTable(res);
    }

    /**
     * 依据 action 的类型，执行相关操作。
     */
    private void handleAction(ServletRequest req) throws ServletException {
        if (req instanceof Request) {
            Request request = (Request) req;
            String method = request.getMethod();
            if (method.equals(IHttpParameter.METHOD_POST)) {
                HashMap<String, String[]> map = request.getHttpParametersHashMap();
                String action = map.get(IHttpParameter.KEY_ACTION)[0];
                if (action.equals(IHttpParameter.ACTION_INSERT)) {
                    NBArticle article = new NBArticle();
                    article.setTitle(map.get("title")[0]);
                    article.setSummary(map.get("summary")[0]);
                    article.setLabels(map.get("labels")[0]);
                    ArticleManager.getInstance().addArticle(article);
                } else if (action.equals(IHttpParameter.ACTION_DELETE)) {
                    String idString = map.get("id")[0];
                    try {
                        int id = Integer.valueOf(idString);
                        ArticleManager.getInstance().delete(id);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.d(TAG, "param action not insert: action=" + action);
                }
            } else {
                Log.v(TAG, "method not POST: method=" + method);
            }
        } else {
            throw new ServletException("ServletRequest.classType.Not.Request");
        }
    }

    /**
     * 刷新表格
     *
     * @param res
     * @throws IOException
     */
    private void refreshTable(ServletResponse res) throws IOException {
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
