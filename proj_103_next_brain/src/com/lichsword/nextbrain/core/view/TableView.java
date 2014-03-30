package com.lichsword.nextbrain.core.view;

import com.lichsword.nextbrain.nb.table.NBArticle;
import com.lichsword.nextbrain.nb.table.NBArticle.Column;
import com.lichsword.nextbrain.nb.table.NBArticle.ColumnDN;

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

            // table column define
            sb.append("<table border=\"1\">");
            sb.append("<tr>");
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

            for (T item : list) {
                NBArticle article = (NBArticle) item;
                // table rows
                sb.append("<tr>");
                sb.append(String.format("<td>%d</td>", article.getId()));
                sb.append(String.format("<td>%d</td>", article.getVisitLevel()));
                sb.append(String.format("<td>%d</td>", article.getReadCount()));
                sb.append(String.format("<td>%d</td>", article.getStatus()));
                sb.append(String.format("<td>%s</td>", article.getQuestion()));
                sb.append(String.format("<td>%s</td>", article.getDesc()));
                sb.append(String.format("<td>%s</td>", article.getLabels()));
                sb.append(String.format("<td>%s</td>", article.getTruth()));
                sb.append(String.format("<td>%s</td>", article.getPattern()));
                sb.append(String.format("<td>%s</td>", article.getReference()));
                sb.append(String.format("<td>%s</td>", article.getExample()));
                sb.append(String.format("<td>%s</td>", article.getCreateTime()));
                sb.append(String.format("<td>%s</td>", article.getModifiedTime()));
                sb.append(String.format("<td>%s</td>", "<button data-id='" + article.getId() + "' class=\"item_delete\">删除</button>"));
                sb.append("</tr>");
            }
            sb.append("</table>");
            return sb.toString();
        }
        return null;
    }

}