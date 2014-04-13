package com.lichsword.nextbrain.core.view;

import com.lichsword.nextbrain.core.Log;

/**
 * Created with IntelliJ IDEA.
 * User: lichsword
 * Date: 14-3-30
 * Time: 下午7:14
 * <p/>
 * TODO
 */
public class TipTextView extends TextView {

    private static final String TAG = TipTextView.class.getSimpleName();

    /**
     * 错误的文本
     */
    public static final int ERROR = 1;
    /**
     * 警告的文本
     */
    public static final int WARNING = 2;
    /**
     * 啰嗦的文本
     */
    public static final int VERBOSE = 3;
    /**
     * 信息的文本
     */
    public static final int INFO = 4;

    private int level;

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String html() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("<span class=\"help-block\">%s</span>", getText()));
        return sb.toString();
    }

    /**
     * @deprecated
     *
     * @param level
     * @return
     */
    private String getLevelCssName(int level) {
        String cssName;
        switch (level) {
            case ERROR:
                cssName = "tip_error";
                break;
            case INFO:
                cssName = "tip_info";
                break;
            case WARNING:
                cssName = "tip_warning";
                break;
            case VERBOSE:
                cssName = "tip_verbose";
                break;
            default:
                cssName = "tip_verbose";
                Log.d(TAG, "Default css name");
                break;
        }
        Log.d(TAG, "choose css class name:" + cssName);
        return cssName;
    }
}
