package com.lichsword.nextbrain.core.res;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created with IntelliJ IDEA.
 * User: lichsword
 * Date: 14-3-24
 * Time: 上午10:46
 * <p/>
 * TODO
 */
public class HtmlResource extends TextResource {

    protected static final String ERROR_MESSAGE =
            "HTTP/1.1 404 File Not Found\r\n"
            + "Content-Type: text/html\r\n"
            + "Content-Length: 23\r\n" + "\r\n"
            + "<h1>File Not Found</h1>";

    public HtmlResource(String uri) {
        super(uri);
    }

    @Override
    public boolean write(PrintWriter writer) {
        try {
            return super.write(writer);// TODO
        } catch (IOException e) {
            e.printStackTrace();
            writer.print(ERROR_MESSAGE);
            return false;
        }
    }

}
