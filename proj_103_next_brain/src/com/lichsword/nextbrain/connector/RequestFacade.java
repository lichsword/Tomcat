package com.lichsword.nextbrain.connector;

import com.lichsword.nextbrain.IRequest;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: lichsword
 * Date: 14-3-19
 * Time: 下午7:58
 * <p/>
 * TODO
 */
public class RequestFacade implements ServletRequest {

    /**
     * The wrapped request.
     */
    protected ServletRequest request = null;

    /**
     * Construct a wrapper for the specified request.
     *
     * @param request The request to be wrapped
     */
    public RequestFacade(IRequest request) {
        super();
        this.request = (ServletRequest) request;
    }


    @Override
    public Object getAttribute(String name) {
        return null;  // TODO
    }

    @Override
    public Enumeration getAttributeNames() {
        return null;  // TODO
    }

    @Override
    public String getCharacterEncoding() {
        return null;  // TODO
    }

    @Override
    public void setCharacterEncoding(String env) throws UnsupportedEncodingException {
        // TODO
    }

    @Override
    public int getContentLength() {
        return 0;  // TODO
    }

    @Override
    public String getContentType() {
        return null;  // TODO
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        return null;  // TODO
    }

    @Override
    public String getParameter(String name) {
        return null;  // TODO
    }

    @Override
    public Enumeration getParameterNames() {
        return null;  // TODO
    }

    @Override
    public String[] getParameterValues(String name) {
        return new String[0];  // TODO
    }

    @Override
    public Map getParameterMap() {
        return null;  // TODO
    }

    @Override
    public String getProtocol() {
        return null;  // TODO
    }

    @Override
    public String getScheme() {
        return null;  // TODO
    }

    @Override
    public String getServerName() {
        return null;  // TODO
    }

    @Override
    public int getServerPort() {
        return 0;  // TODO
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return null;  // TODO
    }

    @Override
    public String getRemoteAddr() {
        return null;  // TODO
    }

    @Override
    public String getRemoteHost() {
        return null;  // TODO
    }

    @Override
    public void setAttribute(String name, Object o) {
        // TODO
    }

    @Override
    public void removeAttribute(String name) {
        // TODO
    }

    @Override
    public Locale getLocale() {
        return null;  // TODO
    }

    @Override
    public Enumeration getLocales() {
        return null;  // TODO
    }

    @Override
    public boolean isSecure() {
        return false;  // TODO
    }

    @Override
    public RequestDispatcher getRequestDispatcher(String path) {
        return null;  // TODO
    }

    @Override
    public String getRealPath(String path) {
        return null;  // TODO
    }

    @Override
    public int getRemotePort() {
        return 0;  // TODO
    }

    @Override
    public String getLocalName() {
        return null;  // TODO
    }

    @Override
    public String getLocalAddr() {
        return null;  // TODO
    }

    @Override
    public int getLocalPort() {
        return 0;  // TODO
    }
}
