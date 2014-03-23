package com.lichsword.nextbrain.business.servlet.page;

import com.lichsword.nextbrain.HttpHeader;

import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * Created with IntelliJ IDEA.
 * User: lichsword
 * Date: 14-3-23
 * Time: 下午10:10
 * <p/>
 * TODO
 */
public interface IHttpPage extends IPage {

    public void renderHttpHeader(OutputStream outputStream, HttpHeader[] httpHeaders);

}
