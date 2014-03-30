package com.lichsword.nextbrain.core.view;

/**
 * Created with IntelliJ IDEA.
 * User: lichsword
 * Date: 14-3-30
 * Time: 下午7:01
 * <p/>
 * TODO
 */
public class TextView extends
        View{

    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String html() {
        return null;  // TODO
    }
}
