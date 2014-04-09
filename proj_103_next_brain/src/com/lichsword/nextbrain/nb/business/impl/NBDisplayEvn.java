package com.lichsword.nextbrain.nb.business.impl;

/**
 * Created with IntelliJ IDEA.
 * User: lichsword
 * Date: 14-4-8
 * Time: 下午7:26
 * <p/>
 *
 * 记录显示配置参数，如显示模式（表格，卡片等）
 */
public class NBDisplayEvn {

    private static NBDisplayEvn sInstance;

    private NBDisplayEvn() {
    }

    public static NBDisplayEvn getInstance() {
        if (null == sInstance) {
            sInstance = new NBDisplayEvn();
        }// end if
        return sInstance;
    }

    public static final int MODE_TABLE = 0;
    public static final int MODE_CARD = 1;
    public static final int MODE_GRAPH = 2;
    public static final int MODE_DEFAULT = MODE_TABLE;

    private int mode = MODE_DEFAULT;

    public void setMode(int mode) {
        this.mode = mode;
    }

    public int getMode() {
        return mode;
    }

}
