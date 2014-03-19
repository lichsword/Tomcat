package com.lichsword.nextbrain.view;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lichsword
 * Date: 14-3-19
 * Time: 上午11:43
 * <p/>
 * TODO
 */
public class LinearLayout extends View {

    List<View> children = new ArrayList<View>();

    public void addChildView(int location, View view) {
        children.add(0, view);
    }

    public void addChildView(View view) {
        children.add(view);
    }

    @Override
    public String html() {
        if (null == children || 0 == children.size()) {
            return null;
        }// end if

        StringBuilder sb = new StringBuilder();
        sb.append("<div>");
        for (View view : children) {
            sb.append(view.html());
        }
        sb.append("</div>");
        return sb.toString();
    }
}
