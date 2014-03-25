package com.lichsword.nextbrain.core;

/**
 * Created with IntelliJ IDEA.
 * User: lichsword
 * Date: 14-3-25
 * Time: 下午3:38
 * <p/>
 * TODO
 */
public class Log {

    //    03-25 15:42:36.687  12798-12798/com.lichsword.checksum.app D/MainActivity﹕ string=helloworldshowmethemoneygoodbye

    /**
     * Verbose log
     * @param tag
     * @param message
     */
    public static void v(String tag, String message) {
        log("v", tag, message);
    }

    /**
     * Debug log
     * @param tag
     * @param message
     */
    public static void d(String tag, String message) {
        log("D", tag, message);
    }

    /**
     * Info log
     * @param tag
     * @param message
     */
    public static void i(String tag, String message) {
        log("I", tag, message);
    }

    /**
     * Warning log
     * @param tag
     * @param message
     */
    public static void w(String tag, String message) {
        log("W", tag, message);
    }

    /**
     * Error log
     * @param tag
     * @param message
     */
    public static void e(String tag, String message) {
        log("E", tag, message);
    }

    /**
     *
     * @param level
     * @param tag
     * @param message
     */
    private static void log(String level, String tag, String message) {
        System.out.println(String.format("%s/%s: %s", level, tag, message));
    }
}
