package com.lichsword.nextbrain.core.http;

import com.lichsword.nextbrain.core.connector.HttpHeader;
import com.lichsword.nextbrain.core.connector.IHttpResponse;
import com.lichsword.nextbrain.backup.Response;

import java.io.OutputStream;

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
