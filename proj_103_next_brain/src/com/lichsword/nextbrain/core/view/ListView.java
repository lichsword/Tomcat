package com.lichsword.nextbrain.core.view;

import com.lichsword.nextbrain.nb.model.NBArticle;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: lichsword
 * Date: 14-4-9
 * Time: 下午3:15
 * <p/>
 * TODO
 */
public class ListView<T> extends View {

    private ArrayList<T> list;

    public ListView() {
        // do nothing
    }

    public ListView(ArrayList<T> list) {
        this.list = list;
    }

    public void setList(ArrayList<T> list) {
        this.list = list;
    }

    @Override
    public String html() {
        if (null == list || 0 == list.size())
            return "";

        StringBuilder sb = new StringBuilder();

        sb.append("<div id=\"card_item\">");
        int i = 0;
        for (T item : list) {
            NBArticle article = (NBArticle) item;
            i++;
//            sb.append(String.format("<p>序号：%d</p>", i));
            sb.append(String.format("<span class=\"badge\">%d</span>", i));


//            String visitLevelString = (NBArticle.VL_PRIVAET == article.getVisitLevel() ? "私有" : "公开");
//            sb.append(String.format("<p>权限：%s</p>", visitLevelString));


//            String statusString = (NBArticle.ST_FIXED == article.getStatus() ? "已解决" : "未解决");
//            sb.append(String.format("<p>状态：%s</p>", statusString));
            sb.append("<div class=\"row\">");
            sb.append(String.format("<div class=\"col-md-7\">问题：%s</div>", article.getQuestion()));
            sb.append(String.format(
                    "<div class=\"col-md-1\">"
                    + "<span class=\"glyphicon glyphicon-eye-open\"> %d</span>"
                    + "</div>", article.getReadCount()));
            int visitLevel = article.getVisitLevel();
            sb.append(String.format(
                    "<div class=\"col-md-1\">"
                    + "ID<span class=\"badge\">%d</span>"
                    + "</div>", article.getId()));
            if (NBArticle.VL_PRIVAET == visitLevel) {
                // "私有"显示“锁”图标，否则不显示
                sb.append(
                    "<div class=\"col-md-1\">"
                    + "<span class=\"glyphicon glyphicon-lock\"></span>"
                    + "</div>"
                );
            }// end if

            int status = article.getStatus();
            if (NBArticle.ST_FIXED != status) {
                // "未解决"则显示“锁”图标，否则不显示
                sb.append(
                    "<div class=\"col-md-1\">"
                    + "<span class=\"glyphicon glyphicon-question-sign\"></span>"
                    + "</div>"
                );
            }// end if

            sb.append("</div>");
            sb.append(String.format("<h5>描述：%s</h5>", article.getDesc()));
            sb.append(String.format("<p>标签：%s</p>", article.getLabels()));
            sb.append(String.format("<p>本质：%s</p>", article.getTruth()));
            sb.append(String.format("<p>模式：%s</p>", article.getPattern()));
            sb.append(String.format("<blockquote><p>引用：%s</p></blockquote>", article.getReference()));
            sb.append(String.format("<p>示例：%s</p>", article.getExample()));
//            sb.append(String.format("<h5>创建时间：%s</h5>", article.getCreateTime()));
            sb.append(String.format("<span class=\"badge\">%s</span>", article.getModifiedTime()));
            sb.append("<hr/>");
        }// end for
        sb.append("</div>");
        return sb.toString();  // TODO
    }
}
