package com.lichsword.nextbrain.nb.business.servlet;

import com.lichsword.nextbrain.backup.Request;
import com.lichsword.nextbrain.core.Log;
import com.lichsword.nextbrain.core.connector.http.IHttpParameter;
import com.lichsword.nextbrain.core.page.HttpPage;
import com.lichsword.nextbrain.core.view.LinearLayout;
import com.lichsword.nextbrain.core.view.TableView;
import com.lichsword.nextbrain.core.view.TipTextView;
import com.lichsword.nextbrain.nb.business.ArticleManager;
import com.lichsword.nextbrain.nb.table.NBArticle;
import com.lichsword.nextbrain.nb.table.NBArticle.Column;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

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
                    // start set parameters.
                    article.setVisitLevel(Integer.valueOf(map.get(Column.VISIT_LEVEL)[0]));
                    Random random = new Random();
                    article.setReadCount(random.nextInt());
                    article.setStatus(Integer.valueOf(map.get(Column.STATUS)[0]));
                    article.setQuestion(map.get(Column.QUESTION)[0]);
                    article.setDesc(map.get(Column.DESC)[0]);
                    article.setLabels(map.get(Column.LABELS)[0]);
                    article.setTruth(map.get(Column.TRUTH)[0]);
                    article.setPattern(map.get(Column.PATTERN)[0]);
                    article.setReference(map.get(Column.REFERENCE)[0]);
                    article.setExample(map.get(Column.EXAMPLE)[0]);
                    // auto get current date, format to time string.
                    SimpleDateFormat sdf = new SimpleDateFormat(NBArticle.TIME_FORMAT);
                    String date = sdf.format(new Date());
                    article.setCreateTime(date);
                    // TODO temp modified same as create time?
                    article.setModifiedTime(date);
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
        // add tip text view TODO 这里可以动态设置文案。
        TipTextView tipTextView = new TipTextView();
        tipTextView.setLevel(TipTextView.ERROR);
        tipTextView.setText("tip text show here");
        linearLayout.addChildView(tipTextView);
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
