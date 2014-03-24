package com.lichsword.nextbrain.nb.dialog;

import com.lichsword.nextbrain.core.view.View;

/**
 * Created with IntelliJ IDEA.
 * User: lichsword
 * Date: 14-3-18
 * Time: 下午10:47
 * <p/>
 * TODO
 */
public class AddArticleDialog extends View {

    public AddArticleDialog() {
    }

    @Override
    public String html() {
        StringBuilder sb = new StringBuilder();
//        style="display:none"
//        class="dialog"
        sb.append("<div>");
        sb.append("<h3>添加记录</h3>");
        sb.append("<form>");
        sb.append("标题");
        sb.append("<input type=\"text\" name=\"tv_title\" />");
        sb.append("<br />");
        sb.append("简介");
        sb.append("<input type=\"text\" name=\"tv_summary\" />");
        sb.append("<br />");
        sb.append("标签");
        sb.append("<input type=\"text\" name=\"tv_labels\" />");
        sb.append("<br />");
        sb.append("<input type=\"submit\" value=\"提交\" />");
        sb.append("<br />");
        sb.append("<input type=\"submit\" value=\"取消\" />");
        sb.append("</form>");
        sb.append("<hr></hr>");
        sb.append("</div>");
        // TODO
        return sb.toString();
    }


}
