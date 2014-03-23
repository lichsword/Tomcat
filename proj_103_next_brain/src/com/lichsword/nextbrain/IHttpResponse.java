package com.lichsword.nextbrain;

/**
 * Created with IntelliJ IDEA.
 * User: lichsword
 * Date: 14-3-23
 * Time: 下午8:59
 * <p/>
 * TODO
 */
public interface IHttpResponse extends IResponse {

    public void addHeader(String name, String value);

    public void addHeader(HttpHeader header);

}
