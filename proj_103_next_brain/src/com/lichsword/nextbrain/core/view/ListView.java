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
            sb.append(String.format("<span id=\"index\" class=\"badge\">%d</span>", i));
//            String visitLevelString = (NBArticle.VL_PRIVAET == article.getVisitLevel() ? "私有" : "公开");
//            sb.append(String.format("<p>权限：%s</p>", visitLevelString));
//            String statusString = (NBArticle.ST_FIXED == article.getStatus() ? "已解决" : "未解决");
//            sb.append(String.format("<p>状态：%s</p>", statusString));
            sb.append(String.format("<p><b>问题：</b><span id=\"content-question\">%s</span></p>", article.getQuestion()));
            sb.append(String.format("<p><b>描述：</b><span id=\"content-desc\">%s</span></p>", article.getDesc()));
            sb.append(String.format("<p><b>标签：</b><span id=\"content-labels\">%s</span></p>", article.getLabels()));
            sb.append(String.format("<p><b>本质：</b><span id=\"content-truth\">%s</span></p>", article.getTruth()));
            sb.append(String.format("<p><b>模式：</b><span id=\"content-pattern\">%s</span></p>", article.getPattern()));
            sb.append(String.format("<blockquote><h5 id=\"ref\">引用：%s</h5></blockquote>", article.getReference()));
            sb.append(String.format("<p><b>示例：</b><span id=\"content-example\">%s</span></p>", article.getExample()));
//            sb.append(String.format("<h5>创建时间：%s</h5>", article.getCreateTime()));
            sb.append("<div class=\"row\">");
            sb.append(String.format(
                    "<div id=\"modified-time\" class=\"col-md-7\">%s</div>", article.getModifiedTime()));
            int readCount = article.getReadCount();
            String readCountString =
                    (readCount > 10000)
                            ? String.format("%.0f万", (float)readCount / 10000)
                            : String.format("%d", readCount);
            sb.append(String.format(
                    "<div class=\"col-md-1\">"
                            + "<span class=\"glyphicon glyphicon-eye-open color-gray\"></span>"
                            + "<span id=\"read-count\"> %s</span>"
                            + "</div>", readCountString));
            int visitLevel = article.getVisitLevel();
            sb.append(String.format(
                    "<div id=\"article-id\" class=\"col-md-1\">"
                            + "ID %d"
                            + "</div>", article.getId()));
            if (NBArticle.VL_PRIVAET == visitLevel) {
                // "私有"显示“锁”图标，否则不显示
                sb.append(
                        "<div class=\"col-md-1\">"
                                + "<span id=\"visit\" class=\"glyphicon glyphicon-lock\"></span>"
                                + "</div>"
                );
            }// end if

            int status = article.getStatus();
            if (NBArticle.ST_FIXED != status) {
                // "未解决"则显示“锁”图标，否则不显示
                sb.append(
                        "<div class=\"col-md-1\">"
                                + "<span id=\"status\"class=\"glyphicon glyphicon-question-sign\"></span>"
                                + "</div>"
                );
            }// end if
            sb.append("</div>");
            sb.append("<hr/>");
        }// end for
        sb.append("</div>");
        return sb.toString();  // TODO
    }
}
