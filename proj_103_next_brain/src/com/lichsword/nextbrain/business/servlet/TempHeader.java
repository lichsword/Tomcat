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

    public static String dumpResponseHeaderString() {
        StringBuilder sb = new StringBuilder();
        sb.append("HTTP/1.1 200 OK");
        sb.append('\n');
        sb.append("Server: Apache/1.3/11 BSafe-SSL/1.38 (Unix)");
        sb.append('\n');
        sb.append("Last-modified: Tue, 04 Jul 2000 09:46:21 GMT");
        sb.append('\n');
        sb.append("Content-length: 2403");
        sb.append('\n');
        sb.append("Content-Type: text/html; charset=UTF-8");
        sb.append('\n');
        sb.append('\n');
        return sb.toString();
    }

    public static byte[] dumpResponseHeaderBytes() {
        String string = dumpResponseHeaderString();
        return string.getBytes();
    }
}
