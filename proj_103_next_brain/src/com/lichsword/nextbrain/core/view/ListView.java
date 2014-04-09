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

        sb.append("<div id=\"card_question\">");
        int i = 0;
        for (T item : list) {
            NBArticle article = (NBArticle) item;
            i++;
            sb.append(String.format("<p>%d<p>", i));
            sb.append(String.format("<p>%d<p>", article.getId()));
            String visitLevelString = (NBArticle.VL_PRIVAET == article.getVisitLevel() ? "私有" : "公开");
            sb.append(String.format("<p>%s</p>", visitLevelString));
            sb.append(String.format("<p>%d<p>", article.getReadCount()));
            String statusString = (NBArticle.ST_FIXED == article.getStatus() ? "已解决" : "未解决");
            sb.append(String.format("<p>%s</p>", statusString));
            sb.append(String.format("<p>%s<p>", article.getQuestion()));
            sb.append(String.format("<p>描述：%s<p>", article.getDesc()));
            sb.append(String.format("<p>标签：%s<p>", article.getLabels()));
            sb.append(String.format("<p>本质：%s<p>", article.getTruth()));
            sb.append(String.format("<p>模式：%s<p>", article.getPattern()));
            sb.append(String.format("<p>引用：%s<p>", article.getReference()));
            sb.append(String.format("<p>示例：%s<p>", article.getExample()));
            sb.append(String.format("<p>示例：%s<p>", article.getCreateTime()));
            sb.append(String.format("<p>示例：%s<p>", article.getModifiedTime()));
            sb.append("<br><hr/><br>");
        }// end for
        sb.append("</div>");
        return sb.toString();  // TODO
    }
}
