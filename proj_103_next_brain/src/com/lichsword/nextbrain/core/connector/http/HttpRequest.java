package com.lichsword.nextbrain.core.connector.http;

import com.lichsword.nextbrain.backup.Request;

import java.io.InputStream;

/**
 * Created with IntelliJ IDEA.
 * User: lichsword
 * Date: 14-3-24
 * Time: 下午7:32
 * <p/>
 * TODO
 */
public class HttpRequest extends Request {

    public HttpRequest(InputStream input) {
        super(input);
    }
}
