package com.lichsword.nextbrain.business.servlet;

/**
 * Created with IntelliJ IDEA.
 * User: lichsword
 * Date: 14-3-23
 * Time: 上午10:03
 * <p/>
 * TODO
 */
public class TempHeader {

    public static String dumpHeaderString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Host: www.zhihu.com");
        sb.append("User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10.9; rv:27.0) Gecko/20100101 Firefox/27.0");
        sb.append("Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        sb.append("Accept-Language: zh-cn,en-us;q=0.7,en;q=0.3");
        sb.append("Accept-Encoding: gzip, deflate");
        sb.append("Cookie:");
        sb.append("Connection: keep-alive");
        sb.append("Cache-Control: max-age=0");
        sb.append("\n\n");
        sb.append("");
        sb.append("");
        return sb.toString();
    }
}
