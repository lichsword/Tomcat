package com.lichsword.nextbrain.core.view;

import com.lichsword.nextbrain.nb.table.NBArticle;
import com.lichsword.nextbrain.nb.table.NBArticle.Column;

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
            sb.append(String.format("<th>%s</th>", Column.ID.toString()));
            sb.append(String.format("<th>%s</th>", Column.TITLE.toString()));
            sb.append(String.format("<th>%s</th>", Column.SUMMARY.toString()));
            sb.append(String.format("<th>%s</th>", Column.LABELS.toString()));
            sb.append(String.format("<th>%s</th>", "操作"));
            sb.append("</tr>");

            for (T item : list) {
                NBArticle article = (NBArticle) item;
                // table rows
                sb.append("<tr>");
                String idString = String.valueOf(article.getId());
                sb.append(String.format("<td>%s</td>", idString));
                sb.append(String.format("<td>%s</td>", article.getTitle()));
                sb.append(String.format("<td>%s</td>", article.getSummary()));
                sb.append(String.format("<td>%s</td>", article.getLabels()));
                sb.append(String.format("<td>%s</td>", "<button data-adcd='" + idString + "' class=\"item_delete\">删除</button>"));
                sb.append("</tr>");
            }
            sb.append("</table>");
            return sb.toString();
        }
        return null;
    }

}