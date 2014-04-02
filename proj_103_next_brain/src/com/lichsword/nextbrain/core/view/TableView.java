package com.lichsword.nextbrain.core.view;

import com.lichsword.nextbrain.nb.model.NBArticle;
import com.lichsword.nextbrain.nb.model.NBArticle.ColumnDN;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: lichsword
 * Date: 14-3-17
 * Time: 下午10:28
 * <p/>
 * TODO
 */
public class TableView<T> extends View {

    private ArrayList<T> list;

    public TableView() {
    }

    public TableView(ArrayList<T> list) {
        this.list = list;
    }

    public void setList(ArrayList<T> list) {
        this.list = list;
    }

    @Override
    public String html() {
        if (null == list || 0 == list.size())
            return "";
        Object object = list.get(0);
        if (object instanceof Object) {
            StringBuilder sb = new StringBuilder();

            // model column define
            sb.append("<table id=\"tb_question\" border=\"1\">");
            sb.append("<tr>");
            sb.append(String.format("<th>%s</th>", "序号"));
            sb.append(String.format("<th>%s</th>", ColumnDN.ID.toString()));
            sb.append(String.format("<th>%s</th>", ColumnDN.VISIT_LEVEL.toString()));
            sb.append(String.format("<th>%s</th>", ColumnDN.READ_COUNT.toString()));
            sb.append(String.format("<th>%s</th>", ColumnDN.STATUS.toString()));
            sb.append(String.format("<th>%s</th>", ColumnDN.QUESTION.toString()));
            sb.append(String.format("<th>%s</th>", ColumnDN.DESC.toString()));
            sb.append(String.format("<th>%s</th>", ColumnDN.LABELS.toString()));
            sb.append(String.format("<th>%s</th>", ColumnDN.TRUTH.toString()));
            sb.append(String.format("<th>%s</th>", ColumnDN.PATTERN.toString()));
            sb.append(String.format("<th>%s</th>", ColumnDN.REFERENCE.toString()));
            sb.append(String.format("<th>%s</th>", ColumnDN.EXAMPLE.toString()));
            sb.append(String.format("<th>%s</th>", ColumnDN.CREATE_TIME.toString()));
            sb.append(String.format("<th>%s</th>", ColumnDN.MODIFIED_TIME.toString()));
            sb.append(String.format("<th>%s</th>", "操作"));
            sb.append("</tr>");

            int i = 0;
            for (T item : list) {

                NBArticle article = (NBArticle) item;
                // ------------ set row class
                // model rows
                if (0 == i % 2) {
                    sb.append("<tr class=\"even\">");
                } else {
                    sb.append("<tr class=\"odd\">");
                }// end if
                i++;// index inc.
                // ------------
                sb.append(String.format("<td>%d</td>", i));
                sb.append(String.format("<td>%d</td>", article.getId()));
                String visitLevelString = (NBArticle.VL_PRIVAET == article.getVisitLevel() ? "私有" : "公开");
                sb.append(String.format("<td>%s</td>", visitLevelString));
                sb.append(String.format("<td>%d</td>", article.getReadCount()));
                String statusString = (NBArticle.ST_FIXED == article.getStatus() ? "已解决" : "未解决");
                sb.append(String.format("<td>%s</td>", statusString));
                sb.append(String.format("<td>%s</td>", ellipsesString(article.getQuestion(), LIMIT)));
                sb.append(String.format("<td>%s</td>", ellipsesString(article.getDesc(), LIMIT)));
                sb.append(String.format("<td>%s</td>", ellipsesString(article.getLabels(), LIMIT)));
                sb.append(String.format("<td>%s</td>", ellipsesString(article.getTruth(), LIMIT)));
                sb.append(String.format("<td>%s</td>", ellipsesString(article.getPattern(), LIMIT)));
                sb.append(String.format("<td>%s</td>", ellipsesString(article.getReference(), LIMIT)));
                sb.append(String.format("<td>%s</td>", ellipsesString(article.getExample(), LIMIT)));
                sb.append(String.format("<td>%s</td>", article.getCreateTime()));
                sb.append(String.format("<td>%s</td>", article.getModifiedTime()));
                sb.append(String.format("<td>%s</td>", "<button data-id='" + article.getId() + "' class=\"item_delete\">删除</button>"));
                sb.append("</tr>");
            }// end for
            sb.append("</table>");
            return sb.toString();
        }
        return null;
    }

    private final int LIMIT = 15;


    /**
     * 以省略号字符显示
     *
     * @param string
     * @param limit
     * @return
     */
    public String ellipsesString(String string, int limit) {
        StringBuilder sb = new StringBuilder();
        if (string.length() > limit) {
            sb.append(string.substring(0, limit));
            sb.append("...");
        } else {
            sb.append(string);
        }
        return sb.toString();
    }


}