package com.lichsword.nextbrain.http;

import com.lichsword.nextbrain.HttpHeader;
import com.lichsword.nextbrain.IHttpResponse;
import com.lichsword.nextbrain.backup.Response;

import javax.servlet.http.Cookie;
import java.io.OutputStream;
import java.security.Principal;
import java.util.Locale;

/**
 * Created with IntelliJ IDEA.
 * User: lichsword
 * Date: 14-3-23
 * Time: 下午6:50
 * <p/>
 * TODO
 */
public class HttpResponse extends Response implements IHttpResponse{

    public HttpResponse(OutputStream output) {
        super(output);
    }

    @Override
    public void addHeader(String name, String value) {
        // TODO
    }

    @Override
    public void addHeader(HttpHeader header) {
        // TODO
    }
}
