package com.lichsword.nextbrain.connector;

import com.lichsword.nextbrain.IConnector;
import com.lichsword.nextbrain.IRequest;
import com.lichsword.nextbrain.IResponse;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Locale;

/**
 * Created with IntelliJ IDEA.
 * User: lichsword
 * Date: 14-3-19
 * Time: 下午7:39
 * <p/>
 * TODO
 */
public class ResponseBase implements ServletResponse, IResponse {

    public void setConnector(IConnector connector) {
        // TODO
    }

    @Override
    public String getCharacterEncoding() {
        return null;  // TODO
    }

    @Override
    public String getContentType() {
        return null;  // TODO
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return null;  // TODO
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        return null;  // TODO
    }

    @Override
    public void setCharacterEncoding(String charset) {
        // TODO
    }

    @Override
    public void setContentLength(int len) {
        // TODO
    }

    @Override
    public void setContentType(String type) {
        // TODO
    }

    @Override
    public void setBufferSize(int size) {
        // TODO
    }

    @Override
    public int getBufferSize() {
        return 0;  // TODO
    }

    @Override
    public void flushBuffer() throws IOException {
        // TODO
    }

    @Override
    public void resetBuffer() {
        // TODO
    }

    @Override
    public boolean isCommitted() {
        return false;  // TODO
    }

    @Override
    public void reset() {
        // TODO
    }

    @Override
    public void setLocale(Locale loc) {
        // TODO
    }

    @Override
    public Locale getLocale() {
        return null;  // TODO
    }

    protected OutputStream outputStream = null;

    public void setStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    protected IRequest request = null;

    public void setRequest(IRequest request) {
        this.request = request;
    }
}