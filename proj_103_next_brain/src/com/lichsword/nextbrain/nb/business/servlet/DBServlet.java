package com.lichsword.nextbrain.nb.business.servlet;

import com.lichsword.nextbrain.backup.Request;
import com.lichsword.nextbrain.core.Log;
import com.lichsword.nextbrain.core.connector.http.IHttpParameter;
import com.lichsword.nextbrain.core.page.HttpPage;
import com.lichsword.nextbrain.core.view.LinearLayout;
import com.lichsword.nextbrain.core.view.TableView;
import com.lichsword.nextbrain.nb.business.ArticleManager;
import com.lichsword.nextbrain.nb.table.NBArticle;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
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
public class DBServlet extends HttpPage {

    private static final String TAG = DBServlet.class.getSimpleName();

    public DBServlet() {
        /**
         * TODO 这里写死，总会遇到网页内容达到上限而只显示一半的问题, P1 解决.
         * ArticlePage 同样有这个问题
         */
        setContentLength(10000); // TODO must be right real count.
        setContentType("text/html");
    }

    @Override
    public void onPreRender(ServletRequest servletRequest) {
        try {
            handleAction(servletRequest);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRenderPage(PrintWriter out) {
        try {
            refreshTable(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        refreshTable(res.getWriter());
    }

    private void refreshTable(PrintWriter out) throws IOException {
        System.out.println("[INFO]from service");
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
//        out.print(TempHeader.dumpResponseHeaderString());
        out.print(linearLayout.html());
        out.flush();
        out.close();
    }


}
